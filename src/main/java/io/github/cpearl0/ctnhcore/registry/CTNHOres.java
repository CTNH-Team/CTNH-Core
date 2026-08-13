package io.github.cpearl0.ctnhcore.registry;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.AdastraMaterials;
import io.github.cpearl0.ctnhcore.data.materials.NaquadahMaterials;
import io.github.cpearl0.ctnhcore.data.materials.PlatinumLineMaterials;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import com.magicbee.ctnhmana.registry.CMMaterials;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import twilightforest.data.tags.BiomeTagGenerator;

import static com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers.ENDSTONE;
import static com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers.STONE;
import static com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE;
import static com.gregtechceu.gtceu.common.data.GTOres.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHWorlds.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.*;

public class CTNHOres {

    @Key("apatite_vein_tf")
    @CN("磷灰石矿脉")
    @EN("Twilight Forest Apatite Vein")
    public static Lang apatiteVeinTf;

    @Key("arsenic_vein_ad")
    @CN("砷矿脉")
    @EN("Ad Astra Arsenic Vein")
    public static Lang arsenicVeinAd;

    @Key("bauxite_vein")
    @CN("铝土矿脉")
    @EN("Bauxite Vein")
    public static Lang bauxiteVein;

    @Key("beryllium_vein_ad")
    @CN("铍矿脉")
    @EN("Ad Astra Beryllium Vein")
    public static Lang berylliumVeinAd;

    @Key("calorite_vein_ad")
    @CN("耐热合金矿脉")
    @EN("Ad Astra Calorite Vein")
    public static Lang caloriteVeinAd;

    @Key("cassiterite_vein_ad")
    @CN("锡石矿脉")
    @EN("Ad Astra Cassiterite Vein")
    public static Lang cassiteriteVeinAd;

    @Key("cassiterite_vein_tf")
    @CN("锡石矿脉")
    @EN("Twilight Forest Cassiterite Vein")
    public static Lang cassiteriteVeinTf;

    @Key("chromium_vein_ad")
    @CN("铬矿脉")
    @EN("Ad Astra Chromium Vein")
    public static Lang chromiumVeinAd;

    @Key("coal_vein_tf")
    @CN("煤矿脉")
    @EN("Twilight Forest Coal Vein")
    public static Lang coalVeinTf;

    @Key("copper_vein_ad")
    @CN("铜矿脉")
    @EN("Ad Astra Copper Vein")
    public static Lang copperVeinAd;

    @Key("ctnhcore:ancient_debris_vein")
    @CN("远古残骸矿脉")
    @EN("Ancient Debris Vein")
    public static Lang ctnhAncientDebrisVein;

    @Key("ctnhcore:apatite_vein_tf")
    @CN("暮色森林磷灰石矿脉")
    @EN("Twilight Forest Apatite Vein")
    public static Lang ctnhApatiteVeinTf;

    @Key("ctnhcore:arctic_crystal_core_vein")
    @CN("暮色森林极寒冰核矿脉")
    @EN("Twilight Forest Arctic Crystal Core Vein")
    public static Lang ctnhArcticCrystalCoreVein;

    @Key("ctnhcore:arsenic_vein")
    @CN("红砷镍矿脉")
    @EN("Red Arsenic Nickel Vein")
    public static Lang ctnhArsenicVein;

    @Key("ctnhcore:arsenic_vein_ad")
    @CN("太空砷矿脉")
    @EN("Space Arsenic Vein")
    public static Lang ctnhArsenicVeinAd;

    @Key("ctnhcore:bauxite_vein")
    @CN("月球铝土矿脉")
    @EN("Moon Bauxite Vein")
    public static Lang ctnhBauxiteVein;

    @Key("ctnhcore:bauxite_vein_aether")
    @CN("天境铝土矿脉")
    @EN("Aether Bauxite Vein")
    public static Lang ctnhBauxiteVeinAether;

    @Key("ctnhcore:beryllium_vein_ad")
    @CN("太空铍矿脉")
    @EN("Space Beryllium Vein")
    public static Lang ctnhBerylliumVeinAd;

    @Key("ctnhcore:calorite_vein_ad")
    @CN("太空耐热金属矿脉")
    @EN("Space Calorite Vein")
    public static Lang ctnhCaloriteVeinAd;

    @Key("ctnhcore:cassiterite_vein_ad")
    @CN("太空锡石矿脉")
    @EN("Space Cassiterite Vein")
    public static Lang ctnhCassiteriteVeinAd;

    @Key("ctnhcore:chromite_vein")
    @CN("铬铁矿脉")
    @EN("Chromite Vein")
    public static Lang ctnhChromiteVein;

    @Key("ctnhcore:chromium_vein_ad")
    @CN("水星铬矿脉")
    @EN("Mercury Chromium Vein")
    public static Lang ctnhChromiumVeinAd;

    @Key("ctnhcore:combustible_ice_vein_aether")
    @CN("天境可燃冰矿脉")
    @EN("Aether Combustible Ice Vein")
    public static Lang ctnhCombustibleIceVeinAether;

    @Key("ctnhcore:copper_vein_ad")
    @CN("太空铜矿脉")
    @EN("Space Copper Vein")
    public static Lang ctnhCopperVeinAd;

    @Key("ctnhcore:cryolite_vein")
    @CN("冰晶石矿脉")
    @EN("Cryolite Vein")
    public static Lang ctnhCryoliteVein;

    @Key("ctnhcore:cryolite_vein_aether")
    @CN("天境冰晶石矿脉")
    @EN("Aether Cryolite Vein")
    public static Lang ctnhCryoliteVeinAether;

    @Key("ctnhcore:desh_vein_ad")
    @CN("月球戴斯矿脉")
    @EN("Moon Desh Vein")
    public static Lang ctnhDeshVeinAd;

    @Key("ctnhcore:diamond_vein_tf")
    @CN("暮色森林钻石矿脉")
    @EN("Twilight Forest Diamond Vein")
    public static Lang ctnhDiamondVeinTf;

    @Key("ctnhcore:dragonflame_vein")
    @CN("暮色森林龙焰矿脉")
    @EN("Twilight Forest Dragonflame Vein")
    public static Lang ctnhDragonflameVein;

    @Key("ctnhcore:eclipse_shadow_vein")
    @CN("暮色森林幽影矿脉")
    @EN("Twilight Forest Eclipse Shadow Vein")
    public static Lang ctnhEclipseShadowVein;

    @Key("ctnhcore:europium_vein")
    @CN("铕萤石矿脉")
    @EN("Europium Fluorite Vein")
    public static Lang ctnhEuropiumVein;

    @Key("ctnhcore:galena_vein_ad")
    @CN("太空方铅矿脉")
    @EN("Space Galena Vein")
    public static Lang ctnhGalenaVeinAd;

    @Key("ctnhcore:galena_vein_tf")
    @CN("暮色森林方铅矿脉")
    @EN("Twilight Forest Galena Vein")
    public static Lang ctnhGalenaVeinTf;

    @Key("ctnhcore:illusion_iron_vein")
    @CN("幻铁矿脉")
    @EN("Illusion Iron Vein")
    public static Lang ctnhIllusionIronVein;

    @Key("ctnhcore:ilmenite_vein")
    @CN("钛铁矿脉")
    @EN("Ilmenite Vein")
    public static Lang ctnhIlmeniteVein;

    @Key("ctnhcore:iridium_vein_ad")
    @CN("金星铱矿脉")
    @EN("Venus Iridium Vein")
    public static Lang ctnhIridiumVeinAd;

    @Key("ctnhcore:iron_vein_ad")
    @CN("太空带状铁矿脉")
    @EN("Space Banded Iron Vein")
    public static Lang ctnhIronVeinAd;

    @Key("ctnhcore:iron_vein_tf")
    @CN("暮色森林铁矿脉")
    @EN("Twilight Forest Iron Vein")
    public static Lang ctnhIronVeinTf;

    @Key("ctnhcore:kaolinite_vein")
    @CN("高岭石矿脉")
    @EN("Kaolinite Vein")
    public static Lang ctnhKaoliniteVein;

    @Key("ctnhcore:lapis_ore_vein")
    @CN("水星青金石矿脉")
    @EN("Mercury Lapis Lazuli Vein")
    public static Lang ctnhLapisOreVein;

    @Key("ctnhcore:lich_bone_vein")
    @CN("暮色森林巫师之骨矿脉")
    @EN("Twilight Forest Lich Bone Vein")
    public static Lang ctnhLichBoneVein;

    @Key("ctnhcore:lubricant_vein_ad")
    @CN("水星滑石矿脉")
    @EN("Mercury Talc Vein")
    public static Lang ctnhLubricantVeinAd;

    @Key("ctnhcore:magnesite_vein_ad")
    @CN("水星菱镁矿脉")
    @EN("Mercury Magnesite Vein")
    public static Lang ctnhMagnesiteVeinAd;

    @Key("ctnhcore:magnetite_vein_ad")
    @CN("太空磁铁矿脉")
    @EN("Space Magnetite Vein")
    public static Lang ctnhMagnetiteVeinAd;

    @Key("ctnhcore:mana_fused_vein")
    @CN("蕴魔矿脉")
    @EN("Mana-Fused Vein")
    public static Lang ctnhManaFusedVein;

    @Key("ctnhcore:manganese_vein_ad")
    @CN("太空锰矿脉")
    @EN("Space Manganese Vein")
    public static Lang ctnhManganeseVeinAd;

    @Key("ctnhcore:molybdenum_vein_ad")
    @CN("太空辉钼矿脉")
    @EN("Space Molybdenite Vein")
    public static Lang ctnhMolybdenumVeinAd;

    @Key("ctnhcore:monazite_vein_n")
    @CN("月球独居石矿脉")
    @EN("Moon Monazite Vein")
    public static Lang ctnhMonaziteVeinN;

    @Key("ctnhcore:moon_seawater_vein")
    @CN("静海矿藏")
    @EN("Moon Sea Water Deposit")
    public static Lang ctnhMoonSeawaterVein;

    @Key("ctnhcore:naquadah_vein_ad")
    @CN("金星硅岩矿脉")
    @EN("Venus Naquadah Vein")
    public static Lang ctnhNaquadahVeinAd;

    @Key("ctnhcore:naquadah_vein_ad_mars")
    @CN("火星硅岩矿脉")
    @EN("Mars Naquadah Vein")
    public static Lang ctnhNaquadahVeinAdMars;

    @Key("ctnhcore:nether_quartz_vein_ow")
    @CN("主世界石英矿脉")
    @EN("Overworld Quartz Vein")
    public static Lang ctnhNetherQuartzVeinOw;

    @Key("ctnhcore:neutronium_vein_ad")
    @CN("霜原星中子素矿脉")
    @EN("Glacio Neutronium Vein")
    public static Lang ctnhNeutroniumVeinAd;

