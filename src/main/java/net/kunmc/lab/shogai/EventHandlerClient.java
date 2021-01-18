package net.kunmc.lab.shogai;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Shogai.MOD_ID)
public class EventHandlerClient {
    @SubscribeEvent
    public static void sound(PlaySoundEvent e) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player == null) {
            return;
        }
        IAttributeInstance attribute = player.getEntityAttribute(HandicapAttributes.NO_HEARING);
        double value = attribute.getAttributeValue();
        if (value < 1) {
            return;
        }
        e.setResultSound(null);
    }

    @SubscribeEvent
    public static void onRender(RenderPlayerEvent.Pre e) {
        IAttributeInstance attribute = e.getEntityPlayer().getEntityAttribute(HandicapAttributes.LIMB);
        double value = attribute.getAttributeValue();
        if (value < 1) {
            return;
        }
        if (e.getRenderer() instanceof CustomRenderPlayer) {
            CustomRenderPlayer render = (CustomRenderPlayer) e.getRenderer();
            ModelPlayer modelPlayer = render.getMainModel();
            if (value >= 1) {
                modelPlayer.bipedLeftArm.render(0);
                modelPlayer.bipedLeftArmwear.render(0);
            }
            if (value >= 2) {
                modelPlayer.bipedRightArm.render(0);
                modelPlayer.bipedRightArmwear.render(0);
            }
            if (value >= 3) {
                modelPlayer.bipedRightLeg.render(0);
                modelPlayer.bipedRightLegwear.render(0);
            }
            if (value >= 4) {
                modelPlayer.bipedLeftLeg.render(0);
                modelPlayer.bipedLeftLegwear.render(0);
            }
            return;
        }
        e.setCanceled(true);
        double y = value < 4 ? e.getY() : e.getY() - 0.6d;
        new CustomRenderPlayer(e.getRenderer().getRenderManager(), ((AbstractClientPlayer) e.getEntityPlayer()).getSkinType().equals("slim")).doRender((AbstractClientPlayer) e.getEntityPlayer(), e.getX(), y ,e.getZ(), e.getEntityPlayer().rotationYawHead, e.getPartialRenderTick());
    }

    @SubscribeEvent
    public static void onRenderHand(RenderSpecificHandEvent e) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        IAttributeInstance attribute = player.getEntityAttribute(HandicapAttributes.LIMB);
        double value = attribute.getAttributeValue();
        if (value < 2) {
            return;
        }
        e.setCanceled(true);

    }

    private static final ResourceLocation SHADER_LOCATION = new ResourceLocation(Shogai.MOD_ID, "shaders/post/color_convolve.json");

    @SubscribeEvent
    public static void onRender(RenderGameOverlayEvent.Pre e) {
        ShaderGroup shaderGroup = Minecraft.getMinecraft().entityRenderer.getShaderGroup();
        if (shaderGroup == null) {
            Minecraft.getMinecraft().entityRenderer.loadShader(SHADER_LOCATION);
            return;
        }
        if (!shaderGroup.getShaderGroupName().equals(SHADER_LOCATION.toString())) {
            Minecraft.getMinecraft().entityRenderer.loadShader(SHADER_LOCATION);
        }
    }
}
