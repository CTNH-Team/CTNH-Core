package io.github.cpearl0.ctnhcore.data.recipe.multiblock;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static io.github.cpearl0.ctnhcore.registry.CTNHMachines.NAQUADAH_REACTOR;
import static twilightforest.init.custom.Restrictions.asStack;

public class NaquadahReactorRecipes {
    public static void init(Consumer<FinishedRecipe> provider){
        CTNHRecipeTypes.NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_reactor_i")
                .inputItems(TagPrefix.bolt, GTMaterials.NaquadahEnriched)
                .outputItems(TagPrefix.bolt, GTMaterials.Naquadah)
                .duration(26042)
                .EUt(-GTValues.V[GTValues.EV])
                .save(provider);
        CTNHRecipeTypes.NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_reactor_ii")
                .inputItems(TagPrefix.rod, GTMaterials.NaquadahEnriched)
                .outputItems(TagPrefix.rod, GTMaterials.Naquadah)
                .duration(3255)
                .EUt(-GTValues.V[GTValues.IV])
                .save(provider);
        CTNHRecipeTypes.NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_reactor_iii")
                .inputItems(TagPrefix.rodLong, GTMaterials.NaquadahEnriched)
                .outputItems(TagPrefix.rodLong, GTMaterials.Naquadah)
                .duration(1907)
                .EUt(-GTValues.V[GTValues.LuV])
                .save(provider);
        CTNHRecipeTypes.NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_reactor_iv")
                .inputItems(TagPrefix.bolt, GTMaterials.Naquadria)
                .outputItems(TagPrefix.bolt, GTMaterials.Naquadah)
                .duration(1907)
                .EUt(-131072)
                .save(provider);
        CTNHRecipeTypes.NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_reactor_v")
                .inputItems(TagPrefix.rod, GTMaterials.Naquadria)
                .outputItems(TagPrefix.rod, GTMaterials.Naquadah)
                .duration(1907)
                .EUt(-524288)
                .save(provider);
         VanillaRecipeHelper.addShapedRecipe(
                 provider, true, "naquadah_reactor_ev",  NAQUADAH_REACTOR[GTValues.EV]
                 .asStack(),
                 "ABA", "CDC", "EBE",
                 'A',  TagPrefix.rod, GTMaterials.Uranium235,
                 'B', CustomTags.IV_CIRCUITS,
                 'C', GTItems.FIELD_GENERATOR_EV,
                 'D', GTMachines.HULL[GTValues.EV].asStack(),
                 'E',  TagPrefix.cableGtQuadruple, GTMaterials.Aluminium
         );
         VanillaRecipeHelper.addShapedRecipe(
                 provider, true, "naquadah_reactor_iv",  NAQUADAH_REACTOR[GTValues.IV]
                 .asStack(),
                 "ABA", "CDC", "EBE",
                 'A',  TagPrefix.rod, GTMaterials.Plutonium241,
                 'B', CustomTags.LuV_CIRCUITS,
                 'C', GTItems.FIELD_GENERATOR_IV,
                 'D', GTMachines.HULL[GTValues.IV].asStack(),
                 'E',  TagPrefix.cableGtQuadruple, GTMaterials.Tungsten
         );
         VanillaRecipeHelper.addShapedRecipe(
                 provider, true, "naquadah_reactor_luv",  NAQUADAH_REACTOR[GTValues.LuV]
                 .asStack(),
                 "ABA", "CDC", "EBE",
                 'A',  TagPrefix.rod, GTMaterials.Europium,
                 'B', CustomTags.ZPM_CIRCUITS,
                 'C', GTItems.FIELD_GENERATOR_LuV,
                 'D', GTMachines.HULL[GTValues.LuV].asStack(),
                 'E',  TagPrefix.cableGtQuadruple, GTMaterials.HSSG
         );
         VanillaRecipeHelper.addShapedRecipe(
                 provider, true, "naquadah_reactor_zpm",  NAQUADAH_REACTOR[GTValues.ZPM]
                 .asStack(),
                 "ABA", "CDC", "EBE",
                 'A',  TagPrefix.rod, GTMaterials.Americium,
                 'B', CustomTags.UV_CIRCUITS,
                 'C', GTItems.FIELD_GENERATOR_ZPM,
                 'D', GTMachines.HULL[GTValues.ZPM].asStack(),
                 'E',  TagPrefix.cableGtQuadruple, GTMaterials.Naquadah
         );
         if (GTCEuAPI.isHighTier() &&  NAQUADAH_REACTOR[GTValues.UV] != null) {
             VanillaRecipeHelper.addShapedRecipe(
                     provider, true, "naquadah_reactor_uv",  NAQUADAH_REACTOR[GTValues.UV]
                 .asStack(),
                     "ABA", "CDC", "EBE",
                     'A',  TagPrefix.rod, GTMaterials.NaquadahAlloy,
                     'B', CustomTags.UHV_CIRCUITS,
                     'C', GTItems.FIELD_GENERATOR_UV,
                     'D', GTMachines.HULL[GTValues.UV].asStack(),
                     'E',  TagPrefix.cableGtQuadruple, GTMaterials.EnrichedNaquadahTriniumEuropiumDuranide
         );
         }
    }
}

