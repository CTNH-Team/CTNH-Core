package io.github.cpearl0.ctnhcore.mixin.gtceu;

import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.data.recipe.misc.RecyclingRecipes;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.Consumer;

@Mixin(value = RecyclingRecipes.class, remap = false)
public class RecyclingRecipesMixin {

    @Inject(
            method = "registerRecyclingRecipes",
            at = @At("HEAD"),
            cancellable = true)
    private static void registerRecyclingRecipesMixin(Consumer<FinishedRecipe> provider, ItemStack input,
                                                      List<MaterialStack> components, boolean ignoreArcSmelting,
                                                      @Nullable TagPrefix prefix, CallbackInfo ci) {
        if (input.getItem() == GTMachines.CLEANING_MAINTENANCE_HATCH.getItem())
            ci.cancel();
    }
}
