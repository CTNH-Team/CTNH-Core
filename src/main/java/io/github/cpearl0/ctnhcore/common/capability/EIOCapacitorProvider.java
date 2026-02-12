package io.github.cpearl0.ctnhcore.common.capability;

import com.enderio.api.capacitor.CapacitorModifier;
import com.enderio.api.capacitor.ICapacitorData;
import com.enderio.base.common.init.EIOCapabilities;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class EIOCapacitorProvider implements ICapabilityProvider {

    private final float base;

    private final ICapacitorData data = new ICapacitorData() {
        @Override
        public float getBase() {
            return base;
        }

        @Override
        public float getModifier(@NotNull CapacitorModifier modifier) {
            return getBase();
        }

        @Override
        public @NotNull Map<CapacitorModifier, Float> getAllModifiers() {
            return Map.of();
        }
    };

    private final LazyOptional<ICapacitorData> optional = LazyOptional.of(() -> data);

    public EIOCapacitorProvider(float base) {
        this.base = base;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        return capability == EIOCapabilities.CAPACITOR ? optional.cast() : LazyOptional.empty();
    }
}
