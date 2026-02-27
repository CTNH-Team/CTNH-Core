package io.github.cpearl0.ctnhcore.mixin.createmetallurgy;

import fr.lucreeper74.createmetallurgy.content.blocks.faucet.FaucetBlockEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = FaucetBlockEntity.class, remap = false)
public class FaucetBlockEntityMixin {
    @Redirect(method = "tryFill", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fluids/capability/IFluidHandler;getFluidInTank(I)Lnet/minecraftforge/fluids/FluidStack;"))
    FluidStack fixGetFluid(IFluidHandler instance, int i){
        for(int index=0; index<instance.getTanks(); index++) {
            if(instance.getFluidInTank(index).isEmpty()) continue;
            return instance.getFluidInTank(index).copy();
        }
        return FluidStack.EMPTY;
    }
}
