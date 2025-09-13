package io.github.cpearl0.ctnhcore.data.recipe.chain;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GCYMRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import io.github.cpearl0.ctnhcore.registry.CTNHMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHMaterials.*;

public class PlatinumLine {
    public static void init(Consumer<FinishedRecipe> provider) {
        remove(provider);
        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder("platinum_slurry_aqua_regia")
                .inputItems(dust,PlatinumGroupSludge,3) // 单槽位输入
                .inputFluids(GTMaterials.AquaRegia.getFluid(6000))//2HNO3+4HCL
                .outputFluids(CTNHMaterials.GoldPlatinumPalladiumAcidSolution.getFluid(1000))
                .outputFluids(GTMaterials.NitricOxide.getFluid(1000))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .outputItems(dust,CTNHMaterials.PlatinumGroupResidue,1)
                .EUt(GTValues.VA[GTValues.HV])
                .duration(640)
                .save(provider);
        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder("platinum_slurry_aqua_regia_fix")
                .inputItems(dust,PlatinumMetal,3) // 单槽位输入
                .inputFluids(GTMaterials.AquaRegia.getFluid(6000))//2HNO3+4HCL
                .outputFluids(CTNHMaterials.GoldPlatinumPalladiumAcidSolution.getFluid(1000))
                .outputFluids(GTMaterials.NitricOxide.getFluid(1000))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .outputItems(dust,CTNHMaterials.PlatinumGroupResidue,1)
                .EUt(GTValues.VA[GTValues.HV])
                .duration(640)
                .save(provider);
//溶液线
        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder("denitrate_gold_platinum_palladium_solution") //反复除硝
                .inputFluids(CTNHMaterials.GoldPlatinumPalladiumAcidSolution.getFluid(1000)) // 单槽位输入：酸性溶液
                .inputFluids(GTMaterials.HydrochloricAcid.getFluid(2000))                    // 单槽位输入：盐酸
                .outputFluids(GTMaterials.NitricOxide.getFluid(500))                        // 一氧化氮（1000mb）
                .outputFluids(GTMaterials.Chlorine.getFluid(2000))                          // 氯气（500mb）
                .outputFluids(CTNHMaterials.DenitratedGoldPlatinumPalladiumSolution.getFluid(500))
                .outputFluids(CTNHMaterials.GoldPlatinumPalladiumAcidSolution.getFluid(500))
                .EUt(GTValues.VA[GTValues.HV])              // 高电压（HV级，14,720 EU/t）
                .duration(320)                              // 反应时间7.5秒（150 ticks）
                .save(provider);
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("ferrous_reduction_denitrated_solution")
                .inputFluids(CTNHMaterials.DenitratedGoldPlatinumPalladiumSolution.getFluid(1000))  // 除硝后的贵金属酸性溶液（含HCl）
                .inputFluids(CTNHMaterials.FerrousSulfate.getFluid(1000))                             // 硫酸亚铁溶液（FeSO₄）
                .outputFluids(CTNHMaterials.FerricSulfate.getFluid(1000))                            // 硫酸铁溶液（Fe₂(SO₄)₃）
                .outputItems(dust,Gold,1)                                          // 金粉（Au）
                .outputFluids(CTNHMaterials.ChloroplatinicChloropalladicSolution.getFluid(1000))    // 氯铂酸-氯钯酸混合溶液
                .EUt(GTValues.VA[GTValues.MV])     // 低电压（LV级，30 EU/t）
                .duration(240)                      // 反应时间 10秒（200 ticks）
                .save(provider);
        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder("palladium_palladium_platinum_oi_new_process")
                .inputItems(dust,PlatinumOre,7)
                .inputFluids(Chlorine.getFluid(8000))
                .inputFluids(HydrochloricAcid.getFluid(4000))
                .outputFluids(CTNHMaterials.ChloroplatinicChloropalladicSolution.getFluid(1000))
                .EUt(GTValues.VA[GTValues.EV])
                .duration(360)
                .save(provider);
        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder("palladium_platinum_oi_new_process")
                .inputItems(dust,PalladiumOre,7)
                .inputFluids(Chlorine.getFluid(4000))
                .inputFluids(HydrochloricAcid.getFluid(2000))
                .inputItems(dust,AmmoniumChloride,10)
                .outputFluids(CTNHMaterials.ChloropalladicAcidMixture.getFluid(3000))
                .EUt(GTValues.VA[GTValues.EV])
                .duration(400)
                .save(provider);
        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder("palladium_platinum_oi_new_process_fix")
                .inputItems(dust,PalladiumMetal,7)
                .inputFluids(Chlorine.getFluid(4000))
                .inputFluids(HydrochloricAcid.getFluid(2000))
                .inputItems(dust,AmmoniumChloride,10)
                .outputFluids(CTNHMaterials.ChloropalladicAcidMixture.getFluid(3000))
                .EUt(GTValues.VA[GTValues.EV])
                .duration(400)
                .save(provider);
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("ammonium_chloroplatinate_synthesis")
                .inputItems(dust,AmmoniumChloride,4)                           // 氯化铵固体（2个，对应2000mb溶液当量）
                .inputFluids(CTNHMaterials.ChloroplatinicChloropalladicSolution.getFluid(3000))  // 氯铂酸-氯钯酸混合溶液（含H₂[PtCl₆]）
                .outputItems(dust,CTNHMaterials.AmmoniumChloroplatinate,9)                // 氯铂酸铵沉淀（(NH₄)₂[PtCl₆]）
                .outputFluids(GTMaterials.HydrochloricAcid.getFluid(2000))                    // 释放的盐酸（2000mb）
                .outputFluids(CTNHMaterials.ChloropalladicAcidMixture.getFluid(3000)) //氯钯酸混合物
                .EUt(GTValues.VA[GTValues.MV])     // 中电压（MV级，480 EU/t）
                .duration(400)                     // 反应时间 20秒（400 ticks）
                .save(provider);
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("ammonium_chloroplatinate_calcination")
                .inputItems(dust,CTNHMaterials.AmmoniumChloroplatinate,9)  // 氯铂酸铵粉末（9个）
                .outputItems(dust,GTMaterials.AmmoniumChloride,4)        // 氯化铵粉末（4个）
                .outputItemsRanged(ChemicalHelper.get(dust, CTNHMaterials.SpongePlatinum), UniformInt.of(1, 3))
                .outputFluids(GTMaterials.Chlorine.getFluid(4000))           // 氯气（4000mb）
                .blastFurnaceTemp(500)       // 高炉温度500K（227℃）
                .EUt(GTValues.VA[GTValues.HV]) // 高电压（HV级，14,720 EU/t）
                .duration(600)               // 反应时间30秒（600 ticks）
                .save(provider);
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("sponge_platinum_calcination")
                .inputItems(dust,CTNHMaterials.SpongePlatinum,1)  // 海绵铂（1个）
                .outputItems(TagPrefix.ingot,GTMaterials.Platinum,1)       // 铂锭（1个）
                .blastFurnaceTemp(1700)       // 高炉温度1700K（1427℃）
                .EUt(GTValues.VA[GTValues.HV]) // 极高电压（EV级，30,720 EU/t）
                .duration(500)               // 反应时间60秒（1200 ticks）
                .save(provider);
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("palladium_ammonia_precipitation")
                .inputFluids(CTNHMaterials.ChloropalladicAcidMixture.getFluid(1000)) // 氯钯酸溶液（含H₂[PdCl₆]）
                .inputFluids(CTNHMaterials.AmmoniaMonohydrate.getFluid(800))           // 氨水（过量）
                .outputItems(dust,CTNHMaterials.Diamminedichloropalladium,2) // 二氯二氨络亚钯
                .outputFluidsRanged(new FluidStack(GTMaterials.HydrochloricAcid.getFluid(),1), UniformInt.of(1000, 1200))      // 副产盐酸
                .EUt(GTValues.VA[GTValues.HV])     // 中电压（480 EU/t）
                .duration(320)                     // 10秒
                .save(provider);
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("palladium_ammine_hydrogen_reduction")
                .inputItems(dust,CTNHMaterials.Diamminedichloropalladium,5)  // 二氯二氨络亚钯（5个）
                .inputFluids(GTMaterials.Hydrogen.getFluid(2000))                 // 氢气（2000mb，过量）
                .outputItemsRanged(ChemicalHelper.get(dust, CTNHMaterials.SpongePalladium), UniformInt.of(1, 2))          // 海绵钯（1个，纯度>99.7%）SpongePalladium
                .outputItems(dust,GTMaterials.AmmoniumChloride,4)        // 氯化铵粉末（4个）
                .blastFurnaceTemp(500)
                .EUt(GTValues.VA[GTValues.HV])
                .duration(400)
                .save(provider);
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("sponge_palladium_smelting")
                .inputItems(dust, CTNHMaterials.SpongePalladium,1)
                .outputItems(TagPrefix.ingot, GTMaterials.Palladium,1)
                .blastFurnaceTemp(1500)
                .EUt(GTValues.VA[GTValues.HV])
                .duration(540)
                .save(provider);
//残渣线
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("platinum_residue_smelting")
                .inputItems(dust, CTNHMaterials.PlatinumGroupResidue, 1)  // 铂族残渣粉（1个）
                .inputItems(dust, CTNHMaterials.Litharge, 2)            // 密陀僧粉（1个）
                .inputItems(dust, GTMaterials.Carbon, 1)                // 碳粉（1个）
                .outputItems(dust, CTNHMaterials.NobleLead, 1)          // 贵铅粉（1个）
                .outputFluids(GTMaterials.CarbonMonoxide.getFluid(1000))          // 一氧化碳（1000mb）
                .blastFurnaceTemp(2000)       // 2000K高温
                .EUt(GTValues.VA[GTValues.EV]) // 高电压（7,680 EU/t）
                .duration(200)                // 20秒（400 ticks）
                .save(provider);
        GTRecipeTypes.CHEMICAL_BATH_RECIPES.recipeBuilder("noble_lead_nitric_leach")
                .inputItems(dust, CTNHMaterials.NobleLead, 1)      // 贵铅粉（1个）
                .inputFluids(GTMaterials.NitricAcid.getFluid(1000))          // 硝酸（1000mb）
                .outputFluids(NitricLeachSolution.getFluid(1000)) // 硝酸浸没溶液
                .outputItems(dust, CTNHMaterials.EnrichedInertMixture, 1) // 富集惰性混合物粉
                .EUt(GTValues.VA[GTValues.HV])     // 中电压（480 EU/t）
                .duration(240)                     // 10秒（200 ticks）
                .save(provider);
        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("nitric_leach_electrolysis")
                .inputFluids(NitricLeachSolution.getFluid(1000))
                .outputItems(dust, GTMaterials.Silver, 1)  // 槽位1
                .outputItems(dust, GTMaterials.Lead, 1)    // 槽位2
                .outputFluids(GTMaterials.NitrogenDioxide.getFluid(1000)) // 槽位3
                .EUt(1920)
                .duration(300)
                .save(provider);
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("enriched_inert_mixture_sulfate_conversion")
                .inputItems(dust, CTNHMaterials.EnrichedInertMixture, 2)  // 富集惰性混合物粉（2个）
                .inputFluids(GTMaterials.SodiumBisulfate.getFluid(144 * 42))            // 熔融硫酸氢钠（3000mb）
                .outputFluids(GTMaterials.RhodiumSulfate.getFluid(1000))  // 硫酸铑溶液（1000mb）
                .outputItems(dust,SodiumSulfate, 21)
                .outputItems(dust,PreciousMetalMixture, 1)
                .blastFurnaceTemp(1200)       // 1200K（实际工业中NaHSO₄熔点为185℃，此处高温模拟反应活化能）
                .EUt(GTValues.VA[GTValues.HV]) // 高电压（7,680 EU/t）
                .duration(400)                // 20秒
                .save(provider);
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("rhodium_sulfate_solution_mixing")
                .inputFluids(GTMaterials.Water.getFluid(1000))
                .inputFluids(GTMaterials.RhodiumSulfate.getFluid(100))  // 100mb 氯铑酸（H₃[RhCl₆]）
                .outputFluids(CTNHMaterials.RhodiumSulfateSolution.getFluid(1000))  // 1000mb 硫酸铑水溶液
                .EUt(120)         // 固定电压480 EU/t（无需GTValues封装）
                .duration(100)    // 10秒（200 ticks）
                .save(provider);
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("rhodium_sulfate_to_hydroxide")
                .inputFluids(CTNHMaterials.RhodiumSulfateSolution.getFluid(10000))  // 10000mb Rh₂(SO₄)₃溶液
                .inputItems(dust, GTMaterials.SodiumHydroxide, 18)        // 6个氢氧化钠粉
                .outputItems(dust, CTNHMaterials.RhodiumHydroxide, 8)    // 8个氢氧化铑粉
                .outputItems(dust,SodiumSulfate, 21)       // 21个硫酸钠粉
                .EUt(120)            // 电压120 EU/t（LV级）
                .duration(100)       // 5秒（100 ticks）
                .save(provider);
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("rhodium_hydroxide_to_chlororhodic_acid")
                .inputItems(dust, CTNHMaterials.RhodiumHydroxide, 4)  // 4个氢氧化铑粉
                .inputFluids(GTMaterials.HydrochloricAcid.getFluid(6000))       // 6000mb盐酸
                .outputFluids(CTNHMaterials.ChlororhodicAcid.getFluid(1000))   // 1000mb氯铑酸
                .outputFluids(GTMaterials.Water.getFluid(3000))                // 3000mb水
                .EUt(GTValues.VA[GTValues.MV])  // 高电压（7,680 EU/t，模拟强酸反应）
                .duration(300)                  // 15秒（300 ticks）
                .save(provider);
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("chlororhodic_acid_to_ammonium_solution")
                .inputFluids(CTNHMaterials.ChlororhodicAcid.getFluid(1000))   // 槽位1：1000mb氯铑酸
                .inputFluids(CTNHMaterials.AmmoniaMonohydrate.getFluid(3000)) // 槽位2：3000mb一水合氨
                .outputFluids(CTNHMaterials.AmmoniumChlororhodateSolution.getFluid(4000)) // 4000mb氯铑酸铵溶液
                .EUt(GTValues.VA[GTValues.MV])  // 中电压（480 EU/t）
                .duration(200)                  // 10秒（200 ticks）
                .save(provider);
        GTRecipeTypes.DISTILLATION_RECIPES.recipeBuilder("ammonium_chlororhodate_full_distill")
                .inputFluids(CTNHMaterials.AmmoniumChlororhodateSolution.getFluid(4000))  // 输入4000mb
                .outputFluids(CTNHMaterials.ConcentratedAmmoniumChlororhodate.getFluid(1000)) // 1500mb浓缩液
                .outputFluids(GTMaterials.Water.getFluid(3000))                           // 2500mb水（可回收）
                .EUt(GTValues.VA[GTValues.HV])  // 中电压（480 EU/t）
                .duration(200)                  // 20秒（400 ticks）
                .save(provider);
        GTRecipeTypes.AUTOCLAVE_RECIPES.recipeBuilder("ammonium_chlororhodate_crystallization")
                .circuitMeta(2)
                .inputFluids(CTNHMaterials.ConcentratedAmmoniumChlororhodate.getFluid(1000))  // 1000mb浓缩液
                .inputItems(dust, CTNHMaterials.AmmoniumChlororhodate, 1)
                .outputItems(dust, CTNHMaterials.AmmoniumChlororhodate, 2)         // 1个氯铑酸铵粉
                .EUt(GTValues.VA[GTValues.HV])  // 高电压（7,680 EU/t）
                .duration(100)                  // 15秒（300 ticks）
                .save(provider);
        GTRecipeTypes.AUTOCLAVE_RECIPES.recipeBuilder("ammonium_chlororhodate_crystallization_two")
                .circuitMeta(1)
                .inputFluids(CTNHMaterials.ConcentratedAmmoniumChlororhodate.getFluid(1000))  // 1000mb浓缩液
                .outputItems(dust, CTNHMaterials.AmmoniumChlororhodate, 1)         // 1个氯铑酸铵粉
                .EUt(GTValues.VA[GTValues.HV])  // 高电压（7,680 EU/t）
                .duration(360)                  // 15秒（300 ticks）
                .save(provider);
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("ammonium_chlororhodate_hydrogen_reduction")
                .inputItems(dust, CTNHMaterials.AmmoniumChlororhodate, 1)  // 1个氯铑酸铵粉
                .inputFluids(GTMaterials.Hydrogen.getFluid(3000))                     // 1500mb氢气
                .outputItems(dust, CTNHMaterials.SpongeRhodium, 1)         // 槽位1：1个海绵铑
                .outputItems(dust, GTMaterials.AmmoniumChloride, 3)        // 槽位2：3个氯化铵粉
                .outputFluids(GTMaterials.Chlorine.getFluid(3000))                   // 流体输出：3000mb氯气
                .blastFurnaceTemp(1200)       // 1200K（927℃）
                .EUt(GTValues.VA[GTValues.EV]) // 高电压（7,680 EU/t）
                .duration(400)                // 20秒（400 ticks）
                .save(provider);
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("sponge_rhodium_smelting")
                .inputItems(dust, CTNHMaterials.SpongeRhodium, 1)  // 1个海绵铑粉
                .outputItems(TagPrefix.ingot, GTMaterials.Rhodium, 1)       // 1个铑锭
                .blastFurnaceTemp(1966)       // 1966K（1693℃，接近铑熔点1964℃）
                .EUt(GTValues.VA[GTValues.EV]) // 极高电压（30,720 EU/t）
                .duration(100)                // 10秒（200 ticks）
                .save(provider);
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("lead_to_litharge_oxidation")
                .inputItems(dust, GTMaterials.Lead, 1)  // 1个铅粉
                .inputFluids(GTMaterials.Oxygen.getFluid(1000))   // 1000mb氧气（1mol）
                .outputItems(dust, CTNHMaterials.Litharge, 2)  // 2个密陀僧粉（PbO）
                .EUt(GTValues.VA[GTValues.LV])  // 低电压（30 EU/t）
                .duration(100)                  // 5秒（100 ticks）
                .save(provider);
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("precious_metal_oxidation")
                .inputItems(dust, CTNHMaterials.PreciousMetalMixture, 1)  // 槽位1：1个贵金属混合物粉
                .inputFluids(CTNHMaterials.SodiumPeroxide.getFluid(144 * 4))           // 槽位2：1000mb熔融过氧化钠
                .inputFluids(GTMaterials.SodiumHydroxide.getFluid(144 * 6))           // 槽位3：1000mb氢氧化钠溶液
                .outputFluids(CTNHMaterials.SodiumOsmateRuthenateSolution.getFluid(2000)) // 2000mb混合溶液
                .outputItems(dust,IridiumDioxide,1)
                .EUt(GTValues.VA[GTValues.EV])  // 高电压（7,680 EU/t）
                .duration(200)                   // 20秒（400 ticks）
                .save(provider);
        GTRecipeTypes.ARC_FURNACE_RECIPES.recipeBuilder("sodium_peroxide_synthesis")
                .inputItems(dust, GTMaterials.Sodium, 2)  // 2个钠粉
                .inputFluids(GTMaterials.Oxygen.getFluid(2000))      // 2000mb氧气（2mol）
                .outputItems(dust, CTNHMaterials.SodiumPeroxide, 4)  // 4个过氧化钠粉
                .EUt(GTValues.VA[GTValues.LV])  // 极高电压（30,720 EU/t）
                .duration(100)                   // 15秒（300 ticks）
                .save(provider);
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("osmate_ruthenate_chlorination")
                .inputFluids(CTNHMaterials.SodiumOsmateRuthenateSolution.getFluid(2000))  // 槽位1：2000mb混合溶液
                .inputFluids(GTMaterials.Chlorine.getFluid(4000))                         // 槽位2：4000mb氯气
                .outputFluids(CTNHMaterials.SodiumOsmateRuthenateChlorideSolution.getFluid(6000))  // 1000mb氯化溶液
                .EUt(GTValues.VA[GTValues.HV])  // 高电压（7,680 EU/t）
                .duration(200)                   // 10秒（200 ticks）
                .save(provider);
        GTRecipeTypes.DISTILLATION_RECIPES.recipeBuilder("osmium_ruthenium_distillation")
                .inputFluids(CTNHMaterials.SodiumOsmateRuthenateChlorideSolution.getFluid(6000))  // 6000mb混合溶液
                .outputItems(dust, GTMaterials.Salt, 8)                   // 8个氯化钠粉
                .outputFluids(GTMaterials.Water.getFluid(1500))
                .outputFluids(GTMaterials.OsmiumTetroxide.getFluid(1000))           // 1000mb四氧化锇气体
                .outputFluids(GTMaterials.RutheniumTetroxide.getFluid(1000))       // 1000mb四氧化钌气体
                .EUt(GTValues.VA[GTValues.EV])  // 极高电压（30,720 EU/t）
                .duration(600)                   // 30秒（600 ticks）
                .save(provider);
        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder("osmium_tetroxide_reduction")
                .inputFluids(GTMaterials.OsmiumTetroxide.getFluid(1000))      // 1000mb OsO₄
                .inputFluids(GTMaterials.Ethanol.getFluid(1000))              // 1000mb 乙醇
                .inputFluids(GTMaterials.HydrochloricAcid.getFluid(6000))     // 6000mb 盐酸
                .outputFluids(CTNHMaterials.ChlorosmicAcidGas.getFluid(1000))   // 1000mb 氯锇酸
                .outputFluids(GTMaterials.Water.getFluid(4000))               // 4000mb 水
                .outputFluids(CTNHMaterials.Acetaldehyde.getFluid(1000))        // 1000mb 乙醛（原生材料）
                .EUt(GTValues.VA[GTValues.HV])  // 高电压（7,680 EU/t）
                .duration(300)                   // 15秒（300 ticks）
                .save(provider);
        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder("ruthenium_tetroxide_reduction")
                .inputFluids(GTMaterials.RutheniumTetroxide.getFluid(1000))  // 1000mb RuO₄
                .inputFluids(GTMaterials.Ethanol.getFluid(1000))              // 1000mb 乙醇
                .inputFluids(GTMaterials.HydrochloricAcid.getFluid(6000))     // 6000mb 盐酸
                .outputFluids(CTNHMaterials.ChlororuthenicAcidGas.getFluid(1000)) // 1000mb 氯钌酸
                .outputFluids(GTMaterials.Water.getFluid(4000))               // 4000mb 水
                .outputFluids(CTNHMaterials.Acetaldehyde.getFluid(1000))        // 1000mb 乙醛
                .EUt(GTValues.VA[GTValues.HV])  // 高电压（7,680 EU/t）
                .duration(300)                   // 15秒（300 ticks）
                .save(provider);
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("ammonium_chlorosmate_synthesis")
                .inputFluids(CTNHMaterials.ChlorosmicAcidGas.getFluid(1000))  // 1000mb H₂[OsCl₆]气体
                .inputItems(dust, GTMaterials.AmmoniumChloride, 4)  // 4个氯化铵粉
                .outputItems(dust, CTNHMaterials.AmmoniumChlorosmate, 1)  // 1个氯锇酸铵粉
                .outputFluids(GTMaterials.HydrochloricAcid.getFluid(2000))          // 2000mb盐酸
                .EUt(GTValues.VA[GTValues.EV])  // 中电压（480 EU/t）
                .duration(200)                   // 10秒（200 ticks）
                .save(provider);
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("ammonium_chlorosmate_decomposition")
                .inputItems(dust, CTNHMaterials.AmmoniumChlorosmate, 1)  // 1个氯锇酸铵粉
                .inputFluids(GTMaterials.Hydrogen.getFluid(4000))                   // 2000mb氢气（2mol）
                .outputItems(dust, GTMaterials.Osmium, 1)                // 1个锇粉
                .outputItems(dust, GTMaterials.AmmoniumChloride, 4)      // 4个氯化铵粉
                .outputFluids(GTMaterials.HydrochloricAcid.getFluid(4000))         // 4000mb盐酸
                .blastFurnaceTemp(800)       // 800K（527℃）
                .EUt(GTValues.VA[GTValues.EV]) // 高电压（7,680 EU/t）
                .duration(260)                // 20秒（400 ticks）
                .save(provider);
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("ammonium_chlororuthenate_synthesis")
                .inputFluids(CTNHMaterials.ChlororuthenicAcidGas.getFluid(1000))  // 1000mb H₂[RuCl₆]气体
                .inputItems(dust, GTMaterials.AmmoniumChloride, 4)      // 4个氯化铵粉
                .outputItems(dust, CTNHMaterials.AmmoniumChlororuthenate, 1)  // 1个氯钌酸铵粉
                .outputFluids(GTMaterials.HydrochloricAcid.getFluid(2000))              // 2000mb盐酸
                .EUt(GTValues.VA[GTValues.EV])  // 高电压（7,680 EU/t）
                .duration(200)                  // 10秒（200 ticks）
                .save(provider);
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("ammonium_chlororuthenate_decomposition")
                .inputItems(dust, CTNHMaterials.AmmoniumChlororuthenate, 1)  // 1个氯钌酸铵粉
                .inputFluids(GTMaterials.Hydrogen.getFluid(4000))                      // 4000mb氢气（4mol）
                .outputItems(dust, GTMaterials.Ruthenium, 1)                 // 1个钌粉
                .outputItems(dust, GTMaterials.AmmoniumChloride, 4)         // 4个氯化铵粉
                .outputFluids(GTMaterials.HydrochloricAcid.getFluid(4000))           // 4000mb盐酸
                .blastFurnaceTemp(800)       // 800K（527℃）
                .EUt(GTValues.VA[GTValues.EV]) // 高电压（7,680 EU/t）
                .duration(260)                // 13秒（260 ticks）
                .save(provider);
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("iridium_oxide_carbon_reduction")
                .inputItems(dust, CTNHMaterials.IridiumDioxide, 3)  // 1个氧化铱粉
                .inputItems(dust, GTMaterials.Carbon, 2)          // 2个碳粉
                .outputItems(dust, GTMaterials.Iridium, 1)        // 1个铱粉（纯度>99%）
                .outputFluids(GTMaterials.CarbonMonoxide.getFluid(2000))    // 2000mb一氧化碳
                .blastFurnaceTemp(1400)       // 1500K（1227℃）
                .EUt(GTValues.VA[GTValues.IV]) // 超高电压（122,880 EU/t）
                .duration(600)                // 30秒（600 ticks）
                .save(provider);
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("ferrous_sulfate_synthesis")
                .circuitMeta(2)
                .inputItems(dust, GTMaterials.Iron, 2)          // 2个铁粉
                .inputFluids(GTMaterials.SulfuricAcid.getFluid(3000))      // 3000mb硫酸（3mol）
                .outputFluids(CTNHMaterials.FerrousSulfate.getFluid(1000)) // 1000mb酸性硫酸亚铁溶液
                .outputFluids(GTMaterials.Hydrogen.getFluid(4000))         // 2000mb氢气
                .EUt(GTValues.VA[GTValues.LV])  // 低电压（30 EU/t）
                .duration(200)                  // 10秒（200 ticks）
                .save(provider);
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("ferric_sulfate_reduction")
                .circuitMeta(1)
                .inputFluids(CTNHMaterials.FerricSulfate.getFluid(2000))  // 1000mb Fe₂(SO₄)₃
                .inputItems(dust, GTMaterials.Iron, 1)                 // 1个铁粉
                .inputFluids(GTMaterials.SulfuricAcid.getFluid(3000))      // 3000mb硫酸（3mol）
                .outputFluids(CTNHMaterials.FerrousSulfate.getFluid(3000)) // 3000mb 酸性FeSO₄
                .EUt(GTValues.VA[GTValues.LV])  // 低电压（30 EU/t）
                .duration(100)                  // 5秒（100 ticks）
                .save(provider);
        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("anhydrous_ferric_sulfate_electrolysis")
                .inputFluids(CTNHMaterials.FerricSulfate.getFluid(1000))  // 1000mb硫酸铁溶液
                .outputItems(dust, GTMaterials.Iron, 2)           // 2个铁粉（Fe）
                .outputItems(dust, GTMaterials.Sulfur, 3)         // 3个硫粉（S）
                .outputFluids(GTMaterials.Oxygen.getFluid(6000))            // 6000mb氧气（O₂）
                .EUt(GTValues.VA[GTValues.HV])  // 高电压（30,720 EU/t）
                .duration(800)                  // 40秒（800 ticks）
                .save(provider);
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("acetaldehyde_hydrogenation")
                .notConsumable(dust, GTMaterials.Platinum, 1)
                .inputFluids(CTNHMaterials.Acetaldehyde.getFluid(1000))  // 1000mb乙醛（CH₃CHO）
                .inputFluids(GTMaterials.Hydrogen.getFluid(2000))         // 2000mb氢气（2H₂）
                .outputFluids(GTMaterials.Ethanol.getFluid(1000))        // 1000mb乙醇（CH₃CH₂OH）
                .EUt(GTValues.VA[GTValues.MV])  // 中电压（480 EU/t）
                .duration(100)           // 10秒（200 ticks）
                .save(provider);
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("nh4_cl")//氯化铵
                .inputFluids(Ammonia.getFluid(1000))
                .inputFluids(Chlorine.getFluid(1000))
                .outputItems(dust,AmmoniumChloride,2)
                .EUt(GTValues.VA[GTValues.HV])
                .duration(100)
                .save(provider);
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("nh3_h2o")//一水合氨
                .inputFluids(Ammonia.getFluid(1000))
                .inputFluids(Water.getFluid(1000))
                .outputFluids(AmmoniaMonohydrate.getFluid(1000))
                .EUt(GTValues.VA[GTValues.LV])
                .duration(20)
                .save(provider);
        GTRecipeTypes.FLUID_SOLIDFICATION_RECIPES.recipeBuilder("nh4_cl_solidfication")
                .inputFluids(AmmoniumChloride.getFluid(500))
                .outputItems(dust,AmmoniumChloride,1)
                .EUt(GTValues.VA[GTValues.LV])
                .duration(20)
                .save(provider);
        GTRecipeTypes.EXTRACTOR_RECIPES.recipeBuilder("nh4_cl_extractor")
                .inputItems(dust,AmmoniumChloride,1)
                .outputFluids(AmmoniumChloride.getFluid(500))
                .EUt(GTValues.VA[GTValues.LV])
                .duration(20)
                .save(provider);
    }
    private static void remove(Consumer<FinishedRecipe> provider) {
        GCYMRecipeTypes.ALLOY_BLAST_RECIPES.recipeBuilder("sodium_pyrosulfate").save(provider);
        GTRecipeTypes.EXTRACTOR_RECIPES.recipeBuilder("extract_osmium_tetroxide_dust").save(provider);
        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("raw_platinum_separation").save(provider);
        GTRecipeTypes.DISTILLERY_RECIPES.recipeBuilder("acidic_osmium_solution_separation_to_hydrochloric_acid")
                .save(provider);
        GTRecipeTypes.DISTILLERY_RECIPES.recipeBuilder("acidic_osmium_solution_separation_to_water").save(provider);
        GTRecipeTypes.DISTILLATION_RECIPES.recipeBuilder("acidic_osmium_solution_separation").save(provider);
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("extract_ruthenium_tetroxide_dust").save(provider);
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("pgs_separation").save(provider);
        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder("rarest_metal_mixture_separation").save(provider);
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("iridium_metal_residue_separation").save(provider);
        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("rhodium_sulfate_separation").save(provider);
        chemicalRemoval(provider, "osmium_tetroxide_separation");
        chemicalRemoval(provider, "inert_metal_mixture_separation");
        chemicalRemoval(provider, "iridium_chloride_separation");
        chemicalRemoval(provider, "ruthenium_tetroxide_separation");
        chemicalRemoval(provider, "raw_palladium_separation");
        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("decomposition_electrolyzing_cooperite")
                .duration(648)
                .inputItems(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Cooperite))
                .outputItems(
                        ChemicalHelper.get(TagPrefix.dust, PlatinumOre, 3),
                        ChemicalHelper.get(TagPrefix.dust, GTMaterials.Nickel),
                        ChemicalHelper.get(TagPrefix.dust, GTMaterials.Sulfur),
                        ChemicalHelper.get(TagPrefix.dust, PalladiumOre)
                )
                .EUt(60).save(provider);
    }

    private static void chemicalRemoval(Consumer<FinishedRecipe> provider, String id) {
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(id).save(provider);
        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder(id).save(provider);
    }
}
