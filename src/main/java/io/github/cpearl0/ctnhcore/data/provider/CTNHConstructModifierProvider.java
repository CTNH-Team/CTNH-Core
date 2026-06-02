package io.github.cpearl0.ctnhcore.data.provider;

import io.github.cpearl0.ctnhcore.registry.CTNHConstructModifier;

import net.minecraft.data.PackOutput;

import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;

public final class CTNHConstructModifierProvider extends AbstractModifierProvider {

    public CTNHConstructModifierProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public String getName() {
        return "CTNHConstruct Modifiers";
    }

    @Override
    protected void addModifiers() {
        buildModifier(CTNHConstructModifier.Ids.GLOBAL_TRAVELLER)
                .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .build();
        buildModifier(CTNHConstructModifier.Ids.SNOW_CITY)
                .build();
        buildModifier(CTNHConstructModifier.Ids.FORTIFICATION)
                .build();
    }
}
