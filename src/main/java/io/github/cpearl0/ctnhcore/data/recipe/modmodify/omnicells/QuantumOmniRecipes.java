package io.github.cpearl0.ctnhcore.data.recipe.modmodify.omnicells;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.EnderIOMaterials;
import io.github.cpearl0.ctnhcore.data.recipe.RecipeRemoval;
import io.github.cpearl0.ctnhcore.data.recipe.RecipeRemoval.RemoveFilter;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import appeng.core.definitions.AEItems;
import com.enderio.base.common.init.EIOItems;
import com.wintercogs.ae2omnicells.common.init.OCItems;
import io.github.lounode.ae2cs.common.init.AECSItems;

import java.util.List;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.data.recipe.CustomTags.*;

public class QuantumOmniRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("ender_ingot"))
                .inputItems(TagPrefix.ingot, EnderIOMaterials.VibrantAlloy)
                .inputItems(TagPrefix.ingot, EnderIOMaterials.EndSteel)
                .outputItems(OCItems.ENDER_INGOT, 2)
                .EUt(VA[GTValues.HV])
                .duration(200)
                .save(provider);
        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("charged_ender_ingot"))
                .inputItems(OCItems.ENDER_INGOT)
                .inputItems(EIOItems.ENDER_CRYSTAL)
                .inputFluids(GTMaterials.EnderEye.getFluid(144))
                .outputItems(OCItems.CHARGED_ENDER_INGOT)
                .EUt(VA[GTValues.HV])
                .duration(150)
                .save(provider);
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("quantum_omni_cell_housing"))
                .inputItems(OCItems.CHARGED_ENDER_INGOT, 4)
                .inputItems(GTBlocks.CASING_LAMINATED_GLASS.asItem(), 2)
                .inputItems(GTItems.QUANTUM_EYE, 2)
                .inputItems(AEItems.SINGULARITY.stack(), 1)
                .inputFluids(EnderIOMaterials.PulsatingAlloy.getFluid(288))
                .outputItems(OCItems.QUANTUM_OMNI_CELL_HOUSING)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(VA[GTValues.HV])
                .duration(100)
                .save(provider);
        GTRecipeTypes.LASER_ENGRAVER_RECIPES.recipeBuilder(CTNHCore.id("multidimensional_expansion_print_press"))
                .inputItems(AECSItems.ENDER_BLANK_PRINT_PRESS.asItem())
                .notConsumable(TagPrefix.lens, CTNHMaterials.SolarFlareBlackDiamond, 1)
                .outputItems(OCItems.MULTIDIMENSIONAL_EXPANSION_PRINT_PRESS)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(VA[GTValues.IV])
                .duration(4000)
                .save(provider);
        GTRecipeTypes.LASER_ENGRAVER_RECIPES.recipeBuilder(CTNHCore.id("omni_link_print_press"))
                .inputItems(AECSItems.ENDER_BLANK_PRINT_PRESS.asItem())
                .notConsumable(TagPrefix.lens, CTNHMaterials.ArcaneCrystal, 1)
                .outputItems(OCItems.OMNI_LINK_PRINT_PRESS)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(VA[GTValues.IV])
                .duration(4000)
                .save(provider);
        GTRecipeTypes.LASER_ENGRAVER_RECIPES.recipeBuilder(CTNHCore.id("complex_link_print_press"))
                .inputItems(AECSItems.ENDER_BLANK_PRINT_PRESS.asItem())
                .notConsumable(TagPrefix.lens, CTNHMaterials.ToxicSwampAmber, 1)
                .outputItems(OCItems.COMPLEX_LINK_PRINT_PRESS)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(VA[GTValues.IV])
                .duration(4000)
                .save(provider);
        GTRecipeTypes.EXTRUDER_RECIPES.recipeBuilder(CTNHCore.id("multidimensional_expansion_circuit_print"))
                .inputItems(AEItems.SINGULARITY.stack(), 1)
                .notConsumable(OCItems.MULTIDIMENSIONAL_EXPANSION_PRINT_PRESS)
                .outputItems(OCItems.MULTIDIMENSIONAL_EXPANSION_CIRCUIT_PRINT, 1)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(VA[GTValues.LuV])
                .duration(600)
                .save(provider);
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("multidimensional_expansion_processor"))
                .inputItems(OCItems.MULTIDIMENSIONAL_EXPANSION_CIRCUIT_PRINT, 1)
                .inputItems(TagPrefix.plate, CTNHMaterials.SolarFlareBlackDiamond, 4)
                .inputItems(GTItems.HIGHLY_ADVANCED_SOC, 16)
                .outputItems(OCItems.MULTIDIMENSIONAL_EXPANSION_PROCESSOR, 1)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(VA[GTValues.IV])
                .duration(200)
                .save(provider);

        // 例子-1
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("quantum_omni_cell_1k"))
                .inputItems(OCItems.QUANTUM_OMNI_CELL_HOUSING, 1)
                .inputItems(OCItems.QUANTUM_OMNI_CELL_COMPONENT_1K, 1)
                .inputItems(OCItems.COMPLEX_OMNI_CELL_COMPONENT_1K, 1)
                .inputItems(OCItems.OMNI_CELL_COMPONENT_1K, 1)
                .inputFluids(EnderIOMaterials.PulsatingAlloy.getFluid(144))
                .outputItems(OCItems.QUANTUM_OMNI_CELL_1K)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(VA[EV])
                .duration(40)
                .save(provider);
        // 例子-2
        GTRecipeTypes.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("quantum_omni_cell_component_1k"))
                .inputItems(GTItems.CARBON_MESH, 16)
                .inputItems(TagPrefix.plateDense, GTMaterials.TungstenSteel, 4)
                .inputItems(CustomTags.IV_CIRCUITS, 3) // 这里需要改
                .inputItems(CustomTags.EV_CIRCUITS, 2) // 这里需要改
                .inputItems(OCItems.MULTIDIMENSIONAL_EXPANSION_PROCESSOR)
                .inputFluids(EnderIOMaterials.PulsatingAlloy.getFluid(144))
                .outputItems(OCItems.QUANTUM_OMNI_CELL_COMPONENT_1K) // 这里需要改
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(VA[GTValues.IV])
                .duration(200)
                .save(provider);

        // 4K~256M 的元件与成品细胞，此前被删除后一直没有补回
        addQuantumCellChain(provider);
    }

    private static final RegistryObject<?>[] QUANTUM_OMNI_COMPONENTS = {
            // {高等级电路常量, 低等级电路常量, 输出物品, 配方名称后缀}
            OCItems.QUANTUM_OMNI_CELL_COMPONENT_1K,
            OCItems.QUANTUM_OMNI_CELL_COMPONENT_4K,
            OCItems.QUANTUM_OMNI_CELL_COMPONENT_16K,
            OCItems.QUANTUM_OMNI_CELL_COMPONENT_64K,
            OCItems.QUANTUM_OMNI_CELL_COMPONENT_256K,
            OCItems.QUANTUM_OMNI_CELL_COMPONENT_1M,
            OCItems.QUANTUM_OMNI_CELL_COMPONENT_4M,
            OCItems.QUANTUM_OMNI_CELL_COMPONENT_16M,
            OCItems.QUANTUM_OMNI_CELL_COMPONENT_64M,
            OCItems.QUANTUM_OMNI_CELL_COMPONENT_256M
    };

    // void registerComponentRecipes(){
    // for(int i = 0; i< QUANTUM_OMNI_COMPONENTS.length; i++){
    // var lowTierCircuit = CIRCUITS_ARRAY[EV + i];
    // var high
    // }
    // }
    //
    // 批量注册量子元件配方
    // for (int i = 0; i < QUANTUM_OMNI_COMPONENT_TIERS.length; i++) {
    // Object[] tierData = QUANTUM_OMNI_COMPONENT_TIERS[i];
    //
    // int highTier = (int) tierData[0]; // 高等级电路常量
    // int lowTier = (int) tierData[1]; // 低等级电路常量
    // ItemLike outputItem = (ItemLike) tierData[2]; // 当前等级输出物品
    // String nameSuffix = (String) tierData[3]; // 配方名称后缀
    //
    // // 获取前一级存储元件（如果存在）
    // ItemLike previousComponent = (i > 0) ?
    // (ItemLike) QUANTUM_OMNI_COMPONENT_TIERS[i-1][2] : null;
    //
    // // ===== 原始配方 =====
    // GTRecipeTypes.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("quantum_omni_cell_component_" + nameSuffix +
    // "_ctnh")
    // .inputItems(GTItems.CARBON_MESH, 16)
    // .inputItems(TagPrefix.plateDense, GTMaterials.TungstenSteel, 4)
    // .inputItems(getCircuitTagByTier(highTier), 3) // 高等级电路
    // .inputItems(getCircuitTagByTier(lowTier), 2) // 低等级电路
    // .inputItems(OCItems.MULTIDIMENSIONAL_EXPANSION_PROCESSOR)
    // .inputFluids(EnderIOMaterials.PulsatingAlloy.getFluid(144))
    // .outputItems(outputItem)
    // .cleanroom(CleanroomType.CLEANROOM)
    // .EUt(VA[highTier])
    // .duration(200)
    // .save(provider);
    //
    // // ===== 新配方：使用前一级元件替代电路 =====
    // if (i > 0 && previousComponent != null) { // 第一级(1K)没有前一级元件
    // GTRecipeTypes.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("quantum_omni_cell_component_" + nameSuffix +
    // "_alt_ctnh")
    // .inputItems(GTItems.CARBON_MESH, 16)
    // .inputItems(TagPrefix.plateDense, GTMaterials.TungstenSteel, 4)
    // .inputItems(getCircuitTagByTier(highTier), 1) // 减少到1个高等级电路
    // .inputItems(previousComponent, 1) // 用1个前一级元件替代部分电路
    // .inputItems(OCItems.MULTIDIMENSIONAL_EXPANSION_PROCESSOR)
    // .inputFluids(EnderIOMaterials.PulsatingAlloy.getFluid(144))
    // .outputItems(outputItem)
    // .cleanroom(CleanroomType.CLEANROOM)
    // .EUt(VA[highTier]) // 电压等级不变
    // .duration(200) // 合成时间也可以调整，这里保持相同
    // .save(provider);
    // }
    // }
    // 定义成品量子元件的信息数组（删除AE元件）
    // private static final Object[][] QUANTUM_OMNI_CELL_TIERS = {
    // // {电压等级, 电压分组索引, 外壳, 量子元件, 复杂元件, 普通元件, 输出成品}
    // {GTValues.EV, 0, OCItems.QUANTUM_OMNI_CELL_HOUSING, OCItems.QUANTUM_OMNI_CELL_COMPONENT_1K,
    // OCItems.COMPLEX_OMNI_CELL_COMPONENT_1K, OCItems.OMNI_CELL_COMPONENT_1K, OCItems.QUANTUM_OMNI_CELL_1K},
    // {GTValues.EV, 0, OCItems.QUANTUM_OMNI_CELL_HOUSING, OCItems.QUANTUM_OMNI_CELL_COMPONENT_4K,
    // OCItems.COMPLEX_OMNI_CELL_COMPONENT_4K, OCItems.OMNI_CELL_COMPONENT_4K, OCItems.QUANTUM_OMNI_CELL_4K},
    // {GTValues.IV, 1, OCItems.QUANTUM_OMNI_CELL_HOUSING, OCItems.QUANTUM_OMNI_CELL_COMPONENT_16K,
    // OCItems.COMPLEX_OMNI_CELL_COMPONENT_16K, OCItems.OMNI_CELL_COMPONENT_16K, OCItems.QUANTUM_OMNI_CELL_16K},
    // {GTValues.IV, 1, OCItems.QUANTUM_OMNI_CELL_HOUSING, OCItems.QUANTUM_OMNI_CELL_COMPONENT_64K,
    // OCItems.COMPLEX_OMNI_CELL_COMPONENT_64K, OCItems.OMNI_CELL_COMPONENT_64K, OCItems.QUANTUM_OMNI_CELL_64K},
    // {GTValues.LuV, 2, OCItems.QUANTUM_OMNI_CELL_HOUSING, OCItems.QUANTUM_OMNI_CELL_COMPONENT_256K,
    // OCItems.COMPLEX_OMNI_CELL_COMPONENT_256K, OCItems.OMNI_CELL_COMPONENT_256K, OCItems.QUANTUM_OMNI_CELL_256K},
    // {GTValues.LuV, 2, OCItems.QUANTUM_OMNI_CELL_HOUSING, OCItems.QUANTUM_OMNI_CELL_COMPONENT_1M,
    // OCItems.COMPLEX_OMNI_CELL_COMPONENT_1M, OCItems.OMNI_CELL_COMPONENT_1M, OCItems.QUANTUM_OMNI_CELL_1M},
    // {GTValues.ZPM, 3, OCItems.QUANTUM_OMNI_CELL_HOUSING, OCItems.QUANTUM_OMNI_CELL_COMPONENT_4M,
    // OCItems.COMPLEX_OMNI_CELL_COMPONENT_4M, OCItems.OMNI_CELL_COMPONENT_4M, OCItems.QUANTUM_OMNI_CELL_4M},
    // {GTValues.ZPM, 3, OCItems.QUANTUM_OMNI_CELL_HOUSING, OCItems.QUANTUM_OMNI_CELL_COMPONENT_16M,
    // OCItems.COMPLEX_OMNI_CELL_COMPONENT_16M, OCItems.OMNI_CELL_COMPONENT_16M, OCItems.QUANTUM_OMNI_CELL_16M},
    // {GTValues.UV, 4, OCItems.QUANTUM_OMNI_CELL_HOUSING, OCItems.QUANTUM_OMNI_CELL_COMPONENT_64M,
    // OCItems.COMPLEX_OMNI_CELL_COMPONENT_64M, OCItems.OMNI_CELL_COMPONENT_64M, OCItems.QUANTUM_OMNI_CELL_64M},
    // {GTValues.UV, 4, OCItems.QUANTUM_OMNI_CELL_HOUSING, OCItems.QUANTUM_OMNI_CELL_COMPONENT_256M,
    // OCItems.COMPLEX_OMNI_CELL_COMPONENT_256M, OCItems.OMNI_CELL_COMPONENT_256M, OCItems.QUANTUM_OMNI_CELL_256M}
    // };
    //
    //// 批量注册成品量子元件配方（1K-256M），删除AE元件
    // for (int i = 0; i < QUANTUM_OMNI_CELL_TIERS.length; i++) {
    // Object[] tierData = QUANTUM_OMNI_CELL_TIERS[i];
    //
    // int voltageTier = (int) tierData[0]; // 电压等级
    // int groupIndex = (int) tierData[1]; // 电压分组索引
    // ItemLike housing = (ItemLike) tierData[2];
    // ItemLike quantumComponent = (ItemLike) tierData[3];
    // ItemLike complexComponent = (ItemLike) tierData[4];
    // ItemLike omniComponent = (ItemLike) tierData[5];
    // ItemLike outputCell = (ItemLike) tierData[6]; // 成品元件（现在索引是6，不是7）
    //
    // // 构建等级名称数组
    // String[] cellNames = {"1k", "4k", "16k", "64k", "256k", "1m", "4m", "16m", "64m", "256m"};
    // String cellName = cellNames[i];
    //
    // GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("quantum_omni_cell_" + cellName + "_ctnh")
    // .inputItems(housing, 1)
    // .inputItems(quantumComponent, 1)
    // .inputItems(complexComponent, 1)
    // .inputItems(omniComponent, 1)
    // .inputFluids(EnderIOMaterials.PulsatingAlloy.getFluid(144))
    // .outputItems(outputCell)
    // .cleanroom(CleanroomType.CLEANROOM)
    // .EUt(VA[voltageTier]) // 每两个等级电压升一级
    // .duration(40)
    // .save(provider);
    // }
    // 量子全知细胞升级链的等级后缀，与下面的电压表一一对应
    private static final String[] QUANTUM_CELL_TIERS = { "4k", "16k", "64k", "256k", "1m", "4m", "16m", "64m",
            "256m" };

    // 成品细胞所在电压等级：沿用删改前的旧表，4K 在 EV，之后每两档升一级，256M 到 UV
    private static final int[] QUANTUM_CELL_VOLTAGE_TIERS = { EV, IV, IV, LuV, LuV, ZPM, ZPM, UV, UV };

    /**
     * 补回 4K~256M 的量子全知元件与成品细胞。
     *
     * <p>
     * ae2omnicells 原本用 AE 工作台按“上一级元件”逐级升级，CTNH 把 1K 改成了 GT 装配流程，其余等级却只做了
     * 删除（见 {@link #omniRemovals()}）没有补回，导致 4K 以上的量子全知细胞在全包里没有获取途径。 这里沿用
     * 1K 的模板：元件在电路装配机里用“高一级电路 3 个 + 本级电路 2 个”制造，成品细胞再在外壳里组装。
     */
    private static void addQuantumCellChain(Consumer<FinishedRecipe> provider) {
        List<RegistryObject<Item>> quantumComponents = List.of(
                OCItems.QUANTUM_OMNI_CELL_COMPONENT_4K,
                OCItems.QUANTUM_OMNI_CELL_COMPONENT_16K,
                OCItems.QUANTUM_OMNI_CELL_COMPONENT_64K,
                OCItems.QUANTUM_OMNI_CELL_COMPONENT_256K,
                OCItems.QUANTUM_OMNI_CELL_COMPONENT_1M,
                OCItems.QUANTUM_OMNI_CELL_COMPONENT_4M,
                OCItems.QUANTUM_OMNI_CELL_COMPONENT_16M,
                OCItems.QUANTUM_OMNI_CELL_COMPONENT_64M,
                OCItems.QUANTUM_OMNI_CELL_COMPONENT_256M);
        List<RegistryObject<Item>> complexComponents = List.of(
                OCItems.COMPLEX_OMNI_CELL_COMPONENT_4K,
                OCItems.COMPLEX_OMNI_CELL_COMPONENT_16K,
                OCItems.COMPLEX_OMNI_CELL_COMPONENT_64K,
                OCItems.COMPLEX_OMNI_CELL_COMPONENT_256K,
                OCItems.COMPLEX_OMNI_CELL_COMPONENT_1M,
                OCItems.COMPLEX_OMNI_CELL_COMPONENT_4M,
                OCItems.COMPLEX_OMNI_CELL_COMPONENT_16M,
                OCItems.COMPLEX_OMNI_CELL_COMPONENT_64M,
                OCItems.COMPLEX_OMNI_CELL_COMPONENT_256M);
        List<RegistryObject<Item>> omniComponents = List.of(
                OCItems.OMNI_CELL_COMPONENT_4K,
                OCItems.OMNI_CELL_COMPONENT_16K,
                OCItems.OMNI_CELL_COMPONENT_64K,
                OCItems.OMNI_CELL_COMPONENT_256K,
                OCItems.OMNI_CELL_COMPONENT_1M,
                OCItems.OMNI_CELL_COMPONENT_4M,
                OCItems.OMNI_CELL_COMPONENT_16M,
                OCItems.OMNI_CELL_COMPONENT_64M,
                OCItems.OMNI_CELL_COMPONENT_256M);
        List<RegistryObject<? extends Item>> cells = List.of(
                OCItems.QUANTUM_OMNI_CELL_4K,
                OCItems.QUANTUM_OMNI_CELL_16K,
                OCItems.QUANTUM_OMNI_CELL_64K,
                OCItems.QUANTUM_OMNI_CELL_256K,
                OCItems.QUANTUM_OMNI_CELL_1M,
                OCItems.QUANTUM_OMNI_CELL_4M,
                OCItems.QUANTUM_OMNI_CELL_16M,
                OCItems.QUANTUM_OMNI_CELL_64M,
                OCItems.QUANTUM_OMNI_CELL_256M);

        for (int i = 0; i < QUANTUM_CELL_TIERS.length; i++) {
            String tier = QUANTUM_CELL_TIERS[i];
            int voltageTier = QUANTUM_CELL_VOLTAGE_TIERS[i];

            GTRecipeTypes.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("quantum_omni_cell_component_" + tier))
                    .inputItems(GTItems.CARBON_MESH, 16)
                    .inputItems(TagPrefix.plateDense, GTMaterials.TungstenSteel, 4)
                    .inputItems(circuitTag(voltageTier + 1), 3)
                    .inputItems(circuitTag(voltageTier), 2)
                    .inputItems(OCItems.MULTIDIMENSIONAL_EXPANSION_PROCESSOR)
                    .inputFluids(EnderIOMaterials.PulsatingAlloy.getFluid(144))
                    .outputItems(quantumComponents.get(i))
                    .cleanroom(CleanroomType.CLEANROOM)
                    .EUt(VA[voltageTier + 1])
                    .duration(200)
                    .save(provider);

            GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("quantum_omni_cell_" + tier))
                    .inputItems(OCItems.QUANTUM_OMNI_CELL_HOUSING, 1)
                    .inputItems(quantumComponents.get(i), 1)
                    .inputItems(complexComponents.get(i), 1)
                    .inputItems(omniComponents.get(i), 1)
                    .inputFluids(EnderIOMaterials.PulsatingAlloy.getFluid(144))
                    .outputItems(cells.get(i).get())
                    .cleanroom(CleanroomType.CLEANROOM)
                    .EUt(VA[voltageTier])
                    .duration(40)
                    .save(provider);
        }
    }

    /** 按电压等级取对应的电路标签，供元件配方复用。 */
    private static TagKey<Item> circuitTag(int voltageTier) {
        return switch (voltageTier) {
            case EV -> CustomTags.EV_CIRCUITS;
            case IV -> CustomTags.IV_CIRCUITS;
            case LuV -> CustomTags.LuV_CIRCUITS;
            case ZPM -> CustomTags.ZPM_CIRCUITS;
            case UV -> CustomTags.UV_CIRCUITS;
            case UHV -> CustomTags.UHV_CIRCUITS;
            default -> throw new IllegalArgumentException("unsupported circuit tier " + voltageTier);
        };
    }

    public static void omniRemovals() {
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:charged_ender_ingot"));
        String[] standardTiers = { "1k", "4k", "16k", "64k", "256k", "1m", "4m", "16m", "64m" };
        for (String tier : standardTiers) {
            RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:components/shaped/omni_cell_component_" + tier));
            RecipeRemoval.remove(new RemoveFilter()
                    .id("ae2omnicells:components/shaped/complex_omni_cell_component_" + tier));
        }

        String[] quantumTiers = { "4k", "16k", "64k", "256k", "1m", "4m", "16m", "64m" };
        for (String tier : quantumTiers) {
            RecipeRemoval.remove(new RemoveFilter()
                    .id("ae2omnicells:components/shaped/quantum_omni_cell_component_" + tier));
        }

        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/housing/omni_cell_housing"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/housing/complex_omni_cell_housing"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:omni_link_print_press"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:inscriber/omni_link_print_press"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:complex_link_print_press"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:inscriber/complex_link_print_press"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:multidimensional_expansion_print_press"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:inscriber/multidimensional_expansion_print_press"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:crafting_unit/shapedomni_crafting_unit_block"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:crafting_unit/shapedcomplex_crafting_unit_block"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:crafting_unit/shapedquantum_crafting_unit_block"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/omni_cell_1k"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/omni_cell_4k"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/omni_cell_16k"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/omni_cell_64k"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/omni_cell_256k"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/omni_cell_1m"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/omni_cell_4m"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/omni_cell_16m"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/omni_cell_64m"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/omni_cell_256m"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/complex_omni_cell_1k"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/complex_omni_cell_4k"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/complex_omni_cell_16k"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/complex_omni_cell_64k"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/complex_omni_cell_256k"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/complex_omni_cell_1m"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/complex_omni_cell_4m"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/complex_omni_cell_16m"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/complex_omni_cell_64m"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/complex_omni_cell_256m"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/quantum_omni_cell_1k"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/quantum_omni_cell_4k"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/quantum_omni_cell_16k"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/quantum_omni_cell_64k"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/quantum_omni_cell_256k"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/quantum_omni_cell_1m"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/quantum_omni_cell_4m"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/quantum_omni_cell_16m"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/quantum_omni_cell_64m"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/shaped/quantum_omni_cell_256m"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:components/shaped/omni_cell_component_256m"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:components/shaped/complex_omni_cell_component_256m"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:components/shaped/quantum_omni_cell_component_256m"));
        RecipeRemoval.remove(new RemoveFilter().id("ae2omnicells:cells/housing/quantum_omni_cell_housing"));
    }
}
