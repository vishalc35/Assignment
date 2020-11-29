package supermetrics.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import supermetrics.client.model.Page;
import supermetrics.client.model.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MetricsTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public MetricsTest() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void get() throws JsonProcessingException {
        var PAGE1_BODY = "{\"meta\": {\"request_id\": \"cI27RvR5_pAHPjVyaAo5Mr4Hyhe7FhZp\"},\"data\": {\"page\": 1,\"posts\": [{\"id\": \"post5fbfa7486a8b2_c70f0e5e\",\"from_name\": \"Macie Mckamey\",\"from_id\": \"user_11\",\"message\": \"galaxy drag speed credit card agriculture beautiful\",\"type\": \"status\",\"created_time\": \"2020-11-26T07:19:21+00:00\"},{\"id\": \"post5fbfa7486a8c5_59145459\",\"from_name\": \"Woodrow Lindholm\",\"from_id\": \"user_14\",\"message\": \"graze instal train mastermind timetable dirty conductor alcohol surround\",\"type\": \"status\",\"created_time\": \"2020-11-26T03:00:33+00:00\"},{\"id\": \"post5fbfa7486a8cd_baca2b7a\",\"from_name\": \"Nydia Croff\",\"from_id\": \"user_2\",\"message\": \"album size rob\",\"type\": \"status\",\"created_time\": \"2020-12-25T21:48:19+00:00\"}]}}";

        SuperMetricsClient client = Mockito.mock(SuperMetricsClient.class);
        Mockito.when(client.fetchPosts(1))
                .thenReturn(objectMapper.readValue(PAGE1_BODY, new TypeReference<Response<Page>>() {
                        }).getData()
                );
        Metrics metrics = new Metrics(client);
        assertEquals(
                "{\"monthStat\":{\"11/2020\":{\"averageMessageLength\":61.5,\"longestPostId\":\"post5fbfa7486a8c5_59145459\",\"countOfPostsByUser\":{\"user_14\":1,\"user_11\":1}},\"12/2020\":{\"averageMessageLength\":14.0,\"longestPostId\":\"post5fbfa7486a8cd_baca2b7a\",\"countOfPostsByUser\":{\"user_2\":1}}},\"countOfPostsByWeek\":{\"48\":2,\"52\":1}}",
                metrics.getMetrics(1)
        );
    }
}