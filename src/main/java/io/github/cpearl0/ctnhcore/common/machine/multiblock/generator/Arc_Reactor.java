package io.github.cpearl0.ctnhcore.common.machine.multiblock.generator;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import com.ctnhlang.CN;
import com.ctnhlang.EN;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.RecipeElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

import java.util.List;

public class Arc_Reactor extends RecipeElectricMultiblockMachine implements ITieredMachine {

    @CN("可输出的电弧强度:%d")
    @EN("Output Arc Intensity: %d")
    public static Lang arcreactorArc;


    @CN("§b桥接已启用§r")
    @EN("§bBridge Enabled§r")
    public static Lang arcreactorConnect;



    public int arc;
    public boolean isconnect = false;
    public BlockPos pos;
    public Level level;

    @Override
    public void afterWorking() {
        super.afterWorking();
        if (!isconnect) {
            arc = arc;
        } else if (getMachine(level, pos) instanceof Arc_Generator gmachine && isconnect) {
            gmachine.arc += arc;
        }
    }

    public Arc_Reactor(IMachineBlockEntity holder, int arc) {
        super(holder);
        this.arc = arc;
    }

    public static Component recipeModifier(MetaMachine machine, RecipeHandlerGroup group, GTRecipe recipe) {
        if (machine instanceof Arc_Reactor dmachine) {
            var level = dmachine.self().getLevel();
            var pos = dmachine.self().getPos();

            pos = pos.offset(0, -5, 0);
            if (getMachine(level, pos) instanceof Arc_Generator gmachine) {
                dmachine.pos = pos;
                dmachine.level = level;
                dmachine.isconnect = true;
            } else {
                dmachine.isconnect = false;
            }
            return null;
        }
        return null;
    }

    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        var tier = getTier();
        if (isconnect) {
            textList.add(textList.size(), arcreactorConnect.translate());
        }
        textList.add(textList.size(),
                arcreactorArc.translate( String.format("%d", arc)));
    }

    @Override
    public boolean regressWhenWaiting() {
        return false;
    }
}
