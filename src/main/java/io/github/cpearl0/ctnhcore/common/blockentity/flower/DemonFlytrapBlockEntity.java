package io.github.cpearl0.ctnhcore.common.blockentity.flower;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.block_entity.FunctionalFlowerBlockEntity;
import vazkii.botania.api.block_entity.RadiusDescriptor;
import wayoftime.bloodmagic.api.compat.EnumDemonWillType;
import wayoftime.bloodmagic.demonaura.WorldDemonWillHandler;

import java.util.List;

public class DemonFlytrapBlockEntity extends FunctionalFlowerBlockEntity {
    public DemonFlytrapBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public int MAX_WILL = 100;
    @Override
    public void tickFlower() {
        super.tickFlower();
        if (!this.getLevel().isClientSide && this.redstoneSignal <= 0) {
            if (ticksExisted % 5 == 0) {
                var chunk = WorldDemonWillHandler.getWillChunk(getLevel(), getEffectivePos());
                if (getMana() < getCost()) return;
                if (chunk.getCurrentWill().getWill(EnumDemonWillType.DEFAULT) >= MAX_WILL) return;
                for (var monster : getMonsters()) {
                    if (getMana() < getCost()) {
                        break;
                    }
                    if (monster.getHealth() <= 4 && !monster.getPersistentData().getBoolean("isDead")) {
                        monster.kill();
                        monster.getPersistentData().putBoolean("isDead", true);
                        chunk.getCurrentWill().addWill(EnumDemonWillType.DEFAULT, monster.getMaxHealth() / 20, MAX_WILL);
                    } else {
                        monster.hurt(getLevel().damageSources().magic(), 4);
                    }
                    addMana(-getCost());
                }
            }
        }
    }
    public List<Monster> getMonsters() {
        var bound = new AABB(getEffectivePos()).inflate(getRange());
        return getLevel().getEntitiesOfClass(Monster.class, bound);
    }
    public int getRange() {
        return 5;
    }
    public int getCost() {
        return 1000;
    }
    @Override
    public int getMaxMana() {
        return this.getCost();
    }

    @Override
    public int getColor() {
        return 0x2af5e1;
    }

    @Override
    public @Nullable RadiusDescriptor getRadius() {
        return RadiusDescriptor.Rectangle.square(bindingPos, getRange());
    }
}
