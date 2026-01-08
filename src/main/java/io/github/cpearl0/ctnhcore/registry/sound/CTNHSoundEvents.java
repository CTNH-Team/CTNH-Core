package io.github.cpearl0.ctnhcore.registry.sound;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CTNHSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT,
            CTNHCore.MODID);
    public static final RegistryObject<SoundEvent> AMBIENT_ASTRAL = SOUND_EVENTS.register("ambient_astral",
            () -> SoundEvent.createVariableRangeEvent(CTNHCore.id("ambient_astral")));
}
