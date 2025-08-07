package io.github.cpearl0.ctnhcore.api;

import com.gregtechceu.gtceu.api.block.IMachineBlock;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.gui.editor.EditableMachineUI;
import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.BlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockShapeInfo;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.api.registry.registrate.MultiblockMachineBuilder;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import lombok.Generated;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class CTNHMultiblockBuilder extends MultiblockMachineBuilder {
    protected CTNHMultiblockBuilder(GTRegistrate registrate, String name, Function<IMachineBlockEntity, ? extends MultiblockControllerMachine> metaMachine, BiFunction<BlockBehaviour.Properties, MultiblockMachineDefinition, IMachineBlock> blockFactory, BiFunction<IMachineBlock, Item.Properties, MetaMachineItem> itemFactory, TriFunction<BlockEntityType<?>, BlockPos, BlockState, IMachineBlockEntity> blockEntityFactory) {
        super(registrate, name, metaMachine, blockFactory, itemFactory, blockEntityFactory);
    }
    private String under_component="ctnh.copyright.info";
    public static CTNHMultiblockBuilder createMulti(GTRegistrate registrate, String name, Function<IMachineBlockEntity, ? extends MultiblockControllerMachine> metaMachine, BiFunction<BlockBehaviour.Properties, MultiblockMachineDefinition, IMachineBlock> blockFactory, BiFunction<IMachineBlock, Item.Properties, MetaMachineItem> itemFactory, TriFunction<BlockEntityType<?>, BlockPos, BlockState, IMachineBlockEntity> blockEntityFactory) {
        return new CTNHMultiblockBuilder(registrate, name, metaMachine, blockFactory, itemFactory, blockEntityFactory);
    }

    public CTNHMultiblockBuilder shapeInfo(Function<MultiblockMachineDefinition, MultiblockShapeInfo> shape) {
        super.shapeInfo(shape);
        return this;
    }

    public CTNHMultiblockBuilder shapeInfos(Function<MultiblockMachineDefinition, List<MultiblockShapeInfo>> shapes) {
        super.shapeInfos(shapes);
        return this;
    }

    public CTNHMultiblockBuilder recoveryItems(Supplier<ItemLike[]> items) {
        super.recoveryItems(items);
        return this;
    }

    public CTNHMultiblockBuilder recoveryStacks(Supplier<ItemStack[]> stacks) {
        super.recoveryStacks(stacks);
        return this;
    }

    public CTNHMultiblockBuilder machine(Function<IMachineBlockEntity, MetaMachine> machine) {
        return (CTNHMultiblockBuilder)super.machine(machine);
    }

    public CTNHMultiblockBuilder shape(VoxelShape shape) {
        return (CTNHMultiblockBuilder)super.shape(shape);
    }

    public CTNHMultiblockBuilder multiblockPreviewRenderer(boolean multiBlockWorldPreview, boolean multiBlockXEIPreview) {
        return (CTNHMultiblockBuilder)super.multiblockPreviewRenderer(multiBlockWorldPreview, multiBlockXEIPreview);
    }

    public CTNHMultiblockBuilder rotationState(RotationState rotationState) {
        return (CTNHMultiblockBuilder)super.rotationState(rotationState);
    }


    public CTNHMultiblockBuilder blockProp(NonNullUnaryOperator<BlockBehaviour.Properties> blockProp) {
        return (CTNHMultiblockBuilder)super.blockProp(blockProp);
    }

    public CTNHMultiblockBuilder itemProp(NonNullUnaryOperator<Item.Properties> itemProp) {
        return (CTNHMultiblockBuilder)super.itemProp(itemProp);
    }

    public CTNHMultiblockBuilder blockBuilder(Consumer<BlockBuilder<? extends Block, ?>> blockBuilder) {
        return (CTNHMultiblockBuilder)super.blockBuilder(blockBuilder);
    }

    public CTNHMultiblockBuilder itemBuilder(Consumer<ItemBuilder<? extends MetaMachineItem, ?>> itemBuilder) {
        return (CTNHMultiblockBuilder)super.itemBuilder(itemBuilder);
    }

    public CTNHMultiblockBuilder recipeTypes(GTRecipeType... recipeTypes) {
        String typeName = "";
        for (int i = 0; i < recipeTypes.length; i++) {
            if (i != 0) {
                typeName += ", " + Component.translatable(recipeTypes[i].registryName.toLanguageKey()).getString();
            }
            else {
                typeName += Component.translatable(recipeTypes[i].registryName.toLanguageKey()).getString();
            }
        }
        this.tooltips(Component.translatable("ctnh.recipe_type.info", typeName));
        return (CTNHMultiblockBuilder)super.recipeTypes(recipeTypes);
    }

    public CTNHMultiblockBuilder recipeType(GTRecipeType recipeTypes) {
        var translationKey = recipeTypes.registryName.getNamespace() + "." + recipeTypes.registryName.getPath();
        this.tooltips(Component.translatable("ctnh.recipe_type.info", Component.translatable(translationKey).getString()));
        return (CTNHMultiblockBuilder)super.recipeType(recipeTypes);
    }

    public CTNHMultiblockBuilder tier(int tier) {
        return (CTNHMultiblockBuilder)super.tier(tier);
    }

    public CTNHMultiblockBuilder recipeOutputLimits(Object2IntMap<RecipeCapability<?>> map) {
        return (CTNHMultiblockBuilder)super.recipeOutputLimits(map);
    }

    public CTNHMultiblockBuilder addOutputLimit(RecipeCapability<?> capability, int limit) {
        return (CTNHMultiblockBuilder)super.addOutputLimit(capability, limit);
    }

    public CTNHMultiblockBuilder itemColor(BiFunction<ItemStack, Integer, Integer> itemColor) {
        return (CTNHMultiblockBuilder)super.itemColor(itemColor);
    }

    public CTNHMultiblockBuilder simpleModel(ResourceLocation model) {
        return (CTNHMultiblockBuilder)super.simpleModel(model);
    }

    public CTNHMultiblockBuilder defaultModel() {
        return (CTNHMultiblockBuilder)super.defaultModel();
    }

    public CTNHMultiblockBuilder tieredHullModel(ResourceLocation model) {
        return (CTNHMultiblockBuilder)super.tieredHullModel(model);
    }

    public CTNHMultiblockBuilder overlayTieredHullModel(String name) {
        return (CTNHMultiblockBuilder)super.overlayTieredHullModel(name);
    }

    public CTNHMultiblockBuilder overlayTieredHullModel(ResourceLocation overlayModel) {
        return (CTNHMultiblockBuilder)super.overlayTieredHullModel(overlayModel);
    }

    public CTNHMultiblockBuilder colorOverlayTieredHullModel(String overlay) {
        return (CTNHMultiblockBuilder)super.colorOverlayTieredHullModel(overlay);
    }

    public CTNHMultiblockBuilder colorOverlayTieredHullModel(String overlay, @Nullable String pipeOverlay, @Nullable String emissiveOverlay) {
        return (CTNHMultiblockBuilder)super.colorOverlayTieredHullModel(overlay, pipeOverlay, emissiveOverlay);
    }

    public CTNHMultiblockBuilder colorOverlayTieredHullModel(ResourceLocation overlay) {
        return (CTNHMultiblockBuilder)super.colorOverlayTieredHullModel(overlay);
    }

    public CTNHMultiblockBuilder colorOverlayTieredHullModel(ResourceLocation overlay, @Nullable ResourceLocation pipeOverlay, @Nullable ResourceLocation emissiveOverlay) {
        return (CTNHMultiblockBuilder)super.colorOverlayTieredHullModel(overlay, pipeOverlay, emissiveOverlay);
    }

    public CTNHMultiblockBuilder workableTieredHullModel(ResourceLocation workableModel) {
        return (CTNHMultiblockBuilder)super.workableTieredHullModel(workableModel);
    }

    public CTNHMultiblockBuilder simpleGeneratorModel(ResourceLocation workableModel) {
        return (CTNHMultiblockBuilder)super.simpleGeneratorModel(workableModel);
    }

    public CTNHMultiblockBuilder workableCasingModel(ResourceLocation baseCasing, ResourceLocation overlayModel) {
        return (CTNHMultiblockBuilder)super.workableCasingModel(baseCasing, overlayModel);
    }

    public CTNHMultiblockBuilder sidedOverlayCasingModel(ResourceLocation baseCasing, ResourceLocation workableModel) {
        return (CTNHMultiblockBuilder)super.sidedOverlayCasingModel(baseCasing, workableModel);
    }

    public CTNHMultiblockBuilder sidedWorkableCasingModel(ResourceLocation baseCasing, ResourceLocation workableModel) {
        return (CTNHMultiblockBuilder)super.sidedWorkableCasingModel(baseCasing, workableModel);
    }

    public CTNHMultiblockBuilder tooltipBuilder(BiConsumer<ItemStack, List<Component>> tooltipBuilder) {
        return (CTNHMultiblockBuilder)super.tooltipBuilder(tooltipBuilder);
    }

    public CTNHMultiblockBuilder appearance(Supplier<BlockState> state) {
        return (CTNHMultiblockBuilder)super.appearance(state);
    }

    public CTNHMultiblockBuilder appearanceBlock(Supplier<? extends Block> block) {
        return (CTNHMultiblockBuilder)super.appearanceBlock(block);
    }

    public CTNHMultiblockBuilder langValue(String langValue) {
        return (CTNHMultiblockBuilder)super.langValue(langValue);
    }

    public CTNHMultiblockBuilder tooltips(Component... components) {
        return (CTNHMultiblockBuilder)super.tooltips(components);
    }

    public CTNHMultiblockBuilder conditionalTooltip(Component component, Supplier<Boolean> condition) {
        return (CTNHMultiblockBuilder) super.conditionalTooltip(component, (Boolean)condition.get());
    }

    public CTNHMultiblockBuilder conditionalTooltip(Component component, boolean condition) {
        if (condition) {
            this.tooltips(component);
        }

        return this;
    }

    public CTNHMultiblockBuilder abilities(PartAbility... abilities) {
        return (CTNHMultiblockBuilder)super.abilities(abilities);
    }

    public CTNHMultiblockBuilder paintingColor(int paintingColor) {
        return (CTNHMultiblockBuilder)super.paintingColor(paintingColor);
    }

    public CTNHMultiblockBuilder recipeModifier(RecipeModifier recipeModifier) {
        return (CTNHMultiblockBuilder)super.recipeModifier(recipeModifier);
    }

    public CTNHMultiblockBuilder recipeModifier(RecipeModifier recipeModifier, boolean alwaysTryModifyRecipe) {
        return (CTNHMultiblockBuilder)super.recipeModifier(recipeModifier, alwaysTryModifyRecipe);
    }

    public CTNHMultiblockBuilder recipeModifiers(RecipeModifier... recipeModifiers) {
        return (CTNHMultiblockBuilder)super.recipeModifiers(recipeModifiers);
    }

    public CTNHMultiblockBuilder recipeModifiers(boolean alwaysTryModifyRecipe, RecipeModifier... recipeModifiers) {
        return (CTNHMultiblockBuilder)super.recipeModifiers(alwaysTryModifyRecipe, recipeModifiers);
    }

    public CTNHMultiblockBuilder noRecipeModifier() {
        return (CTNHMultiblockBuilder)super.noRecipeModifier();
    }

    public CTNHMultiblockBuilder alwaysTryModifyRecipe(boolean alwaysTryModifyRecipe) {
        return (CTNHMultiblockBuilder)super.alwaysTryModifyRecipe(alwaysTryModifyRecipe);
    }

    public CTNHMultiblockBuilder beforeWorking(BiPredicate<IRecipeLogicMachine, GTRecipe> beforeWorking) {
        return (CTNHMultiblockBuilder)super.beforeWorking(beforeWorking);
    }

    public CTNHMultiblockBuilder onWorking(Predicate<IRecipeLogicMachine> onWorking) {
        return (CTNHMultiblockBuilder)super.onWorking(onWorking);
    }

    public CTNHMultiblockBuilder onWaiting(Consumer<IRecipeLogicMachine> onWaiting) {
        return (CTNHMultiblockBuilder)super.onWaiting(onWaiting);
    }

    public CTNHMultiblockBuilder afterWorking(Consumer<IRecipeLogicMachine> afterWorking) {
        return (CTNHMultiblockBuilder)super.afterWorking(afterWorking);
    }

    public CTNHMultiblockBuilder regressWhenWaiting(boolean dampingWhenWaiting) {
        return (CTNHMultiblockBuilder)super.regressWhenWaiting(dampingWhenWaiting);
    }

    public CTNHMultiblockBuilder editableUI(@Nullable EditableMachineUI editableUI) {
        return (CTNHMultiblockBuilder)super.editableUI(editableUI);
    }

    public CTNHMultiblockBuilder onBlockEntityRegister(NonNullConsumer<BlockEntityType<BlockEntity>> onBlockEntityRegister) {
        return (CTNHMultiblockBuilder)super.onBlockEntityRegister(onBlockEntityRegister);
    }
    @Generated
    public CTNHMultiblockBuilder generator(boolean generator) {
        super.generator(generator);
        return this;
    }

    @Generated
    public CTNHMultiblockBuilder pattern(Function<MultiblockMachineDefinition, BlockPattern> pattern) {
        super.pattern(pattern);
        return this;
    }

    @Generated
    public CTNHMultiblockBuilder allowExtendedFacing(boolean allowExtendedFacing) {
        super.allowExtendedFacing(allowExtendedFacing);
        return this;
    }
    @Generated
    public CTNHMultiblockBuilder addUnderTooltip(String tooltip) {
        this.under_component=tooltip;
        return this;
    }

    @Generated
    public CTNHMultiblockBuilder allowFlip(boolean allowFlip) {
        super.allowFlip(allowFlip);
        return this;
    }

    @Generated
    public CTNHMultiblockBuilder partSorter(Comparator<IMultiPart> partSorter) {
        super.partSorter(partSorter);
        return this;
    }

    @Generated
    public CTNHMultiblockBuilder partAppearance(TriFunction<IMultiController, IMultiPart, Direction, BlockState> partAppearance) {
        super.partAppearance(partAppearance);
        return this;
    }

    @Generated
    public BiConsumer<IMultiController, List<Component>> additionalDisplay() {
        return super.additionalDisplay();
    }

    @Generated
    public CTNHMultiblockBuilder additionalDisplay(BiConsumer<IMultiController, List<Component>> additionalDisplay) {
        super.additionalDisplay(additionalDisplay);
        return this;
    }

    @Override
    public MultiblockMachineDefinition register() {
        this.tooltips(Component.literal("————————————————————————"),
                Component.translatable(under_component));
        return super.register();
    }
}
