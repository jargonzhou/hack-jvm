package com.spike.jvm.asm.tree.analysis;

import com.spike.jvm.asm.ASMs;
import com.spike.jvm.asm.tree.MethodTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.analysis.*;

/**
 * @see SimpleVerifier
 */
public class RemoveUnusedCastTransformer extends MethodTransformer {
    private final String owner;

    public RemoveUnusedCastTransformer(String owner, MethodTransformer mt) {
        super(mt);
        this.owner = owner;
    }

    @Override
    public MethodNode transform(MethodNode mn) {
        Analyzer<BasicValue> analyzer = new Analyzer<>(new SimpleVerifier());
        try {
            analyzer.analyze(owner, mn);
            Frame<BasicValue>[] frames = analyzer.getFrames();
            AbstractInsnNode[] insns = mn.instructions.toArray();
            for (int i = 0; i < insns.length; i++) {
                AbstractInsnNode insn = insns[i];
                if (insn.getOpcode() == Opcodes.CHECKCAST) {
                    Frame<BasicValue> f = frames[i];
                    if (f != null && f.getStackSize() > 0) {
                        Object operand = f.getStack(f.getStackSize() - 1);
                        Class<?> to = ASMs.getClass(((TypeInsnNode) insn).desc);
                        Class<?> from = ASMs.getClass(((BasicValue) operand).getType());
                        if (to.isAssignableFrom(from)) {
                            mn.instructions.remove(insn);
                        }
                    }
                }
            }
        } catch (AnalyzerException e) {
            e.printStackTrace();
        }

        return super.transform(mn);
    }
}
