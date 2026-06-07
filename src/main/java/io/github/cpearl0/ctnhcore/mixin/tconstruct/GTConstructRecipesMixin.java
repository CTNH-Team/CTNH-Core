package io.github.cpearl0.ctnhcore.mixin.tconstruct;

import io.github.cpearl0.ctnhcore.common.tconstruct.TConstructFluidTagFilter;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.data.gtceu.GTConstructRecipeType;
import slimeknights.tconstruct.data.gtceu.GTConstructRecipes;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.LV;

@Mixin(value = GTConstructRecipes.class, remap = false)
public class GTConstructRecipesMixin {

    @Inject(method = "generateStandardSolidifierRecipes", at = @At("HEAD"), cancellable = true)
    private static void ctnh$useDirectFluidInsteadOfCommonTag(Consumer<FinishedRecipe> provider,
                                                              Fluid fluid,
                                                              MaterialVariantId outputMaterialVariantId,
                                                              CallbackInfo ci) {
        ResourceLocation fluidId = ForgeRegistries.FLUIDS.getKey(fluid);
        if (fluidId == null || !TConstructFluidTagFilter.isBlockedMaterialPath(fluidId.getPath())) {
            return;
        }
        GTConstructRecipeType.builder()
                .inputFluids(fluid)
                .outputMaterial(outputMaterialVariantId)
                .voltage(LV)
                .register(provider);
        ci.cancel();
    }
}
