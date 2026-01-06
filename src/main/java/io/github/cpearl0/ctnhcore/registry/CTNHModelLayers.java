package io.github.cpearl0.ctnhcore.registry;

import io.github.cpearl0.ctnhcore.client.model.ModelDefinition;
import io.github.cpearl0.ctnhcore.client.model.TurbineRotorModel;

//不同于CTNHModel，这个是用来注册BlockBench的实体模型的
public class CTNHModelLayers {
    public static void init(){}
    public static ModelDefinition TURBINE_ROTOR_MODEL = new ModelDefinition("turbine_rotor", TurbineRotorModel::createBodyLayer);

}
