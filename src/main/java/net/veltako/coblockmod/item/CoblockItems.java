package net.veltako.coblockmod.item;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.veltako.coblockmod.CoblockMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CoblockItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CoblockMod.MOD_ID);

    public static final RegistryObject<Item> COBLOCK_INGOT = ITEMS.register("coblock_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.COBLOCK_TAB).fireResistant()));

    public static final RegistryObject<Item> COBLOCK_NUGGET = ITEMS.register("coblock_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.COBLOCK_TAB).fireResistant()));

    public static final RegistryObject<Item> COBLOCK_POWDER = ITEMS.register("coblock_powder",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.COBLOCK_TAB)));

    public static final RegistryObject<Item> COBLOCK_PICKAXE = ITEMS.register("coblock_pickaxe",
            () -> new PickaxeItem(Tiers.NETHERITE, 10, 5f,
                    new Item.Properties().tab(ModCreativeModeTab.COBLOCK_TAB).fireResistant().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
