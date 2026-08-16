package io.github.cpearl0.ctnhcore.event;

import io.github.cpearl0.ctnhcore.CTNHConfig;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.capability.EIOCapacitorProvider;
import io.github.cpearl0.ctnhcore.integration.legendary.UnderfloorHeatingSystemTempModifier;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.sound.CTNHSoundEvents;

import com.gregtechceu.gtceu.GTCEu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.MissingMappingsEvent;

import com.ctnh.ctnhastral.data.CAEnchantments;
import com.magicbee.ctnhmana.CTNHMana;
import com.mo_guang.ctpp.registry.CTPPItems;
import com.moguang.ctnhbio.CTNHBio;
import com.simibubi.create.AllItems;
import tech.luckyblock.mcmod.ctnhenergy.CTNHEnergy;

import java.util.List;
import java.util.Set;

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
                if (armor.getAllEnchantments().get(CAEnchantments.VACUUM_SEAL.get()) == null) {
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
    public static void onFoodFinished(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof Player player) || player.level().isClientSide) return;

        ItemStack consumed = event.getItem();
        if (consumed.is(ForgeEventHandler.itemId("farmersdelight", "rice"))) {
            giveOrDrop(player, CTNHItems.CRASHED_RICE.asStack());
        } else if (consumed.is(CTPPItems.DOUBLE_BLAZE_CAKE.get())) {
            giveOrDrop(player, AllItems.BLAZE_CAKE_BASE.asStack());
        }
    }

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity.level().isClientSide) return;
        if (!BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType())
                .equals(ResourceLocation.fromNamespaceAndPath("touhou_little_maid", "fairy")))
            return;

        if (!entity.level().dimension().location()
                .equals(ResourceLocation.fromNamespaceAndPath("mythicbotany", "alfheim"))) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onSoulTorchEasterEgg(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        if (player.level().isClientSide) {
            return;
        }
        if (!player.isShiftKeyDown()) {
            return;
        }

        BlockPos pos = event.getPos();
        var state = player.level().getBlockState(pos);
        if (!state.is(Blocks.SOUL_TORCH) && !state.is(Blocks.SOUL_WALL_TORCH)) {
            return;
        }

        event.setCanceled(true);
        Level level = player.level();

        // 生成一颗已激活的烟花火箭（带简单爆炸效果）
        ItemStack firework = new ItemStack(Items.FIREWORK_ROCKET);
        CompoundTag fireworks = new CompoundTag();
        fireworks.putByte("Flight", (byte) 1);
        CompoundTag explosion = new CompoundTag();
        explosion.putIntArray("Colors", new int[] { 0xFFFFFF, 0x55FFFF, 0xFF55FF });
        explosion.putByte("Type", (byte) 1);
        ListTag explosions = new ListTag();
        explosions.add(explosion);
        fireworks.put("Explosions", explosions);
        firework.getOrCreateTag().put("Fireworks", fireworks);

        FireworkRocketEntity rocket = new FireworkRocketEntity(
                level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, firework);
        level.addFreshEntity(rocket);

        // 同时播放 TNT 爆炸音效和《小丑小曲》彩蛋音效
        level.playSound(null, pos, SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 1.0F, 1.0F);
        level.playSound(null, pos, CTNHSoundEvents.EASTER_EGG_CLOWN.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

        // 触发后移除灵魂火把
        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
    }

    @SubscribeEvent
    public static void attachItemStack(AttachCapabilitiesEvent<ItemStack> event) {
        var stack = event.getObject();
        Integer base = EIOCapacitorProvider.getCapacitorBaseMap().get(stack.getItem());
        if (base != null) {
            event.addCapability(CTNHCore.id("eio_capacitor"), new EIOCapacitorProvider(base));
        }
    }

    @SubscribeEvent
    public static void remapIds(MissingMappingsEvent event) {
        if (CTNHConfig.INSTANCE.migration.migrationMode) {
            remapUnsafe(event);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static <T> void remapUnsafe(MissingMappingsEvent event) {
        IForgeRegistry<T> registry = (IForgeRegistry<T>) event.getRegistry();

        ResourceKey<? extends Registry<T>> key = (ResourceKey<? extends Registry<T>>) event.getKey();

        List<MissingMappingsEvent.Mapping<T>> mappings = event.getAllMappings(key);

        Set<String> namespaces = Set.of(
                CTNHCore.MODID,
                CTNHMana.MODID,
                CTNHBio.MODID,
                CTNHEnergy.MODID,
                GTCEu.MOD_ID);

        for (MissingMappingsEvent.Mapping<T> mapping : mappings) {

            ResourceLocation missing = mapping.getKey();

            if (!namespaces.contains(missing.getNamespace()))
                continue;

            String path = missing.getPath();

            for (String ns : namespaces) {

                if (ns.equals(missing.getNamespace()))
                    continue;

                ResourceLocation candidate = ResourceLocation.tryBuild(ns, path);

                if (registry.containsKey(candidate)) {
                    mapping.remap(registry.getValue(candidate));
                    break;
                }
            }
        }
    }

    private static net.minecraft.world.item.Item itemId(String namespace, String path) {
        return BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(namespace, path));
    }

    private static void giveOrDrop(Player player, ItemStack stack) {
        if (!player.addItem(stack)) {
            player.drop(stack, false);
        }
    }
}
