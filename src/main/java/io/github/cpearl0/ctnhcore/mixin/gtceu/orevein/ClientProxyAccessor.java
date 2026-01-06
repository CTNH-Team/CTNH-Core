package io.github.cpearl0.ctnhcore.mixin.gtceu.orevein;

import io.github.cpearl0.ctnhcore.utils.LayeredBiMap;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.client.ClientProxy;

import net.minecraft.resources.ResourceLocation;

import com.google.common.collect.BiMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ClientProxy.class, remap = false)
public class ClientProxyAccessor {

    @Shadow
    @Final
    @Mutable
    public static BiMap<ResourceLocation, GTOreDefinition> CLIENT_ORE_VEINS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void init(CallbackInfo ci) {
        CLIENT_ORE_VEINS = new LayeredBiMap<>(CLIENT_ORE_VEINS, GTRegistries.ORE_VEINS.registry());
    }
}
