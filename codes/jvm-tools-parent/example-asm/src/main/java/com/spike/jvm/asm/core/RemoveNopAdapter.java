package com.spike.jvm.asm.core;

import com.spike.jvm.asm.ASMs;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.NOP;

public class RemoveNopAdapter extends MethodVisitor {
    public RemoveNopAdapter(MethodVisitor methodVisitor) {
        super(ASMs.ASM_VERSION, methodVisitor);
    }

    @Override
    public void visitInsn(int opcode) {
        if (opcode != NOP) {
            super.visitInsn(opcode);
        }
    }
}
