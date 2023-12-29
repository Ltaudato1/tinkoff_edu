package edu.project4;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

public class Renderer {
    private static final double XMIN = -1.777;
    private static final double XMAX = 1.777;
    private static final double YMIN = -1;
    private static final double YMAX = 1;
    private static final int FIRST_TWENTY_PIXELS = 20;
    private static final double GAMMA = 2.2;
    private static final int THREADS = 4;

    private final AffineTransformation[] transformations;
    private final int eqCount;
    private final int pixelsNum;
    private final int fractals;
    private Pixel[][] pixels;

    public Renderer(int eqCount, int pixelsNum, int fractals, Pixel[][] pixels) {
        this.eqCount = eqCount;
        this.pixelsNum = pixelsNum;
        this.fractals = fractals;
        this.pixels = pixels;

        Random random = new Random();
        transformations = new AffineTransformation[eqCount];
        for (int i = 0; i < eqCount; ++i) {
            transformations[i] = AffineTransformation.getRandomTransformation(random);
        }
    }

    private void renderFractal() {
        Random random = new Random();
        int width = pixels.length;
        int height = pixels[0].length;

        double newX = random.nextDouble(XMIN, XMAX);
        double newY = random.nextDouble(YMIN, YMAX);

        for (int step = -FIRST_TWENTY_PIXELS; step < pixelsNum; ++step) {
            int i = random.nextInt(eqCount);

            double x = transformations[i].a() * newX + transformations[i].b() * newY + transformations[i].c();
            double y = transformations[i].d() * newX + transformations[i].e() * newY + transformations[i].f();

            newX = 1 / y;
            newY = 1 / x;

            if (step >= 0) {
                int x1 = width - (int) (((XMAX - newX) / (XMAX - XMIN)) * width);
                int y1 = height - (int) (((YMAX - newY) / (YMAX - YMIN)) * height);

                if (x1 < width && y1 < height && x1 > -1 && y1 > -1) {
                    double red;
                    double green;
                    double blue;
                    if (pixels[x1][y1].hitCount() == 0) {
                        red = transformations[i].red();
                        green = transformations[i].green();
                        blue = transformations[i].blue();
                    } else {
                        red = (transformations[i].red() + pixels[x1][y1].red()) / 2;
                        green = (transformations[i].green() + pixels[x1][y1].green()) / 2;
                        blue = (transformations[i].blue() + pixels[x1][y1].blue()) / 2;
                    }
                    pixels[x1][y1] = new Pixel(red, green, blue, pixels[x1][y1].hitCount() + 1);
                }
            }
        }
    }

    public Pixel[][] renderSingleThread() {
        for (int num = 0; num < fractals; ++num) {
            renderFractal();
        }
        return pixels;
    }

    public Pixel[][] correctionSingleThread() {
        int width = pixels.length;
        int height = pixels[0].length;
        double max = calculateMax(pixels);

        for (int row = 0; row < width; ++row) {
            for (int col = 0; col < height; ++col) {
                double normalized = log10(pixels[row][col].hitCount()) / max;
                double red = (pixels[row][col].red() * pow(normalized, (1.0 / GAMMA)));
                double green = (pixels[row][col].green() * pow(normalized, (1.0 / GAMMA)));
                double blue = (pixels[row][col].blue() * pow(normalized, (1.0 / GAMMA)));
                pixels[row][col] = new Pixel(red, green, blue, pixels[row][col].hitCount());
            }
        }
        return pixels;
    }

    public Pixel[][] renderMultipleThreads() {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);

        for (int threadNum = 0; threadNum < THREADS; threadNum++) {
            int finalThreadNum = threadNum;
            int numFractalsPerThread = fractals / THREADS;

            executor.submit(() -> {
                for (int num = finalThreadNum * numFractalsPerThread; num < (finalThreadNum + 1) * numFractalsPerThread;
                     ++num) {
                    renderFractal();
                }
            });
        }

        try {
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            return pixels;
        } catch (InterruptedException e) {
            return null;
        }
    }

    public Pixel[][] correctionMultipleThreads() {
        int width = pixels.length;
        int height = pixels[0].length;
        double max = calculateMax(pixels);

        ExecutorService executor = Executors.newFixedThreadPool(THREADS);

        final int rowsPerThread = width / THREADS;
        final int colsPerThread = height / THREADS;

        for (int numThread = 0; numThread < THREADS; ++numThread) {
            int finalNumThread = numThread;
            executor.submit(() -> {
                for (int row = rowsPerThread * finalNumThread; row < rowsPerThread * (finalNumThread + 1); ++row) {
                    for (int col = colsPerThread * finalNumThread; col < colsPerThread * (finalNumThread + 1); ++col) {
                        if (pixels[row][col].hitCount() != 0) {
                            double normalized = log10(pixels[row][col].hitCount()) / max;
                            double red = pixels[row][col].red() * Math.pow(normalized, 1.0 / GAMMA);
                            double green = pixels[row][col].green() * Math.pow(normalized, 1.0 / GAMMA);
                            double blue = pixels[row][col].blue() * Math.pow(normalized, 1.0 / GAMMA);

                            pixels[row][col] =
                                new Pixel(
                                    (int) red,
                                    (int) green,
                                    (int) blue,
                                    pixels[row][col].hitCount()
                                );
                        }
                    }
                }
            });
        }
        return pixels;
    }

    private double calculateMax(Pixel[][] pixels) {
        int width = pixels.length;
        int height = pixels[0].length;
        double max = 0.0;

        for (int row = 0; row < width; ++row) {
            for (int col = 0; col < height; ++col) {
                if (pixels[row][col].hitCount() != 0) {
                    double value = log10(pixels[row][col].hitCount());
                    if (value > max) {
                        max = value;
                    }
                }
            }
        }

        return max;
    }
}
