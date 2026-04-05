package io.github.cpearl0.ctnhcore.common.item.debug;

import net.minecraft.world.item.Item;

public class ReloadItem extends Item {

    public ReloadItem(Properties properties) {
        super(properties);
    }

    // spotless:off
//    @Override
//    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
//        var stack = player.getItemInHand(usedHand);
//        GTRegistries.RECIPE_TYPES.forEach(
//                r -> r.getLookup().removeAllRecipes());
//        GTRecipes.recipeRemoval();
//        GTRecipes.recipeAddition(GTDynamicDataPack::addRecipe);
//        if (level.isClientSide()) {
//            System.out.println("客户端配方重载成功");
//        } else {
//            System.out.println("服务端配方重载成功");
//            MinecraftServer server = level.getServer();
//            if (server != null) {
//                Collection<String> selected = server.getPackRepository()
//                        .getSelectedPacks()
//                        .stream()
//                        .map(Pack::getId)
//                        .toList();
//
//                server.reloadResources(selected);
//            }
//
//        }
//        return InteractionResultHolder.success(stack);
//    }
    // spotless:on
}
