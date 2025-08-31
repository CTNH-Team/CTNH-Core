package io.github.cpearl0.ctnhcore.mixin.gtceu;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.item.TagPrefixItem;
import com.gregtechceu.gtceu.common.data.GTItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

@Mixin(GTItems.class)
public class GTItemMixin {
    @Unique
    private static final List<Material> banMaterial = List.of(Platinum, Aluminium, Naquadah);
    @Inject(method = "lambda$cauldronInteraction$169",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;setItemInHand(Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;)V"),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false,
            cancellable = true)
    private static <T extends Item> void cauldronInteraction(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, ItemStack stack, CallbackInfoReturnable<InteractionResult> cir, Item stackItem, TagPrefixItem prefixItem, int level) {
        if (banMaterial.contains(prefixItem.material)) {
            cir.setReturnValue(InteractionResult.PASS);
        }
    }
}
