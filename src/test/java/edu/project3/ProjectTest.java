package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
            try (FileWriter writer = new FileWriter("src/test/java/edu/project3/output.md")) { // Очистка
                return;
            }
        } catch(IOException e) {
            fail();
        }
    }
}
