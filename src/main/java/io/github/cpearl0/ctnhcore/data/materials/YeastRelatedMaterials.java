package io.github.cpearl0.ctnhcore.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import io.github.cpearl0.ctnhcore.CTNHCore;

import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class YeastRelatedMaterials {

    public static Material NORMAL_YEAST;
    public static Material NORMAL_YEAST_SEED_LIQUID;
    public static Material NORMAL_YEAST_LIQUID;
    public static Material NORMAL_YEAST_EXTRACT_LIQUID;
    public static Material CRIMSON_YEAST;
    public static Material CRIMSON_YEAST_SEED_LIQUID;
    public static Material CRIMSON_YEAST_LIQUID;
    public static Material CRIMSON_YEAST_EXTRACT_LIQUID;
    public static Material WARPED_YEAST;
    public static Material WARPED_YEAST_SEED_LIQUID;
    public static Material WARPED_YEAST_LIQUID;
    public static Material WARPED_YEAST_EXTRACT_LIQUID;
    public static Material END_YEAST;
    public static Material END_YEAST_SEED_LIQUID;
    public static Material END_YEAST_LIQUID;
    public static Material END_YEAST_EXTRACT_LIQUID;
    public static Material FLUORESCENCE_YEAST;
    public static Material FLUORESCENCE_YEAST_SEED_LIQUID;
    public static Material FLUORESCENCE_YEAST_LIQUID;
    public static Material FLUORESCENCE_YEAST_EXTRACT_LIQUID;
    public static Material LIGHT_YEAST;
    public static Material LIGHT_YEAST_SEED_LIQUID;
    public static Material LIGHT_YEAST_LIQUID;
    public static Material LIGHT_YEAST_EXTRACT_LIQUID;
    public static Material POLLUTED_FLUORESCENCE_YEAST;
    public static Material RADIATION_MUTATED_YEAST;
    public static Material RADIATION_MUTATED_YEAST_SEED_LIQUID;
    public static Material RADIATION_MUTATED_YEAST_LIQUID;
    public static Material RADIATION_MUTATED_YEAST_EXTRACT_LIQUID;
    public static Material WASTE_NUTRITION_LIQUID;
    public static Material CELLULOSE;
    public static Material LIGNIN;
    public static Material LYASE;
    public static Material AMINO_ACID;
    public static Material BLUE_VITRIOL_SOLUTION;
    public static Material ESCHERICHIA_COLI;
    public static Material DIRT;
    public static Material RHIZOBIUM;
    public static Material AZOTASE;
    public static Material RHIZOBIUM_EXTRACT;
    public static Material CARBONATE_BUFFER;
    public static Material PHOSPHATE_BUFFER;
    public static Material SODIUM_DIHYDROGEN_PHOSPHATE;
    public static Material DIBASIC_SODIUM_PHOSPHATE;
    public static Material THERMODURIC_BACTERIA;
    public static Material TAQ_ENZYME;
    public static Material THERMODURIC_BACTERIA_EXTRACT;
    public static Material ESCHERICHIA_COLI_EXTRACT;
    public static Material CELLULASE;

    public static void init() {
        NORMAL_YEAST = REGISTRATE.material(CTNHCore.id("normal_yeast"))
                .cnlang("酵母")
                .dust()
                .color(0xE3D7B7)
                .buildAndRegister();

        NORMAL_YEAST_SEED_LIQUID = REGISTRATE.material(CTNHCore.id("normal_yeast_seed_liquid"))
                .cnlang("酵母种子液")
                .liquid()
                .color(0xE3D7B7)
                .buildAndRegister();

        NORMAL_YEAST_LIQUID = REGISTRATE.material(CTNHCore.id("normal_yeast_liquid"))
                .cnlang("酵母原液")
                .liquid()
                .color(0xD6C493)
                .buildAndRegister();

        NORMAL_YEAST_EXTRACT_LIQUID = REGISTRATE.material(CTNHCore.id("normal_yeast_extract_liquid"))
                .cnlang("酵母提取液")
                .liquid()
                .color(0xE3BB4F)
                .buildAndRegister();

        CRIMSON_YEAST = REGISTRATE.material(CTNHCore.id("crimson_yeast"))
                .cnlang("绯红酵母")
                .dust()
                .color(0xE58877)
                .buildAndRegister();
        CRIMSON_YEAST_SEED_LIQUID = REGISTRATE.material(CTNHCore.id("crimson_yeast_seed_liquid"))
                .cnlang("绯红酵母种子液")
                .liquid()
                .color(0xE58877)
                .buildAndRegister();

        CRIMSON_YEAST_LIQUID = REGISTRATE.material(CTNHCore.id("crimson_yeast_liquid"))
                .cnlang("绯红酵母原液")
                .liquid()
                .color(0xE66F5A)
                .buildAndRegister();

        CRIMSON_YEAST_EXTRACT_LIQUID = REGISTRATE.material(CTNHCore.id("crimson_yeast_extract_liquid"))
                .cnlang("绯红酵母提取液")
                .liquid()
                .color(0xD85841)
                .buildAndRegister();

        WARPED_YEAST = REGISTRATE.material(CTNHCore.id("warped_yeast"))
                .cnlang("诡异酵母")
                .dust()
                .color(0x6ABBBA)
                .buildAndRegister();

        WARPED_YEAST_SEED_LIQUID = REGISTRATE.material(CTNHCore.id("warped_yeast_seed_liquid"))
                .cnlang("诡异酵母种子液")
                .liquid()
                .color(0x6ABBBA)
                .buildAndRegister();

        WARPED_YEAST_LIQUID = REGISTRATE.material(CTNHCore.id("warped_yeast_liquid"))
                .cnlang("诡异酵母原液")
                .liquid()
                .color(0x3A9795)
                .buildAndRegister();

        WARPED_YEAST_EXTRACT_LIQUID = REGISTRATE.material(CTNHCore.id("warped_yeast_extract_liquid"))
                .cnlang("诡异酵母提取液")
                .liquid()
                .color(0x23807E)
                .buildAndRegister();

        END_YEAST = REGISTRATE.material(CTNHCore.id("end_yeast"))
                .cnlang("末地酵母")
                .dust()
                .color(0x8EF2EB)
                .buildAndRegister();

        END_YEAST_SEED_LIQUID = REGISTRATE.material(CTNHCore.id("end_yeast_seed_liquid"))
                .cnlang("末地酵母种子液")
                .liquid()
                .color(0x8EF2EB)
                .buildAndRegister();

        END_YEAST_LIQUID = REGISTRATE.material(CTNHCore.id("end_yeast_liquid"))
                .cnlang("末地酵母原液")
                .liquid()
                .color(0x63F1E7)
                .buildAndRegister();

        END_YEAST_EXTRACT_LIQUID = REGISTRATE.material(CTNHCore.id("end_yeast_extract_liquid"))
                .cnlang("末地酵母提取液")
                .liquid()
                .color(0x3DC7BD)
                .buildAndRegister();

        FLUORESCENCE_YEAST = REGISTRATE.material(CTNHCore.id("fluorescence_yeast"))
                .cnlang("荧光酵母")
                .dust()
                .color(0xE8F090)
                .buildAndRegister();

        FLUORESCENCE_YEAST_SEED_LIQUID = REGISTRATE.material(CTNHCore.id("fluorescence_yeast_seed_liquid"))
                .cnlang("荧光酵母种子液")
                .liquid()
                .color(0xE8F090)
                .buildAndRegister();

        FLUORESCENCE_YEAST_LIQUID = REGISTRATE.material(CTNHCore.id("fluorescence_yeast_liquid"))
                .cnlang("荧光酵母原液")
                .liquid()
                .color(0xE5F163)
                .buildAndRegister();

        FLUORESCENCE_YEAST_EXTRACT_LIQUID = REGISTRATE.material(CTNHCore.id("fluorescence_yeast_extract_liquid"))
                .cnlang("荧光酵母提取液")
                .liquid()
                .color(0xC7D24D)
                .buildAndRegister();

        LIGHT_YEAST = REGISTRATE.material(CTNHCore.id("light_yeast"))
                .cnlang("光罩酵母")
                .dust()
                .color(0xC0E191)
                .buildAndRegister();

        LIGHT_YEAST_SEED_LIQUID = REGISTRATE.material(CTNHCore.id("light_yeast_seed_liquid"))
                .cnlang("光罩酵母种子液")
                .liquid()
                .color(0xC0E191)
                .buildAndRegister();

        LIGHT_YEAST_LIQUID = REGISTRATE.material(CTNHCore.id("light_yeast_liquid"))
                .cnlang("光罩酵母原液")
                .liquid()
                .color(0xAAE15B)
                .buildAndRegister();

        LIGHT_YEAST_EXTRACT_LIQUID = REGISTRATE.material(CTNHCore.id("light_yeast_extract_liquid"))
                .cnlang("光罩酵母提取液")
                .liquid()
                .color(0x87BF38)
                .buildAndRegister();

        POLLUTED_FLUORESCENCE_YEAST = REGISTRATE.material(CTNHCore.id("polluted_fluorescence_yeast"))
                .cnlang("受污染的荧光酵母")
                .dust()
                .color(0x217523)
                .buildAndRegister();

        RADIATION_MUTATED_YEAST = REGISTRATE.material(CTNHCore.id("radiation_mutated_yeast"))
                .cnlang("辐射突变酵母")
                .dust()
                .color(0x567A22)
                .buildAndRegister();

        RADIATION_MUTATED_YEAST_SEED_LIQUID = REGISTRATE.material(CTNHCore.id("radiation_mutated_yeast_seed_liquid"))
                .cnlang("辐射突变酵母种子液")
                .liquid()
                .color(0x567A22)
                .buildAndRegister();

        RADIATION_MUTATED_YEAST_LIQUID = REGISTRATE.material(CTNHCore.id("radiation_mutated_yeast_liquid"))
                .cnlang("辐射突变酵母原液")
                .liquid()
                .color(0x46661A)
                .buildAndRegister();

        RADIATION_MUTATED_YEAST_EXTRACT_LIQUID = REGISTRATE.material(CTNHCore.id("radiation_mutated_yeast_extract_liquid"))
                .cnlang("辐射突变酵母提取液")
                .liquid()
                .color(0x466916)
                .buildAndRegister();
        WASTE_NUTRITION_LIQUID = REGISTRATE.material(CTNHCore.id("waste_nutrition_liquid"))
                .cnlang("废弃营养液")
                .liquid()
                .color(0xF4DB96)
                .buildAndRegister();

        CELLULOSE = REGISTRATE.material(CTNHCore.id("cellulose"))
                .cnlang("纤维素")
                .formula("(C6H10O5)n")
                .dust()
                .color(0xEEE3BF)
                .buildAndRegister();

        LIGNIN = REGISTRATE.material(CTNHCore.id("lignin"))
                .cnlang("木质素")
                .formula("(CH2ON?S?)n")
                .dust()
                .color(0xB89320)
                .buildAndRegister();

        LYASE = REGISTRATE.material(CTNHCore.id("lyase"))
                .cnlang("裂解酶")
                .dust()
                .color(0xE4C86F)
                .buildAndRegister();

        AMINO_ACID = REGISTRATE.material(CTNHCore.id("amino_acid"))
                .cnlang("氨基酸")
                .liquid()
                .color(0xF4DE5A)
                .buildAndRegister();

        BLUE_VITRIOL_SOLUTION = REGISTRATE.material(CTNHCore.id("blue_vitriol_solution"))
                .cnlang("蓝矾溶液")
                .liquid()
                .color(0x48A5C0)
                .buildAndRegister();

        ESCHERICHIA_COLI = REGISTRATE.material(CTNHCore.id("escherichia_coli"))
                .cnlang("大肠杆菌")
                .dust()
                .color(0x014D15)
                .buildAndRegister();

        DIRT = REGISTRATE.material(CTNHCore.id("dirt"))
                .cnlang("泥土")
                .dust()
                .color(0x9E7D0E)
                .buildAndRegister();

        RHIZOBIUM = REGISTRATE.material(CTNHCore.id("rhizobium"))
                .cnlang("根瘤菌")
                .dust()
                .color(0x5E3A03)
                .buildAndRegister();

        AZOTASE = REGISTRATE.material(CTNHCore.id("azotase"))
                .cnlang("固氮酶")
                .dust()
                .color(0xAADE4B)
                .buildAndRegister();

        RHIZOBIUM_EXTRACT = REGISTRATE.material(CTNHCore.id("rhizobium_extract"))
                .cnlang("根瘤菌裂解液")
                .liquid()
                .color(0x5E3A03)
                .buildAndRegister();

        CARBONATE_BUFFER = REGISTRATE.material(CTNHCore.id("carbonate_buffer"))
                .cnlang("碳酸盐缓冲液")
                .liquid()
                .color(0xFFFCD6)
                .buildAndRegister();

        PHOSPHATE_BUFFER = REGISTRATE.material(CTNHCore.id("phosphate_buffer"))
                .cnlang("磷酸盐缓冲液")
                .liquid()
                .color(0xFFDDDD)
                .buildAndRegister();

        SODIUM_DIHYDROGEN_PHOSPHATE = REGISTRATE.material(CTNHCore.id("sodium_dihydrogen_phosphate"))
                .cnlang("磷酸二氢钠")
                .dust()
                .color(0xE6FF67)
                .components(Sodium, 1, Hydrogen, 2, Phosphorus, 1, Oxygen, 4)
                .buildAndRegister();

        DIBASIC_SODIUM_PHOSPHATE = REGISTRATE.material(CTNHCore.id("dibasic_sodium_phosphate"))
                .cnlang("磷酸氢二钠")
                .dust()
                .color(0xD2EB54)
                .components(Sodium, 2, Hydrogen, 1, Phosphorus, 1, Oxygen, 4)
                .buildAndRegister();

        THERMODURIC_BACTERIA = REGISTRATE.material(CTNHCore.id("thermoduric_bacteria"))
                .cnlang("耐热菌")
                .dust()
                .color(0xF77272)
                .buildAndRegister();

        TAQ_ENZYME = REGISTRATE.material(CTNHCore.id("taq_enzyme"))
                .cnlang("Taq扩增酶")
                .dust()
                .color(0xFCBEBE)
                .buildAndRegister();

        THERMODURIC_BACTERIA_EXTRACT = REGISTRATE.material(CTNHCore.id("thermoduric_bacteria_extract"))
                .cnlang("耐热菌裂解液")
                .liquid()
                .color(0xD98484)
                .buildAndRegister();

        ESCHERICHIA_COLI_EXTRACT = REGISTRATE.material(CTNHCore.id("escherichia_coli_extract"))
                .cnlang("大肠杆菌裂解液")
                .liquid()
                .color(0x08632E)
                .buildAndRegister();

        CELLULASE = REGISTRATE.material(CTNHCore.id("cellulase"))
                .cnlang("纤维素酶")
                .dust()
                .color(0x9FD4B5)
                .buildAndRegister();
    }
}
