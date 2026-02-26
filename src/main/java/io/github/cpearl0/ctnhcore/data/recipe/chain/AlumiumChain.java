package io.github.cpearl0.ctnhcore.data.recipe.chain;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.attribute.FluidAttributes;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import tech.vixhentx.mcmod.ctnhlib.registrate.builders.CTNHMaterial;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static dev.engine_room.flywheel.impl.visualization.storage.Transaction.remove;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.*;


public class AlumiumChain {
    public static void init(Consumer<FinishedRecipe> provider) {
        remove(provider);

        //离心
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("electrolyzing_green_sapphire")
                .inputItems(dust, GreenSapphire,5)//绿色蓝宝石
                .outputItems(dust, Alumina,5)
                .EUt(GTValues.VA[GTValues.HV])
                .duration(100)
                .save(provider);

        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("electrolyzing_sapphire")
                .inputItems(dust, Sapphire,5)//蓝宝石
                .outputItems(dust, Alumina,5)
                .EUt(GTValues.VA[GTValues.HV])
                .duration(100)
                .save(provider);

        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("electrolyzing_ruby")
                .inputItems(dust, Ruby,6)//红宝石
                .outputItems(dust, Alumina,5)
                .outputItems(dust, ChromiumTrioxide,1)
                .EUt(GTValues.VA[GTValues.HV])
                .duration(100)
                .save(provider);

        //电解
        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_pyrope")
                .inputItems(dust, Pyrope,20)//镁铝榴石
                .outputItems(dust, Alumina,5)
                .outputItems(dust, SiliconDioxide,9)
                .outputItems(dust, Magnesium,3)
                .outputFluids(Oxygen.getFluid(3000))
                .EUt(GTValues.VA[GTValues.MV])
                .duration(200)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_granite_red")
                .inputItems(dust, GraniteRed,6)//红色花岗岩
                .outputItems(dust, Alumina,5)
                .outputItems(dust, PotassiumFeldspar,1)
                .EUt(GTValues.VA[GTValues.MV])
                .duration(60)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_potassium_feldspar")
                .inputItems(dust, PotassiumFeldspar,22)//钾长石
                .outputItems(dust, Alumina,5)
                .outputItems(dust, SiliconDioxide,6)
                .outputItems(dust, Potassium,6)
                .outputFluids(Oxygen.getFluid(11000))
                .EUt(GTValues.VA[GTValues.MV])
                .duration(200)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_pollucite")
                .inputItems(dust, Pollucite,22)//铯榴石
                .outputItems(dust, Alumina,5)
                .outputItems(dust, SiliconDioxide,12)
                .outputItems(dust, Caesium,2)
                .outputFluids(Water.getFluid(2000))
                .outputFluids(Oxygen.getFluid(1000))
                .EUt(GTValues.VA[GTValues.MV])
                .duration(280)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_kyanite")
                .inputItems(dust, Kyanite,8)//蓝晶石
                .outputItems(dust, Alumina,5)
                .outputItems(dust, SiliconDioxide,3)
                .EUt(GTValues.VA[GTValues.MV])
                .duration(80)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_spodumene")
                .inputItems(dust, Spodumene,20)//锂辉石
                .outputItems(dust, Alumina,5)
                .outputItems(dust, SiliconDioxide,12)
                .outputItems(dust, Lithium,2)
                .outputFluids(Oxygen.getFluid(1000))
                .EUt(GTValues.VA[GTValues.MV])
                .duration(180)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_spessartine")
                .inputItems(dust, Spessartine,20)//锰铝榴石
                .outputItems(dust, Alumina,5)
                .outputItems(dust, SiliconDioxide,9)
                .outputItems(dust, Manganese,3)
                .outputFluids(Oxygen.getFluid(3000))
                .EUt(GTValues.VA[GTValues.MV])
                .duration(220)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_mica")
                .inputItems(dust, Lepidolite,38)//云母
                .outputItems(dust, Alumina,15)
                .outputItems(dust, SiliconDioxide,18)
                .outputItems(dust, Potassium,2)
                .outputFluids(Fluorine.getFluid(4000))
                .EUt(GTValues.VA[GTValues.MV])
                .duration(380)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_lepidolite")
                .inputItems(dust, Lepidolite,20)//锂云母
                .outputItems(dust, Alumina,10)
                .outputItems(dust, Lithium,3)
                .outputItems(dust, Potassium,1)
                .outputFluids(Oxygen.getFluid(4000))
                .outputFluids(Fluorine.getFluid(2000))
                .EUt(GTValues.VA[GTValues.MV])
                .duration(160)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_grossular")
                .inputItems(dust, Grossular,20)//钙铝榴石
                .outputItems(dust, Alumina,5)
                .outputItems(dust, SiliconDioxide,9)
                .outputItems(dust, Calcium,3)
                .outputFluids(Oxygen.getFluid(3000))
                .EUt(GTValues.VA[GTValues.MV])
                .duration(220)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_glauconite_sand")
                .inputItems(dust, GlauconiteSand,21)//海绿石沙
                .outputItems(dust, Alumina,10)
                .outputItems(dust, Manganese,2)
                .outputItems(dust, Potassium,1)
                .outputFluids(Oxygen.getFluid(6000))
                .outputFluids(Hydrogen.getFluid(2000))
                .EUt(GTValues.VA[GTValues.MV])
                .duration(220)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("electrolyzing_emerald")
                .inputItems(dust, Emerald,29)//绿宝石
                .outputItems(dust, Alumina,50)
                .outputItems(dust, SiliconDioxide,18)
                .outputItems(dust, Beryllium,3)
                .outputFluids(Oxygen.getFluid(3000))
                .EUt(GTValues.VA[GTValues.MV])
                .duration(260)
                .save(provider);
    }
}
