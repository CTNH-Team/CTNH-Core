package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.*;
import io.github.cpearl0.ctnhcore.data.materials.NewExplosivesProductionMaterials;
import io.github.cpearl0.ctnhcore.data.materials.YeastRelatedMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import io.github.cpearl0.ctnhcore.registry.CTNHTagPrefixes;
import io.github.cpearl0.ctnhcore.registry.machines.CTNHMachines;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksB;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.machines.GTMultiMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import com.aetherteam.aether.block.AetherBlocks;
import com.enderio.base.common.init.EIOBlocks;
import com.enderio.base.common.init.EIOItems;
import com.github.alexmodguy.alexscaves.server.block.ACBlockRegistry;
import com.github.alexmodguy.alexscaves.server.item.ACItemRegistry;
import com.moguang.ctnhbio.registry.CBItems;
import com.simibubi.create.AllItems;
import dev.shadowsoffire.hostilenetworks.Hostile;
import earth.terrarium.adastra.common.registry.ModItems;
import org.antarcticgardens.cna.CNABlocks;
import twilightforest.init.TFBlocks;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.block.BotaniaFlowerBlocks;
import vazkii.botania.common.item.BotaniaItems;
import wayoftime.bloodmagic.common.block.BloodMagicBlocks;
import com.mo_guang.ctpp.registry.CTPPMaterials;
import com.moguang.ctnhmana.registry.CMMaterials;
import com.moguang.ctnhmana.registry.CMRecipeTypes;
import com.tterrag.registrate.util.entry.ItemEntry;

import java.util.HashMap;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.data.materials.AviationFabricMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHBlocks.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHItems.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.*;

