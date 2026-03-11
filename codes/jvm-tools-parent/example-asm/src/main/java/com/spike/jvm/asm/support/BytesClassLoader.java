package com.spike.jvm.asm.support;

public class BytesClassLoader extends ClassLoader {
    private final byte[] bytecode;

    public BytesClassLoader(byte[] bytecode) {
        this.bytecode = bytecode;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (bytecode == null) {
            throw new ClassNotFoundException(name);
        }

        return defineClass(name, bytecode, 0, bytecode.length);
    }
}
