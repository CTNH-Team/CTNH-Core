package io.github.cpearl0.ctnhcore.api.jade;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.api.recipe.multithread.MultiThreadRecipeLogic;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.IEnergyContainer;
import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.capability.GTCapabilityHelper;
import com.gregtechceu.gtceu.api.machine.SimpleGeneratorMachine;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.machine.steam.SimpleSteamMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.client.util.TooltipHelper;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.SteamParallelMultiblockMachine;
import com.gregtechceu.gtceu.integration.jade.provider.CapabilityBlockProvider;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gregtechceu.gtceu.utils.GTUtil;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import com.mo_guang.ctpp.common.data.recipe.builder.CTPPRecipeHelper;
import org.jetbrains.annotations.Nullable;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class MultithreadRecipeLogicProvider extends CapabilityBlockProvider<RecipeLogic> {

    public MultithreadRecipeLogicProvider() {
        super(CTNHCore.id("recipe_logic_provider"));
    }

    @Nullable
    @Override
    protected RecipeLogic getCapability(Level level, BlockPos pos, @Nullable Direction side) {
        return GTCapabilityHelper.getRecipeLogic(level, pos, side);
    }

    @Override
    protected void write(CompoundTag data, RecipeLogic capability) {
        data.putBoolean("Working", capability.isWorking());
        var recipeInfo = new CompoundTag();
        if (capability instanceof MultiThreadRecipeLogic m) {
            long totalEU = 0;
            long voltage = 0;
            boolean isInput = false;
            for (RecipeLogic thread : m.getAllWorkers()) {
                var recipe = thread.getLastRecipe();
                if (recipe != null) {
                    var EUt = RecipeHelper.getRealEUtWithIO(recipe);
                    totalEU += EUt.getTotalEU();
                    voltage = Math.max(voltage, getVoltage(thread));
                    isInput = EUt.isInput();
                }
            }
            recipeInfo.putLong("EUt", totalEU);
            recipeInfo.putLong("voltage", voltage);
            recipeInfo.putBoolean("isInput", isInput);
        } else {
            var recipe = capability.getLastRecipe();
            if (recipe != null) {
                var EUt = RecipeHelper.getRealEUtWithIO(recipe);
                recipeInfo.putLong("EUt", EUt.getTotalEU());
                recipeInfo.putLong("voltage", getVoltage(capability));
                recipeInfo.putBoolean("isInput", EUt.isInput());

                var stress = CTPPRecipeHelper.getStressWithIO(recipe);
                recipeInfo.putFloat("Stress", stress);
            }
        }

        if (!recipeInfo.isEmpty()) {
            data.put("Recipe", recipeInfo);
        }
    }

    public static long getVoltage(RecipeLogic capability) {
        long voltage = -1;
        if (capability.machine instanceof SimpleTieredMachine machine) {
            voltage = GTValues.V[machine.getTier()];
        } else if (capability.machine instanceof SimpleGeneratorMachine machine) {
            voltage = GTValues.V[machine.getTier()];
        } else if (capability.machine instanceof WorkableElectricMultiblockMachine machine) {
            var handlers = machine.getCapabilitiesFlat(IO.IN, EURecipeCapability.CAP);
            if (handlers.isEmpty()) handlers = machine.getCapabilitiesFlat(IO.OUT, EURecipeCapability.CAP);
            for (IRecipeHandler<?> handler : handlers) {
                if (handler instanceof IEnergyContainer container) {
                    voltage = Math.max(voltage, Math.max(container.getInputVoltage(), container.getOutputVoltage()));
                }
            }
        }
        // default display as LV, this shouldn't happen because a machine is either electric or steam
        if (voltage == -1) voltage = 32;
        return voltage;
    }

    @Override
    protected void addTooltip(CompoundTag capData, ITooltip tooltip, Player player, BlockAccessor block,
                              BlockEntity blockEntity, IPluginConfig config) {
        if (capData.getBoolean("Working")) {
            var recipeInfo = capData.getCompound("Recipe");
            if (!recipeInfo.isEmpty()) {
                var EUt = recipeInfo.getLong("EUt");
                var isInput = recipeInfo.getBoolean("isInput");
                boolean isSteam = false;

                if (blockEntity instanceof MetaMachineBlockEntity mbe) {
                    var machine = mbe.getMetaMachine();
                    if (machine instanceof SimpleSteamMachine ssm) {
                        EUt = (long) (EUt * ssm.getConversionRate());
                        isSteam = true;
                    } else if (machine instanceof SteamParallelMultiblockMachine smb) {
                        EUt = (long) (EUt * smb.getConversionRate());
                        isSteam = true;
                    }
                }

                if (EUt > 0) {
                    MutableComponent text;

                    if (isSteam) {
                        text = Component.translatable("gtceu.jade.fluid_use", FormattingUtil.formatNumbers(EUt))
                                .withStyle(ChatFormatting.GREEN);
                    } else {
                        var voltage = recipeInfo.getLong("voltage");
                        var tier = GTUtil.getTierByVoltage(voltage);
                        float minAmperage = (float) EUt / GTValues.V[tier];

                        text = Component
                                .translatable("gtceu.jade.amperage_use",
                                        FormattingUtil.formatNumber2Places(minAmperage))
                                .withStyle(ChatFormatting.RED)
                                .append(Component.translatable("gtceu.jade.at").withStyle(ChatFormatting.GREEN));
                        if (tier < GTValues.TIER_COUNT) {
                            text = text.append(Component.literal(GTValues.VNF[tier])
                                    .withStyle(style -> style.withColor(GTValues.VC[tier])));
                        } else {
                            int speed = Mth.clamp(tier - GTValues.TIER_COUNT - 1, 0, GTValues.TIER_COUNT);
                            text = text.append(Component.literal("MAX")
                                    .withStyle(style -> style.withColor(TooltipHelper.rainbowColor(speed)))
                                    .append(Component.literal("+")
                                            .withStyle(style -> style.withColor(GTValues.VC[speed]))
                                            .append(FormattingUtil.formatNumbers(speed))));

                        }
                        text.append(Component.translatable("gtceu.universal.padded_parentheses",
                                (Component.translatable("gtceu.recipe.eu.total",
                                        FormattingUtil.formatNumbers(EUt))))
                                .withStyle(ChatFormatting.WHITE));
                    }

                    if (isInput) {
                        tooltip.add(Component.translatable("gtceu.top.energy_consumption").append(" ").append(text));
                    } else {
                        tooltip.add(Component.translatable("gtceu.top.energy_production").append(" ").append(text));
                    }
                }

                var stress = Math.abs(recipeInfo.getFloat("Stress"));
                var isInputStress = recipeInfo.getFloat("Stress") > 0;
                if (stress != 0) {
                    tooltip.add(
                            Component.translatable("ctpp.top.stress_" + (isInputStress ? "consumption" : "production"))
                                    .append(Component.literal(FormattingUtil.formatNumbers(stress) + " su")
                                            .withStyle(ChatFormatting.AQUA)));
                }
            }
        } else {
            if (blockEntity instanceof MetaMachineBlockEntity mbe &&
                    mbe.metaMachine instanceof IRecipeLogicMachine rlm) {
                var logic = rlm.getRecipeLogic();

                if (logic.showFancyTooltip() && logic.isWorkingEnabled()) {
                    Component status = logic.isWaiting() ?
                            Component.translatable("gtceu.recipe_logic.recipe_waiting")
                                    .withStyle(ChatFormatting.YELLOW) :
                            Component.translatable("gtceu.recipe_logic.setup_fail").withStyle(ChatFormatting.RED);
                    tooltip.add(status);
                    logic.getFancyTooltip().forEach(tooltip::add);
                }
            }
        }
    }
}
