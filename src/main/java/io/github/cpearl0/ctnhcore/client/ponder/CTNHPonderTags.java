package io.github.cpearl0.ctnhcore.client.ponder;

import com.gregtechceu.gtceu.common.data.GTItems;
import com.mo_guang.ctpp.registry.CTPPMultiblockMachines;
import com.simibubi.create.AllBlocks;
import io.github.cpearl0.ctnhcore.CTNHCore;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public final class CTNHPonderTags {
    public static final ResourceLocation Kinetic = ResourceLocation.tryBuild(CTNHCore.MODID, "kinetic");
    public static final ResourceLocation Electric = ResourceLocation.tryBuild(CTNHCore.MODID, "electric");

    public static void register(PonderTagRegistrationHelper<ResourceLocation> helper) {
        helper.registerTag(Kinetic)
                .addToIndex()
                .item(AllBlocks.COGWHEEL.asItem(), true, false)
                .register();

        helper.registerTag(Electric)
                .addToIndex()
                .item(GTItems.COVER_WIRELESS_TRANSMITTER.asItem(), true, false)
                .register();

        helper.addToTag(Kinetic)
                .add(CTPPMultiblockMachines.BIG_DAM.getId());


        helper.addToTag(Electric);

        CTNHCore.LOGGER.info("Ponder tags initialized");
    }
}
