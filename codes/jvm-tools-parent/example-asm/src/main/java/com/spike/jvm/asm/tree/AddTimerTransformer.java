package com.spike.jvm.asm.tree;

import org.objectweb.asm.Handle;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.Iterator;


public class AddTimerTransformer extends ClassTransformer {
    public AddTimerTransformer(ClassTransformer ct) {
        super(ct);
    }

    @Override
    public void transform(ClassNode cn) {
        // public static long timer;
        boolean isPresent = false;
        for (var fn : cn.fields) {
            if (fn.name.equals("timer")) {
                // already has timer field
                isPresent = true;
                break;
            }
        }
        if (!isPresent) {
            FieldNode timerField = new FieldNode(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                    "timer", "J", null, null);
            cn.fields.add(timerField);
        }

        for (var mn : cn.methods) {
            // skip constructor
            if (mn.name.equals("<init>") || mn.name.equals("<clinit>")) {
                continue;
            }
            // add timer logic at the start and end of the method
            var insns = mn.instructions;
            if (insns.size() == 0) {
                continue;
            }
            Iterator<AbstractInsnNode> j = insns.iterator();
            while (j.hasNext()) {
                AbstractInsnNode in = j.next();
                int op = in.getOpcode();
                if ((op >= Opcodes.IRETURN && op <= Opcodes.RETURN) || op == Opcodes.ATHROW) {
                    // timer += System.currentTimeMillis();
                    InsnList il = new InsnList();
                    il.add(new FieldInsnNode(Opcodes.GETSTATIC, cn.name, "timer", "J"));
                    il.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false));
                    il.add(new InsnNode(Opcodes.LADD));
                    il.add(new FieldInsnNode(Opcodes.PUTSTATIC, cn.name, "timer", "J"));

                    // System.out.println("Last " + timer + " ms");
                    il.add(new FieldInsnNode(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
                    il.add(new FieldInsnNode(Opcodes.GETSTATIC, cn.name, "timer", "J"));
                    Handle handle = new Handle(Opcodes.H_INVOKESTATIC,
                            "java/lang/invoke/StringConcatFactory",
                            "makeConcatWithConstants",
                            "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;",
                            false);
                    il.add(new InvokeDynamicInsnNode("makeConcatWithConstants", "(J)Ljava/lang/String;",
                            handle, "Last \u0001 ms"));
                    il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false));

                    insns.insert(in.getPrevious(), il);
                }
            }
            // timer -= System.currentTimeMillis();
            InsnList il = new InsnList();
            il.add(new FieldInsnNode(Opcodes.GETSTATIC, cn.name, "timer", "J"));
            il.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false));
            il.add(new InsnNode(Opcodes.LSUB));
            il.add(new FieldInsnNode(Opcodes.PUTSTATIC, cn.name, "timer", "J"));
            insns.insert(il);
            mn.maxStack += 4;
        }


        super.transform(cn);
    }
}
