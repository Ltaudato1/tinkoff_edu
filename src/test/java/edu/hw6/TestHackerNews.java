package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static edu.hw6.HackerNews.news;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestHackerNews {
    @Test
    @DisplayName("sample test")
    void test() throws IOException {
        long[] ids = HackerNews.hackerNewsTopStories();
        assertFalse(news(ids[12]).equals("Error") || news(ids[12]).equals("Title not found"));
    }
}
