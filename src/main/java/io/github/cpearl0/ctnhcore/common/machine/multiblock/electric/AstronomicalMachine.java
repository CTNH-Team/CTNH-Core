package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import io.github.cpearl0.ctnhcore.common.machine.multiblock.part.CircuitBusPartMachine;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.RecipeElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.ActionResult;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerList;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class AstronomicalMachine extends RecipeElectricMultiblockMachine {

    public static final int START_TIME = 23000;
    public static final int END_TIME = 13000;

    @Getter
    private CircuitBusPartMachine circuitBus;

    public AstronomicalMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    private boolean isValidPhotovoltaicPower() {
        var time = Objects.requireNonNull(getLevel()).getDayTime() % 24000;
        return time > END_TIME && time < START_TIME;
    }

    @Override
    public boolean keepSubscribing() {
        return true;
    }

    @Override
    public Component beforeWorking(@NotNull GTRecipe recipe) {
        if (isValidPhotovoltaicPower()) return null;
        return Component.translatable("ctnh.multiblock.astronomical.info.invalid");
        // final boolean[] begin = {false};
        // getParts().stream()
        // .filter(part -> part instanceof CircuitBusPartMachine)
        // .findFirst()
        // .ifPresent(bus -> {
        // var circuitBus = (CircuitBusPartMachine) bus;
        // if (!circuitBus.getInventory().isEmpty()) {
        // var circuit = circuitBus.getInventory().getStackInSlot(0);
        // begin[0] = AstronomyCircuitItem.workInLevel(circuit, getLevel());
        // }
        // });
        // return begin[0];
    }

    @Override
    public void addDisplayText(@NotNull List<Component> textList) {
        if (isFormed()) {
            if (!isValidPhotovoltaicPower()) {
                textList.add(Component.translatable("ctnh.multiblock.astronomical.info.invalid")
                        .withStyle(ChatFormatting.RED));
            } else {
                super.addDisplayText(textList);
            }
        }
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        for (IMultiPart part : getParts()) {
            if (part instanceof CircuitBusPartMachine circuitBusPartMachine) {
                this.circuitBus = circuitBusPartMachine;
                var handlerList = RecipeHandlerList.of(List.of(circuitBus.getInventory()));
                recipeHandlerLists.add(handlerList);
                traitSubscriptions.add(handlerList.subscribe(recipeLogic::updateTickSubscription));
            }
        }
    }

    @Override
    protected RecipeLogic createRecipeLogic(Object... args) {
        return new AstronomicalMachineRecipeLogic(this);
    }

    @Override
    public AstronomicalMachineRecipeLogic getRecipeLogic() {
        return (AstronomicalMachineRecipeLogic) super.getRecipeLogic();
    }

    public static class AstronomicalMachineRecipeLogic extends RecipeLogic {

        public AstronomicalMachineRecipeLogic(AstronomicalMachine machine) {
            super(machine);
        }

        @Override
        public AstronomicalMachine getMachine() {
            return (AstronomicalMachine) super.getMachine();
        }

        @Override
        protected ActionResult matchRecipe(GTRecipe recipe) {
            var match = matchRecipeNoOutput(recipe);
            if (!match.isSuccess()) return match;

            return matchTickRecipeNoOutput(recipe);
        }

        protected ActionResult matchRecipeNoOutput(GTRecipe recipe) {
            return RecipeHelper.handleRecipe(getLastGroup(), recipe, IO.IN, recipe.inputs, true);
        }

        protected ActionResult matchTickRecipeNoOutput(GTRecipe recipe) {
            if (recipe.hasTick()) {
                return RecipeHelper.handleRecipe(getLastGroup(), recipe, IO.IN, recipe.tickInputs, true);
            }
            return ActionResult.SUCCESS;
        }

        @Override
        public ActionResult handleTickRecipe(GTRecipe recipe) {
            if (!recipe.hasTick()) return ActionResult.SUCCESS;

            var match = matchTickRecipeNoOutput(recipe);
            if (!match.isSuccess()) return match;
            return RecipeHelper.handleRecipe(getLastGroup(), recipe, IO.IN, recipe.tickInputs, false);
        }

        @Override
        protected ActionResult handleRecipeIO(GTRecipe recipe, IO io) {
            var circuitBus = getMachine().getCircuitBus();
            if (io == IO.IN) {
                circuitBus.setLocked(true);
                return ActionResult.SUCCESS;
            }

            if (lastRecipe == null) {
                circuitBus.setLocked(false);
                return ActionResult.SUCCESS;
            }
            ItemStack outputItem = ItemStack.EMPTY;
            var contents = lastRecipe.getOutputContents(ItemRecipeCapability.CAP);
            if (!contents.isEmpty()) {
                outputItem = contents.get(0).getItems()[0];
            }
            if (!outputItem.isEmpty()) {
                circuitBus.setItem(outputItem);
            }
            circuitBus.setLocked(false);
            return ActionResult.SUCCESS;
        }
    }
}
