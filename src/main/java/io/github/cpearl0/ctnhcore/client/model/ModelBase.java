package io.github.cpearl0.ctnhcore.client.model;

import net.minecraft.client.model.geom.ModelPart;

import lombok.Getter;

@Getter
public class ModelBase {

    public final ModelPart root;
    @Getter
    public final ModelDefinition definition;

    // 在render中构造
    public ModelBase(ModelDefinition definition) {
        this.definition = definition;
        this.root = definition.createBodyLayer.get().bakeRoot();
    }
}
