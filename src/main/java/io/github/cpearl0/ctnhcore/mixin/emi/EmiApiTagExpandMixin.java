package io.github.cpearl0.ctnhcore.mixin.emi;

import dev.emi.emi.api.EmiApi;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.bom.BoM;
import io.github.cpearl0.ctnhcore.utils.TagRelationGraph;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;

import static dev.emi.emi.api.EmiApi.focusRecipe;

@Mixin(value = EmiApi.class, remap = false)
public abstract class EmiApiTagExpandMixin {

    @Shadow
    private static void setPages(Map<EmiRecipeCategory, List<EmiRecipe>> recipes, EmiIngredient stack) {}

    @Shadow
    private static Map<EmiRecipeCategory, List<EmiRecipe>> mapRecipes(List<EmiRecipe> list) {
        return null;
    }

    @Unique
    private static final TagRelationGraph TAG_RELATIONS = new TagRelationGraph();

    @Unique
    private static final Map<ItemStack, List<EmiStack>> TAG_CACHE = new HashMap<>();

    static {
        TAG_RELATIONS.addRelationGroup(List.of("ingots", "nuggets", "hot_ingots"));
        TAG_RELATIONS.addRelationGroup(List.of("dusts", "small_dusts", "tiny_dusts"));
    }


    @Inject(
            method = "displayRecipes",
            at = @At(
                    value = "INVOKE",
                    target = "Ldev/emi/emi/api/stack/EmiIngredient;getEmiStacks()Ljava/util/List;"
            ),
            cancellable = true,
            require = 1
    )
    private static void expandTagsBeforeDisplay(EmiIngredient ingredient, CallbackInfo ci) {

        if (ingredient.getEmiStacks().size() != 1) return;

        List<EmiStack> stacks = new ArrayList<>();
        var es = ingredient.getEmiStacks().get(0);
        ItemStack is = es.getItemStack();

        if (!is.isEmpty()) {
            stacks = TAG_CACHE.computeIfAbsent(is, EmiApiTagExpandMixin::extendByRelatedTags);
        }

        if (!stacks.isEmpty()) {
            stacks.add(es);
            EmiIngredient newIngredient = EmiIngredient.of(stacks);

            List<EmiRecipe> recipes = new ArrayList<>();
            for (EmiStack s : stacks) {
                recipes.addAll(EmiApi.getRecipeManager().getRecipesByOutput(s));
            }
            setPages(mapRecipes(recipes), newIngredient);
            focusRecipe(BoM.getRecipe(es));
            ci.cancel();
        }
    }

    @Unique
    private static List<EmiStack> extendByRelatedTags(ItemStack stack) {
        List<EmiStack> output = new ArrayList<>();
        stack.getTags().forEach(tag -> {

            String path = tag.location().getPath();
            int idx = path.indexOf('/');
            if (!tag.location().getNamespace().equals("forge") || idx <= 0) return;

            String prefix = path.substring(0, idx);
            String suffix = path.substring(idx);

            Set<String> related = TAG_RELATIONS.getRelatedTags(prefix);
            if(!related.isEmpty())
            {
                for (String p : related) {

                    ResourceLocation itemLoc =
                            ResourceLocation.tryBuild(tag.location().getNamespace(), p + suffix);

                    List<EmiStack> itemStacks = processItemTag(itemLoc);

                    output.addAll(itemStacks);
                }
                ResourceLocation fluidLoc = ResourceLocation.tryBuild("forge", suffix.substring(1));
                output.addAll(processFluidTag(fluidLoc));
                ResourceLocation moltenFluidLoc = ResourceLocation.tryBuild("forge", "molten_" + suffix.substring(1));
                output.addAll(processFluidTag(moltenFluidLoc));
            }

        });
        return output;
    }

    @Unique
    private static List<EmiStack> processItemTag(ResourceLocation loc) {

        TagKey<Item> key = TagKey.create(Registries.ITEM, loc);
        ITag<Item> tag = ForgeRegistries.ITEMS.tags().getTag(key);
        if (tag.isEmpty()) {
            return List.of();
        }
        List<EmiStack> list = tag.stream()
                .map(item -> EmiStack.of(new ItemStack(item)))
                .toList();

        return list;
    }

    @Unique
    private static List<EmiStack> processFluidTag(ResourceLocation loc) {

        TagKey<Fluid> key = TagKey.create(Registries.FLUID, loc);
        ITag<Fluid> tag = ForgeRegistries.FLUIDS.tags().getTag(key);

        if (tag.isEmpty()) return List.of();

        List<EmiStack> list = tag.stream()
                .map(EmiStack::of)
                .toList();

        return list;
    }
}
