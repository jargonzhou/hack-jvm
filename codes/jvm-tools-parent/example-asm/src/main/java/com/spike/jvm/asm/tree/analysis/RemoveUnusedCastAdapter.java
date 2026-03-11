package com.spike.jvm.asm.tree.analysis;

import com.spike.jvm.asm.ASMs;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AnalyzerAdapter;

/**
 * @see AnalyzerAdapter
 */
public class RemoveUnusedCastAdapter extends MethodVisitor {
    public AnalyzerAdapter aa;

    protected RemoveUnusedCastAdapter(AnalyzerAdapter aa, MethodVisitor mv) {
        super(ASMs.ASM_VERSION, mv);
        this.aa = aa;
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        if (opcode == Opcodes.CHECKCAST) {
            Class<?> to = ASMs.getClass(type);
            if (aa.stack != null && aa.stack.size() > 0) {
                Object operand = aa.stack.get(aa.stack.size() - 1);
                if (operand instanceof String) {
                    Class<?> from = ASMs.getClass((String) operand);
                    if (to.isAssignableFrom(from)) {
                        return;
                    }
                }
            }
        }

        mv.visitTypeInsn(opcode, type);
    }
}
