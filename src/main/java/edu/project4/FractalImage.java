package edu.project4;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FractalImage {
    private int fractals;
    private int pixelsNum;
    private Pixel[][] pixels;
    AffineTransformation[] transformations;
    Renderer renderer;

    public FractalImage(int fractals, int pixelsNum, int width, int height, int eqCount) {
        this.fractals = fractals;
        this.pixelsNum = pixelsNum;
        this.pixels = new Pixel[width][height];
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                pixels[i][j] = new Pixel(0, 0, 0, 0);
            }
        }

        this.renderer = new Renderer(eqCount, pixelsNum, fractals, pixels);
    }

    public void renderBySingleThread() {
        pixels = this.renderer.renderSingleThread();
        pixels = this.renderer.correctionSingleThread();
    }

    public void renderByMultipleThread() {
        pixels = this.renderer.renderMultipleThreads();
        pixels = this.renderer.correctionMultipleThreads();
    }

    public void save(String path, Type fileType) {
        int width = pixels.length;
        int height = pixels[0].length;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                image.setRGB(
                    i,
                    j,
                    new Color((int) pixels[i][j].red(), (int) pixels[i][j].green(), (int) pixels[i][j].blue()).getRGB()
                );
            }
        }

        File output = new File(path);
        try {
            ImageIO.write(image, fileType.name(), output);
        } catch (IOException e) {
            return;
        }
    }
}
