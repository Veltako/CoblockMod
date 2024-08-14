package net.veltako.coblockmod.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.veltako.coblockmod.CoblockMod;
import net.veltako.coblockmod.fluid.ModFluids;
import net.veltako.coblockmod.item.AgriumItems;
import net.veltako.coblockmod.item.CoblockItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = CoblockMod.MOD_ID)
public class ItemWaterEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemWaterEventHandler.class);
    private static final Set<ItemEntity> itemsToCheck = new HashSet<>();

    @SubscribeEvent
    public static void onItemEntityJoinLevel(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof ItemEntity)) {
            return;
        }

        ItemEntity itemEntity = (ItemEntity) event.getEntity();
        Item item = itemEntity.getItem().getItem();

        // Ajouter un message de débogage pour vérifier si l'événement est capturé
        LOGGER.debug("EntityJoinLevelEvent captured: {}", item);

        // Vérifie si l'item est COBLOCK_POWDER
        if (item == CoblockItems.COBLOCK_POWDER.get() || item == AgriumItems.AGRIUM_POWDER.get()) {
            // Ajouter un message de débogage pour vérifier si l'item est COBLOCK_POWDER
            LOGGER.debug("Item is COBLOCK_POWDER");
            itemsToCheck.add(itemEntity);
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            return;
        }

        Set<ItemEntity> itemsToRemove = new HashSet<>();
        for (ItemEntity itemEntity : itemsToCheck) {
            if (!itemEntity.isAlive()) {
                itemsToRemove.add(itemEntity);
                continue;
            }

            BlockPos itemPos = itemEntity.blockPosition();
            Item item = itemEntity.getItem().getItem();
            if (isItemInWater(itemEntity, itemPos)) {
                if (item == CoblockItems.COBLOCK_POWDER.get() || item == AgriumItems.AGRIUM_POWDER.get())
                {
                    itemEntity.discard(); // Supprime l'item
                    itemsToRemove.add(itemEntity);
                }
            } else if (isItemInLava(itemEntity, itemPos)) {
                BlockState soapWaterState = ModFluids.SOURCE_SOAP_WATER.get().defaultFluidState().createLegacyBlock();
                itemEntity.level.setBlock(itemPos, soapWaterState, 3);
                itemEntity.discard(); // Supprime l'item
            }
        }
        itemsToCheck.removeAll(itemsToRemove);
    }

    private static boolean isItemInWater(ItemEntity itemEntity, BlockPos pos) {
        return itemEntity.level.getBlockState(pos).getBlock() == Blocks.WATER;
    }
    private static boolean isItemInLava(ItemEntity itemEntity, BlockPos pos) {
        return itemEntity.level.getBlockState(pos).getBlock() == Blocks.LAVA;
    }
}
