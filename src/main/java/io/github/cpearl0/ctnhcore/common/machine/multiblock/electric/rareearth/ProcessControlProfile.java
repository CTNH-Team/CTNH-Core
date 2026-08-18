package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.rareearth;

import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Prefix;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

@Prefix("info.multiblock.process_control")
public enum ProcessControlProfile {

    CONDENSING_DISCRETE(new Parameter(5, 50, 8), new Parameter(20, 80, 10)),
    OXIDATION_ROASTING(new Parameter(95, 125, 6), new Parameter(2, 12, 3)),
    HIGH_PRESSURE_ALKALI_DIGESTION(new Parameter(1200, 2400, 250), new Parameter(20, 50, 6)),
    SOLVENT_EXTRACTION(new Parameter(200, 700, 60), new Parameter(50, 200, 30)),
    REDUCTION_PRECIPITATION(new Parameter(-700, 200, 120), new Parameter(100, 700, 60)),
    ION_EXCHANGE(new Parameter(5, 40, 7), new Parameter(50, 400, 50)),
    VACUUM_SINTERING(new Parameter(5, 500, 90), new Parameter(5, 80, 12)),
    CRYSTALLIZATION(new Parameter(5, 50, 9), new Parameter(105, 140, 8));

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

    @CN("工况稳定中: %s 秒")
    @EN("Process settling: %s s")
    static Lang settling;

    @CN("等待置换介质: %s")
    @EN("Waiting for priming medium: %s")
    static Lang waitingForPrime;

    @CN("维护结算（每120次）: %s EU%s")
    @EN("Maintenance Settlement (per 120 runs): %s EU%s")
    static Lang holdCostLine;

    @CN("维护原料: %s")
    @EN("Maintenance Supplies: %s")
    static Lang maintenanceSupply;

    @CN("调参原料: %s")
    @EN("Tuning Supplies: %s")
    static Lang tuningSupply;

    @CN("；%s mB %s")
    @EN("; %s mB %s")
    static Lang holdFluidPart;

    @CN("维持物料不足，等待补给")
    @EN("Hold supplies insufficient; waiting for resupply")
    static Lang holdSupplyMissing;

    @CN("缺少每轮耗材: %s")
    @EN("Missing per-batch supply: %s")
    static Lang batchSupplyMissing;

    @CN("运行额度: %s/%s")
    @EN("Run Quota: %s/%s")
    static Lang runQuota;

    @CN("运行额度已耗尽，请补充维护原料")
    @EN("Run quota exhausted; supply maintenance materials")
    static Lang runsExhausted;

    @CN("等待维持材料结算: %s")
    @EN("Waiting for hold settlement: %s")
    static Lang waitingForHold;

    @CN("工艺参数: ")
    @EN("Process Controls: ")
    static Lang recipeDataInfoPrefix;

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

    public static Component settling(Component ticks) {
        return settling.translate(ticks);
    }

    public static Component waitingForPrime(Component medium) {
        return waitingForPrime.translate(medium);
    }

    public static Component holdCostLine(Component eu, Component fluids) {
        return holdCostLine.translate(eu, fluids);
    }

    public static Component maintenanceSupply(Component supplies) {
        return maintenanceSupply.translate(supplies);
    }

    public static Component tuningSupply(Component supplies) {
        return tuningSupply.translate(supplies);
    }

    public static Component holdFluidPart(Component amount, Component name) {
        return holdFluidPart.translate(amount, name);
    }

    public static Component holdSupplyMissing() {
        return holdSupplyMissing.translate();
    }

    public static Component batchSupplyMissing(Component name) {
        return batchSupplyMissing.translate(name);
    }

    public static Component runQuota(Component left, Component total) {
        return runQuota.translate(left, total);
    }

    public static Component runsExhausted() {
        return runsExhausted.translate();
    }

    public static Component waitingForHold(Component amounts) {
        return waitingForHold.translate(amounts);
    }

    public static String recipeDataInfoPrefix() {
        return recipeDataInfoPrefix.translate().getString();
    }

