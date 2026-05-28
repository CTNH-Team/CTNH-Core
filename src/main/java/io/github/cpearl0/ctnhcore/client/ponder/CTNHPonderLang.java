package io.github.cpearl0.ctnhcore.client.ponder;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.client.ponder.Electric.CarbonBrushes;
import io.github.cpearl0.ctnhcore.client.ponder.Kinetic.BigDam;
import io.github.cpearl0.ctnhcore.client.ponder.Kinetic.Meadow;
import io.github.cpearl0.ctnhcore.client.ponder.Kinetic.MechanicalExporter;
import io.github.cpearl0.ctnhcore.client.ponder.Kinetic.SmashingFactory;
import io.github.cpearl0.ctnhcore.client.ponder.Mana.MysticSpire;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.langprovider.LangKeyBuilder;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.contents.TranslatableContents;

import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.registrate.lang.RegistrateCNLangProvider;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import java.lang.reflect.Field;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public final class CTNHPonderLang {

    private static final List<Class<?>> PONDER_LANG_CLASSES = List.of(
            BigDam.class,
            CarbonBrushes.class,
            Meadow.class,
            MechanicalExporter.class,
            SmashingFactory.class,
            MysticSpire.class);

    private static final Map<String, Entry> LANG_ENTRIES = new LinkedHashMap<>();
    private static final Map<String, String> CN_ENTRIES = new LinkedHashMap<>();
    private static boolean collectedLangEntries;

    private CTNHPonderLang() {}

    static Optional<Entry> find(Component component) {
        ComponentContents contents = component.getContents();
        if (contents instanceof TranslatableContents translatable) {
            return find(translatable.getKey());
        }
        return Optional.empty();
    }

    private static Optional<Entry> find(String key) {
        collectLangEntries();
        return Optional.ofNullable(LANG_ENTRIES.get(key));
    }

    private static void collectLangEntries() {
        if (collectedLangEntries) {
            return;
        }
        collectedLangEntries = true;
        for (Class<?> ownerClass : PONDER_LANG_CLASSES) {
            collectLangEntries(ownerClass);
        }
    }

    private static void collectLangEntries(Class<?> ownerClass) {
        for (Field field : ownerClass.getDeclaredFields()) {
            if (!Lang.class.isAssignableFrom(field.getType())) {
                continue;
            }
            EN en = field.getAnnotation(EN.class);
            CN cn = field.getAnnotation(CN.class);
            if (en == null && cn == null) {
                continue;
            }
            LANG_ENTRIES.put(
                    LangKeyBuilder.buildKey(ownerClass, field, CTNHCore.MODID),
                    new Entry(firstValue(en), firstValue(cn)));
        }
    }

    private static String firstValue(EN en) {
        return en == null ? "" : firstValue(en.value());
    }

    private static String firstValue(CN cn) {
        return cn == null ? "" : firstValue(cn.value());
    }

    private static String firstValue(String[] values) {
        return values.length == 0 ? "" : values[0];
    }

    static void addCN(String key, String value) {
        CN_ENTRIES.put(key, value);
    }

    public static void init(RegistrateLangProvider provider) {
        PonderIndex.getLangAccess().provideLang(CTNHCore.MODID, provider::add);
    }

    public static void init(RegistrateCNLangProvider provider) {
        CN_ENTRIES.clear();
        PonderIndex.getLangAccess().provideLang(CTNHCore.MODID, (key, value) -> {});
        CN_ENTRIES.forEach(provider::add);
    }

    record Entry(String en, String cn) {}
}
