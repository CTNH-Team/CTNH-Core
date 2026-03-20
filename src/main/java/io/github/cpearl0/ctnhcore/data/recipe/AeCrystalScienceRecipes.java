package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.item.CrystalItems;

import com.gregtechceu.gtceu.api.data.chemical.material.MarkerMaterials;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;

import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import com.enderio.base.common.init.EIOItems;
import com.wintercogs.ae2omnicells.common.init.OCItems;
import io.github.lounode.ae2cs.common.init.AECSBlocks;
import io.github.lounode.ae2cs.common.init.AECSItems;
import io.github.lounode.ae2cs.common.init.AECSParts;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.lens;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.data.materials.AeCrystalScienceMaterials.*;
import static io.github.cpearl0.ctnhcore.data.materials.AeOmniMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes.CRYSTALLIZER;

public class AeCrystalScienceRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ender_emitter"))
                .circuitMeta(15)
                .inputItems(AEBlocks.DENSE_ENERGY_CELL.asItem(), 1)
                .inputItems(EMITTER_HV, 4)
                .inputItems(SENSOR_HV, 4)
                .inputItems(AEItems.WIRELESS_RECEIVER.asItem(), 4)
                .inputItems(AEItems.FUZZY_CARD.asItem(), 8)
                .inputItems(AEItems.CALCULATION_PROCESSOR.asItem(), 16)
                .inputItems(AECSItems.RESONATING_PROCESSOR, 16)
                .inputFluids(EnderPearl.getFluid(L * 14))
                .outputItems(AECSBlocks.ENDER_EMITTER_BLOCK, 1)
                .EUt(VA[HV]).duration(1200)
                .save(provider);
        // ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("integrated_interface"))
        // .circuitMeta(15)
        // .inputItems(TagPrefix.ring, StainlessSteel,4)
        // .inputItems(TagPrefix.gear, StainlessSteel,1)
        // .inputItems(AECSItems.RESONATING_PROCESSOR, 2)
        // .inputItems(AEBlocks.PATTERN_PROVIDER.asItem(),1)
        // .inputItems(AEBlocks.INTERFACE.asItem(),1)
        // .inputFluids(SolderingAlloy.getFluid(L * 2))
        // .outputItems(AECSBlocks.INTEGRATED_INTERFACE_BLOCK,1)
        // .EUt(VA[HV]).duration(300)
        // .save(provider);
        // ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ex_integrated_interface"))
        // .circuitMeta(15)
        // .inputItems(TagPrefix.ring, StainlessSteel,4)
        // .inputItems(TagPrefix.gear, StainlessSteel,1)
        // .inputItems(AECSItems.RESONATING_PROCESSOR, 2)
        // .inputItems(EPPItemAndBlock.EX_PATTERN_PROVIDER.asItem(),1)
        // .inputItems(EPPItemAndBlock.EX_INTERFACE.asItem(),1)
        // .inputFluids(SolderingAlloy.getFluid(L * 2))
        // .outputItems(AECSBlocks.EX_INTEGRATED_INTERFACE_BLOCK,1)
        // .EUt(VA[HV]).duration(300)
        // .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(CTNHCore.id("blank_print_press"))
                .inputItems(TagPrefix.plateDense, Iron, 1)
                .inputItems(AECSItems.SIMPLE_CIRCUIT_PRINT, 4)
                .circuitMeta(23)
                .outputItems(AECSItems.BLANK_PRINT_PRESS, 1)
                .EUt(VA[LV]).duration(200)
                .save(provider);
        CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("ender_blank_print_press"))
                .inputItems(AECSItems.BLANK_PRINT_PRESS, 1)
                .inputFluids(ENDER_STEEL.getFluid(L * 4))
                .circuitMeta(23)
                .outputItems(AECSItems.ENDER_BLANK_PRINT_PRESS, 1)
                .EUt(VA[HV]).duration(500)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(CTNHCore.id("simple_circuit_print"))
                .inputItems(TagPrefix.plate, NetherQuartz, 1)
                .notConsumable(AEItems.ENGINEERING_PROCESSOR_PRESS.asItem())
                .circuitMeta(23)
                .outputItems(AECSItems.SIMPLE_CIRCUIT_PRINT, 1)
                .EUt(VA[LV]).duration(200)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(CTNHCore.id("simple_circuit_print_pure"))
                .inputItems(AECSItems.PURE_NETHER_QUARTZ_CRYSTAL, 1)
                .notConsumable(AEItems.ENGINEERING_PROCESSOR_PRESS.asItem())
                .circuitMeta(23)
                .outputItems(AECSItems.SIMPLE_CIRCUIT_PRINT, 2)
                .EUt(VA[LV]).duration(200)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(CTNHCore.id("simple_processor"))
                .inputItems(AECSItems.SIMPLE_CIRCUIT_PRINT, 2)
                .inputItems(AEItems.SILICON_PRINT.asItem(), 2)
                .inputItems(CustomTags.LV_CIRCUITS)
                .circuitMeta(23)
                .outputItems(AECSItems.SIMPLE_PROCESSOR, 2)
                .EUt(VA[LV]).duration(100)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(CTNHCore.id("resonating_circuit_print"))
                .inputItems(AECSItems.PURE_RESONATING_CRYSTAL, 1)
                .notConsumable(AECSItems.RESONATING_PRINT_PRESS.asItem())
                .circuitMeta(23)
                .outputItems(AECSItems.RESONATING_CIRCUIT_PRINT, 2)
                .EUt(VA[MV]).duration(200)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(CTNHCore.id("resonating_processor"))
                .inputItems(AECSItems.RESONATING_CIRCUIT_PRINT, 2)
                .inputItems(AEItems.SILICON_PRINT.asItem(), 2)
                .inputItems(CustomTags.LV_CIRCUITS)
                .circuitMeta(23)
                .outputItems(AECSItems.RESONATING_PROCESSOR, 2)
                .EUt(VA[HV]).duration(360)
                .save(provider);
        LASER_ENGRAVER_RECIPES.recipeBuilder(CTNHCore.id("calculation_processor_press"))
                .inputItems(AECSItems.BLANK_PRINT_PRESS, 1)
                .notConsumable(lens, MarkerMaterials.Color.White)
                .outputItems(AEItems.CALCULATION_PROCESSOR_PRESS.asItem())
                .EUt(VA[HV]).duration(360)
                .save(provider);
        LASER_ENGRAVER_RECIPES.recipeBuilder(CTNHCore.id("engineering_processor_press"))
                .inputItems(AECSItems.BLANK_PRINT_PRESS, 1)
                .notConsumable(lens, MarkerMaterials.Color.Blue)
                .outputItems(AEItems.ENGINEERING_PROCESSOR_PRESS.asItem())
                .EUt(VA[HV]).duration(360)
                .save(provider);
        LASER_ENGRAVER_RECIPES.recipeBuilder(CTNHCore.id("logic_processor_press"))
                .inputItems(AECSItems.BLANK_PRINT_PRESS, 1)
                .notConsumable(lens, MarkerMaterials.Color.Yellow)
                .outputItems(AEItems.LOGIC_PROCESSOR_PRESS.asItem())
                .EUt(VA[HV]).duration(360)
                .save(provider);
        LASER_ENGRAVER_RECIPES.recipeBuilder(CTNHCore.id("silicon_press"))
                .inputItems(AECSItems.BLANK_PRINT_PRESS, 1)
                .notConsumable(lens, MarkerMaterials.Color.Gray)
                .outputItems(AEItems.SILICON_PRESS.asItem())
                .EUt(VA[HV]).duration(360)
                .save(provider);
        LASER_ENGRAVER_RECIPES.recipeBuilder(CTNHCore.id("resonating_print_press"))
                .inputItems(AECSItems.BLANK_PRINT_PRESS, 1)
                .notConsumable(TagPrefix.lens, Diamond)
                .outputItems(AECSItems.RESONATING_PRINT_PRESS.asItem())
                .EUt(VA[HV]).duration(360)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("resonating_pattern_provider"))
                .inputItems(AECSItems.RESONATING_PROCESSOR, 4)
                .inputItems(OCItems.OMNI_LINK_PROCESSOR, 4)
                .inputItems(OCItems.COMPLEX_LINK_PROCESSOR, 4)
                .inputItems(AEItems.CALCULATION_PROCESSOR.asItem(), 4)
                .inputItems(AEItems.LOGIC_PROCESSOR.asItem(), 4)
                .inputItems(AEItems.ENGINEERING_PROCESSOR.asItem(), 4)
                .inputItems(AECSBlocks.ENDER_INTERFACE_BLOCK.asItem())
                .inputItems(AEBlocks.PATTERN_PROVIDER.asItem())
                .inputItems(CustomTags.LuV_CIRCUITS, 2)
                .inputItems(CustomTags.IV_CIRCUITS, 2)
                .inputItems(ROBOT_ARM_LuV, 2)
                .inputItems(FLUID_REGULATOR_LuV, 2)
                .inputFluids(ENDER_STEEL.getFluid(L * 4))
                .inputFluids(SolderingAlloy.getFluid(L * 4))
                .outputItems(AECSBlocks.RESONATING_PATTERN_PROVIDER_BLOCK, 1)
                .scannerResearch(b -> b
                        .researchStack(AECSBlocks.ENDER_INTERFACE_BLOCK.toStack())
                        .duration(3000)
                        .EUt(VA[IV]))
                .EUt(VA[LuV]).duration(1200)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("extended_resonating_pattern_provider"))
                .inputItems(AECSBlocks.RESONATING_PATTERN_PROVIDER_BLOCK, 2)
                .inputItems(CustomTags.ZPM_CIRCUITS, 2)
                .inputItems(CustomTags.LuV_CIRCUITS, 2)
                .inputItems(CustomTags.IV_CIRCUITS, 2)
                .inputItems(AEBlocks.CRAFTING_ACCELERATOR.asItem(), 8)
                .inputItems(CrystalItems.PURE_CERTUS_ENERGIUM_CRYSTAL.asItem(), 2)
                .inputItems(CrystalItems.PURE_CERTUS_LAPOTRON_CRYSTAL.asItem(), 2)
                .inputItems(AEItems.CAPACITY_CARD.asItem(), 4)
                .inputItems(ROBOT_ARM_LuV, 2)
                .inputItems(FLUID_REGULATOR_LuV, 2)
                .outputItems(AECSBlocks.EX_RESONATING_PATTERN_PROVIDER_BLOCK.asItem(), 1)
                .scannerResearch(b -> b
                        .researchStack(AECSBlocks.RESONATING_PATTERN_PROVIDER_BLOCK.toStack())
                        .duration(3000)
                        .EUt(VA[IV]))
                .EUt(VA[LuV]).duration(1200)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ender_interface"))
                .circuitMeta(15)
                .inputItems(AECSItems.RESONATING_PROCESSOR, 2)
                .inputItems(AECSItems.PURE_ENDER_QUARTZ, 2)
                .inputItems(AEBlocks.INTERFACE.asItem(), 1)
                .inputItems(SENSOR_HV, 1)
                .inputItems(EMITTER_HV, 1)
                .outputItems(AECSBlocks.ENDER_INTERFACE_BLOCK, 1)
                .EUt(VA[HV]).duration(200)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("extended_ender_interface"))
                .circuitMeta(15)
                .inputItems(AECSBlocks.ENDER_INTERFACE_BLOCK, 2)
                .inputItems(AECSItems.PURE_ENDER_QUARTZ, 2)
                .inputItems(ELECTRIC_PISTON_EV, 2)
                .inputItems(EMITTER_EV, 1)
                .inputItems(SENSOR_EV, 1)
                .inputItems(AEItems.CAPACITY_CARD.asItem())
                .inputItems(AECSItems.RESONATING_PROCESSOR, 1)
                .outputItems(AECSBlocks.EX_ENDER_INTERFACE_BLOCK.asItem(), 1)
                .EUt(VA[EV]).duration(500)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ender_broadcaster"))
                .circuitMeta(15)
                .inputItems(AEBlocks.CONTROLLER.asItem(), 4)
                .inputItems(AEItems.MATTER_BALL.asItem(), 4)
                .inputItems(AECSItems.RESONATING_PROCESSOR, 2)
                .inputItems(CustomTags.HV_CIRCUITS, 2)
                .inputItems(EIOItems.ENDER_CRYSTAL.asItem(), 2)
                .outputItems(AECSBlocks.ENDER_BROADCASTER_BLOCK.asItem(), 1)
                .EUt(VA[EV]).duration(1000)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("pattern_provider_upgrade"))
                .circuitMeta(16)
                .inputItems(TagPrefix.plate, StainlessSteel, 4)
                .inputItems(ELECTRIC_MOTOR_HV, 2)
                .inputItems(CONVEYOR_MODULE_HV, 2)
                .inputItems(AEItems.BLANK_PATTERN.asItem(), 1)
                .inputItems(AEItems.ENGINEERING_PROCESSOR.asItem(), 1)
                .outputItems(AECSItems.PATTERN_PROVIDER_UPGRADE, 1)
                .EUt(VA[HV]).duration(200)
                .save(provider);
        // ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("integrated_interface_upgrade"))
        // .circuitMeta(16)
        // .inputItems(TagPrefix.plate,StainlessSteel,4)
        // .inputItems(TagPrefix.gearSmall,StainlessSteel,2)
        // .inputItems(ROBOT_ARM_HV,2)
        // .inputItems(CONVEYOR_MODULE_HV,2)
        // .inputItems(AEItems.ENGINEERING_PROCESSOR.asItem(),2)
        // .inputItems(AECSItems.RESONATING_PROCESSOR.asItem(),2)
        // .outputItems(AECSItems.INTEGRATED_INTERFACE_UPGRADE,2)
        // .EUt(VA[HV]).duration(200)
        // .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ender_interface_upgrade"))
                .circuitMeta(16)
                .inputItems(AECSItems.RESONATING_PROCESSOR, 2)
                .inputItems(AECSItems.PURE_ENDER_QUARTZ, 2)
                .inputItems(AEItems.BLANK_PATTERN.asItem(), 1)
                .inputItems(SENSOR_HV, 1)
                .inputItems(EMITTER_HV, 1)
                .outputItems(AECSItems.ENDER_INTERFACE_UPGRADE, 1)
                .EUt(VA[HV]).duration(200)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("extended_ender_interface_upgrade"))
                .circuitMeta(16)
                .inputItems(AEItems.BLANK_PATTERN.asItem(), 2)
                .inputItems(AECSItems.PURE_ENDER_QUARTZ, 2)
                .inputItems(ELECTRIC_PISTON_EV, 2)
                .inputItems(EMITTER_EV, 1)
                .inputItems(SENSOR_EV, 1)
                .inputItems(AEItems.CAPACITY_CARD.asItem())
                .inputItems(AECSItems.RESONATING_PROCESSOR, 1)
                .outputItems(AECSItems.EX_ENDER_INTERFACE_UPGRADE.asItem(), 1)
                .EUt(VA[EV]).duration(500)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("resonating_pattern_provider_upgrade"))
                .inputItems(AECSItems.RESONATING_PROCESSOR, 4)
                .inputItems(OCItems.OMNI_LINK_PROCESSOR, 4)
                .inputItems(OCItems.COMPLEX_LINK_PROCESSOR, 4)
                .inputItems(AEItems.CALCULATION_PROCESSOR.asItem(), 4)
                .inputItems(AEItems.LOGIC_PROCESSOR.asItem(), 4)
                .inputItems(AEItems.ENGINEERING_PROCESSOR.asItem(), 4)
                .inputItems(AEItems.BLANK_PATTERN.asItem(), 8)
                .inputItems(CustomTags.LuV_CIRCUITS, 2)
                .inputItems(CustomTags.IV_CIRCUITS, 2)
                .inputItems(ROBOT_ARM_LuV, 2)
                .inputItems(FLUID_REGULATOR_LuV, 2)
                .inputFluids(ENDER_STEEL.getFluid(L * 4))
                .inputFluids(SolderingAlloy.getFluid(L * 4))
                .outputItems(AECSItems.RESONATING_PATTERN_PROVIDER_UPGRADE, 1)
                .scannerResearch(b -> b
                        .researchStack(AECSItems.ENDER_INTERFACE_UPGRADE.toStack())
                        .duration(3000)
                        .EUt(VA[IV]))
                .EUt(VA[LuV]).duration(1200)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("extended_resonating_pattern_provider_upgrade"))
                .inputItems(AEItems.BLANK_PATTERN.asItem(), 16)
                .inputItems(CustomTags.ZPM_CIRCUITS, 2)
                .inputItems(CustomTags.LuV_CIRCUITS, 2)
                .inputItems(CustomTags.IV_CIRCUITS, 2)
                .inputItems(AEBlocks.CRAFTING_ACCELERATOR.asItem(), 8)
                .inputItems(CrystalItems.PURE_CERTUS_ENERGIUM_CRYSTAL.asItem(), 2)
                .inputItems(CrystalItems.PURE_CERTUS_LAPOTRON_CRYSTAL.asItem(), 2)
                .inputItems(AEItems.CAPACITY_CARD.asItem(), 4)
                .inputItems(ROBOT_ARM_LuV, 2)
                .inputItems(FLUID_REGULATOR_LuV, 2)
                .outputItems(AECSItems.EX_RESONATING_PATTERN_PROVIDER_UPGRADE.asItem(), 1)
                .scannerResearch(b -> b
                        .researchStack(AECSItems.RESONATING_PATTERN_PROVIDER_UPGRADE.toStack())
                        .duration(3000)
                        .EUt(VA[IV]))
                .EUt(VA[LuV]).duration(1200)
                .save(provider);
        // ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("meteorite_pattern_provider"))
        // .circuitMeta(15)
        // .inputItems(EPPItemAndBlock.EX_ASSEMBLER.asItem())
        // .inputItems(ROBOT_ARM_EV,2)
        // .inputItems(CONVEYOR_MODULE_EV,2)
        // .inputItems(AEItems.SINGULARITY.asItem(),2)
        // .inputItems(OCItems.OMNI_CELL_COMPONENT_256K,1)
        // .inputItems(AECSItems.RESONATING_PROCESSOR,3)
        // .inputItems(OCItems.OMNI_LINK_PROCESSOR,3)
        // .inputItems(AEBlocks.PATTERN_PROVIDER.asItem(),1)
        // .outputItems(AECSBlocks.METEORITE_PATTERN_PROVIDER_BLOCK.asItem(),1)
        // .EUt(VA[EV]).duration(750)
        // .save(provider);
        // ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("meteorite_pattern_provider_upgrade"))
        // .circuitMeta(16)
        // .inputItems(EPPItemAndBlock.EX_ASSEMBLER.asItem())
        // .inputItems(ROBOT_ARM_EV,2)
        // .inputItems(CONVEYOR_MODULE_EV,2)
        // .inputItems(AEItems.SINGULARITY.asItem(),2)
        // .inputItems(OCItems.OMNI_CELL_COMPONENT_256K,1)
        // .inputItems(AECSItems.RESONATING_PROCESSOR,3)
        // .inputItems(OCItems.OMNI_LINK_PROCESSOR,3)
        // .inputItems(AEItems.BLANK_PATTERN.asItem(),1)
        // .outputItems(AECSItems.METEOR_PATTERN_PROVIDER_UPGRADE.asItem(),1)
        // .EUt(VA[EV]).duration(750)
        // .save(provider);
        // 合成表配方
        VanillaRecipeHelper.addShapedRecipe(provider, true, "simple_pattern_provider",
                AECSBlocks.SIMPLE_PATTERN_PROVIDER_BLOCK.toStack(),
                "ABA",
                "EDE",
                "ACA",
                'A', new MaterialEntry(TagPrefix.plateDense, Iron),
                'B', CONVEYOR_MODULE_LV,
                'C', ELECTRIC_MOTOR_LV,
                'D', new MaterialEntry(TagPrefix.frameGt, Silver),
                'E', AECSItems.SIMPLE_PROCESSOR.asItem());
        VanillaRecipeHelper.addShapedRecipe(provider, true, "resonating_pattern_converter",
                AECSItems.RESONATING_PATTERN_CONVERTER.toStack(),
                "ABA",
                "EDE",
                "FCF",
                'A', new MaterialEntry(TagPrefix.screw, Diamond),
                'B', AECSItems.RESONATING_PROCESSOR,
                'C', new MaterialEntry(TagPrefix.wireGtSingle, BlackSteel),
                'D', CustomTags.MV_CIRCUITS,
                'E', new MaterialEntry(TagPrefix.plate, StainlessSteel),
                'F', AECSItems.RESONATING_DUST.asItem());
        VanillaRecipeHelper.addShapedRecipe(provider, true, "ender_linker",
                AECSItems.enderLink.toStack(),
                "ABA",
                "EDE",
                "FCF",
                'A', AECSItems.PURE_ENDER_QUARTZ,
                'E', new MaterialEntry(TagPrefix.plate, BlackSteel),
                'C', new MaterialEntry(TagPrefix.wireGtSingle, BlackSteel),
                'D', CustomTags.HV_CIRCUITS,
                'B', AECSItems.RESONATING_PROCESSOR,
                'F', AECSItems.RESONATING_DUST);
        VanillaRecipeHelper.addShapedRecipe(provider, true, "quartz_oscillator_clock",
                AECSBlocks.QUARTZ_OSCILLATOR_CLOCK_BLOCK.toStack(),
                "ABA",
                "EDE",
                "FCF",
                'A', new MaterialEntry(TagPrefix.plate, RedAlloy),
                'E', Items.COMPARATOR.asItem(),
                'C', AECSBlocks.SIMPLE_PATTERN_PROVIDER_BLOCK.asItem(),
                'D', CustomTags.LV_CIRCUITS,
                'B', Items.CLOCK.asItem(),
                'F', AECSItems.RESONATING_PROCESSOR.asItem());
        VanillaRecipeHelper.addShapedRecipe(provider, true, "simple_pattern_provider_part",
                AECSParts.SIMPLE_PATTERN_PROVIDER_PART.toStack(),
                "A  ",
                "   ",
                "   ",
                'A', AECSBlocks.SIMPLE_PATTERN_PROVIDER_BLOCK.asItem());
        VanillaRecipeHelper.addShapedRecipe(provider, true, "ender_interface_part",
                AECSParts.ENDER_INTERFACE_PART.toStack(),
                "A  ",
                "   ",
                "   ",
                'A', AECSBlocks.ENDER_INTERFACE_BLOCK.asItem());

        VanillaRecipeHelper.addShapedRecipe(provider, true, "ex_ender_interface_part",
                AECSParts.EX_ENDER_INTERFACE_PART.toStack(),
                "A  ",
                "   ",
                "   ",
                'A', AECSBlocks.EX_ENDER_INTERFACE_BLOCK.asItem());

        VanillaRecipeHelper.addShapedRecipe(provider, true, "resonating_pattern_provider_part",
                AECSParts.RESONATING_PATTERN_PROVIDER_PART.toStack(),
                "A  ",
                "   ",
                "   ",
                'A', AECSBlocks.RESONATING_PATTERN_PROVIDER_BLOCK.asItem());

        VanillaRecipeHelper.addShapedRecipe(provider, true, "ex_resonating_pattern_provider_part",
                AECSParts.EX_RESONATING_PATTERN_PROVIDER_PART.toStack(),
                "A  ",
                "   ",
                "   ",
                'A', AECSBlocks.EX_RESONATING_PATTERN_PROVIDER_BLOCK.asItem());

        VanillaRecipeHelper.addShapedRecipe(provider, true, "quartz_oscillator_clock_part",
                AECSParts.QUARTZ_OSCILLATOR_CLOCK_PART.toStack(),
                "A  ",
                "   ",
                "   ",
                'A', AECSBlocks.QUARTZ_OSCILLATOR_CLOCK_BLOCK.asItem());

        VanillaRecipeHelper.addShapedRecipe(provider, true, "meteorite_pattern_provider_part",
                AECSParts.METEORITE_PATTERN_PROVIDER_PART.toStack(),
                "A  ",
                "   ",
                "   ",
                'A', AECSBlocks.METEORITE_PATTERN_PROVIDER_BLOCK.asItem());

        VanillaRecipeHelper.addShapedRecipe(provider, true, "integrated_interface_part",
                AECSParts.INTEGRATE_INTERFACE_PART.toStack(),
                "A  ",
                "   ",
                "   ",
                'A', AECSBlocks.INTEGRATED_INTERFACE_BLOCK.asItem());

        VanillaRecipeHelper.addShapedRecipe(provider, true, "ex_integrated_interface_part",
                AECSParts.EX_INTEGRATE_INTERFACE_PART.toStack(),
                "A  ",
                "   ",
                "   ",
                'A', AECSBlocks.EX_INTEGRATED_INTERFACE_BLOCK.asItem());
        // 高纯水晶
        // 末影水晶
        CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("ender_quartz_seed"))
                .inputItems(EIOItems.ENDER_CRYSTAL, 1)
                .inputFluids(ENDER_STEEL.getFluid(L))
                .outputItems(AECSItems.ENDER_QUARTZ_SEED, 1)
                .EUt(VA[HV]).duration(200)
                .save(provider);
        AUTOCLAVE_RECIPES.recipeBuilder(CTNHCore.id("purified_ender_quartz"))
                .inputItems(AECSItems.ENDER_QUARTZ_SEED, 1)
                .inputFluids(ENDER_STEEL.getFluid(L))
                .outputItems(AECSItems.PURE_ENDER_QUARTZ, 1)
                .EUt(VA[HV]).duration(600)
                .save(provider);
        CRYSTALLIZER.recipeBuilder(CTNHCore.id("purified_ender_quartz"))
                .inputItems(AECSItems.ENDER_QUARTZ_SEED, 8)
                .inputFluids(ENDER_STEEL.getFluid(L * 2))
                .outputItems(AECSItems.PURE_ENDER_QUARTZ, 8)
                .blastFurnaceTemp(5100)
                .EUt(VA[IV]).duration(300)
                .save(provider);
        // 赛特斯石英
        ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("certus_quartz_seed"))
                .inputItems(TagPrefix.dustSmall, CertusQuartz, 5)
                .inputItems(TagPrefix.dustSmall, SiliconDioxide, 4)
                .outputItems(AECSItems.CERTUS_QUARTZ_SEED, 4)
                .EUt(VA[MV]).duration(500)
                .save(provider);
        AUTOCLAVE_RECIPES.recipeBuilder(CTNHCore.id("purified_certus_quartz_crystal"))
                .inputItems(AECSItems.CERTUS_QUARTZ_SEED, 1)
                .inputFluids(DistilledWater.getFluid(100))
                .outputItems(AECSItems.PURE_CERTUS_QUARTZ_CRYSTAL, 1)
                .EUt(VA[HV]).duration(200)
                .save(provider);
        CRYSTALLIZER.recipeBuilder(CTNHCore.id("purified_certus_quartz_crystal"))
                .inputItems(AECSItems.CERTUS_QUARTZ_SEED, 8)
                .inputFluids(DistilledWater.getFluid(100))
                .outputItems(AECSItems.PURE_CERTUS_QUARTZ_CRYSTAL, 8)
                .blastFurnaceTemp(2700)
                .EUt(VA[IV]).duration(100)
                .save(provider);
        // 主世界石英
        ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("nether_quartz_seed"))
                .inputItems(TagPrefix.dustSmall, NetherQuartz, 5)
                .inputItems(TagPrefix.dustSmall, SiliconDioxide, 4)
                .outputItems(AECSItems.NETHER_QUARTZ_SEED, 4)
                .EUt(VA[LV]).duration(500)
                .save(provider);
        AUTOCLAVE_RECIPES.recipeBuilder(CTNHCore.id("purified_nether_quartz_crystal"))
                .inputItems(AECSItems.NETHER_QUARTZ_SEED, 1)
                .inputFluids(DistilledWater.getFluid(100))
                .outputItems(AECSItems.PURE_NETHER_QUARTZ_CRYSTAL, 1)
                .EUt(VA[MV]).duration(200)
                .save(provider);
        CRYSTALLIZER.recipeBuilder(CTNHCore.id("purified_nether_quartz_crystal"))
                .inputItems(AECSItems.NETHER_QUARTZ_SEED, 8)
                .inputFluids(DistilledWater.getFluid(100))
                .outputItems(AECSItems.PURE_NETHER_QUARTZ_CRYSTAL, 8)
                .blastFurnaceTemp(5500)
                .EUt(VA[IV]).duration(100)
                .save(provider);
        // 福水晶
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("fluix_quartz_seed"))
                .inputItems(AECSItems.NETHER_QUARTZ_SEED, 1)
                .inputItems(AECSItems.CERTUS_QUARTZ_SEED, 1)
                .inputFluids(Redstone.getFluid(L))
                .outputItems(AECSItems.FLUIX_CRYSTAL_SEED, 2)
                .blastFurnaceTemp(1431)
                .EUt(VA[HV]).duration(600)
                .save(provider);
        AUTOCLAVE_RECIPES.recipeBuilder(CTNHCore.id("purified_fluix_crystal"))
                .inputItems(AECSItems.FLUIX_CRYSTAL_SEED, 1)
                .inputFluids(Redstone.getFluid(1000))
                .outputItems(AECSItems.PURE_FLUIX_CRYSTAL, 1)
                .EUt(VA[HV]).duration(300)
                .save(provider);
        CRYSTALLIZER.recipeBuilder(CTNHCore.id("purified_fluix_crystal"))
                .inputItems(AECSItems.FLUIX_CRYSTAL_SEED, 8)
                .inputFluids(Redstone.getFluid(2000))
                .outputItems(AECSItems.PURE_FLUIX_CRYSTAL, 8)
                .blastFurnaceTemp(5500)
                .EUt(VA[IV]).duration(150)
                .save(provider);
        // 陨石水晶
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("meteor_seed"))
                .inputItems(AEItems.SKY_DUST, 1)
                .inputItems(TagPrefix.dust, CertusQuartz, 1)
                .inputFluids(Lava.getFluid(1000))
                .outputItems(AECSItems.METEOR_SEED, 2)
                .blastFurnaceTemp(2700)
                .EUt(VA[HV]).duration(1200)
                .save(provider);
        AUTOCLAVE_RECIPES.recipeBuilder(CTNHCore.id("purified_meteor_crystal"))
                .inputItems(AECSItems.METEOR_SEED, 1)
                .inputFluids(Lava.getFluid(1000))
                .outputItems(AECSItems.PURE_METEOR_CRYSTAL, 1)
                .EUt(VA[HV]).duration(300)
                .save(provider);
        CRYSTALLIZER.recipeBuilder(CTNHCore.id("purified_meteor_crystal"))
                .inputItems(AECSItems.METEOR_SEED, 8)
                .inputFluids(Lava.getFluid(2000))
                .outputItems(AECSItems.PURE_METEOR_CRYSTAL, 8)
                .blastFurnaceTemp(5500)
                .EUt(VA[IV]).duration(150)
                .save(provider);
        // 谐振水晶
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("mix_crystal_sand"))
                .inputItems(TagPrefix.dust, PURIFIED_ENDER_QUARTZ, 1)
                .inputItems(TagPrefix.dust, PURIFIED_METEOR_CRYSTAL, 1)
                .inputFluids(Lava.getFluid(1000))
                .inputFluids(ENDER_STEEL.getFluid(L))
                .outputItems(TagPrefix.dust, CRYSTAL_SAND, 2)
                .EUt(VA[HV]).duration(700)
                .save(provider);
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("resonating_seed"))
                .inputItems(TagPrefix.dust, CRYSTAL_SAND, 1)
                .inputFluids(BlueSteel.getFluid(L * 2))
                .outputItems(AECSItems.RESONATING_SEED, 1)
                .blastFurnaceTemp(3700)
                .EUt(VA[HV]).duration(600)
                .save(provider);
        AUTOCLAVE_RECIPES.recipeBuilder(CTNHCore.id("purified_resonating_crystal"))
                .inputItems(AECSItems.RESONATING_SEED, 1)
                .inputFluids(BlueSteel.getFluid(L))
                .outputItems(AECSItems.PURE_RESONATING_CRYSTAL, 1)
                .EUt(VA[HV]).duration(600)
                .save(provider);
        CRYSTALLIZER.recipeBuilder(CTNHCore.id("purified_resonating_crystal"))
                .inputItems(AECSItems.RESONATING_SEED, 8)
                .inputFluids(BlueSteel.getFluid(L * 2))
                .outputItems(AECSItems.PURE_RESONATING_CRYSTAL, 8)
                .blastFurnaceTemp(5500)
                .EUt(VA[IV]).duration(300)
                .save(provider);
        ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("resonating_seed"))
                .inputItems(AECSItems.RESONATING_DUST, 1)
                .inputItems(AEItems.FLUIX_DUST.asItem(), 1)
                .outputItems(AECSItems.RESONATING_SEED, 2)
                .EUt(VA[EV]).duration(600)
                .save(provider);
    }
}
