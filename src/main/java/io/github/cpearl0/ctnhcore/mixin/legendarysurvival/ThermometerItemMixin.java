package io.github.cpearl0.ctnhcore.mixin.legendarysurvival;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sfiomn.legendarysurvivaloverhaul.common.items.ThermometerItem;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.List;

/** 为 LSO 温度计使用 Core 的名称翻译键，避免跨命名空间的语言覆盖顺序影响显示。 */
@Mixin(ThermometerItem.class)
public abstract class ThermometerItemMixin extends Item {

    @Unique
    @Key("item.ctnhcore.environment_detector")
    @CN("环境检测器")
    @EN("Environment Detector")
    private static Lang ctnhcore$environmentDetectorName;

    protected ThermometerItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public String getDescriptionId() {
        return ctnhcore$environmentDetectorName.key();
    }

    /** 使用说明由 Core 的物品提示事件提供，移除原有体温/饰品 HUD 说明。 */
    @Inject(method = "appendHoverText", at = @At("HEAD"), cancellable = true, require = 1)
    private void ctnhcore$environmentTooltip(ItemStack stack, Level level, List<Component> tooltip,
                                             TooltipFlag flag, CallbackInfo ci) {
        ci.cancel();
    }
}
