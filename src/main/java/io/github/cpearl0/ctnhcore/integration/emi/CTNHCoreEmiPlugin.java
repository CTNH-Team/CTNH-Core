package io.github.cpearl0.ctnhcore.integration.emi;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.enderio.base.common.init.EIOItems;
import com.enderio.machines.common.blockentity.capacitorbank.CapacitorTier;
import com.enderio.machines.common.blockentity.solar.SolarPanelTier;
import com.enderio.machines.common.init.MachineBlocks;
import com.mo_guang.ctpp.registry.CTPPMachines;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.Create;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiInitRegistry;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiInfoRecipe;
import dev.emi.emi.api.recipe.EmiWorldInteractionRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@EmiEntrypoint
public class CTNHCoreEmiPlugin implements EmiPlugin {

    @CN("村庄里的图书管理员掌握这个古老的技术，成为村庄英雄后他就会传授给你")
    @EN("Village librarians guard this ancient technique - prove yourself as a Hero of the Village to learn it")
    public static Lang bauxiteProcessCatalyst;

    @CN("在坠星位标仪式中使用此物品作为祭品可以召唤陨石")
    @EN("Use this item as an offering in the Falling Star Beacon Ritual to summon meteorites")
    public static Lang bloodMagicGregtechHv;

    @CN("通过击杀浸泡在生命源质的生物，将其困扰注入生命源质之中获得")
    @EN("Obtain it by killing creatures soaked in Life Essence and infusing their doubt into Life Essence")
    public static Lang doubt;

    @CN("地狱的猪灵掌握这个技术，尝试与他们交易吧")
    @EN("Nether Piglins possess this knowledge - try bartering with them")
    public static Lang metallurgicalCatalyst;

    @CN("久远的时间使他们变成了水里的宝藏，通过钓鱼获得")
    @EN("The tides of time turned these into aquatic treasures - fish them up")
    public static Lang platinumMetalCatalystShard1;

    @CN("深渊里的深潜一组掌握这个技术，尝试与他们交易吧")
    @EN("The abyssal Drowned Ones hold this secret - attempt to trade with them")
    public static Lang platinumMetalCatalystShard2;

    @CN("通过血魔法邪恶的生灵萃取仪式萃取艾尔夫海姆精灵获取")
    @EN("Obtain it by extracting Alfheim Elves through the Blood Magic Evil Creature Extraction ritual")
    public static Lang psionicMedulla;

    @CN("村庄里的石匠掌握这个古老的技术，成为村庄英雄后他就会传授给你")
    @EN("Village stonemasons know this ancient technique - they'll teach you after you become a Hero of the Village")
    public static Lang stoneProcessCatalyst;

    @CN("使用破坏面板挖世界高度上限即可大量获得")
    @EN("Use an Annihilation Plane at the world height limit to obtain large amounts")
    public static Lang ae2MeteorBlockTooltip;

    private static final List<String> EXTRA_ITEMS = List.of(
            "create:chromatic_compound",
            "create:shadow_steel",
            "create:shadow_steel_casing",
            "create:refined_radiance",
            "create:refined_radiance_casing");

    public static List<Supplier<? extends ItemLike>> disabled = new ArrayList<>();

    @Override
    public void register(EmiRegistry registry) {
        for (String itemId : EXTRA_ITEMS) {
            Item item = resolveItem(itemId);
            if (item != Items.AIR) {
                registry.addEmiStack(EmiStack.of(item));
            }
        }

        addInformation(registry, "gtceu:hv_emitter", bloodMagicGregtechHv.key());
        addInformation(registry, "ae2:sky_stone_block", ae2MeteorBlockTooltip.key());
        addInformation(registry, "ae2:sky_dust", ae2MeteorBlockTooltip.key());
        addInformation(registry, "ctnhcore:stone_process_catalyst", stoneProcessCatalyst.key());
        addInformation(registry, "ctnhcore:metallurgical_catalyst", metallurgicalCatalyst.key());
        addInformation(registry, "ctnhcore:bauxite_process_catalyst", bauxiteProcessCatalyst.key());
        addInformation(registry, "ctnhcore:platinum_metal_catalyst_shard1",
                platinumMetalCatalystShard1.key());
        addInformation(registry, "ctnhcore:platinum_metal_catalyst_shard2",
                platinumMetalCatalystShard2.key());
        addInformation(registry, "gtceu:psionic_medulla_gem", psionicMedulla.key());
        addInformation(registry, "bloodmagic:doubt_bucket", doubt.key());

        addEncasingRecipe(registry, "andesite_cogwheel", AllBlocks.COGWHEEL.asStack(),
                AllBlocks.ANDESITE_CASING.asStack(), AllBlocks.ANDESITE_ENCASED_COGWHEEL.asStack());
        addEncasingRecipe(registry, "andesite_large_cogwheel", AllBlocks.LARGE_COGWHEEL.asStack(),
                AllBlocks.ANDESITE_CASING.asStack(), AllBlocks.ANDESITE_ENCASED_LARGE_COGWHEEL.asStack());
        addEncasingRecipe(registry, "brass_cogwheel", AllBlocks.COGWHEEL.asStack(), AllBlocks.BRASS_CASING.asStack(),
                AllBlocks.BRASS_ENCASED_COGWHEEL.asStack());
        addEncasingRecipe(registry, "brass_large_cogwheel", AllBlocks.LARGE_COGWHEEL.asStack(),
                AllBlocks.BRASS_CASING.asStack(), AllBlocks.BRASS_ENCASED_LARGE_COGWHEEL.asStack());

        registry.addEmiStack(EmiStack.of(AllBlocks.ANDESITE_ENCASED_COGWHEEL));
        registry.addEmiStack(EmiStack.of(AllBlocks.ANDESITE_ENCASED_LARGE_COGWHEEL));
        registry.addEmiStack(EmiStack.of(AllBlocks.BRASS_ENCASED_COGWHEEL));
        registry.addEmiStack(EmiStack.of(AllBlocks.BRASS_ENCASED_LARGE_COGWHEEL));
    }

