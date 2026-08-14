package io.github.cpearl0.ctnhcore.common.capability;

import com.gregtechceu.gtceu.common.data.GTItems;

import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import com.enderio.api.capacitor.CapacitorModifier;
import com.enderio.api.capacitor.ICapacitorData;
import com.enderio.base.common.init.EIOCapabilities;
import com.google.common.base.Suppliers;
import com.magicbee.ctnhmana.registry.CMItems;
import com.moguang.ctnhbio.registry.CBItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EIOCapacitorProvider implements ICapabilityProvider {

    private static final Supplier<Map<Item, Integer>> CAPACITOR_BASE_MAP = Suppliers.memoize(() -> new HashMap<>(Map.of(
            GTItems.CAPACITOR.get(), 1,
            GTItems.SMD_CAPACITOR.get(), 2,
            GTItems.ADVANCED_SMD_CAPACITOR.get(), 4,
            CBItems.WETWARE_CAPACITOR.get(), 8,
            CMItems.MANA_CAPACITOR.get(), 2,
            CMItems.ADVANCED_MANA_CAPACITOR.get(), 3,
            CMItems.BLOOD_CAPACITOR.get(), 4,
            CMItems.WILL_CAPACITOR.get(), 6)));

    public static Map<Item, Integer> getCapacitorBaseMap() {
        return CAPACITOR_BASE_MAP.get();
    }

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
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability,
                                                      @Nullable Direction direction) {
        return capability == EIOCapabilities.CAPACITOR ? optional.cast() : LazyOptional.empty();
    }
}
