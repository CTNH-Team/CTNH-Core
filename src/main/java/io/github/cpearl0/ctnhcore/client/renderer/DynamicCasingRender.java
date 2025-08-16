package io.github.cpearl0.ctnhcore.client.renderer;

import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.client.model.machine.IControllerModelRenderer;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderType;
import com.gregtechceu.gtceu.client.util.ModelUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.cpearl0.ctnhcore.api.Pattern.CTNHBlockMaps;
import io.github.cpearl0.ctnhcore.api.machine.feature.IDynamicCasing;
import lombok.Getter;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicCasingRender extends DynamicRender<IDynamicCasing, DynamicCasingRender> implements IControllerModelRenderer {
    public static final Codec<DynamicCasingRender> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BlockState.CODEC.fieldOf("base_casing").forGetter(DynamicCasingRender::getBaseCasing),
                    Codec.STRING.fieldOf("model_type").forGetter(it -> it.getModelType().name())
            ).apply(instance, DynamicCasingRender::new)
    );
    public static final DynamicRenderType<IDynamicCasing, DynamicCasingRender> TYPE = new DynamicRenderType<>(CODEC);
    @Getter
    private final BlockState baseCasing;
    @Getter
    private final ModelType modelType;
    private Map<BlockState, BakedModel> bakedModelsMap = new HashMap<>();
    public DynamicCasingRender(BlockState base, ModelType type) {
        super();
        this.baseCasing = base;
        this.modelType = type;
    }
    public DynamicCasingRender(BlockState base, String type) {
        super();
        this.baseCasing = base;
        this.modelType = ModelType.getByName(type);
    }
    public void initModel() {
        ModelUtils.registerBakeEventListener(false, event -> {
                bakedModelsMap.put(baseCasing, event.getModels().get(BlockModelShaper.stateToModelLocation(baseCasing)));
                modelType.getModels().forEach(blockState -> {
                    bakedModelsMap.put(blockState, event.getModels().get(BlockModelShaper.stateToModelLocation(blockState)));
                });
            }
        );
    }
    @Override
    public DynamicRenderType<IDynamicCasing, DynamicCasingRender> getType() {
        return TYPE;
    }

    @Override
    public void render(IDynamicCasing feature, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {

    }

    @Override
    public @NotNull List<BakedQuad> getRenderQuads(@Nullable IDynamicCasing machine, @Nullable BlockAndTintGetter level, @Nullable BlockPos pos, @Nullable BlockState blockState, @Nullable Direction side, RandomSource rand, @NotNull ModelData modelData, @Nullable RenderType renderType) {
        List<BakedQuad> quads = new ArrayList<>();
        if (machine != null) {
            BlockState casing = machine.getAppearance();
            var model = bakedModelsMap.get(casing);
            if (model == null) {
                return super.getRenderQuads(machine, level, pos, blockState, side, rand, modelData, renderType);
            }
            var data = model.getModelData(level, pos, casing, modelData);
            quads.addAll(model.getQuads(casing, side, rand, data, renderType));
            return quads;
        }
        return quads;
    }

    @Override
    public boolean shouldRender(IDynamicCasing machine, Vec3 cameraPos) {
        return machine instanceof MultiblockControllerMachine multiblockControllerMachine && multiblockControllerMachine.isFormed();
    }

    @Override
    public void renderPartModel(List<BakedQuad> quads, IMultiController iMultiController, IMultiPart iMultiPart, Direction frontFacing, @Nullable Direction side, RandomSource rand, @NotNull ModelData modelData, @Nullable RenderType renderType) {
        var machine = iMultiController.self();
        var partPos = iMultiPart.self().getPos();
        BlockState casing = baseCasing;
        if (machine instanceof IDynamicCasing dynamicCasing) {
            casing = dynamicCasing.getAppearance();
        }
        var model = bakedModelsMap.get(casing);
        if (model == null) return;

        var data = model.getModelData(machine.getLevel(), partPos, casing, modelData);
        quads.addAll(model.getQuads(casing, side, rand, data, renderType));
    }
    public enum ModelType {
        ChemicalPlant(CTNHBlockMaps.CasingBlock.values().stream().map(block -> block.get().defaultBlockState()).toList());

        private static final Map<String, ModelType> enumMap = new HashMap<>();
        static {
            for (ModelType type : values()) {
                enumMap.put(type.name(), type);
            }
        }
        private List<BlockState> models;
        ModelType(List<BlockState> list) {
            this.models = list;
        }
        public static ModelType getByName(String name) {
            return enumMap.get(name);
        }
        public List<BlockState> getModels() {
            return models;
        }
    }

}
