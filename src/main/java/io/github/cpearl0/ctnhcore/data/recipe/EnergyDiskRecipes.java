package io.github.cpearl0.ctnhcore.data.recipe;

import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import appeng.core.definitions.AEParts;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.moguang.ctnhbio.data.materials.OrganicMaterials;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhbio.registry.CBMaterials;
import io.github.cpearl0.ctnhcore.data.materials.SpecialMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import tech.luckyblock.mcmod.ctnhenergy.registry.CEItems;
import tech.luckyblock.mcmod.ctnhenergy.registry.CEMachines;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.foil;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLY_LINE_RECIPES;

public class EnergyDiskRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {

        ASSEMBLY_LINE_RECIPES.recipeBuilder("me_pattern_buffer_ctnh")
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

        ASSEMBLY_LINE_RECIPES.recipeBuilder("me_pattern_buffer_proxy_ctnh")
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

        ASSEMBLY_LINE_RECIPES.recipeBuilder("me_tag_stocking_input_bus_ctnh")
                .inputItems(CEMachines.STOCKING_IMPORT_BUS_ME, 1)
                .inputItems(CustomTags.LuV_CIRCUITS, 4)
                .inputItems(CBItems.META_CORE, 4)
                .inputItems(AEItems.CELL_COMPONENT_64K,4)
                .inputItems(CONVEYOR_MODULE_LuV, 4)
                .inputItems(ROBOT_ARM_LuV, 4)
                .inputItems(AEItems.SPEED_CARD,8)
                .inputItems(AEItems.ENERGY_CARD,8)
                .inputItems(AEItems.CAPACITY_CARD,8)
                .inputItems(AEItems.REDSTONE_CARD,8)
                .inputItems(TAG_FILTER,8)
                .inputFluids(SolderingAlloy, L * 16)
                .inputFluids(Lubricant, 2000)
                .outputItems(CEMachines.TAG_STOCKING_IMPORT_BUS_ME)
                .scannerResearch(b -> b
                        .researchStack(CEMachines.STOCKING_IMPORT_BUS_ME.asStack())
                        .duration(6000)
                        .EUt(VA[IV]))
                .duration(600).EUt(VA[LuV]).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("me_stocking_import_bus_ctnh")
                .inputItems(ITEM_IMPORT_BUS[IV])
                .inputItems(AEParts.INTERFACE.stack(1))
                .inputItems(CONVEYOR_MODULE_IV)
                .inputItems(SENSOR_IV)
                .inputItems(AEItems.SPEED_CARD.stack(4))
                .outputItems(CEMachines.STOCKING_IMPORT_BUS_ME)
                .duration(300).EUt(VA[IV])
                .addMaterialInfo(true).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("me_substation_hatch_ctnh")
                .inputItems(ENERGY_INPUT_HATCH_4A)
                .inputItems(ENERGY_OUTPUT_HATCH_4A)
                .inputItems(AEBlocks.INTERFACE,4)
                .inputItems(AEBlocks.PATTERN_PROVIDER,4)
                .inputItems(EMITTER_IV,8)
                .inputItems(SENSOR_IV,8)
                .inputItems(wireGtSingle,SamariumIronArsenicOxide,64)
                .inputFluids(SolderingAlloy.getFluid(L*8))
                .outputItems(CEMachines.ME_SUBSTATION_HATCH)
                .duration(2000).EUt(VA[IV])
                .addMaterialInfo(true).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("superconducting_coil_ctnh")
                .inputItems(wireGtDouble,SamariumIronArsenicOxide,64)
                .inputItems(foil,NiobiumTitanium,64)
                .inputFluids(Trinium.getFluid(L*64))
                .outputItems(GTBlocks.SUPERCONDUCTING_COIL,1)
                .duration(100).EUt(VA[IV])
                .addMaterialInfo(true).save(provider);

//        ASSEMBLER_RECIPES.recipeBuilder("dynamo_card_lv_ctnh")
//                .inputItems(AEItems.ADVANCED_CARD)
//                .inputItems(AEItems.CAPACITY_CARD)
//                .inputItems(AEItems.SPEED_CARD)
//                .inputItems(AEItems.ENERGY_CARD)
//                .inputItems(CustomTags.LV_CIRCUITS,3)
//                .inputItems(EMITTER_LV,4)
//                .inputItems(SENSOR_LV,4)
//                .inputItems(GTBlocks.SUPERCONDUCTING_COIL,1)
//                .inputFluids(Trinium.getFluid(L*LV))
//              .outputItems(CEItems.DYNAMO_CARD,1)  把LV改成各个电压的，除了ULV以外,ULV在下面被注释了
//                .duration(600).EUt(VA[LV])
//                .addMaterialInfo(true).save(provider);
//        VanillaRecipeHelper.addShapedRecipe(
//                provider, "dynamo_card_ulv_ctnh",
//                CEItems.DYNAMO_CARD,
//                "ABC", "DED", "FGH",
//                'A', AEItems.CAPACITY_CARD,
//                'B', EMITTER_LV,
//                'C', AEItems.SPEED_CARD,
//                'D', TREATED_WOOD_BOAT,
//                'E', GTBlocks.SUPERCONDUCTING_COIL,
//                'F', AEItems.ADVANCED_CARD,
//                'G', SENSOR_LV,
//                'H', AEItems.ENERGY_CARD);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("me_energy_input_hatch_ctnh")
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
                .inputFluids(OrganicMaterials.Organic_Compound.getFluid(L*16))
                .inputFluids(SpecialMaterials.ENERGY_ESSENCE_SECRETION.getFluid(L*16))
                .outputItems(CEMachines.ENERGY_INPUT_HATCH_ME)
                .stationResearch(b -> b.researchStack(ENERGY_INPUT_HATCH_4A[UV].asStack()).CWUt(64).EUt(VA[UV]))
                .duration(1200).EUt(VA[UV]).save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("me_energy_output_hatch_ctnh")
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
                .inputFluids(OrganicMaterials.Organic_Compound.getFluid(L*16))
                .inputFluids(SpecialMaterials.ENERGY_ESSENCE_SECRETION.getFluid(L*16))
                .outputItems(CEMachines.ENERGY_OUTPUT_HATCH_ME)
                .stationResearch(b -> b.researchStack(ENERGY_OUTPUT_HATCH_4A[UV].asStack()).CWUt(64).EUt(VA[UV]))
                .duration(1200).EUt(VA[UV]).save(provider);
    }
}
