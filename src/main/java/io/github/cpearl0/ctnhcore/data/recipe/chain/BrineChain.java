package io.github.cpearl0.ctnhcore.data.recipe.chain;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.attribute.FluidAttributes;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static io.github.cpearl0.ctnhcore.registry.CTNHMaterials.*;
public class BrineChain {
    public static void init(Consumer<FinishedRecipe> provider) {
        IodineChain(provider);
        BromineChain(provider);
    }
    public static void init() {
        addFluid(GTMaterials.Bromine);
        addDust(GTMaterials.Iodine);
        IodizedBrine = new Material.Builder(CTNHCore.id("iodized_brine"))
                .fluid().color(0x525246)
                .buildAndRegister()
                .setFormula("I?", false);
        IodineBrineMixture = new Material.Builder(CTNHCore.id("iodine_brine_mixture"))
                .fluid()
                .color(0x525234)
                .buildAndRegister()
                .setFormula("I?Cl", false);

        //  18059 Brominated Brine
        BrominatedBrine = new Material.Builder(CTNHCore.id("brominated_brine"))
                .fluid()
                .color(0xA9A990)
                .buildAndRegister()
                .setFormula("Br?", false);
        //  24039 Iodine Slurry
        IodineSlurry = new Material.Builder(CTNHCore.id("iodine_slurry"))
                .fluid()
                .color(0x292923)
                .buildAndRegister()
                .setFormula("I?", false);

        //  24040 Acidic Brominated Brine
        AcidicBrominatedBrine = new Material.Builder(CTNHCore.id("acidic_brominated_brine"))
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().attribute(FluidAttributes.ACID))
                .color(0xC6A76F)
                .buildAndRegister()
                .setFormula("Br?(H2SO4)Cl", true);

        //  24041 Bromine Sulfate Solutions
        BromineSulfateSolution = new Material.Builder(CTNHCore.id("bromine_sulfate_solution"))
                .fluid()
                .color(0xCC9966)
                .buildAndRegister()
                .setFormula("H2SO4Br(H2O)Cl2", true);

        //  24042 Overheated Bromine Sulfate Gas
        OverheatedBromineSulfateSolution = new Material.Builder(CTNHCore.id("overheated_bromine_sulfate_gas"))
                .gas()
                .color(0xC69337)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister()
                .setFormula("H2SO4Br(H2O)2Cl2", true);

        //  24043 Wet Bromine
        WetBromine = new Material.Builder(CTNHCore.id("wet_bromine"))
                .gas()
                .color(0xDB5C5C)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister()
                .setFormula("Br(H2O)", true);

        //  24044 Debrominated Water
        DebrominatedWater = new Material.Builder(CTNHCore.id("debrominated_water"))
                .fluid()
                .color(0x24A3A3)
                .components(GTMaterials.Hydrogen, 2, GTMaterials.Oxygen, 1)
                .buildAndRegister();
    }
    private static void IodineChain(Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("iodine_brine")
                .inputItems(TagPrefix.dust, GTMaterials.Saltpeter)
                .inputFluids(GTMaterials.SaltWater.getFluid(1000))
                .outputItems(TagPrefix.dust, GTMaterials.Potassium)
                .outputFluids(IodizedBrine.getFluid(1000))
                .circuitMeta(1)
                .EUt(1280)
                .duration(240)
                .blastFurnaceTemp(1128)
                .save(provider);

        //  I? + 0.3 Cl -> I?Cl
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("iodine_brine_mixture")
                .inputFluids(IodizedBrine.getFluid(1000))
                .inputFluids(GTMaterials.Chlorine.getFluid(300))
                .outputFluids(IodineBrineMixture.getFluid(1300))
                .EUt(480)
                .duration(240)
                .save(provider);

        //  I?Cl -> Br? + I?
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("brominated_brine")
                .inputFluids(IodineBrineMixture.getFluid(1300))
                .outputFluids(BrominatedBrine.getFluid(1000))
                .outputFluids(IodineSlurry.getFluid(300))
                .EUt(980)
                .duration(120)
                .save(provider);

        //  I? -> I
        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder("iodine")
                .inputFluids(IodineSlurry.getFluid(1200))
                .outputItems(TagPrefix.dust, GTMaterials.Iodine)
                .EUt(1280)
                .duration(200)
                .save(provider);
    }
    private static void BromineChain(Consumer<FinishedRecipe> provider) {
        //  Br? + H2SO4 -> Br?(H2SO4)

        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("acidic_brominated_brine")
                .inputFluids(BrominatedBrine.getFluid(1000))
                .inputFluids(GTMaterials.SulfuricAcid.getFluid(1000))
                .outputFluids(AcidicBrominatedBrine.getFluid(1000))
                .EUt(480)
                .duration(200)
                .save(provider);

        //  Br?(H2SO4) + SO2 + H2O -> H2SO4Br(H2O)Cl2
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("bromine_sulfate_solution")
                .inputFluids(AcidicBrominatedBrine.getFluid(1000))
                .inputFluids(GTMaterials.SulfurDioxide.getFluid(1000))
                .inputFluids(GTMaterials.Water.getFluid(1000))
                .circuitMeta(3)
                .outputFluids(BromineSulfateSolution.getFluid(1000))
                .outputFluids(GTMaterials.SaltWater.getFluid(1000))
                .EUt(480)
                .duration(200)
                .save(provider);

        //  2H2SO4Br(H2O)Cl2 + H2O -> 3H2SO4Br(H2O)2Cl2
        GTRecipeTypes.CRACKING_RECIPES.recipeBuilder("overheated_bromine_sulfate_gas")
                .inputFluids(BromineSulfateSolution.getFluid(2000))
                .inputFluids(GTMaterials.Steam.getFluid(1000))
                .outputFluids(OverheatedBromineSulfateSolution.getFluid(3000))
                .EUt(GTValues.VA[GTValues.HV])
                .duration(400)
                .save(provider);

        //  3H2SO4Br(H2O)2Cl2 -> Br(H2O) + H2O + 2Cl + H2SO4
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("overheated_bromine_sulfate_gas")
                .inputFluids(OverheatedBromineSulfateSolution.getFluid(3000))
                .outputFluids(WetBromine.getFluid(1000))
                .outputFluids(DebrominatedWater.getFluid(1000))
                .outputFluids(GTMaterials.Chlorine.getFluid(2000))
                .outputFluids(GTMaterials.SulfuricAcid.getFluid(1000))
                .EUt(GTValues.VA[GTValues.HV])
                .duration(280)
                .save(provider);

        //  Br(H2O) -> Br + H2O (lost)
        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder("wet_bromine")
                .inputFluids(WetBromine.getFluid(1000))
                .outputFluids(GTMaterials.Bromine.getFluid(1000))
                .EUt(360)
                .duration(80)
                .save(provider);

        //  Salt Water recycle
        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder("debrominated_water")
                .inputFluids(DebrominatedWater.getFluid(1000))
                .outputFluids(GTMaterials.SaltWater.getFluid(100))
                .EUt(360)
                .duration(80)
                .save(provider);
    }
}
