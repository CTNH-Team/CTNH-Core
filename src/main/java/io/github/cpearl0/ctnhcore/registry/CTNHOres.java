package io.github.cpearl0.ctnhcore.registry;

import io.github.cpearl0.ctnhcore.registry.ores.AdAstraOres;
import io.github.cpearl0.ctnhcore.registry.ores.AetherOres;
import io.github.cpearl0.ctnhcore.registry.ores.AlfheimOres;
import io.github.cpearl0.ctnhcore.registry.ores.EndOres;
import io.github.cpearl0.ctnhcore.registry.ores.NetherOres;
import io.github.cpearl0.ctnhcore.registry.ores.OverworldOres;
import io.github.cpearl0.ctnhcore.registry.ores.TwilightForestOres;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

public class CTNHOres {

    // ==================== 未匹配到矿脉的翻译键 ====================

    @Key("cassiterite_vein_tf")
    @CN("锡石矿脉")
    @EN("Twilight Forest Cassiterite Vein")
    public static Lang cassiteriteVeinTf;

    @Key("coal_vein_tf")
    @CN("煤矿脉")
    @EN("Twilight Forest Coal Vein")
    public static Lang coalVeinTf;

    @Key("ctnhcore:iron_vein_tf")
    @CN("暮色森林铁矿脉")
    @EN("Twilight Forest Iron Vein")
    public static Lang ctnhIronVeinTf;

    @Key("ctnhcore:lapis_ore_vein")
    @CN("水星青金石矿脉")
    @EN("Mercury Lapis Lazuli Vein")
    public static Lang ctnhLapisOreVein;

    @Key("ctnhcore:moon_seawater_vein")
    @CN("静海矿藏")
    @EN("Moon Sea Water Deposit")
    public static Lang ctnhMoonSeawaterVein;

    @Key("ctnhcore:neutronium_vein_ad")
    @CN("霜原星中子素矿脉")
    @EN("Glacio Neutronium Vein")
    public static Lang ctnhNeutroniumVeinAd;

    @Key("ctnhcore:seawater_vein_overworld")
    @CN("海水矿藏")
    @EN("Seawater Deposit")
    public static Lang ctnhSeawaterVeinOverworld;

    @Key("ctnhcore:zircon_vein_aether")
    @CN("天境锆石矿脉")
    @EN("Aether Zircon Vein")
    public static Lang ctnhZirconVeinAether;

    @Key("gold_vein_tf")
    @CN("金矿脉")
    @EN("Twilight Forest Gold Vein")
    public static Lang goldVeinTf;

    @Key("gtceu.jei.ore_vein.gold_vein_tf")
    @CN("暮色森林磁铁矿脉")
    @EN("Twilight Forest Magnetite Vein")
    public static Lang gtceuGoldVeinTf;

    @Key("gtceu.jei.ore_vein.iron_vein_tf")
    @CN("暮色森林铁矿脉")
    @EN("Twilight Forest Iron Vein")
    public static Lang gtceuIronVeinTf;

    @Key("gtceu.jei.ore_vein.lapis_ore_vein")
    @CN("水星青金石矿脉")
    @EN("Mercury Lapis Lazuli Vein")
    public static Lang gtceuLapisOreVein;

    @Key("gtceu.jei.ore_vein.neutronium_vein_ad")
    @CN("霜原星中子素矿脉")
    @EN("Glacio Neutronium Vein")
    public static Lang gtceuNeutroniumVeinAd;

    @Key("gtceu.jei.ore_vein.zircon_vein_aether")
    @CN("天境锆石矿脉")
    @EN("Aether Zircon Vein")
    public static Lang gtceuZirconVeinAether;

    @Key("iron_vein_tf")
    @CN("铁矿脉")
    @EN("Twilight Forest Iron Vein")
    public static Lang ironVeinTf;

    @Key("lapis_vein_tf")
    @CN("青金石矿脉")
    @EN("Twilight Forest Lapis Lazuli Vein")
    public static Lang lapisVeinTf;

    @Key("lubricant_vein_tf")
    @CN("皂石矿脉")
    @EN("Twilight Forest Soapstone Vein")
    public static Lang lubricantVeinTf;

    @Key("molybdenite_vein_tf")
    @CN("辉钼矿脉")
    @EN("Twilight Forest Molybdenite Vein")
    public static Lang molybdeniteVeinTf;

    @Key("neutronium_vein_ad")
    @CN("中子素矿脉")
    @EN("Ad Astra Neutronium Vein")
    public static Lang neutroniumVeinAd;

    @Key("nickel_vein_tf")
    @CN("镍矿脉")
    @EN("Twilight Forest Nickel Vein")
    public static Lang nickelVeinTf;

    @Key("olivine_vein_tf")
    @CN("橄榄石矿脉")
    @EN("Twilight Forest Olivine Vein")
    public static Lang olivineVeinTf;

    @Key("sapphire_vein_tf")
    @CN("蓝宝石矿脉")
    @EN("Twilight Forest Sapphire Vein")
    public static Lang sapphireVeinTf;

