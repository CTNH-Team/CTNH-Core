package io.github.cpearl0.ctnhcore.registry;

import com.google.common.collect.Lists;
import com.gregtechceu.gtceu.api.block.MetaMachineBlock;
import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.api.CTNHMultiblockBuilder;
import io.github.cpearl0.ctnhcore.client.model.ModelDefinition;
import lombok.Getter;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import tech.vixhentx.mcmod.ctnhlib.registrate.CNRegistrate;
import tech.vixhentx.mcmod.ctnhlib.registrate.builders.CTNHMachineBuilder;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
//REGISTRATE之力!!!REGISTRATE之力!!!REGISTRATE之力!!!REGISTRATE之力!!!REGISTRATE之力!!!REGISTRATE之力!!!REGISTRATE之力!!!
public final class CTNHRegistrate extends CNRegistrate {

    CTNHRegistrate() {
        super(CTNHCore.MODID);
    }
    public static CTNHRegistrate create() {
        return new CTNHRegistrate();
    }
    @Override
    public CTNHMultiblockBuilder multiblock(String name, Function<IMachineBlockEntity, ? extends MultiblockControllerMachine> metaMachine) {
        return CTNHMultiblockBuilder.createMulti(this, name, metaMachine, MetaMachineBlock::new, MetaMachineItem::new, MetaMachineBlockEntity::new);
    }
    public CTNHMachineBuilder<MachineDefinition> machine(String name, Function<IMachineBlockEntity, MetaMachine> metaMachine) {
        return new CTNHMachineBuilder<>(this, name, MachineDefinition::new, metaMachine,
                MetaMachineBlock::new, MetaMachineItem::new, MetaMachineBlockEntity::new);
    }

    @Deprecated
    @Getter
    private final List<ModelDefinition> models= Lists.newArrayList();
    @Deprecated
    public ModelDefinition registerModel(String name, Supplier<LayerDefinition> createBodyLayer) {
        ModelDefinition model = new ModelDefinition(name, createBodyLayer);
        models.add(model);
        return model;
    }

}
