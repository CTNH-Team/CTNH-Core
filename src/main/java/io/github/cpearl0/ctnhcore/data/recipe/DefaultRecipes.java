package io.github.cpearl0.ctnhcore.data.recipe;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import io.github.cpearl0.ctnhcore.common.recipe.NeutronActivatorCondition;
import io.github.cpearl0.ctnhcore.common.recipe.PlantCasingCondition;
import io.github.cpearl0.ctnhcore.registry.*;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.GTNNMultiblocks;
import io.github.cpearl0.ctnhcore.registry.nuclear.NuclearMaterials;
import net.createmod.catnip.data.Pair;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DefaultRecipes {
    private static final Map<Integer, Pair<Supplier<Item>, Supplier<Item>>> HEAVY_PLATE_TIERS = Map.of(
            // 重型装甲板系列
            1, Pair.of(() -> CTNHItems.HEAVY_INGOT_T1.get(), () -> CTNHItems.HEAVY_PLATE_T1.get()),
            2, Pair.of(() -> CTNHItems.HEAVY_INGOT_T2.get(), () -> CTNHItems.HEAVY_PLATE_T2.get()),
            3, Pair.of(() -> CTNHItems.HEAVY_INGOT_T3.get(), () -> CTNHItems.HEAVY_PLATE_T3.get()),
            4, Pair.of(() -> CTNHItems.HEAVY_INGOT_T4.get(), () -> CTNHItems.HEAVY_PLATE_T4.get()),
            // 放射性材料系列
            5, Pair.of(() -> CTNHItems.EncapsulatedUranium.get(), () -> CTNHItems.EnrichedUraniumNugget.get()),
            6, Pair.of(() -> CTNHItems.EncapsulatedThorium.get(), () -> CTNHItems.EnrichedThoriumNugget.get()),
            7, Pair.of(() -> CTNHItems.EncapsulatedPlutonium.get(), () -> CTNHItems.EnrichedPlutoniumNugget.get())
    );
    public static void init(Consumer<FinishedRecipe> provider) {
        // 注册所有爆破配方
        HEAVY_PLATE_TIERS.forEach((level, items) -> {
                    ItemStack ingot = new ItemStack(items.getFirst().get());
                    ItemStack plate = new ItemStack(items.getSecond().get());

                    // TNT爆破版
                    GTRecipeTypes.IMPLOSION_RECIPES.recipeBuilder("implode_heavy_plate_t" + level + "_tnt")
                            .inputItems(ingot)
                            .outputItems(plate)
                            .chancedOutput(TagPrefix.dust, GTMaterials.DarkAsh, 2500, 0)
                            .explosivesType(new ItemStack(Items.TNT, level * 2))
                            .duration(200 - 20 * level)
                            .save(provider);

                    // 工业TNT版
                    GTRecipeTypes.IMPLOSION_RECIPES.recipeBuilder("implode_heavy_plate_t" + level + "_itnt")
                            .inputItems(ingot)
                            .outputItems(plate)
                            .chancedOutput(TagPrefix.dust, GTMaterials.DarkAsh, 2500, 0)
                            .explosivesType(new ItemStack(GTBlocks.INDUSTRIAL_TNT.get(), level))
                            .duration(100 - 10 * level)
                            .save(provider);

                    // 火药桶版
                    GTRecipeTypes.IMPLOSION_RECIPES.recipeBuilder("implode_heavy_plate_t" + level + "_powderbarrel")
                            .inputItems(ingot)
                            .outputItems(plate)
                            .chancedOutput(TagPrefix.dust, GTMaterials.DarkAsh, 2500, 0)
                            .explosivesType(new ItemStack(GTBlocks.POWDERBARREL, level * 3))
                            .duration(150 - 15 * level)
                            .save(provider);
                    // 炸药
                    GTRecipeTypes.IMPLOSION_RECIPES.recipeBuilder("implode_" + level + "_dynamite")
                            .inputItems(ingot)
                            .outputItems(plate)
                            .chancedOutput(TagPrefix.dust, GTMaterials.DarkAsh, 2500, 0)
                            .explosivesType(GTItems.DYNAMITE.asStack(level * 4))
                            .duration(200 - 20 * level)
                            .save(provider);
                });
            GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("lightning_rod_assembler")
                    .inputItems(TagPrefix.rodLong, GTMaterials.Copper, 2)
                    .inputItems(TagPrefix.plate, GTMaterials.Copper, 3)
                    .outputItems(new ItemStack(Items.LIGHTNING_ROD))
                    .EUt(GTValues.VA[GTValues.LV])
                    .duration(50)
                    .circuitMeta(9)
                    .save(provider);
            VanillaRecipeHelper.addShapedRecipe(provider, "lightning_rod_hand",
                    new ItemStack(Items.LIGHTNING_ROD),
                    "hAf", "ABA", "dBs",
                    'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Copper),
                    'B', ChemicalHelper.get(TagPrefix.rodLong, GTMaterials.Copper)
            );
            // T1芯片
            GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("t1_chip")
                    .inputItems(CTNHItems.COMPUTER.asStack())
                    .inputItems(GTItems.COVER_SCREEN.asStack())
                    .inputItems(GTItems.SENSOR_HV.asStack())
                    .inputItems(GTItems.EMITTER_HV.asStack())
                    .inputItems(CTNHItems.HEAVY_PLATE_T1.asStack())
                    .inputFluids(GTMaterials.SolderingAlloy.getFluid(576))
                    .outputItems(CTNHItems.CHIP_T1.asStack())
                    .cleanroom(CleanroomType.CLEANROOM)
                    .duration(600) // 30秒
                    .EUt(GTValues.VA[GTValues.HV])
                    .save(provider);

            // T2芯片
            GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("t2_chip")
                    .inputItems(CTNHItems.COMPUTER.asStack())
                    .inputItems(GTItems.COVER_SCREEN.asStack())
                    .inputItems(GTItems.FIELD_GENERATOR_EV.asStack(2))
                    .inputItems(CTNHItems.HEAVY_PLATE_T2.asStack())
                    .inputFluids(GTMaterials.SolderingAlloy.getFluid(864))
                    .outputItems(CTNHItems.CHIP_T2.asStack())
                    .cleanroom(CleanroomType.CLEANROOM)
                    .duration(600)
                    .EUt(GTValues.VA[GTValues.EV])
                    .save(provider);

            // T3芯片
            GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("t3_chip")
                    .inputItems(CTNHItems.COMPUTER.asStack())
                    .inputItems(GTItems.COVER_SCREEN.asStack())
                    .inputItems(GTItems.SENSOR_IV.asStack())
                    .inputItems(GTItems.EMITTER_IV.asStack())
                    .inputItems(CTNHItems.HEAVY_PLATE_T3.asStack())
                    .inputFluids(GTMaterials.SolderingAlloy.getFluid(1152))
                    .outputItems(CTNHItems.CHIP_T3.asStack())
                    .cleanroom(CleanroomType.CLEANROOM)
                    .duration(600)
                    .EUt(GTValues.VA[GTValues.IV])
                    .save(provider);
            // T4芯片（需要高级计算机）
            GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("t4_chip")
                    .inputItems(CTNHItems.COMPUTER_ADVANCED.asStack())
                    .inputItems(GTItems.COVER_SCREEN.asStack())
                    .inputItems(GTItems.SENSOR_LuV.asStack())
                    .inputItems(GTItems.EMITTER_LuV.asStack())
                    .inputItems(CTNHItems.HEAVY_PLATE_T4.asStack())
                    .inputFluids(GTMaterials.SolderingAlloy.getFluid(2304))
                    .outputItems(CTNHItems.CHIP_T4.asStack())
                    .cleanroom(CleanroomType.CLEANROOM)
                    .duration(600)
                    .EUt(GTValues.VA[GTValues.LuV])
                    .save(provider);
        // ============== 普通计算机（MV级） ==============
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("computer_normal")
                .inputItems(GTBlocks.MACHINE_CASING_MV.asStack(2)) // 2个MV机器外壳
                .inputItems(TagPrefix.plate, GTMaterials.Aluminium, 2) // 2铝板
                .inputItems(TagPrefix.wireFine, GTMaterials.Aluminium, 2) // 2铝细线
                .inputItems(TagPrefix.rotor, GTMaterials.Iron) // 1铁转子
                .inputItems(GTItems.PLASTIC_CIRCUIT_BOARD.asStack()) // 塑料电路板
                .inputItems(GTItems.BASIC_CIRCUIT_BOARD.asStack()) // 基础电路板
                .inputFluids(GTMaterials.Polyethylene.getFluid(72)) // 72mB聚乙烯
                .outputItems(CTNHItems.COMPUTER.asStack()) // 输出普通计算机
                .duration(200) // 10秒（200 ticks）
                .EUt(GTValues.VA[GTValues.MV]) // MV电压（120 EU/t）
                .save(provider);

        // ============== 高级计算机（HV级） ==============
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("computer_advanced")
                .inputItems(GTBlocks.MACHINE_CASING_HV.asStack()) // 1个HV机器外壳
                .inputItems(TagPrefix.plate, GTMaterials.Aluminium, 2) // 2铝板
                .inputItems(TagPrefix.wireFine, GTMaterials.Tantalum, 2) // 2钽细线（升级材料）
                .inputItems(TagPrefix.rotor, GTMaterials.Iron) // 1铁转子
                .inputItems(GTItems.ADVANCED_CIRCUIT_BOARD.asStack()) // 高级电路板
                .inputItems(GTItems.INTEGRATED_CIRCUIT_HV.asStack()) // HV集成电路
                .inputFluids(GTMaterials.Polyethylene.getFluid(72)) // 72mB聚乙烯
                .outputItems(CTNHItems.COMPUTER_ADVANCED.asStack()) // 输出高级计算机
                .duration(200) // 10秒
                .EUt(GTValues.VA[GTValues.HV]) // HV电压（480 EU/t）
                .save(provider);

            // ==== 夸克核心组装 ====
            CTNHRecipeTypes.PRECISION_ASSEMBLY_RECIPES.recipeBuilder("quark_core_assembly")
                    .inputItems(CustomTags.IV_CIRCUITS, 2)
                    .inputItems(TagPrefix.lens, GTMaterials.Diamond, 8)
                    .inputItems(GTItems.NAND_MEMORY_CHIP.asStack(16))
                    .inputItems(TagPrefix.rotor, GTMaterials.Aluminium)
                    .inputFluids(GTMaterials.Polyethylene.getFluid(576))
                    .inputFluids(GTMaterials.SodiumPotassium.getFluid(288))
                    .inputFluids(GTMaterials.Lubricant.getFluid(144))
                    .inputFluids(GTMaterials.StyreneButadieneRubber.getFluid(144))
                    .outputItems(CTNHItems.QuarkCore)
                    .EUt(GTValues.VA[GTValues.LuV])  // 32768 EU/t
                    .duration(100)  // 5秒
                    .save(provider);

            // 石墨 + 铀238 -> 铀石墨混合物
            GTRecipeTypes.MIXER_RECIPES.recipeBuilder("graphite_uranium_mixture")
                    .inputItems(TagPrefix.dust, GTMaterials.Graphite, 3)
                    .inputItems(TagPrefix.dust, GTMaterials.Uranium238)
                    .outputItems(TagPrefix.dust, CTNHMaterials.GraphiteUraniumMixture, 4)
                    .EUt(GTValues.VA[GTValues.LV])  // 30 EU/t
                    .duration(34)  // 1.7秒
                    .save(provider);

            // 铀石墨混合物 + 碳化钨箔 -> 封装铀
            GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("encapsulated_uranium")
                    .inputItems(TagPrefix.dust, CTNHMaterials.GraphiteUraniumMixture, 4)
                    .inputItems(TagPrefix.foil, GTMaterials.TungstenCarbide, 2)
                    .outputItems(CTNHItems.EncapsulatedUranium)
                    .EUt(GTValues.VA[GTValues.HV])  // 480 EU/t
                    .duration(1400)  // 70秒
                    .save(provider);

            // 钍 + 铀235 + 碳 -> 铀碳化钍混合物
            GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("uranium_carbide_thorium_mixture")
                .inputItems(TagPrefix.dust, GTMaterials.Thorium, 11)
                .inputItems(TagPrefix.dust, NuclearMaterials.Thorium232)
                .inputItems(TagPrefix.dust, GTMaterials.Uranium235)
                .inputItems(TagPrefix.dust, GTMaterials.Carbon, 3)
                .outputItems(TagPrefix.dust, CTNHMaterials.UraniumCarbideThoriumMixture, 16)
                .EUt(GTValues.VA[GTValues.LV])  // 30 EU/t
                .duration(47)  // 2.35秒
                .save(provider);
        // 1. 封装钍
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("encapsulated_thorium")
                .inputItems(TagPrefix.dust, CTNHMaterials.UraniumCarbideThoriumMixture, 64)
                .inputItems(TagPrefix.foil, GTMaterials.TungstenSteel, 4)
                .outputItems(CTNHItems.EncapsulatedThorium)
                .EUt(GTValues.VA[GTValues.HV]) // 480 EU/t
                .duration(300) // 15秒
                .save(provider);

// 2. 钚氧化物铀混合物
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("plutonium_oxide_uranium_mixture")
                .inputItems(TagPrefix.dust, GTMaterials.Plutonium239, 10)
                .inputItems(TagPrefix.dust, GTMaterials.Uranium238, 2)
                .inputItems(TagPrefix.dust, GTMaterials.Carbon, 8)
                .inputFluids(GTMaterials.Oxygen.getFluid(12000))
                .outputItems(TagPrefix.dust, CTNHMaterials.PlutoniumOxideUraniumMixture, 32)
                .EUt(GTValues.VA[GTValues.LV]) // 30 EU/t
                .duration(25) // 1.25秒
                .save(provider);

// 3. 封装钚
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("encapsulated_plutonium")
                .inputItems(TagPrefix.dust, CTNHMaterials.PlutoniumOxideUraniumMixture, 8)
                .inputItems(TagPrefix.foil, GTMaterials.HSSS, 4)
                .outputItems(CTNHItems.EncapsulatedPlutonium)
                .EUt(GTValues.VA[GTValues.HV]) // 480 EU/t
                .duration(1400) // 70秒
                .save(provider);

// 4. 浓缩铀锭
        GTRecipeTypes.COMPRESSOR_RECIPES.recipeBuilder("enriched_uranium_ingot")
                .inputItems(CTNHItems.EnrichedUraniumNugget.asStack(9))
                .outputItems(CTNHItems.EnrichedUranium)
                .EUt(GTValues.VA[GTValues.HV]) // 480 EU/t
                .duration(600) // 30秒
                .save(provider);

// 5. 浓缩钍锭
        GTRecipeTypes.COMPRESSOR_RECIPES.recipeBuilder("enriched_thorium_ingot")
                .inputItems(CTNHItems.EnrichedThoriumNugget.asStack(9))
                .outputItems(CTNHItems.EnrichedThorium)
                .EUt(GTValues.VA[GTValues.MV]) // 120 EU/t
                .duration(200) // 10秒
                .save(provider);

// 6. 浓缩钚锭
        GTRecipeTypes.COMPRESSOR_RECIPES.recipeBuilder("enriched_plutonium_ingot")
                .inputItems(CTNHItems.EnrichedPlutoniumNugget.asStack(9))
                .outputItems(CTNHItems.EnrichedPlutonium)
                .EUt(GTValues.VA[GTValues.MV]) // 120 EU/t
                .duration(1200) // 60秒
                .save(provider);

// 7. 锎生产（聚变）
        GTRecipeTypes.FUSION_RECIPES.recipeBuilder("californium_production")
                .inputFluids(GTMaterials.Plutonium239.getFluid(48))
                .inputFluids(GTMaterials.Beryllium.getFluid(48))
                .outputFluids(GTMaterials.Californium.getFluid(48))
                .fusionStartEU(120_000_000)
                .EUt(GTValues.VA[GTValues.ZPM]) // 196608 EU/t
                .duration(240) // 12秒
                .save(provider);

// 8. 气奥生产（聚变）
        GTRecipeTypes.FUSION_RECIPES.recipeBuilder("oganesson_production")
                .inputFluids(GTMaterials.Californium.getFluid(32))
                .inputFluids(GTMaterials.Calcium.getFluid(720))
                .outputFluids(GTMaterials.Oganesson.getFluid(720))
                .fusionStartEU(600_000_000)
                .EUt(GTValues.VA[GTValues.ZPM]) // 196608 EU/t
                .duration(240) // 12秒
                .save(provider);

// 9. 逆变器组装
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("inverter_assembly")
                .inputItems(TagPrefix.plate, GTMaterials.NetherQuartz, 2)
                .inputItems(CustomTags.MV_CIRCUITS)
                .inputItems(GTItems.COVER_SCREEN)
                .inputItems(GTItems.DIODE.asStack(16))
                .inputItems(TagPrefix.wireGtSingle, GTMaterials.Aluminium, 8)
                .inputFluids(GTMaterials.SolderingAlloy.getFluid(144))
                .outputItems(CTNHItems.INVERTER)
                .EUt(GTValues.VA[GTValues.MV]) // 120 EU/t
                .duration(240) // 12秒
                .save(provider);
        // 1. 钍基液体燃料
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("thorium_based_liquid_fuel")
                .inputItems(CTNHItems.EnrichedThorium)
                .inputItems(TagPrefix.dust, GTMaterials.Lithium, 4)
                .inputFluids(GTMaterials.Mercury.getFluid(1000))
                .outputFluids(CTNHMaterials.ThoriumBasedLiquidFuel.getFluid(1000))
                .circuitMeta(2)
                .EUt(GTValues.VHA[GTValues.HV]) // 480 EU/t
                .duration(3000) // 150秒
                .save(provider);

// 2. 铀基液体燃料
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("uranium_based_liquid_fuel")
                .inputItems(CTNHItems.EnrichedUranium)
                .inputItems(TagPrefix.dust, GTMaterials.Potassium, 8)
                .inputFluids(GTMaterials.Radon.getFluid(1000))
                .outputFluids(CTNHMaterials.UraniumBasedLiquidFuel.getFluid(1000))
                .circuitMeta(1)
                .EUt(GTValues.VHA[GTValues.LuV]) // 32768 EU/t
                .duration(200) // 10秒
                .save(provider);

// 3. 钚基液体燃料
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("plutonium_based_liquid_fuel")
                .inputItems(CTNHItems.EnrichedPlutonium)
                .inputItems(TagPrefix.dust, CTNHMaterials.NeutroniumMixture, 8)
                .inputItems(TagPrefix.dust, GTMaterials.Caesium, 16)
                .inputItems(TagPrefix.dust, GTMaterials.Naquadah, 2)
                .outputFluids(CTNHMaterials.PlutoniumBasedLiquidFuel.getFluid(1000))
                .circuitMeta(1)
                .EUt(GTValues.VA[GTValues.LuV]) // 32768 EU/t
                .duration(360) // 18秒
                .save(provider);

// 4. 激发态钍基燃料
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("thorium_based_liquid_fuel_excited")
                .inputFluids(CTNHMaterials.ThoriumBasedLiquidFuel.getFluid(1000))
                .inputFluids(GTMaterials.Helium.getFluid(250))
                .outputFluids(CTNHMaterials.ThoriumBasedLiquidFuelExcited.getFluid(1000))
                .circuitMeta(1)
                .EUt(GTValues.VHA[GTValues.IV]) // 1920 EU/t
                .duration(120) // 6秒
                .save(provider);

// 5. 中子活化铀基燃料（激发态）
        CTNHRecipeTypes.NEUTRON_ACTIVATOR_RECIPES.recipeBuilder("uranium_based_liquid_fuel_excited")
                .notConsumable(TagPrefix.plate, GTMaterials.Tungsten)
                .inputFluids(CTNHMaterials.UraniumBasedLiquidFuel.getFluid(100))
                .outputFluids(CTNHMaterials.UraniumBasedLiquidFuelExcited.getFluid(100))
                .addCondition(new NeutronActivatorCondition(550, 450))
                .duration(80) // 4秒
                .save(provider);

// 6. 聚变激发铀基燃料
        GTRecipeTypes.FUSION_RECIPES.recipeBuilder("uranium_based_liquid_fuel_excited_fusion")
                .inputFluids(CTNHMaterials.UraniumBasedLiquidFuel.getFluid(10))
                .inputFluids(GTMaterials.Hydrogen.getFluid(100))
                .outputFluids(CTNHMaterials.UraniumBasedLiquidFuelExcited.getFluid(10))
                .fusionStartEU(200_000_000)
                .EUt(GTValues.VA[GTValues.IV]) // 1920 EU/t
                .duration(40) // 2秒
                .save(provider);

// 7. 中子活化钚基燃料（激发态）
        CTNHRecipeTypes.NEUTRON_ACTIVATOR_RECIPES.recipeBuilder("plutonium_based_liquid_fuel_excited")
                .notConsumable(TagPrefix.plate, GTMaterials.Tritanium)
                .inputFluids(CTNHMaterials.PlutoniumBasedLiquidFuel.getFluid(100))
                .outputFluids(CTNHMaterials.PlutoniumBasedLiquidFuelExcited.getFluid(100))
                .addCondition(new NeutronActivatorCondition(600, 500))
                .duration(80) // 4秒
                .save(provider);

// 8. 聚变激发钚基燃料
        GTRecipeTypes.FUSION_RECIPES.recipeBuilder("plutonium_based_liquid_fuel_excited_fusion")
                .inputFluids(GTMaterials.Lutetium.getFluid(16))
                .inputFluids(CTNHMaterials.PlutoniumBasedLiquidFuel.getFluid(20))
                .outputFluids(CTNHMaterials.PlutoniumBasedLiquidFuelExcited.getFluid(20))
                .fusionStartEU(220_000_000)
                .EUt(GTValues.VA[GTValues.LuV]) // 32768 EU/t
                .duration(20) // 1秒
                .save(provider);

// 9. 钍基燃料耗尽（中子活化）
        CTNHRecipeTypes.NEUTRON_ACTIVATOR_RECIPES.recipeBuilder("thorium_based_liquid_fuel_depleted")
                .inputFluids(CTNHMaterials.ThoriumBasedLiquidFuelExcited.getFluid(200))
                .outputFluids(CTNHMaterials.ThoriumBasedLiquidFuelDepleted.getFluid(200))
                .addCondition(new NeutronActivatorCondition(700, 500))
                .duration(10000) // 500秒
                .save(provider);

// 10. 钍基燃料耗尽（大型反应堆）
        CTNHRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder("thorium_based_liquid_fuel_depleted_reactor")
                .inputFluids(CTNHMaterials.ThoriumBasedLiquidFuelExcited.getFluid(1000))
                .outputFluids(CTNHMaterials.ThoriumBasedLiquidFuelDepleted.getFluid(1000))
                .EUt(-2200) // 输出功率
                .duration(500) // 25秒
                .save(provider);
        // 1. 铀基耗尽燃料（大型反应堆）
        CTNHRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder("uranium_based_liquid_fuel_depleted")
                .inputFluids(CTNHMaterials.UraniumBasedLiquidFuelExcited.getFluid(1000))
                .outputFluids(CTNHMaterials.UraniumBasedLiquidFuelDepleted.getFluid(1000))
                .EUt(-12960) // 输出12,960 EU/t
                .duration(100) // 5秒
                .save(provider);

// 2. 钚基耗尽燃料（大型反应堆）
        CTNHRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder("plutonium_based_liquid_fuel_depleted")
                .inputFluids(CTNHMaterials.PlutoniumBasedLiquidFuelExcited.getFluid(1000))
                .outputFluids(CTNHMaterials.PlutoniumBasedLiquidFuelDepleted.getFluid(1000))
                .EUt(-32400) // 输出32,400 EU/t
                .duration(150) // 7.5秒
                .save(provider);

// 3. 钍基耗尽燃料离心
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("thorium_based_liquid_fuel_depleted_centrifuge")
                .inputFluids(CTNHMaterials.ThoriumBasedLiquidFuelDepleted.getFluid(500))
                .outputItems(TagPrefix.dust, NuclearMaterials.Thorium232, 32) // 固定输出
                .chancedOutput(TagPrefix.dust, NuclearMaterials.Thorium232, 8, 8000, 0) // 80%额外
                .outputItems(TagPrefix.dust, GTMaterials.Praseodymium, 32)
                .chancedOutput(TagPrefix.dust, GTMaterials.Praseodymium, 16, 8000, 0)
                .chancedOutput(TagPrefix.dust, GTMaterials.Boron, 3, 3000, 0) // 30%概率
                .chancedOutput(TagPrefix.dust, GTMaterials.Indium, 2, 5000, 0) // 50%概率
                .circuitMeta(1)
                .EUt(1040) // EV级
                .duration(750) // 37.5秒
                .save(provider);

// 4. 铀基耗尽燃料离心
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("uranium_based_liquid_fuel_depleted_centrifuge")
                .inputFluids(CTNHMaterials.UraniumBasedLiquidFuelDepleted.getFluid(1000))
                .chancedOutput(TagPrefix.dust, GTMaterials.Lead, 16, 6000, 0) // 60%概率
                .chancedOutput(TagPrefix.dust, GTMaterials.Bismuth, 1, 1000, 0) // 10%概率
                .chancedOutput(TagPrefix.dust, GTMaterials.Barium, 6, 5000, 0) // 50%概率
                .circuitMeta(1)
                .EUt(1040) // EV级
                .duration(1000) // 50秒
                .save(provider);

// 5. 钚基耗尽燃料离心
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("plutonium_based_liquid_fuel_depleted_centrifuge")
                .inputFluids(CTNHMaterials.PlutoniumBasedLiquidFuelDepleted.getFluid(1000))
                .chancedOutput(TagPrefix.dust, GTMaterials.Tritanium, 9, 5000, 0) // 50%概率
                .chancedOutput(TagPrefix.dust, GTMaterials.Cerium, 4, 8000, 0) // 80%概率
                .chancedOutput(TagPrefix.dust, GTMaterials.Gold, 2, 7500, 0) // 75%概率
                .outputFluids(GTMaterials.Krypton.getFluid(144))
                .circuitMeta(1)
                .EUt(GTValues.VA[GTValues.IV]) // 1920 EU/t
                .duration(2500) // 125秒
                .save(provider);

// 6. 辐射防护板（铱版）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("radiation_protection_plate_iridium")
                .inputItems(TagPrefix.plateDense, GTMaterials.Iridium, 8)
                .inputItems(TagPrefix.plateDense, GTMaterials.NaquadahAlloy, 8)
                .inputFluids(GTMaterials.Lead.getFluid(1152))
                .outputItems(CTNHItems.PlateRadiationProtection)
                .circuitMeta(1)
                .EUt(GTValues.VA[GTValues.EV]) // 1920 EU/t
                .duration(400) // 20秒
                .save(provider);

// 7. 辐射防护板（镧版）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("radiation_protection_plate_lanthanum")
                .inputItems(TagPrefix.plateDense, GTMaterials.Lanthanum, 4)
                .inputItems(TagPrefix.plateDense, GTMaterials.NaquadahAlloy, 8)
                .inputFluids(GTMaterials.Lead.getFluid(1152))
                .outputItems(CTNHItems.PlateRadiationProtection)
                .circuitMeta(1)
                .EUt(GTValues.VA[GTValues.EV]) // 1920 EU/t
                .duration(400) // 20秒
                .save(provider);

// 8. 致密镧板
        GTRecipeTypes.BENDER_RECIPES.recipeBuilder("dense_lanthanum_plate")
                .inputItems(TagPrefix.plate, GTMaterials.Lanthanum, 9)
                .outputItems(TagPrefix.plateDense, GTMaterials.Lanthanum)
                .circuitMeta(9)
                .EUt(96) // LV级
                .duration(1242) // 62.1秒
                .save(provider);

// 9. Naquadah基液体燃料
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("naquadah_based_liquid_fuel")
                .inputItems(TagPrefix.dust, GTMaterials.Naquadria, 42)
                .inputItems(TagPrefix.dust, GTMaterials.Cerium, 16)
                .inputItems(TagPrefix.dust, GTMaterials.Neodymium, 16)
                .outputFluids(CTNHMaterials.NaquadahBasedLiquidFuel.getFluid(1000))
                .EUt(GTValues.VA[GTValues.IV]) // 1920 EU/t
                .duration(300) // 15秒
                .save(provider);

// 10. Naquadah基激发态燃料（聚变）
        GTRecipeTypes.FUSION_RECIPES.recipeBuilder("naquadah_based_liquid_fuel_excited")
                .inputFluids(CTNHMaterials.NaquadahBasedLiquidFuel.getFluid(800))
                .inputFluids(GTMaterials.Radon.getFluid(200))
                .outputFluids(CTNHMaterials.NaquadahBasedLiquidFuelExcited.getFluid(100))
                .fusionStartEU(320_000_000) // 320M EU启动
                .EUt(26000) // IV级
                .duration(500) // 25秒
                .save(provider);

// 11. Naquadah基耗尽燃料（大型反应堆）
        CTNHRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_based_liquid_fuel_depleted")
                .inputFluids(CTNHMaterials.NaquadahBasedLiquidFuelExcited.getFluid(1))
                .outputFluids(CTNHMaterials.NaquadahBasedLiquidFuelDepleted.getFluid(1))
                .EUt(-975_000) // 输出975,000 EU/t
                .duration(60) // 3秒
                .save(provider);

// 12. Naquadah基耗尽燃料离心
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("naquadah_based_liquid_fuel_depleted_centrifuge")
                .inputFluids(CTNHMaterials.NaquadahBasedLiquidFuelDepleted.getFluid(125))
                .chancedOutput(TagPrefix.dust, GTMaterials.Naquadah, 8, 9000, 0) // 90%概率
                .chancedOutput(TagPrefix.dust, GTMaterials.Naquadah, 6, 8500, 0) // 85%概率
                .chancedOutput(TagPrefix.dust, GTMaterials.Naquadah, 4, 5000, 0) // 50%概率
                .chancedOutput(TagPrefix.dust, GTMaterials.Neodymium, 4, 4000, 0) // 40%概率
                .chancedOutput(TagPrefix.dust, GTMaterials.Europium, 4, 2000, 0) // 20%概率
                .outputFluids(GTMaterials.Xenon.getFluid(18))
                .EUt(GTValues.VA[GTValues.EV]) // 1920 EU/t
                .duration(600) // 30秒
                .save(provider);

// 13. 钍232生产（化工厂）
        CTNHRecipeTypes.CHEMICAL_PLANT_RECIPES.recipeBuilder("thorium232_production")
                .inputItems(TagPrefix.dust, GTMaterials.Thorium, 16)
                .inputItems(TagPrefix.dust, GTMaterials.Borax, 12)
                .inputFluids(GTMaterials.DistilledWater.getFluid(2000))
                .inputFluids(GTMaterials.HydrochloricAcid.getFluid(1000))
                .outputItems(TagPrefix.dustSmall, GTMaterials.Thorium, 32)
                .outputItems(TagPrefix.dust, NuclearMaterials.Thorium232, 2)
                .chancedOutput(TagPrefix.dustSmall, NuclearMaterials.Thorium232, 2, 1000, 0) // 10%概率
                .addCondition(new PlantCasingCondition(5)) // T5化工厂
                .circuitMeta(1)
                .EUt(GTValues.VA[GTValues.EV]) // 1920 EU/t
                .duration(6000) // 300秒
                .save(provider);

        // 1. 清洁机器外壳（工作台）
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "clean_machine_casing",
                CTNHBlocks.PROCESS_MACHINE_CASING.asStack(),
                "ABA", "BCB", "ABA",
                'A', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:stainless_steel_foil")),
                'B', CustomTags.IV_CIRCUITS,
                'C', GTBlocks.CASING_STEEL_SOLID.asItem()
        );

// 2. 铱机器外壳（工作台）
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "iridium_machine_casing",
                CTNHBlocks.IRIDIUM_CASING.asStack(),
                "ABA", "ACA", "ABA",
                'A', TagPrefix.plate, GTMaterials.Iridium,
                'B', TagPrefix.screw, GTMaterials.Iridium,
                'C', TagPrefix.gearSmall, GTMaterials.Iridium
        );

