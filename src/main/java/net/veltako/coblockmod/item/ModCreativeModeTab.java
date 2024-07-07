package net.veltako.coblockmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab COBLOCK_TAB = new CreativeModeTab("coblocktab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(CoblockItems.COBLOCK.get());
        }
    };
}
