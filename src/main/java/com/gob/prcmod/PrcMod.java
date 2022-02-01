package com.gob.prcmod;

import com.gob.prcmod.block.ModBlocks;
import com.gob.prcmod.block.ModFluids;
import com.gob.prcmod.events.ModEvents;
import com.gob.prcmod.item.ModItems;
import com.gob.prcmod.setup.ClientProxy;
import com.gob.prcmod.setup.IProxy;
import com.gob.prcmod.setup.ServerProxy;
import com.gob.prcmod.util.Config;
import com.gob.prcmod.util.Registration;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PrcMod.MOD_ID)
public class PrcMod
{

    public static final String MOD_ID = "prcmod";

    public static final ItemGroup GOO_TAB = new ItemGroup("gooTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.GOO_INGOT.get());
        }
    };

    public static IProxy proxy;

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public PrcMod() {

        proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        registerModAdditions();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    registerConfigs();

    proxy.init();

    loadConfigs();
    }


    private void registerConfigs() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);
    }

    private void loadConfigs() {
        Config.LoadConfigFile(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("prcmod-client.toml").toString());
        Config.LoadConfigFile(Config.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("prcmod-server.toml").toString());
    }

    private void registerModAdditions() {
        // Inits addition registration
        Registration.init();
        // registers mod items
        ModItems.register();
        // registers mod blocks
        ModBlocks.register();
        // register mod fluids ;^)
        ModFluids.register();


        // registers mod events
        MinecraftForge.EVENT_BUS.register(new ModEvents());
    }




    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
