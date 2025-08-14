package io.github.cpearl0.ctnhcore.api.machine.feature;

import com.gregtechceu.gtceu.api.machine.feature.IMachineFeature;
import net.minecraft.world.level.block.state.BlockState;

public interface IDynamicCasing extends IMachineFeature {
    BlockState getAppearance();
}
