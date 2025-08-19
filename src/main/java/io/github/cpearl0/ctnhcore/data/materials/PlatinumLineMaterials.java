package io.github.cpearl0.ctnhcore.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import io.github.cpearl0.ctnhcore.CTNHCore;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Platinum;
import static io.github.cpearl0.ctnhcore.registry.CTNHMaterials.*;
public class PlatinumLineMaterials {
    public static void init() {
        addFluid(GTMaterials.RutheniumTetroxide);
        addFluid(GTMaterials.OsmiumTetroxide);
        addFluid(GTMaterials.CalciumChloride);

        PlatinumSalt = new Material.Builder(CTNHCore.id("platinum_salt"))
                .dust()
                .color(0xEEF2AE)
                .iconSet(SAND)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        RefinedPlatinumSalt = new Material.Builder(CTNHCore.id("refined_platinum_salt"))
                .dust()
                .color(0xFFFEC2)
                .iconSet(SAND)
                .flags(DISABLE_DECOMPOSITION).buildAndRegister();

        PalladiumSalt = new Material.Builder(CTNHCore.id("palladium_salt")).dust().color(0x33302D).iconSet(SAND).flags(DISABLE_DECOMPOSITION)
                        .buildAndRegister();

        RhodiumNitrate = new Material.Builder(CTNHCore.id("rhodium_nitrate")).dust().color(0x8C5A0C).iconSet(SAND).flags(DISABLE_DECOMPOSITION)
                        .buildAndRegister();

        RoughlyRhodiumMetal = new Material.Builder(CTNHCore.id("roughly_rhodium_metal")).dust().color(0x594C1A).iconSet(SAND)
                        .flags(DISABLE_DECOMPOSITION).buildAndRegister().setFormula("??Rh??");

        PalladiumMetal = new Material.Builder(CTNHCore.id("palladium_metal")).dust().color(0x30302E).iconSet(SAND).flags(DISABLE_DECOMPOSITION)
                        .buildAndRegister().setFormula("??Pd??");

        MetalSludge = new Material.Builder(CTNHCore.id("metal_sludge")).dust().color(0x362605).iconSet(SAND).buildAndRegister()
                        .setFormula("NiCu");

        PlatinumSlag = new Material.Builder(CTNHCore.id("platinum_slag")).dust().color(0x343318).iconSet(SAND).flags(DISABLE_DECOMPOSITION)
                        .buildAndRegister().setFormula("??IrOsRhRb??");

        ReprecipitatedRhodium = new Material.Builder(CTNHCore.id("reprecipitated_rhodium")).dust().color(0xD40849).iconSet(SAND)
                        .flags(DISABLE_DECOMPOSITION).buildAndRegister().setFormula("Rh2NH4");

        SodiumNitrate = new Material.Builder(CTNHCore.id("sodium_nitrate")).dust().color(0x4e2a40).iconSet(SAND).flags(DISABLE_DECOMPOSITION)
                        .buildAndRegister().setFormula("NaNO3");

        RhodiumSalt = new Material.Builder(CTNHCore.id("rhodium_salt")).dust().fluid().color(0x61200A).iconSet(SAND)
                .flags(DISABLE_DECOMPOSITION).buildAndRegister();

        RhodiumFilterCake = new Material.Builder(CTNHCore.id("rhodium_filter_cake")).dust().fluid().color(0x87350C).iconSet(ROUGH)
                        .flags(DISABLE_DECOMPOSITION).buildAndRegister().setFormula("?Ru?");

        PlatinumMetal = new Material.Builder(CTNHCore.id("platinum_metal")).dust().color(0xEBEBB2).iconSet(ROUGH).flags(DISABLE_DECOMPOSITION)
                        .buildAndRegister().setFormula("??PtPdIrOsRhRu??");
        Platinum.getProperty(PropertyKey.ORE).setDirectSmeltResult(PlatinumMetal);

        SodiumRutheniate = new Material.Builder(CTNHCore.id("sodium_rutheniate")).dust().color(0x282C8C).iconSet(METALLIC)
                        .flags(DISABLE_DECOMPOSITION).buildAndRegister().setFormula("Na2RbO3");

        IridiumDioxide = new Material.Builder(CTNHCore.id("iridium_dioxide")).dust().color(0xA2BFFF).iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION).buildAndRegister().setFormula("IrO2");

        ConcentratedPlatinum = new Material.Builder(CTNHCore.id("concentrated_platinum")).fluid().color(0xC3C39A).iconSet(ROUGH).buildAndRegister();

        PalladiumRichAmmonia = new Material.Builder(CTNHCore.id("palladium_rich_ammonia")).fluid().color(0x676767).iconSet(ROUGH).buildAndRegister();

        RutheniumTetroxideLQ = new Material.Builder(CTNHCore.id("ruthenium_tetroxide_lq")).fluid().color(0xA8A8A8).iconSet(ROUGH).buildAndRegister();

        SodiumFormate = new Material.Builder(CTNHCore.id("sodium_formate")).fluid().color(0xf1939c).iconSet(ROUGH).buildAndRegister();

        RhodiumSulfateGas = new Material.Builder(CTNHCore.id("rhodium_sulfate_gas")).gas().color(0xBD8743).iconSet(ROUGH).buildAndRegister();

        AcidicIridium = new Material.Builder(CTNHCore.id("acidic_iridium")).gas().color(0x634E3A).iconSet(ROUGH).buildAndRegister()
                        .setFormula("???");

        RutheniumTetroxideHot = new Material.Builder(CTNHCore.id("ruthenium_tetroxide_hot")).gas().color(0x9B9B9B).iconSet(ROUGH).buildAndRegister();
    }
}
