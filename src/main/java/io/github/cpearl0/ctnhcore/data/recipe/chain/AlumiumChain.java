package io.github.cpearl0.ctnhcore.data.recipe.chain;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static dev.engine_room.flywheel.impl.visualization.storage.Transaction.remove;
import static io.github.cpearl0.ctnhcore.data.materials.BauxiteProcessingMaterials.*;
import static io.github.cpearl0.ctnhcore.data.materials.CrudeGoldRefiningMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHItems.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.*;


public class AlumiumChain {
    public static void init(Consumer<FinishedRecipe> provider) {
        remove(provider);

        //离心
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("electrolyzing_green_sapphire")
                .inputItems(dust, GreenSapphire,5)//绿色蓝宝石
                .outputItems(dust, Alumina,5)
                .EUt(VA[GTValues.HV])
                .duration(100)
                .save(provider);

        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("electrolyzing_sapphire")
                .inputItems(dust, Sapphire,5)//蓝宝石
                .outputItems(dust, Alumina,5)
                .EUt(VA[GTValues.HV])
                .duration(100)
                .save(provider);

        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("electrolyzing_ruby")
                .inputItems(dust, Ruby,6)//红宝石
                .outputItems(dust, Alumina,5)
                .outputItems(dust, ChromiumTrioxide,1)
                .EUt(VA[GTValues.HV])
                .duration(100)
                .save(provider);

        //电解
        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_pyrope")
                .inputItems(dust, Pyrope,20)//镁铝榴石
                .outputItems(dust, Alumina,5)
                .outputItems(dust, SiliconDioxide,9)
                .outputItems(dust, Magnesium,3)
                .outputFluids(Oxygen.getFluid(3000))
                .EUt(VA[GTValues.MV])
                .duration(200)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_granite_red")
                .inputItems(dust, GraniteRed,6)//红色花岗岩
                .outputItems(dust, Alumina,5)
                .outputItems(dust, PotassiumFeldspar,1)
                .EUt(VA[GTValues.MV])
                .duration(60)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_potassium_feldspar")
                .inputItems(dust, PotassiumFeldspar,22)//钾长石
                .outputItems(dust, Alumina,5)
                .outputItems(dust, SiliconDioxide,6)
                .outputItems(dust, Potassium,6)
                .outputFluids(Oxygen.getFluid(11000))
                .EUt(VA[GTValues.MV])
                .duration(200)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_pollucite")
                .inputItems(dust, Pollucite,22)//铯榴石
                .outputItems(dust, Alumina,5)
                .outputItems(dust, SiliconDioxide,12)
                .outputItems(dust, Caesium,2)
                .outputFluids(Water.getFluid(2000))
                .outputFluids(Oxygen.getFluid(1000))
                .EUt(VA[GTValues.MV])
                .duration(280)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_kyanite")
                .inputItems(dust, Kyanite,8)//蓝晶石
                .outputItems(dust, Alumina,5)
                .outputItems(dust, SiliconDioxide,3)
                .EUt(VA[GTValues.MV])
                .duration(80)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_spodumene")
                .inputItems(dust, Spodumene,20)//锂辉石
                .outputItems(dust, Alumina,5)
                .outputItems(dust, SiliconDioxide,12)
                .outputItems(dust, Lithium,2)
                .outputFluids(Oxygen.getFluid(1000))
                .EUt(VA[GTValues.MV])
                .duration(180)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_spessartine")
                .inputItems(dust, Spessartine,20)//锰铝榴石
                .outputItems(dust, Alumina,5)
                .outputItems(dust, SiliconDioxide,9)
                .outputItems(dust, Manganese,3)
                .outputFluids(Oxygen.getFluid(3000))
                .EUt(VA[GTValues.MV])
                .duration(220)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_mica")
                .inputItems(dust, Lepidolite,38)//云母
                .outputItems(dust, Alumina,15)
                .outputItems(dust, SiliconDioxide,18)
                .outputItems(dust, Potassium,2)
                .outputFluids(Fluorine.getFluid(4000))
                .EUt(VA[GTValues.MV])
                .duration(380)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_lepidolite")
                .inputItems(dust, Lepidolite,20)//锂云母
                .outputItems(dust, Alumina,10)
                .outputItems(dust, Lithium,3)
                .outputItems(dust, Potassium,1)
                .outputFluids(Oxygen.getFluid(4000))
                .outputFluids(Fluorine.getFluid(2000))
                .EUt(VA[GTValues.MV])
                .duration(160)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_grossular")
                .inputItems(dust, Grossular,20)//钙铝榴石
                .outputItems(dust, Alumina,5)
                .outputItems(dust, SiliconDioxide,9)
                .outputItems(dust, Calcium,3)
                .outputFluids(Oxygen.getFluid(3000))
                .EUt(VA[GTValues.MV])
                .duration(220)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_glauconite_sand")
                .inputItems(dust, GlauconiteSand,21)//海绿石沙
                .outputItems(dust, Alumina,10)
                .outputItems(dust, Manganese,2)
                .outputItems(dust, Potassium,1)
                .outputFluids(Oxygen.getFluid(6000))
                .outputFluids(Hydrogen.getFluid(2000))
                .EUt(VA[GTValues.MV])
                .duration(220)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_emerald")
                .inputItems(dust, Emerald,29)//绿宝石
                .outputItems(dust, Alumina,50)
                .outputItems(dust, SiliconDioxide,18)
                .outputItems(dust, Beryllium,3)
                .outputFluids(Oxygen.getFluid(3000))
                .EUt(VA[GTValues.MV])
                .duration(260)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_blue_topaz")
                .inputItems(dust, BlueTopaz,13)//蓝黄玉
                .outputItems(dust, Alumina,5)
                .outputItems(dust, SiliconDioxide,3)
                .outputFluids(Oxygen.getFluid(1000))
                .outputFluids(Hydrogen.getFluid(2000))
                .outputFluids(Fluorine.getFluid(2000))
                .EUt(VA[GTValues.MV])
                .duration(100)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_biotite")
                .inputItems(dust, Biotite,44)//黑云母
                .outputItems(dust, Alumina,15)
                .outputItems(dust, Lithium,18)
                .outputItems(dust, Potassium,2)
                .outputItems(dust, Manganese,6)
                .outputFluids(Fluorine.getFluid(4000))
                .EUt(VA[GTValues.MV])
                .duration(440)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_alunite")
                .inputItems(dust, Alunite,52)//明矾石
                .outputItems(dust, Alumina,15)
                .outputItems(dust, Lithium,12)
                .outputItems(dust, Potassium,2)
                .outputFluids(Oxygen.getFluid(11000))
                .outputFluids(Hydrogen.getFluid(12000))
                .EUt(VA[GTValues.MV])
                .duration(520)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_almandine")
                .inputItems(dust, Almandine,20)//铁铝榴石
                .outputItems(dust, Alumina,5)
                .outputItems(dust, SiliconDioxide,9)
                .outputItems(dust, Iron,3)
                .outputFluids(Oxygen.getFluid(3000))
                .EUt(VA[GTValues.MV])
                .duration(200)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_blue_topaz")
                .inputItems(dust, Clay,14)//粘土
                .outputItems(dust, Alumina,5)
                .outputItems(dust, SiliconDioxide,6)
                .outputItems(dust, Sodium,2)
                .outputItems(dust, Lithium,1)
                .outputFluids(Water.getFluid(6000))
                .EUt(VA[GTValues.MV])
                .duration(180)
                .save(provider);

        //高压电解
        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_topaz")
                .inputItems(dust, Topaz,6)//黄玉
                .outputItems(dust, Aluminium,2)
                .outputItems(dust, Silicon)
                .outputFluids(Hydrogen.getFluid(2000))
                .outputFluids(Fluorine.getFluid(1000))
                .EUt(VA[GTValues.EV])
                .duration(200)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_sodalite")
                .inputItems(dust, Sodalite,11)//方钠石
                .outputItems(dust, Aluminium,3)
                .outputItems(dust, Silicon,3)
                .outputItems(dust, Sodium,4)
                .outputFluids(Chlorine.getFluid(1000))
                .EUt(VA[GTValues.EV])
                .duration(390)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_lazurite")
                .inputItems(dust, Lazurite,14)//蓝金石
                .outputItems(dust, Aluminium,3)
                .outputItems(dust, Silicon,3)
                .outputItems(dust, Sodium,4)
                .outputItems(dust, Calcium,4)
                .EUt(VA[GTValues.EV])
                .duration(460)
                .save(provider);

        //电解氧化铝
        //LV
        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_alumina_lv")
                .inputItems(dust, Alumina,10)
                .inputFluids(SODIUM_HEXAFLUOROALUMINATE.getFluid(1000))
                .outputItems(dust, Aluminium,4)
                .outputItems(dust, SODIUM_FLUORIDE,6)
                .outputItems(dust, ALUMINIUM_TRIFLUORIDE,4)
                .outputFluids(Oxygen.getFluid(6000))
                .EUt(VA[GTValues.LV])
                .duration(200)
                .save(provider);
        //HV
        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_alumina_hv")
                .inputItems(dust, Alumina,10)
                .outputItems(dust, Aluminium,4)
                .outputFluids(Oxygen.getFluid(6000))
                .EUt(VA[GTValues.HV])
                .duration(400)
                .save(provider);

        //六氟铝酸钠
        //冰晶石
        GTRecipeTypes.EXTRACTOR_RECIPES.recipeBuilder("sodium_hexafluoroaluminate_3")
                .inputItems(dust, Cryolite,10)
                .outputFluids(SODIUM_HEXAFLUOROALUMINATE.getFluid(1000))
                .EUt(VA[GTValues.LV])
                .duration(100)
                .save(provider);
        //合成
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("sodium_hexafluoroaluminate")
                .inputItems(dust, SodiumHydroxide,18)
                .inputItems(dust, Alumina,5)
                .inputFluids(HydrofluoricAcid.getFluid(12000))
                .outputFluids(SODIUM_HEXAFLUOROALUMINATE.getFluid(2000))
                .outputFluids(Water.getFluid(9000))
                .EUt(VA[GTValues.MV])
                .duration(400)
                .save(provider);
        // 回收
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("sodium_hexafluoroaluminate")
                .inputItems(dust, SODIUM_FLUORIDE,6)
                .inputItems(dust, ALUMINIUM_TRIFLUORIDE,4)
                .outputFluids(SODIUM_HEXAFLUOROALUMINATE.getFluid(1000))
                .EUt(VA[GTValues.MV])
                .duration(200)
                .save(provider);

        //铝土线
        //碱浸
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("alkali_leach_bauxite")
                .inputItems(dust, Bauxite,13)
                .inputFluids(SODIUM_HYDROXIDE_SOLUTION.getFluid(8000))
                .outputFluids(SODIUM_HYDROXIDE_BAUXITE.getFluid(8000))
                .EUt(VA[GTValues.MV])
                .duration(80)
                .save(provider);
        //碱溶液
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("sodium_hydroxide_solution")
                .inputItems(dust, SodiumHydroxide,3)
                .inputFluids(Water.getFluid(1000))
                .outputFluids(SODIUM_HYDROXIDE_SOLUTION.getFluid(1000))
                .circuitMeta(0)
                .EUt(VA[GTValues.MV])
                .duration(200)
                .save(provider);
        //加热沉淀
        GTRecipeTypes.FLUID_HEATER_RECIPES.recipeBuilder("impure_aluminum_hydroxide_solution")
                .inputFluids(SODIUM_HYDROXIDE_BAUXITE.getFluid(1000))
                .outputFluids(IMPURE_ALUMINIUM_HYDROXIDE_SOLUTION.getFluid(1000))
                .EUt(VA[GTValues.LV])
                .duration(30)
                .save(provider);
        //分离赤泥
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("red_mud")
                .inputFluids(IMPURE_ALUMINIUM_HYDROXIDE_SOLUTION.getFluid(3000))
                .outputFluids(PURE_ALUMINIUM_HYDROXIDE_SOLUTION.getFluid(2000))
                .outputFluids(RED_MUD.getFluid(1000))
                .EUt(VA[GTValues.MV])
                .duration(30)
                .save(provider);
        //分离氢氧化铝
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("aluminium_hydroxide_dust")
                .inputFluids(PURE_ALUMINIUM_HYDROXIDE_SOLUTION.getFluid(1000))
                .outputItems(dust, ALUMINIUM_HYDROXIDE,14)
                .outputFluids(Water.getFluid(1000))
                .EUt(VA[GTValues.MV])
                .duration(240)
                .save(provider);

        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("aluminium_hydroxide_dust_much")
                .notConsumable(dust,ALUMINIUM_HYDROXIDE)
                .inputFluids(PURE_ALUMINIUM_HYDROXIDE_SOLUTION.getFluid(4000))
                .outputItems(dust, ALUMINIUM_HYDROXIDE,56)
                .outputFluids(Water.getFluid(4000))
                .EUt((long) (0.5 * VA[GTValues.HV]))
                .duration(240)
                .save(provider);
        //氢氧化铝脱水
        GTRecipeTypes.FURNACE_RECIPES.recipeBuilder("alumina")
                .inputItems(dust,ALUMINIUM_HYDROXIDE,14)
                .outputItems(dust,Alumina,5)
                .blastFurnaceTemp(1100)
                .EUt(VA[GTValues.MV])
                .duration(200)
                .save(provider);
        //中和赤泥
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("neutralised_red_mud")
                .inputFluids(HydrochloricAcid.getFluid(9000))
                .inputFluids(RED_MUD.getFluid(2000))
                .outputFluids(NEUTRALISED_RED_MUD.getFluid(8000))
                .EUt(VA[GTValues.MV])
                .duration(100)
                .save(provider);
        //分离赤泥浆液
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("red_slurry")
                .inputFluids(NEUTRALISED_RED_MUD.getFluid(8000))
                .outputFluids(RED_SLURRY.getFluid(1000))
                .outputFluids(FERRIC_REE_CHLORIDE.getFluid(1000))
                .outputFluids(SaltWater.getFluid(6000))
                .EUt(VA[GTValues.MV])
                .duration(240)
                .save(provider);
        //分离含氯稀土
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("rare_earth_chloride_solution")
                .inputFluids(FERRIC_REE_CHLORIDE.getFluid(2000))
                .outputFluids(RARE_EARTH_CHLORIDE_SOLUTION.getFluid(1000))
                .outputFluids(Iron3Chloride.getFluid(1000))
                .EUt(VA[GTValues.HV])
                .duration(320)
                .save(provider);
        //硫酸钛酯
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("titanyl_sulfate")
                .inputFluids(SulfuricAcid.getFluid(2000))
                .inputFluids(RED_SLURRY.getFluid(2000))
                .outputFluids(TITANYL_SULFATE.getFluid(2000))
                .outputFluids(Water.getFluid(2000))
                .EUt(VA[GTValues.MV])
                .duration(160)
                .save(provider);
        //四氯化钛
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("titanium_tetrachloride")
                .inputFluids(HydrochloricAcid.getFluid(4000))
                .inputFluids(TITANYL_SULFATE.getFluid(1000))
                .outputFluids(TITANIUM_TETRACHLORIDE_V.getFluid(1000))
                .outputFluids(SulfuricAcid.getFluid(2000))
                .EUt((long) (0.5*VA[GTValues.EV]))
                .duration(160)
                .save(provider);
        //电解氟化钠
        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_sodium_fluoride")
                .inputItems(dust, SODIUM_FLUORIDE,2)
                .outputItems(dust, Sodium,1)
                .outputFluids(Fluorine.getFluid(1000))
                .EUt(VA[GTValues.MV])
                .duration(160)
                .save(provider);
        //水解氟化铝
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("hydrolyzing_aluminium_trifluoride")
                .inputFluids(Water.getFluid(6000))
                .inputItems(dust,ALUMINIUM_TRIFLUORIDE,8)
                .outputItems(dust,ALUMINIUM_HYDROXIDE,8)
                .outputFluids(HydrofluoricAcid.getFluid(6000))
                .EUt(VA[GTValues.MV])
                .duration(160)
                .save(provider);
        //合成氟化钠
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("sodium_fluoride")
                .inputFluids(Fluorine.getFluid(1000))
                .inputItems(dust,Sodium)
                .outputItems(dust,SODIUM_FLUORIDE,2)
                .EUt(VA[GTValues.MV])
                .duration(160)
                .save(provider);
        //离心铝土矿
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("red_slurry")
                .inputItems(dust,Bauxite)
                .outputItems(dust,Alumina)
                .chancedOutput(dust,Gallium,2500,0)
                .chancedOutput(dust,Rutile,3000,0)
                .EUt(VA[GTValues.MV])
                .duration(240)
                .save(provider);
        //催化剂处理铝土
        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder("catalyst_bauxite")
                .inputItems(dust,Bauxite,39)
                .notConsumable(BAUXITE_PROCESS_CATALYST)
                .inputFluids(HydrochloricAcid.getFluid(24000))
                .outputFluids(TITANIUM_TETRACHLORIDE_V.getFluid(3000))
                .outputFluids(FERRIC_REE_CHLORIDE.getFluid(1000))
                .outputFluids(Water.getFluid(12000))
                .EUt((long) (0.5*VA[GTValues.EV]))
                .duration(160)
                .save(provider);
    }
}
