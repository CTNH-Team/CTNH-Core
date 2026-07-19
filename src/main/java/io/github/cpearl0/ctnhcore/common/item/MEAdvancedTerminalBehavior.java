package io.github.cpearl0.ctnhcore.common.item;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;

import io.github.cpearl0.ctnhcore.common.gui.terminal.TerminalInputWidget;
import io.github.cpearl0.ctnhcore.event.BuildTaskManager;
import io.github.cpearl0.ctnhcore.utils.CoilTierHelper;
import io.github.cpearl0.ctnhcore.utils.OrientedItem;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.block.IMachineBlock;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.item.component.IItemUIFactory;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.BlockPattern;
import com.gregtechceu.gtceu.common.block.CoilBlock;

import com.lowdragmc.lowdraglib.gui.editor.ColorPattern;
import com.lowdragmc.lowdraglib.gui.factory.HeldItemUIFactory;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.texture.ResourceBorderTexture;
import com.lowdragmc.lowdraglib.gui.widget.*;
import com.lowdragmc.lowdraglib.utils.BlockInfo;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import appeng.api.implementations.blockentities.IWirelessAccessPoint;
import com.moguang.ctnhmana.common.multiblock.IndustrialAltarMachine;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static io.github.cpearl0.ctnhcore.api.Pattern.AsynBlockPattern.getAdvancedBlockPattern;

public class MEAdvancedTerminalBehavior implements IItemUIFactory {

    @Key("item.ctnh.me_advanced_terminal.setting.1")
    @CN("线圈等级")
    @EN("Coil Tier")
    public static Lang itemMeAdvancedTerminalSetting1;


    @Key("item.ctnh.me_advanced_terminal.setting.1.tooltip")
    @CN("设置自动放置的线圈等级(0:不指定等级)\n设置后会忽略结构本身的要求")
    @EN("Set the tier of coils placed automatically (0: no tier specified)\nThe structure's own requirements are ignored after setting this")
    public static Lang itemMeAdvancedTerminalSetting1Tooltip;


    @Key("item.ctnh.me_advanced_terminal.setting.10")
    @CN("工业血之祭坛结构等级")
    @EN("Industrial Blood Altar Tier")
    public static Lang itemMeAdvancedTerminalSetting10;


    @Key("item.ctnh.me_advanced_terminal.setting.10.tooltip")
    @CN("仅对工业血之祭坛自动成型生效\n设置要构建的工业血之祭坛等级(0-4)\n0 对应二阶，4 对应六阶")
    @EN("Only applies to automatic formation of Industrial Blood Altars\nSet the Industrial Blood Altar tier to build (0-4)\n0 corresponds to Tier II and 4 corresponds to Tier VI")
    public static Lang itemMeAdvancedTerminalSetting10Tooltip;


    @Key("item.ctnh.me_advanced_terminal.setting.2")
    @CN("重复结构次数")
    @EN("Repeated Structure Count")
    public static Lang itemMeAdvancedTerminalSetting2;


    @Key("item.ctnh.me_advanced_terminal.setting.2.tooltip")
    @CN("设置可重复结构(蒸馏塔、装配线等)的重复部分放置次数\n对超净间无效")
    @EN("Set the number of repeated sections placed for repeatable structures (Distillation Towers, Assembly Lines, etc.)\nDoes not apply to Cleanrooms")
    public static Lang itemMeAdvancedTerminalSetting2Tooltip;


    @Key("item.ctnh.me_advanced_terminal.setting.3")
    @CN("无仓室模式")
    @EN("Hatchless Mode")
    public static Lang itemMeAdvancedTerminalSetting3;


    @Key("item.ctnh.me_advanced_terminal.setting.3.tooltip")
    @CN("是否启用无仓室模式(0:不启用,1:启用)\n启用无仓室模式后不会放置任何仓室")
    @EN("Enable hatchless mode (0: disabled, 1: enabled)\nWhen enabled, no hatches will be placed")
    public static Lang itemMeAdvancedTerminalSetting3Tooltip;


    @Key("item.ctnh.me_advanced_terminal.setting.4")
    @CN("线圈替换模式")
    @EN("Coil Replacement Mode")
    public static Lang itemMeAdvancedTerminalSetting4;


    @Key("item.ctnh.me_advanced_terminal.setting.4.tooltip")
    @CN("是否启用线圈替换模式(0:不启用,1:启用)\n启用线圈替换模式会将所有线圈替换为指定等级的线圈\n请确保物品栏中有空间存放替换下来的线圈")
    @EN("Enable coil replacement mode (0: disabled, 1: enabled)\nWhen enabled, all coils are replaced with the specified tier\nMake sure the inventory has room for the replaced coils")
    public static Lang itemMeAdvancedTerminalSetting4Tooltip;


