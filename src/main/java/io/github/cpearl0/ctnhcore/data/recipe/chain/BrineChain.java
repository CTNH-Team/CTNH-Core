package io.github.cpearl0.ctnhcore.data.recipe.chain;

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

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class BrineChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        IodineChain(provider);
        BromineChain(provider);
    }

    public static void init() {
        addFluid(GTMaterials.Bromine);
        addDust(GTMaterials.Iodine);
        Seawater = REGISTRATE.material(CTNHCore.id("seawater"))
                .cnlang("海水")
                .liquid(new FluidBuilder().temperature(288))
                .color(0x3B7BB0)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("Cl?Br?I?[H2O]", false);
        IodizedBrine = REGISTRATE.material(CTNHCore.id("iodized_brine"))
                .cnlang("含碘盐水")
                .fluid().color(0x525246)
                .buildAndRegister()
                .setFormula("I?", false);
        IodineBrineMixture = REGISTRATE.material(CTNHCore.id("iodine_brine_mixture"))
                .cnlang("浓缩碘盐水混合物")
                .fluid()
                .color(0x525234)
                .buildAndRegister()
                .setFormula("I?Cl", false);

        // 18059 Brominated Brine
        BrominatedBrine = REGISTRATE.material(CTNHCore.id("brominated_brine"))
                .cnlang("含溴盐水")
                .fluid()
                .color(0xA9A990)
                .buildAndRegister()
                .setFormula("Br?", false);
        // 24039 Iodine Slurry
        IodineSlurry = REGISTRATE.material(CTNHCore.id("iodine_slurry"))
                .cnlang("碘浆液")
                .fluid()
                .color(0x292923)
                .buildAndRegister()
                .setFormula("I?", false);

        // 24040 Acidic Brominated Brine
        AcidicBrominatedBrine = REGISTRATE.material(CTNHCore.id("acidic_brominated_brine"))
                .cnlang("酸化含溴盐水")
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().attribute(FluidAttributes.ACID))
                .color(0xC6A76F)
                .buildAndRegister()
                .setFormula("Br?(H2SO4)Cl", true);

        // 24041 Bromine Sulfate Solutions
        BromineSulfateSolution = REGISTRATE.material(CTNHCore.id("bromine_sulfate_solution"))
                .cnlang("硫酸溴溶液")
                .fluid()
                .color(0xCC9966)
                .buildAndRegister()
                .setFormula("H2SO4Br(H2O)Cl2", true);

        // 24042 Overheated Bromine Sulfate Gas
        OverheatedBromineSulfateSolution = REGISTRATE.material(CTNHCore.id("overheated_bromine_sulfate_gas"))
                .cnlang("过热硫酸溴气")
                .gas()
                .color(0xC69337)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister()
                .setFormula("H2SO4Br(H2O)2Cl2", true);

        // 24043 Wet Bromine
        WetBromine = REGISTRATE.material(CTNHCore.id("wet_bromine"))
                .cnlang("湿溴气")
                .gas()
                .color(0xDB5C5C)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister()
                .setFormula("Br(H2O)", true);

        // 24044 Debrominated Water
        DebrominatedWater = REGISTRATE.material(CTNHCore.id("debrominated_water"))
                .cnlang("脱溴盐水")
                .fluid()
                .color(0x24A3A3)
                .components(GTMaterials.Hydrogen, 2, Oxygen, 1)
                .buildAndRegister();
    }

    private static void IodineChain(Consumer<FinishedRecipe> provider) {
        // 硝酸钾配方修改
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("potassium_nitrate_synthesis")
                .inputItems(TagPrefix.dust, GTMaterials.PotassiumCarbonate, 6)
                .inputFluids(GTMaterials.NitricAcid.getFluid(2000))
                .outputItems(TagPrefix.dust, Saltpeter, 10)
                .outputFluids(GTMaterials.Water.getFluid(1000))
                .outputFluids(GTMaterials.CarbonDioxide.getFluid(1000))
                .EUt(GTValues.VA[GTValues.HV])
                .duration(200)
                .save(provider);

        // 硝酸钾制氨
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("ammonia_from_potassium_nitrate")
                .inputItems(TagPrefix.dust, GTMaterials.Saltpeter, 5)
                .inputFluids(GTMaterials.Hydrogen.getFluid(8000))
                .outputFluids(GTMaterials.Ammonia.getFluid(1000))
                .outputItems(TagPrefix.dust, GTMaterials.PotassiumHydroxide, 3)
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .EUt(GTValues.VA[GTValues.HV])
                .duration(300)
                .save(provider);

        // 海水粗提盐水
        CTNHRecipeTypes.DESALTING.recipeBuilder("seawater_saltwater")
                .inputFluids(Seawater.getFluid(1000))
                .chancedOutput(dust, GTMaterials.MagnesiumChloride, 2000, 0)
                .chancedOutput(dust, GTMaterials.CalciumChloride, 1000, 0)
                .outputFluids(SaltWater.getFluid(500))
                .circuitMeta(2)
                .EUt(240)
                .duration(300)
                .blastFurnaceTemp(373)
                .save(provider);

        // 海水精提溴碘
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("iodine_brine")
                .inputItems(dust, GTMaterials.Saltpeter, 5)
                .inputFluids(Seawater.getFluid(2000))
                .outputItems(dust, Potassium)
                .outputFluids(IodizedBrine.getFluid(1000))
                .circuitMeta(1)
                .EUt(1280)
                .duration(240)
                .blastFurnaceTemp(640)
                .save(provider);

        // I? + 0.3 Cl -> I?Cl
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("iodine_brine_mixture")
                .inputFluids(IodizedBrine.getFluid(1000))
                .inputFluids(GTMaterials.Chlorine.getFluid(300))
                .outputFluids(IodineBrineMixture.getFluid(1300))
                .EUt(480)
                .duration(240)
                .save(provider);

        // I?Cl -> Br? + I?
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("brominated_brine")
                .inputFluids(IodineBrineMixture.getFluid(1300))
                .outputFluids(BrominatedBrine.getFluid(1000))
                .outputFluids(IodineSlurry.getFluid(300))
                .EUt(980)
                .duration(120)
                .save(provider);

        // I? -> I
        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder("iodine")
                .inputFluids(IodineSlurry.getFluid(1200))
                .outputItems(dust, GTMaterials.Iodine)
                .EUt(1280)
                .duration(200)
                .save(provider);
    }

    private static void BromineChain(Consumer<FinishedRecipe> provider) {
        // Br? + H2SO4 -> Br?(H2SO4)

        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("acidic_brominated_brine")
                .inputFluids(BrominatedBrine.getFluid(1000))
                .inputFluids(GTMaterials.SulfuricAcid.getFluid(1000))
                .outputFluids(AcidicBrominatedBrine.getFluid(1000))
                .EUt(480)
                .duration(200)
                .save(provider);

        // Br?(H2SO4) + SO2 + H2O -> H2SO4Br(H2O)Cl2
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("bromine_sulfate_solution")
                .inputFluids(AcidicBrominatedBrine.getFluid(1000))
                .inputFluids(GTMaterials.SulfurDioxide.getFluid(1000))
                .inputFluids(GTMaterials.Water.getFluid(1000))
                .circuitMeta(3)
                .outputFluids(BromineSulfateSolution.getFluid(1000))
                .outputFluids(SaltWater.getFluid(1000))
                .EUt(480)
                .duration(200)
                .save(provider);

        // 2H2SO4Br(H2O)Cl2 + H2O -> 3H2SO4Br(H2O)2Cl2
        GTRecipeTypes.CRACKING_RECIPES.recipeBuilder("overheated_bromine_sulfate_gas")
                .inputFluids(BromineSulfateSolution.getFluid(2000))
                .inputFluids(GTMaterials.Steam.getFluid(1000))
                .outputFluids(OverheatedBromineSulfateSolution.getFluid(3000))
                .EUt(VA[GTValues.HV])
                .duration(400)
                .save(provider);

        // 3H2SO4Br(H2O)2Cl2 -> Br(H2O) + H2O + 2Cl + H2SO4
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("overheated_bromine_sulfate_gas")
                .inputFluids(OverheatedBromineSulfateSolution.getFluid(3000))
                .outputFluids(WetBromine.getFluid(1000))
                .outputFluids(DebrominatedWater.getFluid(1000))
                .outputFluids(GTMaterials.Chlorine.getFluid(2000))
                .outputFluids(GTMaterials.SulfuricAcid.getFluid(1000))
                .EUt(VA[GTValues.HV])
                .duration(280)
                .save(provider);

        // Br(H2O) -> Br + H2O (lost)
        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder("wet_bromine")
                .inputFluids(WetBromine.getFluid(1000))
                .outputFluids(GTMaterials.Bromine.getFluid(1000))
                .EUt(360)
                .duration(80)
                .save(provider);

        // Salt Water recycle
        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder("debrominated_water")
                .inputFluids(DebrominatedWater.getFluid(1000))
                .outputFluids(SaltWater.getFluid(100))
                .EUt(360)
                .duration(80)
                .save(provider);
    }
}
