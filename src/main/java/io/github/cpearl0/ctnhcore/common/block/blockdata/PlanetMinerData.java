package io.github.cpearl0.ctnhcore.common.block.blockdata;

import org.jetbrains.annotations.NotNull;

public interface PlanetMinerData {
    int getTier();
    boolean centrifugal_allow();

    @NotNull String getPlanetMinerFrameworkName();
}
