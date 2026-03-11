package com.spike.jvm.asm.core;

import com.spike.jvm.asm.ASMs;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AddTimerAdapter extends ClassVisitor {
    private String owner;
    private boolean isInterface;

    protected AddTimerAdapter(ClassVisitor classVisitor) {
        super(ASMs.ASM_VERSION, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);

        this.owner = name;
        this.isInterface = (access & ACC_INTERFACE) != 0;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        if (!isInterface && mv != null && !name.equals("<init>")) {
            mv = new AddTimerMethodAdapter(mv);
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        if (!isInterface) {
            // public static long timer;
            FieldVisitor fv = cv.visitField(ACC_PUBLIC + ACC_STATIC, "timer", "J", null, null);
            if (fv != null) {
                fv.visitEnd();
            }
        }

        cv.visitEnd();
    }

    class AddTimerMethodAdapter extends MethodVisitor {
        protected AddTimerMethodAdapter(MethodVisitor methodVisitor) {
            super(ASMs.ASM_VERSION, methodVisitor);
        }

        @Override
        public void visitCode() {
            mv.visitCode();
            // timer -= System.currentTimeMillis();
            mv.visitFieldInsn(GETSTATIC, owner, "timer", "J");
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitInsn(LSUB);
            mv.visitFieldInsn(PUTSTATIC, owner, "timer", "J");
        }

        @Override
        public void visitInsn(int opcode) {
            // return or throw
            if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
                // timer += System.currentTimeMillis();
                mv.visitFieldInsn(GETSTATIC, owner, "timer", "J");
                mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
                mv.visitInsn(LADD);
                mv.visitFieldInsn(PUTSTATIC, owner, "timer", "J");

                // System.out.println("Last " + timer + " ms");
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitFieldInsn(GETSTATIC, owner, "timer", "J");
                Handle handle = new Handle(H_INVOKESTATIC,
                        "java/lang/invoke/StringConcatFactory",
                        "makeConcatWithConstants",
                        "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;",
                        false);
                mv.visitInvokeDynamicInsn("makeConcatWithConstants", "(J)Ljava/lang/String;",
                        handle, "Last \u0001 ms");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            }
            mv.visitInsn(opcode);
        }

        @Override
        public void visitMaxs(int maxStack, int maxLocals) {
            // update operand stack size: 2 long values
            mv.visitMaxs(maxStack + 4, maxLocals);
        }
    }

}
