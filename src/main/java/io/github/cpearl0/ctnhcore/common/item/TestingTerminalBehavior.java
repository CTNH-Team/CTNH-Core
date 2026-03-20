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
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

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
        CompoundTag tag = stack.getTag();
        return tag != null && tag.getBoolean(TAG_FLIPPED);
    }

    private void setFlipped(ItemStack stack, boolean flipped) {
        stack.getOrCreateTag().putBoolean(TAG_FLIPPED, flipped);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Item item, Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!player.isShiftKeyDown()) {
            return IInteractionItem.super.use(item, level, player, hand);
        }

        if (level.isClientSide) {
            return InteractionResultHolder.success(stack);
        }

        boolean newState = !isFlipped(stack);
        setFlipped(stack, newState);

        Component info = newState ? flipmode.translate().withStyle(ChatFormatting.RED) : normalmode.translate();

        player.sendSystemMessage(info);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        Level level = context.getLevel();

        // 客户端不执行任何逻辑
        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        Player player = context.getPlayer();
        if (player == null) return InteractionResult.PASS;

        IMultiController controller = getController(level, context.getClickedPos());
        if (controller == null) return InteractionResult.PASS;

        if (controller.isFormed()) {
            player.sendSystemMessage(
                    Component.translatable("ctnh.test_terminal.success")
                            .withStyle(ChatFormatting.GREEN));
            return InteractionResult.SUCCESS;
        }

        boolean flipped = isFlipped(stack);
        List<PatternError> errors = detectErrors(controller, flipped);

        for (PatternError error : errors) {
            showError(player, error);
        }

        return InteractionResult.SUCCESS;
    }

    private IMultiController getController(Level level, BlockPos pos) {
        if (MetaMachine.getMachine(level, pos) instanceof IMultiController controller) {
            return controller;
        }
        return null;
    }

    private List<PatternError> detectErrors(IMultiController controller, boolean flipped) {
        List<PatternError> errors = new ArrayList<>();

        // 不允许翻转时，直接读取已有状态
        if (!controller.self().allowFlip()) {
            MultiblockState state = controller.getMultiblockState();
            if (state.error != null) {
                errors.add(state.error);
            }
            return errors;
        }

        BlockPattern pattern = controller.getPattern();
        BlockPos center = controller.self().getPos();
        Level level = controller.self().getLevel();
        Direction up = controller.self().getUpwardsFacing();

        Direction[] facings = controller.hasFrontFacing() ? new Direction[] { controller.self().getFrontFacing() } :
                new Direction[] { Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST };

        for (Direction facing : facings) {

            MultiblockState state = new MultiblockState(level, center);

            pattern.checkPatternAt(state, center, facing, up, flipped, false);

            if (state.hasError()) {
                errors.add(state.error);
            }
        }

        return errors;
    }

    private void showError(Player player, PatternError error) {
        if (error instanceof PatternStringError stringError) {
            player.sendSystemMessage(stringError.getErrorInfo());
            return;
        }

        BlockPos pos = error.getPos();

        if (player instanceof ServerPlayer serverPlayer && pos != null) {
            NETWORK.sendToPlayer(new BlockHighlightPacket(pos), serverPlayer);
        }

        generateErrorMessages(error).forEach(player::sendSystemMessage);
    }

    private List<Component> generateErrorMessages(PatternError error) {
        List<Component> messages = new ArrayList<>();
        BlockPos pos = error.getPos() != null ? error.getPos() : BlockPos.ZERO;

        final int MAX = 5;

        if (error instanceof SinglePredicateError) {

            messages.add(Component.translatable(
                    "ctnh.test_terminal.lack_error",
                    Component.translatable("ctnh.test_terminal.position",
                            pos.getX(), pos.getY(), pos.getZ())));

        } else {

            messages.add(Component.translatable(
                    "ctnh.test_terminal.wrong_error",
                    Component.translatable("ctnh.test_terminal.position",
                            pos.getX(), pos.getY(), pos.getZ())));
        }

        List<List<ItemStack>> candidates = error.getCandidates();

        if (candidates != null) {
            for (List<ItemStack> list : candidates) {

                for (int i = 0; i < Math.min(MAX, list.size()); i++) {
                    ItemStack candidate = list.get(i);
                    Component displayName = FluidUtil.getFluidContained(candidate)
                            .map(FluidStack::getDisplayName)
                            .orElse(candidate.getHoverName());
                    messages.add(
                            Component.literal(" - ")
                                    .append(displayName));
                }

                if (list.size() > MAX) {
                    messages.add(Component.literal(" - ..."));
                }
            }
        }

        return messages;
    }
}
