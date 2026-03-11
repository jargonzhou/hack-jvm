package com.spike.jvm.asm.tree.analysis;

import com.spike.jvm.asm.ASMs;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class RemoveDeadCodeAdapter extends ClassVisitor {
    private final String owner;

    public RemoveDeadCodeAdapter(String owner, ClassVisitor cv) {
        super(ASMs.ASM_VERSION, cv);
        this.owner = owner;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (mv != null) {
            mv = new RemoveDeadCodeMethodAdapter(owner, access, name, descriptor, mv);
        }
        return mv;
    }
}
