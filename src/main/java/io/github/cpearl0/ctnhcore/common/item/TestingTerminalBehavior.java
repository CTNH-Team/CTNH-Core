package io.github.cpearl0.ctnhcore.common.item;

import com.gregtechceu.gtceu.api.item.component.IInteractionItem;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.pattern.BlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockState;
import com.gregtechceu.gtceu.api.pattern.error.PatternError;
import com.gregtechceu.gtceu.api.pattern.error.PatternStringError;
import com.gregtechceu.gtceu.api.pattern.error.SinglePredicateError;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerPlayer;

import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.network.packets.BlockHighlightPacket;

import java.util.ArrayList;
import java.util.List;

import static com.lowdragmc.lowdraglib.networking.LDLNetworking.NETWORK;

public class TestingTerminalBehavior implements IInteractionItem {

    private static final String TAG_FLIPPED = "IsFlipped";
    @CN("翻转模式启动")
    @EN("Flip Mode is On")
    static Lang flipmode;
    @CN("普通模式启动")
    @EN("Normal Mode is On")
    static Lang normalmode;

    private boolean isFlipped(ItemStack stack) {
        if (stack.hasTag()) {
            CompoundTag tag = stack.getTag();
            return tag != null && tag.getBoolean(TAG_FLIPPED);
        }
        return false;
    }

    private void setFlipped(ItemStack stack, boolean flipped) {
        stack.getOrCreateTag().putBoolean(TAG_FLIPPED, flipped);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Item item, Level level, Player player, InteractionHand usedHand) {
        var stack = player.getItemInHand(usedHand);
        if (player.isShiftKeyDown()) {
            if (level.isClientSide) {
                return InteractionResultHolder.success(stack);
            }
            boolean newState = !isFlipped(stack);
            setFlipped(stack, newState);
            Component info = newState ? flipmode.translate().withStyle(ChatFormatting.RED) : normalmode.translate();
            player.sendSystemMessage(info);
            return InteractionResultHolder.success(stack);
        }
        return IInteractionItem.super.use(item, level, player, usedHand);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        Player player = context.getPlayer();
        if (player == null) return InteractionResult.PASS;

        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        IMultiController controller = getMachineController(level, blockPos);
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        else {
            if (controller == null) return InteractionResult.PASS;

            // 处理机器是否成型
            if (controller.isFormed()) {
                sendSuccessMessage(player);
            } else {
                handleUnformedController(player, controller, isFlipped(stack));
            }
            return InteractionResult.SUCCESS;
        }
    }

    private IMultiController getMachineController(Level level, BlockPos blockPos) {
        if (MetaMachine.getMachine(level, blockPos) instanceof IMultiController controller) {
            return controller;
        }
        return null;
    }

    private void sendSuccessMessage(Player player) {
        player.sendSystemMessage(Component.translatable("ctnh.test_terminal.success").withStyle(ChatFormatting.GREEN));
    }

    private void handleUnformedController(Player player, IMultiController controller, boolean isFlipped) {
        if (!controller.self().allowFlip()) {
            MultiblockState multiblockState = controller.getMultiblockState();
            if (multiblockState == null) {
                return;
            }
            PatternError error = multiblockState.error;
            if (error != null) {
                showError(player, error, isFlipped, controller.self().getPos(), controller.self().getLevel());
            }
        } else {
            detectPatternErrors(player, controller, isFlipped);
        }
    }

    private void detectPatternErrors(Player player, IMultiController controller, boolean isFlipped) {
        BlockPattern pattern = controller.getPattern();
        List<PatternError> errors = check(controller, pattern, isFlipped);
        for (int i = 0; i < errors.size(); i++) {
            showError(player, errors.get(i), isFlipped, null, null);
        }
    }

    private List<PatternError> check(IMultiController controller, BlockPattern pattern, boolean isFlipped) {
        List<PatternError> errors = new ArrayList<>();
        if (controller == null) {
            errors.add(new PatternStringError("no controller found"));
            return errors;
        }

        BlockPos centerPos = controller.self().getPos();
        Direction frontFacing = controller.self().getFrontFacing();
        Direction[] facings = controller.hasFrontFacing() ? new Direction[] { frontFacing } :
                new Direction[] { Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST };
        Direction upwardsFacing = controller.self().getUpwardsFacing();
        boolean allowsFlip = controller.self().allowFlip();
        MultiblockState worldState = new MultiblockState(controller.self().getLevel(), controller.self().getPos());

        for (Direction direction : facings) {
            if (!isFlipped) {
                checkPatternAndCollectErrors(pattern, errors, worldState, centerPos, direction, upwardsFacing, false);
            }
            if (allowsFlip && isFlipped) {
                // 检查翻转状态
                checkPatternAndCollectErrors(pattern, errors, worldState, centerPos, direction, upwardsFacing, true);
            }
        }

        return errors;
    }

