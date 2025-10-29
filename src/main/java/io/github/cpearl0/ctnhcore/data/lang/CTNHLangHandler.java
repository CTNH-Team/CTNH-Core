package io.github.cpearl0.ctnhcore.data.lang;

import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.world.level.block.Block;
import tech.vixhentx.mcmod.ctnhlib.registrate.lang.RegistrateCNLangProvider;

import static io.github.cpearl0.ctnhcore.data.lang.old.ChineseLangHandler.cnLangProvider;
import static io.github.cpearl0.ctnhcore.data.lang.old.EnglishLangHandler.enLangProvider;

public class CTNHLangHandler {
    public static void init() {
        MachineLang.init();
    }
    public static void tsl(String key, String cn) {
        cnLangProvider.add(key, cn);
    }

    public static void tsl(String key, String cn, String en) {
        enLangProvider.add(key, en);
        cnLangProvider.add(key, cn);
    }
    public static void tslBlock(Block block, String cn, String en) {
        cnLangProvider.addBlock(() -> block, cn);
        enLangProvider.addBlock(() -> block, en);
    }
    public static void tslBlock(Block block, String cn) { cnLangProvider.addBlock(() -> block, cn);}
}
