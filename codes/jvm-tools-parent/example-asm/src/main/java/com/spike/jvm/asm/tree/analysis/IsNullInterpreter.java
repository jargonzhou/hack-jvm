package com.spike.jvm.asm.tree.analysis;

import com.spike.jvm.asm.ASMs;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.analysis.AnalyzerException;
import org.objectweb.asm.tree.analysis.BasicInterpreter;
import org.objectweb.asm.tree.analysis.BasicValue;

public class IsNullInterpreter extends BasicInterpreter {

    public IsNullInterpreter() {
        super(ASMs.ASM_VERSION);
    }

    @Override
    public BasicValue newOperation(AbstractInsnNode insn) throws AnalyzerException {
        if (insn.getOpcode() == ACONST_NULL) {
            return ASMs.NULL;
        }
        return super.newOperation(insn);
    }

    @Override
    public BasicValue merge(BasicValue value1, BasicValue value2) {
        if (ASMs.isRef(value1) && ASMs.isRef(value2) && value1 != value2) {
            return ASMs.MAYBE_NULL;
        }
        return super.merge(value1, value2);
    }
}
