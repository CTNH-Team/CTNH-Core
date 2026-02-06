package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.DULL;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.METALLIC;
import static com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty.GasTier.LOW;
import static com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty.GasTier.MID;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.NetherStar;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class EnderIOMaterials {

    public static Material EnergeticAlloy;
    public static Material ConductiveAlloy;
    public static Material PulsatingAlloy;
    public static Material VibrantAlloy;
    public static Material Soularium;
    public static Material CopperAlloy;
    public static Material RedstoneAlloy;
    public static Material DarkSteel;
    public static Material EndSteel;
    public static Material ChorusiteAlloy;
    public static Material MelodicAlloy;
    public static Material StellarAlloy;

    public static void init() {
        EnergeticAlloy = REGISTRATE.material(CTNHCore.id("energetic_alloy"))
                .cnlang("充能合金")
                .ingot()
                .liquid()
                .components(Redstone, 1, Glowstone, 1, Gold, 2)
                .blastTemp(1200, LOW, 120, 200)
                .cableProperties(GTValues.V[GTValues.MV], 1, 0, true)
                .color(0xf79b33)
                .iconSet(METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_FINE_WIRE)
                .buildAndRegister();
        ConductiveAlloy = REGISTRATE.material(CTNHCore.id("conductive_alloy"))
                .cnlang("导电合金")
                .ingot()
                .liquid()
                .components(Iron, 1, Redstone, 2)
                .cableProperties(GTValues.V[GTValues.LV], 1, 0, true)
                .color(0xf5c4bd)
                .iconSet(METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_FINE_WIRE)
                .buildAndRegister();
        VibrantAlloy = REGISTRATE.material(CTNHCore.id("vibrant_alloy"))
                .cnlang("振动合金")
                .ingot()
                .liquid()
                .components(EnergeticAlloy, 1, EnderPearl, 1)
                .blastTemp(1600, LOW, 480, 200)
                .cableProperties(GTValues.V[GTValues.HV], 1, 0, true)
                .color(0xbbe87c)
                .iconSet(METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_FINE_WIRE)
                .buildAndRegister();
        PulsatingAlloy = REGISTRATE.material(CTNHCore.id("pulsating_alloy"))
                .cnlang("脉冲合金")
                .ingot()
                .liquid()
                .components(Iron, 1, EnderPearl, 1)
                .color(0x9AEBAD).iconSet(METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR)
                .buildAndRegister();

        Soularium = REGISTRATE.material(CTNHCore.id("soularium"))
                .cnlang("魂金")
                .ingot()
                .liquid()
                .color(0x6C5A34).iconSet(DULL)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR)
                .buildAndRegister();
        CopperAlloy = REGISTRATE.material(CTNHCore.id("copper_alloy"))
                .cnlang("铜合金")
                .ingot()
                .liquid()
                .color(0xBB8418).iconSet(METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR)
                .buildAndRegister();
        RedstoneAlloy = REGISTRATE.material(CTNHCore.id("redstone_alloy"))
                .cnlang("红石合金")
                .ingot()
                .liquid()
                .color(0xed3b50)
                .iconSet(METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR)
                .buildAndRegister();
        DarkSteel = REGISTRATE.material(CTNHCore.id("dark_steel"))
                .cnlang("玄钢")
                .ingot()
                .liquid()
                .components(Steel, 1, Obsidian, 1)
                .blastTemp(1400, LOW, 480, 200)
                .color(0x858585).iconSet(METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR)
                .buildAndRegister();

        EndSteel = REGISTRATE.material(CTNHCore.id("end_steel"))
                .cnlang("末地钢")
                .ingot()
                .liquid()
                .components(DarkSteel, 1, Endstone, 1, Obsidian, 1)
                .blastTemp(2500, MID, 1920, 200)
                .cableProperties(GTValues.V[GTValues.EV], 1, 0, true)
                .color(0xe8e08b)
                .iconSet(METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_FINE_WIRE)
                .buildAndRegister();
        ChorusiteAlloy = REGISTRATE.material(CTNHCore.id("chorusite_alloy"))
                .cnlang("振动合金")
                .ingot()
                .liquid()
                .color(0xc576ed)
                .blastTemp(3000, LOW, 480, 260)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR)
                .buildAndRegister();
        MelodicAlloy = REGISTRATE.material(CTNHCore.id("melodic_alloy"))
                .cnlang("旋律合金")
                .ingot()
                .liquid()
                .components(ChorusiteAlloy, 1, EndSteel, 1)
                .blastTemp(3200, MID, 7680, 280)
                .cableProperties(GTValues.V[GTValues.IV], 1, 0, true)
                .color(0xd271db)
                .iconSet(METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_FINE_WIRE)
                .buildAndRegister();
        StellarAlloy = REGISTRATE.material(CTNHCore.id("stellar_alloy"))
                .cnlang("恒星合金")
                .ingot()
                .liquid()
                .components(NetherStar, 1, MelodicAlloy, 1, CreateMaterials.RefinedRadiance, 1)
                .blastTemp(5000, MID, 30720, 260)
                .cableProperties(GTValues.V[GTValues.LuV], 1, 0, true)
                .color(0xe5f7f7)
                .iconSet(METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_FINE_WIRE)
                .buildAndRegister();
    }
}
