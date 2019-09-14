package com.adidas.producer.functional;

import com.adidas.generated.ActivityEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

@Service
public class ActivityEventClient {

    private static final String API_ENDPOINT = "http://localhost:8080/activity/";

    public int sendActivityEvent(ActivityEvent activityEvent) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_ENDPOINT))
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(activityEvent)))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<Stream<String>> response =
                client.send(request, HttpResponse.BodyHandlers.ofLines());

        return response.statusCode();
    }
}
