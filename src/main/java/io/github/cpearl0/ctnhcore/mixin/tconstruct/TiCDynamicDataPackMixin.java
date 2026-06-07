package io.github.cpearl0.ctnhcore.mixin.tconstruct;

import io.github.cpearl0.ctnhcore.common.tconstruct.TConstructFluidTagFilter;

import net.minecraft.resources.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.data.pack.TiCDynamicDataPack;

@Mixin(value = TiCDynamicDataPack.class, remap = false)
public class TiCDynamicDataPackMixin {

    @Inject(method = "addData", at = @At("HEAD"), cancellable = true)
    private static void ctnh$skipTConstructForgeFluidTags(ResourceLocation location, byte[] bytes, CallbackInfo ci) {
        if (TConstructFluidTagFilter.shouldSkipDynamicFluidTag(location)) {
            ci.cancel();
        }
    }
}
