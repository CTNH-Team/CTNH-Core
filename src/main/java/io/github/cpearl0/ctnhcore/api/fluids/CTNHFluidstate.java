package io.github.cpearl0.ctnhcore.api.fluids;

import com.gregtechceu.gtceu.data.recipe.CustomTags;
import io.github.cpearl0.ctnhcore.data.tags.FluidTypeTags;
import lombok.Getter;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

public enum CTNHFluidstate {
    QUANTUM("ctnh.fluid.quantum", FluidTypeTags.QUANTUM_FLUIDS),
    ANTIMATTER("ctnh.fluid.antimatter",FluidTypeTags.ANTI_FLUIDS)
    ;

    @Getter
    private final String translationKey;
    @Getter
    private final TagKey<Fluid> tagKey;

    CTNHFluidstate(@NotNull String translationKey, @NotNull TagKey<Fluid> tagKey) {
        this.translationKey = translationKey;
        this.tagKey = tagKey;
    }
}
