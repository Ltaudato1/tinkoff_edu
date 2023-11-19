package edu.project3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("MultipleStringLiterals")
public class NginxLogStats {
    Map<String, Integer> statistics = new LinkedHashMap<>();
    Map<String, Integer> codeStatistics = new LinkedHashMap<>();
    Map<String, Integer> resourceStatistics = new LinkedHashMap<>();
    private static final LocalDateTime BASIC_TO_TIME = LocalDateTime.of(9999, 12, 31, 23, 59);
    private static final LocalDateTime BASIC_FROM_TIME = LocalDateTime.of(1, 1, 1, 0, 0);

    public NginxLogStats() {

    }

    public void sendRequest(String[] args) throws IOException {
        try {
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String logPath = args[1];
            LocalDateTime from = BASIC_FROM_TIME;
            LocalDateTime to = BASIC_TO_TIME;
            String format = "markdown";
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "--path":
                        logPath = args[i + 1];
                        break;
                    case "--from":
                        from = LocalDateTime.parse(args[i + 1], formatter);
                        break;
                    case "--to":
                        to = LocalDateTime.parse(args[i + 1], formatter);
                        break;
                    case "--format":
                        format = args[i + 1];
                        break;
                    default:
                        continue;
                }
            }
            String[] fileData = {logPath, from.toString(), to.toString()};

            processLogFile(Paths.get(logPath), from, to);

            statistics = statistics.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    LinkedHashMap::new
                ));
            codeStatistics = codeStatistics.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    LinkedHashMap::new
                ));
            resourceStatistics = resourceStatistics.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    LinkedHashMap::new
                ));

            displayStatistics(format, fileData);
        } catch (IOException e) {
            return;
        }
    }

    private void displayStatistics(String format, String[] fileData) throws IOException {
        if (format.equals("markdown")) {
            LogReporter.markdownPrint(Paths.get("src/test/java/edu/project3/output.md"),
                statistics, "Общая информация", fileData);

            LogReporter.markdownPrint(Paths.get("src/test/java/edu/project3/output.md"),
                resourceStatistics, "Запрашиваемые ресурсы", fileData);

            LogReporter.markdownPrint(Paths.get("src/test/java/edu/project3/output.md"),
                codeStatistics, "Коды ответа", fileData);
        } else {
            LogReporter.adocPrint(Paths.get("src/test/java/edu/project3/output.adoc"),
                statistics, "Общая информация", fileData);

            LogReporter.adocPrint(Paths.get("src/test/java/edu/project3/output.adoc"),
                resourceStatistics, "Запрашиваемые ресурсы", fileData);

            LogReporter.adocPrint(Paths.get("src/test/java/edu/project3/output.adoc"),
                codeStatistics, "Коды ответа", fileData);
        }
    }

    private void processLogFile(Path file, LocalDateTime from, LocalDateTime to) {
        try {
            for (String line : Files.lines(file).toList()) {
                processLogEntry(line, from, to);
            }
        } catch (IOException e) {
            return;
        }
    }

    // Обработка каждой строки по отдельности
    private void processLogEntry(String logEntry, LocalDateTime from, LocalDateTime to) {
        final int INDEX_OF_DATE = 3;
        final int INDEX_OF_BYTES = 8;
        final int INDEX_OF_CODE = 9;
        final int INDEX_OF_RESOURCE = 11;

        final DateTimeFormatter logEntryFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
        String[] logParts = logEntry.split(" ");

        LocalDateTime dateTime = LocalDateTime.parse(logParts[INDEX_OF_DATE].substring(1), logEntryFormatter);
        if (dateTime.isBefore(from) || dateTime.isAfter(to)) {
            return;
        }

        // Общее количество строк
        statistics.put("Total requests", statistics.getOrDefault("Total requests", 0) + 1);

        // Средний размер ответа
        int bytesSent = Integer.parseInt(logParts[INDEX_OF_BYTES]);
        Integer averageBytes = ((statistics.getOrDefault("Average answer size", 0)
            * (statistics.getOrDefault("Total requests", 1) - 1)) + bytesSent)
            / statistics.getOrDefault("Total requests", 1);

        statistics.put("Average answer size", averageBytes);

        // Вхождения разных кодов ответа
        String statusCode = logParts[INDEX_OF_CODE];
        codeStatistics.put(statusCode, codeStatistics.getOrDefault(statusCode, 0) + 1);

        // Запрашиваемые ресурсы
        String request = logParts[INDEX_OF_RESOURCE].replaceAll("\"", "");
        resourceStatistics.put(request, resourceStatistics.getOrDefault(request, 0) + 1);
    }

}
