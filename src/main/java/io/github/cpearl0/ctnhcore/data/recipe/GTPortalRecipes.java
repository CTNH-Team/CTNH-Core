//package io.github.cpearl0.ctnhcore.data.recipe;
//
//import io.github.cpearl0.ctnhcore.CTNHCore;
//
//import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
//import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
//import com.gregtechceu.gtceu.common.data.GTItems;
//import com.gregtechceu.gtceu.common.data.GTMaterials;
//import com.gregtechceu.gtceu.data.recipe.CustomTags;
//import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
//
//import net.minecraft.data.recipes.FinishedRecipe;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.tags.ItemTags;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.level.material.Fluids;
//import net.minecraftforge.fluids.FluidStack;
//
//import com.ironsword.gtportal.common.data.GTPBlocks;
//import com.ironsword.gtportal.common.data.GTPItems;
//import com.ironsword.gtportal.common.data.GTPMachines;
//import dev.denismasterherobrine.angelring.register.ItemRegistry;
//import vazkii.botania.common.item.BotaniaItems;
//
//import java.util.function.Consumer;
//
//import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;
//import static com.mo_guang.ctpp.common.recipe.builder.create.CompactingRecipeBuilder.builder;
//
//public class GTPortalRecipes {
//
//    private static final Item DIMENSION_DATA_STICK = GTPItems.DIMENSION_DATA_STICK.get();
//
//    public static void init(Consumer<FinishedRecipe> provider) {
//        // Simple Nether Portal Frame
//        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("gtportal/simple_nether_portal_frame"),
//                new ItemStack(GTPBlocks.SIMPLE_NETHER_PORTAL_FRAME.asItem(), 4),
//                "ABA", "BCB", "ABA",
//                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Bronze),
//                'B', Items.NETHERRACK,
//                'C', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Rubber));
//
//        // Simple Nether Portal Controller
//        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("gtportal/simple_nether_portal_controller"),
//                new ItemStack(GTPMachines.SIMPLE_NETHER_PORTAL_CONTROLLER.asStack().getItem()),
//                "ADA", "BCB", "ABA",
//                'A', ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Steel),
//                'B', ChemicalHelper.get(TagPrefix.rod, GTMaterials.Steel),
//                'C', GTPBlocks.SIMPLE_NETHER_PORTAL_FRAME.asItem(),
//                'D', Items.FLINT_AND_STEEL);
//
//        // Simple Overworld Portal Frame
//        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("gtportal/simple_overworld_portal_frame"),
//                new ItemStack(GTPBlocks.SIMPLE_OVERWORLD_PORTAL_FRAME.asItem(), 4),
//                "ABA", "BCB", "ABA",
//                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Iron),
//                'B', Items.GRASS_BLOCK,
//                'C', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Rubber));
//
//        // Simple Overworld Portal Controller
//        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("gtportal/simple_overworld_portal_controller"),
//                new ItemStack(GTPMachines.SIMPLE_OVERWORLD_PORTAL_CONTROLLER.asStack().getItem()),
//                "ADA", "BCB", "ABA",
//                'A', ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Steel),
//                'B', ChemicalHelper.get(TagPrefix.rod, GTMaterials.Steel),
//                'C', GTPBlocks.SIMPLE_OVERWORLD_PORTAL_FRAME.asItem(),
//                'D', Items.FLINT_AND_STEEL);
//
//        // Simple Twilight Portal Frame
//        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("gtportal/simple_twilight_portal_frame"),
//                new ItemStack(GTPBlocks.SIMPLE_TWILIGHT_PORTAL_FRAME.asItem(), 4),
//                "ABA", "BCB", "ABA",
//                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.TreatedWood),
//                'B', ItemTags.LEAVES,
//                'C', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Rubber));
//
//        // Simple Twilight Portal Controller
//        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("gtportal/simple_twilight_portal_controller"),
//                new ItemStack(GTPMachines.SIMPLE_TWILIGHT_PORTAL_CONTROLLER.asStack().getItem()),
//                "ADA", "BCB", "ABA",
//                'A', ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Steel),
//                'B', ChemicalHelper.get(TagPrefix.rod, GTMaterials.TreatedWood),
//                'C', GTPBlocks.SIMPLE_TWILIGHT_PORTAL_FRAME.asItem(),
//                'D', ChemicalHelper.get(TagPrefix.gemExquisite, GTMaterials.Diamond));
//
//        // Simple End Portal Frame
//        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("gtportal/simple_end_portal_frame"),
//                new ItemStack(GTPBlocks.SIMPLE_END_PORTAL_FRAME.asItem(), 4),
//                "ABA", "BCB", "ABA",
//                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Aluminium),
//                'B', Items.ENDER_PEARL,
//                'C', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Polyethylene));
//
//        // Simple End Portal Controller
//        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("gtportal/simple_end_portal_controller"),
//                new ItemStack(GTPMachines.SIMPLE_END_PORTAL_CONTROLLER.asStack().getItem()),
//                "ADA", "BCB", "ABA",
//                'A', ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Aluminium),
//                'B', GTPBlocks.BROKEN_END_PORTAL_FRAME.asItem(),
//                'C', GTPBlocks.SIMPLE_END_PORTAL_FRAME.asItem(),
//                'D', ChemicalHelper.get(TagPrefix.block, GTMaterials.EnderEye));
//
//        // Simple Aether Portal Frame
//        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("gtportal/simple_aether_portal_frame"),
//                new ItemStack(GTPBlocks.SIMPLE_AETHER_PORTAL_FRAME.asItem(), 4),
//                "ABA", "BCB", "ABA",
//                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Aluminium),
//                'B', Items.GLOWSTONE,
//                'C', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Polyethylene));
//
//        // Simple Aether Portal Controller
//        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("gtportal/simple_aether_portal_controller"),
//                new ItemStack(GTPMachines.SIMPLE_AETHER_PORTAL_CONTROLLER.asStack().getItem()),
//                "ADA", "BCB", "ABA",
//                'A', ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Aluminium),
//                'B', BotaniaItems.enderAirBottle,
//                'C', GTPBlocks.SIMPLE_AETHER_PORTAL_FRAME.asItem(),
//                'D', ItemRegistry.ANGEL_RING.get());
//
//        // Multidimensional Portal Frame
//        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("gtportal/multidimensional_portal_frame"),
//                new ItemStack(GTPBlocks.MULTIDIMENSIONAL_PORTAL_FRAME.asItem(), 4),
//                "ABA", "BCB", "ABA",
//                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.TungstenSteel),
//                'B', ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.Nichrome),
//                'C', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Polytetrafluoroethylene));
//
//        // Multidimensional Portal Controller
//        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("gtportal/multidimensional_portal_controller"),
//                new ItemStack(GTPMachines.MULTIDIMENSIONAL_PORTAL_CONTROLLER.asStack().getItem()),
//                "ADA", "BCB", "ABA",
//                'A', ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.TungstenSteel),
//                'B', ChemicalHelper.get(TagPrefix.rod, GTMaterials.TungstenSteel),
//                'C', GTPBlocks.MULTIDIMENSIONAL_PORTAL_FRAME.asItem(),
//                'D', CustomTags.IV_CIRCUITS);
//
//        // Assembler: dimension_data_stick
//        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("gtportal/dimension_data_stick"))
//                .inputItems(Items.PAPER)
//                .inputItems(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel, 2))
//                .inputItems(ChemicalHelper.get(TagPrefix.foil, GTMaterials.Rubber, 2))
//                .inputItems(ChemicalHelper.get(TagPrefix.wireFine, GTMaterials.Copper, 2))
//                .outputItems(DIMENSION_DATA_STICK)
//                .EUt(30)
//                .duration(200)
//                .save(provider);
//
//        // Dimension Data Stick NBT variants
//        // twilight_forest
//        ItemStack twilightStick = new ItemStack(DIMENSION_DATA_STICK);
//        CompoundTag twilightTag = new CompoundTag();
//        twilightTag.putString("dimension", "twilightforest:twilight_forest");
//        twilightStick.setTag(twilightTag);
//        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("gtportal/dimension_data_stick_twilight"),
//                twilightStick,
//                "ABA", "ACA", "AAA",
//                'A', ItemTags.FLOWERS,
//                'B', ChemicalHelper.get(TagPrefix.gemExquisite, GTMaterials.Diamond),
//                'C', DIMENSION_DATA_STICK);
//
//        // the_end
//        ItemStack endStick = new ItemStack(DIMENSION_DATA_STICK);
//        CompoundTag endTag = new CompoundTag();
//        endTag.putString("dimension", "minecraft:the_end");
//        endStick.setTag(endTag);
//        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("gtportal/dimension_data_stick_end"),
//                endStick,
//                "AAA", "ABA", "AAA",
//                'A', Items.ENDER_EYE,
//                'B', DIMENSION_DATA_STICK);
//
//        // the_aether
//        ItemStack aetherStick = new ItemStack(DIMENSION_DATA_STICK);
//        CompoundTag aetherTag = new CompoundTag();
//        aetherTag.putString("dimension", "aether:the_aether");
//        aetherStick.setTag(aetherTag);
//        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("gtportal/dimension_data_stick_aether"),
//                aetherStick,
//                "ACA", "CBC", "ACA",
//                'A', immersive_aircraft.Items.ENHANCED_PROPELLER.get(),
//                'B', DIMENSION_DATA_STICK,
//                'C', BotaniaItems.enderAirBottle);
//
//        // overworld
//        ItemStack overworldStick = new ItemStack(DIMENSION_DATA_STICK);
//        CompoundTag overworldTag = new CompoundTag();
//        overworldTag.putString("dimension", "minecraft:overworld");
//        overworldStick.setTag(overworldTag);
//        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("gtportal/dimension_data_stick_overworld"),
//                overworldStick,
//                "AAA", "ABA", "AAA",
//                'A', Items.GRASS_BLOCK,
//                'B', DIMENSION_DATA_STICK);
//
//        // the_nether
//        ItemStack netherStick = new ItemStack(DIMENSION_DATA_STICK);
//        CompoundTag netherTag = new CompoundTag();
//        netherTag.putString("dimension", "minecraft:the_nether");
//        netherStick.setTag(netherTag);
//        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("gtportal/dimension_data_stick_nether"),
//                netherStick,
//                "AAA", "ABA", "AAA",
//                'A', Items.NETHERRACK,
//                'B', DIMENSION_DATA_STICK);
//
//        // Assembler: dimension_data_recorder
//        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("gtportal/dimension_data_recorder"))
//                .inputItems(GTItems.TERMINAL.asStack())
//                .inputItems(Items.ENDER_PEARL, 4)
//                .outputItems(GTPItems.DIMENSION_DATA_RECORDER.asStack())
//                .EUt(120)
//                .duration(200)
//                .save(provider);
//
//        // Create Compacting: end_stone
//        builder("gtportal_end_stone")
//                .inputFluid(new FluidStack(Fluids.WATER, 250))
//                .input(new ItemStack(Items.SAND))
//                .input(new ItemStack(Items.GLOWSTONE_DUST, 2))
//                .output(new ItemStack(Items.END_STONE))
//                .save(provider);
//    }
//}
