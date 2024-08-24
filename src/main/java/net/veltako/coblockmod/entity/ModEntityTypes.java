package net.veltako.coblockmod.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.veltako.coblockmod.CoblockMod;
import net.veltako.coblockmod.entity.custom.BadSnowmanEntity;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CoblockMod.MOD_ID);

    public static final RegistryObject<EntityType<BadSnowmanEntity>> BAD_SNOWMAN =
            ENTITY_TYPES.register("bad_snowman",
                    () -> EntityType.Builder.of(BadSnowmanEntity::new, MobCategory.MONSTER)
                            .sized(1f, 2.7f)
                            .build(new ResourceLocation(CoblockMod.MOD_ID, "bad_snowman").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
