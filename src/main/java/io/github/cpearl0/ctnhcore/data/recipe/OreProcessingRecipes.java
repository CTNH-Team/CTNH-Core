package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import com.mo_guang.ctpp.CTPP;
import com.mo_guang.ctpp.data.recipe.builder.create.*;
import com.mo_guang.ctpp.registry.CreateMaterials;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import slimeknights.mantle.recipe.helper.FluidOutput;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;

import java.util.function.Consumer;

public class OreProcessingRecipes {

    /** 匠魂炉需求温度：对应原 Create 烈焰人燃烧室"加热"等级 */
    private static final int SMELTERY_TEMPERATURE = 850;

    private static final Material[] MATERIAL = {
            GTMaterials.Hematite, GTMaterials.Magnetite,
            CTNHMaterials.PreciousAlloy,
            GTMaterials.Copper, GTMaterials.Diamond, GTMaterials.Tin, GTMaterials.Silver,
            GTMaterials.VanadiumMagnetite, GTMaterials.Spodumene, GTMaterials.RockSalt, GTMaterials.Salt,
            GTMaterials.Lepidolite, GTMaterials.Lazurite, GTMaterials.Lapis, GTMaterials.Sodalite,
            GTMaterials.Calcite, GTMaterials.Graphite, GTMaterials.Coal, GTMaterials.Zinc, GTMaterials.Gold,
            GTMaterials.Cassiterite, GTMaterials.Chalcopyrite, GTMaterials.Pyrite, GTMaterials.Iron,
            GTMaterials.YellowLimonite, GTMaterials.Malachite, GTMaterials.Oilsands, GTMaterials.Goethite,
            GTMaterials.NetherQuartz, GTMaterials.Quartzite, GTMaterials.Opal, GTMaterials.Redstone,
            GTMaterials.Ruby, GTMaterials.Cinnabar, GTMaterials.Nickel, GTMaterials.Lead, GTMaterials.Pentlandite,
            GTMaterials.Realgar, GTMaterials.GarnetYellow, GTMaterials.GarnetRed, GTMaterials.BasalticMineralSand,
            GTMaterials.GraniticMineralSand, GTMaterials.Beryllium, GTMaterials.Molybdenum,
            GTMaterials.Molybdenite, GTMaterials.Garnierite, GTMaterials.Cobaltite, GTMaterials.Topaz,
            GTMaterials.BlueTopaz, GTMaterials.Sulfur, GTMaterials.Chalcocite, GTMaterials.Bornite,
            GTMaterials.Sphalerite, GTMaterials.Saltpeter, GTMaterials.Diatomite, GTMaterials.Electrotine,
            GTMaterials.Alunite, GTMaterials.Grossular, GTMaterials.Pyrolusite, GTMaterials.Tantalite,
            GTMaterials.CertusQuartz, GTMaterials.Barite, GTMaterials.Spessartine, GTMaterials.Gypsum
    };

    public static void init(Consumer<FinishedRecipe> provider) {
        addOreProcessing(provider);
        addMetalMelting(provider);
        addAlloys(provider);
        addPressing(provider);
    }