    @Override
    public void initialize(EmiInitRegistry registry) {
        EIODisable();
        CreateDisable();
        CTPPDisable();

        for (var item : disabled) {
            registry.disableStack(EmiStack.of(item.get()));
        }
    }

    public static void EIODisable() {
        disabled.addAll(List.of(
                MachineBlocks.FLUID_TANK,
                MachineBlocks.PRESSURIZED_FLUID_TANK,
                MachineBlocks.STIRLING_GENERATOR,
                MachineBlocks.SAG_MILL,
                MachineBlocks.ALLOY_SMELTER,
                MachineBlocks.PRIMITIVE_ALLOY_SMELTER,
                MachineBlocks.STIRLING_GENERATOR,
                MachineBlocks.SOUL_ENGINE,
                MachineBlocks.SLICE_AND_SPLICE,
                EIOItems.BASIC_CAPACITOR,
                EIOItems.DOUBLE_LAYER_CAPACITOR,
                EIOItems.OCTADIC_CAPACITOR));

        for (SolarPanelTier tier : SolarPanelTier.values()) {
            disabled.add(MachineBlocks.SOLAR_PANELS.get(tier));
        }
        for (CapacitorTier tier : CapacitorTier.values()) {
            disabled.add(MachineBlocks.CAPACITOR_BANKS.get(tier));
        }
    }

    public static void CreateDisable() {
        disabled.addAll(Arrays.stream(AllBlocks.TOOLBOXES.toArray()).toList());
        // 统一到 GT 锌材料：隐藏机械动力锌锭/锌粒/锌块
        disabled.add(AllItems.ZINC_INGOT);
        disabled.add(AllItems.ZINC_NUGGET);
        disabled.add(AllBlocks.ZINC_BLOCK);
        // 统一到 GT 黄铜材料：隐藏机械动力黄铜锭/黄铜粒/黄铜块
        disabled.add(AllItems.BRASS_INGOT);
        disabled.add(AllItems.BRASS_NUGGET);
        disabled.add(AllBlocks.BRASS_BLOCK);
        // 统一到 GT 银材料：隐藏机械动力碎银
        disabled.add(AllItems.CRUSHED_SILVER);
        // 统一到 GT 钒材料：隐藏 Vintage Improvements 钒锭
        disabled.add(() -> resolveItem("vintageimprovements:vanadium_ingot"));
    }

    public static void CTPPDisable() {
        // 可放置发射器的机器物品是拿不到的中间产物：放置和掉落都走原版 GT 发射器组件，
        // 所以它们既不进创造物品栏（CTPPMachines 注册时用 null 页签），也不该出现在 EMI 里
        for (var definition : CTPPMachines.PLACEABLE_EMITTER) {
            if (definition != null) disabled.add(definition::getItem);
        }
    }

    private static void addInformation(EmiRegistry registry, String itemId, String translationKey) {
        Item item = resolveItem(itemId);
        if (item == Items.AIR) {
            return;
        }

        registry.addRecipe(new EmiInfoRecipe(
                List.<EmiIngredient>of(EmiStack.of(item)),
                List.of(Component.translatable(translationKey)),
                CTNHCore.id("info/" + itemId.replace(':', '/'))));
    }

    private static Item resolveItem(String itemId) {
        ResourceLocation id = ResourceLocation.tryParse(itemId);
        return id == null ? Items.AIR : BuiltInRegistries.ITEM.get(id);
    }

    private static void addEncasingRecipe(EmiRegistry registry, String id, ItemStack cogwheel, ItemStack casing,
                                          ItemStack output) {
        registry.addRecipe(EmiWorldInteractionRecipe.builder()
                .id(Create.asResource("/world/encasing/" + id))
                .leftInput(EmiStack.of(cogwheel))
                .rightInput(EmiStack.of(casing), true)
                .output(EmiStack.of(output))
                .build());
    }
}
