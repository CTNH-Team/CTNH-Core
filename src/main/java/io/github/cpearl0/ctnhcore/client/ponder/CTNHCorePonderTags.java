package io.github.cpearl0.ctnhcore.client.ponder;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;

import com.gregtechceu.gtceu.common.data.GTItems;

import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.createmod.ponder.api.registration.TagBuilder;
import net.minecraft.resources.ResourceLocation;

import com.mo_guang.ctpp.registry.CTPPMachines;
import com.mo_guang.ctpp.registry.CTPPMultiblockMachines;
import com.moguang.ctnhmana.registry.CMMultiblockMachines;
import com.simibubi.create.AllBlocks;
import org.antarcticgardens.cna.CNABlocks;

import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public final class CTNHCorePonderTags {

    public static final ResourceLocation Kinetic = ResourceLocation.tryBuild(CTNHCore.MODID, "kinetic");
    public static final ResourceLocation Electric = ResourceLocation.tryBuild(CTNHCore.MODID, "electric");
    public static final ResourceLocation Mana = ResourceLocation.tryBuild(CTNHCore.MODID, "mana");

    public static void register(PonderTagRegistrationHelper<ResourceLocation> helper) {
        registerTag(helper, Kinetic,
                "CTNH Kinetic Machine", "CTNH机械机器",
                "CTNH Kinetic Machine Ponders", "CTNH机械机器思索")
                .addToIndex()
                .item(AllBlocks.COGWHEEL.asItem(), true, false)
                .register();

        registerTag(helper, Electric,
                "CTNH Electric Machine", "CTNH电力机器",
                "CTNH Electric Machine Ponders", "CTNH电力机器思索")
                .addToIndex()
                .item(GTItems.COVER_WIRELESS_TRANSMITTER.asItem(), true, false)
                .register();

        registerTag(helper, Mana,
                "CTNH Mana Machine", "CTNH魔力机器",
                "CTNH Mana Machine Ponders", "CTNH魔力机器思索")
                .addToIndex()
                .item(net.minecraft.world.item.Items.AMETHYST_SHARD, true, false)
                .register();

        helper.addToTag(Kinetic)
                .add(CTPPMultiblockMachines.BIG_DAM.getId())
                .add(CTPPMultiblockMachines.SMASHING_FACTORY.getId())
                .add(MultiblocksA.MEADOW.getId());

        helper.addToTag(Electric)
                .add(CNABlocks.GENERATOR_COIL.getId())
                .add(CTPPMachines.CARBON_BRUSHES.getId());

        helper.addToTag(Mana)
                .add(CMMultiblockMachines.MysticSpire.getId());

        CTNHCore.LOGGER.info("Ponder tags initialized");
    }

    private static TagBuilder registerTag(PonderTagRegistrationHelper<ResourceLocation> helper,
                                          ResourceLocation id,
                                          String en,
                                          String cn,
                                          String descriptionEn,
                                          String descriptionCn) {
        REGISTRATE.genLang(tagKey(id), en, cn);
        REGISTRATE.genLang(tagDescriptionKey(id), descriptionEn, descriptionCn);
        return helper.registerTag(id);
    }

    private static String tagKey(ResourceLocation id) {
        return id.getNamespace() + ".ponder.tag." + id.getPath();
    }

    private static String tagDescriptionKey(ResourceLocation id) {
        return tagKey(id) + ".description";
    }
}
