package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyUIProvider;
import com.gregtechceu.gtceu.api.gui.fancy.TooltipsPanel;
import com.gregtechceu.gtceu.api.machine.ConditionalSubscriptionHandler;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IExplosionMachine;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDisplayUIMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.machine.multiblock.part.ItemBusPartMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.widget.*;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.part.HighSpeedPipeBlock;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.part.NeutronAcceleratorMachine;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.part.NeutronSensorMachine;
import io.github.cpearl0.ctnhcore.common.recipe.NeutronActivatorCondition;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import it.unimi.dsi.fastutil.longs.Long2ObjectMaps;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class NeutronActivatorMachine extends WorkableMultiblockMachine implements IFancyUIMachine, IDisplayUIMachine, IExplosionMachine {
    public ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NeutronActivatorMachine.class, WorkableMultiblockMachine.MANAGED_FIELD_HOLDER);
    public static int K = 1000;
    public static int M = 1000000;
    public static int MAX_ENERGY = 1200 * M;
    @Persisted
    private int height = 0;

    @Persisted
    @DescSynced
    private int eV = 0;

    @Persisted
    private boolean isWorking = false;
    private ConditionalSubscriptionHandler neutronEnergySubs = new ConditionalSubscriptionHandler(this, this::neutronEnergyUpdate, () -> isFormed);
    private ConditionalSubscriptionHandler moderateSubs = new ConditionalSubscriptionHandler(this, this::moderateUpdate, () -> eV > 0);
    private ConditionalSubscriptionHandler absorptionSubs = new ConditionalSubscriptionHandler(this, this::absorptionUpdate, () -> eV > 0);
    private HashSet<NeutronSensorMachine> sensorMachines = null;
    private HashSet<ItemBusPartMachine> busMachines = null;
    private HashSet<NeutronAcceleratorMachine> acceleratorMachines = null;
    public NeutronActivatorMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public void onStructureFormed() {
        // Declare 'height' as a local variable if not used elsewhere
        height = 0;
        super.onStructureFormed();

        // Cache the Map access to avoid repeated calls
        var matchContext = getMultiblockState().getMatchContext();
        var ioMap = matchContext.getOrCreate("ioMap", Long2ObjectMaps::emptyMap);

        // Cache the result of getParts() to prevent repetitive calls
        var parts = getParts();
        for (var part : parts) {
            IO io = (IO) ioMap.getOrDefault(part.self().getPos().asLong(), IO.BOTH);
            if (io == IO.NONE) continue;

            for (var handlerList : part.getRecipeHandlers()) {
                if (!handlerList.isValid(io)) continue;
                traitSubscriptions.add(handlerList.subscribe(neutronEnergySubs::updateSubscription, EURecipeCapability.CAP));
                traitSubscriptions.add(handlerList.subscribe(moderateSubs::updateSubscription, EURecipeCapability.CAP));
                traitSubscriptions.add(handlerList.subscribe(absorptionSubs::updateSubscription, ItemRecipeCapability.CAP));
            }
            if (part instanceof ItemBusPartMachine itemBusPartMachine) {
                busMachines = (busMachines != null) ? busMachines : new HashSet<>();
                busMachines.add(itemBusPartMachine);
            }
            if (part instanceof NeutronSensorMachine neutronSensorMachine) {
                sensorMachines = sensorMachines != null ? sensorMachines : new HashSet<>();
                sensorMachines.add(neutronSensorMachine);
            }
            if (part instanceof NeutronAcceleratorMachine neutronAcceleratorMachine) {
                acceleratorMachines = acceleratorMachines != null ? acceleratorMachines : new HashSet<>();
                acceleratorMachines.add(neutronAcceleratorMachine);
            }
            if (part instanceof HighSpeedPipeBlock) height++;
        }

        neutronEnergySubs.initialize(getLevel());
    }

    @Override
    public void onLoad() {
        super.onLoad();
        moderateSubs.initialize(getLevel());
    }
    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        height = 0;
        sensorMachines = null;
        busMachines = null;
        acceleratorMachines = null;
    }

    private void neutronEnergyUpdate() {
        if (acceleratorMachines == null) return;
        var anyWorking = false;
        for (var accelerator : acceleratorMachines) {
            var increase = accelerator.consumeEnergy();
            if (increase > 0) {
                anyWorking = true;
                this.eV += (int) Math.max(1.0, (increase * getEfficiencyFactor()));
            }
        }

        this.isWorking = anyWorking;

        if (this.eV > MAX_ENERGY) doExplosion(4F * 32F);

        if (!isWorking) neutronEnergySubs.unsubscribe();
    }

    private void moderateUpdate() {
        if (!isWorking && getOffsetTimer() % 20 == 0L) {
            this.eV = Math.max((eV - 72 * K),0);
        }
        if (this.eV < 0) this.eV = 0;
        if (!isFormed() || sensorMachines == null) return;
        sensorMachines.forEach(sensor -> sensor.update(eV));
    }

    private void absorptionUpdate() {
        if (busMachines == null || eV <= 0) return;
        var hasSlower = false;
        for (var bus : busMachines) {
            var inv = bus.getInventory();
            var io = inv.getHandlerIO();
            if (io == IO.IN || io == IO.BOTH) {
                for (int i = 0; i < inv.getSlots(); i++) {
                    var dustBeryllium = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Beryllium).getItem();
                    var dustGraphite = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Graphite).getItem();
                    var stack = inv.getStackInSlot(i);
                    if (stack.getItem().equals(dustBeryllium) || stack.getItem().equals(dustGraphite)) {
                        hasSlower = true;
                        var consume = Math.min(Math.max((eV / (10 * M)), 1), stack.getCount());
                        inv.extractItemInternal(i, consume, false);
                        this.eV -= 10 * M * consume;
                    }
                }
            }
        }
        if (!hasSlower) absorptionSubs.unsubscribe();
    }
    //////////////////////////////////////
    //**********     GUI     ***********//
    //////////////////////////////////////

    @Override
    public void addDisplayText(List<Component> textList) {
        IDisplayUIMachine.super.addDisplayText(textList);
        if (isFormed()) {
            textList.add(
                    Component.translatable(getRecipeType().registryName.toLanguageKey()).setStyle(
                            Style.EMPTY.withColor(ChatFormatting.AQUA).withHoverEvent(
                                    new HoverEvent(
                                            HoverEvent.Action.SHOW_TEXT, Component.translatable("gtceu.gui.machinemode.title")
                                    )
                            )
                    )
            );
            if (!isWorkingEnabled()) {
                textList.add(Component.translatable("gtceu.multiblock.work_paused"));
            } else if (this.isActive()) {
                textList.add(Component.translatable("gtceu.multiblock.running"));
                var currentProgress = (recipeLogic.getProgressPercent() * 100);
                textList.add(Component.translatable("gtceu.multiblock.progress", currentProgress));
            } else {
                textList.add(Component.translatable("gtceu.multiblock.idling"));
            }
            if (recipeLogic.isWaiting()) {
                textList.add(
                        Component.translatable("gtceu.multiblock.waiting")
                                .setStyle(Style.EMPTY.withColor(ChatFormatting.RED))
                );
            }
            textList.add(Component.translatable("ctnh.multiblock.neutron_activator.info.ev", processNumber(eV)));
            textList.add(
                    Component.translatable(
                            "ctnh.multiblock.neutron_activator.info.height", FormattingUtil.formatNumbers(height)
                    )
            );
            textList.add(
                    Component.translatable(
                            "ctnh.multiblock.neutron_activator.info.efficiency",
                            FormattingUtil.formatNumbers(getEfficiencyFactor() * 100)
                    )
            );
        }
        getDefinition().getAdditionalDisplay().accept(this, textList);
    }
    private String processNumber(int num) {
        var num2 = num / 1000F;
        if (num2 <= 0) {
            return String.format("%d", num);
        }
        if (num2 < 1000.0) {
            return String.format("%.1fK", num2);
        }
        num2 /= 1000F;
        return String.format("%.1fM", num2);
    }

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 170 + 8, 129 + 8);
        var container = new WidgetGroup(4, 4, 170, 129);
        container.addWidget(
                new DraggableScrollableWidgetGroup(4, 4, 162, 121).setBackground(getScreenTexture())
                        .addWidget(new LabelWidget(4, 5, self().getBlockState().getBlock().getDescriptionId())).addWidget(
                                new ComponentPanelWidget(4, 17, this::addDisplayText).setMaxWidthLimit(150)
                                        .clickHandler(this::handleDisplayClick)
                        )
        );
        container.setBackground(GuiTextures.BACKGROUND_INVERSE);
        group.addWidget(container);
        return group;
    }
    @Override
    public ModularUI createUI(Player entityPlayer) {
        return IFancyUIMachine.super.createUI(entityPlayer);
    }

    @Override
    public boolean isRemote() {
        return super.isRemote();
    }

    @Override
    public List<IFancyUIProvider> getSubTabs() {
        return getParts().stream()
                .filter(e -> !(e instanceof HighSpeedPipeBlock))
                .filter(IFancyUIProvider.class::isInstance)
                .map(IFancyUIProvider.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public void attachTooltips(TooltipsPanel tooltipsPanel) {
        for (var part : getParts()) {
            part.attachFancyTooltipsToController(this, tooltipsPanel);
        }
    }
    //////////////////////////////////////
    //******   Multiblock Data   *******//
    //////////////////////////////////////
    private double getVelocityFactor() {
        return Math.pow(0.9, Math.max((height - 4), 0));
    }

    private double getEfficiencyFactor() {
        return Math.pow(0.95, Math.max((height - 4), 0));
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    //////////////////////////////////////
    //******     RECIPE LOGIC    *******//
    //////////////////////////////////////

    @Override
    public boolean alwaysTryModifyRecipe() {
        return true;
    }

    @Override
    protected @Nullable GTRecipe getRealRecipe(GTRecipe recipe) {
        List<NeutronActivatorCondition> conditions = recipe.conditions.stream().filter(NeutronActivatorCondition.class::isInstance)
                .map(NeutronActivatorCondition.class::cast)
                .toList();
        var newRecipe = recipe.copy();
        newRecipe.duration = (int) Math.max((newRecipe.duration * getVelocityFactor()), 1.0);
        if (!conditions.isEmpty()) {
            var condition = conditions.get(0);
            if (eV > (condition.evRange / 10000) * 1000000 || eV < (condition.evRange % 10000) * 1000000) {
                newRecipe.outputs.clear();
                newRecipe.outputs.put(ItemRecipeCapability.CAP, List.of(new Content(Ingredient.of(CTNHItems.RADIOACTIVE_WASTE), 1, 1, 0)));
            }
        }
        return super.getRealRecipe(newRecipe);
    }
}