// 3. 聚苯并咪唑管道（工作台）
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "polybenzimidazole_pipe",
                CTNHBlocks.CASING_POLYBENZIMIDAZOLE_PIPE.asStack(),
                "ABA", "BCB", "ABA",
                'A', TagPrefix.plate, GTMaterials.Polybenzimidazole,
                'B', TagPrefix.pipeNormalFluid, GTMaterials.Polybenzimidazole,
                'C', TagPrefix.frameGt, GTMaterials.Polybenzimidazole
        );

// 4. 高级过滤外壳（组装机）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("advanced_filter_casing")
                .inputItems(TagPrefix.frameGt, GTMaterials.Iridium)
                .inputItems(GTItems.ELECTRIC_MOTOR_UV)
                .inputItems(TagPrefix.rotor, GTMaterials.Iridium)
                .inputItems(GTItems.COVER_ITEM_DETECTOR_ADVANCED, 8)
                .inputItems(TagPrefix.rodLong, GTMaterials.Iridium, 8)
                .outputItems(CTNHBlocks.ADVANCED_FILTER_CASING.asStack())
                .EUt(GTValues.VA[GTValues.LuV]) // 32768 EU/t
                .duration(600) // 30秒
                .save(provider);

// 5. 铱机器外壳（组装机）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("iridium_machine_casing_assembler")
                .inputItems(TagPrefix.plate, GTMaterials.Iridium, 6)
                .inputItems(TagPrefix.screw, GTMaterials.Iridium, 2)
                .inputItems(TagPrefix.gearSmall, GTMaterials.Iridium, 1)
                .outputItems(CTNHBlocks.IRIDIUM_CASING.asStack())
                .EUt(GTValues.VA[GTValues.LV]) // 30 EU/t
                .duration(200) // 10秒
                .save(provider);

