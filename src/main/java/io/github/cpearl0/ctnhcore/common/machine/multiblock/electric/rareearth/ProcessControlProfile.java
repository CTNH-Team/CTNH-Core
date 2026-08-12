package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.rareearth;

import com.gregtechceu.gtceu.api.recipe.GTRecipe;

import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Prefix;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

@Prefix("info.multiblock.process_control")
public enum ProcessControlProfile {

    CONDENSING_DISCRETE(new Parameter(5, 50, 3), new Parameter(20, 80, 4)),
    OXIDATION_ROASTING(new Parameter(95, 125, 2), new Parameter(2, 12, 1)),
    HIGH_PRESSURE_ALKALI_DIGESTION(new Parameter(1200, 2400, 100), new Parameter(20, 50, 2)),
    SOLVENT_EXTRACTION(new Parameter(200, 700, 20), new Parameter(50, 200, 8)),
    REDUCTION_PRECIPITATION(new Parameter(-700, 200, 30), new Parameter(100, 700, 20)),
    ION_EXCHANGE(new Parameter(5, 40, 2), new Parameter(50, 400, 15)),
    VACUUM_SINTERING(new Parameter(5, 500, 25), new Parameter(5, 80, 4)),
    CRYSTALLIZATION(new Parameter(5, 50, 3), new Parameter(105, 140, 2));

    public static final String PRIMARY_RECIPE_DATA = "ctnh_process_primary";
    public static final String SECONDARY_RECIPE_DATA = "ctnh_process_secondary";

    @CN("工艺校准")
    @EN("Process Calibration")
    static Lang calibrationTitle;

    @CN("参数 A")
    @EN("Control A")
    static Lang primaryInput;

    @CN("参数 B")
    @EN("Control B")
    static Lang secondaryInput;

    @CN("运行时锁定")
    @EN("Locked while running")
    static Lang lockedWhileRunning;

    @CN("等待输入以识别配方目标")
    @EN("Waiting for inputs to identify recipe targets")
    static Lang waitingForRecipe;

    @CN("工艺: %s")
    @EN("Process: %s")
    static Lang processLine;

    @CN("%s: %s（目标 %s ~ %s）")
    @EN("%s: %s (target %s - %s)")
    static Lang parameterLine;

    @CN("校准完成，配方可以启动")
    @EN("Calibration complete; recipe can start")
    static Lang calibrated;

    @CN("调整两个参数，使其进入绿色窗口")
    @EN("Tune both controls into their green windows")
    static Lang uncalibrated;

    @CN("工艺参数未进入目标窗口")
    @EN("Process controls are outside their target windows")
    static Lang calibrationRequired;

    @CN("冷凝离散")
    @EN("Fractional Condensing")
    static Lang condensingName;

    @CN("回流比 (×10)")
    @EN("Reflux Ratio (x10)")
    static Lang condensingPrimary;

    @CN("中段冷量 (%)")
    @EN("Middle Cooling Share (%)")
    static Lang condensingSecondary;

    @CN("氧化焙烧")
    @EN("Oxidation Roasting")
    static Lang roastingName;

    @CN("过量氧系数 (%)")
    @EN("Excess Oxygen (%)")
    static Lang roastingPrimary;

    @CN("滚筒转速 (rpm)")
    @EN("Drum Speed (rpm)")
    static Lang roastingSecondary;

    @CN("高压碱煮")
    @EN("High-Pressure Alkali Digestion")
    static Lang digestionName;

    @CN("釜压 (kPa)")
    @EN("Vessel Pressure (kPa)")
    static Lang digestionPrimary;

    @CN("碱液浓度 (%)")
    @EN("Caustic Concentration (%)")
    static Lang digestionSecondary;

    @CN("溶剂萃取")
    @EN("Solvent Extraction")
    static Lang extractionName;

    @CN("水相 pH (×100)")
    @EN("Aqueous pH (x100)")
    static Lang extractionPrimary;

    @CN("O/A 比 (×100)")
    @EN("O/A Ratio (x100)")
    static Lang extractionSecondary;

    @CN("还原沉淀")
    @EN("Reduction Precipitation")
    static Lang precipitationName;

    @CN("氧化还原电位 (mV)")
    @EN("Redox Potential (mV)")
    static Lang precipitationPrimary;

    @CN("槽液 pH (×100)")
    @EN("Tank pH (x100)")
    static Lang precipitationSecondary;

    @CN("离子交换")
    @EN("Ion Exchange")
    static Lang ionExchangeName;

    @CN("柱流量 (×10 BV/h)")
    @EN("Column Flow (x10 BV/h)")
    static Lang ionExchangePrimary;

    @CN("洗脱液 pH (×100)")
    @EN("Eluent pH (x100)")
    static Lang ionExchangeSecondary;

    @CN("真空烧结")
    @EN("Vacuum Sintering")
    static Lang vacuumSinteringName;

    @CN("真空压力 (Pa)")
    @EN("Vacuum Pressure (Pa)")
    static Lang vacuumSinteringPrimary;

    @CN("升温速率 (K/min)")
    @EN("Heating Ramp (K/min)")
    static Lang vacuumSinteringSecondary;

