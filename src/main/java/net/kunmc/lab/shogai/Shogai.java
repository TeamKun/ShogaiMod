package net.kunmc.lab.shogai;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

@Mod(modid = Shogai.MOD_ID, name = Shogai.MOD_NAME, version = Shogai.VERSION)
public class Shogai {

    public static final String MOD_ID = "shogai";
    public static final String MOD_NAME = "Shogai";
    public static final String VERSION = "1.0-SNAPSHOT";

    @Mod.Instance(MOD_ID)
    public static Shogai INSTANCE;

    @SideOnly(Side.CLIENT)
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) throws NoSuchFieldException, IllegalAccessException {

        Field field;
        try {
            field = Minecraft.class.getDeclaredField("field_110449_ao");
        } catch (NoSuchFieldException e) {
            field = Minecraft.class.getDeclaredField("defaultResourcePacks"); // IDE
        }
        field.setAccessible(true);
        List<IResourcePack> resourcePackList = (List<IResourcePack>) field.get(Minecraft.getMinecraft());
        File file = new File("./mods/" + MOD_ID + "-" + VERSION + ".jar");
        if (!file.exists()) {
            // IDE
            file = new File(getClass().getClassLoader().getResource("colorBlind").getPath());
            resourcePackList.add(new FolderResourcePack(file));
        } else {
            resourcePackList.add(new FileResourcePack(file));
        }
        Minecraft.getMinecraft().refreshResources();

    }
}