    private static void addOreProcessing(Consumer<FinishedRecipe> provider) {
        for (Material material : MATERIAL) {
            if (material.hasProperty(PropertyKey.ORE)) {
                int oreMultiplier = material.getProperty(PropertyKey.ORE).getOreMultiplier();
                double basic_chance = 0.3;
                CrushingRecipeBuilder.builder("ctpp/raw_" + material.getName() + "_crushing")
                        .input(ChemicalHelper.get(TagPrefix.rawOre, material).getItem())
                        .output(ChemicalHelper.get(TagPrefix.crushed, material).getItem(), oreMultiplier)
                        .result(ChemicalHelper.get(TagPrefix.crushed, material).getItem().getDefaultInstance(),
                                basic_chance * oreMultiplier)
                        .save(provider);
                CrushingRecipeBuilder.builder("ctpp/crushed_" + material.getName() + "_ore_crushing")
                        .input(ChemicalHelper.get(TagPrefix.crushed, material).getItem())
                        .output(ChemicalHelper.get(TagPrefix.dustImpure, material).getItem(), oreMultiplier)
                        .result(ChemicalHelper.get(TagPrefix.dustImpure, material).getItem().getDefaultInstance(),
                                basic_chance * oreMultiplier)
                        .save(provider);
                SplashingRecipeBuilder.builder("ctpp/crushed_" + material.getName() + "_purified")
                        .input(ChemicalHelper.get(TagPrefix.crushed, material).getItem())
                        .result(ChemicalHelper.get(TagPrefix.crushedPurified, material).getItem().getDefaultInstance())
                        .save(provider);
                SplashingRecipeBuilder.builder("ctpp/impure_" + material.getName() + "_purified")
                        .input(ChemicalHelper.get(TagPrefix.dustImpure, material).getItem())
                        .result(ChemicalHelper.get(TagPrefix.dust, material).getItem().getDefaultInstance())
                        .save(provider);
                if (material.hasProperty(PropertyKey.INGOT)) {
                    SplashingRecipeBuilder.builder("ctpp/" + material.getName() + "_nuggets_from_purified_ore")
                            .input(ChemicalHelper.get(TagPrefix.crushedPurified, material).getItem())
                            .result(new ItemStack(ChemicalHelper.get(TagPrefix.nugget, material).getItem(), 11))
                            .result(new ItemStack(ChemicalHelper.get(TagPrefix.nugget, material).getItem(), 2), 0.4)
                            .save(provider);
                }
            }
        }
    }

    private static void addMetalMelting(Consumer<FinishedRecipe> provider) {
        // 匠魂熔化配方：需求温度 850（对应原 Create 烈焰人燃烧室"加热"等级），输入输出与原配方一致
        for (Material material : MATERIAL) {
            if (!material.hasProperty(PropertyKey.GEM) && material.hasProperty(PropertyKey.ORE)) {
                Material smeltInto = material.getProperty(PropertyKey.ORE).getDirectSmeltResult();
                if (smeltInto.hasFluid()) {
                    MeltingRecipeBuilder.melting(
                            Ingredient.of(ChemicalHelper.get(TagPrefix.crushed, material)),
                            FluidOutput.fromStack(smeltInto.getFluid(108)),
                            SMELTERY_TEMPERATURE, 10)
                            .addByproduct(FluidOutput.fromStack(CreateMaterials.SLAG.getFluid(100)))
                            .save(provider, CTPP.id("metal_smelting/ctpp/melting/curshed_" + material.getName()));
                    MeltingRecipeBuilder.melting(
                            Ingredient.of(ChemicalHelper.get(TagPrefix.crushedPurified, material)),
                            FluidOutput.fromStack(smeltInto.getFluid(144)),
                            SMELTERY_TEMPERATURE, 10)
                            .save(provider,
                                    CTPP.id("metal_smelting/ctpp/melting/purified_curshed_" + material.getName()));
                    MeltingRecipeBuilder.melting(
                            Ingredient.of(ChemicalHelper.get(TagPrefix.dustImpure, material)),
                            FluidOutput.fromStack(smeltInto.getFluid(144)),
                            SMELTERY_TEMPERATURE, 10)
                            .addByproduct(FluidOutput.fromStack(CreateMaterials.SLAG.getFluid(50)))
                            .save(provider,
                                    CTPP.id("metal_smelting/ctpp/melting/impure_" + material.getName() + "_dust"));
                    MeltingRecipeBuilder.melting(
                            Ingredient.of(ChemicalHelper.get(TagPrefix.dust, material)),
                            FluidOutput.fromStack(smeltInto.getFluid(144)),
                            SMELTERY_TEMPERATURE, 10)
                            .save(provider, CTPP.id("metal_smelting/ctpp/melting/" + material.getName() + "_dust"));
                }
            }
            if (material.hasProperty(PropertyKey.INGOT)) {
                MeltingRecipeBuilder.melting(
                        Ingredient.of(ChemicalHelper.get(TagPrefix.crushed, material)),
                        FluidOutput.fromStack(material.getFluid(144)),
                        SMELTERY_TEMPERATURE, 20)
                        .save(provider, CTPP.id("metal_smelting/ctpp/melting/" + material.getName() + "_ingot"));
            }
        }
    }

