package io.github.cpearl0.ctnhcore.data.recipe.age;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;

import java.util.function.Consumer;

public class LowVoltageAgeRecipes {

    // 这里存放的是低压(lv)时期的配方
    public static void init(Consumer<FinishedRecipe> provider) {
        addLVCoverRecipes(provider);
        addPolarizerRecipes(provider);
        addThermalCentrifugeRecipes(provider);
    }

    private static void addLVCoverRecipes(Consumer<FinishedRecipe> provider) {
        // LV电动马达（铜导线环绕磁化钢杆，铁板底座）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/electric_motor_lv"),
                GTItems.ELECTRIC_MOTOR_LV.asStack(),
                " A ", "ABA", "CAC",
                'A', ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.Copper),
                'B', ChemicalHelper.get(TagPrefix.rod, GTMaterials.SteelMagnetic),
                'C', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Iron));

        // LV电动活塞（钢板包裹，锻造锤与扳手组装，内置电动马达）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/electric_piston_lv"),
                GTItems.ELECTRIC_PISTON_LV.asStack(),
                "AAA", "hBw", "CDE",
                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel),
                'B', ChemicalHelper.get(TagPrefix.rod, GTMaterials.Steel),
                'C', ChemicalHelper.get(TagPrefix.gear, GTMaterials.Steel),
                'D', AllBlocks.MECHANICAL_PISTON.asStack(),
                'E', GTItems.ELECTRIC_MOTOR_LV.asStack());

        // LV电动传送带（两条动力皮带夹着电动马达与铜导线）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/conveyor_module_lv"),
                GTItems.CONVEYOR_MODULE_LV.asStack(),
                " A ", "BCB", " A ",
                'A', AllItems.BELT_CONNECTOR.asStack(),
                'B', GTItems.ELECTRIC_MOTOR_LV.asStack(),
                'C', ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.Copper));

        // LV电动泵（铁转子与动力泵，橡胶环密封，电动马达驱动）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/electric_pump_lv"),
                GTItems.ELECTRIC_PUMP_LV.asStack(),
                "dA ", "BCB", " Dw",
                'A', ChemicalHelper.get(TagPrefix.rotor, GTMaterials.Iron),
                'B', ChemicalHelper.get(TagPrefix.ring, GTMaterials.Rubber),
                'C', AllBlocks.MECHANICAL_PUMP.asStack(),
                'D', GTItems.ELECTRIC_MOTOR_LV.asStack());

        // LV机械臂（黄铜手部零件与电动活塞，锡线缆与LV电路）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/robot_arm_lv"),
                GTItems.ROBOT_ARM_LV.asStack(),
                "ABC", "DBE", "FDC",
                'A', AllItems.BRASS_HAND.asStack(),
                'B', ChemicalHelper.get(TagPrefix.rod, GTMaterials.Steel),
                'C', GTItems.ELECTRIC_MOTOR_LV.asStack(),
                'D', ChemicalHelper.get(TagPrefix.cableGtSingle, GTMaterials.Tin),
                'E', GTItems.ELECTRIC_PISTON_LV.asStack(),
                'F', GTItems.ELECTRONIC_CIRCUIT_LV.asStack());

        // LV节流泵（无序：LV电动泵 + LV电路 + 锡线缆 + 螺丝刀）
        VanillaRecipeHelper.addShapelessRecipe(provider, CTNHCore.id("crafttable/fluid_regulator_lv"),
                GTItems.FLUID_REGULATOR_LV.asStack(),
                GTItems.ELECTRIC_PUMP_LV.asStack(),
                GTItems.ELECTRONIC_CIRCUIT_LV.asStack(),
                ChemicalHelper.get(TagPrefix.cableGtSingle, GTMaterials.Tin),
                'd');

        // LV发射器（石英岩居中，黄铜杆与LV电路，锡导线连接）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/emitter_lv"),
                GTItems.EMITTER_LV.asStack(),
                " A ", "BCB", "D D",
                'A', ChemicalHelper.get(TagPrefix.gem, GTMaterials.Quartzite),
                'B', ChemicalHelper.get(TagPrefix.rod, GTMaterials.Brass),
                'C', GTItems.ELECTRONIC_CIRCUIT_LV.asStack(),
                'D', ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.Tin));
    }

    private static void addPolarizerRecipes(Consumer<FinishedRecipe> provider) {
        // LV磁化机（基础两极磁化机：原版GTM配方，第三排第二列改为基础电子电路）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/lv_polarizer"),
                GTMachines.POLARIZER[GTValues.LV].asStack(),
                "ZSZ", "WMW", "ZCZ",
                'Z', ChemicalHelper.get(TagPrefix.wireGtDouble, GTMaterials.Tin),
                'S', ChemicalHelper.get(TagPrefix.rod, GTMaterials.Iron),
                'M', GTMachines.HULL[GTValues.LV].asStack(),
                'W', ChemicalHelper.get(TagPrefix.cableGtSingle, GTMaterials.Tin),
                'C', GTItems.ELECTRONIC_CIRCUIT_LV.asStack());
    }

    private static void addThermalCentrifugeRecipes(Consumer<FinishedRecipe> provider) {
        // LV热力离心机（原版GTM配方，铜导线换为白铜导线）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/lv_thermal_centrifuge"),
                GTMachines.THERMAL_CENTRIFUGE[GTValues.LV].asStack(),
                "CEC", "OMO", "WEW",
                'C', GTItems.ELECTRONIC_CIRCUIT_LV.asStack(),
                'E', GTItems.ELECTRIC_MOTOR_LV.asStack(),
                'M', GTMachines.HULL[GTValues.LV].asStack(),
                'W', ChemicalHelper.get(TagPrefix.cableGtSingle, GTMaterials.Tin),
                'O', ChemicalHelper.get(TagPrefix.wireGtQuadruple, GTMaterials.Cupronickel));
    }
}
