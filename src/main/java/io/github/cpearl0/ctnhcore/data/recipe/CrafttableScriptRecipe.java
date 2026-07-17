package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.BedrockMaterials;
import io.github.cpearl0.ctnhcore.data.materials.EnderIOMaterials;
import io.github.cpearl0.ctnhcore.data.recipe.utils.KeepIngredientRecipeHelper;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.machines.CTNHMachines;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.Mechanical;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksB;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksC;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.machines.GTMultiMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import com.lowdragmc.lowdraglib.utils.NBTToJsonConverter;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.nbt.TagParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import appeng.api.util.AEColor;
import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import appeng.core.definitions.AEParts;
import com.enderio.base.common.init.EIOBlocks;
import com.enderio.base.common.init.EIOItems;
import com.enderio.conduits.common.init.ConduitItems;
import com.github.alexmodguy.alexscaves.server.block.ACBlockRegistry;
import com.github.alexmodguy.alexscaves.server.item.ACItemRegistry;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import com.laidbacksloth.angelblockrenewed.BlockRegistry;
import com.mo_guang.ctpp.registry.CTPPBlocks;
import com.mo_guang.ctpp.registry.CTPPItems;
import com.mo_guang.ctpp.registry.CTPPMaterials;
import com.moguang.ctnhmana.registry.CMBlocks;
import com.moguang.ctnhmana.registry.CMMaterials;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.negodya1.vintageimprovements.VintageBlocks;
import com.negodya1.vintageimprovements.VintageItems;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import earth.terrarium.adastra.common.registry.ModItems;
import fr.lucreeper74.createmetallurgy.registries.CMItems;
import me.khajiitos.jackseconomy.init.ItemBlockReg;
import org.antarcticgardens.cna.CNABlocks;
import org.antarcticgardens.cna.CNAItems;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.lib.BotaniaTags;
import wayoftime.bloodmagic.common.block.BloodMagicBlocks;
import wayoftime.bloodmagic.common.item.BloodMagicItems;

import java.util.Objects;
import java.util.function.Consumer;

public class CrafttableScriptRecipe {

    public static void init(Consumer<FinishedRecipe> provider) {
        addCrafttableRecipes(provider);
    }