// 6. 高速管道方块（组装机）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("high_speed_pipe_block")
                .inputItems(TagPrefix.pipeHugeFluid, GTMaterials.StainlessSteel)
                .inputItems(TagPrefix.frameGt, GTMaterials.BlueAlloy, 32)
                .inputItems(TagPrefix.wireGtSingle, GTMaterials.MercuryBariumCalciumCuprate, 32)
                .inputItems(TagPrefix.plate, GTMaterials.Beryllium, 32)
                .inputItems(CustomTags.IV_CIRCUITS)
                .outputItems(CTNHMachines.HIGH_SPEED_PIPE_BLOCK.asStack())
                .EUt(GTValues.VA[GTValues.EV]) // 1920 EU/t
                .duration(300) // 15秒
                .save(provider);

// 7. 锇硼硅玻璃（流体固化）
//        GTRecipeTypes.FLUID_SOLIDFICATION_RECIPES.recipeBuilder("osmium_borosilicate_glass")
//                .inputItems(TagPrefix.block, GTMaterials.BorosilicateGlass)
//                .inputFluids(GTMaterials.Osmium.getFluid(1152))
//                .outputItems(CTNHBlocks.OSMIUM_BOROSILICATE_GLASS.asStack())
//                .EUt(GTValues.VA[GTValues.ZPM]) // 98304 EU/t
//                .duration(800) // 40秒
//                .save(provider);

