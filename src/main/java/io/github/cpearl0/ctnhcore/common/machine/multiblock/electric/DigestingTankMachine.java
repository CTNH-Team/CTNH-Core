package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.YeastRelatedMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;

public class DigestingTankMachine extends BioMachine {

    public DigestingTankMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    @Override
    protected @NotNull RecipeLogic createRecipeLogic(Object @NotNull... args) {
        return new DigestingTankLogic(this);
    }

    private static class DigestingTankLogic extends RecipeLogic {

        public DigestingTankLogic(IRecipeLogicMachine machine) {
            super(machine);
        }

        @Override
        public void findAndHandleRecipe() {
            super.findAndHandleRecipe();
            if (lastRecipe == null) {
                GTRecipe dynamicFoodRecipe = searchFoodRecipe();
                if (dynamicFoodRecipe != null && checkRecipe(dynamicFoodRecipe).isSuccess()) {
                    setupRecipe(dynamicFoodRecipe);
                }
            }
        }

        @Nullable
        private GTRecipe searchFoodRecipe() {
            var recipeHandlers = machine.getCapabilitiesFlat(IO.IN, ItemRecipeCapability.CAP);
            for (var handler : recipeHandlers) {
                for (var content : handler.getContents()) {
                    if (!(content instanceof ItemStack stack) || stack.isEmpty()) {
                        continue;
                    }
                    if (!isValidFood(stack)) {
                        continue;
                    }

                    GTRecipe circuit0 = buildDynamicFoodRecipe(stack, 0);
                    if (circuit0 != null && matchRecipe(circuit0).isSuccess()) {
                        return circuit0;
                    }

                    GTRecipe circuit1 = buildDynamicFoodRecipe(stack, 1);
                    if (circuit1 != null && matchRecipe(circuit1).isSuccess()) {
                        return circuit1;
                    }
                }
            }
            return null;
        }

        private boolean isValidFood(ItemStack stack) {
            if (stack.getItem() == Items.PUMPKIN_PIE) {
                return false;
            }
            return stack.getFoodProperties(null) != null;
        }

        @Nullable
        private GTRecipe buildDynamicFoodRecipe(ItemStack stack, int circuitMeta) {
            var properties = stack.getFoodProperties(null);
            if (properties == null) {
                return null;
            }

            int baseAmount = (int) Math.floor(properties.getNutrition() + properties.getSaturationModifier() / 2.0f);
            if (baseAmount <= 0) {
                return null;
            }

            ResourceLocation itemId = ResourceLocation.tryParse(String.valueOf(stack.getItem()));
            if (itemId == null) {
                return null;
            }

            String recipeSuffix = circuitMeta == 0 ? "digestion_dynamic" : "digestion2_dynamic";
            String recipeName = itemId.getNamespace() + "_" + itemId.getPath() + "_" + recipeSuffix;

            var builder = CTNHRecipeTypes.DIGESTING.recipeBuilder(CTNHCore.id(recipeName))
                    .EUt(circuitMeta == 0 ? 30 : 120)
                    .inputItems(stack.copyWithCount(1))
                    .circuitMeta(circuitMeta)
                    .duration(100);

            if (circuitMeta == 0) {
                int amount = 100 * baseAmount;
                return builder
                        .inputFluids(GTMaterials.Water.getFluid(amount))
                        .outputFluids(GTMaterials.Biomass.getFluid(amount))
                        .buildRuntime();
            }

            int amount = 75 * baseAmount;
            return builder
                    .inputFluids(GTMaterials.Water.getFluid(amount))
                    .outputFluids(GTMaterials.FermentedBiomass.getFluid(amount))
                    .chancedOutput(dust, YeastRelatedMaterials.ESCHERICHIA_COLI, 500 * baseAmount, 500)
                    .buildRuntime();
        }
    }
}
