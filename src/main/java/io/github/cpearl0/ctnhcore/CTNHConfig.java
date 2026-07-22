package io.github.cpearl0.ctnhcore;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

@Config(id = CTNHCore.MODID)
public class CTNHConfig {

    @Key("config.ctnhcore.option.centrifugeRpmRequirement")
    @CN("机械离心厂最低转速需求")
    @EN("Mechanical Centrifuge minimum RPM")
    public static Lang configOptionCentrifugeRpmRequirement;

    @Key("config.ctnhcore.option.centrifugeSpeedMultiplier")
    @CN("机械离心厂加速倍率")
    @EN("Mechanical Centrifuge speed multiplier")
    public static Lang configOptionCentrifugeSpeedMultiplier;

    @Key("config.ctnhcore.option.centrifugeStressRequirement")
    @CN("机械离心厂应力消耗")
    @EN("Mechanical Centrifuge stress consumption")
    public static Lang configOptionCentrifugeStressRequirement;

    @Key("config.ctnhcore.option.enableFTBUltimineOnGTOres")
    @CN("开启GT矿物连锁")
    @EN("Enable GT ore vein mining")
    public static Lang configOptionEnableFtbUltimineOnGtOres;

    @Key("config.ctnhcore.option.extractorRpmRequirement")
    @CN("机械提取厂最低转速需求")
    @EN("Mechanical Extractor minimum RPM")
    public static Lang configOptionExtractorRpmRequirement;

    @Key("config.ctnhcore.option.extractorSpeedMultiplier")
    @CN("机械提取厂加速倍率")
    @EN("Mechanical Extractor speed multiplier")
    public static Lang configOptionExtractorSpeedMultiplier;

    @Key("config.ctnhcore.option.extractorStressRequirement")
    @CN("机械提取厂应力消耗")
    @EN("Mechanical Extractor stress consumption")
    public static Lang configOptionExtractorStressRequirement;

    @Key("config.ctnhcore.option.ftbPlugin")
    @CN("FTB相关")
    @EN("FTB Options")
    public static Lang configOptionFtbPlugin;

    @Key("config.ctnhcore.option.kinetic")
    @CN("应力相关")
    @EN("Kinetic Options")
    public static Lang configOptionKinetic;

    @Key("config.ctnhcore.option.laserRpmRequirement")
    @CN("机械激光厂最低转速需求")
    @EN("Mechanical Laser minimum RPM")
    public static Lang configOptionLaserRpmRequirement;

    @Key("config.ctnhcore.option.laserSpeedMultiplier")
    @CN("机械激光厂加速倍率")
    @EN("Mechanical Laser speed multiplier")
    public static Lang configOptionLaserSpeedMultiplier;

    @Key("config.ctnhcore.option.laserStressRequirement")
    @CN("机械激光厂应力消耗")
    @EN("Mechanical Laser stress consumption")
    public static Lang configOptionLaserStressRequirement;

    @Key("config.ctnhcore.option.latheRpmRequirement")
    @CN("机械车床厂最低转速需求")
    @EN("Mechanical Lathe minimum RPM")
    public static Lang configOptionLatheRpmRequirement;

    @Key("config.ctnhcore.option.latheSpeedMultiplier")
    @CN("机械车床厂加速倍率")
    @EN("Mechanical Lathe speed multiplier")
    public static Lang configOptionLatheSpeedMultiplier;

    @Key("config.ctnhcore.option.latheStressRequirement")
    @CN("机械车床厂应力消耗")
    @EN("Mechanical Lathe stress consumption")
    public static Lang configOptionLatheStressRequirement;

    @Key("config.ctnhcore.option.mixerRpmRequirement")
    @CN("机械搅拌厂最低转速需求")
    @EN("Mechanical Mixer minimum RPM")
    public static Lang configOptionMixerRpmRequirement;

    @Key("config.ctnhcore.option.mixerSpeedMultiplier")
    @CN("机械搅拌厂加速倍率")
    @EN("Mechanical Mixer speed multiplier")
    public static Lang configOptionMixerSpeedMultiplier;

    @Key("config.ctnhcore.option.mixerStressRequirement")
    @CN("机械搅拌厂应力消耗")
    @EN("Mechanical Mixer stress consumption")
    public static Lang configOptionMixerStressRequirement;

