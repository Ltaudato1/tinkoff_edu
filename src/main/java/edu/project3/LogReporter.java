package edu.project3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

@SuppressWarnings("MultipleStringLiterals")
public class LogReporter {
    private LogReporter() {

    }

    static Map<String, String> codes = Map.ofEntries(
        Map.entry("200", "OK"),
        Map.entry("404", "Not found"),
        Map.entry("500", "Internal server error"),
        Map.entry("400", "Bad Request")
    );

    public static void markdownPrint(Path filePath, Map<String, Integer> info, String header, String[] fileData)
        throws IOException {
        StringBuilder tableBuilder = new StringBuilder();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString(), true))) {
            tableBuilder.append("#### " + header + "\n");
            if (header.equals("Общая информация")) {
                tableBuilder.append("|        Метрика        |      Значение      |\n");
                tableBuilder.append("|:---------------------:|--------------------:|\n");
                tableBuilder.append("|        Файл(ы)        |  ").append(fileData[0]).append("  |\n");
                tableBuilder.append("|     Начальная дата    |  ").append(fileData[1]).append("  |\n");
                tableBuilder.append("|     Конечная дата     |  ").append(fileData[2]).append("  |\n");
                tableBuilder.append("|  Количество запросов  |  ").append(info.get("Total requests")).append("  |\n");
                tableBuilder.append("| Средний размер ответа |  ")
                    .append(info.get("Average answer size"))
                    .append("  |\n");

            } else if (header.equals("Запрашиваемые ресурсы")) {
                tableBuilder.append("|      Ресурс     | Количество |\n");
                tableBuilder.append("|:---------------:|-----------:|\n");
                for (String key : info.keySet()) {
                    tableBuilder.append("|  ").append(key).append("  |  ").append(info.get(key)).append("  |\n");
                }

            } else if (header.equals("Коды ответа")) {
                tableBuilder.append("| Код |          Имя          | Количество |\n");
                tableBuilder.append("|:---:|:---------------------:|-----------:|\n");
                for (String key : info.keySet()) {
                    tableBuilder.append("|  ").append(key).append("  |  ")
                        .append(codes.getOrDefault(key, "N/A")).append("  |  ")
                        .append(info.get(key)).append("  |\n");
                }
            }

            writer.write(tableBuilder.toString());
            writer.write("\n");
        }
    }



    public static void adocPrint(Path filePath, Map<String, Integer> info, String header, String[] fileData) {
        StringBuilder tableBuilder = new StringBuilder();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString(), true))) {
            tableBuilder.append("#### " + header + "\n");
            if (header.equals("Общая информация")) {
                tableBuilder.append("|        Метрика        |      Значение      |\n");
                tableBuilder.append("|=======================|====================|\n");
                tableBuilder.append("|        Файл(ы)        |  ").append(fileData[0]).append("  |\n");
                tableBuilder.append("|     Начальная дата    |  ").append(fileData[1]).append("  |\n");
                tableBuilder.append("|     Конечная дата     |  ").append(fileData[2]).append("  |\n");
                tableBuilder.append("|  Количество запросов  |  ").append(info.get("TotalRequests")).append("  |\n");
                tableBuilder.append("| Средний размер ответа |  ")
                    .append(info.get("Average answer size")).append("  |\n");
                tableBuilder.append("|=======================|====================|\n");

            } else if (header.equals("Запрашиваемые ресурсы")) {
                tableBuilder.append("|      Ресурс     | Количество |\n");
                tableBuilder.append("|=================|============|\n");
                for (String key : info.keySet()) {
                    tableBuilder.append("|  ").append(key).append("  |  ").append(info.get(key)).append("  |\n");
                }
                tableBuilder.append("|=================|============|\n");

            } else if (header.equals("Коды ответа")) {
                tableBuilder.append("| Код |          Имя          | Количество |\n");
                tableBuilder.append("|=====|=======================|============|\n");
                for (String key : info.keySet()) {
                    tableBuilder.append("|  ").append(key).append("  |  ")
                        .append(codes.getOrDefault(key, "N/A")).append("  |  ")
                        .append(info.get(key)).append("  |\n");
                }
                tableBuilder.append("|=====|=======================|============|\n");
            }

            writer.write(tableBuilder.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }
}
