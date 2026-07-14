package io.github.cpearl0.ctnhcore.integration.emi;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import com.enderio.base.common.init.EIOItems;
import com.enderio.machines.common.blockentity.capacitorbank.CapacitorTier;
import com.enderio.machines.common.blockentity.solar.SolarPanelTier;
import com.enderio.machines.common.init.MachineBlocks;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiInitRegistry;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiInfoRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.gregtechceu.gtceu.common.data.machines.GTMultiMachines.PRIMITIVE_BLAST_FURNACE;

@EmiEntrypoint
public class CTNHCoreEmiPlugin implements EmiPlugin {

    private static final List<String> EXTRA_ITEMS = List.of(
            "create:chromatic_compound",
            "create:shadow_steel",
            "create:shadow_steel_casing",
            "create:refined_radiance",
            "create:refined_radiance_casing");

    public static List<Supplier<? extends Item>> disabled = new ArrayList<>();

    @Override
    public void register(EmiRegistry registry) {
        for (String itemId : EXTRA_ITEMS) {
            Item item = resolveItem(itemId);
            if (item != Items.AIR) {
                registry.addEmiStack(EmiStack.of(item));
            }
        }

        addInformation(registry, "gtceu:hv_emitter", "ctnh.blood_magic_gregtech_hv");
        addInformation(registry, "ctnhcore:stone_process_catalyst", "ctnh.stone_process_catalyst");
        addInformation(registry, "ctnhcore:metallurgical_catalyst", "ctnh.metallurgical_catalyst");
        addInformation(registry, "ctnhcore:bauxite_process_catalyst", "ctnh.bauxite_process_catalyst");
        addInformation(registry, "ctnhcore:platinum_metal_catalyst_shard1",
                "ctnh.platinum_metal_catalyst_shard1");
        addInformation(registry, "ctnhcore:platinum_metal_catalyst_shard2",
                "ctnh.platinum_metal_catalyst_shard2");
        addInformation(registry, "gtceu:psionic_medulla_gem", "ctnh.psionic_medulla");
        addInformation(registry, "bloodmagic:doubt_bucket", "ctnh.doubt");
    }

    @Override
    public void initialize(EmiInitRegistry registry) {
        EIODisable();

        for (var item : disabled) {
            registry.disableStack(EmiStack.of(item.get()));
        }
    }

    public static void EIODisable() {
        disabled.addAll(List.of(
                MachineBlocks.FLUID_TANK::asItem,
                MachineBlocks.PRESSURIZED_FLUID_TANK::asItem,
                MachineBlocks.STIRLING_GENERATOR::asItem,
                MachineBlocks.SAG_MILL::asItem,
                MachineBlocks.ALLOY_SMELTER::asItem,
                MachineBlocks.PRIMITIVE_ALLOY_SMELTER::asItem,
                MachineBlocks.STIRLING_GENERATOR::asItem,
                MachineBlocks.SOUL_ENGINE::asItem,
                EIOItems.BASIC_CAPACITOR::asItem,
                EIOItems.DOUBLE_LAYER_CAPACITOR::asItem,
                EIOItems.OCTADIC_CAPACITOR::asItem,
                PRIMITIVE_BLAST_FURNACE::getItem));
        for (SolarPanelTier tier : SolarPanelTier.values()) {
            disabled.add(MachineBlocks.SOLAR_PANELS.get(tier)::asItem);
        }
        for (CapacitorTier tier : CapacitorTier.values()) {
            disabled.add(MachineBlocks.CAPACITOR_BANKS.get(tier)::asItem);
        }
    }

    private static void addInformation(EmiRegistry registry, String itemId, String translationKey) {
        Item item = resolveItem(itemId);
        if (item == Items.AIR) {
            return;
        }

        registry.addRecipe(new EmiInfoRecipe(
                List.<EmiIngredient>of(EmiStack.of(item)),
                List.of(Component.translatable(translationKey)),
                CTNHCore.id("info/" + itemId.replace(':', '/'))));
    }

    private static Item resolveItem(String itemId) {
        ResourceLocation id = ResourceLocation.tryParse(itemId);
        return id == null ? Items.AIR : BuiltInRegistries.ITEM.get(id);
    }
}
