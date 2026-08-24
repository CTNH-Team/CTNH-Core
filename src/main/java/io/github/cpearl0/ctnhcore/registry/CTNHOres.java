package io.github.cpearl0.ctnhcore.registry;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.worldgen.StrataVeinGenerator;
import io.github.cpearl0.ctnhcore.registry.ores.AdAstraOres;
import io.github.cpearl0.ctnhcore.registry.ores.AetherOres;
import io.github.cpearl0.ctnhcore.registry.ores.AlfheimOres;
import io.github.cpearl0.ctnhcore.registry.ores.EndOres;
import io.github.cpearl0.ctnhcore.registry.ores.NetherOres;
import io.github.cpearl0.ctnhcore.registry.ores.OverworldOres;
import io.github.cpearl0.ctnhcore.registry.ores.TwilightForestOres;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGeneratorUtils;
import com.gregtechceu.gtceu.common.data.GTOres;

import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class CTNHOres {

    static {
        ResourceLocation strataId = CTNHCore.id("strata");
        WorldGeneratorUtils.VEIN_GENERATORS.put(strataId, StrataVeinGenerator.CODEC);
        WorldGeneratorUtils.VEIN_GENERATOR_FUNCTIONS.put(strataId, StrataVeinGenerator::new);
    }

    public static GTOreDefinition create(String name, String en, String cn, Consumer<GTOreDefinition> config) {
        ResourceLocation id = CTNHCore.id(name);
        if (GTCEu.isDataGen()) {
            CTNHRegistration.REGISTRATE.genLang(GTOres.getTranslationKey(id), en, cn);
            return null;
        }
        return GTOres.create(id, config);
    }

    public static void init() {
        OverworldOres.init();
        NetherOres.init();
        EndOres.init();
        TwilightForestOres.init();
        AetherOres.init();
        AlfheimOres.init();
        AdAstraOres.init();
    }
}
