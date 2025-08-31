package io.github.cpearl0.ctnhcore.mixin.gtceu;

import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.tterrag.registrate.util.entry.BlockEntry;
import io.github.cpearl0.ctnhcore.api.Pattern.CTNHBlockMaps;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GTBlocks.class)
public class GTBlocksMixin {
    @Inject(method = "createMachineCasingBlock", at = @At("TAIL"), remap = false)
    private static void createMachineCasingBlock(int tier, CallbackInfoReturnable<BlockEntry<Block>> cir) {
        var block = cir.getReturnValue();
        CTNHBlockMaps.MachineCasingBlock.put(tier, block);
    }
}
