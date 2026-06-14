package io.github.cpearl0.ctnhcore.mixin.mc;

import io.github.cpearl0.ctnhcore.data.recipe.TagManagerCache;

import net.minecraft.commands.Commands;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.world.flag.FeatureFlagSet;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin into ReloadableServerResources — 在构造函数中保存引用。
 * <p>
 * 与 KubeJS 的 {@code ReloadableServerResourcesMixin} 原理完全一致：
 * 在构造时保存 {@code ReloadableServerResources} 引用，
 * 以便后续在 {@code RecipeManager.apply} 阶段通过 {@code tagManager.getResult()} 获取 tag 数据。
 */
@Mixin(ReloadableServerResources.class)
public abstract class ReloadableServerResourcesMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void ctnhcore$saveResources(RegistryAccess.Frozen frozen, FeatureFlagSet featureFlagSet,
                                        Commands.CommandSelection commandSelection, int i, CallbackInfo ci) {
        TagManagerCache.setServerResources((ReloadableServerResources) (Object) this);
    }
}
