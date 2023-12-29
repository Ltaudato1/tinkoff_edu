package edu.project4;

import java.util.Random;
import static java.lang.Math.pow;

public record AffineTransformation(double a, double b, double d, double e, double c, double f, int red, int green,
                                   int blue) {

    @SuppressWarnings("MagicNumber")
    public static AffineTransformation getRandomTransformation(Random random) {
        double a;
        double b;
        double d;
        double e;
        double c;
        double f;

        do {
            a = random.nextDouble() * 3 - 1.5;
            b = random.nextDouble() * 3 - 1.5;
            d = random.nextDouble() * 3 - 1.5;
            e = random.nextDouble() * 3 - 1.5;
            c = random.nextDouble() * 10 - 5;
            f = random.nextDouble() * 10 - 5;
        } while (!isValidTransformationMatrix(a, b, d, e));

        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        return new AffineTransformation(a, b, d, e, c, f, red, green, blue);
    }

    private static boolean isValidTransformationMatrix(double a, double b, double d, double e) {
        return a * a + d * d < 1
            && b * b + e * e < 1
            && a * a + b * b + d * d + e * e < 1 + pow(a * e - b * d, 2);
    }
}
