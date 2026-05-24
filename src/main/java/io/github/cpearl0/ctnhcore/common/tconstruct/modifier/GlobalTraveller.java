// “This mod contains code derived from ‘TinkersCalibration’ by Jamesdsj. The original code is licensed under LGPL 2.1.”
package io.github.cpearl0.ctnhcore.common.tconstruct.modifier;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import com.ctnhlang.*;
import com.google.common.base.MoreObjects;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ProcessLootModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.BlockInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

@Domain("modifier")
@Category("global_traveller")
public class GlobalTraveller extends NoLevelsModifier
                             implements TooltipModifierHook, BlockInteractionModifierHook, ProcessLootModifierHook {

    @EN("Global Traveller")
    @CN("环球旅行者")
    @Key("modifier.tconstruct.global_traveller")
    static Lang Null;

    @EN("Travel the world.")
    @CN("全世界折返。")
    @Key("modifier.tconstruct.global_traveller.flavor")
    static Lang flavor;

    @EN("While sneaking, right-click (left-click for ranged weapons) on a container to bind it. Destroyed blocks and drops from killed mobs will be teleported to it.\\nDetailed information can be viewed on the attribute values page.")
    @CN("潜行时对准容器右键（远程武器为左键）以绑定。破坏的方块与杀死怪物的掉落物都会被传送到其中。\n在属性数值页面可以查看详细信息。")
    @Key("modifier.tconstruct.global_traveller.description")
    static Lang description;

    @EN("Bound Container Coordinates")
    @CN("绑定容器坐标")
    static Lang global_pos;

    @EN("Unlink from container at %s %s")
    @CN("取消对位于 %s %s 的容器的绑定")
    static Lang global_unlink;

    @EN("Link to container at %s %s")
    @CN("绑定到位于 %s %s 的容器")
    static Lang global_link;

    @EN("Bound container is valid within this dimension")
    @CN("绑定容器在此维度内且有效")
    static Lang global_valid;

    @EN("Bound container does not exist or is invalid")
    @CN("绑定容器不存在或无效")
    static Lang global_invalid;

    @EN("Bound container is not in this dimension, cannot determine validity")
    @CN("绑定容器不在此维度内，无法判断有效性")
    static Lang global_different_dimension;

    @EN("No bound container")
    @CN("未绑定容器")
    static Lang global_none;

    public static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = ForgeCapabilities.ITEM_HANDLER;
    private final ResourceLocation X = ResourceLocation.tryBuild(CTNHCore.MODID, "global_traveller_x");
    private final ResourceLocation Y = ResourceLocation.tryBuild(CTNHCore.MODID, "global_traveller_y");
    private final ResourceLocation Z = ResourceLocation.tryBuild(CTNHCore.MODID, "global_traveller_z");
    private final ResourceLocation WORLD = ResourceLocation.tryBuild(CTNHCore.MODID, "global_traveller_dimension");

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOLTIP, ModifierHooks.BLOCK_INTERACT, ModifierHooks.PROCESS_LOOT);
    }

    @Override
    public int getPriority() {
        return 10;
    }

    @Override
    public @NotNull InteractionResult afterBlockUse(IToolStackView tool, ModifierEntry modifier, UseOnContext context,
                                                    InteractionSource source) {
        boolean isRanged = tool.hasTag(TinkerTags.Items.RANGED);
        boolean isCorrectClick = (isRanged && source == InteractionSource.LEFT_CLICK) ||
                (!isRanged && source == InteractionSource.RIGHT_CLICK);

        if (isCorrectClick && tool.getCurrentDurability() >= 10 && context.getPlayer() != null &&
                context.getPlayer().isCrouching()) {
            Player player = context.getPlayer();
            if (!player.getCommandSenderWorld().isClientSide) {
                Level world = context.getLevel();
                BlockPos pos = context.getClickedPos();
                BlockEntity block = world.getBlockEntity(pos);
                if (block != null && block.getCapability(ITEM_HANDLER_CAPABILITY).isPresent()) {
                    ModDataNBT persistentData = tool.getPersistentData();
                    if (persistentData.contains(X, 3) && persistentData.contains(Y, 3) &&
                            persistentData.contains(Z, 3) && persistentData.contains(WORLD, 8)) {
                        if (persistentData.getInt(X) == pos.getX() && persistentData.getInt(Y) == pos.getY() &&
                                persistentData.getInt(Z) == pos.getZ() &&
                                persistentData.getString(WORLD).equals(world.dimension().location().getPath())) {
                            persistentData.remove(X);
                            persistentData.remove(Y);
                            persistentData.remove(Z);
                            persistentData.remove(WORLD);
                            player.displayClientMessage(global_unlink.translate(pos.toShortString(),
                                    world.dimension().location().getPath()), true);
                        } else {
                            persistentData.putInt(X, pos.getX());
                            persistentData.putInt(Y, pos.getY());
                            persistentData.putInt(Z, pos.getZ());
                            persistentData.putString(WORLD, world.dimension().location().getPath());
                            player.displayClientMessage(
                                    global_link.translate(pos.toShortString(), world.dimension().location().getPath()),
                                    true);
                        }
                    } else {
                        persistentData.putInt(X, pos.getX());
                        persistentData.putInt(Y, pos.getY());
                        persistentData.putInt(Z, pos.getZ());
                        persistentData.putString(WORLD, world.dimension().location().getPath());
                        player.displayClientMessage(
                                global_link.translate(pos.toShortString(), world.dimension().location().getPath()),
                                true);
                    }
                    player.getCooldowns().addCooldown(tool.getItem(), 40);
                    ToolDamageUtil.damageAnimated(tool, 5, player);
                    return InteractionResult.sidedSuccess(player.getCommandSenderWorld().isClientSide);
                }
            }

        }
        return InteractionResult.PASS;
    }

    @Override
    public void processLoot(IToolStackView tool, ModifierEntry modifier, List<ItemStack> generatedLoot,
                            LootContext context) {
        ModDataNBT persistentData = tool.getPersistentData();
        if (persistentData.contains(X, 3) && persistentData.contains(Y, 3) && persistentData.contains(Z, 3) &&
                persistentData.contains(WORLD, 8)) {
            BlockPos pos = new BlockPos(persistentData.getInt(X), persistentData.getInt(Y), persistentData.getInt(Z));
            ServerLevel level = context.getLevel().getServer().getLevel(ResourceKey.create(Registries.DIMENSION,
                    ResourceLocation.tryParse(persistentData.getString(WORLD))));
            if (level != null) {
                BlockEntity block = level.getBlockEntity(pos);
                if (block != null) {
                    IItemHandler inventory = block.getCapability(ITEM_HANDLER_CAPABILITY).orElse(null);
                    Iterator<ItemStack> iterator = generatedLoot.iterator();
                    List<ItemStack> leftover = new ArrayList<>();
                    while (iterator.hasNext()) {
                        ItemStack stack = iterator.next();
                        leftover.add(ItemHandlerHelper.insertItemStacked(inventory, stack, false));
                    }
                    generatedLoot.clear();
                    generatedLoot.addAll(leftover);
                }
            }
        }
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player,
                           List<Component> tooltip, slimeknights.mantle.client.TooltipKey tooltipKey,
                           TooltipFlag tooltipFlag) {
        ModDataNBT persistentData = tool.getPersistentData();
        if (player != null) {
            if (persistentData.contains(X, 3) && persistentData.contains(Y, 3) && persistentData.contains(Z, 3)) {
                BlockPos pos = new BlockPos(persistentData.getInt(X), persistentData.getInt(Y),
                        persistentData.getInt(Z));
                Level world = player.getCommandSenderWorld();

                tooltip.add(Component
                        .literal(MoreObjects.toStringHelper("").add("X", pos.getX()).add(" Y", pos.getY())
                                .add(" Z", pos.getZ()).toString())
                        .append(" ")
                        .append(persistentData.getString(WORLD))
                        .append(" ")
                        .append(global_pos.translate())
                        .withStyle(style -> style.withColor(TextColor.fromRgb(0xE29AEC))));

                if (world.dimension().location().getPath().equals(persistentData.getString(WORLD))) {
                    BlockEntity block = world.getBlockEntity(pos);
                    if (block != null) {
                        if (block.getCapability(ITEM_HANDLER_CAPABILITY).isPresent()) {
                            tooltip.add(global_valid.translate()
                                    .withStyle(style -> style.withColor(TextColor.fromRgb(0xE29AEC))));
                        } else {
                            tooltip.add(global_invalid.translate()
                                    .withStyle(style -> style.withColor(TextColor.fromRgb(0xE29AEC))));
                        }
                    } else {
                        tooltip.add(global_invalid.translate()
                                .withStyle(style -> style.withColor(TextColor.fromRgb(0xE29AEC))));
                    }
                } else {
                    tooltip.add(global_different_dimension.translate()
                            .withStyle(style -> style.withColor(TextColor.fromRgb(0xE29AEC))));

                }
            } else {
                tooltip.add(global_none.translate().withStyle(style -> style.withColor(TextColor.fromRgb(0xE29AEC))));
            }
        }
    }
}
