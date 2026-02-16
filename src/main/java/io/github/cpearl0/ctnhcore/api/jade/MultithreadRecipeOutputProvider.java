package io.github.cpearl0.ctnhcore.api.jade;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.api.recipe.multithread.MultiThreadRecipeLogic;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.capability.GTCapabilityHelper;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.IntProviderFluidIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.IntProviderIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.integration.jade.GTElementHelper;
import com.gregtechceu.gtceu.integration.jade.provider.CapabilityBlockProvider;
import com.gregtechceu.gtceu.utils.GTUtil;

import net.minecraft.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import snownee.jade.api.BlockAccessor;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.fluid.JadeFluidObject;
import snownee.jade.api.ui.IElementHelper;
import snownee.jade.api.ui.BoxStyle;
import snownee.jade.util.FluidTextHelper;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import org.jetbrains.annotations.Nullable;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Key;

import java.util.ArrayList;
import java.util.List;

public class MultithreadRecipeOutputProvider extends CapabilityBlockProvider<RecipeLogic> {

    public MultithreadRecipeOutputProvider() {
        super(CTNHCore.id("recipe_output_provider"));
    }

    @Key("jade.recipe_output")
    @CN("线程%s - 配方输出：")
    @EN("Thread %s - Recipe Output:")
    static Lang recipe_output;

    @Override
    protected @Nullable RecipeLogic getCapability(Level level, BlockPos pos, @Nullable Direction side) {
        var recipeLogic = GTCapabilityHelper.getRecipeLogic(level, pos, side);
        return recipeLogic instanceof MultiThreadRecipeLogic ? recipeLogic : null;
    }

    @Override
    protected void write(CompoundTag data, RecipeLogic recipeLogic) {
        if (!(recipeLogic instanceof MultiThreadRecipeLogic multiThreadRecipeLogic)) return;

        ListTag workersTag = new ListTag();
        int index = 0;
        for (var worker : multiThreadRecipeLogic.getAllWorkers()) {
            if (!worker.isWorking()) {
                index++;
                continue;
            }
            var recipe = worker.getLastRecipe();
            if (recipe == null) {
                index++;
                continue;
            }

            CompoundTag workerTag = new CompoundTag();
            workerTag.putInt("Index", index);
            workerTag.putBoolean("Active", worker.isActive());
            workerTag.putInt("Progress", worker.getProgress());
            workerTag.putInt("MaxProgress", worker.getMaxProgress());

            int recipeTier = RecipeHelper.getPreOCRecipeEuTier(recipe);
            int chanceTier = recipeTier + recipe.ocLevel;
            var function = recipe.getType().getChanceFunction();
            var itemContents = recipe.getOutputContents(ItemRecipeCapability.CAP);
            var fluidContents = recipe.getOutputContents(FluidRecipeCapability.CAP);
            int runs = recipe.parallels * recipe.batchParallels;

            ListTag itemTags = new ListTag();
            for (var item : itemContents) {
                CompoundTag itemTag;
                if (item.content instanceof IntProviderIngredient provider) {
                    IntProviderIngredient chanced = provider;
                    if (item.chance < item.maxChance) {
                        double countD = (double) runs * function.getBoostedChance(item, recipeTier, chanceTier) /
                                item.maxChance;
                        chanced = (IntProviderIngredient) ItemRecipeCapability.CAP.copyWithModifier(provider,
                                ContentModifier.multiplier(countD));
                    }
                    itemTag = (CompoundTag) JsonOps.INSTANCE.convertTo(NbtOps.INSTANCE, chanced.toJson());
                } else {
                    var stacks = ItemRecipeCapability.CAP.of(item.content).getItems();
                    if (stacks.length == 0 || stacks[0].isEmpty()) continue;
                    var stack = stacks[0];
                    itemTag = new CompoundTag();
                    GTUtil.saveItemStack(stack, itemTag);
                    if (item.chance < item.maxChance) {
                        int count = stack.getCount();
                        double countD = (double) count * runs *
                                function.getBoostedChance(item, recipeTier, chanceTier) / item.maxChance;
                        count = Math.max(1, (int) Math.round(countD));
                        itemTag.putInt("Count", count);
                    }
                }
                itemTags.add(itemTag);
            }
            if (!itemTags.isEmpty()) {
                workerTag.put("OutputItems", itemTags);
            }

            ListTag fluidTags = new ListTag();
            for (var fluid : fluidContents) {
                CompoundTag fluidTag;
                if (fluid.content instanceof IntProviderFluidIngredient provider) {
                    IntProviderFluidIngredient chanced = provider;
                    if (fluid.chance < fluid.maxChance) {
                        double countD = (double) runs * function.getBoostedChance(fluid, recipeTier, chanceTier) /
                                fluid.maxChance;
                        chanced = (IntProviderFluidIngredient) FluidRecipeCapability.CAP.copyWithModifier(provider,
                                ContentModifier.multiplier(countD));
                    }
                    fluidTag = chanced.toNBT();
                } else {
                    FluidStack[] stacks = FluidRecipeCapability.CAP.of(fluid.content).getStacks();
                    if (stacks.length == 0 || stacks[0].isEmpty()) continue;
                    var stack = stacks[0];
                    fluidTag = new CompoundTag();
                    stack.writeToNBT(fluidTag);
                    if (fluid.chance < fluid.maxChance) {
                        int amount = stack.getAmount();
                        double amountD = (double) amount * runs *
                                function.getBoostedChance(fluid, recipeTier, chanceTier) / fluid.maxChance;
                        amount = Math.max(1, (int) Math.round(amountD));
                        fluidTag.putInt("Amount", amount);
                    }
                }
                fluidTags.add(fluidTag);
            }
            if (!fluidTags.isEmpty()) {
                workerTag.put("OutputFluids", fluidTags);
            }

            workersTag.add(workerTag);
            index++;
        }

        if (!workersTag.isEmpty()) {
            data.put("Workers", workersTag);
        }
    }

