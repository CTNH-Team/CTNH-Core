package io.github.cpearl0.ctnhcore.common.machine.cover;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.ICoverable;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.cover.CoverBehavior;
import com.gregtechceu.gtceu.api.cover.CoverDefinition;
import com.gregtechceu.gtceu.api.cover.IUICover;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.TieredEnergyMachine;

import com.lowdragmc.lowdraglib.gui.editor.ColorPattern;
import com.lowdragmc.lowdraglib.gui.texture.GuiTextureGroup;
import com.lowdragmc.lowdraglib.gui.texture.ResourceBorderTexture;
import com.lowdragmc.lowdraglib.gui.texture.TextTexture;
import com.lowdragmc.lowdraglib.gui.widget.*;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.gregtechceu.gtceu.api.capability.GTCapabilityHelper.getEnergyContainer;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CreativeEnergyCover extends CoverBehavior implements IUICover {

    private TickableSubscription subscription;

    @Persisted
    private long energyPerTick;
    @Persisted
    @Getter
    private int tier;
    @Persisted
    @Getter
    private long amperage;
    @Persisted
    private long machineMaxEnergy;

    public CreativeEnergyCover(CoverDefinition definition, ICoverable coverHolder, Direction attachedSide) {
        super(definition, coverHolder, attachedSide);
        this.tier = GTValues.LV;
        this.amperage = 1;
        this.energyPerTick = GTValues.VEX[tier] * amperage;
    }

    @Override
    public Widget createUIWidget() {
        WidgetGroup group = new WidgetGroup(0, 0, 176, 85);

        group.addWidget(new LabelWidget(7, 34, "gtceu.creative.energy.amperage"))
                .addWidget(new ButtonWidget(7, 47, 20, 20,
                        new GuiTextureGroup(ResourceBorderTexture.BUTTON_COMMON, new TextTexture("-")),
                        cd -> setAmperage(--amperage == -1 ? 0 : amperage)))
                .addWidget(new TextFieldWidget(31, 49, 114, 16, () -> String.valueOf(amperage),
                        value -> setAmperage(Integer.parseInt(value))).setNumbersOnly(1, 67108864))
                .addWidget(new ButtonWidget(149, 47, 20, 20,
                        new GuiTextureGroup(ResourceBorderTexture.BUTTON_COMMON, new TextTexture("+")),
                        cd -> {
                            if (amperage < Integer.MAX_VALUE) {
                                setAmperage(++amperage);
                            }
                        }))
                .addWidget(new SelectorWidget(7, 7, 50, 20, Arrays.stream(GTValues.VNF).toList(), -1)
                        .setOnChanged(tier_string -> {
                            setTier(ArrayUtils.indexOf(GTValues.VNF, tier_string));
                        })
                        .setSupplier(() -> GTValues.VNF[tier])
                        .setButtonBackground(ResourceBorderTexture.BUTTON_COMMON)
                        .setBackground(ColorPattern.BLACK.rectTexture())
                        .setValue(GTValues.VNF[tier]));
        return group;
    }

    private void setTier(int i) {
        this.tier = i;
        this.energyPerTick = GTValues.VEX[tier] * amperage;
    }

    private void setAmperage(long l) {
        this.amperage = l;
        this.energyPerTick = GTValues.VEX[tier] * amperage;
    }

    @Override
    public boolean canAttach() {
        var machine = getMachine();
        if (machine instanceof TieredEnergyMachine tieredEnergyMachine &&
                tieredEnergyMachine.energyContainer.getHandlerIO() == IO.IN) {
            var covers = tieredEnergyMachine.getCoverContainer().getCovers();
            for (var cover : covers) {
                if (cover instanceof CreativeEnergyCover) return false;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        updateCoverSub();
    }

    @Override
    public void onRemoved() {
        super.onRemoved();
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void onAttached(ItemStack itemStack, ServerPlayer player) {
        super.onAttached(itemStack, player);
        var machine = getMachine();
        if (machine instanceof TieredEnergyMachine tieredEnergyMachine) {
            this.tier = tieredEnergyMachine.getTier();
            this.energyPerTick = GTValues.VEX[this.tier] * amperage;
            this.machineMaxEnergy = GTValues.VEX[tieredEnergyMachine.getTier()] << 6;
        }
        updateCoverSub();
    }

    private void updateCoverSub() {
        subscription = coverHolder.subscribeServerTick(subscription, this::updateEnergy);
    }

    private void updateEnergy() {
        var energyContainer = getEnergyContainer(coverHolder.getLevel(), coverHolder.getPos(), attachedSide);
        if (energyContainer != null) {
            var changeStored = Math.min(this.machineMaxEnergy - energyContainer.getEnergyStored(), this.energyPerTick);
            if (changeStored <= 0) return;
            energyContainer.addEnergy(changeStored);
        }
        updateCoverSub();
    }

    @Nullable
    private MetaMachine getMachine() {
        return MetaMachine.getMachine(coverHolder.getLevel(), coverHolder.getPos());
    }
}
