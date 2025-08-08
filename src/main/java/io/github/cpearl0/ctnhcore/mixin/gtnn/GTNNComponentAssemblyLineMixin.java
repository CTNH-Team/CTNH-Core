package io.github.cpearl0.ctnhcore.mixin.gtnn;

import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import dev.arbor.gtnn.data.recipes.handler.ComponentAssemblyLineRecipeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ComponentAssemblyLineRecipeHandler.class)
public class GTNNComponentAssemblyLineMixin {
    @Inject(method = "init", at = @At("HEAD"), remap = false, cancellable = true)
    public final void init(GTRecipeBuilder recipeBuilder, CallbackInfo ci) {
        ci.cancel();
    }
}
