package io.github.cpearl0.ctnhcore.mixin.createmetallurgy;

import fr.lucreeper74.createmetallurgy.content.blocks.industrial_crucible.CrucibleBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = CrucibleBlockEntity.class, remap = false)
public class CrucibleBlockEntityMixin {

    /**
     * @author luckyblock
     * @reason not high enough
     */
    @Overwrite
    public int getMaxHeight() {
        return 256;
    }
}
