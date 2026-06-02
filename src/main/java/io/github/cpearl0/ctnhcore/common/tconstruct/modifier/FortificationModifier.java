package io.github.cpearl0.ctnhcore.common.tconstruct.modifier;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHConstructModifier;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.ctnhlang.CN;
import com.ctnhlang.Category;
import com.ctnhlang.Domain;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import twilightforest.capabilities.CapabilityList;

@Domain("modifier")
@Category("fortification")
public class FortificationModifier extends Modifier implements OnAttackedModifierHook {

    private static final String SHIELD_COOLDOWN = CTNHCore.MODID + ":fortification_shield_cooldown";
    private static final int INITIAL_COOLDOWN = 600;

    @EN("Fortification")
    @CN("护盾")
    @Key("modifier.ctnhcore.fortification")
    static Lang name;

    @EN("Calls on Twilight Forest shields when hurt.")
    @CN("受伤时调用暮色森林护盾。")
    @Key("modifier.ctnhcore.fortification.description")
    static Lang description;

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.ON_ATTACKED);
    }

    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context,
                           EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        if (context.getEntity() instanceof Player player) {
            applyShield(player);
        }
    }

    public static void tickCooldown(Player player) {
        CompoundTag data = player.getPersistentData();
        if (!data.contains(SHIELD_COOLDOWN)) {
            data.putInt(SHIELD_COOLDOWN, INITIAL_COOLDOWN);
            return;
        }
        int cooldown = data.getInt(SHIELD_COOLDOWN);
        if (cooldown > 0) {
            data.putInt(SHIELD_COOLDOWN, cooldown - 1);
        }
    }

    public static void applyShield(Player player) {
        if (player.level().isClientSide) return;
        int totalLevel = getTotalLevel(player);
        if (totalLevel <= 0) return;

        CompoundTag data = player.getPersistentData();
        if (data.getInt(SHIELD_COOLDOWN) > 0) return;

        // 迁移自 kubejs/server_scripts/src/tconstruct/modiifiers/fortification.js；直接调用暮色森林能力，替代原指令。
        player.getCapability(CapabilityList.SHIELDS)
                .ifPresent(shields -> shields.setShields(totalLevel, true));
        int cooldown = Math.max(400, (int) (100 * (1 - 0.05 * totalLevel)));
        data.putInt(SHIELD_COOLDOWN, cooldown);
    }

    private static int getTotalLevel(Player player) {
        int level = getLevel(player.getMainHandItem()) + getLevel(player.getOffhandItem());
        for (EquipmentSlot slot : new EquipmentSlot[] { EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS,
                EquipmentSlot.FEET }) {
            level += getLevel(player.getItemBySlot(slot));
        }
        return level;
    }

    private static int getLevel(ItemStack stack) {
        if (stack.isEmpty()) return 0;
        return ModifierUtil.getModifierLevel(stack, CTNHConstructModifier.Ids.FORTIFICATION);
    }
}
