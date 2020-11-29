package supermetrics.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import supermetrics.client.model.Page;
import supermetrics.client.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Base class to find monthly user metrics.
 */

public class Metrics {

    private final SuperMetricsClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static int PAGES_TO_FETCH = 10;

    public Metrics() {
        this(new SuperMetricsClient());
    }

    public Metrics(SuperMetricsClient client) {
        this.client = client;
    }

    public static void main(String[] args) {
        Metrics metrics = new Metrics();
        System.out.println(metrics.getMetrics(PAGES_TO_FETCH));
    }

    /**
     * @param upToPage end page till which we want to fetch posts.
     * @return output from API that gets JSON as String.
     */
    public String getMetrics(Integer upToPage) {
        List<Post> posts = new ArrayList<>();
        for (int pageNo = 1; pageNo <= upToPage; pageNo++) {
            Page page = client.fetchPosts(pageNo);
            posts.addAll(page.getPosts());
        }
        PostSummary summary = new PostSummary();
        summary.setMonthStat(getMonthStat(posts));
        summary.setUsersAvgPostsByMonth(getUsersAvgPostsByMonth(posts));
        summary.setCountOfPostsByWeek(getWeekStat(posts));

        try {
            return objectMapper.writeValueAsString(summary);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")

    private Map<String, MonthStat> getMonthStat(List<Post> posts) {
        return posts
                .stream()
                .collect(Collectors.groupingBy(
                        Post::getMonth,
                        MonthStatCollector.byMonth()
                ));
    }

    private Map<String, Long> getUsersAvgPostsByMonth(List<Post> posts) {
        return posts
                .stream()
                .collect(Collectors.groupingBy(
                        Post::getFromId
                ))
                .entrySet()
                .stream()
                .map(entry -> Map.entry(
                        entry.getKey(),
                        (long) entry.getValue().size() /
                                entry.getValue().stream().map(Post::getMonth).distinct().count()
                ))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    private Map<Integer, Long> getWeekStat(List<Post> posts) {
        return posts
                .stream()
                .collect(Collectors.groupingBy(
                        Post::getWeek,
                        Collectors.counting()
                ));
    }
}
