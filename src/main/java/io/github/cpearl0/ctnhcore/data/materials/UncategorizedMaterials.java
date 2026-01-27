package io.github.cpearl0.ctnhcore.data.materials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import io.github.cpearl0.ctnhcore.CTNHCore;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.data.materials.BedrockMaterials.*;
import static io.github.cpearl0.ctnhcore.data.materials.NewExplosivesProductionMaterials.AETERNIUM;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.*;

public class UncategorizedMaterials {

    public static Material CEMENT;
    public static Material FEFARITE;
    public static Material FEFARITE_INTERMEDIATE_BODY;
    public static Material NETHER_ESSENCE_CRYSTAL_FLUID;
    public static Material KEVLAR_STONE_GROWTH_LIQUID;
    public static Material SHOCK_RESISTANT_ALLOY;
    public static Material CRACKING_SILICA_ROCK_BASED_FUEL;
    public static Material CRACKING_ENRICHED_SILICA_ROCK_BASED_FUEL;
    public static Material CRACKING_SUPER_ENERGY_SILICA_ROCK_BASED_FUEL;
    public static Material CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_I;
    public static Material CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_II;
    public static Material CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_III;
    public static Material HIGH_ENERGY_FUEL;
    public static Material ACID_GARBAGE;
    public static Material EGLIN_STEEL_INGOT;
    public static Material SODIUM22;
    public static Material HIDDENECHOFUNGUS;
    public static Material HIDDENECHOFUNGUSSOLUTION;
    public static Material SODA_DUST;
    public static Material CLGS_BUFFER;
    public static Material CDS_BUFFER;
    public static Material MAGNETRON_SPUTTERING_TARGET_MATERIAL;
    public static Material SUNNARIUM_EXTRACT;
    public static Material RADIATION_SUNNARIUM_EXTRACT;
    public static Material CADMIUM_SULFIDE;
    public static Material HEAT_RESISTANT_FERROCHROME_ALLOY_DS;
    public static Material HEAT_RESISTANT_FERROCHROME_ALLOY_020;
    public static Material OPTICAL_HEAT_RESISTANT_FERROCHROME_ALLOY_080;
    public static Material NAQUADAH_HEAT_RESISTANT_FERROCHROME_ALLOY_792;
    public static Material RADIATION_SIGHT_ALLOY_X;
    public static Material QUANTUM_ALLOY;
    public static Material RADIATION_SIGHT_ALLOY_INF;
    public static Material NAMI_C;
    public static Material LIVING_METAL_PRE;
    public static Material ALKALINE_COMPLEX_ORE_SLURRY;
    public static Material ALKALINE_SLAG;
    public static Material STRONTIUM_CHLORIDE;
    public static Material ORACLE;
    public static Material STEEL_PRECURSOR;
    public static Material HIGH_TEMP_WROUGHT_PRECURSOR;
    public static Material PERRHENIC_ACID;

    public static void init() {
        CEMENT = REGISTRATE.material(CTNHCore.id("cement"))
                .cnlang("水泥")
                .liquid()
                .color(0xD2B48C)
                .buildAndRegister();

        FEFARITE = REGISTRATE.material(CTNHCore.id("fefarite"))
                .cnlang("凯弗石")
                .formula("KeF")
                .ore()
                .gem()
                .dust()
                .color(0x046ED7)
                .iconSet(LIGNITE)
                .buildAndRegister();

        FEFARITE_INTERMEDIATE_BODY = REGISTRATE.material(CTNHCore.id("fefarite_intermediate_body"))
                .cnlang("氟化凯金络合物")
                .liquid()
                .color(0x28358A)
                .buildAndRegister();

        NETHER_ESSENCE_CRYSTAL_FLUID = REGISTRATE.material(CTNHCore.id("nether_essence_crystal_fluid"))
                .cnlang("下界精华培养液")
                .liquid()
                .color(0x046ED7)
                .buildAndRegister();

        KEVLAR_STONE_GROWTH_LIQUID = REGISTRATE.material(CTNHCore.id("kevlar_stone_growth_liquid"))
                .cnlang("凯弗石生长液")
                .liquid()
                .color(0xAAAAAA)
                .buildAndRegister();

        SHOCK_RESISTANT_ALLOY = REGISTRATE.material(CTNHCore.id("shock_resistant_alloy"))
                .cnlang("耐摔合金")
                .formula("🚁HeLiCoPtEr🚁")
                .dust()
                .ingot()
                .color(0xFFD700)
                .liquid()
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW, GENERATE_FOIL, GENERATE_FRAME, GENERATE_RING)
                .buildAndRegister();

