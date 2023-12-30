package edu.project3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

@SuppressWarnings("MultipleStringLiterals")
public class LogReporter {
    private LogReporter() {

    }

    public static void markdownPrint(Path filePath, Stats info, String[] fileData)
        throws IOException {
        StringBuilder tableBuilder = new StringBuilder();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString()))) {
            tableBuilder.append("#### Общая информация\n");
            tableBuilder.append("|        Метрика        |      Значение      |\n");
            tableBuilder.append("|:---------------------:|--------------------:|\n");
            tableBuilder.append("|        Файл(ы)        |  ").append(fileData[0]).append("  |\n");
            tableBuilder.append("|     Начальная дата    |  ").append(fileData[1]).append("  |\n");
            tableBuilder.append("|     Конечная дата     |  ").append(fileData[2]).append("  |\n");
            tableBuilder.append("|  Количество запросов  |  ")
                .append(info.statistics().get(AverageStatistics.TOTAL_REQUESTS)).append("  |\n");
            tableBuilder.append("| Средний размер ответа |  ")
                .append(info.statistics().get(AverageStatistics.AVERAGE_ANSWER_SIZE))
                .append("  |\n");

            tableBuilder.append("#### Запрашиваемые ресурсы\n");
            tableBuilder.append("|      Ресурс     | Количество |\n");
            tableBuilder.append("|:---------------:|-----------:|\n");
            for (String key : info.resourceStatistics().keySet()) {
                tableBuilder.append("|  ").append(key).append("  |  ").append(info.resourceStatistics().get(key))
                    .append("  |\n");
            }

            tableBuilder.append("#### Коды ответа\n");
            tableBuilder.append("| Код |          Имя          | Количество |\n");
            tableBuilder.append("|:---:|:---------------------:|-----------:|\n");
            for (HttpStatusCodes key : info.codeStatistics().keySet()) {
                tableBuilder.append("|  ").append(key).append("  |  ")
                    .append(key.getMessage()).append("  |  ")
                    .append(info.codeStatistics().get(key)).append("  |\n");
            }

            writer.write(tableBuilder.toString());
            writer.write("\n");
        } catch (IOException e) {
            throw new IOException("Failed to print stats");
        }
    }

    public static void adocPrint(Path filePath, Stats info, String[] fileData)
        throws IOException {
        StringBuilder tableBuilder = new StringBuilder();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString(), true))) {
            tableBuilder.append("|        Метрика        |      Значение      |\n");
            tableBuilder.append("|=======================|====================|\n");
            tableBuilder.append("|        Файл(ы)        |  ").append(fileData[0]).append("  |\n");
            tableBuilder.append("|     Начальная дата    |  ").append(fileData[1]).append("  |\n");
            tableBuilder.append("|     Конечная дата     |  ").append(fileData[2]).append("  |\n");
            tableBuilder.append("|  Количество запросов  |  ")
                .append(info.statistics().get(AverageStatistics.TOTAL_REQUESTS)).append("  |\n");
            tableBuilder.append("| Средний размер ответа |  ")
                .append(info.statistics().get(AverageStatistics.AVERAGE_ANSWER_SIZE)).append("  |\n");
            tableBuilder.append("|=======================|====================|\n");

            tableBuilder.append("|      Ресурс     | Количество |\n");
            tableBuilder.append("|=================|============|\n");
            for (String key : info.resourceStatistics().keySet()) {
                tableBuilder.append("|  ").append(key).append("  |  ")
                    .append(info.resourceStatistics().get(key)).append("  |\n");
            }
            tableBuilder.append("|=================|============|\n");

            tableBuilder.append("| Код |          Имя          | Количество |\n");
            tableBuilder.append("|=====|=======================|============|\n");
            for (HttpStatusCodes key : info.codeStatistics().keySet()) {
                tableBuilder.append("|  ").append(key).append("  |  ")
                    .append(key.getMessage()).append("  |  ")
                    .append(info.codeStatistics().get(key)).append("  |\n");
            }
            tableBuilder.append("|=====|=======================|============|\n");

            writer.write(tableBuilder.toString());
            writer.write("\n");
        } catch (IOException e) {
            throw new IOException("failed to print stats");
        }
    }
}
