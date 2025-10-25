package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeHandlerList;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.ActionResult;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gregtechceu.gtceu.utils.GTUtil;
import io.github.cpearl0.ctnhcore.common.item.AstronomyCircuitItem;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.part.CircuitBusPartMachine;
import lombok.Getter;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AstronomicalMachine extends WorkableElectricMultiblockMachine {
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
    public boolean beforeWorking(@Nullable GTRecipe recipe) {
        return isValidPhotovoltaicPower();
//        final boolean[] begin = {false};
//        getParts().stream()
//                .filter(part -> part instanceof CircuitBusPartMachine)
//                .findFirst()
//                .ifPresent(bus -> {
//                    var circuitBus = (CircuitBusPartMachine) bus;
//                    if (!circuitBus.getInventory().isEmpty()) {
//                        var circuit = circuitBus.getInventory().getStackInSlot(0);
//                        begin[0] = AstronomyCircuitItem.workInLevel(circuit, getLevel());
//                    }
//                });
//        return begin[0];
    }

    @Override
    public void addDisplayText(@NotNull List<Component> textList) {
        if (isFormed()) {
            if (!isValidPhotovoltaicPower()) {
                textList.add(Component.translatable("ctnh.multiblock.astronomical.info.invalid").withStyle(ChatFormatting.RED));
            }
            else {
                super.addDisplayText(textList);
            }
        }
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        for (IMultiPart part : getParts()) {
            if(part instanceof CircuitBusPartMachine circuitBusPartMachine){
                this.circuitBus = circuitBusPartMachine;
                addHandlerList(RecipeHandlerList.of(IO.IN, circuitBus.getInventory()));
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

    public static class AstronomicalMachineRecipeLogic extends RecipeLogic{

        public AstronomicalMachineRecipeLogic(AstronomicalMachine machine) {
            super(machine);
        }

        @Override
        public AstronomicalMachine getMachine() {
            return (AstronomicalMachine)super.getMachine();
        }

        @Override
        protected ActionResult matchRecipe(GTRecipe recipe) {
            var match = matchRecipeNoOutput(recipe);
            if (!match.isSuccess()) return match;

            return matchTickRecipeNoOutput(recipe);
        }

        protected ActionResult matchRecipeNoOutput(GTRecipe recipe) {
            if (!machine.hasCapabilityProxies()) return ActionResult.FAIL_NO_CAPABILITIES;
            return RecipeHelper.handleRecipe(machine, recipe, IO.IN, recipe.inputs, Collections.emptyMap(), false,
                    true);
        }

        protected ActionResult matchTickRecipeNoOutput(GTRecipe recipe) {
            if (recipe.hasTick()) {
                if (!machine.hasCapabilityProxies()) return ActionResult.FAIL_NO_CAPABILITIES;
                return RecipeHelper.handleRecipe(machine, recipe, IO.IN, recipe.tickInputs, Collections.emptyMap(),
                        false, true);
            }
            return ActionResult.SUCCESS;
        }

        @Override
        protected ActionResult handleRecipeIO(GTRecipe recipe, IO io) {
            var circuitBus = getMachine().getCircuitBus();
            if(io == IO.IN){
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
                outputItem = ItemRecipeCapability.CAP.of(contents.get(0).content).getItems()[0];
            }
            if (!outputItem.isEmpty()) {
                circuitBus.setItem(outputItem);
            }
            circuitBus.setLocked(false);
            return ActionResult.SUCCESS;
        }
    }
}
