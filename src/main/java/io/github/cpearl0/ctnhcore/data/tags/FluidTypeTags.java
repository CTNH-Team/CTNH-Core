package io.github.cpearl0.ctnhcore.data.tags;

import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import wayoftime.bloodmagic.common.fluid.BloodMagicFluids;

import java.util.Objects;

public class FluidTypeTags {
    // Why did I put Genshin all here? Why not? Science isn't about why, it's about why NOT!
    // Why is so much of our Genshin dangerous? Why don't you marry safe Genshin if you Skip the cutscene so much!?
    // IN FACT, WHY NOT INVENT A SAFETY DOOR THAT WON'T JUMP OVER UR LIFE BECAUSE YOU'RE FIRED!!!
    // Not you test subject, you're doing fine.
    // YES, YOU. BOX. YOUR Genshin STUFF. OUT THE MHY DOOR. PARKING LOT. CAR. GOODBYE!
    public static final TagKey<Fluid> QUANTUM_FLUIDS = TagUtil.createFluidTag("quantum");
    public static final TagKey<Fluid> ANTI_FLUIDS = TagUtil.createFluidTag("anti");
    public static final TagKey<Fluid> BLOOD = TagUtil.createFluidTag("blood");
    public static void init(RegistrateTagsProvider<Fluid> provider) {
        create(provider, BLOOD, BloodMagicFluids.LIFE_ESSENCE_FLUID.get());
    }
    public static void create(RegistrateTagsProvider<Fluid> provider, TagKey<Fluid> tagKey, Fluid... rls) {
        var builder = provider.addTag(tagKey);
        for (Fluid fluid : rls) {
            builder.addOptional(Objects.requireNonNull(ForgeRegistries.FLUIDS.getKey(fluid)));
        }
    }
}
