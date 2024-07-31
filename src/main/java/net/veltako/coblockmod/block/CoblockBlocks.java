package net.veltako.coblockmod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.veltako.coblockmod.CoblockMod;
import net.veltako.coblockmod.item.CoblockItems;
import net.veltako.coblockmod.item.ModCreativeModeTab;

import java.util.function.Supplier;

public class CoblockBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, CoblockMod.MOD_ID);

    public static final RegistryObject<Block> COBLOCK_BLOCK = registryBlock("coblock_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(55f)
                    .explosionResistance(1200f)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
            ), ModCreativeModeTab.COBLOCK_TAB
    );

    public static final RegistryObject<Block> COBLOCK_ORE = registryBlock("coblock_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(50f)
                    .requiresCorrectToolForDrops()
                    .explosionResistance(1000f),
                    UniformInt.of(5, 9)
            ), ModCreativeModeTab.COBLOCK_TAB
    );

    public static final RegistryObject<Block> DEEPSLATE_COBLOCK_ORE = registryBlock("deepslate_coblock_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(50f)
                    .requiresCorrectToolForDrops()
                    .explosionResistance(1200f)
                    .sound(SoundType.DEEPSLATE),
                    UniformInt.of(5, 9)
            ), ModCreativeModeTab.COBLOCK_TAB
    );

    public static final RegistryObject<Block> NETHER_COBLOCK_ORE = registryBlock("nether_coblock_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(50f)
                    .requiresCorrectToolForDrops()
                    .explosionResistance(1000f)
                    .sound(SoundType.NETHER_ORE),
                    UniformInt.of(5, 9)
            ), ModCreativeModeTab.COBLOCK_TAB
    );

    public static final RegistryObject<Block> END_STONE_COBLOCK_ORE = registryBlock("end_stone_coblock_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(50f)
                    .requiresCorrectToolForDrops()
                    .explosionResistance(1200f)
                    .requiresCorrectToolForDrops(),
                    UniformInt.of(5, 9)
            ), ModCreativeModeTab.COBLOCK_TAB
    );


    private static <T extends Block> RegistryObject<T> registryBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registryBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registryBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return CoblockItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab).fireResistant()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
