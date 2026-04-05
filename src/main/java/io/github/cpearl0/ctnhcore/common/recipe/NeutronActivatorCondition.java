package io.github.cpearl0.ctnhcore.common.recipe;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.NeutronActivatorMachine;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeConditions;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Prefix;

@Prefix("recipe.condition.neutron_activator")
public class NeutronActivatorCondition extends RecipeCondition<NeutronActivatorCondition> {

    public static final Codec<NeutronActivatorCondition> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.optionalFieldOf("isReverse", false).forGetter(RecipeCondition::isReverse),
            Codec.INT.fieldOf("evRange").forGetter(cond -> cond.evRange))
            .apply(instance, NeutronActivatorCondition::new));
    public int evRange = 0;

    public NeutronActivatorCondition(int min, int max) {
        super();
        evRange = max * 10000 + min;
    }

    public NeutronActivatorCondition(int evRange) {
        super();
        this.evRange = evRange;
    }

    public NeutronActivatorCondition(boolean isReverse, int evRange) {
        super(isReverse);
        this.evRange = evRange;
    }

    public NeutronActivatorCondition() {
        this(0, 0);
    }

    public static boolean checkNeutronActivatorCondition(MetaMachine machine, GTRecipe recipe) {
        if (machine instanceof NeutronActivatorMachine) {
            return recipe.conditions.get(0) instanceof NeutronActivatorCondition;
        }
        return false;
    }

    @Override
    public RecipeConditionType<NeutronActivatorCondition> getType() {
        return CTNHRecipeConditions.NEUTRON_ACTIVATOR;
    }

    @CN("最小中子动能:\n%s MeV\n最大中子动能:\n%s MeV")
    @EN("Min Neutron Kinetic Energy:\n%s MeV\nMax Neutron Kinetic Energy:\n%s MeV")
    static Lang tooltip;

    @Override
    public Component getTooltips() {
        var min = evRange % 10000;
        var max = evRange / 10000;
        return tooltip.translate(min, max);
    }

    @Override
    protected boolean testCondition(@NotNull GTRecipe gtRecipe, @NotNull RecipeLogic recipeLogic) {
        return checkNeutronActivatorCondition((MetaMachine) recipeLogic.machine, gtRecipe);
    }

    @Override
    public NeutronActivatorCondition createTemplate() {
        return new NeutronActivatorCondition();
    }
}