        CRACKING_SILICA_ROCK_BASED_FUEL = REGISTRATE.material(CTNHCore.id("cracking_silica_rock_based_fuel"))
                .cnlang("氡裂化硅岩基燃料")
                .liquid()
                .color(0x5ABCC)
                .buildAndRegister();

        CRACKING_ENRICHED_SILICA_ROCK_BASED_FUEL = REGISTRATE.material(CTNHCore.id("cracking_enriched_silica_rock_based_fuel"))
                .cnlang("氙裂化富集硅岩基燃料")
                .liquid()
                .color(0x6BCCA)
                .buildAndRegister();

        CRACKING_SUPER_ENERGY_SILICA_ROCK_BASED_FUEL = REGISTRATE.material(CTNHCore.id("cracking_super_energy_silica_rock_based_fuel"))
                .cnlang("裂化超能硅岩基燃料")
                .liquid()
                .color(0x7BACB)
                .buildAndRegister();

        CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_I = REGISTRATE.material(CTNHCore.id("charged_silica_rock_based_fluid_fuel_mk_i"))
                .cnlang("充能硅岩基流体燃料Mk-I")
                .liquid()
                .color(0x55555)
                .buildAndRegister();

        CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_II = REGISTRATE.material(CTNHCore.id("charged_silica_rock_based_fluid_fuel_mk_ii"))
                .cnlang("充能硅岩基流体燃料Mk-II")
                .liquid()
                .color(0x55555)
                .buildAndRegister();

        CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_III = REGISTRATE.material(CTNHCore.id("charged_silica_rock_based_fluid_fuel_mk_iii"))
                .cnlang("充能硅岩基流体燃料Mk-III")
                .liquid()
                .color(0x55555)
                .buildAndRegister();

        HIGH_ENERGY_FUEL = REGISTRATE.material(CTNHCore.id("high_energy_fuel"))
                .cnlang("高能辅料剂")
                .liquid()
                .color(0x351AA)
                .buildAndRegister();

        ACID_GARBAGE = REGISTRATE.material(CTNHCore.id("acid_garbage"))
                .cnlang("酸性废液")
                .liquid()
                .color(0x44444)
                .buildAndRegister();

        EGLIN_STEEL_INGOT = REGISTRATE.material(CTNHCore.id("eglin_steel_ingot"))
                .cnlang("埃格林钢粗胚")
                .dust()
                .ingot()
                .color(0x23312)
                .components(Invar, 5, Iron, 4, Kanthal, 1)
                .buildAndRegister();

        SODIUM22 = REGISTRATE.material(CTNHCore.id("sodium22"))
                .cnlang("钠-22")
                .dust()
                .color(0x4682B4)
                .radioactiveHazard(3)
                .buildAndRegister();

        HIDDENECHOFUNGUS = REGISTRATE.material(CTNHCore.id("hiddenechofungus"))
                .cnlang("幽匿回响菌")
                .dust()
                .color(0x306080)
                .buildAndRegister();

        HIDDENECHOFUNGUSSOLUTION = REGISTRATE.material(CTNHCore.id("hiddenechofungussolution"))
                .cnlang("幽匿回响菌液")
                .liquid()
                .color(0x306080)
                .buildAndRegister();

        SODA_DUST = REGISTRATE.material(CTNHCore.id("soda_dust"))
                .cnlang("苏打玻璃")
                .dust()
                .ingot()
                .flags(GENERATE_PLATE)
                .color(0xFFFFFF)
                .buildAndRegister();

        CLGS_BUFFER = REGISTRATE.material(CTNHCore.id("clgs_buffer"))
                .cnlang("CLGS缓冲")
                .formula("Cu(In, Ga)Se2")
                .dust()
                .flags(GENERATE_PLATE)
                .color(0xFFFFFF)
                .buildAndRegister();

        CDS_BUFFER = REGISTRATE.material(CTNHCore.id("cds_buffer"))
                .cnlang("CdS缓冲")
                .dust()
                .flags(GENERATE_PLATE)
                .color(0xFFD700)
                .buildAndRegister();

        MAGNETRON_SPUTTERING_TARGET_MATERIAL = REGISTRATE.material(CTNHCore.id("magnetron_sputtering_target_material"))
                .cnlang("载磁控溅射物料")
                .dust()
                .flags(GENERATE_PLATE)
                .color(0xFFFFBA)
                .buildAndRegister();

