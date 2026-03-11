package com.spike.jvm.asm.core;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Remove all occurrences of <code>ICONST_0 IADD</code> sequence: add 0.
 */
public class RemoveAddZeroAdapter extends PatternMethodAdapter {
    private static int SEEN_ICONST_0 = 1;

    protected RemoveAddZeroAdapter(MethodVisitor methodVisitor) {
        super(methodVisitor);
    }

    @Override
    public void visitInsn(int opcode) {
        // found ICONST_0 IADD: remove the sequence
        if (state == SEEN_ICONST_0) {
            if (opcode == Opcodes.IADD) {
                state = SEEN_NOTHING;
                return;
            }
        }

        visitInsn();

        // current opcode is ICONST_0: remember it
        if (opcode == Opcodes.ICONST_0) {
            state = SEEN_ICONST_0;
            return;
        }

        // forward to next visitor
        mv.visitInsn(opcode);
    }

    @Override
    protected void visitInsn() {
        if (state == SEEN_ICONST_0) {
            mv.visitInsn(Opcodes.ICONST_0);
        }
        state = SEEN_NOTHING;
    }
}
