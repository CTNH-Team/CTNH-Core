package io.github.cpearl0.ctnhcore.data.recipe.migrated;

import io.github.cpearl0.ctnhcore.registry.CTNHItems;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import committee.nova.mods.avaritia.init.data.provider.recipe.ModShapedRecipeBuilder;

import java.util.function.Consumer;

public class AvaritiaScriptRecipes {

    // 迁移来源：Z:/Git/Create-New-Horizon/kubejs/server_scripts/src/avaritia.js
    // event.recipes.avaritia.shaped_table(2, ...) → tier 2 = End Crafting Table (5x5)
    public static void init(Consumer<FinishedRecipe> provider) {
        addGalaxyMeatball(provider);
        addPrimaryStew(provider);
        addCrystalCatalyst(provider);
    }

    private static void addGalaxyMeatball(Consumer<FinishedRecipe> provider) {
        ModShapedRecipeBuilder.shaped(RecipeCategory.MISC, CTNHItems.GALAXY_MEATBALL.get(), 4, 2)
                .pattern("ABCDE")
                .pattern("FGHIJ")
                .pattern("KLMNO")
                .pattern("PQRST")
                .pattern("UVWXY")
                .define('A', itemOrNull("minecraft:rotten_flesh"))
                .define('B', itemOrNull("minecraft:spider_eye"))
                .define('C', itemOrNull("ends_delight:raw_ender_mite_meat"))
                .define('D', Items.RABBIT)
                .define('E', Items.CHICKEN)
                .define('F', itemOrNull("mynethersdelight:strider_slice"))
                .define('G', itemOrNull("twilightforest:raw_meef"))
                .define('H', itemOrNull("twilightforest:hydra_chop"))
                .define('I', itemOrNull("ends_delight:raw_dragon_meat"))
                .define('J', Items.HONEY_BOTTLE)
                .define('K', itemOrNull("mynethersdelight:hoglin_loin"))
                .define('L', itemOrNull("alexscaves:dinosaur_chop"))
                .define('M', itemOrNull("minecraft:ochre_froglight"))
                .define('N', itemOrNull("artifacts:everlasting_beef"))
                .define('O', itemOrNull("deep_aether:raw_quail"))
                .define('P', itemOrNull("alexscaves:dinosaur_nugget"))
                .define('Q', itemOrNull("cataclysm:blessed_amethyst_crab_meat"))
                .define('R', itemOrNull("apotheosis:warden_tendril"))
                .define('S', itemOrNull("biomancy:living_flesh"))
                .define('T', Items.BEEF)
                .define('U', itemOrNull("biomancy:flesh_bits"))
                .define('V', itemOrNull("twilightdelight:raw_insect"))
                .define('W', itemOrNull("twilightforest:raw_venison"))
                .define('X', itemOrNull("ecologics:crab_claw"))
                .define('Y', Items.MUTTON)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ROTTEN_FLESH))
                .save(provider, ResourceLocation.parse("ctnhcore:avaritia/galaxy_meatball"));
    }

    private static void addPrimaryStew(Consumer<FinishedRecipe> provider) {
        ModShapedRecipeBuilder.shaped(RecipeCategory.MISC, CTNHItems.PRIMARY_STEW.get(), 2, 2)
                .pattern("ABCDE")
                .pattern("FGHIJ")
                .pattern("KLMNO")
                .pattern("PQRST")
                .pattern("UVWXY")
                .define('A', itemOrNull("mynethersdelight:plate_of_cold_striderloaf"))
                .define('B', itemOrNull("mynethersdelight:plate_of_ghasta_with_cream"))
                .define('C', itemOrNull("mynethersdelight:hot_wings_bucket"))
                .define('D', itemOrNull("mynethersdelight:magma_cake_slice"))
                .define('E', itemOrNull("mynethersdelight:hot_cream_cone"))
                .define('F', itemOrNull("twilightdelight:ghast_brain_salad"))
                .define('G', Items.SUSPICIOUS_STEW)
                .define('H', itemOrNull("createcafe:oreo"))
                .define('I', itemOrNull("createcafe:blood_orange_milk_tea"))
                .define('J', itemOrNull("createcafe:iced_coffee"))
                .define('K', itemOrNull("farmersdelight:honey_glazed_ham"))
                .define('L', itemOrNull("farmersdelight:shepherds_pie"))
                .define('M', CTNHItems.GALAXY_MEATBALL.get())
                .define('N', itemOrNull("culturaldelights:exotic_roll_medley"))
                .define('O', itemOrNull("ends_delight:dragon_leg_with_sauce"))
                .define('P', itemOrNull("ends_delight:steamed_dragon_egg"))
                .define('Q', itemOrNull("ends_delight:grilled_shulker"))
                .define('R', itemOrNull("legendarysurvivaloverhaul:glistering_melon_juice"))
                .define('S', itemOrNull("twilightdelight:plate_of_lily_chicken"))
                .define('T', itemOrNull("twilightdelight:plate_of_fiery_snakes"))
                .define('U', itemOrNull("twilightdelight:plate_of_meef_wellington"))
                .define('V', itemOrNull("twilightdelight:tear_drink"))
                .define('W', itemOrNull("biomancy:nutrient_bar"))
                .define('X', Items.RABBIT_STEW)
                .define('Y', itemOrNull("mynethersdelight:plate_of_stuffed_hoglin"))
                .unlockedBy("has_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(CTNHItems.GALAXY_MEATBALL.get()))
                .save(provider, ResourceLocation.parse("ctnhcore:avaritia/primary_stew"));
    }

    private static void addCrystalCatalyst(Consumer<FinishedRecipe> provider) {
        ModShapedRecipeBuilder.shaped(RecipeCategory.MISC, CTNHItems.CRYSTAL_CATALYST.get(), 1, 2)
                .pattern("ABCDE")
                .pattern("FGHIJ")
                .pattern("KLMNO")
                .pattern("PQRST")
                .pattern("UVWXY")
                .define('A', itemOrNull("apotheosis:gem"))
                .define('B', itemOrNull("ctnhmana:elf_catalyst"))
                .define('C', itemOrNull("cataclysm:ceraunus"))
                .define('D', itemOrNull("gtceu:damascus_steel_wrench"))
                .define('E', itemOrNull("bloodmagic:minekey"))
                .define('F', itemOrNull("alexscaves:nuclear_bomb"))
                .define('G', itemOrNull("deep_aether:sun_core"))
                .define('H', Items.HEART_OF_THE_SEA)
                .define('I', itemOrNull("deep_aether:aerwhale_saddle"))
                .define('J', Items.NETHERITE_INGOT)
                .define('K', itemOrNull("cataclysm:cursed_bow"))
                .define('L', itemOrNull("ctnhcore:platinum_metal_catalyst_shard1"))
                .define('M', CTNHItems.PRIMARY_STEW.get())
                .define('N', itemOrNull("ctnhcore:platinum_metal_catalyst_shard2"))
                .define('O', itemOrNull("cataclysm:the_incinerator"))
                .define('P', itemOrNull("twilightforest:lamp_of_cinders"))
                .define('Q', itemOrNull("deep_aether:slider_eye"))
                .define('R', itemOrNull("apotheosis:infused_breath"))
                .define('S', itemOrNull("deep_aether:medal_of_honor"))
                .define('T', itemOrNull("ars_nouveau:wilden_tribute"))
                .define('U', itemOrNull("botania:apothecary_taiga"))
                .define('V', itemOrNull("biomancy:bio_lantern_blue"))
                .define('W', itemOrNull("ad_astra:moon_globe"))
                .define('X', itemOrNull("ctnhcore:stone_process_catalyst"))
                .define('Y', itemOrNull("extrabotany:pandoras_box"))
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(CTNHItems.PRIMARY_STEW.get()))
                .save(provider, ResourceLocation.parse("ctnhcore:avaritia/crystal_catalyst"));
    }

    /**
     * Resolve an item by registry name, returning Items.AIR if not found (for optional mod compat).
     * Avaritia shaped_table recipes ignore air items.
     */
    private static net.minecraft.world.level.ItemLike itemOrNull(String id) {
        var item = net.minecraftforge.registries.ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(id));
        return item != null ? item : Items.AIR;
    }
}
