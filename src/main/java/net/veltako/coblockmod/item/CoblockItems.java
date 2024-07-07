package net.veltako.coblockmod.item;

import net.veltako.coblockmod.CoblockMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CoblockItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CoblockMod.MOD_ID);

    public static final RegistryObject<Item> COBLOCK = ITEMS.register("coblock",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.COBLOCK_TAB)));
    public static final RegistryObject<Item> RAW_COBLOCK = ITEMS.register("raw_coblock",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.COBLOCK_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
