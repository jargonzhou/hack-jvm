package com.spike.jvm.asm.core;

import com.spike.jvm.asm.ASMs;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class RemoveMethodAdapter extends ClassVisitor {
    private final String methodName;
    private final String methodDescriptor;

    protected RemoveMethodAdapter(ClassVisitor classVisitor, String methodName, String methodDescriptor) {
        super(ASMs.ASM_VERSION, classVisitor);
        this.methodName = methodName;
        this.methodDescriptor = methodDescriptor;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (name.equals(methodName) && descriptor.equals(methodDescriptor)) {
            // Skip this method to remove it
            return null;
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }
}