    @Override
    protected void addTooltip(CompoundTag capData, ITooltip tooltip, Player player, BlockAccessor block,
                              BlockEntity blockEntity, IPluginConfig config) {
        if (!capData.contains("Workers", Tag.TAG_LIST)) return;

        ListTag workers = capData.getList("Workers", Tag.TAG_COMPOUND);
        if (workers.isEmpty()) return;

        for (Tag workerTag : workers) {
            if (!(workerTag instanceof CompoundTag t)) continue;
            if (!t.getBoolean("Active")) continue;

            int index = t.getInt("Index");
            Component indexComponent = Component.literal(String.valueOf(index + 1)).withStyle(ChatFormatting.DARK_PURPLE);
            tooltip.add(recipe_output.translate(indexComponent).withStyle(ChatFormatting.GRAY));

            int currentProgress = t.getInt("Progress");
            int maxProgress = t.getInt("MaxProgress");
            if (maxProgress > 0) {
                Component text;
                if (maxProgress < 20) {
                    text = Component.translatable("gtceu.jade.progress_tick", currentProgress, maxProgress);
                } else {
                    text = Component.translatable("gtceu.jade.progress_sec", Math.round(currentProgress / 20.0F),
                            Math.round(maxProgress / 20.0F));
                }

                int color = 0xFFBB1C28;
                tooltip.add(
                        tooltip.getElementHelper().progress(
                                getProgress(currentProgress, maxProgress),
                                text,
                                tooltip.getElementHelper().progressStyle().color(color).textColor(-1),
                                Util.make(BoxStyle.DEFAULT, style -> style.borderColor = 0xFF555555),
                                true));
            }

            addOutputsTooltip(t, tooltip);
        }
    }

