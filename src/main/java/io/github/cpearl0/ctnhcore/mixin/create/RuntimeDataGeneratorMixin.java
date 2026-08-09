package io.github.cpearl0.ctnhcore.mixin.create;

import net.minecraft.resources.ResourceLocation;

import com.simibubi.create.foundation.data.RuntimeDataGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RuntimeDataGenerator.class, remap = false)
public class RuntimeDataGeneratorMixin {

    @Inject(method = "cuttingRecipes",
            at = @At(value = "INVOKE",
                     target = "Lcom/simibubi/create/foundation/data/RuntimeDataGenerator;simpleWoodRecipe(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/resources/ResourceLocation;)V",
                     ordinal = 2,
                     shift = At.Shift.BEFORE),
            cancellable = true)
    private static void cancelFenceRecipe(ResourceLocation itemId, CallbackInfo ci) {
        ci.cancel();
    }
}
