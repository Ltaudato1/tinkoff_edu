package edu.project4;

import org.junit.jupiter.api.Test;
import static java.lang.System.currentTimeMillis;
import static org.assertj.core.api.Fail.fail;

public class ProjectTest {
    @Test
    void sampleTest() {
        long startTime = currentTimeMillis();
        FractalFlameGenSingleThread gen1 = new FractalFlameGenSingleThread(100000, 2000, 20);
        long singleThreadTime = currentTimeMillis() - startTime;

        startTime = currentTimeMillis();
        FractalFlameGenMultipleThread gen2 = new FractalFlameGenMultipleThread(100000, 2000, 20);
        long multipleThreadTime = currentTimeMillis() - startTime;

        System.out.println("Многопоточный алгоритм быстрее в " + (1.0 * singleThreadTime / multipleThreadTime) + " раз");
    }
}
