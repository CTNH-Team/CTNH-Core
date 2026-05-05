package io.github.cpearl0.ctnhcore.data.recipe.mana;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.GTValues;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.effect.MobEffects;

import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
import com.moguang.ctnhbio.registry.CBRecipeTypes;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.HV;
import static io.github.lounode.extrabotany.common.item.ExtraBotanyItems.nightmareFuel;
import static io.github.lounode.extrabotany.common.item.ExtraBotanyItems.spiritFuel;

public class DigesterRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        CBRecipeBuilder.of(CTNHCore.id("spirit_fuel_from_digester"), CBRecipeTypes.DIGEST_RECIPES)
                .nutrient(20)
                .effect(MobEffects.GLOWING)
                .inputItems(nightmareFuel)
                .outputItems(spiritFuel)
                .duration(20)
                .EUt(GTValues.VA[HV])
                .save(provider);
    }
}
