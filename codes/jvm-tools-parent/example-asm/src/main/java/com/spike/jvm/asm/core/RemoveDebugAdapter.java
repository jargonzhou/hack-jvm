package com.spike.jvm.asm.core;

import com.spike.jvm.asm.ASMs;
import org.objectweb.asm.ClassVisitor;

public class RemoveDebugAdapter extends ClassVisitor {
    protected RemoveDebugAdapter(ClassVisitor cv) {
        super(ASMs.ASM_VERSION, cv);
    }

    // not forward means removing

    @Override
    public void visitSource(String source, String debug) {
    }

    @Override
    public void visitOuterClass(String owner, String name, String descriptor) {
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
    }


}
