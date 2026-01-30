package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;

import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class ExtraterrestrialAtmosphereMaterials {

    public static Material AETHER_AIR;
    public static Material LIQUID_AETHER_AIR;
    public static Material ALFHEIM_AIR;
    public static Material TWILIGHTFOREST_AIR;
    public static Material LIQUID_TWILIGHTFOREST_AIR;
    public static Material VENUS_AIR;
    public static Material LIQUID_VENUS_AIR;
    public static Material LIQUID_ALFHEIM_AIR;

    public static void init() {
        AETHER_AIR = REGISTRATE.material(CTNHCore.id("aether_air"))
                .cnlang("天境空气")
                .gas()
                .color(0x8FDDF2)
                .buildAndRegister();

        LIQUID_AETHER_AIR = REGISTRATE.material(CTNHCore.id("liquid_aether_air"))
                .cnlang("液态天境空气")
                .liquid(new FluidBuilder().temperature(30))
                .color(0x7CB9C9)
                .buildAndRegister();

        ALFHEIM_AIR = REGISTRATE.material(CTNHCore.id("alfheim_air"))
                .cnlang("亚尔夫海姆空气")
                .gas()
                .color(0xD798EB)
                .buildAndRegister();

        TWILIGHTFOREST_AIR = REGISTRATE.material(CTNHCore.id("twilightforest_air"))
                .cnlang("暮色森林空气")
                .gas()
                .color(0xADED9C)
                .buildAndRegister();

        LIQUID_TWILIGHTFOREST_AIR = REGISTRATE.material(CTNHCore.id("liquid_twilightforest_air"))
                .cnlang("液态暮色森林空气")
                .liquid(new FluidBuilder().temperature(112))
                .color(0x8DC27F)
                .buildAndRegister();

        VENUS_AIR = REGISTRATE.material(CTNHCore.id("venus_air"))
                .cnlang("金星空气")
                .liquid(new FluidBuilder().temperature(25))
                .color(0xEDE07D)
                .buildAndRegister();

        LIQUID_VENUS_AIR = REGISTRATE.material(CTNHCore.id("liquid_venus_air"))
                .cnlang("液态金星空气")
                .liquid()
                .color(0xDBCF73)
                .buildAndRegister();

        LIQUID_ALFHEIM_AIR = REGISTRATE.material(CTNHCore.id("liquid_alfheim_air"))
                .cnlang("液态亚尔夫海姆空气")
                .liquid(new FluidBuilder().temperature(74))
                .color(0xB981C9)
                .buildAndRegister();
    }
}
