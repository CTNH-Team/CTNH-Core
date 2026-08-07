package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.data.recipe.WoodTypeEntry;
import com.gregtechceu.gtceu.data.recipe.event.WoodTypeEntryEvent;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import com.hollingsworth.arsnouveau.setup.registry.BlockRegistry;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModItems;
import slimeknights.mantle.registration.object.WoodBlockObject;
import slimeknights.tconstruct.world.TinkerWorld;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.init.DAItems;
import twilightforest.init.TFBlocks;
import twilightforest.init.TFItems;

import java.util.List;

/**
 * 注册包内全部非原版木材到 GTCEu 统一木材加工体系（{@link WoodTypeEntry}）。
 *
 * <p>
 * GTCEu 的 {@link WoodTypeEntryEvent} 每次生成默认木材条目时都会发布（一次启动至少
 * 三次），因此注册过程只执行一次（见 {@link #registered}），后续发布直接跳过。
 *
 * <p>
 * 引用规则：编译依赖模组（aether / deep_aether / twilightforest / ars_nouveau /
 * ecologics / tconstruct）一律使用注册对象静态字段；biomesoplenty 不是编译依赖，
 * 用 {@link ModList#isLoaded} 守卫 + {@link ForgeRegistries} 查询，木板缺失时跳过该条目。
 */
