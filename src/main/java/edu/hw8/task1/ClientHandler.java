package edu.hw8.task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private static final int KB = 1024;
    List<String> quotes = List.of(
        "Не переходи на личности там, где их нет",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "Чем ниже интеллект, тем громче оскорбления"
    );

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {

            ByteBuffer buffer = ByteBuffer.allocate(KB);
            int bytesRead = inputStream.read(buffer.array());

            if (bytesRead != -1) {
                String userRequest = StandardCharsets.UTF_8.decode(buffer).toString().trim();


                for (String quote : quotes) {
                    if (quote.contains(userRequest)) {
                        byte[] responseBytes = quote.getBytes(StandardCharsets.UTF_8);
                        outputStream.write(responseBytes);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to process the request");
        }
    }
}
