package edu.project4;

import org.junit.jupiter.api.Test;
import static java.lang.System.currentTimeMillis;
import static org.assertj.core.api.Fail.fail;

public class ProjectTest {
    @Test
    void sampleTest() {
        long startTime = currentTimeMillis();
        FractalImage gen1 = new FractalImage(100000, 2000, 1920, 1080, 20);
        gen1.renderBySingleThread();
        gen1.save("src/test/java/edu/project4/output_single.jpeg", Type.JPEG);
        long singleThreadTime = currentTimeMillis() - startTime;

        startTime = currentTimeMillis();
        FractalImage gen2 = new FractalImage(100000, 2000, 1920, 1080, 20);
        gen2.renderByMultipleThread();
        gen2.save("src/test/java/edu/project4/output_multiple.jpeg", Type.JPEG);
        long multipleThreadTime = currentTimeMillis() - startTime;

        if (multipleThreadTime < singleThreadTime) {
            System.out.println("Многопоточный алгоритм быстрее в " + (1.0 * singleThreadTime / multipleThreadTime) + " раз");
        } else {
            fail("Многопоточный медленнее чем однопоточный");
        }
    }
}
