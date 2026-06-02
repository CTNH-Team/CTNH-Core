package io.github.cpearl0.ctnhcore.client.ponder;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.client.ponder.Electric.CarbonBrushes;
import io.github.cpearl0.ctnhcore.client.ponder.Kinetic.*;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;

import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

import com.mo_guang.ctpp.registry.CTPPMachines;
import com.mo_guang.ctpp.registry.CTPPMultiblockMachines;
import org.antarcticgardens.cna.CNABlocks;

public final class CTNHCorePonderScenes {

    private CTNHCorePonderScenes() {}

    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(CTPPMultiblockMachines.BIG_DAM.getId())
                .addStoryBoard("bigdam/common", BigDam::Common, CTNHCorePonderTags.Kinetic)
                .addStoryBoard("bigdam/common", BigDam::Work, CTNHCorePonderTags.Kinetic);

        helper.forComponents(CTPPMultiblockMachines.SMASHING_FACTORY.getId())
                .addStoryBoard("smashing_factory/common", SmashingFactory::Common, CTNHCorePonderTags.Kinetic);

        helper.forComponents(MultiblocksA.MEADOW.getId())
                .addStoryBoard("meadow/common", Meadow::Common, CTNHCorePonderTags.Kinetic);

        helper.forComponents(CNABlocks.GENERATOR_COIL.getId())
                .addStoryBoard("carbonbrushes/common", CarbonBrushes::ponder, CTNHCorePonderTags.Electric);
        helper.forComponents(CTPPMachines.CARBON_BRUSHES.getId())
                .addStoryBoard("carbonbrushes/common", CarbonBrushes::ponder, CTNHCorePonderTags.Electric);

        helper.forComponents(ResourceLocation.fromNamespaceAndPath("jackseconomy", "mechanical_exporter"))
                .addStoryBoard("mechanicalexporter/common", MechanicalExporter::Common, CTNHCorePonderTags.Kinetic);

        CTNHCore.LOGGER.info("Ponder scenes initialized");
    }
}