// 8. 防辐射机器外壳（组装机）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("radiation_proof_machine_casing")
                .inputItems(TagPrefix.plateDense, GTMaterials.Lead, 6)
                .inputItems(TagPrefix.frameGt, GTMaterials.TungstenSteel)
                .inputFluids(GTMaterials.Concrete.getFluid(1296))
                .outputItems(CTNHBlocks.RADIATION_PROOF_MACHINE_CASING.asStack())
                .EUt(GTValues.VA[GTValues.IV]) // 1920 EU/t
                .duration(40) // 2秒
                .save(provider);

// 9. MAR辐射防护外壳（组装机）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("mar_casing")
                .inputItems(CTNHItems.PlateRadiationProtection.asStack(6))
                .inputItems(TagPrefix.frameGt, GTMaterials.Europium)
                .inputItems(GTItems.FIELD_GENERATOR_MV)
                .outputItems(CTNHBlocks.MAR_CASING.asStack())
                .circuitMeta(1)
                .EUt(GTValues.VA[GTValues.EV]) // 1920 EU/t
                .duration(400) // 20秒
                .save(provider);

// 10. 防辐射框架（组装机）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("frame_radiation")
                .inputItems(TagPrefix.rodLong, GTMaterials.NaquadahAlloy, 8)
                .inputItems(TagPrefix.frameGt, GTMaterials.HSSE, 4)
                .outputItems(TagPrefix.frameGt, CTNHMaterials.RadiationProtection)
                .circuitMeta(24)
                .EUt(GTValues.VA[GTValues.EV]) // 1920 EU/t
                .duration(320) // 16秒
                .save(provider);

