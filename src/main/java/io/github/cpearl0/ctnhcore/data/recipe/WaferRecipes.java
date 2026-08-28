package io.github.cpearl0.ctnhcore.data.recipe;

import com.gregtechceu.gtceu.api.data.chemical.material.MarkerMaterials.Color;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

public class WaferRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 掩膜硅晶圆：硅晶圆 + 液态橡胶（化学浸洗机）
        CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("rubber_masked_silicon_wafer"))
                .inputItems(SILICON_WAFER.get().getDefaultInstance())
                .inputFluids(Rubber.getFluid(288))
                .outputItems(CTNHItems.RUBBER_MASKED_SILICON_WAFER.get().getDefaultInstance())
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(200)
                .EUt(VA[MV])
                .save(provider);

        // 激光蚀刻：掩膜硅晶圆 -> BSC掩模晶圆（淡灰色透镜）
        LASER_ENGRAVER_RECIPES.recipeBuilder(CTNHCore.id("engrave_bsc_wafer_masked"))
                .inputItems(CTNHItems.RUBBER_MASKED_SILICON_WAFER.get().getDefaultInstance())
                .notConsumable(lens, Color.LightGray)
                .outputItems(CTNHItems.BSC_WAFER_MASKED.get().getDefaultInstance())
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(900)
                .EUt(VA[MV])
                .save(provider);

        // 激光蚀刻：掩膜硅晶圆 -> LPIC掩模晶圆（橙色透镜，与未掩膜 LPIC 相同）
        LASER_ENGRAVER_RECIPES.recipeBuilder(CTNHCore.id("engrave_lpic_wafer_masked"))
                .inputItems(CTNHItems.RUBBER_MASKED_SILICON_WAFER.get().getDefaultInstance())
                .notConsumable(lens, Color.Orange)
                .outputItems(CTNHItems.LPIC_WAFER_MASKED.get().getDefaultInstance())
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(900)
                .EUt(VA[MV])
                .save(provider);

        // 激光蚀刻：掩膜硅晶圆 -> RAM掩模晶圆（绿色透镜，与未掩膜 RAM 相同）
        LASER_ENGRAVER_RECIPES.recipeBuilder(CTNHCore.id("engrave_ram_wafer_masked"))
                .inputItems(CTNHItems.RUBBER_MASKED_SILICON_WAFER.get().getDefaultInstance())
                .notConsumable(lens, Color.Green)
                .outputItems(CTNHItems.RAM_WAFER_MASKED.get().getDefaultInstance())
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(900)
                .EUt(VA[MV])
                .save(provider);

        // 激光蚀刻：掩膜硅晶圆 -> 简易SOC掩模晶圆（青色透镜，与未掩膜 SSOC 相同）
        LASER_ENGRAVER_RECIPES.recipeBuilder(CTNHCore.id("engrave_ssoc_wafer_rubber_masked"))
                .inputItems(CTNHItems.RUBBER_MASKED_SILICON_WAFER.get().getDefaultInstance())
                .notConsumable(lens, Color.Cyan)
                .outputItems(CTNHItems.SSOC_WAFER_RUBBER_MASKED.get().getDefaultInstance())
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(900)
                .EUt(VA[MV])
                .save(provider);

        // 电力高炉：BSC掩模晶圆 + 磷粉 + 硼粉 -> 未掩膜BSC晶圆
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("bsc_wafer_blast"))
                .inputItems(CTNHItems.BSC_WAFER_MASKED.get().getDefaultInstance())
                .inputItems(dust, Phosphorus, 1)
                .inputItems(dust, Boron, 1)
                .outputItems(CTNHItems.BSC_WAFER.get().getDefaultInstance())
                .blastFurnaceTemp(1000)
                .duration(300)
                .EUt(VA[MV])
                .save(provider);

        // 电力高炉：RAM掩模晶圆 + 磷粉 + 硼粉 -> 未掩膜RAM晶圆
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("ram_wafer_blast"))
                .inputItems(CTNHItems.RAM_WAFER_MASKED.get().getDefaultInstance())
                .inputItems(dust, Phosphorus, 1)
                .inputItems(dust, Boron, 1)
                .outputItems(RANDOM_ACCESS_MEMORY_WAFER.get().getDefaultInstance())
                .blastFurnaceTemp(1000)
                .duration(300)
                .EUt(VA[MV])
                .save(provider);

        // 电力高炉：LPIC掩模晶圆 + 磷粉 + 镓粉 -> 未掩膜LPIC晶圆
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("lpic_wafer_blast"))
                .inputItems(CTNHItems.LPIC_WAFER_MASKED.get().getDefaultInstance())
                .inputItems(dust, Phosphorus, 1)
                .inputItems(dust, Gallium, 1)
                .outputItems(LOW_POWER_INTEGRATED_CIRCUIT_WAFER.get().getDefaultInstance())
                .blastFurnaceTemp(1000)
                .duration(300)
                .EUt(VA[MV])
                .save(provider);

        // 电力高炉：简易SOC掩模晶圆 + 磷粉 + 硼粉 -> 未掩膜简易SOC晶圆（温度与铁烧钢相同）
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("ssoc_wafer_blast"))
                .inputItems(CTNHItems.SSOC_WAFER_RUBBER_MASKED.get().getDefaultInstance())
                .inputItems(dust, Phosphorus, 1)
                .inputItems(dust, Boron, 1)
                .outputItems(SIMPLE_SYSTEM_ON_CHIP_WAFER.get().getDefaultInstance())
                .blastFurnaceTemp(1000)
                .duration(300)
                .EUt(VA[MV])
                .save(provider);

        // 切割：BSC晶圆 -> BSC芯片 ×8（标准GT写法，自动生成水/蒸馏水/润滑油三种切削液变体）
        CUTTER_RECIPES.recipeBuilder(CTNHCore.id("cut_bsc_chip"))
                .inputItems(CTNHItems.BSC_WAFER.get().getDefaultInstance())
                .outputItems(CTNHItems.BSC_CHIP.get().getDefaultInstance(), 8)
                .duration(900)
                .EUt(VA[MV])
                .save(provider);

        // 电路装配：BSC芯片替代CPU芯片 -> 精密电路 ×2（参照 microprocessor_lv）
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("precision_circuit_from_bsc"))
                .EUt(60).duration(200)
                .inputItems(PLASTIC_CIRCUIT_BOARD)
                .inputItems(CTNHItems.BSC_CHIP.get().getDefaultInstance())
                .inputItems(CustomTags.RESISTORS, 2)
                .inputItems(CustomTags.CAPACITORS, 2)
                .inputItems(CustomTags.TRANSISTORS, 2)
                .inputItems(wireFine, Copper, 2)
                .outputItems(CTNHItems.PRECISION_CIRCUIT.get().getDefaultInstance(), 2)
                .save(provider);

        // 电路装配：2个精密电路替代CPU芯片 -> 精密电路集群 ×2（参照 processor_mv，其余材料减半）
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("precision_circuit_assembly_from_bsc"))
                .EUt(60).duration(200)
                .inputItems(PLASTIC_CIRCUIT_BOARD)
                .inputItems(CTNHItems.PRECISION_CIRCUIT.get().getDefaultInstance(), 2)
                .inputItems(CustomTags.RESISTORS, 2)
                .inputItems(CustomTags.CAPACITORS, 2)
                .inputItems(CustomTags.TRANSISTORS, 2)
                .inputItems(wireFine, RedAlloy, 2)
                .outputItems(CTNHItems.PRECISION_CIRCUIT_ASSEMBLY.get().getDefaultInstance(), 2)
                .save(provider);

        // 电路装配：2个精密电路集群替代处理器 -> 精密电路计算机 ×1（参照 processor_assembly_hv，电容/细导线减半）
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("precision_circuit_computer_from_bsc"))
                .EUt(VA[MV]).duration(400)
                .inputItems(PLASTIC_CIRCUIT_BOARD)
                .inputItems(CTNHItems.PRECISION_CIRCUIT_ASSEMBLY.get().getDefaultInstance(), 2)
                .inputItems(CustomTags.INDUCTORS, 4)
                .inputItems(CustomTags.CAPACITORS, 4)
                .inputItems(RANDOM_ACCESS_MEMORY.get().getDefaultInstance(), 4)
                .inputItems(wireFine, RedAlloy, 4)
                .outputItems(CTNHItems.PRECISION_CIRCUIT_COMPUTER.get().getDefaultInstance(), 1)
                .solderMultiplier(2)
                .save(provider);

        // 电路装配：2个精密电路计算机替代工作站 -> 精密电路主机 ×1（参照 mainframe_iv，铝框架换钢框架×2，材料减半，电压降MV）
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("precision_circuit_mainframe_from_bsc"))
                .EUt(VA[MV]).duration(800)
                .inputItems(frameGt, Steel, 2)
                .inputItems(CTNHItems.PRECISION_CIRCUIT_COMPUTER.get().getDefaultInstance(), 2)
                .inputItems(CustomTags.INDUCTORS, 4)
                .inputItems(CustomTags.CAPACITORS, 8)
                .inputItems(RANDOM_ACCESS_MEMORY.get().getDefaultInstance(), 8)
                .inputItems(wireGtSingle, AnnealedCopper, 8)
                .outputItems(CTNHItems.PRECISION_CIRCUIT_MAINFRAME.get().getDefaultInstance(), 1)
                .solderMultiplier(4)
                .save(provider);

        // ===== 微处理器系列（HV 段重制，替换已移除的 LV~IV 旧配方） =====
        // 微处理器（原 microprocessor_lv，60 -> 240）
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("microprocessor_lv_hv"))
                .EUt(240).duration(200)
                .inputItems(PLASTIC_CIRCUIT_BOARD)
                .inputItems(CENTRAL_PROCESSING_UNIT.get().getDefaultInstance())
                .inputItems(CustomTags.RESISTORS, 2)
                .inputItems(CustomTags.CAPACITORS, 2)
                .inputItems(CustomTags.TRANSISTORS, 2)
                .inputItems(wireFine, Copper, 2)
                .outputItems(MICROPROCESSOR_LV.get().getDefaultInstance(), 3)
                .save(provider);

        // 处理器（原 processor_mv，60 -> 240）
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("processor_mv_hv"))
                .EUt(240).duration(200)
                .inputItems(PLASTIC_CIRCUIT_BOARD)
                .inputItems(CENTRAL_PROCESSING_UNIT.get().getDefaultInstance())
                .inputItems(CustomTags.RESISTORS, 4)
                .inputItems(CustomTags.CAPACITORS, 4)
                .inputItems(CustomTags.TRANSISTORS, 4)
                .inputItems(wireFine, RedAlloy, 4)
                .outputItems(PROCESSOR_MV.get().getDefaultInstance(), 2)
                .save(provider);

        // 处理器装配（原 processor_assembly_hv，120 -> 480）
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("processor_assembly_hv_hv"))
                .EUt(VA[HV]).duration(400)
                .inputItems(PLASTIC_CIRCUIT_BOARD)
                .inputItems(PROCESSOR_MV.get().getDefaultInstance(), 2)
                .inputItems(CustomTags.INDUCTORS, 4)
                .inputItems(CustomTags.CAPACITORS, 8)
                .inputItems(RANDOM_ACCESS_MEMORY.get().getDefaultInstance(), 4)
                .inputItems(wireFine, RedAlloy, 8)
                .outputItems(PROCESSOR_ASSEMBLY_HV.get().getDefaultInstance(), 2)
                .solderMultiplier(2)
                .save(provider);

        // 工作站（原 workstation_ev，120 -> 480）
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("workstation_ev_hv"))
                .EUt(VA[HV]).duration(400)
                .inputItems(PLASTIC_CIRCUIT_BOARD)
                .inputItems(PROCESSOR_ASSEMBLY_HV.get().getDefaultInstance(), 2)
                .inputItems(CustomTags.DIODES, 4)
                .inputItems(RANDOM_ACCESS_MEMORY.get().getDefaultInstance(), 4)
                .inputItems(wireFine, Electrum, 16)
                .inputItems(bolt, BlueAlloy, 16)
                .outputItems(WORKSTATION_EV.get().getDefaultInstance(), 1)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(provider);

        // 主机（原 mainframe_iv，480 不变）
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("mainframe_iv_hv"))
                .EUt(VA[HV]).duration(800)
                .inputItems(frameGt, Aluminium, 2)
                .inputItems(WORKSTATION_EV.get().getDefaultInstance(), 2)
                .inputItems(CustomTags.INDUCTORS, 8)
                .inputItems(CustomTags.CAPACITORS, 16)
                .inputItems(RANDOM_ACCESS_MEMORY.get().getDefaultInstance(), 16)
                .inputItems(wireGtSingle, AnnealedCopper, 16)
                .outputItems(MAINFRAME_IV.get().getDefaultInstance(), 1)
                .solderMultiplier(4)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(provider);
    }
}