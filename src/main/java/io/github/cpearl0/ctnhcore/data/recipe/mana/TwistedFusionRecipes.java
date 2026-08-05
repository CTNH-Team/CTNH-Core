package io.github.cpearl0.ctnhcore.data.recipe.mana;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.recipe.NeutronActivatorCondition;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.recipe.ingredient.fluid.FluidIngredient;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;

import com.moguang.ctnhmana.data.recipe.builder.bloodmagic.BloodAltarRecipeBuilder;
import com.moguang.ctnhmana.data.recipe.builder.botania.ElfPlateRecipeBuilder;
import com.moguang.ctnhmana.data.recipe.builder.botania.RuneRitualRecipeBuilder;
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
import static com.moguang.ctnhmana.registry.multiblock.Misc.*;
import static com.wintercogs.ae2omnicells.common.init.OCItems.*;
import static earth.terrarium.adastra.common.registry.ModItems.*;
import static io.github.cpearl0.ctnhcore.data.materials.AdastraMaterials.*;
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
        // 扭曲聚变（第三路输入为 36mB 扭曲聚合基质；其余流体输入/输出相对旧版×2）
        TWISTED_FUSION.recipeBuilder(CTNHCore.id("twist_power_mana_1"))
                .inputFluids(Mana_Radiation_Mixture.getFluid(128))
                .inputFluids(Super_Plus_Mana.getFluid(64))
                .inputFluids(Twisted_Aggregate_Matrix.getFluid(36))
                .outputFluids(Twist_Power_Mana.getFluid(200))
                .EUt(491520 / 16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("caesium_argon"))
                .inputFluids(Arsenic.getFluid(288))
                .inputFluids(Chromium.getFluid(288))
                .inputFluids(Twisted_Aggregate_Matrix.getFluid(36))
                .outputFluids(Caesium.getFluid(288))
                .outputFluids(Argon.getFluid(2000))
                .EUt(491520 / 16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("carbon_iridium"))
                .inputFluids(Iodine.getFluid(288))
                .inputFluids(Chromium.getFluid(288))
                .inputFluids(Twisted_Aggregate_Matrix.getFluid(36))
                .outputFluids(Carbon.getFluid(288))
                .outputFluids(Iridium.getFluid(288))
                .EUt(491520 / 16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("fluorine_trinium"))
                .inputFluids(Potassium.getFluid(288))
                .inputFluids(Iron.getFluid(288))
                .inputFluids(Twisted_Aggregate_Matrix.getFluid(36))
                .outputFluids(Fluorine.getFluid(2000))
                .outputFluids(Trinium.getFluid(288))
                .EUt(1966000 / 16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("carbon_krypton"))
                .inputFluids(Potassium.getFluid(288))
                .inputFluids(Chromium.getFluid(288))
                .inputFluids(Twisted_Aggregate_Matrix.getFluid(36))
                .outputFluids(Carbon.getFluid(288))
                .outputFluids(Krypton.getFluid(2000))
                .EUt(491520 / 16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("sulfur_indium"))
                .inputFluids(Iodine.getFluid(288))
                .inputFluids(Tin.getFluid(288))
                .inputFluids(Twisted_Aggregate_Matrix.getFluid(36))
                .outputFluids(Sulfur.getFluid(288))
                .outputFluids(Indium.getFluid(288))
                .EUt(491520 / 16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("potassium_duranium"))
                .inputFluids(Deuterium.getFluid(2000))
                .inputFluids(Krypton.getFluid(2000))
                .inputFluids(Twisted_Aggregate_Matrix.getFluid(36))
                .outputFluids(Potassium.getFluid(288))
                .outputFluids(Duranium.getFluid(288))
                .EUt(1966000 / 16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("neon_fluorine"))
                .inputFluids(Iron.getFluid(288))
                .inputFluids(Nitrogen.getFluid(2000))
                .inputFluids(Twisted_Aggregate_Matrix.getFluid(36))
                .outputFluids(Neon.getFluid(2000))
                .outputFluids(Fluorine.getFluid(2000))
                .EUt(1966000 / 16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("tritanium_deuterium"))
                .inputFluids(Trinium.getFluid(2000))
                .inputFluids(Duranium.getFluid(288))
                .inputFluids(Twisted_Aggregate_Matrix.getFluid(36))
                .outputFluids(Tritanium.getFluid(288))
                .outputFluids(Deuterium.getFluid(2000))
                .EUt(7864320 / 16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("neutronium_phosphorus"))
                .inputFluids(Nitrogen.getFluid(2000))
                .inputFluids(Platinum.getFluid(288))
                .inputFluids(Twisted_Aggregate_Matrix.getFluid(36))
                .outputFluids(Neutronium.getFluid(288))
                .outputFluids(Phosphorus.getFluid(288))
                .EUt(7864320 / 16)
                .duration(16)
                .save(provider);
        // 蕴魔处理

        GTRecipeTypes.CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("fused_lp_mixed_mana"))// 源质提纯恶魔粉
                .inputItems(ChemicalHelper.get(dust, Fused_Mixed_Mana), 4)
                .inputFluids(FluidIngredient.of(BloodMagicFluids.LIFE_ESSENCE_FLUID.get(), 16000))
                .outputItems(ChemicalHelper.get(dust, Fused_Lp_Mixed_Mana), 3)
                .EUt(1330 / 2)
                .duration(200)
                .save(provider);
        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("uns"))// 不稳定超富集魔力粉
                .inputItems(ChemicalHelper.get(dust, Plus_Mana))
                .notConsumable(TERRA_CATALYST)
                .inputFluids(Mana.getFluid(10000))
                .outputItems(ChemicalHelper.get(dust, Unstable_Plus_Mana), 4)
                .EUt(1000)
                .duration(10)
                .save(provider);
        ElfPlateRecipeBuilder.builder(CTNHCore.id("infused_plus_mana"))// 不稳定注魔临界魔力粉
                .input(ChemicalHelper.get(dust, Unstable_Plus_Mana).getItem())
                .input(asgardRune)
                .input(runeMana)
                .output(ChemicalHelper.get(dust, Infused_Plus_Mana).getItem())
                .mana(50000)
                .save(provider);
        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("unknown_super_mana"))// 混沌态临界魔力粉
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
        NEUTRON_ACTIVATOR_RECIPES.recipeBuilder(CTNHCore.id("twist_power_mana1"))// 极端扭曲放射态临界魔力粉
                .inputItems(dust, Twist_Mana, 10)
                .inputFluids(Zenith_essence.getFluid(800))
                .outputItems(dust, Twist_Power_Mana, 10)
                .addCondition(new NeutronActivatorCondition(800, 1000))
                .duration(50) // 4秒
                .save(provider);
        ACCELERATOR_UP.recipeBuilder(CTNHCore.id("twist_power_mana2"))
                .addData("type", "nu")
                .addData("speed", 5000)
                .inputItems(dust, Twist_Mana, 20)
                .inputFluids(Zenith_essence.getFluid(1500))
                .outputItems(dust, Twist_Power_Mana, 20)
                .EUt(32678)
                .duration(10)
                .save(provider);
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("twist_power_mana_2"))// 极端扭曲放射态临界魔力粉
                .inputItems(dust, Twist_Power_Mana, 32)
                .inputItems(foil, TungstenCarbide, 16)
                .inputItems(plateDouble, Naquadah, 2)
                .outputItems(ENCAPSULATED_TWIST_MANA.asItem())
                .duration(1200)
                .EUt(14666400 / 1200)
                .save(provider);
        GTRecipeTypes.IMPLOSION_RECIPES.recipeBuilder(CTNHCore.id("ultra_mana_dust"))// 究极魔力粉
                .inputItems(ENCAPSULATED_TWIST_MANA.asItem())
                .inputItems(INDUSTRIAL_TNT.asItem(), 8)
                .outputItems(dust, Ultra_Mana, 24)
                .outputItems(dust, Remain_Mana, 8)
                .EUt(30)
                .duration(10000)
                .save(provider);
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("remain_mana"))// 魔力残留物粉离心
                .inputItems(dust, Remain_Mana, 2)
                .chancedOutput(ChemicalHelper.get(dust, Fused_Mana), (int) 6000f, 1)
                .chancedOutput(ChemicalHelper.get(dust, Plus_Mana), (int) 3000f, 1)
                .chancedOutput(ChemicalHelper.get(dustSmall, Super_Plus_Mana), (int) 3000f, 1)
                .inputFluids(Mana.getFluid(750))
                .EUt(960)
                .duration(100)
                .save(provider);
        // 增殖符文+类星体符文
        GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("proliferation_rune1"))// 增殖符文增殖
                .inputItems(PROLIFERATION_RUNE.asItem())
                .inputItems(FERTILIZER, 16)
                .inputItems(RADIOACTIVE_WASTE, 128)
                .inputFluids(Water.getFluid(10000))
                .circuitMeta(3)
                .outputItems(PROLIFERATION_RUNE.asItem(), 3)
                .chancedOutput(PROLIFERATION_RUNE.asStack(), (int) 9900f, 2)
                .chancedOutput(PROLIFERATION_RUNE.asStack(), (int) 7500f, 1)
                .EUt(24444)
                .duration(1000)
                .save(provider);
        GREENHOUSE_RECIPES.recipeBuilder(CTNHCore.id("proliferation_rune2"))// 增殖符文增殖
                .inputItems(BROKEN_RUNE.asStack())
                .inputItems(overgrowthSeed)
                .inputItems(FERTILIZER, 64)
                .inputItems(RADIOACTIVE_WASTE, 64)
                .inputFluids(Water.getFluid(10000))
                .outputItems(PROLIFERATION_RUNE.asItem(), 3)
                .EUt(24444)
                .duration(1000)
                .save(provider);
        ElfPlateRecipeBuilder.builder(CTNHCore.id("twist_reactor_inf"))
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
        RuneRitualRecipeBuilder.builder(CTNHCore.id("quasar_rune"))// 类星体符文
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
        METEOR_CAPTURER_RECIPES.recipeBuilder(CTNHCore.id("desh"))// 戴斯
                .chancedInput(TIER_1_ROCKET.get().getDefaultInstance(), (int) 5f, 1)
                .inputFluids(FluidIngredient.of(BloodMagicFluids.LIFE_ESSENCE_FLUID.get(), 1000 * 128))
                .outputItems(ChemicalHelper.get(ore, Desh), 512)
                .duration(400)
                .EUt(2048)
                .save(provider);
        METEOR_CAPTURER_RECIPES.recipeBuilder(CTNHCore.id("ostrum"))// 紫金
                .chancedInput(TIER_2_ROCKET.get().getDefaultInstance(), (int) 5f, 1)
                .inputFluids(FluidIngredient.of(BloodMagicFluids.LIFE_ESSENCE_FLUID.get(), 1000 * 256))
                .outputItems(ChemicalHelper.get(ore, Ostrum), 256)
                .duration(400)
                .EUt(8196)
                .save(provider);
        METEOR_CAPTURER_RECIPES.recipeBuilder(CTNHCore.id("calorite"))// 耐热金属
                .chancedInput(TIER_3_ROCKET.get().getDefaultInstance(), (int) 5f, 1)
                .inputFluids(FluidIngredient.of(BloodMagicFluids.LIFE_ESSENCE_FLUID.get(), 1000 * 512))
                .outputItems(ChemicalHelper.get(ore, Calorite), 128)
                .duration(400)
                .EUt(8196 * 4)
                .save(provider);
        METEOR_CAPTURER_RECIPES.recipeBuilder(CTNHCore.id("neutronium"))// 中子素
                .chancedInput(TIER_4_ROCKET.get().getDefaultInstance(), (int) 5f, 1)
                .inputFluids(FluidIngredient.of(BloodMagicFluids.LIFE_ESSENCE_FLUID.get(), 1000 * 1024))
                .outputItems(ChemicalHelper.get(ore, Neutronium), 8)
                .duration(400)
                .EUt(528000 * 4)
                .save(provider);
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("twist"))
                .inputItems(ChemicalHelper.get(plate, Ultra_Mana), 1)
                .inputItems(MANA_FUSION_CASING.asItem(), 1)
                .inputItems(ChemicalHelper.get(plateDouble, AlfSteel), 2)
                .inputFluids(MANA_STABLE_COOLDOWN, 144)
                .outputItems(TWISTED_FUSION_CASING.asItem())
                .duration(400)
                .EUt(GTValues.VA[LuV])
                .save(provider);
        BloodAltarRecipeBuilder.builder(CTNHCore.id("bloodygold_dust_2"))// 血铂B
                .input(ChemicalHelper.get(TagPrefix.dust, PlatinumGroupSludge, 1))
                .output(ChemicalHelper.get(TagPrefix.dust, HEMOPLATINUM, 1))
                .circuitMeta(1)
                .syphon(15000)
                .minimumTier(3)
                .consumeRate(100)
                .drainRate(100)
                .save(provider);
    }
}
