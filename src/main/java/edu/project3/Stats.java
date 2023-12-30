package edu.project3;

import java.util.Map;

public record Stats(Map<AverageStatistics, Integer> statistics,
                    Map<HttpStatusCodes, Integer> codeStatistics,
                    Map<String, Integer> resourceStatistics) {
}
