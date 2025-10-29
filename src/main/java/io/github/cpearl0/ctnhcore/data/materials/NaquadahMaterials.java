package io.github.cpearl0.ctnhcore.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.attribute.FluidAttributes;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.api.data.material.CatalystProperty;

import static io.github.cpearl0.ctnhcore.api.data.material.CTNHPropertyKeys.CATALYST;
import static io.github.cpearl0.ctnhcore.registry.CTNHMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class NaquadahMaterials {
    public static void init() {
        NaquadahOxideMixture = REGISTRATE.material(CTNHCore.id("naquadah_oxide_mixture")).cnlang("氧化硅岩混合物").ore().dust().color(0x4c4c4c).iconSet(MaterialIconSet.ROUGH)
                        .buildAndRegister().setFormula("??NqTiGa??");

        EnrichedNaquadahOxideMixture = REGISTRATE.material(CTNHCore.id("enriched_naquadah_oxide_mixture")).cnlang("氧化富集硅岩混合物").ore().dust().color(0x826868)
                        .iconSet(MaterialIconSet.ROUGH).buildAndRegister().setFormula("??Nq+??");

        NaquadriaOxideMixture = REGISTRATE.material(CTNHCore.id("naquadria_oxide_mixture")).cnlang("氧化超能硅岩混合物").ore().dust().color(0x4d4d55).secondaryColor(0xe7e7ff)
                        .iconSet(MaterialIconSet.RADIOACTIVE).buildAndRegister().setFormula("??*Nq*In??");

        HexafluorideEnrichedNaquadahSolution = REGISTRATE.material(CTNHCore.id("hexafluoride_enriched_naquadah_solution")).cnlang("六氟化富集硅岩溶液").fluid().color(0x868D7F)
                        .components(GTMaterials.NaquadahEnriched, 1, GTMaterials.Fluorine, 6)
                        .flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister();

        XenonHexafluoroEnrichedNaquadate = REGISTRATE.material(CTNHCore.id("xenon_hexafluoro_enriched_naquadate")).cnlang("六氟氙酸富集硅岩").fluid().color(0x255A55)
                        .components(GTMaterials.Xenon, 1, GTMaterials.NaquadahEnriched, 1, GTMaterials.Fluorine, 6)
                        .flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister();

        PalladiumOnCarbon = REGISTRATE.material(CTNHCore.id("palladium_on_carbon")).cnlang("钯碳").dust().color(0x480104).iconSet(MaterialIconSet.DULL)
                        .flags(MaterialFlags.DISABLE_DECOMPOSITION).components(GTMaterials.Palladium, 1, GTMaterials.Carbon, 1)
                        .buildAndRegister();

        GoldTrifluoride = REGISTRATE.material(CTNHCore.id("gold_trifluoride")).cnlang("三氟化金").dust().color(0xE8C478).iconSet(MaterialIconSet.BRIGHT)
                        .components(GTMaterials.Gold, 1, GTMaterials.Fluorine, 3).buildAndRegister();

        EnrichedNaquadahResidueSolution = REGISTRATE.material(CTNHCore.id("enriched_naquadah_residue_solution")).cnlang("富集硅岩残余物溶液").fluid().color(0x868D7F)
                        .iconSet(MaterialIconSet.DULL).flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister()
                        .setFormula("XeAuSbKeF6S2?");

        XenoauricFluoroantimonicAcid = REGISTRATE.material(CTNHCore.id("xenoauric_fluoroantimonic_acid")).cnlang("氟锑酸金二氙残液")
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().attribute(FluidAttributes.ACID)).color(0xE0BD74)
                .components(GTMaterials.Xenon, 2, GTMaterials.Gold, 1, GTMaterials.Antimony, 2, GTMaterials.Fluorine, 12)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister();

        GoldChloride = REGISTRATE.material(CTNHCore.id("gold_chloride")).cnlang("氯化金").fluid().color(0xCCCC66)
                .components(GTMaterials.Gold, 2, GTMaterials.Chlorine, 6).buildAndRegister();

        BromineTrifluoride = REGISTRATE.material(CTNHCore.id("bromine_trifluoride")).cnlang("三氟化溴").fluid().color(0xA88E57)
                .components(GTMaterials.Bromine, 1, GTMaterials.Fluorine, 3).buildAndRegister();

        HexafluorideNaquadriaSolution = REGISTRATE.material(CTNHCore.id("hexafluoride_naquadria_solution")).cnlang("六氟化超能硅岩溶液").fluid().color(0x25C213)
                        .components(GTMaterials.Naquadria, 1, GTMaterials.Fluorine, 6)
                        .flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister();

        RadonDifluoride = REGISTRATE.material(CTNHCore.id("radon_difluoride")).cnlang("二氟化氡").fluid().color(0x8B7EFF)
                .components(GTMaterials.Radon, 1, GTMaterials.Fluorine, 2).buildAndRegister();

        RadonNaquadriaOctafluoride = REGISTRATE.material(CTNHCore.id("radon_naquadria_octafluoride")).cnlang("八氟超能硅岩酸氡").fluid().color(0x85F378)
                        .components(GTMaterials.Radon, 1, GTMaterials.Naquadria, 1, GTMaterials.Fluorine, 8)
                        .flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister();

        NaquadriaResidueSolution = REGISTRATE.material(CTNHCore.id("naquadria_residue_solution")).cnlang("超能硅岩残余物溶液").fluid().color(0x25C213).iconSet(MaterialIconSet.DULL)
                        .flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister().setFormula("InPS6?", true);

        CaesiumFluoride = REGISTRATE.material(CTNHCore.id("caesium_fluoride")).cnlang("氟化铯").fluid().color(0xFF7A5F)
                .components(GTMaterials.Caesium, 1, GTMaterials.Fluorine, 1).buildAndRegister();

        XenonTrioxide = REGISTRATE.material(CTNHCore.id("xenon_trioxide")).cnlang("三氧化氙").fluid().color(0x359FC3)
                .components(GTMaterials.Xenon, 1, GTMaterials.Oxygen, 3).buildAndRegister();

        CaesiumXenontrioxideFluoride = REGISTRATE.material(CTNHCore.id("caesium_xenontrioxide_fluoride")).cnlang("二氟三氧氙酸铯").fluid().color(0x5067D7)
                        .flags(MaterialFlags.DISABLE_DECOMPOSITION).components(
                                GTMaterials.Caesium, 1, GTMaterials.Xenon, 1, GTMaterials.Oxygen, 3, GTMaterials.Fluorine, 1
                        ).flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister();

        NaquadriaCaesiumXenonnonfluoride = REGISTRATE.material(CTNHCore.id("naquadria_caesium_xenonnonfluoride")).cnlang("九氟氙酸超能硅岩铯").fluid().color(0x54C248).components(
                        GTMaterials.Naquadria, 1, GTMaterials.Caesium, 1, GTMaterials.Xenon, 1, GTMaterials.Fluorine, 9
                ).flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister();

        RadonTrioxide = REGISTRATE.material(CTNHCore.id("radon_trioxide")).cnlang("三氧化氡").fluid().color(0x9A6DD7)
                .components(GTMaterials.Radon, 1, GTMaterials.Oxygen, 3).buildAndRegister();

        NaquadriaCaesiumfluoride = REGISTRATE.material(CTNHCore.id("naquadria_caesiumfluoride")).cnlang("二氟超能硅岩酸铯").fluid().color(0xAAEB69)
                        .components(GTMaterials.Naquadria, 1, GTMaterials.Fluorine, 3, GTMaterials.Caesium, 1)
                        .flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister().setFormula("*Nq*F2CsF", true);

        NitrosoniumOctafluoroxenate = REGISTRATE.material(CTNHCore.id("nitrosonium_octafluoroxenate")).cnlang("八氟氙酸亚硝酰").fluid().color(0x953D9F)
                        .components(GTMaterials.NitrogenDioxide, 2, GTMaterials.Xenon, 1, GTMaterials.Fluorine, 8)
                        .buildAndRegister().setFormula("(NO2)2XeF8", true);

        NitrylFluoride = REGISTRATE.material(CTNHCore.id("nitryl_fluoride")).cnlang("硝酰氟").fluid().color(0x8B7EFF)
                .components(GTMaterials.Nitrogen, 1, GTMaterials.Oxygen, 2, GTMaterials.Fluorine, 1)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister();

        AcidicNaquadriaCaesiumfluoride = REGISTRATE.material(CTNHCore.id("acidic_naquadria_caesiumfluoride")).cnlang("硫酸二氟超能硅岩酸铯").fluid().color(0x75EB00).components(
                        GTMaterials.Naquadria, 1, GTMaterials.Fluorine, 3, GTMaterials.Caesium, 1, GTMaterials.Sulfur, 2, GTMaterials.Oxygen, 8
                ).flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister().setFormula("*Nq*F2CsF(SO4)2", true);

        PalladiumOnCarbon.setProperty(CATALYST, new CatalystProperty(50));
    }
}
