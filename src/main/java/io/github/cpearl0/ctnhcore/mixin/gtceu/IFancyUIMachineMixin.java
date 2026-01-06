package io.github.cpearl0.ctnhcore.mixin.gtceu;

import com.gregtechceu.gtceu.api.gui.fancy.IFancyUIProvider;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.IUIMachine;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import io.github.cpearl0.ctnhcore.common.gui.rightconfigurator.IRCFancyUIProvider;
import io.github.cpearl0.ctnhcore.common.gui.rightconfigurator.RCUIWidget;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = IFancyUIMachine.class, remap = false)
public interface IFancyUIMachineMixin extends IRCFancyUIProvider, IUIMachine, IFancyUIProvider {

    /**
     * @author
     * @reason
     */
    @Overwrite
    default ModularUI createUI(Player entityPlayer) {
        return new ModularUI(176, 166, this, entityPlayer).widget(new RCUIWidget(this, 176, 166));
    }
}
