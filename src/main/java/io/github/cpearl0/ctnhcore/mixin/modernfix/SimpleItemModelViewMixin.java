package io.github.cpearl0.ctnhcore.mixin.modernfix;

import com.gregtechceu.gtceu.api.block.IMachineBlock;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import com.llamalad7.mixinextras.sugar.Local;
import org.embeddedt.modernfix.render.SimpleItemModelView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Ported from GTOCore
 * ({@code GregTech-Odyssey/GTOCore-Main: com.gtocore.mixin.modernfix.SimpleItemModelViewMixin}).
 * <p>
 * When ModernFix's {@code mixin.perf.dynamic_resources} is enabled, item models are viewed through
 * {@link SimpleItemModelView} instead of being baked up-front. That view decides which quads to return
 * purely from the {@link Direction} passed in, which breaks GT machine blocks: their models are fully
 * baked per facing, so the face direction alone is not enough to pick the right quads.
 * <p>
 * Forcing {@code true} for machine blocks makes the view return the wrapped model's own quads instead of
 * the direction-filtered fast path, restoring correct facing for GT machine items.
 */
@Mixin(SimpleItemModelView.class)
public abstract class SimpleItemModelViewMixin {

    @Shadow(remap = false)
    protected abstract boolean isCorrectDirectionForType(Direction direction);

    // "getQuads" is remapped to m_213637_ in production by the generated refmap; the INVOKE target is
    // ModernFix-internal and therefore must not be remapped.
    @Redirect(method = "getQuads",
              at = @At(value = "INVOKE",
                       target = "Lorg/embeddedt/modernfix/render/SimpleItemModelView;isCorrectDirectionForType(Lnet/minecraft/core/Direction;)Z",
                       ordinal = 0,
                       remap = false))
    private boolean ctnhcore$isCorrectDirectionForType(SimpleItemModelView instance, Direction direction,
                                                       @Local(argsOnly = true) BlockState state) {
        return (state != null && state.getBlock() instanceof IMachineBlock) || isCorrectDirectionForType(direction);
    }
}
