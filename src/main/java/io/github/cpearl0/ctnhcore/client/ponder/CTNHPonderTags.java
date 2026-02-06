package io.github.cpearl0.ctnhcore.client.ponder;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.simibubi.create.AllBlocks;
import io.github.cpearl0.ctnhcore.CTNHConfig;
import io.github.cpearl0.ctnhcore.CTNHCore;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;

public final class CTNHPonderTags {

    //Translates
    @CN("CTNH机械机器")
    @EN("CTNH Kinetic Machine")
    static Lang KineticTitle;
    @CN("CTNH机械机器思索")
    @EN("CTNH Kinetic Machine Ponders")
    static Lang KineticDesc;

    @CN("CTNH电力")
    @EN("CTNH Electric Machine")
    static Lang ElectricTitle;
    @CN("CTNH电力机器思索")
    @EN("CTNH Electric Machine Ponders")
    static Lang ElectricDesc;

    public static final ResourceLocation Kinetic = ResourceLocation.tryBuild(CTNHCore.MODID, "kinetic");
    public static final ResourceLocation Electric = ResourceLocation.tryBuild(CTNHCore.MODID, "electric");

    public static void register(PonderTagRegistrationHelper<ResourceLocation> helper) {
        helper.registerTag(Kinetic)
                .addToIndex()
                .item(AllBlocks.COGWHEEL.asItem(), true, false)
                .title(KineticTitle.translate().getString())
                .description(KineticDesc.translate().getString())
                .register();

        helper.registerTag(Electric)
                .addToIndex()
                .item(GTItems.WIRELESS.asItem(), true, false)
                .title(ElectricTitle.translate().getString())
                .description(ElectricDesc.translate().getString())
                .register();

        helper.addToTag(Kinetic)
                .add(GTCEu.id("big_dam"));


        helper.addToTag(Electric);

        CTNHCore.LOGGER.info("Ponder tags initialized");
    }
}
