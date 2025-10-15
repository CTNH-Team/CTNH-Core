package io.github.cpearl0.ctnhcore.event;

import appeng.block.crafting.PatternProviderBlock;
import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import earth.terrarium.adastra.api.systems.OxygenApi;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.enchantment.VacuumSealEnchantment;
import io.github.cpearl0.ctnhcore.legendary.UnderfloorHeatingSystemTempModifier;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.generator.WindPowerArrayMachine;
import io.github.cpearl0.ctnhcore.registry.adventure.CTNHEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sfiomn.legendarysurvivaloverhaul.registry.AttributeRegistry;

@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void tickBus(TickEvent.ServerTickEvent event) {
        if (event.getServer().getTickCount() % 40 == 0) {
            UnderfloorHeatingSystemTempModifier.UNDERFLOOR_HEATING_SYSTEM_RANGE.clear();
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void postLevelTickBus(TickEvent.LevelTickEvent event){
        ProvidableNetEventHandler.onPostTick(event);
        ProvidableNetEventHandler.onPreTick(event);
    }

    @SubscribeEvent
    public static void enchantmentTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.isCreative() || player.isSpectator()) return;
            player.getArmorSlots().forEach(armor -> {
                if (armor.getAllEnchantments().get(CTNHEnchantments.VACUUM_SEAL.get()) == null){
                    return;
                }
                player.setTicksFrozen(0);
                if (player.isEyeInFluid(FluidTags.WATER) && !player.level().getBlockState(BlockPos.containing(player.getX(), player.getEyeY(), player.getZ())).is(Blocks.BUBBLE_COLUMN)) {
                    player.setAirSupply(Math.min(player.getMaxAirSupply(), player.getAirSupply() + 4 * 10));
                }
            });
        }
    }

    @SubscribeEvent
    public static void onBlockPlaced(BlockEvent.EntityPlaceEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        if(event.getPlacedBlock().getBlock() instanceof PatternProviderBlock)
        {
            BlockPos placedPos = event.getPos();
            for (Direction dir : Direction.values()) {
                BlockPos neighborPos = placedPos.relative(dir);
                BlockEntity be = event.getLevel().getBlockEntity(neighborPos);
                if(be instanceof MetaMachineBlockEntity metaMachineBlockEntity
                        && metaMachineBlockEntity.getMetaMachine() instanceof SimpleTieredMachine tieredMachine)
                {
                    tieredMachine.setAutoOutputFluids(true);
                    tieredMachine.setAutoOutputItems(true);
                    tieredMachine.setOutputFacingFluids(dir.getOpposite());
                    tieredMachine.setOutputFacingItems(dir.getOpposite());
                    tieredMachine.setAllowInputFromOutputSideItems(true);
                    tieredMachine.setAllowInputFromOutputSideFluids(true);
                }
            }
        }
    }
}
