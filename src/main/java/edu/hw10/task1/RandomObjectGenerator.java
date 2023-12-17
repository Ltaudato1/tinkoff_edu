package edu.hw10.task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.Random;

public class RandomObjectGenerator {
    private final Random random = new Random();
    private static final int NUM_FOR_PROBABILITY = 5;

    public <T> T nextObject(Class<T> clazz) throws Exception {
        if (clazz.isRecord()) {
            RecordComponent[] components = clazz.getRecordComponents();
            Object[] args = new Object[components.length];

            for (int i = 0; i < components.length; i++) {
                args[i] = generateValue(components[i].getType(), components[i].getAnnotations());
            }
            return (T) clazz.getDeclaredConstructors()[0].newInstance(args);
        } else {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Object[] args = new Object[parameterTypes.length];

            for (int i = 0; i < parameterTypes.length; i++) {
                args[i] = generateValue(parameterTypes[i], parameterAnnotations[i]);
            }

            return (T) clazz.getDeclaredConstructors()[0].newInstance(args);
        }
    }


    public <T> T nextObject(Class<T> clazz, String factoryMethod) throws Exception {
        try {
            Method factory = null;
            for (Method method : clazz.getMethods()) {
                if (method.getName().equals(factoryMethod)) {
                    factory = method;
                    break;
                }
            }
            factory.setAccessible(true);
            Annotation[][] parameterAnnotations = factory.getParameterAnnotations();
            Class<?>[] parameterTypes = factory.getParameterTypes();
            Object[] args = new Object[parameterTypes.length];

            for (int i = 0; i < parameterTypes.length; i++) {
                args[i] = generateValue(parameterTypes[i], parameterAnnotations[i]);
            }

            return (T) factory.invoke(null, args);
        } catch (Exception e) {
            throw new NoSuchMethodException("Method doesn't exist");
        }
    }


    private Object generateValue(Class<?> type, Annotation[] annotations) {
        int max = Integer.MAX_VALUE;
        int min = 0;
        boolean nullAllowed = true;

        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == NotNull.class) {
                nullAllowed = false;
            } else if (annotation.annotationType() == Min.class) {
                Min minAnnotation = (Min) annotation;
                min = minAnnotation.min();
            } else if (annotation.annotationType() == Max.class) {
                Max maxAnnotation = (Max) annotation;
                max = maxAnnotation.max();
            }
        }

        return returnValue(max, min, nullAllowed, type);
    }

    private Object returnValue(int max, int min, boolean nullAllowed, Class<?> type) {
        int probabilityForNull = random.nextInt(NUM_FOR_PROBABILITY);

        if (probabilityForNull == 0
            && nullAllowed && !type.isPrimitive()) { // null генерируется с вероятностью 1 / NUM_FOR_PROBABILITY
            return null;
        } else {
            if (type == int.class || type == Integer.class) {
                return min + random.nextInt(max - min);
            } else if (type == Double.class || type == double.class) {
                return random.nextDouble() * (max - min) + min;
            } else {
                throw new IllegalArgumentException("invalid argument");
            }
        }
    }
}
