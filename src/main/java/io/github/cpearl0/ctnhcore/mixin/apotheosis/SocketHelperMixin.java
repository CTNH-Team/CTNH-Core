package io.github.cpearl0.ctnhcore.mixin.apotheosis;

import net.minecraft.world.item.ItemStack;

import dev.shadowsoffire.apotheosis.adventure.socket.SocketHelper;
import dev.shadowsoffire.apotheosis.adventure.socket.SocketedGems;
import dev.shadowsoffire.apotheosis.adventure.socket.gem.GemInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Forces UUID generation for Apotheosis gems on the server side.
 * <p>
 * Apotheosis normally generates gem UUIDs lazily on the client side during tooltip
 * rendering. This can cause Create's logistics system to fail because the client's
 * UUIDs don't match the server's.
 * <p>
 * This mixin forces UUID generation immediately before gems are written to NBT in
 * {@code SocketHelper.setGems()}, ensuring the server-side NBT includes UUIDs.
 */
@Mixin(value = SocketHelper.class, remap = false)
public class SocketHelperMixin {

    /**
     * Before SocketHelper.setGems() serializes gems to NBT, we iterate through each
     * GemInstance and call getUUIDs() to ensure UUIDs are generated and written into
     * the gemStack's NBT. When setGems() later calls gemStack.save(), the UUIDs will
     * be included in the serialized data.
     */
    @Inject(method = "setGems", at = @At("HEAD"), remap = false)
    private static void ctnh$preGenerateUUIDs(ItemStack stack, SocketedGems gems, CallbackInfo ci) {
        for (GemInstance inst : gems) {
            if (inst.isValid()) {
                // Calling getUUIDs() triggers GemItem.getOrCreateUUIDs()
                // which generates UUIDs if missing and writes them directly
                // into gemStack's CompoundTag. Since setGems() will call
                // gemStack.save() later, the UUIDs will be persisted.
                inst.getUUIDs();
            }
        }
    }
}
