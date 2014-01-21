package com.eyougo.mcmods.mcinputall;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.Level;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.RemappingMethodAdapter;
import org.objectweb.asm.util.CheckMethodAdapter;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/**
 * User: mei
 * Date: 1/20/14
 * Time: 11:01
 */
public class MCInputAllTransformer implements IClassTransformer {

    class a extends ClassVisitor {
        String guiTextFieldName;
        String minecraftName;

        public a(ClassVisitor cv) {
            super(Opcodes.ASM4, cv);
            guiTextFieldName = FMLDeobfuscatingRemapper.INSTANCE.unmap("net/minecraft/client/gui/GuiTextField");
            minecraftName = FMLDeobfuscatingRemapper.INSTANCE.unmap("net/minecraft/client/Minecraft");
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if ("func_146203_f".equals(FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(guiTextFieldName, name, desc))) {
                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
                mv.visitCode();
                mv.visitVarInsn(Opcodes.ALOAD, 0);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/eyougo/mcmods/mcinputall/GuiTextFieldFix", "waitInput", "(Lnet/minecraft/client/gui/GuiTextField;)V");
                return mv;
            } else if("run".equals(FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(minecraftName, name, desc))){
                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
                mv.visitCode();
                mv.visitVarInsn(Opcodes.ALOAD, 0);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/eyougo/mcmods/mcinputall/GuiScreenFix", "init", "(Lnet/minecraft/client/Minecraft;)V");
                return mv;
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

    }

    class b extends MethodVisitor {
        public b() {
            super(Opcodes.ASM4, null);
        }
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if ("net.minecraft.client.gui.GuiTextField".equals(transformedName)
                || "net.minecraft.client.Minecraft".equals(transformedName)){
            return transformInside(bytes);
        }
        return bytes;
    }

    private byte[] transformInside(byte[] bytes) {
        ClassReader classReader = new ClassReader(bytes);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classReader.accept(new a(classWriter), ClassReader.EXPAND_FRAMES);
        return classWriter.toByteArray();
    }
}
