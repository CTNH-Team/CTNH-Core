package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.BedrockMaterials;
import io.github.cpearl0.ctnhcore.data.materials.UncategorizedMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTItems;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.block;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.gemExquisite;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLY_LINE_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.CIRCUIT_ASSEMBLER_RECIPES;
import static io.github.cpearl0.ctnhcore.registry.CTNHItems.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHItems.COLORFUL_SOC;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.*;

public class ColorfulsocChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // color_ulv: 32x plastic_printed_circuit_board + colorful_soc + red_alloy_block + soldering_alloy_block +
        // living_metal 72 -> 8192x nand_chip
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("color_ulv"))
                .inputItems(GTItems.PLASTIC_CIRCUIT_BOARD.asStack(32))
                .inputItems(COLORFUL_SOC.asStack())
                .inputItems(block, RedAlloy)
                .inputItems(block, SolderingAlloy)
                .inputFluids(LIVING_METAL.getFluid(72))
                .outputItems(GTItems.NAND_CHIP_ULV, 8192)
                .EUt(32678 * 16 * 4).duration(1)
                .save(provider);

        // color_mv: 128x plastic_printed_circuit_board + colorful_soc + red_alloy_block + annealed_copper_block +
        // living_metal 72 -> 2048x micro_processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("color_mv"))
                .inputItems(GTItems.PLASTIC_CIRCUIT_BOARD.asStack(128))
                .inputItems(COLORFUL_SOC.asStack())
                .inputItems(block, RedAlloy)
                .inputItems(block, AnnealedCopper)
                .inputFluids(LIVING_METAL.getFluid(72))
                .outputItems(GTItems.PROCESSOR_MV, 2048)
                .EUt(32678 * 16 * 4).duration(1)
                .save(provider);

        // color_lv: 64x plastic_printed_circuit_board + colorful_soc + soldering_alloy_block + annealed_copper_block +
        // living_metal 72 -> 4096x microchip_processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("color_lv"))
                .inputItems(GTItems.PLASTIC_CIRCUIT_BOARD.asStack(64))
                .inputItems(COLORFUL_SOC.asStack())
                .inputItems(block, SolderingAlloy)
                .inputItems(block, AnnealedCopper)
                .inputFluids(LIVING_METAL.getFluid(72))
                .outputItems(GTItems.MICROPROCESSOR_LV, 4096)
                .EUt(32678 * 16 * 4).duration(1)
                .save(provider);

        // color_hv: 32x epoxy_printed_circuit_board + colorful_soc + electrum_block + platinum_block + living_metal 144
        // -> 1024x nano_processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("color_hv"))
                .inputItems(GTItems.ADVANCED_CIRCUIT_BOARD.asStack(32))
                .inputItems(COLORFUL_SOC.asStack())
                .inputItems(block, Electrum)
                .inputItems(block, Platinum)
                .inputFluids(LIVING_METAL.getFluid(144))
                .outputItems(GTItems.NANO_PROCESSOR_HV, 1024)
                .EUt(32678 * 16 * 4).duration(1)
                .save(provider);

        // color_ev: 32x multilayer_fiber_reinforced_printed_circuit_board + colorful_soc + niobium_titanium_block +
        // platinum_block + living_metal 288 -> 512x quantum_processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("color_ev"))
                .inputItems(GTItems.ELITE_CIRCUIT_BOARD.asStack(32))
                .inputItems(COLORFUL_SOC.asStack())
                .inputItems(block, NiobiumTitanium)
                .inputItems(block, Platinum)
                .inputFluids(LIVING_METAL.getFluid(288))
                .outputItems(GTItems.QUANTUM_PROCESSOR_EV, 512)
                .EUt(32678 * 16 * 4).duration(1)
                .save(provider);

        // color_iv: 12x multilayer_fiber_reinforced_printed_circuit_board + colorful_soc + niobium_titanium_block +
        // yttrium_barium_cuprate_block + living_metal 288 -> 128x crystal_processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("color_iv"))
                .inputItems(GTItems.ELITE_CIRCUIT_BOARD.asStack(12))
                .inputItems(COLORFUL_SOC.asStack())
                .inputItems(block, NiobiumTitanium)
                .inputItems(block, YttriumBariumCuprate)
                .inputFluids(LIVING_METAL.getFluid(288))
                .outputItems(GTItems.CRYSTAL_PROCESSOR_IV, 128)
                .EUt(32678 * 16 * 4).duration(1)
                .save(provider);

        // color_luv: 8x wetware_printed_circuit_board + colorful_soc + naquadah_block + yttrium_barium_cuprate_block +
        // living_metal 432 -> 64x wetware_processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("color_luv"))
                .inputItems(GTItems.WETWARE_CIRCUIT_BOARD.asStack(8))
                .inputItems(COLORFUL_SOC.asStack())
                .inputItems(block, Naquadah)
                .inputItems(block, YttriumBariumCuprate)
                .inputFluids(LIVING_METAL.getFluid(432))
                .outputItems(GTItems.WETWARE_PROCESSOR_LuV, 64)
                .EUt(32678 * 16 * 4).duration(1)
                .save(provider);

        // color_zpm: 4x echo_printed_circuit_board + colorful_soc + enriched_naquadah_trinium_europium_duranide_block +
        // bedrock_neutronium_block + living_metal 864 -> 32x echo_processor
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
    }
}
