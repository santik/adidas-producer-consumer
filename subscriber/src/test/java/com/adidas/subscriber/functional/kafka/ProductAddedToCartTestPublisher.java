package com.adidas.subscriber.functional.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding({KafkaChannels.class})
public class ProductAddedToCartTestPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductAddedToCartTestPublisher.class);
    private final MessageChannel productAddedToCartOutputChannel;

    @Autowired
    public ProductAddedToCartTestPublisher(MessageChannel productAddedToCartOutputChannel) {
        this.productAddedToCartOutputChannel = productAddedToCartOutputChannel;
    }

    public void publish(Object message) {
        LOGGER.info("Publishing to productAddedToCartOutputChannel channel. Message: {}", message.toString());
        productAddedToCartOutputChannel.send(MessageBuilder.withPayload(message).build());
    }
}