    @CN("结晶生长")
    @EN("Crystal Growth")
    static Lang crystallizationName;

    @CN("冷却速率 (×10 K/min)")
    @EN("Cooling Rate (x10 K/min)")
    static Lang crystallizationPrimary;

    @CN("过饱和度 (%)")
    @EN("Supersaturation (%)")
    static Lang crystallizationSecondary;

    private final Parameter primary;
    private final Parameter secondary;

    ProcessControlProfile(Parameter primary, Parameter secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    public Parameter primary() {
        return primary;
    }

    public Parameter secondary() {
        return secondary;
    }

    public int resolvePrimaryTarget(GTRecipe recipe) {
        return resolveTarget(recipe, PRIMARY_RECIPE_DATA, primary, 0x41C64E6D);
    }

    public int resolveSecondaryTarget(GTRecipe recipe) {
        return resolveTarget(recipe, SECONDARY_RECIPE_DATA, secondary, 0x9E3779B9);
    }

    public Component displayName() {
        return switch (this) {
            case CONDENSING_DISCRETE -> condensingName.translate();
            case OXIDATION_ROASTING -> roastingName.translate();
            case HIGH_PRESSURE_ALKALI_DIGESTION -> digestionName.translate();
            case SOLVENT_EXTRACTION -> extractionName.translate();
            case REDUCTION_PRECIPITATION -> precipitationName.translate();
            case ION_EXCHANGE -> ionExchangeName.translate();
            case VACUUM_SINTERING -> vacuumSinteringName.translate();
            case CRYSTALLIZATION -> crystallizationName.translate();
        };
    }

    public Component primaryLabel() {
        return switch (this) {
            case CONDENSING_DISCRETE -> condensingPrimary.translate();
            case OXIDATION_ROASTING -> roastingPrimary.translate();
            case HIGH_PRESSURE_ALKALI_DIGESTION -> digestionPrimary.translate();
            case SOLVENT_EXTRACTION -> extractionPrimary.translate();
            case REDUCTION_PRECIPITATION -> precipitationPrimary.translate();
            case ION_EXCHANGE -> ionExchangePrimary.translate();
            case VACUUM_SINTERING -> vacuumSinteringPrimary.translate();
            case CRYSTALLIZATION -> crystallizationPrimary.translate();
        };
    }

    public Component secondaryLabel() {
        return switch (this) {
            case CONDENSING_DISCRETE -> condensingSecondary.translate();
            case OXIDATION_ROASTING -> roastingSecondary.translate();
            case HIGH_PRESSURE_ALKALI_DIGESTION -> digestionSecondary.translate();
            case SOLVENT_EXTRACTION -> extractionSecondary.translate();
            case REDUCTION_PRECIPITATION -> precipitationSecondary.translate();
            case ION_EXCHANGE -> ionExchangeSecondary.translate();
            case VACUUM_SINTERING -> vacuumSinteringSecondary.translate();
            case CRYSTALLIZATION -> crystallizationSecondary.translate();
        };
    }

    public static Component calibrationTitle() {
        return calibrationTitle.translate();
    }

    public static Component primaryInput() {
        return primaryInput.translate();
    }

    public static Component secondaryInput() {
        return secondaryInput.translate();
    }

    public static Component lockedWhileRunning() {
        return lockedWhileRunning.translate();
    }

    public static Component waitingForRecipe() {
        return waitingForRecipe.translate();
    }

    public static Component processLine(Component process) {
        return processLine.translate(process);
    }

    public static Component parameterLine(Component label, int current, int min, int max) {
        return parameterLine.translate(label, current, min, max);
    }

    public static Component calibrated() {
        return calibrated.translate();
    }

    public static Component uncalibrated() {
        return uncalibrated.translate();
    }

    public static Component calibrationRequired() {
        return calibrationRequired.translate();
    }

    private int resolveTarget(GTRecipe recipe, String dataKey, Parameter parameter, int salt) {
        if (recipe.data.contains(dataKey, Tag.TAG_ANY_NUMERIC)) {
            return parameter.clamp(recipe.data.getInt(dataKey));
        }
        int recipeHash = recipe.id == null ? recipe.recipeType.registryName.hashCode() : recipe.id.hashCode();
        int hash = recipeHash ^ salt ^ (ordinal() * 0x45D9F3B);
        return parameter.randomTarget(hash);
    }

    public record Parameter(int min, int max, int tolerance) {

        public int clamp(int value) {
            return Math.max(min, Math.min(max, value));
        }

        public int defaultValue() {
            return min + (max - min) / 2;
        }

        public int targetMin(int target) {
            return clamp(target - tolerance);
        }

        public int targetMax(int target) {
            return clamp(target + tolerance);
        }

        public boolean matches(int value, int target) {
            return value >= targetMin(target) && value <= targetMax(target);
        }

        private int randomTarget(int hash) {
            int safeMin = min + tolerance;
            int safeMax = max - tolerance;
            if (safeMin > safeMax) {
                return defaultValue();
            }
            return safeMin + Math.floorMod(hash, safeMax - safeMin + 1);
        }
    }
}
