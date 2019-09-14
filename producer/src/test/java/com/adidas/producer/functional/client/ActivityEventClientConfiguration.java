package com.adidas.producer.functional.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class ActivityEventClientConfiguration {

    @Bean
    public HttpClient getClient() {

        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }
}
