package io.github.cpearl0.ctnhcore.mixin.moonlight;

import net.mehvahdjukaar.moonlight.core.misc.forge.ModLootModifiers;
import net.minecraft.world.level.storage.loot.LootTable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Moonlight's {@code AddTableModifier} rolls its injected table through the Forge-patched
 * {@link LootTable#getRandomItems(LootContext, java.util.function.Consumer)}, which re-enters
 * {@code ForgeHooks.modifyLoot} with the same (sticky) loot table id. Every global loot modifier
 * is therefore evaluated again on the nested roll; entity/tool conditions still match, so
 * {@code add_loot_table} modifiers recurse without bound and crash the server with a
 * StackOverflowError.
 *
 * <p>Redirecting the nested call to the vanilla-raw variant skips global loot modifiers for the
 * injected roll and keeps vanilla's visited-table guard active against accidental table cycles,
 * while leaving all other modifiers untouched.</p>
 */
@Mixin(value = ModLootModifiers.AddTableModifier.class, remap = false)
public abstract class AddTableModifierMixin {

    @Redirect(
            method = "doApply",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/storage/loot/LootTable;getRandomItems(Lnet/minecraft/world/level/storage/loot/LootContext;Ljava/util/function/Consumer;)V",
                    remap = true
            )
    )
    private void ctnhcore$rollInjectedTableWithoutModifiers(LootTable instance, LootContext context,
                                                            java.util.function.Consumer<net.minecraft.world.item.ItemStack> consumer) {
        instance.getRandomItemsRaw(context, consumer);
    }
}
