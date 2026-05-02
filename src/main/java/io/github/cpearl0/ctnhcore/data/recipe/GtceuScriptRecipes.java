package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.*;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import com.mo_guang.ctpp.registry.CTPPMaterials;

import appeng.core.definitions.AEItems;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHItems.*;

public class GtceuScriptRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        // ============== Mixer Recipes ==============

        // 1. rose_quartz2: circuit 5, quartz + 4x redstone -> rose_quartz. EUt 32, dur 100
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("rose_quartz2"))
                .EUt(32).duration(100)
                .circuitMeta(5)
                .inputItems(new ItemStack(Items.QUARTZ))
                .inputItems(new ItemStack(Items.REDSTONE, 4))
                .outputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("create:rose_quartz")))
                .save(provider);

        // 2. polished_rose_quartz2: quartz + redstone 288 -> polished_rose_quartz. EUt 32, dur 100
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("polished_rose_quartz2"))
                .EUt(32).duration(100)
                .inputItems(new ItemStack(Items.QUARTZ))
                .inputFluids(Redstone.getFluid(288))
                .outputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("create:polished_rose_quartz")))
                .save(provider);

        // 3. sky_dust: circuit 2, 10x stone_dust + 2x silicon_dust + electrotine_dust -> 13x sky_dust. EUt 120, dur 100
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("sky_dust"))
                .EUt(120).duration(100)
                .circuitMeta(2)
                .inputItems(dust, Stone, 10)
                .inputItems(dust, Silicon, 2)
                .inputItems(dust, Electrotine)
                .outputItems(AEItems.SKY_DUST.asStack(13))
                .save(provider);

        // 4. aeternium: 2x steel_dust + ender_pearl_dust + netherite_dust -> 4x aeternium_dust. EUt 480, dur 100
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("aeternium"))
                .EUt(480).duration(100)
                .inputItems(dust, Steel, 2)
                .inputItems(dust, EnderPearl)
                .inputItems(dust, Netherite)
                .outputItems(dust, NewExplosivesProductionMaterials.AETERNIUM, 4)
                .save(provider);

        // 5. pyrotheum: small_saltpeter + sulfur_dust + coke_dust + blaze_powder -> 2x pyrotheum_dust. EUt 120, dur 40
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("pyrotheum"))
                .EUt(120).duration(40)
                .inputItems(dustSmall, Saltpeter)
                .inputItems(dust, Sulfur)
                .inputItems(dust, Coke)
                .inputItems(new ItemStack(Items.BLAZE_POWDER))
                .outputItems(dust, CTNHMaterials.Pyrotheum, 2)
                .save(provider);

        // 6. cryotheum: small_saltpeter + snowball + ice_dust + redstone -> 2x cryotheum_dust. EUt 480, dur 40
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("cryotheum"))
                .EUt(480).duration(40)
                .inputItems(dustSmall, Saltpeter)
                .inputItems(new ItemStack(Items.SNOWBALL))
                .inputItems(dust, Ice)
                .inputItems(new ItemStack(Items.REDSTONE))
                .outputItems(dust, CTNHMaterials.Cryotheum, 2)
                .save(provider);

        // 7. chorusite_alloy_dust: andesite_alloy_dust + 2x popped_chorus_fruit + dragon_breath -> 4x chorusite_alloy_dust. EUt 120, dur 200
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("chorusite_alloy_dust"))
                .EUt(120).duration(200)
                .inputItems(dust, CTPPMaterials.AndesiteAlloy)
                .inputItems(new ItemStack(Items.POPPED_CHORUS_FRUIT, 2))
                .inputItems(new ItemStack(Items.DRAGON_BREATH))
                .outputItems(dust, EnderIOMaterials.ChorusiteAlloy, 4)
                .save(provider);

        // 8. dibismuthhydroborat: 2x bismuth_dust + boron_dust + hydrogen 1000 -> 4x dibismuthhydroborat_dust. EUt 120, dur 590
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("dibismuthhydroborat"))
                .EUt(120).duration(590)
                .inputItems(dust, Bismuth, 2)
                .inputItems(dust, Boron)
                .inputFluids(Hydrogen.getFluid(1000))
                .outputItems(dust, SpecialMaterials.DIBISMUTHHYDROBORAT, 4)
                .save(provider);

        // 9. bismuth_tellurite: 2x bismuth_dust + 3x tellurium_dust -> 5x bismuth_tellurite_dust. EUt 80, dur 162
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("bismuth_tellurite"))
                .EUt(80).duration(162)
                .inputItems(dust, Bismuth, 2)
                .inputItems(dust, Tellurium, 3)
                .outputItems(dust, SpecialMaterials.BISMUTH_TELLURITE, 5)
                .save(provider);

        // 10. circuit_compound: 3x dibismuthhydroborat + 2x bismuth_tellurite + indium_gallium_phosphide -> 6x circuit_compound_dust. EUt 15, dur 982
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("circuit_compound"))
                .EUt(15).duration(982)
                .inputItems(dust, SpecialMaterials.DIBISMUTHHYDROBORAT, 3)
                .inputItems(dust, SpecialMaterials.BISMUTH_TELLURITE, 2)
                .inputItems(dust, IndiumGalliumPhosphide)
                .outputItems(dust, SpecialMaterials.CIRCUIT_COMPOUND, 6)
                .save(provider);

        // 11. samarium_dysprosium_terbium_permanent_magnet_alloy: samarium + dysprosium + terbium -> 3x alloy_dust. EUt 122320, dur 200
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("samarium_dysprosium_terbium_permanent_magnet_alloy"))
                .EUt(122320).duration(200)
                .inputItems(dust, Samarium)
                .inputItems(dust, Dysprosium)
                .inputItems(dust, Terbium)
                .outputItems(dust, BedrockMaterials.SAMARIUM_DYSPROSIUM_TERBIUM_PERMANENT_MAGNET_ALLOY, 3)
                .save(provider);

        // 12. nether_essence_crystal_fluid: gold + steel + aluminium + gaas + stainless + small_nether_star + lava -> nether_essence_crystal_fluid 864. EUt 480, dur 500
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("nether_essence_crystal_fluid"))
                .EUt(480).duration(500)
                .inputItems(dust, Gold)
                .inputItems(dust, Steel)
                .inputItems(dust, Aluminium)
                .inputItems(dust, GalliumArsenide)
                .inputItems(dust, StainlessSteel)
                .inputItems(dustSmall, NetherStar)
                .inputFluids(Lava.getFluid(1000))
                .outputFluids(UncategorizedMaterials.NETHER_ESSENCE_CRYSTAL_FLUID.getFluid(864))
                .save(provider);

        // 13. eglin_steel_ingot: 5x invar_dust + 4x iron_dust + kanthal_dust -> 10x eglin_steel_ingot_dust. EUt 64, dur 300
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("eglin_steel_ingot"))
                .EUt(64).duration(300)
                .inputItems(dust, Invar, 5)
                .inputItems(dust, Iron, 4)
                .inputItems(dust, Kanthal)
                .outputItems(dust, UncategorizedMaterials.EGLIN_STEEL_INGOT, 10)
                .save(provider);

        // 14. eglin_alloy_dust: circuit 4, 10x eglin_steel_ingot_dust + sulfur + silver + carbon -> 13x eglin_alloy_dust. EUt 64, dur 150
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("eglin_alloy_dust"))
                .EUt(64).duration(150)
                .circuitMeta(4)
                .inputItems(dust, UncategorizedMaterials.EGLIN_STEEL_INGOT, 10)
                .inputItems(dust, Sulfur)
                .inputItems(dust, Silver)
                .inputItems(dust, Carbon)
                .outputItems(dust, CTNHMaterials.Eglinalloy, 13)
                .save(provider);

        // 15. cement: circuit 6, calcite_dust + 4x clay_dust + 2x iron_dust + water 10000 -> cement 14400. EUt 24, dur 100
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("cement"))
                .EUt(24).duration(100)
                .circuitMeta(6)
                .inputItems(dust, Calcite)
                .inputItems(dust, Clay, 4)
                .inputItems(dust, Iron, 2)
                .inputFluids(Water.getFluid(10000))
                .outputFluids(UncategorizedMaterials.CEMENT.getFluid(14400))
                .save(provider);

        // echo_shard: silicon_dioxide + sculk_dust -> echo_shard_dust. EUt 32, dur 200
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("echo_shard"))
                .EUt(32).duration(200)
                .inputItems(dust, SiliconDioxide, 3)
                .inputItems(dust, Sculk, 2)
                .outputItems(dust, EchoShard, 5)
                .save(provider);

        // ============== Alloy Smelter Recipes ==============

        // 1. andesite_alloy: andesite + iron_ingot -> 2x andesite_alloy_ingot. EUt 7, dur 100
        ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("andesite_alloy"))
                .EUt(7).duration(100)
                .inputItems(new ItemStack(Items.ANDESITE))
                .inputItems(new ItemStack(Items.IRON_INGOT))
                .outputItems(ingot, CTPPMaterials.AndesiteAlloy, 2)
                .save(provider);

        // 2. scarlet_ingot: raw_scarlet_neodymium + iron_ingot -> 2x scarlet_neodymium_ingot. EUt 7, dur 100
        ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("scarlet_ingot"))
                .EUt(7).duration(100)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("alexscaves:raw_scarlet_neodymium")))
                .inputItems(new ItemStack(Items.IRON_INGOT))
                .outputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("alexscaves:scarlet_neodymium_ingot")), 2)
                .save(provider);

        // 3. azure_ingot: raw_azure_neodymium + iron_ingot -> 2x azure_neodymium_ingot. EUt 7, dur 100
        ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("azure_ingot"))
                .EUt(7).duration(100)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("alexscaves:raw_azure_neodymium")))
                .inputItems(new ItemStack(Items.IRON_INGOT))
                .outputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("alexscaves:azure_neodymium_ingot")), 2)
                .save(provider);

        // 4. dark_steel: steel_ingot + obsidian_dust -> dark_steel_ingot. EUt 120, dur 150
        ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("dark_steel"))
                .EUt(120).duration(150)
                .inputItems(ingot, Steel)
                .inputItems(dust, Obsidian)
                .outputItems(ingot, EnderIOMaterials.DarkSteel)
                .save(provider);

        // ============== Macerator Recipes ==============

        // 1. ancient_debris_dust: ancient_debris -> 4x ancient_debris_dust + netherrack_dust, chanced gold_dust 2000/200. EUt 120, dur 200
        MACERATOR_RECIPES.recipeBuilder(CTNHCore.id("ancient_debris_dust"))
                .EUt(120).duration(200)
                .inputItems(new ItemStack(Items.ANCIENT_DEBRIS))
                .outputItems(dust, NewExplosivesProductionMaterials.ANCIENT_DEBRIS, 4)
                .outputItems(dust, Netherrack)
                .chancedOutput(dust, Gold, 2000, 200)
                .save(provider);

        // 2. tiny_uranium_dust: uranium_shard -> tiny_uranium_dust, chanced tiny_uranium 2000/150. EUt 30, dur 20
        MACERATOR_RECIPES.recipeBuilder(CTNHCore.id("tiny_uranium_dust"))
                .EUt(30).duration(20)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("alexscaves:uranium_shard")))
                .outputItems(dustTiny, Uranium)
                .chancedOutput(dustTiny, Uranium, 2000, 150)
                .save(provider);

        // 3. dried_salt_stone: dried_salt -> dried_salt_dust. EUt 16, dur 30
        MACERATOR_RECIPES.recipeBuilder(CTNHCore.id("dried_salt_stone"))
                .EUt(16).duration(30)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:dried_salt")))
                .outputItems(dust, BiodieselFertileSoilMaterials.DRIED_SALT)
                .save(provider);

        // 4. rich_soil: rich_soil -> 4x rich_soil_dust, chanced normal_yeast 2500/200. EUt 30, dur 20
        MACERATOR_RECIPES.recipeBuilder(CTNHCore.id("rich_soil"))
                .EUt(30).duration(20)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("farmersdelight:rich_soil")))
                .outputItems(dust, BiodieselFertileSoilMaterials.RICH_SOIL, 4)
                .chancedOutput(dust, YeastRelatedMaterials.NORMAL_YEAST, 2500, 200)
                .save(provider);

        // 5. rich_soul_soil: resurgent_soil -> 4x rich_soul_soil_dust, chanced crimson_yeast 1500/150, warped_yeast 1500/150. EUt 30, dur 20
        MACERATOR_RECIPES.recipeBuilder(CTNHCore.id("rich_soul_soil"))
                .EUt(30).duration(20)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("mynethersdelight:resurgent_soil")))
                .outputItems(dust, BiodieselFertileSoilMaterials.RICH_SOUL_SOIL, 4)
                .chancedOutput(dust, YeastRelatedMaterials.CRIMSON_YEAST, 1500, 150)
                .chancedOutput(dust, YeastRelatedMaterials.WARPED_YEAST, 1500, 150)
                .save(provider);

        // 6. asurine/crimsite/ochrum/veridium macerator loop
        String[] stones = {"asurine", "crimsite", "ochrum", "veridium"};
        var stoneMaterials = new HashMap<String, Material>();
        stoneMaterials.put("asurine", CreateMaterials.ASURINE);
        stoneMaterials.put("crimsite", CreateMaterials.CRIMSITE);
        stoneMaterials.put("ochrum", CreateMaterials.OCHRUM);
        stoneMaterials.put("veridium", CreateMaterials.VERIDIUM);
        for (String stone : stones) {
            MACERATOR_RECIPES.recipeBuilder(CTNHCore.id(stone + "_macerator"))
                    .EUt(30).duration(100)
                    .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("create:" + stone)))
                    .outputItems(dust, stoneMaterials.get(stone))
                    .chancedOutput(dust, stoneMaterials.get(stone), 1500, 500)
                    .save(provider);
        }

        // 7. seashell: seashell -> 2x calcite_dust. EUt 7, dur 80
        MACERATOR_RECIPES.recipeBuilder(CTNHCore.id("seashell"))
                .EUt(7).duration(80)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ecologics:seashell")))
                .outputItems(dust, Calcite, 2)
                .save(provider);

        // 8. sculk_dust: 2x sculk_vein -> sculk_dust. EUt 8, dur 10
        MACERATOR_RECIPES.recipeBuilder(CTNHCore.id("sculk_dust"))
                .EUt(8).duration(10)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("minecraft:sculk_vein")), 2)
                .outputItems(dust, Sculk)
                .save(provider);
    }
}
