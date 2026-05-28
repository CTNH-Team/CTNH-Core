package io.github.cpearl0.ctnhcore.client.ponder;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.client.ponder.Electric.CarbonBrushes;
import io.github.cpearl0.ctnhcore.client.ponder.Kinetic.*;
import io.github.cpearl0.ctnhcore.client.ponder.Mana.ManaHatch;
import io.github.cpearl0.ctnhcore.client.ponder.Mana.MysticSpire;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;

import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

import com.mo_guang.ctpp.registry.CTPPMachines;
import com.mo_guang.ctpp.registry.CTPPMultiblockMachines;
import org.antarcticgardens.cna.CNABlocks;

public final class CTNHPonderScenes {

    private CTNHPonderScenes() {}

    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(CTPPMultiblockMachines.BIG_DAM.getId())
                .addStoryBoard("bigdam/common", BigDam::Common, CTNHPonderTags.Kinetic)
                .addStoryBoard("bigdam/common", BigDam::Work, CTNHPonderTags.Kinetic);

        helper.forComponents(CTPPMultiblockMachines.SMASHING_FACTORY.getId())
                .addStoryBoard("smashing_factory/common", SmashingFactory::Common, CTNHPonderTags.Kinetic);

        helper.forComponents(MultiblocksA.MEADOW.getId())
                .addStoryBoard("meadow/common", Meadow::Common, CTNHPonderTags.Kinetic);

        helper.forComponents(CNABlocks.GENERATOR_COIL.getId())
                .addStoryBoard("carbonbrushes/common", CarbonBrushes::ponder, CTNHPonderTags.Electric);
        helper.forComponents(CTPPMachines.CARBON_BRUSHES.getId())
                .addStoryBoard("carbonbrushes/common", CarbonBrushes::ponder, CTNHPonderTags.Electric);

        helper.forComponents(ResourceLocation.fromNamespaceAndPath("jackseconomy", "mechanical_exporter"))
                .addStoryBoard("mechanicalexporter/common", MechanicalExporter::Common, CTNHPonderTags.Kinetic);

        // 奥法尖塔思索
        helper.forComponents(ResourceLocation.fromNamespaceAndPath("ctnhmana", "mystic_spire"))
                .addStoryBoard("mysticspire/scene1", MysticSpire::Scene1, CTNHPonderTags.Mana)
                .addStoryBoard("mysticspire/scene2", MysticSpire::Scene2, CTNHPonderTags.Mana)
                .addStoryBoard("mysticspire/scene3", MysticSpire::Scene3, CTNHPonderTags.Mana);

        // 魔力凝聚仓思索 —— 所有含 manahatch 的方块共用同一组故事板
        ResourceLocation[] manahatchBlocks = {
                ResourceLocation.fromNamespaceAndPath("ctnhmana", "manahatch"),
                ResourceLocation.fromNamespaceAndPath("ctnhmana", "elf_manahatch"),
                ResourceLocation.fromNamespaceAndPath("ctnhmana", "giga_manahatch"),
                ResourceLocation.fromNamespaceAndPath("ctnhmana", "sky_manahatch"),
                ResourceLocation.fromNamespaceAndPath("ctnhmana", "industry_manahatch"),
                ResourceLocation.fromNamespaceAndPath("ctnhmana", "bloodmanahatch"),
                ResourceLocation.fromNamespaceAndPath("ctnhmana", "soulmanahatch"),
                ResourceLocation.fromNamespaceAndPath("ctnhmana", "creative_manahatch")
        };
        for (var blockId : manahatchBlocks) {
            helper.forComponents(blockId)
                    .addStoryBoard("manahatch/scene", ManaHatch::Common, CTNHPonderTags.Mana);
        }

        CTNHCore.LOGGER.info("Ponder scenes initialized");
    }
}
