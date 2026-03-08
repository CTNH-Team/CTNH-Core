package io.github.cpearl0.ctnhcore.mixin.create;

import com.simibubi.create.content.kinetics.chainConveyor.ChainConveyorRidingHandler;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChainConveyorRidingHandler.class)
public class ChainConveyorRidingHandlerMixin {

    @Redirect(
            method = "clientTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/phys/Vec3;length()D")
    )
    private static double createhangingonthechain$lengthMixin(Vec3 vec3) {
        // 等效于 diff.length() > 8. “5“ 这个数值并非最优值，更小值应该也能起到相似效果.
        return vec3.length() - 5;
    }
}
