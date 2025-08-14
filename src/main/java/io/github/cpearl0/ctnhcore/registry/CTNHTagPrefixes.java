package io.github.cpearl0.ctnhcore.registry;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.AetherBlocks;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconType;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.lowdragmc.lowdraglib.LDLib;
import earth.terrarium.adastra.common.registry.ModBlocks;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.api.data.material.CTNHMaterialIconType;
import io.github.cpearl0.ctnhcore.api.data.material.CTNHPropertyKeys;
import io.github.cpearl0.ctnhcore.data.CTNHMaterialFlags;
import io.github.cpearl0.ctnhcore.data.materials.ChemicalItems;
import io.github.cpearl0.ctnhcore.registry.worldgen.AstralBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import vazkii.botania.common.block.BotaniaBlocks;

import java.util.List;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHMaterialFlags.GENERATE_HYPER_ROTOR;
import static io.github.cpearl0.ctnhcore.registry.nuclear.NuclearMaterials.Plutonium;
import static io.github.cpearl0.ctnhcore.registry.nuclear.NuclearMaterials.Uranium;

public class CTNHTagPrefixes {
    public static List<Material> nuclears = List.of(Uranium238, Uranium235, Plutonium239, Plutonium241);
    public static TagPrefix oreHolystone;
    public static TagPrefix oreMossyHolystone;
    public static TagPrefix oreLivingrock;
    public static TagPrefix oreIcestone;
    public static TagPrefix oreAstralStone = TagPrefix.oreTagPrefix("astral_stone", BlockTags.MINEABLE_WITH_PICKAXE)
            .registerOre(() -> AstralBlocks.ASTRAL_STONE.get().defaultBlockState(),
                    () -> CTNHMaterials.AstralStone,
                    BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).requiresCorrectToolForDrops().strength(2.0F, 2.0F),
                    CTNHCore.id("block/astral_stone"), false, false, true);
    public static TagPrefix oreMoonStone;
    public static TagPrefix oreVenusStone;
    public static TagPrefix oreMarsStone;
    public static TagPrefix oreMercuryStone;
    public static TagPrefix oreGlacioStone;
    public static TagPrefix oreBlackStone;
    public static TagPrefix oreSoulSoil;

    public static TagPrefix nuclear;
    public static TagPrefix fuel;
    public static TagPrefix DepletedFuel;
    public static TagPrefix waste;
    public static TagPrefix hyperRotor;
    public static TagPrefix catalyst;


    public static void init() {
        oreHolystone = TagPrefix.oreTagPrefix("holystone", BlockTags.MINEABLE_WITH_PICKAXE)
                .registerOre(() -> AetherBlocks.HOLYSTONE.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true),
                        () -> CTNHMaterials.Holystone,
                        BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                        ResourceLocation.tryBuild(Aether.MODID, "block/holystone"), true, false, true);
        oreMossyHolystone = TagPrefix.oreTagPrefix("mossy_holystone", BlockTags.MINEABLE_WITH_PICKAXE)
                .registerOre(() -> AetherBlocks.MOSSY_HOLYSTONE.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true),
                        () -> CTNHMaterials.Holystone,
                        BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                        ResourceLocation.tryBuild(Aether.MODID, "block/mossy_holystone"), true, false, true);
        oreLivingrock = TagPrefix.oreTagPrefix("livingrock", BlockTags.MINEABLE_WITH_PICKAXE)
                .registerOre(() -> BotaniaBlocks.livingrock.defaultBlockState(),
                        () -> CTNHMaterials.Livingrock,
                        BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                        ResourceLocation.tryParse("botania:block/polished_livingrock"), false, false, true);
        oreIcestone = TagPrefix.oreTagPrefix("icestone", BlockTags.MINEABLE_WITH_PICKAXE)
                .registerOre(() -> AetherBlocks.ICESTONE.get().defaultBlockState(),
                        () -> CTNHMaterials.icestone,
                        BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).requiresCorrectToolForDrops().strength(2.0F, 2.0F),
                        ResourceLocation.tryParse("aether:block/icestone"), false, false, true);
        if (LDLib.isModLoaded("ad_astra")) {
//            oreMoonStone = TagPrefix.oreTagPrefix("moon_stone", BlockTags.MINEABLE_WITH_PICKAXE)
//                    .registerOre(() -> ModBlocks.MOON_STONE.get().defaultBlockState(),
//                            () -> CTNHMaterials.Moonstone,
//                            BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3, 3),
//                            CTNHCore.id("block/moon_stone"), false, false, true);
//            oreVenusStone = TagPrefix.oreTagPrefix("venus_stone", BlockTags.MINEABLE_WITH_PICKAXE)
//                    .registerOre(() -> ModBlocks.VENUS_STONE.get().defaultBlockState(),
//                            () -> CTNHMaterials.Venusstone,
//                            BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3, 3),
//                            CTNHCore.id("block/venus_stone"), false, false, true);
//            oreMarsStone = TagPrefix.oreTagPrefix("mars_stone", BlockTags.MINEABLE_WITH_PICKAXE)
//                    .registerOre(() -> ModBlocks.MARS_STONE.get().defaultBlockState(),
//                            () -> CTNHMaterials.Marsstone,
//                            BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3, 3),
//                            CTNHCore.id("block/mars_stone"), false, false, true);
//            oreMercuryStone = TagPrefix.oreTagPrefix("mercury_stone", BlockTags.MINEABLE_WITH_PICKAXE)
//                    .registerOre(() -> ModBlocks.MERCURY_STONE.get().defaultBlockState(),
//                            () -> CTNHMaterials.Mercurystone,
//                            BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3, 3),
//                            CTNHCore.id("block/mercury_stone"), false, false, true);
//            oreGlacioStone = TagPrefix.oreTagPrefix("glacio_stone", BlockTags.MINEABLE_WITH_PICKAXE)
//                    .registerOre(() -> ModBlocks.GLACIO_STONE.get().defaultBlockState(),
//                            () -> CTNHMaterials.Glaciostone,
//                            BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3, 3),
//                            CTNHCore.id("block/glacio_stone"), false, false, true);
//            oreMoonStone.addSecondaryMaterial(new MaterialStack(CTNHMaterials.Moonstone, TagPrefix.dust.materialAmount()));
//            oreVenusStone.addSecondaryMaterial(new MaterialStack(CTNHMaterials.Venusstone, TagPrefix.dust.materialAmount()));
//            oreMarsStone.addSecondaryMaterial(new MaterialStack(CTNHMaterials.Marsstone, TagPrefix.dust.materialAmount()));
//            oreMercuryStone.addSecondaryMaterial(new MaterialStack(CTNHMaterials.Mercurystone, TagPrefix.dust.materialAmount()));
//            oreGlacioStone.addSecondaryMaterial(new MaterialStack(CTNHMaterials.Glaciostone, TagPrefix.dust.materialAmount()));
        }
        oreBlackStone = TagPrefix.oreTagPrefix("black_stone", BlockTags.MINEABLE_WITH_PICKAXE)
                .registerOre(Blocks.BLACKSTONE::defaultBlockState,
                        null,
                        BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3, 3),
                        CTNHCore.id("block/black_stone"), false, false, true);
        oreSoulSoil = TagPrefix.oreTagPrefix("soul_soil", BlockTags.MINEABLE_WITH_SHOVEL)
                .registerOre(Blocks.SOUL_SOIL::defaultBlockState,
                        null,
                        BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(2, 2),
                        CTNHCore.id("block/soul_soil"), false, false, true);

        oreHolystone.addSecondaryMaterial(new MaterialStack(CTNHMaterials.Holystone, TagPrefix.dust.materialAmount()));
        oreMossyHolystone.addSecondaryMaterial(new MaterialStack(CTNHMaterials.Holystone, TagPrefix.dust.materialAmount()));
        oreLivingrock.addSecondaryMaterial(new MaterialStack(CTNHMaterials.Livingrock, TagPrefix.dust.materialAmount()));
        oreIcestone.addSecondaryMaterial(new MaterialStack(CTNHMaterials.icestone, TagPrefix.dust.materialAmount()));
        oreAstralStone.addSecondaryMaterial(new MaterialStack(CTNHMaterials.AstralStone, TagPrefix.dust.materialAmount()));

        nuclear = new TagPrefix("nuclear")
                .idPattern("%s")
                .materialAmount(GTValues.M)
                .materialIconType(CTNHMaterialIconType.NUCLEAR)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(material -> material.hasProperty(CTNHPropertyKeys.NUCLEAR) || nuclears.contains(material));
        fuel = new TagPrefix("fuel")
                .idPattern("%s_fuel")
                .materialAmount(GTValues.M)
                .materialIconType(new MaterialIconType("fuel"))
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(material -> material.hasProperty(CTNHPropertyKeys.NUCLEAR) || nuclears.contains(material));
        DepletedFuel = new TagPrefix("depleted_fuel")
                .idPattern("depleted_%s_fuel")
                .materialAmount(GTValues.M)
                .materialIconType(new MaterialIconType("depleted_fuel"))
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(material -> material.hasProperty(CTNHPropertyKeys.NUCLEAR) || nuclears.contains(material));
        waste = new TagPrefix("waste")
                .idPattern("%s_waste")
                .materialAmount(GTValues.M)
                .materialIconType(new MaterialIconType("waste"))
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(material -> material.hasFlag(CTNHMaterialFlags.GENERATE_WASTE) || material.equals(Uranium) || material.equals(Plutonium));
        hyperRotor = new TagPrefix("hyper_rotor")
                .itemTable(() -> CTNHMaterialBlocks.HYPER_ROTOR_BLOCKS)
                .defaultTagPath("hyper_rotors/%s")
                .unformattedTagPath("hyper_rotors")
                .idPattern("%s_hyper_rotor")
                .materialAmount(GTValues.M * 36)
                .maxStackSize(8)
                .materialIconType(new MaterialIconType("hyper_rotor"))
                .unificationEnabled(true)
                .generateItem(false)
                .generateBlock(false)
                .generationCondition(mat -> mat.hasAnyOfFlags(MaterialFlags.GENERATE_ROTOR, GENERATE_HYPER_ROTOR));
        catalyst = new TagPrefix("catalyst")
                .langValue("%s Catalyst")
                .defaultTagPath("catalyst/%s")
                .unformattedTagPath("catalyst")
                .itemTable(() -> ChemicalItems.CATALYST_ITEMS)
                .materialAmount(-1)
                .materialIconType(CTNHMaterialIconType.CATALYST)
                .unificationEnabled(true);
    }
}
