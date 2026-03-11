package com.spike.jvm.asm.tree.analysis;

import com.spike.jvm.asm.ASMs;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.analysis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @see IsNullInterpreter
 */
public class NullDereferenceAnalyzer {
    public List<AbstractInsnNode> findNullDereferences(String owner, MethodNode mn) throws AnalyzerException {
        List<AbstractInsnNode> result = new ArrayList<>();
        Analyzer<BasicValue> analyzer = new Analyzer<>(new IsNullInterpreter());
        analyzer.analyze(owner, mn);

        Frame<BasicValue>[] frames = analyzer.getFrames();
        AbstractInsnNode[] insns = mn.instructions.toArray();
        for (int i = 0; i < insns.length; i++) {
            AbstractInsnNode insn = insns[i];
            if (frames[i] != null) {
                Value v = ASMs.getTarget(insn, frames[i]);
                if (v == ASMs.NULL || v == ASMs.MAYBE_NULL) {
                    result.add(insn);
                }
            }
        }
        return result;
    }
}
