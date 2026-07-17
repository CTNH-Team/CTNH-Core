package io.github.cpearl0.ctnhcore.data.recipe.mana;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.recipe.ingredient.fluid.FluidIngredient;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

import com.enderio.api.capability.StoredEntityData;
import com.enderio.base.common.init.EIOItems;
import com.moguang.ctnhmana.api.recipe.condition.HellForgeCondition;
import wayoftime.bloodmagic.common.fluid.BloodMagicFluids;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.block;
import static com.moguang.ctnhmana.registry.CMRecipeTypes.HELL_FORGE_RECIPES;
import static wayoftime.bloodmagic.common.item.BloodMagicItems.REAGENT_BINDING;
import static wayoftime.bloodmagic.common.item.BloodMagicItems.REAGENT_BLOOD_LIGHT;

public class MiscManaRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        var filled = EIOItems.FILLED_SOUL_VIAL.get().getDefaultInstance();
        CompoundTag tag = filled.getOrCreateTag();
        CompoundTag entityTag = new CompoundTag();
        entityTag.put("EntityStorage",
                StoredEntityData.of(ResourceLocation.tryParse("minecraft:wither")).serializeNBT());
        tag.put("BlockEntityTag", entityTag);

        HELL_FORGE_RECIPES.recipeBuilder("wither_soul_vial")
                .addCondition(new HellForgeCondition(450))// 恶魔意志消耗量
                .inputItems(ChemicalHelper.get(block, GTMaterials.NetherStar))// 下界之星块
                .inputItems(REAGENT_BLOOD_LIGHT)// 血光试剂
                .inputItems(REAGENT_BINDING)// 束缚试剂
                .inputItems(EIOItems.EMPTY_SOUL_VIAL)// 空灵魂瓶
                .inputFluids(FluidIngredient.of(BloodMagicFluids.DOUBT_FLUID.get(), 6666))
                .outputItems(filled)
                .duration(200)
                .EUt(8000)
                .circuitMeta(1)
                .save(provider);
    }
}
