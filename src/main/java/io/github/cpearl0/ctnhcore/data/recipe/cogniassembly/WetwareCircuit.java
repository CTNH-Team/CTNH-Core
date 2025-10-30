package io.github.cpearl0.ctnhcore.data.recipe.cogniassembly;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.moguang.ctnhbio.data.recipe.CogniRecipeBuilder;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import dev.shadowsoffire.hostilenetworks.data.ModelTier;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.OrdinaryMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.CTNHMaterials;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.moguang.ctnhbio.registry.CBItems.*;
import static dev.shadowsoffire.hostilenetworks.data.ModelTier.*;
import static net.minecraft.world.entity.EntityType.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.moguang.ctnhbio.data.materials.OrganicMaterials.*;

public class WetwareCircuit {
    public static void init(Consumer<FinishedRecipe> provider) {
        CogniAssembly("cogni_wetware_super_computer")
                .EUt(VA[ZPM])
                .duration(200)
                .setIntermediate(WETWARE_PROCESSOR_COMPUTER_UNFINISHED.get())
                .setFinalOutput(WETWARE_SUPER_COMPUTER_UV.get())
                .MIFStep(SUPERIOR, IRON_GOLEM,
                        WETWARE_PRINTED_CIRCUIT_BOARD, 1,
                        SolderingAlloy.getFluid(1152))
                .MIFStep(
                        SUPERIOR, SLIME,
                        WETWARE_PROCESSOR_ASSEMBLY_ZPM, 1,
                        Healing_Compound.getFluid(1000)
                )
                .MIFStep(SUPERIOR, GUARDIAN,
                        WETWARE_DIODE, 16,
                        Organic_Compound.getFluid(1000))
                .MIFStep(SUPERIOR, SPIDER,
                        CTNHItems.ADVANCED_RAM_CHIP, 16,
                        Toxin_Extract.getFluid(1000))
                .IFStep(ChemicalHelper.get(TagPrefix.wireFine, OrdinaryMaterials.BIO_FLEXIBLE, 64),
                        OrdinaryMaterials.POLYPYRROLE.getFluid(432)
                )
                .IFStep(ChemicalHelper.get(TagPrefix.cableGtDouble, OrdinaryMaterials.BLUE_TITANIUM_ALLOY, 8),
                        Polybenzimidazole.getFluid(432)
                )
                .save(provider);

        CogniAssembly("cogni_wetware_processor_mainframe")
                .EUt(VA[ZPM])
                .duration(300)
                .setIntermediate(WETWARE_PROCESSOR_MAINFRAME_UNFINISHED.get())
                .setFinalOutput(WETWARE_MAINFRAME_UHV.get())
                .MIFStep(
                        SUPERIOR, IRON_GOLEM,
                        ChemicalHelper.get(TagPrefix.frameGt, Tritanium, 1),
                        SolderingAlloy.getFluid(1152))
                .MIFStep(
                        SUPERIOR, SLIME,
                        WETWARE_SUPER_COMPUTER_UV, 2,
                        Healing_Compound.getFluid(1000)
                )
                .MIFStep(
                        SUPERIOR, WITHER_SKELETON,
                        WETWARE_CAPACITOR, 16,
                        Withering_Ooze.getFluid(1000)
                )
                .MIFStep(
                        SUPERIOR, ELDER_GUARDIAN,
                        WETWARE_TRANSISTOR, 16,
                        Primordial_Serum.getFluid(200)
                )
                .MIFStep(
                        SUPERIOR, GLOW_SQUID,
                        WETWARE_RESISTOR, 32,
                        DiethylenetriaminePentaacetonitrile.getFluid(1000)
                )
                .MIFStep(
                        SUPERIOR, SHULKER,
                        ChemicalHelper.get(TagPrefix.plate, Duranium, 8),
                        EnderPearl.getFluid(1152)
                )
                .IFStep(ChemicalHelper.get(TagPrefix.wireFine, OrdinaryMaterials.BIO_FLEXIBLE, 64),
                        OrdinaryMaterials.POLYPYRROLE.getFluid(432)
                )
                .IFStep(ChemicalHelper.get(TagPrefix.cableGtDouble, OrdinaryMaterials.BLUE_TITANIUM_ALLOY, 8),
                        Polybenzimidazole.getFluid(432)
                )
                .save(provider);


    }

    public static CogniRecipeBuilder CogniAssembly(String id){
        return CogniRecipeBuilder.start(
                CTNHCore.id(id),
                CBRecipeTypes.COGNI_ASSEMBLY,
                CBRecipeTypes.COGNI_ASSEMBLY_STEP
        );
    }
}
