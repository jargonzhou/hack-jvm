package com.spike.jvm.asm.core;

import com.spike.jvm.asm.ASMs;
import org.objectweb.asm.ClassVisitor;

import java.util.List;

public class MultiClassAdapter extends ClassVisitor {
    protected final List<ClassVisitor> cvs;

    public MultiClassAdapter(List<ClassVisitor> cvs) {
        super(ASMs.ASM_VERSION);
        this.cvs = cvs;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        for (ClassVisitor cv : cvs) {
            cv.visit(version, access, name, signature, superName, interfaces);
        }
    }
}
