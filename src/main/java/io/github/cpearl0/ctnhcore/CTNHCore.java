package io.github.cpearl0.ctnhcore;

import io.github.cpearl0.ctnhcore.client.ClientProxy;
import io.github.cpearl0.ctnhcore.common.CommonProxy;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;
import tech.vixhentx.mcmod.ctnhlib.langprovider.LangProcessor;

import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

@Mod(CTNHCore.MODID)
public class CTNHCore {

    public static final String MODID = "ctnhcore";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final String CUSTOM_TAG_SOURCE = "CTNH Custom Tags";

    public CTNHCore() {
        LangProcessor langProcessor = new LangProcessor(REGISTRATE);
        langProcessor.processAll();

        DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    }

    public static ResourceLocation id(String name) {
        return ResourceLocation.tryBuild(MODID, name);
    }
}
