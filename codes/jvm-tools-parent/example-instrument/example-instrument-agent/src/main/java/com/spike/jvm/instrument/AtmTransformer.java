package com.spike.jvm.instrument;

//import io.netty.buffer.ByteBufUtil;
//import io.netty.buffer.Unpooled;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Example class file transformer.
 */
public class AtmTransformer implements ClassFileTransformer {

    private static final String WITHDRAW_MONEY_METHOD = "withdrawMoney";

    /**
     * The internal form class name of the class to transform
     */
    private String targetClassName;
    /**
     * The class loader of the class we want to transform
     */
    private ClassLoader targetClassLoader;

    public AtmTransformer(String targetClassName, ClassLoader targetClassLoader) {
        this.targetClassName = targetClassName;
        this.targetClassLoader = targetClassLoader;
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] byteCode = classfileBuffer;
//        System.out.println(ByteBufUtil.prettyHexDump(Unpooled.copiedBuffer(byteCode)));

        String finalTargetClassName = this.targetClassName.replaceAll("\\.", "/"); //replace . with /
        if (!className.equals(finalTargetClassName)) {
            return byteCode;
        }

        if (className.equals(finalTargetClassName) && loader.equals(targetClassLoader)) {
            System.out.println("[Agent] Transforming class MyAtm");
            try {
//                ClassPool cp = ClassPool.getDefault();
//                CtClass cc = cp.get(targetClassName);
                // https://stackoverflow.com/questions/35975762/classfiletransformer-implementations-with-javassist
                ClassPool cp = new ClassPool();
                cp.appendClassPath(new LoaderClassPath(loader));
                CtClass cc = cp.makeClass(new ByteArrayInputStream(classfileBuffer));

                CtMethod m = cc.getDeclaredMethod(WITHDRAW_MONEY_METHOD);
                m.addLocalVariable("startTime", CtClass.longType);
                m.insertBefore("startTime = System.currentTimeMillis();");

                StringBuilder endBlock = new StringBuilder();

                m.addLocalVariable("endTime", CtClass.longType);
                m.addLocalVariable("opTime", CtClass.longType);
                endBlock.append("endTime = System.currentTimeMillis();");
                endBlock.append("opTime = (endTime-startTime)/1000;");

                endBlock.append("System.out.println(\"[Application] Withdrawal operation completed in:\" + opTime + \" seconds!\");");

                m.insertAfter(endBlock.toString());

                byteCode = cc.toBytecode();
                cc.detach();
            } catch (NotFoundException | CannotCompileException | IOException e) {
                e.printStackTrace();
            }
        }
        return byteCode;
    }
}