    @Key("item.ctnh.me_advanced_terminal.setting.5")
    @CN("使用AE存储")
    @EN("Use AE Storage")
    public static Lang itemMeAdvancedTerminalSetting5;


    @Key("item.ctnh.me_advanced_terminal.setting.5.tooltip")
    @CN("是否启用AE库存(0:不启用,1:启用)\n启用后将优先在AE库存中检索\n通过ME无线访问点链接到网络")
    @EN("Enable AE storage (0: disabled, 1: enabled)\nWhen enabled, the AE storage is searched first\nConnect to the network through an ME Wireless Access Point")
    public static Lang itemMeAdvancedTerminalSetting5Tooltip;


    @Key("item.ctnh.me_advanced_terminal.setting.6")
    @CN("放置流体")
    @EN("Place Fluids")
    public static Lang itemMeAdvancedTerminalSetting6;


    @Key("item.ctnh.me_advanced_terminal.setting.6.tooltip")
    @CN("是否启用流体放置(0:不启用,1:启用)\n启用后将检索并消耗物品栏/背包流体容器中的或AE库存中的流体\n可堆叠的流体容器须保证堆叠数为1")
    @EN("Enable fluid placement (0: disabled, 1: enabled)\nWhen enabled, fluids are searched for and consumed from fluid containers in the inventory/backpack or AE storage\nStackable fluid containers must have a stack size of 1")
    public static Lang itemMeAdvancedTerminalSetting6Tooltip;


    @Key("item.ctnh.me_advanced_terminal.setting.7")
    @CN("在流体中放置")
    @EN("Place in Fluids")
    public static Lang itemMeAdvancedTerminalSetting7;


    @Key("item.ctnh.me_advanced_terminal.setting.7.tooltip")
    @CN("是否在流体中放置方块(0:不启用,1:启用)\n启用后会将空间中的流体视为空位\n与“放置流体”同时启用时，不会在流体中放置流体")
    @EN("Place blocks in fluids (0: disabled, 1: enabled)\nWhen enabled, fluids in the space are treated as empty space\nWhen enabled together with Place Fluids, fluids will not be placed in fluids")
    public static Lang itemMeAdvancedTerminalSetting7Tooltip;


    @Key("item.ctnh.me_advanced_terminal.setting.8")
    @CN("拆除模式")
    @EN("Removal Mode")
    public static Lang itemMeAdvancedTerminalSetting8;


    @Key("item.ctnh.me_advanced_terminal.setting.8.tooltip")
    @CN("是否启用拆除模式(0:不启用,1:启用)\n请确保物品栏中有空间存放拆除的方块\n与“使用AE存储”同时启用时，拆除的方块会自动存入AE存储")
    @EN("Enable removal mode (0: disabled, 1: enabled)\nMake sure the inventory has room for removed blocks\nWhen enabled together with AE Storage, removed blocks are automatically stored in AE storage")
    public static Lang itemMeAdvancedTerminalSetting8Tooltip;


    @Key("item.ctnh.me_advanced_terminal.setting.9")
    @CN("多方块成型配置")
    @EN("Multiblock Formation Configuration")
    public static Lang itemMeAdvancedTerminalSetting9;


    @Key("item.ctnh.me_advanced_terminal.setting.9.tooltip")
    @CN("多方块成型配置")
    @EN("Multiblock formation configuration")
    public static Lang itemMeAdvancedTerminalSetting9Tooltip;


    @Key("item.ctnh.me_advanced_terminal.setting.title")
    @CN("多方块结构成型配置")
    @EN("Multiblock Formation Settings")
    public static Lang itemMeAdvancedTerminalSettingTitle;



    // 配置键常量
    private static final String COIL_TIER_KEY = "CoilTier";
    private static final String REPEAT_COUNT_KEY = "RepeatCount";
    private static final String ALTAR_TIER_KEY = "AltarTier";
    private static final String NO_HATCH_MODE_KEY = "NoHatchMode";
    private static final String REPLACE_COIL_MODE_KEY = "ReplaceCoilMode";
    private static final String USE_AE_KEY = "UseAEStorage";
    private static final String PLACE_FLUID_KEY = "PlaceFluid";
    private static final String PLACE_IN_FLUID_KEY = "PlaceInFluid";

