package net.veltako.coblockmod.event;

import net.veltako.coblockmod.CoblockMod;
import net.veltako.coblockmod.block.AgriumBlocks;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CoblockMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BlockBreakEventHandler {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getPlayer() != null && event.getState().getBlock() == AgriumBlocks.NETHER_AGRIUM_ORE_OVER_MELTED.get()) {
            event.getPlayer().addEffect(new MobEffectInstance(MobEffects.POISON, 200, 3)); // Effet de poison pendant 10 secondes (200 ticks)
        }
    }
}
