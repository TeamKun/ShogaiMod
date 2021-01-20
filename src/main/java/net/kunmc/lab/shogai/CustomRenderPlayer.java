package net.kunmc.lab.shogai;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.ai.attributes.IAttributeInstance;

public class CustomRenderPlayer extends RenderPlayer {
    public CustomRenderPlayer(RenderManager renderManager) {
        super(renderManager);
    }

    public CustomRenderPlayer(RenderManager renderManager, boolean useSmallArms) {
        super(renderManager, useSmallArms);
    }

    @Override
    protected void renderLayers(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scaleIn)
    {
        for (LayerRenderer<AbstractClientPlayer> layerrenderer : this.layerRenderers)
        {
            boolean flag = this.setBrightness(entitylivingbaseIn, partialTicks, layerrenderer.shouldCombineTextures());
            if (layerrenderer.getClass().equals(LayerHeldItem.class)) {
                IAttributeInstance attribute = entitylivingbaseIn.getEntityAttribute(HandicapAttributes.LIMB);
                double value = attribute.getAttributeValue();
                if (value < 4) {
                    layerrenderer.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleIn);
                }
            } else {
                layerrenderer.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleIn);
            }

            if (flag)
            {
                this.unsetBrightness();
            }
        }
    }
}