    @Key("config.ctnhcore.option.pressorRpmRequirement")
    @CN("机械辊压厂最低转速需求")
    @EN("Mechanical Pressor minimum RPM")
    public static Lang configOptionPressorRpmRequirement;

    @Key("config.ctnhcore.option.pressorSpeedMultiplier")
    @CN("机械辊压厂加速倍率")
    @EN("Mechanical Pressor speed multiplier")
    public static Lang configOptionPressorSpeedMultiplier;

    @Key("config.ctnhcore.option.pressorStressRequirement")
    @CN("机械辊压厂应力消耗")
    @EN("Mechanical Pressor stress consumption")
    public static Lang configOptionPressorStressRequirement;

    @Key("config.ctnhcore.option.sifterRpmRequirement")
    @CN("机械筛选厂最低转速需求")
    @EN("Mechanical Sifter minimum RPM")
    public static Lang configOptionSifterRpmRequirement;

    @Key("config.ctnhcore.option.sifterSpeedMultiplier")
    @CN("机械筛选厂加速倍率")
    @EN("Mechanical Sifter speed multiplier")
    public static Lang configOptionSifterSpeedMultiplier;

    @Key("config.ctnhcore.option.sifterStressRequirement")
    @CN("机械筛选厂应力消耗")
    @EN("Mechanical Sifter stress consumption")
    public static Lang configOptionSifterStressRequirement;

    public static CTNHConfig INSTANCE;
    private static final Object LOCK = new Object();

    public static void init() {
        synchronized (LOCK) {
            if (INSTANCE == null) {
                INSTANCE = Configuration.registerConfig(CTNHConfig.class, ConfigFormats.yaml()).getConfigInstance();
            }
        }
    }

    @Configurable
    @Configurable.Comment("Migration Setting")
    public Migration migration = new Migration();

    public static class Migration {

        @Configurable
        @Configurable.Comment({
                "Whether enable migration function, which might prevent blocks from disappearing when migrating saves from old versions.  " +
                        "NOTICE: Turn it off after migration, or will slow down the loading",
                "Default: false" })
        public boolean migrationMode = false;
    }

    @Configurable
    @Configurable.Comment("Optimization Setting")
    public Optimization optimization = new Optimization();

    public static class Optimization {

        @Configurable
        @Configurable.Comment({
                "At what interval will nature spawner try to spawn creatures? Vanilla is 1",
                "Default: 20" })
        @Configurable.Range(min = 1, max = 200)
        public int natureSpawnCycle = 20;
    }

    @Configurable
    @Configurable.Comment("Terminal AutoBuild")
    public Terminal terminal = new Terminal();

    public static class Terminal {

        @Configurable
        @Configurable.Comment({ "How many blocks GT-MBST-A will put per tick when AE storage is not available",
                "Default: 64" })
        @Configurable.Range(min = 8, max = 256)
        public int blocksPerTick = 64;

        @Configurable
        @Configurable.Comment({ "How many blocks GT-MBST-A will put per tick when AE storage is available",
                "Default: 8" })
        @Configurable.Range(min = 1, max = 64)
        public int blocksPerTickWithAE = 8;
    }

    @Configurable
    @Configurable.Comment("FTB's plugins")
    public FTBPlugin ftbPlugin = new FTBPlugin();
    @Configurable
    @Configurable.Comment("Mechanical Machine's Buffer")
    public Kinetic kinetic = new Kinetic();

    public static class FTBPlugin {

        @Configurable
        @Configurable.Comment({ "Enable FTBUltimine on GregTech Ores", "Default: false" })
        public boolean enableFTBUltimineOnGTOres = false;
    }

    public static class Kinetic {

