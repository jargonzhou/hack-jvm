package com.spike.jvm.asm.core;

import com.spike.jvm.asm.ASMs;
import org.objectweb.asm.MethodVisitor;

import java.util.List;

public class MultiMethodAdapter extends MethodVisitor {
    private final List<MethodVisitor> mvs;

    protected MultiMethodAdapter(List<MethodVisitor> mvs) {
        super(ASMs.ASM_VERSION);
        this.mvs = mvs;
    }

    @Override
    public void visitCode() {
        for (MethodVisitor mv : mvs) {
            mv.visitCode();
        }
    }
}
