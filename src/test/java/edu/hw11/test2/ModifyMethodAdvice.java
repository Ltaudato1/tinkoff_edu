package edu.hw11.test2;

import net.bytebuddy.asm.Advice;

class ModifyMethodAdvice {
    @Advice.OnMethodExit
    static void exit(@Advice.Return(readOnly = false) int result, @Advice.Argument(0) int a, @Advice.Argument(1) int b) {
        // Изменяем возвращаемое значение на произведение a и b
        result = a * b;
    }
}
