package io.github.cpearl0.ctnhcore.common.machine.multiblock.part;

import io.github.cpearl0.ctnhcore.api.recipe.multithread.MultiThreadRecipeLogic;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockDisplayText;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredPartMachine;
import com.gregtechceu.gtceu.common.data.GTRecipeCategories;
import com.gregtechceu.gtceu.integration.emi.recipe.GTEmiRecipe;
import com.gregtechceu.gtceu.integration.emi.recipe.GTRecipeEMICategory;
import com.gregtechceu.gtceu.integration.jei.recipe.GTRecipeJEICategory;

import com.lowdragmc.lowdraglib.gui.util.ClickData;
import com.lowdragmc.lowdraglib.gui.widget.ComponentPanelWidget;
import com.lowdragmc.lowdraglib.gui.widget.DraggableScrollableWidgetGroup;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.jei.JEIPlugin;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

import dev.emi.emi.api.EmiApi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class AsynThreadHatchMachine extends TieredPartMachine implements IFancyUIMachine {

    static int MAX_THREADS = 16;

    @DescSynced
    @Persisted
    ArrayList<String> recipeCategoryCache = new ArrayList<>();

    @DescSynced
    @Persisted
    ArrayList<String> recipeIDCache = new ArrayList<>();

    public AsynThreadHatchMachine(IMachineBlockEntity holder, int tier) {
        super(holder, tier);
        for (int i = 0; i < MAX_THREADS; i++) {
            recipeCategoryCache.add("null");
            recipeIDCache.add("null");
        }
    }

    @Override
    public boolean hasPlayerInventory() {
        return false;
    }

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 200 + 8, 200 + 8);
        group.addWidget(new DraggableScrollableWidgetGroup(4, 4, 200, 200).setBackground(GuiTextures.DISPLAY)
                // .addWidget(new LabelWidget(4, 5, self().getBlockState().getBlock().getDescriptionId()))
                .addWidget(new ComponentPanelWidget(4, 5, this::addDisplayText)
                        .textSupplier(this.getLevel().isClientSide ? null : this::addDisplayText)
                        .setMaxWidthLimit(200)
                        .clickHandler(this::handleDisplayClick)));

        group.setBackground(GuiTextures.BACKGROUND_INVERSE);
        return group;
    }

    MutableComponent enabledText(boolean e) {
        String enabled = "已启用";
        String notEnabled = "已禁用";
        return Component.literal(e ? enabled : notEnabled);
    }

    UnaryOperator<Style> hoverText(Component text) {
        return style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, text));
    }

    void addDisplayText(List<Component> textList) {
        if (getControllers().isEmpty() || !getControllers().first().isFormed()) {
            textList.add(Component.literal("无控制目标"));
        } else if (getControllers().first() instanceof WorkableElectricMultiblockMachine machine &&
                machine.getRecipeLogic() instanceof MultiThreadRecipeLogic recipeLogic) {
                    textList.add(Component.translatable("控制目标：%s",
                            Component.translatable(machine.getBlockState().getBlock().getDescriptionId())));
                    MultiblockDisplayText.builder(textList, isFormed())
                            .addEnergyUsageLine(machine.getEnergyContainer())
                            .addEnergyTierLine(machine.getTier());
                    for (int i = 0; i < recipeLogic.getAllWorkers().size(); i++) {

                        textList.add(Component.literal("--------------------------------"));
                        var thread = recipeLogic.getAllWorkers().get(i);

                        Component hoverText2 = Component.literal("点击切换").withStyle(ChatFormatting.GRAY);

                        textList.add(Component.translatable("线程%s:", i + 1).append(
                                ComponentPanelWidget.withButton(
                                        enabledText(thread.isEnabled()), "enable_" + i).copy()
                                        .withStyle(hoverText(hoverText2)))

                );

                        var overclockButtonText = Component.translatable("");
                        Component hoverText0 = Component.literal("设置该线程可用于运行配方或超频的最大EU/t")
                                .withStyle(ChatFormatting.GRAY);
                        overclockButtonText.append(Component.literal("超频等级：").withStyle(hoverText(hoverText0)));

                        // Component hoverText2 = Component.literal("add").withStyle(ChatFormatting.GRAY);
                        overclockButtonText.append(ComponentPanelWidget.withButton(
                                Component.literal("[-] "), "subOverclockTier_" + i));
                        Component voltageName = Component.literal(GTValues.VNF[thread.getOverclockTier()]);
                        overclockButtonText.append(voltageName);
                        overclockButtonText.append(ComponentPanelWidget.withButton(
                                Component.literal(" [+]"), "addOverclockTier_" + i));

                        textList.add(overclockButtonText);

                        Component hoverText5 = Component.literal("点击查询").withStyle(ChatFormatting.GRAY);
                        var lastRecipe = thread.getLastOriginRecipe();
                        var id = "无";
                        if (lastRecipe != null) {
                            recipeCategoryCache.set(i, lastRecipe.recipeCategory.name);
                            recipeIDCache.set(i, lastRecipe.id.toString());
                            id = lastRecipe.id.toString();
                        }
                        textList.add(Component.translatable("上一个配方：")
                                .append(
                                        ComponentPanelWidget.withButton(Component.literal(id), "lastRecipe_" + i)
                                                .copy().withStyle(hoverText(hoverText5))));

                        Component hoverText1 = Component.literal("启用后，该线程只会运行上一个运行的配方，且无视线程保护")
                                .withStyle(ChatFormatting.GRAY);
                        var lockRecipeButtonText = Component.literal("");
                        lockRecipeButtonText.append(Component.literal("配方锁定：").withStyle(hoverText(hoverText1)));
                        lockRecipeButtonText.append(
                                ComponentPanelWidget.withButton(enabledText(thread.isLockRecipe()), "switchLock_" + i)
                                        .copy().withStyle(hoverText(hoverText2)));
                        textList.add(lockRecipeButtonText);

                        Component hoverText3 = Component.literal("启用后，该线程不会运行其他线程已锁定或正在运行的配方")
                                .withStyle(ChatFormatting.GRAY);

                        var threadProtectButtonText = Component.literal("");
                        threadProtectButtonText.append(Component.literal("线程保护：").withStyle(hoverText(hoverText3)));
                        threadProtectButtonText.append(
                                ComponentPanelWidget
                                        .withButton(enabledText(thread.isThreadProtect()), "switchProtect_" + i)
                                        .copy().withStyle(hoverText(hoverText2)));
                        textList.add(threadProtectButtonText);

                    }
                }
    }

    void handleDisplayClick(String componentData, ClickData clickData) {
        if (!(clickData.button == 0)) return;
        String[] parts = componentData.split("_");
        if (parts.length != 2) return;
        String op = parts[0];
        int id = Integer.parseInt(parts[1]);

        if (!clickData.isRemote) {
            if (getControllers().first() instanceof WorkableElectricMultiblockMachine machine &&
                    machine.getRecipeLogic() instanceof MultiThreadRecipeLogic recipeLogic) {
                if (id < 0 || id >= recipeLogic.getAllWorkers().size()) return;
                var thread = recipeLogic.getAllWorkers().get(id);
                switch (op) {
                    case "enable": {
                        recipeLogic.setWorkingEnabled(!thread.isEnabled(), id);
                        break;
                    }
                    case "subOverclockTier": {
                        var tier = thread.getOverclockTier();
                        if (tier > 0) thread.setOverclockTier(tier - 1);
                        break;
                    }
                    case "addOverclockTier": {
                        var tier = thread.getOverclockTier();
                        if (tier < GTValues.MAX_TRUE) thread.setOverclockTier(tier + 1);
                        break;
                    }
                    case "switchLock": {
                        thread.setLockRecipe(!thread.isLockRecipe());
                        break;
                    }
                    case "switchProtect": {
                        thread.setThreadProtect(!thread.isThreadProtect());
                        break;
                    }
                }

            }
        } else if (op.equals("lastRecipe")) {
            String recipeCategoryName = recipeCategoryCache.get(id);
            String recipeID = recipeIDCache.get(id);
            if (recipeCategoryName.equals("null") || recipeID.equals("null")) return;
            var recipeCategory = GTRecipeCategories.get(recipeCategoryName);
            recipeCategory.getRecipeType().getRecipesInCategory(recipeCategory).stream()
                    .filter(r -> r.id.toString().equals(recipeID))
                    .findFirst()
                    .ifPresent(
                            recipe -> {
                                if (GTCEu.Mods.isJEILoaded()) {
                                    var category = new GTRecipeJEICategory(JEIPlugin.jeiHelpers, recipeCategory);
                                    JEIPlugin.jeiRuntime.getRecipesGui().showRecipes(
                                            category,
                                            List.of(recipe),
                                            List.of());
                                } else if (GTCEu.Mods.isEMILoaded()) {
                                    var category = GTRecipeEMICategory.machineCategory(recipeCategory);
                                    EmiApi.displayRecipe(new GTEmiRecipe(recipe, category));
                                }
                            });

        }
    }

    @Override
    public boolean canShared() {
        return false;
    }
}
