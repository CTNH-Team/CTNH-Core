package io.github.cpearl0.ctnhcore.registry.worldgen;

import com.gregtechceu.gtceu.common.data.GTModels;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.entry.BlockEntry;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.block.AstralFlowerBlock;
import io.github.cpearl0.ctnhcore.common.block.AstralGrassBlock;
import io.github.cpearl0.ctnhcore.common.block.AstralSaplingBlock;
import io.github.cpearl0.ctnhcore.common.block.AstralTallGrassBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;

import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class AstralBlocks {
    public static void init() {}
    public static final BlockEntry<RotatedPillarBlock> ASTRAL_LOG = createLogLikeBlock("astral_log");
    public static final BlockEntry<RotatedPillarBlock> PEPPER_CRATE = createLogLikeBlock("pepper_crate");
    public static final BlockEntry<RotatedPillarBlock> GARLIC_CRATE = createLogLikeBlock("garlic_crate");
    public static final BlockEntry<RotatedPillarBlock> CASSAVA_CRATE = createLogLikeBlock("cassava_crate");
    public static final BlockEntry<RotatedPillarBlock> FRUIT_CAFE_CRATE = createLogLikeBlock("fruit_cafe_crate");
    public static final BlockEntry<RotatedPillarBlock> ASPARAGUS_CRATE = createLogLikeBlock("asparagus_crate");
    public static final BlockEntry<Block> ASTRAL_COBBLESTONE = createStoneLikeBlock("astral_cobblestone", CTNHCore.id("block/stones/astral_cobblestone"));
    public static BlockEntry<Block> ASTRAL_STONE;
    public static final BlockEntry<FallingBlock> ASTRAL_SAND = createSandLikeBlock("astral_sand", CTNHCore.id("block/sands/astral_sand"));
    @SuppressWarnings("removal")
    public static BlockEntry<Block> ASTRAL_DIRT;
    @SuppressWarnings("removal")
    public static final BlockEntry<AstralSaplingBlock> ASTRAL_SAPLING = REGISTRATE
            .block("astral_sapling", properties -> new AstralSaplingBlock(new AbstractTreeGrower() {

                protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource random,
                                                                                    boolean largeHive) {
                    return CTNHConfiguredFeatures.ASTRAL_TREE;
                }
            }, properties))
            .initialProperties(() -> Blocks.OAK_SAPLING)
            .lang("Astral Sapling")
            .blockstate(GTModels::createCrossBlockState)
            .addLayer(() -> RenderType::cutoutMipped)
            .tag(BlockTags.SAPLINGS)
            .item()
            .model(GTModels::rubberTreeSaplingModel)
            .tag(ItemTags.SAPLINGS)
            .build()
            .register();
    public static BlockEntry<GrassBlock> ASTRAL_GRASS_BLOCK;
    public static BlockEntry<AstralFlowerBlock> BLUE_FLOWER = createFlowerBlock("blue_flower", MobEffects.ABSORPTION);
    public static BlockEntry<AstralFlowerBlock> PINK_FLOWER = createFlowerBlock("pink_flower", MobEffects.DAMAGE_BOOST);
    public static BlockEntry<AstralGrassBlock> ASTRAL_GRASS = createTallGrassBlock("astral_grass");
    public static BlockEntry<AstralTallGrassBlock> ASTRAL_TALL_GRASS = createDoublePlantBlock("astral_tall_grass");

    @SuppressWarnings("removal")
    public static BlockEntry<Block> createStoneLikeBlock(String name, ResourceLocation texture) {
        var builder = REGISTRATE.block(name, Block::new)
                .initialProperties(() -> Blocks.STONE)
                .blockstate((ctx, prov) -> {
                    prov.simpleBlock(ctx.getEntry(), prov.models().cubeAll(name, texture));
                })
                .loot(RegistrateBlockLootTables::dropSelf)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false)).addLayer(() -> RenderType::cutoutMipped)
                .tag(BlockTags.MINEABLE_WITH_PICKAXE);
        return builder.item(BlockItem::new)
                .build()
                .register();
    }
    @SuppressWarnings("removal")
    private static BlockEntry<RotatedPillarBlock> createLogLikeBlock(String name) {
        return REGISTRATE.block(name, RotatedPillarBlock::new)
                .initialProperties(() -> Blocks.OAK_WOOD)
                .addLayer(() -> RenderType::cutoutMipped)
                .blockstate((ctx,prov)->prov.logBlock(ctx.getEntry()))
                .tag(BlockTags.MINEABLE_WITH_AXE)
                .item(BlockItem::new)
                .build()
                .register();
    }
    @SuppressWarnings("removal")
    public static BlockEntry<FallingBlock> createSandLikeBlock(String name, ResourceLocation texture) {
        return REGISTRATE.block(name, FallingBlock::new)
                .initialProperties(() -> Blocks.SAND)
                .blockstate((ctx, prov) -> {
                    prov.simpleBlock(ctx.getEntry(), prov.models().cubeAll(name, texture));
                })
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false)).addLayer(() -> RenderType::cutoutMipped)
                .tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .item(BlockItem::new)
                .build()
                .register();
    }
    @SuppressWarnings("removal")
    public static BlockEntry<AstralFlowerBlock> createFlowerBlock(String name, MobEffect effect) {
        return REGISTRATE.block(name, (properties) -> new AstralFlowerBlock(() -> effect, 5, properties))
                .properties(p -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY))
                .blockstate(GTModels::createCrossBlockState)
                .addLayer(() -> RenderType::cutoutMipped)
                .item(BlockItem::new)
                .model(GTModels::rubberTreeSaplingModel)
                .build()
                .register();
    }
    @SuppressWarnings("removal")
    public static BlockEntry<AstralGrassBlock> createTallGrassBlock(String name) {
        return REGISTRATE.block(name, AstralGrassBlock::new)
                .initialProperties(() -> Blocks.GRASS)
                .blockstate(GTModels::createCrossBlockState)
                .addLayer(() -> RenderType::cutoutMipped)
                .item(BlockItem::new)
                .model(GTModels::rubberTreeSaplingModel)
                .build()
                .register();
    }
    @SuppressWarnings("removal")
    public static BlockEntry<AstralTallGrassBlock> createDoublePlantBlock(String name) {
        return REGISTRATE.block(name, AstralTallGrassBlock::new)
                .initialProperties(() -> Blocks.TALL_GRASS)
                .blockstate(GTModels::createCrossBlockState)
                .addLayer(() -> RenderType::cutoutMipped)
                .item(BlockItem::new)
                .model(GTModels::rubberTreeSaplingModel)
                .build()
                .register();
    }
}
