package net.veltako.coblockmod.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.veltako.coblockmod.CoblockMod;
import net.veltako.coblockmod.entity.ModEntityTypes;
import net.veltako.coblockmod.entity.custom.BadSnowmanEntity;

public class ModEvents {

    @Mod.EventBusSubscriber(modid = CoblockMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)

    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.BAD_SNOWMAN.get(), BadSnowmanEntity.setAttributes());
        }
    }
}
