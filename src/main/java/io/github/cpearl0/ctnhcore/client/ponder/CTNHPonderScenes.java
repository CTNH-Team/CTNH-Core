package io.github.cpearl0.ctnhcore.client.ponder;

import com.gregtechceu.gtceu.GTCEu;
import com.mo_guang.ctpp.common.machine.multiblock.BigDamMachine;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.client.ponder.Kinetic.BigDam;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public final class CTNHPonderScenes {
    private CTNHPonderScenes() {
    }
    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(GTCEu.id("big_dam"))
                .addStoryBoard("bigdam_common", BigDam::Common, CTNHPonderTags.Kinetic)
        ;

        CTNHCore.LOGGER.info("Ponder scenes initialized");
    }


}
