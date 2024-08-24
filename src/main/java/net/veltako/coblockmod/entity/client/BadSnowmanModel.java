package net.veltako.coblockmod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.veltako.coblockmod.CoblockMod;
import net.veltako.coblockmod.entity.custom.BadSnowmanEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BadSnowmanModel extends AnimatedGeoModel<BadSnowmanEntity> {
    @Override
    public ResourceLocation getModelResource(BadSnowmanEntity badSnowmanEntity) {
        return new ResourceLocation(CoblockMod.MOD_ID, "geo/bad_snowman.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BadSnowmanEntity badSnowmanEntity) {
        return new ResourceLocation(CoblockMod.MOD_ID, "textures/entity/bad_snowman.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BadSnowmanEntity badSnowmanEntity) {
        return new ResourceLocation(CoblockMod.MOD_ID, "animations/bad_snowman.animation.json");
    }
}
