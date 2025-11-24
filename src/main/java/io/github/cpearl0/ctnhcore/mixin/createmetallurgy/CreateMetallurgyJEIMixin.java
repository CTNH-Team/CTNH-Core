package io.github.cpearl0.ctnhcore.mixin.createmetallurgy;

import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import fr.lucreeper74.createmetallurgy.compat.jei.CreateMetallurgyJEI;
import fr.lucreeper74.createmetallurgy.registries.CMBlocks;
import fr.lucreeper74.createmetallurgy.registries.CMItems;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = CreateMetallurgyJEI.class, remap = false)
public class CreateMetallurgyJEIMixin {
    @Final
    @Shadow
    private List<CreateRecipeCategory<?>> allCategories;

    @Inject(
            method = "registerRecipeCatalysts",
            at = @At("TAIL")
    )
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration, CallbackInfo ci) {
        allCategories.forEach(
                c -> {
                    var path = c.getRecipeType().getUid().getPath();
                    if(path.equals("melting") || path.equals("alloying")){
                        registration.addRecipeCatalysts(
                                c.getRecipeType(),
                                CMBlocks.INDUSTRIAL_CRUCIBLE,
                                CMItems.FOUNDRY_UNIT
                        );
                    }
                }
        );
    }
}
