package edu.hw11;

import java.io.IOException;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

@SuppressWarnings("MultipleStringLiterals")
public class Task3 {
    private Task3() {

    }

    // Код с asm сложно читаемый, так что комментарий дал к каждой строке
    public static byte[] generateFibFunction() throws IOException {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        MethodVisitor mv;

        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Fibonacci", null, "java/lang/Object", null);

        // Добавляем метод fib
        mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "fib", "(I)J", null, null);
        mv.visitCode();

        // Добавляем начальный фрейм
        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

        mv.visitVarInsn(Opcodes.ILOAD, 0);
        Label nonZeroLabel = new Label();
        mv.visitJumpInsn(Opcodes.IFNE, nonZeroLabel); // if (n != 0)
        mv.visitInsn(Opcodes.LCONST_0); // Загрузка 0 на стек
        mv.visitInsn(Opcodes.LRETURN); // Возвращаем 0
        mv.visitLabel(nonZeroLabel); // Метка для случая n != 0

        // Генерация кода метода
        Label elseLabel = new Label();
        Label endLabel = new Label();

        mv.visitVarInsn(Opcodes.ILOAD, 0); // Загрузка значения n на стек
        mv.visitInsn(Opcodes.ICONST_2); // Загрузка константы 2 на стек
        mv.visitJumpInsn(Opcodes.IF_ICMPLE, elseLabel); // Сравнение: если n <= 2, перейти к elseLabel

        // Вычисление fib(n-1)
        mv.visitVarInsn(Opcodes.ILOAD, 0); // Загрузка значения n на стек
        mv.visitInsn(Opcodes.ICONST_1); // Загрузка константы 1 на стек
        mv.visitInsn(Opcodes.ISUB); // Вычитание: n - 1
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "Fibonacci", "fib", "(I)J", false); // Вызов fib(n-1)
        mv.visitVarInsn(Opcodes.ILOAD, 0); // Загрузка значения n на стек
        mv.visitInsn(Opcodes.ICONST_2); // Загрузка константы 2 на стек
        mv.visitInsn(Opcodes.ISUB); // Вычитание: n - 2
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "Fibonacci", "fib", "(I)J", false); // Вызов fib(n-2)
        mv.visitInsn(Opcodes.LADD); // Сложение результатов

        // Пропуск кода else блока при n <= 2
        mv.visitJumpInsn(Opcodes.GOTO, endLabel);

        // else блок: если n <= 2, возвращаем n
        mv.visitLabel(elseLabel);
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.I2L); // Преобразование int в long

        mv.visitLabel(endLabel);

        mv.visitInsn(Opcodes.LRETURN); // Возвращаем результат типа long
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        cw.visitEnd();
        return cw.toByteArray();
    }
}
