package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.recipe.NeutronActivatorCondition;
import io.github.cpearl0.ctnhcore.common.recipe.PlantCasingCondition;
import io.github.cpearl0.ctnhcore.data.machines.GTNNMachines;
import io.github.cpearl0.ctnhcore.data.materials.BedrockMaterials;
import io.github.cpearl0.ctnhcore.data.materials.PlatinumLineMaterials;
import io.github.cpearl0.ctnhcore.data.materials.UncategorizedMaterials;
import io.github.cpearl0.ctnhcore.registry.*;
import io.github.cpearl0.ctnhcore.registry.machines.CTNHMachines;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.GTNNMultiblocks;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksB;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.common.data.machines.GCYMMachines;
import com.gregtechceu.gtceu.common.data.machines.GTMultiMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.createmod.catnip.data.Pair;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import appeng.core.definitions.AEItems;
import tech.luckyblock.mcmod.ctnhenergy.registry.CEMultiblock;

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
            7, Pair.of(() -> CTNHItems.EncapsulatedPlutonium.get(), () -> CTNHItems.EnrichedPlutoniumNugget.get()));

    public static void init(Consumer<FinishedRecipe> provider) {
        // 注册所有爆破配方
        HEAVY_PLATE_TIERS.forEach((level, items) -> {
            ItemStack ingot = new ItemStack(items.getFirst().get());
            ItemStack plate = new ItemStack(items.getSecond().get());

            // TNT爆破版
            // 普通TNT版
            GTRecipeTypes.IMPLOSION_RECIPES.recipeBuilder(CTNHCore.id("implode_heavy_plate_t" + level + "_tnt"))
                    .inputItems(ingot)
                    .outputItems(plate)
                    .chancedOutput(TagPrefix.dust, GTMaterials.DarkAsh, 2500, 0)
                    .explosivesType(new ItemStack(Items.TNT, level * 2))
                    .duration(200 - 20 * level)
                    .save(provider);

            // 工业TNT版
            GTRecipeTypes.IMPLOSION_RECIPES.recipeBuilder(CTNHCore.id("implode_heavy_plate_t" + level + "_itnt"))
                    .inputItems(ingot)
                    .outputItems(plate)
                    .chancedOutput(TagPrefix.dust, GTMaterials.DarkAsh, 2500, 0)
                    .explosivesType(new ItemStack(GTBlocks.INDUSTRIAL_TNT.get(), level))
                    .duration(100 - 10 * level)
                    .save(provider);

            // 火药桶版
            GTRecipeTypes.IMPLOSION_RECIPES
                    .recipeBuilder(CTNHCore.id("implode_heavy_plate_t" + level + "_powderbarrel"))
                    .inputItems(ingot)
                    .outputItems(plate)
                    .chancedOutput(TagPrefix.dust, GTMaterials.DarkAsh, 2500, 0)
                    .explosivesType(new ItemStack(GTBlocks.POWDERBARREL, level * 3))
                    .duration(150 - 15 * level)
                    .save(provider);

            // 炸药版
            GTRecipeTypes.IMPLOSION_RECIPES.recipeBuilder(CTNHCore.id("implode_heavy_plate_t" + level + "_dynamite"))
                    .inputItems(ingot)
                    .outputItems(plate)
                    .chancedOutput(TagPrefix.dust, GTMaterials.DarkAsh, 2500, 0)
                    .explosivesType(GTItems.DYNAMITE.asStack(level * 4))
                    .duration(200 - 20 * level)
                    .save(provider);
        });

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("lightning_rod_assembler"))
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
                'B', ChemicalHelper.get(TagPrefix.rodLong, GTMaterials.Copper));

        // T1芯片
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("t1_chip"))
                .inputItems(CTNHItems.COMPUTER.asStack())
                .inputItems(GTItems.COVER_SCREEN.asStack())
                .inputItems(GTItems.SENSOR_HV.asStack())
                .inputItems(GTItems.EMITTER_HV.asStack())
                .inputItems(CTNHItems.HEAVY_PLATE_T1.asStack())
                .inputItems(CTNHItems.PROGRAM_ROCKET_1)
                .inputFluids(GTMaterials.SolderingAlloy.getFluid(576))
                .outputItems(CTNHItems.CHIP_T1.asStack())
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(600) // 30秒
                .EUt(GTValues.VA[GTValues.HV])
                .save(provider);

        // T2芯片
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("t2_chip"))
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
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("t3_chip"))
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
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("t4_chip"))
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
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("computer_normal"))
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
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("computer_advanced"))
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
        CTNHRecipeTypes.PRECISION_ASSEMBLY_RECIPES.recipeBuilder(CTNHCore.id("quark_core_assembly"))
                .inputItems(CustomTags.IV_CIRCUITS, 2)
                .inputItems(TagPrefix.lens, GTMaterials.Diamond, 8)
                .inputItems(GTItems.NAND_MEMORY_CHIP.asStack(16))
                .inputItems(TagPrefix.rotor, GTMaterials.Aluminium)
                .inputFluids(GTMaterials.Polyethylene.getFluid(576))
                .inputFluids(GTMaterials.SodiumPotassium.getFluid(288))
                .inputFluids(GTMaterials.Lubricant.getFluid(144))
                .inputFluids(GTMaterials.StyreneButadieneRubber.getFluid(144))
                .CWUt(8)
                .outputItems(CTNHItems.QuarkCore)
                .EUt(GTValues.VA[GTValues.LuV])  // 32768 EU/t
                .duration(100)  // 5秒
                .save(provider);

        // 石墨 + 铀238 -> 铀石墨混合物
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("graphite_uranium_mixture")
                .inputItems(TagPrefix.dust, GTMaterials.Graphite, 3)
                .inputItems(TagPrefix.dust, GTMaterials.Uranium238)
                .outputItems(TagPrefix.dust, CTNHMaterials.GraphiteUraniumMixture, 4)
                .EUt(GTValues.VA[GTValues.LV]) // 30 EU/t
                .duration(34) // 1.7秒
                .save(provider);

        // 铀石墨混合物 + 碳化钨箔 -> 封装铀
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("encapsulated_uranium")
                .inputItems(TagPrefix.dust, CTNHMaterials.GraphiteUraniumMixture, 4)
                .inputItems(TagPrefix.foil, GTMaterials.TungstenCarbide, 2)
                .outputItems(CTNHItems.EncapsulatedUranium)
                .EUt(GTValues.VA[GTValues.HV]) // 480 EU/t
                .duration(1400) // 70秒
                .save(provider);

        // 钍 + 铀235 + 碳 -> 铀碳化钍混合物
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("uranium_carbide_thorium_mixture")
                .inputItems(TagPrefix.dust, GTMaterials.Thorium, 11)
                .inputItems(TagPrefix.dust, CTNHMaterials.Thorium232)
                .inputItems(TagPrefix.dust, GTMaterials.Uranium235)
                .inputItems(TagPrefix.dust, GTMaterials.Carbon, 3)
                .outputItems(TagPrefix.dust, CTNHMaterials.UraniumCarbideThoriumMixture, 16)
                .EUt(GTValues.VA[GTValues.LV]) // 30 EU/t
                .duration(47) // 2.35秒
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
        GTRecipeTypes.FUSION_RECIPES.recipeBuilder(CTNHCore.id("californium_production"))
                .inputFluids(GTMaterials.Plutonium239.getFluid(48))
                .inputFluids(GTMaterials.Beryllium.getFluid(48))
                .outputFluids(GTMaterials.Californium.getFluid(48))
                .fusionStartEU(120_000_000)
                .EUt(GTValues.VA[GTValues.ZPM]) // 196608 EU/t
                .duration(240) // 12秒
                .save(provider);

        // 8. 气奥生产（聚变）
        GTRecipeTypes.FUSION_RECIPES.recipeBuilder(CTNHCore.id("oganesson_production"))
                .inputFluids(GTMaterials.Californium.getFluid(32))
                .inputFluids(GTMaterials.Calcium.getFluid(720))
                .outputFluids(GTMaterials.Oganesson.getFluid(720))
                .fusionStartEU(600_000_000)
                .EUt(GTValues.VA[GTValues.ZPM]) // 196608 EU/t
                .duration(240) // 12秒
                .save(provider);

        // 9. 逆变器组装
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("inverter_assembly"))
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
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder(CTNHCore.id("thorium_based_liquid_fuel"))
                .inputItems(CTNHItems.EnrichedThorium)
                .inputItems(TagPrefix.dust, GTMaterials.Lithium, 4)
                .inputFluids(GTMaterials.Mercury.getFluid(1000))
                .outputFluids(CTNHMaterials.ThoriumBasedLiquidFuel.getFluid(1000))
                .circuitMeta(2)
                .EUt(GTValues.VHA[GTValues.HV]) // 480 EU/t
                .duration(3000) // 150秒
                .save(provider);

        // 2. 铀基液体燃料
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder(CTNHCore.id("uranium_based_liquid_fuel"))
                .inputItems(CTNHItems.EnrichedUranium)
                .inputItems(TagPrefix.dust, GTMaterials.Potassium, 8)
                .inputFluids(GTMaterials.Radon.getFluid(1000))
                .outputFluids(CTNHMaterials.UraniumBasedLiquidFuel.getFluid(1000))
                .circuitMeta(1)
                .EUt(GTValues.VHA[GTValues.LuV]) // 32768 EU/t
                .duration(200) // 10秒
                .save(provider);

        // 3. 钚基液体燃料
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder(CTNHCore.id("plutonium_based_liquid_fuel"))
                .inputItems(CTNHItems.EnrichedPlutonium)
                .inputItems(TagPrefix.dust, GTMaterials.Neutronium, 8)
                .inputItems(TagPrefix.dust, GTMaterials.Caesium, 16)
                .inputItems(TagPrefix.dust, GTMaterials.Naquadah, 2)
                .outputFluids(CTNHMaterials.PlutoniumBasedLiquidFuel.getFluid(1000))
                .circuitMeta(1)
                .EUt(GTValues.VA[GTValues.LuV]) // 32768 EU/t
                .duration(360) // 18秒
                .save(provider);

        // 4. 激发态钍基燃料
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder(CTNHCore.id("thorium_based_liquid_fuel_excited"))
                .inputFluids(CTNHMaterials.ThoriumBasedLiquidFuel.getFluid(1000))
                .inputFluids(GTMaterials.Helium.getFluid(250))
                .outputFluids(CTNHMaterials.ThoriumBasedLiquidFuelExcited.getFluid(1000))
                .circuitMeta(1)
                .EUt(GTValues.VHA[GTValues.IV]) // 1920 EU/t
                .duration(120) // 6秒
                .save(provider);

        // 5. 中子活化铀基燃料（激发态）
        CTNHRecipeTypes.NEUTRON_ACTIVATOR_RECIPES.recipeBuilder(CTNHCore.id("uranium_based_liquid_fuel_excited"))
                .notConsumable(TagPrefix.plate, GTMaterials.Tungsten)
                .inputFluids(CTNHMaterials.UraniumBasedLiquidFuel.getFluid(100))
                .outputFluids(CTNHMaterials.UraniumBasedLiquidFuelExcited.getFluid(100))
                .addCondition(new NeutronActivatorCondition(450, 550))
                .duration(80) // 4秒
                .save(provider);

        // 6. 聚变激发铀基燃料
        GTRecipeTypes.FUSION_RECIPES.recipeBuilder(CTNHCore.id("uranium_based_liquid_fuel_excited_fusion"))
                .inputFluids(CTNHMaterials.UraniumBasedLiquidFuel.getFluid(10))
                .inputFluids(GTMaterials.Hydrogen.getFluid(100))
                .outputFluids(CTNHMaterials.UraniumBasedLiquidFuelExcited.getFluid(10))
                .fusionStartEU(200_000_000)
                .EUt(GTValues.VA[GTValues.IV]) // 1920 EU/t
                .duration(40) // 2秒
                .save(provider);

        // 7. 中子活化钚基燃料（激发态）
        CTNHRecipeTypes.NEUTRON_ACTIVATOR_RECIPES.recipeBuilder(CTNHCore.id("plutonium_based_liquid_fuel_excited"))
                .notConsumable(TagPrefix.plate, GTMaterials.Tritanium)
                .inputFluids(CTNHMaterials.PlutoniumBasedLiquidFuel.getFluid(100))
                .outputFluids(CTNHMaterials.PlutoniumBasedLiquidFuelExcited.getFluid(100))
                .addCondition(new NeutronActivatorCondition(500, 600))
                .duration(80) // 4秒
                .save(provider);

        // 8. 聚变激发钚基燃料
        GTRecipeTypes.FUSION_RECIPES.recipeBuilder(CTNHCore.id("plutonium_based_liquid_fuel_excited_fusion"))
                .inputFluids(GTMaterials.Lutetium.getFluid(16))
                .inputFluids(CTNHMaterials.PlutoniumBasedLiquidFuel.getFluid(20))
                .outputFluids(CTNHMaterials.PlutoniumBasedLiquidFuelExcited.getFluid(20))
                .fusionStartEU(220_000_000)
                .EUt(GTValues.VA[GTValues.LuV]) // 32768 EU/t
                .duration(20) // 1秒
                .save(provider);

        // 9. 钍基燃料耗尽（中子活化）
        CTNHRecipeTypes.NEUTRON_ACTIVATOR_RECIPES.recipeBuilder(CTNHCore.id("thorium_based_liquid_fuel_depleted"))
                .inputFluids(CTNHMaterials.ThoriumBasedLiquidFuelExcited.getFluid(200))
                .outputFluids(CTNHMaterials.ThoriumBasedLiquidFuelDepleted.getFluid(200))
                .addCondition(new NeutronActivatorCondition(500, 700))
                .duration(10000) // 500秒
                .save(provider);

        // 10. 钍基燃料耗尽（大型反应堆）
        CTNHRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES
                .recipeBuilder(CTNHCore.id("thorium_based_liquid_fuel_depleted_reactor"))
                .inputFluids(CTNHMaterials.ThoriumBasedLiquidFuelExcited.getFluid(1000))
                .outputFluids(CTNHMaterials.ThoriumBasedLiquidFuelDepleted.getFluid(1000))
                .EUt(-2200) // 输出功率
                .duration(500) // 25秒
                .save(provider);

        // 1. 铀基耗尽燃料（大型反应堆）
        CTNHRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder(CTNHCore.id("uranium_based_liquid_fuel_depleted"))
                .inputFluids(CTNHMaterials.UraniumBasedLiquidFuelExcited.getFluid(1000))
                .outputFluids(CTNHMaterials.UraniumBasedLiquidFuelDepleted.getFluid(1000))
                .EUt(-12960) // 输出12,960 EU/t
                .duration(100) // 5秒
                .save(provider);

        // 2. 钚基耗尽燃料（大型反应堆）
        CTNHRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES
                .recipeBuilder(CTNHCore.id("plutonium_based_liquid_fuel_depleted"))
                .inputFluids(CTNHMaterials.PlutoniumBasedLiquidFuelExcited.getFluid(1000))
                .outputFluids(CTNHMaterials.PlutoniumBasedLiquidFuelDepleted.getFluid(1000))
                .EUt(-32400) // 输出32,400 EU/t
                .duration(150) // 7.5秒
                .save(provider);

        // 3. 钍基耗尽燃料离心
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("thorium_based_liquid_fuel_depleted_centrifuge"))
                .inputFluids(CTNHMaterials.ThoriumBasedLiquidFuelDepleted.getFluid(500))
                .outputItems(TagPrefix.dust, CTNHMaterials.Thorium232, 32) // 固定输出
                .chancedOutput(TagPrefix.dust, CTNHMaterials.Thorium232, 8, 8000, 0) // 80%额外
                .outputItems(TagPrefix.dust, GTMaterials.Praseodymium, 32)
                .chancedOutput(TagPrefix.dust, GTMaterials.Praseodymium, 16, 8000, 0)
                .chancedOutput(TagPrefix.dust, GTMaterials.Boron, 3, 3000, 0) // 30%概率
                .chancedOutput(TagPrefix.dust, GTMaterials.Indium, 2, 5000, 0) // 50%概率
                .circuitMeta(1)
                .EUt(1040) // EV级
                .duration(750) // 37.5秒
                .save(provider);

        // 4. 铀基耗尽燃料离心
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("uranium_based_liquid_fuel_depleted_centrifuge"))
                .inputFluids(CTNHMaterials.UraniumBasedLiquidFuelDepleted.getFluid(1000))
                .chancedOutput(TagPrefix.dust, GTMaterials.Lead, 16, 6000, 0) // 60%概率
                .chancedOutput(TagPrefix.dust, GTMaterials.Bismuth, 1, 1000, 0) // 10%概率
                .chancedOutput(TagPrefix.dust, GTMaterials.Barium, 6, 5000, 0) // 50%概率
                .circuitMeta(1)
                .EUt(1040) // EV级
                .duration(1000) // 50秒
                .save(provider);

        // 5. 钚基耗尽燃料离心
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("plutonium_based_liquid_fuel_depleted_centrifuge"))
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
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("radiation_protection_plate_iridium"))
                .inputItems(TagPrefix.plateDense, GTMaterials.Iridium, 8)
                .inputItems(TagPrefix.plateDense, GTMaterials.NaquadahAlloy, 8)
                .inputFluids(GTMaterials.Lead.getFluid(1152))
                .outputItems(CTNHItems.PlateRadiationProtection)
                .circuitMeta(1)
                .EUt(GTValues.VA[GTValues.EV]) // 1920 EU/t
                .duration(400) // 20秒
                .save(provider);

        // 7. 辐射防护板（镧版）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("radiation_protection_plate_lanthanum"))
                .inputItems(TagPrefix.plateDense, GTMaterials.Lanthanum, 4)
                .inputItems(TagPrefix.plateDense, GTMaterials.NaquadahAlloy, 8)
                .inputFluids(GTMaterials.Lead.getFluid(1152))
                .outputItems(CTNHItems.PlateRadiationProtection)
                .circuitMeta(1)
                .EUt(GTValues.VA[GTValues.EV]) // 1920 EU/t
                .duration(400) // 20秒
                .save(provider);

        // 9. Naquadah基液体燃料
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder(CTNHCore.id("naquadah_based_liquid_fuel"))
                .inputItems(TagPrefix.dust, GTMaterials.Naquadria, 42)
                .inputItems(TagPrefix.dust, GTMaterials.Cerium, 16)
                .inputItems(TagPrefix.dust, GTMaterials.Neodymium, 16)
                .outputFluids(CTNHMaterials.NaquadahBasedLiquidFuel.getFluid(1000))
                .EUt(GTValues.VA[GTValues.IV]) // 1920 EU/t
                .duration(300) // 15秒
                .save(provider);

        // 10. Naquadah基激发态燃料（聚变）
        GTRecipeTypes.FUSION_RECIPES.recipeBuilder(CTNHCore.id("naquadah_based_liquid_fuel_excited"))
                .inputFluids(CTNHMaterials.NaquadahBasedLiquidFuel.getFluid(800))
                .inputFluids(GTMaterials.Radon.getFluid(200))
                .outputFluids(CTNHMaterials.NaquadahBasedLiquidFuelExcited.getFluid(100))
                .fusionStartEU(320_000_000) // 320M EU启动
                .EUt(26000) // IV级
                .duration(500) // 25秒
                .save(provider);

        // 11. Naquadah基耗尽燃料（大型反应堆）
        CTNHRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder(CTNHCore.id("naquadah_based_liquid_fuel_depleted"))
                .inputFluids(CTNHMaterials.NaquadahBasedLiquidFuelExcited.getFluid(1))
                .outputFluids(CTNHMaterials.NaquadahBasedLiquidFuelDepleted.getFluid(1))
                .EUt(-975_000) // 输出975,000 EU/t
                .duration(60) // 3秒
                .save(provider);

        // 12. Naquadah基耗尽燃料离心
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("naquadah_based_liquid_fuel_depleted_centrifuge"))
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
        CTNHRecipeTypes.CHEMICAL_PLANT_RECIPES.recipeBuilder(CTNHCore.id("thorium232_production"))
                .inputItems(TagPrefix.dust, GTMaterials.Thorium, 16)
                .inputItems(TagPrefix.dust, GTMaterials.Borax, 12)
                .inputFluids(GTMaterials.DistilledWater.getFluid(2000))
                .inputFluids(GTMaterials.HydrochloricAcid.getFluid(1000))
                .outputItems(TagPrefix.dustSmall, GTMaterials.Thorium, 32)
                .outputItems(TagPrefix.dust, CTNHMaterials.Thorium232, 2)
                .chancedOutput(TagPrefix.dustSmall, CTNHMaterials.Thorium232, 2, 1000, 0) // 10%概率
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
                'A', ChemicalHelper.get(TagPrefix.foil, GTMaterials.StainlessSteel),
                'B', CustomTags.IV_CIRCUITS,
                'C', GTBlocks.CASING_STEEL_SOLID.asItem());

        // 2. 铱机器外壳（工作台）
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "iridium_machine_casing",
                CTNHBlocks.IRIDIUM_CASING.asStack(),
                "ABA", "ACA", "ABA",
                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Iridium),
                'B', ChemicalHelper.get(TagPrefix.screw, GTMaterials.Iridium),
                'C', ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Iridium));

        // 3. 聚苯并咪唑管道（工作台）
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "polybenzimidazole_pipe",
                CTNHBlocks.CASING_POLYBENZIMIDAZOLE_PIPE.asStack(),
                "ABA", "BCB", "ABA",
                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Polybenzimidazole),
                'B', ChemicalHelper.get(TagPrefix.pipeNormalFluid, GTMaterials.Polybenzimidazole),
                'C', ChemicalHelper.get(TagPrefix.frameGt, GTMaterials.Polybenzimidazole));

        // 4. 高级过滤外壳（组装机）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("advanced_filter_casing"))
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
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("iridium_machine_casing_assembler"))
                .inputItems(TagPrefix.plate, GTMaterials.Iridium, 6)
                .inputItems(TagPrefix.screw, GTMaterials.Iridium, 2)
                .inputItems(TagPrefix.gearSmall, GTMaterials.Iridium, 1)
                .outputItems(CTNHBlocks.IRIDIUM_CASING.asStack())
                .EUt(GTValues.VA[GTValues.LV]) // 30 EU/t
                .duration(200) // 10秒
                .save(provider);

        // 6. 高速管道方块（组装机）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("high_speed_pipe_block"))
                .inputItems(TagPrefix.pipeHugeFluid, GTMaterials.StainlessSteel)
                .inputItems(TagPrefix.frameGt, GTMaterials.BlueAlloy, 32)
                .inputItems(TagPrefix.wireGtSingle, GTMaterials.MercuryBariumCalciumCuprate, 32)
                .inputItems(TagPrefix.plate, GTMaterials.Beryllium, 32)
                .inputItems(CustomTags.IV_CIRCUITS)
                .outputItems(CTNHBlocks.HIGH_SPEED_PIPE_BLOCK.asStack())
                .EUt(GTValues.VA[GTValues.EV]) // 1920 EU/t
                .duration(300) // 15秒
                .save(provider);

        // 7. 锇硼硅玻璃（流体固化）
        // GTRecipeTypes.FLUID_SOLIDFICATION_RECIPES.recipeBuilder(CTNHCore.id("osmium_borosilicate_glass"))
        // .inputItems(TagPrefix.block, GTMaterials.BorosilicateGlass)
        // .inputFluids(GTMaterials.Osmium.getFluid(1152))
        // .outputItems(CTNHBlocks.OSMIUM_BOROSILICATE_GLASS.asStack())
        // .EUt(GTValues.VA[GTValues.ZPM]) // 98304 EU/t
        // .duration(800) // 40秒
        // .save(provider);

        // 8. 防辐射机器外壳（组装机）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("radiation_proof_machine_casing"))
                .inputItems(TagPrefix.plateDense, GTMaterials.Lead, 6)
                .inputItems(TagPrefix.frameGt, GTMaterials.TungstenSteel)
                .inputFluids(GTMaterials.Concrete.getFluid(1296))
                .outputItems(CTNHBlocks.RADIATION_PROOF_MACHINE_CASING.asStack())
                .EUt(GTValues.VA[GTValues.EV]) // 1920 EU/t
                .duration(40) // 2秒
                .save(provider);

        // 9. MAR辐射防护外壳（组装机）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("field_restriction_casing"))
                .inputItems(CTNHItems.PlateRadiationProtection.asStack(6))
                .inputItems(TagPrefix.frameGt, GTMaterials.Europium)
                .inputItems(GTItems.FIELD_GENERATOR_MV)
                .outputItems(CTNHBlocks.FIELD_RESTRICTION_CASING.asStack())
                .circuitMeta(1)
                .EUt(GTValues.VA[GTValues.EV]) // 1920 EU/t
                .duration(400) // 20秒
                .save(provider);

        // 10. 防辐射框架（组装机）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("frame_radiation"))
                .inputItems(TagPrefix.rodLong, GTMaterials.NaquadahAlloy, 8)
                .inputItems(TagPrefix.frameGt, GTMaterials.HSSE, 4)
                .outputItems(TagPrefix.frameGt, CTNHMaterials.RadiationProtection)
                .circuitMeta(24)
                .EUt(GTValues.VA[GTValues.EV]) // 1920 EU/t
                .duration(320) // 16秒
                .save(provider);

        // 11. 中子混合物处理（混合机）
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder(CTNHCore.id("neutronium_mixture"))
                .inputItems(TagPrefix.dust, CTNHMaterials.NeutroniumMixture, 4)
                .inputFluids(GTMaterials.Americium.getFluid(FluidStorageKeys.PLASMA, 144))
                .inputFluids(GTMaterials.Naquadria.getFluid(144))
                .outputItems(TagPrefix.dust, GTMaterials.Neutronium)
                .circuitMeta(1)
                .EUt(GTValues.VA[GTValues.ZPM]) // 98304 EU/t
                .duration(200) // 10秒
                .save(provider);

        // 11.5 谢尔顿矿粉电解
        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("cooperite_dust"))
                .inputItems(TagPrefix.dust, GTMaterials.Cooperite, 6)
                .outputItems(TagPrefix.dust, PlatinumLineMaterials.PlatinumOre, 3)
                .outputItems(TagPrefix.dust, GTMaterials.Nickel)
                .outputItems(TagPrefix.dust, GTMaterials.Sulfur)
                .outputItems(TagPrefix.dust, PlatinumLineMaterials.PalladiumOre)
                .EUt(GTValues.VA[GTValues.MV]) // 98304 EU/t
                .duration(600) // 30秒
                .save(provider);

        // 12. 中子矿离心（无序合成）
        VanillaRecipeHelper.addShapelessRecipe(
                provider,
                "centrifuged_ore_to_dust_neutronium",
                ChemicalHelper.get(TagPrefix.dust, CTNHMaterials.NeutroniumMixture),
                'h',
                ChemicalHelper.get(TagPrefix.crushedRefined, GTMaterials.Neutronium));

        // 1. 电解糖
        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("sugar_ele"))
                .inputItems(Items.SUGAR, 24)
                .outputItems(TagPrefix.dust, GTMaterials.Coal, 6)
                .outputFluids(GTMaterials.Hydrogen.getFluid(12000))
                .outputFluids(GTMaterials.Oxygen.getFluid(6000))
                .EUt(GTValues.VA[GTValues.LV]) // 98304 EU/t
                .duration(320) // 30秒
                .save(provider);

        // 2. 催化剂仓
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("catalyst_hatch"))
                .inputItems(CustomTags.MV_CIRCUITS)
                .inputItems(GTMachines.ITEM_IMPORT_BUS[GTValues.HV].asStack())
                .inputItems(GTMachines.ITEM_EXPORT_BUS[GTValues.HV].asStack())
                .inputItems(GTBlocks.MACHINE_CASING_EV.asItem())
                .circuitMeta(1)
                .outputItems(CTNHMachines.CATALYST_HATCH.asStack())
                .EUt(GTValues.VA[GTValues.HV])
                .duration(300)
                .save(provider);

        // 3. 大型Naquadah脱水机（组装机）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("large_naquadah_reactor_casing"))
                .inputItems(GCYMBlocks.CASING_HIGH_TEMPERATURE_SMELTING.asItem())
                .inputItems(TagPrefix.wireGtHex, GTMaterials.IndiumTinBariumTitaniumCuprate, 4)
                .inputItems(GTItems.BATTERY_LuV_VANADIUM.asStack(1))
                .inputItems(GTItems.ROBOT_ARM_EV.asStack(4))
                .inputItems(TagPrefix.plate, GTMaterials.Zeron100, 8)
                .inputItems(CustomTags.IV_CIRCUITS, 8)
                .inputFluids(GTMaterials.Zeron100.getFluid(2880))
                .outputItems(GTNNMultiblocks.LARGE_DEHYDRATOR.asStack())
                .EUt(GTValues.VA[GTValues.LuV]) // 32768 EU/t
                .duration(2400) // 120秒
                .save(provider);

        // 4. 大型Naquadah反应堆（装配线）
        GTRecipeTypes.ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("large_naquadah_reactor"))
                .inputItems(TagPrefix.frameGt, GTMaterials.Neutronium, 8)
                .inputItems(CTNHItems.PlateRadiationProtection.asStack(16))
                .inputItems(GTItems.FIELD_GENERATOR_ZPM.asStack(2))
                .inputItems(GTItems.ELECTRIC_PUMP_ZPM.asStack(8))
                .inputItems(CustomTags.UV_CIRCUITS, 4)
                .inputItems(TagPrefix.wireGtOctal, GTMaterials.IndiumTinBariumTitaniumCuprate, 8)
                .inputItems(TagPrefix.pipeHugeFluid, GTMaterials.Naquadah, 4)
                .inputItems(TagPrefix.plate, GTMaterials.NaquadahAlloy, 8)
                .inputItems(TagPrefix.screw, GTMaterials.Osmium, 16)
                .outputItems(GTNNMultiblocks.LARGE_NAQUADAH_REACTOR.asStack())
                .scannerResearch(CTNHMachines.NAQUADAH_REACTOR[GTValues.LuV].asStack())
                .EUt(GTValues.VA[GTValues.ZPM])   // 98304 EU/t
                .duration(4200) // 210秒
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("neutron_sensor"))
                .inputItems(GTBlocks.MACHINE_CASING_IV.asStack())
                .inputItems(GTItems.COVER_ACTIVITY_DETECTOR)
                .inputItems(GTItems.COVER_SCREEN)
                .inputItems(TagPrefix.plate, GTMaterials.VanadiumGallium, 4)
                .inputItems(CustomTags.EV_CIRCUITS)
                .inputItems(GTItems.SENSOR_HV.asStack(2))
                .circuitMeta(1)
                .outputItems(GTNNMachines.NEUTRON_SENSOR.asStack())
                .EUt(GTValues.VA[GTValues.EV]) // 1920 EU/t
                .duration(300) // 15秒
                .save(provider);

        // 13. 中子活化器（精密装配）
        CTNHRecipeTypes.PRECISION_ASSEMBLY_RECIPES.recipeBuilder(CTNHCore.id("neutron_activator"))
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
                'A', ChemicalHelper.get(TagPrefix.plateDense, GTMaterials.Steel), // 致密钢板
                'B', CTNHItems.EnrichedUranium.asStack() // 富集铀（直接引用）
        );

        // MV中子加速器
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("neutron_accelerator_mv"))
                .inputItems(CTNHItems.INVERTER.asStack())
                .inputItems(GTMachines.HULL[GTValues.MV].asStack())
                .inputItems(TagPrefix.cableGtSingle, GTMaterials.Copper, 2)
                .inputItems(TagPrefix.plate, GTMaterials.Polyethylene)
                .inputItems(TagPrefix.plate, GTMaterials.Beryllium, 2)
                .inputItems(GTItems.ELECTRIC_MOTOR_MV)
                .outputItems(GTNNMachines.NEUTRON_ACCELERATOR[GTValues.MV].asStack())
                .EUt(GTValues.VA[GTValues.MV]) // 120 EU/t
                .duration(300) // 15秒
                .save(provider);

        // HV中子加速器
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("neutron_accelerator_hv"))
                .inputItems(CTNHItems.INVERTER.asStack())
                .inputItems(GTMachines.HULL[GTValues.HV].asStack())
                .inputItems(TagPrefix.cableGtSingle, GTMaterials.Gold, 2)
                .inputItems(TagPrefix.plate, GTMaterials.PolyvinylChloride)
                .inputItems(TagPrefix.plateDouble, GTMaterials.Beryllium, 2)
                .inputItems(GTItems.ELECTRIC_MOTOR_HV.asStack(2))
                .outputItems(GTNNMachines.NEUTRON_ACCELERATOR[GTValues.HV].asStack())
                .EUt(GTValues.VA[GTValues.HV]) // 480 EU/t
                .duration(300) // 15秒
                .save(provider);

        // EV中子加速器
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("neutron_accelerator_ev"))
                .inputItems(CTNHItems.INVERTER.asStack())
                .inputItems(GTMachines.HULL[GTValues.EV].asStack())
                .inputItems(TagPrefix.cableGtSingle, GTMaterials.Aluminium, 2)
                .inputItems(TagPrefix.plate, GTMaterials.StyreneButadieneRubber)
                .inputItems(TagPrefix.plate, GTMaterials.SteelMagnetic, 4)
                .inputItems(TagPrefix.plate, GTMaterials.TungstenCarbide, 2)
                .inputItems(GTItems.ELECTRIC_MOTOR_EV.asStack(2))
                .outputItems(GTNNMachines.NEUTRON_ACCELERATOR[GTValues.EV].asStack())
                .EUt(GTValues.VA[GTValues.EV]) // 1920 EU/t
                .duration(300) // 15秒
                .save(provider);

        // IV中子加速器
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("neutron_accelerator_iv"))
                .inputItems(CTNHItems.INVERTER.asStack())
                .inputItems(GTMachines.HULL[GTValues.IV].asStack())
                .inputItems(TagPrefix.cableGtSingle, GTMaterials.Tungsten, 2)
                .inputItems(TagPrefix.plate, GTMaterials.SiliconeRubber)
                .inputItems(TagPrefix.plate, GTMaterials.SteelMagnetic, 4)
                .inputItems(TagPrefix.plateDouble, GTMaterials.TungstenCarbide, 2)
                .inputItems(GTItems.ELECTRIC_MOTOR_IV.asStack(2))
                .outputItems(GTNNMachines.NEUTRON_ACCELERATOR[GTValues.IV].asStack())
                .EUt(GTValues.VA[GTValues.IV]) // 1920 EU/t
                .duration(300) // 15秒
                .save(provider);

        // LuV中子加速器（装配线）
        GTRecipeTypes.ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("neutron_accelerator_luv"))
                .inputItems(CTNHItems.INVERTER.asStack(2))
                .inputItems(GTMachines.HULL[GTValues.LuV].asStack())
                .inputItems(TagPrefix.cableGtSingle, GTMaterials.YttriumBariumCuprate, 2)
                .inputItems(TagPrefix.plate, GTMaterials.NetherStar)
                .inputItems(TagPrefix.plate, GTMaterials.Polybenzimidazole, 4)
                .inputItems(TagPrefix.plate, GTMaterials.NeodymiumMagnetic, 4)
                .inputItems(GTItems.ELECTRIC_MOTOR_LuV.asStack(2))
                .inputFluids(GTMaterials.Argon.getFluid(3000))
                .outputItems(GTNNMachines.NEUTRON_ACCELERATOR[GTValues.LuV].asStack())
                .scannerResearch(GTNNMachines.NEUTRON_ACCELERATOR[GTValues.IV].asStack())
                .EUt(GTValues.VA[GTValues.LuV]) // 32768 EU/t
                .duration(300) // 15秒
                .save(provider);

        // ZPM中子加速器（装配线）
        GTRecipeTypes.ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("neutron_accelerator_zpm"))
                .inputItems(CTNHItems.INVERTER.asStack(2))
                .inputItems(GTMachines.HULL[GTValues.ZPM].asStack())
                .inputItems(TagPrefix.cableGtSingle, GTMaterials.VanadiumGallium, 2)
                .inputItems(TagPrefix.plate, GTMaterials.NetherStar)
                .inputItems(TagPrefix.plate, GTMaterials.Polybenzimidazole, 8)
                .inputItems(TagPrefix.rodLong, GTMaterials.SamariumMagnetic, 4)
                .inputItems(GTItems.ELECTRIC_MOTOR_ZPM.asStack(2))
                .inputItems(TagPrefix.wireGtQuadruple, GTMaterials.UraniumTriplatinum, 4)
                .inputFluids(GTMaterials.Xenon.getFluid(3000))
                .outputItems(GTNNMachines.NEUTRON_ACCELERATOR[GTValues.ZPM].asStack())
                .scannerResearch(GTNNMachines.NEUTRON_ACCELERATOR[GTValues.LuV].asStack())
                .EUt(GTValues.VA[GTValues.ZPM]) // 98304 EU/t
                .duration(300) // 15秒
                .save(provider);

        // UV中子加速器（装配线）
        GTRecipeTypes.ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("neutron_accelerator_uv"))
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
                .outputItems(GTNNMachines.NEUTRON_ACCELERATOR[GTValues.UV].asStack())
                .scannerResearch(GTNNMachines.NEUTRON_ACCELERATOR[GTValues.ZPM].asStack())
                .EUt(GTValues.VA[GTValues.UV]) // 393216 EU/t
                .duration(300) // 15秒
                .save(provider);
        // ULV中子加速器
        VanillaRecipeHelper.addShapedRecipe(
                provider, "neutron_accelerator_ulv",
                GTNNMachines.NEUTRON_ACCELERATOR[GTValues.ULV].asStack(),
                "ABC", "DEF", "ABC",
                'A', ChemicalHelper.get(TagPrefix.cableGtSingle, GTMaterials.Lead),
                'B', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Lead),
                'C', ChemicalHelper.get(TagPrefix.rotor, GTMaterials.Lead),
                'D', ItemTags.PLANKS,
                'E', GTMachines.HULL[GTValues.ULV].asStack(),
                'F', CTNHItems.INVERTER.asStack());

        // LV中子加速器
        VanillaRecipeHelper.addShapedRecipe(
                provider, "neutron_accelerator_lv",
                GTNNMachines.NEUTRON_ACCELERATOR[GTValues.LV].asStack(),
                "ABC", "DEF", "ABC",
                'A', ChemicalHelper.get(TagPrefix.cableGtSingle, GTMaterials.Tin),
                'B', ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Lead),
                'C', GTItems.ELECTRIC_MOTOR_LV.asStack(),
                'D', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Rubber),
                'E', GTMachines.HULL[GTValues.LV].asStack(),
                'F', CTNHItems.INVERTER.asStack());
        // MV脱水机
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "dehydrator_mv",
                CTNHMachines.DEHYDRATOR[GTValues.MV].asStack(),
                "ABA", "CDC", "EFE",
                'A', ChemicalHelper.get(TagPrefix.wireFine, GTMaterials.RedAlloy),
                'B', CustomTags.MV_CIRCUITS,
                'C', ChemicalHelper.get(TagPrefix.cableGtQuadruple, GTMaterials.Copper),
                'D', GTMachines.HULL[GTValues.MV].asStack(),
                'E', ChemicalHelper.get(TagPrefix.gear, GTMaterials.Steel),
                'F', GTItems.ROBOT_ARM_MV);

        // HV脱水机
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "dehydrator_hv",
                CTNHMachines.DEHYDRATOR[GTValues.HV].asStack(),
                "ABA", "CDC", "EFE",
                'A', ChemicalHelper.get(TagPrefix.wireFine, GTMaterials.Electrum),
                'B', CustomTags.HV_CIRCUITS,
                'C', ChemicalHelper.get(TagPrefix.cableGtQuadruple, GTMaterials.Silver),
                'D', GTMachines.HULL[GTValues.HV].asStack(),
                'E', ChemicalHelper.get(TagPrefix.gear, GTMaterials.Potin),
                'F', GTItems.ROBOT_ARM_HV);

        // EV脱水机
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "dehydrator_ev",
                CTNHMachines.DEHYDRATOR[GTValues.EV].asStack(),
                "ABA", "CDC", "EFE",
                'A', GTItems.VOLTAGE_COIL_EV,
                'B', CustomTags.EV_CIRCUITS,
                'C', ChemicalHelper.get(TagPrefix.cableGtQuadruple, GTMaterials.Aluminium),
                'D', GTMachines.HULL[GTValues.EV].asStack(),
                'E', ChemicalHelper.get(TagPrefix.gear, GTMaterials.TungstenSteel),
                'F', GTItems.ROBOT_ARM_EV);

        // IV脱水机
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "dehydrator_iv",
                CTNHMachines.DEHYDRATOR[GTValues.IV].asStack(),
                "ABA", "CDC", "EFE",
                'A', GTItems.VOLTAGE_COIL_IV, // IV级电压线圈（直接引用，无TagPrefix）
                'B', CustomTags.IV_CIRCUITS,  // IV级电路
                'C', ChemicalHelper.get(TagPrefix.cableGtQuadruple, GTMaterials.Tungsten), // 四重钨电缆
                'D', GTMachines.HULL[GTValues.IV].asStack(), // IV级机器外壳
                'E', ChemicalHelper.get(TagPrefix.gear, GTMaterials.Nichrome), // 镍铬合金齿轮
                'F', GTItems.ROBOT_ARM_IV     // IV级机械臂
        );

        // LuV脱水机
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "dehydrator_luv",
                CTNHMachines.DEHYDRATOR[GTValues.LuV].asStack(),
                "ABA", "CDC", "EFE",
                'A', GTItems.VOLTAGE_COIL_LuV, // LuV级电压线圈
                'B', CustomTags.LuV_CIRCUITS,  // LuV级电路
                'C', ChemicalHelper.get(TagPrefix.cableGtQuadruple, GTMaterials.Naquadah),
                'D', GTMachines.HULL[GTValues.LuV].asStack(), // LuV级机器外壳
                'E', ChemicalHelper.get(TagPrefix.gear, GTMaterials.Ultimet), // Ultimet合金齿轮
                'F', GTItems.ROBOT_ARM_LuV    // LuV级机械臂
        );

        // ZPM脱水机
        VanillaRecipeHelper.addShapedRecipe(
                provider, true, "dehydrator_zpm",
                CTNHMachines.DEHYDRATOR[GTValues.ZPM].asStack(),
                "ABA", "CDC", "EFE",
                'A', GTItems.VOLTAGE_COIL_ZPM, // ZPM级电压线圈
                'B', CustomTags.ZPM_CIRCUITS,  // ZPM级电路
                'C', ChemicalHelper.get(TagPrefix.cableGtQuadruple, GTMaterials.Osmium), // 四重锇电缆
                'D', GTMachines.HULL[GTValues.ZPM].asStack(), // ZPM级机器外壳
                'E', ChemicalHelper.get(TagPrefix.gear, GTMaterials.Zeron100), // Zeron-100合金齿轮
                'F', GTItems.ROBOT_ARM_ZPM     // ZPM级机械臂
        );
        VanillaRecipeHelper.addShapedRecipe(
                provider, "polybenzimidazole_pipe",
                CTNHBlocks.CASING_POLYBENZIMIDAZOLE_PIPE.asStack(),
                "CAC", "ABA", "CAC",
                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Polybenzimidazole),
                'B', ChemicalHelper.get(TagPrefix.frameGt, GTMaterials.Polybenzimidazole),
                'C',
                ChemicalHelper.get(TagPrefix.pipeNormalFluid, GTMaterials.Polybenzimidazole));
        VanillaRecipeHelper.addShapedRecipe(
                provider, "naquadah_firebox_casing_one",
                CTNHBlocks.NAQUADAH_FIREBOX.asStack(),
                "CAC", "ABA", "CAC",
                'A', CTNHItems.NeutronSource,
                'B', ChemicalHelper.get(TagPrefix.frameGt, GTMaterials.Tungsten),
                'C', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Naquadah));
        VanillaRecipeHelper.addShapedRecipe(provider, true, "power_substation_ctnh",
                CEMultiblock.POWER_SUBSTATION.asStack(),
                "LPL", "CBC", "LPL", 'L', GTItems.LAPOTRON_CRYSTAL, 'P', GTItems.POWER_INTEGRATED_CIRCUIT, 'C',
                CustomTags.LuV_CIRCUITS, 'B', GTBlocks.CASING_PALLADIUM_SUBSTATION.asStack());

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("charged_certus_quartz_crystal"))
                .inputItems(AEItems.CERTUS_QUARTZ_CRYSTAL.asItem())
                .outputItems(
                        AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem())
                .EUt(GTValues.VA[GTValues.MV])
                .duration(100)
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("naquadah_firebox_casing_two"))
                .inputItems(CTNHItems.NeutronSource, 3)
                .inputItems(TagPrefix.plate, GTMaterials.Naquadah, 3)
                .inputItems(TagPrefix.frameGt, GTMaterials.Tungsten)
                .outputItems(CTNHBlocks.NAQUADAH_FIREBOX.asStack())
                .EUt(GTValues.VA[GTValues.IV])
                .duration(400)
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("gt_mbst_a"))
                .inputItems(GTItems.TERMINAL)
                .inputItems(AEItems.WIRELESS_TERMINAL.stack())
                .inputItems(TagPrefix.screw, GTMaterials.Aluminium, 2)
                .inputItems(CustomTags.MV_CIRCUITS)
                .outputItems(CTNHItems.ME_ADVANCED_TERMINAL)
                .EUt(GTValues.VA[GTValues.MV])
                .duration(200)
                .save(provider);

        add80ExtendRecipes(provider);
    }

    private static void add80ExtendRecipes(Consumer<FinishedRecipe> provider) {
        // 迁移来源：Z:/Git/Create-New-Horizon/kubejs/server_scripts/src/80extend.js
        GTRecipeTypes.ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("china"))
                .inputItems(GTItems.VOLTAGE_COIL_IV.asStack(4))
                .inputItems(TagPrefix.foil, GTMaterials.Naquadah, 32)
                .inputItems(GTItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.asStack(16))
                .inputItems(GTItems.ELECTRIC_PUMP_IV.asStack(4))
                .inputItems(GTNNMultiblocks.CHEMICAL_PLANT.asStack())
                .inputItems(GTMultiMachines.CRACKER.asStack(64))
                .outputItems(MultiblocksB.SINOPE_CHEMICAL.asStack())
                .EUt(32800)
                .duration(1440)
                .save(provider);

        GCYMRecipeTypes.ALLOY_BLAST_RECIPES.recipeBuilder(CTNHCore.id("man"))
                .inputItems(TagPrefix.dust, GTMaterials.Lithium, 10)
                .inputItems(TagPrefix.dust, GTMaterials.Cobalt, 10)
                .inputItems(TagPrefix.dust, GTMaterials.Platinum, 10)
                .inputItems(TagPrefix.dust, GTMaterials.Erbium, 10)
                .inputFluids(CTNHMaterials.Pyrotheum.getFluid(1440))
                .inputFluids(GTMaterials.Helium.getFluid(1440))
                .outputFluids(UncategorizedMaterials.SHOCK_RESISTANT_ALLOY.getFluid(2400))
                .EUt(2400)
                .duration(240)
                .save(provider);

        GTRecipeTypes.FUSION_RECIPES.recipeBuilder(CTNHCore.id("americium_and_naquadria_to_neutronium_plasma"))
                .outputFluids(GTMaterials.Neutronium.getFluid(256))
                .inputFluids(GTMaterials.Naquadria.getFluid(256))
                .inputFluids(GTMaterials.Americium.getFluid(256))
                .fusionStartEU(600000000)
                .duration(100)
                .EUt(32678 * 4)
                .save(provider);

        CTNHRecipeTypes.SINOPE.recipeBuilder(gtceuId("thorium_232_dust"))
                .outputItems(TagPrefix.dust, CTNHMaterials.Thorium232, 4)
                .outputItems(TagPrefix.dustSmall, GTMaterials.Thorium, 16)
                .inputItems(TagPrefix.dust, GTMaterials.Thorium, 32)
                .inputItems(TagPrefix.dust, GTMaterials.Borax, 16)
                .inputFluids(GTMaterials.HydrochloricAcid.getFluid(1500))
                .circuitMeta(1)
                .EUt(1920)
                .duration(6000)
                .save(provider);

        GTRecipeTypes.ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("neutronium_alloy_casing_block"))
                .outputItems(CTNHBlocks.CASING_NEUTRONIUM_ALLOY_BLOCK.asStack())
                .inputItems(GTItems.ROBOT_ARM_ZPM)
                .inputItems(GTItems.ELECTRIC_PISTON_ZPM)
                .inputItems(GTItems.ELECTRIC_MOTOR_ZPM.asStack(2))
                .inputItems(CustomTags.UV_CIRCUITS, 2)
                .inputItems(CustomTags.UHV_CIRCUITS)
                .inputItems(CTNHBlocks.CASING_NAQUADAH_BLOCK.asStack())
                .inputItems(TagPrefix.plateDouble, BedrockMaterials.BEDROCK_NEUTRONIUM, 2)
                .inputItems(TagPrefix.plateDense, GTMaterials.Darmstadtium, 8)
                .inputFluids(BedrockMaterials.AETHER.getFluid(1280))
                .inputFluids(GTMaterials.Naquadria.getFluid(1280))
                .stationResearch(b -> b
                        .researchStack(CTNHBlocks.CASING_NAQUADAH_BLOCK.asStack())
                        .dataStack(GTItems.TOOL_DATA_ORB.asStack())
                        .EUt(GTValues.VA[GTValues.ZPM])
                        .CWUt(48))
                .EUt(32678 * 16)
                .duration(20 * 40)
                .save(provider);

        GTRecipeTypes.ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("plasma_alloy_blast_smelter"))
                .inputFluids(GTMaterials.Iron.getFluid(FluidStorageKeys.PLASMA, 12800))
                .inputFluids(GTMaterials.Helium.getFluid(FluidStorageKeys.PLASMA, 12800))
                .inputItems(GCYMMachines.BLAST_ALLOY_SMELTER.asStack(64))
                .inputItems(GTMultiMachines.MULTI_SMELTER.asStack(64))
                .inputItems(GCYMMachines.MEGA_BLAST_FURNACE.asStack(64))
                .inputItems(MultiblocksA.SUPER_EBF.asStack(64))
                .inputItems(CustomTags.UV_CIRCUITS, 64)
                .inputItems(TagPrefix.plateDouble, BedrockMaterials.BEDROCK_NEUTRONIUM, 64)
                .inputItems(TagPrefix.gear, GTMaterials.Tritanium, 16)
                .inputItems(TagPrefix.frameGt, GTMaterials.Naquadria, 16)
                .outputItems(MultiblocksB.PLASMA_ALLOY_BLAST_SMELTER.asStack())
                .stationResearch(b -> b
                        .researchStack(GCYMMachines.MEGA_BLAST_FURNACE.asStack())
                        .dataStack(GTItems.TOOL_DATA_ORB.asStack())
                        .EUt(GTValues.VA[GTValues.UV])
                        .CWUt(64))
                .EUt(32678 * 32)
                .duration(20 * 200)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("naalf"))
                .inputItems(TagPrefix.dust, CTNHMaterials.Cryolite, 10)
                .outputItems(TagPrefix.dust, GTMaterials.Sodium, 3)
                .outputItems(TagPrefix.dust, GTMaterials.Aluminium)
                .outputFluids(GTMaterials.Fluorine.getFluid(6000))
                .circuitMeta(19)
                .EUt(512)
                .duration(20 * 40)
                .save(provider);

        CTNHRecipeTypes.DECAY_POOLS.recipeBuilder(CTNHCore.id("naalf"))
                .inputItems(TagPrefix.dust, CTNHMaterials.Cryolite, 1000)
                .circuitMeta(24)
                .outputItems(TagPrefix.dust, UncategorizedMaterials.SODIUM22, 50)
                .outputFluids(GTMaterials.Magnesium.getFluid(1000))
                .outputFluids(GTMaterials.Magnalium.getFluid(10000))
                .duration(20 * 36000)
                .save(provider);

        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("assembly_line_casing"),
                GTBlocks.CASING_ASSEMBLY_CONTROL.asStack(),
                "ABA", "ACA", "ABA",
                'A', CustomTags.ZPM_CIRCUITS,
                'B', CustomTags.LuV_CIRCUITS,
                'C', ChemicalHelper.get(TagPrefix.frameGt, GTMaterials.TungstenSteel));

        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("assembly_line_unit"),
                GTBlocks.CASING_ASSEMBLY_LINE.asStack(),
                "BAB", "ACA", "BAB",
                'A', GTItems.ROBOT_ARM_IV,
                'B', ChemicalHelper.get(TagPrefix.gear, GTMaterials.Ruridit),
                'C', CustomTags.ZPM_CIRCUITS);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(gtceuId("assembly_line_casing_1"))
                .outputItems(GTBlocks.CASING_ASSEMBLY_CONTROL.asStack(8))
                .inputItems(CustomTags.LuV_CIRCUITS, 8)
                .inputItems(CustomTags.UV_CIRCUITS, 8)
                .inputItems(TagPrefix.frameGt, GTMaterials.TungstenSteel, 8)
                .circuitMeta(1)
                .EUt(8192)
                .duration(20 * 160)
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(gtceuId("assembly_line_casing_2"))
                .outputItems(GTBlocks.CASING_ASSEMBLY_CONTROL)
                .inputItems(CustomTags.ZPM_CIRCUITS, 6)
                .inputItems(CustomTags.LuV_CIRCUITS, 2)
                .inputItems(TagPrefix.frameGt, GTMaterials.TungstenSteel)
                .circuitMeta(2)
                .EUt(8192)
                .duration(20 * 20)
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(gtceuId("assembly_line_unit_1"))
                .outputItems(GTBlocks.CASING_ASSEMBLY_LINE.asStack(4))
                .inputItems(GTItems.ROBOT_ARM_LuV.asStack(4))
                .inputItems(TagPrefix.gear, GTMaterials.Ruridit, 12)
                .inputItems(CustomTags.LuV_CIRCUITS, 4)
                .circuitMeta(1)
                .EUt(8192)
                .duration(20 * 120)
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(gtceuId("assembly_line_unit_2"))
                .outputItems(GTBlocks.CASING_ASSEMBLY_LINE.asStack())
                .inputItems(GTItems.ROBOT_ARM_IV.asStack(4))
                .inputItems(TagPrefix.gear, GTMaterials.Ruridit, 4)
                .inputItems(CustomTags.ZPM_CIRCUITS)
                .circuitMeta(2)
                .EUt(8192)
                .duration(20 * 20)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("decomposition_electrolyzing_ammonium_chloride"))
                .inputItems(TagPrefix.dust, GTMaterials.AmmoniumChloride, 2)
                .outputFluids(GTMaterials.HydrochloricAcid.getFluid(144))
                .outputFluids(GTMaterials.Ammonia.getFluid(144))
                .EUt(25)
                .duration(20)
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("cryotheum_freezer"))
                .outputItems(MultiblocksB.CRYOTHEUMFREEZER.asStack())
                .inputItems(CustomTags.ZPM_CIRCUITS, 4)
                .inputItems(TagPrefix.dust, CTNHMaterials.Cryotheum, 64)
                .inputItems(CTNHBlocks.SUPERCOOLED_BLOCK.asStack())
                .inputItems(CTNHBlocks.SUPER_FREEZE_BLOCK.asStack())
                .inputItems(GTItems.ELECTRIC_PUMP_IV.asStack(6))
                .inputItems(TagPrefix.cableGtSingle, GTMaterials.HSSG, 12)
                .inputFluids(GTMaterials.PCBCoolant.getFluid(10000))
                .duration(1000)
                .EUt(8192)
                .save(provider);

        GTRecipeTypes.VACUUM_RECIPES.recipeBuilder(CTNHCore.id("super_machine_casing_frost_proof"))
                .outputItems(CTNHBlocks.SUPER_FREEZE_BLOCK.asStack())
                .inputItems(GTBlocks.CASING_ALUMINIUM_FROSTPROOF.asStack())
                .inputFluids(CTNHMaterials.Cryotheum.getFluid(5000))
                .inputFluids(GTMaterials.PCBCoolant.getFluid(5000))
                .duration(100)
                .EUt(8192)
                .save(provider);

        GTRecipeTypes.MIXER_RECIPES.recipeBuilder(gtceuId("cadmium_sulfide_dust"))
                .outputItems(TagPrefix.dust, UncategorizedMaterials.CADMIUM_SULFIDE, 2)
                .inputItems(TagPrefix.dust, GTMaterials.Cadmium)
                .inputItems(TagPrefix.dust, GTMaterials.Sulfur)
                .duration(100)
                .EUt(10)
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("research_dataset"))
                .outputItems(CTNHItems.RESEARCH_DATASET.asStack())
                .inputItems(GTItems.TOOL_DATA_ORB)
                .inputItems(GTItems.TOOL_DATA_STICK)
                .inputItems(Items.PAPER)
                .duration(20)
                .EUt(10)
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ultimate_combustion_engine"))
                .outputItems(MultiblocksA.ULTIMATE_COMBUSTION_ENGINE.asStack())
                .inputItems(GTItems.ELECTRIC_PISTON_LuV.asStack(12))
                .inputItems(GTItems.ELECTRIC_PUMP_LuV.asStack(12))
                .inputItems(GTMultiMachines.EXTREME_COMBUSTION_ENGINE.asStack(8))
                .inputItems(CustomTags.UV_CIRCUITS, 2)
                .EUt(32768 * 4)
                .duration(120)
                .save(provider);

        CTNHRecipeTypes.FUEL_REFINING.recipeBuilder(CTNHCore.id("end_of_oil"))
                .inputItems(TagPrefix.dust, GTMaterials.Trinium, 4)
                .inputFluids(CTNHMaterials.NaquadahBasedLiquidFuelExcited.getFluid(25))
                .inputFluids(GTMaterials.HighOctaneGasoline.getFluid(10000))
                .outputFluids(CTNHMaterials.NQ_END_OF_GASOLINE.getFluid(20000))
                .EUt(23678 * 4)
                .duration(60)
                .blastFurnaceTemp(7200)
                .save(provider);

        CTNHRecipeTypes.SILICA_ROCK_FUEL_REFINERY.recipeBuilder(gtceuId("compressed_aether_plasma_1"))
                .outputFluids(CTNHMaterials.COMPRESSED_AETHER.getFluid(FluidStorageKeys.PLASMA, 4000))
                .inputFluids(UncategorizedMaterials.CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_I.getFluid(100))
                .inputFluids(BedrockMaterials.AETHER.getFluid(FluidStorageKeys.PLASMA, 4000))
                .duration(100)
                .EUt(32768 * 4)
                .save(provider);

        CTNHRecipeTypes.SILICA_ROCK_FUEL_REFINERY.recipeBuilder(gtceuId("compressed_aether_plasma_2"))
                .outputFluids(CTNHMaterials.COMPRESSED_AETHER.getFluid(FluidStorageKeys.PLASMA, 6000))
                .inputFluids(UncategorizedMaterials.CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_II.getFluid(50))
                .inputFluids(BedrockMaterials.AETHER.getFluid(FluidStorageKeys.PLASMA, 4000))
                .duration(25)
                .EUt(32768 * 4 * 4)
                .save(provider);

        CTNHRecipeTypes.SILICA_ROCK_FUEL_REFINERY.recipeBuilder(gtceuId("compressed_aether_plasma_3"))
                .outputFluids(CTNHMaterials.COMPRESSED_AETHER.getFluid(FluidStorageKeys.PLASMA, 8000))
                .inputFluids(UncategorizedMaterials.CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_III.getFluid(25))
                .inputFluids(BedrockMaterials.AETHER.getFluid(FluidStorageKeys.PLASMA, 4000))
                .duration(25)
                .EUt(32768 * 4 * 4)
                .save(provider);

        GTRecipeTypes.ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("strongly_interacting_neutron_reflector"))
                .outputItems(CTNHItems.STRONGLY_INTERACTING_NEUTRON_REFLECTOR.asStack())
                .inputItems(GTItems.NEUTRON_REFLECTOR.asStack(8))
                .inputItems(TagPrefix.plateDouble, BedrockMaterials.BEDROCK_NEUTRONIUM, 8)
                .inputItems(TagPrefix.plate, GTMaterials.Naquadria, 8)
                .inputItems(CTNHItems.PlateRadiationProtection.asStack(8))
                .inputFluids(GTMaterials.NaquadahAlloy.getFluid(1024))
                .inputFluids(GTMaterials.Lubricant.getFluid(1024))
                .EUt(114514)
                .duration(1000)
                .stationResearch(b -> b
                        .researchStack(ChemicalHelper.get(TagPrefix.plateDense, GTMaterials.Iridium))
                        .dataStack(GTItems.TOOL_DATA_ORB.asStack())
                        .EUt(GTValues.VA[GTValues.UV])
                        .CWUt(48))
                .save(provider);

        GTRecipeTypes.ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("uhv_fluid_drilling_inf"))
                .inputItems(TagPrefix.plate, BedrockMaterials.AETHER, 16)
                .inputItems(CTNHBlocks.CASING_NEUTRONIUM_ALLOY_BLOCK.asStack(8))
                .inputItems(CTNHItems.STRONGLY_INTERACTING_NEUTRON_REFLECTOR.asStack(4))
                .inputItems(CustomTags.UEV_CIRCUITS, 4)
                .inputItems(GTMachines.QUANTUM_TANK[GTValues.UHV].asStack())
                .inputFluids(BedrockMaterials.BEDROCK_NEUTRONIUM.getFluid(16000))
                .inputFluids(CTNHMaterials.LIVING_METAL.getFluid(4000))
                .outputItems(MultiblocksB.FLUID_DRILLING_INF[GTValues.UHV].asStack())
                .inputItems(GTItems.ELECTRIC_PUMP_UHV.asStack(16))
                .inputItems(GTItems.ELECTRIC_PISTON_UHV.asStack(8))
                .EUt(GTValues.VA[GTValues.UEV])
                .duration(100 * 20)
                .stationResearch(b -> b
                        .researchStack(GTMultiMachines.FLUID_DRILLING_RIG[GTValues.EV].asStack())
                        .dataStack(GTItems.TOOL_DATA_ORB.asStack())
                        .EUt(GTValues.VA[GTValues.UHV])
                        .CWUt(64))
                .save(provider);
    }

    private static ResourceLocation gtceuId(String path) {
        return ResourceLocation.fromNamespaceAndPath("gtceu", path);
    }

    private static ItemStack itemStack(String id) {
        return itemStack(id, 1);
    }

    private static ItemStack itemStack(String id, int count) {
        return new ItemStack(item(id), count);
    }

    private static Item item(String id) {
        return java.util.Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(id)), id);
    }

    private static FluidStack fluidStack(String id, int amount) {
        return new FluidStack(fluid(id), amount);
    }

    private static net.minecraft.world.level.material.Fluid fluid(String id) {
        return java.util.Objects.requireNonNull(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse(id)), id);
    }
}