    private static void addAlloys(Consumer<FinishedRecipe> provider) {
        MixingRecipeBuilder.builder("ctpp/mixing/alloying/brass")
                .inputFluid(GTMaterials.Copper.getFluid(432))
                .inputFluid(GTMaterials.Zinc.getFluid(144))
                .heatRequirement(HeatCondition.HEATED)
                .resultFluid(GTMaterials.Brass.getFluid(576))
                .save(provider);
        MixingRecipeBuilder.builder("ctpp/mixing/alloying/bronze")
                .inputFluid(GTMaterials.Copper.getFluid(432))
                .inputFluid(GTMaterials.Tin.getFluid(144))
                .heatRequirement(HeatCondition.HEATED)
                .resultFluid(GTMaterials.Bronze.getFluid(576))
                .save(provider);
        MixingRecipeBuilder.builder("ctpp/mixing/alloying/potin")
                .inputFluid(GTMaterials.Bronze.getFluid(576))
                .inputFluid(GTMaterials.Lead.getFluid(72))
                .heatRequirement(HeatCondition.HEATED)
                .resultFluid(GTMaterials.Potin.getFluid(648))
                .save(provider);
        MixingRecipeBuilder.builder("ctpp/mixing/alloying/tin_alloy")
                .inputFluid(GTMaterials.Iron.getFluid(144))
                .inputFluid(GTMaterials.Tin.getFluid(144))
                .heatRequirement(HeatCondition.HEATED)
                .resultFluid(GTMaterials.TinAlloy.getFluid(288))
                .save(provider);
        MeltingRecipeBuilder.melting(
                Ingredient.of(ChemicalHelper.get(TagPrefix.dust, CreateMaterials.AndesiteAlloy)),
                FluidOutput.fromStack(CreateMaterials.AndesiteAlloy.getFluid(144)),
                SMELTERY_TEMPERATURE, 22)
                .save(provider, CTPP.id("metal_smelting/ctpp/melting/alloying_dust"));
        MeltingRecipeBuilder.melting(
                Ingredient.of(ChemicalHelper.get(TagPrefix.ingot, CreateMaterials.AndesiteAlloy)),
                FluidOutput.fromStack(CreateMaterials.AndesiteAlloy.getFluid(144)),
                SMELTERY_TEMPERATURE, 22)
                .save(provider, CTPP.id("metal_smelting/ctpp/melting/alloying_ingot"));
    }

    /**
     * Create 冲压机（机械动力压片）配方：1 个锭 → 1 个对应板材。
     * 与 GTCEu 卷板机锭→板材配方使用相同的材料判定条件，覆盖所有已定义可压板材料。
     */
    private static void addPressing(Consumer<FinishedRecipe> provider) {
        for (Material material : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            if (!material.hasProperty(PropertyKey.INGOT) || !material.hasFlag(MaterialFlags.GENERATE_PLATE) ||
                    material.hasFlag(MaterialFlags.NO_WORKING) || material.hasFlag(MaterialFlags.NO_SMASHING)) {
                continue;
            }
            ItemStack ingot = ChemicalHelper.get(TagPrefix.ingot, material);
            ItemStack plate = ChemicalHelper.get(TagPrefix.plate, material);
            if (ingot.isEmpty() || plate.isEmpty()) {
                continue;
            }
            PressingRecipeBuilder.builder(material.getName())
                    .input(ingot)
                    .output(plate)
                    .save(provider);
        }
    }
}
