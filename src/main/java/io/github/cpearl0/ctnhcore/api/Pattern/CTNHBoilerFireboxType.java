package io.github.cpearl0.ctnhcore.api.Pattern;

import com.gregtechceu.gtceu.GTCEu;

import com.gregtechceu.gtceu.common.block.BoilerFireboxType;
import io.github.cpearl0.ctnhcore.CTNHCore;
import net.minecraft.resources.ResourceLocation;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public record CTNHBoilerFireboxType(String name, ResourceLocation bottom, ResourceLocation top, ResourceLocation side) {
    public static final Map<String, BoilerFireboxType> FIREBOX_TYPES = new HashMap();
    public static final Codec<BoilerFireboxType> CODEC;
    public static BoilerFireboxType NAQUADAH_FIREBOX;

    static {
        CODEC = Codec.STRING.comapFlatMap((name) -> {
            BoilerFireboxType type = (BoilerFireboxType)FIREBOX_TYPES.get(name);
            return type != null ? DataResult.success(type) : DataResult.error(() -> "A firebox type named " + name + " does not exist");
        }, BoilerFireboxType::name);
        NAQUADAH_FIREBOX = new BoilerFireboxType("naquadah_firebox", CTNHCore.id("block/casings/nq_casing"), CTNHCore.id("block/casings/nq_casing"), CTNHCore.id("block/casings/firebox/machine_casing_firebox_naquadah"));
    }
}