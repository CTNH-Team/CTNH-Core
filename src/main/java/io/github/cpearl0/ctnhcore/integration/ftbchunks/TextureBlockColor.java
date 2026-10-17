package io.github.cpearl0.ctnhcore.integration.ftbchunks;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.SpriteContents;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.mojang.blaze3d.platform.NativeImage;
import dev.ftb.mods.ftbchunks.client.map.MapManager;
import dev.ftb.mods.ftbchunks.client.map.color.BlockColor;
import dev.ftb.mods.ftblibrary.icon.Color4I;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/** Uses the same texture atlas and position-dependent tint inputs as world rendering. */
@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class TextureBlockColor implements BlockColor {

    private static final Map<TextureRegion, TextureColor> TEXTURES = new ConcurrentHashMap<>();
    private static volatile Map<SpriteContents, TexturePixels> texturePixels = Map.of();

    private final Block block;
    private final BlockColor fallback;

    public TextureBlockColor(Block block, BlockColor fallback) {
        this.block = block;
        this.fallback = fallback;
    }

    @SubscribeEvent
    public static void onTextureStitched(TextureStitchEvent.Post event) {
        var atlas = event.getAtlas();
        if (!atlas.location().equals(InventoryMenu.BLOCK_ATLAS)) return;
        Map<SpriteContents, TexturePixels> snapshot = new HashMap<>();
        for (var location : atlas.getTextureLocations()) {
            var contents = atlas.getSprite(location).contents();
            snapshot.put(contents, TexturePixels.copy(contents.byMipLevel[0]));
        }
        // Upload runs on the render thread. Publish only complete, independently owned pixel arrays.
        texturePixels = Map.copyOf(snapshot);
        TEXTURES.clear();
        // The model cache is replaced after the stitch event; enqueue rather than run inline.
        Minecraft.getInstance().tell(() -> MapManager.getInstance().ifPresent(manager -> {
            synchronized (manager.lock) {
                manager.updateAllRegions(false);
            }
        }));
    }

    @Override
    public Color4I getBlockColor(BlockAndTintGetter level, BlockPos pos) {
        // FTB also renders saved regions whose chunks are not loaded in the current client level.
        BlockState state = level == null ? block.defaultBlockState() : level.getBlockState(pos);
        if (!state.is(block)) state = block.defaultBlockState();
        var minecraft = Minecraft.getInstance();
        var fluid = state.getFluidState();
        if (!fluid.isEmpty() && state.getRenderShape() != RenderShape.MODEL) {
            var properties = IClientFluidTypeExtensions.of(fluid);
            var texture = level == null ? properties.getStillTexture() : properties.getStillTexture(fluid, level, pos);
            var sprite = minecraft.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(texture);
            int tint = level == null ? properties.getTintColor() : properties.getTintColor(fluid, level, pos);
            var samples = new Samples();
            samples.add(region(sprite), tint, 1);
            return samples.weight == 0 ? fallback.getBlockColor(level, pos) : samples.color();
        }
        if (state.getRenderShape() != RenderShape.MODEL) return fallback.getBlockColor(level, pos);

        var model = minecraft.getBlockRenderer().getBlockModel(state);
        var data = level == null ? ModelData.EMPTY : model.getModelData(level, pos, state, ModelData.EMPTY);
        long seed = state.getSeed(pos);
        var random = RandomSource.create(seed);
        var samples = new Samples();
        for (RenderType type : model.getRenderTypes(state, random, data)) {
            random.setSeed(seed);
            sampleQuads(samples, model.getQuads(state, Direction.UP, random, data, type), state, level, pos);
            random.setSeed(seed);
            sampleQuads(samples, model.getQuads(state, null, random, data, type), state, level, pos);
        }
        if (samples.weight == 0) {
            int tint = minecraft.getBlockColors().getColor(state, level, pos, 0);
            samples.add(region(model.getParticleIcon(data)), tint, 1);
        }
        return samples.weight == 0 ? fallback.getBlockColor(level, pos) : samples.color();
    }

    private static void sampleQuads(Samples samples, List<BakedQuad> quads, BlockState state,
                                    BlockAndTintGetter level, BlockPos pos) {
        for (var quad : quads) {
            if (quad.getDirection() != Direction.UP) continue;
            int[] vertices = quad.getVertices();
            int stride = vertices.length / 4;
            double area = 0;
            int red = 0, green = 0, blue = 0;
            float minU = Float.POSITIVE_INFINITY, maxU = Float.NEGATIVE_INFINITY;
            float minV = Float.POSITIVE_INFINITY, maxV = Float.NEGATIVE_INFINITY;
            for (int i = 0; i < 4; i++) {
                int a = i * stride, b = (i + 1) % 4 * stride;
                area += Float.intBitsToFloat(vertices[a]) * Float.intBitsToFloat(vertices[b + 2]) -
                        Float.intBitsToFloat(vertices[b]) * Float.intBitsToFloat(vertices[a + 2]);
                int color = vertices[a + 3];
                red += color & 255;
                green += color >> 8 & 255;
                blue += color >> 16 & 255;
                float u = Float.intBitsToFloat(vertices[a + 4]), v = Float.intBitsToFloat(vertices[a + 5]);
                minU = Math.min(minU, u);
                maxU = Math.max(maxU, u);
                minV = Math.min(minV, v);
                maxV = Math.max(maxV, v);
            }
            area = Math.abs(area) * .5;
            if (area == 0) continue;
            var sprite = quad.getSprite();
            var contents = sprite.contents();
            int x0 = Mth.clamp(
                    Mth.floor((minU - sprite.getU0()) / (sprite.getU1() - sprite.getU0()) * contents.width()),
                    0, contents.width() - 1);
            int x1 = Mth.clamp(Mth.ceil((maxU - sprite.getU0()) / (sprite.getU1() - sprite.getU0()) * contents.width()),
                    x0 + 1, contents.width());
            int y0 = Mth.clamp(
                    Mth.floor((minV - sprite.getV0()) / (sprite.getV1() - sprite.getV0()) * contents.height()),
                    0, contents.height() - 1);
            int y1 = Mth.clamp(
                    Mth.ceil((maxV - sprite.getV0()) / (sprite.getV1() - sprite.getV0()) * contents.height()),
                    y0 + 1, contents.height());
            int tint = quad.isTinted() ?
                    Minecraft.getInstance().getBlockColors().getColor(state, level, pos, quad.getTintIndex()) : -1;
            int combinedTint = Math.round((float) (tint >> 16 & 255) * red / 1020) << 16 |
                    Math.round((float) (tint >> 8 & 255) * green / 1020) << 8 |
                    Math.round((float) (tint & 255) * blue / 1020);
            samples.add(new TextureRegion(contents, x0, y0, x1, y1), combinedTint, area);
        }
    }

    private static TextureRegion region(TextureAtlasSprite sprite) {
        var contents = sprite.contents();
        return new TextureRegion(contents, 0, 0, contents.width(), contents.height());
    }

    private record TextureRegion(SpriteContents contents, int minX, int minY, int maxX, int maxY) {

        private TextureColor average() {
            var image = texturePixels.get(contents);
            // During model replacement an old quad can outlive its atlas; use the original map color.
            if (image == null) return new TextureColor(0, 0, 0, 0);
            long red = 0, green = 0, blue = 0, alpha = 0, pixels = 0;
            // Average the animation sheet; model UVs refer to a single frame's region.
            for (int frameY = 0; frameY < image.height; frameY += contents.height()) {
                for (int frameX = 0; frameX < image.width; frameX += contents.width()) {
                    for (int y = minY; y < maxY; y++) {
                        for (int x = minX; x < maxX; x++) {
                            int rgba = image.rgba[(frameY + y) * image.width + frameX + x];
                            int a = rgba >>> 24;
                            // NativeImage exposes ABGR integers, unlike BlockColors' RGB tint.
                            red += (long) (rgba & 255) * a;
                            green += (long) (rgba >> 8 & 255) * a;
                            blue += (long) (rgba >> 16 & 255) * a;
                            alpha += a;
                            pixels++;
                        }
                    }
                }
            }
            return alpha == 0 ? new TextureColor(0, 0, 0, 0) :
                    new TextureColor((double) red / alpha, (double) green / alpha, (double) blue / alpha,
                            (double) alpha / (pixels * 255));
        }
    }

    private record TexturePixels(int width, int height, int[] rgba) {

        private static TexturePixels copy(NativeImage image) {
            int width = image.getWidth(), height = image.getHeight();
            int[] rgba = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    rgba[y * width + x] = image.getPixelRGBA(x, y);
                }
            }
            return new TexturePixels(width, height, rgba);
        }
    }

    private record TextureColor(double red, double green, double blue, double coverage) {}

    private static final class Samples {

        private double red, green, blue, weight;

        private void add(TextureRegion region, int tint, double area) {
            var texture = TEXTURES.computeIfAbsent(region, TextureRegion::average);
            double contribution = texture.coverage * area;
            red += texture.red * (tint >> 16 & 255) / 255 * contribution;
            green += texture.green * (tint >> 8 & 255) / 255 * contribution;
            blue += texture.blue * (tint & 255) / 255 * contribution;
            weight += contribution;
        }

        private Color4I color() {
            return Color4I.rgb((int) Math.round(red / weight), (int) Math.round(green / weight),
                    (int) Math.round(blue / weight));
        }
    }
}
