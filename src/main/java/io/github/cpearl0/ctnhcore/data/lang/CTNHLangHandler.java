package io.github.cpearl0.ctnhcore.data.lang;

public class CTNHLangHandler {
    private static CTNHLangProvider provider;
    public static void init(CTNHLangProvider provider) {
        CTNHLangHandler.provider = provider;
        ItemLang.init(provider);
        MachineLang.init(provider);
        MaterialLang.init(provider);
    }
    public static void tsl(String key, String cn) {
        provider.addCN(key, cn);
    }

    public static void tsl(String key, String cn, String en) {
        provider.add(key, en, cn);
    }
}
