package io.github.cpearl0.ctnhcore.registry.jade;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;

public class CTNHJadePlugin {

    @Key("config.jade.plugin_ctnhcore.recipe_logic_provider")
    @CN("配方耗电信息")
    @EN("Recipe Logic Info")
    public static Lang configJadePluginCtnhcoreRecipeLogicProvider;


    @Key("config.jade.plugin_ctnhcore.recipe_output_provider")
    @CN("配方输出信息")
    @EN("Recipe Output Info")
    public static Lang configJadePluginCtnhcoreRecipeOutputProvider;


    @Key("config.jade.plugin_ctnhcore.thread_status_provider")
    @CN("线程信息")
    @EN("Thread Info")
    public static Lang configJadePluginCtnhcoreThreadStatusProvider;



    public static void init() {
        // JadePriorityManager.registerBlockComponent(
        // new MultithreadRecipeLogicProvider(),
        // Block.class,
        // 1350,
        // "multithread_recipe_logic_data");
        // JadePriorityManager.registerBlockData(
        // new MultithreadRecipeLogicProvider(),
        // BlockEntity.class,
        // 1350,
        // "multithread_recipe_logic_component");
        //
        // JadePriorityManager.registerBlockComponent(
        // new ThreadStatusProvider(),
        // Block.class,
        // 1250,
        // "thread_status_data");
        // JadePriorityManager.registerBlockData(
        // new ThreadStatusProvider(),
        // BlockEntity.class,
        // 1250,
        // "thread_status_component");
        //
        // JadePriorityManager.registerBlockComponent(
        // new MultithreadRecipeOutputProvider(),
        // Block.class,
        // 1650,
        // "multithread_recipe_output_data");
        // JadePriorityManager.registerBlockData(
        // new MultithreadRecipeOutputProvider(),
        // BlockEntity.class,
        // 1650,
        // "multithread_recipe_output_component");
    }
}
