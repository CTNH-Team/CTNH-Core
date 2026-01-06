package io.github.cpearl0.ctnhcore.common.gui;

import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.lowdragmc.lowdraglib.gui.modular.ModularUIGuiContainer;
import com.lowdragmc.lowdraglib.gui.util.DrawerHelper;
import com.lowdragmc.lowdraglib.gui.util.TextFormattingUtil;
import com.lowdragmc.lowdraglib.utils.Position;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.hugehatch.HugeItemBusPartMachine;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Prefix;

import java.util.List;
import java.util.Optional;

@Prefix("hugeslotwidget")
public class HugeSlotWidget extends SlotWidget {

    public HugeSlotWidget(){
        super();
    }

    public HugeSlotWidget(IItemHandlerModifiable itemHandler, int slotIndex, int xPosition, int yPosition,
                      boolean canTakeItems, boolean canPutItems) {
        super(itemHandler, slotIndex, xPosition, yPosition, canTakeItems, canPutItems);
    }

    @Override
    protected Slot createSlot(IItemHandlerModifiable itemHandler, int index) {
        return new HugeWidgetSlotItemHandler(itemHandler, index, 0, 0);
    }

    @EN("Amount: %s / %s")
    @CN("数量：%s / %s")
    static Lang item_amount;

    @Override
    public List<Component> getTooltipTexts() {

        List<Component> tooltips = super.getTooltipTexts();
        if(slotReference != null
                &&slotReference.getItem().getMaxStackSize() != slotReference.getMaxStackSize(slotReference.getItem()))
        {
            tooltips.add(
                    item_amount.translate(
                            FormattingUtil.formatNumbers(slotReference.getItem().getCount()),
                            FormattingUtil.formatNumbers(slotReference.getMaxStackSize(slotReference.getItem()))
                    )
            );
        }
        return tooltips;
    }

    @Override
    public void drawInBackground(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        drawBackgroundTexture(graphics, mouseX, mouseY);
        Position pos = this.getPosition();
        if (this.slotReference != null) {
            ItemStack itemStack = this.getRealStack(this.slotReference.getItem());
            ModularUIGuiContainer modularUIGui = this.gui == null ? null : this.gui.getModularUIGui();
            if (itemStack.isEmpty() && modularUIGui != null && modularUIGui.getQuickCrafting() && modularUIGui.getQuickCraftSlots().contains(this.slotReference)) {
                int splitSize = modularUIGui.getQuickCraftSlots().size();
                itemStack = this.gui.getModularUIContainer().getCarried();
                if (!itemStack.isEmpty() && splitSize > 1 && AbstractContainerMenu.canItemQuickReplace(this.slotReference, itemStack, true)) {
                    itemStack = itemStack.copy();
                    itemStack.grow(AbstractContainerMenu.getQuickCraftPlaceCount(modularUIGui.getQuickCraftSlots(), modularUIGui.dragSplittingLimit, itemStack));
                    int k = Math.min(itemStack.getMaxStackSize(), this.slotReference.getMaxStackSize(itemStack));
                    if (itemStack.getCount() > k) {
                        itemStack.setCount(k);
                    }
                }
            }

            if (!itemStack.isEmpty()) {
                DrawerHelper.drawItemStack(graphics, itemStack, pos.x + 1, pos.y + 1, -1,
                        TextFormattingUtil.formatLongToCompactString(itemStack.getCount(), 3));
            }
        }

        this.drawOverlay(graphics, mouseX, mouseY, partialTicks);
        if (this.drawHoverOverlay && this.isMouseOverElement((double)mouseX, (double)mouseY) && this.getHoverElement((double)mouseX, (double)mouseY) == this) {
            RenderSystem.colorMask(true, true, true, false);
            DrawerHelper.drawSolidRect(graphics, this.getPosition().x + 1, this.getPosition().y + 1, 16, 16, -2130706433);
            RenderSystem.colorMask(true, true, true, true);
        }
    }

    public class HugeWidgetSlotItemHandler extends WidgetSlotItemHandler{

        private final IItemHandlerModifiable itemHandler;
        private final int index;

        public HugeWidgetSlotItemHandler(IItemHandlerModifiable itemHandler, int index, int xPosition, int yPosition) {
            super(itemHandler, index, xPosition, yPosition);
            this.itemHandler = itemHandler;
            this.index = index;
        }

        @Override
        public int getMaxStackSize(@NotNull ItemStack stack) {
            if(itemHandler instanceof HugeItemBusPartMachine.HugeItemStackHandler hugeItemStackHandler)
                return hugeItemStackHandler.getStackLimit(index, stack);
            return super.getMaxStackSize(stack);
        }

        @Override
        public boolean mayPlace(@NotNull ItemStack stack) {
            var stack1 = itemHandler.getStackInSlot(index);
            boolean mayReplace = stack1.getCount() <= stack1.getMaxStackSize() || ItemStack.isSameItemSameTags(stack, stack1);
            return super.mayPlace(stack) && mayReplace;
        }

        @Override
        public Optional<ItemStack> tryRemove(int count, int decrement, Player player) {
            count = Math.min(count, itemHandler.getStackInSlot(index).getMaxStackSize());
            return super.tryRemove(count, decrement, player);
        }
    }
}
