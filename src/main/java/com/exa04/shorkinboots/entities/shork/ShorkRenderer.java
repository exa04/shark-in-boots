package com.exa04.shorkinboots.entities.shork;

import com.exa04.shorkinboots.ShorkInBootsMod;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ShorkRenderer extends MobRenderer<Shork, ShorkModel<Shork>> {
    public ShorkRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new ShorkModel<>(ctx.bakeLayer(ShorkInBootsMod.ModelLayers.SHORK_LAYER)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(Shork entity) {
        return new ResourceLocation(ShorkInBootsMod.MODID, "textures/entity/shork.png");
    }

    @Override
    public void render(Shork entity, float entityYaw, float partialTricks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        if (entity.isBaby()) {
            matrixStack.scale(0.6f, 0.5f, 0.6f);
        }
        super.render(entity, entityYaw, partialTricks, matrixStack, buffer, packedLight);
    }
}
