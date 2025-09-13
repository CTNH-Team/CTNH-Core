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
public class NaquadahMaterials {
    public static void init() {
        NaquadahOxideMixture = new Material.Builder(CTNHCore.id("naquadah_oxide_mixture")).ore().dust().color(0x4c4c4c).iconSet(MaterialIconSet.ROUGH)
                        .buildAndRegister().setFormula("??NqTiGa??");

        EnrichedNaquadahOxideMixture = new Material.Builder(CTNHCore.id("enriched_naquadah_oxide_mixture")).ore().dust().color(0x826868)
                        .iconSet(MaterialIconSet.ROUGH).buildAndRegister().setFormula("??Nq+??");

        NaquadriaOxideMixture = new Material.Builder(CTNHCore.id("naquadria_oxide_mixture")).ore().dust().color(0x4d4d55).secondaryColor(0xe7e7ff)
                        .iconSet(MaterialIconSet.RADIOACTIVE).buildAndRegister().setFormula("??*Nq*In??");

        HexafluorideEnrichedNaquadahSolution = new Material.Builder(CTNHCore.id("hexafluoride_enriched_naquadah_solution")).fluid().color(0x868D7F)
                        .components(GTMaterials.NaquadahEnriched, 1, GTMaterials.Fluorine, 6)
                        .flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister();

        XenonHexafluoroEnrichedNaquadate = new Material.Builder(CTNHCore.id("xenon_hexafluoro_enriched_naquadate")).fluid().color(0x255A55)
                        .components(GTMaterials.Xenon, 1, GTMaterials.NaquadahEnriched, 1, GTMaterials.Fluorine, 6)
                        .flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister();

        PalladiumOnCarbon = new Material.Builder(CTNHCore.id("palladium_on_carbon")).dust().color(0x480104).iconSet(MaterialIconSet.DULL)
                        .flags(MaterialFlags.DISABLE_DECOMPOSITION).components(GTMaterials.Palladium, 1, GTMaterials.Carbon, 1)
                        .buildAndRegister();

        GoldTrifluoride = new Material.Builder(CTNHCore.id("gold_trifluoride")).dust().color(0xE8C478).iconSet(MaterialIconSet.BRIGHT)
                        .components(GTMaterials.Gold, 1, GTMaterials.Fluorine, 3).buildAndRegister();

        EnrichedNaquadahResidueSolution = new Material.Builder(CTNHCore.id("enriched_naquadah_residue_solution")).fluid().color(0x868D7F)
                        .iconSet(MaterialIconSet.DULL).flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister()
                        .setFormula("XeAuSbKeF6S2?");

        XenoauricFluoroantimonicAcid = new Material.Builder(CTNHCore.id("xenoauric_fluoroantimonic_acid"))
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().attribute(FluidAttributes.ACID)).color(0xE0BD74)
                .components(GTMaterials.Xenon, 2, GTMaterials.Gold, 1, GTMaterials.Antimony, 2, GTMaterials.Fluorine, 12)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister();

        GoldChloride = new Material.Builder(CTNHCore.id("gold_chloride")).fluid().color(0xCCCC66)
                .components(GTMaterials.Gold, 2, GTMaterials.Chlorine, 6).buildAndRegister();

        BromineTrifluoride = new Material.Builder(CTNHCore.id("bromine_trifluoride")).fluid().color(0xA88E57)
                .components(GTMaterials.Bromine, 1, GTMaterials.Fluorine, 3).buildAndRegister();

        HexafluorideNaquadriaSolution = new Material.Builder(CTNHCore.id("hexafluoride_naquadria_solution")).fluid().color(0x25C213)
                        .components(GTMaterials.Naquadria, 1, GTMaterials.Fluorine, 6)
                        .flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister();

        RadonDifluoride = new Material.Builder(CTNHCore.id("radon_difluoride")).fluid().color(0x8B7EFF)
                .components(GTMaterials.Radon, 1, GTMaterials.Fluorine, 2).buildAndRegister();

        RadonNaquadriaOctafluoride = new Material.Builder(CTNHCore.id("radon_naquadria_octafluoride")).fluid().color(0x85F378)
                        .components(GTMaterials.Radon, 1, GTMaterials.Naquadria, 1, GTMaterials.Fluorine, 8)
                        .flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister();

        NaquadriaResidueSolution = new Material.Builder(CTNHCore.id("naquadria_residue_solution")).fluid().color(0x25C213).iconSet(MaterialIconSet.DULL)
                        .flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister().setFormula("InPS6?", true);

        CaesiumFluoride = new Material.Builder(CTNHCore.id("caesium_fluoride")).fluid().color(0xFF7A5F)
                .components(GTMaterials.Caesium, 1, GTMaterials.Fluorine, 1).buildAndRegister();

        XenonTrioxide = new Material.Builder(CTNHCore.id("xenon_trioxide")).fluid().color(0x359FC3)
                .components(GTMaterials.Xenon, 1, GTMaterials.Oxygen, 3).buildAndRegister();

        CaesiumXenontrioxideFluoride = new Material.Builder(CTNHCore.id("caesium_xenontrioxide_fluoride")).fluid().color(0x5067D7)
                        .flags(MaterialFlags.DISABLE_DECOMPOSITION).components(
                                GTMaterials.Caesium, 1, GTMaterials.Xenon, 1, GTMaterials.Oxygen, 3, GTMaterials.Fluorine, 1
                        ).flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister();

        NaquadriaCaesiumXenonnonfluoride = new Material.Builder(CTNHCore.id("naquadria_caesium_xenonnonfluoride")).fluid().color(0x54C248).components(
                        GTMaterials.Naquadria, 1, GTMaterials.Caesium, 1, GTMaterials.Xenon, 1, GTMaterials.Fluorine, 9
                ).flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister();

        RadonTrioxide = new Material.Builder(CTNHCore.id("radon_trioxide")).fluid().color(0x9A6DD7)
                .components(GTMaterials.Radon, 1, GTMaterials.Oxygen, 3).buildAndRegister();

        NaquadriaCaesiumfluoride = new Material.Builder(CTNHCore.id("naquadria_caesiumfluoride")).fluid().color(0xAAEB69)
                        .components(GTMaterials.Naquadria, 1, GTMaterials.Fluorine, 3, GTMaterials.Caesium, 1)
                        .flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister().setFormula("*Nq*F2CsF", true);

        NitrosoniumOctafluoroxenate = new Material.Builder(CTNHCore.id("nitrosonium_octafluoroxenate")).fluid().color(0x953D9F)
                        .components(GTMaterials.NitrogenDioxide, 2, GTMaterials.Xenon, 1, GTMaterials.Fluorine, 8)
                        .buildAndRegister().setFormula("(NO2)2XeF8", true);

        NitrylFluoride = new Material.Builder(CTNHCore.id("nitryl_fluoride")).fluid().color(0x8B7EFF)
                .components(GTMaterials.Nitrogen, 1, GTMaterials.Oxygen, 2, GTMaterials.Fluorine, 1)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister();

        AcidicNaquadriaCaesiumfluoride = new Material.Builder(CTNHCore.id("acidic_naquadria_caesiumfluoride")).fluid().color(0x75EB00).components(
                        GTMaterials.Naquadria, 1, GTMaterials.Fluorine, 3, GTMaterials.Caesium, 1, GTMaterials.Sulfur, 2, GTMaterials.Oxygen, 8
                ).flags(MaterialFlags.DISABLE_DECOMPOSITION).buildAndRegister().setFormula("*Nq*F2CsF(SO4)2", true);

        PalladiumOnCarbon.setProperty(CATALYST, new CatalystProperty(50));
    }
}
