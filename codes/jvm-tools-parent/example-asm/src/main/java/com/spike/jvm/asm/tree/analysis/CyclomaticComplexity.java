package com.spike.jvm.asm.tree.analysis;

import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.analysis.*;

/**
 * @see org.objectweb.asm.tree.analysis.Analyzer#newControlFlowEdge(int, int)
 * @see Node
 */
public class CyclomaticComplexity {
    public int getCyclomaticComplexity(String owner, MethodNode mn) throws AnalyzerException {
        Analyzer<BasicValue> analyzer = new Analyzer<>(new BasicInterpreter()) {
            @Override
            protected Frame<BasicValue> newFrame(int nLocals, int nStack) {
                return new Node<>(nLocals, nStack);
            }

            @Override
            protected Frame<BasicValue> newFrame(Frame<? extends BasicValue> frame) {
                return new Node<>(frame);
            }

            @Override
            protected void newControlFlowEdge(int insnIndex, int successorIndex) {
                Node<BasicValue> s = (Node<BasicValue>) getFrames()[insnIndex];
                s.successors.add((Node<BasicValue>) getFrames()[successorIndex]);
            }
        };
        analyzer.analyze(owner, mn);
        Frame<BasicValue>[] frames = analyzer.getFrames();
        int edges = 0;
        int nodes = 0;
        for (int i = 0; i < frames.length; i++) {
            if (frames[i] != null) {
                edges += ((Node<BasicValue>) frames[i]).successors.size();
                nodes += 1;
            }
        }

        return edges - nodes + 2;
    }
}