// 11. 中子混合物处理（混合机）
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("neutronium_mixture")
                .inputItems(TagPrefix.dust, CTNHMaterials.NeutroniumMixture)
                .inputFluids(GTMaterials.Helium.getFluid(FluidStorageKeys.PLASMA, 144))
                .outputItems(TagPrefix.dust, GTMaterials.Neutronium)
                .circuitMeta(1)
                .EUt(GTValues.VA[GTValues.ZPM]) // 98304 EU/t
                .duration(225) // 11.25秒
                .save(provider);

// 12. 中子矿离心（无序合成）
        VanillaRecipeHelper.addShapelessRecipe(
                provider,
                "centrifuged_ore_to_dust_neutronium",
                ChemicalHelper.get(TagPrefix.dust, CTNHMaterials.NeutroniumMixture),
                'h',
                ChemicalHelper.get(TagPrefix.crushedRefined, GTMaterials.Neutronium)
        );

// 2. 催化剂舱口（组装机）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("catalyst_hatch")
                .inputItems(CustomTags.MV_CIRCUITS)
                .inputItems(GTMachines.ITEM_IMPORT_BUS[GTValues.MV])
                .outputItems(CTNHMachines.CATALYST_HATCH)
                .EUt(GTValues.VA[GTValues.MV]) // 120 EU/t
                .duration(300) // 15秒
                .save(provider);

