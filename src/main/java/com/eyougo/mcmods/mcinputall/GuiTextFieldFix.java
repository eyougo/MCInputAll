package com.eyougo.mcmods.mcinputall;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiTextField;
import org.apache.logging.log4j.Level;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import scala.Char;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * User: mei
 * Date: 1/21/14
 * Time: 10:26
 */
public class GuiTextFieldFix {
//    private static Field lX;
//    private static Field lY;
//    private static Field rX;
//    private static Field rY;
//    private static Field fontRenderer;
    private static JFrame textFrame;
    private static JTextField textField;

    public static synchronized void createTextFrame(GuiTextField guiTextField){

        if (textFrame == null){
            textFrame = new JFrame();
            textFrame.setUndecorated(true);
            textFrame.setBackground(new Color(0, 0, 0, 0));
            textFrame.getContentPane().setLayout(new java.awt.FlowLayout());
            textField = new JTextField(50);
            textField.setFont(new Font("SansSerif", 0, 16));
            textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            textField.setVisible(true);
            textFrame.getContentPane().add(textField, "South");
            textFrame.pack();
            textFrame.setVisible(true);

            textFrame.setLocation(Display.getX(), Display.getY() + Display.getHeight() - 8);
            InputMap im = textField.getInputMap();
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");

            ActionMap am = textField.getActionMap();
            am.put("enter", new EnterAction(guiTextField));
            am.put("escape", new EscapeAction());

            FMLLog.log(Level.INFO, "init text input");
        }
    }

    static class EnterAction extends AbstractAction{
        private GuiTextField guiTextField;
        public EnterAction(GuiTextField guiTextField){
            this.guiTextField = guiTextField;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            textFrame.dispose();
            textFrame = null;
            guiTextField.func_146180_a(textField.getText());
            GuiScreenFix.keyTyped((char)Keyboard.KEY_RETURN,Keyboard.KEY_RETURN);
        }
    }

    static class EscapeAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent ae) {
            textFrame.dispose();
            textFrame = null;
            GuiScreenFix.keyTyped((char)Keyboard.KEY_ESCAPE,Keyboard.KEY_ESCAPE);
        }
    }


    public static void test(GuiTextField guiTextField, String s){

        FMLLog.log(Level.INFO, "initInputAll"+guiTextField+"=="+s);
    }

    //显示swing输入框
    public static void waitInput(GuiTextField guiTextField) {
        try {
            createTextFrame(guiTextField);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public static void func_146178_a()
    {  FMLLog.log(Level.INFO, "func_146178_a");
    }

    public static void func_146180_a()
    {  FMLLog.log(Level.INFO, "func_146180_a");
    }

    public static void func_146179_b()
    {   FMLLog.log(Level.INFO, "func_146179_b");

    }

    public static void func_146207_c()
    {   FMLLog.log(Level.INFO, "func_146207_c");

    }

    public static void func_146191_b()
    {
        FMLLog.log(Level.INFO, "func_146191_b");
    }

    public static void func_146177_a()
    {
        FMLLog.log(Level.INFO, "func_146177_a");
    }


    public static void func_146175_b()
    {
        FMLLog.log(Level.INFO, "func_146175_b");
    }

    public static void func_146187_c()
    {
        FMLLog.log(Level.INFO, "func_146187_c");
    }

    public static void func_146183_a()
    {
        FMLLog.log(Level.INFO, "func_146183_a");
    }

    public static void func_146197_a()
    {
        FMLLog.log(Level.INFO, "func_146197_a");
    }

    public static void func_146182_d()
    {
        FMLLog.log(Level.INFO, "func_146182_d");
    }

    public static void func_146190_e()
    {
        FMLLog.log(Level.INFO, "func_146190_e");
    }

    public static void func_146196_d()
    {
        FMLLog.log(Level.INFO, "func_146196_d");
    }

    public static void func_146202_e()
    {
        FMLLog.log(Level.INFO, "func_146202_e");
    }

    public static void func_146201_a()
    {
        FMLLog.log(Level.INFO, "func_146201_a");
    }
    public static void func_146203_f()
    {
        FMLLog.log(Level.INFO, "func_146203_f");
    }
    public static void func_146185_a()
    {
        FMLLog.log(Level.INFO, "func_146185_a");
    }
    public static void func_146195_b()
    {
        FMLLog.log(Level.INFO, "func_146195_b");
    }
    public static void func_146199_i()
    {
        FMLLog.log(Level.INFO, "func_146199_i");
    }
    public static void func_146200_o()
    {
        FMLLog.log(Level.INFO, "func_146200_o");
    }
    public static void func_146181_i()
    {
        FMLLog.log(Level.INFO, "func_146181_i");
    }
    public static void func_146205_d()
    {
        FMLLog.log(Level.INFO, "func_146205_d");
    }
    public static void func_146194_f()
    {
        FMLLog.log(Level.INFO, "func_146194_f");
    }
    public static void func_146176_q()
    {
        FMLLog.log(Level.INFO, "func_146176_q");
    }
    public static void func_146208_g()
    {
        FMLLog.log(Level.INFO, "func_146208_g");
    }
    public static void func_146188_c()
    {
        FMLLog.log(Level.INFO, "func_146188_c");
    }
}
