package io.github.cpearl0.ctnhcore.registry;

import com.gregtechceu.gtceu.api.data.DimensionMarker;
import net.minecraft.resources.ResourceLocation;

import static com.gregtechceu.gtceu.common.data.GTDimensionMarkers.createAndRegister;

public class CTNHDimensionMarkers {
    public static final DimensionMarker MOON = createAndRegister(ResourceLocation.tryParse("ad_astra:moon"),
            1, ResourceLocation.tryParse("ad_astra:moon_stone"), "dimension.ad_astra:moon");
    public static final DimensionMarker MARS = createAndRegister(ResourceLocation.tryParse("ad_astra:mars"),
            2, ResourceLocation.tryParse("ad_astra:mars_stone"), "dimension.ad_astra:mars");
    public static final DimensionMarker VENUS = createAndRegister(ResourceLocation.tryParse("ad_astra:venus"),
            3, ResourceLocation.tryParse("ad_astra:venus_stone"), "dimension.ad_astra:venus");
    public static final DimensionMarker MERCURY = createAndRegister(ResourceLocation.tryParse("ad_astra:mercury"),
            3, ResourceLocation.tryParse("ad_astra:mercury_stone"), "dimension.ad_astra:mercury");
    public static final DimensionMarker GLACIO = createAndRegister(ResourceLocation.tryParse("ad_astra:glacio"),
            7, ResourceLocation.tryParse("ad_astra:glacio_stone"), "dimension.ad_astra:glacio");
    public static final DimensionMarker JUPITER = createAndRegister(ResourceLocation.tryParse("ad_extendra:jupiter"),
            7, ResourceLocation.tryParse("ad_extendra:jupiter_stone"), "dimension.ad_extendra:jupiter");
    public static final DimensionMarker AETHER = createAndRegister(ResourceLocation.tryParse("aether:the_aether"),
            2, ResourceLocation.tryParse("aether:holystone"), "dimension.aether:the_aether");
    public static final DimensionMarker TWILIGHTFOREST = createAndRegister(ResourceLocation.tryParse("twilightforest:twilight_forest"),
            7, ResourceLocation.tryParse("minecraft:oak_leaves"), "dimension.twilightforest:twilight_forest");
    public static final DimensionMarker ALFHEIM = createAndRegister(ResourceLocation.tryParse("mythicbotany:alfheim"),
            3, ResourceLocation.tryParse("botania:livingrock"), "dimension.mythicbotany:alfheim");

    public static void init() {

    }
}
