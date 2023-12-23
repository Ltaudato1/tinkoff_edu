package edu.hw8.task2;

public class FixedThreadPool implements ThreadPool {
    private final int nThreads;
    private final Thread[] threads;
    private final RunnableQueue taskQueue;

    private FixedThreadPool(int nThreads) {
        this.nThreads = nThreads;
        this.threads = new Thread[nThreads];
        this.taskQueue = new RunnableQueue();

        for (int i = 0; i < nThreads; i++) {
            threads[i] = new Thread(new Worker());
        }
    }

    public static FixedThreadPool create(int nThreads) {
        if (nThreads <= 0) {
            throw new IllegalArgumentException("Invalid number of threads");
        } else {
            return new FixedThreadPool(nThreads);
        }
    }

    @Override
    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        taskQueue.enqueue(runnable);
    }

    @Override
    public void close() throws Exception {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public static int fibonacciCalculate(int n) {
        if (n < 2) {
            return n;
        }
        return fibonacciCalculate(n - 1) + fibonacciCalculate(n - 2);
    }



    public class Worker implements Runnable {
        private Worker() {

        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    Runnable task = taskQueue.dequeue();
                    task.run();
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
