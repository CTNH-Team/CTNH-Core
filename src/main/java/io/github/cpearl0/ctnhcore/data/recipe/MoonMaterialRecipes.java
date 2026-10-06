package io.github.cpearl0.ctnhcore.data.recipe;

import com.gregtechceu.gtceu.api.data.chemical.material.ItemMaterialData;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.ItemMaterialInfo;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack;
import com.gregtechceu.gtceu.data.recipe.StoneTypeEntry;
import com.gregtechceu.gtceu.data.recipe.misc.StoneMachineRecipes;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;

import earth.terrarium.adastra.common.registry.ModItems;

import java.util.List;
import java.util.function.Consumer;

import static com.ctnh.ctnhastral.data.CAMaterials.Moonstone;
import static com.ctnh.ctnhastral.registry.worldgen.MoonBlocks.*;
import static com.gregtechceu.gtceu.api.GTValues.M;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

/** Bind existing lunar blocks to GT's stone families and material recycling, without per-block recipes. */
public class MoonMaterialRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        // The addon hook runs before GT's RecyclingRecipes.init, on every recipe reload.
        // Keep the declarations here so the generic generators see the current material data.
        for (var entry : List.of(
                new StoneTypeEntry.Builder("ctnhastral", "ctnhastral_moon_stone")
                        .stone(MOON_STONE.asItem()).crackedStone(MOON_COBBLESTONE.asItem())
                        .polishedStone(ModItems.MOON_STONE_BRICKS.get())
                        .slab(ModItems.MOON_STONE_SLAB.get()).stair(ModItems.MOON_STONE_STAIRS.get())
                        .chiselStone(ModItems.MOON_PILLAR.get())
                        .material(Moonstone).registerAllMaterialInfo().build(),
                new StoneTypeEntry.Builder("ctnhastral", "ctnhastral_moon_cobblestone")
                        .stone(MOON_COBBLESTONE.asItem()).smeltStone(MOON_STONE.asItem())
                        .polishedStone(ModItems.POLISHED_MOON_STONE.get())
                        .slab(ModItems.MOON_COBBLESTONE_SLAB.get()).stair(ModItems.MOON_COBBLESTONE_STAIRS.get())
                        .material(Moonstone).registerAllMaterialInfo().build(),
                new StoneTypeEntry.Builder("ctnhastral", "ctnhastral_moon_anorthosite")
                        .stone(MOON_ANORTHOSITE.asItem()).crackedStone(MOON_BROKEN_ANORTHOSITE.asItem())
                        .material(Stone).registerAllMaterialInfo().build(),
                new StoneTypeEntry.Builder("ctnhastral", "ctnhastral_moon_broken_anorthosite")
                        .stone(MOON_BROKEN_ANORTHOSITE.asItem()).smeltStone(MOON_ANORTHOSITE.asItem())
                        .material(Stone).registerAllMaterialInfo().build(),
                new StoneTypeEntry.Builder("ctnhastral", "ctnhastral_silicon_crust")
                        .stone(SILICON_CRUST.asItem()).crackedStone(SILICON_GRAVEL.asItem())
                        .material(SiliconDioxide).registerAllMaterialInfo().build())) {
            StoneMachineRecipes.registerStoneMaterialInfo(entry);
            StoneMachineRecipes.registerStoneTypeRecipes(provider, entry);
        }

        material(MOON_SAND, Moonstone, M);
        material(SILICON_BRECCIA, Stone, M);
        for (var block : List.of(MOONLIGHT_SAND, SILICON_GRAVEL, SILICON_CRYSTAL)) {
            material(block, SiliconDioxide, M);
        }
        material(IRIDESCENT_SILICON_CRYSTAL, SiliconDioxide, 2 * M);
        material(SILICON_CRYSTAL_BLOCK, SiliconDioxide, 4 * M);
        material(BUDDING_SILICON_CRYSTAL, SiliconDioxide, 4 * M);
        material(SMALL_SILICON_CRYSTAL_BUD, SiliconDioxide, M / 9);
        material(MEDIUM_SILICON_CRYSTAL_BUD, SiliconDioxide, 2 * M / 9);
        material(LARGE_SILICON_CRYSTAL_BUD, SiliconDioxide, 4 * M / 9);

        ItemMaterialData.registerMaterialInfo(LUMINOUS_MOON_SAND, new ItemMaterialInfo(
                new MaterialStack(SiliconDioxide, M), new MaterialStack(Glowstone, M)));
        ItemMaterialData.registerMaterialInfo(MOONLIGHT_CRYSTAL, new ItemMaterialInfo(
                new MaterialStack(SiliconDioxide, 4 * M), new MaterialStack(Glowstone, 4 * M)));

        // Use the same Ice material as vanilla ice. Its dust already has GT extraction/heating recipes.
        material(MOON_DENSE_ICE, Ice, M);
        ItemMaterialData.registerMaterialInfo(MOON_DUSTY_ICE, new ItemMaterialInfo(
                new MaterialStack(Ice, M / 2), new MaterialStack(Moonstone, M / 2)));
    }

    private static void material(ItemLike item, Material material, long amount) {
        ItemMaterialData.registerMaterialInfo(item, new ItemMaterialInfo(new MaterialStack(material, amount)));
    }
}
