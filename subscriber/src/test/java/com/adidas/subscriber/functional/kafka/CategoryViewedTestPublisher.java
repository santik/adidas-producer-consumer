package com.adidas.subscriber.functional.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding({KafkaChannels.class})
public class CategoryViewedTestPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryViewedTestPublisher.class);
    private final MessageChannel categoryViewedOutputChannel;

    @Autowired
    public CategoryViewedTestPublisher(MessageChannel categoryViewedOutputChannel) {
        this.categoryViewedOutputChannel = categoryViewedOutputChannel;
    }

    public void publish(Object message) {
        LOGGER.info("Publishing to categoryViewedOutputChannel channel. Message: {}", message.toString());
        categoryViewedOutputChannel.send(MessageBuilder.withPayload(message).build());
    }
}