@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WoodTypeEntries {

    /**
     * GTCEu 每次调用 {@code WoodMachineRecipes#getDefaultEntries()} 都会发布事件
     * （一次启动至少三次），注册一次后跳过后续发布。
     */
    private static boolean registered;

    @SubscribeEvent
    public static void onWoodTypeEntry(WoodTypeEntryEvent event) {
        if (registered) return;
        registered = true;
        List<WoodTypeEntry> entries = event.getEntries();
        addAether(entries);
        addDeepAether(entries);
        addBiomesOPlenty(entries);
        addTwilightForest(entries);
        addArsNouveau(entries);
        addEcologics(entries);
        addTConstruct(entries);
    }

    private static TagKey<Item> itemTag(String id) {
        return TagKey.create(Registries.ITEM, ResourceLocation.parse(id));
    }

    // ==================== Aether ====================

    private static void addAether(List<WoodTypeEntry> entries) {
        entries.add(new WoodTypeEntry.Builder("aether", "skyroot")
                .logTag(itemTag("aether:skyroot_logs"))
                .log(AetherBlocks.SKYROOT_LOG.get().asItem())
                .strippedLog(AetherBlocks.STRIPPED_SKYROOT_LOG.get().asItem())
                .wood(AetherBlocks.SKYROOT_WOOD.get().asItem())
                .strippedWood(AetherBlocks.STRIPPED_SKYROOT_WOOD.get().asItem())
                .planks(AetherBlocks.SKYROOT_PLANKS.get().asItem(), "skyroot_planks")
                .door(AetherBlocks.SKYROOT_DOOR.get().asItem(), "skyroot_door")
                .trapdoor(AetherBlocks.SKYROOT_TRAPDOOR.get().asItem(), "skyroot_trapdoor")
                .slab(AetherBlocks.SKYROOT_SLAB.get().asItem(), "skyroot_slab")
                .fence(AetherBlocks.SKYROOT_FENCE.get().asItem(), "skyroot_fence")
                .fenceGate(AetherBlocks.SKYROOT_FENCE_GATE.get().asItem(), "skyroot_fence_gate")
                .stairs(AetherBlocks.SKYROOT_STAIRS.get().asItem(), "skyroot_stairs")
                .boat(AetherItems.SKYROOT_BOAT.get(), "skyroot_boat")
                .chestBoat(AetherItems.SKYROOT_CHEST_BOAT.get(), "skyroot_chest_boat")
                .sign(AetherBlocks.SKYROOT_SIGN.get().asItem(), "skyroot_sign")
                .hangingSign(AetherBlocks.SKYROOT_HANGING_SIGN.get().asItem(), "skyroot_hanging_sign")
                .button(AetherBlocks.SKYROOT_BUTTON.get().asItem(), "skyroot_button")
                .pressurePlate(AetherBlocks.SKYROOT_PRESSURE_PLATE.get().asItem(), "skyroot_pressure_plate")
                .registerAllMaterialInfo().build());

        // 黄金橡木是 skyroot 变体：无 stripped/家具，产出 skyroot 木板
        entries.add(new WoodTypeEntry.Builder("aether", "golden_oak")
                .logTag(itemTag("aether:golden_oak_logs"))
                .log(AetherBlocks.GOLDEN_OAK_LOG.get().asItem())
                .wood(AetherBlocks.GOLDEN_OAK_WOOD.get().asItem())
                .planks(AetherBlocks.SKYROOT_PLANKS.get().asItem(), "skyroot_planks")
                .registerAllMaterialInfo().build());
    }

    // ==================== Deep Aether ====================

    private static void addDeepAether(List<WoodTypeEntry> entries) {
        entries.add(new WoodTypeEntry.Builder("deep_aether", "roseroot")
                .logTag(itemTag("deep_aether:roseroot_logs"))
                .log(DABlocks.ROSEROOT_LOG.get().asItem())
                .strippedLog(DABlocks.STRIPPED_ROSEROOT_LOG.get().asItem())
                .wood(DABlocks.ROSEROOT_WOOD.get().asItem())
                .strippedWood(DABlocks.STRIPPED_ROSEROOT_WOOD.get().asItem())
                .planks(DABlocks.ROSEROOT_PLANKS.get().asItem(), "roseroot_planks")
                .door(DABlocks.ROSEROOT_DOOR.get().asItem(), "roseroot_door")
                .trapdoor(DABlocks.ROSEROOT_TRAPDOOR.get().asItem(), "roseroot_trapdoor")
                .slab(DABlocks.ROSEROOT_SLAB.get().asItem(), "roseroot_slab")
                .fence(DABlocks.ROSEROOT_FENCE.get().asItem(), "roseroot_fence")
                .fenceGate(DABlocks.ROSEROOT_FENCE_GATE.get().asItem(), "roseroot_fence_gate")
                .stairs(DABlocks.ROSEROOT_STAIRS.get().asItem(), "roseroot_stairs")
                .boat(DAItems.ROSEROOT_BOAT.get(), "roseroot_boat")
                .chestBoat(DAItems.ROSEROOT_CHEST_BOAT.get(), "roseroot_chest_boat")
                .sign(DABlocks.ROSEROOT_SIGN.get().asItem(), "roseroot_sign")
                .hangingSign(DABlocks.ROSEROOT_HANGING_SIGN.get().asItem(), "roseroot_hanging_sign")
                .button(DABlocks.ROSEROOT_BUTTON.get().asItem(), "roseroot_button")
                .pressurePlate(DABlocks.ROSEROOT_PRESSURE_PLATE.get().asItem(), "roseroot_pressure_plate")
                .registerAllMaterialInfo().build());
        entries.add(new WoodTypeEntry.Builder("deep_aether", "yagroot")
                .logTag(itemTag("deep_aether:yagroot_logs"))
                .log(DABlocks.YAGROOT_LOG.get().asItem())
                .strippedLog(DABlocks.STRIPPED_YAGROOT_LOG.get().asItem())
                .wood(DABlocks.YAGROOT_WOOD.get().asItem())
                .strippedWood(DABlocks.STRIPPED_YAGROOT_WOOD.get().asItem())
                .planks(DABlocks.YAGROOT_PLANKS.get().asItem(), "yagroot_planks")
                .door(DABlocks.YAGROOT_DOOR.get().asItem(), "yagroot_door")
                .trapdoor(DABlocks.YAGROOT_TRAPDOOR.get().asItem(), "yagroot_trapdoor")
                .slab(DABlocks.YAGROOT_SLAB.get().asItem(), "yagroot_slab")
                .fence(DABlocks.YAGROOT_FENCE.get().asItem(), "yagroot_fence")
                .fenceGate(DABlocks.YAGROOT_FENCE_GATE.get().asItem(), "yagroot_fence_gate")
                .stairs(DABlocks.YAGROOT_STAIRS.get().asItem(), "yagroot_stairs")
                .boat(DAItems.YAGROOT_BOAT.get(), "yagroot_boat")
                .chestBoat(DAItems.YAGROOT_CHEST_BOAT.get(), "yagroot_chest_boat")
                .sign(DABlocks.YAGROOT_SIGN.get().asItem(), "yagroot_sign")
                .hangingSign(DABlocks.YAGROOT_HANGING_SIGN.get().asItem(), "yagroot_hanging_sign")
                .button(DABlocks.YAGROOT_BUTTON.get().asItem(), "yagroot_button")
                .pressurePlate(DABlocks.YAGROOT_PRESSURE_PLATE.get().asItem(), "yagroot_pressure_plate")
                .registerAllMaterialInfo().build());
        entries.add(new WoodTypeEntry.Builder("deep_aether", "cruderoot")
                .logTag(itemTag("deep_aether:cruderoot_logs"))
                .log(DABlocks.CRUDEROOT_LOG.get().asItem())
                .strippedLog(DABlocks.STRIPPED_CRUDEROOT_LOG.get().asItem())
                .wood(DABlocks.CRUDEROOT_WOOD.get().asItem())
                .strippedWood(DABlocks.STRIPPED_CRUDEROOT_WOOD.get().asItem())
                .planks(DABlocks.CRUDEROOT_PLANKS.get().asItem(), "cruderoot_planks")
                .door(DABlocks.CRUDEROOT_DOOR.get().asItem(), "cruderoot_door")
                .trapdoor(DABlocks.CRUDEROOT_TRAPDOOR.get().asItem(), "cruderoot_trapdoor")
                .slab(DABlocks.CRUDEROOT_SLAB.get().asItem(), "cruderoot_slab")
                .fence(DABlocks.CRUDEROOT_FENCE.get().asItem(), "cruderoot_fence")
                .fenceGate(DABlocks.CRUDEROOT_FENCE_GATE.get().asItem(), "cruderoot_fence_gate")
                .stairs(DABlocks.CRUDEROOT_STAIRS.get().asItem(), "cruderoot_stairs")
                .boat(DAItems.CRUDEROOT_BOAT.get(), "cruderoot_boat")
                .chestBoat(DAItems.CRUDEROOT_CHEST_BOAT.get(), "cruderoot_chest_boat")
                .sign(DABlocks.CRUDEROOT_SIGN.get().asItem(), "cruderoot_sign")
                .hangingSign(DABlocks.CRUDEROOT_HANGING_SIGN.get().asItem(), "cruderoot_hanging_sign")
                .button(DABlocks.CRUDEROOT_BUTTON.get().asItem(), "cruderoot_button")
                .pressurePlate(DABlocks.CRUDEROOT_PRESSURE_PLATE.get().asItem(), "cruderoot_pressure_plate")
                .registerAllMaterialInfo().build());
        entries.add(new WoodTypeEntry.Builder("deep_aether", "conberry")
                .logTag(itemTag("deep_aether:conberry_logs"))
                .log(DABlocks.CONBERRY_LOG.get().asItem())
                .strippedLog(DABlocks.STRIPPED_CONBERRY_LOG.get().asItem())
                .wood(DABlocks.CONBERRY_WOOD.get().asItem())
                .strippedWood(DABlocks.STRIPPED_CONBERRY_WOOD.get().asItem())
                .planks(DABlocks.CONBERRY_PLANKS.get().asItem(), "conberry_planks")
                .door(DABlocks.CONBERRY_DOOR.get().asItem(), "conberry_door")
                .trapdoor(DABlocks.CONBERRY_TRAPDOOR.get().asItem(), "conberry_trapdoor")
                .slab(DABlocks.CONBERRY_SLAB.get().asItem(), "conberry_slab")
                .fence(DABlocks.CONBERRY_FENCE.get().asItem(), "conberry_fence")
                .fenceGate(DABlocks.CONBERRY_FENCE_GATE.get().asItem(), "conberry_fence_gate")
                .stairs(DABlocks.CONBERRY_STAIRS.get().asItem(), "conberry_stairs")
                .boat(DAItems.CONBERRY_BOAT.get(), "conberry_boat")
                .chestBoat(DAItems.CONBERRY_CHEST_BOAT.get(), "conberry_chest_boat")
                .sign(DABlocks.CONBERRY_SIGN.get().asItem(), "conberry_sign")
                .hangingSign(DABlocks.CONBERRY_HANGING_SIGN.get().asItem(), "conberry_hanging_sign")
                .button(DABlocks.CONBERRY_BUTTON.get().asItem(), "conberry_button")
                .pressurePlate(DABlocks.CONBERRY_PRESSURE_PLATE.get().asItem(), "conberry_pressure_plate")
                .registerAllMaterialInfo().build());
        entries.add(new WoodTypeEntry.Builder("deep_aether", "sunroot")
                .logTag(itemTag("deep_aether:sunroot_logs"))
                .log(DABlocks.SUNROOT_LOG.get().asItem())
                .strippedLog(DABlocks.STRIPPED_SUNROOT_LOG.get().asItem())
                .wood(DABlocks.SUNROOT_WOOD.get().asItem())
                .strippedWood(DABlocks.STRIPPED_SUNROOT_WOOD.get().asItem())
                .planks(DABlocks.SUNROOT_PLANKS.get().asItem(), "sunroot_planks")
                .door(DABlocks.SUNROOT_DOOR.get().asItem(), "sunroot_door")
                .trapdoor(DABlocks.SUNROOT_TRAPDOOR.get().asItem(), "sunroot_trapdoor")
                .slab(DABlocks.SUNROOT_SLAB.get().asItem(), "sunroot_slab")
                .fence(DABlocks.SUNROOT_FENCE.get().asItem(), "sunroot_fence")
                .fenceGate(DABlocks.SUNROOT_FENCE_GATE.get().asItem(), "sunroot_fence_gate")
                .stairs(DABlocks.SUNROOT_STAIRS.get().asItem(), "sunroot_stairs")
                .boat(DAItems.SUNROOT_BOAT.get(), "sunroot_boat")
                .chestBoat(DAItems.SUNROOT_CHEST_BOAT.get(), "sunroot_chest_boat")
                .sign(DABlocks.SUNROOT_SIGN.get().asItem(), "sunroot_sign")
                .hangingSign(DABlocks.SUNROOT_HANGING_SIGN.get().asItem(), "sunroot_hanging_sign")
                .button(DABlocks.SUNROOT_BUTTON.get().asItem(), "sunroot_button")
                .pressurePlate(DABlocks.SUNROOT_PRESSURE_PLATE.get().asItem(), "sunroot_pressure_plate")
                .registerAllMaterialInfo().build());
    }

    // ==================== Biomes O' Plenty ====================

    private static void addBiomesOPlenty(List<WoodTypeEntry> entries) {
        if (!ModList.get().isLoaded("biomesoplenty")) return;
        String[] woods = {
                "fir", "redwood", "mahogany", "jacaranda", "palm", "willow", "dead", "magic",
                "umbran", "hellbark", "pine", "maple", "empyreal"
        };
        for (String wood : woods) {
            Item planks = bopItem(wood + "_planks");
            if (planks == null) continue;
            entries.add(new WoodTypeEntry.Builder("biomesoplenty", wood)
                    .logTag(itemTag("biomesoplenty:" + wood + "_logs"))
                    .log(bopItem(wood + "_log"))
                    .strippedLog(bopItem("stripped_" + wood + "_log"))
                    .wood(bopItem(wood + "_wood"))
                    .strippedWood(bopItem("stripped_" + wood + "_wood"))
                    .planks(planks, wood + "_planks")
                    .door(bopItem(wood + "_door"), wood + "_door")
                    .trapdoor(bopItem(wood + "_trapdoor"), wood + "_trapdoor")
                    .slab(bopItem(wood + "_slab"), wood + "_slab")
                    .fence(bopItem(wood + "_fence"), wood + "_fence")
                    .fenceGate(bopItem(wood + "_fence_gate"), wood + "_fence_gate")
                    .stairs(bopItem(wood + "_stairs"), wood + "_stairs")
                    .boat(bopItem(wood + "_boat"), wood + "_boat")
                    .chestBoat(bopItem(wood + "_chest_boat"), wood + "_chest_boat")
                    .sign(bopItem(wood + "_sign"), wood + "_sign")
                    .hangingSign(bopItem(wood + "_hanging_sign"), wood + "_hanging_sign")
                    .button(bopItem(wood + "_button"), wood + "_button")
                    .pressurePlate(bopItem(wood + "_pressure_plate"), wood + "_pressure_plate")
                    .registerAllMaterialInfo().build());
        }
    }

    private static Item bopItem(String path) {
        return ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("biomesoplenty", path));
    }

    // ==================== Twilight Forest ====================

    private static void addTwilightForest(List<WoodTypeEntry> entries) {
        entries.add(new WoodTypeEntry.Builder("twilightforest", "dark")
                .logTag(itemTag("twilightforest:darkwood_logs"))
                .log(TFBlocks.DARK_LOG.get().asItem())
                .strippedLog(TFBlocks.STRIPPED_DARK_LOG.get().asItem())
                .wood(TFBlocks.DARK_WOOD.get().asItem())
                .strippedWood(TFBlocks.STRIPPED_DARK_WOOD.get().asItem())
                .planks(TFBlocks.DARK_PLANKS.get().asItem(), "wood/dark_planks")
                .door(TFBlocks.DARK_DOOR.get().asItem(), "wood/dark_door")
                .trapdoor(TFBlocks.DARK_TRAPDOOR.get().asItem(), "wood/dark_trapdoor")
                .slab(TFBlocks.DARK_SLAB.get().asItem(), "wood/dark_slab")
                .fence(TFBlocks.DARK_FENCE.get().asItem(), "wood/dark_fence")
                .fenceGate(TFBlocks.DARK_GATE.get().asItem(), "wood/dark_gate")
                .stairs(TFBlocks.DARK_STAIRS.get().asItem(), "wood/dark_stairs")
                .boat(TFItems.DARK_BOAT.get(), "dark_boat")
                .chestBoat(TFItems.DARK_CHEST_BOAT.get(), "dark_chest_boat")
                .sign(TFBlocks.DARK_SIGN.get().asItem(), "wood/dark_sign")
                .hangingSign(TFBlocks.DARK_HANGING_SIGN.get().asItem(), "wood/dark_hanging_sign")
                .button(TFBlocks.DARK_BUTTON.get().asItem(), "wood/dark_button")
                .pressurePlate(TFBlocks.DARK_PLATE.get().asItem(), "wood/dark_plate")
                .registerAllMaterialInfo().build());
        entries.add(new WoodTypeEntry.Builder("twilightforest", "canopy")
                .logTag(itemTag("twilightforest:canopy_logs"))
                .log(TFBlocks.CANOPY_LOG.get().asItem())
                .strippedLog(TFBlocks.STRIPPED_CANOPY_LOG.get().asItem())
                .wood(TFBlocks.CANOPY_WOOD.get().asItem())
                .strippedWood(TFBlocks.STRIPPED_CANOPY_WOOD.get().asItem())
                .planks(TFBlocks.CANOPY_PLANKS.get().asItem(), "wood/canopy_planks")
                .door(TFBlocks.CANOPY_DOOR.get().asItem(), "wood/canopy_door")
                .trapdoor(TFBlocks.CANOPY_TRAPDOOR.get().asItem(), "wood/canopy_trapdoor")
                .slab(TFBlocks.CANOPY_SLAB.get().asItem(), "wood/canopy_slab")
                .fence(TFBlocks.CANOPY_FENCE.get().asItem(), "wood/canopy_fence")
                .fenceGate(TFBlocks.CANOPY_GATE.get().asItem(), "wood/canopy_gate")
                .stairs(TFBlocks.CANOPY_STAIRS.get().asItem(), "wood/canopy_stairs")
                .boat(TFItems.CANOPY_BOAT.get(), "canopy_boat")
                .chestBoat(TFItems.CANOPY_CHEST_BOAT.get(), "canopy_chest_boat")
                .sign(TFBlocks.CANOPY_SIGN.get().asItem(), "wood/canopy_sign")
                .hangingSign(TFBlocks.CANOPY_HANGING_SIGN.get().asItem(), "wood/canopy_hanging_sign")
                .button(TFBlocks.CANOPY_BUTTON.get().asItem(), "wood/canopy_button")
                .pressurePlate(TFBlocks.CANOPY_PLATE.get().asItem(), "wood/canopy_plate")
                .registerAllMaterialInfo().build());
        entries.add(new WoodTypeEntry.Builder("twilightforest", "twilight_oak")
                .logTag(itemTag("twilightforest:twilight_oak_logs"))
                .log(TFBlocks.TWILIGHT_OAK_LOG.get().asItem())
                .strippedLog(TFBlocks.STRIPPED_TWILIGHT_OAK_LOG.get().asItem())
                .wood(TFBlocks.TWILIGHT_OAK_WOOD.get().asItem())
                .strippedWood(TFBlocks.STRIPPED_TWILIGHT_OAK_WOOD.get().asItem())
                .planks(TFBlocks.TWILIGHT_OAK_PLANKS.get().asItem(), "wood/twilight_oak_planks")
                .door(TFBlocks.TWILIGHT_OAK_DOOR.get().asItem(), "wood/twilight_oak_door")
                .trapdoor(TFBlocks.TWILIGHT_OAK_TRAPDOOR.get().asItem(), "wood/twilight_oak_trapdoor")
                .slab(TFBlocks.TWILIGHT_OAK_SLAB.get().asItem(), "wood/twilight_oak_slab")
                .fence(TFBlocks.TWILIGHT_OAK_FENCE.get().asItem(), "wood/twilight_oak_fence")
                .fenceGate(TFBlocks.TWILIGHT_OAK_GATE.get().asItem(), "wood/twilight_oak_gate")
                .stairs(TFBlocks.TWILIGHT_OAK_STAIRS.get().asItem(), "wood/twilight_oak_stairs")
                .boat(TFItems.TWILIGHT_OAK_BOAT.get(), "twilight_oak_boat")
                .chestBoat(TFItems.TWILIGHT_OAK_CHEST_BOAT.get(), "twilight_oak_chest_boat")
                .sign(TFBlocks.TWILIGHT_OAK_SIGN.get().asItem(), "wood/twilight_oak_sign")
                .hangingSign(TFBlocks.TWILIGHT_OAK_HANGING_SIGN.get().asItem(), "wood/twilight_oak_hanging_sign")
                .button(TFBlocks.TWILIGHT_OAK_BUTTON.get().asItem(), "wood/twilight_oak_button")
                .pressurePlate(TFBlocks.TWILIGHT_OAK_PLATE.get().asItem(), "wood/twilight_oak_plate")
                .registerAllMaterialInfo().build());
        entries.add(new WoodTypeEntry.Builder("twilightforest", "time")
                .logTag(itemTag("twilightforest:timewood_logs"))
                .log(TFBlocks.TIME_LOG.get().asItem())
                .strippedLog(TFBlocks.STRIPPED_TIME_LOG.get().asItem())
                .wood(TFBlocks.TIME_WOOD.get().asItem())
                .strippedWood(TFBlocks.STRIPPED_TIME_WOOD.get().asItem())
                .planks(TFBlocks.TIME_PLANKS.get().asItem(), "wood/time_planks")
                .door(TFBlocks.TIME_DOOR.get().asItem(), "wood/time_door")
                .trapdoor(TFBlocks.TIME_TRAPDOOR.get().asItem(), "wood/time_trapdoor")
                .slab(TFBlocks.TIME_SLAB.get().asItem(), "wood/time_slab")
                .fence(TFBlocks.TIME_FENCE.get().asItem(), "wood/time_fence")
                .fenceGate(TFBlocks.TIME_GATE.get().asItem(), "wood/time_gate")
                .stairs(TFBlocks.TIME_STAIRS.get().asItem(), "wood/time_stairs")
                .boat(TFItems.TIME_BOAT.get(), "time_boat")
                .chestBoat(TFItems.TIME_CHEST_BOAT.get(), "time_chest_boat")
                .sign(TFBlocks.TIME_SIGN.get().asItem(), "wood/time_sign")
                .hangingSign(TFBlocks.TIME_HANGING_SIGN.get().asItem(), "wood/time_hanging_sign")
                .button(TFBlocks.TIME_BUTTON.get().asItem(), "wood/time_button")
                .pressurePlate(TFBlocks.TIME_PLATE.get().asItem(), "wood/time_plate")
                .registerAllMaterialInfo().build());
        entries.add(new WoodTypeEntry.Builder("twilightforest", "transformation")
                .logTag(itemTag("twilightforest:transwood_logs"))
                .log(TFBlocks.TRANSFORMATION_LOG.get().asItem())
                .strippedLog(TFBlocks.STRIPPED_TRANSFORMATION_LOG.get().asItem())
                .wood(TFBlocks.TRANSFORMATION_WOOD.get().asItem())
                .strippedWood(TFBlocks.STRIPPED_TRANSFORMATION_WOOD.get().asItem())
                .planks(TFBlocks.TRANSFORMATION_PLANKS.get().asItem(), "wood/transformation_planks")
                .door(TFBlocks.TRANSFORMATION_DOOR.get().asItem(), "wood/transformation_door")
                .trapdoor(TFBlocks.TRANSFORMATION_TRAPDOOR.get().asItem(), "wood/transformation_trapdoor")
                .slab(TFBlocks.TRANSFORMATION_SLAB.get().asItem(), "wood/transformation_slab")
                .fence(TFBlocks.TRANSFORMATION_FENCE.get().asItem(), "wood/transformation_fence")
                .fenceGate(TFBlocks.TRANSFORMATION_GATE.get().asItem(), "wood/transformation_gate")
                .stairs(TFBlocks.TRANSFORMATION_STAIRS.get().asItem(), "wood/transformation_stairs")
                .boat(TFItems.TRANSFORMATION_BOAT.get(), "transformation_boat")
                .chestBoat(TFItems.TRANSFORMATION_CHEST_BOAT.get(), "transformation_chest_boat")
                .sign(TFBlocks.TRANSFORMATION_SIGN.get().asItem(), "wood/transformation_sign")
                .hangingSign(TFBlocks.TRANSFORMATION_HANGING_SIGN.get().asItem(), "wood/transformation_hanging_sign")
                .button(TFBlocks.TRANSFORMATION_BUTTON.get().asItem(), "wood/transformation_button")
                .pressurePlate(TFBlocks.TRANSFORMATION_PLATE.get().asItem(), "wood/transformation_plate")
                .registerAllMaterialInfo().build());
        entries.add(new WoodTypeEntry.Builder("twilightforest", "mining")
                .logTag(itemTag("twilightforest:mining_logs"))
                .log(TFBlocks.MINING_LOG.get().asItem())
                .strippedLog(TFBlocks.STRIPPED_MINING_LOG.get().asItem())
                .wood(TFBlocks.MINING_WOOD.get().asItem())
                .strippedWood(TFBlocks.STRIPPED_MINING_WOOD.get().asItem())
                .planks(TFBlocks.MINING_PLANKS.get().asItem(), "wood/mining_planks")
                .door(TFBlocks.MINING_DOOR.get().asItem(), "wood/mining_door")
                .trapdoor(TFBlocks.MINING_TRAPDOOR.get().asItem(), "wood/mining_trapdoor")
                .slab(TFBlocks.MINING_SLAB.get().asItem(), "wood/mining_slab")
                .fence(TFBlocks.MINING_FENCE.get().asItem(), "wood/mining_fence")
                .fenceGate(TFBlocks.MINING_GATE.get().asItem(), "wood/mining_gate")
                .stairs(TFBlocks.MINING_STAIRS.get().asItem(), "wood/mining_stairs")
                .boat(TFItems.MINING_BOAT.get(), "mining_boat")
                .chestBoat(TFItems.MINING_CHEST_BOAT.get(), "mining_chest_boat")
                .sign(TFBlocks.MINING_SIGN.get().asItem(), "wood/mining_sign")
                .hangingSign(TFBlocks.MINING_HANGING_SIGN.get().asItem(), "wood/mining_hanging_sign")
                .button(TFBlocks.MINING_BUTTON.get().asItem(), "wood/mining_button")
                .pressurePlate(TFBlocks.MINING_PLATE.get().asItem(), "wood/mining_plate")
                .registerAllMaterialInfo().build());
        entries.add(new WoodTypeEntry.Builder("twilightforest", "sorting")
                .logTag(itemTag("twilightforest:sortwood_logs"))
                .log(TFBlocks.SORTING_LOG.get().asItem())
                .strippedLog(TFBlocks.STRIPPED_SORTING_LOG.get().asItem())
                .wood(TFBlocks.SORTING_WOOD.get().asItem())
                .strippedWood(TFBlocks.STRIPPED_SORTING_WOOD.get().asItem())
                .planks(TFBlocks.SORTING_PLANKS.get().asItem(), "wood/sorting_planks")
                .door(TFBlocks.SORTING_DOOR.get().asItem(), "wood/sorting_door")
                .trapdoor(TFBlocks.SORTING_TRAPDOOR.get().asItem(), "wood/sorting_trapdoor")
                .slab(TFBlocks.SORTING_SLAB.get().asItem(), "wood/sorting_slab")
                .fence(TFBlocks.SORTING_FENCE.get().asItem(), "wood/sorting_fence")
                .fenceGate(TFBlocks.SORTING_GATE.get().asItem(), "wood/sorting_gate")
                .stairs(TFBlocks.SORTING_STAIRS.get().asItem(), "wood/sorting_stairs")
                .boat(TFItems.SORTING_BOAT.get(), "sorting_boat")
                .chestBoat(TFItems.SORTING_CHEST_BOAT.get(), "sorting_chest_boat")
                .sign(TFBlocks.SORTING_SIGN.get().asItem(), "wood/sorting_sign")
                .hangingSign(TFBlocks.SORTING_HANGING_SIGN.get().asItem(), "wood/sorting_hanging_sign")
                .button(TFBlocks.SORTING_BUTTON.get().asItem(), "wood/sorting_button")
                .pressurePlate(TFBlocks.SORTING_PLATE.get().asItem(), "wood/sorting_plate")
                .registerAllMaterialInfo().build());
        // 暮色红树林与原版红树林同名，用 twilight_mangrove 消歧
        entries.add(new WoodTypeEntry.Builder("twilightforest", "twilight_mangrove")
                .logTag(itemTag("twilightforest:mangrove_logs"))
                .log(TFBlocks.MANGROVE_LOG.get().asItem())
                .strippedLog(TFBlocks.STRIPPED_MANGROVE_LOG.get().asItem())
                .wood(TFBlocks.MANGROVE_WOOD.get().asItem())
                .strippedWood(TFBlocks.STRIPPED_MANGROVE_WOOD.get().asItem())
                .planks(TFBlocks.MANGROVE_PLANKS.get().asItem(), "wood/mangrove_planks")
                .door(TFBlocks.MANGROVE_DOOR.get().asItem(), "wood/mangrove_door")
                .trapdoor(TFBlocks.MANGROVE_TRAPDOOR.get().asItem(), "wood/mangrove_trapdoor")
                .slab(TFBlocks.MANGROVE_SLAB.get().asItem(), "wood/mangrove_slab")
                .fence(TFBlocks.MANGROVE_FENCE.get().asItem(), "wood/mangrove_fence")
                .fenceGate(TFBlocks.MANGROVE_GATE.get().asItem(), "wood/mangrove_gate")
                .stairs(TFBlocks.MANGROVE_STAIRS.get().asItem(), "wood/mangrove_stairs")
                .boat(TFItems.MANGROVE_BOAT.get(), "mangrove_boat")
                .chestBoat(TFItems.MANGROVE_CHEST_BOAT.get(), "mangrove_chest_boat")
                .sign(TFBlocks.MANGROVE_SIGN.get().asItem(), "wood/mangrove_sign")
                .hangingSign(TFBlocks.MANGROVE_HANGING_SIGN.get().asItem(), "wood/mangrove_hanging_sign")
                .button(TFBlocks.MANGROVE_BUTTON.get().asItem(), "wood/mangrove_button")
                .pressurePlate(TFBlocks.MANGROVE_PLATE.get().asItem(), "wood/mangrove_plate")
                .registerAllMaterialInfo().build());
    }

    // ==================== Ars Nouveau ====================

    private static void addArsNouveau(List<WoodTypeEntry> entries) {
        // 4 色 archwood 共用 archwood_planks 与 forge:logs/archwood 标签，家具保留 ars 原配方
        entries.add(new WoodTypeEntry.Builder("ars_nouveau", "red_archwood")
                .logTag(itemTag("forge:logs/archwood"))
                .log(BlockRegistry.BLAZING_LOG.get().asItem())
                .strippedLog(BlockRegistry.STRIPPED_AWLOG_RED.get().asItem())
                .wood(BlockRegistry.BLAZING_WOOD.get().asItem())
                .strippedWood(BlockRegistry.STRIPPED_AWWOOD_RED.get().asItem())
                .planks(BlockRegistry.ARCHWOOD_PLANK.get().asItem(), "archwood_planks")
                .registerAllMaterialInfo().build());
        entries.add(new WoodTypeEntry.Builder("ars_nouveau", "blue_archwood")
                .logTag(itemTag("forge:logs/archwood"))
                .log(BlockRegistry.CASCADING_LOG.get().asItem())
                .strippedLog(BlockRegistry.STRIPPED_AWLOG_BLUE.get().asItem())
                .wood(BlockRegistry.CASCADING_WOOD.get().asItem())
                .strippedWood(BlockRegistry.STRIPPED_AWWOOD_BLUE.get().asItem())
                .planks(BlockRegistry.ARCHWOOD_PLANK.get().asItem(), "archwood_planks")
                .registerAllMaterialInfo().build());
        entries.add(new WoodTypeEntry.Builder("ars_nouveau", "purple_archwood")
                .logTag(itemTag("forge:logs/archwood"))
                .log(BlockRegistry.VEXING_LOG.get().asItem())
                .strippedLog(BlockRegistry.STRIPPED_AWLOG_PURPLE.get().asItem())
                .wood(BlockRegistry.VEXING_WOOD.get().asItem())
                .strippedWood(BlockRegistry.STRIPPED_AWWOOD_PURPLE.get().asItem())
                .planks(BlockRegistry.ARCHWOOD_PLANK.get().asItem(), "archwood_planks")
                .registerAllMaterialInfo().build());
        entries.add(new WoodTypeEntry.Builder("ars_nouveau", "green_archwood")
                .logTag(itemTag("forge:logs/archwood"))
                .log(BlockRegistry.FLOURISHING_LOG.get().asItem())
                .strippedLog(BlockRegistry.STRIPPED_AWLOG_GREEN.get().asItem())
                .wood(BlockRegistry.FLOURISHING_WOOD.get().asItem())
                .strippedWood(BlockRegistry.STRIPPED_AWWOOD_GREEN.get().asItem())
                .planks(BlockRegistry.ARCHWOOD_PLANK.get().asItem(), "archwood_planks")
                .registerAllMaterialInfo().build());
    }

    // ==================== Ecologics ====================

    private static void addEcologics(List<WoodTypeEntry> entries) {
        entries.add(new WoodTypeEntry.Builder("ecologics", "walnut")
                .logTag(itemTag("ecologics:walnut_logs"))
                .log(ModBlocks.WALNUT_LOG.get().asItem())
                .strippedLog(ModBlocks.STRIPPED_WALNUT_LOG.get().asItem())
                .wood(ModBlocks.WALNUT_WOOD.get().asItem())
                .strippedWood(ModBlocks.STRIPPED_WALNUT_WOOD.get().asItem())
                .planks(ModBlocks.WALNUT_PLANKS.get().asItem(), "walnut_planks")
                .door(ModBlocks.WALNUT_DOOR.get().asItem(), "walnut_door")
                .trapdoor(ModBlocks.WALNUT_TRAPDOOR.get().asItem(), "walnut_trapdoor")
                .slab(ModBlocks.WALNUT_SLAB.get().asItem(), "walnut_slab")
                .fence(ModBlocks.WALNUT_FENCE.get().asItem(), "walnut_fence")
                .fenceGate(ModBlocks.WALNUT_FENCE_GATE.get().asItem(), "walnut_fence_gate")
                .stairs(ModBlocks.WALNUT_STAIRS.get().asItem(), "walnut_stairs")
                .boat(ModItems.WALNUT_BOAT.get(), "walnut_boat")
                .chestBoat(ModItems.WALNUT_CHEST_BOAT.get(), "walnut_chest_boat")
                .sign(ModItems.WALNUT_SIGN.get(), "walnut_sign")
                .hangingSign(ModItems.WALNUT_HANGING_SIGN.get(), "walnut_hanging_sign")
                .button(ModBlocks.WALNUT_BUTTON.get().asItem(), "walnut_button")
                .pressurePlate(ModBlocks.WALNUT_PRESSURE_PLATE.get().asItem(), "walnut_pressure_plate")
                .registerAllMaterialInfo().build());
        entries.add(new WoodTypeEntry.Builder("ecologics", "coconut")
                .logTag(itemTag("ecologics:coconut_logs"))
                .log(ModBlocks.COCONUT_LOG.get().asItem())
                .strippedLog(ModBlocks.STRIPPED_COCONUT_LOG.get().asItem())
                .wood(ModBlocks.COCONUT_WOOD.get().asItem())
                .strippedWood(ModBlocks.STRIPPED_COCONUT_WOOD.get().asItem())
                .planks(ModBlocks.COCONUT_PLANKS.get().asItem(), "coconut_planks")
                .door(ModBlocks.COCONUT_DOOR.get().asItem(), "coconut_door")
                .trapdoor(ModBlocks.COCONUT_TRAPDOOR.get().asItem(), "coconut_trapdoor")
                .slab(ModBlocks.COCONUT_SLAB.get().asItem(), "coconut_slab")
                .fence(ModBlocks.COCONUT_FENCE.get().asItem(), "coconut_fence")
                .fenceGate(ModBlocks.COCONUT_FENCE_GATE.get().asItem(), "coconut_fence_gate")
                .stairs(ModBlocks.COCONUT_STAIRS.get().asItem(), "coconut_stairs")
                .boat(ModItems.COCONUT_BOAT.get(), "coconut_boat")
                .chestBoat(ModItems.COCONUT_CHEST_BOAT.get(), "coconut_chest_boat")
                .sign(ModItems.COCONUT_SIGN.get(), "coconut_sign")
                .hangingSign(ModItems.COCONUT_HANGING_SIGN.get(), "coconut_hanging_sign")
                .button(ModBlocks.COCONUT_BUTTON.get().asItem(), "coconut_button")
                .pressurePlate(ModBlocks.COCONUT_PRESSURE_PLATE.get().asItem(), "coconut_pressure_plate")
                .registerAllMaterialInfo().build());
    }

    // ==================== TConstruct (GregTech-Construct fork) ====================

    private static void addTConstruct(List<WoodTypeEntry> entries) {
        addTConstructWood(entries, "tinkers_skyroot", TinkerWorld.skyroot);
        addTConstructWood(entries, "tinkers_greenheart", TinkerWorld.greenheart);
        addTConstructWood(entries, "tinkers_bloodshroom", TinkerWorld.bloodshroom);
        addTConstructWood(entries, "tinkers_enderbark", TinkerWorld.enderbark);
    }

    private static void addTConstructWood(List<WoodTypeEntry> entries, String woodName, WoodBlockObject woodObject) {
        // fork 移除木材配方后未重建，recipe-name 全部为 null（无原配方可移除）
        entries.add(new WoodTypeEntry.Builder("tconstruct", woodName)
                .logTag(woodObject.getLogItemTag())
                .log(woodObject.getLog().asItem())
                .strippedLog(woodObject.getStrippedLog().asItem())
                .wood(woodObject.getWood().asItem())
                .strippedWood(woodObject.getStrippedWood().asItem())
                .planks(woodObject.get().asItem(), null)
                .door(woodObject.getDoor().asItem(), null)
                .trapdoor(woodObject.getTrapdoor().asItem(), null)
                .slab(woodObject.getSlab().asItem(), null)
                .fence(woodObject.getFence().asItem(), null)
                .fenceGate(woodObject.getFenceGate().asItem(), null)
                .stairs(woodObject.getStairs().asItem(), null)
                .sign(woodObject.getSign().asItem(), null)
                .hangingSign(woodObject.getHangingSign().asItem(), null)
                .button(woodObject.getButton().asItem(), null)
                .pressurePlate(woodObject.getPressurePlate().asItem(), null)
                .registerAllMaterialInfo().build());
    }
}
