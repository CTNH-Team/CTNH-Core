package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import io.github.cpearl0.ctnhcore.api.Pattern.CTNHBlockMaps;
import io.github.cpearl0.ctnhcore.api.machine.feature.IDynamicCasing;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static java.lang.Math.min;

public class ChemicalPlantMachine extends WorkableElectricMultiblockMachine implements IDynamicCasing {
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            ChemicalPlantMachine.class, WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER);
    public int casingTier = 0;
    public int pipeTier = 0;
    public int coilTier = 0;
    public int voltageTier = 0;
    public ChemicalPlantMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        var context = getMultiblockState().getMatchContext();
        this.coilTier = context.getOrDefault("CoilType", 0);
        this.casingTier = context.getOrDefault("PlantCasing", 0);
        this.pipeTier = context.getOrDefault("Pipe", 0);
        this.voltageTier = context.getOrDefault("MachineCasing", 0);
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        coilTier = 0;
        pipeTier = 0;
        casingTier = 0;
        voltageTier = 0;
    }
    public int getSpeedMultiplier() {
        return coilTier * 50;
    }
    public int getMaxParallel() {
        return Math.max(((pipeTier) - 1) * 2 + 1, 1);
    }
    @Override
    public long getMaxVoltage() {
        return GTValues.V[voltageTier];
    }

    @Override
    protected @Nullable GTRecipe getRealRecipe(GTRecipe recipe) {
        if (voltageTier < GTValues.UHV && RecipeHelper.getRecipeEUtTier(recipe) > voltageTier) {
            return null;
        }
        var modified = super.getRealRecipe(recipe);
        if (casingTier > 0) {
            var copied = recipe == modified? modified.copy() : modified;
            if (copied != null) {
                copied.duration = (int) (copied.duration / (1 + coilTier * 0.5));
            }
            return copied;
        }
        return modified;
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        if (isFormed()) {
            textList.add(
                    Component.translatable(
                            "ctnh.multiblock.chemical_plant.info.heating_coil", coilTier * 50
                    )
            );
            textList.add(
                    Component.translatable(
                            "ctnh.multiblock.chemical_plant.info.parallel_level", pipeTier * 2
                    )
            );
            textList.add(
                    Component.translatable(
                            "ctnh.multiblock.chemical_plant.info.tier", GTValues.VNF[voltageTier]
                    )
            );
            textList.add(
                    Component.translatable(
                            "ctnh.multiblock.chemical_plant.info.chance", getChance()
                    )
            );
        }
    }
    public int getChance() {
        return (int) min((100 - 20 * (pipeTier - 1)), 100.0);
    }

    @Override
    public @NotNull BlockState getBlockAppearance(BlockState state, BlockAndTintGetter level, BlockPos pos, Direction side, BlockState sourceState, BlockPos sourcePos) {
        return getAppearance();
    }

    @Override
    public BlockState getAppearance() {
        if (isFormed()) {
            if (CTNHBlockMaps.CasingBlock.get(casingTier) == null) {
                return CTNHBlockMaps.CasingBlock.get(1).get().defaultBlockState();
            }
            return CTNHBlockMaps.CasingBlock.get(casingTier).get().defaultBlockState();
        }
        return CTNHBlockMaps.CasingBlock.get(1).get().defaultBlockState();
    }

    @Override
    public @Nullable BlockState getPartAppearance(IMultiPart part, Direction side, BlockState sourceState, BlockPos sourcePos) {
        var appearanceBlock = CTNHBlockMaps.CasingBlock.get(casingTier);
        return appearanceBlock != null ? appearanceBlock.get().defaultBlockState() : super.getPartAppearance(part, side, sourceState, sourcePos);
    }
}
