package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

public class MeteorCaptureMachine extends WorkableElectricMultiblockMachine {
    public MeteorCaptureMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public boolean onWorking() {
        var pos = getPos();
        var level = getLevel();
        var progress = getProgress();
        var maxprogress = getMaxProgress();
        var recipe = getRecipeLogic().getLastOriginRecipe();
        var radius = recipe == null ? 8 : recipe.data.getInt("radius");
        var block = recipe == null ? Blocks.STONE : BuiltInRegistries.BLOCK.get(ResourceLocation.parse(recipe.data.getString("rock")));
        //build
        var center=pos.offset(0,15,0);
        if(radius<=0 || maxprogress<=15) return true;
        if(progress==0){
            for(var i=-radius;i<=radius;i++){
                for(var j=-radius;j<=radius;j++){
                    for(var k=-radius;k<=radius;k++){
                        if(i*i+j*j+k*k<=(radius+0.5)*(radius+0.5)){
                            if (level != null) {
                                level.setBlockAndUpdate(center.offset(i,j,k), block.defaultBlockState());
                            }
                        }
                    }
                }
            }
        }else if(progress>=maxprogress/5){
            var piece=maxprogress*0.8/(radius*2+2);
            var piece2=piece/(radius*2+2);
            if(piece2>1){
                var phase=progress-maxprogress/5;
                if(Math.floor(phase/piece2)>Math.floor((phase-1)/piece2)){
                    var i=-(Math.floor(phase/piece2)%(radius*2+2))+radius+1;
                    var j=-Math.floor(phase/piece)+radius+1;
                    for(var k=-radius;k<=radius;k++){
                        if(i*i+j*j+k*k<=(radius+0.5)*(radius+0.5)){
                            level.setBlockAndUpdate(center.offset((int) i, (int) j,k), Blocks.AIR.defaultBlockState());
                        }
                    }
                }
            }
            else{
                var phase=progress-maxprogress/5;
                if(Math.floor(phase/piece)>Math.floor((phase-1)/piece)){
                    var j=-Math.floor(phase/piece)+radius+1;
                    for(var i=-radius;i<=radius;i++){
                        for(var k=-radius;k<=radius;k++){
                            if(i*i+j*j+k*k<=(radius+0.5)*(radius+0.5)){
                                level.setBlockAndUpdate(center.offset(i, (int) j,k), Blocks.AIR.defaultBlockState());
                            }
                        }
                    }
                }
            }

        }
        return super.onWorking();
    }
}
