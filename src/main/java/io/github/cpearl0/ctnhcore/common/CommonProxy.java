package io.github.cpearl0.ctnhcore.common;

import io.github.cpearl0.ctnhcore.CTNHConfig;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.client.ponder.CTNHCorePonderPlugin;
import io.github.cpearl0.ctnhcore.common.item.MEAdvancedTerminalItem;
import io.github.cpearl0.ctnhcore.common.world.CTNHChunkLoading;
import io.github.cpearl0.ctnhcore.data.CTNHCoreDatagen;
import io.github.cpearl0.ctnhcore.data.materials.AeCrystalScienceMaterials;
import io.github.cpearl0.ctnhcore.data.materials.AeOmniMaterials;
import io.github.cpearl0.ctnhcore.data.recipe.RecipeRemoval;
import io.github.cpearl0.ctnhcore.data.tags.*;
import io.github.cpearl0.ctnhcore.data.worldgen.CTNHBiomeModifiers;
import io.github.cpearl0.ctnhcore.registry.*;
import io.github.cpearl0.ctnhcore.registry.adventure.CTNHEnchantments;
import io.github.cpearl0.ctnhcore.registry.jade.CTNHJadePlugin;
import io.github.cpearl0.ctnhcore.registry.machines.CTNHMachines;
import io.github.cpearl0.ctnhcore.registry.machines.GTMachineModify;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;
import io.github.cpearl0.ctnhcore.registry.material.GTMaterialAddon;
import io.github.cpearl0.ctnhcore.registry.sound.CTNHSoundEvents;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.DimensionMarker;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.category.GTRecipeCategory;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.unification.material.MaterialRegistryManager;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import appeng.api.features.GridLinkables;
import tech.vixhentx.mcmod.ctnhlib.client.ponder.CTNHPonderLang;

import java.util.List;
import java.util.Set;

import static io.github.cpearl0.ctnhcore.registry.CTNHItems.ME_ADVANCED_TERMINAL;

@SuppressWarnings("removal")
public class CommonProxy {

    public CommonProxy() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.register(this);
        init();
    }

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addGenericListener(MachineDefinition.class, CommonProxy::registerMachines);
        modEventBus.addGenericListener(GTRecipeType.class, CommonProxy::registerRecipeTypes);
        modEventBus.addGenericListener(DimensionMarker.class, CommonProxy::registerDimensionMarkers);
        modEventBus.addGenericListener(GTRecipeCategory.class, CommonProxy::onRecipeCategoryRegister);
        modEventBus.addGenericListener(RecipeConditionType.class, CommonProxy::registerRecipeConditions);

        CTNHCreativeModeTabs.init();
        CTNHSoundEvents.SOUND_EVENTS.register(modEventBus);
        CTNHRegistration.REGISTRATE.registerRegistrate();
        CTNHEnchantments.Enchantments.register(modEventBus);
        CTNHRecipes.init(modEventBus);
        CTNHTemperatureModifierRegister.init();
        CTNHCoreDatagen.init();
        CTNHConfig.init();
        CTNHDamageTypes.init();
        CTNHJadePlugin.init();
        RecipeRemoval.init();
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

    public static void registerRecipeConditions(GTCEuAPI.RegisterEvent<ResourceLocation, RecipeConditionType<?>> event) {
        CTNHRecipeConditions.init();
    }

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event) {
        // Clean up stale Forge persistent chunk tickets for ctnhcore on load
        event.enqueueWork(CTNHChunkLoading::registerValidationCallback);
        event.enqueueWork(() -> GTToolType.KNIFE.itemTags.addAll(List.of(
                TagKey.create(Registries.ITEM, new ResourceLocation("farmersdelight", "straw_harvesters")),
                TagKey.create(Registries.ITEM, new ResourceLocation("farmersdelight", "tools/knives")))));

        // Register the ME Advanced Terminal as linkable to a grid via an ME Wireless Access Point
        GridLinkables.register(ME_ADVANCED_TERMINAL, MEAdvancedTerminalItem.LINKABLE_HANDLER);
    }

    @SubscribeEvent
    public void registerMaterial(MaterialRegistryEvent event) {
        MaterialRegistryManager.getInstance().createRegistry(CTNHCore.MODID);
    }

    @SubscribeEvent
    public void registerMaterials(MaterialEvent event) {
        CTNHMaterials.init();
        CTNHMaterials.tagPrefixIgnore();
        GTMaterialAddon.tagPrefixIgnore();
        AeOmniMaterials.tagPrefixIgnore();
        AeCrystalScienceMaterials.tagPrefixIgnore();
        GTMaterialAddon.init();
    }

    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        var registries = event.getLookupProvider();

        if (event.includeServer()) {
            generator.addProvider(true, new CTNHBiomeTagsProvider(packOutput, registries, existingFileHelper));
            generator.addProvider(true, new CTNHExtraItemTagsProvider(packOutput, registries, existingFileHelper));
            generator.addProvider(true, new CTNHExtraBlockTagsProvider(packOutput, registries, existingFileHelper));
            generator.addProvider(true, new CTNHExtraFluidTagsProvider(packOutput, registries, existingFileHelper));
            generator.addProvider(true, new CTNHEntityTypeTagsProvider(packOutput, registries, existingFileHelper));
            generator.addProvider(true, new CTNHBiomeModifiers(packOutput));
            generator.addProvider(true, new net.minecraftforge.common.data.DatapackBuiltinEntriesProvider(
                    packOutput, registries, new net.minecraft.core.RegistrySetBuilder()
                            .add(Registries.DAMAGE_TYPE, CTNHDamageTypes::bootstrap),
                    Set.of(CTNHCore.MODID)));
        }

        if (event.includeClient()) {
            CTNHPonderLang.init(new CTNHCorePonderPlugin());
            GTRegistries.ORE_VEINS.unfreeze();
            CTNHOres.init();
        }
    }
}
