package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.YeastRelatedMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;

import com.mo_guang.ctpp.data.recipe.builder.create.SequencedAssemblyRecipeBuilder;

import java.util.function.Consumer;

public class CoalChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // coke dust -> high quality solid fuel
        ItemStack cokeDustSeq = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Coke);
        ItemStack highQualityFuel = CTNHItems.HIGH_QUALITY_SOLID_FUEL.asStack();
        if (!cokeDustSeq.isEmpty() && !highQualityFuel.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder(CTNHCore.id("coke_dust_to_high_quality_fuel"))
                    .input(cokeDustSeq)
                    .transitional(cokeDustSeq)
                    .result(highQualityFuel)
                    .cutting()
                    .deploying(ChemicalHelper.get(TagPrefix.dust, YeastRelatedMaterials.LIGNIN))
                    .filling(cokeDustSeq, GTMaterials.Creosote.getFluid(250))
                    .pressing()
                    .loops(1)
                    .save(provider);
        }
    }
}