// 3. 大型Naquadah脱水机（组装机）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("large_naquadah_reactor_casing")
                .inputItems(GCYMBlocks.CASING_HIGH_TEMPERATURE_SMELTING.asItem())
                .inputItems(TagPrefix.wireGtHex, GTMaterials.IndiumTinBariumTitaniumCuprate, 4)
                .inputItems(GTItems.BATTERY_LuV_VANADIUM.asStack(1))
                .inputItems(GTItems.ROBOT_ARM_EV.asStack(4))
                .inputItems(TagPrefix.plate, GTMaterials.Zeron100, 8)
                .inputItems(CustomTags.IV_CIRCUITS, 8)
                .inputFluids(GTMaterials.Zeron100.getFluid(2880))
                .outputItems(GTNNMultiblocks.LARGE_DEHYDRATOR)
                .EUt(GTValues.VA[GTValues.LuV]) // 32768 EU/t
                .duration(2400) // 120秒
                .save(provider);

// 4. 大型Naquadah反应堆（装配线）
        GTRecipeTypes.ASSEMBLY_LINE_RECIPES.recipeBuilder("large_naquadah_reactor")
                 .inputItems(TagPrefix.frameGt, GTMaterials.Neutronium, 8)
                 .inputItems(CTNHItems.PlateRadiationProtection.asStack(16))
                 .inputItems(GTItems.FIELD_GENERATOR_ZPM.asStack(2))
                 .inputItems(GTItems.ELECTRIC_PUMP_ZPM.asStack(8))
                 .inputItems(CustomTags.UV_CIRCUITS, 4)
                 .inputItems(TagPrefix.wireGtOctal, GTMaterials.IndiumTinBariumTitaniumCuprate, 8)
                 .inputItems(TagPrefix.pipeHugeFluid, GTMaterials.Naquadah, 4)
                 .inputItems(TagPrefix.plate, GTMaterials.NaquadahAlloy, 8)
                 .inputItems(TagPrefix.screw, GTMaterials.Osmium, 16)
                 .outputItems(GTNNMultiblocks.LARGE_NAQUADAH_REACTOR)
                 .scannerResearch(CTNHMachines.NAQUADAH_REACTOR[GTValues.LuV].asStack())
                 .EUt(GTValues.VA[GTValues.ZPM])   //98304 EU/t
                 .duration(4200) // 210秒
                 .save(provider);
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("neutron_sensor")
                .inputItems(GTBlocks.MACHINE_CASING_IV.asStack())
                .inputItems(GTItems.COVER_ACTIVITY_DETECTOR)
                .inputItems(GTItems.COVER_SCREEN)
                .inputItems(TagPrefix.plate, GTMaterials.VanadiumGallium, 4)
                .inputItems(CustomTags.EV_CIRCUITS)
                .inputItems(GTItems.SENSOR_HV.asStack(2))
                .circuitMeta(1)
                .outputItems(CTNHMachines.NEUTRON_SENSOR.asStack())
                .EUt(GTValues.VA[GTValues.EV]) // 1920 EU/t
                .duration(300) // 15秒
                .save(provider);

