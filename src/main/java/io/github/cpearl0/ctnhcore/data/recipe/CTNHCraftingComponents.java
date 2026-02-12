package io.github.cpearl0.ctnhcore.data.recipe;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.data.recipe.CraftingComponent;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhmana.registry.CMItems;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class CTNHCraftingComponents {
    public static final Map<Integer, Material> GT_SUPERCONDUCTING_MATERIAL = new HashMap<>();
    public static final Map<Item, Integer> CAPACITOR_BASE_MAP = new HashMap<>();
    public static CraftingComponent CTNH_SUPERCONDUCTING_MATERIAL;

    public static void init(){
        GT_SUPERCONDUCTING_MATERIAL.put(LV, ManganesePhosphide);
        GT_SUPERCONDUCTING_MATERIAL.put(MV, MagnesiumDiboride);
        GT_SUPERCONDUCTING_MATERIAL.put(HV, MercuryBariumCalciumCuprate);
        GT_SUPERCONDUCTING_MATERIAL.put(EV, UraniumTriplatinum);
        GT_SUPERCONDUCTING_MATERIAL.put(IV, SamariumIronArsenicOxide);
        GT_SUPERCONDUCTING_MATERIAL.put(LuV, IndiumTinBariumTitaniumCuprate);
        GT_SUPERCONDUCTING_MATERIAL.put(ZPM, UraniumRhodiumDinaquadide);
        GT_SUPERCONDUCTING_MATERIAL.put(UV, EnrichedNaquadahTriniumEuropiumDuranide);
        GT_SUPERCONDUCTING_MATERIAL.put(UHV, RutheniumTriniumAmericiumNeutronate);


        CAPACITOR_BASE_MAP.putAll(Map.of(
                GTItems.CAPACITOR.get(), 1,
                GTItems.SMD_CAPACITOR.get(), 2,
                GTItems.ADVANCED_SMD_CAPACITOR.get(), 4,
                CBItems.WETWARE_CAPACITOR.get(), 8,
                CMItems.MANA_CAPACITOR.get(), 2,
                CMItems.ADVANCED_MANA_CAPACITOR.get(), 3,
                CMItems.BLOOD_CAPACITOR.get(), 4,
                CMItems.WILL_CAPACITOR.get(), 6
        ));
    }
}
