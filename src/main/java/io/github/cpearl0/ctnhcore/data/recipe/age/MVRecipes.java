package io.github.cpearl0.ctnhcore.data.recipe.age;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;

import com.mo_guang.ctpp.data.recipe.builder.create.MechanicalCraftingRecipeBuilder;
import com.mo_guang.ctpp.registry.CreateMaterials;
import com.unrealdinnerbone.javd.JAVDRegistry;

import java.util.function.Consumer;

public class MVRecipes {

    // 这里存放的是中压(mv)时期的配方
    public static void init(Consumer<FinishedRecipe> provider) {
        addJavdPortalBlockRecipes(provider);
        addMVMotorRecipes(provider);
    }

    private static void addJavdPortalBlockRecipes(Consumer<FinishedRecipe> provider) {
        // 传送门方块（机械合成：暗影钢与黑钢双层板，MV机壳）
        MechanicalCraftingRecipeBuilder.builder(CTNHCore.id("javd_portal_block"))
                .pattern("AAAAA", "ABCBA", "ACDCA", "ABCBA", "AAAAA")
                .key('A', ChemicalHelper.get(TagPrefix.plateDouble, CreateMaterials.ShadowSteel))
                .key('B', CustomTags.HV_CIRCUITS)
                .key('C', ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.BlackSteel))
                .key('D', GTBlocks.MACHINE_CASING_MV.asStack())
                .output(new ItemStack(JAVDRegistry.PORTAL_BLOCK_ITEM.get()))
                .save(provider);
    }

    private static void addMVMotorRecipes(Consumer<FinishedRecipe> provider) {
        // MV电动马达（铜镍导线环绕磁化钢杆，铝板底座）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/electric_motor_mv"),
                GTItems.ELECTRIC_MOTOR_MV.asStack(),
                " A ", "ABA", "CAC",
                'A', ChemicalHelper.get(TagPrefix.wireGtDouble, GTMaterials.Cupronickel),
                'B', ChemicalHelper.get(TagPrefix.rod, GTMaterials.SteelMagnetic),
                'C', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Aluminium));
    }
}
