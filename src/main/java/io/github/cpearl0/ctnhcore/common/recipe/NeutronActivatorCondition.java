package io.github.cpearl0.ctnhcore.common.recipe;

import com.google.gson.JsonObject;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.NeutronActivatorMachine;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeConditions;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.util.GsonHelper;
import org.jetbrains.annotations.NotNull;

public class NeutronActivatorCondition extends RecipeCondition {
    public static final Codec<NeutronActivatorCondition> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.BOOL.optionalFieldOf("isReverse", false).forGetter(RecipeCondition::isReverse),
                    Codec.INT.fieldOf("evRange").forGetter(cond -> cond.evRange)
            ).apply(instance, NeutronActivatorCondition::new)
    );
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
    public RecipeConditionType<?> getType() {
        return CTNHRecipeConditions.NEUTRON_ACTIVATOR;
    }

    @Override
    public Component getTooltips() {
        var min = evRange % 10000;
        var max = evRange / 10000;
        return Component.translatable("ctnh.recipe.condition.neutron_activator_condition_tooltip", min, max);
    }

    @Override
    protected boolean testCondition(@NotNull GTRecipe gtRecipe, @NotNull RecipeLogic recipeLogic) {
        return checkNeutronActivatorCondition((MetaMachine) recipeLogic.machine, gtRecipe);
    }

    @Override
    public RecipeCondition createTemplate() {
        return new NeutronActivatorCondition();
    }

    @Override
    public @NotNull JsonObject serialize() {
        var value = super.serialize();
        value.addProperty("evRange", this.evRange);
        return value;
    }

    @Override
    public RecipeCondition deserialize(@NotNull JsonObject config) {
        super.deserialize(config);
        this.evRange = GsonHelper.getAsInt(config, "evRange", 0);
        return this;
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf) {
        super.toNetwork(buf);
        buf.writeInt(this.evRange);
    }

    @Override
    public RecipeCondition fromNetwork(FriendlyByteBuf buf) {
        super.fromNetwork(buf);
        this.evRange = buf.readInt();
        return this;
    }
}
