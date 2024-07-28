package net.veltako.coblockmod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.veltako.coblockmod.CoblockMod;

public class AgriumItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CoblockMod.MOD_ID);

    public static final RegistryObject<Item> AGRIUM_SHARD = ITEMS.register("agrium_shard",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.COBLOCK_TAB)));

    public static final RegistryObject<Item> AGRIUM_POWDER = ITEMS.register("agrium_powder",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.COBLOCK_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
