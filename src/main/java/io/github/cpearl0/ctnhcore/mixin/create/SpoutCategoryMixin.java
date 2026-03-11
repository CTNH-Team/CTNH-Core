package io.github.cpearl0.ctnhcore.mixin.create;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.compat.jei.category.SpoutCategory;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = SpoutCategory.class, remap = false)
public class SpoutCategoryMixin {

    @Redirect(method = "lambda$consumeRecipes$0",
              at = @At(value = "INVOKE",
                       target = "Lcom/simibubi/create/foundation/fluid/FluidIngredient;fromFluidStack(Lnet/minecraftforge/fluids/FluidStack;)Lcom/simibubi/create/foundation/fluid/FluidIngredient;"))
    private static FluidIngredient resetAmount(FluidStack fluidStack, @Local(argsOnly = true) IFluidHandlerItem fhi) {
        fluidStack.setAmount(fhi.getTankCapacity(0));
        return FluidIngredient.fromFluidStack(fluidStack);
    }
}