// 13. 中子活化器（精密装配）
        CTNHRecipeTypes.PRECISION_ASSEMBLY_RECIPES.recipeBuilder("neutron_activator")
                .inputItems(CTNHItems.QuarkCore.asStack(2))
                .inputItems(GTItems.SENSOR_EV.asStack(2))
                .inputItems(CTNHItems.NeutronSource)
                .inputFluids(GTMaterials.StainlessSteel.getFluid(576))
                .inputFluids(GTMaterials.TungstenCarbide.getFluid(144))
                .outputItems(GTNNMultiblocks.NEUTRON_ACTIVATOR.asStack())
                .EUt(GTValues.VA[GTValues.IV]) // 1920 EU/t
                .duration(100) // 5秒
                .save(provider);

// 14. 中子源（工作台）
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "neutron_source",
                CTNHItems.NeutronSource.asStack(),
                " A ", "ABA", " A ",
                'A', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:dense_steel_plate")), // 致密钢板
                'B', CTNHItems.EnrichedUranium.asStack() // 富集铀（直接引用）
        );
        // MV中子加速器
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("neutron_accelerator_mv")
                .inputItems(CTNHItems.INVERTER.asStack())
                .inputItems(GTMachines.HULL[GTValues.MV].asStack())
                .inputItems(TagPrefix.cableGtSingle, GTMaterials.Copper, 2)
                .inputItems(TagPrefix.plate, GTMaterials.Polyethylene)
                .inputItems(TagPrefix.plate, GTMaterials.Beryllium, 2)
                .inputItems(GTItems.ELECTRIC_MOTOR_MV)
                .outputItems(CTNHMachines.NEUTRON_ACCELERATOR[GTValues.MV].asStack())
                .EUt(GTValues.VA[GTValues.MV]) // 120 EU/t
                .duration(300) // 15秒
                .save(provider);

        // HV中子加速器
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("neutron_accelerator_hv")
                .inputItems(CTNHItems.INVERTER.asStack())
                .inputItems(GTMachines.HULL[GTValues.HV].asStack())
                .inputItems(TagPrefix.cableGtSingle, GTMaterials.Gold, 2)
                .inputItems(TagPrefix.plate, GTMaterials.PolyvinylChloride)
                .inputItems(TagPrefix.plateDouble, GTMaterials.Beryllium, 2)
                .inputItems(GTItems.ELECTRIC_MOTOR_HV.asStack(2))
                .outputItems(CTNHMachines.NEUTRON_ACCELERATOR[GTValues.HV].asStack())
                .EUt(GTValues.VA[GTValues.HV]) // 480 EU/t
                .duration(300) // 15秒
                .save(provider);

        // EV中子加速器
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("neutron_accelerator_ev")
                .inputItems(CTNHItems.INVERTER.asStack())
                .inputItems(GTMachines.HULL[GTValues.EV].asStack())
                .inputItems(TagPrefix.cableGtSingle, GTMaterials.Aluminium, 2)
                .inputItems(TagPrefix.plate, GTMaterials.StyreneButadieneRubber)
                .inputItems(TagPrefix.plate, GTMaterials.IronMagnetic, 4)
                .inputItems(TagPrefix.plate, GTMaterials.TungstenCarbide, 2)
                .inputItems(GTItems.ELECTRIC_MOTOR_EV.asStack(2))
                .outputItems(CTNHMachines.NEUTRON_ACCELERATOR[GTValues.EV].asStack())
                .EUt(GTValues.VA[GTValues.EV]) // 1920 EU/t
                .duration(300) // 15秒
                .save(provider);

        // IV中子加速器
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("neutron_accelerator_iv")
                .inputItems(CTNHItems.INVERTER.asStack())
                .inputItems(GTMachines.HULL[GTValues.IV].asStack())
                .inputItems(TagPrefix.cableGtSingle, GTMaterials.Tungsten, 2)
                .inputItems(TagPrefix.plate, GTMaterials.SiliconeRubber)
                .inputItems(TagPrefix.plate, GTMaterials.SteelMagnetic, 4)
                .inputItems(TagPrefix.plateDouble, GTMaterials.TungstenCarbide, 2)
                .inputItems(GTItems.ELECTRIC_MOTOR_IV.asStack(2))
                .outputItems(CTNHMachines.NEUTRON_ACCELERATOR[GTValues.IV].asStack())
                .EUt(GTValues.VA[GTValues.IV]) // 1920 EU/t
                .duration(300) // 15秒
                .save(provider);

        // LuV中子加速器（装配线）
        GTRecipeTypes.ASSEMBLY_LINE_RECIPES.recipeBuilder("neutron_accelerator_luv")
                .inputItems(CTNHItems.INVERTER.asStack(2))
                .inputItems(GTMachines.HULL[GTValues.LuV].asStack())
                .inputItems(TagPrefix.cableGtSingle, GTMaterials.YttriumBariumCuprate, 2)
                .inputItems(TagPrefix.plate, GTMaterials.NetherStar)
                .inputItems(TagPrefix.plate, GTMaterials.Polybenzimidazole, 4)
                .inputItems(TagPrefix.plate, GTMaterials.NeodymiumMagnetic, 4)
                .inputItems(GTItems.ELECTRIC_MOTOR_LuV.asStack(2))
                .inputFluids(GTMaterials.Argon.getFluid(3000))
                .outputItems(CTNHMachines.NEUTRON_ACCELERATOR[GTValues.LuV].asStack())
                .scannerResearch(CTNHMachines.NEUTRON_ACCELERATOR[GTValues.IV].asStack())
                .EUt(GTValues.VA[GTValues.LuV]) // 32768 EU/t
                .duration(300) // 15秒
                .save(provider);

        // ZPM中子加速器（装配线）
        GTRecipeTypes.ASSEMBLY_LINE_RECIPES.recipeBuilder("neutron_accelerator_zpm")
                .inputItems(CTNHItems.INVERTER.asStack(2))
                .inputItems(GTMachines.HULL[GTValues.ZPM].asStack())
                .inputItems(TagPrefix.cableGtSingle, GTMaterials.VanadiumGallium, 2)
                .inputItems(TagPrefix.plate, GTMaterials.NetherStar)
                .inputItems(TagPrefix.plate, GTMaterials.Polybenzimidazole, 8)
                .inputItems(TagPrefix.rodLong, GTMaterials.SamariumMagnetic, 4)
                .inputItems(GTItems.ELECTRIC_MOTOR_ZPM.asStack(2))
                .inputItems(TagPrefix.wireGtQuadruple, GTMaterials.UraniumTriplatinum, 4)
                .inputFluids(GTMaterials.Xenon.getFluid(3000))
                .outputItems(CTNHMachines.NEUTRON_ACCELERATOR[GTValues.ZPM].asStack())
                .scannerResearch(CTNHMachines.NEUTRON_ACCELERATOR[GTValues.LuV].asStack())
                .EUt(GTValues.VA[GTValues.ZPM]) // 98304 EU/t
                .duration(300) // 15秒
                .save(provider);

        // UV中子加速器（装配线）
        GTRecipeTypes.ASSEMBLY_LINE_RECIPES.recipeBuilder("neutron_accelerator_uv")
                .inputItems(CTNHItems.INVERTER.asStack(4))
                .inputItems(GTMachines.HULL[GTValues.UV].asStack())
                .inputItems(TagPrefix.cableGtSingle, GTMaterials.NaquadahAlloy, 2)
                .inputItems(TagPrefix.plate, GTMaterials.NetherStar, 2)
                .inputItems(TagPrefix.plate, GTMaterials.Polybenzimidazole, 4)
                .inputItems(GTItems.VOLTAGE_COIL_ZPM.asStack(4))
                .inputItems(TagPrefix.rodLong, GTMaterials.NickelZincFerrite, 16)
                .inputItems(GTItems.ELECTRIC_MOTOR_UV.asStack(2))
                .inputItems(TagPrefix.wireGtQuadruple, GTMaterials.IndiumTinBariumTitaniumCuprate, 4)
                .inputFluids(GTMaterials.Oganesson.getFluid(3000))
                .outputItems(CTNHMachines.NEUTRON_ACCELERATOR[GTValues.UV].asStack())
                .scannerResearch(CTNHMachines.NEUTRON_ACCELERATOR[GTValues.ZPM].asStack())
                .EUt(GTValues.VA[GTValues.UV]) // 393216 EU/t
                .duration(300) // 15秒
                .save(provider);
        // ULV中子加速器
        VanillaRecipeHelper.addShapedRecipe(
                provider, "neutron_accelerator_ulv",
                CTNHMachines.NEUTRON_ACCELERATOR[GTValues.ULV].asStack(),
                "ABC", "DEF", "ABC",
                'A', TagPrefix.cableGtSingle, GTMaterials.Lead,
                'B', TagPrefix.plate, GTMaterials.Lead,
                'C', TagPrefix.rotor, GTMaterials.Lead,
                'D', TagPrefix.plate, GTMaterials.Wood,
                'E', GTMachines.HULL[GTValues.ULV].asStack(),
                'F', CTNHItems.INVERTER.asStack()
        );

        // LV中子加速器
        VanillaRecipeHelper.addShapedRecipe(
                provider, "neutron_accelerator_lv",
                CTNHMachines.NEUTRON_ACCELERATOR[GTValues.LV].asStack(),
                "ABC", "DEF", "ABC",
                'A', TagPrefix.cableGtSingle, GTMaterials.Tin,
                'B', TagPrefix.plateDouble, GTMaterials.Lead,
                'C', GTItems.ELECTRIC_MOTOR_LV.asStack(),
                'D', TagPrefix.plate, GTMaterials.Rubber,
                'E', GTMachines.HULL[GTValues.LV].asStack(),
                'F', CTNHItems.INVERTER.asStack()
        );
         // MV脱水机
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "dehydrator_mv",
                CTNHMachines.DEHYDRATOR[GTValues.MV].asStack(),
                "ABA", "CDC", "EFE",
                'A', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:fine_red_alloy_wire")),
                'B', CustomTags.MV_CIRCUITS,
                'C', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:copper_quadruple_cable")),
                'D', GTMachines.HULL[GTValues.MV].asStack(),
                'E', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:steel_gear")),
                'F', GTItems.ROBOT_ARM_MV
        );

           //HV脱水机
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "dehydrator_hv",
                CTNHMachines.DEHYDRATOR[GTValues.HV].asStack(),
                "ABA", "CDC", "EFE",
                'A', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:fine_electrum_wire")),
                'B', CustomTags.HV_CIRCUITS,
                'C', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:silver_quadruple_cable")),
                'D', GTMachines.HULL[GTValues.HV].asStack(),
                'E', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:potin_gear")),
                'F', GTItems.ROBOT_ARM_HV
        );

           //EV脱水机
         VanillaRecipeHelper.addShapedRecipe(
                 provider, true, "dehydrator_ev",
                 CTNHMachines.DEHYDRATOR[GTValues.EV].asStack(),
                 "ABA", "CDC", "EFE",
                 'A', GTItems.VOLTAGE_COIL_EV,
                 'B', CustomTags.EV_CIRCUITS,
                 'C', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:aluminium_quadruple_cable")),
                 'D', GTMachines.HULL[GTValues.EV].asStack(),
                 'E', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:tungsten_steel_gear")),
                 'F', GTItems.ROBOT_ARM_EV
         );

           //IV脱水机
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "dehydrator_iv",
                CTNHMachines.DEHYDRATOR[GTValues.IV].asStack(),
                "ABA", "CDC", "EFE",
                'A', GTItems.VOLTAGE_COIL_IV, // IV级电压线圈（直接引用，无TagPrefix）
                'B', CustomTags.IV_CIRCUITS,  // IV级电路
                'C', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:tungsten_quadruple_cable")), // 四重钨电缆
                'D', GTMachines.HULL[GTValues.IV].asStack(), // IV级机器外壳
                'E', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:nichrome_gear")), // 镍铬合金齿轮
                'F', GTItems.ROBOT_ARM_IV     // IV级机械臂
        );

          //LuV脱水机
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "dehydrator_luv",
                CTNHMachines.DEHYDRATOR[GTValues.LuV].asStack(),
                "ABA", "CDC", "EFE",
                'A', GTItems.VOLTAGE_COIL_LuV, // LuV级电压线圈
                'B', CustomTags.LuV_CIRCUITS,  // LuV级电路
                'C', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:naquadah_quadruple_cable")),
                'D', GTMachines.HULL[GTValues.LuV].asStack(), // LuV级机器外壳
                'E', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:ultimet_gear")), // Ultimet合金齿轮
                'F', GTItems.ROBOT_ARM_LuV    // LuV级机械臂
        );

           //ZPM脱水机
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "dehydrator_zpm",
                CTNHMachines.DEHYDRATOR[GTValues.ZPM].asStack(),
                "ABA", "CDC", "EFE",
                'A', GTItems.VOLTAGE_COIL_ZPM, // ZPM级电压线圈
                'B', CustomTags.ZPM_CIRCUITS,  // ZPM级电路
                'C', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:osmium_quadruple_cable")), // 四重锇电缆
                'D', GTMachines.HULL[GTValues.ZPM].asStack(), // ZPM级机器外壳
                'E', ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:zeron_100_gear")), // Zeron-100合金齿轮
                'F', GTItems.ROBOT_ARM_ZPM     // ZPM级机械臂
        );
    }
}



