package io.github.cpearl0.ctnhcore.common.gui;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.FancyMachineUIWidget;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyUIProvider;
import com.gregtechceu.gtceu.api.machine.fancyconfigurator.MachineModeFancyConfigurator;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.lowdragmc.lowdraglib.gui.editor.ColorPattern;
import com.lowdragmc.lowdraglib.gui.texture.*;
import com.lowdragmc.lowdraglib.gui.widget.*;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.CryotheumFreezer;
import io.github.cpearl0.ctnhcore.registry.CTNHGuiTextures;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.ArrayList;
import java.util.List;

public class  MachineModeFancyConfiguratorTest implements IFancyUIProvider {

    protected CryotheumFreezer machine;

    public MachineModeFancyConfiguratorTest(IRecipeLogicMachine machine) {
        if(machine instanceof CryotheumFreezer cmachine)
            this.machine = cmachine;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("gtceu.gui.machinemode.title");
    }

    @Override
    public IGuiTexture getTabIcon() {
        return new ItemStackTexture(CTNHItems.LEVEL_ITEM.get());
    }
    public MutableComponent target() {
        return Component.translatable("ctnh.freezeui.1", machine.speed_up,3.5);
    }
    public MutableComponent target2() {
        return Component.translatable("ctnh.freezeui.3", machine.energy_muti,3.5);
    }
    public MutableComponent target3() {
        return Component.translatable("ctnh.freezeui.4", 2*Math.pow(2,machine.parallel_muti),2*Math.pow(2,10));
    }
    @Override
    public Widget createMainPage(FancyMachineUIWidget widget) {
        var group = new WidgetGroup(0, 0, 200, 200);
        group.setBackground(CTNHGuiTextures.FREEZE_BACKGROUND);
        group.addWidget(new TextTextureWidget(10, 20, 50, 20)
                .setText(() -> machine.provider_a())

        );
        group.addWidget(new ImageWidget(10, 40, 15, 15, CTNHGuiTextures.COLD_ICON)
                .setHoverTooltips("增加冷冻机工作速度，每级增加25%工作速度"));
        var speed_progress=(new ProgressWidget(machine.JEIProgress, 50, 60, 100, 15, new ProgressTexture(CTNHGuiTextures.TEST_BAR,CTNHGuiTextures.TEST_BAR_FULL).setFillDirection(ProgressTexture.FillDirection.LEFT_TO_RIGHT))
                .setHoverTooltips(target())
        );

        var button_m=(new ButtonWidget(10, 60, 15, 15, new GuiTextureGroup(new IGuiTexture[]{ResourceBorderTexture.BUTTON_COMMON, new TextTexture("-")}), clickData ->
                {
                    if (clickData.button == 0) {
                        if(machine.speed_up>1.0)
                        {
                            machine.a += 1;
                            machine.speed_up-=0.25;
                            machine.markDirty();
                        }
                    }
                    machine.markDirty();
                    speed_progress.setHoverTooltips(target());
                }
                ).setHoverTooltips("减少该技能点数")
        );
        var button_plus=(new ButtonWidget(175, 60, 15, 15, new GuiTextureGroup(new IGuiTexture[]{ResourceBorderTexture.BUTTON_COMMON, new TextTexture("+")}), clickData ->
                {
                    if (clickData.button == 0) {
                        if(machine.a>0&&machine.speed_up<=3.5)
                        {
                            machine.a -= 1;
                            machine.speed_up+=0.25;
                            machine.markDirty();
                        }


                    }
                    machine.markDirty();
                    speed_progress.setHoverTooltips(target());
                }
                ).setHoverTooltips("增加该技能点数")
        );


        group.addWidget(new ImageWidget(10, 100-10, 15, 15, CTNHGuiTextures.ENERGY_ICON)
                .setHoverTooltips("增加冷冻机工作效率，每级增加25%能源利用效率（最终能源消耗除以能源利用效率）"));
        var speed_progress2=(new ProgressWidget(machine.JEIProgress2, 50, 120-10, 100, 15, new ProgressTexture(CTNHGuiTextures.TEST_BAR,CTNHGuiTextures.TEST_BAR_FULL).setFillDirection(ProgressTexture.FillDirection.LEFT_TO_RIGHT))
                .setHoverTooltips(target2())
        );
        var button_m2=(new ButtonWidget(10, 120-10, 15, 15, new GuiTextureGroup(new IGuiTexture[]{ResourceBorderTexture.BUTTON_COMMON, new TextTexture("-")}), clickData ->
        {
            if (clickData.button == 0) {
                if(machine.energy_muti>1.0)
                {
                    machine.a += 1;
                    machine.energy_muti-=0.25;
                }


            }
            machine.markDirty();
            speed_progress2.setHoverTooltips(target2());
        }
        ).setHoverTooltips("减少该技能点数")
        );
        var button_plus2=(new ButtonWidget(175, 120-10, 15, 15, new GuiTextureGroup(new IGuiTexture[]{ResourceBorderTexture.BUTTON_COMMON, new TextTexture("+")}), clickData ->
        {
            if (clickData.button == 0) {
                if(machine.a>0&&machine.energy_muti<=3.5)
                {
                    machine.a -= 1;
                    machine.energy_muti+=0.25;
                }


            }
            machine.markDirty();
            speed_progress2.setHoverTooltips(target2());
        }
        ).setHoverTooltips("增加该技能点数")
        );


        group.addWidget(new ImageWidget(10, 140, 15, 15, CTNHGuiTextures.ENERGY_ICON)
                .setHoverTooltips("增加冷冻机并行，每升1级，并行便乘以2"));
        var speed_progress3=(new ProgressWidget(machine.JEIProgress3, 50, 160, 100, 15, new ProgressTexture(CTNHGuiTextures.TEST_BAR,CTNHGuiTextures.TEST_BAR_FULL).setFillDirection(ProgressTexture.FillDirection.LEFT_TO_RIGHT))
                .setHoverTooltips(target3())
        );
        var button_m3=(new ButtonWidget(10, 160, 15, 15, new GuiTextureGroup(new IGuiTexture[]{ResourceBorderTexture.BUTTON_COMMON, new TextTexture("-")}), clickData ->
        {
            if (clickData.button == 0) {
                if(machine.parallel_muti>1)
                {
                    machine.a += 1;
                    machine.parallel_muti-=1;
                }


            }
            machine.markDirty();
            speed_progress3.setHoverTooltips(target3());
        }
        ).setHoverTooltips("减少该技能点数")
        );
        var button_plus3=(new ButtonWidget(175, 160, 15, 15, new GuiTextureGroup(new IGuiTexture[]{ResourceBorderTexture.BUTTON_COMMON, new TextTexture("+")}), clickData ->
        {
            if (clickData.button == 0) {
                if(machine.a>0&&machine.parallel_muti<=10)
                {
                    machine.a -= 1;
                    machine.parallel_muti+=1;
                }


            }
            machine.markDirty();
            speed_progress3.setHoverTooltips(target3());
        }
        ).setHoverTooltips("增加该技能点数")
        );

        group.addWidget(speed_progress);
        group.addWidget(button_plus);
        group.addWidget(button_m);
        group.addWidget(speed_progress2);
        group.addWidget(button_plus2);
        group.addWidget(button_m2);
        group.addWidget(speed_progress3);
        group.addWidget(button_plus3);
        group.addWidget(button_m3);
        return group;
    }

    @Override
    public List<Component> getTabTooltips() {
        List<Component> tooltip = new ArrayList<>();
        tooltip.add(Component.translatable("ctnh.freezeui.2"));
        return tooltip;
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
