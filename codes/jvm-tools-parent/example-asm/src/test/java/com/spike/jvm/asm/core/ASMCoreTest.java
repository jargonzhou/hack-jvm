package com.spike.jvm.asm.core;

import com.spike.jvm.asm.ASMs;
import com.spike.jvm.junit.OutputTestBase;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.*;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import static org.objectweb.asm.Opcodes.*;

public class ASMCoreTest extends OutputTestBase {
    // =========================================================================
    // Classes
    // =========================================================================

    @Test
    public void testParsingClass() throws IOException {
        ClassPrinter cp = new ClassPrinter();
        // class reader
        ClassReader cr = new ClassReader("java.lang.Runnable");
        cr.accept(cp, 0);
    }

    static class MyClassLoader extends ClassLoader {
        public Class<?> defineClass(String name, byte[] b) {
            return defineClass(name, b, 0, b.length);
        }
    }

    /**
     * @see Opcodes
     */
    @Test
    public void testGeneratingClass() {
        byte[] b = this.generateComparableClassBytes();

        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> c = myClassLoader.defineClass("pkg.Comparable", b);
        System.out.println(c.toGenericString());
    }

    /**
     * @see pkg.Comparable
     */
    private byte[] generateComparableClassBytes() {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(ASMs.JAVA_VERSION, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, "pkg/Comparable", null, "java/lang/Object", null /*new String[] { "pkg/Mesurable" } */);
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I", null, -1).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, 0).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, 1).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();
        cw.visitEnd();
        return cw.toByteArray();
    }

    @Test
    public void testTransformingClass() {
        byte[] b = this.generateComparableClassBytes();

        // chain: reader -> adapter -> writer

        ClassWriter cw = new ClassWriter(0);
//        ClassVisitor cv = new ClassVisitor(ASMConstant.ASM_VERSION, cw) {};
        ChangeVersionAdapter cva = new ChangeVersionAdapter(cw);
        ClassReader cr = new ClassReader(b);
//        cr.accept(cv, 0);
        cr.accept(cva, 0);
        byte[] b2 = cw.toByteArray();
        System.out.println(ByteBufUtil.prettyHexDump(Unpooled.copiedBuffer(b2)));

        // using in agent
        System.out.println(ByteBufUtil.prettyHexDump(Unpooled.copiedBuffer(ExampleAgent.transform(b))));
    }

    /**
     * @see RemoveDebugAdapter
     * @see RemoveMethodAdapter
     */
    @Test
    public void testRemovingClassMember() {
    }

    /**
     * @see AddFieldAdapter
     */
    @Test
    public void testAddingClassMember() {
    }

    /**
     * @see MultiClassAdapter
     */
    @Test
    public void testTransformationChain() {
    }

    /**
     * @see org.objectweb.asm.Type
     */
    @Test
    public void testToolsType() throws NoSuchMethodException {
        // Type
        Assertions.assertEquals("double", Type.DOUBLE_TYPE.getClassName());
        Assertions.assertEquals("D", Type.DOUBLE_TYPE.getInternalName());

        Type stringType = Type.getType(String.class);
        Assertions.assertEquals("java/lang/String", stringType.getInternalName());
        Assertions.assertEquals("Ljava/lang/String;", stringType.getDescriptor());

        Method method = String.class.getMethod("length");
        Type methodType = Type.getType(method);
        Assertions.assertEquals("()I", Type.getMethodDescriptor(method));
        Assertions.assertEquals("()I", methodType.getDescriptor());
        Type[] argTypes = methodType.getArgumentTypes();
        Assertions.assertEquals(0, argTypes.length);
        Assertions.assertEquals("I", methodType.getReturnType().getDescriptor());
    }

    /**
     * @see org.objectweb.asm.util.TraceClassVisitor
     */
    @Test
    public void testToolsTraceClassVisitor() {
        byte[] b = this.generateTraceClassBytes();
        System.err.println(getOutput());
        output(() -> {
            System.out.println(ByteBufUtil.prettyHexDump(Unpooled.copiedBuffer(b)));
        });
    }

