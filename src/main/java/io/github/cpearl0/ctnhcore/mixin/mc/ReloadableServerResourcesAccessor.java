package io.github.cpearl0.ctnhcore.mixin.mc;

import net.minecraft.server.ReloadableServerResources;
import net.minecraft.tags.TagManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Accessor for ReloadableServerResources.tagManager（private 字段）。
 */
@Mixin(ReloadableServerResources.class)
public interface ReloadableServerResourcesAccessor {

    @Accessor("tagManager")
    TagManager ctnhcore$getTagManager();
}
