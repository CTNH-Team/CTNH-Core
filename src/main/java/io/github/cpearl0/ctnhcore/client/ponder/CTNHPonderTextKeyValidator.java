package io.github.cpearl0.ctnhcore.client.ponder;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.createmod.ponder.api.registration.LangRegistryAccess;
import net.createmod.ponder.api.registration.StoryBoardEntry;
import net.createmod.ponder.foundation.PonderIndex;
import net.createmod.ponder.foundation.registration.PonderLocalization;
import net.createmod.ponder.foundation.registration.PonderSceneRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class CTNHPonderTextKeyValidator {

    private CTNHPonderTextKeyValidator() {}

    public static void validateAll() {
        long startTime = System.nanoTime();
        PonderLocalization localization = requirePonderLocalization();
        List<RuntimeException> failures = new ArrayList<>();

        PonderIndex.getSceneAccess().getRegisteredEntries().stream()
                .map(Map.Entry::getValue)
                .filter(entry -> CTNHCore.MODID.equals(entry.getNamespace()))
                .forEach(entry -> validateEntry(localization, entry, failures));

        long elapsedMs = (System.nanoTime() - startTime) / 1_000_000;
        if (!failures.isEmpty()) {
            IllegalStateException error = new IllegalStateException(
                    "CTNH Ponder text key validation failed for " + failures.size() + " scene(s) after " + elapsedMs +
                            " ms");
            failures.forEach(error::addSuppressed);
            throw error;
        }

        CTNHCore.LOGGER.info("Validated CTNH Ponder text keys in {} ms", elapsedMs);
    }

    private static PonderLocalization requirePonderLocalization() {
        LangRegistryAccess access = PonderIndex.getLangAccess();
        if (access instanceof PonderLocalization localization) {
            return localization;
        }
        throw new IllegalStateException(
                "Unexpected Ponder lang registry implementation: " + access.getClass().getName());
    }

    private static void validateEntry(
                                      PonderLocalization localization, StoryBoardEntry entry,
                                      List<RuntimeException> failures) {
        try {
            PonderSceneRegistry.compileScene(localization, entry, null);
        } catch (RuntimeException e) {
            failures.add(new IllegalStateException(
                    "Failed CTNH Ponder validation for component " + entry.getComponent() + ", schematic " +
                            entry.getSchematicLocation(),
                    e));
        }
    }
}
