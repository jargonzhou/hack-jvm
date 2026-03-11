package com.spike.jvm.asm.tree;

import org.objectweb.asm.tree.ClassNode;

public class ClassTransformer {
    protected final ClassTransformer ct;

    public ClassTransformer(ClassTransformer ct) {
        this.ct = ct;
    }

    public void transform(ClassNode cn) {
        // chain
        if (ct != null) {
            ct.transform(cn);
        }
    }
}