    @Key("ctnhcore:nickel_vein")
    @CN("镍矿脉")
    @EN("Nickel Vein")
    public static Lang ctnhNickelVein;

    @Key("ctnhcore:nickel_vein_ad")
    @CN("太空镍矿脉")
    @EN("Space Nickel Vein")
    public static Lang ctnhNickelVeinAd;

    @Key("ctnhcore:niobium_vein_ad")
    @CN("霜原星铌矿脉")
    @EN("Glacio Niobium Vein")
    public static Lang ctnhNiobiumVeinAd;

    @Key("ctnhcore:olivine_vein_ad")
    @CN("水星橄榄石矿脉")
    @EN("Mercury Olivine Vein")
    public static Lang ctnhOlivineVeinAd;

    @Key("ctnhcore:osmium_vein_ad")
    @CN("霜原星锇矿脉")
    @EN("Glacio Osmium Vein")
    public static Lang ctnhOsmiumVeinAd;

    @Key("ctnhcore:ostrum_vein_ad")
    @CN("火星紫金矿脉")
    @EN("Mars Ostrum Vein")
    public static Lang ctnhOstrumVeinAd;

    @Key("ctnhcore:phosphate_vein")
    @CN("磷酸盐矿脉")
    @EN("Phosphate Vein")
    public static Lang ctnhPhosphateVein;

    @Key("ctnhcore:pitchblende_vein_ad")
    @CN("太空沥青铀矿脉")
    @EN("Space Pitchblende Vein")
    public static Lang ctnhPitchblendeVeinAd;

    @Key("ctnhcore:platinum_vein_ad")
    @CN("太空铂矿脉")
    @EN("Space Platinum Vein")
    public static Lang ctnhPlatinumVeinAd;

    @Key("ctnhcore:precious_alloy_vein")
    @CN("贵金属矿脉")
    @EN("Precious Alloy Vein")
    public static Lang ctnhPreciousAlloyVein;

    @Key("ctnhcore:pyrolusite_vein_ad")
    @CN("金星软锰矿脉")
    @EN("Venus Pyrolusite Vein")
    public static Lang ctnhPyrolusiteVeinAd;

    @Key("ctnhcore:quartzite_vein")
    @CN("石英岩矿脉")
    @EN("Quartzite Vein")
    public static Lang ctnhQuartziteVein;

    @Key("ctnhcore:redstone_vein_ad")
    @CN("太空红石矿脉")
    @EN("Space Redstone Vein")
    public static Lang ctnhRedstoneVeinAd;

    @Key("ctnhcore:rhodium_sulfur_crystal_vein")
    @CN("铑硫晶矿脉")
    @EN("Rhodium Sulfur Crystal Vein")
    public static Lang ctnhRhodiumSulfurCrystalVein;

    @Key("ctnhcore:ruthenium_amalgam_vein")
    @CN("钌汞齐矿脉")
    @EN("Ruthenium Amalgam Vein")
    public static Lang ctnhRutheniumAmalgamVein;

    @Key("ctnhcore:rutile_vein_ad")
    @CN("金星金红石矿脉")
    @EN("Venus Rutile Vein")
    public static Lang ctnhRutileVeinAd;

    @Key("ctnhcore:saltpeter_vein_ad")
    @CN("水星粗硝石矿脉")
    @EN("Mercury Saltpeter Vein")
    public static Lang ctnhSaltpeterVeinAd;

    @Key("ctnhcore:salts_vein_ad")
    @CN("火星盐矿脉")
    @EN("Mars Salts Vein")
    public static Lang ctnhSaltsVeinAd;

    @Key("ctnhcore:salts_vein_tf")
    @CN("暮色森林盐矿脉")
    @EN("Twilight Forest Salts Vein")
    public static Lang ctnhSaltsVeinTf;

    @Key("ctnhcore:scheelite_vein_ad")
    @CN("太空白钨矿脉")
    @EN("Space Scheelite Vein")
    public static Lang ctnhScheeliteVeinAd;

    @Key("ctnhcore:scheelite_vein_aether")
    @CN("天境白钨矿脉")
    @EN("Aether Scheelite Vein")
    public static Lang ctnhScheeliteVeinAether;

    @Key("ctnhcore:seawater_vein_overworld")
    @CN("海水矿藏")
    @EN("Seawater Deposit")
    public static Lang ctnhSeawaterVeinOverworld;

    @Key("ctnhcore:sheldonite_vein_moon")
    @CN("月球谢尔顿矿脉")
    @EN("Moon Sheldonite Vein")
    public static Lang ctnhSheldoniteVeinMoon;

    @Key("ctnhcore:steel_leaf_vein")
    @CN("暮色森林钢叶矿脉")
    @EN("Twilight Forest Steel Leaf Vein")
    public static Lang ctnhSteelLeafVein;

    @Key("ctnhcore:sulfur_vein_ad")
    @CN("太空硫矿脉")
    @EN("Space Sulfur Vein")
    public static Lang ctnhSulfurVeinAd;

    @Key("ctnhcore:tetrahedrite_vein_ad")
    @CN("太空黝铜矿脉")
    @EN("Space Tetrahedrite Vein")
    public static Lang ctnhTetrahedriteVeinAd;

    @Key("ctnhcore:thorium_vein_ad")
    @CN("太空钍矿脉")
    @EN("Space Thorium Vein")
    public static Lang ctnhThoriumVeinAd;

    @Key("ctnhcore:thunderstrike_vein")
    @CN("暮色森林雷纹矿脉")
    @EN("Twilight Forest Thunderstrike Vein")
    public static Lang ctnhThunderstrikeVein;

    @Key("ctnhcore:toxic_swamp_amber_vein")
    @CN("暮色森林毒沼琥珀矿脉")
    @EN("Twilight Forest Toxic Swamp Amber Vein")
    public static Lang ctnhToxicSwampAmberVein;

    @Key("ctnhcore:tuff_uraninite_vein_ad")
    @CN("太空晶质铀矿脉")
    @EN("Space Tuff Uraninite Vein")
    public static Lang ctnhTuffUraniniteVeinAd;

    @Key("ctnhcore:uranium238_vein_ad")
    @CN("水星铀238矿脉")
    @EN("Mercury Uranium-238 Vein")
    public static Lang ctnhUranium238VeinAd;

    @Key("ctnhcore:wollastonite_vein")
    @CN("硅灰石矿脉")
    @EN("Wollastonite Vein")
    public static Lang ctnhWollastoniteVein;

    @Key("ctnhcore:zanite_vein_aether")
    @CN("天境紫晶矿石")
    @EN("Aether Zanite Vein")
    public static Lang ctnhZaniteVeinAether;

    @Key("ctnhcore:zinc_vein")
    @CN("锌矿脉")
    @EN("Zinc Vein")
    public static Lang ctnhZincVein;

    @Key("ctnhcore:zircon_vein_aether")
    @CN("天境锆石矿脉")
    @EN("Aether Zircon Vein")
    public static Lang ctnhZirconVeinAether;

    @Key("ctnhcore:zirkelite_vein")
    @CN("钛锆钍石矿脉")
    @EN("Zirkelite Vein")
    public static Lang ctnhZirkeliteVein;

    @Key("desh_vein_ad")
    @CN("戴斯矿脉")
    @EN("Ad Astra Desh Vein")
    public static Lang deshVeinAd;

    @Key("diamond_vein_tf")
    @CN("钻石矿脉")
    @EN("Twilight Forest Diamond Vein")
    public static Lang diamondVeinTf;

    @Key("galena_vein_ad")
    @CN("方铅矿脉")
    @EN("Ad Astra Galena Vein")
    public static Lang galenaVeinAd;

    @Key("galena_vein_tf")
    @CN("方铅矿脉")
    @EN("Twilight Forest Galena Vein")
    public static Lang galenaVeinTf;

    @Key("gold_vein_tf")
    @CN("金矿脉")
    @EN("Twilight Forest Gold Vein")
    public static Lang goldVeinTf;

    @Key("gtceu.jei.ore_vein.ancient_debris_vein")
    @CN("远古残骸矿脉")
    @EN("Ancient Debris Vein")
    public static Lang gtceuAncientDebrisVein;

    @Key("gtceu.jei.ore_vein.apatite_vein_tf")
    @CN("暮色森林磷灰石矿脉")
    @EN("Twilight Forest Apatite Vein")
    public static Lang gtceuApatiteVeinTf;

    @Key("gtceu.jei.ore_vein.arctic_crystal_core_vein")
    @CN("暮色森林极寒冰核矿脉")
    @EN("Twilight Forest Arctic Crystal Core Vein")
    public static Lang gtceuArcticCrystalCoreVein;

    @Key("gtceu.jei.ore_vein.arsenic_vein")
    @CN("红砷镍矿脉")
    @EN("Red Arsenic Nickel Vein")
    public static Lang gtceuArsenicVein;

    @Key("gtceu.jei.ore_vein.arsenic_vein_ad")
    @CN("太空砷矿脉")
    @EN("Space Arsenic Vein")
    public static Lang gtceuArsenicVeinAd;

    @Key("gtceu.jei.ore_vein.bauxite_vein")
    @CN("月球铝土矿脉")
    @EN("Moon Bauxite Vein")
    public static Lang gtceuBauxiteVein;

    @Key("gtceu.jei.ore_vein.bauxite_vein_aether")
    @CN("天境铝土矿脉")
    @EN("Aether Bauxite Vein")
    public static Lang gtceuBauxiteVeinAether;

    @Key("gtceu.jei.ore_vein.beryllium_vein_ad")
    @CN("太空铍矿脉")
    @EN("Space Beryllium Vein")
    public static Lang gtceuBerylliumVeinAd;

    @Key("gtceu.jei.ore_vein.calorite_vein_ad")
    @CN("太空耐热金属矿脉")
    @EN("Space Calorite Vein")
    public static Lang gtceuCaloriteVeinAd;

    @Key("gtceu.jei.ore_vein.cassiterite_vein_ad")
    @CN("太空锡石矿脉")
    @EN("Space Cassiterite Vein")
    public static Lang gtceuCassiteriteVeinAd;

    @Key("gtceu.jei.ore_vein.chromite_vein")
    @CN("铬铁矿脉")
    @EN("Chromite Vein")
    public static Lang gtceuChromiteVein;

    @Key("gtceu.jei.ore_vein.chromium_vein_ad")
    @CN("水星铬矿脉")
    @EN("Mercury Chromium Vein")
    public static Lang gtceuChromiumVeinAd;

    @Key("gtceu.jei.ore_vein.combustible_ice_vein_aether")
    @CN("天境可燃冰矿脉")
    @EN("Aether Combustible Ice Vein")
    public static Lang gtceuCombustibleIceVeinAether;

    @Key("gtceu.jei.ore_vein.copper_vein_ad")
    @CN("太空铜矿脉")
    @EN("Space Copper Vein")
    public static Lang gtceuCopperVeinAd;