        SUNNARIUM_EXTRACT = REGISTRATE.material(CTNHCore.id("sunnarium_extract"))
                .cnlang("阳光提取物")
                .dust()
                .liquid()
                .color(0xFFFF01)
                .buildAndRegister();

        RADIATION_SUNNARIUM_EXTRACT = REGISTRATE.material(CTNHCore.id("radiation_sunnarium_extract"))
                .cnlang("辐射阳光提取物")
                .liquid()
                .plasma()
                .color(0xFFFF01)
                .buildAndRegister();

        CADMIUM_SULFIDE = REGISTRATE.material(CTNHCore.id("cadmium_sulfide"))
                .cnlang("硫化镉")
                .ingot()
                .blastTemp(3600)
                .dust()
                .iconSet(METALLIC)
                .components(Cadmium, 1, Sulfur, 1)
                .color(0xFFFACD)
                .flags(GENERATE_DENSE, GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW, GENERATE_FOIL, GENERATE_FRAME, GENERATE_RING, GENERATE_LONG_ROD)
                .buildAndRegister();

        HEAT_RESISTANT_FERROCHROME_ALLOY_DS = REGISTRATE.material(CTNHCore.id("heat_resistant_ferrochrome_alloy_ds"))
                .cnlang("耐热铬铁合金-ds")
                .ingot()
                .dust()
                .liquid()
                .color(0x8A9B7A)
                .components(Iron, 23, Cobalt, 9, Chromium, 9, Nickel, 9)
                .blastTemp(3600, BlastProperty.GasTier.LOW, GTValues.VA[GTValues.HV], 500)
                .flags(GENERATE_DENSE, GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW, GENERATE_FOIL, GENERATE_FRAME, GENERATE_RING, GENERATE_LONG_ROD)
                .buildAndRegister();

        HEAT_RESISTANT_FERROCHROME_ALLOY_020 = REGISTRATE.material(CTNHCore.id("heat_resistant_ferrochrome_alloy_020"))
                .cnlang("耐热铬铁合金-020")
                .ingot()
                .dust()
                .liquid()
                .components(Iron, 10, Copper, 1, Chromium, 5, Nickel, 9)
                .blastTemp(5400, BlastProperty.GasTier.MID, GTValues.VA[GTValues.EV], 800)
                .color(0x4E7D6D)
                .flags(GENERATE_DENSE, GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW, GENERATE_FOIL, GENERATE_FRAME, GENERATE_RING, GENERATE_LONG_ROD)
                .buildAndRegister();

        OPTICAL_HEAT_RESISTANT_FERROCHROME_ALLOY_080 = REGISTRATE.material(CTNHCore.id("optical_heat_resistant_ferrochrome_alloy_080"))
                .cnlang("光性耐热铬铁合金-080")
                .ingot()
                .dust()
                .liquid()
                .components(Iron, 10, Chromium, 5, Nickel, 9, Titanium, 4, SUNNARIUM, 9)
                .blastTemp(7200, BlastProperty.GasTier.MID, GTValues.VA[GTValues.LuV], 1000)
                .color(0x4E7D9D)
                .flags(GENERATE_DENSE, GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW, GENERATE_FOIL, GENERATE_FRAME, GENERATE_RING, GENERATE_LONG_ROD)
                .buildAndRegister();

        NAQUADAH_HEAT_RESISTANT_FERROCHROME_ALLOY_792 = REGISTRATE.material(CTNHCore.id("naquadah_heat_resistant_ferrochrome_alloy_792"))
                .cnlang("硅岩基耐热铬铁合金-792")
                .ingot()
                .dust()
                .liquid()
                .components(Iron, 70, Chromium, 20, Nickel, 5, Manganese, 5, Naquadah, 10)
                .blastTemp(7200, BlastProperty.GasTier.MID, GTValues.VA[GTValues.LuV], 3000)
                .color(0x2A2A1A)
                .flags(GENERATE_DENSE, GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW, GENERATE_FOIL, GENERATE_FRAME, GENERATE_RING, GENERATE_LONG_ROD)
                .buildAndRegister();

