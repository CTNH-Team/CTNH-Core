package io.github.cpearl0.ctnhcore.mixin.tmrv;

import com.mo_guang.ctpp.integration.jei.CTPPJeiPlugin;
import dev.nolij.toomanyrecipeviewers.JEIPlugins;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 临时解决 CTPP JEI Plugin 被跳过导致两种新鼓风机配方无法显示的问题
 * 这个问题应该通过在 CTPP 中保留 JEI/EMI Plugin 二者之一来解决，等待后续重构
 */
@Mixin(value = JEIPlugins.class, remap = false)
public class JEIPluginsMixin {

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void ctnhcore$addCTPPPlugin(CallbackInfo ci) {
        try {
            CTPPJeiPlugin ctppPlugin = new CTPPJeiPlugin();
            JEIPlugins.allPlugins.add(ctppPlugin);
            JEIPlugins.modPlugins.add(ctppPlugin);
        } catch (Throwable t) {
            // 忽略任何可能发生的异常，避免影响 JEI 的正常加载
        }
    }
}