    private static void addCrafttableRecipes(Consumer<FinishedRecipe> provider) {
        shaped(provider, "andesite_alloy_ingot",
                stack(ChemicalHelper.get(TagPrefix.ingot, CTPPMaterials.AndesiteAlloy), 4), "ABA", "BAB", "ABA",
                'A', Items.IRON_INGOT, 'B', Items.ANDESITE);
        shaped(provider, "shaft", stack(AllBlocks.SHAFT.asStack(), 4), "A", "A", "A", 'A',
                ChemicalHelper.get(TagPrefix.ingot, CTPPMaterials.AndesiteAlloy));
        shaped(provider, "mechanical_press", AllBlocks.MECHANICAL_PRESS.asStack(), " A ", "BCB", " D ", 'A',
                Items.IRON_INGOT, 'B', AllBlocks.SHAFT.asStack(), 'C', AllBlocks.ANDESITE_CASING.asStack(), 'D',
                Items.IRON_BLOCK);
        shaped(provider, "recipe_card", stack(VintageItems.RECIPE_CARD.asItem(), 4), " A ", "BBB", "CCC", 'A',
                CMItems.SANDPAPER_BELT.asItem(), 'B', Items.REDSTONE, 'C',
                ChemicalHelper.get(TagPrefix.plate, GTMaterials.Brass));
        shaped(provider, "windmill_bearing", AllBlocks.WINDMILL_BEARING.asStack(), "AAA", "BCB", "BDB", 'A',
                ItemTags.WOODEN_SLABS, 'B', Items.STONE, 'C', CTPPItems.BASIC_MECHANISM.asStack(), 'D',
                AllBlocks.SHAFT.asStack());
        shaped(provider, "mechanical_bearing", AllBlocks.MECHANICAL_BEARING.asStack(), "AAA", "BCB", "BDB", 'A',
                ItemTags.WOODEN_SLABS, 'B', ItemTags.PLANKS, 'C',
                ChemicalHelper.get(TagPrefix.plate, CTPPMaterials.AndesiteAlloy), 'D', AllBlocks.SHAFT.asStack());
        shapeless(provider, "depot", AllBlocks.DEPOT.asStack(), AllBlocks.ANDESITE_CASING.asStack(),
                ChemicalHelper.get(TagPrefix.plate, CTPPMaterials.AndesiteAlloy));
        shaped(provider, "cogwheel", stack(AllBlocks.COGWHEEL.asStack(), 4), " A ", "ABA", " A ", 'A',
                ItemTags.PLANKS, 'B', ChemicalHelper.get(TagPrefix.gearSmall, CTPPMaterials.AndesiteAlloy));
        shaped(provider, "large_cogwheel", stack(AllBlocks.LARGE_COGWHEEL.asStack(), 4), " A ", "ABA", " A ", 'A',
                GTBlocks.TREATED_WOOD_PLANK.asStack(), 'B',
                ChemicalHelper.get(TagPrefix.gear, CTPPMaterials.AndesiteAlloy));
        shaped(provider, "water_wheel", AllBlocks.WATER_WHEEL.asStack(), "AAA", "ABA", "AAA", 'A',
                GTBlocks.TREATED_WOOD_PLANK.asStack(), 'B', AllBlocks.LARGE_COGWHEEL.asStack());
        shaped(provider, "mechanical_drill", AllBlocks.MECHANICAL_DRILL.asStack(), " D ", "CBC", " A ", 'A',
                AllBlocks.ANDESITE_CASING.asStack(), 'B', AllBlocks.SHAFT.asStack(), 'C',
                ChemicalHelper.get(TagPrefix.ingot, CTPPMaterials.AndesiteAlloy), 'D',
                ChemicalHelper.get(TagPrefix.toolHeadDrill, GTMaterials.Iron));
        shaped(provider, "mechanical_mixer_create", AllBlocks.MECHANICAL_MIXER.asStack(), "ABA", "CDC", " E ", 'A',
                CTPPItems.BASIC_MECHANISM.asStack(), 'B', AllBlocks.SHAFT.asStack(), 'C',
                AllBlocks.ANDESITE_CASING.asStack(), 'D', AllBlocks.LARGE_COGWHEEL.asStack(), 'E',
                AllItems.WHISK.asStack());
        shaped(provider, "electron_tube", AllItems.ELECTRON_TUBE.asStack(), " A ", "BCB", " B ", 'A',
                AllItems.POLISHED_ROSE_QUARTZ.asItem(), 'B',
                ChemicalHelper.get(TagPrefix.plate, CTPPMaterials.AndesiteAlloy), 'C',
                ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Iron));
        shaped(provider, "deployer", AllBlocks.DEPLOYER.asStack(), "ABA", "CDC", " E ", 'A',
                CTPPItems.BASIC_MECHANISM.asStack(), 'B', AllItems.ELECTRON_TUBE.asStack(), 'C',
                AllBlocks.ANDESITE_CASING.asStack(), 'D', AllBlocks.SHAFT.asStack(), 'E',
                AllItems.BRASS_HAND.asStack());
        shaped(provider, "contraption_controls", AllBlocks.CONTRAPTION_CONTROLS.asStack(), " A ", "BCB", " D ", 'A',
                Items.OAK_BUTTON, 'B', CTPPItems.BASIC_MECHANISM.asStack(), 'C', AllBlocks.ANDESITE_CASING.asStack(),
                'D', AllItems.ELECTRON_TUBE.asStack());
        shaped(provider, "redstone_magnet", stack(CNABlocks.REDSTONE_MAGNET.asStack(), 2), "ABC", "BDB", "CBA", 'A',
                ACItemRegistry.SCARLET_NEODYMIUM_INGOT.get(), 'B', AllItems.POLISHED_ROSE_QUARTZ.asItem(), 'C',
                ACItemRegistry.AZURE_NEODYMIUM_INGOT.get(), 'D', CNABlocks.MAGNETITE_BLOCK.asStack());
        shaped(provider, "basic_energiser", CNABlocks.BASIC_ENERGISER.asStack(), " A ", "BCB", " D ", 'A',
                AllItems.PRECISION_MECHANISM.asStack(), 'B', AllBlocks.SHAFT.asStack(), 'C',
                AllBlocks.RAILWAY_CASING.asStack(), 'D', Items.LIGHTNING_ROD);
        shaped(provider, "lightning_rod_crafttable", stack(Items.LIGHTNING_ROD), " A ", " B ", " C ", 'A',
                Items.COPPER_BLOCK, 'B', Items.COPPER_INGOT, 'C',
                ChemicalHelper.get(TagPrefix.rod, GTMaterials.Copper));
        shaped(provider, "void_chassis", stack(EIOBlocks.VOID_CHASSIS.asItem()), "ABA", "BCB", "ABA", 'A',
                Items.IRON_BARS, 'B',
                ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel), 'C', CustomTags.MV_CIRCUITS);
        shaped(provider, "spout", AllBlocks.SPOUT.asStack(), "ABA", "ACA", " D ", 'A',
                ChemicalHelper.get(TagPrefix.plate, GTMaterials.Bronze), 'B', Items.GLASS, 'C',
                AllBlocks.COPPER_CASING.asStack(), 'D', AllBlocks.FLUID_PIPE.asItem());
        shaped(provider, "apothecary_default", stack(BotaniaBlocks.defaultAltar), "ABA", "CCC", " D ", 'A',
                Items.STONE_BRICK_STAIRS, 'B', BotaniaTags.Items.PETALS, 'C', Items.STONE_BRICK_SLAB, 'D',
                Items.STONE_BRICK_WALL);
        keepShaped(provider, "magnetic_iron_ingot_alexscaves",
                stack(ChemicalHelper.get(TagPrefix.ingot, GTMaterials.IronMagnetic), 7),
                new String[] { "ABA", "AAA", "ACA" },
                new Object[] { ACBlockRegistry.SCARLET_MAGNET.get(), ACBlockRegistry.AZURE_MAGNET.get() }, 'A',
                Items.IRON_INGOT, 'B', ACBlockRegistry.SCARLET_MAGNET.get(), 'C', ACBlockRegistry.AZURE_MAGNET.get());
        keepShaped(provider, "magnetic_iron_rod_alexscaves",
                stack(ChemicalHelper.get(TagPrefix.rod, GTMaterials.IronMagnetic), 7),
                new String[] { "ABA", "AAA", "ACA" },
                new Object[] { ACBlockRegistry.SCARLET_MAGNET.get(), ACBlockRegistry.AZURE_MAGNET.get() }, 'A',
                ChemicalHelper.get(TagPrefix.rod, GTMaterials.Iron), 'B', ACBlockRegistry.SCARLET_MAGNET.get(), 'C',
                ACBlockRegistry.AZURE_MAGNET.get());
        keepShaped(provider, "magnetic_iron_bolt_alexscaves",
                stack(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.IronMagnetic), 7),
                new String[] { "ABA", "AAA", "ACA" },
                new Object[] { ACBlockRegistry.SCARLET_MAGNET.get(), ACBlockRegistry.AZURE_MAGNET.get() }, 'A',
                ChemicalHelper.get(TagPrefix.bolt, GTMaterials.Iron), 'B', ACBlockRegistry.SCARLET_MAGNET.get(), 'C',
                ACBlockRegistry.AZURE_MAGNET.get());
        shaped(provider, "magnetite_block", stack(CNABlocks.MAGNETITE_BLOCK.asStack(), 5), "ABA", "BAB", "ABA", 'A',
                Items.STONE, 'B', ChemicalHelper.get(TagPrefix.ingot, GTMaterials.IronMagnetic));
        shaped(provider, "rotation_speed_controller", AllBlocks.ROTATION_SPEED_CONTROLLER.asStack(), " A ", "BCB",
                "DDD", 'A', AllBlocks.COGWHEEL.asStack(), 'B', AllBlocks.SHAFT.asStack(), 'C',
                AllItems.PRECISION_MECHANISM.asStack(), 'D', AllBlocks.BRASS_CASING.asStack());
        shaped(provider, "portable_storage_interface", AllBlocks.PORTABLE_STORAGE_INTERFACE.asStack(), "   ", "ABC",
                "   ",
                'A', AllBlocks.ANDESITE_CASING.asStack(), 'B', CTPPItems.BASIC_MECHANISM.asStack(), 'C',
                AllBlocks.CHUTE.asStack());
        shaped(provider, "cart_assembler", AllBlocks.CART_ASSEMBLER.asStack(), " A ", "BCB", "DED", 'A',
                Items.SLIME_BALL,
                'B', AllBlocks.POWERED_LATCH.asStack(), 'C', AllBlocks.ANDESITE_CASING.asStack(), 'D',
                ChemicalHelper.get(TagPrefix.plate, CTPPMaterials.AndesiteAlloy), 'E',
                CTPPItems.BASIC_MECHANISM.asStack());
        shaped(provider, "millstone", AllBlocks.MILLSTONE.asStack(), " A ", "BCB", "DDD", 'A',
                AllBlocks.CHUTE.asStack(), 'B',
                AllBlocks.COGWHEEL.asStack(), 'C', CTPPItems.BASIC_MECHANISM.asStack(), 'D', Items.SMOOTH_STONE);
        shaped(provider, "belt_connector_leather", stack(AllItems.BELT_CONNECTOR.asItem(), 3), "   ", "AAA", "BBB", 'A',
                Items.LEATHER, 'B', Items.DRIED_KELP);
        shaped(provider, "belt_connector_polyethylene", stack(AllItems.BELT_CONNECTOR.asItem(), 6), "   ", "AAA", "BBB",
                'A',
                ChemicalHelper.get(TagPrefix.plate, GTMaterials.Polyethylene), 'B', Items.DRIED_KELP);
        shaped(provider, "belt_connector_polyvinyl_chloride", stack(AllItems.BELT_CONNECTOR.asItem(), 8), "   ", "AAA",
                "BBB",
                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.PolyvinylChloride), 'B', Items.DRIED_KELP);
        shaped(provider, "belt_connector_polybenzimidazole", stack(AllItems.BELT_CONNECTOR.asItem(), 16), "   ", "AAA",
                "BBB",
                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Polybenzimidazole), 'B', Items.DRIED_KELP);
        shaped(provider, "charger", stack(AEBlocks.CHARGER.asItem()), " AA", "BCD", " AA", 'A',
                ChemicalHelper.get(TagPrefix.plate, GTMaterials.CertusQuartz), 'B', Items.REDSTONE, 'C', Items.HOPPER,
                'D', ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Aluminium));
        shaped(provider, "energy_acceptor", stack(AEBlocks.ENERGY_ACCEPTOR.asItem()), "ABA", "BCB", "ABA", 'A',
                ChemicalHelper.get(TagPrefix.plate, GTMaterials.CertusQuartz), 'B',
                ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.RedAlloy), 'C',
                ChemicalHelper.get(TagPrefix.wireGtQuadruple, EnderIOMaterials.EnergeticAlloy));
        shaped(provider, "fluix_glass_cable", stack(AEParts.GLASS_CABLE.item(AEColor.TRANSPARENT), 4), "AAA", "BCB",
                "AAA", 'A', Items.GLASS, 'B', AEItems.FLUIX_DUST.asItem(), 'C', AEParts.QUARTZ_FIBER.asItem());
        shaped(provider, "angel_block", stack(BlockRegistry.ANGEL_BLOCK_ITEM.get().asItem()), "ABA", "BCB", "ABA", 'A',
                ChemicalHelper.get(TagPrefix.rod, GTMaterials.Tin), 'B', BotaniaBlocks.elfGlass.asItem(), 'C',
                BloodMagicItems.REAGENT_AIR.get());
        shaped(provider, "goggles", AllItems.GOGGLES.asStack(), "ABA", "CDC", "   ", 'A', Items.LEAD, 'B',
                Items.SLIME_BALL, 'C', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Gold), 'D',
                BotaniaItems.engineerGoggles);
        shaped(provider, "lens_normal", stack(BotaniaItems.lensNormal), " A ", "ABA", " A ", 'A',
                ChemicalHelper.get(TagPrefix.plate, CMMaterials.ManaSteel), 'B', Items.GLASS_PANE);
        shaped(provider, "lens_magnet", stack(BotaniaItems.lensMagnet), "   ", "ABC", "   ", 'A',
                Tags.Items.DYES_BLUE, 'B', BotaniaItems.lensNormal, 'C', Tags.Items.DYES_RED);
        keepShaped(provider, "magnetic_iron_ingot_lens",
                stack(ChemicalHelper.get(TagPrefix.ingot, GTMaterials.IronMagnetic), 8),
                new String[] { "AAA", "ABA", "AAA" }, new Object[] { BotaniaItems.lensMagnet }, 'A', Items.IRON_INGOT,
                'B', BotaniaItems.lensMagnet);
        keepShaped(provider, "magnetic_iron_rod_lens",
                stack(ChemicalHelper.get(TagPrefix.rod, GTMaterials.IronMagnetic), 8),
                new String[] { "AAA", "ABA", "AAA" }, new Object[] { BotaniaItems.lensMagnet }, 'A',
                ChemicalHelper.get(TagPrefix.rod, GTMaterials.Iron), 'B', BotaniaItems.lensMagnet);
        keepShaped(provider, "magnetic_iron_bolt_lens",
                stack(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.IronMagnetic), 8),
                new String[] { "AAA", "ABA", "AAA" }, new Object[] { BotaniaItems.lensMagnet }, 'A',
                ChemicalHelper.get(TagPrefix.bolt, GTMaterials.Iron), 'B', BotaniaItems.lensMagnet);
        shaped(provider, "layered_magnet", stack(CNABlocks.LAYERED_MAGNET.asStack(), 2), "AAA", "BCB", "AAA", 'A',
                CNAItems.OVERCHARGED_GOLDEN_SHEET.asStack(), 'B', CNABlocks.REDSTONE_MAGNET.asStack(), 'C',
                ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Steel));
        shaped(provider, "electrical_connector", stack(CNABlocks.ELECTRICAL_CONNECTOR.asStack(), 2), " A ", " B ",
                "CBC",
                'A', ChemicalHelper.get(TagPrefix.wireFine, GTMaterials.Copper), 'B',
                ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.Copper), 'C',
                ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel));
        shaped(provider, "digestion_tank", MultiblocksA.DIGESTION_TANK.asStack(), "ABC", "BDB", "EBE", 'A',
                GTItems.CONVEYOR_MODULE_MV.asStack(), 'B', CustomTags.MV_CIRCUITS, 'C',
                GTItems.ELECTRIC_PUMP_MV.asStack(), 'D', GTMachines.HULL[GTValues.MV].asStack(), 'E', Items.BRICKS);
        shaped(provider, "seawater_desalting_factory", MultiblocksA.SEAWATER_DESALTING_FACTORY.asStack(), "ABC", "DED",
                "FBF", 'A', GTItems.FIELD_GENERATOR_LV.asStack(), 'B', GTBlocks.CASING_STEEL_SOLID.asStack(), 'C',
                GTItems.SENSOR_LV.asStack(), 'D', CustomTags.MV_CIRCUITS, 'E', GTMachines.HULL[GTValues.MV].asStack(),
                'F', GTItems.ELECTRIC_MOTOR_LV.asStack());
        shaped(provider, "industrial_primitive_blast_furnace",
                MultiblocksA.INDUSTRIAL_PRIMITIVE_BLAST_FURNACE.asStack(), "ABA", "BCB", "DBD", 'A',
                GTItems.CONVEYOR_MODULE_LV.asStack(), 'B', CustomTags.LV_CIRCUITS, 'C', Items.BLAST_FURNACE, 'D',
                GTBlocks.CASING_PRIMITIVE_BRICKS.asStack());
        shaped(provider, "fermenting_tank", MultiblocksA.FERMENTING_TANK.asStack(), "ABA", "CDC", "EFE", 'A',
                GTItems.SENSOR_MV.asStack(), 'B', GTItems.ROBOT_ARM_MV.asStack(), 'C', CustomTags.HV_CIRCUITS, 'D',
                GTMachines.MIXER[GTValues.MV].asStack(), 'E', GTBlocks.CASING_STEEL_SOLID.asStack(), 'F',
                GTMachines.FLUID_HEATER[GTValues.MV].asStack());
        shaped(provider, "slaughter_house", MultiblocksA.SLAUGHTER_HOUSE.asStack(), "ABA", "BCB", "DED", 'A',
                ChemicalHelper.get(TagPrefix.gear, GTMaterials.StainlessSteel), 'B', Items.NETHERITE_SWORD, 'C',
                GTMachines.HULL[GTValues.HV].asStack(), 'D', GTItems.CONVEYOR_MODULE_HV.asStack(), 'E',
                CustomTags.HV_CIRCUITS);

        Material[] rotorMaterials = { GTMaterials.Steel, GTMaterials.WroughtIron, GTMaterials.Iron, GTMaterials.Brass,
                GTMaterials.Bronze, GTMaterials.Aluminium };
        for (Material material : rotorMaterials) {
            shaped(provider, "turbine_rotor_" + material.getName(),
                    stack("gtceu:turbine_rotor", 1, "{GT.PartStats:{Material:\"gtceu:" + material.getName() + "\"}}"),
                    "AAA",
                    "ABA", "AAA", 'A', ChemicalHelper.get(TagPrefix.turbineBlade, material), 'B',
                    ChemicalHelper.get(TagPrefix.rodLong, material));
        }

        shaped(provider, "ulv_rotor_holder", CTNHMachines.ROTOR_HOLDER_EXTEND[GTValues.ULV].asStack(), "ABA", "BCB",
                "ABA", 'A',
                ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Brass), 'B',
                ChemicalHelper.get(TagPrefix.gear, GTMaterials.Bronze), 'C', GTMachines.HULL[GTValues.ULV].asStack());
        shaped(provider, "lv_rotor_holder", CTNHMachines.ROTOR_HOLDER_EXTEND[GTValues.LV].asStack(), "ABA", "BCB",
                "ABA", 'A',
                ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Steel), 'B',
                ChemicalHelper.get(TagPrefix.gear, GTMaterials.WroughtIron), 'C',
                GTMachines.HULL[GTValues.LV].asStack());
        shaped(provider, "mv_rotor_holder", CTNHMachines.ROTOR_HOLDER_EXTEND[GTValues.MV].asStack(), "ABA", "BCB",
                "ABA", 'A',
                ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Aluminium), 'B',
                ChemicalHelper.get(TagPrefix.gear, GTMaterials.Magnalium), 'C', GTMachines.HULL[GTValues.MV].asStack());
        shapedExact(provider, ResourceLocation.parse("enderio:item_conduit"), stack(ConduitItems.ITEM.asItem(), 4),
                new String[] { "AAA", "BBB", "AAA" }, 'A', EIOItems.CONDUIT_BINDER.asItem(), 'B',
                ChemicalHelper.get(TagPrefix.ingot, EnderIOMaterials.PulsatingAlloy));
        shaped(provider, "wind_power_array", MultiblocksA.WIND_POWER_ARRAY.asStack(), "ABA", "BCB", "DED", 'A',
                ChemicalHelper.get(TagPrefix.rod, GTMaterials.Steel), 'B', GTBlocks.CASING_STEEL_SOLID.asStack(), 'C',
                AllBlocks.WINDMILL_BEARING.asStack(), 'D', GTItems.ELECTRIC_MOTOR_LV.asStack(), 'E',
                CustomTags.LV_CIRCUITS);
        shaped(provider, "advanced_wind_power_array", MultiblocksA.ADVANCED_WIND_POWER_ARRAY.asStack(), "ABA", "BCB",
                "DED", 'A', ChemicalHelper.get(TagPrefix.rod, GTMaterials.StainlessSteel), 'B',
                GTBlocks.CASING_STAINLESS_CLEAN.asStack(), 'C', MultiblocksA.WIND_POWER_ARRAY.asStack(), 'D',
                GTItems.ELECTRIC_MOTOR_HV.asStack(), 'E', CustomTags.HV_CIRCUITS);
        shaped(provider, "super_wind_power_array", MultiblocksA.SUPER_WIND_POWER_ARRAY.asStack(), "ABA", "BCB", "DED",
                'A', ChemicalHelper.get(TagPrefix.rod, GTMaterials.TungstenSteel), 'B',
                GTBlocks.CASING_TUNGSTENSTEEL_ROBUST.asStack(), 'C', MultiblocksA.ADVANCED_WIND_POWER_ARRAY.asStack(),
                'D', GTItems.ELECTRIC_MOTOR_IV.asStack(), 'E', CustomTags.IV_CIRCUITS);
        shaped(provider, "meadow", MultiblocksA.MEADOW.asStack(), "ABA", "CAC", "DED", 'A',
                CTPPItems.BASIC_MECHANISM.asStack(), 'B', Items.WHEAT_SEEDS, 'C', Items.WHEAT, 'D',
                AllBlocks.SHAFT.asStack(), 'E', AllItems.BELT_CONNECTOR.asItem());
        shaped(provider, "ion_exchanger", MultiblocksA.ION_EXCHANGER.asStack(), "BCB", "DAD", "BCB", 'A',
                GTMachines.HULL[GTValues.IV].asStack(), 'B',
                ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.HSSE), 'C', GTItems.ELECTRIC_PUMP_IV.asStack(),
                'D', GTItems.FLUID_FILTER.asStack());
        shaped(provider, "crystallizer", MultiblocksA.CRYSTALLIZER.asStack(), "SCS", "DAD", "BVB", 'A',
                GTMachines.AUTOCLAVE[GTValues.IV].asStack(), 'B',
                ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.HSSE), 'C', GTItems.ELECTRIC_PUMP_IV.asStack(),
                'D', GTBlocks.COIL_HSSG.asStack(), 'S', ChemicalHelper.get(TagPrefix.spring, GTMaterials.HSSG), 'V',
                ChemicalHelper.get(TagPrefix.frameGt, GTMaterials.HSSE));
        shaped(provider, "vacuum_sintering_tower", MultiblocksA.VACUUM_SINTERING_TOWER.asStack(), "BCB", "DAD", "SVS",
                'A', GTMultiMachines.ELECTRIC_BLAST_FURNACE.asStack(), 'B',
                ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.HSSE), 'C',
                GTMachines.ELECTRIC_FURNACE[GTValues.IV].asStack(),
                'D', GTBlocks.COIL_HSSG.asStack(), 'S', ChemicalHelper.get(TagPrefix.spring, GTMaterials.HSSG), 'V',
                GTMachines.ALLOY_SMELTER[GTValues.IV].asStack());
        shaped(provider, "condensing_discrete", MultiblocksA.CONDENSING_DISCRETE.asStack(), "BCB", "DAG", "SVS", 'A',
                GTMultiMachines.VACUUM_FREEZER.asStack(), 'B',
                ChemicalHelper.get(TagPrefix.plateDense, GTMaterials.Aluminium), 'C',
                GTMachines.CENTRIFUGE[GTValues.IV].asStack(), 'D', GTMachines.THERMAL_CENTRIFUGE[GTValues.IV].asStack(),
                'S',
                ChemicalHelper.get(TagPrefix.spring, GTMaterials.VanadiumGallium), 'V',
                GTMachines.SIFTER[GTValues.IV].asStack(), 'G',
                GTItems.FLUID_FILTER.asStack());
        shaped(provider, "greenhouse", MultiblocksC.GREENHOUSE.asStack(), "DED", "ACA", "BAB", 'A',
                ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Steel), 'B', GTItems.ELECTRIC_PUMP_MV.asStack(),
                'C', GTMachines.HULL[GTValues.MV].asStack(), 'D', CustomTags.MV_CIRCUITS, 'E', Items.GLOWSTONE);
        shaped(provider, "ultrasonic_apparatus", MultiblocksA.ULTRASONIC_APPARATUS.asStack(), "ABA", "BCB", "DED", 'A',
                ChemicalHelper.get(TagPrefix.gear, GTMaterials.StainlessSteel), 'B', CustomTags.HV_CIRCUITS, 'C',
                GTMachines.HULL[GTValues.HV].asStack(), 'D', GTItems.ELECTRIC_PUMP_HV.asStack(), 'E',
                ChemicalHelper.get(TagPrefix.rotor, GTMaterials.StainlessSteel));
        shaped(provider, "super_centrifuge", MultiblocksA.SUPER_CENTRIFUGE.asStack(), "ABC", "CDE", "ABC", 'A',
                GTItems.ELECTRIC_PUMP_EV.asStack(), 'B',
                ChemicalHelper.get(TagPrefix.rotor, GTMaterials.StainlessSteel), 'C', CustomTags.EV_CIRCUITS, 'D',
                GTMachines.HULL[GTValues.EV].asStack(), 'E',
                ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Titanium));
        shaped(provider, "bio_reactor", MultiblocksA.BIO_REACTOR.asStack(), "ABE", "CDE", "ABE", 'A',
                GTItems.ROBOT_ARM_EV.asStack(), 'B', ChemicalHelper.get(TagPrefix.rotor, GTMaterials.Titanium), 'C',
                CustomTags.IV_CIRCUITS, 'D', CTNHBlocks.BIO_REACTOR_CASING.asStack(), 'E',
                ChemicalHelper.get(TagPrefix.ingot, GTMaterials.UraniumTriplatinum));
        shaped(provider, "diamond_sword", stack(Items.DIAMOND_SWORD), "A", "A", "B", 'A', Items.DIAMOND, 'B',
                Items.STICK);
        shaped(provider, "chemical_vapor_deposition_machine", MultiblocksA.CHEMICAL_VAPOR_DEPOSITION_MACHINE.asStack(),
                "ABA", "CDE", "VVV", 'A', CustomTags.EV_CIRCUITS, 'B',
                ChemicalHelper.get(TagPrefix.pipeHugeFluid, GTMaterials.Polytetrafluoroethylene), 'C',
                GTItems.CONVEYOR_MODULE_EV.asStack(), 'D',
                GTMachines.HULL[GTValues.EV].asStack(), 'E', GTItems.ELECTRIC_PUMP_EV.asStack(), 'V',
                ChemicalHelper.get(TagPrefix.plateDense, GTMaterials.StainlessSteel));
        shaped(provider, "novice_spell_book", stack(ItemsRegistry.NOVICE_SPELLBOOK.get().asItem()), "AAA", "ABA", "AEA",
                'A',
                ItemsRegistry.MAGE_FIBER.get(), 'B', Items.BOOK, 'E', BotaniaItems.manaSteel);
        shaped(provider, "coke_tower", MultiblocksA.COKE_TOWER.asStack(), "GAG", "DBD", "GEG", 'A',
                GTMultiMachines.PYROLYSE_OVEN.asStack(), 'B', GTMachines.HULL[GTValues.IV].asStack(), 'D',
                GTItems.ELECTRIC_PUMP_IV.asStack(), 'E',
                ChemicalHelper.get(TagPrefix.plateDense, GTMaterials.TungstenSteel), 'G', CustomTags.LuV_CIRCUITS);
        shaped(provider, "empty_exporter_manifest", stack(ItemBlockReg.EMPTY_EXPORTER_TICKET_ITEM.get()), "ABA", "CCC",
                "CDC",
                'A', Items.RED_CARPET, 'B', tag("forge:chests"), 'C', Items.STRING, 'D', Items.FEATHER);
        shaped(provider, "golden_exporter_manifest", stack(ItemBlockReg.GOLDEN_EXPORTER_TICKET_ITEM.get()), "AAA",
                "ABA",
                "AAA", 'A', ChemicalHelper.get(TagPrefix.foil, GTMaterials.Gold), 'B',
                ItemBlockReg.EMPTY_EXPORTER_TICKET_ITEM.get());
        shaped(provider, "mechanical_importer", stack(ItemBlockReg.MECHANICAL_IMPORTER.get()), "ABA", "BCB", "ADA", 'A',
                ChemicalHelper.get(TagPrefix.foil, GTMaterials.Gold), 'B',
                ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel), 'C',
                CTPPBlocks.HEAVY_MACHINERY_CASING.asStack(), 'D', AllBlocks.COGWHEEL.asStack());
        shaped(provider, "mechanical_exporter", stack(ItemBlockReg.MECHANICAL_EXPORTER.get()), "ADA", "BCB", "ABA", 'A',
                ChemicalHelper.get(TagPrefix.foil, GTMaterials.Gold), 'B',
                ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel), 'C',
                CTPPBlocks.HEAVY_MACHINERY_CASING.asStack(), 'D', AllBlocks.COGWHEEL.asStack());
        shaped(provider, "exporter", stack(ItemBlockReg.EXPORTER.get()), "ADA", "BCB", "ABA", 'A',
                ChemicalHelper.get(TagPrefix.foil, GTMaterials.Gold), 'B',
                ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel), 'C',
                CTPPBlocks.HEAVY_MACHINERY_CASING.asStack(), 'D',
                ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.RedAlloy));
        shaped(provider, "dimensional_gas_collection_chamber",
                MultiblocksA.DIMENSIONAL_GAS_COLLECTION_CHAMBER.asStack(), "AAA", "BCB", "DED", 'A',
                GTItems.TAG_FLUID_FILTER.asStack(), 'B', CustomTags.HV_CIRCUITS, 'C', GTBlocks.FILTER_CASING.asStack(),
                'D',
                GTItems.ELECTRIC_PUMP_HV.asStack(), 'E', GTMultiMachines.CLEANROOM.asStack());
        shaped(provider, "large_steel_furnace", MultiblocksA.LARGE_STEEL_FURNACE.asStack(), "DAD", "BCB", "DAD", 'A',
                ChemicalHelper.get(TagPrefix.spring, GTMaterials.Copper), 'B', CustomTags.LV_CIRCUITS, 'C',
                GTMultiMachines.STEAM_OVEN.asStack(), 'D', GTBlocks.CASING_PRIMITIVE_BRICKS.asStack());
        shaped(provider, "large_steel_alloy_furnace", MultiblocksA.LARGE_STEEL_ALLOY_FURNACE.asStack(), "DAD", "BCB",
                "DAD", 'A', ChemicalHelper.get(TagPrefix.spring, GTMaterials.Copper), 'B', CustomTags.LV_CIRCUITS,
                'C', GTMachines.STEAM_ALLOY_SMELTER.right().asStack(), 'D', GTBlocks.CASING_PRIMITIVE_BRICKS.asStack());
        shaped(provider, "testing_terminal", CTNHItems.TESTING_TERMINAL.asStack(), "BBB", "BAB", "BBB", 'A',
                GTItems.TERMINAL.asStack(), 'B', Items.REDSTONE);
        shapeless(provider, "stone_button", new ItemStack(Items.STONE_BUTTON), Items.STONE);
        shaped(provider, "sweat_shop", MultiblocksA.SWEATSHOP.asStack(), "BBB", "CAC", "EDE", 'A',
                AllItems.PRECISION_MECHANISM.asStack(), 'B', GTBlocks.CASING_STEEL_SOLID.asStack(), 'C',
                GTItems.ELECTRIC_MOTOR_LV.asStack(), 'D', GTMachines.ASSEMBLER[GTValues.LV].asStack(), 'E',
                GTItems.EMITTER_LV.asStack());
        if (ModList.get().isLoaded("biomesoplenty")) {
            shaped(provider, "rose_quartz_chunk", stack("biomesoplenty:rose_quartz_chunk", 4), "   ", " A ", "   ", 'A',
                    item("biomesoplenty:rose_quartz_block"));
        }
        shaped(provider, "bronze_large_boiler", GTMultiMachines.LARGE_BOILER_BRONZE.asStack(), "BCB", "CAC", "BCB", 'A',
                GTBlocks.FIREBOX_BRONZE.asStack(), 'B', ChemicalHelper.get(TagPrefix.cableGtSingle, GTMaterials.Lead),
                'C',
                CustomTags.ULV_CIRCUITS);
        shaped(provider, "high_strength_concrete", CTNHBlocks.HIGH_GRADE_COKE_OVEN_BRICKS.asStack(), " C ", "CAC",
                " C ", 'A',
                GTBlocks.CASING_COKE_BRICKS.asStack(), 'C', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel));
        shaped(provider, "advanced_coke_oven", MultiblocksA.ADVANCED_COKE_OVEN.asStack(), "BCB", "CAC", "BCB", 'A',
                GTMultiMachines.COKE_OVEN.asStack(), 'B', CTNHBlocks.HIGH_GRADE_COKE_OVEN_BRICKS.asStack(), 'C',
                ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel));
        shaped(provider, "ulv_input_bus", GTMachines.ITEM_IMPORT_BUS[GTValues.ULV].asStack(), "A", "B", 'A',
                GTMachines.HULL[GTValues.ULV].asStack(), 'B', Tags.Items.CHESTS_WOODEN);
        shaped(provider, "ulv_output_bus", GTMachines.ITEM_EXPORT_BUS[GTValues.ULV].asStack(), "B", "A", 'A',
                GTMachines.HULL[GTValues.ULV].asStack(), 'B', Tags.Items.CHESTS_WOODEN);
        shaped(provider, "elementium_normal_fluid_pipe", CMBlocks.ELEMENTIUM_NORMAL_FLUID_PIPE.asStack(2), "A A", "A A",
                "A A", 'A', ChemicalHelper.get(TagPrefix.plate, CMMaterials.Elementium));
        shaped(provider, "elementium_pipe_casing", CMBlocks.ELEMENTIUM_PIPE_CASING.asStack(), "CAC", "ABA", "CAC", 'A',
                CMBlocks.ELEMENTIUM_NORMAL_FLUID_PIPE.asStack(), 'B',
                ChemicalHelper.get(TagPrefix.frameGt, CMMaterials.Elementium), 'C',
                ChemicalHelper.get(TagPrefix.plate, CMMaterials.Elementium));
        shaped(provider, "ev_chemical_generator", MultiblocksA.EV_CHEMICAL_GENERATOR.asStack(), "ABA", "CDC", "EBE",
                'A', ChemicalHelper.get(TagPrefix.rotor, GTMaterials.Titanium), 'B',
                GTBlocks.CASING_TITANIUM_TURBINE.asStack(), 'C', GTBlocks.COIL_NICHROME.asStack(), 'D',
                ChemicalHelper.get(TagPrefix.cableGtSingle, GTMaterials.Aluminium), 'E',
                GTItems.ELECTRIC_MOTOR_EV.asStack());
        shaped(provider, "iv_chemical_generator", MultiblocksA.IV_CHEMICAL_GENERATOR.asStack(), "ABA", "CDC", "EBE",
                'A', ChemicalHelper.get(TagPrefix.rotor, GTMaterials.TungstenSteel), 'B',
                GTBlocks.CASING_TUNGSTENSTEEL_TURBINE.asStack(), 'C',
                GTBlocks.COIL_RTMALLOY.asStack(), 'D',
                ChemicalHelper.get(TagPrefix.cableGtSingle, GTMaterials.Platinum), 'E',
                GTItems.ELECTRIC_MOTOR_IV.asStack());
        shaped(provider, "forest_sea_tree_farm", MultiblocksB.FOREST_SEA_TREE_FARM.asStack(), "ABA", "BCB", "AEA", 'A',
                GTItems.FIELD_GENERATOR_IV.asStack(), 'B', GTMachines.WORLD_ACCELERATOR[GTValues.EV].asStack(), 'C',
                GTMachines.HULL[GTValues.IV].asStack(), 'E', CTNHItems.ECOLOGICAL_STAR.asStack());
        shaped(provider, "sterilizing_filter_casing", GTBlocks.FILTER_CASING_STERILE.asStack(), "ADA", "BCB", "FEG",
                'A', GTItems.TAG_FLUID_FILTER.asStack(), 'B', EIOBlocks.END_STEEL_BARS.asStack(), 'C',
                ChemicalHelper.get(TagPrefix.rotor, GTMaterials.HSSS),
                'D', GTItems.BLACKLIGHT.asStack(), 'E', ChemicalHelper.get(TagPrefix.frameGt, GTMaterials.Europium),
                'F', GTItems.ELECTRIC_MOTOR_LuV.asStack(),
                'G', GTItems.ELECTRIC_PUMP_LuV.asStack());
        shaped(provider, "uhv_parallel_hatch", CTNHMachines.PARALLEL_HATCH[GTValues.UHV].asStack(), "ABC", "BDB", "EBE",
                'A',
                GTItems.SENSOR_UHV.asStack(), 'B', CustomTags.UEV_CIRCUITS, 'C', GTItems.EMITTER_UHV.asStack(), 'D',
                GTMachines.HULL[GTValues.UHV].asStack(), 'E',
                ChemicalHelper.get(TagPrefix.cableGtDouble, BedrockMaterials.ADAMANTITE));
        shaped(provider, "astronomical_observatory", MultiblocksA.ASTRONOMICAL_OBSERVATORY.asStack(), "ABA", "CDC",
                "AEA", 'A', GTBlocks.CASING_STAINLESS_CLEAN.asStack(), 'B', GTBlocks.CASING_TEMPERED_GLASS.asStack(),
                'C', GTItems.ROBOT_ARM_HV.asStack(), 'D', Items.DAYLIGHT_DETECTOR, 'E', GTItems.SENSOR_HV.asStack());
        shaped(provider, "mechanical_pressor", Mechanical.MECHANICAL_PRESSOR.asStack(), "AAA", "BCB", "DED", 'A',
                CTPPItems.STEEL_MECHANISM.asStack(), 'B', AllBlocks.RAILWAY_CASING.asStack(), 'C',
                AllBlocks.MECHANICAL_PRESS.asStack(), 'D', AllBlocks.LARGE_COGWHEEL.asStack(), 'E',
                AllBlocks.DEPOT.asStack());
        shaped(provider, "mechanical_mixer_ctnh", Mechanical.MECHANICAL_MIXER.asStack(), "AAA", "BCB", "DED", 'A',
                CTPPItems.STEEL_MECHANISM.asStack(), 'B', AllBlocks.RAILWAY_CASING.asStack(), 'C',
                AllBlocks.MECHANICAL_MIXER.asStack(), 'D', AllBlocks.FLUID_PIPE.asItem(), 'E',
                AllBlocks.BASIN.asItem());
        shaped(provider, "mechanical_centrifuge", Mechanical.MECHANICAL_CENTRIFUGE.asStack(), "AAA", "BCB", "DDD", 'A',
                CTPPItems.STEEL_MECHANISM.asStack(), 'B', AllBlocks.RAILWAY_CASING.asStack(), 'C',
                VintageBlocks.CENTRIFUGE.get(), 'D', AllBlocks.BASIN.asItem());
        shaped(provider, "mechanical_sifter", Mechanical.MECHANICAL_SIFTER.asStack(), "AAA", "BCB", "DDD", 'A',
                CTPPItems.STEEL_MECHANISM.asStack(), 'B', AllBlocks.RAILWAY_CASING.asStack(), 'C',
                VintageBlocks.VIBRATING_TABLE.get(), 'D', GTItems.ITEM_FILTER.asStack());
        shaped(provider, "mechanical_extractor", Mechanical.MECHANICAL_EXTRACTOR.asStack(), "AAA", "BCB", "DED", 'A',
                CTPPItems.STEEL_MECHANISM.asStack(), 'B', AllBlocks.RAILWAY_CASING.asStack(), 'C',
                fr.lucreeper74.createmetallurgy.registries.CMBlocks.FOUNDRY_MIXER_BLOCK.get(), 'D',
                AllBlocks.FLUID_PIPE.asItem(), 'E',
                fr.lucreeper74.createmetallurgy.registries.CMBlocks.FOUNDRY_BASIN_BLOCK.get());
        shaped(provider, "mechanical_lathe", Mechanical.MECHANICAL_LATHE.asStack(), "AAA", "BCB", "DDD", 'A',
                CTPPItems.STEEL_MECHANISM.asStack(), 'B', AllBlocks.RAILWAY_CASING.asStack(), 'C',
                VintageBlocks.LATHE_ROTATING.get(), 'D', AllItems.BELT_CONNECTOR.asItem());
        shaped(provider, "iron_plating", stack(ModItems.IRON_PLATING.get(), 64), "AAA", "A A", "AAA", 'A',
                tag("forge:plates/iron"));
        shapeless(provider, "dungeon_brick1", stack(BloodMagicBlocks.DUNGEON_BRICK_1.get()),
                BloodMagicBlocks.DUNGEON_BRICK_ASSORTED.get());

        String[][] caveTablets = {
                { "primordial_caves", "candy_cavity" }, { "toxic_caves", "primordial_caves" },
                { "abyssal_chasm", "toxic_caves" }, { "forlorn_hollows", "abyssal_chasm" },
                { "magnetic_caves", "forlorn_hollows" }, { "candy_cavity", "magnetic_caves" }
        };
        for (String[] conversion : caveTablets) {
            shapeless(provider, "cave_tablet_" + conversion[0], caveTablet(conversion[0]), caveTablet(conversion[1]));
        }
        shapelessExact(provider, ResourceLocation.parse("ae2:redstone_p2p_tunnel"),
                stack(AEParts.REDSTONE_P2P_TUNNEL.asItem()), AEParts.ME_P2P_TUNNEL.asItem(), Items.REDSTONE);
        shapelessExact(provider, ResourceLocation.parse("ae2:fe_p2p_tunnel"), stack(AEParts.FE_P2P_TUNNEL.asItem()),
                AEParts.ME_P2P_TUNNEL.asItem(), AEItems.FLUIX_DUST.asItem());
        shapelessExact(provider, ResourceLocation.parse("ae2:fluid_p2p_tunnel"),
                stack(AEParts.FLUID_P2P_TUNNEL.asItem()), AEParts.ME_P2P_TUNNEL.asItem(), Items.BUCKET);
        shapelessExact(provider, ResourceLocation.parse("ae2:item_p2p_tunnel"), stack(AEParts.ITEM_P2P_TUNNEL.asItem()),
                AEParts.ME_P2P_TUNNEL.asItem(), Items.CHEST);
    }

    private static void shaped(Consumer<FinishedRecipe> provider, String id, ItemStack result, Object... recipe) {
        shaped(provider, CTNHCore.id("crafttable/" + id), result, recipe);
    }

    private static void shaped(Consumer<FinishedRecipe> provider, ResourceLocation id, ItemStack result,
                               Object... recipe) {
        VanillaRecipeHelper.addShapedRecipe(provider, id, result, recipe);
    }

    private static void shapeless(Consumer<FinishedRecipe> provider, String id, ItemStack result, Object... recipe) {
        shapeless(provider, CTNHCore.id("crafttable/" + id), result, recipe);
    }

    private static void shapeless(Consumer<FinishedRecipe> provider, ResourceLocation id, ItemStack result,
                                  Object... recipe) {
        VanillaRecipeHelper.addShapelessRecipe(provider, id, result, recipe);
    }

    private static ItemStack stack(ItemLike item) {
        return stack(item, 1);
    }

    private static ItemStack stack(ItemLike item, int count) {
        return new ItemStack(item, count);
    }

    private static ItemStack stack(ItemStack stack, int count) {
        ItemStack result = stack.copy();
        result.setCount(count);
        return result;
    }

    private static ItemStack stack(String id) {
        return stack(id, 1);
    }

    private static ItemStack stack(String id, int count) {
        return new ItemStack(item(id), count);
    }

    private static ItemStack stack(String id, int count, String snbt) {
        ItemStack stack = stack(id, count);
        try {
            stack.setTag(TagParser.parseTag(snbt));
        } catch (CommandSyntaxException e) {
            throw new IllegalArgumentException("Invalid recipe SNBT for " + id + ": " + snbt, e);
        }
        return stack;
    }

    private static ItemStack caveTablet(String biome) {
        ItemStack stack = new ItemStack(ACItemRegistry.CAVE_TABLET.get());
        try {
            stack.setTag(TagParser.parseTag("{CaveBiome:\"alexscaves:" + biome + "\"}"));
        } catch (CommandSyntaxException e) {
            throw new IllegalArgumentException("Invalid cave tablet biome: " + biome, e);
        }
        return stack;
    }

    private static Item item(String id) {
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(id)), id);
    }

    private static TagKey<Item> tag(String id) {
        return TagKey.create(Registries.ITEM, ResourceLocation.parse(id));
    }

    private static void keepShaped(Consumer<FinishedRecipe> provider, String id, ItemStack result, String[] pattern,
                                   Object[] keepIngredients, Object... key) {
        KeepIngredientRecipeHelper.addKeepIngredientShapedRecipe(provider,
                CTNHCore.id("crafttable/" + id), result,
                pattern, ingredients(keepIngredients), key);
    }

    private static Ingredient[] ingredients(Object... ingredients) {
        Ingredient[] result = new Ingredient[ingredients.length];
        for (int i = 0; i < ingredients.length; i++) {
            result[i] = ingredient(ingredients[i]);
        }
        return result;
    }

    private static Ingredient ingredient(Object ingredient) {
        if (ingredient instanceof Ingredient directIngredient) {
            return directIngredient;
        }
        if (ingredient instanceof TagKey<?> key) {
            return Ingredient.of((TagKey<Item>) key);
        }
        if (ingredient instanceof ItemStack stack) {
            return Ingredient.of(stack);
        }
        if (ingredient instanceof Item ingredientItem) {
            return Ingredient.of(ingredientItem);
        }
        if (ingredient instanceof ItemLike itemLike) {
            return Ingredient.of(itemLike);
        }
        throw new IllegalArgumentException("Unsupported keep recipe ingredient: " + ingredient);
    }

    private static void shapedExact(Consumer<FinishedRecipe> provider, ResourceLocation id, ItemStack result,
                                    String[] pattern, Object... key) {
        provider.accept(new FinishedRecipe() {

            @Override
            public void serializeRecipeData(JsonObject json) {
                JsonArray patternJson = new JsonArray();
                for (String row : pattern) {
                    patternJson.add(row);
                }
                json.add("pattern", patternJson);

                JsonObject keyJson = new JsonObject();
                for (int i = 0; i < key.length; i += 2) {
                    keyJson.add(String.valueOf(key[i]), ingredientJson(key[i + 1]));
                }
                json.add("key", keyJson);
                json.add("result", resultJson(result));
            }

            @Override
            public ResourceLocation getId() {
                return id;
            }

            @Override
            public RecipeSerializer<?> getType() {
                return RecipeSerializer.SHAPED_RECIPE;
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

    private static void shapelessExact(Consumer<FinishedRecipe> provider, ResourceLocation id, ItemStack result,
                                       Object... ingredients) {
        provider.accept(new FinishedRecipe() {

            @Override
            public void serializeRecipeData(JsonObject json) {
                JsonArray ingredientsJson = new JsonArray();
                for (Object ingredient : ingredients) {
                    ingredientsJson.add(ingredientJson(ingredient));
                }
                json.add("ingredients", ingredientsJson);
                json.add("result", resultJson(result));
            }

            @Override
            public ResourceLocation getId() {
                return id;
            }

            @Override
            public RecipeSerializer<?> getType() {
                return RecipeSerializer.SHAPELESS_RECIPE;
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

    private static JsonObject resultJson(ItemStack stack) {
        JsonObject result = new JsonObject();
        result.addProperty("item", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(stack.getItem())).toString());
        if (stack.getCount() > 1) {
            result.addProperty("count", stack.getCount());
        }
        if (stack.hasTag() && stack.getTag() != null) {
            result.add("nbt", NBTToJsonConverter.getObject(stack.getTag()));
        }
        return result;
    }

    private static JsonObject ingredientJson(Object ingredient) {
        if (ingredient instanceof TagKey<?> key) {
            return Ingredient.of((TagKey<Item>) key).toJson().getAsJsonObject();
        }
        if (ingredient instanceof ItemStack stack) {
            return Ingredient.of(stack).toJson().getAsJsonObject();
        }
        if (ingredient instanceof Item ingredientItem) {
            return Ingredient.of(ingredientItem).toJson().getAsJsonObject();
        }
        throw new IllegalArgumentException("Unsupported keep recipe ingredient: " + ingredient);
    }
}
