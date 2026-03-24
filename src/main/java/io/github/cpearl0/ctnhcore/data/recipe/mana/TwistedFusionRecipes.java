package io.github.cpearl0.ctnhcore.data.recipe.mana;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.recipe.NeutronActivatorCondition;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;

import com.moguang.ctnhmana.common.recipe.builder.ElfPlateRecipeBuilder;
import com.moguang.ctnhmana.common.recipe.builder.bloodmagic.BloodAltarRecipeBuilder;
import com.moguang.ctnhmana.common.recipe.builder.botania.RuneRitualRecipeBuilder;
import wayoftime.bloodmagic.common.fluid.BloodMagicFluids;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.INDUSTRIAL_TNT;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.moguang.ctnhmana.registry.CMBlocks.*;
import static com.moguang.ctnhmana.registry.CMItems.*;
import static com.moguang.ctnhmana.registry.CMMaterials.*;
import static com.moguang.ctnhmana.registry.CMRecipeTypes.*;
import static com.moguang.ctnhmana.registry.multiblock.misc.*;
import static com.moguang.ctnhmana.registry.multiblock.misc.TWISTED_FUSION_MK3;
import static com.wintercogs.ae2omnicells.common.init.OCItems.*;
import static earth.terrarium.adastra.common.registry.ModItems.*;
import static io.github.cpearl0.ctnhcore.data.materials.AdastraMaterials.*;
import static io.github.cpearl0.ctnhcore.data.materials.PlatinumLineMaterials.PlatinumGroupResidue;
import static io.github.cpearl0.ctnhcore.registry.CTNHItems.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA.COMPRESSED_FUSION_REACTOR;
import static mythicbotany.register.ModBlocks.mjoellnir;
import static mythicbotany.register.ModItems.*;
import static net.minecraft.world.item.Items.*;
import static tech.luckyblock.mcmod.ctnhenergy.registry.CEItems.*;
import static vazkii.botania.common.item.BotaniaItems.*;
import static vazkii.botania.common.item.BotaniaItems.overgrowthSeed;

