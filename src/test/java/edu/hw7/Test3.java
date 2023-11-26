package edu.hw7;

import edu.hw7.Task3.DataBaseReadWriteLock;
import edu.hw7.Task3.DataBaseSynchronized;
import edu.hw7.Task3.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test3 {
    @Test
    @DisplayName("sample test (3.5)")
    void testMultithreadedAddAndFindByName() throws InterruptedException {
        DataBaseReadWriteLock database = new DataBaseReadWriteLock();

        int numThreads = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);

        for (int i = 0; i < numThreads; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        Person person = new Person((j + finalI * 1000),
                            "Name" + (j + finalI * 1000),
                            "Address" + (j + finalI * 1000),
                            "Phone" + (j + finalI * 1000));
                        database.add(person);
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        assertEquals(numThreads * 1000, database.getTotalCount());

        for (int i = 0; i < 4000; i++) {
            assertEquals(List.of((new Person(i, "Name" + i, "Address" + i, "Phone" + i))),
                database.findByName("Name" + i));

            assertEquals(List.of((new Person(i, "Name" + i, "Address" + i, "Phone" + i))),
                database.findByAddress("Address" + i));

            assertEquals(List.of((new Person(i, "Name" + i, "Address" + i, "Phone" + i))),
                database.findByPhone("Phone" + i));
        }

        executorService.shutdown();
    }


    @Test
    @DisplayName("sample test (3)")
    void testMultithreadedAddAndFindByName2() throws InterruptedException {
        DataBaseSynchronized database = new DataBaseSynchronized();

        int numThreads = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);

        for (int i = 0; i < numThreads; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        Person person = new Person((j + finalI * 1000),
                            "Name" + (j + finalI * 1000),
                            "Address" + (j + finalI * 1000),
                            "Phone" + (j + finalI * 1000));
                        database.add(person);
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        assertEquals(numThreads * 1000, database.getTotalCount());

        for (int i = 0; i < 4000; i++) {
            assertEquals(List.of((new Person(i, "Name" + i, "Address" + i, "Phone" + i))),
                database.findByName("Name" + i));

            assertEquals(List.of((new Person(i, "Name" + i, "Address" + i, "Phone" + i))),
                database.findByAddress("Address" + i));

            assertEquals(List.of((new Person(i, "Name" + i, "Address" + i, "Phone" + i))),
                database.findByPhone("Phone" + i));
        }

        executorService.shutdown();
    }
}
