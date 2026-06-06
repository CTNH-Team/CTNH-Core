package io.github.cpearl0.ctnhcore.client.ponder;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.client.ponder.Electric.GregTechMultiblocks;
import io.github.cpearl0.ctnhcore.client.ponder.Electric.NeutronActivator;
import io.github.cpearl0.ctnhcore.client.ponder.Kinetic.*;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.GTNNMultiblocks;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;

import com.gregtechceu.gtceu.common.data.machines.GTMultiMachines;

import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

import org.antarcticgardens.cna.CNABlocks;

public final class CTNHCorePonderScenes {

    private CTNHCorePonderScenes() {}

    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(MultiblocksA.MEADOW.getId())
                .addStoryBoard("meadow/common", Meadow::Common, CTNHCorePonderTags.CTNHPonder);
;
        helper.forComponents(GTMultiMachines.COKE_OVEN.getId())
                .addStoryBoard("coke_oven/common", GregTechMultiblocks::CokeOven, CTNHCorePonderTags.CTNHPonder);

        helper.forComponents(GTMultiMachines.ASSEMBLY_LINE.getId())
                .addStoryBoard("assembly_line/common", GregTechMultiblocks::AssemblyLine, CTNHCorePonderTags.CTNHPonder);

        helper.forComponents(GTNNMultiblocks.NEUTRON_ACTIVATOR.getId())
                .addStoryBoard("neutron_activator/common", NeutronActivator::Common, CTNHCorePonderTags.CTNHPonder);

        helper.forComponents(ResourceLocation.fromNamespaceAndPath("jackseconomy", "mechanical_exporter"))
                .addStoryBoard("mechanicalexporter/common", MechanicalExporter::Common, CTNHCorePonderTags.CTNHPonder);

        CTNHCore.LOGGER.info("Ponder scenes initialized");
    }
}
