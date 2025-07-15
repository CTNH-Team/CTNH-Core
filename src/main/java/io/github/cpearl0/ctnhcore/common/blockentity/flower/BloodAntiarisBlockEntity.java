package io.github.cpearl0.ctnhcore.common.blockentity.flower;

import io.github.cpearl0.ctnhcore.data.tags.FluidTypeTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.block_entity.RadiusDescriptor;
import vazkii.botania.common.block.flower.generating.FluidGeneratorBlockEntity;
import wayoftime.bloodmagic.api.compat.EnumDemonWillType;
import wayoftime.bloodmagic.demonaura.WorldDemonWillHandler;

public class BloodAntiarisBlockEntity extends FluidGeneratorBlockEntity {
    public BloodAntiarisBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, FluidTypeTags.BLOOD, 200, 500);
    }

    @Override
    public int getMaxMana() {
        return 10000;
    }

    @Override
    public int getColor() {
        return 0xbd1c1c;
    }
    public int getRange() {
        return 3;
    }

    @Override
    public int getCooldownTime(boolean b) {
        double will = 0;
        for (var willType: EnumDemonWillType.values()) {
            will = Math.max(WorldDemonWillHandler.getCurrentWill(getLevel(), getEffectivePos(), willType), will);
        }
        return (int) (300 * (100 - will) / 100);
    }

    @Override
    public void doBurnParticles() {

    }

    @Override
    public void playSound() {

    }

    @Override
    public @Nullable RadiusDescriptor getRadius() {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), getRange());
    }
}
