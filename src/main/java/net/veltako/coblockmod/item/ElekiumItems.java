package net.veltako.coblockmod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.veltako.coblockmod.CoblockMod;

public class ElekiumItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CoblockMod.MOD_ID);

    public static final RegistryObject<Item> ELEKIUM_INGOT = ITEMS.register("elekium_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.COBLOCK_TAB)));

    public static final RegistryObject<Item> ELEKIUM_NUGGET = ITEMS.register("elekium_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.COBLOCK_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
