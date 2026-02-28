package io.github.cpearl0.ctnhcore;

import io.github.cpearl0.ctnhcore.api.Pattern.CTNHBlockMaps;
import io.github.cpearl0.ctnhcore.data.CTNHBlockInfo;
import io.github.cpearl0.ctnhcore.data.recipe.*;
import io.github.cpearl0.ctnhcore.data.recipe.chain.*;
import io.github.cpearl0.ctnhcore.data.recipe.cogniassembly.WetwareCircuit;
import io.github.cpearl0.ctnhcore.data.recipe.generated.HyperRotorRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.mana.EternalGardenRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.mana.TwistedFusionRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.modmodify.EIORecipes;
import io.github.cpearl0.ctnhcore.data.recipe.modmodify.omnicells.QuantumOmniRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.multiblock.*;
import io.github.cpearl0.ctnhcore.registry.*;
import io.github.cpearl0.ctnhcore.registry.worldgen.AstralBlocks;

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
    public String addonModId() {
        return CTNHCore.MODID;
    }

    @Override
    public void registerTagPrefixes() {
        AstralBlocks.init();
        CTNHBlockInfo.init();
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
        QuantumOmniRecipes.init(provider);
        EUCellRecipes.init(provider);
        // 移植配方
        NaquadahReactorRecipes.init(provider);
        DefaultRecipes.init(provider);
        AlumiumChain.init(provider);
        PlatinumLine.init(provider);
        BrineChain.init(provider);
        FuelChain.init(provider);
        AdAstraRecipes.init(provider);
        NaquadahLine.init(provider);

        CasingRecipes.init(provider);

        LuvModifyRecipe.init(provider);
        HugeHatchRecipes.init(provider);
        TwistedFusionRecipes.init(provider);
        EternalGardenRecipes.init(provider);
    }

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {
        AdAstraRecipes.remove(consumer);
        RecipeRemoval.init(consumer);
    }
}