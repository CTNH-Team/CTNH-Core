package io.github.cpearl0.ctnhcore.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import io.github.cpearl0.ctnhcore.CTNHCore;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.DULL;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.LIGNITE;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class BiodieselFertileSoilMaterials {

    public static Material LIQUID_FLUORINE;
    public static Material SODIUM_CARBONATE_SOLUTION;
    public static Material SODIUM_CHROMATE_SOLUTION;
    public static Material SODIUM_DICHROMATE_SOLUTION;
    public static Material CHROMIUM_OXIDE;
    public static Material BITUMEN;
    public static Material PETROLEUM_COKE;
    public static Material DRIED_SALT;
    public static Material EMULSIFIED_BITUMEN;
    public static Material EMULSIFIED_BITUMEN_SLURRY;
    public static Material SIMPLE_EMULGATOR;
    public static Material SIMPLE_DEMULSIFIER;
    public static Material EMULGATOR;
    public static Material BUTYL_ACETATE;
    public static Material PURE_BITUMEN;
    public static Material PENICILLIN;
    public static Material OIL_REFINED_RESIDUES;
    public static Material RICH_SOIL;
    public static Material ETHANOL_MIXTURE;
    public static Material RAW_BIO_DIESEL;
    public static Material RICH_SOUL_SOIL;

    public static void init() {
        LIQUID_FLUORINE = REGISTRATE.material(CTNHCore.id("liquid_fluorine"))
                .cnlang("液态氟")
                .liquid(new FluidBuilder().temperature(100))
                .color(0x26A4E8)
                .components(Fluorine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        SODIUM_CARBONATE_SOLUTION = REGISTRATE.material(CTNHCore.id("sodium_carbonate_solution"))
                .cnlang("碳酸钠溶液")
                .liquid()
                .color(0x636389)
                .buildAndRegister();

        SODIUM_CHROMATE_SOLUTION = REGISTRATE.material(CTNHCore.id("sodium_chromate_solution"))
                .cnlang("铬酸钠溶液")
                .liquid()
                .color(0xBBB310)
                .buildAndRegister();

        SODIUM_DICHROMATE_SOLUTION = REGISTRATE.material(CTNHCore.id("sodium_dichromate_solution"))
                .cnlang("重铬酸钠溶液")
                .liquid()
                .color(0xB55A10)
                .buildAndRegister();

        CHROMIUM_OXIDE = REGISTRATE.material(CTNHCore.id("chromium_oxide"))
                .cnlang("氧化铬")
                .dust()
                .color(0x3DC34D)
                .iconSet(DULL)
                .components(Chromium, 2, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        BITUMEN = REGISTRATE.material(CTNHCore.id("bitumen"))
                .cnlang("沥青")
                .liquid()
                .color(0x51524A)
                .buildAndRegister();

        PETROLEUM_COKE = REGISTRATE.material(CTNHCore.id("petroleum_coke"))
                .cnlang("石油焦")
                .gem()
                .liquid()
                .color(0x545252)
                .iconSet(LIGNITE)
                .burnTime(3200)
                .buildAndRegister();

        DRIED_SALT = REGISTRATE.material(CTNHCore.id("dried_salt"))
                .cnlang("干岩盐")
                .dust()
                .color(0x919182)
                .buildAndRegister();

        EMULSIFIED_BITUMEN = REGISTRATE.material(CTNHCore.id("emulsified_bitumen"))
                .cnlang("乳化沥青")
                .liquid()
                .color(0x848572)
                .buildAndRegister();

        EMULSIFIED_BITUMEN_SLURRY = REGISTRATE.material(CTNHCore.id("emulsified_bitumen_slurry"))
                .cnlang("乳化沥青砂浆")
                .liquid()
                .color(0x5B5C4C)
                .buildAndRegister();

        SIMPLE_EMULGATOR = REGISTRATE.material(CTNHCore.id("simple_emulgator"))
                .cnlang("简易乳化剂")
                .liquid()
                .color(0xD9DE7C)
                .buildAndRegister();

        SIMPLE_DEMULSIFIER = REGISTRATE.material(CTNHCore.id("simple_demulsifier"))
                .cnlang("简易破乳剂")
                .liquid()
                .color(0xA2A659)
                .buildAndRegister();

        EMULGATOR = REGISTRATE.material(CTNHCore.id("emulgator"))
                .cnlang("破乳剂")
                .liquid()
                .color(0xDCE34F)
                .buildAndRegister();

        BUTYL_ACETATE = REGISTRATE.material(CTNHCore.id("butyl_acetate"))
                .cnlang("醋酸丁酯")
                .liquid()
                .color(0xFFDE49)
                .buildAndRegister();

        PURE_BITUMEN = REGISTRATE.material(CTNHCore.id("pure_bitumen"))
                .cnlang("纯净沥青")
                .liquid()
                .color(0x696969)
                .buildAndRegister();

        PENICILLIN = REGISTRATE.material(CTNHCore.id("penicillin"))
                .cnlang("青霉素")
                .dust()
                .color(0x56BAB5)
                .buildAndRegister();

        OIL_REFINED_RESIDUES = REGISTRATE.material(CTNHCore.id("oil_refined_residues"))
                .cnlang("炼油渣")
                .dust()
                .color(0x828277)
                .buildAndRegister();

        RICH_SOIL = REGISTRATE.material(CTNHCore.id("rich_soil"))
                .cnlang("沃土")
                .dust()
                .color(0x613A0D)
                .iconSet(DULL)
                .buildAndRegister();

        ETHANOL_MIXTURE = REGISTRATE.material(CTNHCore.id("ethanol_mixture"))
                .cnlang("含乙醇混合液")
                .liquid()
                .color(0xDE671E)
                .buildAndRegister();

        RAW_BIO_DIESEL = REGISTRATE.material(CTNHCore.id("raw_bio_diesel"))
                .cnlang("粗制生物柴油")
                .liquid()
                .color(0xE8A548)
                .buildAndRegister();

        RICH_SOUL_SOIL = REGISTRATE.material(CTNHCore.id("rich_soul_soil"))
                .cnlang("灵魂沃土")
                .dust()
                .color(0x426362)
                .iconSet(DULL)
                .buildAndRegister();
    }
}