public class TwistedFusionRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 扭曲聚变
        TWISTED_FUSION.recipeBuilder(CTNHCore.id("twist_power_mana"))
                .inputFluids(Mana_Radiation_Mixture.getFluid(64))
                .inputFluids(Super_Plus_Mana.getFluid(32))
                .outputFluids(Twist_Power_Mana.getFluid(100))
                .EUt(491520 / 16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("caesium_argon"))
                .inputFluids(Arsenic.getFluid(144))
                .inputFluids(Chromium.getFluid(144))
                .outputFluids(Caesium.getFluid(144))
                .outputFluids(Argon.getFluid(1000))
                .EUt(491520 / 16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("carbon_iridium"))
                .inputFluids(Iodine.getFluid(144))
                .inputFluids(Chromium.getFluid(144))
                .outputFluids(Carbon.getFluid(144))
                .outputFluids(Iridium.getFluid(144))
                .EUt(491520 / 16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("fluorine_trinium"))
                .inputFluids(Potassium.getFluid(144))
                .inputFluids(Iron.getFluid(144))
                .outputFluids(Fluorine.getFluid(1000))
                .outputFluids(Trinium.getFluid(144))
                .EUt(1966000 / 16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("carbon_krypton"))
                .inputFluids(Potassium.getFluid(144))
                .inputFluids(Chromium.getFluid(144))
                .outputFluids(Carbon.getFluid(144))
                .outputFluids(Krypton.getFluid(1000))
                .EUt(491520 / 16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("sulfur_indium"))
                .inputFluids(Iodine.getFluid(144))
                .inputFluids(Tin.getFluid(144))
                .outputFluids(Sulfur.getFluid(144))
                .outputFluids(Indium.getFluid(144))
                .EUt(491520 / 16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("potassium_duranium"))
                .inputFluids(Deuterium.getFluid(1000))
                .inputFluids(Krypton.getFluid(1000))
                .outputFluids(Potassium.getFluid(144))
                .outputFluids(Duranium.getFluid(144))
                .EUt(1966000 / 16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("neon_fluorine"))
                .inputFluids(Iron.getFluid(144))
                .inputFluids(Nitrogen.getFluid(1000))
                .outputFluids(Neon.getFluid(1000))
                .outputFluids(Fluorine.getFluid(1000))
                .EUt(1966000 / 16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("tritanium_deuterium"))
                .inputFluids(Trinium.getFluid(1000))
                .inputFluids(Duranium.getFluid(144))
                .outputFluids(Tritanium.getFluid(144))
                .outputFluids(Deuterium.getFluid(1000))
                .EUt(7864320 / 16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("neutronium_phosphorus"))
                .inputFluids(Nitrogen.getFluid(1000))
                .inputFluids(Platinum.getFluid(144))
                .outputFluids(Neutronium.getFluid(144))
                .outputFluids(Phosphorus.getFluid(144))
                .EUt(7864320 / 16)
                .duration(16)
                .save(provider);
        // 蕴魔处理
        MANA_TRANSFORMER_RECIPES.recipeBuilder("fused_mixed_mana")// 分选蕴魔粉
                .inputItems(ChemicalHelper.get(dust, Fused_Mana))
                .notConsumable(ELF_CATALYST)
                .outputItems(ChemicalHelper.get(dust, Fused_Mixed_Mana), 16)
                .outputItems(ChemicalHelper.get(dust, ManaSteel), 4)
                .outputItems(ChemicalHelper.get(dust, Elementium), 4)
                .outputItems(dragonstone)
                .outputFluids(Mana.getFluid(1000))
                .save(provider);
        GTRecipeTypes.CHEMICAL_BATH_RECIPES.recipeBuilder("fused_lp_mixed_mana")// 源质提纯恶魔粉
                .inputItems(ChemicalHelper.get(dust, Fused_Mixed_Mana), 4)
                .inputFluids(FluidIngredient.of(BloodMagicFluids.LIFE_ESSENCE_FLUID.get(), 16000))
                .outputItems(ChemicalHelper.get(dust, Fused_Lp_Mixed_Mana), 3)
                .EUt(1330 / 2)
                .duration(200)
                .save(provider);
        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder("uns")// 不稳定超富集魔力粉
                .inputItems(ChemicalHelper.get(dust, Plus_Mana))
                .notConsumable(TERRA_CATALYST)
                .inputFluids(Mana.getFluid(10000))
                .outputItems(ChemicalHelper.get(dust, Unstable_Plus_Mana), 4)
                .EUt(1000)
                .duration(10)
                .save(provider);
        ElfPlateRecipeBuilder.builder("infused_plus_mana")// 不稳定注魔临界魔力粉
                .input(ChemicalHelper.get(dust, Unstable_Plus_Mana).getItem())
                .input(asgardRune)
                .input(runeMana)
                .output(ChemicalHelper.get(dust, Infused_Plus_Mana).getItem())
                .mana(50000)
                .save(provider);
        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder("unknown_super_mana")// 混沌态临界魔力粉
                .inputItems(ChemicalHelper.get(dust, Super_Plus_Mana), 2)
                .inputFluids(Mana.getFluid(1000))
                .inputFluids(FluidIngredient.of(BloodMagicFluids.LIFE_ESSENCE_FLUID.get(), 1000))
                .inputFluids(Krypton.getFluid(50))
                .inputFluids(Naquadah.getFluid(300))
                .inputFluids(Xenon.getFluid(150))
                .outputItems(ChemicalHelper.get(dust, Unknown_Super_Mana))
                .EUt(9999)
                .duration(100)
                .save(provider);
        NEUTRON_ACTIVATOR_RECIPES.recipeBuilder("twist_power_mana1")// 极端扭曲放射态临界魔力粉
                .inputItems(dust, Twist_Mana, 10)
                .inputFluids(Zenith_essence.getFluid(800))
                .outputItems(dust, Twist_Power_Mana, 10)
                .addCondition(new NeutronActivatorCondition(800, 1000))
                .duration(50) // 4秒
                .save(provider);
        ACCELERATOR_UP.recipeBuilder("twist_power_mana2")
                .addData("type", "nu")
                .addData("speed", 5000)
                .inputItems(dust, Twist_Mana, 20)
                .inputFluids(Zenith_essence.getFluid(1500))
                .outputItems(dust, Twist_Power_Mana, 20)
                .EUt(32678)
                .duration(10)
                .save(provider);
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("twist_power_mana"))// 极端扭曲放射态临界魔力粉
                .inputItems(dust, Twist_Power_Mana, 32)
                .inputItems(foil, TungstenCarbide, 16)
                .inputItems(plateDouble, Naquadah, 2)
                .outputItems(ENCAPSULATED_TWIST_MANA)
                .duration(1200)
                .EUt(14666400 / 1200)
                .save(provider);
        GTRecipeTypes.IMPLOSION_RECIPES.recipeBuilder("ultra_mana_dust")// 究极魔力粉
                .inputItems(ENCAPSULATED_TWIST_MANA)
                .inputItems(INDUSTRIAL_TNT.asItem(), 8)
                .outputItems(dust, Ultra_Mana, 24)
                .outputItems(dust, Remain_Mana, 8)
                .EUt(30)
                .duration(10000)
                .save(provider);
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("remain_mana")// 魔力残留物粉离心
                .inputItems(dust, Remain_Mana, 2)
                .chancedOutput(ChemicalHelper.get(dust, Fused_Mana), (int) 6000f, 1)
                .chancedOutput(ChemicalHelper.get(dust, Plus_Mana), (int) 3000f, 1)
                .chancedOutput(ChemicalHelper.get(dustSmall, Super_Plus_Mana), (int) 3000f, 1)
                .inputFluids(Mana.getFluid(750))
                .EUt(960)
                .duration(100)
                .save(provider);
        // 增殖符文+类星体符文
        GREENHOUSE_RECIPES.recipeBuilder("proliferation_rune1")// 增殖符文增殖
                .inputItems(PROLIFERATION_RUNE)
                .inputItems(FERTILIZER, 16)
                .inputItems(RADIOACTIVE_WASTE, 128)
                .inputFluids(Water.getFluid(10000))
                .circuitMeta(3)
                .outputItems(PROLIFERATION_RUNE, 3)
                .chancedOutput(PROLIFERATION_RUNE.asStack(), (int) 9900f, 2)
                .chancedOutput(PROLIFERATION_RUNE.asStack(), (int) 7500f, 1)
                .EUt(24444)
                .duration(1000)
                .save(provider);
        GREENHOUSE_RECIPES.recipeBuilder("proliferation_rune2")// 增殖符文增殖
                .inputItems(BROKEN_RUNE.asStack())
                .inputItems(overgrowthSeed)
                .inputItems(FERTILIZER, 64)
                .inputItems(RADIOACTIVE_WASTE, 64)
                .inputFluids(Water.getFluid(10000))
                .outputItems(PROLIFERATION_RUNE.asItem(), 3)
                .EUt(24444)
                .duration(1000)
                .save(provider);
        ElfPlateRecipeBuilder.builder("twist_reactor_inf")
                .input(TWISTED_FUSION_MK1.getItem())
                .input(TWISTED_FUSION_MK2.getItem())
                .input(TWISTED_FUSION_MK3.getItem())
                .input(COMPRESSED_FUSION_REACTOR[LuV].getItem())
                .input(COMPRESSED_FUSION_REACTOR[ZPM].getItem())
                .input(COMPRESSED_FUSION_REACTOR[UV].getItem())
                .input(TERMINAL_TWISTED_COIL.asItem())
                .input(CRYSTAL_CATALYST.asItem())
                .input(QUASAR_RUNE.asItem())
                .output(TWISTED_FUSION_MKINFINITY.asStack())
                .mana(Integer.MAX_VALUE)
                .save(provider);
        RuneRitualRecipeBuilder.builder("quasar_rune")// 类星体符文
                .center(mjoellnir.asItem())
                .rune2(HORIZEN_RUNE.asItem(), 2, -2, true)
                .rune2(STARLIGHT_RUNE.asItem(), -3, 3, true)
                .rune2(TWIST_RUNE.asItem(), -4, 4, true)
                .rune2(PROLIFERATION_RUNE.asItem(), -5, 5, true)
                .rune(OMNI_CELL_COMPONENT_1M.get().asItem(), 0, 2)
                .rune(OMNI_CELL_COMPONENT_1M.get().asItem(), 1, 2)
                .rune(OMNI_CELL_COMPONENT_1M.get().asItem(), -1, 2)
                .rune(COMPLEX_OMNI_CELL_COMPONENT_1M.get().asItem(), 0, -2)
                .rune(COMPLEX_OMNI_CELL_COMPONENT_1M.get().asItem(), 1, -2)
                .rune(COMPLEX_OMNI_CELL_COMPONENT_1M.get().asItem(), -1, -2)
                .rune(QUANTUM_OMNI_CELL_COMPONENT_1K.get().asItem(), 2, 0)
                .rune(QUANTUM_OMNI_CELL_COMPONENT_1K.get().asItem(), 2, 1)
                .rune(QUANTUM_OMNI_CELL_COMPONENT_1K.get().asItem(), 2, -1)
                .rune(EU_CELL[ZPM].get().asItem(), -2, 0)
                .rune(EU_CELL[ZPM].get().asItem(), -2, 1)
                .rune(EU_CELL[ZPM].get().asItem(), -2, -1)
                .input(midgardRune)
                .input(niflheimRune)
                .input(Zenith_essence.getBucket())
                .input(alfheimRune)
                .input(helheimRune)
                .input(vanaheimRune)
                .input(joetunheimRune)
                .input(muspelheimRune)
                .input(nidavellirRune)
                .input(asgardRune)
                .output(QUASAR_RUNE.asItem())
                .mana(5000000)
                .save(provider);
        VanillaRecipeHelper.addShapedRecipe(// 精灵催化剂
                provider, "elf_catalyst",
                ELF_CATALYST.asStack(1),
                "AAA",
                "ABA",
                "AAA",
                'A', ChemicalHelper.get(rawOreBlock, Fused_Mana).getItem().asItem(),
                'B', ChemicalHelper.get(block, AlfSteel).getItem().asItem());
        METEOR_CAPTURER_RECIPES.recipeBuilder("desh")// 戴斯
                .chancedInput(TIER_1_ROCKET.get().getDefaultInstance(), (int) 5f, 1)
                .inputFluids(FluidIngredient.of(BloodMagicFluids.LIFE_ESSENCE_FLUID.get(), 1000 * 128))
                .outputItems(ChemicalHelper.get(ore, Desh), 512)
                .duration(400)
                .EUt(2048)
                .save(provider);
        METEOR_CAPTURER_RECIPES.recipeBuilder("ostrum")// 紫金
                .chancedInput(TIER_2_ROCKET.get().getDefaultInstance(), (int) 5f, 1)
                .inputFluids(FluidIngredient.of(BloodMagicFluids.LIFE_ESSENCE_FLUID.get(), 1000 * 256))
                .outputItems(ChemicalHelper.get(ore, Ostrum), 256)
                .duration(400)
                .EUt(8196)
                .save(provider);
        METEOR_CAPTURER_RECIPES.recipeBuilder("calorite")// 耐热金属
                .chancedInput(TIER_3_ROCKET.get().getDefaultInstance(), (int) 5f, 1)
                .inputFluids(FluidIngredient.of(BloodMagicFluids.LIFE_ESSENCE_FLUID.get(), 1000 * 512))
                .outputItems(ChemicalHelper.get(ore, Calorite), 128)
                .duration(400)
                .EUt(8196 * 4)
                .save(provider);
        METEOR_CAPTURER_RECIPES.recipeBuilder("neutronium")// 中子素
                .chancedInput(TIER_4_ROCKET.get().getDefaultInstance(), (int) 5f, 1)
                .inputFluids(FluidIngredient.of(BloodMagicFluids.LIFE_ESSENCE_FLUID.get(), 1000 * 1024))
                .outputItems(ChemicalHelper.get(ore, Neutronium), 8)
                .duration(400)
                .EUt(528000 * 4)
                .save(provider);
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("twist")
                .inputItems(ChemicalHelper.get(plate, Ultra_Mana), 2)
                .inputItems(GTBlocks.FUSION_CASING, 2)
                .inputItems(ChemicalHelper.get(plateDouble, AlfSteel), 2)
                .outputItems(TWISTED_FUSION_CASING)
                .duration(400)
                .EUt(GTValues.VA[LuV])
                .save(provider);
        BloodAltarRecipeBuilder.builder("bloodygold_dust_2")// 血铂B
                .input(ChemicalHelper.get(TagPrefix.dust, PlatinumGroupResidue, 1))
                .output(ChemicalHelper.get(TagPrefix.dust, HEMOPLATINUM, 1))
                .circuitMeta(1)
                .syphon(15000)
                .minimumTier(4)
                .consumeRate(100)
                .drainRate(100)
                .save(provider);
    }
}
