package io.github.cpearl0.ctnhcore.mixin.mc;

import io.github.cpearl0.ctnhcore.registry.CTNHRegistration;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import net.minecraft.server.packs.resources.MultiPackResourceManager;

import java.util.ArrayList;
import java.util.List;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
//    @ModifyArg(method = "reloadResources",
//            at = @At(value = "NEW", target = "net/minecraft/server/packs/resources/MultiPackResourceManager")
//            , index = 1)
//    public List<PackResources> gtceu$injectDynamicData(PackType type, List<PackResources> packs) {
//        List<PackResources> packResources = new ArrayList<>(packs);
//        packResources.addAll(CTNHRegistration.getAllPackResources());
//        return packResources;
//    }
}