    /**
     * @see pkg.Comparable
     */
    protected byte[] generateTraceClassBytes() {
        ClassWriter cw = new ClassWriter(0);
        TraceClassVisitor tcv = new TraceClassVisitor(cw, new PrintWriter(System.out));

        tcv.visit(ASMs.JAVA_VERSION, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, "pkg/Comparable", null, "java/lang/Object", new String[]{"pkg/Mesurable"});
        tcv.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I", null, -1).visitEnd();
        tcv.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, 0).visitEnd();
        tcv.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, 1).visitEnd();
        tcv.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();
        tcv.visitEnd();
        return cw.toByteArray();
    }

    /**
     * @see org.objectweb.asm.util.CheckClassAdapter
     */
    @Test
    public void testToolsCheckClassAdapter() {

    }

    /**
     * @see org.objectweb.asm.util.ASMifier
     */
    @Test
    public void testToolsASMifier() throws IOException {
        // fully qualified class name
//        ASMifier.main(new String[]{"pkg.Bean"});
        try {
            ASMifier.main(new String[]{"pkg.C"});
        } catch (IOException e) {
            Assertions.assertNotNull(e);
        }

//        output(() -> {
//            try {
//                ASMifier.main(new String[]{"java.lang.Runnable"});
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        ASMifier.main(new String[]{"java.lang.Runnable"});

        // also works on class files
        ASMifier.main(new String[]{"target/test-classes/pkg/Bean.class"});
    }

    /**
     * @see Textifier
     */
    @Test
    public void testToolsTextifier() throws IOException {
        Textifier.main(new String[]{"java.lang.Runnable"});
    }

    // =========================================================================
    // Methods
    // =========================================================================

    /**
     * @see org.objectweb.asm.MethodVisitor
     * @see ClassWriter#COMPUTE_MAXS
     * @see ClassWriter#COMPUTE_FRAMES
     * @see pkg.Bean
     */
    @Test
    public void testGeneratingMethod() {
        byte[] b = this.generateBeanClassBytes();
        Assertions.assertNotNull(b);
    }

    protected byte[] generateBeanClassBytes() {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        TraceClassVisitor tcv = new TraceClassVisitor(cw, new PrintWriter(System.out));

        // Generate by ASMifier
        tcv.visit(V17, ACC_PUBLIC | ACC_SUPER, "pkg/Bean", null, "java/lang/Object", null);
        tcv.visitSource("Bean.java", null);
        tcv.visitField(ACC_PRIVATE, "f", "I", null, null).visitEnd();
        MethodVisitor methodVisitor;
        {
            methodVisitor = tcv.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(6, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lpkg/Bean;", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = tcv.visitMethod(ACC_PUBLIC, "getF", "()I", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(10, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, "pkg/Bean", "f", "I");
            methodVisitor.visitInsn(IRETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lpkg/Bean;", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = tcv.visitMethod(ACC_PUBLIC, "setF", "(I)V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(14, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ILOAD, 1);
            methodVisitor.visitFieldInsn(PUTFIELD, "pkg/Bean", "f", "I");
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(15, label1);
            methodVisitor.visitInsn(RETURN);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLocalVariable("this", "Lpkg/Bean;", null, label0, label2, 0);
            methodVisitor.visitLocalVariable("f", "I", null, label0, label2, 1);
            methodVisitor.visitMaxs(2, 2);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = tcv.visitMethod(ACC_PUBLIC, "checkAndSetF", "(I)V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(18, label0);
            methodVisitor.visitVarInsn(ILOAD, 1);
            Label label1 = new Label();
            methodVisitor.visitJumpInsn(IFLT, label1);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(19, label2);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ILOAD, 1);
            methodVisitor.visitFieldInsn(PUTFIELD, "pkg/Bean", "f", "I");
            Label label3 = new Label();
            methodVisitor.visitJumpInsn(GOTO, label3);
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(21, label1);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitTypeInsn(NEW, "java/lang/IllegalArgumentException");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/IllegalArgumentException", "<init>", "()V", false);
            methodVisitor.visitInsn(ATHROW);
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLineNumber(23, label3);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitInsn(RETURN);
            Label label4 = new Label();
            methodVisitor.visitLabel(label4);
            methodVisitor.visitLocalVariable("this", "Lpkg/Bean;", null, label0, label4, 0);
            methodVisitor.visitLocalVariable("f", "I", null, label0, label4, 1);
            methodVisitor.visitMaxs(2, 2);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = tcv.visitMethod(ACC_PUBLIC | ACC_STATIC, "sleep", "(J)V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            Label label1 = new Label();
            Label label2 = new Label();
            methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/InterruptedException");
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(27, label0);
            methodVisitor.visitVarInsn(LLOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "sleep", "(J)V", false);
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(30, label1);
            Label label3 = new Label();
            methodVisitor.visitJumpInsn(GOTO, label3);
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(28, label2);
            methodVisitor.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{"java/lang/InterruptedException"});
            methodVisitor.visitVarInsn(ASTORE, 2);
            Label label4 = new Label();
            methodVisitor.visitLabel(label4);
            methodVisitor.visitLineNumber(29, label4);
            methodVisitor.visitVarInsn(ALOAD, 2);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/InterruptedException", "printStackTrace", "()V", false);
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLineNumber(31, label3);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitInsn(RETURN);
            Label label5 = new Label();
            methodVisitor.visitLabel(label5);
            methodVisitor.visitLocalVariable("e", "Ljava/lang/InterruptedException;", null, label4, label3, 2);
            methodVisitor.visitLocalVariable("d", "J", null, label0, label5, 0);
            methodVisitor.visitMaxs(2, 3);
            methodVisitor.visitEnd();
        }
        tcv.visitEnd();
        return cw.toByteArray();
    }

    /**
     * @see RemoveNopAdapter
     * @see RemoveNopClassAdapter
     * @see MultiMethodAdapter
     */
    @Test
    public void testTransformingMethod() {
    }

    /**
     * @see AddTimerAdapter
     */
    @Test
    public void testStatelessTransformation() throws Exception {
        byte[] b2 = output(() -> {
            byte[] b = this.generateClassC();

            System.out.println("==========================================================");
            ClassReader cr = new ClassReader(b);
            ClassWriter cw = new ClassWriter(0);
            AddTimerAdapter ata = new AddTimerAdapter(cw);
            TraceClassVisitor tcv = new TraceClassVisitor(ata, new PrintWriter(System.out));
            cr.accept(tcv, 0);

            byte[] bb = cw.toByteArray();
            try {
                ASMs.dumpClassfile(bb, "target/C.class");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return bb;
        });

        // WARN: must comment pkg/C.java
        ASMs.runClassfile(b2, "pkg.C", clazz -> {
            try {
                Object obj = clazz.getDeclaredConstructor().newInstance();
                Method m = clazz.getMethod("m");
                m.invoke(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // assertions on output
        System.err.println(getOutput());
        org.assertj.core.api.Assertions.assertThat(getOutput()).containsPattern("Last \\d+ ms");
    }

    protected byte[] generateClassC() {
        ClassWriter cw = new ClassWriter(0);
        TraceClassVisitor classWriter = new TraceClassVisitor(cw, new PrintWriter(System.out));
        FieldVisitor fieldVisitor;
        RecordComponentVisitor recordComponentVisitor;
        MethodVisitor methodVisitor;
        AnnotationVisitor annotationVisitor0;

        classWriter.visit(ASMs.JAVA_VERSION, ACC_PUBLIC | ACC_SUPER, "pkg/C", null, "java/lang/Object", null);

        classWriter.visitSource("C.java", null);

        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(3, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lpkg/C;", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "m", "()V", null, new String[]{"java/lang/Exception"});
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(5, label0);
            methodVisitor.visitLdcInsn(Long.valueOf(100L));
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "sleep", "(J)V", false);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(6, label1);
            methodVisitor.visitInsn(RETURN);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLocalVariable("this", "Lpkg/C;", null, label0, label2, 0);
            methodVisitor.visitMaxs(2, 1);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();
        return cw.toByteArray();
    }

    /**
     * @see RemoveAddZeroAdapter
     * <p>
     * more: RemoveGetFieldPutFieldAdapter
     */
    @Test
    public void testStatefulTransformation() {
    }

    /**
     * @see org.objectweb.asm.commons.AnalyzerAdapter
     */
    @Test
    public void testToolsAnalyzerAdapter() {

    }

    /**
     * @see org.objectweb.asm.commons.LocalVariablesSorter
     */
    @Test
    public void testToolsLocalVariablesSorter() {
        // newLocal
    }

    /**
     * @see org.objectweb.asm.commons.AdviceAdapter
     */
    @Test
    public void testToolsAdviceAdapter() {
        // onMethodEnter
        // onMethodExit
    }

    // =========================================================================
    // Metadata
    // =========================================================================

    //
    // Generics
    //

    //
    // Annotations
    //

    //
    // Debug
    //
}
