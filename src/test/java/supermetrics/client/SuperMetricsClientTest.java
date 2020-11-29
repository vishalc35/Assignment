package supermetrics.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SuperMetricsClientTest {

    @Test
    void fetchPosts() {
        SuperMetricsClient client = new SuperMetricsClient();
        var page = client.fetchPosts(1);
        assertNotNull(page);
        assertNotNull(page.getPosts());
        assertFalse(page.getPosts().isEmpty());
    }
}