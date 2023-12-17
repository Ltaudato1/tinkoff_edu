package edu.hw10;

import edu.hw10.task1.RandomObjectGenerator;
import edu.hw10.task1.TestClass;
import edu.hw10.task1.TestRecord;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test1 {

    @Test
    void sampleTest() throws Exception {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        var test = rog.nextObject(TestClass.class, "create");
        var test2 = rog.nextObject(TestRecord.class);

        assertTrue(test.a < 15 && test2.b() != null);
    }
}