    private void checkPatternAndCollectErrors(BlockPattern pattern, List<PatternError> errors,
                                              MultiblockState worldState,
                                              BlockPos centerPos, Direction direction, Direction upwardsFacing,
                                              boolean flip) {
        pattern.checkPatternAt(worldState, centerPos, direction, upwardsFacing, flip, false);
        if (worldState.hasError()) {
            errors.add(worldState.error);
        }
    }

    private void showError(Player player, PatternError error, boolean flip, BlockPos fallbackPos, Level fallbackWorld) {
        if (error instanceof PatternStringError) {
            player.sendSystemMessage(((PatternStringError) error).getErrorInfo());
            return;
        }
        BlockPos pos = safeGetPos(error, fallbackPos);
        if (player instanceof ServerPlayer serverPlayer && pos != null) {
            NETWORK.sendToPlayer(new BlockHighlightPacket(pos), serverPlayer);
        }

        List<Component> show = generateErrorMessages(error, flip, pos);
        show.forEach(player::sendSystemMessage);
    }

    private List<Component> generateErrorMessages(PatternError error, boolean flip, BlockPos fallbackPos) {
        List<Component> messages = new ArrayList<>();
        BlockPos pos = safeGetPos(error, fallbackPos);
        if (pos == null) {
            pos = BlockPos.ZERO;
        }

        final int MAX_ITEMS_PER_CANDIDATE = 5;
        if (error instanceof SinglePredicateError) {
            List<List<ItemStack>> candidates = safeGetCandidates(error);
            messages.add(Component.translatable("ctnh.test_terminal.lack_error",
                    Component.translatable("ctnh.test_terminal.position", pos.getX(), pos.getY(), pos.getZ())));

            // 遍历所有候选物品列表
            for (List<ItemStack> candidate : candidates) {
                if (!candidate.isEmpty()) {
                    // 只取前5个ItemStack

                    int totalItems = candidate.size();

                    for (int i = 0; i < Math.min(MAX_ITEMS_PER_CANDIDATE, totalItems); i++) {
                        ItemStack itemStack = candidate.get(i);
                        Component itemName = itemStack.getHoverName();
                        messages.add(
                                Component.literal(" - ")
                                        .append(itemName)
                                        .append(Component.translatable("ctnh.test_terminal.error_info",
                                    safeGetErrorInfo(error))));
                    }

                    // 如果超过5个，显示"..."
                    if (totalItems > MAX_ITEMS_PER_CANDIDATE) {
                        messages.add(Component.literal(" - ..."));
                    }
                }
            }
        } else {
            messages.add(Component.translatable("ctnh.test_terminal.wrong_error",
                    Component.translatable("ctnh.test_terminal.position", pos.getX(), pos.getY(), pos.getZ())));
            List<List<ItemStack>> candidates = safeGetCandidates(error);
            // 设置每个候选列表最多显示的项目数

            for (List<ItemStack> candidate : candidates) {
                if (!candidate.isEmpty()) {
                    // 遍历前MAX_ITEMS_PER_CANDIDATE个ItemStack
                    for (int i = 0; i < Math.min(MAX_ITEMS_PER_CANDIDATE, candidate.size()); i++) {
                        ItemStack itemStack = candidate.get(i);
                        messages.add(Component.literal(" - ").append(itemStack.getDisplayName()));
                    }

                    // 如果超过MAX_ITEMS_PER_CANDIDATE个，显示省略号
                    if (candidate.size() > MAX_ITEMS_PER_CANDIDATE) {
                        messages.add(Component.literal(" - ..."));
                    }
                }
            }
        }
        return messages;
    }

    private BlockPos safeGetPos(PatternError error, BlockPos fallbackPos) {
        if (error != null) {
            try {
                BlockPos pos = error.getPos();
                if (pos != null) {
                    return pos;
                }
            } catch (Exception ignored) {}
        }
        return fallbackPos;
    }

    private Level safeGetWorld(PatternError error, Level fallbackWorld) {
        if (error != null) {
            try {
                Level world = error.getWorld();
                if (world != null) {
                    return world;
                }
            } catch (Exception ignored) {}
        }
        return fallbackWorld;
    }

    private Component safeGetErrorInfo(PatternError error) {
        if (error != null) {
            try {
                Component info = error.getErrorInfo();
                if (info != null) {
                    return info;
                }
            } catch (Exception ignored) {}
        }
        return Component.translatable("ctnh.test_terminal.error_info", Component.literal("unknown"));
    }

    private List<List<ItemStack>> safeGetCandidates(PatternError error) {
        if (error != null) {
            try {
                List<List<ItemStack>> candidates = error.getCandidates();
                if (candidates != null) {
                    return candidates;
                }
            } catch (Exception ignored) {}
        }
        return List.of();
    }
}
