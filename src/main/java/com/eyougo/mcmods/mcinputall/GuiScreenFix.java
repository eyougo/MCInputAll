package com.eyougo.mcmods.mcinputall;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

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
            currentScreen = Minecraft.class.getDeclaredField("currentScreen");
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
}
