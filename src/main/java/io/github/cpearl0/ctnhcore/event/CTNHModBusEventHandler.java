package io.github.cpearl0.ctnhcore.event;

import io.github.cpearl0.ctnhcore.CTNHConfig;
import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.GTCEu;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.MissingMappingsEvent;

import com.magicbee.ctnhmana.CTNHMana;
import com.moguang.ctnhbio.CTNHBio;
import tech.luckyblock.mcmod.ctnhenergy.CTNHEnergy;

import java.util.List;
import java.util.Set;

/**
 * mod 事件总线上的处理器。
 * <p>
 * {@link MissingMappingsEvent} 由注册表加载阶段在 mod bus 上派发，因此不能和 {@link ForgeEventHandler}
 * （FORGE 总线）放在同一个类里——那样 remapIds 永远不会被调用。
 */
@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CTNHModBusEventHandler {

    @SubscribeEvent
    public static void remapIds(MissingMappingsEvent event) {
        if (CTNHConfig.INSTANCE.migration.migrationMode) {
            remapUnsafe(event);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static <T> void remapUnsafe(MissingMappingsEvent event) {
        IForgeRegistry<T> registry = (IForgeRegistry<T>) event.getRegistry();

        ResourceKey<? extends Registry<T>> key = (ResourceKey<? extends Registry<T>>) event.getKey();

        List<MissingMappingsEvent.Mapping<T>> mappings = event.getAllMappings(key);

        Set<String> namespaces = Set.of(
                CTNHCore.MODID,
                CTNHMana.MODID,
                CTNHBio.MODID,
                CTNHEnergy.MODID,
                GTCEu.MOD_ID);

        for (MissingMappingsEvent.Mapping<T> mapping : mappings) {

            ResourceLocation missing = mapping.getKey();

            if (!namespaces.contains(missing.getNamespace()))
                continue;

            String path = missing.getPath();

            for (String ns : namespaces) {

                if (ns.equals(missing.getNamespace()))
                    continue;

                ResourceLocation candidate = ResourceLocation.tryBuild(ns, path);

                if (registry.containsKey(candidate)) {
                    mapping.remap(registry.getValue(candidate));
                    break;
                }
            }
        }
    }
}
