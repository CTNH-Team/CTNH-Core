package io.github.cpearl0.ctnhcore.registry;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.block.MaterialBlock;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.common.block.CableBlock;
import com.tterrag.registrate.util.entry.BlockEntry;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.block.TurbineRotorBlock;
import io.github.cpearl0.ctnhcore.common.item.TurbineRotorItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Blocks;

import static io.github.cpearl0.ctnhcore.registry.CTNHTagPrefixes.*;

public class CTNHMaterialBlocks {

    public static Table<TagPrefix, Material, BlockEntry<? extends TurbineRotorBlock>> HYPER_ROTOR_BLOCKS;
    //Temporary
    private static ImmutableTable.Builder<TagPrefix, Material, BlockEntry<? extends TurbineRotorBlock>> HYPER_ROTOR_BLOCKS_TEMP =
            ImmutableTable.builder();

    public static void generateHyperRotorBlocks(){
        for (MaterialRegistry registry : GTCEuAPI.materialManager.getRegistries()) {
            GTRegistrate registrate = registry.getRegistrate();
            for (Material material : registry.getAllMaterials()) {
                var condition = hyperRotor.generationCondition();
                if (condition == null || condition.test(material)) {
                    registerHyperRotorBlock(hyperRotor, material, registrate);
                }
            }
        }
        HYPER_ROTOR_BLOCKS = HYPER_ROTOR_BLOCKS_TEMP.build();
        HYPER_ROTOR_BLOCKS_TEMP = null;
    }

    private static void registerHyperRotorBlock(TagPrefix tagPrefix, Material material, GTRegistrate registrate) {
        HYPER_ROTOR_BLOCKS_TEMP.put(tagPrefix, material, registrate
                .block(material.getName(), TurbineRotorBlock.create(material.getMaterialARGB()))
                .initialProperties(() -> Blocks.OBSIDIAN)
                .tag(TagKey.create(BuiltInRegistries.BLOCK.key(), new ResourceLocation("forge", "mineable/wrench")), BlockTags.MINEABLE_WITH_PICKAXE)
                .blockstate((ctx, prov) ->
                        prov.simpleBlock(ctx.getEntry(), prov.models().cubeAll(material.getName(), new ResourceLocation("minecraft:block/iron_block"))))
                .item(TurbineRotorItem::new)
                .build()
                .register()
        );
    }
}
