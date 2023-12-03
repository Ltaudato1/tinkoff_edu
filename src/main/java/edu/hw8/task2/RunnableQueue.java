package edu.hw8.task2;

import java.util.LinkedList;
import java.util.Queue;

public class RunnableQueue {
    private final Queue<Runnable> queue;

    public RunnableQueue() {
        this.queue = new LinkedList<>();
    }

    public synchronized void enqueue(Runnable runnable) {
        queue.offer(runnable);
        notify();
    }

    public synchronized Runnable dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return queue.poll();
    }
}

