package net.veltako.coblockmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.veltako.coblockmod.CoblockMod;
import net.veltako.coblockmod.entity.custom.BadSnowmanEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BadSnowmanRenderer extends GeoEntityRenderer<BadSnowmanEntity> {

    public BadSnowmanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BadSnowmanModel());
        this.shadowRadius = 1f;
    }

    @Override
    public ResourceLocation getTextureLocation(BadSnowmanEntity animatable) {
        return new ResourceLocation(CoblockMod.MOD_ID, "textures/entity/bad_snowman.png");
    }

    @Override
    public RenderType getRenderType(BadSnowmanEntity animatable, float partialTick, PoseStack poseStack,
                                    @Nullable MultiBufferSource bufferSource,
                                    @Nullable VertexConsumer buffer,
                                    int packedLight, ResourceLocation texture) {
        poseStack.scale( 1f, 1f, 1f);
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