        RADIATION_SIGHT_ALLOY_X = REGISTRATE.material(CTNHCore.id("radiation_sight_alloy_x"))
                .cnlang("辐射视距合金-X")
                .ingot()
                .dust()
                .liquid()
                .components(Iridium, 9, Dysprosium, 4, STABALLOY, 6, UraniumRhodiumDinaquadide, 8, Neptunium, 7, Mendelevium, 2)
                .blastTemp(9000, BlastProperty.GasTier.HIGHER, GTValues.VA[GTValues.ZPM], 1000)
                .color(0x2D3A2A)
                .flags(GENERATE_DENSE, GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW, GENERATE_FOIL, GENERATE_FRAME, GENERATE_RING, GENERATE_LONG_ROD)
                .buildAndRegister();

        QUANTUM_ALLOY = REGISTRATE.material(CTNHCore.id("quantum_alloy"))
                .cnlang("量子合金")
                .ingot()
                .dust()
                .liquid()
                .components(Ultimet, 3, Lapotron, 3, Americium, 3, siliconCarbide, 7, Palladium, 2, AETHER, 1, Germanium, 4, Neutronium, 2)
                .blastTemp(10800, BlastProperty.GasTier.HIGHER, GTValues.VA[GTValues.UV], 33333)
                .color(0x808080)
                .flags(GENERATE_DENSE, GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW, GENERATE_FOIL, GENERATE_FRAME, GENERATE_RING, GENERATE_LONG_ROD)
                .buildAndRegister();

        RADIATION_SIGHT_ALLOY_INF = REGISTRATE.material(CTNHCore.id("radiation_sight_alloy_inf"))
                .cnlang("辐射视距合金-∞")
                .ingot()
                .dust()
                .liquid()
                .components(QUANTUM_ALLOY, 3, RADIATION_SIGHT_ALLOY_X, 2, Naquadria, 7, HiddenAlloy, 2, BOUNDLESS, 3, AETERNIUM, 3, ADAMANTITE, 2)
                .blastTemp(12600, BlastProperty.GasTier.HIGHER, GTValues.VA[GTValues.UHV], 2500)
                .color(0x2D3A2A)
                .flags(GENERATE_DENSE, GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW, GENERATE_FOIL, GENERATE_FRAME, GENERATE_RING, GENERATE_LONG_ROD)
                .buildAndRegister();

        NAMI_C = REGISTRATE.material(CTNHCore.id("nami_c"))
                .cnlang("纳米级沉积碳")
                .dust()
                .liquid()
                .color(0x000000)
                .buildAndRegister();

        LIVING_METAL_PRE = REGISTRATE.material(CTNHCore.id("living_metal_pre"))
                .cnlang("未激活态活体金属")
                .formula("Sn?Pb?&LMα")
                .liquid()
                .color(0xC0C0C0)
                .buildAndRegister();

        ALKALINE_COMPLEX_ORE_SLURRY = REGISTRATE.material(CTNHCore.id("alkaline_complex_ore_slurry"))
                .cnlang("碱性复合矿液")
                .liquid()
                .color(0x40E0D0)
                .buildAndRegister();

        ALKALINE_SLAG = REGISTRATE.material(CTNHCore.id("alkaline_slag"))
                .cnlang("碱液滤渣")
                .dust()
                .color(0x505050)
                .buildAndRegister();

        STRONTIUM_CHLORIDE = REGISTRATE.material(CTNHCore.id("strontium_chloride"))
                .cnlang("氯化锶")
                .formula("SrCl2")
                .dust()
                .color(0xFFC0CB)
                .buildAndRegister();

        ORACLE = REGISTRATE.material(CTNHCore.id("oracle"))
                .cnlang("神秘液体")
                .formula("THANKS_FOR_YOUR_PLAYING")
                .dust()
                .ingot()
                .liquid()
                .plasma()
                .color(0xFFC0CB)
                .buildAndRegister();

        STEEL_PRECURSOR = REGISTRATE.material(CTNHCore.id("steel_precursor"))
                .cnlang("预制钢")
                .formula("Fe8C3")
                .dust()
                .color(0x4A4A4A)
                .buildAndRegister();

        HIGH_TEMP_WROUGHT_PRECURSOR = REGISTRATE.material(CTNHCore.id("high_temp_wrought_precursor"))
                .cnlang("预处理锻铁")
                .dust()
                .ingot()
                .iconSet(METALLIC)
                .color(0x8C7853)
                .blastTemp(7200, BlastProperty.GasTier.HIGHER, GTValues.VA[GTValues.UHV], 2500)
                .buildAndRegister();

        PERRHENIC_ACID = REGISTRATE.material(CTNHCore.id("perrhenic_acid"))
                .cnlang("高铼酸")
                .formula("HReO4")
                .liquid()
                .color(0x654AAA)
                .buildAndRegister();
    }
}
