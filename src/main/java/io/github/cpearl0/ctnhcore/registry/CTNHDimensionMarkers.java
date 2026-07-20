package io.github.cpearl0.ctnhcore.registry;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import com.ctnhlang.CN;
import com.ctnhlang.EN;

import com.gregtechceu.gtceu.api.data.DimensionMarker;

import net.minecraft.resources.ResourceLocation;

import static com.gregtechceu.gtceu.common.data.GTDimensionMarkers.createAndRegister;

public class CTNHDimensionMarkers {

    @CN("霜原星")
    @EN("Glacio")
    public static Lang dimensionAdAstraGlacio;


    @CN("火星")
    @EN("Mars")
    public static Lang dimensionAdAstraMars;


    @CN("水星")
    @EN("Mercury")
    public static Lang dimensionAdAstraMercury;


    @CN("月球")
    @EN("Moon")
    public static Lang dimensionAdAstraMoon;


    @CN("金星")
    @EN("Venus")
    public static Lang dimensionAdAstraVenus;


    @CN("木星")
    @EN("Jupiter")
    public static Lang dimensionAdExtendraJupiter;


    @CN("天境")
    @EN("Aether")
    public static Lang dimensionAetherTheAether;


    @CN("暮色森林")
    @EN("Twilight Forest")
    public static Lang dimensionTwilightforestTwilightForest;



    public static final DimensionMarker MOON = createAndRegister(ResourceLocation.tryParse("ad_astra:moon"),
            1, ResourceLocation.tryParse("ad_astra:moon_stone"), dimensionAdAstraMoon.key());
    public static final DimensionMarker MARS = createAndRegister(ResourceLocation.tryParse("ad_astra:mars"),
            2, ResourceLocation.tryParse("ad_astra:mars_stone"), dimensionAdAstraMars.key());
    public static final DimensionMarker VENUS = createAndRegister(ResourceLocation.tryParse("ad_astra:venus"),
            3, ResourceLocation.tryParse("ad_astra:venus_stone"), dimensionAdAstraVenus.key());
    public static final DimensionMarker MERCURY = createAndRegister(ResourceLocation.tryParse("ad_astra:mercury"),
            3, ResourceLocation.tryParse("ad_astra:mercury_stone"), dimensionAdAstraMercury.key());
    public static final DimensionMarker GLACIO = createAndRegister(ResourceLocation.tryParse("ad_astra:glacio"),
            7, ResourceLocation.tryParse("ad_astra:glacio_stone"), dimensionAdAstraGlacio.key());
    public static final DimensionMarker JUPITER = createAndRegister(ResourceLocation.tryParse("ad_extendra:jupiter"),
            7, ResourceLocation.tryParse("ad_extendra:jupiter_stone"), dimensionAdExtendraJupiter.key());
    public static final DimensionMarker AETHER = createAndRegister(ResourceLocation.tryParse("aether:the_aether"),
            2, ResourceLocation.tryParse("aether:holystone"), dimensionAetherTheAether.key());
    public static final DimensionMarker TWILIGHTFOREST = createAndRegister(
            ResourceLocation.tryParse("twilightforest:twilight_forest"),
            7, ResourceLocation.tryParse("minecraft:oak_leaves"), dimensionTwilightforestTwilightForest.key());
    // public static final DimensionMarker ALFHEIM =
    // createAndRegister(ResourceLocation.tryParse("mythicbotany:alfheim"),
    // 3, ResourceLocation.tryParse("botania:livingrock"), "dimension.mythicbotany:alfheim");

    public static void init() {}
}
