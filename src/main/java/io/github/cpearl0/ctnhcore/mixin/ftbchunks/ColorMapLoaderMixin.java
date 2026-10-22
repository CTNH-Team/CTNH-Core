package io.github.cpearl0.ctnhcore.mixin.ftbchunks;

import io.github.cpearl0.ctnhcore.integration.ftbchunks.TextureBlockColor;

import net.minecraft.world.level.block.Block;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.ftb.mods.ftbchunks.ColorMapLoader;
import dev.ftb.mods.ftbchunks.client.map.color.BlockColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;

@Mixin(value = ColorMapLoader.class, remap = false)
public class ColorMapLoaderMixin {

    // FTB Chunks 2001.3.6: only the default MapColor branch, before explicit resource-pack overrides.
    @WrapOperation(method = "apply(Lcom/google/gson/JsonObject;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
                   at = @At(value = "INVOKE",
                            target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",
                            ordinal = 5))
    private Object ctnhcore$sampleRenderedSurface(Map<Object, Object> colors, Object id, Object color,
                                                  Operation<Object> original, @Local Block block) {
        // FTB's CustomBlockColor fast path skips getBlockColor(level, pos); use the dynamic interface.
        return original.call(colors, id, new TextureBlockColor(block, (BlockColor) color));
    }
}
