package io.github.cpearl0.ctnhcore.data.recipe.chain;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.mo_guang.ctpp.common.recipe.builder.create.SequencedAssemblyRecipeBuilder;
import io.github.cpearl0.ctnhcore.data.materials.YeastRelatedMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class CoalChain {
    public static void init(Consumer<FinishedRecipe> provider) {
        // coke dust -> high quality solid fuel
        ItemStack cokeDustSeq = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Coke);
        ItemStack highQualityFuel = CTNHItems.HIGH_QUALITY_SOLID_FUEL.asStack();
        if (!cokeDustSeq.isEmpty() && !highQualityFuel.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder("coke_dust_to_high_quality_fuel")
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
