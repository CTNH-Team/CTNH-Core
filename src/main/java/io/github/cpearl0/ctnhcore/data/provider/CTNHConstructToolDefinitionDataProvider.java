package io.github.cpearl0.ctnhcore.data.provider;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.data.PackOutput;

import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;

public final class CTNHConstructToolDefinitionDataProvider extends AbstractToolDefinitionDataProvider {

    public CTNHConstructToolDefinitionDataProvider(PackOutput packOutput) {
        super(packOutput, CTNHCore.MODID);
    }

    @Override
    public String getName() {
        return "CTNHConstruct Tool Definition Recipes";
    }

    @Override
    protected void addToolDefinitions() {}
}
