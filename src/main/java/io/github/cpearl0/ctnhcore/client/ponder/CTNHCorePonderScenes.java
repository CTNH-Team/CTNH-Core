package io.github.cpearl0.ctnhcore.client.ponder;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.client.ponder.Electric.ChemicalPlant;
import io.github.cpearl0.ctnhcore.client.ponder.Electric.GregTechMultiblocks;
import io.github.cpearl0.ctnhcore.client.ponder.Electric.NeutronActivator;
import io.github.cpearl0.ctnhcore.client.ponder.Kinetic.*;
import io.github.cpearl0.ctnhcore.client.ponder.Misc.Drum;
import io.github.cpearl0.ctnhcore.client.ponder.example.ChemicalReactorUi;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.GTNNMultiblocks;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.machines.GTMultiMachines;

import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public final class CTNHCorePonderScenes {

    private CTNHCorePonderScenes() {}

    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(MultiblocksA.MEADOW.getId())
                .addStoryBoard("meadow/common", Meadow::Common, CTNHCorePonderTags.CTNHPonder);;
        helper.forComponents(
                GTMachines.WOODEN_DRUM.getId(),
                GTMachines.BRONZE_DRUM.getId(),
                GTMachines.STEEL_DRUM.getId(),
                GTMachines.ALUMINIUM_DRUM.getId(),
                GTMachines.STAINLESS_STEEL_DRUM.getId(),
                GTMachines.GOLD_DRUM.getId(),
                GTMachines.TITANIUM_DRUM.getId(),
                GTMachines.TUNGSTENSTEEL_DRUM.getId())
                .addStoryBoard("drum/common", Drum::Common, CTNHCorePonderTags.CTNHPonder);

        helper.forComponents(GTMultiMachines.COKE_OVEN.getId())
                .addStoryBoard("coke_oven/common", GregTechMultiblocks::CokeOven, CTNHCorePonderTags.CTNHPonder);

        helper.forComponents(GTMachines.CHEMICAL_REACTOR[GTValues.LV].getId())
                .addStoryBoard("chemical_reactor_ui/common", ChemicalReactorUi::Common,
                        CTNHCorePonderTags.CTNHPonder);

        helper.forComponents(GTMultiMachines.ASSEMBLY_LINE.getId())
                .addStoryBoard("assembly_line/common", GregTechMultiblocks::AssemblyLine,
                        CTNHCorePonderTags.CTNHPonder);

        helper.forComponents(GTNNMultiblocks.NEUTRON_ACTIVATOR.getId())
                .addStoryBoard("neutron_activator/common", NeutronActivator::Common, CTNHCorePonderTags.CTNHPonder);

        helper.forComponents(GTNNMultiblocks.CHEMICAL_PLANT.getId())
                .addStoryBoard("chemical_plant/common", ChemicalPlant::Common, CTNHCorePonderTags.CTNHPonder);

        helper.forComponents(ResourceLocation.fromNamespaceAndPath("jackseconomy", "mechanical_exporter"))
                .addStoryBoard("mechanicalexporter/common", MechanicalExporter::Common, CTNHCorePonderTags.CTNHPonder);

        CTNHCore.LOGGER.info("Ponder scenes initialized");
    }
}
