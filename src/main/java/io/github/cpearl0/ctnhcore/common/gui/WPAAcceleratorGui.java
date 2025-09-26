package io.github.cpearl0.ctnhcore.common.gui;

import com.gregtechceu.gtceu.api.gui.fancy.FancyMachineUIWidget;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyUIProvider;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.lowdragmc.lowdraglib.gui.texture.*;
import com.lowdragmc.lowdraglib.gui.widget.*;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.WideParticleAccelerator;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.CTNHGuiTextures;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class WPAAcceleratorGui implements IFancyUIProvider {
    protected WideParticleAccelerator machine;

    public WPAAcceleratorGui(IRecipeLogicMachine machine) {
        if(machine instanceof WideParticleAccelerator cmachine)
            this.machine = cmachine;
    }
    public MutableComponent get_mode() {
        return(Component.translatable("ctnh.multiblock.wide_accelerator.info.power",(double)machine.store_energy/1000000,(double)machine.max_energy/1000000));
    }
    public Function<Double, String> target() {
        return speed->Component.translatable("ctnh.multiblock.wide_accelerator.info.electric_speed",speed*5000).getString();
    }
    public Function<Double, String> target2() {
        return speed->Component.translatable("ctnh.multiblock.wide_accelerator.info.nu_speed", speed*5000).getString();
    }
    public Function<Double, String> target3() {
        return speed->Component.translatable("ctnh.multiblock.wide_accelerator.info.proton_speed", speed*5000).getString();
    }
    public void change_electric(int add,boolean reverse)
    {
        if(!reverse)
        {
            if(machine.electric_speed<5000&&machine.store_energy>10000000L*add)
            {
                machine.electric_speed += add;
                machine.electric_speed=Math.min(machine.electric_speed,5000);
                machine.store_energy -= 10000000L*add;
            }
        }
        if(reverse)
        {
            if(machine.electric_speed>0&&machine.store_energy>10000000L*add)
            {
                machine.electric_speed -= add;
                machine.electric_speed=Math.max(machine.electric_speed,0);
                machine.store_energy -= 10000000L*add;
            }
        }

    }
    public void change_proton(int add,boolean reverse)
    {
        if(!reverse)
        {
            if(machine.proton_speed<5000&&machine.store_energy>10000000L*add)
            {
                machine.proton_speed += add;
                machine.proton_speed=Math.min(machine.proton_speed,5000);
                machine.store_energy -= 10000000L*add;
            }
        }
        if(reverse)
        {
            if(machine.proton_speed>0&&machine.store_energy>10000000L*add)
            {
                machine.proton_speed -= add;
                machine.proton_speed=Math.max(machine.proton_speed,0);
                machine.store_energy -= 10000000L*add;
            }
        }
    }
    public void change_nu(int add,boolean reverse)
    {
        if(!reverse)
        {
            if(machine.nu_speed<5000&&machine.store_energy>10000000L*add)
            {
                machine.nu_speed += add;
                machine.nu_speed=Math.min(machine.nu_speed,5000);
                machine.store_energy -= 10000000L*add;
            }
        }
        if(reverse)
        {
            if(machine.nu_speed>0&&machine.store_energy>10000000L*add)
            {
                machine.nu_speed -= add;
                machine.nu_speed=Math.max(machine.nu_speed,0);
                machine.store_energy -= 10000000L*add;
            }
        }
    }

    @Override
    public Widget createMainPage(FancyMachineUIWidget widget) {
        var group = new WidgetGroup(0, 0, 200, 200);
        group.setBackground(CTNHGuiTextures.FREEZE_BACKGROUND);
        group.addWidget(new TextTextureWidget(10, 20, 120, 20)
                .setText(() -> get_mode())
        );
//        group.addWidget(new TextFieldWidget(10, 70, 150, 10, () -> String.valueOf(machine.nu_value), this::set_nu_value)
//                .setMaxStringLength(11)
//                .setNumbersOnly(0, 100)
//                .setHoverTooltips(Component.translatable("ctnh.wpa.value"))
//        );
        /// ///////////////////////////////
        /// /        电子轨道逻辑/       ////
        /// //////////////////////////
        group.addWidget(new TextTextureWidget(10, 40, 40, 15)
                .setText(Component.translatable("ctnh.multiblock.wide_accelerator.gui.electric")));

        var speed_progress=(new ProgressWidget(machine.get_electric, 50, 60, 100, 15, new ProgressTexture(CTNHGuiTextures.TEST_BAR,CTNHGuiTextures.TEST_BAR_FULL).setFillDirection(ProgressTexture.FillDirection.LEFT_TO_RIGHT)
        )
                .setDynamicHoverTips(target())
        );
        var button_m=(new ButtonWidget(10, 60, 15, 15, new GuiTextureGroup(new IGuiTexture[]{ResourceBorderTexture.BUTTON_COMMON, new TextTexture("-")}), clickData ->
        {
            if (clickData.button == 0) {
                if(clickData.isShiftClick)
                {
                    change_electric(10,true);
                }
                else if(clickData.isCtrlClick)
                {
                    change_electric(100,true);
                }
                else
                {
                    change_electric(1,true);
                }
            }
            machine.markDirty();
        }
        ).setHoverTooltips("减少电子速度")
        );
        var button_plus=(new ButtonWidget(175, 60, 15, 15, new GuiTextureGroup(new IGuiTexture[]{ResourceBorderTexture.BUTTON_COMMON, new TextTexture("+")}), clickData ->
        {
            if (clickData.button == 0) {
                if(clickData.isShiftClick)
                {
                    change_electric(10,false);
                }
                else if(clickData.isCtrlClick)
                {
                    change_electric(100,false);
                }
                else
                {
                    change_electric(1,false);
                }
            }
            machine.markDirty();
        }
        ).setHoverTooltips("增加电子速度")
        );
        
        
        /// ///////////////////////////////
        /// /        中子轨道逻辑/       ////
        /// //////////////////////////
        ///
        group.addWidget(new TextTextureWidget(10, 120-35, 40, 15)
                .setText(Component.translatable("ctnh.multiblock.wide_accelerator.gui.nu")));
        var speed_progress2=(new ProgressWidget(machine.get_nu, 50, 120-10, 100, 15, new ProgressTexture(CTNHGuiTextures.TEST_BAR,CTNHGuiTextures.TEST_BAR_FULL).setFillDirection(ProgressTexture.FillDirection.LEFT_TO_RIGHT)
        )
                .setDynamicHoverTips(target2())
        );
        var button_m2=(new ButtonWidget(10, 120-10, 15, 15, new GuiTextureGroup(new IGuiTexture[]{ResourceBorderTexture.BUTTON_COMMON, new TextTexture("-")}), clickData ->
        {
            if (clickData.button == 0) {
                if(clickData.isShiftClick)
                {
                    change_nu(10,true);
                }
                else if(clickData.isCtrlClick)
                {
                    change_nu(100,true);
                }
                else
                {
                    change_nu(1,true);
                }
            }
            machine.markDirty();
        }
        ).setHoverTooltips("减少中子速度")
        );
        var button_plus2=(new ButtonWidget(175, 120-10, 15, 15, new GuiTextureGroup(new IGuiTexture[]{ResourceBorderTexture.BUTTON_COMMON, new TextTexture("+")}), clickData ->
        {
            if (clickData.button == 0) {
                if(clickData.isShiftClick)
                {
                    change_nu(10,false);
                }
                else if(clickData.isCtrlClick)
                {
                    change_nu(100,false);
                }
                else
                {
                    change_nu(1,false);
                }
            }
            machine.markDirty();
        }
        ).setHoverTooltips("增加中子速度")
        );
        /// ///////////////////////////////
        /// /        原子轨道逻辑/       ////
        /// //////////////////////////
        group.addWidget(new TextTextureWidget(10, 140, 40, 15)
                .setText(Component.translatable("ctnh.multiblock.wide_accelerator.gui.proton")));
        var speed_progress3=(new ProgressWidget(machine.get_proton, 50, 160, 100, 15, new ProgressTexture(CTNHGuiTextures.TEST_BAR,CTNHGuiTextures.TEST_BAR_FULL).setFillDirection(ProgressTexture.FillDirection.LEFT_TO_RIGHT)
        )
                .setDynamicHoverTips(target3())
        );
        var button_m3=(new ButtonWidget(10, 160, 15, 15, new GuiTextureGroup(new IGuiTexture[]{ResourceBorderTexture.BUTTON_COMMON, new TextTexture("-")}), clickData ->
        {
            if (clickData.button == 0) {
                if(clickData.isShiftClick)
                {
                    change_proton(10,true);
                }
                else if(clickData.isCtrlClick)
                {
                    change_proton(100,true);
                }
                else
                {
                    change_proton(1,true);
                }
            }
            machine.markDirty();
        }
        ).setHoverTooltips("减少原子速度")
        );
        var button_plus3=(new ButtonWidget(175, 160, 15, 15, new GuiTextureGroup(new IGuiTexture[]{ResourceBorderTexture.BUTTON_COMMON, new TextTexture("+")}), clickData ->
        {
            if (clickData.button == 0) {
                if(clickData.isShiftClick)
                {
                    change_proton(10,false);
                }
                else if(clickData.isCtrlClick)
                {
                    change_proton(100,false);
                }
                else
                {
                    change_proton(1,false);
                }
            }
            machine.markDirty();
        }

        ).setHoverTooltips("增加原子速度")
        );

        /// ///////////////////////////////
        /// /        增加显示界面/       ////
        /// //////////////////////////
        ///
        machine.markDirty();
        group.addWidget(speed_progress2);
        group.addWidget(button_m2);
        group.addWidget(button_plus2);
        group.addWidget(speed_progress);
        group.addWidget(button_m);
        group.addWidget(button_plus);
        group.addWidget(speed_progress3);
        group.addWidget(button_m3);
        group.addWidget(button_plus3);
        return group;
    }



    @Override
    public List<Component> getTabTooltips() {
        List<Component> tooltip = new ArrayList<>();
        tooltip.add(Component.translatable("ctnh.multiblock.wide_accelerator.gui.name"));
        return tooltip;
    }
    @Override
    public Component getTitle() {
        return Component.translatable("gtceu.gui.machinemode.title");
    }

    @Override
    public IGuiTexture getTabIcon() {
        return new ItemStackTexture(CTNHBlocks.WIDESPEEDINGPIPE.asItem());
    }
    public class MachineModeConfigurator extends WidgetGroup {

        public MachineModeConfigurator(int x, int y, int width, int height) {
            super(x, y, width, height);
        }

        @Override
        public void writeInitialData(FriendlyByteBuf buffer) {
            buffer.writeVarInt(machine.getActiveRecipeType());
        }

        @Override
        public void readInitialData(FriendlyByteBuf buffer) {
            machine.setActiveRecipeType(buffer.readVarInt());
        }

        @Override
        public void detectAndSendChanges() {
            this.writeUpdateInfo(0, buf -> buf.writeVarInt(machine.getActiveRecipeType()));
        }

        @Override
        public void readUpdateInfo(int id, FriendlyByteBuf buffer) {
            if (id == 0) {
                machine.setActiveRecipeType(buffer.readVarInt());
            }
        }
    }
}
