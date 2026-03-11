package com.spike.jvm.asm.core;

import com.spike.jvm.asm.ASMs;
import com.spike.jvm.asm.tree.AddTimerTransformer;
import com.spike.jvm.asm.tree.OptimizeJumpTransformer;
import com.spike.jvm.asm.tree.analysis.CyclomaticComplexity;
import com.spike.jvm.asm.tree.analysis.RemoveDeadCodeAdapter;
import com.spike.jvm.junit.OutputTestBase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;
import java.lang.reflect.Method;

public class ASMTreeTest extends OutputTestBase {
    // =========================================================================
    // Classes
    // =========================================================================

    /**
     * @see org.objectweb.asm.tree.ClassNode
     * @see org.objectweb.asm.tree.MethodNode
     * @see org.objectweb.asm.tree.FieldNode
     */
    @Test
    public void testGeneratingClass() {
        ClassNode cn = this.createClassNode();
        output(() -> {
            TraceClassVisitor tcv = new TraceClassVisitor(new PrintWriter(System.out));
            cn.accept(tcv);
        });
    }

    private ClassNode createClassNode() {
        ClassNode cn = new ClassNode();
        cn.version = ASMs.JAVA_VERSION;
        cn.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE;
        cn.name = "pkg/Comparable";
        cn.superName = "java/lang/Object";
        cn.interfaces.add("pkg/Measurable");
        // fields
        cn.fields.add(new FieldNode(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "LESS", "I", null, -1));
        cn.fields.add(new FieldNode(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "EQUAL", "I", null, 0));
        cn.fields.add(new FieldNode(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "GREATER", "I", null, 1));
        // methods
        cn.methods.add(new MethodNode(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT,
                "compareTo", "(Ljava/lang/Object;)I", null, null));
        return cn;
    }

    /**
     * @see com.spike.jvm.asm.tree.RemoveMethodTransformer
     * @see com.spike.jvm.asm.tree.AddFieldTransformer
     */
    @Test
    public void testAddAndRemoveClassMembers() {
        ClassNode cn = this.createClassNode();
        // remove method
        var removeMethodTransformer = new com.spike.jvm.asm.tree.RemoveMethodTransformer(null,
                "compareTo", "(Ljava/lang/Object;)I");
        removeMethodTransformer.transform(cn);
        // add field
        var addFieldTransformer = new com.spike.jvm.asm.tree.AddFieldTransformer(null,
                Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "DEFAULT", "I");
        addFieldTransformer.transform(cn);
        // output
        output(() -> {
            TraceClassVisitor tcv = new TraceClassVisitor(new PrintWriter(System.out));
            cn.accept(tcv);
        });
    }

    /**
     * <pre>
     * class ClassNode extends ClassVisitor {}
     *
     * </pre>
     *
     * @see ClassNode
     */
    @Test
    public void testComposition() {
        // construct ClassNode from byte array
        byte[] b = new ASMCoreTest().generateTraceClassBytes();
        ClassReader cr = new ClassReader(b);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0); // read
        Assertions.assertThat(cn.name).isEqualTo("pkg/Comparable");

