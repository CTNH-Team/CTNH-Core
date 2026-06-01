package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import com.buuz135.functionalstorage.FunctionalStorage;
import com.buuz135.functionalstorage.item.StorageUpgradeItem;
import com.simibubi.create.AllBlocks;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLY_LINE_RECIPES;

public class FunctionalStorageRecipes {

    private static final TagKey<Item> DRAWER_TAG = TagKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath("functionalstorage", "drawer"));

    private static final Item FLUID_TANK = AllBlocks.FLUID_TANK.asStack().getItem();

    private static final Item COPPER_BLOCK = Blocks.COPPER_BLOCK.asItem();
    private static final Item HV_SUPER_TANK = GTMachines.SUPER_TANK[HV].asStack().getItem();
    private static final Item HV_SUPER_CHEST = GTMachines.SUPER_CHEST[HV].asStack().getItem();
    private static final Item LUV_QUANTUM_TANK = GTMachines.QUANTUM_TANK[LuV].asStack().getItem();
    private static final Item LUV_QUANTUM_CHEST = GTMachines.QUANTUM_CHEST[LuV].asStack().getItem();

    public static void init(Consumer<FinishedRecipe> provider) {
        // fluid_1
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("functionalstorage/fluid_1"),
                new ItemStack(FunctionalStorage.FLUID_DRAWER_1.getLeft().get().asItem()),
                "AAA", " B ", "AAA",
                'A', new ItemStack(Items.SMOOTH_STONE),
                'B', new ItemStack(FLUID_TANK));

        // storage_controller
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/storage_controller"),
                new ItemStack(FunctionalStorage.DRAWER_CONTROLLER.getLeft().get().asItem()),
                "AAA", "BCB", "DED",
                'A', new ItemStack(Items.SMOOTH_STONE),
                'B', ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Steel),
                'C', ChemicalHelper.get(TagPrefix.gemExquisite, GTMaterials.Emerald),
                'D', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel),
                'E', new ItemStack(Items.COMPARATOR));

        // copper_upgrade
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/copper_upgrade"),
                new ItemStack(FunctionalStorage.STORAGE_UPGRADES.get(StorageUpgradeItem.StorageTier.COPPER).get()),
                "ABA", "BCB", "DED",
                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Copper),
                'B', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Bronze),
                'C', new ItemStack(FunctionalStorage.FLUID_DRAWER_1.getLeft().get().asItem()),
                'D', new ItemStack(COPPER_BLOCK),
                'E', DRAWER_TAG);

        // gold_upgrade
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/gold_upgrade"),
                new ItemStack(FunctionalStorage.STORAGE_UPGRADES.get(StorageUpgradeItem.StorageTier.GOLD).get()),
                "ABA", "CDC", "EFE",
                'A', ChemicalHelper.get(TagPrefix.screw, GTMaterials.Electrum),
                'B', new ItemStack(FunctionalStorage.FLUID_DRAWER_1.getLeft().get().asItem()),
                'C', ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Electrum),
                'D', new ItemStack(FunctionalStorage.STORAGE_UPGRADES.get(StorageUpgradeItem.StorageTier.COPPER).get()),
                'E', ChemicalHelper.get(TagPrefix.rod, GTMaterials.Electrum),
                'F', DRAWER_TAG);

        // diamond_upgrade (assembler)
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("functionalstorage/diamond_upgrade"))
                .inputItems(HV_SUPER_TANK)
                .inputItems(HV_SUPER_CHEST)
                .inputItems(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Diamond, 2))
                .inputItems(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Diamond, 2))
                .inputItems(FunctionalStorage.STORAGE_UPGRADES.get(StorageUpgradeItem.StorageTier.GOLD).get())
                .outputItems(FunctionalStorage.STORAGE_UPGRADES.get(StorageUpgradeItem.StorageTier.DIAMOND).get())
                .EUt(480)
                .duration(100)
                .save(provider);

        // netherite_upgrade (assembly_line)
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("functionalstorage/netherite_upgrade"))
                .inputItems(FunctionalStorage.STORAGE_UPGRADES.get(StorageUpgradeItem.StorageTier.DIAMOND).get())
                .inputItems(LUV_QUANTUM_TANK)
                .inputItems(LUV_QUANTUM_CHEST)
                .inputItems(Items.NETHERITE_INGOT, 4)
                .outputItems(FunctionalStorage.STORAGE_UPGRADES.get(StorageUpgradeItem.StorageTier.NETHERITE).get())
                .EUt(VA[LuV])
                .duration(100)
                .save(provider);

        // framed_storage_controller (shapeless)
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("crafttable/framed_storage_controller"),
                new ItemStack(FunctionalStorage.FRAMED_DRAWER_CONTROLLER.getLeft().get().asItem()),
                new ItemStack(FunctionalStorage.DRAWER_CONTROLLER.getLeft().get().asItem()));
    }
}