    private void addOutputsTooltip(CompoundTag capData, ITooltip tooltip) {
        List<Ingredient> outputItems = new ArrayList<>();
        if (capData.contains("OutputItems", Tag.TAG_LIST)) {
            ListTag itemTags = capData.getList("OutputItems", Tag.TAG_COMPOUND);
            if (!itemTags.isEmpty()) {
                for (Tag tag : itemTags) {
                    if (tag instanceof CompoundTag tCompoundTag) {
                        if (tCompoundTag.contains("count_provider")) {
                            var ingredient = IntProviderIngredient.SERIALIZER
                                    .parse((JsonObject) NbtOps.INSTANCE.convertTo(JsonOps.INSTANCE, tCompoundTag));
                            outputItems.add(ingredient);
                        } else {
                            var stack = GTUtil.loadItemStack(tCompoundTag);
                            if (!stack.isEmpty()) {
                                outputItems.add(SizedIngredient.create(stack));
                            }
                        }
                    }
                }
            }
        }
        List<FluidIngredient> outputFluids = new ArrayList<>();
        if (capData.contains("OutputFluids", Tag.TAG_LIST)) {
            ListTag fluidTags = capData.getList("OutputFluids", Tag.TAG_COMPOUND);
            for (Tag tag : fluidTags) {
                if (tag instanceof CompoundTag tCompoundTag) {
                    if (tCompoundTag.contains("count_provider")) {
                        var ingredient = IntProviderFluidIngredient.fromNBT(tCompoundTag);
                        outputFluids.add(ingredient);
                    } else {
                        var stack = FluidStack.loadFluidStackFromNBT(tCompoundTag);
                        if (!stack.isEmpty()) {
                            outputFluids.add(FluidIngredient.of(stack));
                        }
                    }
                }
            }
        }
        addItemTooltips(tooltip, outputItems);
        addFluidTooltips(tooltip, outputFluids);
    }

    private void addItemTooltips(ITooltip iTooltip, List<Ingredient> outputItems) {
        IElementHelper helper = iTooltip.getElementHelper();
        for (Ingredient itemOutput : outputItems) {
            if (itemOutput != null && !itemOutput.isEmpty()) {
                ItemStack item;
                MutableComponent text = CommonComponents.space();
                if (itemOutput instanceof IntProviderIngredient provider) {
                    item = provider.getInner().getItems()[0];
                    text = text.append(Component.translatable("gtceu.gui.content.range",
                            String.valueOf(provider.getCountProvider().getMinValue()),
                            String.valueOf(provider.getCountProvider().getMaxValue())));
                } else {
                    item = itemOutput.getItems()[0];
                    text.append(String.valueOf(item.getCount()));
                    item.setCount(1);
                }
                text.append(Component.translatable("gtceu.gui.content.times_item", getItemName(item))
                        .withStyle(ChatFormatting.WHITE));

                iTooltip.add(helper.smallItem(item));
                iTooltip.append(text);
            }
        }
    }

    private void addFluidTooltips(ITooltip iTooltip, List<FluidIngredient> outputFluids) {
        for (FluidIngredient fluidOutput : outputFluids) {
            if (fluidOutput != null && !fluidOutput.isEmpty()) {
                FluidStack stack;
                MutableComponent text = CommonComponents.space();
                if (fluidOutput instanceof IntProviderFluidIngredient provider) {
                    stack = provider.getInner().getStacks()[0];
                    text.append(Component.translatable("gtceu.gui.content.range",
                            FluidTextHelper.getUnicodeMillibuckets(provider.getCountProvider().getMinValue(), true),
                            FluidTextHelper.getUnicodeMillibuckets(provider.getCountProvider().getMaxValue(), true)));
                } else {
                    stack = fluidOutput.getStacks()[0];
                    text.append(FluidTextHelper.getUnicodeMillibuckets(stack.getAmount(), true));
                }
                text.append(CommonComponents.space()).append(getFluidName(stack)).withStyle(ChatFormatting.WHITE);

                iTooltip.add(GTElementHelper.smallFluid(getFluid(stack)));
                iTooltip.append(text);
            }
        }
    }

    private Component getItemName(ItemStack stack) {
        return stack.getDisplayName().copy().withStyle(ChatFormatting.WHITE);
    }

    private Component getFluidName(FluidStack stack) {
        return ComponentUtils.wrapInSquareBrackets(stack.getDisplayName()).withStyle(ChatFormatting.WHITE);
    }

    private JadeFluidObject getFluid(FluidStack stack) {
        return JadeFluidObject.of(stack.getFluid(), stack.getAmount());
    }
}
