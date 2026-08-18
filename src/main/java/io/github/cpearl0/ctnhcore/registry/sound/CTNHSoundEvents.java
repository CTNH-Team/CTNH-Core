package io.github.cpearl0.ctnhcore.registry.sound;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CTNHSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT,
            CTNHCore.MODID);

    public static final RegistryObject<SoundEvent> EASTER_EGG_CLOWN = SOUND_EVENTS.register("easter_egg_clown",
            () -> SoundEvent.createVariableRangeEvent(CTNHCore.id("easter_egg_clown")));
}
