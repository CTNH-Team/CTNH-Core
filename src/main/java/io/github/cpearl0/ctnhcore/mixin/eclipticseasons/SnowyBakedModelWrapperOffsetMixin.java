package io.github.cpearl0.ctnhcore.mixin.eclipticseasons;

import io.github.cpearl0.ctnhcore.client.util.SnowOverlayQuadOffset;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.ModelData;

import com.teamtea.eclipticseasons.client.model.SnowyBakedModelWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(SnowyBakedModelWrapper.class)
public abstract class SnowyBakedModelWrapperOffsetMixin extends BakedModelWrapper<BakedModel> {

    protected SnowyBakedModelWrapperOffsetMixin(BakedModel originalModel) {
        super(originalModel);
    }

    @Shadow(remap = false)
    public abstract boolean isReplace();

    @Override
    public List<BakedQuad> getQuads(BlockState state, Direction direction, RandomSource random) {
        List<BakedQuad> quads = super.getQuads(state, direction, random);
        if (!isReplace()) {
            SnowOverlayQuadOffset.offsetAllIfNeeded(quads);
        }
        return quads;
    }

    @Override
    public List<BakedQuad> getQuads(BlockState state, Direction direction, RandomSource random,
                                    ModelData modelData, RenderType renderType) {
        List<BakedQuad> quads = super.getQuads(state, direction, random, modelData, renderType);
        if (!isReplace()) {
            SnowOverlayQuadOffset.offsetAllIfNeeded(quads);
        }
        return quads;
    }
}
