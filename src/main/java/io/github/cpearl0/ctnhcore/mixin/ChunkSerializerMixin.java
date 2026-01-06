package io.github.cpearl0.ctnhcore.mixin;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.*;
import io.github.cpearl0.ctnhcore.CTNHConfig;
import net.minecraft.core.IdMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.PalettedContainer;
import net.minecraft.world.level.chunk.storage.ChunkSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ChunkSerializer.class)
public abstract class ChunkSerializerMixin{

    @Unique
    private static <T> DataResult<Pair<BlockState, T>> CTNHCore$decode(final DynamicOps<T> ops, final T input) {
        if (ops == NbtOps.INSTANCE) {
            var tag = (CompoundTag) input;
            var name = ResourceLocation.tryParse(tag.getString("Name"));
            if (name != null && !ForgeRegistries.BLOCKS.containsKey(name)) {
                var new_name = name;
                if (name.getNamespace().equals("kubejs") || name.getNamespace().equals("gtceu") || name.getNamespace().equals("gtnn"))
                    new_name = ResourceLocation.tryBuild("ctnhcore", name.getPath());
                tag.putString("Name", new_name.toString());
            }
        }
        return BlockState.CODEC.decode(ops, input);
    }

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/chunk/PalettedContainer;codecRW(Lnet/minecraft/core/IdMap;Lcom/mojang/serialization/Codec;Lnet/minecraft/world/level/chunk/PalettedContainer$Strategy;Ljava/lang/Object;)Lcom/mojang/serialization/Codec;"))
    private static <T> Codec<PalettedContainer<BlockState>> CTNHCore$myCodec(IdMap<BlockState> pRegistry, Codec<BlockState> pCodec, PalettedContainer.Strategy pStrategy, T pValue) {
        Codec<BlockState> codec = pCodec;
        if(CTNHConfig.INSTANCE.migration.migrationMode)
        {
            Encoder<BlockState> encoder = BlockState.CODEC::encode;
            Decoder<BlockState> decoder = ChunkSerializerMixin::CTNHCore$decode;
            codec = Codec.of(encoder, decoder);
       }
        return PalettedContainer.codecRW(pRegistry, codec, pStrategy, Blocks.AIR.defaultBlockState());
    }
}
