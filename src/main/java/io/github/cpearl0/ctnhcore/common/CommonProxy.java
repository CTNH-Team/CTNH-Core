package io.github.cpearl0.ctnhcore.common;

import io.github.cpearl0.ctnhcore.CTNHConfig;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.api.data.material.CTNHPropertyKeys;
import io.github.cpearl0.ctnhcore.common.item.MEAdvancedTerminalItem;
import io.github.cpearl0.ctnhcore.data.CTNHCoreDatagen;
import io.github.cpearl0.ctnhcore.registry.*;
import io.github.cpearl0.ctnhcore.registry.adventure.CTNHEnchantments;
import io.github.cpearl0.ctnhcore.registry.jade.CTNHJadePlugin;
import io.github.cpearl0.ctnhcore.registry.machines.CTNHMachines;
import io.github.cpearl0.ctnhcore.registry.machines.GTMachineModify;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;
import io.github.cpearl0.ctnhcore.registry.material.GTMaterialAddon;
import io.github.cpearl0.ctnhcore.registry.sound.CTNHSoundDefinitionsProvider;
import io.github.cpearl0.ctnhcore.registry.sound.CTNHSoundEvents;
import io.github.cpearl0.ctnhcore.registry.worldgen.*;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.DimensionMarker;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.MaterialProperties;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.category.GTRecipeCategory;
import com.gregtechceu.gtceu.api.recipe.chance.logic.ChanceLogic;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.gregtechceu.gtceu.data.tags.BiomeTagsLoader;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import appeng.api.features.GridLinkables;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

import java.util.Set;

import static io.github.cpearl0.ctnhcore.registry.CTNHItems.ME_ADVANCED_TERMINAL;

@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {

    @SuppressWarnings("removal")
    public CommonProxy() {
        init();
    }

    @SuppressWarnings("removal")
    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addGenericListener(MachineDefinition.class, CommonProxy::registerMachines);
        modEventBus.addGenericListener(GTRecipeType.class, CommonProxy::registerRecipeTypes);
        modEventBus.addGenericListener(DimensionMarker.class, CommonProxy::registerDimensionMarkers);
        modEventBus.addGenericListener(GTRecipeCategory.class, CommonProxy::onRecipeCategoryRegister);
        modEventBus.addGenericListener(ChanceLogic.class, CommonProxy::registerChanceLogic);
        modEventBus.addGenericListener(RecipeConditionType.class, CommonProxy::registerRecipeConditions);

        CTNHCreativeModeTabs.init();
        CTNHRegistration.REGISTRATE.registerRegistrate();
        CTNHSoundEvents.SOUND_EVENTS.register(modEventBus);
        CTNHEnchantments.Enchantments.register(modEventBus);
        CTNHRecipes.init(modEventBus);
        CTNHTemperatureModifierRegister.init();
        CTNHCoreDatagen.init();
        CTNHConfig.init();
        CTNHDamageTypes.init();
        CTNHJadePlugin.init();
    }

    @SubscribeEvent
    public static void addMaterialFlag(MaterialEvent event) {
        GTMaterialAddon.init();
    }

    public static void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        CTNHMachines.init();
        CTNHMultiblockMachines.init();
        GTMachineModify.init();
    }

    public static void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        CTNHRecipeTypes.init();
    }

    public static void onRecipeCategoryRegister(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeCategory> event) {
        CTNHRecipeCategories.init();
    }

    public static void registerDimensionMarkers(GTCEuAPI.RegisterEvent<ResourceLocation, DimensionMarker> event) {
        CTNHDimensionMarkers.init();
    }

    public static void registerChanceLogic(GTCEuAPI.RegisterEvent<ResourceLocation, ChanceLogic> event) {
        CTNHChanceLogic.init();
    }

    public static void registerRecipeConditions(GTCEuAPI.RegisterEvent<ResourceLocation, RecipeConditionType> event) {
        CTNHRecipeConditions.init();
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        // CTNHMaterials.tagPrefixIgnore();
        event.enqueueWork(() -> {
            Regions.register(new CTNHOverworldRegion(2));
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, CTNHCore.MODID,
                    CTNHSurfaceRuleData.customSurface());
        });
        GridLinkables.register(ME_ADVANCED_TERMINAL, MEAdvancedTerminalItem.LINKABLE_HANDLER);
    }

    @SubscribeEvent
    public static void registerMaterials(MaterialEvent event) {
        MaterialProperties.addBaseType(CTNHPropertyKeys.NUCLEAR);
        CTNHMaterials.init();
        CTNHMaterials.tagPrefixIgnore();
        GTMaterialAddon.tagPrefixIgnore();
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        var registries = event.getLookupProvider();
        if (event.includeClient()) {
            generator.addProvider(true,
                    new CTNHSoundDefinitionsProvider(packOutput, CTNHCore.MODID, existingFileHelper));
        }
        if (event.includeServer()) {
            var set = Set.of(CTNHCore.MODID);
            generator.addProvider(true, new BiomeTagsLoader(packOutput, registries, existingFileHelper));
            DatapackBuiltinEntriesProvider provider = generator.addProvider(true, new DatapackBuiltinEntriesProvider(
                    packOutput, registries, new RegistrySetBuilder()
                            .add(Registries.BIOME, CTNHBiomes::bootstrap)
                            .add(Registries.CONFIGURED_FEATURE, CTNHConfiguredFeatures::bootstrap)
                            .add(Registries.PLACED_FEATURE, CTNHPlacements::bootstrap)
                            .add(Registries.DIMENSION_TYPE, CTNHDimensionTypes::bootstrap)
                            .add(Registries.LEVEL_STEM, CTNHDimensions::bootstrap)
                            .add(Registries.NOISE_SETTINGS, CTNHNoiseGenerationSettings::bootstrap)
                            .add(Registries.DENSITY_FUNCTION, CTNHDensityFunctions::bootstrap)
                            .add(Registries.DAMAGE_TYPE, CTNHDamageTypes::bootstrap),
                    set));
        }
    }
}
