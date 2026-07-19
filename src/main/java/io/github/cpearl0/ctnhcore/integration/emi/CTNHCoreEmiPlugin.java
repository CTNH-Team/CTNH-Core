package io.github.cpearl0.ctnhcore.integration.emi;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import com.enderio.base.common.init.EIOItems;
import com.enderio.machines.common.blockentity.capacitorbank.CapacitorTier;
import com.enderio.machines.common.blockentity.solar.SolarPanelTier;
import com.enderio.machines.common.init.MachineBlocks;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiInitRegistry;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiInfoRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.gregtechceu.gtceu.common.data.machines.GTMultiMachines.PRIMITIVE_BLAST_FURNACE;

@EmiEntrypoint
public class CTNHCoreEmiPlugin implements EmiPlugin {

    @Key("ctnh.bauxite_process_catalyst")
    @CN("村庄里的图书管理员掌握这个古老的技术，成为村庄英雄后他就会传授给你")
    @EN("Village librarians guard this ancient technique - prove yourself as a Hero of the Village to learn it")
    public static Lang bauxiteProcessCatalyst;


    @Key("ctnh.blood_magic_gregtech_hv")
    @CN("在坠星位标仪式中使用此物品作为祭品可以召唤陨石")
    @EN("Use this item as an offering in the Falling Star Beacon Ritual to summon meteorites")
    public static Lang bloodMagicGregtechHv;


    @Key("ctnh.doubt")
    @CN("通过击杀浸泡在生命源质的生物，将其困扰注入生命源质之中获得")
    @EN("Obtain it by killing creatures soaked in Life Essence and infusing their doubt into Life Essence")
    public static Lang doubt;


    @Key("ctnh.metallurgical_catalyst")
    @CN("地狱的猪灵掌握这个技术，尝试与他们交易吧")
    @EN("Nether Piglins possess this knowledge - try bartering with them")
    public static Lang metallurgicalCatalyst;


    @Key("ctnh.platinum_metal_catalyst_shard1")
    @CN("久远的时间使他们变成了水里的宝藏，通过钓鱼获得")
    @EN("The tides of time turned these into aquatic treasures - fish them up")
    public static Lang platinumMetalCatalystShard1;


    @Key("ctnh.platinum_metal_catalyst_shard2")
    @CN("深渊里的深潜一组掌握这个技术，尝试与他们交易吧")
    @EN("The abyssal Drowned Ones hold this secret - attempt to trade with them")
    public static Lang platinumMetalCatalystShard2;


    @Key("ctnh.psionic_medulla")
    @CN("通过血魔法邪恶的生灵萃取仪式萃取艾尔夫海姆精灵获取")
    @EN("Obtain it by extracting Alfheim Elves through the Blood Magic Evil Creature Extraction ritual")
    public static Lang psionicMedulla;


    @Key("ctnh.stone_process_catalyst")
    @CN("村庄里的石匠掌握这个古老的技术，成为村庄英雄后他就会传授给你")
    @EN("Village stonemasons know this ancient technique - they'll teach you after you become a Hero of the Village")
    public static Lang stoneProcessCatalyst;



    private static final List<String> EXTRA_ITEMS = List.of(
            "create:chromatic_compound",
            "create:shadow_steel",
            "create:shadow_steel_casing",
            "create:refined_radiance",
            "create:refined_radiance_casing");

    public static List<Supplier<? extends Item>> disabled = new ArrayList<>();

    @Override
    public void register(EmiRegistry registry) {
        for (String itemId : EXTRA_ITEMS) {
            Item item = resolveItem(itemId);
            if (item != Items.AIR) {
                registry.addEmiStack(EmiStack.of(item));
            }
        }

        addInformation(registry, "gtceu:hv_emitter", bloodMagicGregtechHv.key());
        addInformation(registry, "ctnhcore:stone_process_catalyst", stoneProcessCatalyst.key());
        addInformation(registry, "ctnhcore:metallurgical_catalyst", metallurgicalCatalyst.key());
        addInformation(registry, "ctnhcore:bauxite_process_catalyst", bauxiteProcessCatalyst.key());
        addInformation(registry, "ctnhcore:platinum_metal_catalyst_shard1",
                platinumMetalCatalystShard1.key());
        addInformation(registry, "ctnhcore:platinum_metal_catalyst_shard2",
                platinumMetalCatalystShard2.key());
        addInformation(registry, "gtceu:psionic_medulla_gem", psionicMedulla.key());
        addInformation(registry, "bloodmagic:doubt_bucket", doubt.key());
    }

    @Override
    public void initialize(EmiInitRegistry registry) {
        EIODisable();

        for (var item : disabled) {
            registry.disableStack(EmiStack.of(item.get()));
        }
    }

    public static void EIODisable() {
        disabled.addAll(List.of(
                MachineBlocks.FLUID_TANK::asItem,
                MachineBlocks.PRESSURIZED_FLUID_TANK::asItem,
                MachineBlocks.STIRLING_GENERATOR::asItem,
                MachineBlocks.SAG_MILL::asItem,
                MachineBlocks.ALLOY_SMELTER::asItem,
                MachineBlocks.PRIMITIVE_ALLOY_SMELTER::asItem,
                MachineBlocks.STIRLING_GENERATOR::asItem,
                MachineBlocks.SOUL_ENGINE::asItem,
                EIOItems.BASIC_CAPACITOR::asItem,
                EIOItems.DOUBLE_LAYER_CAPACITOR::asItem,
                EIOItems.OCTADIC_CAPACITOR::asItem,
                PRIMITIVE_BLAST_FURNACE::getItem));
        for (SolarPanelTier tier : SolarPanelTier.values()) {
            disabled.add(MachineBlocks.SOLAR_PANELS.get(tier)::asItem);
        }
        for (CapacitorTier tier : CapacitorTier.values()) {
            disabled.add(MachineBlocks.CAPACITOR_BANKS.get(tier)::asItem);
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
}
