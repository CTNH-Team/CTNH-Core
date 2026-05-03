package io.github.cpearl0.ctnhcore;

import io.github.cpearl0.ctnhcore.client.ClientProxy;
import io.github.cpearl0.ctnhcore.common.CommonProxy;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

@Mod(CTNHCore.MODID)
public class CTNHCore {

    public static final String MODID = "ctnhcore";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final String CUSTOM_TAG_SOURCE = "CTNH Custom Tags";

    public CTNHCore() {
        DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    }

    public static ResourceLocation id(String name) {
        return ResourceLocation.tryBuild(MODID, name);
    }
}
