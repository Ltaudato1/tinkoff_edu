package edu.hw8.task1;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class QuoteFinder {
    private static final int PORT = 8080;
    private static final int THREADS = 4;
    private static final int MAX_CONNECTIONS = 10;
    private final Semaphore semaphore = new Semaphore(MAX_CONNECTIONS);

    public void init() {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                semaphore.acquire();

                Socket clientSocket = serverSocket.accept();

                executorService.execute(new ClientHandler(clientSocket, semaphore));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to server");
        } finally {
            executorService.shutdown();
        }
    }
}