    public long holdEUt(int primaryValue, int secondaryValue) {
        return switch (this) {
            case CONDENSING_DISCRETE -> 2L * primaryValue;
            case OXIDATION_ROASTING -> 32L * secondaryValue;
            case HIGH_PRESSURE_ALKALI_DIGESTION -> primaryValue / 20L;
            case SOLVENT_EXTRACTION -> 0L;
            case REDUCTION_PRECIPITATION -> Math.max(0L, (200 - primaryValue) / 10L);
            case ION_EXCHANGE -> 8L * primaryValue;
            case VACUUM_SINTERING -> (long) (8.0 * Math.sqrt(500.0 / primaryValue)) + 6L * secondaryValue;
            case CRYSTALLIZATION -> 4L * primaryValue + 2L * (secondaryValue - 100);
        };
    }

    public FluidStack holdFluidA(int primaryValue, int secondaryValue) {
        return switch (this) {
            case CONDENSING_DISCRETE -> GTMaterials.Lubricant.getFluid(Math.max(1, secondaryValue / 2));
            case OXIDATION_ROASTING -> GTMaterials.Oxygen.getFluid(Math.max(1, primaryValue - 90));
            case HIGH_PRESSURE_ALKALI_DIGESTION -> GTMaterials.SodiumHydroxide
                    .getFluid(Math.max(1, secondaryValue / 5));
            case SOLVENT_EXTRACTION -> {
                int amount = Math.abs(primaryValue - 450) / 25;
                yield amount <= 0 ? FluidStack.EMPTY :
                        (primaryValue < 450 ? GTMaterials.SulfuricAcid : GTMaterials.SodiumHydroxide).getFluid(amount);
            }
            case REDUCTION_PRECIPITATION -> {
                int amount = Math.abs(secondaryValue - 400) / 20;
                yield amount <= 0 ? FluidStack.EMPTY :
                        (secondaryValue < 400 ? GTMaterials.SulfuricAcid : GTMaterials.SodiumHydroxide)
                                .getFluid(amount);
            }
            case ION_EXCHANGE -> GTMaterials.HydrochloricAcid.getFluid(Math.max(1, secondaryValue / 40));
            case VACUUM_SINTERING -> GTMaterials.Nitrogen.getFluid(500);
            case CRYSTALLIZATION -> GTMaterials.SaltWater.getFluid(Math.max(1, primaryValue / 10));
        };
    }

    public FluidStack holdFluidB(int primaryValue, int secondaryValue) {
        return switch (this) {
            case SOLVENT_EXTRACTION -> GTMaterials.Benzene.getFluid(Math.max(1, secondaryValue / 20));
            default -> FluidStack.EMPTY;
        };
    }

    public FluidStack primeFluidA() {
        return switch (this) {
            case CONDENSING_DISCRETE -> GTMaterials.SaltWater.getFluid(500);
            case OXIDATION_ROASTING -> GTMaterials.Oxygen.getFluid(500);
            case HIGH_PRESSURE_ALKALI_DIGESTION -> GTMaterials.SodiumHydroxide.getFluid(250);
            case SOLVENT_EXTRACTION -> GTMaterials.Benzene.getFluid(1000);
            case REDUCTION_PRECIPITATION -> GTMaterials.SulfuricAcid.getFluid(500);
            case ION_EXCHANGE -> GTMaterials.HydrochloricAcid.getFluid(1000);
            case VACUUM_SINTERING -> GTMaterials.Nitrogen.getFluid(500);
            case CRYSTALLIZATION -> GTMaterials.SaltWater.getFluid(1000);
        };
    }

    public FluidStack primeFluidB() {
        return switch (this) {
            case HIGH_PRESSURE_ALKALI_DIGESTION -> GTMaterials.Water.getFluid(500);
            default -> FluidStack.EMPTY;
        };
    }

    public ItemStack batchItem(int primaryValue) {
        return ItemStack.EMPTY;
    }

    public FluidStack batchFluid() {
        return FluidStack.EMPTY;
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
