package io.github.cpearl0.ctnhcore.client.model;

import net.minecraft.client.model.geom.builders.LayerDefinition;

import lombok.Getter;

// 导入BlockBench的模型
@Getter
public class TemplateModel extends ModelBase {

    public TemplateModel(ModelDefinition definition) {
        super(definition);
        // 其他的部件定义
    }

    public static LayerDefinition createBodyLayer() {
        throw new UnsupportedOperationException("模型类里一定要有模型");
    }
}