    public MEAdvancedTerminalBehavior() {}

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player == null || !player.isShiftKeyDown()) {
            return InteractionResult.PASS;
        }

        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        if (level.isClientSide()) {
            return InteractionResult.sidedSuccess(true);
        }

        MetaMachine machine = MetaMachine.getMachine(level, blockPos);
        if (!(machine instanceof IMultiController controller)) {
            return InteractionResult.PASS;
        }

        AutoBuildSetting settings = getAutoBuildSetting(player.getMainHandItem());
        if (context.getItemInHand().getItem() instanceof MEAdvancedTerminalItem terminal) {
            settings.accessPoint = terminal.getAccessPoint(context.getItemInHand(), context.getLevel());
        }

        if (shouldStartAutoBuild(controller, machine, settings)) {
            var pattern = getAdvancedBlockPattern(resolveTargetPattern(controller, machine, settings));
            if (pattern != null) {
                pattern.startAutoBuild(player, controller.getMultiblockState(), settings);
                BuildTaskManager.getInstance().registerTask(player, pattern);
            }
        }

        return InteractionResult.sidedSuccess(false);
    }

    private AutoBuildSetting getAutoBuildSetting(ItemStack itemStack) {
        CompoundTag tag = itemStack.getOrCreateTag();
        return new AutoBuildSetting(
                tag.getInt(COIL_TIER_KEY),
                tag.getInt(REPEAT_COUNT_KEY),
                tag.getInt(ALTAR_TIER_KEY),
                tag.getInt(NO_HATCH_MODE_KEY),
                tag.getInt(REPLACE_COIL_MODE_KEY),
                tag.getInt(USE_AE_KEY),
                tag.getInt(PLACE_FLUID_KEY),
                tag.getInt(PLACE_IN_FLUID_KEY));
    }

    private boolean shouldStartAutoBuild(IMultiController controller, MetaMachine machine, AutoBuildSetting settings) {
        if (!controller.isFormed()) {
            return true;
        }
        if (machine instanceof WorkableMultiblockMachine workableMachine && settings.isReplaceCoilMode()) {
            return true;
        }
        return shouldUpgradeIndustrialAltar(machine, settings);
    }

    private boolean shouldUpgradeIndustrialAltar(MetaMachine machine, AutoBuildSetting settings) {
        if (!(machine instanceof IndustrialAltarMachine altarMachine)) {
            return false;
        }
        return settings.getAltarTier() > altarMachine.getMatchedPatternIndex();
    }

    private BlockPattern resolveTargetPattern(IMultiController controller, MetaMachine machine,
                                              AutoBuildSetting settings) {
        if (!(machine instanceof IndustrialAltarMachine altarMachine)) {
            return controller.getPattern();
        }
        BlockPattern selectedPattern = getIndustrialAltarPattern(altarMachine, settings.getAltarTier());
        return selectedPattern == null ? controller.getPattern() : selectedPattern;
    }

    private BlockPattern getIndustrialAltarPattern(IndustrialAltarMachine altarMachine, int altarTier) {
        int index = Math.max(0, Math.min(altarTier, 4));
        return switch (index) {
            case 0 -> altarMachine.getDefinition().getPatternFactory().get();
            case 1 -> IndustrialAltarMachine.createLevel3Pattern(altarMachine.getDefinition());
            case 2 -> IndustrialAltarMachine.createLevel4Pattern(altarMachine.getDefinition());
            case 3 -> IndustrialAltarMachine.createLevel5Pattern(altarMachine.getDefinition());
            case 4 -> IndustrialAltarMachine.createLevel6Pattern(altarMachine.getDefinition());
            default -> altarMachine.getDefinition().getPatternFactory().get();
        };
    }

    @Override
    public ModularUI createUI(HeldItemUIFactory.HeldItemHolder holder, Player player) {
        return new ModularUI(176, 166, holder, player)
                .widget(createSettingsWidget(player));
    }

    private Widget createSettingsWidget(Player player) {
        ItemStack handItem = player.getMainHandItem();
        WidgetGroup group = new WidgetGroup(0, 0, 182 + 8, 137 + 8);
        var scrollGroup = new DraggableScrollableWidgetGroup(4, 4, 182, 137)
                .setBackground(GuiTextures.DISPLAY)
                .setYScrollBarWidth(2)
                .setYBarStyle(null, ColorPattern.T_WHITE.rectTexture().setRadius(1))
                // .addWidget(new AlignLabelWidget(89, 5, "item.ctnh.me_advanced_terminal.setting.title")
                // .setTextAlign(ALIGN_CENTER));
                .addWidget(new LabelWidget(40, 5,
                        itemMeAdvancedTerminalSettingTitle.translate().getString()));

        List<SettingConfig> settings = Arrays.asList(
                new SettingConfig(
                        itemMeAdvancedTerminalSetting1.key(),
                        getCoilTooltip(),
                        COIL_TIER_KEY,
                        () -> getTagValue(handItem, COIL_TIER_KEY, 0),
                        value -> setTagValue(handItem, COIL_TIER_KEY, value),
                        0, GTCEuAPI.HEATING_COILS.size()),
                new SettingConfig(
                        itemMeAdvancedTerminalSetting2.key(),
                        new ArrayList<>(Collections.singletonList(
                                itemMeAdvancedTerminalSetting2Tooltip.translate())),
                        REPEAT_COUNT_KEY,
                        () -> getTagValue(handItem, REPEAT_COUNT_KEY, 0),
                        value -> setTagValue(handItem, REPEAT_COUNT_KEY, value),
                        0, 99),
                new SettingConfig(
                        itemMeAdvancedTerminalSetting10.key(),
                        new ArrayList<>(Collections.singletonList(
                                itemMeAdvancedTerminalSetting10Tooltip.translate())),
                        ALTAR_TIER_KEY,
                        () -> getTagValue(handItem, ALTAR_TIER_KEY, 0),
                        value -> setTagValue(handItem, ALTAR_TIER_KEY, value),
                        0, 4),
                new SettingConfig(
                        itemMeAdvancedTerminalSetting3.key(),
                        new ArrayList<>(Collections.singletonList(
                                itemMeAdvancedTerminalSetting3Tooltip.translate())),
                        NO_HATCH_MODE_KEY,
                        () -> getTagValue(handItem, NO_HATCH_MODE_KEY, 1),
                        value -> setTagValue(handItem, NO_HATCH_MODE_KEY, value),
                        0, 1),
                new SettingConfig(
                        itemMeAdvancedTerminalSetting4.key(),
                        new ArrayList<>(Collections.singletonList(
                                itemMeAdvancedTerminalSetting4Tooltip.translate())),
                        REPLACE_COIL_MODE_KEY,
                        () -> getTagValue(handItem, REPLACE_COIL_MODE_KEY, 0),
                        value -> setTagValue(handItem, REPLACE_COIL_MODE_KEY, value),
                        0, 1),
                new SettingConfig(
                        itemMeAdvancedTerminalSetting5.key(),
                        new ArrayList<>(Collections.singletonList(
                                itemMeAdvancedTerminalSetting5Tooltip.translate())),
                        USE_AE_KEY,
                        () -> getTagValue(handItem, USE_AE_KEY, 0),
                        value -> setTagValue(handItem, USE_AE_KEY, value),
                        0, 1),
                new SettingConfig(
                        itemMeAdvancedTerminalSetting6.key(),
                        new ArrayList<>(Collections.singletonList(
                                itemMeAdvancedTerminalSetting6Tooltip.translate())),
                        PLACE_FLUID_KEY,
                        () -> getTagValue(handItem, PLACE_FLUID_KEY, 0),
                        value -> setTagValue(handItem, PLACE_FLUID_KEY, value),
                        0, 1),
                new SettingConfig(
                        itemMeAdvancedTerminalSetting7.key(),
                        new ArrayList<>(Collections.singletonList(
                                itemMeAdvancedTerminalSetting7Tooltip.translate())),
                        PLACE_IN_FLUID_KEY,
                        () -> getTagValue(handItem, PLACE_IN_FLUID_KEY, 0),
                        value -> setTagValue(handItem, PLACE_IN_FLUID_KEY, value),
                        0, 1));

        int rowIndex = 1;
        for (SettingConfig config : settings) {
            scrollGroup.addWidget(new LabelWidget(4, 5 + 16 * rowIndex, config.labelKey)
                    .setHoverTooltips(config.tooltipKey));
            scrollGroup.addWidget(new TerminalInputWidget(140, 5 + 16 * rowIndex, 20, 16,
                    config.getValue, config.setValue)
                    .setMin(config.minValue)
                    .setMax(config.maxValue));
            rowIndex++;
        }

        group.addWidget(scrollGroup);
        group.setBackground(new ResourceBorderTexture(
                "ae2:textures/guis/background.png", 256, 256, 4, 4));
        return group;
    }

    private List<Component> getCoilTooltip() {
        List<Component> lines = new ArrayList<>();
        lines.add(itemMeAdvancedTerminalSetting1Tooltip.translate());
        GTCEuAPI.HEATING_COILS.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getKey().getTier()))
                .forEach(entry -> lines.add(Component.literal(String.valueOf(entry.getKey().getTier() + 1))
                        .append(":")
                        .append(entry.getValue().get().getName())));
        return lines;
    }

    private int getTagValue(ItemStack stack, String key, int defaultValue) {
        return stack.getOrCreateTag().getInt(key);
    }

    private void setTagValue(ItemStack stack, String key, int value) {
        stack.getOrCreateTag().putInt(key, value);
    }

    @Getter
    @Setter
    public static class AutoBuildSetting {

        private final int coilTier;
        private final int repeatCount;
        private final int altarTier;
        private final int noHatchMode;
        private final int replaceCoilMode;
        private final int useAEStorage;
        private final int placeFluid;
        private final int placeInFluid;

        private IWirelessAccessPoint accessPoint;

        public static final TagKey<Item> HATCH_TAG = ItemTags.create(ResourceLocation.tryBuild("forge", "hatch"));

        public AutoBuildSetting(int coilTier, int repeatCount, int altarTier, int noHatchMode,
                                int replaceCoilMode, int useAEStorage, int placeFluid, int placeInFluid) {
            this.coilTier = coilTier;
            this.repeatCount = repeatCount;
            this.altarTier = altarTier;
            this.noHatchMode = noHatchMode;
            this.replaceCoilMode = replaceCoilMode;
            this.useAEStorage = useAEStorage;
            this.placeFluid = placeFluid;
            this.placeInFluid = placeInFluid;
        }

        public AutoBuildSetting() {
            this(0, 0, 0, 1, 0, 0, 1, 0);
        }

        public List<OrientedItem> apply(BlockInfo[] blockInfos) {
            List<OrientedItem> candidates = new ArrayList<>();
            if (blockInfos != null) {
                // 处理线圈方块的特殊逻辑
                if (shouldReplaceCoils(blockInfos)) {

                    int tier = Math.min(coilTier - 1, blockInfos.length - 1);
                    if (tier == -1) {
                        for (int i = 0; i < blockInfos.length - 1; i++) {

                            candidates.add(OrientedItem.createOrientedItem(blockInfos[i]));
                        }
                    } else {
                        Arrays.stream(CoilTierHelper.getCoilBlocks(tier))
                                .map(BlockInfo::new)
                                .map(OrientedItem::createOrientedItem)
                                .forEach(candidates::add);
                        // candidates.add(OrientedItem.createOrientedItem(coilBlockInfos));
                    }
                    return candidates;
                }

                // 处理普通方块
                for (BlockInfo info : blockInfos) {
                    if (info.getBlockState().getBlock() != Blocks.AIR &&
                            (this.noHatchMode == 0 || !(info.getBlockState().getBlock() instanceof IMachineBlock))) {
                        candidates.add(OrientedItem.createOrientedItem(info));
                    }
                }
            }
            return candidates;
        }

        private boolean shouldReplaceCoils(BlockInfo[] blockInfos) {
            return Arrays.stream(blockInfos)
                    .filter(info -> info.getBlockState().getBlock() instanceof CoilBlock)
                    .map(info -> info.getBlockState().getBlock())
                    .distinct()
                    .count() > 1;
        }

        public boolean isPlaceHatch(BlockInfo[] blockInfos) {
            if (this.noHatchMode == 0) return true;
            if (blockInfos != null && blockInfos.length > 0) {
                return Arrays.stream(blockInfos).noneMatch(b -> b.getBlockState().getBlock() instanceof IMachineBlock);
                // var blockInfo = blockInfos[0];
                // if (blockInfo.getBlockState().getBlock() instanceof MetaMachineBlock machineBlock) {
                // var id = machineBlock.getDefinition().getName();
                // for (String hatchName : HATCH_NAMES) {
                // if (id.contains(hatchName)) return false;
                // }
                // }
            }
            return true;
        }

        public boolean isReplaceCoilMode() {
            return replaceCoilMode == 1;
        }
    }

    private static class SettingConfig {

        final String labelKey;
        final List<Component> tooltipKey;
        final String tagKey;
        final Supplier<Integer> getValue;
        final Consumer<Integer> setValue;
        final int minValue;
        final int maxValue;

        SettingConfig(String labelKey, List<Component> tooltipKey, String tagKey,
                      Supplier<Integer> getValue, Consumer<Integer> setValue,
                      int minValue, int maxValue) {
            this.labelKey = labelKey;
            this.tooltipKey = tooltipKey;
            this.tagKey = tagKey;
            this.getValue = getValue;
            this.setValue = setValue;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }
}
