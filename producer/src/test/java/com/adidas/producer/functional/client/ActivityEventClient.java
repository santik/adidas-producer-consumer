package com.adidas.producer.functional.client;

import com.adidas.generated.ActivityEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

/**
 * Pricing Service client.
 */
@Service
public class ActivityEventClient {

    private HttpClient client;

    @Autowired
    public ActivityEventClient(HttpClient client) {
        this.client = client;
    }

    public int sendActivityEvent(ActivityEvent activityEvent) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/activity/"))
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(activityEvent)))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<Stream<String>> response =
                client.send(request, HttpResponse.BodyHandlers.ofLines());

        return response.statusCode();
    }
}
