package io.github.cpearl0.ctnhcore.data.item;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.ElectricStats;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.tterrag.registrate.util.entry.ItemEntry;

import static com.gregtechceu.gtceu.common.data.GTItems.attach;
import static com.gregtechceu.gtceu.common.data.GTItems.modelPredicate;
import static com.gregtechceu.gtceu.common.data.models.GTModels.overrideModel;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class CrystalItems {
    public static ItemEntry<ComponentItem> PURE_CERTUS_ENERGIUM_CRYSTAL = REGISTRATE.item("pure_certus_energy_crystal", ComponentItem::create)
            .cnlang("高纯能量水晶")
            .lang("Pure Certus Energium Crystal")
            .model(overrideModel(GTCEu.id("battery"), 8))
            .onRegister(modelPredicate(GTCEu.id("battery"), ElectricStats::getStoredPredicate))
            .onRegister(attach(ElectricStats.createRechargeableBattery(50_000_000L, GTValues.HV)))
            .tag(CustomTags.HV_BATTERIES)
            .register();

    public static ItemEntry<ComponentItem> PURE_CERTUS_LAPOTRON_CRYSTAL = REGISTRATE.item("pure_certus_lapotron_crystal", ComponentItem::create)
            .cnlang("高纯兰波顿水晶")
            .lang("Pure Certus Lapotron Crystal")
            .model(overrideModel(GTCEu.id("battery"), 8))
            .onRegister(modelPredicate(GTCEu.id("battery"), ElectricStats::getStoredPredicate))
            .onRegister(attach(ElectricStats.createRechargeableBattery(100_000_000L, GTValues.EV)))
            .tag(CustomTags.EV_BATTERIES)
            .register();

    public static ItemEntry<ComponentItem> RESONANCE_CRYSTAL = REGISTRATE.item("resonance_crystal", ComponentItem::create)
            .cnlang("共振水晶")
            .lang("Resonance Crystal")
            .model(overrideModel(GTCEu.id("battery"), 8))
            .onRegister(modelPredicate(GTCEu.id("battery"), ElectricStats::getStoredPredicate))
            .onRegister(attach(ElectricStats.createRechargeableBattery(500_000_000L, GTValues.IV)))
            .tag(CustomTags.IV_BATTERIES)
            .register();

    public static ItemEntry<ComponentItem> PURE_CERTUS_RESONANCE_CRYSTAL = REGISTRATE.item("pure_certus_resonance_crystal", ComponentItem::create)
            .cnlang("高纯共振水晶")
            .lang("Pure Certus Resonance Crystal")
            .model(overrideModel(GTCEu.id("battery"), 8))
            .onRegister(modelPredicate(GTCEu.id("battery"), ElectricStats::getStoredPredicate))
            .onRegister(attach(ElectricStats.createRechargeableBattery(1_000_000_000L, GTValues.IV)))
            .tag(CustomTags.IV_BATTERIES)
            .register();

    public static ItemEntry<ComponentItem> ECHO_CRYSTAL = REGISTRATE.item("echo_crystal", ComponentItem::create)
            .cnlang("回响水晶")
            .lang("Echo Crystal")
            .model(overrideModel(GTCEu.id("battery"), 8))
            .onRegister(modelPredicate(GTCEu.id("battery"), ElectricStats::getStoredPredicate))
            .onRegister(attach(ElectricStats.createRechargeableBattery(40_000_000_000L, GTValues.LuV)))
            .tag(CustomTags.LuV_BATTERIES)
            .register();

    public static ItemEntry<ComponentItem> PURE_CERTUS_ECHO_CRYSTAL = REGISTRATE.item("pure_certus_echo_crystal", ComponentItem::create)
            .cnlang("高纯回响水晶")
            .lang("Pure Certus Echo Crystal")
            .model(overrideModel(GTCEu.id("battery"), 8))
            .onRegister(modelPredicate(GTCEu.id("battery"), ElectricStats::getStoredPredicate))
            .onRegister(attach(ElectricStats.createRechargeableBattery(160_000_000_000L, GTValues.LuV)))
            .tag(CustomTags.LuV_BATTERIES)
            .register();

    public static ItemEntry<ComponentItem> RESONANCE_CRYSTAL_BLANK = REGISTRATE.item("resonance_crystal_blank", ComponentItem::create)
            .cnlang("共振水晶粗胚")
            .lang("Resonance Crystal Blank")
            .register();

    public static ItemEntry<ComponentItem> ECHO_CRYSTAL_BLANK = REGISTRATE.item("echo_crystal_blank", ComponentItem::create)
            .cnlang("回响水晶粗胚")
            .lang("Echo Crystal Blank")
            .register();

    public static ItemEntry<ComponentItem> ENERGY_CRYSTAL_GRANULE = REGISTRATE.item("energy_crystal_granule", ComponentItem::create)
            .cnlang("能量水晶晶粒")
            .lang("Energy Crystal Granule")
            .register();

    public static ItemEntry<ComponentItem> LAPOTRON_CRYSTAL_GRANULE = REGISTRATE.item("lapotron_crystal_granule", ComponentItem::create)
            .cnlang("兰波顿水晶晶粒")
            .lang("Lapotron Crystal Granule")
            .register();

    public static ItemEntry<ComponentItem> RESONANCE_CRYSTAL_GRANULE = REGISTRATE.item("resonance_crystal_granule", ComponentItem::create)
            .cnlang("共振水晶晶粒")
            .lang("Resonance Crystal Granule")
            .register();

    public static ItemEntry<ComponentItem> ECHO_CRYSTAL_GRANULE = REGISTRATE.item("echo_crystal_granule", ComponentItem::create)
            .cnlang("回响水晶晶粒")
            .lang("Echo Crystal Granule")
            .register();

    public static void init(){

    }
}
