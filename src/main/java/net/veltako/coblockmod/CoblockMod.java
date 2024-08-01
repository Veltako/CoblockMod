package net.veltako.coblockmod;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.veltako.coblockmod.block.AgriumBlocks;
import net.veltako.coblockmod.block.CoblockBlocks;
import net.veltako.coblockmod.block.ElekiumBlocks;
import net.veltako.coblockmod.block.PheniciumBlocks;
import net.veltako.coblockmod.event.BlockBreakEventHandler;
import net.veltako.coblockmod.event.ItemWaterEventHandler;
import net.veltako.coblockmod.item.AgriumItems;
import net.veltako.coblockmod.item.CoblockItems;
import net.veltako.coblockmod.item.ElekiumItems;
import net.veltako.coblockmod.item.PheniciumItems;
import net.veltako.coblockmod.world.feature.ModConfiguredFeatures;
import net.veltako.coblockmod.world.feature.ModPlacedFeatures;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CoblockMod.MOD_ID)
public class CoblockMod {
    public static final String MOD_ID = "coblockmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CoblockMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        CoblockItems.register(modEventBus);
        CoblockBlocks.register(modEventBus);

        PheniciumItems.register(modEventBus);
        PheniciumBlocks.register(modEventBus);

        ElekiumItems.register(modEventBus);
        ElekiumBlocks.register(modEventBus);

        AgriumItems.register(modEventBus);
        AgriumBlocks.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        // Enregistrement du gestionnaire d'événements de rupture de bloc
        MinecraftForge.EVENT_BUS.register(BlockBreakEventHandler.class);
        MinecraftForge.EVENT_BUS.register(ItemWaterEventHandler.class);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
