package io.github.cpearl0.ctnhcore.data.recipe;

import com.mo_guang.ctpp.registry.CTPPMaterials;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterialBlocks;
import net.minecraft.data.recipes.FinishedRecipe;
import tech.vixhentx.mcmod.ctnhlib.CTNHLib;
import tech.vixhentx.mcmod.ctnhlib.registrate.builders.CTNHMaterial;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.block.BotaniaFlowerBlocks;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTItems.QUANTUM_STAR;
import static com.moguang.ctnhmana.registry.CMRecipeTypes.*;
import java.util.function.Consumer;

import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.*;
import static io.github.lounode.extrabotany.common.block.flower.ExtrabotanyFlowerBlocks.*;
import static io.github.lounode.extrabotany.common.item.ExtraBotanyItems.*;
import static mythicbotany.register.ModItems.*;
import static com.moguang.ctnhmana.data.recipe.utils.BotaniaIngredients.*;
import static com.moguang.ctnhmana.registry.CMMaterials.*;
import static mythicbotany.register.ModBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTItems.FERTILIZER;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.minecraft.world.item.Items.*;

public class EternalGardenRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        ETERNAL_GARDEN.recipeBuilder("hydroangeas1")//水绣球-水
                .notConsumable(BotaniaFlowerBlocks.hydroangeas.asItem())
                .inputFluids(Water.getFluid(1000))
                .outputFluids(Mana.getFluid(100))
                .circuitMeta(1)
                .EUt(256)
                .duration(20)
                .save(provider);
        ETERNAL_GARDEN.recipeBuilder("hydroangeas2")//水绣球-蒸馏水
                .notConsumable(BotaniaFlowerBlocks.hydroangeas.asItem())
                .inputFluids(DistilledWater.getFluid(1000))
                .outputFluids(Mana.getFluid(200))
                .circuitMeta(2)
                .EUt(12800/25)
                .duration(25)
                .save(provider);
        ETERNAL_GARDEN.recipeBuilder("shulk_me_not")//勿落草
                .notConsumable(BotaniaFlowerBlocks.shulkMeNot.asItem())
                .outputFluids(Mana.getFluid(4))
                .circuitMeta(1)
                .EUt(4096)
                .duration(1)
                .save(provider);
        ETERNAL_GARDEN.recipeBuilder("thermalily1")//炽玫瑰-极热之炽焰
                .notConsumable(BotaniaFlowerBlocks.thermalily.asItem())
                .inputFluids(Pyrotheum.getFluid(1000))
                .outputFluids(Mana.getFluid(2000))
                .circuitMeta(1)
                .EUt(51200/25)
                .duration(25)
                .save(provider);
        ETERNAL_GARDEN.recipeBuilder("thermalily2")//炽玫瑰-烈焰
                .notConsumable(BotaniaFlowerBlocks.thermalily.asItem())
                .inputFluids(Blaze.getFluid(1000))
                .outputFluids(Mana.getFluid(1000))
                .circuitMeta(2)
                .EUt(51200/25)
                .duration(25)
                .save(provider);
        ETERNAL_GARDEN.recipeBuilder("thermalily3")//炽玫瑰-岩浆
                .notConsumable(BotaniaFlowerBlocks.thermalily.asItem())
                .inputFluids(Lava.getFluid(1000))
                .outputFluids(Mana.getFluid(500))
                .circuitMeta(3)
                .EUt(2048)
                .duration(20)
                .save(provider);
        ETERNAL_GARDEN.recipeBuilder("wither_aconite1")//凋零菟葵-量子之星
                .notConsumable(witherAconite.asItem())
                .inputItems(NETHER_STAR)
                .outputFluids(Mana.getFluid(150000))
                .circuitMeta(1)
                .EUt(8192)
                .duration(2000)
                .save(provider);
        ETERNAL_GARDEN.recipeBuilder("wither_aconite2")//凋零菟葵-下界之星
                .notConsumable(witherAconite.asItem())
                .inputItems(QUANTUM_STAR)
                .outputFluids(Mana.getFluid(500000))
                .circuitMeta(2)
                .EUt(32768)
                .duration(2500)
                .save(provider);
        ETERNAL_GARDEN.recipeBuilder("reikarlily12")//雷卡兰-12
                .notConsumable(reikarlily.asItem())
                .outputFluids(Mana.getFluid(1))
                .circuitMeta(12)
                .EUt(522468)
                .duration(100)
                .save(provider);
        ETERNAL_GARDEN.recipeBuilder("reikarlily24")//雷卡兰-24
                .notConsumable(reikarlily.asItem())
                .outputFluids(Mana.getFluid(1))
                .circuitMeta(24)
                .EUt(512)
                .duration(100)
                .save(provider);
    }
}
