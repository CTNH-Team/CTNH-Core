package io.github.cpearl0.ctnhcore.mixin.aecs;

import dev.emi.emi.api.EmiRegistry;
import io.github.lounode.ae2cs.integration.emi.EmiPlugin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = EmiPlugin.class, remap = false)
public class EmiPluginMixin {

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void register(EmiRegistry registry) {}
}
