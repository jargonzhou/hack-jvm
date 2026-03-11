package com.spike.jvm.asm.tree.analysis;

import org.objectweb.asm.tree.analysis.Frame;
import org.objectweb.asm.tree.analysis.Value;

import java.util.HashSet;
import java.util.Set;

/**
 * @param <V>
 * @see Frame
 */
public class Node<V extends Value> extends Frame<V> {
    Set<Node<V>> successors = new HashSet<>();

    public Node(int numLocals, int maxStack) {
        super(numLocals, maxStack);
    }

    public Node(Frame<? extends V> frame) {
        super(frame);
    }
}
