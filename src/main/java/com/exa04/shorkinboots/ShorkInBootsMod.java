package com.exa04.shorkinboots;

import com.exa04.shorkinboots.entities.shork.Shork;
import com.exa04.shorkinboots.entities.shork.ShorkModel;
import com.exa04.shorkinboots.entities.shork.ShorkRenderer;
import com.exa04.shorkinboots.sound.Sounds;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ShorkInBootsMod.MODID)
public class ShorkInBootsMod {
    public static final String MODID = "shorkinboots";
    protected static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);
    public static final RegistryObject<EntityType<Shork>> SHORK_IN_BOOTS = ENTITIES.register("shork_in_boots", () ->
            EntityType.Builder
                    .of(Shork::new, MobCategory.CREATURE)
                    .sized(0.8F, 0.5F)
                    .build(new ResourceLocation(MODID, "shork_in_boots").toString()));

    public class ModelLayers {
        public static final ModelLayerLocation SHORK_LAYER = new ModelLayerLocation(
                new ResourceLocation(ShorkInBootsMod.MODID, "shork_layer"),
                "main");
    }

    public ShorkInBootsMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        ENTITIES.register(modEventBus);
        Sounds.register(modEventBus);

        // modEventBus.addListener(this::addCreative);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
    }

//    private void addCreative(BuildCreativeModeTabContentsEvent event) {
//    }

    @Mod.EventBusSubscriber(modid = ShorkInBootsMod.MODID)
    public static class Events {
        @SubscribeEvent
        public static void feedShork(PlayerInteractEvent.EntityInteract event) {
            if (event.getTarget().getType() == ShorkInBootsMod.SHORK_IN_BOOTS.get()
                    && !event.getItemStack().isEmpty()
                    && event.getItemStack().getItem() == Items.COOKIE && event.getSide().isServer()) {
                event.getTarget().playSound(Sounds.SHORK_NOM.get());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(SHORK_IN_BOOTS.get(), ShorkRenderer::new);
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }

        @SubscribeEvent
        public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(ModelLayers.SHORK_LAYER, ShorkModel::createBodyLayer);
        }
    }

    @Mod.EventBusSubscriber(modid = ShorkInBootsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ShorkInBootsMod.SHORK_IN_BOOTS.get(), Shork.createAttributes().build());
        }
    }
}

