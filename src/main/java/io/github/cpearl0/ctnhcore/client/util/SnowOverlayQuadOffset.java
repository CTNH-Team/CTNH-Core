package io.github.cpearl0.ctnhcore.client.util;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.Direction;
import net.minecraftforge.client.model.IQuadTransformer;

import com.teamtea.eclipticseasons.client.model.bakequad.BakedQuadRetextured;
import com.teamtea.eclipticseasons.client.model.bakequad.BakedQuadRetexturedAndReUV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public final class SnowOverlayQuadOffset {

    private static final Logger LOGGER = LoggerFactory.getLogger("CTNH-EclipticFix");
    private static final AtomicInteger OFFSET_LOG_COUNT = new AtomicInteger();
    private static final Set<BakedQuad> OFFSETED_QUADS = Collections
            .synchronizedSet(Collections.newSetFromMap(new IdentityHashMap<>()));
    public static final float SNOW_OVERLAY_OFFSET = 0.01f;

    private SnowOverlayQuadOffset() {}

    public static void offsetAllIfNeeded(List<? extends BakedQuad> quads) {
        for (BakedQuad quad : quads) {
            if (quad instanceof BakedQuadRetextured || quad instanceof BakedQuadRetexturedAndReUV) {
                continue;
            }
            pushOutAlongFaceNormal(quad, SNOW_OVERLAY_OFFSET);
        }
    }

    public static void pushOutAlongFaceNormal(BakedQuad quad, float amount) {
        if (amount == 0.0f) {
            return;
        }
        if (!OFFSETED_QUADS.add(quad)) {
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

        if (OFFSET_LOG_COUNT.getAndIncrement() < 50) {
            LOGGER.info("CTNH-EclipticFix offset quad={} direction={} amount={}",
                    quad.getClass().getSimpleName(), direction, amount);
        }

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
