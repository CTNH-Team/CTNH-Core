package io.github.cpearl0.ctnhcore.data.lang;

import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.world.level.block.Block;

public class CTNHLangHandler {
    private static CTNHLangProvider provider;
    public static void init(CTNHLangProvider provider) {
        CTNHLangHandler.provider = provider;
        ItemLang.init(provider);
        MachineLang.init(provider);
        MaterialLang.init(provider);
        RecipeLang.init(provider);
        BlockLang.init(provider);
    }
    public static void tsl(CTNHLangProvider provider, String key, String cn) {
        provider.addCN(key, cn);
    }

    public static void tsl(CTNHLangProvider provider, String key, String cn, String en) {
        provider.add(key, en, cn);
    }
    public static void tslBlock(CTNHLangProvider provider, Block block, String cn, String en) { provider.addBlock(block, en, cn);}
    public static void tslBlock(CTNHLangProvider provider, Block block, String cn) { provider.addBlockCN(block, cn);}
}
