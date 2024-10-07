package com.exa04.shorkinboots.sound;

import com.exa04.shorkinboots.ShorkInBootsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Sounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ShorkInBootsMod.MODID);

    public static final RegistryObject<SoundEvent> SHORK_AMBIENT = registerSoundEvents("shork_ambient");
    public static final RegistryObject<SoundEvent> SHORK_HURT = registerSoundEvents("shork_hurt");
    public static final RegistryObject<SoundEvent> SHORK_DEATH = registerSoundEvents("shork_death");
    public static final RegistryObject<SoundEvent> SHORK_NOM = registerSoundEvents("shork_nom");

    public static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ShorkInBootsMod.MODID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