        @Configurable
        @Configurable.Comment({ "The rpm requirement for mechanical pressor machines", "Default: 64" })
        @Configurable.Range(min = 16, max = 256)
        public int pressorRpmRequirement = 64;
        @Configurable
        @Configurable.Comment({ "Mechanical Pressor's Speed Multiplier relative to its voltage level", "Default: 2" })
        @Configurable.DecimalRange(min = 0.5, max = 4.0)
        public float pressorSpeedMultiplier = 2;
        @Configurable
        @Configurable.Comment({
                "Mechanical Pressor's stress requirement(This value will be multiplied by its basic EUt cost)",
                "Default: 512" })
        @Configurable.DecimalRange(min = 1.0, max = 1024.0)
        public float pressorStressRequirement = 512;
        @Configurable
        @Configurable.Comment({ "The rpm requirement for mechanical mixer machines", "Default: 64" })
        @Configurable.Range(min = 16, max = 256)
        public int mixerRpmRequirement = 64;
        @Configurable
        @Configurable.Comment({ "Mechanical Mixer's Speed Multiplier relative to its voltage level", "Default: 2" })
        @Configurable.DecimalRange(min = 0.5, max = 4.0)
        public float mixerSpeedMultiplier = 2;
        @Configurable
        @Configurable.Comment({
                "Mechanical Mixer's stress requirement(This value will be multiplied by its basic EUt cost)",
                "Default: 512" })
        @Configurable.DecimalRange(min = 1.0, max = 1024.0)
        public float mixerStressRequirement = 512;
        @Configurable
        @Configurable.Comment({ "The rpm requirement for mechanical centrifuge machines", "Default: 64" })
        @Configurable.Range(min = 16, max = 256)
        public int centrifugeRpmRequirement = 64;
        @Configurable
        @Configurable.Comment({ "Mechanical Centrifuge's Speed Multiplier relative to its voltage level",
                "Default: 2" })
        @Configurable.DecimalRange(min = 0.5, max = 4.0)
        public float centrifugeSpeedMultiplier = 2;
        @Configurable
        @Configurable.Comment({
                "Mechanical Centrifuge's stress requirement(This value will be multiplied by its basic EUt cost)",
                "Default: 512" })
        @Configurable.DecimalRange(min = 1.0, max = 1024.0)
        public float centrifugeStressRequirement = 512;
        @Configurable
        @Configurable.Comment({ "The rpm requirement for mechanical sifter machines", "Default: 128" })
        @Configurable.Range(min = 16, max = 256)
        public int sifterRpmRequirement = 128;
        @Configurable
        @Configurable.Comment({ "Mechanical Sifter's Speed Multiplier relative to its voltage level", "Default: 2" })
        @Configurable.DecimalRange(min = 0.5, max = 4.0)
        public float sifterSpeedMultiplier = 2;
        @Configurable
        @Configurable.Comment({
                "Mechanical Sifter's stress requirement(This value will be multiplied by its basic EUt cost)",
                "Default: 512" })
        @Configurable.DecimalRange(min = 1.0, max = 1024.0)
        public float sifterStressRequirement = 512;
        @Configurable
        @Configurable.Comment({ "The rpm requirement for mechanical lathe machines", "Default: 128" })
        @Configurable.Range(min = 16, max = 256)
        public int latheRpmRequirement = 128;
        @Configurable
        @Configurable.Comment({ "Mechanical Lathe's Speed Multiplier relative to its voltage level", "Default: 2" })
        @Configurable.DecimalRange(min = 0.5, max = 4.0)
        public float latheSpeedMultiplier = 2;
        @Configurable
        @Configurable.Comment({
                "Mechanical Lathe's stress requirement(This value will be multiplied by its basic EUt cost)",
                "Default: 512" })
        @Configurable.DecimalRange(min = 1.0, max = 1024.0)
        public float latheStressRequirement = 512;
        @Configurable
        @Configurable.Comment({ "The rpm requirement for mechanical laser machines", "Default: 128" })
        @Configurable.Range(min = 16, max = 256)
        public int laserRpmRequirement = 128;
        @Configurable
        @Configurable.Comment({ "Mechanical Laser's Speed Multiplier relative to its voltage level", "Default: 2" })
        @Configurable.DecimalRange(min = 0.5, max = 4.0)
        public float laserSpeedMultiplier = 2;
        @Configurable
        @Configurable.Comment({
                "Mechanical Laser's stress requirement(This value will be multiplied by its basic EUt cost)",
                "Default: 512" })
        @Configurable.DecimalRange(min = 1.0, max = 1024.0)
        public float laserStressRequirement = 512;
    }
}
