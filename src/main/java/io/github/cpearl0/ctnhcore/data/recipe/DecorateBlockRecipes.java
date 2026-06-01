package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import sfiomn.legendarysurvivaloverhaul.registry.ItemRegistry;

import java.util.function.Consumer;

public class DecorateBlockRecipes {

    private record MaterialGroup(String prefix, Material material, Material rodMaterial) {}

    private static final MaterialGroup[] MATERIALS = {
            new MaterialGroup("bronze_plated_bricks", GTMaterials.Bronze, GTMaterials.Bronze),
            new MaterialGroup("solid_steel", GTMaterials.Steel, GTMaterials.Steel),
            new MaterialGroup("frost_proof", GTMaterials.Aluminium, GTMaterials.Aluminium),
            new MaterialGroup("clean_stainless_steel", GTMaterials.StainlessSteel, GTMaterials.StainlessSteel),
            new MaterialGroup("stable_titanium", GTMaterials.Titanium, GTMaterials.Titanium),
            new MaterialGroup("robust_tungstensteel", GTMaterials.TungstenSteel, GTMaterials.TungstenSteel),
            new MaterialGroup("palladium_substation", GTMaterials.RhodiumPlatedPalladium,
                    GTMaterials.RhodiumPlatedPalladium),
            new MaterialGroup("inert_ptfe", GTMaterials.Polytetrafluoroethylene, GTMaterials.Polytetrafluoroethylene),
            new MaterialGroup("heatproof", GTMaterials.Invar, GTMaterials.Invar),
            new MaterialGroup("sturdy_hsse_green", GTMaterials.Bronze, GTMaterials.Bronze),
    };

    private static Item getOutput(String prefix, String type) {
        return ForgeRegistries.ITEMS.getValue(CTNHCore.id("machine_casing_" + prefix + "_" + type));
    }

    public static void init(Consumer<FinishedRecipe> provider) {
        for (MaterialGroup mg : MATERIALS) {
            ItemStack ingot = ChemicalHelper.get(TagPrefix.ingot, mg.material);
            Item slabOutput = getOutput(mg.prefix, "slab");
            Item wallOutput = getOutput(mg.prefix, "wall");
            Item stairsOutput = getOutput(mg.prefix, "stairs");
            Item fenceOutput = getOutput(mg.prefix, "fence");
            ItemStack rod = ChemicalHelper.get(TagPrefix.rod, mg.rodMaterial);

            // Slab: 6x
            VanillaRecipeHelper.addShapedRecipe(provider,
                    CTNHCore.id("crafttable/machine_casing_" + mg.prefix + "_slab"),
                    new ItemStack(slabOutput, 6),
                    "DDD", "   ", "   ",
                    'D', ingot);

            // Wall: 6x
            VanillaRecipeHelper.addShapedRecipe(provider,
                    CTNHCore.id("crafttable/machine_casing_" + mg.prefix + "_wall"),
                    new ItemStack(wallOutput, 6),
                    "DDD", "DDD", "   ",
                    'D', ingot);

            // Stairs: 6x
            VanillaRecipeHelper.addShapedRecipe(provider,
                    CTNHCore.id("crafttable/machine_casing_" + mg.prefix + "_stairs"),
                    new ItemStack(stairsOutput, 6),
                    "D  ", "DD ", "DDD",
                    'D', ingot);

            // Fence: 3x
            VanillaRecipeHelper.addShapedRecipe(provider,
                    CTNHCore.id("crafttable/machine_casing_" + mg.prefix + "_fence"),
                    new ItemStack(fenceOutput, 3),
                    "   ", "DAD", "DAD",
                    'D', ingot,
                    'A', rod);
        }

        // ender_light
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/ender_light"),
                CTNHItems.ENDER_LIGHT.asStack(),
                "DDD", "ABC", "EEE",
                'D', ItemRegistry.SUN_FERN.get(),
                'A', ItemRegistry.GLISTERING_MELON_JUICE.get(),
                'B', ItemRegistry.GOLDEN_APPLE_JUICE.get(),
                'C', ItemRegistry.GOLDEN_CARROT_JUICE.get(),
                'E', ItemRegistry.ICE_FERN.get());
    }
}
