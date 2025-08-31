package io.github.cpearl0.ctnhcore.mixin.mc;

import com.mojang.datafixers.util.Pair;
import io.github.cpearl0.ctnhcore.registry.CTNHRegistration;
import net.minecraft.server.WorldLoader;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.CloseableResourceManager;
import net.minecraft.server.packs.resources.MultiPackResourceManager;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.WorldDataConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.List;

@Mixin(WorldLoader.PackConfig.class)
public class WorldLoaderPackConfigMixin {
    @ModifyArg(method = "*",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/server/packs/resources/MultiPackResourceManager;<init>(Lnet/minecraft/server/packs/PackType;Ljava/util/List;)V"),
            index = 1)
    public List<PackResources> gtceu$injectDynamicData(PackType type, List<PackResources> packs) {
        List<PackResources> packResources = new ArrayList<>(packs);
        packResources.addAll(CTNHRegistration.getAllPackResources());
        return packResources;
    }
}
