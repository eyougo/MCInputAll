package com.eyougo.mcmods.mcinputall;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * User: mei
 * Date: 1/20/14
 * Time: 11:33
 */
public class GuiScreenFix {
    private static Minecraft minecraft;
    private static Method keyTyped;
    private static Field currentScreen;

    static {
        try {
            currentScreen = Minecraft.class.getDeclaredField(MCInputAll.RUNTIME_DEOBF?"field_71462_r":"currentScreen");
            currentScreen.setAccessible(true);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public static void init(Minecraft mc) {
        minecraft = mc;
    }

    public static void keyTyped(char c, int k) {
        try {
            keyTyped = currentScreen.get(minecraft).getClass().getDeclaredMethod(MCInputAll.RUNTIME_DEOBF ? "func_73869_a" : "keyTyped", char.class, int.class);
            keyTyped.setAccessible(true);
            keyTyped.invoke(currentScreen.get(minecraft), c, k);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public static void func_71389_H(){
        FMLLog.log(Level.INFO, "func_71389_H");
    }
    public static void func_71384_a(){
        FMLLog.log(Level.INFO, "func_71384_a");
    }
    public static void func_110436_a(){
        FMLLog.log(Level.INFO, "func_110436_a");
    }
    public static void func_110435_P(){
        FMLLog.log(Level.INFO, "func_110435_P");
    }
    public static void func_71357_I(){
        FMLLog.log(Level.INFO, "func_71357_I");
    }
    public static void func_71405_e(){
        FMLLog.log(Level.INFO, "func_71405_e");
    }
    public static void func_99999_d(){
        FMLLog.log(Level.INFO, "func_99999_d");
    }
    public static void func_71411_J(){
        FMLLog.log(Level.INFO, "func_71411_J");
    }
    public static void func_147120_f(){
        FMLLog.log(Level.INFO, "func_147120_f");
    }
    public static void func_71398_f(){
        FMLLog.log(Level.INFO, "func_71398_f");
    }
    public static void func_71365_K(){
        FMLLog.log(Level.INFO, "func_71365_K");
    }
    public static void func_71400_g(){
        FMLLog.log(Level.INFO, "func_71400_g");
    }
    public static void func_71381_h(){
        FMLLog.log(Level.INFO, "func_71381_h");
    }
    public static void func_71364_i(){
        FMLLog.log(Level.INFO, "func_71364_i");
    }
    public static void func_71385_j(){
        FMLLog.log(Level.INFO, "func_71385_j");
    }
    public static void func_147116_af(){
        FMLLog.log(Level.INFO, "func_147116_af");
    }
    public static void func_147121_ag(){
        FMLLog.log(Level.INFO, "func_147121_ag");
    }
    public static void func_71352_k(){
        FMLLog.log(Level.INFO, "func_71352_k");
    }
    public static void func_147119_ah(){
        FMLLog.log(Level.INFO, "func_147119_ah");
    }
    public static void func_71407_l(){
        FMLLog.log(Level.INFO, "func_71407_l");
    }
    public static void func_147112_ai(){
        FMLLog.log(Level.INFO, "func_147112_ai");
    }
    public static void func_147106_B(){
        FMLLog.log(Level.INFO, "func_147106_B");
    }
    public static void func_71363_D(){
        FMLLog.log(Level.INFO, "func_71363_D");
    }
}
