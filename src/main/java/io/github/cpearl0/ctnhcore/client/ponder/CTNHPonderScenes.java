package io.github.cpearl0.ctnhcore.client.ponder;

import com.gregtechceu.gtceu.GTCEu;
import com.mo_guang.ctpp.common.machine.multiblock.BigDamMachine;
import com.mo_guang.ctpp.registry.CTPPMultiblockMachines;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.client.ponder.Kinetic.BigDam;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public final class CTNHPonderScenes {
    private CTNHPonderScenes() {
    }
    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(CTPPMultiblockMachines.BIG_DAM.getId())
                .addStoryBoard("bigdam/common", BigDam::Common, CTNHPonderTags.Kinetic)
                .addStoryBoard("bigdam/common", BigDam::Work, CTNHPonderTags.Kinetic)
        ;

        CTNHCore.LOGGER.info("Ponder scenes initialized");
    }


}