        // convert ClassNode to byte array
        ClassWriter cw = new ClassWriter(0);
        cn.accept(cw); // write
        byte[] b2 = cw.toByteArray();
        Assertions.assertThat(b2).isEqualTo(b);
    }

    // =========================================================================
    // Methods
    // =========================================================================

    /**
     * @see pkg.Bean#checkAndSetF(int)
     * @see pkg.Bean
     */
    @Test
    public void testGeneratingMethod() {
        ClassNode cn = this.generateBeanClassNode();

        ClassWriter cw = new ClassWriter(0);
        TraceClassVisitor tcv = new TraceClassVisitor(cw, new PrintWriter(System.out));
        cn.accept(tcv);
    }

    protected ClassNode generateBeanClassNode() {
        ClassNode cn = new ClassNode();
        cn.version = ASMs.JAVA_VERSION;
        cn.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE;
        cn.name = "pkg/Bean";
        cn.superName = "java/lang/Object";

        MethodNode mn = new MethodNode(Opcodes.ACC_PUBLIC, "checkAndSetF",
                "(I)V", null, null);
        InsnList il = new InsnList();
        il.add(new VarInsnNode(Opcodes.ILOAD, 1));
        LabelNode label = new LabelNode();
        il.add(new JumpInsnNode(Opcodes.IFLT, label));
        il.add(new VarInsnNode(Opcodes.ALOAD, 0));
        il.add(new VarInsnNode(Opcodes.ILOAD, 1));
        il.add((new FieldInsnNode(Opcodes.PUTFIELD, "pkg/Bane", "f", "I")));
        LabelNode end = new LabelNode();
        il.add(new JumpInsnNode(Opcodes.GOTO, end));
        il.add(label);
        il.add(new FrameNode(Opcodes.F_SAME, 0, null, 0, null));
        il.add(new TypeInsnNode(Opcodes.NEW, "java/lang/IllegalArgumentException"));
        il.add(new InsnNode(Opcodes.DUP));
        il.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                "java/lang/IllegalArgumentException", "<init>", "()V", false));
        il.add(new InsnNode(Opcodes.ATHROW));
        il.add(end);

        il.add(new FrameNode(Opcodes.F_SAME, 0, null, 0, null));
        mn.instructions.add(il);
        mn.maxStack = 2;
        mn.maxLocals = 2;

        Assertions.assertThat(mn.name).isEqualTo("checkAndSetF");

        cn.methods.add(mn);

        return cn;
    }

    /**
     * @see com.spike.jvm.asm.tree.AddTimerTransformer
     * @see com.spike.jvm.asm.tree.RemoveGetFieldPutFieldTransformer
     * @see com.spike.jvm.asm.tree.OptimizeJumpTransformer
     */
    @Test
    public void testTransformingMethod() throws ClassNotFoundException {
        System.out.println("=== AddTimerTransformer");
        byte[] b = new ASMCoreTest().generateClassC();
        ClassReader cr = new ClassReader(b);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0); // read

        System.out.println("=== AddTimerTransformer after");
        AddTimerTransformer transformer = new AddTimerTransformer(null);
        transformer.transform(cn);

        ClassWriter cw = new ClassWriter(0);
        cn.accept(cw); // write
        byte[] b2 = cw.toByteArray();

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
        org.assertj.core.api.Assertions.assertThat(getOutput()).containsPattern("Last \\d+ ms");


        System.out.println("=== OptimizeJumpTransformer");
        byte[] bOfC = new ASMCoreTest().generateBeanClassBytes();
        ClassReader cr2 = new ClassReader(bOfC);
        ClassNode cn2 = new ClassNode();
        cr2.accept(cn2, 0);
        OptimizeJumpTransformer transformer2 = new OptimizeJumpTransformer(null);
        System.out.println("=== OptimizeJumpTransformer after");
        cn2.methods.forEach(mn -> {
            transformer2.transform(mn);
        });
        cn2.accept(new TraceClassVisitor(new PrintWriter(System.out))); // write
    }

    // =========================================================================
    // Method Analysis
    // =========================================================================

    /**
     * @see com.spike.jvm.asm.tree.analysis.RemoveDeadCodeAdapter
     */
    @Test
    public void testBasicDataFlowAnalysis() {
        System.out.println("=== OptimizeJumpTransformer");
        byte[] bOfC = new ASMCoreTest().generateBeanClassBytes();
        ClassReader cr2 = new ClassReader(bOfC);
        ClassNode cn2 = new ClassNode();
        cr2.accept(cn2, 0);
        OptimizeJumpTransformer transformer2 = new OptimizeJumpTransformer(null);
        System.out.println("=== OptimizeJumpTransformer after");
        cn2.methods.forEach(mn -> {
            transformer2.transform(mn);
        });
        ClassWriter cw = new ClassWriter(0);
        TraceClassVisitor tcv = new TraceClassVisitor(cw, new PrintWriter(System.out));
        cn2.accept(tcv); // write
        byte[] b = cw.toByteArray();

        System.out.println("=== RemoveDeadCodeAdapter");
        ClassReader cr3 = new ClassReader(b);
        TraceClassVisitor tcv2 = new TraceClassVisitor(new PrintWriter(System.out));
        RemoveDeadCodeAdapter adapter = new RemoveDeadCodeAdapter("pkg/Bean", tcv2);
        cr3.accept(adapter, 0);
    }

    /**
     * @see com.spike.jvm.asm.tree.analysis.BasicVerifierMethodAdapter
     * @see com.spike.jvm.asm.tree.analysis.RemoveUnusedCastTransformer
     * @see com.spike.jvm.asm.tree.analysis.RemoveUnusedCastAdapter
     */
    @Test
    public void testBasicDataFlowVerifier() {
    }

    /**
     * @see com.spike.jvm.asm.tree.analysis.IsNullInterpreter
     * @see com.spike.jvm.asm.tree.analysis.NullDereferenceAnalyzer
     */
    @Test
    public void testUserDefinedDataFlowAnalysis() {
    }

    /**
     * @see com.spike.jvm.asm.tree.analysis.CyclomaticComplexity
     */
    @Test
    public void testControlFlowAnalysis() {
        byte[] bOfC = new ASMCoreTest().generateBeanClassBytes();
        ClassReader cr2 = new ClassReader(bOfC);
        ClassNode cn2 = new ClassNode();
        cr2.accept(cn2, 0);

        cn2.methods.forEach(mn -> {
            CyclomaticComplexity cc = new CyclomaticComplexity();
            try {
                int complexity = cc.getCyclomaticComplexity("pkg/Bean", mn);
                System.out.println("Method: " + mn.name + ", Cyclomatic Complexity: " + complexity);
            } catch (org.objectweb.asm.tree.analysis.AnalyzerException e) {
                e.printStackTrace();
            }
        });
        Assertions.assertThat(getOutput()).contains("Method: <init>, Cyclomatic Complexity: 1" + System.lineSeparator() +
                "Method: getF, Cyclomatic Complexity: 1" + System.lineSeparator() +
                "Method: setF, Cyclomatic Complexity: 1" + System.lineSeparator() +
                "Method: checkAndSetF, Cyclomatic Complexity: 1" + System.lineSeparator() +
                "Method: sleep, Cyclomatic Complexity: 1");
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
