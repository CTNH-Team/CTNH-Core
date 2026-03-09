package io.github.cpearl0.ctnhcore.mixin.mc;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(value = TagKey.class)
public class TagKeyMixin {

    @Shadow
    @Final
    private ResourceKey<?> registry;

    @Shadow
    @Final
    private ResourceLocation location;

    @Unique
    private int hash;

    @Inject(method = "<init>", at = @At("TAIL"))
    void initHash(ResourceKey<?> registry, ResourceLocation location, CallbackInfo ci) {
        hash = (registry.location().hashCode() * 31) + location.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TagKey<?> tagKey)) {
            return false;
        }
        if (tagKey.hashCode() != hash) {
            return false;
        }
        return Objects.equals(registry, tagKey.registry()) && Objects.equals(location, tagKey.location());
    }

    @Override
    public int hashCode() {
        return hash;
    }
}
