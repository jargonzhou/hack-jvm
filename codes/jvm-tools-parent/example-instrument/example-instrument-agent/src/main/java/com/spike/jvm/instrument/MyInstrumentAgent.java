package com.spike.jvm.instrument;

import java.lang.instrument.Instrumentation;

/**
 * Example from <a href="https://www.baeldung.com/java-instrumentation">Guide to Java Instrumentation</a>
 *
 * @see java.lang.instrument.ClassFileTransformer
 */
public class MyInstrumentAgent {

    // static
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("[Agent] in premain method");

        String className = "com.spike.jvm.instrument.MyAtm";
        transformClass(className, inst);
    }

    // dynamic
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("[Agent] in agentmain method");

        String className = "com.spike.jvm.instrument.MyAtm";
        transformClass(className, inst);
    }

    private static void transformClass(String className, Instrumentation instrumentation) {
        Class<?> targetCls = null;
        ClassLoader targetClassLoader = null;
        // see if we can get the class using forName
        try {
            targetCls = Class.forName(className);
            targetClassLoader = targetCls.getClassLoader();
            transform(targetCls, targetClassLoader, instrumentation);
            return;
        } catch (Exception ex) {
            System.err.println("Class [{}] not found with Class.forName");
        }
        // otherwise iterate all loaded classes and find what we want
        for (Class<?> clazz : instrumentation.getAllLoadedClasses()) {
            if (clazz.getName().equals(className)) {
                targetCls = clazz;
                targetClassLoader = targetCls.getClassLoader();
                transform(targetCls, targetClassLoader, instrumentation);
                return;
            }
        }
        throw new RuntimeException("Failed to find class [" + className + "]");
    }

    private static void transform(Class<?> clazz, ClassLoader classLoader, Instrumentation instrumentation) {
        AtmTransformer dt = new AtmTransformer(clazz.getName(), classLoader);
        instrumentation.addTransformer(dt, true);
        try {
            instrumentation.retransformClasses(clazz);
        } catch (Exception ex) {
            throw new RuntimeException("Transform failed for class: [" + clazz.getName() + "]", ex);
        }
    }

}
