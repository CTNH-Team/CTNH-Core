package io.github.cpearl0.ctnhcore;

import io.github.cpearl0.ctnhcore.api.Pattern.CTNHBlockMaps;
import io.github.cpearl0.ctnhcore.data.recipe.*;
import io.github.cpearl0.ctnhcore.data.recipe.chain.*;
import io.github.cpearl0.ctnhcore.data.recipe.cogniassembly.WetwareCircuit;
import io.github.cpearl0.ctnhcore.data.recipe.create.CafeRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.create.CreateMetallurgyRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.create.CreateOreExcavationRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.create.CreateRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.create.DieselGeneratorRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.generated.HyperRotorRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.immersiveaircraft.ImmersiveAircraftRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.mana.DigesterRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.mana.EternalGardenRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.mana.MiscManaRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.mana.TwistedFusionRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.migrated.AE2ScriptRecipe;
import io.github.cpearl0.ctnhcore.data.recipe.migrated.GtceuScriptRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.modmodify.EIORecipes;
import io.github.cpearl0.ctnhcore.data.recipe.modmodify.omnicells.QuantumOmniRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.multiblock.*;
import io.github.cpearl0.ctnhcore.data.recipe.tconstruct.TConstructRecipes;
import io.github.cpearl0.ctnhcore.registry.*;
import io.github.cpearl0.ctnhcore.registry.machines.CTNHMachines;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

@GTAddon
public class CTNHCoreGTAddon implements IGTAddon {

    @Override
    public GTRegistrate getRegistrate() {
        return CTNHRegistration.REGISTRATE;
    }

    @Override
    public void initializeAddon() {
        CTNHItems.init();
        CTNHBlocks.init();
        CTNHBlockEntities.init();
        CTNHBlockMaps.initBlocks();
    }

    @Override
    public void registerCovers() {
        CTNHMachines.initCovers();
    }

    @Override
    public String addonModId() {
        return CTNHCore.MODID;
    }

    @Override
    public void registerTagPrefixes() {
        CTNHTagPrefixes.init();
    }

    @Override
    public void registerElements() {
        CTNHElements.init();
    }

    @Override
    public void registerOreVeins() {
        CTNHOres.init();
    }

    @Override
    public void registerFluidVeins() {
        CTNHFluidVeins.init();
    }

    @Override
    public void registerWorldgenLayers() {
        CTNHWorldgenLayers.init();
    }

    @Override
    public void registerSounds() {}

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        CTNHCraftingComponents.init();

        UnderfloorHeatingSystemRecipes.init(provider);
        AstronomicalObservatoryRecipes.init(provider);
        PersonalComputerRecipes.init(provider);
        SlaughterHouseRecipes.init(provider);

        PhotovoltaicStationRecipes.init(provider);
        ChemConsumerRecipes.init(provider);
        WaterPowerStationRecipes.init(provider);
        ArcGeneratorRecipes.init(provider);
        MeadowRecipes.init(provider);
        SinteringRecipes.init(provider);
        Sinope_recipes.init(provider);
        MachinesRecipes.init(provider);
        AcceleratorRecipes.init(provider);
        // NuclearRecipes.init(provider);
        TurbineRecipes.init(provider);
        HighPerformanceComputerRecipes.init(provider);

        HyperRotorRecipes.registerAll(provider);
        OrdinaryRecipes.init(provider);
        AssemblyLineRecipes.init(provider);

        // 兼容 mod 配方
        WetwareCircuit.init(provider);
        EIORecipes.init(provider);
        AeCrystalScienceRecipes.init(provider);
        AE2ScriptRecipe.init(provider);
        QuantumOmniRecipes.init(provider);
        EUCellRecipes.init(provider);
        // 移植配方
        GeneralCircuitRecipes.init(provider);
        NanoRecipes.init(provider);
        GTPortalRecipes.init(provider);
        DecorateBlockRecipes.init(provider);
        FunctionalStorageRecipes.init(provider);
        CafeRecipes.init(provider);
        SunRecipes.init(provider);
        TinkersRecipes.init(provider);
        SophisticatedStorageRecipes.init(provider);
        ImmersiveAircraftRecipes.init(provider);
        NaquadahReactorRecipes.init(provider);
        DefaultRecipes.init(provider);
        CrafttableScriptRecipe.init(provider);
        ApothesisScriptRecipe.init(provider);
        SophisticatedBackpacksScriptRecipe.init(provider);
        AlumiumChain.init(provider);
        PlatinumLine.init(provider);
        BrineChain.init(provider);
        FuelChain.init(provider);
        AdAstraRecipes.init(provider);
        NaquadahLine.init(provider);
        CementChain.init(provider);

        AlumiumChain.init(provider);
        BrineChain.init(provider);
        CementChain.init(provider);
        ChromiteChain.init(provider);
        CoalChain.init(provider);
        ColorfulsocChain.init(provider);
        FuelChain.init(provider);
        FuelRefiningChain.init(provider);
        GeyanChain.init(provider);
        GoldChain.init(provider);
        GraphiteChain.init(provider);
        IodineChain.init(provider);
        NaquadahLine.init(provider);
        PlatinumLine.init(provider);
        RareearthChain.init(provider);
        SeleniumTelluriumChain.init(provider);
        SiliconChain.init(provider);
        SnowAdjust.init(provider);
        SpaceFabric.init(provider);
        StonedustChain.init(provider);
        TantaliteChain.init(provider);
        TiChain.init(provider);
        WChain.init(provider);
        WoodChain.init(provider);
        ZirconChain.init(provider);

        CreateRecipes.init(provider);
        DieselGeneratorRecipes.init(provider);

        ImmersiveAircraftRecipes.init(provider);
        CreateOreExcavationRecipes.init(provider);
        CreateMetallurgyRecipes.init(provider);
        TConstructRecipes.init(provider);

        CasingRecipes.init(provider);
        UHVPartsRecipe.init(provider);

        LuvModifyRecipe.init(provider);
        HugeHatchRecipes.init(provider);
        TwistedFusionRecipes.init(provider);
        EternalGardenRecipes.init(provider);
        MiscManaRecipes.init(provider);

        YeastRecipes.init(provider);
        BioChemistryRecipes.init(provider);

        DigesterRecipes.init(provider);

        GtceuScriptRecipes.init(provider);
    }

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {
        RecipeRemoval.init(consumer);
    }
}
