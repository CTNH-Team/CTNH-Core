package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import io.github.cpearl0.ctnhcore.api.Pattern.CTNHBlockMaps;
import io.github.cpearl0.ctnhcore.api.machine.feature.IDynamicCasing;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.RecipeElectricMultiblockMachine;

import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Prefix;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.List;

import static java.lang.Math.min;

@Prefix("info.multiblock.chemical_plant")
public class ChemicalPlantMachine extends RecipeElectricMultiblockMachine implements IDynamicCasing {

    @Persisted
    @DescSynced
    public int casingTier = 0;
    @Persisted
    @DescSynced
    public int pipeTier = 0;
    @Persisted
    @DescSynced
    public int coilTier = 0;

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
        this.tier = Math.min(tier, context.getOrDefault("MachineCasing", 0));
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        coilTier = 0;
        pipeTier = 0;
        casingTier = 0;
    }

    public int getSpeedMultiplier() {
        return coilTier * 50;
    }

    public int getMaxParallel() {
        return Math.max(((pipeTier) - 1) * 2 + 1, 1);
    }

    @CN("§6提速: %s%%")
    @EN("§6Speed: %s%%")
    static Lang coil;

    @CN("§5并行: %s")
    @EN("§bParallel: %s")
    static Lang parallel;
    @CN("§6催化剂消耗概率: %s%%")
    @EN("§6Catalyst consumption probability:\n%s%%")
    static Lang chance;

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        if (isFormed()) {
            textList.add(
                    coil.translate(coilTier * 50));
            textList.add(
                    parallel.translate(pipeTier * 2));
            textList.add(
                    chance.translate(getChance()));
        }
    }

    public int getChance() {
        return (int) min((100 - 20 * (pipeTier - 1)), 100.0);
    }

    @Override
    public @NotNull BlockState getBlockAppearance(BlockState state, BlockAndTintGetter level, BlockPos pos,
                                                  Direction side, BlockState sourceState, BlockPos sourcePos) {
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
    public @Nullable BlockState getPartAppearance(IMultiPart part, Direction side, BlockState sourceState,
                                                  BlockPos sourcePos) {
        var appearanceBlock = CTNHBlockMaps.CasingBlock.get(casingTier);
        return appearanceBlock != null ? appearanceBlock.get().defaultBlockState() :
                super.getPartAppearance(part, side, sourceState, sourcePos);
    }
}
