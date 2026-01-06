package io.github.cpearl0.ctnhcore.common.gui.rightconfigurator;

import com.gregtechceu.gtceu.api.gui.fancy.FancyMachineUIWidget;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyUIProvider;
import com.lowdragmc.lowdraglib.utils.Position;
import com.lowdragmc.lowdraglib.utils.Size;
import lombok.Getter;

@Getter
public class RCUIWidget extends FancyMachineUIWidget {
    protected final RightConfiguratorPanel rightConfiguratorPanel;

    public RCUIWidget(IFancyUIProvider mainPage, int width, int height) {
        super(mainPage, width, height);
        addWidget(this.rightConfiguratorPanel = new RightConfiguratorPanel(-(24 + 2), height));
    }

    @Override
    protected void setupFancyUI(IFancyUIProvider fancyUI, boolean showInventory) {
        super.setupFancyUI(fancyUI, showInventory);
        var page = fancyUI.createMainPage(this);
        var size = new Size(Math.max(172, page.getSize().width + border * 2),
                Math.max(86, page.getSize().height + border * 2));
        if(fancyUI instanceof IRCFancyUIProvider provider)
            provider.attachRightConfigurators(rightConfiguratorPanel);
        rightConfiguratorPanel
                .setSelfPosition(new Position(size.width + 2, getGui().getHeight() - rightConfiguratorPanel.getSize().height - 4));
    }

    @Override
    protected void clearUI() {
        super.clearUI();
        this.rightConfiguratorPanel.clear();
    }
}
