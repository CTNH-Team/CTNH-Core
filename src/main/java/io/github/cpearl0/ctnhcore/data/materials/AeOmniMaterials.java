package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;

import com.wintercogs.ae2omnicells.common.init.OCBlocks;
import com.wintercogs.ae2omnicells.common.init.OCItems;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.METALLIC;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class AeOmniMaterials {

    public static Material ENDER_STEEL;
    public static Material CHARGED_ENDER_STEEL;

    public static void tagPrefixIgnore() {
        TagPrefix.ingot.setIgnored(ENDER_STEEL, () -> OCItems.ENDER_INGOT.get().asItem());
        TagPrefix.block.setIgnoredBlock(ENDER_STEEL, OCBlocks.ENDER_INGOT_BLOCK);
        TagPrefix.ingot.setIgnored(CHARGED_ENDER_STEEL, () -> OCItems.CHARGED_ENDER_INGOT.get().asItem());
    }

    public static void init() {
        ENDER_STEEL = REGISTRATE.material(CTNHCore.id("ender_steel"))
                .cnlang("末影钢")
                .ingot()
                .liquid()
                .color(0x339786) // 主色调：深紫色/靛蓝色
                .secondaryColor(0x8AF1DF) // 次级色调：中等紫色/亮紫色
                .flags(GENERATE_PLATE)
                .iconSet(METALLIC)
                .buildAndRegister();
        CHARGED_ENDER_STEEL = REGISTRATE.material(CTNHCore.id("charged_ender_steel"))
                .cnlang("充能末影钢")
                .ingot()
                .liquid()
                .color(0x339786) // 主色调：深紫色/靛蓝色
                .secondaryColor(0x8AF1DF) // 次级色调：中等紫色/亮紫色
                .flags(GENERATE_PLATE)
                .iconSet(METALLIC)
                .buildAndRegister();
    }
}
