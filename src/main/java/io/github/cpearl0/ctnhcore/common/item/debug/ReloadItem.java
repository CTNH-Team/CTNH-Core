package io.github.cpearl0.ctnhcore.common.item.debug;

import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTRecipes;
import com.gregtechceu.gtceu.data.pack.GTDynamicDataPack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class ReloadItem extends Item {
    public ReloadItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        var stack = player.getItemInHand(usedHand);
        GTRegistries.RECIPE_TYPES.forEach(
                r -> r.getLookup().removeAllRecipes()
        );
        GTRecipes.recipeRemoval();
        GTRecipes.recipeAddition(GTDynamicDataPack::addRecipe);
        if(level.isClientSide()){
            System.out.println("客户端配方重载成功");
        }
        else {
            System.out.println("服务端配方重载成功");
            MinecraftServer server = level.getServer();
            if (server != null) {
                Collection<String> selected = server.getPackRepository()
                        .getSelectedPacks()
                        .stream()
                        .map(Pack::getId)
                        .toList();

                server.reloadResources(selected);
            }

        }
        return InteractionResultHolder.success(stack);
    }
}
