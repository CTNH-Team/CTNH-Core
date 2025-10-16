package io.github.cpearl0.ctnhcore.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDisplayUIMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredPartMachine;
import com.lowdragmc.lowdraglib.gui.util.ClickData;
import com.lowdragmc.lowdraglib.gui.widget.*;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AsynThreadHatchMachine extends TieredPartMachine implements IFancyUIMachine {
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            AsynThreadHatchMachine.class, MultiblockPartMachine.MANAGED_FIELD_HOLDER
    );

//    @Persisted
//    private int threads = 0;

    public AsynThreadHatchMachine(IMachineBlockEntity holder, int tier) {
        super(holder, tier);
    }

    @DescSynced
    @Persisted
    int rate = 100;

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 182 + 8, 117 + 8);
        group.addWidget(new DraggableScrollableWidgetGroup(4, 4, 182, 117).setBackground(GuiTextures.DISPLAY)
                .addWidget(new LabelWidget(4, 5, self().getBlockState().getBlock().getDescriptionId()))
                .addWidget(new ComponentPanelWidget(4, 17, this::addDisplayText)
                        .textSupplier(this.getLevel().isClientSide ? null : this::addDisplayText)
                        .setMaxWidthLimit(200)
                        .clickHandler(this::handleDisplayClick)))
        ;

        group.setBackground(GuiTextures.BACKGROUND_INVERSE);
        return group;
    }

    void addDisplayText(List<Component> textList) {
        textList.add(Component.literal(rate + "\n"));
        var buttonText = Component.translatable("ctnh.multiblock.underfloor_heating_system.info.rate_modify");
        buttonText.append(" ");
        Component hoverText1 = Component.literal("minus").withStyle(ChatFormatting.GRAY);
        Component hoverText2 = Component.literal("add").withStyle(ChatFormatting.GRAY);
        buttonText.append(ComponentPanelWidget.withButton(
                Component.literal("[-]"), "sub")
                .copy().withStyle(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverText1)))
        );
        buttonText.append(" ");
        buttonText.append(ComponentPanelWidget.withButton(
                Component.literal("[+]"), "add")
                .copy().withStyle(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverText2)))
        );

        textList.add(buttonText);
    }

    void addThreadText(List<Component> textList, int id) {

    }


    void handleDisplayClick(String componentData, ClickData clickData) {
        if (!clickData.isRemote) {
            int result = componentData.equals("add") ? 5 : -5;
            this.rate = Mth.clamp(rate + result, 25, 100);
        }
    }

    @Override
    @NotNull
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public boolean canShared() {
        return false;
    }
}
