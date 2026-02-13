package io.github.cpearl0.ctnhcore.mixin.emi;

import com.gregtechceu.gtceu.common.data.GTItems;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.stack.FluidEmiStack;
import dev.emi.emi.screen.EmiScreenManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = EmiScreenManager.class, remap = false)
public abstract class EmiScreenManagerMixin {

    @Shadow
    private static Minecraft client;

    @Redirect(method = "give",
              at = @At(value = "INVOKE",
                       target = "Ldev/emi/emi/api/stack/EmiStack;getItemStack()Lnet/minecraft/world/item/ItemStack;"))
    private static ItemStack allowFluidStack(EmiStack stack, @Share("realStack") LocalRef<ItemStack> realStackRef) {
        if (realStackRef.get() != null)
            return realStackRef.get();
        ItemStack realStack = ItemStack.EMPTY;
        if (stack.getItemStack().isEmpty() && stack instanceof FluidEmiStack fluidEmiStack) {
            if (fluidEmiStack.getKey() instanceof Fluid fluid) {
                // if(client.player != null){
                // ItemStack cursor = client.player.containerMenu.getCarried().copy();
                // cursor.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(p -> {
                // p.fill(new FluidStack(fluid, Integer.MAX_VALUE), IFluidHandler.FluidAction.EXECUTE);
                // realStackRef.set(cursor);
                // });
                // if(realStackRef.get() != null) return realStackRef.get();
                // }

                // if(fluid.getBucket() != Items.AIR){
                // realStack = new ItemStack(fluid.getBucket());
                // }
                // else {
                // var container = GTItems.FLUID_CELL.asStack();
                // container.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(
                // p -> p.fill(new FluidStack(fluid, 1000), IFluidHandler.FluidAction.EXECUTE)
                // );
                // realStack = container;
                // }
                var container = GTItems.FLUID_CELL.asStack();
                container.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(
                        p -> p.fill(new FluidStack(fluid, 1000, fluidEmiStack.getNbt()), IFluidHandler.FluidAction.EXECUTE));
                realStack = container;
            }
        } else {
            realStack = stack.getItemStack();
        }
        realStackRef.set(realStack);
        return realStack;
    }

    // @Inject(method = "deleteCursor", at = @At(value = "INVOKE", target =
    // "Ldev/emi/emi/screen/EmiScreenManager;getHoveredSpace(II)Ldev/emi/emi/screen/EmiScreenManager$ScreenSpace;"),
    // cancellable = true)
    // private static void fillContainer(int mx, int my,
    // CallbackInfoReturnable<Boolean> cir,
    // @Local(name = "cursor") ItemStack cursor,
    // @Local(name = "handled") AbstractContainerScreen<?> handled
    // ){
    // var stacks = EmiScreenManager.getHoveredStack(mx, my, true).getStack().getEmiStacks();
    // if(stacks.size() == 1 && stacks.get(0) instanceof FluidEmiStack fluidEmiStack){
    // cursor.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(
    // c -> {
    // c.fill(new FluidStack((Fluid) fluidEmiStack.getKey(), Integer.MAX_VALUE), IFluidHandler.FluidAction.EXECUTE);
    // handled.getMenu().setCarried(cursor);
    // EmiNetwork.sendToServer(new CreateItemC2SPacket(1, cursor));
    // cir.setReturnValue(true);
    // }
    // );
    // }
    //
    // }
}
