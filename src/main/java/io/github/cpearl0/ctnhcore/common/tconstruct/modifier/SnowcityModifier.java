package io.github.cpearl0.ctnhcore.common.tconstruct.modifier;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import com.ctnhlang.CN;
import com.ctnhlang.Category;
import com.ctnhlang.Domain;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

@Domain("modifier")
@Category("snowcity")
public class SnowcityModifier extends Modifier implements MeleeHitModifierHook {

    @EN("Snowcity")
    @CN("斯诺大习题")
    @Key("modifier.ctnhcore.snowcity")
    static Lang name;

    @EN("Has a chance to drop gold when attacking.")
    @CN("攻击时概率掉落金。")
    @Key("modifier.ctnhcore.snowcity.description")
    static Lang description;

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT);
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context,
                              float damageDealt) {
        Level level = context.getLevel();
        Entity target = context.getTarget();
        if (level.isClientSide || context.getPlayerAttacker() == null || target == null) return;

        // 迁移自 kubejs/server_scripts/src/tconstruct/modiifiers/snowcity.js。
        int chance = modifier.getLevel() * 10;
        double x = target.getX() + level.random.nextInt(3) - 1;
        double y = target.getY() + 1;
        double z = target.getZ() + level.random.nextInt(3) - 1;
        if (level.random.nextInt(100) < chance) {
            ModifierUtil.dropItem(level, x, y, z, new ItemStack(Items.GOLD_INGOT));
        }
        if (level.random.nextInt(1000) < chance) {
            ModifierUtil.dropItem(level, x, y, z, new ItemStack(Items.GOLD_BLOCK));
        }
    }
}