    @Key("gtceu.jei.ore_vein.cryolite_vein")
    @CN("冰晶石矿脉")
    @EN("Cryolite Vein")
    public static Lang gtceuCryoliteVein;

    @Key("gtceu.jei.ore_vein.cryolite_vein_aether")
    @CN("天境冰晶石矿脉")
    @EN("Aether Cryolite Vein")
    public static Lang gtceuCryoliteVeinAether;

    @Key("gtceu.jei.ore_vein.desh_vein_ad")
    @CN("月球戴斯矿脉")
    @EN("Moon Desh Vein")
    public static Lang gtceuDeshVeinAd;

    @Key("gtceu.jei.ore_vein.diamond_vein_tf")
    @CN("暮色森林钻石矿脉")
    @EN("Twilight Forest Diamond Vein")
    public static Lang gtceuDiamondVeinTf;

    @Key("gtceu.jei.ore_vein.dragonflame_vein")
    @CN("暮色森林龙焰矿脉")
    @EN("Twilight Forest Dragonflame Vein")
    public static Lang gtceuDragonflameVein;

    @Key("gtceu.jei.ore_vein.eclipse_shadow_vein")
    @CN("暮色森林幽影矿脉")
    @EN("Twilight Forest Eclipse Shadow Vein")
    public static Lang gtceuEclipseShadowVein;

    @Key("gtceu.jei.ore_vein.europium_vein")
    @CN("铕萤石矿脉")
    @EN("Europium Fluorite Vein")
    public static Lang gtceuEuropiumVein;

    @Key("gtceu.jei.ore_vein.galena_vein_ad")
    @CN("太空方铅矿脉")
    @EN("Space Galena Vein")
    public static Lang gtceuGalenaVeinAd;

    @Key("gtceu.jei.ore_vein.galena_vein_tf")
    @CN("暮色森林方铅矿脉")
    @EN("Twilight Forest Galena Vein")
    public static Lang gtceuGalenaVeinTf;

    @Key("gtceu.jei.ore_vein.gold_vein_tf")
    @CN("暮色森林磁铁矿脉")
    @EN("Twilight Forest Magnetite Vein")
    public static Lang gtceuGoldVeinTf;

    @Key("gtceu.jei.ore_vein.illusion_iron_vein")
    @CN("幻铁矿脉")
    @EN("Illusion Iron Vein")
    public static Lang gtceuIllusionIronVein;

    @Key("gtceu.jei.ore_vein.ilmenite_vein")
    @CN("钛铁矿脉")
    @EN("Ilmenite Vein")
    public static Lang gtceuIlmeniteVein;

    @Key("gtceu.jei.ore_vein.iridium_vein_ad")
    @CN("金星铱矿脉")
    @EN("Venus Iridium Vein")
    public static Lang gtceuIridiumVeinAd;

    @Key("gtceu.jei.ore_vein.iron_vein_ad")
    @CN("太空带状铁矿脉")
    @EN("Space Banded Iron Vein")
    public static Lang gtceuIronVeinAd;

    @Key("gtceu.jei.ore_vein.iron_vein_tf")
    @CN("暮色森林铁矿脉")
    @EN("Twilight Forest Iron Vein")
    public static Lang gtceuIronVeinTf;

    @Key("gtceu.jei.ore_vein.kaolinite_vein")
    @CN("高岭石矿脉")
    @EN("Kaolinite Vein")
    public static Lang gtceuKaoliniteVein;

    @Key("gtceu.jei.ore_vein.lapis_ore_vein")
    @CN("水星青金石矿脉")
    @EN("Mercury Lapis Lazuli Vein")
    public static Lang gtceuLapisOreVein;

    @Key("gtceu.jei.ore_vein.lich_bone_vein")
    @CN("暮色森林巫师之骨矿脉")
    @EN("Twilight Forest Lich Bone Vein")
    public static Lang gtceuLichBoneVein;

    @Key("gtceu.jei.ore_vein.lubricant_vein_ad")
    @CN("水星滑石矿脉")
    @EN("Mercury Talc Vein")
    public static Lang gtceuLubricantVeinAd;

    @Key("gtceu.jei.ore_vein.magnesite_vein_ad")
    @CN("水星菱镁矿脉")
    @EN("Mercury Magnesite Vein")
    public static Lang gtceuMagnesiteVeinAd;

    @Key("gtceu.jei.ore_vein.magnetite_vein_ad")
    @CN("太空磁铁矿脉")
    @EN("Space Magnetite Vein")
    public static Lang gtceuMagnetiteVeinAd;

    @Key("gtceu.jei.ore_vein.mana_fused_vein")
    @CN("蕴魔矿脉")
    @EN("Mana-Fused Vein")
    public static Lang gtceuManaFusedVein;

    @Key("gtceu.jei.ore_vein.manganese_vein_ad")
    @CN("太空锰矿脉")
    @EN("Space Manganese Vein")
    public static Lang gtceuManganeseVeinAd;

    @Key("gtceu.jei.ore_vein.molybdenum_vein_ad")
    @CN("太空辉钼矿脉")
    @EN("Space Molybdenite Vein")
    public static Lang gtceuMolybdenumVeinAd;

    @Key("gtceu.jei.ore_vein.monazite_vein_n")
    @CN("月球独居石矿脉")
    @EN("Moon Monazite Vein")
    public static Lang gtceuMonaziteVeinN;

    @Key("gtceu.jei.ore_vein.naquadah_vein_ad")
    @CN("金星硅岩矿脉")
    @EN("Venus Naquadah Vein")
    public static Lang gtceuNaquadahVeinAd;

    @Key("gtceu.jei.ore_vein.naquadah_vein_ad_mars")
    @CN("火星硅岩矿脉")
    @EN("Mars Naquadah Vein")
    public static Lang gtceuNaquadahVeinAdMars;

    @Key("gtceu.jei.ore_vein.nether_quartz_vein_ow")
    @CN("主世界石英矿脉")
    @EN("Overworld Quartz Vein")
    public static Lang gtceuNetherQuartzVeinOw;

    @Key("gtceu.jei.ore_vein.neutronium_vein_ad")
    @CN("霜原星中子素矿脉")
    @EN("Glacio Neutronium Vein")
    public static Lang gtceuNeutroniumVeinAd;

    @Key("gtceu.jei.ore_vein.nickel_vein")
    @CN("镍矿脉")
    @EN("Nickel Vein")
    public static Lang gtceuNickelVein;

    @Key("gtceu.jei.ore_vein.nickel_vein_ad")
    @CN("太空镍矿脉")
    @EN("Space Nickel Vein")
    public static Lang gtceuNickelVeinAd;

    @Key("gtceu.jei.ore_vein.niobium_vein_ad")
    @CN("霜原星铌矿脉")
    @EN("Glacio Niobium Vein")
    public static Lang gtceuNiobiumVeinAd;

    @Key("gtceu.jei.ore_vein.olivine_vein_ad")
    @CN("水星橄榄石矿脉")
    @EN("Mercury Olivine Vein")
    public static Lang gtceuOlivineVeinAd;

    @Key("gtceu.jei.ore_vein.osmium_vein_ad")
    @CN("霜原星锇矿脉")
    @EN("Glacio Osmium Vein")
    public static Lang gtceuOsmiumVeinAd;

    @Key("gtceu.jei.ore_vein.ostrum_vein_ad")
    @CN("火星紫金矿脉")
    @EN("Mars Ostrum Vein")
    public static Lang gtceuOstrumVeinAd;

    @Key("gtceu.jei.ore_vein.phosphate_vein")
    @CN("磷酸盐矿脉")
    @EN("Phosphate Vein")
    public static Lang gtceuPhosphateVein;

    @Key("gtceu.jei.ore_vein.pitchblende_vein_ad")
    @CN("太空沥青铀矿脉")
    @EN("Space Pitchblende Vein")
    public static Lang gtceuPitchblendeVeinAd;

    @Key("gtceu.jei.ore_vein.platinum_vein_ad")
    @CN("太空铂矿脉")
    @EN("Space Platinum Vein")
    public static Lang gtceuPlatinumVeinAd;

    @Key("gtceu.jei.ore_vein.precious_alloy_vein")
    @CN("贵金属矿脉")
    @EN("Precious Alloy Vein")
    public static Lang gtceuPreciousAlloyVein;

    @Key("gtceu.jei.ore_vein.pyrolusite_vein_ad")
    @CN("金星软锰矿脉")
    @EN("Venus Pyrolusite Vein")
    public static Lang gtceuPyrolusiteVeinAd;

    @Key("gtceu.jei.ore_vein.quartzite_vein")
    @CN("石英岩矿脉")
    @EN("Quartzite Vein")
    public static Lang gtceuQuartziteVein;

    @Key("gtceu.jei.ore_vein.redstone_vein_ad")
    @CN("太空红石矿脉")
    @EN("Space Redstone Vein")
    public static Lang gtceuRedstoneVeinAd;

    @Key("gtceu.jei.ore_vein.rhodium_sulfur_crystal_vein")
    @CN("铑硫晶矿脉")
    @EN("Rhodium Sulfur Crystal Vein")
    public static Lang gtceuRhodiumSulfurCrystalVein;

    @Key("gtceu.jei.ore_vein.ruthenium_amalgam_vein")
    @CN("钌汞齐矿脉")
    @EN("Ruthenium Amalgam Vein")
    public static Lang gtceuRutheniumAmalgamVein;

    @Key("gtceu.jei.ore_vein.rutile_vein_ad")
    @CN("金星金红石矿脉")
    @EN("Venus Rutile Vein")
    public static Lang gtceuRutileVeinAd;

    @Key("gtceu.jei.ore_vein.saltpeter_vein_ad")
    @CN("水星粗硝石矿脉")
    @EN("Mercury Saltpeter Vein")
    public static Lang gtceuSaltpeterVeinAd;

    @Key("gtceu.jei.ore_vein.salts_vein_ad")
    @CN("火星盐矿脉")
    @EN("Mars Salts Vein")
    public static Lang gtceuSaltsVeinAd;

    @Key("gtceu.jei.ore_vein.salts_vein_tf")
    @CN("暮色森林盐矿脉")
    @EN("Twilight Forest Salts Vein")
    public static Lang gtceuSaltsVeinTf;

    @Key("gtceu.jei.ore_vein.scheelite_vein_ad")
    @CN("太空白钨矿脉")
    @EN("Space Scheelite Vein")
    public static Lang gtceuScheeliteVeinAd;

    @Key("gtceu.jei.ore_vein.scheelite_vein_aether")
    @CN("天境白钨矿脉")
    @EN("Aether Scheelite Vein")
    public static Lang gtceuScheeliteVeinAether;

