package io.github.cpearl0.ctnhcore.common.block;

import io.github.cpearl0.ctnhcore.client.renderer.TurbineRotorRender;
import io.github.cpearl0.ctnhcore.common.blockentity.TurbineRotorBE;
import io.github.cpearl0.ctnhcore.registry.CTNHBlockEntities;

import com.gregtechceu.gtceu.api.block.ActiveBlock;

import com.lowdragmc.lowdraglib.client.renderer.IBlockRendererProvider;
import com.lowdragmc.lowdraglib.client.renderer.IRenderer;
import com.lowdragmc.lowdraglib.utils.ColorUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import com.tterrag.registrate.util.nullness.NonNullFunction;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

import static com.gregtechceu.gtceu.api.block.property.GTBlockStateProperties.ACTIVE;

public class TurbineRotorBlock extends ActiveBlock implements EntityBlock, IBlockRendererProvider {

    @Getter
    float R, G, B, A;// 颜色通道

    public TurbineRotorBlock(Properties pProperties, float r, float g, float b, float a) {
        super(pProperties.noOcclusion());
        registerDefaultState(defaultBlockState().setValue(BlockStateProperties.FACING, Direction.NORTH));
        this.R = r;
        this.G = g;
        this.B = b;
        this.A = a;
    }

    public static NonNullFunction<Properties, TurbineRotorBlock> create(float r, float g, float b, float a) {
        return (p) -> new TurbineRotorBlock(p, r, g, b, a);
    }

    public static NonNullFunction<Properties, TurbineRotorBlock> create(int color) {
        return create(
                ColorUtils.red(color),
                ColorUtils.green(color),
                ColorUtils.blue(color),
                ColorUtils.alpha(color));
    }

    public boolean isActive(BlockState state) {
        return state.getValue(ACTIVE);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TurbineRotorBE(CTNHBlockEntities.TURBINE_ROTOR.get(), pPos, pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(BlockStateProperties.FACING);
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        super.getStateForPlacement(context);
        return this.defaultBlockState().setValue(BlockStateProperties.FACING,
                context.getNearestLookingDirection().getOpposite());
    }

    @Override
    public @Nonnull IRenderer getRenderer(BlockState blockState) {
        return TurbineRotorRender.INSTANCE;
    }
}
