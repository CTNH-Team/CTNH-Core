package io.github.cpearl0.ctnhcore.common.gui.rightconfigurator;

import io.github.cpearl0.ctnhcore.utils.IAllowSameContainer;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyConfiguratorButton;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;

import com.lowdragmc.lowdraglib.gui.texture.ResourceTexture;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import tech.vixhentx.mcmod.ctnhlib.client.gui.IRCFancyUIProvider;
import tech.vixhentx.mcmod.ctnhlib.client.gui.RightConfiguratorPanel;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.List;

import static io.github.cpearl0.ctnhcore.api.CTNHLangValues.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHGuiTextures.*;

public interface IAllowSameUIProvider extends IRCFancyUIProvider {

    default void attachRightConfigurators(RightConfiguratorPanel panel) {
        if (!(this instanceof MetaMachine machine)) return;

        for (var trait : machine.getTraits()) {
            if (!(trait instanceof IAllowSameContainer container)) continue;

            ConfigButtonData data = resolveButtonData(trait);
            if (data == null) continue;

            panel.attachConfigurators(
                    new IFancyConfiguratorButton.Toggle(
                            data.texture.getSubTexture(0, 0.5, 1, 0.5),
                            data.texture.getSubTexture(0, 0, 1, 0.5),
                            container::isAllowSame,
                            (clickData, pressed) -> container.setAllowSame(pressed)
                    ).setTooltipsSupplier(pressed -> buildTooltips(
                            data.titleLang,
                            data.tooltipLang,
                            pressed
                    ))
            );
        }
    }

    /**
     * 根据 trait 类型解析按钮所需的全部静态数据
     */
    private static ConfigButtonData resolveButtonData(Object trait) {
        if (trait instanceof NotifiableItemStackHandler inv
                && inv.getSlots() > 1
                && inv.capabilityIO != IO.NONE) {

            return switch (inv.capabilityIO) {
                case IN -> new ConfigButtonData(
                        ALLOW_SAME_ITEM_IN,
                        allow_same_item_in_title,
                        allow_same_item_in_tooltip
                );
                case OUT -> new ConfigButtonData(
                        ALLOW_SAME_ITEM_OUT,
                        allow_same_item_out_title,
                        allow_same_item_out_tooltip
                );
                default -> null;
            };
        }

        if (trait instanceof NotifiableFluidTank tank
                && tank.getTanks() > 1
                && tank.getCapabilityIO() != IO.NONE) {

            return switch (tank.getCapabilityIO()) {
                case IN -> new ConfigButtonData(
                        ALLOW_SAME_FLUID_IN,
                        allow_same_fluid_in_title,
                        allow_same_fluid_in_tooltip
                );
                case OUT -> new ConfigButtonData(
                        ALLOW_SAME_FLUID_OUT,
                        allow_same_fluid_out_title,
                        allow_same_fluid_out_tooltip
                );
                default -> null;
            };
        }

        return null;
    }

    /**
     * 每次调用都重新创建 Component，避免任何可变状态泄漏
     */
    private static List<Component> buildTooltips(
            Lang title,
            Lang tooltip,
            boolean pressed
    ) {
        MutableComponent titleLine = title.translate()
                .append(": ")
                .append(pressed ? DISABLED.translate() : ENABLED.translate() );

        MutableComponent tooltipLine = pressed
                ? tooltip.translate()
                : not_allow.translate().copy().append(tooltip.translate());

        return List.of(titleLine, tooltipLine);
    }

    /**
     * UI 所需的纯数据载体（不可变）
     */
    record ConfigButtonData(
            ResourceTexture texture,
            Lang titleLang,
            Lang tooltipLang
    ) {}
}
