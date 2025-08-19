package io.github.cpearl0.ctnhcore.data.recipe;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import earth.terrarium.adastra.common.registry.ModItems;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.CTNHMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLY_LINE_RECIPES;

public class AdAstraRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder("heavy_ingot_t1")
                .inputItems(plateDense, Brass)
                .inputItems(plateDense, Aluminium)
                .inputItems(plateDense, Steel)
                .outputItems(CTNHItems.HEAVY_INGOT_T1)
                .inputFluids(StainlessSteel.getFluid(72))
                .circuitMeta(1)
                .duration(300).EUt(GTValues.VA[GTValues.HV]).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("heavy_ingot_t2")
                .inputItems(CTNHItems.HEAVY_INGOT_T1)
                .inputItems(plateDense, CTNHMaterials.Desh, 2)
                .outputItems(CTNHItems.HEAVY_INGOT_T2)
                .inputFluids(TungstenSteel.getFluid(72))
                .circuitMeta(1)
                .duration(300).EUt(GTValues.VA[GTValues.EV]).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("heavy_ingot_t3")
                .inputItems(CTNHItems.HEAVY_INGOT_T2)
                .inputItems(plateDense, CTNHMaterials.Ostrum, 4)
                .outputItems(CTNHItems.HEAVY_INGOT_T3)
                .inputFluids(Platinum.getFluid(72))
                .circuitMeta(1)
                .duration(300).EUt(GTValues.VA[GTValues.IV]).save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("heavy_ingot_t4")
                .inputItems(CTNHItems.HEAVY_INGOT_T3)
                .inputItems(plateDense, CTNHMaterials.Calorite, 3)
                .inputItems(plateDense, CTNHMaterials.Calorite, 3)
                .inputItems(bolt, Ruridit, 4)
                .outputItems(CTNHItems.HEAVY_INGOT_T4)
                .inputFluids(CTNHMaterials.Cerrobase140.getFluid(36))
                .duration(300).EUt(GTValues.VA[GTValues.LuV]).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("space_helmet")
                .inputItems(Items.CHAINMAIL_HELMET.getDefaultInstance(), ChemicalHelper.get(plate, Glass))
                .outputItems(ModItems.SPACE_HELMET)
                .inputFluids(Glue.getFluid(72))
                .EUt(GTValues.VA[GTValues.MV]).duration(400).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("netherite_space_helmet")
                .inputItems(ModItems.SPACE_HELMET.get().getDefaultInstance(), CTNHItems.HEAVY_PLATE_T3.asStack(5))
                .outputItems(ModItems.NETHERITE_SPACE_HELMET)
                .inputFluids(StainlessSteel.getFluid(72))
                .EUt(GTValues.VA[GTValues.HV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("netherite_space_suit")
                .inputItems(
                        ModItems.SPACE_SUIT.get().getDefaultInstance(),
                        new ItemStack(ModItems.STEEL_TANK.get(), 2),
                        CTNHItems.HEAVY_PLATE_T3.asStack(8)
                )
                .outputItems(ModItems.NETHERITE_SPACE_SUIT)
                .inputFluids(StainlessSteel.getFluid(72))
                .EUt(GTValues.VA[GTValues.HV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("netherite_space_pants")
                .inputItems(ModItems.SPACE_PANTS.get().getDefaultInstance(), CTNHItems.HEAVY_PLATE_T3.asStack(7))
                .outputItems(ModItems.NETHERITE_SPACE_PANTS)
                .inputFluids(StainlessSteel.getFluid(72))
                .EUt(GTValues.VA[GTValues.HV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("netherite_space_boots")
                .inputItems(ModItems.SPACE_BOOTS.get().getDefaultInstance(), CTNHItems.HEAVY_PLATE_T3.asStack(4))
                .outputItems(ModItems.NETHERITE_SPACE_BOOTS)
                .inputFluids(StainlessSteel.getFluid(72))
                .EUt(GTValues.VA[GTValues.HV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("jet_suit_helmet")
                .inputItems(ModItems.NETHERITE_SPACE_HELMET.get().getDefaultInstance(), CTNHItems.HEAVY_PLATE_T4.asStack(5))
                .outputItems(ModItems.JET_SUIT_HELMET)
                .inputFluids(Titanium.getFluid(144))
                .EUt(GTValues.VA[GTValues.EV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("jet_suit")
                .inputItems(
                        ModItems.NETHERITE_SPACE_SUIT.get().getDefaultInstance(), new ItemStack(ModItems.OSTRUM_TANK.get(), 2),
                        GTItems.POWER_THRUSTER_ADVANCED.asStack(2), CTNHItems.HEAVY_PLATE_T4.asStack(8)
                )
                .outputItems(ModItems.JET_SUIT)
                .inputFluids(Titanium.getFluid(144))
                .EUt(GTValues.VA[GTValues.EV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("jet_suit_pants")
                .inputItems(ModItems.NETHERITE_SPACE_PANTS.get().getDefaultInstance(), CTNHItems.HEAVY_PLATE_T4.asStack(7))
                .outputItems(ModItems.JET_SUIT_PANTS)
                .inputFluids(Titanium.getFluid(144))
                .EUt(GTValues.VA[GTValues.EV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("jet_suit_boots")
                .inputItems(ModItems.NETHERITE_SPACE_BOOTS.get().getDefaultInstance(), CTNHItems.HEAVY_PLATE_T4.asStack(4))
                .outputItems(ModItems.JET_SUIT_BOOTS)
                .inputFluids(Titanium.getFluid(144))
                .EUt(GTValues.VA[GTValues.EV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("desh_engine")
                .inputItems(
                        new ItemStack(ModItems.STEEL_TANK.get(), 3), CTNHItems.HEAVY_PLATE_T2.asStack(2),
                        new ItemStack(ModItems.STEEL_ENGINE.get(), 2), CTNHItems.CHIP_T2.asStack()
                )
                .outputItems(ModItems.DESH_ENGINE)
                .inputFluids(Polyethylene.getFluid(144))
                .EUt(GTValues.VA[GTValues.EV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("ostrum_engine")
                .inputItems(
                        new ItemStack(ModItems.OSTRUM_TANK.get(), 4),
                        CTNHItems.HEAVY_PLATE_T3.asStack(4),
                        CTNHItems.HEAVY_PLATE_T2.asStack(2),
                        new ItemStack(ModItems.DESH_ENGINE.get(), 2),
                        CTNHItems.CHIP_T3.asStack()
                )
                .outputItems(ModItems.OSTRUM_ENGINE)
                .inputFluids(Polytetrafluoroethylene.getFluid(144))
                .EUt(GTValues.VA[GTValues.IV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("ostrum_tank")
                .inputItems(
                        ModItems.STEEL_TANK.get().getDefaultInstance(),
                        ChemicalHelper.get(plateDouble, Titanium, 5),
                        CTNHItems.CHIP_T3.asStack(4),
                        CTNHItems.HEAVY_PLATE_T2.asStack(2)
                )
                .outputItems(ModItems.OSTRUM_TANK)
                .inputFluids(StainlessSteel.getFluid(144))
                .EUt(GTValues.VA[GTValues.HV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("steel_tank")
                .inputItems(GTItems.FLUID_CELL_LARGE_STEEL.asStack(), CTNHItems.CHIP_T1.asStack(2))
                .inputFluids(StainlessSteel.getFluid(72))
                .outputItems(ModItems.STEEL_TANK)
                .circuitMeta(1)
                .EUt(GTValues.VA[GTValues.LV]).duration(20).save(provider);
        VanillaRecipeHelper.addShapedRecipe(
                provider, "steel_tank", new ItemStack(ModItems.STEEL_TANK.get()),
                "DhD", "ABA", "DdD",
                'A', CTNHItems.CHIP_T1.asStack(),
                'B', GTItems.FLUID_CELL_LARGE_STEEL.asStack(),
                'D', new MaterialEntry(screw, StainlessSteel)
        );
        ASSEMBLER_RECIPES.recipeBuilder("rocket_nose_cone")
                .inputItems(CTNHItems.HEAVY_PLATE_T1.asStack(4), new ItemStack(Items.LIGHTNING_ROD))
                .inputFluids(StainlessSteel.getFluid(36))
                .outputItems(ModItems.ROCKET_NOSE_CONE)
                .circuitMeta(4)
                .EUt(GTValues.VA[GTValues.LV]).duration(50).save(provider);
        VanillaRecipeHelper.addShapedRecipe(
                provider, "rocket_nose_cone", new ItemStack(ModItems.ROCKET_NOSE_CONE.get()),
                "dBh", "ADA", "DDD",
                'A', new MaterialEntry(screw, StainlessSteel),
                'B', new ItemStack(Items.LIGHTNING_ROD),
                'D', CTNHItems.HEAVY_PLATE_T1.asStack()
        );
        ASSEMBLER_RECIPES.recipeBuilder("rocket_fin")
                .inputItems(plateDouble, Steel, 2)
                .inputItems(CTNHItems.HEAVY_PLATE_T1.asStack(4))
                .inputItems(ModItems.ROCKET_NOSE_CONE)
                .outputItems(ModItems.ROCKET_FIN)
                .circuitMeta(5)
                .EUt(GTValues.VA[GTValues.LV]).duration(50).save(provider);
        VanillaRecipeHelper.addShapedRecipe(
                provider, "rocket_fin", new ItemStack(ModItems.ROCKET_FIN.get()),
                "hAf", "BAB", "BsB",
                'A', new MaterialEntry(plateDouble, StainlessSteel),
                'B', CTNHItems.HEAVY_PLATE_T1.asStack()
        );
        ASSEMBLER_RECIPES.recipeBuilder("steel_engine")
                .inputItems(CTNHItems.HEAVY_PLATE_T1.asStack(4), new ItemStack(ModItems.STEEL_TANK.get(), 2))
                .inputItems(
                        GTBlocks.FIREBOX_STEEL.asStack(),
                        GTItems.COVER_ACTIVITY_DETECTOR.asStack(),
                        CTNHItems.CHIP_T1.asStack()
                )
                .outputItems(ModItems.STEEL_ENGINE)
                .EUt(GTValues.VA[GTValues.LV]).duration(100).save(provider);
        VanillaRecipeHelper.addShapedRecipe(
                provider, "steel_engine", new ItemStack(ModItems.STEEL_ENGINE.get()),
                "DED", "CBC", "DAD",
                'A', GTItems.COVER_ACTIVITY_DETECTOR.asStack(),
                'B', GTBlocks.FIREBOX_STEEL.asStack(),
                'C', new ItemStack(ModItems.STEEL_TANK.get()),
                'D', CTNHItems.HEAVY_PLATE_T1.asStack(),
                'E', CTNHItems.CHIP_T1.asStack()
        );
        VanillaRecipeHelper.addShapedRecipe(
                provider, "space_suit", new ItemStack(ModItems.SPACE_SUIT.get()),
                "ADA", "CBC", "ADA",
                'A', CTNHItems.HEAVY_PLATE_T1.asStack(),
                'B', new ItemStack(ModItems.OXYGEN_GEAR.get()),
                'C', new ItemStack(ModItems.GAS_TANK.get()),
                'D', new MaterialEntry(screw, StainlessSteel)
        );
        VanillaRecipeHelper.addShapedRecipe(
                provider, "oxygen_gear", new ItemStack(ModItems.OXYGEN_GEAR.get()),
                "AEA", "CBC", "ADA",
                'A', new MaterialEntry(plateDouble, Steel),
                'B', GTItems.ELECTRIC_PUMP_HV.asStack(),
                'C', GTItems.FLUID_CELL.asStack(),
                'D', new MaterialEntry(rotor, Steel),
                'E', GTItems.SENSOR_HV
        );
        VanillaRecipeHelper.addShapedRecipe(
                provider, "space_pants", new ItemStack(ModItems.SPACE_PANTS.get()),
                "AAA", "AhA", "A A",
                'A', CTNHItems.HEAVY_PLATE_T1.asStack()
        );
        VanillaRecipeHelper.addShapedRecipe(
                provider, "space_boots", new ItemStack(ModItems.SPACE_BOOTS.get()),
                "AhA", "A A",
                'A', CTNHItems.HEAVY_PLATE_T1.asStack()
        );
        VanillaRecipeHelper.addShapedRecipe(
                provider, "oxygen_tank", new ItemStack(ModItems.GAS_TANK.get()),
                "AhA", "ACA", "AAA",
                'A', new MaterialEntry(plateDouble, Aluminium),
                'C', GTItems.FLUID_CELL.asStack()
        );
        VanillaRecipeHelper.addShapedRecipe(
                provider, "fan", new ItemStack(ModItems.FAN.get()),
                "AwA", "ECE", "AdA",
                'A', CTNHItems.HEAVY_PLATE_T1.asStack(),
                'C', new MaterialEntry(rodLong, StainlessSteel),
                'E', new MaterialEntry(rotor, Steel)
        );
        VanillaRecipeHelper.addShapedRecipe(
                provider, "oxygen_loader", new ItemStack(ModItems.OXYGEN_LOADER.get()),
                "ABA", "CDE", "FGF",
                'A', new MaterialEntry(plateDouble, Steel),
                'B', new ItemStack(ModItems.OXYGEN_GEAR.get()),
                'C', new ItemStack(Items.IRON_BARS),
                'D', new ItemStack(ModItems.FAN.get()),
                'E', GTItems.ELECTRIC_MOTOR_HV.asStack(),
                'F', new MaterialEntry(plateDouble, Aluminium),
                'G', new MaterialEntry(cableGtDouble, Aluminium)
        );
        ASSEMBLY_LINE_RECIPES.recipeBuilder("calorite_engine")
                .inputItems(CTNHItems.HEAVY_PLATE_T4.asStack(32), CTNHItems.HEAVY_PLATE_T3.asStack(16))
                .inputItems(new ItemStack(ModItems.OSTRUM_ENGINE.get(), 8), CTNHItems.CHIP_T4.asStack(2))
                .inputItems(new ItemStack(ModItems.OSTRUM_TANK.get(), 8))
                .outputItems(ModItems.CALORITE_ENGINE)
                .inputFluids(Platinum.getFluid(4032))
                .inputFluids(Iridium.getFluid(2016))
                .inputFluids(Palladium.getFluid(1008))
                .inputFluids(Osmium.getFluid(504))
                .EUt(GTValues.VA[GTValues.LuV]).duration(600).save(provider);
    }
    public static void remove(Consumer<ResourceLocation> consumer) {
        consumer.accept(ResourceLocation.tryParse("ad_astra:space_helmet"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:space_suit"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:space_pants"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:space_boots"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:netherite_space_helmet"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:netherite_space_suit"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:netherite_space_pants"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:netherite_space_boots"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:jet_suit_helmet"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:jet_suit"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:jet_suit_pants"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:jet_suit_boots"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:steel_engine"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:rocket_nose_cone"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:rocket_fin"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:steel_tank"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:nasa_workbench/tier_1_rocket_from_nasa_workbench"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:nasa_workbench/tier_2_rocket_from_nasa_workbench"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:nasa_workbench/tier_3_rocket_from_nasa_workbench"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:nasa_workbench/tier_4_rocket_from_nasa_workbench"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:desh_engine"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:desh_tank"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:ostrum_engine"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:ostrum_tank"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:calorite_engine"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:calorite_tank"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:oxygen_gear"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:oxygen_tank"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:fan"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:gas_tank"));
        consumer.accept(ResourceLocation.tryParse("ad_astra:oxygen_loader"));
    }
}
