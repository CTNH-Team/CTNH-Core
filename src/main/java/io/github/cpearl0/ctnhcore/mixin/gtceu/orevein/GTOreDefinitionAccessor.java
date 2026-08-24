package io.github.cpearl0.ctnhcore.mixin.gtceu.orevein;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.generator.VeinGenerator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = GTOreDefinition.class, remap = false)
public interface GTOreDefinitionAccessor {

    @Accessor("veinGenerator")
    void setVeinGenerator(VeinGenerator generator);
}
