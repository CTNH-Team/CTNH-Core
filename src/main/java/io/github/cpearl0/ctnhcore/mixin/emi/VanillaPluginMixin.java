package io.github.cpearl0.ctnhcore.mixin.emi;

import net.minecraft.world.item.Item;

import dev.emi.emi.VanillaPlugin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.Set;

@Mixin(value = VanillaPlugin.class, remap = false)
public class VanillaPluginMixin {

    @Redirect(method = "compressRecipesToTags",
              at = @At(value = "INVOKE", target = "Ljava/util/List;size()I", ordinal = 0))
    private static int sizeUpperBound(List<?> instance, Set<Item> stacks) {
        if (instance.size() > stacks.size()) {
            return Integer.MAX_VALUE;
        }
        return 0;
    }
}
