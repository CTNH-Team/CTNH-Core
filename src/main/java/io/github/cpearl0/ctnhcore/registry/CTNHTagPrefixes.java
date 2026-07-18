package io.github.cpearl0.ctnhcore.registry;

import io.github.cpearl0.ctnhcore.api.data.material.CTNHMaterialIconType;
import io.github.cpearl0.ctnhcore.data.materials.ChemicalItems;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconType;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;

import com.lowdragmc.lowdraglib.LDLib;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.AetherBlocks;

import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterialFlags.GENERATE_HYPER_ROTOR;

public class CTNHTagPrefixes {

    public static TagPrefix oreHolystone;
    public static TagPrefix oreMossyHolystone;
    public static TagPrefix oreIcestone;
    public static TagPrefix oreSoulSoil;
    public static TagPrefix oreBlackStone;

    public static TagPrefix hyperRotor;
    public static TagPrefix catalyst;

    public static void init() {
//        hyperRotor = REGISTRATE.tagPrefix("hyper_rotor")
//                .cnlang("超级%s转子")
//                .lang("%s Hyper Rotor")
//                .itemTable(() -> CTNHMaterialBlocks.HYPER_ROTOR_BLOCKS)
//                .defaultTagPath("hyper_rotors/%s")
//                .unformattedTagPath("hyper_rotors")
//                .idPattern("%s_hyper_rotor")
//                .materialAmount(GTValues.M * 36)
//                .maxStackSize(8)
//                .materialIconType(new MaterialIconType("hyper_rotor"))
//                .unificationEnabled(true)
//                .generateItem(false)
//                .generateBlock(false)
//                .generationCondition(mat -> mat.hasAnyOfFlags(MaterialFlags.GENERATE_ROTOR, GENERATE_HYPER_ROTOR));

        oreHolystone = REGISTRATE.oreTagPrefix("holystone", BlockTags.MINEABLE_WITH_PICKAXE)
                .cnlang("圣石%s矿石")
                .lang("Holystone %s Ore")
                .registerOre(
                        () -> AetherBlocks.HOLYSTONE.get().defaultBlockState()
                                .setValue(AetherBlockStateProperties.DOUBLE_DROPS, true),
                        () -> CTNHMaterials.Holystone,
                        () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).requiresCorrectToolForDrops()
                                .strength(3.0F, 3.0F),
                        ResourceLocation.tryBuild(Aether.MODID, "block/holystone"), true, false, true);
        oreMossyHolystone = REGISTRATE.oreTagPrefix("mossy_holystone", BlockTags.MINEABLE_WITH_PICKAXE)
                .cnlang("覆苔圣石%s矿石")
                .lang("Mossy Holystone %s Ore")
                .registerOre(
                        () -> AetherBlocks.MOSSY_HOLYSTONE.get().defaultBlockState()
                                .setValue(AetherBlockStateProperties.DOUBLE_DROPS, true),
                        () -> CTNHMaterials.Holystone,
                        () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).requiresCorrectToolForDrops()
                                .strength(3.0F, 3.0F),
                        ResourceLocation.tryBuild(Aether.MODID, "block/mossy_holystone"), true, false, true);

        oreIcestone = REGISTRATE.oreTagPrefix("icestone", BlockTags.MINEABLE_WITH_PICKAXE)
                .cnlang("冰石%s矿石")
                .lang("Icestone %s Ore")
                .registerOre(() -> AetherBlocks.ICESTONE.get().defaultBlockState(),
                        () -> CTNHMaterials.icestone,
                        () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).requiresCorrectToolForDrops()
                                .strength(2.0F, 2.0F),
                        ResourceLocation.tryParse("aether:block/icestone"), false, false, true);
        if (LDLib.isModLoaded("ad_astra")) {

        }
        oreBlackStone = REGISTRATE.oreTagPrefix("black_stone", BlockTags.MINEABLE_WITH_PICKAXE)
                .cnlang("嵌%s黑石")
                .lang("Blackstone %s Ore")
                .registerOre(Blocks.BLACKSTONE::defaultBlockState,
                        null,
                        () -> BlockBehaviour.Properties.copy(Blocks.BLACKSTONE),
                        ResourceLocation.tryBuild("minecraft", "block/blackstone"), false, false, true);
        oreSoulSoil = REGISTRATE.oreTagPrefix("soul_soil", BlockTags.MINEABLE_WITH_SHOVEL)
                .cnlang("含%s灵魂土")
                .lang("Soul Soil %s Ore")
                .registerOre(Blocks.SOUL_SOIL::defaultBlockState,
                        null,
                        () -> BlockBehaviour.Properties.copy(Blocks.SOUL_SOIL),
                        ResourceLocation.tryBuild("minecraft", "block/soul_soil"), false, false, true);

        oreHolystone.addSecondaryMaterial(new MaterialStack(CTNHMaterials.Holystone, TagPrefix.dust.materialAmount()));
        oreMossyHolystone
                .addSecondaryMaterial(new MaterialStack(CTNHMaterials.Holystone, TagPrefix.dust.materialAmount()));

        oreIcestone.addSecondaryMaterial(new MaterialStack(CTNHMaterials.icestone, TagPrefix.dust.materialAmount()));

        catalyst = REGISTRATE.tagPrefix("catalyst")
                .cnlang("%s催化剂")
                .lang("%s Catalyst")
                .langValue("%s Catalyst")
                .defaultTagPath("catalyst/%s")
                .unformattedTagPath("catalyst")
                .itemTable(() -> ChemicalItems.CATALYST_ITEMS)
                .materialAmount(-1)
                .materialIconType(CTNHMaterialIconType.CATALYST)
                .unificationEnabled(true);
    }
}
