package io.github.cpearl0.ctnhcore.registry;

import io.github.cpearl0.ctnhcore.registry.ores.MoonOres;

import com.gregtechceu.gtceu.api.data.chemical.material.ItemMaterialData;
import com.gregtechceu.gtceu.api.data.worldgen.IWorldGenLayer;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGeneratorUtils;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static io.github.cpearl0.ctnhcore.registry.CTNHWorlds.*;

public enum CTNHWorldgenLayers implements IWorldGenLayer, StringRepresentable {

    MOON("ctnh_moon", new TagMatchTest(CTNHTags.MOON_ORE_REPLACEABLES), Set.of(CTNHWorlds.MOON)),

    ADASTRA("ad_astra", new TagMatchTest(CTNHTags.AD_ASTRA_STONES), Set.of(MARS, MERCURY, VENUS, GLACIO)),

    TWILIGHT("twilight_forest", new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), Set.of(TWILIGHT_FOREST)),

    AETHER("aether", new TagMatchTest(CTNHTags.AETHER_STONES), Set.of(THE_AETHER)),

    ALFHEIM("alfheim", new TagMatchTest(CTNHTags.ALFHEIM_STONES), Set.of(CTNHWorlds.ALFHEIM));

    public static void init() {
        // GT resolves material layers through the host state's ore prefix after testing the replacement tag.
        ItemMaterialData.ORES_INVERSE.putAll(MoonOres.oreHosts());
    }

    CTNHWorldgenLayers(String name, RuleTest target, Set<ResourceLocation> levels) {
        this.name = name;
        this.target = target;
        this.levels = levels;
        WorldGeneratorUtils.WORLD_GEN_LAYERS.put(name, this);
    }

    private final String name;
    @Getter
    private final Set<ResourceLocation> levels;
    @Getter
    private final RuleTest target;

    @Override
    public boolean isApplicableForLevel(ResourceLocation level) {
        return levels.contains(level);
    }

    @Override
    @NotNull
    public String getSerializedName() {
        return name;
    }
}
