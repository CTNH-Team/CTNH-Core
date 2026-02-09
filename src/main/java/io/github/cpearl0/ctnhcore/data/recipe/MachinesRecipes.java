package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.AdastraMaterials;
import io.github.cpearl0.ctnhcore.registry.*;
import io.github.cpearl0.ctnhcore.registry.machines.CTNHMachines;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.data.recipe.CraftingComponent;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluids;

import com.simibubi.create.AllBlocks;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gregtechceu.gtceu.common.data.machines.GTResearchMachines.OBJECT_HOLDER;
import static com.gregtechceu.gtceu.data.recipe.GTCraftingComponents.*;
import static com.gregtechceu.gtceu.data.recipe.misc.MetaTileEntityLoader.registerMachineRecipe;
import static io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes.PHOTOVOLTAIC_ASSEMBER;
import static io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes.PHOTOVOLTAIC_GENERATOR;
import static io.github.cpearl0.ctnhcore.registry.machines.CTNHMachines.DRONEHOLDER;
import static twilightforest.init.TFItems.STEELEAF_INGOT;

public class MachinesRecipes {

    public static CraftingComponent MONITOR = CraftingComponent.of("monitor", COVER_SCREEN.asStack())
            .add(LV, COVER_SCREEN.asStack())
            .add(MV, COVER_SCREEN.asStack())
            .add(HV, COVER_SCREEN.asStack())
            .add(EV, COVER_SCREEN.asStack())
            .add(IV, COVER_SCREEN.asStack())
            .add(LuV, COVER_SCREEN.asStack())
            .add(ZPM, COVER_SCREEN.asStack())
            .add(UV, COVER_SCREEN.asStack())
            .add(UHV, COVER_SCREEN.asStack())
            .add(UEV, COVER_SCREEN.asStack())
            .add(UIV, COVER_SCREEN.asStack())
            .add(UXV, COVER_SCREEN.asStack())
            .add(OpV, COVER_SCREEN.asStack())
            .add(MAX, COVER_SCREEN.asStack());

