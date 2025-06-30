package io.github.cpearl0.ctnhcore.mixin.jei;

import com.mojang.logging.LogUtils;
import io.github.cpearl0.ctnhcore.utils.TagRelationGraph;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IFocusFactory;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.gui.recipes.RecipeGuiLogic;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.*;
import java.util.stream.Collectors;

@Mixin(value = RecipeGuiLogic.class, remap = false)
public abstract class RecipeGuiLogicMixin {
    private static final Logger LOGGER = LogUtils.getLogger();

    @Shadow
    @Final
    private IFocusFactory focusFactory;

    @Unique
    private static final TagRelationGraph TAG_RELATIONS = new TagRelationGraph();

    @Unique
    private static final Map<ResourceLocation, List<ItemStack>> TAG_CACHE = new HashMap<>();
    static {
        // 定义互相可转换的标签组
        TAG_RELATIONS.addRelationGroup(List.of("ingots", "nuggets", "hot_ingots"));
        TAG_RELATIONS.addRelationGroup(List.of( "dusts", "small_dusts", "tiny_dusts"));
    }


    @ModifyVariable(
            method = "showFocus",
            at = @At("HEAD"),
            argsOnly = true
    )
    private IFocusGroup enhanceTaggedRecipes(IFocusGroup originalFocuses) {
        List<IFocus<?>> newFocuses = new ArrayList<>(originalFocuses.getAllFocuses());

        originalFocuses.getAllFocuses().forEach(focus -> {
            if (focus.getTypedValue().getType() == VanillaTypes.ITEM_STACK &&
            focus.getRole() == RecipeIngredientRole.OUTPUT) {
                ItemStack stack = (ItemStack) focus.getTypedValue().getIngredient();
                cTNH_Core$processTagRelations(stack, newFocuses);
            }
        });

        return focusFactory.createFocusGroup(newFocuses);
    }

    @Unique
    private void cTNH_Core$processTagRelations(ItemStack stack, List<IFocus<?>> focusList) {
        stack.getTags().forEach(tag -> {
            String tagPath = tag.location().getPath();
            int slashIndex = tagPath.indexOf('/');
            if (slashIndex == -1) return;

            String prefix = tagPath.substring(0, slashIndex);
            String suffix = tagPath.substring(slashIndex);

            // 获取所有相关联的标签前缀
            Set<String> relatedPrefixes = TAG_RELATIONS.getRelatedTags(prefix);

            relatedPrefixes.forEach(relatedPrefix -> {
                ResourceLocation relatedTagLoc = new ResourceLocation(
                        tag.location().getNamespace(),
                        relatedPrefix + suffix
                );
                List<ItemStack> stacks = TAG_CACHE.get(relatedTagLoc);
                if(stacks == null)
                {
                    stacks = cTNH_Core$processAndCacheTargetTag(relatedTagLoc);
                }
                stacks.forEach(s -> {
                    focusList.add(focusFactory.createFocus(
                            RecipeIngredientRole.OUTPUT,
                            VanillaTypes.ITEM_STACK,
                            s
                    ));
                });
            });
        });
    }

    @Unique
    private List<ItemStack> cTNH_Core$processAndCacheTargetTag(ResourceLocation targetLoc) {
        TagKey<Item> targetTagKey = TagKey.create(Registries.ITEM, targetLoc);
        ITag<Item> targetTag = ForgeRegistries.ITEMS.tags().getTag(targetTagKey);

        if (targetTag.isEmpty()) {
            LOGGER.debug("[JEI Debug] No item matches the Tag: {}", targetLoc);
            // 缓存空结果避免重复查询
            TAG_CACHE.put(targetLoc, Collections.emptyList());
            return Collections.emptyList();
        }

        List<ItemStack> stacks = targetTag.stream()
                .map(ItemStack::new)
                .collect(Collectors.toList());

        // 缓存结果
        TAG_CACHE.put(targetLoc, stacks);
        return stacks;
    }
}