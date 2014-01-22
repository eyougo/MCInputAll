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

        public a(ClassVisitor cv) {
            super(Opcodes.ASM4, cv);
            guiTextFieldName = FMLDeobfuscatingRemapper.INSTANCE.unmap("net/minecraft/client/gui/GuiTextField");
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if ("func_146203_f".equals(FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(guiTextFieldName, name, desc))) {
                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
                mv.visitCode();
                mv.visitVarInsn(Opcodes.ALOAD, 0);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/eyougo/mcmods/mcinputall/GuiTextFieldFix", "waitInput", "(Lnet/minecraft/client/gui/GuiTextField;)V");
                return mv;
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

    }

    class b extends ClassVisitor {
        String minecraftName;

        public b(ClassVisitor cv) {
            super(Opcodes.ASM4, cv);
            minecraftName = FMLDeobfuscatingRemapper.INSTANCE.unmap("net/minecraft/client/Minecraft");
        }

        @Override
        public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
            FMLLog.log(Level.INFO, "field:"+name + ":" + FMLDeobfuscatingRemapper.INSTANCE.mapFieldName(minecraftName, name, desc));

            return super.visitField(access, name, desc, signature, value);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if((MCInputAll.RUNTIME_DEOBF?"func_99999_d":"run").equals(FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(minecraftName, name, desc))){
                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
                mv.visitCode();
                mv.visitVarInsn(Opcodes.ALOAD, 0);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/eyougo/mcmods/mcinputall/GuiScreenFix", "init", "(Lnet/minecraft/client/Minecraft;)V");
                return mv;
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

    }


    class c extends MethodVisitor {
        public c() {
            super(Opcodes.ASM4, null);
        }
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if ("net.minecraft.client.gui.GuiTextField".equals(transformedName)){
            return transformInsideA(bytes);
        }else if("net.minecraft.client.Minecraft".equals(transformedName)){
            return transformInsideB(bytes);
        }
        return bytes;
    }

    private byte[] transformInsideA(byte[] bytes) {
        ClassReader classReader = new ClassReader(bytes);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classReader.accept(new a(classWriter), ClassReader.EXPAND_FRAMES);
        return classWriter.toByteArray();
    }
    private byte[] transformInsideB(byte[] bytes) {
        ClassReader classReader = new ClassReader(bytes);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classReader.accept(new b(classWriter), ClassReader.EXPAND_FRAMES);
        return classWriter.toByteArray();
    }
}
