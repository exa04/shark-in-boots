package com.exa04.shorkinboots.datagen;

import com.exa04.shorkinboots.ShorkInBootsMod;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_SHORK = registerKey("add_shork");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        context.register(ADD_SHORK, ForgeBiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(
                biomes.getOrThrow(BiomeTags.IS_BEACH),
                new MobSpawnSettings.SpawnerData(ShorkInBootsMod.SHORK_IN_BOOTS.get(), 80, 2, 6)
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(ShorkInBootsMod.MODID, name));
    }
}
