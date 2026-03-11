package com.spike.jvm.asm.tree;

import org.objectweb.asm.tree.MethodNode;

public class MethodTransformer {
    protected final MethodTransformer mt;

    public MethodTransformer(MethodTransformer mt) {
        this.mt = mt;
    }

    public MethodNode transform(MethodNode mn) {
        // chain
        if (mt != null) {
            return mt.transform(mn);
        } else {
            return mn;
        }
    }
}
