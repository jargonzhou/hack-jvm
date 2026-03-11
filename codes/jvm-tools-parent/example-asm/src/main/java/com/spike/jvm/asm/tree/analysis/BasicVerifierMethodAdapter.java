package com.spike.jvm.asm.tree.analysis;

import com.spike.jvm.asm.ASMs;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.analysis.Analyzer;
import org.objectweb.asm.tree.analysis.AnalyzerException;
import org.objectweb.asm.tree.analysis.BasicValue;
import org.objectweb.asm.tree.analysis.BasicVerifier;

/**
 * @see BasicVerifier
 */
public class BasicVerifierMethodAdapter extends MethodVisitor {
    private final String owner;
    private final MethodVisitor next;

    protected BasicVerifierMethodAdapter(String owner, int access, String name, String desc, MethodVisitor next) {
        super(ASMs.ASM_VERSION, new MethodNode(access, name, desc, null, null));
        this.owner = owner;
        this.next = next;
    }

    @Override
    public void visitEnd() {
        MethodNode mn = (MethodNode) mv;
        Analyzer<BasicValue> analyzer = new Analyzer<>(new BasicVerifier());
        try {
            analyzer.analyze(owner, mn);
        } catch (AnalyzerException e) {
            throw new RuntimeException(e);
        }

        mn.accept(next);
    }
}
