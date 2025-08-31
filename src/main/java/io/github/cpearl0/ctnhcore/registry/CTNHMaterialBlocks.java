package io.github.cpearl0.ctnhcore.registry;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import io.github.cpearl0.ctnhcore.common.block.MaterialTurbineRotorBlock;
import io.github.cpearl0.ctnhcore.common.block.TurbineRotorBlock;
import io.github.cpearl0.ctnhcore.common.item.MaterialTurbineRotorItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Blocks;

import static io.github.cpearl0.ctnhcore.registry.CTNHTagPrefixes.*;

public class CTNHMaterialBlocks {

    public static Table<TagPrefix, Material, BlockEntry<? extends TurbineRotorBlock>> HYPER_ROTOR_BLOCKS;

    public static void generateHyperRotorBlocks(){
        ImmutableTable.Builder<TagPrefix, Material, BlockEntry<? extends TurbineRotorBlock>> builder =
                ImmutableTable.builder();
        for (MaterialRegistry registry : GTCEuAPI.materialManager.getRegistries()) {
            for (Material material : registry.getAllMaterials()) {
                if (hyperRotor.generationCondition().test(material)) {
                    registerHyperRotorBlock(hyperRotor, material, registry.getRegistrate(),builder);
                }
            }
        }
        HYPER_ROTOR_BLOCKS = builder.build();
    }

    private static void registerHyperRotorBlock(TagPrefix tagPrefix, Material material, GTRegistrate registrate,ImmutableTable.Builder<TagPrefix, Material, BlockEntry<? extends TurbineRotorBlock>> builder) {
        builder.put(tagPrefix, material, registrate
                .block(tagPrefix.idPattern().formatted(material.getName()), MaterialTurbineRotorBlock.create(material))
                .initialProperties(() -> Blocks.OBSIDIAN)
//                .lang(tagPrefix.langValue().formatted(toEnglishName(material.getName())))
                .tag(TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.tryBuild("forge", "mineable/wrench")), BlockTags.MINEABLE_WITH_PICKAXE)
                .tag(tagPrefix.getBlockTags(material))
                .blockstate((ctx, prov) ->
                        prov.simpleBlock(ctx.getEntry(), prov.models().cubeAll(material.getName(), ResourceLocation.tryParse("minecraft:block/iron_block"))))
                .item(MaterialTurbineRotorItem::new)
                .tag(tagPrefix.getItemTags(material))
                .build()
                .register()
        );
    }
}
