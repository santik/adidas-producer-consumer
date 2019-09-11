package com.adidas.subscriber.redis;

import com.adidas.generated.ProductUserViewed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
public class RedisConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfiguration.class);

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.timeout.miliseconds}")
    private long redisTimeout;

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        LOGGER.info("redishost given to connection factory {} ", redisHost);
        LOGGER.info("redisport given to connection factory {} ", redisPort);
        LOGGER.info("redistimeout given to connection factory {} ", redisTimeout);
        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
        LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder()
                .commandTimeout(Duration.of(redisTimeout, ChronoUnit.MILLIS))
                .build();
        return new LettuceConnectionFactory(redisConfiguration, clientConfiguration);
    }
}

