package io.github.cpearl0.ctnhcore.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.wintercogs.ae2omnicells.common.init.OCBlocks;
import com.wintercogs.ae2omnicells.common.init.OCItems;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.lounode.ae2cs.common.init.AECSBlocks;
import io.github.lounode.ae2cs.common.init.AECSItems;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class AeCrystalScienceMaterials {

    public static Material CRYSTAL_SAND;
    public static Material PURIFIED_METEOR_CRYSTAL;
    public static Material PURIFIED_ENDER_QUARTZ;

    public static void tagPrefixIgnore() {
        TagPrefix.gem.setIgnored(PURIFIED_ENDER_QUARTZ, () -> AECSItems.PURE_ENDER_QUARTZ.get().asItem());
        TagPrefix.gem.setIgnored(PURIFIED_METEOR_CRYSTAL, () -> AECSItems.PURE_METEOR_CRYSTAL.get().asItem());
        TagPrefix.block.setIgnored(PURIFIED_ENDER_QUARTZ, () -> AECSBlocks.PURE_ENDER_QUARTZ_BLOCK.get().asItem());
        TagPrefix.block.setIgnored(PURIFIED_METEOR_CRYSTAL, () -> AECSBlocks.PURE_METEOR_CRYSTAL_BLOCK.get().asItem());
    }

    public static void init() {
        CRYSTAL_SAND = REGISTRATE.material(CTNHCore.id("crystal_sand"))
                .cnlang("水晶砂")
                .dust()
                .color(0xD1F1FA) // 主色调：淡蓝色/石英色
                .secondaryColor(0x87CEEB) // 次级色调：天蓝色
                .iconSet(FINE)
                .buildAndRegister();
        PURIFIED_ENDER_QUARTZ = REGISTRATE.material(CTNHCore.id("purified_ender_quartz"))
                .cnlang("高纯末影石英")
                .gem()
                .dust() // 粉末形态
                .color(0x258273) // 主色调：淡紫色/薰衣草色，接近图片中的颜色
                .secondaryColor(0x2BCBAF) // 次级色调：中紫色，增加层次感
                .flags(DISABLE_DECOMPOSITION,EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES)
                .buildAndRegister();
        PURIFIED_METEOR_CRYSTAL = REGISTRATE.material(CTNHCore.id("purified_meteor_crystal"))
                .cnlang("高纯陨石水晶")
                .gem()
                .dust() // 粉末形态
                .color(0x414445) // 主色调：淡青色/粉蓝，接近图片中的颜色
                .secondaryColor(0x97FCCB) // 次级色调：钢蓝色，增加层次感
                .flags(DISABLE_DECOMPOSITION,EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES)
                .buildAndRegister();
    }
}
