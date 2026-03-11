package com.spike.jvm.asm.core;

import com.spike.jvm.asm.ASMs;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

public class AddFieldAdapter extends ClassVisitor {
    private final int access;
    private final String name;
    private final String descriptor;
    private boolean isFieldPresent;

    protected AddFieldAdapter(ClassVisitor classVisitor, int access, String name, String descriptor) {
        super(ASMs.ASM_VERSION, classVisitor);
        this.access = access;
        this.name = name;
        this.descriptor = descriptor;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        if (name.equals(this.name)) {
            this.isFieldPresent = true;
        }
        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public void visitEnd() {
        if (!this.isFieldPresent) {
            // add new field
            FieldVisitor fv = cv.visitField(access, name, descriptor, null, null);
            if (fv != null) {
                fv.visitEnd();
            }
        }
        cv.visitEnd();
    }
}
