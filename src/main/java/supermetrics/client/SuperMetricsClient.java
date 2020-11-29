package supermetrics.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import supermetrics.client.modal.Auth;
import supermetrics.client.modal.Page;
import supermetrics.client.modal.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * SuperMetrics client to establish connection and fetch posts
 *
 */
public class SuperMetricsClient {

    public static final String URL_SUPER_METRICS_AUTH = "https://api.supermetrics.com/assignment/register";
    public static final String URL_SUPER_METRICS_POSTS = "https://api.supermetrics.com/assignment/posts";
    private final HttpClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Response<Auth> auth = null;

    public SuperMetricsClient() {
        this.client = HttpClient.newHttpClient();
        objectMapper.registerModule(new JavaTimeModule());
        auth();
        if (auth == null) {
            throw new RuntimeException("Unable to authentication");
        }
    }

    public Page fetchPosts(Integer page) {
        return doGet(
                URL_SUPER_METRICS_POSTS + "?sl_token=" + auth.getData().getToken() + "&page=" + page,
                new TypeReference<Response<Page>>() {
                })
                .getData();
    }

    private void auth() {
        var params = new HashMap<String, String>();
        params.put("client_id", "ju16a6m81mhid5ue1z3v2g0uh");
        params.put("email", "vishalc35@gmail.com");
        params.put("name", "Vishal");
        this.auth = doPost(URL_SUPER_METRICS_AUTH, params, new TypeReference<Response<Auth>>() {
        });
    }

    public <T> Response<T> doGet(String url, TypeReference<Response<T>> tr) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .headers("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .GET()
                .build();
        return doFetch(request, tr);
    }

    public  <T> Response<T> doPost(String url, Map<String, String> params, TypeReference<Response<T>> tr) {
        var sJoiner = new StringJoiner("&");
        for (var entry : params.entrySet()) {
            sJoiner.add(URLEncoder.encode(entry.getKey(), UTF_8) + "="
                    + URLEncoder.encode(entry.getValue(), UTF_8));
        }
        HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofByteArray(sJoiner.toString().getBytes(UTF_8));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .headers("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .POST(body)
                .build();
        return doFetch(request, tr);
    }

    private <T> Response<T> doFetch(HttpRequest request, TypeReference<Response<T>> tr) {
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == HttpURLConnection.HTTP_OK) {
                return objectMapper.readValue(response.body(), tr);
            } else {
                throw new RuntimeException("unexpected status code:" + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
