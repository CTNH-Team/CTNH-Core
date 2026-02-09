package io.github.cpearl0.ctnhcore.event;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.capability.EIOCapacitorProvider;
import io.github.cpearl0.ctnhcore.data.recipe.CTNHCraftingComponents;
import io.github.cpearl0.ctnhcore.integration.legendary.UnderfloorHeatingSystemTempModifier;
import io.github.cpearl0.ctnhcore.registry.adventure.CTNHEnchantments;

import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTRecipes;
import com.gregtechceu.gtceu.data.pack.GTDynamicDataPack;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.mojang.brigadier.CommandDispatcher;

@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void tickBus(TickEvent.ServerTickEvent event) {
        if (event.getServer().getTickCount() % 40 == 0) {
            UnderfloorHeatingSystemTempModifier.UNDERFLOOR_HEATING_SYSTEM_RANGE.clear();
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void postLevelTickBus(TickEvent.LevelTickEvent event) {
        ProvidableNetEventHandler.onPostTick(event);
        ProvidableNetEventHandler.onPreTick(event);
    }

    @SubscribeEvent
    public static void enchantmentTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.isCreative() || player.isSpectator()) return;
            player.getArmorSlots().forEach(armor -> {
                if (armor.getAllEnchantments().get(CTNHEnchantments.VACUUM_SEAL.get()) == null) {
                    return;
                }
                player.setTicksFrozen(0);
                if (player.isEyeInFluid(FluidTags.WATER) && !player.level()
                        .getBlockState(BlockPos.containing(player.getX(), player.getEyeY(), player.getZ()))
                        .is(Blocks.BUBBLE_COLUMN)) {
                    player.setAirSupply(Math.min(player.getMaxAirSupply(), player.getAirSupply() + 4 * 10));
                }
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(
                Commands.literal("gtreload")
                        .requires(src -> src.hasPermission(2)) // 权限等级，可选
                        .executes(context -> {
                            CommandSourceStack source = context.getSource();

                            GTRegistries.RECIPE_TYPES.forEach(
                                    r -> r.getLookup().removeAllRecipes());
                            GTRecipes.recipeRemoval();
                            GTRecipes.recipeAddition(GTDynamicDataPack::addRecipe);

                            source.sendSuccess(
                                    () -> Component.literal("配方重载完毕"),
                                    false);

                            return 1; // 返回执行结果
                        }));
    }

    @SubscribeEvent
    public static void attachItemStack(AttachCapabilitiesEvent<ItemStack> event) {
        var stack = event.getObject();
        Integer base = CTNHCraftingComponents.CAPACITOR_BASE_MAP.get(stack.getItem());
        if(base != null){
            event.addCapability(CTNHCore.id("eio_capacitor"), new EIOCapacitorProvider(base));
        }
    }
}