    public static void init(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapedRecipe(provider, true, "underfloor_heating_system",
                MultiblocksA.UNDERFLOOR_HEATING_SYSTEM.asStack(),
                "SPS", "IwI", "SPS",
                'S', new ItemStack(AllBlocks.COPPER_SHINGLES.getStandard().get()),
                'P', GTBlocks.CASING_BRONZE_PIPE.asStack(),
                'I', new MaterialEntry(TagPrefix.plate, Gold));

        VanillaRecipeHelper.addShapedRecipe(provider, true, "ultimate_engine_intake_casing",
                CTNHBlocks.CASING_ULTIMATE_ENGINE_INTAKE.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft), "PhP",
                "RFR", "PwP", 'R', new MaterialEntry(TagPrefix.rotor, GTMaterials.NaquadahAlloy), 'F',
                CTNHBlocks.CASING_NAQUADAH_BLOCK.asStack(), 'P',
                new MaterialEntry(TagPrefix.pipeNormalFluid, GTMaterials.NaquadahAlloy));
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("zpm_large_miner"))
                .inputItems(GTMachines.HULL[ZPM])
                .inputItems(frameGt, Osmiridium, 4)
                .inputItems(CustomTags.ZPM_CIRCUITS, 4)
                .inputItems(ELECTRIC_MOTOR_ZPM, 4)
                .inputItems(ELECTRIC_PUMP_ZPM, 4)
                .inputItems(CONVEYOR_MODULE_ZPM, 4)
                .inputItems(gear, Osmiridium, 4)
                .circuitMeta(2)
                .outputItems(MultiblocksA.ZPM_LARGE_MINER)
                .duration(400).EUt(VA[ZPM]).save(provider);

        COMPRESSOR_RECIPES.recipeBuilder(CTNHCore.id("steelleaf"))
                .duration(300)
                .EUt(2)
                .inputItems(dust, CTNHMaterials.SteelLeaf, 1)
                .outputItems(STEELEAF_INGOT, 1)
                .save(provider);

        MIXER_RECIPES.recipeBuilder(CTNHCore.id("plantfood"))
                .duration(500)
                .EUt(28)
                .inputItems(dust, CTNHMaterials.SpiritAsh, 4)
                .inputItems(dust, Apatite, 4)
                .outputItems(FERTILIZER, 16)
                .save(provider);

        CTNHRecipeTypes.ARC_REACTOR.recipeBuilder(CTNHCore.id("test"))
                .EUt(8192)
                .duration(20)
                .circuitMeta(1)
                .save(provider);

        CTNHRecipeTypes.TRAP_ENERGY.recipeBuilder(CTNHCore.id("test"))
                .EUt(1)
                .duration(10)
                .circuitMeta(1)
                .save(provider);

        CTNHRecipeTypes.VOID_MINER.recipeBuilder(CTNHCore.id("void"))
                .EUt(32678 * 64)
                .duration(20 * 100)
                .circuitMeta(1)
                .save(provider);

        CTNHRecipeTypes.PVDRONE.recipeBuilder(CTNHCore.id("blank"))
                .duration(100)
                .circuitMeta(1)
                .save(provider);

        CTNHRecipeTypes.PVDRONE.recipeBuilder(CTNHCore.id("meteorite_capture"))
                .duration(1000)
                .circuitMeta(2)
                .outputItems(CTNHTagPrefixes.oreMoonStone, AdastraMaterials.Desh)
                .outputItems(CTNHTagPrefixes.oreVenusStone, AdastraMaterials.Calorite)
                .outputItems(CTNHTagPrefixes.oreMarsStone, AdastraMaterials.Ostrum)
                .EUt(VA[LuV])
                .save(provider);

        CTNHRecipeTypes.COMPILER_RECIPE.recipeBuilder(CTNHCore.id("test"))
                .inputItems(dust, CTNHMaterials.SteelLeaf, 1)
                .inputItems(dust, CTNHMaterials.SteelLeaf, 1)
                .inputItems(dust, CTNHMaterials.SteelLeaf, 1)
                .inputItems(dust, CTNHMaterials.SteelLeaf, 1)
                .inputItems(dust, CTNHMaterials.SteelLeaf, 1)
                .outputItems(dust, CTNHMaterials.SpiritAsh, 1)
                .addData("1", 48)
                .addData("2", 30)
                .addData("3", 30)
                .addData("noisea", 20)
                .addData("noiseb", 2)
                .addData("range", 120)
                .duration(20 * 30)
                .EUt(1000000)
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder(CTNHCore.id("end"))
                .inputFluids(CTNHMaterials.NQ_END_OF_GASOLINE.getFluid(1))
                .duration(1000)
                .EUt(-320)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("naquadah_gearbox_casing"))
                .inputItems(plate, NaquadahAlloy, 4)
                .inputItems(gear, NaquadahAlloy, 2)
                .inputItems(frameGt, NaquadahAlloy)
                .circuitMeta(4)
                .outputItems(CTNHBlocks.CASING_NAQUADAH_GEARBOX.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .duration(50).EUt(16).save(provider);

        VanillaRecipeHelper.addShapedRecipe(provider, true, "casing_naquadah_alloy_gearbox",
                CTNHBlocks.CASING_NAQUADAH_GEARBOX.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft), "PhP", "GFG",
                "PwP", 'P', new MaterialEntry(TagPrefix.plate, GTMaterials.NaquadahAlloy), 'F',
                new MaterialEntry(frameGt, GTMaterials.NaquadahAlloy), 'G',
                new MaterialEntry(gear, GTMaterials.NaquadahAlloy));

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("empty_program"))
                .inputItems(wireFine, RedAlloy, 8)
                .inputItems(plate, Steel, 2)
                .inputFluids(FluidIngredient.of(Fluids.WATER, 1000))
                .outputItems(CTNHItems.PROGRAM_EMPTY.asStack())
                .EUt(30)
                .duration(200)
                .save(provider);

