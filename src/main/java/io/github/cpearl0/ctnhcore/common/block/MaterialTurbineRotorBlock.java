package io.github.cpearl0.ctnhcore.common.block;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.lowdragmc.lowdraglib.utils.ColorUtils;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.network.chat.MutableComponent;
import org.jetbrains.annotations.NotNull;

import static io.github.cpearl0.ctnhcore.registry.CTNHTagPrefixes.hyperRotor;

public class MaterialTurbineRotorBlock extends TurbineRotorBlock {
    public final Material material;
    public MaterialTurbineRotorBlock(Properties pProperties, Material material) {
        super(pProperties,ColorUtils.red(material.getMaterialARGB()),
                ColorUtils.green(material.getMaterialARGB()),
                ColorUtils.blue(material.getMaterialARGB()),
                ColorUtils.alpha(material.getMaterialARGB()));
        this.material = material;
    }

    public static NonNullFunction<Properties,TurbineRotorBlock> create(Material material) {
        return (p) -> new MaterialTurbineRotorBlock(p, material);
    }

    @Override @NotNull
    public String getDescriptionId() {
        return hyperRotor.getUnlocalizedName(material);
    }

    @Override @NotNull
    public MutableComponent getName() {
        return hyperRotor.getLocalizedName(material);
    }
}