    @Key("gtceu.jei.ore_vein.sheldonite_vein_moon")
    @CN("月球谢尔顿矿脉")
    @EN("Moon Sheldonite Vein")
    public static Lang gtceuSheldoniteVeinMoon;

    @Key("gtceu.jei.ore_vein.steel_leaf_vein")
    @CN("暮色森林钢叶矿脉")
    @EN("Twilight Forest Steel Leaf Vein")
    public static Lang gtceuSteelLeafVein;

    @Key("gtceu.jei.ore_vein.sulfur_vein_ad")
    @CN("太空硫矿脉")
    @EN("Space Sulfur Vein")
    public static Lang gtceuSulfurVeinAd;

    @Key("gtceu.jei.ore_vein.tetrahedrite_vein_ad")
    @CN("太空黝铜矿脉")
    @EN("Space Tetrahedrite Vein")
    public static Lang gtceuTetrahedriteVeinAd;

    @Key("gtceu.jei.ore_vein.thorium_vein_ad")
    @CN("太空钍矿脉")
    @EN("Space Thorium Vein")
    public static Lang gtceuThoriumVeinAd;

    @Key("gtceu.jei.ore_vein.thunderstrike_vein")
    @CN("暮色森林雷纹矿脉")
    @EN("Twilight Forest Thunderstrike Vein")
    public static Lang gtceuThunderstrikeVein;

    @Key("gtceu.jei.ore_vein.toxic_swamp_amber_vein")
    @CN("暮色森林毒沼琥珀矿脉")
    @EN("Twilight Forest Toxic Swamp Amber Vein")
    public static Lang gtceuToxicSwampAmberVein;

    @Key("gtceu.jei.ore_vein.tuff_uraninite_vein_ad")
    @CN("太空晶质铀矿脉")
    @EN("Space Tuff Uraninite Vein")
    public static Lang gtceuTuffUraniniteVeinAd;

    @Key("gtceu.jei.ore_vein.uranium238_vein_ad")
    @CN("水星铀238矿脉")
    @EN("Mercury Uranium-238 Vein")
    public static Lang gtceuUranium238VeinAd;

    @Key("gtceu.jei.ore_vein.wollastonite_vein")
    @CN("硅灰石矿脉")
    @EN("Wollastonite Vein")
    public static Lang gtceuWollastoniteVein;

    @Key("gtceu.jei.ore_vein.zanite_vein_aether")
    @CN("天境紫晶矿石")
    @EN("Aether Zanite Vein")
    public static Lang gtceuZaniteVeinAether;

    @Key("gtceu.jei.ore_vein.zinc_vein")
    @CN("锌矿脉")
    @EN("Zinc Vein")
    public static Lang gtceuZincVein;

    @Key("gtceu.jei.ore_vein.zircon_vein_aether")
    @CN("天境锆石矿脉")
    @EN("Aether Zircon Vein")
    public static Lang gtceuZirconVeinAether;

    @Key("gtceu.jei.ore_vein.zirkelite_vein")
    @CN("钛锆钍石矿脉")
    @EN("Zirkelite Vein")
    public static Lang gtceuZirkeliteVein;

    @Key("ilmenite_vein")
    @CN("钛铁矿脉")
    @EN("Ilmenite Vein")
    public static Lang ilmeniteVein;

    @Key("iridium_vein_ad")
    @CN("铱矿脉")
    @EN("Ad Astra Iridium Vein")
    public static Lang iridiumVeinAd;

    @Key("iron_vein_ad")
    @CN("铁矿脉")
    @EN("Ad Astra Iron Vein")
    public static Lang ironVeinAd;

    @Key("iron_vein_tf")
    @CN("铁矿脉")
    @EN("Twilight Forest Iron Vein")
    public static Lang ironVeinTf;

    @Key("kaolinite_vein")
    @CN("高岭石矿脉")
    @EN("Kaolinite Vein")
    public static Lang kaoliniteVein;

    @Key("lapis_vein_ad")
    @CN("青金石矿脉")
    @EN("Ad Astra Lapis Lazuli Vein")
    public static Lang lapisVeinAd;

    @Key("lapis_vein_tf")
    @CN("青金石矿脉")
    @EN("Twilight Forest Lapis Lazuli Vein")
    public static Lang lapisVeinTf;

    @Key("lubricant_vein_ad")
    @CN("皂石矿脉")
    @EN("Ad Astra Soapstone Vein")
    public static Lang lubricantVeinAd;

    @Key("lubricant_vein_tf")
    @CN("皂石矿脉")
    @EN("Twilight Forest Soapstone Vein")
    public static Lang lubricantVeinTf;

    @Key("magnesite_vein_ad")
    @CN("菱镁矿脉")
    @EN("Ad Astra Magnesite Vein")
    public static Lang magnesiteVeinAd;

    @Key("magnetite_vein_ad")
    @CN("磁铁矿脉")
    @EN("Ad Astra Magnetite Vein")
    public static Lang magnetiteVeinAd;

    @Key("manganese_vein_ad")
    @CN("锰矿脉")
    @EN("Ad Astra Manganese Vein")
    public static Lang manganeseVeinAd;

    @Key("molybdenite_vein_tf")
    @CN("辉钼矿脉")
    @EN("Twilight Forest Molybdenite Vein")
    public static Lang molybdeniteVeinTf;

    @Key("molybdenum_vein_ad")
    @CN("钼矿脉")
    @EN("Ad Astra Molybdenum Vein")
    public static Lang molybdenumVeinAd;

    @Key("monazite_vein_n")
    @CN("独居石矿脉")
    @EN("Moon Monazite Vein")
    public static Lang monaziteVeinN;

    @Key("naquadah_vein_ad")
    @CN("硅岩矿脉")
    @EN("Ad Astra Naquadah Vein")
    public static Lang naquadahVeinAd;

    @Key("naquadah_vein_ad_mars")
    @CN("硅岩矿脉")
    @EN("Mars Naquadah Vein")
    public static Lang naquadahVeinAdMars;

    @Key("neutronium_vein_ad")
    @CN("中子素矿脉")
    @EN("Ad Astra Neutronium Vein")
    public static Lang neutroniumVeinAd;

    @Key("nickel_vein_ad")
    @CN("镍矿脉")
    @EN("Ad Astra Nickel Vein")
    public static Lang nickelVeinAd;

    @Key("nickel_vein_tf")
    @CN("镍矿脉")
    @EN("Twilight Forest Nickel Vein")
    public static Lang nickelVeinTf;

    @Key("niobium_vein_ad")
    @CN("铌矿脉")
    @EN("Ad Astra Niobium Vein")
    public static Lang niobiumVeinAd;

    @Key("olivine_vein_ad")
    @CN("橄榄石矿脉")
    @EN("Ad Astra Olivine Vein")
    public static Lang olivineVeinAd;

    @Key("olivine_vein_tf")
    @CN("橄榄石矿脉")
    @EN("Twilight Forest Olivine Vein")
    public static Lang olivineVeinTf;

    @Key("osmium_vein_ad")
    @CN("锇矿脉")
    @EN("Ad Astra Osmium Vein")
    public static Lang osmiumVeinAd;

    @Key("ostrum_vein_ad")
    @CN("紫金矿脉")
    @EN("Ad Astra Ostrum Vein")
    public static Lang ostrumVeinAd;

    @Key("pitchblende_vein_ad")
    @CN("沥青铀矿脉")
    @EN("Ad Astra Pitchblende Vein")
    public static Lang pitchblendeVeinAd;

    @Key("platinum_vein_ad")
    @CN("铂矿脉")
    @EN("Ad Astra Platinum Vein")
    public static Lang platinumVeinAd;

    @Key("pyrolusite_vein_ad")
    @CN("软锰矿脉")
    @EN("Ad Astra Pyrolusite Vein")
    public static Lang pyrolusiteVeinAd;

    @Key("quartzite_vein")
    @CN("石英岩矿脉")
    @EN("Quartzite Vein")
    public static Lang quartziteVein;

    @Key("redstone_vein_ad")
    @CN("红石矿脉")
    @EN("Ad Astra Redstone Vein")
    public static Lang redstoneVeinAd;

    @Key("rutile_vein_ad")
    @CN("金红石矿脉")
    @EN("Ad Astra Rutile Vein")
    public static Lang rutileVeinAd;

    @Key("saltpeter_vein_ad")
    @CN("蓝石矿脉")
    @EN("Ad Astra Saltpeter Vein")
    public static Lang saltpeterVeinAd;

    @Key("salts_vein_ad")
    @CN("盐矿脉")
    @EN("Ad Astra Salts Vein")
    public static Lang saltsVeinAd;

    @Key("salts_vein_tf")
    @CN("盐矿脉")
    @EN("Twilight Forest Salts Vein")
    public static Lang saltsVeinTf;

    @Key("sapphire_vein_tf")
    @CN("蓝宝石矿脉")
    @EN("Twilight Forest Sapphire Vein")
    public static Lang sapphireVeinTf;

    @Key("scheelite_vein_ad")
    @CN("白钨矿脉")
    @EN("Ad Astra Scheelite Vein")
    public static Lang scheeliteVeinAd;

    @Key("sulfur_vein_ad")
    @CN("硫矿脉")
    @EN("Ad Astra Sulfur Vein")
    public static Lang sulfurVeinAd;

    @Key("tetrahedrite_vein_ad")
    @CN("黝铜矿脉")
    @EN("Ad Astra Tetrahedrite Vein")
    public static Lang tetrahedriteVeinAd;

    @Key("tuff_uraninite_vein_ad")
    @CN("晶质铀矿脉")
    @EN("Ad Astra Tuff Uraninite Vein")
    public static Lang tuffUraniniteVeinAd;

    @Key("uranium238_vein_ad")
    @CN("铀238矿脉")
    @EN("Ad Astra Uranium-238 Vein")
    public static Lang uranium238VeinAd;

    @Key("wollastonite_vein")
    @CN("白云石矿脉")
    @EN("Dolomite Vein")
    public static Lang wollastoniteVein;

