package io.github.cpearl0.ctnhcore.common.gui.rightconfigurator;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.ConfiguratorPanel;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyConfiguratorButton;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.hepdd.gtmthings.api.machine.trait.ProgrammableCircuitHandler;
import com.lowdragmc.lowdraglib.gui.texture.ResourceTexture;
import io.github.cpearl0.ctnhcore.registry.CTNHGuiTextures;
import io.github.cpearl0.ctnhcore.utils.IAllowSameContainer;
import net.minecraft.network.chat.Component;

import java.util.List;

import static io.github.cpearl0.ctnhcore.registry.CTNHGuiTextures.*;

public interface IRCFancyUIProvider {
    default void attachRightConfigurators(RightConfiguratorPanel rightConfiguratorPanel) {
        if(this instanceof MetaMachine machine){
            for(var trait: machine.getTraits()){
                if(trait instanceof IAllowSameContainer container){
                    ResourceTexture button = null;
                    String allow = "", notAllow = "";

                    if(trait instanceof NotifiableItemStackHandler inventory
                            && inventory.getSlots() > 1
                            && inventory.capabilityIO != IO.NONE){
                        if(inventory.capabilityIO ==IO.IN){
                            button = ALLOW_SAME_ITEM_IN;
                            allow = "允许相同物品输入";
                            notAllow = "不允许相同物品输入";
                        }
                        else if(inventory.capabilityIO == IO.OUT){
                            button = ALLOW_SAME_ITEM_OUT;
                            allow = "允许相同物品输出";
                            notAllow = "不允许相同物品输出";
                        }
                    }
                    else if(trait instanceof NotifiableFluidTank tank
                            && tank.getTanks() > 1
                            && tank.getCapabilityIO() != IO.NONE){
                        if(tank.capabilityIO ==IO.IN){
                            button = ALLOW_SAME_FLUID_IN;
                            allow = "允许相同流体输入";
                            notAllow = "不允许相同流体输入";
                        }
                        else if(tank.capabilityIO == IO.OUT){
                            button = ALLOW_SAME_FLUID_OUT;
                            allow = "允许相同流体输出";
                            notAllow = "不允许相同流体输出";
                        }
                    }

                    if(button != null){
                        final String finalAllow = allow;
                        final String finalNotAllow = notAllow;
                        rightConfiguratorPanel.attachConfigurators(
                                new IFancyConfiguratorButton.Toggle(
                                        button.getSubTexture(0, 0.5, 1, 0.5),
                                        button.getSubTexture(0, 0, 1, 0.5),
                                        container::isAllowSame, (clickData, pressed) ->container.setAllowSame(pressed))
                                        .setTooltipsSupplier(pressed -> List.of(
                                                Component.literal(
                                                        pressed ? finalAllow : finalNotAllow)))
                        );
                    }
                }





            }

        }
    }
}
