package com.adidas.subscriber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@IntegrationComponentScan
@SpringBootApplication
public class Subscriber {
    public static void main(String[] args) {
        SpringApplication.run(Subscriber.class, args);
    }
}
