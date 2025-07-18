package io.github.cpearl0.ctnhcore.common.item;

import appeng.api.implementations.blockentities.IWirelessAccessPoint;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.item.component.IItemUIFactory;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.common.block.CoilBlock;
//import com.hepdd.gtmthings.api.gui.widget.AlignLabelWidget;
import com.hepdd.gtmthings.api.gui.widget.TerminalInputWidget;
//import com.hepdd.gtmthings.api.misc.Hatch;
import com.lowdragmc.lowdraglib.gui.editor.ColorPattern;
import com.lowdragmc.lowdraglib.gui.factory.HeldItemUIFactory;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.widget.*;
import com.lowdragmc.lowdraglib.utils.BlockInfo;
import io.github.cpearl0.ctnhcore.event.BuildTaskManager;
import io.github.cpearl0.ctnhcore.utils.CoilTierHelper;
import io.github.cpearl0.ctnhcore.utils.OrientedItem;
import lombok.Getter;
import lombok.Setter;
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

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

//import static com.hepdd.gtmthings.api.gui.widget.AlignLabelWidget.ALIGN_CENTER;
import static io.github.cpearl0.ctnhcore.api.Pattern.AsynBlockPattern.getAdvancedBlockPattern;

public class MEAdvancedTerminalBehavior implements IItemUIFactory {
    // 配置键常量
    private static final String COIL_TIER_KEY = "CoilTier";
    private static final String REPEAT_COUNT_KEY = "RepeatCount";
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
        if(context.getItemInHand().getItem() instanceof MEAdvancedTerminalItem terminal)
        {
            settings.accessPoint = terminal.getAccessPoint(context.getItemInHand(), context.getLevel());
        }

        if (!controller.isFormed() || (machine instanceof WorkableMultiblockMachine workableMachine &&
                settings.isReplaceCoilMode())) {
            var pattern = getAdvancedBlockPattern(controller.getPattern());
            if (pattern != null)
            {
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
                tag.getInt(NO_HATCH_MODE_KEY),
                tag.getInt(REPLACE_COIL_MODE_KEY),
                tag.getInt(USE_AE_KEY),
                tag.getInt(PLACE_FLUID_KEY),
                tag.getInt(PLACE_IN_FLUID_KEY)
        );
    }

    @Override
    public ModularUI createUI(HeldItemUIFactory.HeldItemHolder holder, Player player) {
        return new ModularUI(176, 166, holder, player)
                .widget(createSettingsWidget(player));
    }

    private Widget createSettingsWidget(Player player) {
        ItemStack handItem = player.getMainHandItem();
        WidgetGroup group = new WidgetGroup(0, 0, 182 + 8, 117 + 8);
        var scrollGroup = new DraggableScrollableWidgetGroup(4, 4, 182, 117)
                .setBackground(GuiTextures.DISPLAY)
                .setYScrollBarWidth(2)
                .setYBarStyle(null, ColorPattern.T_WHITE.rectTexture().setRadius(1))
//                .addWidget(new AlignLabelWidget(89, 5, "item.ctnh.me_advanced_terminal.setting.title")
//                        .setTextAlign(ALIGN_CENTER));
                .addWidget(new LabelWidget(40, 5, Component.translatable("item.ctnh.me_advanced_terminal.setting.title").getString()));

        List<SettingConfig> settings = Arrays.asList(
                new SettingConfig(
                        "item.ctnh.me_advanced_terminal.setting.1",
                        getCoilTooltip(),
                        COIL_TIER_KEY,
                        () -> getTagValue(handItem, COIL_TIER_KEY, 0),
                        value -> setTagValue(handItem, COIL_TIER_KEY, value),
                        0, GTCEuAPI.HEATING_COILS.size()
                ),
                new SettingConfig(
                        "item.ctnh.me_advanced_terminal.setting.2",
                        new ArrayList<>(Collections.singletonList(
                                Component.translatable("item.ctnh.me_advanced_terminal.setting.2.tooltip"))),
                        REPEAT_COUNT_KEY,
                        () -> getTagValue(handItem, REPEAT_COUNT_KEY, 0),
                        value -> setTagValue(handItem, REPEAT_COUNT_KEY, value),
                        0, 99
                ),
                new SettingConfig(
                        "item.ctnh.me_advanced_terminal.setting.3",
                        new ArrayList<>(Collections.singletonList(
                                Component.translatable("item.ctnh.me_advanced_terminal.setting.3.tooltip"))),
                        NO_HATCH_MODE_KEY,
                        () -> getTagValue(handItem, NO_HATCH_MODE_KEY, 1),
                        value -> setTagValue(handItem, NO_HATCH_MODE_KEY, value),
                        0, 1
                ),
                new SettingConfig(
                        "item.ctnh.me_advanced_terminal.setting.4",
                        new ArrayList<>(Collections.singletonList(
                                Component.translatable("item.ctnh.me_advanced_terminal.setting.4.tooltip"))),
                        REPLACE_COIL_MODE_KEY,
                        () -> getTagValue(handItem, REPLACE_COIL_MODE_KEY, 0),
                        value -> setTagValue(handItem, REPLACE_COIL_MODE_KEY, value),
                        0, 1
                ),
                new SettingConfig(
                        "item.ctnh.me_advanced_terminal.setting.5",
                        new ArrayList<>(Collections.singletonList(
                                Component.translatable("item.ctnh.me_advanced_terminal.setting.5.tooltip"))),
                        USE_AE_KEY,
                        () -> getTagValue(handItem, USE_AE_KEY, 0),
                        value -> setTagValue(handItem, USE_AE_KEY, value),
                        0, 1
                ),
                new SettingConfig(
                        "item.ctnh.me_advanced_terminal.setting.6",
                        new ArrayList<>(Collections.singletonList(
                                Component.translatable("item.ctnh.me_advanced_terminal.setting.6.tooltip"))),
                        PLACE_FLUID_KEY,
                        () -> getTagValue(handItem, PLACE_FLUID_KEY, 0),
                        value -> setTagValue(handItem, PLACE_FLUID_KEY, value),
                        0, 1
                ),
                new SettingConfig(
                        "item.ctnh.me_advanced_terminal.setting.7",
                        new ArrayList<>(Collections.singletonList(
                                Component.translatable("item.ctnh.me_advanced_terminal.setting.7.tooltip"))),
                        PLACE_IN_FLUID_KEY,
                        () -> getTagValue(handItem, PLACE_IN_FLUID_KEY, 0),
                        value -> setTagValue(handItem, PLACE_IN_FLUID_KEY, value),
                        0, 1
                )
        );

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
        group.setBackground(GuiTextures.BACKGROUND_INVERSE);
        return group;
    }

