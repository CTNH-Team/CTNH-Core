package io.github.cpearl0.ctnhcore.common.machine.multiblock;

import com.enderio.base.common.init.EIOFluids;
import com.enderio.machines.common.init.MachineBlocks;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IMachineModifyDrops;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.mojang.authlib.GameProfile;
import io.github.cpearl0.ctnhcore.api.gui.CTNHGuiTextures;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SlaughterHouseMachine extends WorkableElectricMultiblockMachine implements IMachineModifyDrops {
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            SlaughterHouseMachine.class, WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER);
    @Persisted
    public final NotifiableItemStackHandler machineStorage;
    public UUID uuid = UUID.randomUUID();
    public List<String> mobList = new ArrayList<>();
    public double damagePerSecond = 4.0;
    public static int ticksPerSecond = 20;
    public ItemStack hostWeapon = Items.DIRT.getDefaultInstance();

    private FakePlayer fakePlayer;

    public FakePlayer getFakePlayer(ServerLevel level) {
        if (fakePlayer == null) {
            fakePlayer = new FakePlayer(level, new GameProfile(uuid, "slaughter"));
        }
        return fakePlayer;
    }


    public SlaughterHouseMachine(IMachineBlockEntity holder) {
        super(holder);
        this.machineStorage = createMachineStorage((byte) 1);
    }

    @Override
    public void onStructureFormed() {
        if (!getLevel().isClientSide) {
            this.fakePlayer = new FakePlayer((ServerLevel) getLevel(), new GameProfile(uuid, "slaughter"));
        }
        resetWeapon();
        super.onStructureFormed();
    }

    protected NotifiableItemStackHandler createMachineStorage(byte value) {
        return new NotifiableItemStackHandler(
                this, 1, IO.NONE, IO.BOTH, slots -> new CustomItemStackHandler(1) {
            @Override
            public int getSlotLimit(int slot) {
                return value;
            }
            @Override
            public void onContentsChanged(int slot) {
                resetWeapon();
                super.onContentsChanged(slot);
            }
        });
    }
    @Override
    public void onDrops(List<ItemStack> drops) {
        clearInventory(machineStorage.storage);
    }

    @Override
    public @NotNull Widget createUIWidget() {
        var widget = super.createUIWidget();
        if (widget instanceof WidgetGroup group) {
            var size = group.getSize();
            group.addWidget(
                    new SlotWidget(machineStorage.storage, 0, size.width - 30, size.height - 30, true, true)
                            .setBackground(CTNHGuiTextures.SLOT_WEAPON));
        }
        return widget;
    }

    public ItemStack getMachineStorageItem() {
        return machineStorage.getStackInSlot(0);
    }

    public void resetWeapon() {
        if (machineStorage.isEmpty()){
            hostWeapon = Items.DIRT.getDefaultInstance();
        }
        else {
            hostWeapon = getMachineStorageItem();
        }
        if (!getLevel().isClientSide) {
            getFakePlayer((ServerLevel) getLevel()).setItemInHand(InteractionHand.MAIN_HAND, hostWeapon);
        }

        damagePerSecond = calculateFinalValue(1, hostWeapon.getAttributeModifiers(EquipmentSlot.MAINHAND).get(Attributes.ATTACK_DAMAGE).stream())
                * calculateFinalValue(4, hostWeapon.getAttributeModifiers(EquipmentSlot.MAINHAND).get(Attributes.ATTACK_SPEED).stream());
    }
    public static double calculateFinalValue(double baseValue, Stream<AttributeModifier> modifiers) {
        // 按操作类型分组并处理
        var modifiersByOp = modifiers.collect(
                Collectors.groupingBy(AttributeModifier::getOperation)
        );

        // 1. 处理 ADDITION
        double addition = modifiersByOp.getOrDefault(AttributeModifier.Operation.ADDITION, List.of())
                .stream()
                .mapToDouble(AttributeModifier::getAmount)
                .sum();

        // 2. 处理 MULTIPLY_BASE（基于原始值）
        double multiplyBase = modifiersByOp.getOrDefault(AttributeModifier.Operation.MULTIPLY_BASE, List.of())
                .stream()
                .mapToDouble(AttributeModifier::getAmount)
                .sum();

        // 3. 处理 MULTIPLY_TOTAL（基于当前值）
        double multiplyTotal = modifiersByOp.getOrDefault(AttributeModifier.Operation.MULTIPLY_TOTAL, List.of())
                .stream()
                .mapToDouble(AttributeModifier::getAmount)
                .reduce(1.0, (a, b) -> a * (1 + b)); // 连乘 (1 + amount)

        // 最终计算
        return (baseValue + addition + (baseValue * multiplyBase)) * multiplyTotal;
    }
    @Override
    public boolean beforeWorking(@Nullable GTRecipe recipe) {
        if (!super.beforeWorking(recipe))
            return false;
        return !mobList.isEmpty();
    }
    public void resetMobList() {
        mobList.clear();
        MachineUtils.applyContents(this, contents -> {
            if (contents instanceof ItemStack item) {
                if (item.is(MachineBlocks.POWERED_SPAWNER.asItem()) && item.hasTag()) {
                    var mob = item.getTag().getCompound("BlockEntityTag").getCompound("EntityStorage").getCompound("Entity").getString("id");
                    if(!mobList.contains(mob)){
                        mobList.add(mob);
                    }
                }
            }
        }, ItemRecipeCapability.CAP, IO.IN);
    }
    public static ModifierFunction recipeModifier(MetaMachine machine, GTRecipe recipe){
        ServerLevel level = (ServerLevel) machine.getLevel();
        var newrecipe = GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK).applyModifier(machine,recipe);
        if(machine instanceof SlaughterHouseMachine smachine) {
            smachine.resetWeapon();
            smachine.resetMobList();
            if (!smachine.mobList.isEmpty()) {
                // 战利品模式
                double totaltime = 0;
                int totalExperience = 0;
                List<Content> itemList = new ArrayList<>();
                int repeatTimes = smachine.getTier() - 2;
                for (int i = 0; i < 4; i++) {
                    int index = level.getRandom().nextInt(smachine.mobList.size());
                    String mob = smachine.mobList.get(index);
                    var mobentity = EntityType.byString(mob).get().create(machine.getLevel());
                    if (mobentity instanceof LivingEntity livingEntity) {
                        var health = 0;
                        if (livingEntity.getArmorValue() != 0) {
                            var armor = livingEntity.getArmorValue();
                            health += livingEntity.getMaxHealth() / ((double) 20 / (armor + 20));
                        } else {
                            health += livingEntity.getMaxHealth();
                        }
                        var enchantInfluence = EnchantmentHelper.getDamageBonus(smachine.hostWeapon, livingEntity.getMobType());
                        totaltime += health / ((smachine.damagePerSecond + enchantInfluence) * repeatTimes) * ticksPerSecond;
                        totalExperience += livingEntity.getExperienceReward() * 20;

                        if (mob.equals("minecraft:wither")) {
                            itemList.add(new Content(SizedIngredient.create(Items.NETHER_STAR.getDefaultInstance()), 1, 1, 0));
                            continue;
                        }
                        var fakePlayer = smachine.getFakePlayer(level);
                        var loottable = Objects.requireNonNull(level.getServer()).getLootData().getLootTable(ResourceLocation.tryParse(mob.split(":")[0] + ":entities/" + mob.split(":")[1]));
                        var lootparams = new LootParams.Builder((ServerLevel) machine.getLevel())
                                .withParameter(LootContextParams.LAST_DAMAGE_PLAYER, fakePlayer)
                                .withParameter(LootContextParams.TOOL, smachine.hostWeapon)
                                .withParameter(LootContextParams.THIS_ENTITY, mobentity)
                                .withParameter(LootContextParams.DAMAGE_SOURCE, new DamageSources(level.getServer().registryAccess()).mobAttack(fakePlayer))
                                .withParameter(LootContextParams.ORIGIN, machine.getPos().getCenter())
                                .withParameter(LootContextParams.KILLER_ENTITY, fakePlayer)
                                .withParameter(LootContextParams.BLOCK_STATE, machine.getBlockState())
                                .withParameter(LootContextParams.BLOCK_ENTITY, machine.getLevel().getBlockEntity(machine.getPos()))
                                .withParameter(LootContextParams.DIRECT_KILLER_ENTITY, fakePlayer)
                                .withParameter(LootContextParams.EXPLOSION_RADIUS, 0F)
                                .create(loottable.getParamSet());
                        var loots = loottable.getRandomItems(lootparams);
                        loots.forEach(itemStack -> {
                            if (!itemStack.isEmpty()) {
                                itemList.add(new Content(SizedIngredient.create(itemStack), 1, 1, 0));
                            }
                        });
                    }
                }
                var modifier = ContentModifier.multiplier(repeatTimes);
                newrecipe.outputs.put(ItemRecipeCapability.CAP, itemList);
                newrecipe.outputs.put(FluidRecipeCapability.CAP, List.of(new Content(FluidIngredient.of(new FluidStack(EIOFluids.XP_JUICE.get().getSource(), totalExperience)), 1, 1, 0)));
                newrecipe.duration = (int) totaltime * repeatTimes;
                modifier.applyContents(newrecipe.outputs);
            }
        }
        return recipe1 -> newrecipe;
    }
    @Override
    public void addDisplayText(@NotNull List<Component> textList) {
        super.addDisplayText(textList);
        var mobName = mobList.stream().map(mob -> EntityType.byString(mob).get().getDescription().getString()).toList();
        textList.add(textList.size(), Component.translatable("ctnh.multiblock.slaughter_house.info.mobcount", mobList.size(),mobName));
    }
    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
}
