package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.*;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.common.data.machines.GTMultiMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import com.mo_guang.ctpp.registry.CTPPMaterials;

import appeng.core.definitions.AEItems;

import io.github.cpearl0.ctnhcore.registry.machines.CTNHMachines;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksB;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.data.materials.AviationFabricMaterials.KAPTON_K;
import static io.github.cpearl0.ctnhcore.registry.CTNHBlocks.*;
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
                .inputItems(new ItemStack(Items.SCULK_VEIN, 2))
                .outputItems(dust, Sculk)
                .save(provider);

        // ============== Assembler Recipes ==============

        // 1. manatransformer: circuits/lv + 4x red_alloy_plate + 4x botania:livingrock -> botania:mana_fluxfield. EUt 30, dur 100
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("manatransformer"))
                .EUt(30).duration(100)
                .inputItems(CustomTags.LV_CIRCUITS)
                .inputItems(plate, RedAlloy, 4)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("botania:livingrock")), 4)
                .outputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("botania:mana_fluxfield")))
                .save(provider);

        // 2. submarine: alexscaves:enigmatic_engine + 2x #hv + 4x energy_crystal + 2x hv_sensor + 4x hv_electric_motor + 8x red_steel_plate -> alexscaves:submarine. EUt 480, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("submarine"))
                .EUt(480).duration(400)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("alexscaves:enigmatic_engine")))
                .inputItems(CustomTags.HV_CIRCUITS, 2)
                .inputItems(GTItems.ENERGIUM_CRYSTAL.asStack(4))
                .inputItems(GTItems.SENSOR_HV.asStack(2))
                .inputItems(GTItems.ELECTRIC_MOTOR_HV.asStack(4))
                .inputItems(plate, RedSteel, 8)
                .outputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("alexscaves:submarine")))
                .save(provider);

        // 3. blaze_blast_furnace_casing: 4x stainless_steel_plate + 2x stainless_steel_frame + botania:blaze_block + PVC 288 -> ctnhcore:blaze_blast_furnace_casing. EUt 480, dur 100
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("blaze_blast_furnace_casing"))
                .EUt(480).duration(100)
                .inputItems(plate, StainlessSteel, 4)
                .inputItems(frameGt, StainlessSteel, 2)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("botania:blaze_block")))
                .inputFluids(PolyvinylChloride.getFluid(288))
                .outputItems(BLAZE_BLAST_FURNACE_CASING.asStack())
                .save(provider);

        // 4. blaze_blast_furnace: 4x #ev + 4x blaze_blast_furnace_casing + 2x pyrotheum_dust + 2x hv_sensor + hv_field_generator + PVC 288 -> ctnhcore:blaze_blast_furnace. EUt 480, dur 200
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("blaze_blast_furnace"))
                .EUt(480).duration(200)
                .inputItems(CustomTags.EV_CIRCUITS, 4)
                .inputItems(BLAZE_BLAST_FURNACE_CASING.asStack(4))
                .inputItems(dust, CTNHMaterials.Pyrotheum, 2)
                .inputItems(GTItems.SENSOR_HV.asStack(2))
                .inputItems(GTItems.FIELD_GENERATOR_HV.asStack())
                .inputFluids(PolyvinylChloride.getFluid(288))
                .outputItems(MultiblocksA.BLAZE_BLAST_FURNACE.asStack())
                .save(provider);

        // 5. thermometer_casing: 2x gold_plate + 2x redstone + steel_rod + rubber 144 -> kubejs:thermometer_case. EUt 30, dur 100
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("thermometer_casing"))
                .EUt(30).duration(100)
                .inputItems(plate, Gold, 2)
                .inputItems(new ItemStack(Items.REDSTONE, 2))
                .inputItems(rod, Steel)
                .inputFluids(Rubber.getFluid(144))
                .outputItems(THERMOMETER_CASE.asStack())
                .save(provider);

        // 6. cover_ender_fluid_link: 2x ender_pearl_plate + hv_sensor + double_stainless_steel_plate + hv_emitter + hv_electric_pump + polyethylene 288 -> gtceu:ender_fluid_link_cover. EUt 480, dur 320
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("cover_ender_fluid_link"))
                .EUt(480).duration(320)
                .inputItems(plate, EnderPearl, 2)
                .inputItems(GTItems.SENSOR_HV.asStack())
                .inputItems(plateDouble, StainlessSteel)
                .inputItems(GTItems.EMITTER_HV.asStack())
                .inputItems(GTItems.ELECTRIC_PUMP_HV.asStack())
                .inputFluids(Polyethylene.getFluid(288))
                .outputItems(GTItems.COVER_ENDER_FLUID_LINK.asStack())
                .save(provider);

        // 7. lv_energy_output_hatch_4a: lv_energy_output_hatch + 2x tin_quadruple_wire + 2x steel_plate -> ctnhcore:lv_energy_output_hatch_4a. EUt 7, dur 100
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("lv_energy_output_hatch_4a"))
                .EUt(7).duration(100)
                .inputItems(GTMachines.ENERGY_OUTPUT_HATCH[GTValues.LV].asStack())
                .inputItems(wireGtQuadruple, Tin, 2)
                .inputItems(plate, Steel, 2)
                .outputItems(CTNHMachines.ENERGY_OUTPUT_HATCH_4A_LOWER[GTValues.LV].asStack())
                .save(provider);

        // 8. mv_energy_output_hatch_4a: mv_energy_output_hatch + 2x cupronickel_quadruple_wire + 2x aluminium_plate -> ctnhcore:mv_energy_output_hatch_4a. EUt 30, dur 100
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("mv_energy_output_hatch_4a"))
                .EUt(30).duration(100)
                .inputItems(GTMachines.ENERGY_OUTPUT_HATCH[GTValues.MV].asStack())
                .inputItems(wireGtQuadruple, Cupronickel, 2)
                .inputItems(plate, Aluminium, 2)
                .outputItems(CTNHMachines.ENERGY_OUTPUT_HATCH_4A_LOWER[GTValues.MV].asStack())
                .save(provider);

        // 9. hv_energy_output_hatch_4a: hv_energy_output_hatch + 2x silver_quadruple_wire + 2x stainless_steel_plate -> ctnhcore:hv_energy_output_hatch_4a. EUt 120, dur 100
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("hv_energy_output_hatch_4a"))
                .EUt(120).duration(100)
                .inputItems(GTMachines.ENERGY_OUTPUT_HATCH[GTValues.HV].asStack())
                .inputItems(wireGtQuadruple, Silver, 2)
                .inputItems(plate, StainlessSteel, 2)
                .outputItems(CTNHMachines.ENERGY_OUTPUT_HATCH_4A_LOWER[GTValues.HV].asStack())
                .save(provider);

        // 10. fluxuated_magnetite: create_new_age:layered_magnet + 2x botania:mana_diamond + 2x diamond_screw -> create_new_age:fluxuated_magnetite. EUt 30, dur 60
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("fluxuated_magnetite"))
                .EUt(30).duration(60)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("create_new_age:layered_magnet")))
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("botania:mana_diamond")), 2)
                .inputItems(screw, Diamond, 2)
                .outputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("create_new_age:fluxuated_magnetite")))
                .save(provider);

        // 11. space_helmet: glass_plate + 4x space_fabric + glue 72 -> ad_astra:space_helmet. EUt 120, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("space_helmet"))
                .EUt(120).duration(400)
                .inputItems(plate, Glass)
                .inputItems(SPACE_FABRIC.asStack(4))
                .inputFluids(Glue.getFluid(72))
                .outputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ad_astra:space_helmet")))
                .save(provider);

        // 12. space_suit: ad_astra:oxygen_gear + 2x ad_astra:gas_tank + 4x heavy_plate_t1 + 2x stainless_steel_screw + 4x space_fabric -> ad_astra:space_suit. EUt 120, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("space_suit"))
                .EUt(120).duration(400)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ad_astra:oxygen_gear")))
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ad_astra:gas_tank")), 2)
                .inputItems(HEAVY_PLATE_T1.asStack(4))
                .inputItems(screw, StainlessSteel, 2)
                .inputItems(SPACE_FABRIC.asStack(4))
                .outputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ad_astra:space_suit")))
                .save(provider);

        // 13. space_pants: 5x heavy_plate_t1 + 3x space_fabric -> ad_astra:space_pants. circuit 0. EUt 120, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("space_pants"))
                .EUt(120).duration(400)
                .circuitMeta(0)
                .inputItems(HEAVY_PLATE_T1.asStack(5))
                .inputItems(SPACE_FABRIC.asStack(3))
                .outputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ad_astra:space_pants")))
                .save(provider);

        // 14. space_boots: 4x heavy_plate_t1 + 2x space_fabric -> ad_astra:space_boots. circuit 1. EUt 120, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("space_boots"))
                .EUt(120).duration(400)
                .circuitMeta(1)
                .inputItems(HEAVY_PLATE_T1.asStack(4))
                .inputItems(SPACE_FABRIC.asStack(2))
                .outputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ad_astra:space_boots")))
                .save(provider);

        // 15. deep_learner: computer_monitor_cover + 2x double_black_steel_plate + 2x black_steel_plate + black_steel_gear + #ev -> hostilenetworks:deep_learner. EUt 480, dur 200
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("deep_learner"))
                .EUt(480).duration(200)
                .inputItems(GTItems.COVER_SCREEN.asStack())
                .inputItems(plateDouble, BlackSteel, 2)
                .inputItems(plate, BlackSteel, 2)
                .inputItems(gear, BlackSteel)
                .inputItems(CustomTags.EV_CIRCUITS)
                .outputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("hostilenetworks:deep_learner")))
                .save(provider);

        // 16. fuel_refining_factory: 12x large_chemical_reactor + 8x distillation_tower + 8x cracker + 32x nichrome_coil_block + 7x dense_steel_plate + 7x dense_stainless_steel_plate + 64x black_steel_frame + 32x #iv -> ctnhcore:fuel_refining_factory. EUt 480, dur 48000
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("fuel_refining_factory"))
                .EUt(480).duration(48000)
                .inputItems(GTMultiMachines.LARGE_CHEMICAL_REACTOR.asStack(12))
                .inputItems(GTMultiMachines.DISTILLATION_TOWER.asStack(8))
                .inputItems(GTMultiMachines.CRACKER.asStack(8))
                .inputItems(GTBlocks.COIL_NICHROME.asStack(32))
                .inputItems(plateDense, Steel, 7)
                .inputItems(plateDense, StainlessSteel, 7)
                .inputItems(frameGt, BlackSteel, 64)
                .inputItems(CustomTags.IV_CIRCUITS, 32)
                .outputItems(MultiblocksA.FUEL_REFINING_FACTORY.asStack())
                .save(provider);

        // 17. large_fermenting: 4x fermenting_tank + 4x titanium_plate + 2x #iv + 2x iv_robot_arm + 4x tempered_glass + biomass 1000 -> ctnhcore:large_fermenting_tank. EUt 1920, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("large_fermenting"))
                .EUt(1920).duration(400)
                .inputItems(MultiblocksA.FERMENTING_TANK.asStack(4))
                .inputItems(plate, Titanium, 4)
                .inputItems(CustomTags.IV_CIRCUITS, 2)
                .inputItems(GTItems.ROBOT_ARM_IV.asStack(2))
                .inputItems(GTBlocks.CASING_TEMPERED_GLASS.asStack(4))
                .inputFluids(Biomass.getFluid(1000))
                .outputItems(MultiblocksA.LARGE_FERMENTING_TANK.asStack())
                .save(provider);

        // 18. large_bottle: 2x ev_super_tank + 4x tempered_glass + 2x ev_electric_pump + polyethylene 288 -> ctnhcore:large_bottle. EUt 480, dur 120
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("large_bottle"))
                .EUt(480).duration(120)
                .inputItems(GTMachines.SUPER_TANK[GTValues.EV].asStack(2))
                .inputItems(GTBlocks.CASING_TEMPERED_GLASS.asStack(4))
                .inputItems(GTItems.ELECTRIC_PUMP_EV.asStack(2))
                .inputFluids(Polyethylene.getFluid(288))
                .outputItems(MultiblocksA.LARGE_BOTTLE.asStack())
                .save(provider);

        // 19. refractory_brick_1: coke_oven_bricks + 4x firebrick + concrete 500 -> gtceu:firebricks. EUt 24, dur 50
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("refractory_brick_1"))
                .EUt(24).duration(50)
                .inputItems(GTBlocks.CASING_COKE_BRICKS.asStack())
                .inputItems(GTItems.FIRECLAY_BRICK.asStack(4))
                .inputFluids(Concrete.getFluid(500))
                .outputItems(GTBlocks.CASING_PRIMITIVE_BRICKS.asStack())
                .save(provider);

        // 20. decay_pools_machine: 28x dense_lead_plate + hv_machine_hull + 4x neutron_source + 8x double_uranium_plate + soldering_alloy 4000 -> ctnhcore:decay_pools_machine. EUt 1920, dur 900
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("decay_pools_machine"))
                .EUt(1920).duration(900)
                .inputItems(plateDense, Lead, 28)
                .inputItems(GTMachines.HULL[GTValues.HV].asStack())
                .inputItems(NeutronSource.asStack(4))
                .inputItems(plateDouble, Uranium, 8)
                .inputFluids(SolderingAlloy.getFluid(4000))
                .outputItems(MultiblocksA.DECAY_POOLS.asStack())
                .save(provider);

        // 21. plasma_condensation_coil: superconducting_coil + 4x luv_electric_pump + 32x niobium_titanium_tiny_fluid_pipe + 4x luv_field_generator + liquid_helium 8000 -> ctnhcore:plasma_cooled_core. EUt 24768, dur 200
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("plasma_condensation_coil"))
                .EUt(24768).duration(200)
                .inputItems(GTBlocks.SUPERCONDUCTING_COIL.asStack())
                .inputItems(GTItems.ELECTRIC_PUMP_LuV.asStack(4))
                .inputItems(pipeTinyFluid, NiobiumTitanium, 32)
                .inputItems(GTItems.FIELD_GENERATOR_LuV.asStack(4))
                .inputFluids(Helium.getFluid(FluidStorageKeys.LIQUID, 8000))
                .outputItems(PLASMA_COOLED_CORE.asStack())
                .save(provider);

        // 22. plasma_mechanical_housing: luv_machine_casing + 6x neutron_reflector + enriched_naquadah_frame + liquid_helium 1000 -> 2x ctnhcore:antifreeze_heatproof_machine_casing. EUt 24768, dur 50
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("plasma_mechanical_housing"))
                .EUt(24768).duration(50)
                .inputItems(GTBlocks.MACHINE_CASING_LuV.asStack())
                .inputItems(GTItems.NEUTRON_REFLECTOR.asStack(6))
                .inputItems(frameGt, NaquadahEnriched)
                .inputFluids(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .outputItems(CASING_ANTIFREEZE_HEATPROOF_MACHINE.asStack(2))
                .save(provider);

        // 23. simple_nutritious: paper + biomass 1000 -> 4x ctnhcore:simple_nutritious_meal. EUt 30, dur 40
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("simple_nutritious"))
                .EUt(30).duration(40)
                .inputItems(new ItemStack(Items.PAPER))
                .inputFluids(Biomass.getFluid(1000))
                .outputItems(SIMPLE_NUTRITIOUS_MEAL.asStack(4))
                .save(provider);

        // 24. super_ebf: 16x electric_blast_furnace + 4x clean_machine_casing + 4x #luv + 32x hssg_single_wire + 2x luv_electric_pump + PBI 576 -> ctnhcore:super_ebf. EUt 30720, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("super_ebf"))
                .EUt(30720).duration(400)
                .inputItems(GTMultiMachines.ELECTRIC_BLAST_FURNACE.asStack(16))
                .inputItems(GTBlocks.CASING_STAINLESS_CLEAN.asStack(4))
                .inputItems(CustomTags.LuV_CIRCUITS, 4)
                .inputItems(wireGtSingle, HSSG, 32)
                .inputItems(GTItems.ELECTRIC_PUMP_LuV.asStack(2))
                .inputFluids(Polybenzimidazole.getFluid(576))
                .outputItems(MultiblocksA.SUPER_EBF.asStack())
                .save(provider);

        // 25. mega_oil_cracking: 8x cracker + 2x #iv + 8x platinum_single_cable + 2x dense_tungsten_steel_plate + PBI 576 -> ctnhcore:mega_oil_cracking_unit. EUt 7680, dur 200
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("mega_oil_cracking"))
                .EUt(7680).duration(200)
                .inputItems(GTMultiMachines.CRACKER.asStack(8))
                .inputItems(CustomTags.IV_CIRCUITS, 2)
                .inputItems(cableGtSingle, Platinum, 8)
                .inputItems(plateDense, TungstenSteel, 2)
                .inputFluids(Polybenzimidazole.getFluid(576))
                .outputItems(MultiblocksA.MEGA_OIL_CRACKING_UNIT.asStack())
                .save(provider);

        // 26. mega_lcr: 64x large_chemical_reactor + 64x ev_voltage_coil + 32x #ev + soldering_alloy 9216 -> ctnhcore:mega_lcr. EUt 480, dur 7200
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("mega_lcr"))
                .EUt(480).duration(7200)
                .inputItems(GTMultiMachines.LARGE_CHEMICAL_REACTOR.asStack(64))
                .inputItems(GTItems.VOLTAGE_COIL_EV.asStack(64))
                .inputItems(CustomTags.EV_CIRCUITS, 32)
                .inputFluids(SolderingAlloy.getFluid(9216))
                .outputItems(MultiblocksA.MEGA_LCR.asStack())
                .save(provider);

        // 27. reactor_condensation_block: 7x dense_obsidian_plate + 7x dense_lead_plate + 7x dense_naquadah_alloy_plate + 2x frostproof_machine_casing + PBI 8000 -> 4x ctnhcore:reactor_condensation_block. EUt 122330, dur 100
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("reactor_condensation_block"))
                .EUt(122330).duration(100)
                .inputItems(plateDense, Obsidian, 7)
                .inputItems(plateDense, Lead, 7)
                .inputItems(plateDense, NaquadahAlloy, 7)
                .inputItems(GTBlocks.CASING_ALUMINIUM_FROSTPROOF.asStack(2))
                .inputFluids(Polybenzimidazole.getFluid(8000))
                .outputItems(REACTOR_CONDENSATION_BLOCK.asStack(4))
                .save(provider);

        // 28. ecological_star: 64x each oak/spruce/birch/jungle/acacia/dark_oak/cherry sapling + time_sapling + 64x plant_ball + bacteria 16000 -> ctnhcore:ecological_star. EUt 6144, dur 1200
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ecological_star"))
                .EUt(6144).duration(1200)
                .inputItems(new ItemStack(Items.OAK_SAPLING, 64))
                .inputItems(new ItemStack(Items.SPRUCE_SAPLING, 64))
                .inputItems(new ItemStack(Items.BIRCH_SAPLING, 64))
                .inputItems(new ItemStack(Items.JUNGLE_SAPLING, 64))
                .inputItems(new ItemStack(Items.ACACIA_SAPLING, 64))
                .inputItems(new ItemStack(Items.DARK_OAK_SAPLING, 64))
                .inputItems(new ItemStack(Items.CHERRY_SAPLING, 64))
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("twilightforest:time_sapling")))
                .inputItems(GTItems.PLANT_BALL.asStack(64))
                .inputFluids(Bacteria.getFluid(16000))
                .outputItems(ECOLOGICAL_STAR.asStack())
                .save(provider);

        // 29. natural_ecological_shell_casing: eglin_alloy_frame + 4x eglin_alloy_gear + 4x plant_ball + 2x brass_small_item_pipe + #saplings + acacia_planks + biomass 16000 -> ctnhcore:natural_ecological_shell_casing. EUt 120, dur 160
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("natural_ecological_shell_casing"))
                .EUt(120).duration(160)
                .inputItems(frameGt, CTNHMaterials.Eglinalloy)
                .inputItems(gear, CTNHMaterials.Eglinalloy, 4)
                .inputItems(GTItems.PLANT_BALL.asStack(4))
                .inputItems(pipeSmallItem, Brass, 2)
                .inputItems(ItemTags.SAPLINGS)
                .inputItems(new ItemStack(Items.ACACIA_PLANKS))
                .inputFluids(Biomass.getFluid(16000))
                .outputItems(NATURAL_ECOLOGICAL_SHELL_CASING.asStack())
                .save(provider);

        // 30. cleaning_maintenance_hatch: auto_maintenance_hatch + 2x item_smart_filter + 2x fluid_filter + 2x basic_item_filter + 2x filter_casing + 4x ev_electric_motor + 4x #iv + electrum 16000 -> gtceu:cleaning_maintenance_hatch. EUt 1920, dur 500
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("cleaning_maintenance_hatch"))
                .EUt(1920).duration(500)
                .inputItems(GTMachines.AUTO_MAINTENANCE_HATCH.asStack())
                .inputItems(GTItems.SMART_ITEM_FILTER.asStack(2))
                .inputItems(GTItems.FLUID_FILTER.asStack(2))
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("enderio:basic_item_filter")), 2)
                .inputItems(GTBlocks.FILTER_CASING.asStack(2))
                .inputItems(GTItems.ELECTRIC_MOTOR_EV.asStack(4))
                .inputItems(CustomTags.IV_CIRCUITS, 4)
                .inputFluids(Electrum.getFluid(16000))
                .outputItems(GTMachines.CLEANING_MAINTENANCE_HATCH.asStack())
                .save(provider);

        // 31. sterilizing_filter_casing_as: europium_frame + hsss_rotor + luv_electric_motor + luv_electric_pump + 2x fluid_tag_filter + blacklight + 2x end_steel_bars -> gtceu:sterilizing_filter_casing. EUt 24768, dur 500
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("sterilizing_filter_casing_as"))
                .EUt(24768).duration(500)
                .inputItems(frameGt, Europium)
                .inputItems(rotor, HSSS)
                .inputItems(GTItems.ELECTRIC_MOTOR_LuV.asStack())
                .inputItems(GTItems.ELECTRIC_PUMP_LuV.asStack())
                .inputItems(GTItems.TAG_FLUID_FILTER.asStack(2))
                .inputItems(GTItems.BLACKLIGHT.asStack())
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("enderio:end_steel_bars")), 2)
                .outputItems(GTBlocks.FILTER_CASING_STERILE.asStack())
                .save(provider);

        // 32. coke_oven_bricks1: 4x coke_oven_brick + cement 144 -> gtceu:coke_oven_bricks. EUt 12, dur 40
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("coke_oven_bricks1"))
                .EUt(12).duration(40)
                .inputItems(GTItems.COKE_OVEN_BRICK.asStack(4))
                .inputFluids(UncategorizedMaterials.CEMENT.getFluid(144))
                .outputItems(GTBlocks.CASING_COKE_BRICKS.asStack())
                .save(provider);

        // 33. coke_oven_bricks2: 4x coke_oven_brick + concrete 72 -> gtceu:coke_oven_bricks. EUt 12, dur 40
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("coke_oven_bricks2"))
                .EUt(12).duration(40)
                .inputItems(GTItems.COKE_OVEN_BRICK.asStack(4))
                .inputFluids(Concrete.getFluid(72))
                .outputItems(GTBlocks.CASING_COKE_BRICKS.asStack())
                .save(provider);

        // 34. biological_patch_transistor: naquadria_foil + 8x fine_osmiridium_wire + kapton_k 288 -> 32x ctnhcore:biological_patch_transistor. EUt 24768, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("biological_patch_transistor"))
                .EUt(24768).duration(400)
                .inputItems(foil, Naquadria)
                .inputItems(wireFine, Osmiridium, 8)
                .inputFluids(KAPTON_K.getFluid(288))
                .outputItems(BIOLOGICAL_PATCH_TRANSISTOR.asStack(32))
                .save(provider);

        // 35. biological_patch_resistor: ruridit_dust + 4x fine_europium_wire + kapton_k 576 -> 32x ctnhcore:biological_patch_resistor. EUt 24768, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("biological_patch_resistor"))
                .EUt(24768).duration(400)
                .inputItems(dust, Ruridit)
                .inputItems(wireFine, Europium, 4)
                .inputFluids(KAPTON_K.getFluid(576))
                .outputItems(BIOLOGICAL_PATCH_RESISTOR.asStack(32))
                .save(provider);

        // 36. biological_patch_capacitor: 2x kapton_k_foil + osmiridium_foil + kapton_k 72 -> 32x ctnhcore:biological_patch_capacitor. EUt 24768, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("biological_patch_capacitor"))
                .EUt(24768).duration(400)
                .inputItems(foil, KAPTON_K , 2)
                .inputItems(foil, Osmiridium)
                .inputFluids(KAPTON_K.getFluid(72))
                .outputItems(BIOLOGICAL_PATCH_CAPACITOR.asStack(32))
                .save(provider);

        // 37. biological_patch_diode: exquisite_arcane_crystal_gem + 32x fine_naquadah_alloy_wire + kapton_k 576 -> 64x ctnhcore:biological_patch_diode. EUt 24768, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("biological_patch_diode"))
                .EUt(24768).duration(400)
                .inputItems(gemExquisite, ArcaneCrystal)
                .inputItems(wireFine, NaquadahAlloy, 32)
                .inputFluids(KAPTON_K.getFluid(576))
                .outputItems(BIOLOGICAL_PATCH_DIODE.asStack(64))
                .save(provider);

        // 38. biological_patch_inductor: osmiridium_ring + 4x fine_tritanium_wire + kapton_k 288 -> 16x ctnhcore:biological_patch_inductor. EUt 24768, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("biological_patch_inductor"))
                .EUt(24768).duration(400)
                .inputItems(ring, Osmiridium)
                .inputItems(wireFine, Tritanium, 4)
                .inputFluids(KAPTON_K.getFluid(288))
                .outputItems(BIOLOGICAL_PATCH_INDUCTOR.asStack(16))
                .save(provider);

        // 39. gas_centrifuge: hv_centrifuge + 4x clean_machine_casing + 2x stainless_steel_turbine_blade -> ctnhcore:gas_centrifuge. EUt 480, dur 100
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("gas_centrifuge"))
                .EUt(480).duration(100)
                .inputItems(GTMachines.CENTRIFUGE[GTValues.HV].asStack())
                .inputItems(GTBlocks.CASING_STAINLESS_CLEAN.asStack(4))
                .inputItems(turbineBlade, StainlessSteel, 2)
                .outputItems(MultiblocksB.GAS_CENTRIFUGE.asStack())
                .save(provider);
    }
}
