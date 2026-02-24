package io.github.cpearl0.ctnhcore.client.ponder;

import com.mo_guang.ctpp.registry.CTPPMachines;
import com.mo_guang.ctpp.registry.CTPPMultiblockMachines;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.client.ponder.Electric.CarbonBrushes;
import io.github.cpearl0.ctnhcore.client.ponder.Kinetic.*;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

import org.antarcticgardens.newage.NewAgeBlocks;

public final class CTNHPonderScenes {
    private CTNHPonderScenes() {
    }
    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(CTPPMultiblockMachines.BIG_DAM.getId())
                .addStoryBoard("bigdam/common", BigDam::Common, CTNHPonderTags.Kinetic)
                .addStoryBoard("bigdam/common", BigDam::Work, CTNHPonderTags.Kinetic)
        ;

        helper.forComponents(CTPPMultiblockMachines.SMASHING_FACTORY.getId())
                .addStoryBoard("smashing_factory/common", SmashingFactory::Common, CTNHPonderTags.Kinetic);

        helper.forComponents(MultiblocksA.MEADOW.getId())
                .addStoryBoard("meadow/common", Meadow::Common, CTNHPonderTags.Kinetic);

        // Create New Age 碳刷和发电机线圈思索
        helper.forComponents(NewAgeBlocks.GENERATOR_COIL.getId())
                .addStoryBoard("carbonbrushes/common", CarbonBrushes::ponder, CTNHPonderTags.Electric);
        helper.forComponents(CTPPMachines.CARBON_BRUSHES.getId())
                .addStoryBoard("carbonbrushes/common", CarbonBrushes::ponder, CTNHPonderTags.Electric);

        CTNHCore.LOGGER.info("Ponder scenes initialized");
    }


}
