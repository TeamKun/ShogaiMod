package net.kunmc.lab.shogai;

import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Shogai.MOD_ID, name = Shogai.MOD_NAME, version = Shogai.VERSION)
public class Shogai {

    public static final String MOD_ID = "shogai";
    public static final String MOD_NAME = "Shogai";
    public static final String VERSION = "1.0-SNAPSHOT";

    @Mod.Instance(MOD_ID)
    public static Shogai INSTANCE;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Framebuffer framebuffer = Minecraft.getMinecraft().getFramebuffer();
        framebuffer.enableStencil();
    }
}
