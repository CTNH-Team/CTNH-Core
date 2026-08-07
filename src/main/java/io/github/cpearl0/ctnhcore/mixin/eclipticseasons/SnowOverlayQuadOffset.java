package io.github.cpearl0.ctnhcore.mixin.eclipticseasons;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.Direction;
import net.minecraftforge.client.model.IQuadTransformer;

final class SnowOverlayQuadOffset {

    static final float SNOW_OVERLAY_OFFSET = 0.003f;

    private SnowOverlayQuadOffset() {}

    static void pushOutAlongFaceNormal(BakedQuad quad, float amount) {
        if (amount == 0.0f) {
            return;
        }

        int[] vertices = quad.getVertices();
        Direction direction = quad.getDirection();
        if (direction == null) {
            return;
        }

        int xNormal = direction.getStepX();
        int yNormal = direction.getStepY();
        int zNormal = direction.getStepZ();

        for (int i = 0; i < 4; i++) {
            int vertexOffset = i * IQuadTransformer.STRIDE + IQuadTransformer.POSITION;
            float x = Float.intBitsToFloat(vertices[vertexOffset]);
            float y = Float.intBitsToFloat(vertices[vertexOffset + 1]);
            float z = Float.intBitsToFloat(vertices[vertexOffset + 2]);

            vertices[vertexOffset] = Float.floatToRawIntBits(x + amount * xNormal);
            vertices[vertexOffset + 1] = Float.floatToRawIntBits(y + amount * yNormal);
            vertices[vertexOffset + 2] = Float.floatToRawIntBits(z + amount * zNormal);
        }
    }
}
