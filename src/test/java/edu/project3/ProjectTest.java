package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import static edu.project3.AverageStatistics.AVERAGE_ANSWER_SIZE;
import static edu.project3.AverageStatistics.TOTAL_REQUESTS;
import static edu.project3.HttpStatusCodes.N_A;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class ProjectTest {
    @Test
    @DisplayName("Sample test (adoc)")
    void testAdoc() {
        try {
            NginxLogStats logStats = new NginxLogStats();
            String[] args = {"--path", "src/test/java/edu/project3/nginx_logs.txt", "--format", "adoc"};
            logStats.sendRequest(args);
            assertFalse(logStats.statistics.isEmpty() || logStats.resourceStatistics.isEmpty()
                || logStats.codeStatistics.isEmpty()
                || !new File("src/test/java/edu/project3/output.adoc").exists());
            try (FileWriter writer = new FileWriter("src/test/java/edu/project3/output.adoc")) { // Очистка
                return;
            }
        } catch(IOException e) {
            fail();
        }
    }

    @Test
    @DisplayName("Sample test (md)")
    void testMarkdown() {
        try {
            NginxLogStats logStats = new NginxLogStats();
            String[] args = {"--path", "src/test/java/edu/project3/nginx_logs.txt", "--format", "markdown"};
            logStats.sendRequest(args);
            assertFalse(logStats.statistics.isEmpty() || logStats.resourceStatistics.isEmpty()
                || logStats.codeStatistics.isEmpty()
                || !new File("src/test/java/edu/project3/output.md").exists());
            System.out.println(logStats.statistics);
            try (FileWriter writer = new FileWriter("src/test/java/edu/project3/output.md")) { // Очистка
                return;
            }
        } catch(IOException e) {
            fail();
        }
    }

    @Test
    @DisplayName("Test for correct stats")
    void testForStats() throws IOException {
        NginxLogStats logStats = new NginxLogStats();
        String[] args = {"--path", "src/test/java/edu/project3/nginx_logs.txt", "--format", "markdown"};
        logStats.sendRequest(args);

        // Average stats
        assertEquals(Map.of(
            TOTAL_REQUESTS, 51462,
            AVERAGE_ANSWER_SIZE, 200),
            logStats.statistics);

        // Code stats
        assertEquals(Map.of(
            N_A, 51462
        ), logStats.codeStatistics);

        // Resource stats (слишком много, поэтому проверим 2-3 поля)
        assertEquals(logStats.resourceStatistics.get("Debian"), 47801);
        assertEquals(logStats.resourceStatistics.get("Chef"), 969);
        assertEquals(logStats.resourceStatistics.get("Ubuntu"), 46);

        try (FileWriter writer = new FileWriter("src/test/java/edu/project3/output.md")) { // Очистка
            return;
        } // очистка
    }

    @Test
    @DisplayName("Test for correct argument reading")
    void testForCorrectRequest() throws IOException {
        NginxLogStats logStats = new NginxLogStats();
        String[] args = {"--path", "src/test/java/edu/project3/nginx_logs.txt", "--format", "markdown"};
        logStats.sendRequest(args);

        String[] rightArguments = new String[]{"src/test/java/edu/project3/nginx_logs.txt",
            LocalDateTime.of(1, 1, 1, 0, 0).toString(),
            LocalDateTime.of(9999, 12, 31, 23, 59).toString(),
            "markdown"};

        for (int i = 0; i < rightArguments.length; ++i) {
            assertEquals(rightArguments[i], logStats.arguments[i]);
        }

        try (FileWriter writer = new FileWriter("src/test/java/edu/project3/output.md")) { // Очистка
            return;
        } // очистка
    }
}
