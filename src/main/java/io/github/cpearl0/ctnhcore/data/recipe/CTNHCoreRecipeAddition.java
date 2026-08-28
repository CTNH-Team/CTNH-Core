package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.data.recipe.age.*;
import io.github.cpearl0.ctnhcore.data.recipe.chain.*;
import io.github.cpearl0.ctnhcore.data.recipe.cogniassembly.WetwareCircuit;
import io.github.cpearl0.ctnhcore.data.recipe.create.CafeRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.create.CreateOreExcavationRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.create.CreateRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.create.DieselGeneratorRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.generated.HyperRotorRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.immersiveaircraft.ImmersiveAircraftRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.mana.DigesterRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.mana.MiscManaRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.mana.TwistedFusionRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.migrated.AE2ScriptRecipe;
import io.github.cpearl0.ctnhcore.data.recipe.migrated.BioScriptRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.migrated.GtceuScriptRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.modmodify.EIORecipes;
import io.github.cpearl0.ctnhcore.data.recipe.modmodify.omnicells.QuantumOmniRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.multiblock.*;
import io.github.cpearl0.ctnhcore.data.recipe.wood.WoodMachineRecipes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class CTNHCoreRecipeAddition {

    public static void init(Consumer<FinishedRecipe> provider) {
        CTNHCraftingComponents.init();

        WoodMachineRecipes.registerMaterialInfo();
        WoodMachineRecipes.init(provider);
        GtceuAssemblerRecipeFixes.init(provider);

        OreProcessingRecipes.init(provider);
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

        HyperRotorRecipes.registerAll(provider);
        OrdinaryRecipes.init(provider);
        WaferRecipes.init(provider);
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

        PrimitiveKineticAgeRecipes.init(provider);
        LVRecipes.init(provider);
        MVRecipes.init(provider);
        HVRecipes.init(provider);
        EVRecipes.init(provider);
        IVRecipes.init(provider);
        LuVRecipes.init(provider);
        ZPMRecipes.init(provider);
        UVRecipes.init(provider);
        UHVRecipes.init(provider);
        CreateRecipes.init(provider);
        DieselGeneratorRecipes.init(provider);

        ImmersiveAircraftRecipes.init(provider);
        CreateOreExcavationRecipes.init(provider);
        CasingRecipes.init(provider);
        UHVPartsRecipe.init(provider);

        LuvModifyRecipe.init(provider);

        TwistedFusionRecipes.init(provider);
        MiscManaRecipes.init(provider);

        YeastRecipes.init(provider);
        BioChemistryRecipes.init(provider);

        DigesterRecipes.init(provider);

        GtceuScriptRecipes.init(provider);
        BioScriptRecipes.init(provider);
        // AvaritiaScriptRecipes.init(provider);

        HNNRecipes.init(provider);
        // 来自kjs
        OtherRecipesFromKJS.init(provider);
    }
}
