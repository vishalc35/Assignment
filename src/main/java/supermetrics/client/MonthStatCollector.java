package supermetrics.client;

import supermetrics.client.modal.Post;

import java.util.stream.Collector;

/**
 * Utility class to aggregate data from posts
 */
public class MonthStatCollector {

    public static Collector<Post, MonthRecord, MonthStat> byMonth() {
        return Collector.of(
                MonthRecord::new,
                (result, post) -> {
                    result.totalPosts++;
                    result.totalMessageChars += post.getMessage().length();
                    result.longestPostId = post.getId();
                    result.messageSize = post.getMessage().length();
                },
                (result1, result2) -> {
                    result1.totalPosts += result2.totalPosts;
                    result1.totalMessageChars += result2.totalMessageChars;
                    if (result1.messageSize.compareTo(result2.messageSize) < 0) {
                        result1.longestPostId = result2.longestPostId;
                    }
                    return result1;
                },
                result -> {
                    var stat = new MonthStat();
                    stat.setAverageMessageLength((double) result.totalMessageChars / result.totalPosts);
                    stat.setLongestPostId(result.longestPostId);
                    return stat;
                }
        );
    }

    static class MonthRecord {

        Integer totalPosts = 0;
        Integer totalMessageChars = 0;
        String longestPostId = null;
        Integer messageSize = 0;

    }
}
