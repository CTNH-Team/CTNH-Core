package io.github.cpearl0.ctnhcore.data.recipe;

import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import appeng.core.definitions.AEParts;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.machines.GTMultiMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.GTCraftingComponents;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.moguang.ctnhbio.registry.CBItems;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.item.CrystalItems;
import io.github.cpearl0.ctnhcore.data.materials.SpecialMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import tech.luckyblock.mcmod.ctnhenergy.common.item.DynamoCardItem;
import tech.luckyblock.mcmod.ctnhenergy.registry.CEItems;
import tech.luckyblock.mcmod.ctnhenergy.registry.CEMachines;
import tech.luckyblock.mcmod.ctnhenergy.registry.CEMultiblock;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.foil;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.moguang.ctnhbio.data.materials.OrganicMaterials.Organic_Compound;
import static com.moguang.ctnhbio.registry.CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES;
import static io.github.cpearl0.ctnhcore.data.materials.SpecialMaterials.RESONANCE_CRYSTAL;
import static io.github.cpearl0.ctnhcore.data.materials.SpecialMaterials.STELLAR_ENERGY;
import static io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.starlight;

public class EUCellRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("me_pattern_buffer"))
                .inputItems(DUAL_IMPORT_HATCH[LuV], 1)
                .inputItems(EMITTER_LuV, 1)
                .inputItems(CustomTags.LuV_CIRCUITS, 4)
                .inputItems(AEBlocks.PATTERN_PROVIDER.asItem(), 3)
                .inputItems(AEBlocks.INTERFACE.asItem(), 3)
                .inputItems(AEItems.SPEED_CARD.asItem(), 4)
                .inputItems(AEItems.CAPACITY_CARD.asItem(), 2)
                .inputItems(wireFine, Europium, 64)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("pccard:card_programmed_circuit")), 2)
                .inputFluids(SolderingAlloy, L * 4)
                .inputFluids(Lubricant, 500)
                .outputItems(CEMachines.ME_PATTERN_BUFFER)
                .scannerResearch(b -> b
                        .researchStack(DUAL_IMPORT_HATCH[LuV].asStack())
                        .duration(3000)
                        .EUt(VA[IV]))
                .duration(600).EUt(VA[LuV]).save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("me_pattern_buffer_proxy"))
                .inputItems(HULL[LuV], 1)
                .inputItems(SENSOR_LuV, 2)
                .inputItems(CustomTags.LuV_CIRCUITS, 1)
                .inputItems(AEBlocks.QUANTUM_LINK.asItem(), 1)
                .inputItems(AEBlocks.QUANTUM_RING.asItem(), 2)
                .inputItems(wireFine, Europium, 64)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("pccard:card_programmed_circuit")), 2)
                .inputFluids(SolderingAlloy, L * 4)
                .inputFluids(Lubricant, 500)
                .outputItems(CEMachines.ME_PATTERN_BUFFER_PROXY)
                .scannerResearch(b -> b
                        .researchStack(CEMachines.ME_PATTERN_BUFFER.asStack())
                        .duration(3000)
                        .EUt(VA[IV]))
                .duration(600).EUt(VA[LuV]).save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("me_tag_stocking_input_bus"))
                .inputItems(CEMachines.STOCKING_IMPORT_BUS_ME, 1)
                .inputItems(CustomTags.LuV_CIRCUITS, 4)
                .inputItems(CBItems.META_CORE, 4)
                .inputItems(AEItems.CELL_COMPONENT_64K.stack(),4)
                .inputItems(CONVEYOR_MODULE_LuV, 4)
                .inputItems(ROBOT_ARM_LuV, 4)
                .inputItems(AEItems.SPEED_CARD.stack(),8)
                .inputItems(AEItems.ENERGY_CARD.stack(),8)
                .inputItems(AEItems.CAPACITY_CARD.stack(),8)
                .inputItems(AEItems.REDSTONE_CARD.stack(),8)
                .inputItems(TAG_FILTER,8)
                .inputFluids(SolderingAlloy, L * 16)
                .inputFluids(Lubricant, 2000)
                .outputItems(CEMachines.TAG_STOCKING_IMPORT_BUS_ME)
                .scannerResearch(b -> b
                        .researchStack(CEMachines.STOCKING_IMPORT_BUS_ME.asStack())
                        .duration(6000)
                        .EUt(VA[IV]))
                .duration(600).EUt(VA[LuV]).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("me_stocking_import_bus"))
                .inputItems(ITEM_IMPORT_BUS[IV])
                .inputItems(AEParts.INTERFACE.stack(1))
                .inputItems(CONVEYOR_MODULE_IV)
                .inputItems(SENSOR_IV)
                .inputItems(AEItems.SPEED_CARD.stack(4))
                .outputItems(CEMachines.STOCKING_IMPORT_BUS_ME)
                .duration(300).EUt(VA[IV])
                .addMaterialInfo(true).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("me_substation_hatch"))
                .inputItems(ENERGY_INPUT_HATCH_4A[IV])
                .inputItems(ENERGY_OUTPUT_HATCH_4A[IV])
                .inputItems(AEBlocks.INTERFACE.asItem(),4)
                .inputItems(AEBlocks.PATTERN_PROVIDER.asItem(),4)
                .inputItems(EMITTER_IV,8)
                .inputItems(SENSOR_IV,8)
                .inputItems(wireGtSingle,SamariumIronArsenicOxide,64)
                .inputFluids(SolderingAlloy.getFluid(L*8))
                .outputItems(CEMachines.ME_SUBSTATION_HATCH)
                .duration(2000).EUt(VA[IV])
                .addMaterialInfo(true).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("superconducting_coil"))
                .inputItems(wireGtDouble,SamariumIronArsenicOxide,64)
                .inputItems(foil,NiobiumTitanium,64)
                .inputFluids(Trinium.getFluid(L*64))
                .outputItems(GTBlocks.SUPERCONDUCTING_COIL,1)
                .duration(100).EUt(VA[IV])
                .addMaterialInfo(true).save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("me_energy_input_hatch"))
                .inputItems(ENERGY_INPUT_HATCH_4A[UV],4)
                .inputItems(TRANSFORMER[UV],4)
                .inputItems(GTBlocks.FUSION_COIL,16)
                .inputItems(GTBlocks.SUPERCONDUCTING_COIL,16)
                .inputItems(CustomTags.UHV_CIRCUITS,2)
                .inputItems(CustomTags.UV_CIRCUITS,2)
                .inputItems(CustomTags.ZPM_CIRCUITS,2)
                .inputItems(CustomTags.LuV_CIRCUITS,2)
                .inputItems(wireGtDouble,SamariumIronArsenicOxide,64)
                .inputItems(wireGtDouble,IndiumTinBariumTitaniumCuprate,32)
                .inputItems(wireGtDouble,UraniumRhodiumDinaquadide,16)
                .inputItems(wireGtDouble,EnrichedNaquadahTriniumEuropiumDuranide,8)
                .inputItems(CEMachines.ME_SUBSTATION_HATCH,4)
                .inputItems(CBItems.OMNI_CORE,4)
                .inputItems(CTNHItems.QuarkCore,4)
                .inputItems(CTNHItems.PlateRadiationProtection,16)
                .inputFluids(CTNHMaterials.Cerrobase140.getFluid(L*16))
                .inputFluids(Neutronium.getFluid(L*16))
                .inputFluids(Organic_Compound.getFluid(L*16))
                .inputFluids(SpecialMaterials.ENERGY_ESSENCE_SECRETION.getFluid(L*16))
                .outputItems(CEMachines.ENERGY_INPUT_HATCH_ME)
                .stationResearch(b -> b.researchStack(ENERGY_INPUT_HATCH_4A[UV].asStack()).CWUt(64).EUt(VA[UV]))
                .duration(1200).EUt(VA[UV]).save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("me_energy_output_hatch"))
                .inputItems(ENERGY_OUTPUT_HATCH_4A[UV],4)
                .inputItems(TRANSFORMER[UV],4)
                .inputItems(GTBlocks.FUSION_COIL,16)
                .inputItems(GTBlocks.SUPERCONDUCTING_COIL,16)
                .inputItems(CustomTags.UHV_CIRCUITS,2)
                .inputItems(CustomTags.UV_CIRCUITS,2)
                .inputItems(CustomTags.ZPM_CIRCUITS,2)
                .inputItems(CustomTags.LuV_CIRCUITS,2)
                .inputItems(wireGtDouble,SamariumIronArsenicOxide,64)
                .inputItems(wireGtDouble,IndiumTinBariumTitaniumCuprate,32)
                .inputItems(wireGtDouble,UraniumRhodiumDinaquadide,16)
                .inputItems(wireGtDouble,EnrichedNaquadahTriniumEuropiumDuranide,8)
                .inputItems(CEMachines.ME_SUBSTATION_HATCH,4)
                .inputItems(CBItems.OMNI_CORE,4)
                .inputItems(CTNHItems.QuarkCore,4)
                .inputItems(CTNHItems.PlateRadiationProtection,16)
                .inputFluids(CTNHMaterials.Cerrobase140.getFluid(L*16))
                .inputFluids(Neutronium.getFluid(L*16))
                .inputFluids(Organic_Compound.getFluid(L*16))
                .inputFluids(SpecialMaterials.ENERGY_ESSENCE_SECRETION.getFluid(L*16))
                .outputItems(CEMachines.ENERGY_OUTPUT_HATCH_ME)
                .stationResearch(b -> b.researchStack(ENERGY_OUTPUT_HATCH_4A[UV].asStack()).CWUt(64).EUt(VA[UV]))
                .duration(1200).EUt(VA[UV]).save(provider);

        AUTOCLAVE_RECIPES.recipeBuilder(CTNHCore.id("pure_certus_energy_crystal_autoclave"))
                .inputItems(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.stack(),1)
                .inputItems(ENERGIUM_DUST,63)
                .inputFluids(STELLAR_ENERGY.getFluid(144))
                .chancedOutput(CrystalItems.PURE_CERTUS_ENERGIUM_CRYSTAL.asStack(),2500,250)
                .duration(2400).EUt(VA[EV]).save(provider);
        CRYSTALLIZER.recipeBuilder(CTNHCore.id("pure_certus_energy_crystal_crystallizer"))
                .inputItems(CrystalItems.ENERGY_CRYSTAL_GRANULE,1)
                .inputItems(ENERGIUM_DUST,62)
                .inputItems(dust,NetherStar,10)
                .inputFluids(STELLAR_ENERGY.getFluid(288))
                .chancedOutput(CrystalItems.PURE_CERTUS_ENERGIUM_CRYSTAL.asStack(),7500,500)
                .blastFurnaceTemp(3200)
                .duration(2400).EUt(VA[IV]).save(provider);
        FORGE_HAMMER_RECIPES.recipeBuilder(CTNHCore.id("pure_certus_energy_crystal_forge_hammer"))
                .inputItems(CrystalItems.PURE_CERTUS_ENERGIUM_CRYSTAL,1)
                .outputItems(CrystalItems.ENERGY_CRYSTAL_GRANULE,8)
                .duration(200).EUt(VA[IV]).save(provider);

        AUTOCLAVE_RECIPES.recipeBuilder(CTNHCore.id("pure_certus_lapotron_crystal_autoclave"))
                .inputItems(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.stack(),1)
                .inputItems(dust,Lapotron,126)
                .inputFluids(STELLAR_ENERGY.getFluid(288))
                .chancedOutput(CrystalItems.PURE_CERTUS_LAPOTRON_CRYSTAL.asStack(),2000,500)
                .duration(4800).EUt(VA[IV]).save(provider);
        CRYSTALLIZER.recipeBuilder(CTNHCore.id("pure_certus_lapotron_crystal_crystallizer"))
                .inputItems(CrystalItems.LAPOTRON_CRYSTAL_GRANULE,1)
                .inputItems(dust,Lapotron,125)
                .inputItems(dust,NetherStar,20)
                .inputFluids(STELLAR_ENERGY.getFluid(288))
                .chancedOutput(CrystalItems.PURE_CERTUS_LAPOTRON_CRYSTAL.asStack(),7000,500)
                .blastFurnaceTemp(5100)
                .duration(4800).EUt(VA[LuV]).save(provider);
        FORGE_HAMMER_RECIPES.recipeBuilder(CTNHCore.id("pure_certus_lapotron_crystal_forge_hammer"))
                .inputItems(CrystalItems.PURE_CERTUS_LAPOTRON_CRYSTAL,1)
                .outputItems(CrystalItems.LAPOTRON_CRYSTAL_GRANULE,6)
                .duration(400).EUt(VA[LuV]).save(provider);

        MIXER_RECIPES.recipeBuilder(CTNHCore.id("resonance_crystal_dust"))
                .inputItems(dust,CTNHMaterials.LightningPattern,5)
                .inputItems(dust, Amethyst,5)
                .inputItems(ENERGIUM_DUST,5)
                .inputItems(dust,CTNHMaterials.Zanite,5)
                .inputItems(dust,CTNHMaterials.Ambrosium,5)
                .inputItems(dust,CTNHMaterials.Skyjade,5)
                .outputItems(dust, RESONANCE_CRYSTAL,30)
                .duration(1200).EUt(VA[IV]).save(provider);
        AUTOCLAVE_RECIPES.recipeBuilder(CTNHCore.id("resonance_crystal_autoclave"))
                .inputItems(dust, RESONANCE_CRYSTAL,60)
                .inputFluids(STELLAR_ENERGY.getFluid(576))
                .outputItems(CrystalItems.RESONANCE_CRYSTAL_BLANK,1)
                .duration(2400).EUt(VA[IV]).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("resonance_crystal_blank_assembler"))
                .inputItems(CrystalItems.RESONANCE_CRYSTAL_BLANK,1)
                .inputItems(CustomTags.EV_CIRCUITS,2)
                .outputItems(CrystalItems.RESONANCE_CRYSTAL,1)
                .duration(2400).EUt(VA[IV]).save(provider);
        AUTOCLAVE_RECIPES.recipeBuilder(CTNHCore.id("pure_certus_resonance_crystal_autoclave"))
                .inputItems(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.stack(),1)
                .inputItems(dust,RESONANCE_CRYSTAL,120)
                .inputFluids(STELLAR_ENERGY.getFluid(576))
                .chancedOutput(CrystalItems.PURE_CERTUS_RESONANCE_CRYSTAL.asStack(),1500,750)
                .duration(9600).EUt(VA[LuV]).save(provider);
        CRYSTALLIZER.recipeBuilder(CTNHCore.id("pure_certus_resonance_crystal_crystallizer"))
                .inputItems(CrystalItems.RESONANCE_CRYSTAL_GRANULE,1)
                .inputItems(dust,RESONANCE_CRYSTAL,120)
                .inputItems(dust,NetherStar,30)
                .inputFluids(STELLAR_ENERGY.getFluid(576))
                .chancedOutput(CrystalItems.PURE_CERTUS_RESONANCE_CRYSTAL.asStack(),6500,750)
                .blastFurnaceTemp(7200)
                .duration(9600).EUt(VA[ZPM]).save(provider);
        FORGE_HAMMER_RECIPES.recipeBuilder(CTNHCore.id("pure_certus_resonance_crystal_forge_hammer"))
                .inputItems(CrystalItems.PURE_CERTUS_RESONANCE_CRYSTAL,1)
                .outputItems(CrystalItems.RESONANCE_CRYSTAL_GRANULE,4)
                .duration(800).EUt(VA[ZPM]).save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("eu_cell_housing"))
                .inputItems(plateDense,RhodiumPlatedPalladium,4)
                .inputItems(plateDense,TungstenSteel,4)
                .inputItems(GTBlocks.FUSION_GLASS,2)
                .inputFluids(SpecialMaterials.ENERGY_ESSENCE_SECRETION.getFluid(144))
                .outputItems(CEItems.EU_CELL_HOUSING,1)
                .duration(400).EUt(VA[LuV]).save(provider);

        FERMENTING.recipeBuilder(CTNHCore.id("energy_essence_secretion_1"))
                .circuitMeta(1)
                .notConsumableFluid(STELLAR_ENERGY.getFluid(100))
                .inputFluids(Organic_Compound.getFluid(144))
                .outputFluids(SpecialMaterials.ENERGY_ESSENCE_SECRETION.getFluid(1000))
                .EUt(3600).duration(500).save(provider);
        FERMENTING.recipeBuilder(CTNHCore.id("energy_essence_secretion_2"))
                .circuitMeta(2)
                .notConsumableFluid(STELLAR_ENERGY.getFluid(1000))
                .inputFluids(Organic_Compound.getFluid(576))
                .outputFluids(SpecialMaterials.ENERGY_ESSENCE_SECRETION.getFluid(10000))
                .EUt(36000).duration(500).save(provider);
        FERMENTING.recipeBuilder(CTNHCore.id("energy_essence_secretion_3"))
                .circuitMeta(3)
                .notConsumableFluid(STELLAR_ENERGY.getFluid(10000))
                .inputFluids(Organic_Compound.getFluid(2304))
                .outputFluids(SpecialMaterials.ENERGY_ESSENCE_SECRETION.getFluid(100000))
                .EUt(360000).duration(500).save(provider);
        FERMENTING.recipeBuilder(CTNHCore.id("energy_essence_secretion_4"))
                .circuitMeta(4)
                .notConsumableFluid(STELLAR_ENERGY.getFluid(100000))
                .inputFluids(Organic_Compound.getFluid(4608))
                .outputFluids(SpecialMaterials.ENERGY_ESSENCE_SECRETION.getFluid(1000000))
                .EUt(3600000).duration(500).save(provider);
        FERMENTING.recipeBuilder(CTNHCore.id("energy_essence_secretion_5"))
                .circuitMeta(5)
                .notConsumableFluid(STELLAR_ENERGY.getFluid(1000000))
                .inputFluids(Organic_Compound.getFluid(4608*2))
                .outputFluids(SpecialMaterials.ENERGY_ESSENCE_SECRETION.getFluid(10000000))
                .EUt(36000000).duration(500).save(provider);

        DIFFERENTIAL_CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("stellar_energy_produce"))
                .inputFluids(starlight.getFluid(1000))
                .outputFluidsRanged(new FluidStack(STELLAR_ENERGY.getFluid(), 1), UniformInt.of(2, 4))
                .EUt(VA[EV]).duration(1000).save(provider);

        for(var tier: tiersBetween(LV, OpV)){

            CULTIVATION_ROOM.recipeBuilder(CTNHCore.id("stellar_energy" + VN[tier].toLowerCase()))
                    .inputItems(CustomTags.CIRCUITS_ARRAY[tier],10)
                    .inputFluids(STELLAR_ENERGY.getFluid(144))
                    .outputFluidsRanged(new FluidStack(STELLAR_ENERGY.getFluid(), 100), UniformInt.of(144, 144 * (tier + 1)))
                    .EUt(1920 * LV)
                    .duration(2000)
                    .save(provider);

            ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("dynamo_card_" + VN[tier].toLowerCase()))
                    .inputItems(AEItems.ADVANCED_CARD.stack())
                    .inputItems(AEItems.CAPACITY_CARD.stack())
                    .inputItems(AEItems.SPEED_CARD.stack())
                    .inputItems(AEItems.ENERGY_CARD.stack())
                    .inputItems(CustomTags.CIRCUITS_ARRAY[tier],3)
                    .inputItems(GTCraftingComponents.EMITTER.get(tier),4)
                    .inputItems(GTCraftingComponents.SENSOR.get(tier),4)
                    .inputFluids((CTNHCraftingComponents.GT_SUPERCONDUCTING_MATERIAL.getOrDefault(tier, Neutronium)).getFluid(L * 5))
                    .outputItems(DynamoCardItem.getInstanceByTier(tier),1)
                    .duration(600).EUt(VA[Math.max(tier, EV)])
                    .save(provider);

            var recipeType = tier >= LuV ? ASSEMBLY_LINE_RECIPES : BIOELECTRIC_FORGE_RECIPES;
            var euCellBuilder = recipeType.recipeBuilder(CTNHCore.id("eu_cell" + VN[tier].toLowerCase()))
                    .inputItems(CEItems.EU_CELL_HOUSING,1)
                    .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ae2:fluix_glass_cable")),16)
                    .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ae2:fluix_smart_cable")),16)
                    .inputItems(DynamoCardItem.getInstanceByTier(tier))
                    .inputItems(CustomTags.CIRCUITS_ARRAY[tier],4)
                    .inputItems(CustomTags.CIRCUITS_ARRAY[tier + 1],4)
                    .inputItems(GTCraftingComponents.VOLTAGE_COIL.get(tier),4)
                    .inputFluids(SpecialMaterials.ENERGY_ESSENCE_SECRETION.getFluid(5760))
                    .outputItems(CEItems.EU_CELL[tier])
                    .duration(2400)
                    .EUt(VA[Math.max(tier, EV)]);

            if(tier >= IV) euCellBuilder.inputItems(GTBlocks.SUPERCONDUCTING_COIL,4);

            if (tier >= LuV) {
                euCellBuilder.inputItems(GTMultiMachines.ACTIVE_TRANSFORMER.asStack(),1)
                        .inputItems(CEMultiblock.POWER_SUBSTATION.asStack(),1)
                        .inputItems(CEMachines.ME_SUBSTATION_HATCH.asStack(),1)
                        .inputItems(CrystalItems.PURE_CERTUS_ENERGIUM_CRYSTAL,1);

                var researchStack = CEItems.EU_CELL[tier - 1].asStack();
                euCellBuilder.stationResearch(b -> b.researchStack(researchStack).CWUt(1 << (tier - IV)).EUt(VA[UV]));

                if(tier >= UV){
                    euCellBuilder.inputItems(CrystalItems.PURE_CERTUS_RESONANCE_CRYSTAL, 1);
                }

                if (tier >= UIV){
                    euCellBuilder.inputItems(CrystalItems.PURE_CERTUS_ECHO_CRYSTAL, 1);
                }
            }

            euCellBuilder.save(provider);
        }

    }
}
