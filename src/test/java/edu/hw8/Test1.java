package edu.hw8;

import edu.hw8.task1.QuoteFinder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test1 {
    @Test
    public void sampleTestForServerResponse() {
        InputStream originalSystemIn = System.in;
        PrintStream originalSystemOut = System.out;

        try {
            String userInput = "личности";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
            System.setIn(inputStream);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);

            Thread serverThread = new Thread(() -> {
                QuoteFinder quoteFinder = new QuoteFinder();
                quoteFinder.init();
            });

            serverThread.start();

            Thread.sleep(5000);

            String serverResponse = sendRequestAndGetResponse("localhost", 10, "личности");

            assertEquals("Не переходи на личности там, где их нет", serverResponse);

        } catch (Exception e) {
            fail("Exception thrown");
        } finally {
            System.setIn(originalSystemIn);
            System.setOut(originalSystemOut);
        }
    }

    private String sendRequestAndGetResponse(String host, int port, String request) {
        try (Socket socket = new Socket(host, port);
             PrintStream printStream = new PrintStream(socket.getOutputStream(), true);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             InputStream inputStream = socket.getInputStream()) {

            printStream.println(request);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            return byteArrayOutputStream.toString(StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
