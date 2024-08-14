package net.veltako.coblockmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.veltako.coblockmod.CoblockMod;
import net.veltako.coblockmod.fluid.ModFluids;

public class AgriumItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CoblockMod.MOD_ID);

    public static final RegistryObject<Item> AGRIUM_SHARD = ITEMS.register("agrium_shard",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.COBLOCK_TAB)));

    public static final RegistryObject<Item> AGRIUM_POWDER = ITEMS.register("agrium_powder",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.COBLOCK_TAB)));

    public static final RegistryObject<Item> SOAP_WATER_BUCKET = ITEMS.register("soap_water_bucket",
            () -> new BucketItem(ModFluids.SOURCE_SOAP_WATER,
                    new Item.Properties().tab(ModCreativeModeTab.COBLOCK_TAB).craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> AGRIUM_APPLE = ITEMS.register("agrium_apple",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.COBLOCK_TAB).food(Food.AGRIUM_APPLE)));

    public static class Food {
        public static final FoodProperties AGRIUM_APPLE = new FoodProperties.Builder()
                .nutrition(20)
                .saturationMod(1f)
                .alwaysEat()
                .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 6), 1f)
                .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 6), 1f)
                .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 200, 6), 1f)
                .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 6), 1f)
                .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 6), 1f)
                .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 200, 6), 1f)
                .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 6), 1f)
                .effect(() -> new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 200, 6), 1f)
                .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 6), 1f)
                .effect(() -> new MobEffectInstance(MobEffects.LUCK, 200, 6), 1f)
                .effect(() -> new MobEffectInstance(MobEffects.JUMP, 200, 6), 1f)
                .effect(() -> new MobEffectInstance(MobEffects.HEAL, 200, 6), 1f)
                .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 200, 6), 1f)
                .build();
    }
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
