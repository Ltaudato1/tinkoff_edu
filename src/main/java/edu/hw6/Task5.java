package edu.hw6;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task5 {
    private Task5() {

    }

    private static final Pattern PATTERN = Pattern.compile("\"title\"\\s*:\\s*\"([^\"]+)\"");

    public static long[] hackerNewsTopStories() {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
                .GET()
                .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            String[] idStrings = response.body().replaceAll("[\\[\\]\"]", "").split(",");

            return Arrays.stream(idStrings)
                .mapToLong(Long::parseLong)
                .toArray();
        } catch (Exception e) {
            return new long[]{};
        }
    }

    public static String news(long storyId) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/" + storyId + ".json"))
            .GET()
            .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            Matcher matcher = PATTERN.matcher(json);

            if (matcher.find()) {
                return matcher.group(1);
            } else {
                return "Title not found";
            }
        } catch (Exception e) {
            throw new Exception("Failed to write news titles");
        }
    }
}
