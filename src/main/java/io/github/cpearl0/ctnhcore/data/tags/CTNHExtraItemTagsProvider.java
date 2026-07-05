package io.github.cpearl0.ctnhcore.data.tags;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CTNHExtraItemTagsProvider extends TagsProvider<Item> {

    private static final List<String> FOODS = List.of(
            "farmersdelight:wheet_dough", "farmersdelight:rough_pasta", "farmersdelight:pumpkin_slice",
            "farmersdelight:cabbage_leaf", "farmersdelight:minced_beef", "farmersdelight:beef_patty",
            "farmersdelight:chicken_cuts", "farmersdelight:cooked_chicken_cuts", "farmersdelight:bacon",
            "farmersdelight:cooked_bacon", "farmersdelight:cod_slice", "farmersdelight:cooked_cod_slice",
            "farmersdelight:salmon_slice", "farmersdelight:cooked_salmon_slice", "farmersdelight:mutton_chops",
            "farmersdelight:cooked_mutton_chops", "farmersdelight:ham", "farmersdelight:smoked_ham",
            "farmersdelight:pie_crust", "farmersdelight:cake_slice", "farmersdelight:apple_pie_slice",
            "farmersdelight:sweet_berry_cheesecake_slice", "farmersdelight:chocolate_pie_slice",
            "farmersdelight:sweet_berry_cookie", "farmersdelight:honey_cookie", "farmersdelight:melon_popsicle",
            "farmersdelight:glow_berry_custard", "farmersdelight:fruit_salad", "farmersdelight:mixed_salad",
            "farmersdelight:nether_salad", "farmersdelight:barbecue_stick", "farmersdelight:egg_sandwich",
            "farmersdelight:chicken_sandwich", "farmersdelight:hamburger", "farmersdelight:bacon_sandwich",
            "farmersdelight:mutton_wrap", "farmersdelight:dumplings", "farmersdelight:kelp_roll",
            "farmersdelight:kelp_roll_slice", "farmersdelight:cooked_rice", "farmersdelight:bone_broth",
            "farmersdelight:beef_stew", "farmersdelight:chicken_soup", "farmersdelight:vegetable_soup",
            "farmersdelight:fish_stew", "farmersdelight:fried_rice", "farmersdelight:pumpkin_soup",
            "farmersdelight:baked_cod_stew", "farmersdelight:noodle_soup", "farmersdelight:bacon_and_eggs",
            "farmersdelight:pasta_with_meatballs", "farmersdelight:pasta_with_mutton_chop",
            "farmersdelight:mushroom_rice", "farmersdelight:roasted_mutton_chops",
            "farmersdelight:vegetable_noodles", "farmersdelight:steak_and_potatoes", "farmersdelight:ratatouille",
            "farmersdelight:squid_ink_pasta", "farmersdelight:grilled_salmon", "farmersdelight:roast_chicken",
            "farmersdelight:stuffed_pumpkin", "farmersdelight:honey_glazed_ham", "farmersdelight:shepherds_pie",
            "farmersdelight:dog_food", "twilightdelight:hydra_piece", "twilightdelight:raw_venison_rib",
            "twilightdelight:cooked_venison_rib", "twilightdelight:raw_meef_slice",
            "twilightdelight:cooked_meef_slice", "twilightdelight:raw_tomahawk_smeak",
            "twilightdelight:cooked_tomahawk_smeak", "twilightdelight:raw_insect",
            "twilightdelight:cooked_insect", "twilightdelight:torchberry_cookie", "twilightdelight:naga_chip",
            "twilightdelight:chocolate_wafer", "twilightdelight:experiment_113", "twilightdelight:chocolate_113",
            "twilightdelight:milky_113", "twilightdelight:glow_113", "twilightdelight:honey_113",
            "twilightdelight:experiment_110", "twilightdelight:meef_wrap", "twilightdelight:ghast_burger",
            "twilightdelight:hydra_burger", "twilightdelight:berry_stick", "twilightdelight:glowstew",
            "twilightdelight:mushgloom_sauce", "twilightdelight:glow_venison_rib_with_pasta",
            "twilightdelight:mushgloom_meef_pasta", "twilightdelight:fried_insect",
            "twilightdelight:thousand_plant_stew", "twilightdelight:grilled_ghast",
            "twilightdelight:grilled_tomahawk_smeak", "twilightdelight:borer_tear_soup",
            "twilightdelight:ghast_brain_salad", "twilightdelight:plate_of_lily_chicken",
            "twilightdelight:plate_of_fiery_snakes", "twilightdelight:plate_of_meef_wellington",
            "twilightdelight:thorn_rose_tea", "twilightdelight:torchberry_juice",
            "twilightdelight:phytochemical_juice", "twilightdelight:glacier_ice_tea",
            "twilightdelight:torchberry_pie_slice", "twilightdelight:aurora_pie_slice",
            "nethersdelight:hoglin_loin", "nethersdelight:hoglin_sirloin", "nethersdelight:hoglin_ear",
            "nethersdelight:strider_slice", "nethersdelight:grilled_strider", "nethersdelight:ground_strider",
            "nethersdelight:warped_moldy_meat", "nethersdelight:strider_moss_stew",
            "nethersdelight:plate_of_stuffed_hoglin_snout", "nethersdelight:plate_of_stuffed_hoglin_ham",
            "nethersdelight:plate_of_stuffed_hoglin_roast", "nethersdelight:nether_skewer",
            "nethersdelight:magma_gelatin", "nethersdelight:propelpearl");

    private static final List<String> FERMENTABLE = List.of(
            "minecraft:carrot", "minecraft:potato", "minecraft:beetroot",
            "minecraft:wheat_seeds", "minecraft:pumpkin_seeds", "minecraft:melon_stem");

    private static final List<String> UPRIGHT_ON_BELT = List.of(
            "gtmfo:smore_1", "gtmfo:smore_2", "gtmfo:smore_4");

    public CTNHExtraItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider,
                                     @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.ITEM, provider, CTNHCore.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(itemTag("alexscaves", "deep_one_barters"))
                .addOptionalTag(ResourceLocation.fromNamespaceAndPath("forge", "exquisite_gems"));

        addOptionals(itemTag("forge", "foods"), FOODS);
        addOptionals(itemTag("forge", "fermentable"), FERMENTABLE);
        addOptionals(itemTag("forge", "stripped_logs"), List.of("gtceu:stripped_rubber_log"));
        addOptionals(itemTag("curios", "curio"), List.of("tiab:time_in_a_bottle"));
        addOptionals(itemTag("create", "upright_on_belt"), UPRIGHT_ON_BELT);

        addByPattern(itemTag("farmersdelight", "straw_harvesters"), "gtceu:(?!.*butchery_).*_knife");
        addByPattern(itemTag("farmersdelight", "tools/knives"), "gtceu:(?!.*butchery_).*_knife");
    }

    private void addOptionals(TagKey<Item> tagKey, List<String> ids) {
        var builder = tag(tagKey);
        ids.stream()
                .map(ResourceLocation::parse)
                .forEach(builder::addOptional);
    }

    private void addByPattern(TagKey<Item> tagKey, String pattern) {
        var builder = tag(tagKey);
        TagClearHelper.expandPattern(pattern, BuiltInRegistries.ITEM)
                .forEach(builder::addOptional);
    }

    private static TagKey<Item> itemTag(String namespace, String path) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(namespace, path));
    }
}