// registerMachineRecipe 可能是一个自定义方法，这里不修改
        registerMachineRecipe(provider, CTNHMachines.PERSONAL_COMPUTER, "PDP",
                "CAC", "PBP", 'A', HULL, 'C', ROTOR, 'P', CABLE, 'D', CIRCUIT, 'B', SENSOR);

        registerMachineRecipe(provider, CTNHMachines.CIRCUIT_BUS, "A", "B", 'A', MONITOR, 'B', HULL);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("astronomy_circuit"))
                .inputItems(PLASTIC_CIRCUIT_BOARD)
                .inputItems(CENTRAL_PROCESSING_UNIT, 2)
                .inputItems(NAND_MEMORY_CHIP, 32)
                .inputItems(RANDOM_ACCESS_MEMORY, 4)
                .inputItems(wireFine, Tin, 16)
                .inputItems(plate, Polyethylene, 4)
                .outputItems(CTNHItems.ASTRONOMY_CIRCUIT_1.asStack())
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(90).save(provider);

        PHOTOVOLTAIC_GENERATOR.recipeBuilder(CTNHCore.id("test"))
                .duration(20)
                .EUt(-1)
                .circuitMeta(1)
                .save(provider);

        PHOTOVOLTAIC_ASSEMBER.recipeBuilder(CTNHCore.id("test"))
                .duration(20)
                // .EUt(-1)
                .circuitMeta(1)
                .addData("input", 32678)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("drone_holder"))
                .duration(100)
                .EUt(32678 * 4)
                .circuitMeta(1)
                .inputItems(OBJECT_HOLDER)
                .outputItems(DRONEHOLDER)
                .inputItems(CustomTags.UV_CIRCUITS, 4)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("lv_digital_miner"))
                .inputItems(GTMachines.MINER[LV].asStack())
                .inputItems(CONVEYOR_MODULE_LV.asStack(2))
                .inputItems(ROBOT_ARM_LV.asStack(2))
                .inputItems(EMITTER_LV.asStack())
                .inputItems(SENSOR_LV.asStack())
                .inputItems(CustomTags.MV_CIRCUITS, 2)
                .inputFluids(GTMaterials.SolderingAlloy.getFluid(144))
                .outputItems(CTNHMachines.DIGITAL_MINER[LV].asStack())
                .duration(200)
                .EUt(VA[LV])
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("mv_digital_miner"))
                .inputItems(GTMachines.MINER[MV].asStack())
                .inputItems(CONVEYOR_MODULE_MV.asStack(2))
                .inputItems(ROBOT_ARM_MV.asStack(2))
                .inputItems(EMITTER_MV.asStack())
                .inputItems(SENSOR_MV.asStack())
                .inputItems(CustomTags.HV_CIRCUITS, 2)
                .inputFluids(GTMaterials.SolderingAlloy.getFluid(144))
                .outputItems(CTNHMachines.DIGITAL_MINER[MV].asStack())
                .duration(200)
                .EUt(VA[MV])
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("hv_digital_miner"))
                .inputItems(GTMachines.MINER[HV].asStack())
                .inputItems(CONVEYOR_MODULE_HV.asStack(2))
                .inputItems(ROBOT_ARM_HV.asStack(2))
                .inputItems(EMITTER_HV.asStack())
                .inputItems(SENSOR_HV.asStack())
                .inputItems(CustomTags.EV_CIRCUITS, 2)
                .inputFluids(GTMaterials.SolderingAlloy.getFluid(144))
                .outputItems(CTNHMachines.DIGITAL_MINER[HV].asStack())
                .duration(200)
                .EUt(VA[HV])
                .save(provider);
    }
}
