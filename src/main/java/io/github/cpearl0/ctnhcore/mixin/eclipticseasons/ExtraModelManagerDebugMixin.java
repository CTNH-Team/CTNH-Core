package io.github.cpearl0.ctnhcore.mixin.eclipticseasons;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

import com.teamtea.eclipticseasons.client.core.ExtraModelManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mixin(value = ExtraModelManager.class, remap = false)
public abstract class ExtraModelManagerDebugMixin {

    private static final Logger LOGGER = LoggerFactory.getLogger("CTNH-EclipticFix");

    @Inject(method = "makeSnowyBakedQuads", at = @At("RETURN"))
    private static void ctnhcore$debugMakeSnowyBakedQuads(BlockState state, List<BakedQuad> original,
                                                          ArrayList<BakedQuad> extra,
                                                          CallbackInfoReturnable<List<BakedQuad>> cir) {
        ResourceLocation id = BuiltInRegistries.BLOCK.getKey(state.getBlock());
        String path = id.getPath();
        if (path.contains("heatproof") || path.contains("machine_casing")) {
            List<BakedQuad> out = cir.getReturnValue();
            LOGGER.info("CTNH-EclipticFix makeSnowyBakedQuads block={} source={} extra={} out={} classes={}",
                    id, original.size(), extra.size(), out.size(),
                    out.stream().map(q -> q.getClass().getSimpleName()).distinct().collect(Collectors.joining(",")));
        }
    }
}