    public static void init() {
        OverworldOres.init();
        NetherOres.init();
        EndOres.init();
        TwilightForestOres.init();
        AetherOres.init();
        AlfheimOres.init();
        AdAstraOres.init();
    }

    // ==================== 被注释掉的矿脉 ====================
    //
    // Twilight Forest
    // GTNNOres.INSTANCE.getGOLD_VEIN_TF().layeredVeinGenerator(generator -> generator
    // .buildLayerPattern(pattern -> pattern
    // .layer(l -> l.weight(3).mat(GTMaterials.Magnetite).size(2, 4))
    // .layer(l -> l.weight(2).mat(GTMaterials.VanadiumMagnetite).size(1, 1))
    // .layer(l -> l.weight(1).mat(CTNHMaterials.PreciousAlloy).size(1, 1))
    // )
    // );
    //
    // Ad Astra
    // GTNNOres.INSTANCE.getARSENIC_VEIN_AD().layeredVeinGenerator(generator -> generator
    // .buildLayerPattern(pattern -> pattern
    // .layer(l -> l.weight(1).mat(CTNHMaterials.Sperrylite).size(1, 2))
    // .layer(l -> l.weight(1).mat(CTNHMaterials.Bismuthinite).size(1, 2))
    // .layer(l -> l.weight(1).mat(GTMaterials.Stibnite).size(1, 2))
    // )
    // );
    // GTNNOres.INSTANCE.getIRIDIUM_VEIN_AD().layeredVeinGenerator(generator -> generator
    // .buildLayerPattern(pattern -> pattern
    // .layer(l -> l.weight(1).mat(GTMaterials.Nickel).size(2, 3))
    // .layer(l -> l.weight(1).mat(CTNHMaterials.MeteoricTroilite).size(1, 2))
    // .layer(l -> l.weight(1).mat(CTNHMaterials.PalladiumSulfide).size(1, 2))
    // )
    // );
    // GTNNOres.INSTANCE.getCHROMIUM_VEIN_AD().layeredVeinGenerator(generator -> generator
    // .buildLayerPattern(pattern -> pattern
    // .layer(l -> l.weight(1).mat(GTMaterials.Chromite).size(2, 3))
    // .layer(l -> l.weight(1).mat(CTNHMaterials.Wolframite).size(1, 2))
    // .layer(l -> l.weight(1).mat(CTNHMaterials.Tarkianite).size(1, 2))
    // .layer(l -> l.weight(1).mat(GTMaterials.Pyrolusite).size(1, 2))
    // )
    // );
    // GTNNOres.INSTANCE.getNIOBIUM_VEIN_AD().layeredVeinGenerator(generator -> generator
    // .buildLayerPattern(pattern -> pattern
    // .layer(l -> l.weight(1).mat(GTMaterials.Niobium).size(2, 3))
    // .layer(l -> l.weight(1).mat(CTNHMaterials.MeteoricTroilite).size(1, 2))
    // .layer(l -> l.weight(1).mat(CTNHMaterials.Germanite).size(1, 2))
    // )
    // );
    // GTNNOres.INSTANCE.getOSMIUM_VEIN_AD().layeredVeinGenerator(generator -> generator
    // .buildLayerPattern(pattern -> pattern
    // .layer(l -> l.weight(1).mat(GTMaterials.Nickel).size(2, 3))
    // .layer(l -> l.weight(1).mat(CTNHMaterials.OsmiumIronSpinel).size(1, 1))
    // .layer(l -> l.weight(1).mat(CTNHMaterials.MeteoricTroilite).size(1, 1))
    // .layer(l -> l.weight(1).mat(CTNHMaterials.Crocoite).size(2, 3))
    // )
    // );
    // GTNNOres.INSTANCE.getNEUTRONIUM_VEIN_AD().layeredVeinGenerator(generator -> generator
    // .buildLayerPattern(pattern -> pattern
    // .layer(l -> l.weight(1).mat(GTMaterials.Neutronium).size(2, 3))
    // .layer(l -> l.weight(1).mat(CTNHMaterials.OsmiumIronSpinel).size(1, 1))
    // .layer(l -> l.weight(1).mat(GTMaterials.Naquadria).size(1, 1))
    // .layer(l -> l.weight(1).mat(CTNHMaterials.Rheniite).size(1, 1))
    // )
    // );
    //
    // Glacio
    // public static final GTOreDefinition NEUTRONIUM_VEIN_AD = create(
    // CTNHCore.id("neutronium_vein_ad"), vein -> {
    // vein.clusterSize(24).weight(10).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(GLACIO)
    // .heightRangeUniform(-50, -10).discardChanceOnAirExposure(0f)
    // .layeredVeinGenerator(generator -> {
    // generator.buildLayerPattern(pattern -> {
    // pattern.layer(l -> l.weight(2).mat(GTMaterials.Neutronium).size(1, 2))
    // .layer(l -> l.weight(1).mat(InfinityCatalyst).size(1, 2))
    // .layer(l -> l.weight(1).mat(GTMaterials.Naquadria).size(1, 2))
    // .layer(l -> l.weight(1).mat(GTMaterials.Titanium).size(1, 2));
    // });
    // });
    // }
    // );
}
