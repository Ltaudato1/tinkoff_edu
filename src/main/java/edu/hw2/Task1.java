package edu.hw2;

public class Task1 {
    public interface Expr {
        double evaluate();

        public record Constant(double num) implements Expr {
            public Constant(Expr operand) {
                this(operand.evaluate());
            }

            public double evaluate() {
                return this.num;
            }
        }

        public record Negate(double num) implements Expr {
            public Negate(Expr operand) {
                this(operand.evaluate());
            }

            public double evaluate() {
                return -this.num;
            }
        }

        public record Addition(double num1, double num2) implements Expr {
            public Addition(Expr operand1, Expr operand2) {
                this(operand1.evaluate(), operand2.evaluate());
            }

            public Addition(double num, Expr operand) {
                this(num, operand.evaluate());
            }

            public Addition(Expr operand, double num) {
                this(operand.evaluate(), num);
            }

            public double evaluate() {
                return this.num1 + this.num2;
            }
        }

        public record Multiplication(double num1, double num2) implements Expr {
            public Multiplication(Expr operand1, Expr operand2) {
                this(operand1.evaluate(), operand2.evaluate());
            }

            public Multiplication(double num, Expr operand) {
                this(num, operand.evaluate());
            }

            public Multiplication(Expr operand, double num) {
                this(num, operand.evaluate());
            }

            public double evaluate() {
                return this.num1 * this.num2;
            }
        }

        public record Exponent(double base, double exponent) implements Expr {
            public Exponent(Expr operand1, Expr operand2) {
                this(operand1.evaluate(), operand2.evaluate());
            }

            public Exponent(double num, Expr operand) {
                this(num, operand.evaluate());
            }

            public Exponent(Expr operand, double num) {
                this(operand.evaluate(), num);
            }

            public double evaluate() {
                return Math.pow(base, exponent);
            }
        }
    }
}
