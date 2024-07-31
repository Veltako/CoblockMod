package net.veltako.coblockmod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.veltako.coblockmod.CoblockMod;
import net.veltako.coblockmod.item.PheniciumItems;
import net.veltako.coblockmod.item.ModCreativeModeTab;

import java.util.function.Supplier;

public class PheniciumBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, CoblockMod.MOD_ID);

    public static final RegistryObject<Block> PHENICIUM_BLOCK = registryBlock("phenicium_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(35f)
                    .requiresCorrectToolForDrops()
            ), ModCreativeModeTab.COBLOCK_TAB
    );


    public static final RegistryObject<Block> NETHER_PHENICIUM_ORE = registryBlock("nether_phenicium_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(30f)
                    .requiresCorrectToolForDrops(),
                    UniformInt.of(4, 8)
            ), ModCreativeModeTab.COBLOCK_TAB
    );

    private static <T extends Block> RegistryObject<T> registryBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registryBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registryBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return PheniciumItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab).fireResistant()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
