package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipes;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.MarkerMaterial;
import com.gregtechceu.gtceu.api.data.chemical.material.MarkerMaterials;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import appeng.api.util.AEColor;
import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import appeng.core.definitions.AEParts;
import appeng.recipes.transform.TransformCircumstance;
import appeng.recipes.transform.TransformRecipeBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wintercogs.ae2omnicells.common.init.OCBlocks;
import com.enderio.machines.common.init.MachineBlocks;
import com.glodblock.github.extendedae.common.EPPItemAndBlock;
import vazkii.botania.common.block.BotaniaBlocks;
import com.wintercogs.ae2omnicells.common.init.OCItems;
import io.github.cpearl0.ctnhcore.data.materials.EnderIOMaterials;
import io.github.cpearl0.ctnhcore.data.materials.NewExplosivesProductionMaterials;
import io.github.cpearl0.ctnhcore.data.materials.SpecialMaterials;
import tech.luckyblock.mcmod.ctnhenergy.registry.CEItems;

import java.util.Objects;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

public class AE2ScriptRecipe {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 元件升级合成
        addCellComponentUpgradeRecipes(provider);
        // 机器/零件合成
        addMachineRecipes(provider);
        // GT 机器配方和处理器配方
        addProcessorRecipes(provider);
        // 杂项 GT 配方
        addMiscRecipes(provider);
    }

    // ============== 元件升级合成 ==============
    private static void addCellComponentUpgradeRecipes(Consumer<FinishedRecipe> provider) {
        ItemLike[] cells = {
                AEItems.CELL_COMPONENT_1K.asItem(), AEItems.CELL_COMPONENT_4K.asItem(),
                AEItems.CELL_COMPONENT_16K.asItem(), AEItems.CELL_COMPONENT_64K.asItem(),
                AEItems.CELL_COMPONENT_256K.asItem()
        };
        ItemLike[] omnis = {
                OCItems.OMNI_CELL_COMPONENT_1K.get(), OCItems.OMNI_CELL_COMPONENT_4K.get(),
                OCItems.OMNI_CELL_COMPONENT_16K.get(), OCItems.OMNI_CELL_COMPONENT_64K.get(),
                OCItems.OMNI_CELL_COMPONENT_256K.get(), OCItems.OMNI_CELL_COMPONENT_1M.get(),
                OCItems.OMNI_CELL_COMPONENT_4M.get(), OCItems.OMNI_CELL_COMPONENT_16M.get(),
                OCItems.OMNI_CELL_COMPONENT_64M.get()
        };
        ItemLike[] cOmnis = {
                OCItems.COMPLEX_OMNI_CELL_COMPONENT_1K.get(), OCItems.COMPLEX_OMNI_CELL_COMPONENT_4K.get(),
                OCItems.COMPLEX_OMNI_CELL_COMPONENT_16K.get(), OCItems.COMPLEX_OMNI_CELL_COMPONENT_64K.get(),
                OCItems.COMPLEX_OMNI_CELL_COMPONENT_256K.get(), OCItems.COMPLEX_OMNI_CELL_COMPONENT_1M.get(),
                OCItems.COMPLEX_OMNI_CELL_COMPONENT_4M.get(), OCItems.COMPLEX_OMNI_CELL_COMPONENT_16M.get(),
                OCItems.COMPLEX_OMNI_CELL_COMPONENT_64M.get()
        };
        Object[] dusts = {
                Items.REDSTONE, Items.GLOWSTONE_DUST,
                ChemicalHelper.get(dust, CertusQuartz), AEItems.SKY_DUST.asItem(),
                ChemicalHelper.get(dust, Silicon), ChemicalHelper.get(dust, Electrotine),
                AEItems.FLUIX_DUST.asItem(), ChemicalHelper.get(dust, Yttrofluorite)
        };
        ItemLike[] glasses = {
                AEBlocks.QUARTZ_GLASS.asItem(), AEBlocks.QUARTZ_VIBRANT_GLASS.asItem(),
                BotaniaBlocks.manaGlass.asItem(), BotaniaBlocks.elfGlass.asItem()
        };
        ItemLike[] iGlasses = {
                AEBlocks.QUARTZ_GLASS.asItem(), AEBlocks.QUARTZ_VIBRANT_GLASS.asItem(),
                GTBlocks.CASING_LAMINATED_GLASS.asItem(), GTBlocks.FUSION_GLASS.asItem()
        };
        Object[] metals = {
                ChemicalHelper.get(plate, Steel), ChemicalHelper.get(plate, Aluminium),
                ChemicalHelper.get(plate, StainlessSteel), ChemicalHelper.get(plate, Titanium),
                ChemicalHelper.get(plate, TungstenSteel), ChemicalHelper.get(plate, RhodiumPlatedPalladium),
                ChemicalHelper.get(plate, NaquadahAlloy), ChemicalHelper.get(plate, Darmstadtium)
        };

        for (int i = 0; i < 8; i++) {
            int g = i / 2;
            TagKey<Item> circuit = switch (i) {
                case 0 -> CustomTags.LV_CIRCUITS;
                case 1 -> CustomTags.MV_CIRCUITS;
                case 2 -> CustomTags.HV_CIRCUITS;
                case 3 -> CustomTags.EV_CIRCUITS;
                case 4 -> CustomTags.IV_CIRCUITS;
                case 5 -> CustomTags.LuV_CIRCUITS;
                case 6 -> CustomTags.ZPM_CIRCUITS;
                case 7 -> CustomTags.UV_CIRCUITS;
                default -> throw new IllegalArgumentException();
            };
            if (i <= 3)
                shaped(provider, "cell_component_upgrade_" + i, cells[i + 1],
                        "BDB", "ACA", "EAE",
                        'A', cells[i], 'B', dusts[i], 'C', glasses[g], 'D', circuit, 'E', metals[i]);
            if (i >= 4) {
                shaped(provider, "omni_component_upgrade_industrial_" + i, omnis[i + 1],
                        "BDB", "ACA", "EAE",
                        'A', omnis[i], 'B', dusts[i], 'C', iGlasses[g], 'D', circuit, 'E', metals[i]);
                shaped(provider, "complex_omni_component_upgrade_industrial_" + i, cOmnis[i + 1],
                        "BDB", "ACA", "EAE",
                        'A', cOmnis[i], 'B', dusts[i], 'C', iGlasses[g], 'D', circuit, 'E', metals[i]);
            }
            shaped(provider, "omni_component_upgrade_" + i, omnis[i + 1],
                    "BDB", "ACA", "EAE",
                    'A', omnis[i], 'B', dusts[i], 'C', glasses[g], 'D', circuit, 'E', metals[i]);
            shaped(provider, "complex_omni_component_upgrade_" + i, cOmnis[i + 1],
                    "BDB", "ACA", "EAE",
                    'A', cOmnis[i], 'B', dusts[i], 'C', glasses[g], 'D', circuit, 'E', metals[i]);
        }

        shaped(provider, "cell_component_1k", AEItems.CELL_COMPONENT_1K.asItem(),
                "BDB", "ACA", "EAE",
                'A', ChemicalHelper.get(plate, CertusQuartz), 'B', Items.REDSTONE,
                'C', GTBlocks.CASING_TEMPERED_GLASS.asItem(), 'D', CustomTags.ULV_CIRCUITS,
                'E', ChemicalHelper.get(plate, Iron));
        shaped(provider, "omni_cell_component_1k", OCItems.OMNI_CELL_COMPONENT_1K.get(),
                "BDB", "ACA", "BAB",
                'A', ChemicalHelper.get(plate, CertusQuartz), 'B', ChemicalHelper.get(dust, EnderPearl),
                'C', AEItems.CELL_COMPONENT_1K.asItem(), 'D', CustomTags.ULV_CIRCUITS);
        shaped(provider, "complex_omni_cell_component_1k", OCItems.COMPLEX_OMNI_CELL_COMPONENT_1K.get(),
                "BDB", "ACA", "EAE",
                'A', ChemicalHelper.get(plate, CertusQuartz), 'B', ChemicalHelper.get(dust, EnderPearl),
                'C', AEItems.CELL_COMPONENT_1K.asItem(), 'D', CustomTags.ULV_CIRCUITS,
                'E', AEItems.FLUIX_DUST.asItem());

        for (int i = 0; i < 4; i++) {
            var input = (i < 2) ? OCItems.OMNI_CELL_COMPONENT_64M.get()
                    : OCItems.COMPLEX_OMNI_CELL_COMPONENT_64M.get();
            var output = (i < 2) ? OCItems.OMNI_CELL_COMPONENT_256M.get()
                    : OCItems.COMPLEX_OMNI_CELL_COMPONENT_256M.get();
            var glass = (i % 2 == 0) ? GTBlocks.FUSION_GLASS.asItem() : BotaniaBlocks.elfGlass.asItem();
            var suffix = (i < 2 ? "omni" : "complex") + "_256m_" + (i % 2 == 0 ? "industrial" : "elf");
            shaped(provider, "cell_component_" + suffix, output,
                    "BDB", "ACA", "EAE",
                    'A', input, 'B', ChemicalHelper.get(dust, EuropiumFluorite),
                    'C', glass, 'D', CustomTags.UHV_CIRCUITS,
                    'E', ChemicalHelper.get(plate, Neutronium));
        }
    }

    private static void addMachineRecipes(Consumer<FinishedRecipe> provider) {
        shaped(provider, "ex_interface", EPPItemAndBlock.EX_INTERFACE,
                "ABA", "CDC", "EFE",
                'A', ChemicalHelper.get(plate, Titanium), 'B', GTItems.ELECTRIC_PISTON_EV,
                'C', AEBlocks.INTERFACE.asItem(),
                'D', AEItems.CAPACITY_CARD.asItem(), 'E', GTItems.ELECTRIC_MOTOR_EV,
                'F', AEBlocks.CRAFTING_ACCELERATOR.asItem());
        shaped(provider, "ex_pattern_provider", EPPItemAndBlock.EX_PATTERN_PROVIDER,
                "ABC", "BDB", "EBE",
                'A', GTItems.ELECTRIC_PISTON_EV, 'B', AEBlocks.PATTERN_PROVIDER.asItem(),
                'C', GTItems.CONVEYOR_MODULE_EV, 'D', AEBlocks.CRAFTING_ACCELERATOR.asItem(),
                'E', ChemicalHelper.get(plate, Titanium));
        shaped(provider, "ex_export_bus_part", EPPItemAndBlock.EX_EXPORT_BUS,
                "ABA", "CDC", "EBE",
                'A', AEItems.SPEED_CARD.asItem(), 'B', AEBlocks.CRAFTING_ACCELERATOR.asItem(),
                'C', AEItems.CAPACITY_CARD.asItem(), 'D', AEParts.EXPORT_BUS.asItem(),
                'E', GTItems.ELECTRIC_MOTOR_EV);
        shaped(provider, "ex_import_bus_part", EPPItemAndBlock.EX_IMPORT_BUS,
                "ABA", "CDC", "EBE",
                'A', AEItems.SPEED_CARD.asItem(), 'B', AEBlocks.CRAFTING_ACCELERATOR.asItem(),
                'C', AEItems.CAPACITY_CARD.asItem(), 'D', AEParts.IMPORT_BUS.asItem(),
                'E', GTItems.ELECTRIC_MOTOR_EV);
        shaped(provider, "ex_pattern_access_part", EPPItemAndBlock.EX_PATTERN_TERMINAL,
                "ABA", "CDC", "EFE",
                'A', AEParts.GLASS_CABLE.item(AEColor.TRANSPARENT), 'B', Items.REDSTONE_LAMP,
                'C', GTItems.SENSOR_EV, 'D', AEParts.PATTERN_ACCESS_TERMINAL.asItem(),
                'E', CustomTags.EV_CIRCUITS, 'F', GTItems.EMITTER_EV);
        shaped(provider, "ex_drive", EPPItemAndBlock.EX_DRIVE,
                "AAA", "BCB", "ADA",
                'A', ChemicalHelper.get(plate, Titanium), 'B', AEBlocks.DRIVE.asItem(),
                'C', GTItems.ELECTRIC_PISTON_EV, 'D', AEItems.CAPACITY_CARD.asItem());
        shaped(provider, "wireless_receiver", AEItems.WIRELESS_RECEIVER.asItem(),
                " A ", "BCB", " B ",
                'A', AEItems.FLUIX_PEARL.asItem(), 'B', ChemicalHelper.get(plate, Iron),
                'C', AEParts.QUARTZ_FIBER.asItem());
        shaped(provider, "storage_drive", AEBlocks.DRIVE.asItem(),
                "ABA", "C C", "ABA",
                'A', ChemicalHelper.get(plate, StainlessSteel), 'B', AEItems.ENGINEERING_PROCESSOR.asItem(),
                'C', AEParts.GLASS_CABLE.item(AEColor.TRANSPARENT));
        shaped(provider, "import_bus", AEParts.IMPORT_BUS.asItem(),
                " A ", "BCB",
                'A', AEItems.ANNIHILATION_CORE.asItem(), 'B', ChemicalHelper.get(plate, Steel),
                'C', Items.STICKY_PISTON);
        shaped(provider, "export_bus", AEParts.EXPORT_BUS.asItem(),
                "ABA", " C ",
                'A', ChemicalHelper.get(plate, Steel), 'B', AEItems.FORMATION_CORE.asItem(),
                'C', Items.PISTON);
        shaped(provider, "merequester", ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("merequester:requester")),
                "ABA", "CDC", "AEA",
                'A', ChemicalHelper.get(plate, StainlessSteel), 'B', AEBlocks.CRAFTING_ACCELERATOR.asItem(),
                'C', AEBlocks.INTERFACE.asItem(),
                'D', GTItems.SENSOR_HV, 'E', ChemicalHelper.get(gem, Amethyst));
        VanillaRecipeHelper.addShapedRecipe(provider, true, CTNHCore.id("storage_bus_from_parts"),
                new ItemStack(AEParts.STORAGE_BUS.asItem(), 1), "ABC",
                'A', AEParts.IMPORT_BUS.asItem(), 'B', AEBlocks.INTERFACE.asItem(), 'C', AEParts.EXPORT_BUS.asItem());
        shaped(provider, "pattern_provider", AEBlocks.PATTERN_PROVIDER.asItem(),
                "ABA", "CDC", "AEA",
                'A', ChemicalHelper.get(plate, StainlessSteel), 'B', AEItems.BLANK_PATTERN.asItem(),
                'C', AEItems.ENGINEERING_PROCESSOR.asItem(), 'D', GTItems.ELECTRIC_MOTOR_HV,
                'E', GTItems.CONVEYOR_MODULE_HV);
        shaped(provider, "ae2_interface", AEBlocks.INTERFACE.asItem(),
                "ABC", "BDB", "EBE",
                'A', AEParts.IMPORT_BUS.asItem(), 'B', ChemicalHelper.get(plate, CertusQuartz),
                'C', AEParts.EXPORT_BUS.asItem(), 'D', AEItems.LOGIC_PROCESSOR.asItem(),
                'E', ChemicalHelper.get(plate, Steel));
        shaped(provider, "pattern_encoding_terminal", AEParts.PATTERN_ENCODING_TERMINAL.asItem(),
                "ABC",
                'A', AEItems.ENGINEERING_PROCESSOR.asItem(), 'B', AEParts.CRAFTING_TERMINAL.asItem(),
                'C', AEItems.BLANK_PATTERN.asItem());
        shaped(provider, "ingredient_buffer", EPPItemAndBlock.INGREDIENT_BUFFER,
                "ABA", "CDC", "EBF",
                'A', ChemicalHelper.get(plate, StainlessSteel), 'B', AEItems.CELL_COMPONENT_4K.asItem(),
                'C', AEBlocks.QUARTZ_VIBRANT_GLASS.asItem(), 'D', AEItems.ENGINEERING_PROCESSOR.asItem(),
                'E', GTItems.ELECTRIC_PUMP_HV, 'F', GTItems.CONVEYOR_MODULE_HV);
        shaped(provider, "molecular_assembler", AEBlocks.MOLECULAR_ASSEMBLER.asItem(),
                "ABA", "DCE", "ABA",
                'A', ChemicalHelper.get(plate, StainlessSteel), 'B', GTBlocks.CASING_TEMPERED_GLASS.asItem(),
                'C', MachineBlocks.CRAFTER.asItem(),
                'D', AEItems.FORMATION_CORE.asItem(), 'E', AEItems.ANNIHILATION_CORE.asItem());
        addEUP2PTunnelRecipe(provider);
    }

    private static void addProcessorRecipes(Consumer<FinishedRecipe> provider) {
        WIREMILL_RECIPES.recipeBuilder(CTNHCore.id("quartz_fiber"))
                .EUt(120).duration(60)
                .inputItems(plate, NetherQuartz)
                .outputItems(AEParts.QUARTZ_FIBER.asItem(), 2)
                .save(provider);

        for (var mats : new Object[][]{
                {Rubber, 144}, {SiliconeRubber, 72}, {StyreneButadieneRubber, 36}
        }) {
            ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("fluix_covered_cable_" + ((Material) mats[0]).getName()))
                    .EUt(120).duration(40)
                    .inputItems(AEParts.GLASS_CABLE.item(AEColor.TRANSPARENT))
                    .inputFluids(((Material) mats[0]).getFluid((int) mats[1]))
                    .outputItems(AEParts.COVERED_CABLE.item(AEColor.TRANSPARENT))
                    .save(provider);
        }

        FORMING_PRESS_RECIPES.recipeBuilder(CTNHCore.id("silicon_print"))
                .EUt(120).circuitMeta(23).duration(200)
                .inputItems(plate, Silicon).notConsumable(AEItems.SILICON_PRESS.asItem())
                .outputItems(AEItems.SILICON_PRINT.asItem())
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ex_charger"))
                .EUt(120).duration(400)
                .inputItems(AEBlocks.CHARGER.asItem(), 4)
                .inputItems(GTItems.SENSOR_EV).inputItems(GTItems.EMITTER_EV)
                .inputItems(AEBlocks.INTERFACE.asItem())
                .inputItems(AEParts.STORAGE_BUS.asItem(), 2)
                .outputItems(EPPItemAndBlock.EX_CHARGER.asItem())
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ex_interface_update"))
                .EUt(480).duration(100)
                .inputItems(AEBlocks.INTERFACE.asItem()).inputItems(AEBlocks.CRAFTING_ACCELERATOR.asItem())
                .inputItems(AEItems.CAPACITY_CARD.asItem())
                .inputItems(plate, Titanium, 2).inputItems(GTItems.ELECTRIC_MOTOR_EV, 2)
                .inputItems(GTItems.ELECTRIC_PISTON_EV)
                .outputItems(EPPItemAndBlock.INTERFACE_UPGRADE)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ex_pattern_provider_update"))
                .EUt(480).duration(100)
                .inputItems(AEBlocks.PATTERN_PROVIDER.asItem(), 3).inputItems(AEBlocks.CRAFTING_ACCELERATOR.asItem())
                .inputItems(plate, Titanium, 2).inputItems(GTItems.CONVEYOR_MODULE_EV)
                .inputItems(GTItems.ELECTRIC_PISTON_EV)
                .outputItems(EPPItemAndBlock.PATTERN_PROVIDER_UPGRADE)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ex_io_bus_update"))
                .EUt(480).duration(100)
                .inputItems(AEItems.SPEED_CARD.asItem(), 2).inputItems(AEItems.CAPACITY_CARD.asItem(), 2)
                .inputItems(GTItems.ELECTRIC_MOTOR_EV, 2).inputItems(AEBlocks.CRAFTING_ACCELERATOR.asItem(), 2)
                .outputItems(EPPItemAndBlock.IO_BUS_UPGRADE)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("pattern_terminal_update"))
                .EUt(480).duration(100)
                .inputItems(AEParts.GLASS_CABLE.item(AEColor.TRANSPARENT), 2).inputItems(GTItems.SENSOR_EV, 2)
                .inputItems(Items.REDSTONE_LAMP).inputItems(GTItems.QUANTUM_PROCESSOR_EV, 2)
                .inputItems(GTItems.EMITTER_EV)
                .outputItems(EPPItemAndBlock.PATTERN_UPGRADE)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("drive_update"))
                .EUt(480).duration(100)
                .inputItems(AEBlocks.DRIVE.asItem()).inputItems(AEItems.CAPACITY_CARD.asItem())
                .inputItems(plate, Titanium, 5).inputItems(GTItems.ELECTRIC_PISTON_EV)
                .outputItems(EPPItemAndBlock.DRIVE_UPGRADE)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("blank_pattern"))
                .EUt(480).duration(100)
                .inputItems(CustomTags.LV_CIRCUITS)
                .inputItems(plate, StainlessSteel, 4).inputItems(gearSmall, StainlessSteel, 2)
                .inputItems(cableGtSingle, Gold, 4).inputItems(screw, Aluminium, 2)
                .outputItems(new ItemStack(AEItems.BLANK_PATTERN.asItem(), 8))
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("tag_storage_bus"))
                .EUt(1920).duration(100)
                .inputItems(AEParts.STORAGE_BUS.asItem()).inputItems(GTItems.ROBOT_ARM_EV)
                .inputItems(GTItems.SENSOR_EV).inputItems(bolt, Titanium, 2)
                .inputItems(Items.BOOK).inputFluids(Tin.getFluid(144))
                .outputItems(EPPItemAndBlock.TAG_STORAGE_BUS)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("tag_export_bus"))
                .EUt(1920).duration(100)
                .inputItems(AEParts.EXPORT_BUS.asItem()).inputItems(GTItems.ROBOT_ARM_EV)
                .inputItems(GTItems.SENSOR_EV).inputItems(bolt, Titanium, 2)
                .inputItems(Items.BOOK).inputFluids(Tin.getFluid(144))
                .outputItems(EPPItemAndBlock.TAG_EXPORT_BUS)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ex_molecular_assembler"))
                .EUt(480).duration(100)
                .inputItems(AEBlocks.MOLECULAR_ASSEMBLER.asItem(), 4)
                .inputItems(AEBlocks.CRAFTING_ACCELERATOR.asItem(), 2)
                .inputItems(AEParts.SMART_CABLE.item(AEColor.TRANSPARENT), 2)
                .inputItems(AEItems.ENGINEERING_PROCESSOR.asItem(), 2).inputItems(GTItems.SENSOR_EV)
                .inputFluids(Tin.getFluid(144))
                .outputItems(EPPItemAndBlock.EX_ASSEMBLER.asItem())
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("basic_card"))
                .EUt(120).duration(100)
                .inputItems(plate, Iron, 2).inputItems(plate, Gold)
                .inputItems(foil, RedAlloy, 2).inputItems(AEItems.CALCULATION_PROCESSOR.asItem())
                .outputItems(new ItemStack(AEItems.BASIC_CARD.asItem(), 2))
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("advanced_card"))
                .EUt(480).duration(100)
                .inputItems(plate, StainlessSteel, 2).inputItems(plate, Electrum)
                .inputItems(foil, RedAlloy, 2).inputItems(AEItems.ENGINEERING_PROCESSOR.asItem())
                .outputItems(new ItemStack(AEItems.ADVANCED_CARD.asItem(), 2))
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("crafting_unit"))
                .EUt(480).duration(100)
                .inputItems(AEItems.LOGIC_PROCESSOR.asItem(), 2).inputItems(AEItems.CALCULATION_PROCESSOR.asItem())
                .inputItems(plate, Steel, 4).inputItems(AEParts.GLASS_CABLE.item(AEColor.TRANSPARENT), 2)
                .outputItems(AEBlocks.CRAFTING_UNIT.asItem())
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("item_cell_housing"))
                .EUt(480).duration(100)
                .inputItems(plate, Steel, 3).inputItems(screw, Steel, 2)
                .inputItems(foil, RedAlloy, 2).inputItems(AEBlocks.QUARTZ_GLASS.asItem(), 2)
                .outputItems(AEItems.ITEM_CELL_HOUSING.asItem())
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("fluid_cell_housing"))
                .EUt(480).duration(100)
                .inputItems(plate, Bronze, 3).inputItems(screw, BismuthBronze, 2)
                .inputItems(foil, Rubber, 2).inputItems(AEBlocks.QUARTZ_GLASS.asItem(), 2)
                .outputItems(AEItems.FLUID_CELL_HOUSING.asItem())
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("wireless_booster"))
                .EUt(480).duration(100)
                .inputItems(plate, Steel, 2).inputItems(AEItems.CALCULATION_PROCESSOR.asItem())
                .inputItems(AEItems.FLUIX_DUST.asItem())
                .outputItems(AEItems.WIRELESS_BOOSTER.asItem())
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("omni_cell_housing"))
                .EUt(1920).duration(100)
                .inputItems(OCItems.ENDER_INGOT.get(), 4).inputItems(foil, Polycaprolactam, 4)
                .inputItems(screw, Aluminium, 2).inputItems(GTBlocks.CASING_TEMPERED_GLASS.asItem(), 2)
                .inputItems(OCItems.OMNI_LINK_PROCESSOR.get())
                .outputItems(OCItems.OMNI_CELL_HOUSING.get())
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("complex_omni_cell_housing"))
                .EUt(7680).duration(100)
                .inputItems(OCItems.OMNI_CELL_HOUSING.get())
                .inputItems(plate, Netherite, 2).inputItems(screw, Netherite, 2)
                .inputItems(OCItems.COMPLEX_LINK_PROCESSOR.get())
                .outputItems(OCItems.COMPLEX_OMNI_CELL_HOUSING.get())
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("crafting_unit_block"))
                .EUt(1920).duration(100)
                .inputItems(OCItems.ENDER_INGOT.get(), 4).inputItems(OCItems.OMNI_LINK_PROCESSOR.get(), 2)
                .inputItems(AEBlocks.CRAFTING_UNIT.asItem()).inputItems(AEBlocks.CRAFTING_ACCELERATOR.asItem())
                .inputItems(AEParts.SMART_DENSE_CABLE.item(AEColor.TRANSPARENT), 4)
                .inputFluids(SolderingAlloy.getFluid(288))
                .outputItems(OCBlocks.OMNI_CRAFTING_UNIT_BLOCK.get().asItem())
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("complex_crafting_unit_block"))
                .EUt(7680 * 4).duration(100)
                .inputItems(OCBlocks.OMNI_CRAFTING_UNIT_BLOCK.get().asItem())
                .inputItems(plate, Netherite, 2).inputItems(OCItems.COMPLEX_LINK_PROCESSOR.get(), 2)
                .inputItems(OCItems.CHARGED_ENDER_INGOT.get(), 4)
                .inputItems(gearSmall, RhodiumPlatedPalladium, 2).inputItems(screw, Netherite, 2)
                .inputFluids(NewExplosivesProductionMaterials.ANCIENT_DEBRIS_LEACH.getFluid(8000))
                .inputFluids(Lubricant.getFluid(1000))
                .outputItems(OCBlocks.COMPLEX_CRAFTING_UNIT_BLOCK.get().asItem())
                .save(provider);

        // ============== 处理器合成 ==============
        // 1K-256K 处理器：冲压 -> 装配 -> 刻印
        makeProcessor(provider, "logic_processor", plate, Gold,
                AEItems.LOGIC_PROCESSOR_PRESS.asItem(),
                AEItems.LOGIC_PROCESSOR_PRINT.asItem(), AEItems.LOGIC_PROCESSOR.asItem(),
                MarkerMaterials.Color.Yellow);
        makeProcessor(provider, "calculation_processor", plate, CertusQuartz,
                AEItems.CALCULATION_PROCESSOR_PRESS.asItem(),
                AEItems.CALCULATION_PROCESSOR_PRINT.asItem(), AEItems.CALCULATION_PROCESSOR.asItem(),
                MarkerMaterials.Color.Cyan);
        makeProcessor(provider, "engineering_processor", plate, Diamond,
                AEItems.ENGINEERING_PROCESSOR_PRESS.asItem(),
                AEItems.ENGINEERING_PROCESSOR_PRINT.asItem(), AEItems.ENGINEERING_PROCESSOR.asItem(),
                MarkerMaterials.Color.Lime);

        FORMING_PRESS_RECIPES.recipeBuilder(CTNHCore.id("omni_link_circuit_print"))
                .EUt(480).circuitMeta(23).duration(200)
                .inputItems(OCItems.ENDER_INGOT.get()).notConsumable(OCItems.OMNI_LINK_PRINT_PRESS.get())
                .outputItems(OCItems.OMNI_LINK_CIRCUIT_PRINT.get())
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("omni_link_processor"))
                .EUt(480).circuitMeta(23).duration(360)
                .inputItems(OCItems.OMNI_LINK_CIRCUIT_PRINT.get(), 2)
                .inputItems(AEItems.SILICON_PRINT.asItem(), 2)
                .inputItems(CustomTags.IV_CIRCUITS)
                .outputItems(OCItems.OMNI_LINK_PROCESSOR.get(), 2)
                .save(provider);
        // 与 QuantumOmniRecipes 中的 ender_ingot 重叠，保留迁移代码但不注册。
        /*
        CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("ender_ingot"))
                .EUt(480).duration(60)
                .inputItems(ingot, EnderIOMaterials.EndSteel)
                .inputFluids(EnderPearl.getFluid(288))
                .outputItems(OCItems.ENDER_INGOT.get())
                .save(provider);
        */
        FORMING_PRESS_RECIPES.recipeBuilder(CTNHCore.id("complex_link_circuit_print"))
                .EUt(480).circuitMeta(23).duration(200)
                .inputItems(plate, Netherite).notConsumable(OCItems.COMPLEX_LINK_PRINT_PRESS.get())
                .outputItems(OCItems.COMPLEX_LINK_CIRCUIT_PRINT.get())
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("complex_link_processor"))
                .EUt(480).circuitMeta(23).duration(360)
                .inputItems(OCItems.COMPLEX_LINK_CIRCUIT_PRINT.get(), 2)
                .inputItems(AEItems.SILICON_PRINT.asItem(), 2)
                .inputItems(CustomTags.LuV_CIRCUITS)
                .outputItems(OCItems.COMPLEX_LINK_PROCESSOR.get(), 2)
                .save(provider);
        // 与 QuantumOmniRecipes 中的链接压印板配方重叠，保留迁移代码但不注册。
        /*
        LASER_ENGRAVER_RECIPES.recipeBuilder(CTNHCore.id("omni_link_print_press"))
                .EUt(120).duration(1600)
                .inputItems(plate, Stellite100)
                .notConsumable(lens, ArcaneCrystal, 1)
                .outputItems(OCItems.OMNI_LINK_PRINT_PRESS.get())
                .save(provider);
        LASER_ENGRAVER_RECIPES.recipeBuilder(CTNHCore.id("complex_link_print_press"))
                .EUt(120).duration(1600)
                .inputItems(plate, Stellite100)
                .notConsumable(lens, ToxicSwampAmber, 1)
                .outputItems(OCItems.COMPLEX_LINK_PRINT_PRESS.get())
                .save(provider);
        */
    }

    private static void makeProcessor(Consumer<FinishedRecipe> provider, String name,
                                      TagPrefix platePrefix, Material plateMat,
                                      Item press, Item printed, Item processor,
                                      MarkerMaterial lensMat) {
        FORMING_PRESS_RECIPES.recipeBuilder(CTNHCore.id(name + "_print"))
                .EUt(30).circuitMeta(23).duration(200)
                .inputItems(platePrefix, plateMat).notConsumable(press)
                .outputItems(printed)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id(name))
                .EUt(30).circuitMeta(23).duration(360)
                .inputItems(printed, 2)
                .inputItems(AEItems.SILICON_PRINT.asItem(), 2)
                .inputItems(CustomTags.MV_CIRCUITS)
                .outputItems(processor, 2)
                .save(provider);
        // 与 AeCrystalScienceRecipes 中的处理器压印板配方重叠，保留迁移代码但不注册。
        /*
        LASER_ENGRAVER_RECIPES.recipeBuilder(CTNHCore.id(name + "_press"))
                .EUt(30).duration(1600)
                .inputItems(plate, StainlessSteel)
                .notConsumable(lens, lensMat)
                .outputItems(press)
                .save(provider);
        */
    }

    private static void addMiscRecipes(Consumer<FinishedRecipe> provider) {
        MACERATOR_RECIPES.recipeBuilder(CTNHCore.id("fluix_dust"))
                .EUt(2).duration(40)
                .inputItems(AEItems.FLUIX_CRYSTAL.asItem()).outputItems(AEItems.FLUIX_DUST.asItem())
                .save(provider);
        MACERATOR_RECIPES.recipeBuilder(CTNHCore.id("sky_dust"))
                .EUt(2).duration(80)
                .inputItems(AEBlocks.SKY_STONE_BLOCK.asItem()).outputItems(AEItems.SKY_DUST.asItem())
                .save(provider);

        ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("quartz_glass"))
                .EUt(16).duration(100)
                .inputItems(ChemicalHelper.get(dust, SpecialMaterials.QUARTZ_GLASS))
                .notConsumable(GTItems.SHAPE_MOLD_BLOCK)
                .outputItems(AEBlocks.QUARTZ_GLASS.asItem())
                .save(provider);

        MIXER_RECIPES.recipeBuilder(CTNHCore.id("quartz_glass_dust"))
                .EUt(30).duration(80)
                .inputItems(dust, Glass).inputItems(dust, CertusQuartz)
                .outputItems(ChemicalHelper.get(dust, SpecialMaterials.QUARTZ_GLASS, 2))
                .save(provider);
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("fluix_crystal"))
                .EUt(120).circuitMeta(3).duration(60)
                .inputItems(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem())
                .inputItems(Items.REDSTONE).inputItems(Items.QUARTZ)
                .inputFluids(Water.getFluid(250))
                .outputItems(AEItems.FLUIX_CRYSTAL.asItem(), 2)
                .save(provider);

        TransformRecipeBuilder.transform(provider, CTNHCore.id("transform/certus_quartz_crystals"),
                AEItems.CERTUS_QUARTZ_CRYSTAL.asItem(), 2,
                TransformCircumstance.fluid(FluidTags.WATER),
                Ingredient.of(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem()),
                Ingredient.of(ChemicalHelper.get(dust, CertusQuartz)));

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("cell_component_4k_changed"))
                .EUt(120).duration(100)
                .inputItems(CustomTags.LV_CIRCUITS).inputItems(CustomTags.ULV_CIRCUITS, 4)
                .inputItems(AEBlocks.QUARTZ_GLASS.asItem())
                .inputItems(wireGtQuadruple, MercuryBariumCalciumCuprate, 2)
                .inputItems(plate, StainlessSteel, 2)
                .outputItems(AEItems.CELL_COMPONENT_4K.asItem())
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("cell_component_16k_changed"))
                .EUt(480).duration(60)
                .inputItems(CustomTags.MV_CIRCUITS).inputItems(CustomTags.LV_CIRCUITS, 4)
                .inputItems(AEBlocks.QUARTZ_GLASS.asItem())
                .inputItems(wireGtQuadruple, UraniumTriplatinum, 2)
                .inputItems(plate, Titanium, 2)
                .outputItems(AEItems.CELL_COMPONENT_16K.asItem())
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("cell_component_64k_changed"))
                .EUt(480).duration(60)
                .inputItems(CustomTags.HV_CIRCUITS, 4).inputItems(CustomTags.MV_CIRCUITS, 16)
                .inputItems(AEBlocks.QUARTZ_GLASS.asItem())
                .inputItems(wireGtQuadruple, UraniumTriplatinum, 2)
                .inputItems(plate, Titanium, 2)
                .outputItems(AEItems.CELL_COMPONENT_64K.asItem())
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("cell_component_256k_changed"))
                .EUt(120 * 4 * 4 * 4).duration(60)
                .inputItems(CustomTags.EV_CIRCUITS, 4).inputItems(CustomTags.HV_CIRCUITS, 16)
                .inputItems(AEBlocks.QUARTZ_GLASS.asItem())
                .inputItems(wireGtQuadruple, IndiumTinBariumTitaniumCuprate, 4)
                .inputItems(plate, RhodiumPlatedPalladium, 4)
                .outputItems(AEItems.CELL_COMPONENT_256K.asItem())
                .save(provider);
    }

    private static void addEUP2PTunnelRecipe(Consumer<FinishedRecipe> provider) {
        provider.accept(new FinishedRecipe() {

            @Override
            public void serializeRecipeData(JsonObject json) {
                json.addProperty("type", CTNHCore.id("keep_ingredient_shaped").toString());

                JsonArray pattern = new JsonArray();
                pattern.add("AB");
                json.add("pattern", pattern);

                JsonObject key = new JsonObject();
                key.add("A", Ingredient.of(AEParts.ME_P2P_TUNNEL.asItem()).toJson());
                key.add("B", Ingredient.of(CEItems.EU_P2P.asItem()).toJson());
                json.add("key", key);

                JsonObject result = new JsonObject();
                result.addProperty("item", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(CEItems.EU_P2P.asItem())).toString());
                json.add("result", result);
                json.add("keepIngredient", Ingredient.of(CEItems.EU_P2P.asItem()).toJson());
            }

            @Override
            public ResourceLocation getId() {
                return CTNHCore.id("eu_p2p_tunnel");
            }

            @Override
            public RecipeSerializer<?> getType() {
                return CTNHRecipes.KEEP_INGREDIENT_SHAPED_SERIALIZER.get();
            }

            @Override
            public JsonObject serializeAdvancement() {
                return null;
            }

            @Override
            public ResourceLocation getAdvancementId() {
                return null;
            }
        });
    }

    private static void shaped(Consumer<FinishedRecipe> provider, String id, ItemLike result,
                               Object... recipe) {
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id(id),
                new ItemStack(result, 1), recipe);
    }
}
