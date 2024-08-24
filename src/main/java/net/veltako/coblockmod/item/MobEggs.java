package net.veltako.coblockmod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.veltako.coblockmod.CoblockMod;
import net.veltako.coblockmod.entity.ModEntityTypes;

public class MobEggs {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CoblockMod.MOD_ID);

    public static final RegistryObject<Item> BAD_SNOWMAN_EGG = ITEMS.register("bad_snowman_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.BAD_SNOWMAN, 0xd9d9d9, 0xc06316,
                    new Item.Properties().tab(ModCreativeModeTab.COBLOCK_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
