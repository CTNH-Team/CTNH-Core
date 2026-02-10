package io.github.cpearl0.ctnhcore.data.recipe.multiblock;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.EnderIOMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class PhotovoltaicStationRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapedRecipe(
                provider,
                CTNHCore.id("photovoltaic_power_station_energetic"),
                MultiblocksA.PHOTOVOLTAIC_POWER_STATION_ENERGETIC.asStack(),
                "AAA",
                "BCB",
                "DED",
                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Gold),
                'B', GTItems.ELECTRIC_MOTOR_LV.asStack(),
                'C', CTNHBlocks.CASING_REFLECT_LIGHT.asStack(),
                'D', GTItems.CAPACITOR.asStack(),
                'E', CustomTags.LV_CIRCUITS
        );

        VanillaRecipeHelper.addShapedRecipe(
                provider,
                CTNHCore.id("photovoltaic_power_station_pulsating"),
                MultiblocksA.PHOTOVOLTAIC_POWER_STATION_PULSATING.asStack(),
                "AAA",
                "BCB",
                "DED",
                'A', ChemicalHelper.get(TagPrefix.plate, EnderIOMaterials.PulsatingAlloy),
                'B', GTItems.ELECTRIC_MOTOR_MV.asStack(),
                'C', CTNHBlocks.CASING_REFLECT_LIGHT.asStack(),
                'D', GTItems.CAPACITOR.asStack(),
                'E', CustomTags.MV_CIRCUITS
        );

        VanillaRecipeHelper.addShapedRecipe(
                provider,
                CTNHCore.id("photovoltaic_power_station_vibrant"),
                MultiblocksA.PHOTOVOLTAIC_POWER_STATION_VIBRANT.asStack(),
                "AAA",
                "BCB",
                "DED",
                'A', ChemicalHelper.get(TagPrefix.plate, EnderIOMaterials.VibrantAlloy),
                'B', GTItems.ELECTRIC_MOTOR_HV.asStack(),
                'C', CTNHBlocks.CASING_REFLECT_LIGHT.asStack(),
                'D', GTItems.SMD_CAPACITOR.asStack(),
                'E', CustomTags.HV_CIRCUITS
        );
    }
}
