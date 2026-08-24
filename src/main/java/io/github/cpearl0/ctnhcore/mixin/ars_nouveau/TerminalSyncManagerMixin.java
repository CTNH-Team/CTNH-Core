package io.github.cpearl0.ctnhcore.mixin.ars_nouveau;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import com.hollingsworth.arsnouveau.client.container.TerminalSyncManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Keeps arbitrary item NBT in Ars Nouveau's Bookwyrm terminal sync. */
@Mixin(value = TerminalSyncManager.class, remap = false)
public class TerminalSyncManagerMixin {

    @Inject(method = "getSyncTag", at = @At("RETURN"), cancellable = true)
    private static void ctnhcore$syncFullItemTag(ItemStack stack, CallbackInfoReturnable<CompoundTag> cir) {
        if (stack.hasTag()) {
            cir.setReturnValue(stack.getTag().copy());
        }
    }
}
