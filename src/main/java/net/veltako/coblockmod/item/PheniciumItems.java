package net.veltako.coblockmod.item;

import net.veltako.coblockmod.CoblockMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PheniciumItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CoblockMod.MOD_ID);

    public static final RegistryObject<Item> PHENICIUM_INGOT = ITEMS.register("phenicium_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.COBLOCK_TAB).fireResistant()));

    public static final RegistryObject<Item> PHENICIUM_RAW = ITEMS.register("phenicium_raw",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.COBLOCK_TAB).fireResistant()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