public class GtceuScriptRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        // ============== Mixer Recipes ==============

        // 1. rose_quartz2: circuit 5, quartz + 4x redstone -> rose_quartz. EUt 32, dur 100
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("rose_quartz2"))
                .EUt(32).duration(100)
                .circuitMeta(5)
                .inputItems(new ItemStack(Items.QUARTZ))
                .inputItems(new ItemStack(Items.REDSTONE, 4))
                .outputItems(AllItems.ROSE_QUARTZ.asStack())
                .save(provider);

        // 2. polished_rose_quartz2: quartz + redstone 288 -> polished_rose_quartz. EUt 32, dur 100
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("polished_rose_quartz2"))
                .EUt(32).duration(100)
                .inputItems(new ItemStack(Items.QUARTZ))
                .inputFluids(Redstone.getFluid(288))
                .outputItems(AllItems.POLISHED_ROSE_QUARTZ.asStack())
                .save(provider);

        // 3. sky_dust: circuit 2, 10x stone_dust + 2x silicon_dust + electrotine_dust -> 13x sky_dust. EUt 120, dur 100
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("sky_dust"))
                .EUt(120).duration(100)
                .circuitMeta(2)
                .inputItems(dust, Stone, 10)
                .inputItems(dust, Silicon, 2)
                .inputItems(dust, Electrotine)
                .outputItems(AEItems.SKY_DUST, 13)
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

        // 7. chorusite_alloy_dust: andesite_alloy_dust + 2x popped_chorus_fruit + dragon_breath -> 4x
        // chorusite_alloy_dust. EUt 120, dur 200
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("chorusite_alloy_dust"))
                .EUt(120).duration(200)
                .inputItems(dust, CTPPMaterials.AndesiteAlloy)
                .inputItems(new ItemStack(Items.POPPED_CHORUS_FRUIT, 2))
                .inputItems(new ItemStack(Items.DRAGON_BREATH))
                .outputItems(dust, EnderIOMaterials.ChorusiteAlloy, 4)
                .save(provider);

        // 8. dibismuthhydroborat: 2x bismuth_dust + boron_dust + hydrogen 1000 -> 4x dibismuthhydroborat_dust. EUt 120,
        // dur 590
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

        // 10. circuit_compound: 3x dibismuthhydroborat + 2x bismuth_tellurite + indium_gallium_phosphide -> 6x
        // circuit_compound_dust. EUt 15, dur 982
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("circuit_compound"))
                .EUt(15).duration(982)
                .inputItems(dust, SpecialMaterials.DIBISMUTHHYDROBORAT, 3)
                .inputItems(dust, SpecialMaterials.BISMUTH_TELLURITE, 2)
                .inputItems(dust, IndiumGalliumPhosphide)
                .outputItems(dust, SpecialMaterials.CIRCUIT_COMPOUND, 6)
                .save(provider);

        // 11. samarium_dysprosium_terbium_permanent_magnet_alloy: samarium + dysprosium + terbium -> 3x alloy_dust. EUt
        // 122320, dur 200
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("samarium_dysprosium_terbium_permanent_magnet_alloy"))
                .EUt(122320).duration(200)
                .inputItems(dust, Samarium)
                .inputItems(dust, Dysprosium)
                .inputItems(dust, Terbium)
                .outputItems(dust, BedrockMaterials.SAMARIUM_DYSPROSIUM_TERBIUM_PERMANENT_MAGNET_ALLOY, 3)
                .save(provider);

        // 12. nether_essence_crystal_fluid: gold + steel + aluminium + gaas + stainless + small_nether_star + lava ->
        // nether_essence_crystal_fluid 864. EUt 480, dur 500
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

        // 13. eglin_steel_ingot: 5x invar_dust + 4x iron_dust + kanthal_dust -> 10x eglin_steel_ingot_dust. EUt 64, dur
        // 300
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("eglin_steel_ingot"))
                .EUt(64).duration(300)
                .inputItems(dust, Invar, 5)
                .inputItems(dust, Iron, 4)
                .inputItems(dust, Kanthal)
                .outputItems(dust, UncategorizedMaterials.EGLIN_STEEL_INGOT, 10)
                .save(provider);

        // 14. eglin_alloy_dust: circuit 4, 10x eglin_steel_ingot_dust + sulfur + silver + carbon -> 13x
        // eglin_alloy_dust. EUt 64, dur 150
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("eglin_alloy_dust"))
                .EUt(64).duration(150)
                .circuitMeta(4)
                .inputItems(dust, UncategorizedMaterials.EGLIN_STEEL_INGOT, 10)
                .inputItems(dust, Sulfur)
                .inputItems(dust, Silver)
                .inputItems(dust, Carbon)
                .outputItems(dust, CTNHMaterials.Eglinalloy, 13)
                .save(provider);

        // 15. cement: circuit 6, calcite_dust + 4x clay_dust + 2x iron_dust + water 10000 -> cement 14400. EUt 24, dur
        // 100
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

        // 16. zirkelite_dust_one: 3x zirkelite_dust + sodium_hydroxide_solution 1000 + sodium_carbonate_solution 1000
        // -> alkaline_slag_dust + alkaline_complex_ore_slurry 1000. EUt 1920, dur 200
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("zirkelite_dust_one"))
                .EUt(1920).duration(200)
                .inputItems(dust, Zirkelite, 3)
                .inputFluids(BauxiteProcessingMaterials.SODIUM_HYDROXIDE_SOLUTION.getFluid(1000))
                .inputFluids(BiodieselFertileSoilMaterials.SODIUM_CARBONATE_SOLUTION.getFluid(1000))
                .outputItems(dust, UncategorizedMaterials.ALKALINE_SLAG)
                .outputFluids(UncategorizedMaterials.ALKALINE_COMPLEX_ORE_SLURRY.getFluid(1000))
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
                .inputItems(ACItemRegistry.RAW_SCARLET_NEODYMIUM.get())
                .inputItems(new ItemStack(Items.IRON_INGOT))
                .outputItems(
                        ACItemRegistry.SCARLET_NEODYMIUM_INGOT.get(), 2)
                .save(provider);

        // 3. azure_ingot: raw_azure_neodymium + iron_ingot -> 2x azure_neodymium_ingot. EUt 7, dur 100
        ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("azure_ingot"))
                .EUt(7).duration(100)
                .inputItems(ACItemRegistry.RAW_AZURE_NEODYMIUM.get())
                .inputItems(new ItemStack(Items.IRON_INGOT))
                .outputItems(ACItemRegistry.AZURE_NEODYMIUM_INGOT.get(),
                        2)
                .save(provider);

        // 4. dark_steel: steel_ingot + obsidian_dust -> dark_steel_ingot. EUt 120, dur 150
        ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("dark_steel"))
                .EUt(120).duration(150)
                .inputItems(ingot, Steel)
                .inputItems(dust, Obsidian)
                .outputItems(ingot, EnderIOMaterials.DarkSteel)
                .save(provider);

        // ============== Macerator Recipes ==============

        // 1. ancient_debris_dust: ancient_debris -> 4x ancient_debris_dust + netherrack_dust, chanced gold_dust
        // 2000/200. EUt 120, dur 200
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
                .inputItems(ACItemRegistry.URANIUM_SHARD.get())
                .outputItems(dustTiny, Uranium238)
                .chancedOutput(dustTiny, Uranium238, 2000, 150)
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

        // 5. rich_soul_soil: resurgent_soil -> 4x rich_soul_soil_dust, chanced crimson_yeast 1500/150, warped_yeast
        // 1500/150. EUt 30, dur 20
        MACERATOR_RECIPES.recipeBuilder(CTNHCore.id("rich_soul_soil"))
                .EUt(30).duration(20)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("mynethersdelight:resurgent_soil")))
                .outputItems(dust, BiodieselFertileSoilMaterials.RICH_SOUL_SOIL, 4)
                .chancedOutput(dust, YeastRelatedMaterials.CRIMSON_YEAST, 1500, 150)
                .chancedOutput(dust, YeastRelatedMaterials.WARPED_YEAST, 1500, 150)
                .save(provider);

        // 6. asurine/crimsite/ochrum/veridium macerator loop
        String[] stones = { "asurine", "crimsite", "ochrum", "veridium" };
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

        // 1. manatransformer: circuits/lv + 4x red_alloy_plate + 4x botania:livingrock -> botania:mana_fluxfield. EUt
        // 30, dur 100
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("manatransformer"))
                .EUt(30).duration(100)
                .inputItems(CustomTags.LV_CIRCUITS)
                .inputItems(plate, RedAlloy, 4)
                .inputItems(BotaniaBlocks.livingrock.asItem(), 4)
                .outputItems(BotaniaBlocks.rfGenerator.asItem())
                .save(provider);

        // 2. submarine: alexscaves:enigmatic_engine + 2x #hv + 4x energy_crystal + 2x hv_sensor + 4x hv_electric_motor
        // + 8x red_steel_plate -> alexscaves:submarine. EUt 480, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("submarine"))
                .EUt(480).duration(400)
                .inputItems(ACBlockRegistry.ENIGMATIC_ENGINE.get().asItem())
                .inputItems(CustomTags.HV_CIRCUITS, 2)
                .inputItems(GTItems.ENERGIUM_CRYSTAL.asStack(4))
                .inputItems(GTItems.SENSOR_HV.asStack(2))
                .inputItems(GTItems.ELECTRIC_MOTOR_HV.asStack(4))
                .inputItems(plate, RedSteel, 8)
                .outputItems(ACItemRegistry.SUBMARINE.get())
                .save(provider);

        // 3. blaze_blast_furnace_casing: 4x stainless_steel_plate + 2x stainless_steel_frame + botania:blaze_block +
        // PVC 288 -> ctnhcore:blaze_blast_furnace_casing. EUt 480, dur 100
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("blaze_blast_furnace_casing"))
                .EUt(480).duration(100)
                .inputItems(plate, StainlessSteel, 4)
                .inputItems(frameGt, StainlessSteel, 2)
                .inputItems(BotaniaBlocks.blazeBlock.asItem())
                .inputFluids(PolyvinylChloride.getFluid(288))
                .outputItems(BLAZE_BLAST_FURNACE_CASING.asStack())
                .save(provider);

        // 4. blaze_blast_furnace: 4x #ev + 4x blaze_blast_furnace_casing + 2x pyrotheum_dust + 2x hv_sensor +
        // hv_field_generator + PVC 288 -> ctnhcore:blaze_blast_furnace. EUt 480, dur 200
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

        // 5. thermometer_casing: 2x gold_plate + 2x redstone + steel_rod + rubber 144 -> kubejs:thermometer_case. EUt
        // 30, dur 100
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("thermometer_casing"))
                .EUt(30).duration(100)
                .inputItems(plate, Gold, 2)
                .inputItems(new ItemStack(Items.REDSTONE, 2))
                .inputItems(rod, Steel)
                .inputFluids(Rubber.getFluid(144))
                .outputItems(THERMOMETER_CASE.asStack())
                .save(provider);

        // 6. cover_ender_fluid_link: 2x ender_pearl_plate + hv_sensor + double_stainless_steel_plate + hv_emitter +
        // hv_electric_pump + polyethylene 288 -> gtceu:ender_fluid_link_cover. EUt 480, dur 320
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

        // 7. lv_energy_output_hatch_4a: lv_energy_output_hatch + 2x tin_quadruple_wire + 2x steel_plate ->
        // ctnhcore:lv_energy_output_hatch_4a. EUt 7, dur 100
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("lv_energy_output_hatch_4a"))
                .EUt(7).duration(100)
                .inputItems(GTMachines.ENERGY_OUTPUT_HATCH[GTValues.LV].asStack())
                .inputItems(wireGtQuadruple, Tin, 2)
                .inputItems(plate, Steel, 2)
                .outputItems(CTNHMachines.ENERGY_OUTPUT_HATCH_4A_LOWER[GTValues.LV].asStack())
                .save(provider);

        // 8. mv_energy_output_hatch_4a: mv_energy_output_hatch + 2x cupronickel_quadruple_wire + 2x aluminium_plate ->
        // ctnhcore:mv_energy_output_hatch_4a. EUt 30, dur 100
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("mv_energy_output_hatch_4a"))
                .EUt(30).duration(100)
                .inputItems(GTMachines.ENERGY_OUTPUT_HATCH[GTValues.MV].asStack())
                .inputItems(wireGtQuadruple, Cupronickel, 2)
                .inputItems(plate, Aluminium, 2)
                .outputItems(CTNHMachines.ENERGY_OUTPUT_HATCH_4A_LOWER[GTValues.MV].asStack())
                .save(provider);

        // 9. hv_energy_output_hatch_4a: hv_energy_output_hatch + 2x silver_quadruple_wire + 2x stainless_steel_plate ->
        // ctnhcore:hv_energy_output_hatch_4a. EUt 120, dur 100
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("hv_energy_output_hatch_4a"))
                .EUt(120).duration(100)
                .inputItems(GTMachines.ENERGY_OUTPUT_HATCH[GTValues.HV].asStack())
                .inputItems(wireGtQuadruple, Silver, 2)
                .inputItems(plate, StainlessSteel, 2)
                .outputItems(CTNHMachines.ENERGY_OUTPUT_HATCH_4A_LOWER[GTValues.HV].asStack())
                .save(provider);

        // 10. fluxuated_magnetite: create_new_age:layered_magnet + 2x botania:mana_diamond + 2x diamond_screw ->
        // create_new_age:fluxuated_magnetite. EUt 30, dur 60
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("fluxuated_magnetite"))
                .EUt(30).duration(60)
                .inputItems(CNABlocks.LAYERED_MAGNET.asStack())
                .inputItems(new ItemStack(BotaniaItems.manaDiamond, 2))
                .inputItems(screw, Diamond, 2)
                .outputItems(
                        CNABlocks.FLUXUATED_MAGNETITE.asStack())
                .save(provider);

        // 11. space_helmet: glass_plate + 4x space_fabric + glue 72 -> ad_astra:space_helmet. EUt 120, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("space_helmet"))
                .EUt(120).duration(400)
                .inputItems(plate, Glass)
                .inputItems(SPACE_FABRIC.asStack(4))
                .inputFluids(Glue.getFluid(72))
                .outputItems(ModItems.SPACE_HELMET)
                .save(provider);

        // 12. space_suit: ad_astra:oxygen_gear + 2x ad_astra:gas_tank + 4x heavy_plate_t1 + 2x stainless_steel_screw +
        // 4x space_fabric -> ad_astra:space_suit. EUt 120, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("space_suit"))
                .EUt(120).duration(400)
                .inputItems(ModItems.OXYGEN_GEAR.get())
                .inputItems(ModItems.GAS_TANK.get(), 2)
                .inputItems(HEAVY_PLATE_T1.asStack(4))
                .inputItems(screw, StainlessSteel, 2)
                .inputItems(SPACE_FABRIC.asStack(4))
                .outputItems(ModItems.SPACE_SUIT)
                .save(provider);

        // 13. space_pants: 5x heavy_plate_t1 + 3x space_fabric -> ad_astra:space_pants. circuit 0. EUt 120, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("space_pants"))
                .EUt(120).duration(400)
                .circuitMeta(0)
                .inputItems(HEAVY_PLATE_T1.asStack(5))
                .inputItems(SPACE_FABRIC.asStack(3))
                .outputItems(ModItems.SPACE_PANTS)
                .save(provider);

        // 14. space_boots: 4x heavy_plate_t1 + 2x space_fabric -> ad_astra:space_boots. circuit 1. EUt 120, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("space_boots"))
                .EUt(120).duration(400)
                .circuitMeta(1)
                .inputItems(HEAVY_PLATE_T1.asStack(4))
                .inputItems(SPACE_FABRIC.asStack(2))
                .outputItems(ModItems.SPACE_BOOTS)
                .save(provider);

        // 15. deep_learner: computer_monitor_cover + 2x double_black_steel_plate + 2x black_steel_plate +
        // black_steel_gear + #ev -> hostilenetworks:deep_learner. EUt 480, dur 200
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("deep_learner"))
                .EUt(480).duration(200)
                .inputItems(GTItems.COVER_SCREEN.asStack())
                .inputItems(plateDouble, BlackSteel, 2)
                .inputItems(plate, BlackSteel, 2)
                .inputItems(gear, BlackSteel)
                .inputItems(CustomTags.EV_CIRCUITS)
                .outputItems(Hostile.Items.DEEP_LEARNER.get())
                .save(provider);

        // 16. fuel_refining_factory: 12x large_chemical_reactor + 8x distillation_tower + 8x cracker + 32x
        // nichrome_coil_block + 7x dense_steel_plate + 7x dense_stainless_steel_plate + 64x black_steel_frame + 32x #iv
        // -> ctnhcore:fuel_refining_factory. EUt 480, dur 48000
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

        // 17. large_fermenting: 4x fermenting_tank + 4x titanium_plate + 2x #iv + 2x iv_robot_arm + 4x tempered_glass +
        // biomass 1000 -> ctnhcore:large_fermenting_tank. EUt 1920, dur 400
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

        // 18. large_bottle: 2x ev_super_tank + 4x tempered_glass + 2x ev_electric_pump + polyethylene 288 ->
        // ctnhcore:large_bottle. EUt 480, dur 120
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

        // 20. decay_pools_machine: 28x dense_lead_plate + hv_machine_hull + 4x neutron_source + 8x double_uranium_plate
        // + soldering_alloy 4000 -> ctnhcore:decay_pools_machine. EUt 1920, dur 900
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("decay_pools_machine"))
                .EUt(1920).duration(900)
                .inputItems(plateDense, Lead, 28)
                .inputItems(GTMachines.HULL[GTValues.HV].asStack())
                .inputItems(NeutronSource.asStack(4))
                .inputItems(plateDouble, Uranium238, 8)
                .inputFluids(SolderingAlloy.getFluid(4000))
                .outputItems(MultiblocksA.DECAY_POOLS.asStack())
                .save(provider);

        // 21. plasma_condensation_coil: superconducting_coil + 4x luv_electric_pump + 32x
        // niobium_titanium_tiny_fluid_pipe + 4x luv_field_generator + liquid_helium 8000 ->
        // ctnhcore:plasma_cooled_core. EUt 24768, dur 200
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("plasma_condensation_coil"))
                .EUt(24768).duration(200)
                .inputItems(GTBlocks.SUPERCONDUCTING_COIL.asStack())
                .inputItems(GTItems.ELECTRIC_PUMP_LuV.asStack(4))
                .inputItems(pipeTinyFluid, NiobiumTitanium, 32)
                .inputItems(GTItems.FIELD_GENERATOR_LuV.asStack(4))
                .inputFluids(Helium.getFluid(FluidStorageKeys.LIQUID, 8000))
                .outputItems(PLASMA_COOLED_CORE.asStack())
                .save(provider);

        // 22. plasma_mechanical_housing: luv_machine_casing + 6x neutron_reflector + enriched_naquadah_frame +
        // liquid_helium 1000 -> 2x ctnhcore:antifreeze_heatproof_machine_casing. EUt 24768, dur 50
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

        // 24. super_ebf: 16x electric_blast_furnace + 4x clean_machine_casing + 4x #luv + 32x hssg_single_wire + 2x
        // luv_electric_pump + PBI 576 -> ctnhcore:super_ebf. EUt 30720, dur 400
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

        // 25. mega_oil_cracking: 8x cracker + 2x #iv + 8x platinum_single_cable + 2x dense_tungsten_steel_plate + PBI
        // 576 -> ctnhcore:mega_oil_cracking_unit. EUt 7680, dur 200
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("mega_oil_cracking"))
                .EUt(7680).duration(200)
                .inputItems(GTMultiMachines.CRACKER.asStack(8))
                .inputItems(CustomTags.IV_CIRCUITS, 2)
                .inputItems(cableGtSingle, Platinum, 8)
                .inputItems(plateDense, TungstenSteel, 2)
                .inputFluids(Polybenzimidazole.getFluid(576))
                .outputItems(MultiblocksA.MEGA_OIL_CRACKING_UNIT.asStack())
                .save(provider);

        // 26. mega_lcr: 64x large_chemical_reactor + 64x ev_voltage_coil + 32x #ev + soldering_alloy 9216 ->
        // ctnhcore:mega_lcr. EUt 480, dur 7200
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("mega_lcr"))
                .EUt(480).duration(7200)
                .inputItems(GTMultiMachines.LARGE_CHEMICAL_REACTOR.asStack(64))
                .inputItems(GTItems.VOLTAGE_COIL_EV.asStack(64))
                .inputItems(CustomTags.EV_CIRCUITS, 32)
                .inputFluids(SolderingAlloy.getFluid(9216))
                .outputItems(MultiblocksA.MEGA_LCR.asStack())
                .save(provider);

        // 27. reactor_condensation_block: 7x dense_obsidian_plate + 7x dense_lead_plate + 7x dense_naquadah_alloy_plate
        // + 2x frostproof_machine_casing + PBI 8000 -> 4x ctnhcore:reactor_condensation_block. EUt 122330, dur 100
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("reactor_condensation_block"))
                .EUt(122330).duration(100)
                .inputItems(plateDense, Obsidian, 7)
                .inputItems(plateDense, Lead, 7)
                .inputItems(plateDense, NaquadahAlloy, 7)
                .inputItems(GTBlocks.CASING_ALUMINIUM_FROSTPROOF.asStack(2))
                .inputFluids(Polybenzimidazole.getFluid(8000))
                .outputItems(REACTOR_CONDENSATION_BLOCK.asStack(4))
                .save(provider);

        // 28. ecological_star: 64x each oak/spruce/birch/jungle/acacia/dark_oak/cherry sapling + time_sapling + 64x
        // plant_ball + bacteria 16000 -> ctnhcore:ecological_star. EUt 6144, dur 1200
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ecological_star"))
                .EUt(6144).duration(1200)
                .inputItems(new ItemStack(Items.OAK_SAPLING, 64))
                .inputItems(new ItemStack(Items.SPRUCE_SAPLING, 64))
                .inputItems(new ItemStack(Items.BIRCH_SAPLING, 64))
                .inputItems(new ItemStack(Items.JUNGLE_SAPLING, 64))
                .inputItems(new ItemStack(Items.ACACIA_SAPLING, 64))
                .inputItems(new ItemStack(Items.DARK_OAK_SAPLING, 64))
                .inputItems(new ItemStack(Items.CHERRY_SAPLING, 64))
                .inputItems(TFBlocks.TIME_SAPLING.get().asItem())
                .inputItems(GTItems.PLANT_BALL.asStack(64))
                .inputFluids(Bacteria.getFluid(16000))
                .outputItems(ECOLOGICAL_STAR.asStack())
                .save(provider);

        // 29. natural_ecological_shell_casing: eglin_alloy_frame + 4x eglin_alloy_gear + 4x plant_ball + 2x
        // brass_small_item_pipe + #saplings + acacia_planks + biomass 16000 ->
        // ctnhcore:natural_ecological_shell_casing. EUt 120, dur 160
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

        // 30. cleaning_maintenance_hatch: auto_maintenance_hatch + 2x item_smart_filter + 2x fluid_filter + 2x
        // basic_item_filter + 2x filter_casing + 4x ev_electric_motor + 4x #iv + electrum 16000 ->
        // gtceu:cleaning_maintenance_hatch. EUt 1920, dur 500
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("cleaning_maintenance_hatch"))
                .EUt(1920).duration(500)
                .inputItems(GTMachines.AUTO_MAINTENANCE_HATCH.asStack())
                .inputItems(GTItems.SMART_ITEM_FILTER.asStack(2))
                .inputItems(GTItems.FLUID_FILTER.asStack(2))
                .inputItems(EIOItems.BASIC_ITEM_FILTER.asStack(), 2)
                .inputItems(GTBlocks.FILTER_CASING.asStack(2))
                .inputItems(GTItems.ELECTRIC_MOTOR_EV.asStack(4))
                .inputItems(CustomTags.IV_CIRCUITS, 4)
                .inputFluids(Electrum.getFluid(16000))
                .outputItems(GTMachines.CLEANING_MAINTENANCE_HATCH.asStack())
                .save(provider);

        // 31. sterilizing_filter_casing_as: europium_frame + hsss_rotor + luv_electric_motor + luv_electric_pump + 2x
        // fluid_tag_filter + blacklight + 2x end_steel_bars -> gtceu:sterilizing_filter_casing. EUt 24768, dur 500
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("sterilizing_filter_casing_as"))
                .EUt(24768).duration(500)
                .inputItems(frameGt, Europium)
                .inputItems(rotor, HSSS)
                .inputItems(GTItems.ELECTRIC_MOTOR_LuV.asStack())
                .inputItems(GTItems.ELECTRIC_PUMP_LuV.asStack())
                .inputItems(GTItems.TAG_FLUID_FILTER.asStack(2))
                .inputItems(GTItems.BLACKLIGHT.asStack())
                .inputItems(EIOBlocks.END_STEEL_BARS.asStack(), 2)
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

        // 34. biological_patch_transistor: naquadria_foil + 8x fine_osmiridium_wire + kapton_k 288 -> 32x
        // ctnhcore:biological_patch_transistor. EUt 24768, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("biological_patch_transistor"))
                .EUt(24768).duration(400)
                .inputItems(foil, Naquadria)
                .inputItems(wireFine, Osmiridium, 8)
                .inputFluids(KAPTON_K.getFluid(288))
                .outputItems(BIOLOGICAL_PATCH_TRANSISTOR.asStack(32))
                .save(provider);

        // 35. biological_patch_resistor: ruridit_dust + 4x fine_europium_wire + kapton_k 576 -> 32x
        // ctnhcore:biological_patch_resistor. EUt 24768, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("biological_patch_resistor"))
                .EUt(24768).duration(400)
                .inputItems(dust, Ruridit)
                .inputItems(wireFine, Europium, 4)
                .inputFluids(KAPTON_K.getFluid(576))
                .outputItems(BIOLOGICAL_PATCH_RESISTOR.asStack(32))
                .save(provider);

        // 36. biological_patch_capacitor: 2x kapton_k_foil + osmiridium_foil + kapton_k 72 -> 32x
        // ctnhcore:biological_patch_capacitor. EUt 24768, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("biological_patch_capacitor"))
                .EUt(24768).duration(400)
                .inputItems(foil, KAPTON_K, 2)
                .inputItems(foil, Osmiridium)
                .inputFluids(KAPTON_K.getFluid(72))
                .outputItems(BIOLOGICAL_PATCH_CAPACITOR.asStack(32))
                .save(provider);

        // 37. biological_patch_diode: exquisite_arcane_crystal_gem + 32x fine_naquadah_alloy_wire + kapton_k 576 -> 64x
        // ctnhcore:biological_patch_diode. EUt 24768, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("biological_patch_diode"))
                .EUt(24768).duration(400)
                .inputItems(gemExquisite, ArcaneCrystal)
                .inputItems(wireFine, NaquadahAlloy, 32)
                .inputFluids(KAPTON_K.getFluid(576))
                .outputItems(BIOLOGICAL_PATCH_DIODE.asStack(64))
                .save(provider);

        // 38. biological_patch_inductor: osmiridium_ring + 4x fine_tritanium_wire + kapton_k 288 -> 16x
        // ctnhcore:biological_patch_inductor. EUt 24768, dur 400
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("biological_patch_inductor"))
                .EUt(24768).duration(400)
                .inputItems(ring, Osmiridium)
                .inputItems(wireFine, Tritanium, 4)
                .inputFluids(KAPTON_K.getFluid(288))
                .outputItems(BIOLOGICAL_PATCH_INDUCTOR.asStack(16))
                .save(provider);

        // 39. gas_centrifuge: hv_centrifuge + 4x clean_machine_casing + 2x stainless_steel_turbine_blade ->
        // ctnhcore:gas_centrifuge. EUt 480, dur 100
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("gas_centrifuge"))
                .EUt(480).duration(100)
                .inputItems(GTMachines.CENTRIFUGE[GTValues.HV].asStack())
                .inputItems(GTBlocks.CASING_STAINLESS_CLEAN.asStack(4))
                .inputItems(turbineBlade, StainlessSteel, 2)
                .outputItems(MultiblocksB.GAS_CENTRIFUGE.asStack())
                .save(provider);

        // ============== Gas Collector Recipes ==============

        // 1. twilightforest_air: circuit 4, dimension twilightforest:twilight_forest -> twilightforest_air 10000. EUt
        // 16, dur 200
        GAS_COLLECTOR_RECIPES.recipeBuilder(CTNHCore.id("twilightforest_air"))
                .EUt(16).duration(200)
                .circuitMeta(4)
                .dimension(ResourceLocation.parse("twilightforest:twilight_forest"))
                .outputFluids(ExtraterrestrialAtmosphereMaterials.TWILIGHTFOREST_AIR.getFluid(10000))
                .save(provider);

        // 2. alfheim_air: circuit 5, dimension mythicbotany:alfheim -> alfheim_air 10000. EUt 64, dur 200
        GAS_COLLECTOR_RECIPES.recipeBuilder(CTNHCore.id("alfheim_air"))
                .EUt(64).duration(200)
                .circuitMeta(5)
                .dimension(ResourceLocation.parse("mythicbotany:alfheim"))
                .outputFluids(ExtraterrestrialAtmosphereMaterials.ALFHEIM_AIR.getFluid(10000))
                .save(provider);

        // 3. aether_air: circuit 6, dimension aether:the_aether -> aether_air 10000. EUt 256, dur 200
        GAS_COLLECTOR_RECIPES.recipeBuilder(CTNHCore.id("aether_air"))
                .EUt(256).duration(200)
                .circuitMeta(6)
                .dimension(ResourceLocation.parse("aether:the_aether"))
                .outputFluids(ExtraterrestrialAtmosphereMaterials.AETHER_AIR.getFluid(10000))
                .save(provider);

        // 4. venus_air: circuit 7, dimension ad_astra:venus -> venus_air 10000. EUt 1024, dur 200
        GAS_COLLECTOR_RECIPES.recipeBuilder(CTNHCore.id("venus_air"))
                .EUt(1024).duration(200)
                .circuitMeta(7)
                .dimension(ResourceLocation.parse("ad_astra:venus"))
                .outputFluids(ExtraterrestrialAtmosphereMaterials.VENUS_AIR.getFluid(10000))
                .save(provider);

        // ============== Vacuum Freezer Recipes ==============

        // 5. twilightforest_air_freeze: twilightforest_air 4000 -> liquid_twilightforest_air 4000. EUt 120, dur 80
        VACUUM_RECIPES.recipeBuilder(CTNHCore.id("twilightforest_air_freeze"))
                .EUt(120).duration(80)
                .inputFluids(ExtraterrestrialAtmosphereMaterials.TWILIGHTFOREST_AIR.getFluid(4000))
                .outputFluids(ExtraterrestrialAtmosphereMaterials.LIQUID_TWILIGHTFOREST_AIR.getFluid(4000))
                .save(provider);

        // 6. alfheim_air_freeze: alfheim_air 4000 -> liquid_alfheim_air 4000. EUt 480, dur 80
        VACUUM_RECIPES.recipeBuilder(CTNHCore.id("alfheim_air_freeze"))
                .EUt(480).duration(80)
                .inputFluids(ExtraterrestrialAtmosphereMaterials.ALFHEIM_AIR.getFluid(4000))
                .outputFluids(ExtraterrestrialAtmosphereMaterials.LIQUID_ALFHEIM_AIR.getFluid(4000))
                .save(provider);

        // 7. aether_air_freeze: aether_air 4000 -> liquid_aether_air 4000. EUt VA[LuV], dur 80
        VACUUM_RECIPES.recipeBuilder(CTNHCore.id("aether_air_freeze"))
                .EUt(GTValues.VA[GTValues.LuV]).duration(80)
                .inputFluids(ExtraterrestrialAtmosphereMaterials.AETHER_AIR.getFluid(4000))
                .outputFluids(ExtraterrestrialAtmosphereMaterials.LIQUID_AETHER_AIR.getFluid(4000))
                .save(provider);

        // 8. venus_air_freeze: venus_air 4000 -> liquid_venus_air 4000. EUt VA[LuV], dur 80
        VACUUM_RECIPES.recipeBuilder(CTNHCore.id("venus_air_freeze"))
                .EUt(GTValues.VA[GTValues.LuV]).duration(80)
                .inputFluids(ExtraterrestrialAtmosphereMaterials.VENUS_AIR.getFluid(4000))
                .outputFluids(ExtraterrestrialAtmosphereMaterials.LIQUID_VENUS_AIR.getFluid(4000))
                .save(provider);

        // ============== Distillation Tower Recipes ==============

        // 9. liquid_twilightforest_air: liquid_twilightforest_air 25000 -> nitrogen 12500 + oxygen 10000 +
        // carbon_dioxide 2000 + helium 500. EUt 120, dur 2000
        DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("liquid_twilightforest_air"))
                .EUt(120).duration(2000)
                .inputFluids(ExtraterrestrialAtmosphereMaterials.LIQUID_TWILIGHTFOREST_AIR.getFluid(25000))
                .outputFluids(Nitrogen.getFluid(12500))
                .outputFluids(Oxygen.getFluid(10000))
                .outputFluids(CarbonDioxide.getFluid(2000))
                .outputFluids(Helium.getFluid(500))
                .save(provider);

        // 10. liquid_alfheim_air: liquid_alfheim_air 100000 -> mana_powder + mana 100 + oxygen 25000 + steam 12000 +
        // neon 10000 + carbon_dioxide 5000 + helium 5000 + argon 3000. EUt 360, dur 2000
        DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("liquid_alfheim_air"))
                .EUt(360).duration(2000)
                .inputFluids(ExtraterrestrialAtmosphereMaterials.LIQUID_ALFHEIM_AIR.getFluid(100000))
                .outputItems(new ItemStack(BotaniaItems.manaPowder))
                .outputFluids(CMMaterials.Mana.getFluid(100))
                .outputFluids(Oxygen.getFluid(25000))
                .outputFluids(Steam.getFluid(12000))
                .outputFluids(Neon.getFluid(10000))
                .outputFluids(CarbonDioxide.getFluid(5000))
                .outputFluids(Helium.getFluid(5000))
                .outputFluids(Argon.getFluid(3000))
                .save(provider);

        // 11. liquid_aether_air: liquid_aether_air 100000 -> oxygen 25000 + hydrogen 12000 + fluorine 10000 + steam
        // 12000 + neon 10000 + carbon_dioxide 5000 + helium 5000. EUt 7680, dur 2000
        DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("liquid_aether_air"))
                .EUt(7680).duration(2000)
                .inputFluids(ExtraterrestrialAtmosphereMaterials.LIQUID_AETHER_AIR.getFluid(100000))
                .outputFluids(Oxygen.getFluid(25000))
                .outputFluids(Hydrogen.getFluid(12000))
                .outputFluids(Fluorine.getFluid(10000))
                .outputFluids(Steam.getFluid(12000))
                .outputFluids(Neon.getFluid(10000))
                .outputFluids(CarbonDioxide.getFluid(5000))
                .outputFluids(Helium.getFluid(5000))
                .save(provider);

        // 12. liquid_venus_air: liquid_venus_air 100000 -> distilled_water 25000 + carbon_dioxide 30000 +
        // sulfur_dioxide 15000 + radon 5000 + helium 10000 + sulfuric_acid 5000 + argon 1000 + krypton 5000. EUt
        // VA[LuV], dur 2000
        DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("liquid_venus_air"))
                .EUt(GTValues.VA[GTValues.LuV]).duration(2000)
                .inputFluids(ExtraterrestrialAtmosphereMaterials.LIQUID_VENUS_AIR.getFluid(100000))
                .outputFluids(DistilledWater.getFluid(25000))
                .outputFluids(CarbonDioxide.getFluid(30000))
                .outputFluids(SulfurDioxide.getFluid(15000))
                .outputFluids(Radon.getFluid(5000))
                .outputFluids(Helium.getFluid(10000))
                .outputFluids(SulfuricAcid.getFluid(5000))
                .outputFluids(Argon.getFluid(1000))
                .outputFluids(Krypton.getFluid(5000))
                .save(provider);

        // 13. impure_oil: impure_oil 100 -> sulfuric_heavy_fuel 50 + sulfuric_naphtha 30 + bitumen 60 + small
        // oil_refined_residues dust. EUt 60, dur 100
        DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("impure_oil"))
                .EUt(60).duration(100)
                .inputFluids(ImpureOil.getFluid(100))
                .outputFluids(SulfuricHeavyFuel.getFluid(50))
                .outputFluids(SulfuricNaphtha.getFluid(30))
                .outputFluids(BiodieselFertileSoilMaterials.BITUMEN.getFluid(60))
                .outputItems(dustSmall, BiodieselFertileSoilMaterials.OIL_REFINED_RESIDUES)
                .save(provider);

        // ============== Centrifuge Recipes ==============

        // 14. alexscaves_acid: acid 1000 -> water 500 + hydrochloric_acid 500 + 2x tiny_uranium_dust. EUt 30, dur 400
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("alexscaves_acid"))
                .EUt(30).duration(400)
                .inputFluids(FluidIngredient
                        .of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("alexscaves:acid")), 1000))
                .outputFluids(Water.getFluid(500))
                .outputFluids(HydrochloricAcid.getFluid(500))
                .outputItems(dustTiny, Uranium238, 2)
                .save(provider);

        // 15. oil_refined_residues: oil_refined_residues_dust -> stone_dust + small_oxidized_residues_dust. Chanced:
        // magnetite 1500/100, garnierite 1500/100, pyrite 1500/100. EUt 30, dur 20
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("oil_refined_residues"))
                .EUt(30).duration(20)
                .inputItems(dust, BiodieselFertileSoilMaterials.OIL_REFINED_RESIDUES)
                .outputItems(dust, Stone)
                .outputItems(dustSmall, StonePowderMaterials.OXIDIZED_RESIDUES)
                .chancedOutput(dust, Magnetite, 1500, 100)
                .chancedOutput(dust, Garnierite, 1500, 100)
                .chancedOutput(dust, Pyrite, 1500, 100)
                .save(provider);

        // 16. dried_salt: dried_salt_dust -> stone_dust + 2x tiny_salt_dust + 2x tiny_rock_salt_dust. EUt 30, dur 20
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("dried_salt"))
                .EUt(30).duration(20)
                .inputItems(dust, BiodieselFertileSoilMaterials.DRIED_SALT)
                .outputItems(dust, Stone)
                .outputItems(dustTiny, Salt, 2)
                .outputItems(dustTiny, RockSalt, 2)
                .save(provider);

        // 17. slag: 8x slag_dust -> 3x alumina_dust + 3x silicon_dioxide_dust + 2x magnesia_dust. Chanced:
        // calcium_sulfide 2500/500, sodium_sulfide 1500/500. EUt 30, dur 288
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("slag"))
                .EUt(30).duration(288)
                .inputItems(dust, CreateMaterials.SLAG, 8)
                .outputItems(dust, Alumina, 3)
                .outputItems(dust, SiliconDioxide, 3)
                .outputItems(dust, Magnesia, 2)
                .chancedOutput(dust, CreateMaterials.CALCIUM_SULFIDE, 2500, 500)
                .chancedOutput(dust, SodiumSulfide, 1500, 500)
                .save(provider);

        // Stone loop centrifuge: asurine/crimsite/ochrum/veridium dust centrifuge
        {
            String[] centriStones = { "asurine", "crimsite", "ochrum", "veridium" };
            var centriStoneMaterials = new HashMap<String, Material>();
            centriStoneMaterials.put("asurine", CreateMaterials.ASURINE);
            centriStoneMaterials.put("crimsite", CreateMaterials.CRIMSITE);
            centriStoneMaterials.put("ochrum", CreateMaterials.OCHRUM);
            centriStoneMaterials.put("veridium", CreateMaterials.VERIDIUM);
            var centriByproducts = new HashMap<String, Material>();
            centriByproducts.put("asurine", Zinc);
            centriByproducts.put("crimsite", Iron);
            centriByproducts.put("ochrum", PreciousAlloy);
            centriByproducts.put("veridium", Copper);
            var centriChanced = new HashMap<String, Material>();
            centriChanced.put("asurine", Gallium);
            centriChanced.put("crimsite", Cobalt);
            centriChanced.put("ochrum", Silver);
            centriChanced.put("veridium", Nickel);
            for (String stone : centriStones) {
                CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id(stone + "_dust_centrifuge"))
                        .EUt(30).duration(60)
                        .inputItems(dust, centriStoneMaterials.get(stone))
                        .outputItems(dust, SiliconDioxide)
                        .outputItems(dustSmall, centriByproducts.get(stone))
                        .chancedOutput(dustSmall, centriChanced.get(stone), 1500, 250)
                        .save(provider);
            }
        }

        // Stone slurry loop centrifuge: asurine/crimsite/veridium slurry centrifuge (ochrum handled separately)
        {
            String[] slurryStones = { "asurine", "crimsite", "veridium" };
            var slurryMaterials = new HashMap<String, Material>();
            slurryMaterials.put("asurine", CreateMaterials.ASURINE_SLURRY);
            slurryMaterials.put("crimsite", CreateMaterials.CRIMSITE_SLURRY);
            slurryMaterials.put("veridium", CreateMaterials.VERIDIUM_SLURRY);
            var slurryMainOutputs = new HashMap<String, Material>();
            slurryMainOutputs.put("asurine", Zinc);
            slurryMainOutputs.put("crimsite", Iron);
            slurryMainOutputs.put("veridium", Copper);
            var slurryChanced1 = new HashMap<String, Material>();
            slurryChanced1.put("asurine", Gallium);
            slurryChanced1.put("crimsite", Cobalt);
            slurryChanced1.put("veridium", Nickel);
            for (String stone : slurryStones) {
                CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id(stone + "_slurry_centrifuge"))
                        .EUt(480).duration(200)
                        .inputFluids(slurryMaterials.get(stone).getFluid(3000))
                        .outputFluids(DilutedHydrochloricAcid.getFluid(2000))
                        .outputItems(dustSmall, Silicon, 2)
                        .outputItems(dust, slurryMainOutputs.get(stone))
                        .chancedOutput(dust, slurryChanced1.get(stone), 1000, 500)
                        .save(provider);
            }
            // ochrum slurry gets additional chloroauric_acid output
            CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("ochrum_slurry_centrifuge_special"))
                    .EUt(480).duration(200)
                    .inputFluids(CreateMaterials.OCHRUM_SLURRY.getFluid(3000))
                    .outputFluids(DilutedHydrochloricAcid.getFluid(2000))
                    .outputItems(dustSmall, Silicon, 2)
                    .outputFluids(CrudeGoldRefiningMaterials.CHLOROAURIC_ACID.getFluid(500))
                    .chancedOutput(dust, Silver, 1000, 500)
                    .save(provider);
        }

        // 18. ir_line_fix: iridium_metal_residue_dust -> iridium_chloride_dust + platinum_sludge_residue_dust. EUt 24,
        // dur 100
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("ir_line_fix"))
                .EUt(24).duration(100)
                .inputItems(dust, IridiumMetalResidue)
                .outputItems(dust, IridiumChloride)
                .outputItems(dust, PlatinumSludgeResidue)
                .save(provider);

        // 19. moon_dust: 36x moon_stone_dust -> chanced outputs + helium_3 3600. EUt 480, dur 500
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("moon_dust"))
                .EUt(480).duration(500)
                .inputItems(dust, Moonstone, 36)
                .outputFluids(Helium3.getFluid(3600))
                .save(provider);

        // 20. mars_dust: 36x mars_stone_dust -> chanced outputs + bacteria 3600. EUt 1920, dur 500
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("mars_dust"))
                .EUt(1920).duration(500)
                .inputItems(dust, Marsstone, 36)
                .outputFluids(Bacteria.getFluid(3600))
                .save(provider);

        // 21. venus_dust: 36x venus_stone_dust -> chanced outputs + sulfuric_acid 3600. EUt 6144, dur 500
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("venus_dust"))
                .EUt(6144).duration(500)
                .inputItems(dust, Venusstone, 36)
                .outputFluids(SulfuricAcid.getFluid(3600))
                .save(provider);

        // 22. mercury_dust: 36x mercury_stone_dust -> chanced outputs + mercury 3600. EUt 21453, dur 500
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("mercury_dust"))
                .EUt(21453).duration(500)
                .inputItems(dust, Mercurystone, 36)
                .outputFluids(Mercury.getFluid(3600))
                .save(provider);

        // 23. glacio_dust: 36x glacio_stone_dust -> chanced outputs + radon 3600. EUt 122330, dur 500
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("glacio_dust"))
                .EUt(122330).duration(500)
                .inputItems(dust, Glaciostone, 36)
                .outputFluids(Radon.getFluid(3600))
                .save(provider);

        // 24. dolomite: 6x dolomite_dust -> 5x calcite_dust + magnesium_dust. EUt 30, dur 38
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("dolomite"))
                .EUt(30).duration(38)
                .inputItems(dust, Dolomite, 6)
                .outputItems(dust, Calcite, 5)
                .outputItems(dust, Magnesium)
                .save(provider);

        // 25. zirkelite_dust_one_two: alkaline_slag_dust -> 6x zircon_dust + 2x rutile_dust + 6x niobium_oxide_dust.
        // EUt 480, dur 300
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("zirkelite_dust_one_two"))
                .EUt(480).duration(300)
                .inputItems(dust, UncategorizedMaterials.ALKALINE_SLAG)
                .outputItems(dust, Zircon, 6)
                .outputItems(dust, Rutile, 2)
                .outputItems(dust, NiobiumTantalumJointProcessingMaterials.NIOBIUM_OXIDE, 6)
                .save(provider);

        // 26. alkaline_complex_ore_slurry: alkaline_complex_ore_slurry 1000 -> caesium_hydroxide_dust + 3x
        // calcium_hydroxide_dust + 5x pitchblende_dust. Chanced: strontium_chloride 1000/100. EUt 1920, dur 300
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("alkaline_complex_ore_slurry"))
                .EUt(1920).duration(300)
                .inputFluids(UncategorizedMaterials.ALKALINE_COMPLEX_ORE_SLURRY.getFluid(1000))
                .outputItems(dust, PitchblendeRefiningMaterials.CAESIUM_HYDROXIDE)
                .outputItems(dust, CalciumHydroxide, 3)
                .outputItems(dust, Pitchblende, 5)
                .chancedOutput(dust, UncategorizedMaterials.STRONTIUM_CHLORIDE, 1000, 100)
                .save(provider);

        // 27. tarkianite_dust: 2x tarkianite_dust -> rheniite_dust + molybdenite_dust. EUt 480, dur 400
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("tarkianite_dust"))
                .EUt(480).duration(400)
                .inputItems(dust, Tarkianite, 2)
                .outputItems(dust, Rheniite)
                .outputItems(dust, Molybdenite)
                .save(provider);

        // 28. twilightforest_air centrifuge: twilightforest_air 10000 -> nitrogen 2500 + oxygen 2500. EUt 7, dur 800
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("twilightforest_air_centrifuge"))
                .EUt(7).duration(800)
                .inputFluids(ExtraterrestrialAtmosphereMaterials.TWILIGHTFOREST_AIR.getFluid(10000))
                .outputFluids(Nitrogen.getFluid(2500))
                .outputFluids(Oxygen.getFluid(2500))
                .save(provider);

        // 29. alfheim_air centrifuge: alfheim_air 10000 -> mana 5 + oxygen 1500 + helium 1000. EUt 240, dur 800
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("alfheim_air_centrifuge"))
                .EUt(240).duration(800)
                .inputFluids(ExtraterrestrialAtmosphereMaterials.ALFHEIM_AIR.getFluid(10000))
                .outputFluids(CMMaterials.Mana.getFluid(5))
                .outputFluids(Oxygen.getFluid(1500))
                .outputFluids(Helium.getFluid(1000))
                .save(provider);

        // 30. venus_air centrifuge: venus_air 10000 -> steam 1500 + carbon_dioxide 1000 + helium 500. EUt 1920, dur 800
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("venus_air_centrifuge"))
                .EUt(1920).duration(800)
                .inputFluids(ExtraterrestrialAtmosphereMaterials.VENUS_AIR.getFluid(10000))
                .outputFluids(Steam.getFluid(1500))
                .outputFluids(CarbonDioxide.getFluid(1000))
                .outputFluids(Helium.getFluid(500))
                .save(provider);

        // ============== Chemical Reactor Recipes ==============

        // 31. ancient_debris_leach: 3x ancient_debris_dust + aqua_regia 3000 -> ancient_debris_leach 2000 +
        // nitrogen_dioxide 1000. EUt 120, dur 100
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("ancient_debris_leach"))
                .EUt(120).duration(100)
                .inputItems(dust, NewExplosivesProductionMaterials.ANCIENT_DEBRIS, 3)
                .inputFluids(AquaRegia.getFluid(3000))
                .outputFluids(NewExplosivesProductionMaterials.ANCIENT_DEBRIS_LEACH.getFluid(2000))
                .outputFluids(NitrogenDioxide.getFluid(1000))
                .save(provider);

        // 32. soda_ash_dust: 6x sodium_hydroxide_dust + carbon_dioxide 1000 -> 6x soda_ash_dust. EUt 20, dur 50
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("soda_ash_dust"))
                .EUt(20).duration(50)
                .inputItems(dust, SodiumHydroxide, 6)
                .inputFluids(CarbonDioxide.getFluid(1000))
                .outputItems(dust, SodaAsh, 6)
                .save(provider);

        // 33. ir_fix: 2x iridium_chloride_dust + 3x calcium_dust -> 9x calcium_chloride_dust + 2x iridium_dust. EUt
        // 1920, dur 300
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("ir_fix"))
                .EUt(1920).duration(300)
                .inputItems(dust, IridiumChloride, 2)
                .inputItems(dust, Calcium, 3)
                .outputItems(dust, CalciumChloride, 9)
                .outputItems(dust, Iridium, 2)
                .save(provider);

        // 34. pt_fix: 3x platinum_raw_dust + 2x calcium_dust -> 6x calcium_chloride_dust + 3x platinum_dust. EUt 24,
        // dur 30
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("pt_fix"))
                .EUt(24).duration(30)
                .inputItems(dust, PlatinumRaw, 3)
                .inputItems(dust, Calcium, 2)
                .outputItems(dust, CalciumChloride, 6)
                .outputItems(dust, Platinum, 3)
                .save(provider);

        // 35. tnt: 4x powdery_block + sulfuric_acid 250 -> tnt. EUt 24, dur 100
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("tnt"))
                .EUt(24).duration(100)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("mynethersdelight:powdery_block")), 4)
                .inputFluids(SulfuricAcid.getFluid(250))
                .outputItems(new ItemStack(Items.TNT))
                .save(provider);

        // 36. sodium_carbonate_solution1: sodium_carbonate_solution 1000 + carbon_dioxide 1000 -> 12x
        // sodium_bicarbonate_dust. EUt 16, dur 50
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("sodium_carbonate_solution1"))
                .EUt(16).duration(50)
                .inputFluids(BiodieselFertileSoilMaterials.SODIUM_CARBONATE_SOLUTION.getFluid(1000))
                .inputFluids(CarbonDioxide.getFluid(1000))
                .outputItems(dust, SodiumBicarbonate, 12)
                .save(provider);

        // 37. cao: circuit 2, 2x quicklime_dust + carbon_dioxide 1000 -> 5x calcite_dust. EUt 30, dur 80
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("cao"))
                .EUt(30).duration(80)
                .circuitMeta(2)
                .inputItems(dust, Quicklime, 2)
                .inputFluids(CarbonDioxide.getFluid(1000))
                .outputItems(dust, Calcite, 5)
                .save(provider);

        // 38. wolframite: 7x wolframite_dust + calcium_dust -> 6x scheelite_dust + iron_dust + manganese_dust. EUt 24,
        // dur 30
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("wolframite"))
                .EUt(24).duration(30)
                .inputItems(dust, Wolframite, 7)
                .inputItems(dust, Calcium)
                .outputItems(dust, Scheelite, 6)
                .outputItems(dust, Iron)
                .outputItems(dust, Manganese)
                .save(provider);

        // 39. direct_hydrogen_reduction: perrhenic_acid 1000 + hydrogen 3500 + notConsumable platinum_dust ->
        // rhenium_dust + water 4000. EUt 480, dur 400
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("direct_hydrogen_reduction"))
                .EUt(480).duration(400)
                .inputFluids(UncategorizedMaterials.PERRHENIC_ACID.getFluid(1000))
                .inputFluids(Hydrogen.getFluid(3500))
                .notConsumable(dust, Platinum)
                .outputItems(dust, Rhenium)
                .outputFluids(Water.getFluid(4000))
                .save(provider);

        // ============== Large Chemical Reactor Recipes ==============

        // 40. tarkianite_acid_leaching: 3x rheniite_dust + nitric_acid 7000 -> perrhenic_acid 1000 + sulfuric_acid 2000
        // + nitrogen_dioxide 7000. EUt 240, dur 450
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("tarkianite_acid_leaching"))
                .EUt(240).duration(450)
                .inputItems(dust, Rheniite, 3)
                .inputFluids(NitricAcid.getFluid(7000))
                .outputFluids(UncategorizedMaterials.PERRHENIC_ACID.getFluid(1000))
                .outputFluids(SulfuricAcid.getFluid(2000))
                .outputFluids(NitrogenDioxide.getFluid(7000))
                .save(provider);

        // ============== Electric Blast Furnace Recipes ==============

        // 41. titanium: 10x ilmenite_dust + 2x carbon_dust -> 2x wrought_iron_ingot + 2x rutile_dust + carbon_monoxide
        // 2000. EUt 480, dur 800, temp 1700
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("titanium"))
                .EUt(480).duration(800)
                .blastFurnaceTemp(1700)
                .inputItems(dust, Ilmenite, 10)
                .inputItems(dust, Carbon, 2)
                .outputItems(ingot, WroughtIron, 2)
                .outputItems(dust, Rutile, 2)
                .outputFluids(CarbonMonoxide.getFluid(2000))
                .save(provider);

        // 42. nether_star: nether_star_dust -> 2x nether_star + nether_essence_crystal_fluid 576. EUt 1920, dur 400,
        // temp 4500
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("nether_star"))
                .EUt(1920).duration(400)
                .blastFurnaceTemp(4500)
                .inputItems(dust, NetherStar)
                .outputItems(new ItemStack(Items.NETHER_STAR, 2))
                .outputFluids(UncategorizedMaterials.NETHER_ESSENCE_CRYSTAL_FLUID.getFluid(576))
                .save(provider);

        // 43. germanium1: circuit 11, 16x purified_sphalerite_ore + argon 1000 -> 2x tiny_germanium_dust. EUt 6144, dur
        // 200, temp 5200
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("germanium1"))
                .EUt(6144).duration(200)
                .blastFurnaceTemp(5200)
                .circuitMeta(11)
                .inputItems(crushedPurified, Sphalerite, 16)
                .inputFluids(Argon.getFluid(1000))
                .outputItems(dustTiny, Germanium, 2)
                .save(provider);

        // 44. germanium2: circuit 1, 16x purified_sphalerite_ore -> 2x tiny_germanium_dust. EUt 6144, dur 400, temp
        // 5200
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("germanium2"))
                .EUt(6144).duration(400)
                .blastFurnaceTemp(5200)
                .circuitMeta(1)
                .inputItems(crushedPurified, Sphalerite, 16)
                .outputItems(dustTiny, Germanium, 2)
                .save(provider);

        // 45. ruthenium_amalgam: 3x ruthenium_amalgam_dust -> ruthenium_dust + mercury 2000. EUt 240, dur 50, temp 1700
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("ruthenium_amalgam"))
                .EUt(240).duration(50)
                .blastFurnaceTemp(1700)
                .inputItems(dust, RutheniumAmalgam, 3)
                .outputItems(dust, Ruthenium)
                .outputFluids(Mercury.getFluid(2000))
                .save(provider);

        // ============== Chemical Bath Recipes ==============

        // 46. netherite_magnet: fluxuated_magnetite + ancient_debris_leach 8000 -> create_new_age:netherite_magnet. EUt
        // 120, dur 60
        CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("netherite_magnet"))
                .EUt(120).duration(60)
                .inputItems(
                        CNABlocks.FLUXUATED_MAGNETITE.asStack())
                .inputFluids(NewExplosivesProductionMaterials.ANCIENT_DEBRIS_LEACH.getFluid(8000))
                .outputItems(CNABlocks.NETHERITE_MAGNET.asStack())
                .save(provider);

        // 47. fiber_glass: cellulose_dust + glass 288 -> fiber_glass 288. EUt 30, dur 120
        CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("fiber_glass"))
                .EUt(30).duration(120)
                .inputItems(dust, YeastRelatedMaterials.CELLULOSE)
                .inputFluids(Glass.getFluid(288))
                .outputFluids(FIBER_GLASS.getFluid(288))
                .save(provider);

        // 48. naoh: sodium_dust + water 1000 -> hydrogen 1000 + 3x sodium_hydroxide_dust. EUt 24, dur 40
        CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("naoh"))
                .EUt(24).duration(40)
                .inputItems(dust, Sodium)
                .inputFluids(Water.getFluid(1000))
                .outputFluids(Hydrogen.getFluid(1000))
                .outputItems(dust, SodiumHydroxide, 3)
                .save(provider);

        // 49. advance_machine_casing_solid_steel: 8x solid_machine_casing + special_composite_steel_m77 288 -> 8x
        // advance_machine_casing_solid_steel. EUt 24678, dur 600
        CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("advance_machine_casing_solid_steel"))
                .EUt(24678).duration(600)
                .inputItems(GTBlocks.CASING_STEEL_SOLID.asStack(8))
                .inputFluids(SpecialCompositeSteelM77.getFluid(288))
                .outputItems(ADVANCE_MACHINE_CASING_SOLID_STEEL.asStack(8))
                .save(provider);

        // 50. chocolate: create:bar_of_chocolate + milk 250 -> gtmfo:milk_chocolate. EUt 32, dur 20
        CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("chocolate"))
                .EUt(32).duration(20)
                .inputItems(AllItems.BAR_OF_CHOCOLATE.asStack())
                .inputFluids(Milk.getFluid(250))
                .outputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtmfo:milk_chocolate")))
                .save(provider);

        // ============== Electrolyzer Recipes ==============

        // 51. aluminium_dust_fix: 14x aluminium_sulfite_dust -> 2x aluminium_dust + 3x sulfur_dust + oxygen 9000. EUt
        // 60, dur 280
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("aluminium_dust_fix"))
                .EUt(60).duration(280)
                .inputItems(dust, AluminiumSulfite, 14)
                .outputItems(dust, Aluminium, 2)
                .outputItems(dust, Sulfur, 3)
                .outputFluids(Oxygen.getFluid(9000))
                .save(provider);

        // 52. strontium_chloride_dust: 3x strontium_chloride_dust -> strontium_dust + chlorine 2000. EUt 6144, dur 200
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("strontium_chloride_dust"))
                .EUt(6144).duration(200)
                .inputItems(dust, UncategorizedMaterials.STRONTIUM_CHLORIDE, 3)
                .outputItems(dust, Strontium)
                .outputFluids(Chlorine.getFluid(2000))
                .save(provider);

        // 53. osmium_iron_spinel_dust: 7x osmium_iron_spinel_dust -> 10x rarest_metal_mixture_dust + 2x iron_dust +
        // oxygen 4000. EUt 1920, dur 200
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("osmium_iron_spinel_dust"))
                .EUt(1920).duration(200)
                .inputItems(dust, OsmiumIronSpinel, 7)
                .outputItems(dust, RarestMetalMixture, 10)
                .outputItems(dust, Iron, 2)
                .outputFluids(Oxygen.getFluid(4000))
                .save(provider);

        // ============== Fluid Solidifier Recipes ==============

        // 54. potassium_dust: potassium 144 -> potassium_dust. EUt 24, dur 20
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder(CTNHCore.id("potassium_dust"))
                .EUt(24).duration(20)
                .inputFluids(Potassium.getFluid(144))
                .outputItems(dust, Potassium)
                .save(provider);

        // 55. p_dust: phosphorus 144 -> phosphorus_dust. EUt 8, dur 20
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder(CTNHCore.id("p_dust"))
                .EUt(8).duration(20)
                .inputFluids(Phosphorus.getFluid(144))
                .outputItems(dust, Phosphorus)
                .save(provider);

        // ============== Forge Hammer Recipes ==============

        // 56. hot_high_temp_wrought_precursor_ingot_fixed: hot_high_temp_wrought_precursor_ingot -> wrought_iron_ingot.
        // EUt 8, dur 100
        FORGE_HAMMER_RECIPES.recipeBuilder(CTNHCore.id("hot_high_temp_wrought_precursor_ingot_fixed"))
                .EUt(8).duration(100)
                .inputItems(ingot, UncategorizedMaterials.HIGH_TEMP_WROUGHT_PRECURSOR)
                .outputItems(ingot, WroughtIron)
                .save(provider);

        // ============== Polarizer Recipes ==============

        // 57. samarium_dysprosium_terbium_permanent_magnet_alloy_ingot: ingot -> magnetic ingot. EUt 490120, dur 100
        POLARIZER_RECIPES.recipeBuilder(CTNHCore.id("samarium_dysprosium_terbium_permanent_magnet_alloy_ingot"))
                .EUt(490120).duration(100)
                .inputItems(ingot, BedrockMaterials.SAMARIUM_DYSPROSIUM_TERBIUM_PERMANENT_MAGNET_ALLOY)
                .outputItems(ingot, BedrockMaterials.SAMARIUM_DYSPROSIUM_TERBIUM_PERMANENT_MAGNET_ALLOY_MAGNETIC)
                .save(provider);

        // 58. samarium_dysprosium_terbium_permanent_magnet_alloy_magnetic_rod: rod -> magnetic rod. EUt 490120, dur 100
        POLARIZER_RECIPES.recipeBuilder(CTNHCore.id("samarium_dysprosium_terbium_permanent_magnet_alloy_magnetic_rod"))
                .EUt(490120).duration(100)
                .inputItems(rod, BedrockMaterials.SAMARIUM_DYSPROSIUM_TERBIUM_PERMANENT_MAGNET_ALLOY)
                .outputItems(rod, BedrockMaterials.SAMARIUM_DYSPROSIUM_TERBIUM_PERMANENT_MAGNET_ALLOY_MAGNETIC)
                .save(provider);

        // 59. long_samarium_dysprosium_terbium_permanent_magnet_alloy_magnetic_rod: long_rod -> magnetic long_rod. EUt
        // 490120, dur 200
        POLARIZER_RECIPES
                .recipeBuilder(CTNHCore.id("long_samarium_dysprosium_terbium_permanent_magnet_alloy_magnetic_rod"))
                .EUt(490120).duration(200)
                .inputItems(rodLong, BedrockMaterials.SAMARIUM_DYSPROSIUM_TERBIUM_PERMANENT_MAGNET_ALLOY)
                .outputItems(rodLong, BedrockMaterials.SAMARIUM_DYSPROSIUM_TERBIUM_PERMANENT_MAGNET_ALLOY_MAGNETIC)
                .save(provider);

        // ============== Fluid Heater Recipes ==============

        // 60. sterilebiologicalculturemediumstocksolution: circuit 1, biologicalculturemediumstocksolution 100 ->
        // sterilebiologicalculturemediumstocksolution 100. EUt 122330, dur 40
        FLUID_HEATER_RECIPES.recipeBuilder(CTNHCore.id("sterilebiologicalculturemediumstocksolution"))
                .EUt(122330).duration(40)
                .circuitMeta(1)
                .inputFluids(BiologicalCultureMediumStockSolution.getFluid(100))
                .outputFluids(SterileBiologicalCultureMediumStockSolution.getFluid(100))
                .save(provider);

        // ============== Canner Recipes ==============

        // 61. radon: radon_bottle -> glass_bottle + radon 250. EUt VA[HV], dur 40
        CANNER_RECIPES.recipeBuilder(CTNHCore.id("radon"))
                .EUt(GTValues.VA[GTValues.HV]).duration(40)
                .inputItems(ACItemRegistry.RADON_BOTTLE.get())
                .outputItems(new ItemStack(Items.GLASS_BOTTLE))
                .outputFluids(Radon.getFluid(250))
                .save(provider);

        // 62. scp500_base: notConsumable pill_casting_mold + life_essence_fluid 8000 -> scp_500_base. EUt 8192, dur 100
        CANNER_RECIPES.recipeBuilder(CTNHCore.id("scp500_base"))
                .EUt(8192).duration(100)
                .notConsumable(GTItems.SHAPE_MOLD_PILL.asStack())
                .inputFluids(FluidIngredient.of(
                        ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("bloodmagic:life_essence_fluid")), 8000))
                .outputItems(SCP_500_BASE.asStack())
                .save(provider);

        // 63. scp500: scp_500_base + milk 8000 -> scp_500. EUt 8192, dur 100
        CANNER_RECIPES.recipeBuilder(CTNHCore.id("scp500"))
                .EUt(8192).duration(100)
                .inputItems(SCP_500_BASE.asStack())
                .inputFluids(Milk.getFluid(8000))
                .outputItems(SCP_500.asStack())
                .save(provider);

        // ============== Implosion Compressor Recipes ==============

        // 64. bedrock: 64x bedrock_dust_block + 64x alexscaves:nuclear_bomb -> bedrock. EUt 491200, dur 20. cleanroom
        // STERILE_CLEANROOM
        IMPLOSION_RECIPES.recipeBuilder(CTNHCore.id("bedrock"))
                .EUt(491200).duration(20)
                .inputItems(block, BedrockMaterials.BEDROCK_DUST, 64)
                .inputItems(ACBlockRegistry.NUCLEAR_BOMB.get().asItem(), 64)
                .outputItems(ingot, BedrockMaterials.BEDROCK_DUST)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .save(provider);

        // ============== Desalting Recipes ==============

        // 65. salt1: circuit 0, seawater 1000 -> 2x salt_dust + chanced small_rock_salt 4000/500. EUt 120, dur 400.
        // blastFurnaceTemp(1600)
        CTNHRecipeTypes.DESALTING.recipeBuilder(CTNHCore.id("salt1"))
                .EUt(120).duration(400)
                .blastFurnaceTemp(1600)
                .circuitMeta(0)
                .inputFluids(Seawater.getFluid(1000))
                .outputItems(dust, Salt, 2)
                .chancedOutput(dustSmall, RockSalt, 4000, 500)
                .save(provider);

        // 66. salt2: circuit 1, seawater 2000 -> 3x salt_dust + chanced small_rock_salt 6000/500 + iodized_brine 100.
        // EUt 480, dur 400. blastFurnaceTemp(2900)
        CTNHRecipeTypes.DESALTING.recipeBuilder(CTNHCore.id("salt2"))
                .EUt(480).duration(400)
                .blastFurnaceTemp(2900)
                .circuitMeta(1)
                .inputFluids(Seawater.getFluid(2000))
                .outputItems(dust, Salt, 3)
                .chancedOutput(dustSmall, RockSalt, 6000, 500)
                .outputFluids(IodizedBrine.getFluid(100))
                .save(provider);

        // ============== Primitive Blast Furnace Recipes ==============

        // 67. high_quality: iron_ingot + high_quality_solid_fuel -> steel_ingot + chanced ash 500/0. dur 1200
        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder(CTNHCore.id("high_quality"))
                .duration(1200)
                .inputItems(ingot, Iron)
                .inputItems(HIGH_QUALITY_SOLID_FUEL.asStack())
                .outputItems(ingot, Steel)
                .chancedOutput(dust, Ash, 500, 0)
                .save(provider);

        // 68. high_quality1: wrought_iron_ingot + high_quality_solid_fuel -> steel_ingot + chanced ash 500/0. dur 300
        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder(CTNHCore.id("high_quality1"))
                .duration(300)
                .inputItems(ingot, WroughtIron)
                .inputItems(HIGH_QUALITY_SOLID_FUEL.asStack())
                .outputItems(ingot, Steel)
                .chancedOutput(dust, Ash, 500, 0)
                .save(provider);

        // 69. refined_steel: 3x refined_iron_ingot + 3x coke_gem -> 24x steel_ingot. dur 100
        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder(CTNHCore.id("refined_steel"))
                .duration(100)
                .inputItems(REFINED_IRON_INGOT.asStack(3))
                .inputItems(gem, Coke, 3)
                .outputItems(ingot, Steel, 24)
                .save(provider);

        // ============== Autoclave Recipes ==============

        // 70. budding_quartz1: quartz_block + distilled_water 200 -> damaged_budding_quartz. EUt 120, dur 200
        AUTOCLAVE_RECIPES.recipeBuilder(CTNHCore.id("budding_quartz1"))
                .EUt(120).duration(200)
                .inputItems(new ItemStack(AEBlocks.QUARTZ_BLOCK.block()))
                .inputFluids(DistilledWater.getFluid(200))
                .outputItems(new ItemStack(AEBlocks.DAMAGED_BUDDING_QUARTZ.block()))
                .save(provider);

        // 71. budding_quartz2: damaged_budding_quartz + distilled_water 200 -> chipped_budding_quartz. EUt 120, dur 200
        AUTOCLAVE_RECIPES.recipeBuilder(CTNHCore.id("budding_quartz2"))
                .EUt(120).duration(200)
                .inputItems(new ItemStack(AEBlocks.DAMAGED_BUDDING_QUARTZ.block()))
                .inputFluids(DistilledWater.getFluid(200))
                .outputItems(new ItemStack(AEBlocks.CHIPPED_BUDDING_QUARTZ.block()))
                .save(provider);

        // 72. budding_quartz3: chipped_budding_quartz + distilled_water 200 -> flawed_budding_quartz. EUt 120, dur 200
        AUTOCLAVE_RECIPES.recipeBuilder(CTNHCore.id("budding_quartz3"))
                .EUt(120).duration(200)
                .inputItems(new ItemStack(AEBlocks.CHIPPED_BUDDING_QUARTZ.block()))
                .inputFluids(DistilledWater.getFluid(200))
                .outputItems(new ItemStack(AEBlocks.FLAWED_BUDDING_QUARTZ.block()))
                .save(provider);

        // 73. budding_quartz4: flawed_budding_quartz + distilled_water 200 -> flawless_budding_quartz. EUt 120, dur 200
        AUTOCLAVE_RECIPES.recipeBuilder(CTNHCore.id("budding_quartz4"))
                .EUt(120).duration(200)
                .inputItems(new ItemStack(AEBlocks.FLAWED_BUDDING_QUARTZ.block()))
                .inputFluids(DistilledWater.getFluid(200))
                .outputItems(new ItemStack(AEBlocks.FLAWLESS_BUDDING_QUARTZ.block()))
                .save(provider);

        // ============== Misc Recipes ==============

        // 74. blank_data_model (forming_press): 2x ender_pearl_plate + 2x stainless_steel_plate + 2x fine_platinum_wire
        // + smooth_stone -> hostilenetworks:blank_data_model. EUt 480, dur 200
        FORMING_PRESS_RECIPES.recipeBuilder(CTNHCore.id("blank_data_model"))
                .EUt(480).duration(200)
                .inputItems(plate, EnderPearl, 2)
                .inputItems(plate, StainlessSteel, 2)
                .inputItems(wireFine, Platinum, 2)
                .inputItems(new ItemStack(Items.SMOOTH_STONE))
                .outputItems(Hostile.Items.BLANK_DATA_MODEL.get())
                .save(provider);

        // 75. bedrock_drilling_rigs (assembler): 4x tungstencu_diamond_plating_frame + 24x #uv + 4x
        // naquadah_alloy_drill_head + 7x dense_naquadah_alloy_plate + 4x zpm_electric_motor + 4x zpm_conveyor_module +
        // europium 1440. circuit 2. EUt 122880, dur 1200
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("bedrock_drilling_rigs"))
                .EUt(122880).duration(1200)
                .circuitMeta(2)
                .inputItems(frameGt, BedrockMaterials.TUNGSTENCU_DIAMOND_PLATING, 4)
                .inputItems(CustomTags.UV_CIRCUITS, 24)
                .inputItems(toolHeadDrill, NaquadahAlloy, 4)
                .inputItems(plateDense, NaquadahAlloy, 7)
                .inputItems(GTItems.ELECTRIC_MOTOR_ZPM.asStack(4))
                .inputItems(GTItems.CONVEYOR_MODULE_ZPM.asStack(4))
                .inputFluids(Europium.getFluid(1440))
                .outputItems(MultiblocksA.BEDROCK_DRILLING_RIGS.asStack())
                .save(provider);

        // ============== Greenhouse Recipes ==============

        // Rubber sapling
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("rubber_sapling"))
                .circuitMeta(1)
                .notConsumable(GTBlocks.RUBBER_SAPLING.asStack())
                .inputFluids(Water.getFluid(1000))
                .outputItems(GTBlocks.RUBBER_LOG.asStack(), 32)
                .outputItems(GTItems.STICKY_RESIN.asStack(), 8)
                .outputItems(GTBlocks.RUBBER_SAPLING.asStack(), 4)
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("rubber_sapling_boosted"))
                .circuitMeta(2)
                .notConsumable(GTBlocks.RUBBER_SAPLING.asStack())
                .inputItems(GTItems.FERTILIZER.asStack(), 4)
                .inputFluids(Water.getFluid(1000))
                .outputItems(GTBlocks.RUBBER_LOG.asStack(), 64)
                .outputItems(GTItems.STICKY_RESIN.asStack(), 16)
                .outputItems(GTBlocks.RUBBER_SAPLING.asStack(), 4)
                .duration(320).EUt(120)
                .save(provider);

        // Oak sapling
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("oak_sapling"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.OAK_SAPLING))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.OAK_LOG, 64))
                .outputItems(new ItemStack(Items.OAK_SAPLING, 4))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("oak_sapling_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.OAK_SAPLING))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.OAK_LOG, 64))
                .outputItems(new ItemStack(Items.OAK_LOG, 64))
                .outputItems(new ItemStack(Items.OAK_SAPLING, 4))
                .duration(320).EUt(120)
                .save(provider);

        // Dark oak sapling
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("dark_oak_sapling"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.DARK_OAK_SAPLING))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.DARK_OAK_LOG, 64))
                .outputItems(new ItemStack(Items.DARK_OAK_SAPLING, 4))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("dark_oak_sapling_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.DARK_OAK_SAPLING))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.DARK_OAK_LOG, 64))
                .outputItems(new ItemStack(Items.DARK_OAK_LOG, 64))
                .outputItems(new ItemStack(Items.DARK_OAK_SAPLING, 4))
                .duration(320).EUt(120)
                .save(provider);

        // Spruce sapling
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("spruce_sapling"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.SPRUCE_SAPLING))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.SPRUCE_LOG, 64))
                .outputItems(new ItemStack(Items.SPRUCE_SAPLING, 4))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("spruce_sapling_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.SPRUCE_SAPLING))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.SPRUCE_LOG, 64))
                .outputItems(new ItemStack(Items.SPRUCE_LOG, 64))
                .outputItems(new ItemStack(Items.SPRUCE_SAPLING, 4))
                .duration(320).EUt(120)
                .save(provider);

        // Birch sapling
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("birch_sapling"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.BIRCH_SAPLING))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.BIRCH_LOG, 64))
                .outputItems(new ItemStack(Items.BIRCH_SAPLING, 4))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("birch_sapling_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.BIRCH_SAPLING))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.BIRCH_LOG, 64))
                .outputItems(new ItemStack(Items.BIRCH_LOG, 64))
                .outputItems(new ItemStack(Items.BIRCH_SAPLING, 4))
                .duration(320).EUt(120)
                .save(provider);

        // Acacia sapling
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("acacia_sapling"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.ACACIA_SAPLING))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.ACACIA_LOG, 64))
                .outputItems(new ItemStack(Items.ACACIA_SAPLING, 4))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("acacia_sapling_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.ACACIA_SAPLING))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.ACACIA_LOG, 64))
                .outputItems(new ItemStack(Items.ACACIA_LOG, 64))
                .outputItems(new ItemStack(Items.ACACIA_SAPLING, 4))
                .duration(320).EUt(120)
                .save(provider);

        // Jungle sapling
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("jungle_sapling"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.JUNGLE_SAPLING))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.JUNGLE_LOG, 64))
                .outputItems(new ItemStack(Items.JUNGLE_SAPLING, 4))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("jungle_sapling_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.JUNGLE_SAPLING))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.JUNGLE_LOG, 64))
                .outputItems(new ItemStack(Items.JUNGLE_LOG, 64))
                .outputItems(new ItemStack(Items.JUNGLE_SAPLING, 4))
                .duration(320).EUt(120)
                .save(provider);

        // Azalea
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("azalea"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.AZALEA))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.OAK_LOG, 64))
                .outputItems(new ItemStack(Items.AZALEA, 4))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("azalea_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.AZALEA))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.OAK_LOG, 64))
                .outputItems(new ItemStack(Items.OAK_LOG, 64))
                .outputItems(new ItemStack(Items.AZALEA, 4))
                .duration(320).EUt(120)
                .save(provider);

        // Flowering azalea
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("flowering_azalea"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.FLOWERING_AZALEA))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.OAK_LOG, 64))
                .outputItems(new ItemStack(Items.FLOWERING_AZALEA, 4))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("flowering_azalea_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.FLOWERING_AZALEA))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.OAK_LOG, 64))
                .outputItems(new ItemStack(Items.OAK_LOG, 64))
                .outputItems(new ItemStack(Items.FLOWERING_AZALEA, 4))
                .duration(320).EUt(120)
                .save(provider);

        // Cherry sapling
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("cherry_sapling"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.CHERRY_SAPLING))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.CHERRY_LOG, 64))
                .outputItems(new ItemStack(Items.CHERRY_SAPLING, 4))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("cherry_sapling_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.CHERRY_SAPLING))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.CHERRY_LOG, 64))
                .outputItems(new ItemStack(Items.CHERRY_LOG, 64))
                .outputItems(new ItemStack(Items.CHERRY_SAPLING, 4))
                .duration(320).EUt(120)
                .save(provider);

        // Mangrove propagule
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("mangrove_propagule"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.MANGROVE_PROPAGULE))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.MANGROVE_LOG, 64))
                .outputItems(new ItemStack(Items.MANGROVE_PROPAGULE, 4))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("mangrove_propagule_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.MANGROVE_PROPAGULE))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.MANGROVE_LOG, 64))
                .outputItems(new ItemStack(Items.MANGROVE_LOG, 64))
                .outputItems(new ItemStack(Items.MANGROVE_PROPAGULE, 4))
                .duration(320).EUt(120)
                .save(provider);

        // Hollow oak sapling (Twilight Forest)
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("hollow_oak_sapling"))
                .circuitMeta(1)
                .notConsumable(
                        TFBlocks.HOLLOW_OAK_SAPLING.get().asItem())
                .inputFluids(Water.getFluid(1000))
                .outputItems(TFBlocks.TWILIGHT_OAK_LOG.get().asItem(),
                        64)
                .outputItems(
                        TFBlocks.HOLLOW_OAK_SAPLING.get().asItem(), 4)
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("hollow_oak_sapling_boosted"))
                .circuitMeta(2)
                .notConsumable(
                        TFBlocks.HOLLOW_OAK_SAPLING.get().asItem())
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(TFBlocks.TWILIGHT_OAK_LOG.get().asItem(),
                        64)
                .outputItems(TFBlocks.TWILIGHT_OAK_LOG.get().asItem(),
                        64)
                .outputItems(
                        TFBlocks.HOLLOW_OAK_SAPLING.get().asItem(), 4)
                .duration(320).EUt(120)
                .save(provider);

        // Crop greenhouse: sugar cane
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("sugar_cane"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.SUGAR_CANE))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.SUGAR_CANE, 24))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("sugar_cane_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.SUGAR_CANE))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.SUGAR_CANE, 48))
                .duration(320).EUt(120)
                .save(provider);

        // Kelp
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("kelp"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.KELP))
                .inputFluids(Water.getFluid(2000))
                .outputItems(new ItemStack(Items.KELP, 24))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("kelp_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.KELP))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(2000))
                .outputItems(new ItemStack(Items.KELP, 48))
                .duration(320).EUt(120)
                .save(provider);

        // Bamboo
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("bamboo"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.BAMBOO))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.BAMBOO, 24))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("bamboo_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.BAMBOO))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.BAMBOO, 48))
                .duration(320).EUt(120)
                .save(provider);

        // Cactus
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("cactus"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.CACTUS))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.CACTUS, 24))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("cactus_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.CACTUS))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.CACTUS, 48))
                .duration(320).EUt(120)
                .save(provider);

        // Wheat
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("wheat"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.WHEAT_SEEDS))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.WHEAT, 24))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("wheat_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.WHEAT_SEEDS))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.WHEAT, 48))
                .duration(320).EUt(120)
                .save(provider);

        // Carrot
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("carrot"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.CARROT))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.CARROT, 24))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("carrot_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.CARROT))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.CARROT, 48))
                .duration(320).EUt(120)
                .save(provider);

        // Potato
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("potato"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.POTATO))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.POTATO, 24))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("potato_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.POTATO))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.POTATO, 48))
                .duration(320).EUt(120)
                .save(provider);

        // Beetroot
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("beetroot"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.BEETROOT_SEEDS))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.BEETROOT, 24))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("beetroot_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.BEETROOT_SEEDS))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.BEETROOT, 48))
                .duration(320).EUt(120)
                .save(provider);

        // Melon
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("melon"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.MELON_SEEDS))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.MELON, 12))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("melon_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.MELON_SEEDS))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.MELON, 24))
                .duration(320).EUt(120)
                .save(provider);

        // Pumpkin
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("pumpkin"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.PUMPKIN_SEEDS))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.PUMPKIN, 12))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("pumpkin_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.PUMPKIN_SEEDS))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.PUMPKIN, 24))
                .duration(320).EUt(120)
                .save(provider);

        // Nether wart
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("nether_wart"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.NETHER_WART))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.NETHER_WART, 12))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("nether_wart_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.NETHER_WART))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.NETHER_WART, 24))
                .duration(320).EUt(120)
                .save(provider);

        // Red mushroom
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("red_mushroom"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.RED_MUSHROOM))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.RED_MUSHROOM, 12))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("red_mushroom_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.RED_MUSHROOM))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.RED_MUSHROOM, 24))
                .duration(320).EUt(120)
                .save(provider);

        // Brown mushroom
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("brown_mushroom"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.BROWN_MUSHROOM))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.BROWN_MUSHROOM, 12))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("brown_mushroom_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.BROWN_MUSHROOM))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.BROWN_MUSHROOM, 24))
                .duration(320).EUt(120)
                .save(provider);

        // Cocoa beans
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("cocoa_beans"))
                .circuitMeta(1)
                .notConsumable(new ItemStack(Items.COCOA_BEANS))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.COCOA_BEANS, 4))
                .duration(640).EUt(120)
                .save(provider);

        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("cocoa_beans_boosted"))
                .circuitMeta(2)
                .notConsumable(new ItemStack(Items.COCOA_BEANS))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.COCOA_BEANS, 24))
                .duration(320).EUt(120)
                .save(provider);

        // Rhizobium special greenhouse
        CTNHRecipeTypes.GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("rhizobium"))
                .circuitMeta(3)
                .notConsumable(new ItemStack(Items.SPRUCE_SAPLING))
                .inputItems(GTItems.FERTILIZER.asStack(4))
                .inputItems(dust, YeastRelatedMaterials.RHIZOBIUM, 4)
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.SPRUCE_LOG, 32))
                .outputItems(dust, YeastRelatedMaterials.RHIZOBIUM, 8)
                .outputItems(dust, SodiumNitrite, 50)
                .duration(640).EUt(120)
                .save(provider);

        // ============== Chemical Plant Recipes ==============

        // rp_1_mixed_fuel
        CTNHRecipeTypes.CHEMICAL_PLANT_RECIPES.recipeBuilder(CTNHCore.id("rp_1_mixed_fuel"))
                .circuitMeta(1)
                .inputFluids(RP1RocketFuel.getFluid(500))
                .inputFluids(RocketFuel.getFluid(500))
                .inputFluids(Oxygen.getFluid(2000))
                .outputFluids(RP1.getFluid(1000))
                .EUt(480).duration(300)
                .save(provider);

        // ============== Dimensional Gas Collection Recipes ==============

        // main_world_air1
        CTNHRecipeTypes.DIMENSIONAL_GAS_COLLECTION.recipeBuilder(CTNHCore.id("main_world_air1"))
                .notConsumable(new ItemStack(Items.POPPY))
                .circuitMeta(1)
                .outputFluids(Air.getFluid(500000))
                .EUt(1920).duration(200)
                .save(provider);

        // main_world_air2
        CTNHRecipeTypes.DIMENSIONAL_GAS_COLLECTION.recipeBuilder(CTNHCore.id("main_world_air2"))
                .notConsumable(GTMultiMachines.VACUUM_FREEZER.asStack())
                .notConsumable(new ItemStack(Items.POPPY))
                .outputFluids(LiquidAir.getFluid(500000))
                .EUt(6144).duration(200)
                .save(provider);

        // nether_1
        CTNHRecipeTypes.DIMENSIONAL_GAS_COLLECTION.recipeBuilder(CTNHCore.id("nether_1"))
                .notConsumable(new ItemStack(Items.WITHER_ROSE))
                .circuitMeta(1)
                .outputFluids(NetherAir.getFluid(500000))
                .EUt(6144).duration(200)
                .save(provider);

        // nether_2
        CTNHRecipeTypes.DIMENSIONAL_GAS_COLLECTION.recipeBuilder(CTNHCore.id("nether_2"))
                .notConsumable(new ItemStack(Items.WITHER_ROSE))
                .notConsumable(GTMultiMachines.VACUUM_FREEZER.asStack())
                .outputFluids(LiquidNetherAir.getFluid(500000))
                .EUt(24768).duration(200)
                .save(provider);

        // the_end_1
        CTNHRecipeTypes.DIMENSIONAL_GAS_COLLECTION.recipeBuilder(CTNHCore.id("the_end_1"))
                .notConsumable(new ItemStack(Items.CHORUS_FLOWER))
                .circuitMeta(1)
                .outputFluids(EnderAir.getFluid(500000))
                .EUt(24768).duration(200)
                .save(provider);

        // the_end_2
        CTNHRecipeTypes.DIMENSIONAL_GAS_COLLECTION.recipeBuilder(CTNHCore.id("the_end_2"))
                .notConsumable(new ItemStack(Items.CHORUS_FLOWER))
                .notConsumable(GTMultiMachines.VACUUM_FREEZER.asStack())
                .outputFluids(LiquidEnderAir.getFluid(500000))
                .EUt(122330).duration(200)
                .save(provider);

        // sky_dimension_1
        CTNHRecipeTypes.DIMENSIONAL_GAS_COLLECTION.recipeBuilder(CTNHCore.id("sky_dimension_1"))
                .notConsumable(AetherBlocks.WHITE_FLOWER.get().asItem())
                .circuitMeta(1)
                .outputFluids(ExtraterrestrialAtmosphereMaterials.AETHER_AIR.getFluid(500000))
                .EUt(6144).duration(200)
                .save(provider);

        // sky_dimension_2
        CTNHRecipeTypes.DIMENSIONAL_GAS_COLLECTION.recipeBuilder(CTNHCore.id("sky_dimension_2"))
                .notConsumable(AetherBlocks.WHITE_FLOWER.get().asItem())
                .notConsumable(GTMultiMachines.VACUUM_FREEZER.asStack())
                .outputFluids(ExtraterrestrialAtmosphereMaterials.LIQUID_AETHER_AIR.getFluid(500000))
                .EUt(24768).duration(200)
                .save(provider);

        // mana_1
        CTNHRecipeTypes.DIMENSIONAL_GAS_COLLECTION.recipeBuilder(CTNHCore.id("mana_1"))
                .notConsumable(BotaniaFlowerBlocks.entropinnyum.asItem())
                .circuitMeta(1)
                .outputFluids(ExtraterrestrialAtmosphereMaterials.ALFHEIM_AIR.getFluid(200000))
                .EUt(24768).duration(200)
                .save(provider);

        // mana_2
        CTNHRecipeTypes.DIMENSIONAL_GAS_COLLECTION.recipeBuilder(CTNHCore.id("mana_2"))
                .notConsumable(BotaniaFlowerBlocks.entropinnyum.asItem())
                .notConsumable(GTMultiMachines.VACUUM_FREEZER.asStack())
                .outputFluids(ExtraterrestrialAtmosphereMaterials.LIQUID_ALFHEIM_AIR.getFluid(200000))
                .EUt(122330).duration(200)
                .save(provider);

        // twilight_air1
        CTNHRecipeTypes.DIMENSIONAL_GAS_COLLECTION.recipeBuilder(CTNHCore.id("twilight_air1"))
                .notConsumable(
                        TFBlocks.RAINBOW_OAK_SAPLING.get().asItem())
                .circuitMeta(1)
                .outputFluids(ExtraterrestrialAtmosphereMaterials.TWILIGHTFOREST_AIR.getFluid(500000))
                .EUt(1920).duration(200)
                .save(provider);

        // twilight_air2
        CTNHRecipeTypes.DIMENSIONAL_GAS_COLLECTION.recipeBuilder(CTNHCore.id("twilight_air2"))
                .notConsumable(
                        TFBlocks.RAINBOW_OAK_SAPLING.get().asItem())
                .notConsumable(GTMultiMachines.VACUUM_FREEZER.asStack())
                .outputFluids(ExtraterrestrialAtmosphereMaterials.LIQUID_TWILIGHTFOREST_AIR.getFluid(500000))
                .EUt(6144).duration(200)
                .save(provider);

        // venus_air1
        CTNHRecipeTypes.DIMENSIONAL_GAS_COLLECTION.recipeBuilder(CTNHCore.id("venus_air1"))
                .notConsumable(ModItems.RAW_CALORITE.get())
                .circuitMeta(1)
                .outputFluids(ExtraterrestrialAtmosphereMaterials.VENUS_AIR.getFluid(500000))
                .EUt(122330).duration(200)
                .save(provider);

        // venus_air2
        CTNHRecipeTypes.DIMENSIONAL_GAS_COLLECTION.recipeBuilder(CTNHCore.id("venus_air2"))
                .notConsumable(ModItems.RAW_CALORITE.get())
                .notConsumable(GTMultiMachines.VACUUM_FREEZER.asStack())
                .outputFluids(ExtraterrestrialAtmosphereMaterials.LIQUID_VENUS_AIR.getFluid(500000))
                .EUt(490123).duration(200)
                .save(provider);

        // ============== Martial Morality Eye Recipes ==============

        // ore_production_1
        CTNHRecipeTypes.MARTIAL_MORALITY_EYE.recipeBuilder(CTNHCore.id("ore_production_1"))
                .inputItems(new ItemStack(Items.COBBLESTONE, 64))
                .inputItems(new ItemStack(Items.GOLD_INGOT, 64))
                .inputFluids(Steam.getFluid(64000))
                .chancedOutput(ChemicalHelper.get(rawOre, Trona, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Asbestos, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Vanadium, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Tin, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Nickel, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Silver, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Gold, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Antimony, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Copper, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Iron, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Cassiterite, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, CassiteriteSand, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Chalcopyrite, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Cinnabar, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Coal, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Diamond, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Garnierite, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Calcite, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, VanadiumMagnetite, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Tantalite, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Apatite, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, Lapis, 64), 1000, 0)
                .chancedOutput(ChemicalHelper.get(rawOre, TricalciumPhosphate, 64), 1000, 0)
                .EUt(96).duration(2400)
                .save(provider);

        // ore_production_2
        CTNHRecipeTypes.MARTIAL_MORALITY_EYE.recipeBuilder(CTNHCore.id("ore_production_2"))
                .inputItems(new ItemStack(Items.STONE, 64))
                .chancedInput(new ItemStack(Items.DIAMOND_BLOCK, 64), 7500, -100)
                .inputFluids(Creosote.getFluid(64000))
                .outputFluids(Lava.getFluid(8000))
                .outputFluids(FluidIngredient
                        .of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("alexscaves:acid")), 8000))
                .chancedOutput(ChemicalHelper.get(rawOre, Trona, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Asbestos, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Vanadium, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Tin, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Nickel, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Silver, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Gold, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Antimony, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Copper, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Iron, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Cassiterite, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, CassiteriteSand, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Chalcopyrite, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Cinnabar, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Coal, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Diamond, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Garnierite, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Calcite, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, VanadiumMagnetite, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Tantalite, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Apatite, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, Lapis, 64), 1000, 50)
                .chancedOutput(ChemicalHelper.get(rawOre, TricalciumPhosphate, 64), 1000, 50)
                .EUt(96).duration(2400)
                .save(provider);

        // ore_production_3
        CTNHRecipeTypes.MARTIAL_MORALITY_EYE.recipeBuilder(CTNHCore.id("ore_production_3"))
                .inputItems(ModItems.MOON_STONE.get(), 64)
                .chancedInput(ChemicalHelper.get(block, GalliumArsenide, 4), 5500, -100)
                .inputFluids(RocketFuel.getFluid(4000))
                .outputFluids(NitricAcid.getFluid(1000))
                .outputFluids(SulfuricAcid.getFluid(1000))
                .outputFluids(PhosphoricAcid.getFluid(1000))
                .EUt(480).duration(4800)
                .save(provider);

        // ============== Cultivation Room Recipes ==============

        // stem_cell_one
        CTNHRecipeTypes.CULTIVATION_ROOM.recipeBuilder(CTNHCore.id("stem_cell_one"))
                .inputItems(GTItems.STEM_CELLS.asStack(64))
                .chancedInput(new ItemStack(Items.SCULK_VEIN), 1000, 200)
                .chancedInput(GTItems.PETRI_DISH.asStack(), 2000, -10)
                .inputFluids(SterileGrowthMedium.getFluid(1000))
                .inputFluids(FermentedBiomass.getFluid(1000))
                .chancedOutput(BiologicalCultureMediumStockSolution.getFluid(2000), 5000, 400)
                .EUt(122330).duration(3000)
                .save(provider);

        // sculk_vein
        CTNHRecipeTypes.CULTIVATION_ROOM.recipeBuilder(CTNHCore.id("sculk_vein"))
                .notConsumable(new ItemStack(Items.SCULK_CATALYST))
                .chancedInput(GTItems.PETRI_DISH.asStack(), 2000, -10)
                .inputItems(new ItemStack(Items.STONE, 4))
                .inputFluids(FluidIngredient
                        .of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("enderio:xp_juice")), 1000))
                .outputItems(new ItemStack(Items.SCULK_VEIN, 4))
                .outputItems(new ItemStack(Items.SCULK, 3))
                .EUt(480).duration(200)
                .save(provider);

        // sculk_vein_2
        CTNHRecipeTypes.CULTIVATION_ROOM.recipeBuilder(CTNHCore.id("sculk_vein_2"))
                .notConsumable(new ItemStack(Items.SCULK_CATALYST))
                .chancedInput(GTItems.PETRI_DISH.asStack(), 2000, -10)
                .inputItems(new ItemStack(Items.STONE, 16))
                .inputFluids(SterileBiologicalCultureMediumStockSolution.getFluid(100))
                .outputItems(new ItemStack(Items.SCULK_VEIN, 64))
                .outputItems(new ItemStack(Items.SCULK, 16))
                .EUt(480).duration(200)
                .save(provider);

        // sculk_vein_3
        CTNHRecipeTypes.CULTIVATION_ROOM.recipeBuilder(CTNHCore.id("sculk_vein_3"))
                .notConsumable(new ItemStack(Items.SCULK_CATALYST))
                .chancedInput(GTItems.PETRI_DISH.asStack(), 2000, -10)
                .inputItems(new ItemStack(Items.STONE, 8))
                .inputFluids(SterileGrowthMedium.getFluid(100))
                .outputItems(new ItemStack(Items.SCULK_VEIN, 32))
                .outputItems(new ItemStack(Items.SCULK, 8))
                .EUt(480).duration(200)
                .save(provider);

        // ============== Circuit Assembler Recipes ==============

        // echo_circuit_board
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("echo_circuit_board"))
                .inputItems(CBItems.WETWARE_CIRCUIT_BOARD.asStack(), 4)
                .inputItems(new ItemStack(Items.ECHO_SHARD, 3))
                .inputItems(new ItemStack(Items.SCULK_VEIN, 5))
                .inputItems(GTItems.STEM_CELLS.asStack(4))
                .inputItems(wireGtSingle, HiddenAlloy, 3)
                .inputItems(pipeTinyFluid, HiddenAlloy, 3)
                .inputFluids(SterileBiologicalCultureMediumStockSolution.getFluid(600))
                .outputItems(ECHO_CIRCUIT_BOARD.asStack(16))
                .EUt(491520).duration(60)
                .save(provider);

        // echo_processor_1
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("echo_processor_1"))
                .inputItems(GTItems.NEURO_PROCESSOR.asStack())
                .inputItems(SCULK_CELL.asStack())
                .inputItems(GTItems.QUBIT_CENTRAL_PROCESSING_UNIT.asStack(), 4)
                .inputItems(BIOLOGICAL_PATCH_CAPACITOR.asStack(16))
                .inputItems(BIOLOGICAL_PATCH_TRANSISTOR.asStack(16))
                .inputItems(wireFine, EnrichedNaquadahTriniumEuropiumDuranide, 16)
                .inputFluids(Cerrobase140.getFluid(288))
                .outputItems(ECHO_PROCESSOR.asStack(4))
                .EUt(491520).duration(400)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .save(provider);

        // echo_printed_circuit_board
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("echo_printed_circuit_board"))
                .inputItems(ECHO_CIRCUIT_BOARD.asStack())
                .inputItems(foil, Trinium, 8)
                .inputFluids(Iron3Chloride.getFluid(6000))
                .outputItems(
                        ECHO_PRINTED_CIRCUIT_BOARD.asStack())
                .EUt(1920).duration(3600)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .save(provider);

        // ============== Chemical Vapor Deposition Recipes ==============

        // w-cdimod
        CTNHRecipeTypes.CHEMICAL_VAPOR_DEPOSITION.recipeBuilder(CTNHCore.id("w-cdimod"))
                .inputItems(gemFlawless, Diamond)
                .inputItems(foil, Copper, 16)
                .inputFluids(Tungsten.getFluid(144))
                .outputItems(gem, BedrockMaterials.TUNGSTENCU_DIAMOND_PLATING)
                .EUt(114514).duration(500)
                .save(provider);

        // ============== Crystallizer Recipes ==============

        // flawless_tungstencu_diamond_plating_gem
        CTNHRecipeTypes.CRYSTALLIZER.recipeBuilder(CTNHCore.id("flawless_tungstencu_diamond_plating_gem"))
                .inputItems(dust, BedrockMaterials.TUNGSTENCU_DIAMOND_PLATING)
                .inputItems(foil, Copper, 32)
                .inputFluids(Tungsten.getFluid(288))
                .outputItems(gem, BedrockMaterials.TUNGSTENCU_DIAMOND_PLATING)
                .EUt(491520).duration(1000)
                .blastFurnaceTemp(10800)
                .save(provider);

        // ============== Differential Centrifuge Recipes ==============

        // alexscaves_acid
        CTNHRecipeTypes.DIFFERENTIAL_CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("alexscaves_acid"))
                .inputFluids(FluidIngredient
                        .of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("alexscaves:acid")), 1000))
                .outputFluids(Water.getFluid(500))
                .outputFluids(HydrochloricAcid.getFluid(250))
                .outputFluids(SulfuricAcid.getFluid(100))
                .outputFluids(PhosphoricAcid.getFluid(150))
                .outputItems(dustTiny, Uranium238, 2)
                .chancedOutput(dustTiny, Plutonium239, 2000, 500)
                .EUt(1920).duration(160)
                .save(provider);

        // ============== Rocket Engine Recipes ==============

        // engine_1
        CTNHRecipeTypes.ROCKET_ENGINE_RECIPES.recipeBuilder(CTNHCore.id("engine_1"))
                .inputFluids(RocketFuel.getFluid(16))
                .EUt(-64).duration(125)
                .save(provider);

        // engine_2
        CTNHRecipeTypes.ROCKET_ENGINE_RECIPES.recipeBuilder(CTNHCore.id("engine_2"))
                .inputFluids(RP1.getFluid(1))
                .EUt(-2048).duration(40)
                .save(provider);

        // engine_3
        CTNHRecipeTypes.ROCKET_ENGINE_RECIPES.recipeBuilder(CTNHCore.id("engine_3"))
                .inputFluids(DenseHydrazineMixedFuel.getFluid(1))
                .EUt(-2048).duration(80)
                .save(provider);

        // engine_4
        CTNHRecipeTypes.ROCKET_ENGINE_RECIPES.recipeBuilder(CTNHCore.id("engine_4"))
                .inputFluids(MethylhydrazineNitrateRocketFuel.getFluid(1))
                .EUt(-2048).duration(160)
                .save(provider);

        // engine_5
        CTNHRecipeTypes.ROCKET_ENGINE_RECIPES.recipeBuilder(CTNHCore.id("engine_5"))
                .inputFluids(UDMHRocketFuel.getFluid(1))
                .EUt(-2048).duration(320)
                .save(provider);

        // ============== Gas Turbine Recipes ==============

        GAS_TURBINE_FUELS.recipeBuilder(CTNHCore.id("turbine_1"))
                .inputFluids(CoalGas.getFluid(1))
                .EUt(-32).duration(20)
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder(CTNHCore.id("turbine_2"))
                .inputFluids(WoodGas.getFluid(3))
                .EUt(-32).duration(20)
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder(CTNHCore.id("turbine_3"))
                .inputFluids(Benzene.getFluid(1))
                .EUt(-32).duration(20)
                .save(provider);

        GAS_TURBINE_FUELS.recipeBuilder(CTNHCore.id("nitrobenzene_one"))
                .inputFluids(Nitrobenzene.getFluid(1))
                .EUt(-48).duration(45)
                .save(provider);

        // ============== Combustion Generator Recipes ==============

        COMBUSTION_GENERATOR_FUELS.recipeBuilder(CTNHCore.id("combustion_1"))
                .inputFluids(Naphtha.getFluid(1))
                .EUt(-32).duration(15)
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder(CTNHCore.id("combustion_2"))
                .inputFluids(LightFuel.getFluid(1))
                .EUt(-32).duration(15)
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder(CTNHCore.id("combustion_3"))
                .inputFluids(Diesel.getFluid(1))
                .EUt(-32).duration(25)
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder(CTNHCore.id("combustion_4"))
                .inputFluids(BioDiesel.getFluid(1))
                .EUt(-32).duration(20)
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder(CTNHCore.id("light_fuel_one"))
                .inputFluids(LightFuel.getFluid(1))
                .EUt(-32).duration(20)
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder(CTNHCore.id("dieselp_one"))
                .inputFluids(BioDiesel.getFluid(1))
                .EUt(-32).duration(25)
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder(CTNHCore.id("dieselp_two"))
                .inputFluids(Diesel.getFluid(1))
                .EUt(-32).duration(30)
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder(CTNHCore.id("boosted_diesel_one"))
                .inputFluids(CetaneBoostedDiesel.getFluid(1))
                .EUt(-32).duration(50)
                .save(provider);

        // ============== Naquadah Reactor Fuel Recipes ==============

        // generator1 - Naquadah Mk1
        CTNHRecipeTypes.NAQ_MK1.recipeBuilder(CTNHCore.id("generator1"))
                .inputFluids(BedrockMaterials.SUPERFUELMK1.getFluid(1))
                .EUt(-399576000).duration(300)
                .save(provider);

        // generator12 - Large Naquadah Reactor
        CTNHRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder(CTNHCore.id("generator12"))
                .inputFluids(UncategorizedMaterials.CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_I.getFluid(1))
                .outputFluids(NaquadahBasedLiquidFuelDepleted.getFluid(1))
                .EUt(-2300000).duration(40)
                .save(provider);

        // generator22
        CTNHRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder(CTNHCore.id("generator22"))
                .inputFluids(UncategorizedMaterials.CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_II.getFluid(1))
                .outputFluids(NaquadahBasedLiquidFuelDepleted.getFluid(5))
                .EUt(-9511000).duration(80)
                .save(provider);

        // generator32
        CTNHRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder(CTNHCore.id("generator32"))
                .inputFluids(UncategorizedMaterials.CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_III.getFluid(1))
                .outputFluids(NaquadahBasedLiquidFuelDepleted.getFluid(10))
                .EUt(-88540000).duration(160)
                .save(provider);

        // generator42
        CTNHRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder(CTNHCore.id("generator42"))
                .inputFluids(BedrockMaterials.TARANLIQUIDFUEL_L.getFluid(1))
                .outputFluids(PlutoniumBasedLiquidFuelDepleted.getFluid(1))
                .EUt(-1572864).duration(10)
                .save(provider);

        // generator52
        CTNHRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder(CTNHCore.id("generator52"))
                .inputFluids(BedrockMaterials.TARANLIQUIDFUEL_M.getFluid(1))
                .outputFluids(PlutoniumBasedLiquidFuelDepleted.getFluid(2))
                .EUt(-3145728).duration(16)
                .save(provider);

        // generator62
        CTNHRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder(CTNHCore.id("generator62"))
                .inputFluids(BedrockMaterials.TARANLIQUIDFUEL_H.getFluid(1))
                .outputFluids(PlutoniumBasedLiquidFuelDepleted.getFluid(5))
                .EUt(-4718592).duration(32)
                .save(provider);

        // ============== Shaped Crafting Recipes ==============

        // Treated wood small fluid pipe
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("treated_wood_small_fluid_pipe"),
                ChemicalHelper.get(pipeSmallFluid, TreatedWood),
                "S", "P", "M",
                'S', CustomTags.SAWS,
                'P', GTBlocks.TREATED_WOOD_PLANK.asStack(),
                'M', CustomTags.MALLETS);

        // Treated wood normal fluid pipe
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("treated_wood_normal_fluid_pipe"),
                ChemicalHelper.get(pipeNormalFluid, TreatedWood),
                "PS", "P ", "PM",
                'P', GTBlocks.TREATED_WOOD_PLANK.asStack(),
                'S', CustomTags.SAWS,
                'M', CustomTags.MALLETS);

        // Treated wood large fluid pipe
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("treated_wood_large_fluid_pipe"),
                ChemicalHelper.get(pipeLargeFluid, TreatedWood),
                "PSP", "P P", "PMP",
                'P', GTBlocks.TREATED_WOOD_PLANK.asStack(),
                'S', CustomTags.SAWS,
                'M', CustomTags.MALLETS);

        // Digital Well of Suffer crafting loop (LV through UV)
        {
            String[] dwosTiers = { "lv", "mv", "hv", "ev", "iv", "luv", "zpm", "uv" };
            int[] voltageTiers = { GTValues.LV, GTValues.MV, GTValues.HV, GTValues.EV, GTValues.IV, GTValues.LuV,
                    GTValues.ZPM, GTValues.UV };
            ItemEntry<?>[] pumps = {
                    GTItems.ELECTRIC_PUMP_LV, GTItems.ELECTRIC_PUMP_MV, GTItems.ELECTRIC_PUMP_HV,
                    GTItems.ELECTRIC_PUMP_EV, GTItems.ELECTRIC_PUMP_IV, GTItems.ELECTRIC_PUMP_LuV,
                    GTItems.ELECTRIC_PUMP_ZPM, GTItems.ELECTRIC_PUMP_UV
            };
            TagKey[] circuits = {
                    CustomTags.LV_CIRCUITS, CustomTags.MV_CIRCUITS, CustomTags.HV_CIRCUITS,
                    CustomTags.EV_CIRCUITS, CustomTags.IV_CIRCUITS, CustomTags.LuV_CIRCUITS,
                    CustomTags.ZPM_CIRCUITS, CustomTags.UV_CIRCUITS
            };

            for (int i = 0; i < dwosTiers.length; i++) {
                String tier = dwosTiers[i];
                int voltTier = voltageTiers[i];
                VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id(tier + "_digital_well_of_suffer"),
                        new ItemStack(ForgeRegistries.ITEMS
                                .getValue(ResourceLocation.parse("ctnhmana:" + tier + "_digital_well_of_suffer"))),
                        "PCP", "SHS", "PCP",
                        'P', pumps[i],
                        'C', circuits[i],
                        'H', GTMachines.HULL[voltTier].asStack(),
                        'S', new ItemStack(
                                BloodMagicBlocks.SACRIFICE_RUNE.get().asItem()));
            }
        }

        // ============== Smelting Recipe ==============

        VanillaRecipeHelper.addSmeltingRecipe(provider, CTNHCore.id("smelting_hot_high_temp_wrought_precursor"),
                ChemicalHelper.get(ingotHot, UncategorizedMaterials.HIGH_TEMP_WROUGHT_PRECURSOR),
                new ItemStack(Items.IRON_INGOT),
                1.4f);

        // =============== Platinum Chain ================
        // 从 PlatinumChain.js 迁移
        CMRecipeTypes.MANA_TRANSFORMER_RECIPES.recipeBuilder(CTNHCore.id("crystal_catalyst1"))
                .inputItems(dust, PlatinumGroupSludge, 42)
                .notConsumable(CRYSTAL_CATALYST)
                .outputItems(dust, Palladium, 7)
                .outputItems(dust, Platinum, 7)
                .outputItems(dust, Ruthenium, 7)
                .outputItems(dust, Rhodium, 7)
                .outputItems(dust, Osmium, 7)
                .outputItems(dust, Iridium, 7)
                .EUt(1920)
                .duration(600)
                .circuitMeta(1)
                .save(provider);

        // =============== Iodine Chain ================
        // 从 IodineChain.js 迁移
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("sodium_iodate"))
                .inputItems(dust, SodiumHydroxide, 18)
                .inputItems(dust, NewExplosivesProductionMaterials.SODIUM_IODIDE, 2)
                .inputFluids(YeastRelatedMaterials.BLUE_VITRIOL_SOLUTION.getFluid(3000))
                .inputFluids(SulfurTrioxide.getFluid(3000))
                .outputItems(dust, NewExplosivesProductionMaterials.SODIUM_IODATE, 5)
                .outputItems(dust, Copper, 3)
                .outputItems(dust, Sodium, 3)
                .outputFluids(NewExplosivesProductionMaterials.SODIUM_SULFATE_SOLUTION.getFluid(3000))
                .outputFluids(Water.getFluid(6000))
                .outputFluids(SulfurDioxide.getFluid(3000))
                .EUt(1920)
                .duration(290)
                .save(provider);

        // =============== Snow Adjust ================
        // 从 SnowAdjust.js 迁移
        VACUUM_RECIPES.recipeBuilder(CTNHCore.id("adjust_liquid_oxygen"))
                .inputFluids(Oxygen.getFluid(1000))
                .outputFluids(Oxygen.getFluid(FluidStorageKeys.LIQUID, 1000))
                .EUt(480)
                .duration(120)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("adjust_ender_fluid_link"))
                .inputItems(GTItems.SENSOR_LuV.asStack())
                .inputItems(GTItems.EMITTER_LuV.asStack())
                .inputItems(GTItems.ELECTRIC_PUMP_LuV.asStack())
                .inputItems(CustomTags.LuV_CIRCUITS, 2)
                .inputItems(plate, EnderEye, 8)
                .inputItems(plateDense, RhodiumPlatedPalladium, 4)
                .inputFluids(Polybenzimidazole.getFluid(576))
                .outputItems(GTItems.COVER_ENDER_FLUID_LINK.asStack())
                .EUt(30720)
                .duration(200)
                .save(provider);

        // =============== WChain ================
        // 从 WChain.js 迁移

        // 1. Dehydrate tungstic acid: 7x tungstic_acid_dust -> 4x tungsten_trioxide_dust + water 1000
        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder(CTNHCore.id("tungsten_trioxide_dust"))
                .inputItems(dust, TungsticAcid, 7)
                .outputItems(dust, SpecialMaterials.TUNGSTEN_TRIOXIDE, 4)
                .outputFluids(Water.getFluid(1000))
                .EUt(480)
                .duration(100)
                .save(provider);

        // 2. Reduce tungsten trioxide to tungsten dust: 4x tungsten_trioxide_dust + hydrogen 6000 -> tungsten_dust + water 3000
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("tungsten_dust"))
                .inputItems(dust, SpecialMaterials.TUNGSTEN_TRIOXIDE, 4)
                .inputFluids(Hydrogen.getFluid(6000))
                .outputItems(dust, Tungsten)
                .outputFluids(Water.getFluid(3000))
                .circuitMeta(2)
                .EUt(480)
                .duration(60)
                .blastFurnaceTemp(3500)
                .save(provider);

        // 3. Reduce tungsten trioxide to tungsten ingot: 8x tungsten_trioxide_dust + 3x carbon_dust -> 2x hot_tungsten_ingot + carbon_dioxide 3000
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("tungsten_ingot"))
                .inputItems(dust, SpecialMaterials.TUNGSTEN_TRIOXIDE, 8)
                .inputItems(dust, Carbon, 3)
                .outputItems(ChemicalHelper.get(ingotHot, Tungsten, 2))
                .outputFluids(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(1000)
                .blastFurnaceTemp(3500)
                .save(provider);

        // =============== TiChain ================
        // 从 TiChain.js 迁移

        // 1. Distill titanium tetrachloride: titanium_tetrachloride 3000 -> gallium_dust + iron_iii_chloride 1000 + titanium_tetrachloride 1000 + refining_titanium_tetrachloride 1250
        DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("refining_titanium_tetrachloride_bucket"))
                .inputFluids(TitaniumTetrachloride.getFluid(3000))
                .outputItems(dust, Gallium, 3)
                .outputFluids(Iron3Chloride.getFluid(1000))
                .outputFluids(TitaniumTetrachloride.getFluid(1000))
                .outputFluids(BauxiteProcessingMaterials.REFINING_TITANIUM_TETRACHLORIDE.getFluid(1250))
                .EUt(120)
                .duration(100)
                .save(provider);

        // 2. Remove vanadium: titanium_tetrachloride_v 6000 + water 9000 + 2x aluminium_dust -> 8x aluminium_chloride_dust + 21x vanadium_pentoxide_dust + hydrochloric_acid 18000 + titanium_tetrachloride 6000
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("vanadium_pentoxide_dust"))
                .inputFluids(BauxiteProcessingMaterials.TITANIUM_TETRACHLORIDE_V.getFluid(6000))
                .inputFluids(Water.getFluid(9000))
                .inputItems(dust, Aluminium, 2)
                .outputItems(dust, BauxiteProcessingMaterials.ALUMINIUM_CHLORIDE, 8)
                .outputItems(dust, VanadiumPentoxide, 21)
                .outputFluids(HydrochloricAcid.getFluid(18000))
                .outputFluids(TitaniumTetrachloride.getFluid(6000))
                .EUt(120)
                .duration(150)
                .save(provider);

        // 3. Synthesize vanadium-containing TiCl4: chlorine 48000 + 6x rutile_dust + 12x carbon_dust -> carbon_monoxide 12000 + titanium_tetrachloride_v 6000
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("ticl4"))
                .inputFluids(Chlorine.getFluid(48000))
                .inputItems(dust, Rutile, 6)
                .inputItems(dust, Carbon, 12)
                .outputFluids(CarbonMonoxide.getFluid(12000))
                .outputFluids(BauxiteProcessingMaterials.TITANIUM_TETRACHLORIDE_V.getFluid(6000))
                .EUt(480)
                .duration(120)
                .save(provider);

        // 4. Convert high-purity TiCl4 to titanium ingot: refining_titanium_tetrachloride 5000 + 10x magnesium_dust -> 5x hot_titanium_ingot + 30x magnesium_chloride_dust
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("titanium_ingot"))
                .inputFluids(BauxiteProcessingMaterials.REFINING_TITANIUM_TETRACHLORIDE.getFluid(5000))
                .inputItems(dust, Magnesium, 10)
                .outputItems(ChemicalHelper.get(ingotHot, Titanium, 5))
                .outputItems(dust, MagnesiumChloride, 30)
                .EUt(480)
                .duration(150)
                .blastFurnaceTemp(2200)
                .save(provider);

        // =============== ChromiteChain ================
        // 从 ChromiteChain.js 迁移

        // 1. Sodium carbonate solution: soda_ash_dust + water -> sodium_carbonate_solution
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("sodium_carbonate_solution"))
                .inputItems(dust, SodaAsh)
                .inputFluids(Water.getFluid(1000))
                .outputFluids(BiodieselFertileSoilMaterials.SODIUM_CARBONATE_SOLUTION.getFluid(1000))
                .EUt(30).duration(200)
                .save(provider);

        // 2. Sodium chromate from sodium carbonate: chromite_dust + oxygen + sodium_carbonate_solution -> magnetite_dust + carbon_dioxide + sodium_chromate_solution
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("sodium_chromate_from_sodium_carbonate"))
                .inputItems(dust, Chromite)
                .inputFluids(Oxygen.getFluid(1000))
                .inputFluids(BiodieselFertileSoilMaterials.SODIUM_CARBONATE_SOLUTION.getFluid(1000))
                .outputItems(dust, Magnetite)
                .outputFluids(CarbonDioxide.getFluid(1000))
                .outputFluids(BiodieselFertileSoilMaterials.SODIUM_CHROMATE_SOLUTION.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 3. Sodium dichromate from sodium chromate: sulfuric_acid + sodium_chromate_solution -> sodium_sulfate_dust + sodium_dichromate_solution
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("sodium_dichromate_from_sodium_chromate"))
                .inputFluids(SulfuricAcid.getFluid(1000))
                .inputFluids(BiodieselFertileSoilMaterials.SODIUM_CHROMATE_SOLUTION.getFluid(1000))
                .outputItems(dust, SodiumSulfate)
                .outputFluids(BiodieselFertileSoilMaterials.SODIUM_DICHROMATE_SOLUTION.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 4. Chromium oxide from sodium dichromate: carbon_dust + sodium_dichromate_solution -> soda_ash_dust + chromium_oxide_dust + carbon_monoxide
        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder(CTNHCore.id("chromium_oxide_dust_from_sodium_dichromate"))
                .inputItems(dust, Carbon)
                .inputFluids(BiodieselFertileSoilMaterials.SODIUM_DICHROMATE_SOLUTION.getFluid(1000))
                .outputItems(dust, SodaAsh)
                .outputItems(dust, BiodieselFertileSoilMaterials.CHROMIUM_OXIDE)
                .outputFluids(CarbonMonoxide.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 5. Chrome from chromium oxide: chromium_oxide_dust + aluminium_dust -> chromium_dust + alumina_dust
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("chrome_from_chromium_3"))
                .inputItems(dust, BiodieselFertileSoilMaterials.CHROMIUM_OXIDE)
                .inputItems(dust, Aluminium)
                .outputItems(dust, Chromium)
                .outputItems(dust, Alumina)
                .EUt(480).duration(200)
                .blastFurnaceTemp(1700)
                .save(provider);

        // 6. Sodium sulfide from sodium sulfate: sodium_sulfate_dust + carbon_dust -> sodium_sulfide_dust + carbon_dioxide
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("sodium_sulfide_from_sodium_sulfate"))
                .inputItems(dust, SodiumSulfate)
                .inputItems(dust, Carbon)
                .outputItems(dust, SodiumSulfide)
                .outputFluids(CarbonDioxide.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 7. Soda ash from sodium sulfide: sodium_sulfide_dust + quicklime_dust + carbon_dioxide -> soda_ash_dust + calcium_sulfide_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("soda_ash_from_sodium_sulfide"))
                .inputItems(dust, SodiumSulfide)
                .inputItems(dust, Quicklime)
                .inputFluids(CarbonDioxide.getFluid(1000))
                .outputItems(dust, SodaAsh)
                .outputItems(dust, CreateMaterials.CALCIUM_SULFIDE)
                .EUt(120).duration(200)
                .save(provider);

        // =============== SeleniumTelluriumChain ================
        // 从 SeleniumTelluriumChain.js 迁移

        // 1. Blue vitriol: purified_chalcopyrite_ore + nitric_acid -> blue_vitriol_solution + tiny_platinum_group_sludge_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("blue_vitriol"))
                .inputItems(crushedPurified, Chalcopyrite)
                .inputFluids(NitricAcid.getFluid(1000))
                .outputFluids(YeastRelatedMaterials.BLUE_VITRIOL_SOLUTION.getFluid(1000))
                .outputItems(dustTiny, PlatinumGroupSludge)
                .EUt(120).duration(200)
                .save(provider);

        // 2. Blue vitriol electrolysis: blue_vitriol_solution -> sulfuric_acid + oxygen + copper_dust + chancedOutput(chalcogen_anode_mud_dust)
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("blue_vitriol1"))
                .inputFluids(YeastRelatedMaterials.BLUE_VITRIOL_SOLUTION.getFluid(1000))
                .outputFluids(SulfuricAcid.getFluid(1000))
                .outputFluids(Oxygen.getFluid(1000))
                .outputItems(dust, Copper)
                .chancedOutput(dust, NewExplosivesProductionMaterials.CHALCOGEN_ANODE_MUD, 2500, 500)
                .EUt(120).duration(200)
                .save(provider);

        // 3. Chalcogen anode mud centrifuge: chalcogen_anode_mud_dust -> silver_dust + chancedOutput(copper_dust) + chancedOutput(gold_dust)
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("chalcogen_anode_mud_bonus"))
                .inputItems(dust, NewExplosivesProductionMaterials.CHALCOGEN_ANODE_MUD)
                .outputItems(dust, Silver)
                .chancedOutput(dust, Copper, 2500, 500)
                .chancedOutput(dust, Gold, 1500, 500)
                .EUt(30).duration(200)
                .save(provider);

        // 4. Tellurium recycle: chalcogen_anode_mud_dust + soda_ash_dust + oxygen -> sodium_tellurite_dust + selenium_dioxide_dust + silver_ingot + carbon_dioxide
        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder(CTNHCore.id("tellurium_recycle"))
                .inputItems(dust, NewExplosivesProductionMaterials.CHALCOGEN_ANODE_MUD)
                .inputItems(dust, SodaAsh)
                .inputFluids(Oxygen.getFluid(1000))
                .outputItems(dust, NewExplosivesProductionMaterials.SODIUM_TELLURITE)
                .outputItems(dust, NewExplosivesProductionMaterials.SELENIUM_DIOXIDE)
                .outputItems(ingot, Silver)
                .outputFluids(CarbonDioxide.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 5. Tellurium recycle electrolysis: sodium_tellurite_dust + water -> tellurium_dioxide_dust + sodium_hydroxide_dust
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("tellurium_recycle1"))
                .inputItems(dust, NewExplosivesProductionMaterials.SODIUM_TELLURITE)
                .inputFluids(Water.getFluid(1000))
                .outputItems(dust, NewExplosivesProductionMaterials.TELLURIUM_DIOXIDE)
                .outputItems(dust, SodiumHydroxide)
                .EUt(120).duration(200)
                .save(provider);

        // 6. Tellurium recycle chemical: tellurium_dioxide_dust + sulfur_dioxide + water -> tellurium_dust + sulfuric_acid + sulfur_trioxide
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("tellurium_recycle2"))
                .inputItems(dust, NewExplosivesProductionMaterials.TELLURIUM_DIOXIDE)
                .inputFluids(SulfurDioxide.getFluid(1000))
                .inputFluids(Water.getFluid(1000))
                .outputItems(dust, Tellurium)
                .outputFluids(SulfuricAcid.getFluid(1000))
                .outputFluids(SulfurTrioxide.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 7. Selenium dioxide recycle: selenium_dioxide_dust + water -> selenous_acid
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("selenium_dioxide_recycle"))
                .inputItems(dust, NewExplosivesProductionMaterials.SELENIUM_DIOXIDE)
                .inputFluids(Water.getFluid(1000))
                .outputFluids(NewExplosivesProductionMaterials.SELENOUS_ACID.getFluid(1000))
                .EUt(30).duration(200)
                .save(provider);

        // 8. Selenium recycle: selenous_acid + sulfur_dioxide -> selenium_dust + sulfuric_acid + sulfur_trioxide
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("selenium_dioxide_recycle1"))
                .inputFluids(NewExplosivesProductionMaterials.SELENOUS_ACID.getFluid(1000))
                .inputFluids(SulfurDioxide.getFluid(1000))
                .outputItems(dust, Selenium)
                .outputFluids(SulfuricAcid.getFluid(1000))
                .outputFluids(SulfurTrioxide.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // =============== TantaliteChain ================
        // 从 TantaliteChain.js 迁移

        // 1. Tantalum alkaline mixture: tantalite_dust + pyrochlore_dust + sodium_carbonate_solution -> tantalum_alkaline_mixture
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("tantalum_alkaline_mixture"))
                .inputItems(dust, Tantalite)
                .inputItems(dust, Pyrochlore)
                .inputFluids(BiodieselFertileSoilMaterials.SODIUM_CARBONATE_SOLUTION.getFluid(1000))
                .outputFluids(NiobiumTantalumJointProcessingMaterials.TANTALUM_ALKALINE_MIXTURE.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 2. Tantalite fluorine: potassium_fluoride_dust + tantalum_alkaline_mixture -> tantalite_fluorine + soda_ash_dust + manganese_dust + stone_dust
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("tantalite_fluorine"))
                .inputItems(dust, NiobiumTantalumJointProcessingMaterials.POTASSIUM_FLUORIDE)
                .inputFluids(NiobiumTantalumJointProcessingMaterials.TANTALUM_ALKALINE_MIXTURE.getFluid(1000))
                .outputFluids(NiobiumTantalumJointProcessingMaterials.TANTALITE_FLUORINE.getFluid(1000))
                .outputItems(dust, SodaAsh)
                .outputItems(dust, Manganese)
                .outputItems(dust, Stone)
                .EUt(480).duration(200)
                .save(provider);

        // 3. Potassium fluoride: potassium_dust + fluorine -> potassium_fluoride_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("potassium_fluoride"))
                .inputItems(dust, Potassium)
                .inputFluids(Fluorine.getFluid(1000))
                .outputItems(dust, NiobiumTantalumJointProcessingMaterials.POTASSIUM_FLUORIDE)
                .EUt(120).duration(200)
                .save(provider);

        // 4. Niobium tantalite: chromium_trioxide_dust + ammonia_monohydrate + tantalite_fluorine -> potassium_hydroxide_dust + chromium_dust + ammonium_fluoride + niobium_tantalite
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("niobium_tantalite"))
                .inputItems(dust, ChromiumTrioxide)
                .inputFluids(PlatinumLineMaterials.AmmoniaMonohydrate.getFluid(1000))
                .inputFluids(NiobiumTantalumJointProcessingMaterials.TANTALITE_FLUORINE.getFluid(1000))
                .outputItems(dust, PotassiumHydroxide)
                .outputItems(dust, Chromium)
                .outputFluids(SpecialMaterials.AMMONIUM_FLUORIDE.getFluid(1000))
                .outputFluids(NiobiumTantalumJointProcessingMaterials.NIOBIUM_TANTALITE.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 5. Tantalite oxide: niobium_tantalite -> tantalite_oxide_dust + niobium_oxide_dust + water
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("tantalite_oxide_dust"))
                .inputFluids(NiobiumTantalumJointProcessingMaterials.NIOBIUM_TANTALITE.getFluid(1000))
                .outputItems(dust, NiobiumTantalumJointProcessingMaterials.TANTALITE_OXIDE)
                .outputItems(dust, NiobiumTantalumJointProcessingMaterials.NIOBIUM_OXIDE)
                .outputFluids(Water.getFluid(1000))
                .EUt(30).duration(200)
                .save(provider);

        // 6. Niobium dust: niobium_oxide_dust + hematite_dust + aluminium_dust -> niobium_dust + iron_dust + alumina_dust
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("niobium_dust"))
                .inputItems(dust, NiobiumTantalumJointProcessingMaterials.NIOBIUM_OXIDE)
                .inputItems(dust, Hematite)
                .inputItems(dust, Aluminium)
                .outputItems(dust, Niobium)
                .outputItems(dust, Iron)
                .outputItems(dust, Alumina)
                .EUt(480).duration(200)
                .blastFurnaceTemp(1700)
                .save(provider);

        // 7. Tantalum dust: tantalite_oxide_dust + hematite_dust + aluminium_dust -> tantalum_dust + iron_dust + alumina_dust
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("tantalum_dust"))
                .inputItems(dust, NiobiumTantalumJointProcessingMaterials.TANTALITE_OXIDE)
                .inputItems(dust, Hematite)
                .inputItems(dust, Aluminium)
                .outputItems(dust, Tantalum)
                .outputItems(dust, Iron)
                .outputItems(dust, Alumina)
                .EUt(480).duration(200)
                .blastFurnaceTemp(1700)
                .save(provider);

        // 8. Ammonia from ammonium fluoride: ammonium_fluoride -> ammonia + fluorine
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("ammonia"))
                .inputFluids(SpecialMaterials.AMMONIUM_FLUORIDE.getFluid(1000))
                .outputFluids(Ammonia.getFluid(1000))
                .outputFluids(Fluorine.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 9. Hematite: iron_dust + oxygen -> hematite_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("hematite_dust"))
                .inputItems(dust, Iron)
                .inputFluids(Oxygen.getFluid(1000))
                .outputItems(dust, Hematite)
                .EUt(30).duration(200)
                .save(provider);

        // =============== GoldChain ================
        // 从 GoldChain.js 迁移

        // 1. Tier1 gold processing: gold_alloy_dust -> tiny_gold_dust + copper_dust
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("tier1_gold_processing"))
                .inputItems(dust, CrudeGoldRefiningMaterials.GOLD_ALLOY)
                .outputItems(dustTiny, Gold)
                .outputItems(dust, Copper)
                .EUt(30).duration(200)
                .save(provider);

        // 2. Tier2 gold processing: gold_leach_dust + hydrogen -> water + copper_dust + tiny_gold_dust
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("tier2_gold_processing"))
                .inputItems(dust, CrudeGoldRefiningMaterials.GOLD_LEACH)
                .inputFluids(Hydrogen.getFluid(1000))
                .outputFluids(Water.getFluid(1000))
                .outputItems(dust, Copper)
                .outputItems(dustTiny, Gold)
                .EUt(120).duration(200)
                .save(provider);

        // 3. Tier3 gold processing: copper_leach_dust -> copper_dust + chancedOutput(lead/iron/gallium/nickel/silver)
        SIFTER_RECIPES.recipeBuilder(CTNHCore.id("tier3_gold_processing"))
                .inputItems(dust, CrudeGoldRefiningMaterials.COPPER_LEACH)
                .outputItems(dust, Copper)
                .chancedOutput(dust, Lead, 2500, 500)
                .chancedOutput(dust, Iron, 2000, 500)
                .chancedOutput(dust, Gallium, 1500, 500)
                .chancedOutput(dust, Nickel, 1000, 500)
                .chancedOutput(dust, Silver, 500, 250)
                .EUt(120).duration(200)
                .save(provider);

        // 4. Gold alloy 1: precious_alloy_dust + copper_dust -> gold_alloy_ingot
        ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("gold_alloy1"))
                .inputItems(dust, PreciousAlloy)
                .inputItems(dust, Copper)
                .outputItems(ingot, CrudeGoldRefiningMaterials.GOLD_ALLOY)
                .EUt(16).duration(200)
                .save(provider);

        // 5. Gold alloy 2: precious_alloy_ingot + copper_dust -> gold_alloy_ingot
        ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("gold_alloy2"))
                .inputItems(ingot, PreciousAlloy)
                .inputItems(dust, Copper)
                .outputItems(ingot, CrudeGoldRefiningMaterials.GOLD_ALLOY)
                .EUt(16).duration(200)
                .save(provider);

        // 6. Gold alloy 3: precious_alloy_dust + copper_ingot -> gold_alloy_ingot
        ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("gold_alloy3"))
                .inputItems(dust, PreciousAlloy)
                .inputItems(ingot, Copper)
                .outputItems(ingot, CrudeGoldRefiningMaterials.GOLD_ALLOY)
                .EUt(16).duration(200)
                .save(provider);

        // 7. Gold alloy 4: precious_alloy_ingot + copper_ingot -> gold_alloy_ingot
        ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("gold_alloy4"))
                .inputItems(ingot, PreciousAlloy)
                .inputItems(ingot, Copper)
                .outputItems(ingot, CrudeGoldRefiningMaterials.GOLD_ALLOY)
                .EUt(16).duration(200)
                .save(provider);

        // 8. Gold leach dust: gold_alloy_ingot + nitric_acid -> gold_leach_dust + nitrogen_dioxide
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("gold_leach_dust"))
                .inputItems(ingot, CrudeGoldRefiningMaterials.GOLD_ALLOY)
                .inputFluids(NitricAcid.getFluid(1000))
                .outputItems(dust, CrudeGoldRefiningMaterials.GOLD_LEACH)
                .outputFluids(NitrogenDioxide.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 9. Copper leach dust: gold_leach_dust + hydrochloric_acid -> copper_leach_dust + chloroauric_acid
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("copper_leach_dust"))
                .inputItems(dust, CrudeGoldRefiningMaterials.GOLD_LEACH)
                .inputFluids(HydrochloricAcid.getFluid(1000))
                .outputItems(dust, CrudeGoldRefiningMaterials.COPPER_LEACH)
                .outputFluids(CrudeGoldRefiningMaterials.CHLOROAURIC_ACID.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 10. Chloroauric acid to gold: chloroauric_acid + notConsumable(potassium_metabi_sulfite_dust) -> gold_dust + water + chlorine
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("chloroauricacid_to_gold"))
                .inputFluids(CrudeGoldRefiningMaterials.CHLOROAURIC_ACID.getFluid(1000))
                .notConsumable(dust, BauxiteProcessingMaterials.POTASSIUM_METABI_SULFITE)
                .outputItems(dust, Gold)
                .outputFluids(Water.getFluid(1000))
                .outputFluids(Chlorine.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 11. Potassium metabisulfite: potassium_dust + sulfur_dust + oxygen -> potassium_metabi_sulfite_dust
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("potassium_metabi_sulfite_dust"))
                .inputItems(dust, Potassium)
                .inputItems(dust, Sulfur)
                .inputFluids(Oxygen.getFluid(2000))
                .outputItems(dust, BauxiteProcessingMaterials.POTASSIUM_METABI_SULFITE)
                .EUt(30).duration(200)
                .save(provider);

        // =============== GraphiteChain ================
        // 从 GraphiteChain.js 迁移

        // 1. Graphite gas: graphite_dust -> graphite_steam
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("graphite_gas"))
                .inputItems(dust, Graphite)
                .outputFluids(GrapheneProductionLineMaterials.GRAPHITE_STEAM.getFluid(1000))
                .EUt(480).duration(200)
                .blastFurnaceTemp(1700)
                .save(provider);

        // 2. Graphene plate production method 1: graphite_steam + iridium_plate + nitrogen -> graphite_ir_plate_plate
        CTNHRecipeTypes.CHEMICAL_VAPOR_DEPOSITION.recipeBuilder(CTNHCore.id("graphene_plate_production_method_1"))
                .inputFluids(GrapheneProductionLineMaterials.GRAPHITE_STEAM.getFluid(1000))
                .inputItems(plate, Iridium)
                .inputFluids(Nitrogen.getFluid(1000))
                .outputItems(plate, GrapheneProductionLineMaterials.GRAPHITE_IR_PLATE)
                .EUt(480).duration(200)
                .save(provider);

        // 3. Graphene plate production method 2: graphite_steam + double_iridium_plate + nitrogen -> double_graphite_ir_plate_plate
        CTNHRecipeTypes.CHEMICAL_VAPOR_DEPOSITION.recipeBuilder(CTNHCore.id("graphene_plate_production_method_2"))
                .inputFluids(GrapheneProductionLineMaterials.GRAPHITE_STEAM.getFluid(1000))
                .inputItems(plateDouble, Iridium)
                .inputFluids(Nitrogen.getFluid(1000))
                .outputItems(plateDouble, GrapheneProductionLineMaterials.GRAPHITE_IR_PLATE)
                .EUt(480).duration(200)
                .save(provider);

        // 4. Iridium plate graphene separation 1: graphite_ir_plate_plate + hydrochloric_acid -> hydrogen + iridium_chloride_dust + graphene_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("iridium_plate_graphene_separation_1"))
                .inputItems(plate, GrapheneProductionLineMaterials.GRAPHITE_IR_PLATE)
                .inputFluids(HydrochloricAcid.getFluid(1000))
                .outputFluids(Hydrogen.getFluid(1000))
                .outputItems(dust, IridiumChloride)
                .outputItems(dust, Graphene)
                .EUt(480).duration(200)
                .save(provider);

        // 5. Iridium plate graphene separation 2: double_graphite_ir_plate_plate + hydrochloric_acid -> hydrogen + iridium_chloride_dust + graphene_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("iridium_plate_graphene_separation_2"))
                .inputItems(plateDouble, GrapheneProductionLineMaterials.GRAPHITE_IR_PLATE)
                .inputFluids(HydrochloricAcid.getFluid(1000))
                .outputFluids(Hydrogen.getFluid(2000))
                .outputItems(dust, IridiumChloride, 2)
                .outputItems(dust, Graphene, 2)
                .EUt(480).duration(200)
                .save(provider);

        // 6. Graphene powder: graphite_dust + duct_tape -> chancedOutput(small_graphene_dust)
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("graphene_powder"))
                .inputItems(dust, Graphite)
                .inputItems(GTItems.DUCT_TAPE.asStack())
                .chancedOutput(dustSmall, Graphene, 5000, 500)
                .EUt(120).duration(200)
                .save(provider);

        // 7. Graphite gas production method 1: fluid_cell + methane -> graphite_steam + fluid_cell(hydrogen)
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("graphite_gas_production_method_1"))
                .inputItems(GTItems.FLUID_CELL.asStack())
                .inputFluids(Methane.getFluid(1000))
                .outputFluids(GrapheneProductionLineMaterials.GRAPHITE_STEAM.getFluid(1000))
                .outputItems(GTItems.FLUID_CELL.asStack())
                .EUt(480).duration(200)
                .blastFurnaceTemp(1700)
                .save(provider);

        // 8. Graphite gas production method 1 no hydrogen: methane -> graphite_steam
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("graphite_gas_production_method_1_no_hydrogen"))
                .inputFluids(Methane.getFluid(1000))
                .outputFluids(GrapheneProductionLineMaterials.GRAPHITE_STEAM.getFluid(1000))
                .EUt(480).duration(200)
                .blastFurnaceTemp(1700)
                .save(provider);

        // =============== ZirconChain ================
        // 从 ZirconChain.js 迁移

        // 1. Barium hydroxide: barium_dust + hydrogen_peroxide -> barium_hydroxide_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("barium_hydroxide"))
                .inputItems(dust, Barium)
                .inputFluids(HydrogenPeroxide.getFluid(1000))
                .outputItems(dust, NewExplosivesProductionMaterials.BARIUM_HYDROXIDE)
                .EUt(120).duration(200)
                .save(provider);

        // 2. Mesityl oxide: notConsumable(barium_hydroxide_dust) + acetone -> mesityl_oxide + water
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("mesityl_oxide"))
                .notConsumable(dust, NewExplosivesProductionMaterials.BARIUM_HYDROXIDE)
                .inputFluids(Acetone.getFluid(2000))
                .outputFluids(NewExplosivesProductionMaterials.MESITYL_OXIDE.getFluid(1000))
                .outputFluids(Water.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 3. Methyl isobutyl ketone: notConsumable(palladium_on_carbon) + carbon_dust + mesityl_oxide + water -> methyl_isobutyl_ketone + carbon_monoxide
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("methyl_isobutyl_ketone"))
                .notConsumable(CTNHTagPrefixes.catalyst, NaquadahMaterials.PalladiumOnCarbon)
                .inputItems(dust, Carbon)
                .inputFluids(NewExplosivesProductionMaterials.MESITYL_OXIDE.getFluid(1000))
                .inputFluids(Water.getFluid(1000))
                .outputFluids(NewExplosivesProductionMaterials.METHYL_ISOBUTYL_KETONE.getFluid(1000))
                .outputFluids(CarbonMonoxide.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 4. Thiocyanic acid: sulfur_dust + hydrogen_cyanide -> thiocyanic_acid
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("thiocyanic_acid"))
                .inputItems(dust, Sulfur)
                .inputFluids(HydrogenCyanide.getFluid(1000))
                .outputFluids(NewExplosivesProductionMaterials.THIOCYANIC_ACID.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 5. Zr-Hf separation mix: thiocyanic_acid + methyl_isobutyl_ketone -> zr_hf_separation_mix
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("zr_hf_separation_mix"))
                .inputFluids(NewExplosivesProductionMaterials.THIOCYANIC_ACID.getFluid(1000))
                .inputFluids(NewExplosivesProductionMaterials.METHYL_ISOBUTYL_KETONE.getFluid(1000))
                .outputFluids(ZrHfSeparationMaterials.ZR_HF_SEPARATION_MIX.getFluid(1000))
                .EUt(30).duration(200)
                .save(provider);

        // 6. Zr-Hf chloride: zircon_dust + chlorine -> zr_hf_chloride + zircon_chlorinating_residue
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("zr_hf_chloride"))
                .inputItems(dust, Zircon)
                .inputFluids(Chlorine.getFluid(4000))
                .outputFluids(ZrHfSeparationMaterials.ZR_HF_CHLORIDE.getFluid(1000))
                .outputFluids(ZrHfSeparationMaterials.ZIRCON_CHLORINATING_RESIDUE.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 7. Silicon chloride: zircon_chlorinating_residue -> silicon_chloride + chancedOutput(cobalt_dust) + chancedOutput(rare_earth_dust)
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("silicon_chloride"))
                .inputFluids(ZrHfSeparationMaterials.ZIRCON_CHLORINATING_RESIDUE.getFluid(1000))
                .outputFluids(NewExplosivesProductionMaterials.SILICON_CHLORIDE.getFluid(1000))
                .chancedOutput(dust, Cobalt, 2500, 500)
                .chancedOutput(dust, RareEarth, 1500, 500)
                .EUt(120).duration(200)
                .save(provider);

        // 8. Zr-Hf oxy chlorides: water + zr_hf_chloride -> zr_hf_oxy_chloride + hydrochloric_acid
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("zr_hf_oxy_chlorides"))
                .inputFluids(Water.getFluid(1000))
                .inputFluids(ZrHfSeparationMaterials.ZR_HF_CHLORIDE.getFluid(1000))
                .outputFluids(ZrHfSeparationMaterials.ZR_HF_OXY_CHLORIDE.getFluid(1000))
                .outputFluids(HydrochloricAcid.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 9. Cubic zirconia: hydrogen_peroxide + zr_hf_oxy_chloride + sulfur_trioxide + ammonium_chloride + notConsumableFluid(zr_hf_separation_mix) -> ammonium_sulfate + hydrochloric_acid + cubic_zirconia_dust + chancedOutput(hafnium_oxide_dust)
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("cubic_zirconia"))
                .inputFluids(HydrogenPeroxide.getFluid(1000))
                .inputFluids(ZrHfSeparationMaterials.ZR_HF_OXY_CHLORIDE.getFluid(1000))
                .inputFluids(SulfurTrioxide.getFluid(1000))
                .inputFluids(AmmoniumChloride.getFluid(1000))
                .notConsumableFluid(ZrHfSeparationMaterials.ZR_HF_SEPARATION_MIX.getFluid(1000))
                .outputFluids(NewExplosivesProductionMaterials.AMMONIUM_SULFATE.getFluid(1000))
                .outputFluids(HydrochloricAcid.getFluid(2000))
                .outputItems(dust, ZrHfSeparationMaterials.CUBIC_ZIRCONIA)
                .chancedOutput(dust, ZrHfSeparationMaterials.HAFNIUM_OXIDE, 5000, 500)
                .EUt(480).duration(200)
                .save(provider);

        // 10. Zirconium tetrachloride: carbon_dust + cubic_zirconia_dust + chlorine -> carbon_dioxide + zirconium_tetrachloride_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("zirconium_tetrachloride"))
                .inputItems(dust, Carbon)
                .inputItems(dust, ZrHfSeparationMaterials.CUBIC_ZIRCONIA)
                .inputFluids(Chlorine.getFluid(4000))
                .outputFluids(CarbonDioxide.getFluid(1000))
                .outputItems(dust, zirconiumTetrachloride)
                .EUt(120).duration(200)
                .save(provider);

        // 11. Zirconium dust: zirconium_tetrachloride_dust + magnesium_dust -> zirconium_dust + magnesium_chloride_dust
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("zirconium_dust"))
                .inputItems(dust, zirconiumTetrachloride)
                .inputItems(dust, Magnesium)
                .outputItems(dust, Zirconium)
                .outputItems(dust, MagnesiumChloride)
                .EUt(480).duration(200)
                .blastFurnaceTemp(1700)
                .save(provider);

        // 12. Hafnium tetrachloride: carbon_dust + hafnium_oxide_dust + chlorine -> carbon_dioxide + hafnium_chloride_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("hafnium_tetrachloride"))
                .inputItems(dust, Carbon)
                .inputItems(dust, ZrHfSeparationMaterials.HAFNIUM_OXIDE)
                .inputFluids(Chlorine.getFluid(4000))
                .outputFluids(CarbonDioxide.getFluid(1000))
                .outputItems(dust, ZrHfSeparationMaterials.HAFNIUM_CHLORIDE)
                .EUt(120).duration(200)
                .save(provider);

        // 13. Hafnium dust: hafnium_chloride_dust + magnesium_dust -> hafnium_dust + magnesium_chloride_dust
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("hafnium_dust"))
                .inputItems(dust, ZrHfSeparationMaterials.HAFNIUM_CHLORIDE)
                .inputItems(dust, Magnesium)
                .outputItems(dust, Hafnium)
                .outputItems(dust, MagnesiumChloride)
                .EUt(480).duration(200)
                .blastFurnaceTemp(1700)
                .save(provider);

        // =============== Silicon Chain ================
        // 从 SiliconChain.js 迁移

        // Zeolite 电解替换输出：aluminium_dust -> alumina_dust, silicon_dust -> silicon_dioxide_dust
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("zeolite_electrolysis"))
                .EUt(60).duration(288)
                .inputItems(dust, Zeolite, 19)
                .outputItems(dust, Sodium, 2)
                .outputItems(dust, Alumina, 2)
                .outputItems(dust, SiliconDioxide, 3)
                .outputFluids(Oxygen.getFluid(10000))
                .outputFluids(Water.getFluid(2000))
                .save(provider);

        // 红石离心替换输出：silicon_dust -> silicon_dioxide_dust
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("decomposition_centrifuging__redstone"))
                .EUt(30).duration(1400)
                .inputItems(dust, Redstone, 10)
                .outputItems(dust, SiliconDioxide)
                .outputItems(dust, Pyrite)
                .outputItems(dust, Ruby)
                .outputItems(dust, Mercury, 3)
                .save(provider);

        // 钙铁榴石
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("andradite"))
                .EUt(60).duration(480)
                .inputItems(dust, Andradite, 20)
                .outputItems(dust, Iron, 2)
                .outputItems(dust, Calcium, 3)
                .outputItems(dust, SiliconDioxide, 9)
                .outputFluids(Oxygen.getFluid(6000))
                .save(provider);

        // 铁辉石
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("ferrosilite"))
                .EUt(60).duration(120)
                .inputItems(dust, Ferrosilite, 5)
                .outputItems(dust, Iron)
                .outputItems(dust, SiliconDioxide, 3)
                .outputFluids(Oxygen.getFluid(1000))
                .save(provider);

        // 钙辉石
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("wollastonite"))
                .EUt(60).duration(110)
                .inputItems(dust, CTNHMaterials.Wollastonite, 5)
                .outputItems(dust, Calcium)
                .outputItems(dust, SiliconDioxide, 3)
                .outputFluids(Oxygen.getFluid(1000))
                .save(provider);

        // 黑曜石
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("obsidian"))
                .EUt(60).duration(192)
                .inputItems(dust, Obsidian, 8)
                .outputItems(dust, Iron)
                .outputItems(dust, Magnesium)
                .outputItems(dust, SiliconDioxide, 6)
                .save(provider);

        // 滑石
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("talc"))
                .EUt(60).duration(378)
                .inputItems(dust, Talc, 21)
                .outputItems(dust, Magnesium, 3)
                .outputItems(dust, SiliconDioxide, 12)
                .outputFluids(Oxygen.getFluid(4000))
                .outputFluids(Hydrogen.getFluid(2000))
                .save(provider);

        // 皂石
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("soapstone"))
                .EUt(60).duration(378)
                .inputItems(dust, Soapstone, 21)
                .outputItems(dust, Magnesium, 3)
                .outputItems(dust, SiliconDioxide, 12)
                .outputFluids(Oxygen.getFluid(4000))
                .outputFluids(Hydrogen.getFluid(2000))
                .save(provider);

        // 膨润土
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("bentonite"))
                .EUt(60).duration(480)
                .inputItems(dust, Bentonite, 30)
                .outputItems(dust, Sodium)
                .outputItems(dust, Magnesium, 6)
                .outputItems(dust, SiliconDioxide, 36)
                .outputFluids(Water.getFluid(5000))
                .outputFluids(Hydrogen.getFluid(6000))
                .save(provider);

        // 石棉粉
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("asbestos"))
                .EUt(60).duration(252)
                .inputItems(dust, Asbestos, 18)
                .outputItems(dust, Magnesium, 3)
                .outputItems(dust, SiliconDioxide, 6)
                .outputFluids(Oxygen.getFluid(5000))
                .outputFluids(Hydrogen.getFluid(4000))
                .save(provider);

        // 钙铬榴石
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("uvarovite"))
                .EUt(360).duration(480)
                .inputItems(dust, Uvarovite, 20)
                .outputItems(dust, Calcium, 3)
                .outputItems(dust, Chromium, 2)
                .outputItems(dust, SiliconDioxide, 9)
                .outputFluids(Oxygen.getFluid(6000))
                .save(provider);

        // 漂白土
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("fullers_earth"))
                .EUt(60).duration(336)
                .inputItems(dust, FullersEarth, 21)
                .outputItems(dust, Magnesium)
                .outputItems(dust, SiliconDioxide, 12)
                .outputFluids(Oxygen.getFluid(3000))
                .outputFluids(Hydrogen.getFluid(1000))
                .outputFluids(Water.getFluid(4000))
                .save(provider);

        // 二氧化硅处理
        // 碳还原二氧化硅
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("silicon_dioxide_reduction"))
                .EUt(300).duration(200)
                .inputItems(dust, SiliconDioxide, 3)
                .inputItems(dust, Carbon, 2)
                .outputItems(dust, Silicon)
                .outputFluids(CarbonMonoxide.getFluid(2000))
                .save(provider);

        // 二氧化硅氯化
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("silicon_dioxide_chloride"))
                .EUt(30).duration(40)
                .inputItems(dust, SiliconDioxide, 3)
                .inputFluids(HydrochloricAcid.getFluid(4000))
                .outputFluids(NewExplosivesProductionMaterials.SILICON_CHLORIDE.getFluid(1000))
                .outputFluids(Water.getFluid(2000))
                .save(provider);

        // 钠还原四氯化硅
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("silicon_from_sodium"))
                .EUt(30).duration(40)
                .inputItems(dust, Sodium, 4)
                .inputFluids(NewExplosivesProductionMaterials.SILICON_CHLORIDE.getFluid(1000))
                .outputItems(dust, Salt, 8)
                .outputItems(dust, Silicon)
                .save(provider);

        // 钾还原四氯化硅
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("silicon_from_potassium"))
                .EUt(30).duration(40)
                .inputItems(dust, Potassium, 4)
                .inputFluids(NewExplosivesProductionMaterials.SILICON_CHLORIDE.getFluid(1000))
                .outputItems(dust, RockSalt, 8)
                .outputItems(dust, Silicon)
                .save(provider);

        // 钠还原四氟化硅
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("silicon_from_sodium2"))
                .EUt(30).duration(40)
                .inputItems(dust, Sodium, 4)
                .inputFluids(CTNHMaterials.siliconFluoride.getFluid(1000))
                .outputItems(dust, BauxiteProcessingMaterials.SODIUM_FLUORIDE, 8)
                .outputItems(dust, Silicon)
                .save(provider);

        // =============== Space Fabric ================
        // 从 SpaceFabric.js 迁移

        // 二甲基甲酰胺
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("dimethylformamide"))
                .inputFluids(CarbonMonoxide.getFluid(1000))
                .inputFluids(Dimethylamine.getFluid(1000))
                .outputFluids(NewExplosivesProductionMaterials.DIMETHYLFORMAMIDE.getFluid(1000))
                .duration(200).EUt(480)
                .save(provider);

        // 均苯四酸二酐
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("pyrometillic_dianhydride"))
                .inputFluids(Oxygen.getFluid(1500))
                .inputFluids(Toluene.getFluid(250))
                .outputFluids(PYROMETILLIC_DIANHYDRIDE.getFluid(250))
                .outputFluids(Water.getFluid(1500))
                .duration(400).EUt(480)
                .save(provider);

        // 对氨基二苯醚沉降物
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("oxydianiline_sludge"))
                .inputFluids(AminoPhenol.getFluid(1000))
                .inputFluids(Nitrochlorobenzene.getFluid(1000))
                .inputFluids(NewExplosivesProductionMaterials.DIMETHYLFORMAMIDE.getFluid(1000))
                .inputItems(dust, PotassiumCarbonate)
                .outputFluids(OXYDIANILINE_SLUDGE.getFluid(250))
                .outputFluids(Water.getFluid(1500))
                .duration(400).EUt(480)
                .save(provider);

        // 蒸馏对氨基二苯醚沉降物
        DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("distill_oxydianiline_sludge"))
                .inputFluids(OXYDIANILINE_SLUDGE.getFluid(1000))
                .outputFluids(NewExplosivesProductionMaterials.DIMETHYLFORMAMIDE.getFluid(1000))
                .outputFluids(OXYDIANILINE.getFluid(144))
                .duration(200).EUt(480)
                .save(provider);

        // 聚酰亚胺K
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("kapton_k"))
                .inputFluids(PYROMETILLIC_DIANHYDRIDE.getFluid(1000))
                .inputFluids(OXYDIANILINE.getFluid(1000))
                .outputFluids(KAPTON_K.getFluid(1000))
                .duration(400).EUt(480)
                .save(provider);

        // 溴化钴
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("cobalt_bromide"))
                .inputItems(dust, Cobalt)
                .inputFluids(Bromine.getFluid(1000))
                .inputFluids(AceticAcid.getFluid(1000))
                .outputFluids(COBALT_BROMIDE.getFluid(1000))
                .duration(60).EUt(480)
                .save(provider);

        // 溴化锰
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("manganese_bromide"))
                .inputItems(dust, Manganese)
                .inputFluids(Bromine.getFluid(1000))
                .inputFluids(AceticAcid.getFluid(1000))
                .outputFluids(MANGANESE_BROMIDE.getFluid(1000))
                .duration(60).EUt(480)
                .save(provider);

        // 乙酸锰
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("manganese_acetate"))
                .inputItems(dust, Manganese)
                .inputFluids(AceticAcid.getFluid(1000))
                .outputFluids(MANGANESE_ACETATE.getFluid(1000))
                .duration(60).EUt(480)
                .save(provider);

        // 氢溴酸
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("hydrobromic_acid"))
                .notConsumable(dust, Platinum)
                .inputFluids(Water.getFluid(1000))
                .inputFluids(Bromine.getFluid(1000))
                .inputFluids(Hydrogen.getFluid(1000))
                .outputFluids(NewExplosivesProductionMaterials.HYDROBROMIC_ACID.getFluid(1000))
                .duration(60).EUt(480)
                .save(provider);

        // 钴锰溴催化剂
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("co_mn_br_catalyst"))
                .inputFluids(MANGANESE_BROMIDE.getFluid(1000))
                .inputFluids(MANGANESE_ACETATE.getFluid(1000))
                .inputFluids(NewExplosivesProductionMaterials.HYDROBROMIC_ACID.getFluid(1000))
                .inputFluids(COBALT_BROMIDE.getFluid(1000))
                .outputFluids(CO_MN_BR_CATALYST.getFluid(4000))
                .duration(100).EUt(480)
                .save(provider);

        // 三氯甲苯
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("bis_trichloromethyl_benzene"))
                .inputFluids(Dimethylbenzene.getFluid(1000))
                .inputFluids(Chlorine.getFluid(12000))
                .outputFluids(BIS_TRICHLOROMETHYL_BENZENE.getFluid(1000))
                .outputFluids(HydrochloricAcid.getFluid(6000))
                .duration(60).EUt(480)
                .save(provider);

        // 对苯二酸
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("therephthalic_acid"))
                .inputFluids(Dimethylbenzene.getFluid(1000))
                .inputFluids(Oxygen.getFluid(2000))
                .inputFluids(CO_MN_BR_CATALYST.getFluid(1000))
                .outputFluids(TEREPHTHALIC_ACID.getFluid(1000))
                .outputFluids(Water.getFluid(1000))
                .duration(60).EUt(480)
                .save(provider);

        // 对苯二酰氯
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("terephthaloyl_chloride"))
                .inputFluids(BIS_TRICHLOROMETHYL_BENZENE.getFluid(1000))
                .inputFluids(TEREPHTHALIC_ACID.getFluid(1000))
                .outputFluids(TEREPHTHALOYL_CHLORIDE.getFluid(2000))
                .outputFluids(HydrochloricAcid.getFluid(1000))
                .duration(60).EUt(480)
                .save(provider);

        // 硝基苯胺
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("nitroaniline"))
                .inputFluids(Nitrochlorobenzene.getFluid(1000))
                .inputFluids(Ammonia.getFluid(2000))
                .outputFluids(NITROANILINE.getFluid(1000))
                .outputFluids(AmmoniumChloride.getFluid(1000))
                .duration(60).EUt(480)
                .save(provider);

        // 对苯二胺
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("para_phenylenediamine"))
                .inputFluids(NITROANILINE.getFluid(1000))
                .inputFluids(Hydrogen.getFluid(6000))
                .outputFluids(PARA_PHENYLENEDIAMINE.getFluid(1000))
                .outputFluids(Water.getFluid(3000))
                .duration(60).EUt(480)
                .save(provider);

        // 对芳纶
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("para_aramid"))
                .inputFluids(PARA_PHENYLENEDIAMINE.getFluid(1000))
                .inputFluids(TEREPHTHALOYL_CHLORIDE.getFluid(1000))
                .outputFluids(PARA_ARAMID.getFluid(1000))
                .outputFluids(HydrochloricAcid.getFluid(2000))
                .duration(200).EUt(480)
                .save(provider);

        // 太空织物
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("space_fabric"))
                .inputItems(ChemicalHelper.get(foil, Polytetrafluoroethylene, 4))
                .inputItems(ChemicalHelper.get(foil, PARA_ARAMID, 4))
                .inputItems(ChemicalHelper.get(foil, PolyphenyleneSulfide, 4))
                .inputFluids(FIBER_GLASS.getFluid(576))
                .outputItems(SPACE_FABRIC.asStack())
                .duration(100).EUt(480)
                .save(provider);

        // =============== Stone Dust Chain ================
        // 从 StonedustChain.js 迁移

        // 催化剂配方
        CTNHRecipeTypes.DIFFERENTIAL_CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("stone_dust_with_catalyst"))
                .inputItems(dust, Stone, 60)
                .notConsumable(STONE_PROCESS_CATALYST.asStack())
                .outputItems(ChemicalHelper.get(dustTiny, StonePowderMaterials.INERT_RESIDUES))
                .outputItems(ChemicalHelper.get(dustSmall, StonePowderMaterials.OXIDIZED_RESIDUES, 2))
                .outputItems(ChemicalHelper.get(dustSmall, StonePowderMaterials.HEAVY_OXIDIZED_RESIDUES, 2))
                .outputItems(ChemicalHelper.get(dustSmall, Magnetite))
                .inputFluids(HydrofluoricAcid.getFluid(12000))
                .outputFluids(BauxiteProcessingMaterials.RED_MUD.getFluid(75))
                .outputFluids(StonePowderMaterials.FLUOROSILICIC_ACID.getFluid(2000))
                .EUt(480).duration(200)
                .save(provider);

        // 产线配方
        // 搅拌：石头粉 + 氢氟酸 -> 污浊六氟硅酸
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("dirty_hexafluorosilicic_acid"))
                .inputItems(dust, Stone, 24)
                .inputFluids(HydrofluoricAcid.getFluid(6000))
                .outputFluids(StonePowderMaterials.DIRTY_HEXAFLUOROSILICIC_ACID.getFluid(3000))
                .EUt(100).duration(40)
                .save(provider);

        // 离心：污浊六氟硅酸 -> 稀释六氟硅酸 + 石头残渣
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("dilute_hexafluorosilicic_acid"))
                .inputFluids(StonePowderMaterials.DIRTY_HEXAFLUOROSILICIC_ACID.getFluid(3000))
                .outputFluids(StonePowderMaterials.DILUTE_HEXAFLUOROSILICIC_ACID.getFluid(3000))
                .outputItems(dust, StonePowderMaterials.STONE_RESIDUE, 12)
                .duration(40).EUt(100)
                .save(provider);

        // 蒸馏：稀释六氟硅酸 -> 水 + 氟硅酸
        DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("fluorosilicic_acid"))
                .inputFluids(StonePowderMaterials.DILUTE_HEXAFLUOROSILICIC_ACID.getFluid(3000))
                .outputFluids(Water.getFluid(2000))
                .outputFluids(StonePowderMaterials.FLUOROSILICIC_ACID.getFluid(1000))
                .duration(160).EUt(200)
                .save(provider);

        // 化学反应：石头残渣 -> 精良残渣 + 磁铁矿
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("uncommon_residues_dust"))
                .inputItems(dust, StonePowderMaterials.STONE_RESIDUE, 24)
                .outputItems(dust, StonePowderMaterials.UNCOMMON_RESIDUES)
                .outputItems(ChemicalHelper.get(dustSmall, Magnetite))
                .inputFluids(BauxiteProcessingMaterials.SODIUM_HYDROXIDE_SOLUTION.getFluid(1000))
                .outputFluids(BauxiteProcessingMaterials.SODIUM_HYDROXIDE_SOLUTION.getFluid(925))
                .outputFluids(BauxiteProcessingMaterials.RED_MUD.getFluid(75))
                .duration(40).EUt(100)
                .save(provider);

        // 冷冻机：氟 -> 液态氟
        VACUUM_RECIPES.recipeBuilder(CTNHCore.id("liquid_fluorine"))
                .inputFluids(Fluorine.getFluid(1000))
                .outputFluids(BiodieselFertileSoilMaterials.LIQUID_FLUORINE.getFluid(1000))
                .EUt(1920).duration(240)
                .save(provider);

        // 化学反应：液态氧 + 液态氟 -> 二氟化二氧
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("dioxygendifluoride"))
                .inputFluids(Oxygen.getFluid(FluidStorageKeys.LIQUID, 2000))
                .inputFluids(BiodieselFertileSoilMaterials.LIQUID_FLUORINE.getFluid(2000))
                .outputFluids(StonePowderMaterials.DIOXYGENDIFLUORIDE.getFluid(1000))
                .duration(80).EUt(200)
                .save(provider);

        // 化学反应：精良残渣 -> 待分离氧化金属残渣
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("partially_oxidized_residues_dust"))
                .inputItems(dust, StonePowderMaterials.UNCOMMON_RESIDUES)
                .outputItems(dust, StonePowderMaterials.PARTIALLY_OXIDIZED_RESIDUES)
                .inputFluids(StonePowderMaterials.DIOXYGENDIFLUORIDE.getFluid(1000))
                .duration(80).EUt(100)
                .save(provider);

        // 离心：待分离氧化金属残渣 -> 纯净残渣 + 氧化残渣溶液
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("oxidized_residual_solution"))
                .inputItems(dust, StonePowderMaterials.PARTIALLY_OXIDIZED_RESIDUES, 10)
                .outputItems(dust, StonePowderMaterials.INERT_RESIDUES)
                .inputFluids(DistilledWater.getFluid(10000))
                .outputFluids(StonePowderMaterials.OXIDIZED_RESIDUAL_SOLUTION.getFluid(10000))
                .duration(200).EUt(100)
                .save(provider);

        // 脱水机：氧化残渣溶液 -> 氧化残渣 + 重氧化残渣
        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder(CTNHCore.id("oxidized_residues_dust"))
                .outputItems(dust, StonePowderMaterials.OXIDIZED_RESIDUES)
                .outputItems(dust, StonePowderMaterials.HEAVY_OXIDIZED_RESIDUES)
                .inputFluids(StonePowderMaterials.OXIDIZED_RESIDUAL_SOLUTION.getFluid(2000))
                .duration(80).EUt(3000)
                .save(provider);

        // 电弧炉：氧化残渣 -> 金属残渣
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("metallic_residues_dust"))
                .inputItems(dust, StonePowderMaterials.OXIDIZED_RESIDUES, 10)
                .outputItems(dust, StonePowderMaterials.METALLIC_RESIDUES)
                .inputFluids(Hydrogen.getFluid(60000))
                .outputFluids(StonePowderMaterials.DILUTE_HYDROFLUORIC_ACID.getFluid(40000))
                .duration(1600).EUt(2000).blastFurnaceTemp(3500)
                .save(provider);

        // 电弧炉：重氧化残渣 -> 重金属残渣
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("heavy_metallic_residues_dust"))
                .inputItems(dust, StonePowderMaterials.HEAVY_OXIDIZED_RESIDUES, 10)
                .outputItems(dust, StonePowderMaterials.HEAVY_METALLIC_RESIDUES)
                .inputFluids(Hydrogen.getFluid(60000))
                .outputFluids(StonePowderMaterials.DILUTE_HYDROFLUORIC_ACID.getFluid(40000))
                .duration(1600).EUt(2000).blastFurnaceTemp(3500)
                .save(provider);

        // 蒸馏：稀氢氟酸 -> 水 + 氢氟酸
        DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("hydrofluoric_acid"))
                .inputFluids(StonePowderMaterials.DILUTE_HYDROFLUORIC_ACID.getFluid(2000))
                .outputFluids(Water.getFluid(1000))
                .outputFluids(HydrofluoricAcid.getFluid(1000))
                .duration(80).EUt(200)
                .save(provider);

        // 魔力转化器：金属残渣分离
        CMRecipeTypes.MANA_TRANSFORMER_RECIPES.recipeBuilder(CTNHCore.id("metallic_residues_dust_seperate"))
                .inputItems(dust, StonePowderMaterials.METALLIC_RESIDUES, 10)
                .outputItems(dust, StonePowderMaterials.DIAMAGNETIC_RESIDUES, 3)
                .outputItems(dust, StonePowderMaterials.PARAMAGNETIC_RESIDUES, 3)
                .outputItems(dust, StonePowderMaterials.FERROMAGNETIC_RESIDUES, 3)
                .outputItems(dust, StonePowderMaterials.UNCOMMON_RESIDUES)
                .duration(80).EUt(8000)
                .save(provider);

        // 魔力转化器：重金属残渣分离
        CMRecipeTypes.MANA_TRANSFORMER_RECIPES.recipeBuilder(CTNHCore.id("heavy_metallic_residues_dust_seperate"))
                .inputItems(dust, StonePowderMaterials.HEAVY_METALLIC_RESIDUES, 10)
                .outputItems(dust, StonePowderMaterials.HEAVY_DIAMAGNETIC_RESIDUES, 3)
                .outputItems(dust, StonePowderMaterials.HEAVY_PARAMAGNETIC_RESIDUES, 3)
                .outputItems(dust, StonePowderMaterials.HEAVY_FERROMAGNETIC_RESIDUES, 3)
                .outputItems(dust, StonePowderMaterials.EXOTIC_HEAVY_RESIDUES)
                .duration(80).EUt(8000)
                .save(provider);

        // 离心：铁磁性残渣
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("ferromagnetic_residues_dust"))
                .inputItems(dust, StonePowderMaterials.FERROMAGNETIC_RESIDUES, 6)
                .outputItems(ChemicalHelper.get(dustSmall, Iron))
                .outputItems(ChemicalHelper.get(dustSmall, Nickel))
                .outputItems(ChemicalHelper.get(dustSmall, Cobalt))
                .duration(100).EUt(3000)
                .save(provider);

        // 离心：抗磁性残渣
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("diamagnetic_residues_dust"))
                .inputItems(dust, StonePowderMaterials.DIAMAGNETIC_RESIDUES, 6)
                .outputItems(ChemicalHelper.get(dustSmall, Calcium))
                .outputItems(ChemicalHelper.get(dustSmall, Zinc))
                .outputItems(ChemicalHelper.get(dustSmall, Copper))
                .outputItems(ChemicalHelper.get(dustSmall, Gallium))
                .outputItems(ChemicalHelper.get(dustSmall, Beryllium))
                .outputItems(ChemicalHelper.get(dustSmall, Tin))
                .duration(100).EUt(3000)
                .save(provider);

        // 离心：顺磁性残渣
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("paramagnetic_residues_dust"))
                .inputItems(dust, StonePowderMaterials.PARAMAGNETIC_RESIDUES, 6)
                .outputItems(ChemicalHelper.get(dustSmall, Sodium))
                .outputItems(ChemicalHelper.get(dustSmall, Potassium))
                .outputItems(ChemicalHelper.get(dustSmall, Magnesium))
                .outputItems(ChemicalHelper.get(dustSmall, Titanium))
                .outputItems(ChemicalHelper.get(dustSmall, Vanadium))
                .outputItems(ChemicalHelper.get(dustSmall, Manganese))
                .duration(100).EUt(3000)
                .save(provider);

        // 离心：重顺磁性残渣
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("heavy_paramagnetic_residues_dust"))
                .inputItems(dust, StonePowderMaterials.HEAVY_PARAMAGNETIC_RESIDUES, 6)
                .outputItems(ChemicalHelper.get(dustSmall, Thorium))
                .outputItems(ChemicalHelper.get(dustSmall, Uranium238))
                .outputItems(ChemicalHelper.get(dustSmall, Tungsten))
                .outputItems(ChemicalHelper.get(dustSmall, Hafnium))
                .outputItems(ChemicalHelper.get(dustSmall, Tantalum))
                .outputItems(ChemicalHelper.get(dustSmall, Thallium))
                .duration(100).EUt(3000)
                .save(provider);

        // 离心：重抗磁性残渣
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("heavy_diamagnetic_residues_dust"))
                .inputItems(dust, StonePowderMaterials.HEAVY_DIAMAGNETIC_RESIDUES, 6)
                .outputItems(ChemicalHelper.get(dustSmall, Lead))
                .outputItems(ChemicalHelper.get(dustSmall, Cadmium))
                .outputItems(ChemicalHelper.get(dustSmall, Indium))
                .outputItems(ChemicalHelper.get(dustSmall, Gold))
                .outputItems(ChemicalHelper.get(dustSmall, Bismuth))
                .outputFluids(Mercury.getFluid(36))
                .duration(120).EUt(3000)
                .save(provider);

        // 离心：重铁磁性残渣
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("heavy_ferromagnetic_residues_dust"))
                .inputItems(dust, StonePowderMaterials.HEAVY_FERROMAGNETIC_RESIDUES, 6)
                .outputItems(ChemicalHelper.get(dustSmall, Dysprosium))
                .duration(120).EUt(3000)
                .save(provider);

        // 大型化学反应：清洗纯净残渣
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("clean_inert_residues"))
                .inputItems(dust, StonePowderMaterials.INERT_RESIDUES, 10)
                .notConsumableFluid(FluoroantimonicAcid.getFluid(1000))
                .outputItems(dust, StonePowderMaterials.CLEAN_INERT_RESIDUES, 10)
                .outputItems(dust, NaquadahMaterials.NaquadahOxideMixture)
                .duration(320).EUt(200)
                .save(provider);

        // =============== Rare Earth Chain ================
        // 从 RareearthChain.js 迁移

        // 研磨：稀土 -> 含铁稀土 + 石粉
        MACERATOR_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_fe_one"))
                .inputItems(dust, RareEarth, 32)
                .outputItems(dust, RareEarthMaterials.RARE_EARTH_FE_ONE, 32)
                .outputItems(dust, Stone, 12)
                .EUt(480).duration(60)
                .save(provider);

        // 电磁分选：含铁稀土 -> 含铁精磨稀土 + 磁性铁
        ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_fe_two"))
                .inputItems(dust, RareEarthMaterials.RARE_EARTH_FE_ONE, 32)
                .outputItems(dust, RareEarthMaterials.RARE_EARTH_FE_TWO, 16)
                .outputItems(dust, IronMagnetic, 32)
                .EUt(480).duration(240)
                .save(provider);

        // 化学浸洗：含铁精磨稀土 + 盐酸 -> 精磨稀土 + 三氯化铁
        CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_intensive_research"))
                .inputItems(dust, RareEarthMaterials.RARE_EARTH_FE_TWO, 16)
                .inputFluids(HydrochloricAcid.getFluid(9000))
                .outputItems(dust, RareEarthMaterials.RARE_EARTH_INTENSIVE_RESEARCH, 12)
                .outputFluids(Iron3Chloride.getFluid(3000))
                .duration(200).EUt(480)
                .save(provider);

        // 搅拌：精磨稀土 + 独居石 + 氟碳铈矿 -> 稀土混合物
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_mixture"))
                .inputItems(dust, RareEarthMaterials.RARE_EARTH_INTENSIVE_RESEARCH)
                .inputItems(dust, Bastnasite)
                .inputItems(dust, Monazite)
                .outputItems(dust, RareEarthMaterials.RARE_EARTH_MIXTURE, 3)
                .EUt(480).duration(360)
                .save(provider);

        // 搅拌：稀土混合物 + 氢氧化钠 + 水 -> 碱式稀土混合物
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_mixture_oh"))
                .circuitMeta(1)
                .inputItems(dust, RareEarthMaterials.RARE_EARTH_MIXTURE, 4)
                .inputItems(dust, SodiumHydroxide, 4)
                .inputFluids(Water.getFluid(4000))
                .outputFluids(RareEarthMaterials.RARE_EARTH_MIXTURE_OH.getFluid(1000))
                .EUt(480).duration(480)
                .save(provider);

        // 化学反应：碱式稀土混合物 + 盐酸 -> 稀土氯化物溶液 + 盐
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_chloride_solution"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_MIXTURE_OH.getFluid(1000))
                .inputFluids(HydrochloricAcid.getFluid(6000))
                .outputFluids(BauxiteProcessingMaterials.RARE_EARTH_CHLORIDE_SOLUTION.getFluid(6000))
                .outputItems(dust, Salt, 8)
                .EUt(480).duration(120)
                .save(provider);

        // 流体加热器：稀土氯化物溶液 -> 沸腾稀土氯化物
        FLUID_HEATER_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_chloride_boil"))
                .inputFluids(BauxiteProcessingMaterials.RARE_EARTH_CHLORIDE_SOLUTION.getFluid(3000))
                .outputFluids(RareEarthMaterials.RARE_EARTH_CHLORIDE_BOIL.getFluid(3000))
                .EUt(120).duration(200)
                .save(provider);

        // 结晶器：沸腾稀土氯化物 -> 稀土晶体 + 水
        CTNHRecipeTypes.CRYSTALLIZER.recipeBuilder(CTNHCore.id("rare_earth_crystals"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_CHLORIDE_BOIL.getFluid(3000))
                .outputItems(dust, RareEarthMaterials.RARE_EARTH_CRYSTALS, 4)
                .outputFluids(Water.getFluid(6000))
                .EUt(1920).duration(480).blastFurnaceTemp(4500)
                .save(provider);

        // 离子交换器：稀土晶体 -> 高/低亲和力稀土
        CTNHRecipeTypes.ION_EXCHANGER.recipeBuilder(CTNHCore.id("rare_earth_high_affinity"))
                .inputItems(dust, RareEarthMaterials.RARE_EARTH_CRYSTALS)
                .inputFluids(HydrochloricAcid.getFluid(1500))
                .outputFluids(RareEarthMaterials.RARE_EARTH_HIGH_AFFINITY.getFluid(1000))
                .outputFluids(RareEarthMaterials.RARE_EARTH_LOW_AFFINTY.getFluid(1000))
                .outputFluids(DilutedHydrochloricAcid.getFluid(4000))
                .outputFluids(BauxiteProcessingMaterials.RED_MUD.getFluid(1000))
                .EUt(1920).duration(960)
                .save(provider);

        // 离心：低亲和力稀土 -> 轻稀土 + 中稀土
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_low"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_LOW_AFFINTY.getFluid(1000))
                .outputItems(dust, RareEarthMaterials.RARE_EARTH_LOW, 4)
                .outputItems(dust, RareEarthMaterials.RARE_EARTH_MIDDLE, 2)
                .EUt(480).duration(800)
                .save(provider);

        // 离心：高亲和力稀土 -> 中稀土 + 重稀土
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_high"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_HIGH_AFFINITY.getFluid(1000))
                .outputItems(dust, RareEarthMaterials.RARE_EARTH_MIDDLE, 4)
                .outputItems(dust, RareEarthMaterials.RARE_EARTH_HIGH, 2)
                .EUt(1920).duration(800)
                .save(provider);

        // 化学浸洗：重稀土 + 氢氟酸 -> 氟浸没重稀土 + 铕
        CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_high_fluoride"))
                .inputItems(dust, RareEarthMaterials.RARE_EARTH_HIGH)
                .inputFluids(HydrofluoricAcid.getFluid(4000))
                .outputFluids(RareEarthMaterials.RARE_EARTH_HIGH_FLUORIDE.getFluid(1000))
                .outputItems(ChemicalHelper.get(dustTiny, Europium))
                .EUt(6144).duration(60)
                .save(provider);

        // 化学浸洗：中稀土 + 氢氟酸 -> 氟浸没中稀土 + 钐
        CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_middle_fluoride"))
                .inputItems(dust, RareEarthMaterials.RARE_EARTH_MIDDLE)
                .inputFluids(HydrofluoricAcid.getFluid(4000))
                .outputFluids(RareEarthMaterials.RARE_EARTH_MIDDLE_FLUORIDE.getFluid(1000))
                .outputItems(ChemicalHelper.get(dustTiny, Samarium))
                .EUt(1920).duration(60)
                .save(provider);

        // 化学浸洗：轻稀土 + 氢氟酸 -> 氟浸没轻稀土 + 钕
        CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_low_fluoride"))
                .inputItems(dust, RareEarthMaterials.RARE_EARTH_LOW)
                .inputFluids(HydrofluoricAcid.getFluid(4000))
                .outputFluids(RareEarthMaterials.RARE_EARTH_LOW_FLUORIDE.getFluid(1000))
                .outputItems(ChemicalHelper.get(dustTiny, Neodymium))
                .EUt(480).duration(60)
                .save(provider);

        // 真空烧结：氟浸没轻稀土 -> 蒸汽 + 氟
        CTNHRecipeTypes.VACUUM_SINTERING.recipeBuilder(CTNHCore.id("rare_earth_low_fluoride_steam"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_LOW_FLUORIDE.getFluid(1000))
                .outputFluids(RareEarthMaterials.RARE_EARTH_LOW_FLUORIDE_STEAM.getFluid(1000))
                .outputFluids(Fluorine.getFluid(4000))
                .EUt(480).duration(60).blastFurnaceTemp(4500)
                .save(provider);

        // 真空烧结：氟浸没重稀土 -> 蒸汽 + 氟
        CTNHRecipeTypes.VACUUM_SINTERING.recipeBuilder(CTNHCore.id("rare_earth_high_fluoride_steam"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_HIGH_FLUORIDE.getFluid(1000))
                .outputFluids(RareEarthMaterials.RARE_EARTH_HIGH_FLUORIDE_STEAM.getFluid(1000))
                .outputFluids(Fluorine.getFluid(4000))
                .EUt(6144).duration(60).blastFurnaceTemp(4500)
                .save(provider);

        // 真空烧结：氟浸没中稀土 -> 蒸汽 + 氟
        CTNHRecipeTypes.VACUUM_SINTERING.recipeBuilder(CTNHCore.id("rare_earth_middle_fluoride_steam"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_MIDDLE_FLUORIDE.getFluid(1000))
                .outputFluids(RareEarthMaterials.RARE_EARTH_MIDDLE_FLUORIDE_STEAM.getFluid(1000))
                .outputFluids(Fluorine.getFluid(4000))
                .EUt(1920).duration(60).blastFurnaceTemp(4500)
                .save(provider);

        // 冷凝分离：轻稀土蒸汽
        CTNHRecipeTypes.CONDENSING_DISCRETE.recipeBuilder(CTNHCore.id("lanthanum_cerium_praseodymium_neodymium_oxygen_mixture"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_LOW_FLUORIDE_STEAM.getFluid(1000))
                .outputItems(dust, RareEarthMaterials.LANTHANUM_CERIUM_PRASEODYMIUM_NEODYMIUM_OXYGEN_MIXTURE, 8)
                .outputItems(dust, RareEarthMaterials.EUROPIUM_GADOLINIUM_TERBIUM_DYSPROSIUM_OXYGEN_MIXTURE, 4)
                .outputItems(dust, RareEarthMaterials.YTTRIUM_HOLMIUM_ERBIUM_THULIUM_YTTERBIUM_OXYGEN_LUTETIUM_MIXTURE, 2)
                .EUt(6144).duration(240)
                .save(provider);

        // 冷凝分离：中稀土蒸汽
        CTNHRecipeTypes.CONDENSING_DISCRETE.recipeBuilder(CTNHCore.id("europium_gadolinium_terbium_dysprosium_oxygen_mixture"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_MIDDLE_FLUORIDE_STEAM.getFluid(1000))
                .outputItems(dust, RareEarthMaterials.LANTHANUM_CERIUM_PRASEODYMIUM_NEODYMIUM_OXYGEN_MIXTURE, 2)
                .outputItems(dust, RareEarthMaterials.EUROPIUM_GADOLINIUM_TERBIUM_DYSPROSIUM_OXYGEN_MIXTURE, 8)
                .outputItems(dust, RareEarthMaterials.YTTRIUM_HOLMIUM_ERBIUM_THULIUM_YTTERBIUM_OXYGEN_LUTETIUM_MIXTURE, 4)
                .EUt(6144).duration(240)
                .save(provider);

        // 冷凝分离：重稀土蒸汽
        CTNHRecipeTypes.CONDENSING_DISCRETE.recipeBuilder(CTNHCore.id("yttrium_holmium_erbium_thulium_ytterbium_oxygen_lutetium_mixture"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_HIGH_FLUORIDE_STEAM.getFluid(1000))
                .outputItems(dust, RareEarthMaterials.LANTHANUM_CERIUM_PRASEODYMIUM_NEODYMIUM_OXYGEN_MIXTURE, 2)
                .outputItems(dust, RareEarthMaterials.EUROPIUM_GADOLINIUM_TERBIUM_DYSPROSIUM_OXYGEN_MIXTURE, 4)
                .outputItems(dust, RareEarthMaterials.YTTRIUM_HOLMIUM_ERBIUM_THULIUM_YTTERBIUM_OXYGEN_LUTETIUM_MIXTURE, 8)
                .EUt(6144).duration(240)
                .save(provider);

        // 化学反应：轻稀土氧化混合物 + 盐酸 -> 氯化物 + 水
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("lan_cer_pra_neo_chloride"))
                .inputItems(dust, RareEarthMaterials.LANTHANUM_CERIUM_PRASEODYMIUM_NEODYMIUM_OXYGEN_MIXTURE, 5)
                .inputFluids(HydrochloricAcid.getFluid(24000))
                .outputFluids(Water.getFluid(12000))
                .outputItems(dust, RareEarthMaterials.LAN_CER_PRA_NEO_CHLORIDE, 5)
                .EUt(6144).duration(120)
                .save(provider);

        // 化学反应：重稀土氧化混合物 + 盐酸 -> 氯化物 + 水
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("ytt_hol_erb_thu_ytt_chloride"))
                .inputItems(dust, RareEarthMaterials.YTTRIUM_HOLMIUM_ERBIUM_THULIUM_YTTERBIUM_OXYGEN_LUTETIUM_MIXTURE, 5)
                .inputFluids(HydrochloricAcid.getFluid(24000))
                .outputFluids(Water.getFluid(12000))
                .outputItems(dust, RareEarthMaterials.YTT_HOL_ERB_THU_YTT_CHLORIDE, 5)
                .EUt(6144).duration(480)
                .save(provider);

        // 化学反应：中稀土氧化混合物 + 盐酸 -> 氯化物 + 水
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("eur_gado_ter_dyspr_chloride"))
                .inputItems(dust, RareEarthMaterials.EUROPIUM_GADOLINIUM_TERBIUM_DYSPROSIUM_OXYGEN_MIXTURE, 5)
                .inputFluids(HydrochloricAcid.getFluid(24000))
                .outputFluids(Water.getFluid(12000))
                .outputItems(dust, RareEarthMaterials.EUR_GADO_TER_DYSPR_CHLORIDE, 5)
                .EUt(6144).duration(240)
                .save(provider);

        // 离心：轻稀土氯化物 -> 单质稀土 + 氯气
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("lanthanum_dust"))
                .inputItems(dust, RareEarthMaterials.LAN_CER_PRA_NEO_CHLORIDE, 5)
                .outputItems(dust, Lanthanum)
                .outputItems(dust, Cerium)
                .outputItems(dust, Praseodymium)
                .outputItems(dust, Neodymium)
                .outputItems(dust, Promethium)
                .outputFluids(Chlorine.getFluid(24000))
                .EUt(6144).duration(120).blastFurnaceTemp(5200)
                .save(provider);

        // 离心：中稀土氯化物 -> 单质稀土 + 氯气
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("samarium_dust"))
                .inputItems(dust, RareEarthMaterials.EUR_GADO_TER_DYSPR_CHLORIDE, 5)
                .outputItems(dust, Samarium)
                .outputItems(dust, Europium)
                .outputItems(dust, Gadolinium)
                .outputItems(dust, Terbium)
                .outputItems(dust, Dysprosium)
                .outputItems(dust, Holmium)
                .outputFluids(Chlorine.getFluid(24000))
                .EUt(6144).duration(120).blastFurnaceTemp(5200)
                .save(provider);

        // 离心：重稀土氯化物 -> 单质稀土 + 氯气
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("holmium_dust"))
                .inputItems(dust, RareEarthMaterials.YTT_HOL_ERB_THU_YTT_CHLORIDE, 5)
                .outputItems(dust, Erbium)
                .outputItems(dust, Thulium)
                .outputItems(dust, Ytterbium)
                .outputItems(dust, Lutetium)
                .outputItems(dust, Scandium)
                .outputItems(dust, Yttrium)
                .outputFluids(Chlorine.getFluid(24000))
                .EUt(6144).duration(120).blastFurnaceTemp(5200)
                .save(provider);

        // =============== Colorful SOC Chain ================
        // 从 ColorfulsocChain.js 迁移

        // color_ulv: 32x plastic_printed_circuit_board + colorful_soc + red_alloy_block + soldering_alloy_block + living_metal 72 -> 8192x nand_chip
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("color_ulv"))
                .inputItems(GTItems.PLASTIC_CIRCUIT_BOARD.asStack(32))
                .inputItems(COLORFUL_SOC.asStack())
                .inputItems(block, RedAlloy)
                .inputItems(block, SolderingAlloy)
                .inputFluids(LIVING_METAL.getFluid(72))
                .outputItems(GTItems.NAND_CHIP_ULV, 8192)
                .EUt(32678 * 16 * 4).duration(1)
                .save(provider);

        // color_mv: 128x plastic_printed_circuit_board + colorful_soc + red_alloy_block + annealed_copper_block + living_metal 72 -> 2048x micro_processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("color_mv"))
                .inputItems(GTItems.PLASTIC_CIRCUIT_BOARD.asStack(128))
                .inputItems(COLORFUL_SOC.asStack())
                .inputItems(block, RedAlloy)
                .inputItems(block, AnnealedCopper)
                .inputFluids(LIVING_METAL.getFluid(72))
                .outputItems(GTItems.PROCESSOR_MV, 2048)
                .EUt(32678 * 16 * 4).duration(1)
                .save(provider);

        // color_lv: 64x plastic_printed_circuit_board + colorful_soc + soldering_alloy_block + annealed_copper_block + living_metal 72 -> 4096x microchip_processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("color_lv"))
                .inputItems(GTItems.PLASTIC_CIRCUIT_BOARD.asStack(64))
                .inputItems(COLORFUL_SOC.asStack())
                .inputItems(block, SolderingAlloy)
                .inputItems(block, AnnealedCopper)
                .inputFluids(LIVING_METAL.getFluid(72))
                .outputItems(GTItems.MICROPROCESSOR_LV, 4096)
                .EUt(32678 * 16 * 4).duration(1)
                .save(provider);

        // color_hv: 32x epoxy_printed_circuit_board + colorful_soc + electrum_block + platinum_block + living_metal 144 -> 1024x nano_processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("color_hv"))
                .inputItems(GTItems.ADVANCED_CIRCUIT_BOARD.asStack(32))
                .inputItems(COLORFUL_SOC.asStack())
                .inputItems(block, Electrum)
                .inputItems(block, Platinum)
                .inputFluids(LIVING_METAL.getFluid(144))
                .outputItems(GTItems.NANO_PROCESSOR_HV, 1024)
                .EUt(32678 * 16 * 4).duration(1)
                .save(provider);

        // color_ev: 32x multilayer_fiber_reinforced_printed_circuit_board + colorful_soc + niobium_titanium_block + platinum_block + living_metal 288 -> 512x quantum_processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("color_ev"))
                .inputItems(GTItems.ELITE_CIRCUIT_BOARD.asStack(32))
                .inputItems(COLORFUL_SOC.asStack())
                .inputItems(block, NiobiumTitanium)
                .inputItems(block, Platinum)
                .inputFluids(LIVING_METAL.getFluid(288))
                .outputItems(GTItems.QUANTUM_PROCESSOR_EV, 512)
                .EUt(32678 * 16 * 4).duration(1)
                .save(provider);

        // color_iv: 12x multilayer_fiber_reinforced_printed_circuit_board + colorful_soc + niobium_titanium_block + yttrium_barium_cuprate_block + living_metal 288 -> 128x crystal_processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("color_iv"))
                .inputItems(GTItems.ELITE_CIRCUIT_BOARD.asStack(12))
                .inputItems(COLORFUL_SOC.asStack())
                .inputItems(block, NiobiumTitanium)
                .inputItems(block, YttriumBariumCuprate)
                .inputFluids(LIVING_METAL.getFluid(288))
                .outputItems(GTItems.CRYSTAL_PROCESSOR_IV, 128)
                .EUt(32678 * 16 * 4).duration(1)
                .save(provider);

        // color_luv: 8x wetware_printed_circuit_board + colorful_soc + naquadah_block + yttrium_barium_cuprate_block + living_metal 432 -> 64x wetware_processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("color_luv"))
                .inputItems(GTItems.WETWARE_CIRCUIT_BOARD.asStack(8))
                .inputItems(COLORFUL_SOC.asStack())
                .inputItems(block, Naquadah)
                .inputItems(block, YttriumBariumCuprate)
                .inputFluids(LIVING_METAL.getFluid(432))
                .outputItems(GTItems.WETWARE_PROCESSOR_LuV, 64)
                .EUt(32678 * 16 * 4).duration(1)
                .save(provider);

        // color_zpm: 4x echo_printed_circuit_board + colorful_soc + enriched_naquadah_trinium_europium_duranide_block + bedrock_neutronium_block + living_metal 864 -> 32x echo_processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("color_zpm"))
                .inputItems(ECHO_PRINTED_CIRCUIT_BOARD.asStack(4))
                .inputItems(COLORFUL_SOC.asStack())
                .inputItems(block, EnrichedNaquadahTriniumEuropiumDuranide)
                .inputItems(block, BedrockMaterials.BEDROCK_NEUTRONIUM)
                .inputFluids(LIVING_METAL.getFluid(864))
                .outputItems(ECHO_PROCESSOR, 32)
                .EUt(32678 * 16 * 4).duration(1)
                .save(provider);

        // colorful_gem: 5种精致宝石 -> 精致异彩宝石 (cwut 64)
        CTNHRecipeTypes.LS_RECIPE.recipeBuilder(CTNHCore.id("colorful_gem"))
                .inputItems(gemExquisite, Ruby)
                .inputItems(gemExquisite, Sapphire)
                .inputItems(gemExquisite, Emerald)
                .inputItems(gemExquisite, Topaz)
                .inputItems(gemExquisite, Zircon)
                .outputItems(gemExquisite, COLORFUL_GEM)
                .EUt(32678 * 16 * 4).duration(100)
                .addData("cwut", 64)
                .save(provider);

        // 精致异彩宝石 -> 相变棱晶SOC
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("exquisite_colorful_gem_gem"))
                .inputItems(gemExquisite, COLORFUL_GEM)
                .inputItems(gemExquisite, ArcaneCrystal)
                .inputItems(gemExquisite, BedrockMaterials.TUNGSTENCU_DIAMOND_PLATING)
                .inputItems(gemExquisite, Quartzite)
                .inputItems(gemExquisite, CertusQuartz)
                .inputItems(gemExquisite, Diamond)
                .inputItems(GTItems.CRYSTAL_SYSTEM_ON_CHIP.asStack())
                .inputItems(GTItems.HIGHLY_ADVANCED_SOC_WAFER.asStack())
                .inputFluids(MysteryFluid.getFluid(1000))
                .inputFluids(UncategorizedMaterials.QUANTUM_ALLOY.getFluid(1000))
                .inputFluids(BedrockMaterials.BEDROCK_NEUTRONIUM.getFluid(1000))
                .inputFluids(LIVING_METAL.getFluid(288))
                .outputItems(COLORFUL_SOC.asStack())
                .EUt(GTValues.VA[GTValues.UEV]).duration(2000)
                .save(provider);

        // ============== Recipe Replace Re-additions ==============

        // 1. endstone_separation (centrifuge): replaced platinum_dust with platinum_metal_dust
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("endstone_separation"))
                .inputItems(dust, Endstone, 12)
                .outputItems(dustSmall, GTMaterials.PlatinumRaw, 3)
                .chancedOutput(dustTiny, GTMaterials.Iridium, 2500, 500)
                .chancedOutput(dustTiny, GTMaterials.Osmium, 1500, 500)
                .EUt(30).duration(400)
                .save(provider);

        // 2. small_wooden_pipe (shaped): replaced planks with wood_plate
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("small_wooden_pipe"),
                ChemicalHelper.get(pipeSmallFluid, Wood, 2),
                "P", "P", "P",
                'P', ChemicalHelper.get(plate, Wood));

        // 3. hv_diode (shaped): replaced smd_diode with #gtceu:diodes tag
        {
            TagKey<Item> diodeTag = ItemTags.create(ResourceLocation.parse("gtceu:diodes"));
            VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("hv_diode"),
                    new ItemStack(GTMachines.DIODE[GTValues.HV].asStack().getItem()),
                    "CRd", "CRd", "CRd",
                    'C', ChemicalHelper.get(cableGtSingle, Platinum),
                    'R', ChemicalHelper.get(plate, Ruby),
                    'd', diodeTag);
        }
    }
}
