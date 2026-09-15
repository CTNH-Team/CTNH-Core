package io.github.cpearl0.ctnhcore.common.machine.multiblock.generator;

import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.IEnergyContainer;
import com.gregtechceu.gtceu.api.capability.IWorkable;
import com.gregtechceu.gtceu.api.capability.forge.GTCapability;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.FancyMachineUIWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.IDropSaveMachine;
import com.gregtechceu.gtceu.api.machine.feature.IExplosionMachine;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDisplayUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockDisplayText;
import com.gregtechceu.gtceu.api.misc.EnergyContainerList;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gregtechceu.gtceu.utils.GTUtil;

import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.widget.*;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.ctnhlang.CN;
import com.ctnhlang.EN;
import earth.terrarium.adastra.api.planets.Planet;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PhotovoltaicPowerStationMachine extends MultiblockControllerMachine implements IFancyUIMachine,
                                             IDisplayUIMachine, IWorkable, IExplosionMachine, IDropSaveMachine {

    @CN("最终效率：%s%%")
    @EN("Final Efficiency: %s%%")
    public static Lang photovoltaicPowerStationInfo1;

    @CN("产能功率：%s/%s EU/t")
    @EN("Generating: %s/%s EU/t")
    public static Lang photovoltaicPowerStationInfo2;

    @CN("有方块阻挡")
    @EN("Shadowed")
    public static Lang photovoltaicPowerStationInfoInvalid;

    @CN("光照过于微弱")
    @EN("At night")
    public static Lang photovoltaicPowerStationInfoNight;

    @CN("损坏度：%s%%")
    @EN("Damage: %s%%")
    public static Lang photovoltaicPowerStationInfoDamage;

    @CN("已修复，当前损坏度：%s%%")
    @EN("Repaired. Damage now %s%%")
    public static Lang photovoltaicPowerStationRepairMessage;

    @CN("损坏度低于 %s%%，无需修复")
    @EN("Damage below %s%%, repair not needed")
    public static Lang photovoltaicPowerStationRepairTooLow;

    @CN("该光伏面板无法使用萤石粉修复")
    @EN("This photovoltaic panel cannot be repaired with Glowstone Dust")
    public static Lang photovoltaicPowerStationRepairGlowstoneRejected;

    // const
    public static final int START_TIME = 23000;
    public static final int END_TIME = 13000;

    /** 每秒损坏度增量区间，实际增量再乘以维度倍率与当前光照强度 */
    private static final float DAMAGE_PER_SECOND_MIN = 0.0001f;
    private static final float DAMAGE_PER_SECOND_RANGE = 0.0015f;
    /** 满损坏时出力衰减到 50% */
    private static final double DAMAGE_MAX_PENALTY = 0.5;
    private static final double DAMAGE_CURVE_K = 8.0;
    private static final double DAMAGE_CURVE_MID = 0.5;

    /** 手持右键时单个材料的修复量 */
    private static final float REPAIR_GLOWSTONE = 0.01f;
    private static final float REPAIR_SUNNARIUM = 0.05f;
    /** 损坏度低于该值时不消耗材料，避免浪费 */
    private static final float REPAIR_MIN_DAMAGE = 0.005f;

    public final int BASIC_RATE;
    /** 损坏速度倍率，1.0 为基准；越低越耐用 */
    public final float damageRate;
    /** 是否允许用萤石粉修复；高级面板只接受阳光化合物粉 */
    public final boolean allowGlowstoneRepair;
    private int rate_mul = 0;

    /** 损坏度，0.0 ~ 1.0；结构解体后保留 */
    @Persisted
    @DescSynced
    private float damage = 0f;

    private long lastOutputEnergy;
    // @Override
    @Getter
    @Setter
    private boolean isWorkingEnabled = true;
    private EnergyContainerList energyContainer;

    public PhotovoltaicPowerStationMachine(IMachineBlockEntity holder, int basicRate, float damageRate,
                                           boolean allowGlowstoneRepair) {
        super(holder);
        BASIC_RATE = basicRate * 1024;
        this.damageRate = damageRate;
        this.allowGlowstoneRepair = allowGlowstoneRepair;
    }

    // 最好成型再用
    public void updateEnergyContainer() {
        List<IEnergyContainer> containers = new ArrayList<>();

        for (IMultiPart part : getParts())
            part.self().holder.self()
                    .getCapability(GTCapability.CAPABILITY_ENERGY_CONTAINER)
                    .ifPresent(containers::add);

        energyContainer = new EnergyContainerList(containers);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (this.isFormed() && getLevel() instanceof ServerLevel serverLevel) {
            updateEnergyContainer();
            serverLevel.getServer().tell(new TickTask(0, this::updateTickSubscription));
        }
    }

    @Override
    public void onUnload() {
        super.onUnload();
        energyContainer = null;
        if (tickSubs != null) {
            tickSubs.unsubscribe();
            tickSubs = null;
        }
    }

    // @Override
    // public boolean checkPattern() {
    // var ret = super.checkPattern();
    // if (!isRemote() && ret && getUpwardsFacing() != Direction.NORTH){
    // getLevel().getServer().submit(
    // () -> doExplosion(10f)
    // );
    //
    // return false;
    // }
    // return ret;
    // }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        if (tickSubs != null) {
            tickSubs.unsubscribe();
            tickSubs = null;
        }
        energyContainer = null;
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        updateEnergyContainer();
        // 计算发电效率
        rate_mul = getDimensionMultiplier(getLevel().dimension());
        if (getLevel() instanceof ServerLevel serverLevel && rate_mul > 0) {
            serverLevel.getServer().tell(new TickTask(0, this::updateTickSubscription));
        }
    }

    /**
     * 维度对太阳能发电的倍率，0 表示该维度无法发电。
     * CTRL 提示文本同样以此为准，避免两处数值漂移。
     */
    public static int getDimensionMultiplier(ResourceKey<Level> dimension) {
        var str = dimension.location().toString();
        if (dimension == Level.OVERWORLD || str.equals("twilightforest:twilight_forest") ||
                str.equals("mythicbotany:alfheim")) {
            return 1;
        } else if (dimension == AetherDimensions.AETHER_LEVEL) {
            return 2;
        } else if (dimension == Planet.MOON || dimension == Planet.MOON_ORBIT) {
            return 2;
        } else if (dimension == Planet.VENUS || dimension == Planet.VENUS_ORBIT) {
            return 2;
        } else if (dimension == Planet.MERCURY || dimension == Planet.MERCURY_ORBIT) {
            return 8;
        } else if (dimension == Planet.MARS || dimension == Planet.MARS_ORBIT) {
            return 8;
        } else if (dimension == Planet.GLACIO || dimension == Planet.GLACIO_ORBIT) {
            return 16;
        }
        return 0;
    }

    /// ///////////////////////////////
    /// / 运行逻辑/ ////
    /// //////////////////////////
    @Nullable
    protected TickableSubscription tickSubs;

    protected void updateTickSubscription() {
        if (isFormed) {
            tickSubs = subscribeServerTick(tickSubs, this::tick);
        } else if (tickSubs != null) {
            tickSubs.unsubscribe();
            tickSubs = null;
        }
    }

    public void tick() {
        var level = getLevel();
        assert level != null;
        if (getPowerState() != Status.VALID) return;
        // 获取时间
        var time = level.getDayTime() % 24000;
        if (time > START_TIME) {
            time -= START_TIME;
        } else if (time < END_TIME) {
            time += 24000 - START_TIME;
        } else return;

        // 计算发电功率
        var sunFactor = Math.sin((double) time / (END_TIME + 24000 - START_TIME) * Math.PI);
        lastOutputEnergy = (long) (sunFactor * BASIC_RATE * rate_mul * getDamageFactor());
        energyContainer.changeEnergy(lastOutputEnergy);

        // 磨损随光照强度变化：正午最快，日出日落趋近于 0，夜晚不磨损
        if (getOffsetTimer() % 20 == 0) {
            damage = Math.min(1f, damage +
                    (DAMAGE_PER_SECOND_MIN + (float) Math.random() * DAMAGE_PER_SECOND_RANGE) * rate_mul *
                            (float) sunFactor * damageRate);
            markDirty();
        }
    }

    /**
     * 损坏度越高出力越低，最低衰减到 {@link #DAMAGE_MAX_PENALTY}。
     * sigmoid 保证低损坏几乎无感，中段平滑陡降，高损坏触底。
     */
    public double getDamageFactor() {
        return 1.0 - DAMAGE_MAX_PENALTY * sigmoid(damage, DAMAGE_CURVE_K, DAMAGE_CURVE_MID);
    }

    private static double sigmoid(double x, double k, double c) {
        return 1.0 / (1.0 + Math.exp(-k * (x - c)));
    }

    /**
     * 手持修复材料右键控制器或结构内的光伏方块时消耗一个并修复。
     * 损坏度低于 {@link #REPAIR_MIN_DAMAGE} 时拒绝修复。
     */
    public InteractionResult tryRepairByHand(Player player, InteractionHand hand) {
        var held = player.getItemInHand(hand);
        if (held.isEmpty()) return InteractionResult.PASS;

        float repaired;
        if (held.getItem() == ChemicalHelper.get(TagPrefix.dust, CTNHMaterials.Sunnarium).getItem()) {
            repaired = REPAIR_SUNNARIUM;
        } else if (held.getItem() == ChemicalHelper.get(TagPrefix.dust, GTMaterials.Glowstone).getItem()) {
            if (!allowGlowstoneRepair) {
                if (getLevel() != null && !getLevel().isClientSide) {
                    player.displayClientMessage(
                            photovoltaicPowerStationRepairGlowstoneRejected.translate().withStyle(ChatFormatting.RED),
                            true);
                }
                return InteractionResult.CONSUME;
            }
            repaired = REPAIR_GLOWSTONE;
        } else {
            return InteractionResult.PASS;
        }

        if (getLevel() != null && getLevel().isClientSide) return InteractionResult.SUCCESS;

        // 损坏度过低时无法修复，材料保留在手上
        if (damage < REPAIR_MIN_DAMAGE) {
            player.displayClientMessage(photovoltaicPowerStationRepairTooLow
                    .translate(String.format("%.2f", REPAIR_MIN_DAMAGE * 100f)), true);
            return InteractionResult.CONSUME;
        }

        if (!player.isCreative()) {
            held.shrink(1);
        }
        damage = Math.max(0f, damage - repaired);
        markDirty();
        player.displayClientMessage(
                photovoltaicPowerStationRepairMessage.translate(String.format("%.2f", damage * 100f)), true);
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult onUse(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand,
                                   BlockHitResult hit) {
        var result = tryRepairByHand(player, hand);
        if (result != InteractionResult.PASS) return result;
        return super.onUse(state, world, pos, player, hand, hit);
    }

    /** 掉落物 NBT 中承载损坏度的键 */
    private static final String DAMAGE_TAG = "ctnh_photovoltaic_damage";

    /**
     * 破坏控制器时把损坏度写进掉落物，防止拆掉主方块重放来重置损坏度。
     * 只写损坏度而不调用默认实现，避免把 isFormed 之类的结构状态一并带进物品。
     */
    @Override
    public void saveToItem(CompoundTag tag) {
        if (damage > 0f) {
            tag.putFloat(DAMAGE_TAG, damage);
        }
    }

    @Override
    public void loadFromItem(CompoundTag tag) {
        if (tag.contains(DAMAGE_TAG)) {
            damage = tag.getFloat(DAMAGE_TAG);
            markDirty();
        }
    }

    @Override
    public int getProgress() {
        return 0;
    }

    @Override
    public int getMaxProgress() {
        return 0;
    }

    @Override
    public boolean isActive() {
        return lastStatus == Status.VALID;
    }

    // 末影b灯怎么讨厌Enum吗
    public enum Status {
        VALID,
        INVALID,
        NIGHT
    }

    Status lastStatus = Status.INVALID;

    private Status getPowerState() {
        var time = Objects.requireNonNull(getLevel()).getDayTime() % 24000;
        if (time % 20 != 0) return lastStatus;
        if (lastStatus != Status.VALID || time % 300 == 0) {// 15秒钟扫一次,绝对不会卡?
            if (time > END_TIME && time < START_TIME) {
                return lastStatus = Status.NIGHT;
            }

            var facing = getFrontFacing();
            var pos = getHolder().pos();
            switch (facing) {
                case NORTH -> {
                    for (var x = -2; x <= 2; x++) {
                        for (var z = 1; z < 6; z++) {
                            if (!getLevel().canSeeSky(pos.offset(x, 7, z))) {
                                return lastStatus = Status.INVALID;
                            }
                        }
                    }
                }
                case SOUTH -> {
                    for (var x = -2; x <= 2; x++) {
                        for (var z = -5; z < 0; z++) {
                            if (!getLevel().canSeeSky(pos.offset(x, 7, z))) {
                                return lastStatus = Status.INVALID;
                            }
                        }
                    }
                }
                case WEST -> {
                    for (var x = 1; x < 6; x++) {
                        for (var z = -2; z <= 2; z++) {
                            if (!getLevel().canSeeSky(pos.offset(x, 7, z))) {
                                return lastStatus = Status.INVALID;
                            }
                        }
                    }
                }
                case EAST -> {
                    for (var x = -5; x < 0; x++) {
                        for (var z = -2; z <= 2; z++) {
                            if (!getLevel().canSeeSky(pos.offset(x, 7, z))) {
                                return lastStatus = Status.INVALID;
                            }
                        }
                    }
                }
            }
        }
        return lastStatus = Status.VALID;
    }

    /// /////////////////
    /// UI //
    /// /////////////////
    @Override
    public void addDisplayText(@NotNull List<Component> textList) {
        if (isFormed()) {
            var valid = getPowerState();
            var voltageName = GTValues.VNF[GTUtil.getTierByVoltage(lastOutputEnergy)];
            MultiblockDisplayText.builder(textList, isFormed())
                    .setWorkingStatus(isWorkingEnabled, valid == Status.VALID)
                    .addWorkingStatusLine();

            if (valid == Status.VALID) {
                // 最终效率已包含日光衰减、维度倍率与损坏衰减
                textList.add(photovoltaicPowerStationInfo1.translate(
                        String.format("%.1f", lastOutputEnergy * 100f / BASIC_RATE)));
                textList.add(photovoltaicPowerStationInfo2.translate(
                        FormattingUtil.formatNumbers(lastOutputEnergy), voltageName));
            } else {
                Lang statusText = valid == Status.INVALID ? photovoltaicPowerStationInfoInvalid :
                        photovoltaicPowerStationInfoNight;
                textList.add(statusText.translate().withStyle(ChatFormatting.RED));
            }

            // 损坏度是玩家唯一需要主动干预的量，任何状态下都要可见
            var damageStyle = damage >= 0.8f ? ChatFormatting.RED :
                    damage >= 0.5f ? ChatFormatting.YELLOW : ChatFormatting.GRAY;
            textList.add(photovoltaicPowerStationInfoDamage.translate(String.format("%.2f", damage * 100f))
                    .withStyle(damageStyle));
        }
    }

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 182 + 8, 117 + 8);
        group.addWidget(new DraggableScrollableWidgetGroup(4, 4, 182, 117).setBackground(getScreenTexture())
                .addWidget(new LabelWidget(4, 5, self().getBlockState().getBlock().getDescriptionId()))
                .addWidget(new ComponentPanelWidget(4, 17, this::addDisplayText)
                        .textSupplier(this.getLevel().isClientSide ? null : this::addDisplayText)
                        .setMaxWidthLimit(200)
                        .clickHandler(this::handleDisplayClick)));
        group.setBackground(GuiTextures.BACKGROUND_INVERSE);
        return group;
    }

    @Override
    public ModularUI createUI(Player entityPlayer) {
        return new ModularUI(198, 208, this, entityPlayer).widget(new FancyMachineUIWidget(this, 198, 208));
    }
}