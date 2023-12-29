package edu.hw11.test3;

public class ByteClassLoader extends ClassLoader {
    public Class<?> defineClass(String name, byte[] bytecode) {
        return defineClass(name, bytecode, 0, bytecode.length);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }
}
