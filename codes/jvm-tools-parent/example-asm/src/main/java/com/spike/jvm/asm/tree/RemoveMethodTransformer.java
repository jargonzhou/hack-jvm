package com.spike.jvm.asm.tree;

public class RemoveMethodTransformer extends ClassTransformer {
    private final String methodName;
    private final String methodDesc;

    public RemoveMethodTransformer(ClassTransformer ct, String methodName, String methodDesc) {
        super(ct);
        this.methodName = methodName;
        this.methodDesc = methodDesc;
    }

    @Override
    public void transform(org.objectweb.asm.tree.ClassNode cn) {
        cn.methods.removeIf(mn -> mn.name.equals(methodName) && mn.desc.equals(methodDesc));
        super.transform(cn);
    }
}
