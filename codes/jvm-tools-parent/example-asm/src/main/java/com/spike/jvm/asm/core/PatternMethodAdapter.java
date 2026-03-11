package com.spike.jvm.asm.core;

import com.spike.jvm.asm.ASMs;
import org.objectweb.asm.MethodVisitor;

public abstract class PatternMethodAdapter extends MethodVisitor {
    protected final static int SEEN_NOTHING = 0;
    protected int state;

    protected PatternMethodAdapter(MethodVisitor methodVisitor) {
        super(ASMs.ASM_VERSION, methodVisitor);
    }

    @Override
    public void visitInsn(int opcode) {
        visitInsn();

        mv.visitInsn(opcode);
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        visitInsn();

        mv.visitIntInsn(opcode, operand);
    }

    // more visitXxxInsn()

    protected abstract void visitInsn();
}