    private List<Component> getCoilTooltip() {
        List<Component> lines = new ArrayList<>();
        lines.add(Component.translatable("item.ctnh.me_advanced_terminal.setting.1.tooltip"));
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
        private final int noHatchMode;
        private final int replaceCoilMode;
        private final int useAEStorage;
        private final int placeFluid;
        private final int placeInFluid;

        private IWirelessAccessPoint accessPoint;

        public static final TagKey<Item> HATCH_TAG = ItemTags.create(new ResourceLocation("forge", "hatch"));

        public AutoBuildSetting(int coilTier, int repeatCount, int noHatchMode,
                                int replaceCoilMode, int useAEStorage, int placeFluid, int placeInFluid) {
            this.coilTier = coilTier;
            this.repeatCount = repeatCount;
            this.noHatchMode = noHatchMode;
            this.replaceCoilMode = replaceCoilMode;
            this.useAEStorage = useAEStorage;
            this.placeFluid = placeFluid;
            this.placeInFluid = placeInFluid;

        }

        public AutoBuildSetting() {
            this(0, 0, 1, 0, 0, 1, 0);
        }

        public List<OrientedItem> apply(BlockInfo[] blockInfos) {
            List<OrientedItem> candidates = new ArrayList<>();
            if (blockInfos != null) {
                // 处理线圈方块的特殊逻辑
                if (Arrays.stream(blockInfos).anyMatch(info ->
                        info.getBlockState().getBlock() instanceof CoilBlock)) {

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
                        //candidates.add(OrientedItem.createOrientedItem(coilBlockInfos));
                    }
                    return candidates;
                }

                // 处理普通方块
                for (BlockInfo info : blockInfos) {
                    if (info.getBlockState().getBlock() != Blocks.AIR
                            &&(this.noHatchMode == 0 || !info.getItemStackForm().is(HATCH_TAG))) {
                        candidates.add(OrientedItem.createOrientedItem(info));
                    }
                }
            }
            return candidates;
        }

        public boolean isPlaceHatch(BlockInfo[] blockInfos) {
            if (this.noHatchMode == 0) return true;
            if (blockInfos != null && blockInfos.length > 0) {
                return Arrays.stream(blockInfos).noneMatch(b -> b.getItemStackForm().is(HATCH_TAG));
//                var blockInfo = blockInfos[0];
//                if (blockInfo.getBlockState().getBlock() instanceof MetaMachineBlock machineBlock) {
//                    var id = machineBlock.getDefinition().getName();
//                    for (String hatchName : HATCH_NAMES) {
//                        if (id.contains(hatchName)) return false;
//                    }
//                }
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