package edu.hw2;

import edu.hw2.Task1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test1 {
    @Test
    @DisplayName("Sample test")
    void test() {
        var two = new Task1.Expr.Constant(2);
        var four = new Task1.Expr.Constant(4);
        var negOne = new Task1.Expr.Negate(new Task1.Expr.Constant(1));
        var sumTwoFour = new Task1.Expr.Addition(two, four);
        var mult = new Task1.Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Task1.Expr.Exponent(mult, 2);
        var res = new Task1.Expr.Addition(exp, new Task1.Expr.Constant(1));

        assertThat(res.evaluate()).isEqualTo(37);
    }

    @Test
    @DisplayName("Все цифры")
    void testOnlyDigits() {
        var res = new Task1.Expr.Multiplication(12312, 34);
        var exp = new Task1.Expr.Exponent(36, 2);
        var add = new Task1.Expr.Addition(2, 2);
        var neg = new Task1.Expr.Negate(12);

        assertThat(res.evaluate()).isEqualTo(418608);
    }

    @Test
    @DisplayName("Нагромождение методов")
    void testManyMethods() {
        var res = new Task1.Expr.Exponent(
            new Task1.Expr.Addition(new Task1.Expr.Constant(12), new Task1.Expr.Negate(new Task1.Expr.Constant(10))),
            new Task1.Expr.Multiplication(new Task1.Expr.Addition(2, 0.5), 2)
        );
        assertThat(res.evaluate()).isEqualTo(32);
    }
}
