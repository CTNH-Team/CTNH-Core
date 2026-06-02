package io.github.cpearl0.ctnhcore;

import io.github.cpearl0.ctnhcore.common.tconstruct.materials.CTNHConstructMaterialRecipes;
import io.github.cpearl0.ctnhcore.common.tconstruct.modifier.FortificationModifier;
import io.github.cpearl0.ctnhcore.common.tconstruct.modifier.GlobalTraveller;
import io.github.cpearl0.ctnhcore.common.tconstruct.modifier.SnowcityModifier;
import io.github.cpearl0.ctnhcore.data.provider.CTNHConstructMaterialPartTextureGenerator;
import io.github.cpearl0.ctnhcore.data.provider.CTNHConstructMaterialRenderInfoProvider;
import io.github.cpearl0.ctnhcore.data.provider.CTNHConstructMaterialSpriteProvider;
import io.github.cpearl0.ctnhcore.data.provider.CTNHConstructMaterialStatsProvider;
import io.github.cpearl0.ctnhcore.data.provider.CTNHConstructMaterialsDataProvider;
import io.github.cpearl0.ctnhcore.data.provider.CTNHConstructMaterialsTraitsProvider;
import io.github.cpearl0.ctnhcore.data.provider.CTNHConstructModifierProvider;
import io.github.cpearl0.ctnhcore.data.provider.CTNHConstructToolDefinitionDataProvider;
import io.github.cpearl0.ctnhcore.data.recipe.tconstruct.CTNHConstructCastingRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.tconstruct.CTNHConstructMeltingRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.tconstruct.CTNHConstructModifierRecipes;
import io.github.cpearl0.ctnhcore.registry.CTNHConstructFuel;

import slimeknights.tconstruct.data.resource.TiCDynamicResourceGenerator;
import slimeknights.tconstruct.library.addon.DynamicProviderRegistrar;
import slimeknights.tconstruct.library.addon.ITiCAddon;
import slimeknights.tconstruct.library.addon.ITiCStaticModifierAddon;
import slimeknights.tconstruct.library.addon.TiCAddon;

@TiCAddon(requiredMods = CTNHCore.MODID)
public class CTNHCoreTiCAddon implements ITiCAddon, ITiCStaticModifierAddon {

    @Override
    public String addonModId() {
        return CTNHCore.MODID;
    }

    @Override
    public void registerStaticModifiers(StaticModifierRegistrar registrar) {
        registrar.register("global_traveller", GlobalTraveller::new);
        registrar.register("snow_city", SnowcityModifier::new);
        registrar.register("fortification", FortificationModifier::new);
    }

    @Override
    public void registerDynamicRecipeProviders(DynamicProviderRegistrar registrar) {
        registrar.addProvider("CTNHConstructMaterialRecipes", CTNHConstructMaterialRecipes::new);
        registrar.addProvider("CTNHConstructModifierRecipes", CTNHConstructModifierRecipes::new);
        registrar.addProvider("CTNHConstructMeltingRecipes", CTNHConstructMeltingRecipes::new);
        registrar.addProvider("CTNHConstructCastingRecipes", CTNHConstructCastingRecipes::new);
        registrar.addProvider("CTNHConstructFuel", CTNHConstructFuel::new);
    }

    @Override
    public void registerDynamicTinkeringProviders(DynamicProviderRegistrar registrar) {
        registrar.addProvider("CTNHConstructToolDefinitionDataProvider", CTNHConstructToolDefinitionDataProvider::new);
        registrar.addProvider("CTNHConstructModifierProvider", CTNHConstructModifierProvider::new);
    }

    @Override
    public void registerDynamicMaterialProviders(DynamicProviderRegistrar registrar) {
        registrar.addProvider("CTNHConstructMaterialsDataProvider", CTNHConstructMaterialsDataProvider::new);
        registrar.addProvider("CTNHConstructMaterialsTraitsProvider", CTNHConstructMaterialsTraitsProvider::new);
        registrar.addProvider("CTNHConstructMaterialStatsProvider", CTNHConstructMaterialStatsProvider::new);
    }

    @Override
    public void registerDynamicResourceProviders(DynamicProviderRegistrar registrar) {
        CTNHConstructMaterialSpriteProvider materialSprites = new CTNHConstructMaterialSpriteProvider();
        registrar.addProvider("CTNHConstructMaterialRenderInfoProvider",
                output -> new CTNHConstructMaterialRenderInfoProvider(output, materialSprites,
                        TiCDynamicResourceGenerator.createExistingFileHelperForAddons()));
        registrar.addProvider("CTNHConstructMaterialPartTextureGenerator",
                CTNHConstructMaterialPartTextureGenerator::new);
    }
}