    public static void init() {
        MAGNETITE_VEIN_OW.layeredVeinGenerator(generator -> generator
                .buildLayerPattern(pattern -> pattern
                        .layer(l -> l.weight(3).mat(GTMaterials.Magnetite).size(2, 4))
                        .layer(l -> l.weight(2).mat(GTMaterials.VanadiumMagnetite).size(1, 1))
                        .layer(l -> l.weight(1).mat(CTNHMaterials.PreciousAlloy).size(1, 1))));
        // Chromite
        // Alumina
        // GTNNOres.INSTANCE.getGOLD_VEIN_TF().layeredVeinGenerator(generator -> generator
        // .buildLayerPattern(pattern -> pattern
        // .layer(l -> l.weight(3).mat(GTMaterials.Magnetite).size(2, 4))
        // .layer(l -> l.weight(2).mat(GTMaterials.VanadiumMagnetite).size(1, 1))
        // .layer(l -> l.weight(1).mat(CTNHMaterials.PreciousAlloy).size(1, 1))
        // )
        // );
        // GTNNOres.INSTANCE.getARSENIC_VEIN_AD().layeredVeinGenerator(generator -> generator
        // .buildLayerPattern(pattern -> pattern
        // .layer(l -> l.weight(1).mat(CTNHMaterials.Sperrylite).size(1, 2))
        // .layer(l -> l.weight(1).mat(CTNHMaterials.Bismuthinite).size(1, 2))
        // .layer(l -> l.weight(1).mat(GTMaterials.Stibnite).size(1, 2))
        // )
        // );
        // GTNNOres.INSTANCE.getIRIDIUM_VEIN_AD().layeredVeinGenerator(generator -> generator
        // .buildLayerPattern(pattern -> pattern
        // .layer(l -> l.weight(1).mat(GTMaterials.Nickel).size(2, 3))
        // .layer(l -> l.weight(1).mat(CTNHMaterials.MeteoricTroilite).size(1, 2))
        // .layer(l -> l.weight(1).mat(CTNHMaterials.PalladiumSulfide).size(1, 2))
        // )
        // );
        // GTNNOres.INSTANCE.getCHROMIUM_VEIN_AD().layeredVeinGenerator(generator -> generator
        // .buildLayerPattern(pattern -> pattern
        // .layer(l -> l.weight(1).mat(GTMaterials.Chromite).size(2, 3))
        // .layer(l -> l.weight(1).mat(CTNHMaterials.Wolframite).size(1, 2))
        // .layer(l -> l.weight(1).mat(CTNHMaterials.Tarkianite).size(1, 2))
        // .layer(l -> l.weight(1).mat(GTMaterials.Pyrolusite).size(1, 2))
        // )
        // );
        // GTNNOres.INSTANCE.getNIOBIUM_VEIN_AD().layeredVeinGenerator(generator -> generator
        // .buildLayerPattern(pattern -> pattern
        // .layer(l -> l.weight(1).mat(GTMaterials.Niobium).size(2, 3))
        // .layer(l -> l.weight(1).mat(CTNHMaterials.MeteoricTroilite).size(1, 2))
        // .layer(l -> l.weight(1).mat(CTNHMaterials.Germanite).size(1, 2))
        // )
        // );
        // GTNNOres.INSTANCE.getOSMIUM_VEIN_AD().layeredVeinGenerator(generator -> generator
        // .buildLayerPattern(pattern -> pattern
        // .layer(l -> l.weight(1).mat(GTMaterials.Nickel).size(2, 3))
        // .layer(l -> l.weight(1).mat(CTNHMaterials.OsmiumIronSpinel).size(1, 1))
        // .layer(l -> l.weight(1).mat(CTNHMaterials.MeteoricTroilite).size(1, 1))
        // .layer(l -> l.weight(1).mat(CTNHMaterials.Crocoite).size(2, 3))
        // )
        // );
        // GTNNOres.INSTANCE.getNEUTRONIUM_VEIN_AD().layeredVeinGenerator(generator -> generator
        // .buildLayerPattern(pattern -> pattern
        // .layer(l -> l.weight(1).mat(GTMaterials.Neutronium).size(2, 3))
        // .layer(l -> l.weight(1).mat(CTNHMaterials.OsmiumIronSpinel).size(1, 1))
        // .layer(l -> l.weight(1).mat(GTMaterials.Naquadria).size(1, 1))
        // .layer(l -> l.weight(1).mat(CTNHMaterials.Rheniite).size(1, 1))
        // )
        // );
        GTRegistries.ORE_VEINS.remove(GTCEu.id("nether_quartz_vein"));
        GTRegistries.ORE_VEINS.remove(GTCEu.id("nickel_vein"));
        GTRegistries.ORE_VEINS.remove(GTCEu.id("galena_vein"));
        GTRegistries.ORE_VEINS.remove(GTCEu.id("sheldonite_vein"));
        GTRegistries.ORE_VEINS.remove(GTCEu.id("bauxite_vein_end"));
        GTRegistries.ORE_VEINS.remove(GTCEu.id("naquadah_vein"));
        MICA_VEIN.layer(WorldGenLayers.NETHERRACK)
                .dimensions(ResourceLocation.tryParse("minecraft:the_nether"))
                .biomes(BiomeTags.IS_NETHER)
                .heightRangeUniform(0, 25)
                .layeredVeinGenerator(generator -> generator
                        .buildLayerPattern(pattern -> pattern
                                .layer(l -> l.weight(3).mat(GTMaterials.Kyanite).size(2, 4))
                                .layer(l -> l.weight(2).mat(GTMaterials.Mica).size(1, 1))
                                .layer(l -> l.weight(2).mat(CTNHMaterials.Alumina).size(1, 1))
                                .layer(l -> l.weight(1).mat(GTMaterials.Pollucite).size(1, 1))));
    }

