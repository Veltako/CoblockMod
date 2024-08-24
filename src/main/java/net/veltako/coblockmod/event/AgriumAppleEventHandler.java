package net.veltako.coblockmod.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.server.MinecraftServer;
import net.veltako.coblockmod.CoblockMod;
import net.veltako.coblockmod.item.AgriumItems;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = CoblockMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AgriumAppleEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger("CoblockMod");

    private static final Map<UUID, Integer> playersToAffect = new HashMap<>();

    @SubscribeEvent
    public static void onItemUseFinish(LivingEntityUseItemEvent.Finish event) {
        LivingEntity entity = event.getEntity();
        ItemStack itemStack = event.getItem();
        Level level = entity.getLevel();

        if (entity instanceof Player && itemStack.getItem() == AgriumItems.AGRIUM_APPLE.get()) {
            if (!level.isClientSide && entity instanceof ServerPlayer) {
                ServerPlayer player = (ServerPlayer) entity;

                // Ajoute le joueur dans la map avec un délai de 200 ticks (10 secondes)
                playersToAffect.put(player.getUUID(), 200);

                // Debug: Message pour vérifier l'ajout du joueur
                LOGGER.info("Player {} added to affect list with 200 ticks remaining.", player.getName().getString());
            }
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            return;
        }

        MinecraftServer server = event.getServer();

        // Réduit le délai pour chaque joueur
        playersToAffect.entrySet().removeIf(entry -> {
            UUID playerId = entry.getKey();
            int ticksLeft = entry.getValue() - 1;

            if (ticksLeft <= 0) {
                ServerPlayer player = server.getPlayerList().getPlayer(playerId);

                // Vérifie que le joueur est connecté et vivant
                if (player != null && player.isAlive()) {
                    player.setHealth(2.0F); // Définit la santé à 1 cœur

                    // Debug: Message pour vérifier la modification de la santé
                    LOGGER.info("Player {} health set to 1 heart.", player.getName().getString());
                } else {
                    LOGGER.warn("Player {} not found or is not alive.", playerId);
                }

                return true; // Retire le joueur de la map après avoir appliqué l'effet
            } else {
                entry.setValue(ticksLeft);
                return false; // Garde le joueur dans la map
            }
        });
    }
}
