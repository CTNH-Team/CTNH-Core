package io.github.cpearl0.ctnhcore.mixin.creatediesel;

import io.github.cpearl0.ctnhcore.integration.creatediesel.GTBedrockOilBridge;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;

import com.jesz.createdieselgenerators.content.tools.OilScannerItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OilScannerItem.class)
public class OilScannerItemMixin {

    @Inject(
            method = "use",
            at = @At("HEAD"),
            cancellable = true,
            require = 1)
    private void ctnhcore$scanGTBedrockOil(Level level, Player player, InteractionHand hand,
                                           CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack scanner = player.getItemInHand(hand);
        scanner.getOrCreateTag().putInt("Time", 0);
        scanner.getOrCreateTag().putInt("Type", 1);

        if (level instanceof ServerLevel serverLevel) {
            var currentChunk = new ChunkPos(player.blockPosition());
            var currentOilVein = GTBedrockOilBridge.getOilVeinInfo(serverLevel, currentChunk);
            if (currentOilVein != null) {
                scanner.getOrCreateTag().putInt("Type", 3);
                player.displayClientMessage(createCurrentFieldMessage(currentOilVein), true);
            } else {
                var nearestOilVein = GTBedrockOilBridge.findNearestOilVein(serverLevel, player.blockPosition());
                if (nearestOilVein == null) {
                    player.displayClientMessage(Component.literal("附近没有油田").withStyle(ChatFormatting.GRAY), true);
                } else {
                    scanner.getOrCreateTag().putInt("Type", 2);
                    player.displayClientMessage(createNearestFieldMessage(player, nearestOilVein), true);
                }
            }
        }

        cir.setReturnValue(InteractionResultHolder.success(scanner));
    }

    private static Component createCurrentFieldMessage(GTBedrockOilBridge.OilVeinInfo oilVein) {
        return Component.literal("当前区块油田：")
                .withStyle(ChatFormatting.GOLD)
                .append(oilVein.fluidName().copy().withStyle(ChatFormatting.YELLOW))
                .append(Component.literal("，储量：" + oilVein.reservePercentage() + "%（" +
                        oilVein.operationsRemaining() + "/" +
                        com.gregtechceu.gtceu.api.data.worldgen.bedrockfluid.BedrockFluidVeinSavedData.MAXIMUM_VEIN_OPERATIONS +
                        "）").withStyle(ChatFormatting.GREEN));
    }

    private static Component createNearestFieldMessage(Player player, GTBedrockOilBridge.OilVeinInfo oilVein) {
        ChunkPos targetChunk = oilVein.chunkPos();
        double targetX = targetChunk.getMinBlockX() + 7.5;
        double targetZ = targetChunk.getMinBlockZ() + 7.5;
        String direction = getDirection(targetX - player.getX(), targetZ - player.getZ());
        int distance = (int) Math.ceil(Math.sqrt(
                GTBedrockOilBridge.distanceSquaredToChunk(player.blockPosition(), targetChunk)));

        return Component.literal("最近油田：")
                .withStyle(ChatFormatting.GOLD)
                .append(oilVein.fluidName().copy().withStyle(ChatFormatting.YELLOW))
                .append(Component.literal("，方向：" + direction + "，距离：" + distance + " 格")
                        .withStyle(ChatFormatting.AQUA));
    }

    private static String getDirection(double deltaX, double deltaZ) {
        double absoluteX = Math.abs(deltaX);
        double absoluteZ = Math.abs(deltaZ);
        if (absoluteX < absoluteZ * 0.5) return deltaZ > 0 ? "南" : "北";
        if (absoluteZ < absoluteX * 0.5) return deltaX > 0 ? "东" : "西";
        return (deltaZ > 0 ? "南" : "北") + (deltaX > 0 ? "东" : "西");
    }
}
