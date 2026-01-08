package io.github.cpearl0.ctnhcore.mixin.emi;

import io.github.cpearl0.ctnhcore.utils.TagRelationGraph;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import dev.emi.emi.api.EmiApi;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.bom.BoM;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static dev.emi.emi.api.EmiApi.focusRecipe;

@Mixin(value = EmiApi.class, remap = false)
public abstract class EmiApiTagExpandMixin {

    @Shadow
    private static void setPages(Map<EmiRecipeCategory, List<EmiRecipe>> recipes, EmiIngredient stack) {}

    @Shadow
    private static Map<EmiRecipeCategory, List<EmiRecipe>> mapRecipes(List<EmiRecipe> list) {
        return null;
    }

    @Shadow
    private static List<EmiRecipe> pruneUses(List<EmiRecipe> list, EmiIngredient context) {
        return null;
    }

    @Shadow
    private static List<EmiRecipe> pruneSources(List<EmiRecipe> list, EmiStack context) {
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
                     target = "Ldev/emi/emi/api/stack/EmiIngredient;getEmiStacks()Ljava/util/List;"),
            cancellable = true,
            require = 1)
    private static void expandTagsBeforeDisplay(EmiIngredient ingredient, CallbackInfo ci) {
        if (ingredient.getEmiStacks().size() != 1) return;
        List<EmiRecipe> recipes = new ArrayList<>();
        var es = ingredient.getEmiStacks().get(0);
        EmiIngredient fluid = ctnhcore$getFluidFromStack(ingredient);
        if (fluid != null) {
            EmiStack fluidStack = fluid.getEmiStacks().get(0);

            recipes.addAll(pruneSources(
                    EmiApi.getRecipeManager().getRecipesByOutput(fluidStack),
                    fluidStack
            ));
            recipes.addAll(pruneSources(EmiApi.getRecipeManager().getRecipesByOutput(es), es));

            if (!recipes.isEmpty()) {
                setPages(mapRecipes(recipes), ingredient);
                focusRecipe(BoM.getRecipe(fluidStack));
                ci.cancel();
                return;
            }
        }

        List<EmiStack> stacks = new ArrayList<>();


        ItemStack is = es.getItemStack();

        if (!is.isEmpty()) {
            stacks = TAG_CACHE.computeIfAbsent(is, EmiApiTagExpandMixin::ctnhcore$extendByRelatedTags);
        }

        if (!stacks.isEmpty()) {
            stacks.add(es);
            EmiIngredient newIngredient = EmiIngredient.of(stacks);


            for (EmiStack s : stacks) {
                recipes.addAll(EmiApi.getRecipeManager().getRecipesByOutput(s));
            }
            setPages(mapRecipes(recipes), newIngredient);
            focusRecipe(BoM.getRecipe(es));
            ci.cancel();
        }
    }

    @Inject(method = "displayUses", at = @At("HEAD"), remap = false, cancellable = true)
    private static void injectFluidUses(EmiIngredient stack, CallbackInfo ci) {
        if (stack.isEmpty()) return;
        EmiStack zero = stack.getEmiStacks().get(0);
        EmiIngredient fluid = ctnhcore$getFluidFromStack(stack);
        if (fluid == null) return;

        EmiStack fluidStack = fluid.getEmiStacks().get(0);

        List<EmiRecipe> uses = new ArrayList<>();
        uses.addAll(pruneUses(
                EmiApi.getRecipeManager().getRecipesByInput(fluidStack),
                fluid
        ));
        uses.addAll(pruneUses(EmiApi.getRecipeManager().getRecipesByInput(zero), zero));
        if (!uses.isEmpty()) {
            setPages(mapRecipes(uses), stack);
            ci.cancel();
        }

    }


    @Unique
    private static List<EmiStack> ctnhcore$extendByRelatedTags(ItemStack stack) {
        List<EmiStack> output = new ArrayList<>();
        stack.getTags().forEach(tag -> {

            String path = tag.location().getPath();
            int idx = path.indexOf('/');
            if (!tag.location().getNamespace().equals("forge") || idx <= 0) return;

            String prefix = path.substring(0, idx);
            String suffix = path.substring(idx);

            Set<String> related = TAG_RELATIONS.getRelatedTags(prefix);
            if (!related.isEmpty()) {
                for (String p : related) {

                    ResourceLocation itemLoc = ResourceLocation.tryBuild(tag.location().getNamespace(), p + suffix);

                    List<EmiStack> itemStacks = ctnhcore$processItemTag(itemLoc);

                    output.addAll(itemStacks);
                }
                ResourceLocation fluidLoc = ResourceLocation.tryBuild("forge", suffix.substring(1));
                output.addAll(ctnhcore$processFluidTag(fluidLoc));
                ResourceLocation moltenFluidLoc = ResourceLocation.tryBuild("forge", "molten_" + suffix.substring(1));
                output.addAll(ctnhcore$processFluidTag(moltenFluidLoc));
            }

        });
        return output;
    }

    @Unique
    private static List<EmiStack> ctnhcore$processItemTag(ResourceLocation loc) {
        TagKey<Item> key = TagKey.create(Registries.ITEM, loc);
        ITag<Item> tag = ForgeRegistries.ITEMS.tags().getTag(key);

        return tag.stream()
                .map(item -> EmiStack.of(new ItemStack(item)))
                .toList();
    }

    @Unique
    private static List<EmiStack> ctnhcore$processFluidTag(ResourceLocation loc) {
        TagKey<Fluid> key = TagKey.create(Registries.FLUID, loc);
        ITag<Fluid> tag = ForgeRegistries.FLUIDS.tags().getTag(key);

        return tag.stream()
                .map(EmiStack::of)
                .toList();
    }

    @Unique
    private static @Nullable EmiIngredient ctnhcore$getFluidFromStack(EmiIngredient ingredient) {
        if (!(ingredient instanceof EmiStack stack)) {
            return null;
        }

        // 1. 原版桶
        if (stack.getKey() instanceof BucketItem bucket) {
            Fluid fluid = bucket.getFluid();
            return fluid == Fluids.EMPTY ? null : EmiStack.of(fluid);
        }

        // 2. Forge 流体容器
        if (stack.hasNbt()) {
            Fluid fluid = stack.getItemStack()
                    .getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM)
                    .map(h -> h.getFluidInTank(0).getFluid())
                    .orElse(Fluids.EMPTY);

            return fluid == Fluids.EMPTY ? null : EmiStack.of(fluid);
        }

        return null;
    }

}
