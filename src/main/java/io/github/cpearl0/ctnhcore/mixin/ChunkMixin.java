package io.github.cpearl0.ctnhcore.mixin;

import net.minecraft.world.level.chunk.LevelChunk;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(LevelChunk.class)
public class ChunkMixin {
    // @Final
    // @Shadow
    // Level level;
    //
    // @Inject(method = "setBlockState",
    // at = @At(value = "INVOKE",
    // opcode = Opcodes.GETFIELD,
    // target = "Lnet/minecraft/world/level/block/state/BlockState;hasBlockEntity()Z",
    // ordinal = 2))
    // private void CTNH$onChunkChangedWhenPlace(BlockPos pos, BlockState state, boolean isMoving,
    // CallbackInfoReturnable<BlockState> cir) {
    // if (level.isClientSide || !level.captureBlockSnapshots)
    // return;
    // MinecraftServer server = level.getServer();
    // if (server != null) {
    // if (level instanceof ServerLevel serverLevel) {
    // for (var structure : MultiblockWorldSavedData.getOrCreate(serverLevel)
    // .getControllerInChunk(((LevelChunk) (Object) this).getPos())) {
    // if (structure.isPosInCache(pos)) {
    // server.executeBlocking(() -> structure.onBlockStateChanged(pos, state));
    // }
    // }
    // }
    // }
    // }
}
