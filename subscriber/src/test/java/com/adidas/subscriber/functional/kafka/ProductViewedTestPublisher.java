package com.adidas.subscriber.functional.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding({KafkaChannels.class})
public class ProductViewedTestPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductViewedTestPublisher.class);
    private final MessageChannel productViewedOutputChannel;

    @Autowired
    public ProductViewedTestPublisher(MessageChannel productViewedOutputChannel) {
        this.productViewedOutputChannel = productViewedOutputChannel;
    }

    public void publish(Object message) {
        LOGGER.info("Publishing to productViewedOutputChannel channel. Message: {}", message.toString());
        productViewedOutputChannel.send(MessageBuilder.withPayload(message).build());
    }
}
