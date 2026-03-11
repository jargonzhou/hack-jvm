package com.spike.jvm.asm.tree.analysis;

import com.spike.jvm.asm.ASMs;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.analysis.*;

/**
 * @see Analyzer
 * @see BasicInterpreter
 */
public class RemoveDeadCodeMethodAdapter extends MethodVisitor {
    private final String owner;
    private final MethodVisitor next;

    public RemoveDeadCodeMethodAdapter(String owner, int access, String name, String desc, MethodVisitor next) {
        // class MethodNode extends MethodVisitor {}
        super(ASMs.ASM_VERSION, new MethodNode(ASMs.ASM_VERSION, access, name, desc, null, null));
        this.owner = owner;
        this.next = next;
    }

    @Override
    public void visitEnd() {
        MethodNode mn = (MethodNode) mv;
        Analyzer<BasicValue> a = new Analyzer<>(new BasicInterpreter());
        try {
            a.analyze(owner, mn);
            Frame<BasicValue>[] frames = a.getFrames();
            AbstractInsnNode[] insns = mn.instructions.toArray();
            for (int i = 0; i < frames.length; i++) {
                // null frame means unreachable code
                if (frames[i] == null && !(insns[i] instanceof LabelNode)) {
                    mn.instructions.remove(insns[i]);
                }
            }
        } catch (AnalyzerException e) {
            e.printStackTrace();
        }

        mn.accept(next);
    }
}
