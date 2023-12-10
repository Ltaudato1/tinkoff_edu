package edu.project4;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import static java.lang.Math.log10;
import static java.lang.Math.pow;
import static java.lang.Math.tanh;

public class FractalFlameGenSingleThread extends JFrame {

    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    private static final int FIRST_TWENTY_PIXELS = 20;
    private static final double XMIN = -1.777;
    private static final double XMAX = 1.777;
    private static final double YMIN = -1;
    private static final double YMAX = 1;
    private static final double GAMMA = 2.2;

    private final int fractals;
    private final int pixelsNum;
    private final int eqCount;

    private BufferedImage image;

    Pixel[][] pixels = new Pixel[WIDTH][HEIGHT];

    public FractalFlameGenSingleThread(int fractals, int pixelsNum, int eqCount) {
        this.fractals = fractals;
        this.pixelsNum = pixelsNum;
        this.eqCount = eqCount;

        for (int i = 0; i < WIDTH; ++i) {
            for (int j = 0; j < HEIGHT; ++j) {
                pixels[i][j] = new Pixel(0, 0, 0, 0);
            }
        }

        initUI();
    }

    private void initUI() {
        setTitle("Fractal Flame");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        render();
        correction();

        for (int i = 0; i < WIDTH; ++i) {
            for (int j = 0; j < HEIGHT; ++j) {
                image.setRGB(
                    i,
                    j,
                    new Color((int) pixels[i][j].red, (int) pixels[i][j].green, (int) pixels[i][j].blue).getRGB()
                );
            }
        }

        File output = new File("src/main/java/edu/project4/output_single.jpeg");
        try {
            ImageIO.write(image, "jpeg", output);
        } catch (IOException e) {
            return;
        }
    }

    private void render() {
        AffineTransformation[] transformations = new AffineTransformation[eqCount];
        Random random = new Random();
        for (int i = 0; i < eqCount; ++i) {
            transformations[i] = getRandomTransformation(random);
        }
        for (int num = 0; num < fractals; ++num) {
            double newX = random.nextDouble(XMIN, XMAX);
            double newY = random.nextDouble(YMIN, YMAX);

            for (int step = -FIRST_TWENTY_PIXELS; step < pixelsNum; ++step) {
                int i = random.nextInt(eqCount);

                double x = transformations[i].a * newX + transformations[i].b * newY + transformations[i].c;
                double y = transformations[i].d * newX + transformations[i].e * newY + transformations[i].f;

                newX = 1 / y;
                newY = 1 / x;

                if (step >= 0) {
                    int x1 = WIDTH - (int) (((XMAX - newX) / (XMAX - XMIN)) * WIDTH);
                    int y1 = HEIGHT - (int) (((YMAX - newY) / (YMAX - YMIN)) * HEIGHT);

                    if (x1 < WIDTH && y1 < HEIGHT && x1 > -1 && y1 > -1) {
                        double red;
                        double green;
                        double blue;
                        if (pixels[x1][y1].hitCount == 0) {
                            red = transformations[i].red;
                            green = transformations[i].green;
                            blue = transformations[i].blue;
                        } else {
                            red = (transformations[i].red + pixels[x1][y1].red) / 2;
                            green = (transformations[i].green + pixels[x1][y1].green) / 2;
                            blue = (transformations[i].blue + pixels[x1][y1].blue) / 2;
                        }
                        pixels[x1][y1] = new Pixel(red, green, blue, pixels[x1][y1].hitCount + 1);
                    }
                }
            }
        }
    }

    private void correction() {
        double max = 0.0;
        double[][] normal = new double[WIDTH][HEIGHT];

        for (int row = 0; row < WIDTH; ++row) {
            for (int col = 0; col < HEIGHT; ++col) {
                if (pixels[row][col].hitCount != 0) {
                    normal[row][col] = log10(pixels[row][col].hitCount);
                    if (normal[row][col] > max) {
                        max = normal[row][col];
                    }
                }
            }
        }

        for (int row = 0; row < WIDTH; ++row) {
            for (int col = 0; col < HEIGHT; ++col) {
                normal[row][col] /= max;
                double red = (pixels[row][col].red * pow(normal[row][col], (1.0 / GAMMA)));
                double green = (pixels[row][col].green * pow(normal[row][col], (1.0 / GAMMA)));
                double blue = (pixels[row][col].blue * pow(normal[row][col], (1.0 / GAMMA)));
                pixels[row][col] = new Pixel(red, green, blue, pixels[row][col].hitCount);
            }
        }
    }

    @SuppressWarnings("MagicNumber")
    private AffineTransformation getRandomTransformation(Random random) {
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

    private boolean isValidTransformationMatrix(double a, double b, double d, double e) {
        return a * a + d * d < 1
            && b * b + e * e < 1
            && a * a + b * b + d * d + e * e < 1 + pow(a * e - b * d, 2);
    }

    private record AffineTransformation(double a, double b, double d, double e, double c, double f, int red, int green,
                                        int blue) {
    }

    public record Pixel(double red, double green, double blue, int hitCount) {
    }
}
