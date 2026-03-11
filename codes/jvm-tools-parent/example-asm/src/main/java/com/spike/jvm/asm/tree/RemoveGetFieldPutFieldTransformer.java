package com.spike.jvm.asm.tree;

import com.spike.jvm.asm.ASMs;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

import java.util.Iterator;

public class RemoveGetFieldPutFieldTransformer extends MethodTransformer {
    public RemoveGetFieldPutFieldTransformer(MethodTransformer mt) {
        super(mt);
    }

    @Override
    public MethodNode transform(MethodNode mn) {
        // remove field self assignments
        InsnList insns = mn.instructions;
        Iterator<AbstractInsnNode> i = insns.iterator();
        while (i.hasNext()) {
            AbstractInsnNode i1 = i.next();
            // look for ALOAD 0
            if (ASMs.isALOAD0(i1)) {
                AbstractInsnNode i2 = ASMs.getNext(i1);
                if (ASMs.isALOAD0(i2)) {
                    AbstractInsnNode i3 = ASMs.getNext(i2);
                    while (ASMs.isALOAD0(i3)) {
                        i1 = i2;
                        i2 = i3;
                        i3 = ASMs.getNext(i);
                    }

                    if (i3 != null && i3.getOpcode() == Opcodes.GETFIELD) {
                        AbstractInsnNode i4 = ASMs.getNext(i3);
                        if (i4 != null && i4.getOpcode() == Opcodes.PUTFIELD) {
                            if (ASMs.sameField(i3, i4)) {
                                insns.remove(i1);
                                insns.remove(i2);
                                insns.remove(i3);
                                insns.remove(i4);
                            }
                        }
                    }
                }
            }

            return super.transform(mn);
        }

        return super.transform(mn);
    }
}
