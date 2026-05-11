package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.NewExplosivesProductionMaterials;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.data.materials.AviationFabricMaterials.*;
import static io.github.cpearl0.ctnhcore.data.materials.AviationFabricMaterials.BIS_TRICHLOROMETHYL_BENZENE;
import static io.github.cpearl0.ctnhcore.data.materials.AviationFabricMaterials.COBALT_BROMIDE;
import static io.github.cpearl0.ctnhcore.data.materials.AviationFabricMaterials.CO_MN_BR_CATALYST;
import static io.github.cpearl0.ctnhcore.data.materials.AviationFabricMaterials.FIBER_GLASS;
import static io.github.cpearl0.ctnhcore.data.materials.AviationFabricMaterials.KAPTON_K;
import static io.github.cpearl0.ctnhcore.data.materials.AviationFabricMaterials.MANGANESE_ACETATE;
import static io.github.cpearl0.ctnhcore.data.materials.AviationFabricMaterials.MANGANESE_BROMIDE;
import static io.github.cpearl0.ctnhcore.data.materials.AviationFabricMaterials.NITROANILINE;
import static io.github.cpearl0.ctnhcore.data.materials.AviationFabricMaterials.OXYDIANILINE;
import static io.github.cpearl0.ctnhcore.data.materials.AviationFabricMaterials.PARA_ARAMID;
import static io.github.cpearl0.ctnhcore.data.materials.AviationFabricMaterials.PARA_PHENYLENEDIAMINE;
import static io.github.cpearl0.ctnhcore.data.materials.AviationFabricMaterials.PYROMETILLIC_DIANHYDRIDE;
import static io.github.cpearl0.ctnhcore.data.materials.AviationFabricMaterials.TEREPHTHALIC_ACID;
import static io.github.cpearl0.ctnhcore.data.materials.AviationFabricMaterials.TEREPHTHALOYL_CHLORIDE;
import static io.github.cpearl0.ctnhcore.registry.CTNHItems.SPACE_FABRIC;

/** Converted from server_scripts/src/gtceu/chain/SpaceFabric.js */
public class SpaceFabric {

    public static void init(Consumer<FinishedRecipe> provider) {
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
    }
}
