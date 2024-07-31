package net.veltako.coblockmod.event;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.veltako.coblockmod.CoblockMod;
import net.veltako.coblockmod.block.CoblockBlocks;

@Mod.EventBusSubscriber(modid = CoblockMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    @SubscribeEvent
    public static void onBlockBreakEvent(BlockEvent.BreakEvent event) {
        BlockState state = event.getState();
        if (state.getBlock() == CoblockBlocks.COBLOCK_ORE.get() ||
                state.getBlock() == CoblockBlocks.DEEPSLATE_COBLOCK_ORE.get() ||
                state.getBlock() == CoblockBlocks.NETHER_COBLOCK_ORE.get() ||
                state.getBlock() == CoblockBlocks.END_STONE_COBLOCK_ORE.get()) {
            if (event.getPlayer().getMainHandItem().getItem() != Items.GOLDEN_PICKAXE) {
                event.setCanceled(true);
            }
        }
    }
}
