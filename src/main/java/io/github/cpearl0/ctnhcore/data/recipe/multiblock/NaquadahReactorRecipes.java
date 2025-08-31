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
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHMachines.NAQUADAH_REACTOR;
import static twilightforest.init.custom.Restrictions.asStack;

public class NaquadahReactorRecipes {
    public static void init(Consumer<FinishedRecipe> provider){
        CTNHRecipeTypes.NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_reactor_i")
                .inputItems(TagPrefix.bolt, GTMaterials.NaquadahEnriched)
                .outputItems(TagPrefix.bolt, GTMaterials.Naquadah)
                .duration(26042)
                .EUt(-GTValues.V[EV])
                .save(provider);
        CTNHRecipeTypes.NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_reactor_ii")
                .inputItems(TagPrefix.rod, GTMaterials.NaquadahEnriched)
                .outputItems(TagPrefix.rod, GTMaterials.Naquadah)
                .duration(3255)
                .EUt(-GTValues.V[IV])
                .save(provider);
        CTNHRecipeTypes.NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_reactor_iii")
                .inputItems(TagPrefix.rodLong, GTMaterials.NaquadahEnriched)
                .outputItems(TagPrefix.rodLong, GTMaterials.Naquadah)
                .duration(1907)
                .EUt(-GTValues.V[LuV])
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
                provider, true, "naquadah_reactor_ev",
                NAQUADAH_REACTOR[EV].asStack(),
                "ABA", "CDC", "EBE",
                'A', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:uranium_235_rod")), // 铀-235棒
                'B', CustomTags.IV_CIRCUITS, // IV级电路（直接引用）
                'C', GTItems.FIELD_GENERATOR_EV, // EV级场发生器（直接引用）
                'D', GTMachines.HULL[EV].asStack(), // EV级机器外壳（直接引用）
                'E', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:aluminium_quadruple_cable")) // 四重铝电缆
        );
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "naquadah_reactor_iv",
                NAQUADAH_REACTOR[IV].asStack(),
                "ABA", "CDC", "EBE",
                'A', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:plutonium_241_rod")), // 钚-241棒
                'B', CustomTags.LuV_CIRCUITS, // LuV级电路
                'C', GTItems.FIELD_GENERATOR_IV, // IV级场发生器
                'D', GTMachines.HULL[IV].asStack(), // IV级机器外壳
                'E', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:tungsten_quadruple_cable")) // 四重钨电缆
        );
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "naquadah_reactor_luv",
                NAQUADAH_REACTOR[LuV].asStack(),
                "ABA", "CDC", "EBE",
                'A', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:europium_rod")), // 铕棒
                'B', CustomTags.ZPM_CIRCUITS, // ZPM级电路
                'C', GTItems.FIELD_GENERATOR_LuV, // LuV级场发生器
                'D', GTMachines.HULL[LuV].asStack(), // LuV级机器外壳
                'E', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:hssg_quadruple_cable")) // 四重HSSG电缆
        );
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "naquadah_reactor_zpm",
                NAQUADAH_REACTOR[ZPM].asStack(),
                "ABA", "CDC", "EBE",
                'A', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:americium_rod")), // 镅棒
                'B', CustomTags.UV_CIRCUITS, // UV级电路
                'C', GTItems.FIELD_GENERATOR_ZPM, // ZPM级场发生器
                'D', GTMachines.HULL[GTValues.ZPM].asStack(), // ZPM级机器外壳
                'E', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:naquadah_quadruple_cable")) // 四重镎电缆
        );
        if (GTCEuAPI.isHighTier() && NAQUADAH_REACTOR[GTValues.UV] != null) {
            VanillaRecipeHelper.addShapedRecipe(
                    provider, true, "naquadah_reactor_uv",
                    NAQUADAH_REACTOR[UV].asStack(),
                    "ABA", "CDC", "EBE",
                    'A', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:naquadah_alloy_rod")),
                    'B', CustomTags.UHV_CIRCUITS, // UHV级电路
                    'C', GTItems.FIELD_GENERATOR_UV, // UV级场发生器
                    'D', GTMachines.HULL[GTValues.UV].asStack(), // UV级机器外壳
                    'E', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:enriched_naquadah_trinium_europium_duranide_hex_wire")) // 四重富集镎-铕-三钛合金电缆
            );
        }
    }
}

