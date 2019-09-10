package com.adidas.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.integration.annotation.IntegrationComponentScan;

@IntegrationComponentScan
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Producer {
    public static void main(String[] args) {
        SpringApplication.run(Producer.class, args);
    }
}
