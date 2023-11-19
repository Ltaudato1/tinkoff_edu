package edu.hw6;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private HackerNews() {

    }

    public static long[] hackerNewsTopStories() {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
                .GET()
                .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            String[] idStrings = response.body().replaceAll("[\\[\\]\"]", "").split(",");
            long[] ids = Arrays.stream(idStrings)
                .mapToLong(Long::parseLong)
                .toArray();

            return ids;
        } catch (Exception e) {
            return new long[]{};
        }
    }

    public static String news(long storyId) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/" + storyId + ".json"))
            .GET()
            .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            Pattern pattern = Pattern.compile("\"title\"\\s*:\\s*\"([^\"]+)\"");
            Matcher matcher = pattern.matcher(json);

            if (matcher.find()) {
                return matcher.group(1);
            } else {
                return "Title not found";
            }
        } catch (Exception e) {
            return "Error";
        }
    }
}
