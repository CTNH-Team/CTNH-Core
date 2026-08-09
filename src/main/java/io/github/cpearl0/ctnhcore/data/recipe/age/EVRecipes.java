package io.github.cpearl0.ctnhcore.data.recipe.age;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class EVRecipes {

    // 这里存放的是超高压(ev)时期的配方
    public static void init(Consumer<FinishedRecipe> provider) {
        addEVMotorRecipes(provider);
    }

    private static void addEVMotorRecipes(Consumer<FinishedRecipe> provider) {
        // EV电动马达（坎塔尔导线环绕钕磁杆，钛板底座）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/electric_motor_ev"),
                GTItems.ELECTRIC_MOTOR_EV.asStack(),
                " A ", "ABA", "CAC",
                'A', ChemicalHelper.get(TagPrefix.wireGtDouble, GTMaterials.Kanthal),
                'B', ChemicalHelper.get(TagPrefix.rod, GTMaterials.NeodymiumMagnetic),
                'C', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Titanium));
    }
}
