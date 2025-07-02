package io.github.cpearl0.ctnhcore.common.gui;

import com.gregtechceu.gtceu.api.gui.fancy.FancyMachineUIWidget;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyUIProvider;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.gui.texture.ItemStackTexture;
import com.lowdragmc.lowdraglib.gui.widget.TextFieldWidget;
import com.lowdragmc.lowdraglib.gui.widget.TextTextureWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.CryotheumFreezer;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.WideParticleAccelerator;
import io.github.cpearl0.ctnhcore.registry.CTNHGuiTextures;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;

public class WPAAcceleratorGui implements IFancyUIProvider {
    protected WideParticleAccelerator machine;

    public WPAAcceleratorGui(IRecipeLogicMachine machine) {
        if(machine instanceof WideParticleAccelerator cmachine)
            this.machine = cmachine;

    }
    public MutableComponent get_mode() {
        if (machine.reverse == -1) return Component.translatable("ctnh.wpa.mode.1");
        else return Component.translatable("ctnh.wpa.mode.2");
    }
    public void set_nu_value(String value)
    {
        if (value.isEmpty()) return;
        machine.nu_value = Integer.parseInt(value);
    }
    public void set_proton_value(String value)
    {
        if (value.isEmpty()) return;
        machine.proton_value = Integer.parseInt(value);
    }
    public void set_electric_value(String value)
    {
        if (value.isEmpty()) return;
        machine.electric_value = Integer.parseInt(value);
    }
    @Override
    public Widget createMainPage(FancyMachineUIWidget widget) {
        var group = new WidgetGroup(0, 0, 200, 200);
        group.setBackground(CTNHGuiTextures.FREEZE_BACKGROUND);
        group.addWidget(new TextTextureWidget(10, 20, 50, 20)
                .setText(() -> get_mode())

        );
        group.addWidget(new TextTextureWidget(10, 40, 40, 15)
                .setText(Component.translatable("ctnh.wpa.wide.1")));
        group.addWidget(new TextFieldWidget(10, 70, 150, 10, () -> String.valueOf(machine.nu_value), this::set_nu_value)
                .setMaxStringLength(11)
                .setNumbersOnly(0, 100)
                .setHoverTooltips(Component.translatable("ctnh.wpa.value"))
        );
        return group;
    }



    @Override
    public List<Component> getTabTooltips() {
        List<Component> tooltip = new ArrayList<>();
        tooltip.add(Component.translatable("ctnh.freezeui.2"));
        return tooltip;
    }
    @Override
    public Component getTitle() {
        return Component.translatable("gtceu.gui.machinemode.title");
    }

    @Override
    public IGuiTexture getTabIcon() {
        return new ItemStackTexture(CTNHItems.LEVEL_ITEM.get());
    }
}
