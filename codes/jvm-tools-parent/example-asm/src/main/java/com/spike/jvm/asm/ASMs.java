package com.spike.jvm.asm;

import com.spike.jvm.asm.support.BytesClassLoader;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.tree.analysis.BasicValue;
import org.objectweb.asm.tree.analysis.Frame;
import org.objectweb.asm.tree.analysis.Value;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * ASM utilities.
 */
public interface ASMs {
    int ASM_VERSION = Opcodes.ASM9;
    int JAVA_VERSION = Opcodes.V17;

    BasicValue NULL = new BasicValue(null);
    BasicValue MAYBE_NULL = new BasicValue(null);

    // =========================================================================
    // Tools
    // =========================================================================

    static void dumpBytecode(byte[] b) {
        System.out.println(ByteBufUtil.prettyHexDump(Unpooled.copiedBuffer(b)));
    }

    static void dumpClassfile(byte[] b, String fileName) throws IOException {
        try (OutputStream os = Files.newOutputStream(Paths.get(fileName))) {
            os.write(b);
            os.flush();
        }
    }

    static void runClassfile(byte[] b, String className, Consumer<Class<?>> c) throws ClassNotFoundException {
        BytesClassLoader cl = new BytesClassLoader(b);
        Class<?> clazz = cl.loadClass(className);
        c.accept(clazz);
    }

    // =========================================================================
    // Method
    // =========================================================================

    public static AbstractInsnNode getNext(AbstractInsnNode insnNode) {
        if (insnNode == null) {
            return null;
        }

        do {
            insnNode = insnNode.getNext();
            if (insnNode != null && !(insnNode instanceof LineNumberNode)) {
                break;
            }
        } while (insnNode != null);
        return insnNode;
    }

    static AbstractInsnNode getNext(Iterator<AbstractInsnNode> i) {
        while (i.hasNext()) {
            AbstractInsnNode in = i.next();
            if (!(in instanceof LineNumberNode)) {
                return in;
            }
        }
        return null;
    }

    static boolean isALOAD0(AbstractInsnNode insnNode) {
        return insnNode != null
                && insnNode instanceof VarInsnNode
                && insnNode.getOpcode() == Opcodes.ALOAD
                && ((VarInsnNode) insnNode).var == 0;
    }

    static boolean sameField(AbstractInsnNode i, AbstractInsnNode j) {
        return i instanceof FieldInsnNode
                && j instanceof FieldInsnNode
                && ((FieldInsnNode) i).name.equals(((FieldInsnNode) j).name);
    }

    static Class<?> getClass(String desc) {
        try {
            return Class.forName(desc.replace('/', '.'));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static Class<?> getClass(Type t) {
        if (t.getSort() == Type.OBJECT) {
            return getClass(t.getInternalName());
        } else {
            return getClass(t.getDescriptor());
        }
    }

    // =========================================================================
    // Analysis
    // =========================================================================

    static boolean isRef(Value v) {
        return v == BasicValue.REFERENCE_VALUE || v == MAYBE_NULL || v == NULL;
    }

    static Value getTarget(AbstractInsnNode insn, Frame<BasicValue> frame) {
        switch (insn.getOpcode()) {
            case Opcodes.GETFIELD:
            case Opcodes.ARRAYLENGTH:
            case Opcodes.MONITORENTER:
            case Opcodes.MONITOREXIT:
                return getStatckValue(frame, 0);
            case Opcodes.PUTFIELD:
                return getStatckValue(frame, 1);
            case Opcodes.INVOKEVIRTUAL:
            case Opcodes.INVOKESPECIAL:
            case Opcodes.INVOKEINTERFACE:
                String desc = ((MethodInsnNode) insn).desc;
                return getStatckValue(frame, Type.getArgumentTypes(desc).length);
        }
        return null;
    }

    static BasicValue getStatckValue(Frame<BasicValue> frame, int index) {
        int top = frame.getStackSize() - 1;
        if (index <= top) {
            return frame.getStack(top - index);
        } else {
            return null;
        }
    }
}
