package io.github.cpearl0.ctnhcore.registry;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.ScalableReservoirComputingMachine;
import io.github.cpearl0.ctnhcore.data.CreateRecipeTypes;
import io.github.cpearl0.ctnhcore.data.recipe.BioChemistryRecipes;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.block.ICoilType;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gregtechceu.gtceu.utils.ResearchManager;

import com.lowdragmc.lowdraglib.LDLib;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget;
import com.lowdragmc.lowdraglib.utils.CycleItemStackHandler;
import com.lowdragmc.lowdraglib.utils.LocalizationUtils;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.item.ItemStack;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import com.moguang.ctnhbio.api.capability.recipe.EntityRecipeCapability;
import com.simibubi.create.AllBlocks;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.ArrayList;
import java.util.List;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.LEFT_TO_RIGHT;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class CTNHRecipeTypes {

    @CN("§c危险粒子实验")
    @EN("§cDangerous Particle Experiment")
    public static Lang accDanger;

    @Key("ctnh.nuclear_reactor.basic")
    @CN("这是一个耗能设备，但是会产生大量的热量，可以转化用以发电")
    @EN("This is an energy-consuming machine that produces a large amount of heat, which can be converted into power")
    public static Lang nuclearReactorBasic;

    @Key("ctnh.nuclear_reactor.coolant")
    @CN("冷却液可以使用蒸汽（150°C），氘（450°C），钠（800°C），钠钾合金（900°C），反应的堆温越高，消耗冷却液的速度越快，冷却液的热容越大，消耗速度越慢")
    @EN("Coolant can be steam (150°C), deuterium (450°C), sodium (800°C), or sodium-potassium alloy (900°C). Higher reactor temperature increases coolant consumption, while higher coolant heat capacity reduces consumption")
    public static Lang nuclearReactorCoolant;

    @Key("ctnh.nuclear_reactor.overclock")
    @CN("冷却液并非运行所必须，但是在有冷却液时，配方每运行一秒，进度会增加两秒")
    @EN("Coolant is not required for operation, but with coolant present, recipe progress increases by two seconds for every one second of operation")
    public static Lang nuclearReactorOverclock;

    @Key("ctnh.nuclear_reactor.safe")
    @CN("反应堆不会过热爆炸")
    @EN("The reactor will not explode from overheating")
    public static Lang nuclearReactorSafe;

    @CN("模式：加速电子")
    @EN("Mode: Electron Acceleration")
    public static Lang recipeAcceleratorModeElement;

    @CN("加速类型：电子")
    @EN("Accelerated Particle: Electron")
    public static Lang recipeAcceleratorModeElementConsume;

    @CN("模式：加速中子")
    @EN("Mode: Neutron Acceleration")
    public static Lang recipeAcceleratorModeNu;

    @CN("加速类型：中子")
    @EN("Accelerated Particle: Neutron")
    public static Lang recipeAcceleratorModeNuConsume;

    @CN("模式：加速质子")
    @EN("Mode: Proton Acceleration")
    public static Lang recipeAcceleratorModeProton;

    @CN("加速类型：质子")
    @EN("Accelerated Particle: Proton")
    public static Lang recipeAcceleratorModeProtonConsume;

    @CN("需求速度:%.2fGev")
    @EN("Required Velocity: %.2f GeV")
    public static Lang recipeAcceleratorModeSpeedG;

    @CN("需求速度：%.2fMev")
    @EN("Required Velocity: %.2f MeV")
    public static Lang recipeAcceleratorModeSpeedM;

    @CN("满功率需求电弧强度:%d")
    @EN("Full-Power Required Arc Intensity: %d")
    public static Lang recipeArcGeneratorMaxRequire;

    @CN("需求电弧强度:%d")
    @EN("Required Arc Intensity: %d")
    public static Lang recipeArcGeneratorRequire;

    @Key("ctnh.recipe.hellforge.info.drain")
    @CN("消耗：%s意志")
    @EN("Drain: %s Will")
    public static Lang recipeHellforgeInfoDrain;

    @Key("ctnh.recipe.hellforge.info.minimum_drain")
    @CN("最少：%s意志")
    @EN("Minimum Drain: %s Will")
    public static Lang recipeHellforgeInfoMinimumDrain;

    @CN("消耗/输入的lp量:%.1f")
    @EN("LP consumption/input: %.1f")
    public static Lang recipeIndustrialAltarInfo0;

    @Key("ctnh.recipe.quasar_eye.info.0")
    @CN("启动消耗:%.1f")
    @EN("Activation Cost: %.1f")
    public static Lang recipeQuasarEyeInfo0;

    @Key("ctnh.recipe.quasar_eye.info.1")
    @CN("能量等级: %d")
    @EN("Energy Tier: %d")
    public static Lang recipeQuasarEyeInfo1;

    @Key("ctnh.recipe.quasar_eye.info.2")
    @CN("启动等级: %d")
    @EN("Activation Tier: %d")
    public static Lang recipeQuasarEyeInfo2;

    @Key("ctnh.recipe_type.info")
    @CN("配方类型：%s")
    @EN("Recipe Type：%s")
    public static Lang recipeTypeInfo;

    @Key("ctnh.recipe_type.list")
    @CN("%s, %s")
    @EN("%s, %s")
    public static Lang recipeTypeList;

    @CN("模拟电压消耗: %d EUt")
    @EN("Simulated voltage consumption: %d EUt")
    public static Lang spacephotovoltaicbasestationRecipeEutModel;

    @CN("需求光伏方块等级: %d")
    @EN("Required photovoltaic block tier: %d")
    public static Lang spacephotovoltaicbasestationRecipePvcTier;

    @CN("牺牲者: %s")
    @EN("Sacrifices: %s")
    public static Lang ctnhSrcSacrifice;

    @Key("gtceu.recipe_logic.recipe_waiting")
    @CN("配方等待中：")
    @EN("Recipe waiting:")
    public static Lang gtceuRecipeLogicRecipeWaiting;

    @Key("gtceu.recipe_logic.setup_fail")
    @CN("配方启动失败：")
    @EN("Recipe failed to start:")
    public static Lang gtceuRecipeLogicSetupFail;

    @Key("multiblock.ctnh.nuclear_reactor.consume_amount")
    @CN("冷却液消耗率：%s mB/s")
    @EN("Coolant consumption rate: %s mB/s")
    public static Lang multiblockCtnhNuclearReactorConsumeAmount;

    @Key("multiblock.ctnh.nuclear_reactor.coolant")
    @CN("冷却液：%s")
    @EN("Coolant: %s")
    public static Lang multiblockCtnhNuclearReactorCoolant;

    @Key("multiblock.ctnh.nuclear_reactor.coolant_amount")
    @CN("冷却液量：%s mB")
    @EN("Coolant amount: %s mB")
    public static Lang multiblockCtnhNuclearReactorCoolantAmount;

    @Key("nuclear_reactor")
    @CN("核能转化时刻")
    @EN("Nuclear Energy Conversion")
    public static Lang nuclearReactor;

    public static final GTRecipeType UNDERFLOOR_HEATING_SYSTEM = REGISTRATE
            .recipeType(CTNHCore.id("underfloor_heating_system"), GTRecipeTypes.ELECTRIC)
            .cnlang("地暖")
            .setMaxIOSize(0, 0, 1, 1)
            .setEUIO(IO.NONE)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

    public static final GTRecipeType ASTRONOMICAL_OBSERVATORY = REGISTRATE
            .recipeType(CTNHCore.id("astronomical_observatory"), GTRecipeTypes.ELECTRIC)
            .cnlang("天文台")
            .setMaxIOSize(1, 1, 0, 0)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

    public static final GTRecipeType PERSONAL_COMPUTER = REGISTRATE
            .recipeType(CTNHCore.id("personal_computer"), GTRecipeTypes.ELECTRIC)
            .cnlang("个人电脑")
            .setMaxIOSize(9, 2, 0, 0)
            .setEUIO(IO.IN)
            .setMaxTooltips(4)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

    public static final GTRecipeType DIGITAL_MINER = REGISTRATE
            .recipeType(CTNHCore.id("digital_miner"), GTRecipeTypes.ELECTRIC)
            .cnlang("数字型采矿")
            .setMaxIOSize(0, 27, 0, 0)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.SLOT)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

    public static final GTRecipeType OXYGEN_ENRICHER_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("oxygen_enricher"), GTRecipeTypes.ELECTRIC)
            .cnlang("氧气富集器")
            .setMaxIOSize(0, 0, 1, 0)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COOLING);

    public static final GTRecipeType SLAUGHTER_HOUSE = REGISTRATE
            .recipeType(CTNHCore.id("slaughter_house"), GTRecipeTypes.ELECTRIC)
            .cnlang("屠宰场")
            .setMaxIOSize(4, 4, 2, 2)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL);

    public static final GTRecipeType FERMENTING = REGISTRATE
            .recipeType(CTNHCore.id("fermenting"), GTRecipeTypes.MULTIBLOCK)
            .cnlang("发酵罐")
            .setEUIO(IO.IN)
            .setMaxIOSize(4, 4, 2, 1)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL)
            .addDataInfo(data -> LocalizationUtils.format("gtceu.recipe.temperature",
                    FormattingUtil.formatNumbers(data.getInt("ebf_temp"))))
            .addDataInfo(data -> {
                var requiredCoil = ICoilType.getMinRequiredType(data.getInt("ebf_temp"));
                if (LDLib.isClient() && requiredCoil != null && requiredCoil.getMaterial() != null) {
                    return LocalizationUtils.format("gtceu.recipe.coil.tier",
                            I18n.get(requiredCoil.getMaterial().getUnlocalizedName()));
                }
                return "";
            })
            .setUiBuilder((recipe, widgetGroup) -> {
                var temp = recipe.data.getInt("ebf_temp");
                var items = new ArrayList<List<ItemStack>>();
                items.add(GTCEuAPI.HEATING_COILS.entrySet().stream()
                        .filter(coil -> coil.getKey().getCoilTemperature() >= temp)
                        .map(coil -> new ItemStack(coil.getValue().get())).toList());
                widgetGroup.addWidget(new SlotWidget(new CycleItemStackHandler(items), 0,
                        widgetGroup.getSize().width - 25, widgetGroup.getSize().height - 32, false, false));
            });
    public static final GTRecipeType DIGESTING = REGISTRATE.recipeType(CTNHCore.id("digesting"), MULTIBLOCK)
            .cnlang("化粪池")
            .setEUIO(IO.IN)
            .setMaxIOSize(2, 1, 2, 4)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .addDataInfo(data -> {
                if (data.getBoolean("special")) {
                    return BioChemistryRecipes.digestion_info.translate().getString();
                }
                return "";
            })
            .setSound(GTSoundEntries.CHEMICAL);
    public static final GTRecipeType BEDROCK_DRILLING_RIGS = REGISTRATE
            .recipeType(CTNHCore.id("bedrock_drilling_rigs"), GTRecipeTypes.ELECTRIC)
            .cnlang("基岩钻机")
            .setEUIO(IO.IN)
            .setMaxIOSize(1, 3, 1, 1)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ELECTROLYZER);

    public static final GTRecipeType NAQ_MK1 = REGISTRATE.recipeType(CTNHCore.id("naq_mk1"), GTRecipeTypes.ELECTRIC)
            .cnlang("超能燃料")
            .setEUIO(IO.OUT)
            .setMaxIOSize(0, 0, 2, 1)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.TURBINE);
    public static final GTRecipeType PHASE_INVERSION = REGISTRATE
            .recipeType(CTNHCore.id("phase_inversion"), GTRecipeTypes.ELECTRIC)
            .cnlang("反相蚀刻")
            .setEUIO(IO.IN)
            .setMaxIOSize(6, 6, 2, 2)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ELECTROLYZER);
    public static final GTRecipeType RESONANT_MAGICAL_ASSEMBLY = REGISTRATE
            .recipeType(CTNHCore.id("resonant_assemble"), GTRecipeTypes.ELECTRIC)
            .cnlang("振动共鸣组装")
            .setEUIO(IO.IN)
            .setMaxIOSize(9, 9, 2, 2)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ELECTROLYZER);

    public static final GTRecipeType PLASMA_CONDENSER_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("plasma_condenser"), GTRecipeTypes.ELECTRIC)
            .cnlang("等离子冷凝").setEUIO(IO.IN).setMaxIOSize(2, 2, 2, 2)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COOLING);

    public static final GTRecipeType MEADOW = REGISTRATE.recipeType(CTNHCore.id("meadow"), MULTIBLOCK)
            .cnlang("牧场养殖").setMaxIOSize(2, 3, 2, 2)
            .setMaxSize(IO.IN, EntityRecipeCapability.CAP, 1)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.BATH);

    public static final GTRecipeType CHEMICAL_GENERATOR = REGISTRATE
            .recipeType(CTNHCore.id("chemical_generator"), GENERATOR)
            .cnlang("化学能发电")
            .setEUIO(IO.OUT)
            .setMaxIOSize(2, 0, 2, 0)
            .setSlotOverlay(false, false, GuiTextures.BOX_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COMBUSTION);
    public static final GTRecipeType VOID_MINER = REGISTRATE.recipeType(CTNHCore.id("void_miner"), GENERATOR)
            .cnlang("虚空采矿")
            .setEUIO(IO.IN)
            .setMaxIOSize(1, 6, 3, 0)
            .setSlotOverlay(false, false, GuiTextures.BOX_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COMBUSTION);
    public static final GTRecipeType SINTERING_KILN = REGISTRATE
            .recipeType(CTNHCore.id("sintering_kiln"), GTRecipeTypes.ELECTRIC)
            .cnlang("烧结")
            .setMaxIOSize(4, 4, 2, 0)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COOLING);
    public static final GTRecipeType CHEMICAL_VAPOR_DEPOSITION = REGISTRATE
            .recipeType(CTNHCore.id("chemical_vapor_deposition"), ELECTRIC)
            .cnlang("化学气相沉积")
            .setEUIO(IO.IN)
            .setMaxIOSize(2, 2, 2, 2)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COOLING);
    public static final GTRecipeType MARTIAL_MORALITY_EYE = REGISTRATE
            .recipeType(CTNHCore.id("martial_morality_eye"), GTRecipeTypes.ELECTRIC)
            .cnlang("武德之眼").setEUIO(IO.IN).setMaxIOSize(2, 27, 1, 3)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL);
    public static final GTRecipeType DIMENSIONAL_GAS_COLLECTION = REGISTRATE
            .recipeType(CTNHCore.id("dimensional_gas_collection"), GTRecipeTypes.ELECTRIC)
            .cnlang("维度集气").setEUIO(IO.IN).setMaxIOSize(2, 2, 2, 2)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COOLING);
    public static final GTRecipeType CONDENSING_DISCRETE = REGISTRATE
            .recipeType(CTNHCore.id("condensing_discrete"), GTRecipeTypes.ELECTRIC)
            .cnlang("冷凝离散").setEUIO(IO.IN).setMaxIOSize(6, 6, 6, 6)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COOLING);
    public static final GTRecipeType ION_EXCHANGER = REGISTRATE
            .recipeType(CTNHCore.id("ion_exchanger"), GTRecipeTypes.ELECTRIC)
            .cnlang("离子交换").setEUIO(IO.IN).setMaxIOSize(6, 6, 6, 6)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COOLING);
    // public static final GTRecipeType DECAY_VESSEL = REGISTRATE.recipeType(CTNHCore.id("decay_vessel"),
    // GTRecipeTypes.ELECTRIC)
    // .cnlang("").setMaxIOSize(2, 1, 0, 2)
    // .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
    // .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
    // .setSound(GTSoundEntries.MINER);
    public static final GTRecipeType DECAY_POOLS = REGISTRATE
            .recipeType(CTNHCore.id("decay_pools"), GTRecipeTypes.ELECTRIC)
            .cnlang("衰变罐").setEUIO(IO.IN).setMaxIOSize(2, 1, 0, 2)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.MINER);
    public static final GTRecipeType SILICA_ROCK_FUEL_REFINERY = REGISTRATE
            .recipeType(CTNHCore.id("silica_rock_fuel_refinery"), ELECTRIC)
            .cnlang("硅岩燃料精炼")
            .setEUIO(IO.IN)
            .setMaxIOSize(3, 0, 3, 2)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL);
    public static final GTRecipeType WOOD_BIONICS = REGISTRATE.recipeType(CTNHCore.id("wood_bionics"), ELECTRIC)
            .cnlang("§e林海树场")
            .setEUIO(IO.IN)
            .setMaxIOSize(1, 4, 1, 0)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CUT);
    public static final GTRecipeType FUEL_REFINING = REGISTRATE
            .recipeType(CTNHCore.id("fuel_refining"), GTRecipeTypes.ELECTRIC)
            .cnlang("燃料精炼").setEUIO(IO.IN).setMaxIOSize(3, 3, 3, 3)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ELECTROLYZER)
            .addDataInfo(data -> LocalizationUtils.format("gtceu.recipe.temperature",
                    FormattingUtil.formatNumbers(data.getInt("ebf_temp"))))
            .addDataInfo(data -> {
                var requiredCoil = ICoilType.getMinRequiredType(data.getInt("ebf_temp"));
                if (LDLib.isClient() && requiredCoil != null && requiredCoil.getMaterial() != null) {
                    return LocalizationUtils.format("gtceu.recipe.coil.tier",
                            I18n.get(requiredCoil.getMaterial().getUnlocalizedName()));
                }
                return "";
            })
            .setUiBuilder((recipe, widgetGroup) -> {
                var temp = recipe.data.getInt("ebf_temp");
                var items = new ArrayList();
                items.add(GTCEuAPI.HEATING_COILS.entrySet().stream()
                        .filter(coil -> coil.getKey().getCoilTemperature() >= temp)
                        .map(coil -> new ItemStack(coil.getValue().get())).toList());
                widgetGroup.addWidget(new SlotWidget(new CycleItemStackHandler(items), 0,
                        widgetGroup.getSize().width - 25, widgetGroup.getSize().height - 32, false, false));
            });
    public static final GTRecipeType VACUUM_SINTERING = REGISTRATE
            .recipeType(CTNHCore.id("vacuum_sintering"), GTRecipeTypes.ELECTRIC)
            .cnlang("真空烧结")
            .setEUIO(IO.IN)
            .setMaxIOSize(6, 6, 6, 6)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ELECTROLYZER)
            .addDataInfo(data -> LocalizationUtils.format("gtceu.recipe.temperature",
                    FormattingUtil.formatNumbers(data.getInt("ebf_temp"))))
            .addDataInfo(data -> {
                var requiredCoil = ICoilType.getMinRequiredType(data.getInt("ebf_temp"));
                if (LDLib.isClient() && requiredCoil != null && requiredCoil.getMaterial() != null) {
                    return LocalizationUtils.format("gtceu.recipe.coil.tier",
                            I18n.get(requiredCoil.getMaterial().getUnlocalizedName()));
                }
                return "";
            })
            .setUiBuilder((recipe, widgetGroup) -> {
                var temp = recipe.data.getInt("ebf_temp");
                var items = new ArrayList();
                items.add(GTCEuAPI.HEATING_COILS.entrySet().stream()
                        .filter(coil -> coil.getKey().getCoilTemperature() >= temp)
                        .map(coil -> new ItemStack(coil.getValue().get())).toList());
                widgetGroup.addWidget(new SlotWidget(new CycleItemStackHandler(items), 0,
                        widgetGroup.getSize().width - 25, widgetGroup.getSize().height - 32, false, false));
            });
    public static final GTRecipeType CRYSTALLIZER = REGISTRATE
            .recipeType(CTNHCore.id("crystallizer"), GTRecipeTypes.ELECTRIC)
            .cnlang("结晶反应")
            .setEUIO(IO.IN)
            .setMaxIOSize(6, 6, 6, 6)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_CRYSTALLIZATION, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL)
            .addDataInfo(data -> LocalizationUtils.format("gtceu.recipe.temperature",
                    FormattingUtil.formatNumbers(data.getInt("ebf_temp"))))
            .addDataInfo(data -> {
                var requiredCoil = ICoilType.getMinRequiredType(data.getInt("ebf_temp"));
                if (LDLib.isClient() && requiredCoil != null && requiredCoil.getMaterial() != null) {
                    return LocalizationUtils.format("gtceu.recipe.coil.tier",
                            I18n.get(requiredCoil.getMaterial().getUnlocalizedName()));
                }
                return "";
            })
            .setUiBuilder((recipe, widgetGroup) -> {
                var temp = recipe.data.getInt("ebf_temp");
                var items = new ArrayList();
                items.add(GTCEuAPI.HEATING_COILS.entrySet().stream()
                        .filter(coil -> coil.getKey().getCoilTemperature() >= temp)
                        .map(coil -> new ItemStack(coil.getValue().get())).toList());
                widgetGroup.addWidget(new SlotWidget(new CycleItemStackHandler(items), 0,
                        widgetGroup.getSize().width - 25, widgetGroup.getSize().height - 32, false, false));
            });
    public static final GTRecipeType DESALTING = REGISTRATE.recipeType(CTNHCore.id("desalting"), GTRecipeTypes.ELECTRIC)
            .cnlang("海水脱盐")
            .setEUIO(IO.IN)
            .setMaxIOSize(1, 4, 1, 1)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ELECTROLYZER)
            .addDataInfo(data -> LocalizationUtils.format("gtceu.recipe.temperature",
                    FormattingUtil.formatNumbers(data.getInt("ebf_temp"))))
            .addDataInfo(data -> {
                var requiredCoil = ICoilType.getMinRequiredType(data.getInt("ebf_temp"));
                if (LDLib.isClient() && requiredCoil != null && requiredCoil.getMaterial() != null) {
                    return LocalizationUtils.format("gtceu.recipe.coil.tier",
                            I18n.get(requiredCoil.getMaterial().getUnlocalizedName()));
                }
                return "";
            })
            .setUiBuilder((recipe, widgetGroup) -> {
                var temp = recipe.data.getInt("ebf_temp");
                var items = new ArrayList();
                items.add(GTCEuAPI.HEATING_COILS.entrySet().stream()
                        .filter(coil -> coil.getKey().getCoilTemperature() >= temp)
                        .map(coil -> new ItemStack(coil.getValue().get())).toList());
                widgetGroup.addWidget(new SlotWidget(new CycleItemStackHandler(items), 0,
                        widgetGroup.getSize().width - 25, widgetGroup.getSize().height - 32, false, false));
            });
    public static final GTRecipeType WATER_POWER = REGISTRATE.recipeType(CTNHCore.id("water_power"), GENERATOR)
            .cnlang("水力发电").setEUIO(IO.OUT).setMaxIOSize(0, 0, 1, 0)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COOLING);
    public static final GTRecipeType BIO_REACTOR = REGISTRATE.recipeType(CTNHCore.id("bio_reactor"), ELECTRIC)
            .cnlang("生物反应").setEUIO(IO.IN).setMaxIOSize(4, 4, 2, 2)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL);
    public static final String KINETIC = "kinetic";
    public static final GTRecipeType INDUSTRIAL_ALTAR_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("industrial_altar"), ELECTRIC)
            .cnlang("§4工业血之祭坛§r").setEUIO(IO.IN).setMaxIOSize(4, 4, 2, 2)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL)
            .setUiBuilder((recipe, group) -> {
                var handler = new CustomItemStackHandler(AllBlocks.SHAFT.asStack());
                group.addWidget(
                        new com.gregtechceu.gtceu.api.gui.widget.SlotWidget(handler, 0, group.getSize().width - 30,
                                group.getSize().height - 30, false, false));
            })
            .addDataInfo(data -> LocalizationUtils.format(recipeIndustrialAltarInfo0.key(),
                    String.format("%.1f", data.getFloat("addlp"))));

    public static final GTRecipeType NANO_GENERATOR = REGISTRATE.recipeType(CTNHCore.id("nano_generator"), GENERATOR)
            .cnlang("摩擦发电").setMaxIOSize(4, 4, 0, 0)
            .setEUIO(IO.OUT)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.TURBINE);
    public static final GTRecipeType SINOPE = REGISTRATE.recipeType(CTNHCore.id("sinope"), GTRecipeTypes.ELECTRIC)
            .cnlang("规模化化工").setMaxIOSize(4, 4, 4, 4)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.TURBINE);
    public static final GTRecipeType ACCELERATOR_UP = REGISTRATE.recipeType(CTNHCore.id("accelerator_upmode"), ELECTRIC)
            .cnlang("粒子加速").setMaxIOSize(9, 9, 3, 3)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressTexture.FillDirection.DOWN_TO_UP)
            .setSound(GTSoundEntries.FIRE)
            .addDataInfo(data -> {
                if (data.getString("type").equals("addnu")) {
                    return LocalizationUtils.format(recipeAcceleratorModeNu.key());
                } else if (data.getString("type").equals("addproton")) {
                    return LocalizationUtils.format(recipeAcceleratorModeProton.key());
                } else if (data.getString("type").equals("addelement")) {
                    return LocalizationUtils.format(recipeAcceleratorModeElement.key());
                } else if (data.getString("type").equals("element")) {
                    return LocalizationUtils.format(recipeAcceleratorModeElementConsume.key());
                } else if (data.getString("type").equals("nu")) {
                    return LocalizationUtils.format(recipeAcceleratorModeNuConsume.key());
                } else if (data.getString("type").equals("proton")) {
                    return LocalizationUtils.format(recipeAcceleratorModeProtonConsume.key());
                }
                return "";
            })
            .addDataInfo(data -> {

                if (data.getString("type").equals("element") || data.getString("type").equals("nu") ||
                        data.getString("type").equals("proton")) {
                    var speed = data.getDouble("speed");
                    if (data.getDouble("speed") < 1000) {
                        return LocalizationUtils.format(recipeAcceleratorModeSpeedM.key(),
                                String.format("%.2f", speed));
                    }
                    if (speed >= 1000) {
                        return LocalizationUtils.format(recipeAcceleratorModeSpeedG.key(),
                                String.format("%.2f", speed / 1000));
                    }

                }
                return "";
            })
            .addDataInfo(data -> {
                if (data.contains("darkmatter")) {
                    return LocalizationUtils.format(accDanger.key());
                }
                return "";

            });

    public static final GTRecipeType ARC_GENERATOR = REGISTRATE.recipeType(CTNHCore.id("arc_generator"), GENERATOR)
            .cnlang("物质撕裂湮灭").setMaxIOSize(6, 6, 3, 3)
            .setEUIO(IO.OUT)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .addDataInfo(data -> LocalizationUtils.format(recipeArcGeneratorRequire.key(),
                    String.format("%d", data.getInt("requirearc"))))
            .addDataInfo(data -> LocalizationUtils.format(recipeArcGeneratorMaxRequire.key(),
                    String.format("%d", data.getInt("maxarc"))))
            .setSound(GTSoundEntries.CHEMICAL);
    public static final GTRecipeType ARC_REACTOR = REGISTRATE.recipeType(CTNHCore.id("arc_reactor"), ELECTRIC)
            .cnlang("电弧发生").setMaxIOSize(6, 6, 3, 3)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL);
    public static final GTRecipeType MAGIC_FUEL_GENERATOR = REGISTRATE
            .recipeType(CTNHCore.id("magic_fuel_generator"), ELECTRIC)
            .cnlang("魔力燃料精炼").setMaxIOSize(6, 6, 6, 6)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL);
    public static final GTRecipeType CULTIVATION_ROOM = REGISTRATE.recipeType(CTNHCore.id("cultivation_room"), ELECTRIC)
            .cnlang("培养").setMaxIOSize(3, 3, 3, 3)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.MIXER);

    public static final GTRecipeType CT_ASSEMBLY_LINE = REGISTRATE.recipeType(CTNHCore.id("ct_assembly_line"), ELECTRIC)
            .cnlang("机械装配线").setMaxIOSize(6, 1, 3, 0)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL);
    public static final GTRecipeType CT_ASSEMBLY_LINE_MAKER = REGISTRATE
            .recipeType(CTNHCore.id("ct_assembly_line_maker"), ELECTRIC)
            .cnlang("机械部件装配线").setMaxIOSize(6, 1, 0, 0)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL);
    public static final GTRecipeType PVB_RECIPE = REGISTRATE.recipeType(CTNHCore.id("pvb_recipe"), ELECTRIC)
            .cnlang("物理气相沉积").setMaxIOSize(6, 6, 3, 3)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL);
    public static final GTRecipeType LS_RECIPE = REGISTRATE.recipeType(CTNHCore.id("laser_sorter_recipe"), ELECTRIC)
            .cnlang("激光分配").setMaxIOSize(6, 6, 3, 3)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL);

    public static final GTRecipeType PHOTOVOLTAIC_GENERATOR = REGISTRATE
            .recipeType(CTNHCore.id("photovoltaic_generator"), GENERATOR)
            .cnlang("光伏发电").setMaxIOSize(2, 2, 2, 2)
            .setEUIO(IO.OUT)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL);
    public static final GTRecipeType PHOTOVOLTAIC_ASSEMBER = REGISTRATE
            .recipeType(CTNHCore.id("photovoltaic_assember"), GENERATOR)
            .cnlang("太空光伏组装").setMaxIOSize(6, 6, 3, 3)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .addDataInfo(data -> {
                if (data.contains("tier")) {
                    return LocalizationUtils.format(spacephotovoltaicbasestationRecipePvcTier.key(),
                            String.format("%d", data.getInt("tier")));
                }
                return "";
            })
            .addDataInfo(data -> {
                if (data.contains("input")) {
                    return LocalizationUtils.format(spacephotovoltaicbasestationRecipeEutModel.key(),
                            String.format("%d", data.getInt("input")));
                }
                return "";
            })
            .setSound(GTSoundEntries.CHEMICAL);
    public static final GTRecipeType PVDRONE = REGISTRATE.recipeType(CTNHCore.id("pv_drone_recipe"), ELECTRIC)
            .cnlang("光伏无人机").setMaxIOSize(6, 6, 3, 3)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL);
    public static final GTRecipeType NUCLEAR_REACTOR_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("nuclear_reactor"), MULTIBLOCK)
            .cnlang("裂变反应").setMaxIOSize(6, 3, 3, 3)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL)
            .addDataInfo(data -> LocalizationUtils.format(CTNHItems.itemNuclearReactorHeat.key(),
                    String.format("%.1f", data.getFloat("heat"))));
    public static final GTRecipeType GAS_CENTRIFUGE_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("gas_centrifuge"), MULTIBLOCK)
            .cnlang("气体离心").setMaxIOSize(0, 0, 1, 3)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CENTRIFUGE);
    public static final GTRecipeType HOT_COOLANT_TURBINE_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("hot_coolant_turbine"), MULTIBLOCK)
            .cnlang("热冷却涡轮").setMaxIOSize(0, 0, 1, 1)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.TURBINE);

    public static final GTRecipeType COMPILER_RECIPE = REGISTRATE.recipeType(CTNHCore.id("compiler_recipe"), ELECTRIC)
            .cnlang("神经矩阵编译").setMaxIOSize(5, 1, 0, 0)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL);

    public static final GTRecipeType SCALABLE_RESERVOIR_COMPUTING = REGISTRATE
            .recipeType(CTNHCore.id("scalable_reservoir_computing"), ELECTRIC)
            .cnlang("突触凝练").setEUIO(IO.IN).setMaxIOSize(1, 0, 1, 0)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSlotOverlay(false, false, GuiTextures.RESEARCH_STATION_OVERLAY)
            .setSlotOverlay(false, true, GuiTextures.FLUID_SLOT)
            .setSound(GTSoundEntries.COMPUTATION)
            .addDataInfo(data -> LocalizationUtils.format("gtceu.machine.hpca.component_type.computation_cwut",
                    data.getInt("maxCWUt")))
            .addDataInfo(
                    data -> LocalizationUtils.format(ScalableReservoirComputingMachine.ctnhSrcWetwareDuration.key(),
                            data.getInt("wetwareDuration")))
            // TODO: 渲染以后写
            .addDataInfo(data -> LocalizationUtils.format(ctnhSrcSacrifice.key(), data.getString("sacrifice")));
    public static final GTRecipeType DIFFERENTIAL_CENTRIFUGE_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("differential_centrifuge"), ELECTRIC)
            .cnlang("差速离心")
            .setEUIO(IO.IN)
            .setMaxIOSize(6, 6, 6, 6)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CENTRIFUGE);
    public static final GTRecipeType ULTRASONICATION_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("ultrasonication"), ELECTRIC)
            .cnlang("超声破碎").setEUIO(IO.IN).setMaxIOSize(3, 3, 3, 3)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.MACERATOR);
    public static final GTRecipeType ZENITH_EXTRUDER_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("zenith_extruder"), ELECTRIC)
            .cnlang("天顶塑形").setEUIO(IO.IN).setMaxIOSize(3, 18, 1, 0)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.MACERATOR);

    public static final GTRecipeType GREENHOUSE_RECIPES = REGISTRATE.recipeType(CTNHCore.id("greenhouse"), MULTIBLOCK)
            .cnlang("温室").setEUIO(IO.IN).setMaxIOSize(4, 4, 1, 0)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.BATH);

    public static final GTRecipeType COMPONENT_ASSEMBLY_LINE_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("component_assembly_line"), GTRecipeTypes.MULTIBLOCK)
            .cnlang("部件装配").setMaxIOSize(12, 1, 12, 0)
            .setEUIO(IO.IN)
            .setMaxTooltips(4)
            .setSound(GTSoundEntries.ASSEMBLER);
    public static final GTRecipeType CIRCUIT_ASSEMBLY_LINE_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("circuit_assembly_line"), GTRecipeTypes.MULTIBLOCK)
            .cnlang("电路装配").setMaxIOSize(6, 1, 1, 0)
            .setEUIO(IO.IN)
            .setMaxTooltips(4)
            .setHasResearchSlot(true)
            .setSound(GTSoundEntries.ASSEMBLER)
            .onRecipeBuild(ResearchManager::createDefaultResearchRecipe);

    public static final GTRecipeType CHEMICAL_PLANT_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("chemical_plant"), GTRecipeTypes.MULTIBLOCK)
            .cnlang("化工厂")
            .setMaxTooltips(4)
            .setMaxIOSize(5, 4, 4, 4).setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.BOX_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setMaxTooltips(4)
            .setSound(GTSoundEntries.COOLING);

    public static final GTRecipeType NEUTRON_ACTIVATOR_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("neutron_activator"), GTRecipeTypes.MULTIBLOCK)
            .cnlang("中子活化").setMaxIOSize(9, 9, 1, 1)
            .setMaxTooltips(5)
            .setSound(GTSoundEntries.COOLING);

    public static final GTRecipeType LARGE_NAQUADAH_REACTOR_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("large_naquadah_reactor"), GTRecipeTypes.MULTIBLOCK)
            .cnlang("大型硅岩反应").setMaxIOSize(0, 0, 1, 1)
            .setEUIO(IO.OUT).setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CENTRIFUGE);

    public static final GTRecipeType DEHYDRATOR_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("dehydrator"), GTRecipeTypes.ELECTRIC)
            .cnlang("脱水").setMaxIOSize(2, 9, 1, 1).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CENTRIFUGE);

    public static final GTRecipeType NAQUADAH_REACTOR_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("naquadah_reactor"), GTRecipeTypes.ELECTRIC)
            .cnlang("硅岩反应").setMaxIOSize(1, 1, 0, 0).setEUIO(IO.OUT)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CENTRIFUGE);

    public static final GTRecipeType STONE_BEDROCK_ORE_MACHINE_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("homemade_bedrock_ore_machine"), GTRecipeTypes.STEAM)
            .cnlang("")
            .setXEIVisible(false)
            .setMaxIOSize(1, 6, 0, 0)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.FURNACE);

    public static final GTRecipeType ROCKET_ENGINE_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("rocket_engine"), GTRecipeTypes.ELECTRIC)
            .cnlang("火箭引擎").setMaxIOSize(0, 0, 1, 1).setEUIO(IO.OUT)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CENTRIFUGE);

    public static final GTRecipeType PRECISION_ASSEMBLY_RECIPES = REGISTRATE
            .recipeType(CTNHCore.id("precision_assembly"), GTRecipeTypes.ELECTRIC)
            .cnlang("精密组装").setMaxIOSize(4, 1, 4, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setMaxTooltips(4)
            .setSound(GTSoundEntries.COOLING);

    public static void init() {
        CreateRecipeTypes.init();
        // MIXER_RECIPES.cnlang("").setMaxIOSize(6, 1, 9, 1);
    }
}
