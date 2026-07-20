package io.github.cpearl0.ctnhcore.common.item;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import com.ctnhlang.CN;
import com.ctnhlang.EN;

import com.gregtechceu.gtceu.api.item.ComponentItem;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IDataItem extends ComponentItem {

    @CN("当前噪声值：%s")
    @EN("Current noise: %s")
    public static Lang dataNoise;


    @CN("当前公式: a%s+b%s+c%s+d")
    @EN("Current formula: a%s+b%s+c%s+d")
    public static Lang itemDataTip1;


    @CN("获取的倍率: %s")
    @EN("Obtained multiplier: %s")
    public static Lang itemDataTip2;



    public IDataItem(Properties properties) {
        super(properties
                .rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents,
                                TooltipFlag isAdvanced) {
        CompoundTag nbt = stack.getOrCreateTag();
        if (nbt.contains("formula")) {
            var formula = nbt.getLongArray("formula");
            tooltipComponents.add(itemDataTip1.translate( String.format("%d", formula[0]),
                    String.format("%d", formula[1]), String.format("%d", formula[2])));
        }
        if (nbt.contains("muti")) {
            tooltipComponents
                    .add(itemDataTip2.translate( String.format("%.2f", nbt.getDouble("muti"))));
        }
        if (nbt.contains("noise")) {
            tooltipComponents
                    .add(dataNoise.translate( String.format("%.2f", nbt.getDouble("noise"))));
        }
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced); // 调用父类方法以处理原版提示信息
    }
}
