package edu.hw10.task2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {
    private final Object target;
    private final Map<String, Object> cache = new HashMap<>();

    private CacheProxy(Object target) {
        this.target = target;
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(Object target, Class<?> interfaceClass) {
        return (T) Proxy.newProxyInstance(
            interfaceClass.getClassLoader(),
            new Class<?>[]{interfaceClass},
            new CacheProxy(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            String key = generateKey(method, args);
            if (cache.containsKey(key)) {
                return cache.get(key);
            }

            Object result = method.invoke(target, args);
            cache.put(key, result);

            Cache cacheAnnotation = method.getAnnotation(Cache.class);
            if (cacheAnnotation.persist()) {
                try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter("src/main/java/edu/hw10/task2/" + key + ".txt"))) {
                    writer.write(result + "");
                }
            }

            return result;
        } else {
            return method.invoke(target, args);
        }
    }

    private String generateKey(Method method, Object[] args) {
        return method.getName() + Arrays.hashCode(args);
    }

}
