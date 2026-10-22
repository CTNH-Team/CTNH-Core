package io.github.cpearl0.ctnhcore.mixin.legendarysurvival;

import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import sfiomn.legendarysurvivaloverhaul.registry.CommandRegistry;

/** 旧体温已停用，不能再注册读取旧环境或修改旧体温的命令。 */
@Mixin(value = CommandRegistry.class, remap = false)
public abstract class TemperatureCommandMixin {

    // LSO 2.3.20.1 依次注册温度、肢体损伤、生命值；首个返回值直接丢弃。
    @Redirect(method = "registerCommandsEvent",
              at = @At(value = "INVOKE",
                       target = "Lcom/mojang/brigadier/CommandDispatcher;register(Lcom/mojang/brigadier/builder/LiteralArgumentBuilder;)Lcom/mojang/brigadier/tree/LiteralCommandNode;",
                       ordinal = 0),
              require = 1)
    private static LiteralCommandNode<CommandSourceStack> ctnh$retireTemperatureCommand(
                                                                                        CommandDispatcher<CommandSourceStack> dispatcher,
                                                                                        LiteralArgumentBuilder<CommandSourceStack> builder) {
        return null;
    }
}
