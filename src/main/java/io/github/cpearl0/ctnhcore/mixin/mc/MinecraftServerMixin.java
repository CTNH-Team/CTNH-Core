package io.github.cpearl0.ctnhcore.mixin.mc;

import net.minecraft.server.MinecraftServer;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    // @ModifyArg(method = "*",
    // at = @At(value = "INVOKE",
    // target =
    // "Lnet/minecraft/server/packs/resources/MultiPackResourceManager;<init>(Lnet/minecraft/server/packs/PackType;Ljava/util/List;)V"),
    // index = 1)
    // public List<PackResources> gtceu$injectDynamicData(PackType type, List<PackResources> packs) {
    // List<PackResources> packResources = new ArrayList<>(packs);
    // packResources.addAll(CTNHRegistration.getAllPackResources());
    // return packResources;
    // }
}
