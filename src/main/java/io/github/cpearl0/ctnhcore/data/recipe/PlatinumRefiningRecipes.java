package io.github.cpearl0.ctnhcore.data.recipe;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.ingot;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHMaterials.*;

public class PlatinumRefiningRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        CHEMICAL_RECIPES.recipeBuilder("precious_alloy_dissolution")
                .inputItems(ingot,PreciousAlloy, 4) // 4个贵金属锭
                .inputFluids(AquaRegia.getFluid(3000)) // 3000mB王水
                .outputFluids(AU_PD_PT_SOLUTION.getFluid(2000)) // 金铂钯溶液
                .outputItems(dust,INSOLUBLE_RESIDUE,1) // 不溶渣
                .duration(200).EUt(VA[HV]).save(provider);
        CHEMICAL_RECIPES.recipeBuilder("au_reduction_with_oxalic_acid")
                .inputFluids(AU_PD_PT_SOLUTION.getFluid(1000)) // 输入金铂钯溶液
                .inputFluids(OxalicAcid.getFluid(1000)) // 输入草酸
                .outputItems(dust,CRUDE_GOLD,1)                     // 产出粗金
                .outputFluids(NitrogenDioxide.getFluid(750)) // 产出二氧化氮-制硝酸
                .outputFluids(Pt_Pd_CHLORIDE_SOLUTION.getFluid(1000)) // 产出铂钯溶液
                .outputFluids(CarbonDioxide.getFluid(2000))  // 副产CO₂-制草酸
                .duration(200).EUt(VA[MV]).save(provider);
        CHEMICAL_RECIPES.recipeBuilder("oxalic_acid_recovery")
                .inputFluids(CarbonDioxide.getFluid(2000))  // 回收CO₂
                .inputFluids(Hydrogen.getFluid(2000))        // 补充H₂
                .outputFluids(OxalicAcid.getFluid(1000)) // 产出草酸
                .duration(300).EUt(VA[LV]).save(provider);
        CHEMICAL_RECIPES.recipeBuilder("platinum_precipitation")
                .inputFluids(Pt_Pd_CHLORIDE_SOLUTION.getFluid(1000)) // 输入铂钯溶液
                .inputItems(dust, AmmoniumChloride, 2)              // 消耗2 NH₄Cl
                .outputItems(dust,AMMONIUM_HEXACHLOROPLATINATE, 1)        // 产出铂铵盐 (黄色)
                .outputFluids(PALLADIUM_CHLORIDE_SOLUTION.getFluid(800)) // 剩余钯溶液
                .duration(200).EUt(VA[HV]).save(provider);
        BLAST_RECIPES.recipeBuilder("platinum_reduction")
                .inputItems(dust, AMMONIUM_HEXACHLOROPLATINATE, 1)  // (NH₄)₂PtCl₆
                .inputFluids(Hydrogen.getFluid(200))     // 200mB H₂
                .outputItems(dust, PLATINUM_BLACK, 1)               // 铂黑
                .outputItems(dust, AmmoniumChloride, 1)            //回收一个氯化铵粉末
                .outputFluids(HydrochloricAcid.getFluid(500))      // HCl气体
                .blastFurnaceTemp(1200)                                    // 1200K氢气氛围
                .duration(300).EUt(VA[HV]).save(provider);
        ARC_FURNACE_RECIPES.recipeBuilder("platinum_black_refining")
                .inputItems(dust, PLATINUM_BLACK, 2)
                .outputItems(ingot, Platinum, 1)  // 标准铂锭
                .duration(100).EUt(VA[MV]).save(provider);
        CHEMICAL_RECIPES.recipeBuilder("palladium_ammonia_complex")
                .inputFluids(PALLADIUM_CHLORIDE_SOLUTION.getFluid(6400)) // H₂PdCl₄
                .inputFluids(Ammonia.getFluid(4000))            // 4NH₃·H₂O
                .outputItems(dust,DIAMMINE_PALLADIUM_CHLORIDE, 1)            // [Pd(NH₃)₂Cl₂]↓
                .outputFluids(AmmoniumChloride.getFluid(2000)) // 2NH₄Cl(aq)
                .duration(200).EUt(VA[MV]).save(provider);
        BLAST_RECIPES.recipeBuilder("palladium_hydrogen_reduction")
                .inputItems(dust, DIAMMINE_PALLADIUM_CHLORIDE, 1) // [Pd(NH₃)₂Cl₂]
                .inputFluids(Hydrogen.getFluid(2000))              // 2H₂
                .outputItems(dust, Palladium, 1)                  // Pd
                .outputFluids(AmmoniumChloride.getFluid(2000)) // 2NH₄Cl(aq)
                .blastFurnaceTemp(500)
                .duration(150).EUt(VA[HV]).save(provider);
        BLAST_RECIPES.recipeBuilder("osmium_volatilization")
                .inputItems(dust, INSOLUBLE_RESIDUE, 1)
                .inputFluids(Oxygen.getFluid(4000))
                .outputFluids(OSMIUM_TETROXIDE_GAS.getFluid(1000)) // OsO₄(g)
                .outputItems(dust,RU_RH_IR_RESIDUE,1)        // 剩余渣
                .outputItems(dust,Massicot,1)
                .outputItems(dust,SILVER_CHLORIDE,1)
                .blastFurnaceTemp(800)
                .duration(300).EUt(VA[EV]).save(provider);
        CHEMICAL_RECIPES.recipeBuilder("osmium_tetroxide_neutralization")
                .inputFluids(OSMIUM_TETROXIDE_GAS.getFluid(1000)) // OsO₄
                .inputItems(dust, SodiumHydroxide, 16)
                .outputItems(dust, SodiumOsmate, 2) // Na₂[OsO₄(OH)₂]
                .duration(100).EUt(VA[HV]).save(provider);
        BLAST_RECIPES.recipeBuilder("sodium_osmate_carbon_reduction")
                .inputItems(dust, SodiumOsmate, 2)  // 2Na₂[OsO₄(OH)₂]
                .inputItems(dust, Carbon, 4)         // 4C (过量100%)
                .outputItems(dust, Osmium, 1)       // Os
                .outputItems(dust, SodaAsh, 24) // 2Na₂CO₃
                .outputFluids(new FluidStack(Fluids.WATER, 2000))  // 2H₂O
                .blastFurnaceTemp(5400)
                .duration(400).EUt(VA[IV]).save(provider);
        BLAST_RECIPES.recipeBuilder("ruthenium_chlorination")
                .inputItems(dust, RU_RH_IR_RESIDUE, 1)
                .inputFluids(Chlorine.getFluid(14000))
                .outputFluids(RUTHENIUM_TETRACHLORIDE_GAS.getFluid(1000)) // RuCl₄↑ (沸点315°C)
                .outputItems(dust, RH_IR_CHLORIDE_RESIDUE)
                .blastFurnaceTemp(500)
                .duration(300).EUt(VA[EV]).save(provider);
        CHEMICAL_RECIPES.recipeBuilder("rhodium_iridium_separation")
                .inputItems(dust, RH_IR_CHLORIDE_RESIDUE, 1)
                .inputItems(dust, SodiumBisulfate, 49)
                .outputItems(dust, RhodiumSulfate, 5)
                .outputItems(dust, Salt, 14)
                .outputItems(dust, IRIDIUM_METAL_SPONGE, 1)
                .outputFluids(SulfurTrioxide.getFluid(2000))
                .outputFluids(SulfuricAcid.getFluid(2000))
                .outputFluids(HydrochloricAcid.getFluid(3000))
                .duration(400).EUt(VA[IV]).save(provider);
        CHEMICAL_RECIPES.recipeBuilder("ruthenium_tetrachloride_absorption")
                .inputFluids(RUTHENIUM_TETRACHLORIDE_GAS.getFluid(1000))
                .inputItems(dust, SodiumHydroxide, 8)
                .outputItems(dust, SodiumRutheniate, 2)
                .outputFluids(HydrochloricAcid.getFluid(4000))
                .duration(150).EUt(VA[HV]).save(provider);
        BLAST_RECIPES.recipeBuilder("sodium_ruthenate_reduction")
                .inputItems(dust, SodiumRutheniate, 2)
                .inputFluids(Hydrogen.getFluid(4000))
                .outputItems(dust, Ruthenium, 1)
                .outputItems(dust, SodiumHydroxide, 8)
                .blastFurnaceTemp(600)
                .duration(200).EUt(VA[EV]).save(provider);
        CHEMICAL_RECIPES.recipeBuilder("rhodium_zinc_replacement")
                .inputItems(dust, RhodiumSulfate, 5)
                .inputItems(dust, Zinc, 3)
                .outputItems(dust, Rhodium, 2)
                .outputItems(dust, ZincSulfate, 18)
                .duration(200).EUt(VA[MV]).save(provider);
        ARC_FURNACE_RECIPES.recipeBuilder("iridium_sponge_refining")
                .inputItems(dust, IRIDIUM_METAL_SPONGE, 2)
                .outputItems(ingot, Iridium, 1)
                .duration(300).EUt(VA[EV]).save(provider);
        ARC_FURNACE_RECIPES.recipeBuilder("sodium_peroxide_combustion")
                .inputItems(dust, Sodium, 2)
                .inputFluids(Oxygen.getFluid(2000))
                .outputItems(dust, SODIUM_PEROXIDE, 4)
                .duration(150).EUt(VA[HV]).save(provider);
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder("ammonium_chloride_solidification")
                .inputFluids(AmmoniumChloride.getFluid(250))
                .outputItems(dust, AmmoniumChloride, 1)
                .duration(40)
                .EUt(VA[LV])
                .save(provider);
        EXTRACTOR_RECIPES.recipeBuilder("ammonium_chloride_dissolution")
                .inputItems(dust, AmmoniumChloride, 1)
                .outputFluids(AmmoniumChloride.getFluid(250))
                .duration(30)
                .EUt(VA[LV])
                .save(provider);
    }
}
