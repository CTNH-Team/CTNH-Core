package io.github.cpearl0.ctnhcore.api.fluids;

import com.google.common.base.Preconditions;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.FluidState;
import com.gregtechceu.gtceu.api.fluids.attribute.FluidAttribute;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKey;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.Collection;

import static com.gregtechceu.gtceu.api.fluids.FluidConstants.*;
import static io.github.cpearl0.ctnhcore.api.fluids.CTNHFluidstate.ANTIMATTER;
import static io.github.cpearl0.ctnhcore.api.fluids.CTNHFluidstate.QUANTUM;

import  io.github.cpearl0.ctnhcore.api.fluids.CTNHFluidstate;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CTNHFluidBuilder extends FluidBuilder {
    private static final int INFER_TEMPERATURE = -1;
    private static final int INFER_COLOR = 0xFFFFFFFF;
    private static final int INFER_DENSITY = -1;
    private static final int INFER_LUMINOSITY = -1;
    private static final int INFER_VISCOSITY = -1;

    @Setter
    private String name = null;
    @Setter
    private String translation = null;

    private final Collection<FluidAttribute> attributes = new ArrayList<>();

    @Setter
    private CTNHFluidstate state = QUANTUM;
    private int temperature = INFER_TEMPERATURE;
    private int color = INFER_COLOR;
    private boolean isColorEnabled = true;
    @Setter
    private int density = INFER_DENSITY;
    private int luminosity = INFER_LUMINOSITY;
    private int viscosity = INFER_VISCOSITY;
    @Setter
    private int burnTime = -1;

    @Getter
    @Setter
    private ResourceLocation still = null;
    @Getter
    @Setter
    private ResourceLocation flowing = null;
    private boolean hasCustomStill = false;
    private boolean hasCustomFlowing = false;

    private boolean hasFluidBlock = false;
    private boolean hasBucket = true;
    public CTNHFluidBuilder() {
        super();
    }
    private void determineTemperature(@NotNull Material material) {
        if (temperature != INFER_TEMPERATURE) return;
        if (1==2) {
            temperature = ROOM_TEMPERATURE;
        } else {
            BlastProperty property = material.getProperty(PropertyKey.BLAST);
            if (property == null) {
                temperature = switch (state) {
                    case ANTIMATTER -> {
                        yield -2;
                    }
                    case QUANTUM ->
                    {
                        yield ROOM_TEMPERATURE;
                    }
                };
            } else {
                temperature=switch (state)
                {
                    case ANTIMATTER -> -(property.getBlastTemperature());
                    case QUANTUM ->property.getBlastTemperature();
                };
            }
        }
    }
    private void determineDensity() {
        if (density != INFER_DENSITY) return;
        density = switch (state) {
            case ANTIMATTER->-10000;
            case QUANTUM -> -9999;
        };
    }
    private void determineTextures(@NotNull Material material, @Nullable FluidStorageKey key, @NotNull String modid) {
        if (key != null) {
            if (hasCustomStill) {
                still = ResourceLocation.tryBuild(modid, "block/fluids/fluid." + name);
            } else {
                still = key.getIconType().getBlockTexturePath(material.getMaterialIconSet(), true);
            }
        } else {
            still = ResourceLocation.tryBuild(modid, "block/fluids/fluid." + name);
        }

        if (hasCustomFlowing) {
            flowing = ResourceLocation.tryBuild(modid, "block/fluids/fluid." + name + "_flow");
        } else {
            flowing = still;
        }
    }
    private void determineViscosity(@NotNull Material material) {
        if (viscosity != INFER_VISCOSITY) return;
        viscosity = DEFAULT_PLASMA_VISCOSITY;
    }
    private void determineLuminosity(@NotNull Material material) {
        if (luminosity != INFER_LUMINOSITY) return;
        if (state == QUANTUM) {
            BlastProperty property = material.getProperty(PropertyKey.BLAST);
            if(property.getBlastTemperature()==1)
            luminosity = 15;
            if(property.getBlastTemperature()==2)
                luminosity = 10;
            if(property.getBlastTemperature()==3)
                luminosity = 5;
            if(property.getBlastTemperature()==4)
                luminosity = 0;
        }else
        {

            luminosity=0;
        }
    }
    @Override
    public @NotNull FluidBuilder temperature(int temperature) {
        this.temperature = temperature;
        return this;
    }
}