    public static GTOreDefinition NETHER_QUARTZ_VEIN_OW = create(CTNHCore.id("nether_quartz_vein_ow"), vein -> vein
            .weight(80)
            .clusterSize(40)
            .density(0.25F)
            .discardChanceOnAirExposure(0)
            .layer(STONE)
            .dimensions(ResourceLocation.tryParse("minecraft:overworld"))
            .heightRangeUniform(20, 60)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.NetherQuartz).size(2, 4))
                            .layer(l -> l.weight(1).mat(GTMaterials.Quartzite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Opal).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.NetherQuartz)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));
    public static GTOreDefinition NICKEL_VEIN_OW = create(CTNHCore.id("nickel_vein"), vein -> vein
            .weight(40)
            .clusterSize(40)
            .density(0.25F)
            .discardChanceOnAirExposure(0)
            .layer(WorldGenLayers.NETHERRACK)
            .dimensions(ResourceLocation.tryParse("minecraft:the_nether"))
            .heightRangeUniform(10, 60)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Garnierite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Nickel).size(1, 1))
                            .layer(l -> l.weight(2).mat(GTMaterials.Cobaltite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Pentlandite).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Nickel)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));
    public static GTOreDefinition ANCIENT_DEBRIS_VEIN = create(CTNHCore.id("ancient_debris_vein"),
            vein -> vein.weight(5).clusterSize(35)
                    .density(0.4F)
                    .discardChanceOnAirExposure(0)
                    .layer(WorldGenLayers.NETHERRACK)
                    .dimensions(ResourceLocation.tryParse("minecraft:the_nether"))
                    .heightRangeUniform(0, 25)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(CTNHMaterials.PreciousAlloy).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Sulfur).size(2, 2))
                                    .layer(l -> l.weight(1).block(() -> Blocks.ANCIENT_DEBRIS).size(1, 1))
                                    .layer(l -> l.weight(1).mat(GTMaterials.NetherQuartz).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.PreciousAlloy)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));

    public static GTOreDefinition CHROMITE_VEIN = create(CTNHCore.id("chromite_vein"), vein -> vein.weight(60)
            .clusterSize(40)
            .density(0.25F)
            .discardChanceOnAirExposure(0)
            .layer(ENDSTONE)
            .dimensions(ResourceLocation.tryParse("minecraft:the_end"))
            .heightRangeUniform(0, 40)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Chromite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.YellowLimonite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Magnetite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Lead).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Chromite)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));
    public static GTOreDefinition CRYOLITE_VEIN = create(CTNHCore.id("cryolite_vein"), vein -> vein.weight(70)
            .clusterSize(40)
            .density(0.25F)
            .discardChanceOnAirExposure(0)
            .layer(CTNHWorldgenLayers.TWILIGHT)
            .dimensions(TWILIGHT_FOREST)
            .heightRangeUniform(-30, 0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(CTNHMaterials.Cryolite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Silver).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Mica).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Lead).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.Cryolite)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));
    public static GTOreDefinition STEEL_LEAF_VEINN = create(CTNHCore.id("steel_leaf_vein"), vein -> vein.weight(20)
            .clusterSize(40)
            .density(0.35F)
            .discardChanceOnAirExposure(0)
            .layer(CTNHWorldgenLayers.TWILIGHT)
            .dimensions(TWILIGHT_FOREST)
            .biomes(BiomeTagGenerator.VALID_NAGA_COURTYARD_BIOMES)
            .heightRangeUniform(-33, 20)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(CTNHMaterials.SteelLeaf).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Apatite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Galena).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Pyrochlore).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.SteelLeaf)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));
    public static GTOreDefinition LICH_BONE_VEIN = create(CTNHCore.id("lich_bone_vein"), vein -> vein.weight(20)
            .clusterSize(45)
            .density(0.35F)
            .discardChanceOnAirExposure(0)
            .layer(CTNHWorldgenLayers.TWILIGHT)
            .dimensions(TWILIGHT_FOREST)
            .biomes(BiomeTagGenerator.VALID_LICH_TOWER_BIOMES)
            .heightRangeUniform(-33, 20)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.TricalciumPhosphate).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Coal).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Graphite).size(1, 1))
                            .layer(l -> l.weight(1).mat(CTNHMaterials.SpiritAsh).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.SpiritAsh)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));
    public static GTOreDefinition TOXIC_SWAMP_AMBER_VEIN = create(CTNHCore.id("toxic_swamp_amber_vein"),
            vein -> vein.weight(50)
                    .clusterSize(35)
                    .density(0.45F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .dimensions(TWILIGHT_FOREST)
                    .biomes(BiomeTagGenerator.VALID_LABYRINTH_BIOMES)
                    .heightRangeUniform(-33, 20)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(GTMaterials.Cinnabar).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Galena).size(1, 1))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Saltpeter).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.ToxicSwampAmber).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.ToxicSwampAmber)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));
    public static GTOreDefinition ILLUSION_IRON_VEIN = create(CTNHCore.id("illusion_iron_vein"), vein -> vein.weight(50)
            .clusterSize(25)
            .density(0.45F)
            .discardChanceOnAirExposure(0)
            .layer(CTNHWorldgenLayers.TWILIGHT)
            .dimensions(TWILIGHT_FOREST)
            .biomes(BiomeTagGenerator.VALID_KNIGHT_STRONGHOLD_BIOMES)
            .heightRangeUniform(-33, 20)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Pyrite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.VanadiumMagnetite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Tantalite).size(1, 1))
                            .layer(l -> l.weight(1).mat(CTNHMaterials.IllusionIron).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.IllusionIron)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));
    public static GTOreDefinition ARCTIC_CRYSTAL_CORE_VEIN = create(CTNHCore.id("arctic_crystal_core_vein"),
            vein -> vein.weight(50)
                    .clusterSize(25)
                    .density(0.45F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .dimensions(TWILIGHT_FOREST)
                    .biomes(BiomeTagGenerator.VALID_AURORA_PALACE_BIOMES)
                    .heightRangeUniform(-33, 20)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(GTMaterials.Electrotine).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Kyanite).size(1, 1))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Lapis).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.PolarIceCore).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.PolarIceCore)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));
    public static GTOreDefinition DRAGONFLAME_VEIN = create(CTNHCore.id("dragonflame_vein"), vein -> vein.weight(50)
            .clusterSize(35)
            .density(0.55F)
            .discardChanceOnAirExposure(0)
            .layer(CTNHWorldgenLayers.TWILIGHT)
            .dimensions(TWILIGHT_FOREST)
            .biomes(BiomeTagGenerator.VALID_HYDRA_LAIR_BIOMES)
            .heightRangeUniform(-33, 20)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Hematite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Ruby).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Pyrochlore).size(1, 1))
                            .layer(l -> l.weight(1).mat(CTNHMaterials.Dragonflame).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.Dragonflame)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));
    public static GTOreDefinition ECLIPSE_SHADOW_VEIN = create(CTNHCore.id("eclipse_shadow_vein"),
            vein -> vein.weight(50)
                    .clusterSize(45)
                    .density(0.25F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .dimensions(TWILIGHT_FOREST)
                    .biomes(BiomeTagGenerator.VALID_DARK_TOWER_BIOMES)
                    .heightRangeUniform(-33, 20)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(GTMaterials.Stibnite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Antimony).size(1, 1))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Silver).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.EclipseShadow).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.EclipseShadow)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));
    public static GTOreDefinition LIGHTNING_VEIN_VEIN = create(CTNHCore.id("thunderstrike_vein"),
            vein -> vein.weight(50)
                    .clusterSize(65)
                    .density(0.65F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .dimensions(TWILIGHT_FOREST)
                    .biomes(BiomeTagGenerator.VALID_TROLL_CAVE_BIOMES)
                    .heightRangeUniform(-33, 20)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(GTMaterials.Gold).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Diamond).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.EclipseShadow).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.LightningPattern).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.LightningPattern)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));
    public static GTOreDefinition CRYOLITE_VEIN_AETHER = create(CTNHCore.id("cryolite_vein_aether"),
            vein -> vein.weight(40)
                    .clusterSize(40)
                    .density(0.45F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .dimensions(THE_AETHER)
                    .heightRangeUniform(20, 80)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(CTNHMaterials.Cryolite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Silver).size(1, 1))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Mica).size(1, 1))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Lead).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.Cryolite)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));
    public static GTOreDefinition BAUXITE_VEIN_AETHER = create(CTNHCore.id("bauxite_vein_aether"),
            vein -> vein.weight(60)
                    .clusterSize(40)
                    .density(0.35F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .dimensions(THE_AETHER)
                    .heightRangeUniform(20, 80)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(GTMaterials.Bauxite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Ilmenite).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.Alumina).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Bauxite)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));
    public static GTOreDefinition SCHEELITE_VEIN_AETHER = create(CTNHCore.id("scheelite_vein_aether"),
            vein -> vein.weight(50)
                    .clusterSize(45)
                    .density(0.45F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .dimensions(THE_AETHER)
                    .heightRangeUniform(10, 50)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(GTMaterials.Scheelite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Tungstate).size(1, 1))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Lithium).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Scheelite)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));
    public static GTOreDefinition ZANITE_VEIN_AETHER = create(CTNHCore.id("zanite_vein_aether"), vein -> vein.weight(40)
            .clusterSize(45)
            .density(0.25F)
            .discardChanceOnAirExposure(0)
            .layer(CTNHWorldgenLayers.AETHER)
            .dimensions(THE_AETHER)
            .heightRangeUniform(10, 60)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(CTNHMaterials.Zanite).size(2, 4))
                            .layer(l -> l.weight(2).mat(CTNHMaterials.Ambrosium).size(1, 1))
                            .layer(l -> l.weight(1).mat(CTNHMaterials.Skyjade).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.Zanite)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));
    public static GTOreDefinition SHELDONITE_VEIN_MOON = create(CTNHCore.id("sheldonite_vein_moon"),
            vein -> vein.clusterSize(40)
                    .density(0.3F)
                    .weight(40)
                    .layer(CTNHWorldgenLayers.ADASTRA)
                    .heightRangeUniform(5, 50)
                    .dimensions(MOON)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(GTMaterials.Bornite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Cooperite).size(1, 1))
                                    .layer(l -> l.weight(2).mat(PlatinumLineMaterials.PlatinumOre).size(1, 1))
                                    .layer(l -> l.weight(1).mat(PlatinumLineMaterials.PalladiumOre).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(PlatinumLineMaterials.PlatinumOre)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));
    public static GTOreDefinition ZINC_VEIN = create(CTNHCore.id("zinc_vein"), vein -> vein.weight(60)
            .clusterSize(40)
            .density(0.25F)
            .discardChanceOnAirExposure(0)
            .layer(STONE)
            .dimensions(ResourceLocation.tryParse("minecraft:overworld"))
            .heightRangeUniform(20, 50)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Zinc).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Copper).size(1, 1))
                            .layer(l -> l.weight(2).mat(GTMaterials.YellowLimonite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Hematite).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Copper)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));
    public static GTOreDefinition PRECIOUS_ALLOY_VEIN = create(CTNHCore.id("precious_alloy_vein"),
            vein -> vein.weight(30)
                    .clusterSize(40)
                    .density(0.25F)
                    .discardChanceOnAirExposure(0)
                    .layer(STONE)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .heightRangeUniform(-10, 30)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(CTNHMaterials.PreciousAlloy).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Silver).size(1, 3))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Tin).size(1, 1))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Copper).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.PreciousAlloy)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));
    public static GTOreDefinition ARSENIC_VEIN = create(CTNHCore.id("arsenic_vein"), vein -> vein.weight(10)
            .clusterSize(40)
            .density(0.25F)
            .discardChanceOnAirExposure(0)
            .layer(CTNHWorldgenLayers.TWILIGHT)
            .dimensions(TWILIGHT_FOREST)
            .heightRangeUniform(-30, 0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(CTNHMaterials.Nickeline).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.ArsenicTrioxide).size(1, 3))
                            .layer(l -> l.weight(2).mat(GTMaterials.Pentlandite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Realgar).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.Nickeline)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));
    public static GTOreDefinition PHOSPHATE_VEIN = create(CTNHCore.id("phosphate_vein"), vein -> vein.weight(40)
            .clusterSize(30)
            .density(0.30F)
            .discardChanceOnAirExposure(0)
            .layer(CTNHWorldgenLayers.ADASTRA)
            .dimensions(MOON)
            .heightRangeUniform(-20, 50)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Apatite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.TricalciumPhosphate).size(1, 3))
                            .layer(l -> l.weight(2).mat(CTNHMaterials.TrisodiumPhosphate).size(1, 2))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.TrisodiumPhosphate)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));
    public static GTOreDefinition ZIRKELITE_VEIN = create(CTNHCore.id("zirkelite_vein"), vein -> vein.weight(60)
            .clusterSize(40)
            .density(0.25F)
            .discardChanceOnAirExposure(0)
            .layer(CTNHWorldgenLayers.ADASTRA)
            .dimensions(MARS, VENUS, MERCURY)
            .heightRangeUniform(30, 80)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(CTNHMaterials.Zirkelite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Thorium).size(1, 1))
                            .layer(l -> l.weight(2).mat(CTNHMaterials.Zircon).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Ilmenite).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.Zirkelite)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));
    public static GTOreDefinition RHODIUM_SULFUR_CRYSTAL_VEIN = create(CTNHCore.id("rhodium_sulfur_crystal_vein"),
            vein -> vein.weight(40)
                    .clusterSize(40)
                    .density(0.25F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.ADASTRA)
                    .dimensions(VENUS)
                    .heightRangeUniform(30, 80)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(CTNHMaterials.RhodiumSulfurCrystal).size(1, 1))
                                    .layer(l -> l.weight(2).mat(CTNHMaterials.PalladiumSulfide).size(1, 1))
                                    .layer(l -> l.weight(2).mat(CTNHMaterials.Cerite).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.GadoliniteSm).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.RhodiumSulfurCrystal)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));
    public static GTOreDefinition RUTHENIUM_AMALGAM_VEIN = create(CTNHCore.id("ruthenium_amalgam_vein"),
            vein -> vein.weight(50)
                    .clusterSize(40)
                    .density(0.25F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.ADASTRA)
                    .dimensions(MERCURY)
                    .heightRangeUniform(30, 80)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(CTNHMaterials.RutheniumAmalgam).size(1, 1))
                                    .layer(l -> l.weight(2).mat(CTNHMaterials.Smithsonite).size(1, 1))
                                    .layer(l -> l.weight(2).mat(CTNHMaterials.Tarkianite).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.SolarFlareBlackDiamond).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.RutheniumAmalgam)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));
    public static GTOreDefinition EUROPIUM_VEIN = create(CTNHCore.id("europium_vein"), vein -> vein.weight(30)
            .clusterSize(40)
            .density(0.25F)
            .discardChanceOnAirExposure(0)
            .layer(CTNHWorldgenLayers.ADASTRA)
            .dimensions(GLACIO)
            .heightRangeUniform(30, 80)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(1).mat(CTNHMaterials.Germanite).size(1, 1))
                            .layer(l -> l.weight(1).mat(CTNHMaterials.Roquesite).size(1, 1))
                            .layer(l -> l.weight(1).mat(CTNHMaterials.Yttrofluorite).size(1, 1))
                            .layer(l -> l.weight(1).mat(CTNHMaterials.EuropiumFluorite).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.EuropiumFluorite)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));
    public static GTOreDefinition COMBUSTIBLE_ICE_VEIN_AETHER = create(CTNHCore.id("combustible_ice_vein_aether"),
            vein -> vein.weight(80)
                    .clusterSize(30)
                    .density(0.55F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .dimensions(THE_AETHER)
                    .heightRangeUniform(20, 80)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(CTNHMaterials.CombustibleIce).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Coal).size(1, 1))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Opal).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.CombustibleIce).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.CombustibleIce)
                            .placement(ABOVE)
                            .density(0.2F)
                            .radius(5)));
    public static GTOreDefinition MANA_FUSED_VEIN = create(CTNHCore.id("mana_fused_vein"),
            vein -> vein.weight(80)
                    .clusterSize(40)
                    .density(0.25F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.ALFHEIM)
                    .dimensions(ALFHEIM)
                    .heightRangeUniform(20, 40)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(CMMaterials.Fused_Mana).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Gold).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Gold)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));
    public static final GTOreDefinition KAOLINITE_VEIN = create(
            CTNHCore.id("kaolinite_vein"),
            vein -> {
                vein.clusterSize(24).weight(60).layer(WorldGenLayers.STONE).density(0.4f)
                        .dimensions(BuiltinDimensionTypes.OVERWORLD.location()).heightRangeUniform(30, 70)
                        .discardChanceOnAirExposure(0f).layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(Kaolinite).size(1, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Zeolite).size(1, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.FullersEarth).size(1, 3))
                                        .layer(l -> l.weight(1).mat(GTMaterials.GlauconiteSand).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition WOLLASTONITE_VEIN = create(
            CTNHCore.id("wollastonite_vein"),
            vein -> {
                vein.clusterSize(36).weight(40).layer(WorldGenLayers.STONE).density(0.4f)
                        .dimensions(BuiltinDimensionTypes.OVERWORLD.location()).heightRangeUniform(120, 200)
                        .discardChanceOnAirExposure(0f).layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> {
                                    l.weight(2).mat(Dolomite).size(1, 3);
                                }).layer(l -> {
                                    l.weight(2).mat(Wollastonite).size(1, 3);
                                }).layer(l -> {
                                    l.weight(2).mat(GTMaterials.Trona).size(1, 3);
                                }).layer(l -> {
                                    l.weight(1).mat(GTMaterials.Andradite).size(1, 1);
                                });
                            });
                        });
            });

    public static final GTOreDefinition GALENA_VEIN_TF = create(
            CTNHCore.id("galena_vein_tf"),
            vein -> {
                vein.clusterSize(30).weight(40).layer(CTNHWorldgenLayers.TWILIGHT).density(0.25f)
                        .dimensions(TWILIGHT_FOREST)
                        .heightRangeUniform(-30, 0).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> {
                                    l.weight(3).mat(GTMaterials.Galena).size(2, 4);
                                }).layer(l -> {
                                    l.weight(3).mat(GTMaterials.Silver).size(1, 1);
                                }).layer(l -> {
                                    l.weight(1).mat(GTMaterials.Lead).size(1, 1);
                                });
                            });
                        }).surfaceIndicatorGenerator(indicator -> {
                            indicator.surfaceRock(GTMaterials.Galena)
                                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE).density(0.4f)
                                    .radius(5);
                        });
            });

    public static final GTOreDefinition DIAMOND_VEIN_TF = create(
            CTNHCore.id("diamond_vein_tf"), vein -> {
                vein.clusterSize(30).weight(40).layer(CTNHWorldgenLayers.TWILIGHT).density(0.25f)
                        .dimensions(TWILIGHT_FOREST)
                        .heightRangeUniform(-30, 0).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Graphite).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Diamond).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Coal).size(1, 1));
                            });
                        }).surfaceIndicatorGenerator(indicator -> {
                            indicator.surfaceRock(GTMaterials.Diamond)
                                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE).density(0.4f)
                                    .radius(5);
                        });
            });

    public static final GTOreDefinition APATITE_VEIN_TF = create(
            CTNHCore.id("apatite_vein_tf"), vein -> {
                vein.clusterSize(30).weight(40).layer(CTNHWorldgenLayers.TWILIGHT).density(0.25f)
                        .dimensions(TWILIGHT_FOREST)
                        .heightRangeUniform(-30, 0).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Apatite).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.TricalciumPhosphate).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Pyrochlore).size(1, 1));
                            });
                        }).surfaceIndicatorGenerator(indicator -> {
                            indicator.surfaceRock(GTMaterials.Apatite)
                                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE).density(0.4f)
                                    .radius(5);
                        });
            });

    public static final GTOreDefinition SALTS_VEIN_TF = create(
            CTNHCore.id("salts_vein_tf"), vein -> {
                vein.clusterSize(30).weight(50).layer(CTNHWorldgenLayers.TWILIGHT).density(0.2f)
                        .dimensions(TWILIGHT_FOREST)
                        .heightRangeUniform(-30, 0).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.RockSalt).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Salt).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Lepidolite).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Spodumene).size(1, 1));
                            });
                        }).surfaceIndicatorGenerator(indicator -> {
                            indicator.surfaceRock(GTMaterials.Salt)
                                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE).density(0.4f)
                                    .radius(5);
                        });
            });

    // Moon 和其他星球的矿脉
    public static final GTOreDefinition MONAZITE_VEIN_N = create(
            CTNHCore.id("monazite_vein_n"), vein -> {
                vein.clusterSize(24).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f)
                        .dimensions(MOON, VENUS, GLACIO).heightRangeUniform(20, 40)
                        .discardChanceOnAirExposure(0f).layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Bastnasite).size(2, 4))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Monazite).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Neodymium).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition BAUXITE_VEIN = create(
            CTNHCore.id("bauxite_vein"), vein -> {
                vein.clusterSize(36).weight(80).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f).dimensions(MOON)
                        .heightRangeUniform(10, 80).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Bauxite).size(1, 4))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Ilmenite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(CTNHMaterials.Alumina).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition ILMENITE_VEIN = create(
            CTNHCore.id("ilmenite_vein"), vein -> {
                vein.clusterSize(24).weight(16).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(MOON)
                        .heightRangeUniform(-70, 10).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Ilmenite).size(1, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Chromite).size(1, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Uvarovite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Perlite).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition QUARTZITE_VEIN = create(
            CTNHCore.id("quartzite_vein"), vein -> {
                vein.clusterSize(24).weight(20).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f)
                        .dimensions(MOON, MARS, VENUS).heightRangeUniform(30, 80)
                        .discardChanceOnAirExposure(0f).layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Quartzite).size(2, 4))
                                        .layer(l -> l.weight(3).mat(GTMaterials.Barite).size(2, 4))
                                        .layer(l -> l.weight(3).mat(GTMaterials.CertusQuartz).size(2, 4));
                            });
                        });
            });
    public static final GTOreDefinition MOLYBDENUM_VEIN_AD = create(
            CTNHCore.id("molybdenum_vein_ad"), vein -> {
                vein.clusterSize(25).weight(5).layer(CTNHWorldgenLayers.ADASTRA).density(0.25f)
                        .dimensions(MOON, MERCURY).heightRangeUniform(20, 50).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Wulfenite).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Molybdenite).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Molybdenum).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Powellite).size(1, 1));
                            });
                        });
            });

    // Galena AD
    public static final GTOreDefinition GALENA_VEIN_AD = create(
            CTNHCore.id("galena_vein_ad"), vein -> {
                vein.clusterSize(30).weight(40).layer(CTNHWorldgenLayers.ADASTRA).density(0.25f)
                        .dimensions(MOON, MARS, VENUS, GLACIO).heightRangeUniform(-15, 45)
                        .discardChanceOnAirExposure(0f).layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Galena).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Silver).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Lead).size(1, 1));
                            });
                        });
            });

    // Copper AD
    public static final GTOreDefinition COPPER_VEIN_AD = create(
            CTNHCore.id("copper_vein_ad"), vein -> {
                vein.clusterSize(36).weight(80).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f)
                        .dimensions(MOON, MERCURY).heightRangeUniform(-40, 15).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Chalcopyrite).size(2, 3))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Iron).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Pyrite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Copper).size(1, 2));
                            });
                        });
            });

    // Cassiterite AD
    public static final GTOreDefinition CASSITERITE_VEIN_AD = create(
            CTNHCore.id("cassiterite_vein_ad"), vein -> {
                vein.clusterSize(36).weight(50).layer(CTNHWorldgenLayers.ADASTRA).density(0.4f)
                        .dimensions(MOON, VENUS).heightRangeUniform(10, 80).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Tin).size(2, 3))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Cassiterite).size(1, 2));
                            });
                        });
            });

    // Desh AD
    public static final GTOreDefinition DESH_VEIN_AD = create(
            CTNHCore.id("desh_vein_ad"), vein -> {
                vein.clusterSize(24).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f).dimensions(MOON)
                        .heightRangeUniform(5, 40).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(AdastraMaterials.Desh).size(2, 3))
                                        .layer(l -> l.weight(1).mat(ArcaneCrystal).size(1, 2));
                            });
                        });
            });

    // Ostrum AD (Mars)
    public static final GTOreDefinition OSTRUM_VEIN_AD = create(
            CTNHCore.id("ostrum_vein_ad"), vein -> {
                vein.clusterSize(24).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f).dimensions(MARS)
                        .heightRangeUniform(5, 40).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(AdastraMaterials.Ostrum).size(2, 3))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Scheelite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Tungstate).size(1, 1));
                            });
                        });
            });

    // Arsenic AD
    public static final GTOreDefinition ARSENIC_VEIN_AD = create(
            CTNHCore.id("arsenic_vein_ad"), vein -> {
                vein.clusterSize(32).weight(60).layer(CTNHWorldgenLayers.ADASTRA).density(0.4f).dimensions(MARS)
                        .heightRangeUniform(40, 60).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(1).mat(GTMaterials.Arsenic).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Bismuth).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Antimony).size(1, 2));
                            });
                        });
            });

    // Pitchblende AD
    public static final GTOreDefinition PITCHBLENDE_VEIN_AD = create(
            CTNHCore.id("pitchblende_vein_ad"), vein -> {
                vein.clusterSize(24).weight(40).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f)
                        .dimensions(MARS, VENUS).heightRangeUniform(20, 60).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Pitchblende).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Uraninite).size(1, 2));
                            });
                        });
            });

    // Tuff Uraninite AD
    public static final GTOreDefinition TUFF_URANINITE_VEIN_AD = create(
            CTNHCore.id("tuff_uraninite_vein_ad"), vein -> {
                vein.clusterSize(24).weight(20).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f)
                        .dimensions(MARS, MERCURY).heightRangeUniform(20, 30).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Uraninite).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Pitchblende).size(1, 2));
                            });
                        });
            });

    // Scheelite AD
    public static final GTOreDefinition SCHEELITE_VEIN_AD = create(
            CTNHCore.id("scheelite_vein_ad"), vein -> {
                vein.clusterSize(24).weight(16).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f)
                        .dimensions(MARS, GLACIO).heightRangeUniform(20, 60).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Scheelite).size(2, 4))
                                        .layer(l -> l.weight(3).mat(GTMaterials.Tungstate).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Lithium).size(1, 1));
                            });
                        });
            });

    // Sulfur AD
    public static final GTOreDefinition SULFUR_VEIN_AD = create(
            CTNHCore.id("sulfur_vein_ad"), vein -> {
                vein.clusterSize(30).weight(100).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f)
                        .dimensions(MARS, VENUS).heightRangeUniform(10, 30).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Sulfur).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Pyrite).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Sphalerite).size(1, 1));
                            });
                        });
            });

    // Redstone AD
    public static final GTOreDefinition REDSTONE_VEIN_AD = create(
            CTNHCore.id("redstone_vein_ad"), vein -> {
                vein.clusterSize(30).weight(60).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f)
                        .dimensions(MARS, VENUS).heightRangeUniform(5, 40).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Redstone).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Ruby).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Cinnabar).size(1, 1));
                            });
                        });
            });

    // Nickel AD
    public static final GTOreDefinition NICKEL_VEIN_AD = create(
            CTNHCore.id("nickel_vein_ad"), vein -> {
                vein.clusterSize(30).weight(40).layer(CTNHWorldgenLayers.ADASTRA).density(0.25f)
                        .dimensions(MARS, VENUS, GLACIO).heightRangeUniform(-10, 60)
                        .discardChanceOnAirExposure(0f).layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Garnierite).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Nickel).size(1, 1))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Cobaltite).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Pentlandite).size(1, 1));
                            });
                        });
            });

    // Magnetite AD
    public static final GTOreDefinition MAGNETITE_VEIN_AD = create(
            CTNHCore.id("magnetite_vein_ad"), vein -> {
                vein.clusterSize(35).weight(80).layer(CTNHWorldgenLayers.ADASTRA).density(0.15f)
                        .dimensions(MARS, MERCURY, GLACIO).heightRangeUniform(10, 60)
                        .discardChanceOnAirExposure(0f).layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Magnetite).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.VanadiumMagnetite).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Gold).size(1, 1));
                            });
                        });
            });

    // Iron AD
    public static final GTOreDefinition IRON_VEIN_AD = create(
            CTNHCore.id("iron_vein_ad"), vein -> {
                vein.clusterSize(36).weight(120).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f)
                        .dimensions(MARS, MERCURY).heightRangeUniform(-10, 60).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(5).mat(GTMaterials.Goethite).size(1, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.YellowLimonite).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Hematite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Malachite).size(1, 2));
                            });
                        });
            });

    // Beryllium AD
    public static final GTOreDefinition BERYLLIUM_VEIN_AD = create(
            CTNHCore.id("beryllium_vein_ad"), vein -> {
                vein.clusterSize(24).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f)
                        .dimensions(MARS, MERCURY, VENUS).heightRangeUniform(5, 30)
                        .discardChanceOnAirExposure(0f).layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Beryllium).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Emerald).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Thorium).size(1, 1));
                            });
                        });
            });

    // Tetrahedrite AD
    public static final GTOreDefinition TETRAHEDRITE_VEIN_AD = create(
            CTNHCore.id("tetrahedrite_vein_ad"), vein -> {
                vein.clusterSize(36).weight(70).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f)
                        .dimensions(MARS, VENUS).heightRangeUniform(80, 120).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(4).mat(GTMaterials.Tetrahedrite).size(2, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Copper).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Stibnite).size(1, 1));
                            });
                        });
            });

    // Salts AD
    public static final GTOreDefinition SALTS_VEIN_AD = create(
            CTNHCore.id("salts_vein_ad"), vein -> {
                vein.clusterSize(30).weight(50).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(MARS)
                        .heightRangeUniform(30, 70).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.RockSalt).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Salt).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Lepidolite).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Spodumene).size(1, 1));
                            });
                        });
            });

    // Naquadah AD Mars
    public static final GTOreDefinition NAQUADAH_VEIN_AD_MARS = create(
            CTNHCore.id("naquadah_vein_ad_mars"), vein -> {
                vein.clusterSize(48).weight(5).layer(CTNHWorldgenLayers.ADASTRA).density(0.4f).dimensions(MARS)
                        .heightRangeUniform(10, 90).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(NaquadahMaterials.NaquadahOxideMixture).size(2, 4))
                                        .layer(l -> l.weight(1).mat(NaquadahMaterials.EnrichedNaquadahOxideMixture)
                                                .size(1,
                                                        2));
                            });
                        });
            });
    // Mercury 矿脉
    public static final GTOreDefinition CHROMIUM_VEIN_AD = create(
            CTNHCore.id("chromium_vein_ad"), vein -> {
                vein.clusterSize(24).weight(5).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(MERCURY)
                        .heightRangeUniform(-15, 15).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(1).mat(GTMaterials.Chromite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Tungsten).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Molybdenum).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Manganese).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition URANIUM238_VEIN_AD = create(
            CTNHCore.id("uranium238_vein_ad"), vein -> {
                vein.clusterSize(12).weight(5).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(MERCURY)
                        .heightRangeUniform(65, 120).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Uranium238).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Plutonium239).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Thorium).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition MAGNESITE_VEIN_AD = create(
            CTNHCore.id("magnesite_vein_ad"), vein -> {
                vein.clusterSize(36).weight(55).layer(CTNHWorldgenLayers.ADASTRA).density(0.4f).dimensions(MERCURY)
                        .heightRangeUniform(35, 65).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Magnesite).size(1, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Hematite).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Sulfur).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Opal).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition PLATINUM_VEIN_AD = create(
            CTNHCore.id("platinum_vein_ad"), vein -> {
                vein.clusterSize(24).weight(10).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(MERCURY)
                        .heightRangeUniform(-5, 25).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(PlatinumLineMaterials.PlatinumOre).size(1, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Chromite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Cooperite).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition LAPIS_VEIN_AD = create(
            CTNHCore.id("lapis_vein_ad"), vein -> {
                vein.clusterSize(24).weight(40).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f).dimensions(MERCURY)
                        .heightRangeUniform(20, 50).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Lazurite).size(1, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Sodalite).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Lapis).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Calcite).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition OLIVINE_VEIN_AD = create(
            CTNHCore.id("olivine_vein_ad"), vein -> {
                vein.clusterSize(30).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.25f).dimensions(MERCURY)
                        .heightRangeUniform(-20, 10).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Bentonite).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Magnetite).size(1, 1))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Olivine).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.GlauconiteSand).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition MANGANESE_VEIN_AD = create(
            CTNHCore.id("manganese_vein_ad"), vein -> {
                vein.clusterSize(24).weight(20).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f)
                        .dimensions(MERCURY, GLACIO).heightRangeUniform(-30, 0).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Grossular).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Spessartine).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Pyrolusite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Tantalite).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition LUBRICANT_VEIN_AD = create(
            CTNHCore.id("lubricant_vein_ad"), vein -> {
                vein.clusterSize(25).weight(40).layer(CTNHWorldgenLayers.ADASTRA).density(0.25f).dimensions(MERCURY)
                        .heightRangeUniform(0, 50).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Soapstone).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Talc).size(1, 1))
                                        .layer(l -> l.weight(2).mat(GTMaterials.GlauconiteSand).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Pentlandite).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition SALTPETER_VEIN_AD = create(
            CTNHCore.id("saltpeter_vein_ad"), vein -> {
                vein.clusterSize(30).weight(40).layer(CTNHWorldgenLayers.ADASTRA).density(0.25f).dimensions(MERCURY)
                        .heightRangeUniform(5, 45).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Saltpeter).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Diatomite).size(1, 1))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Electrotine).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Alunite).size(1, 1));
                            });
                        });
            });

    // Venus 矿脉
    public static final GTOreDefinition CALORITE_VEIN_AD = create(
            CTNHCore.id("calorite_vein_ad"), vein -> {
                vein.clusterSize(24).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f).dimensions(VENUS)
                        .heightRangeUniform(5, 40).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(AdastraMaterials.Calorite).size(2, 3))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Amethyst).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition RUTILE_VEIN_AD = create(
            CTNHCore.id("rutile_vein_ad"), vein -> {
                vein.clusterSize(18).weight(8).layer(CTNHWorldgenLayers.ADASTRA).density(0.4f).dimensions(VENUS)
                        .heightRangeUniform(-15, 20).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Rutile).size(1, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Titanium).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Bauxite).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition IRIDIUM_VEIN_AD = create(
            CTNHCore.id("iridium_vein_ad"), vein -> {
                vein.clusterSize(24).weight(10).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(VENUS)
                        .heightRangeUniform(-5, 40).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Nickel).size(2, 3))
                                        .layer(l -> l.weight(1).mat(CTNHMaterials.MeteoricTroilite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(PlatinumLineMaterials.PalladiumOre).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition PYROLUSITE_VEIN_AD = create(
            CTNHCore.id("pyrolusite_vein_ad"), vein -> {
                vein.clusterSize(24).weight(10).layer(CTNHWorldgenLayers.ADASTRA).density(0.4f).dimensions(VENUS)
                        .heightRangeUniform(0, 30).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Pyrolusite).size(2, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Apatite).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Tantalite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Pyrochlore).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition NAQUADAH_VEIN_AD = create(
            CTNHCore.id("naquadah_vein_ad"), vein -> {
                vein.clusterSize(48).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.4f).dimensions(VENUS)
                        .heightRangeUniform(10, 90).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(NaquadahMaterials.NaquadahOxideMixture).size(2, 3))
                                        .layer(l -> l.weight(1).mat(NaquadahMaterials.EnrichedNaquadahOxideMixture)
                                                .size(1,
                                                        2));
                            });
                        });
            });

    // Glacio 矿脉
    public static final GTOreDefinition OSMIUM_VEIN_AD = create(
            CTNHCore.id("osmium_vein_ad"), vein -> {
                vein.clusterSize(24).weight(10).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(GLACIO)
                        .heightRangeUniform(-5, 30).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Nickel).size(2, 3))
                                        .layer(l -> l.weight(2).mat(CTNHMaterials.OsmiumIronSpinel).size(1, 2))
                                        .layer(l -> l.weight(1).mat(CTNHMaterials.MeteoricTroilite).size(1, 1));
                            });
                        });
            });

    // public static final GTOreDefinition NEUTRONIUM_VEIN_AD = create(
    // CTNHCore.id("neutronium_vein_ad"), vein -> {
    // vein.clusterSize(24).weight(10).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(GLACIO)
    // .heightRangeUniform(-50, -10).discardChanceOnAirExposure(0f)
    // .layeredVeinGenerator(generator -> {
    // generator.buildLayerPattern(pattern -> {
    // pattern.layer(l -> l.weight(2).mat(GTMaterials.Neutronium).size(1, 2))
    // .layer(l -> l.weight(1).mat(InfinityCatalyst).size(1, 2))
    // .layer(l -> l.weight(1).mat(GTMaterials.Naquadria).size(1, 2))
    // .layer(l -> l.weight(1).mat(GTMaterials.Titanium).size(1, 2));
    // });
    // });
    // }
    // );
    //
    public static final GTOreDefinition NIOBIUM_VEIN_AD = create(
            CTNHCore.id("niobium_vein_ad"), vein -> {
                vein.clusterSize(36).weight(60).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(GLACIO)
                        .heightRangeUniform(-50, -10).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Niobium).size(1, 2))
                                        .layer(l -> l.weight(2).mat(CTNHMaterials.MeteoricTroilite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Gallium).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition THORIUM_VEIN_AD = create(
            CTNHCore.id("thorium_vein_ad"), vein -> {
                vein.clusterSize(36).weight(60).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(GLACIO)
                        .heightRangeUniform(-10, 30).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Thorium).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Uranium235).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Plutonium241).size(1, 2));
                            });
                        });
            });
}
