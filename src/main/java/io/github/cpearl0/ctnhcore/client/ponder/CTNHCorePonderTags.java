package io.github.cpearl0.ctnhcore.client.ponder;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.GTNNMultiblocks;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.Mechanical;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;

import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.machines.GTMultiMachines;

import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

import tech.vixhentx.mcmod.ctnhlib.client.ponder.CTNHPonderTagHelper;

import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public final class CTNHCorePonderTags {

    public static final ResourceLocation MechanicalMultiblock = ResourceLocation.tryBuild(CTNHCore.MODID,
            "mechanical_multiblock");
    public static final ResourceLocation CTNHPonder = ResourceLocation.tryBuild(CTNHCore.MODID, "ctnhponder");

    public static void register(PonderTagRegistrationHelper<ResourceLocation> helper) {
        CTNHPonderTagHelper.registerTag(REGISTRATE, helper, MechanicalMultiblock,
                "Mechanical Multiblock", "机械多方块机器",
                "Ponders on CTNH mechanical multiblock machines", "CTNH机械多方块机器思索")
                .addToIndex()
                .item(Mechanical.MECHANICAL_PRESSOR.getItem(), true, false)
                .register();

        CTNHPonderTagHelper.registerTag(REGISTRATE, helper, CTNHPonder,
                "CTNH Electric Machine", "CTNHCore机器",
                "CTNH Electric Machine Ponders", "CTNHCore机器思索")
                .addToIndex()
                .item(GTItems.COVER_WIRELESS_TRANSMITTER.asItem(), true, false)
                .register();

        helper.addToTag(MechanicalMultiblock)
                .add(Mechanical.MECHANICAL_PRESSOR.getId())
                .add(Mechanical.MECHANICAL_MIXER.getId())
                .add(Mechanical.MECHANICAL_CENTRIFUGE.getId())
                .add(Mechanical.MECHANICAL_SIFTER.getId())
                .add(Mechanical.MECHANICAL_EXTRACTOR.getId())
                .add(Mechanical.MECHANICAL_LATHE.getId());

        helper.addToTag(CTNHPonder)
                .add(GTMultiMachines.COKE_OVEN.getId())
                .add(GTMultiMachines.ASSEMBLY_LINE.getId())
                .add(GTNNMultiblocks.NEUTRON_ACTIVATOR.getId())
                .add(MultiblocksA.MEADOW.getId());

        CTNHCore.LOGGER.info("Ponder tags initialized");
    }
}
