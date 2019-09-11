package com.adidas.subscriber.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface KafkaChannels {

    String CATEGORY_VIEWED_INPUT_CHANNEL = "categoryViewedInputChannel";
    String PRODUCT_ADDED_TO_CART_INPUT_CHANNEL = "productAddedToCartInputChannel";
    String PRODUCT_VIEWED_INPUT_CHANNEL = "productViewedInputChannel";

    @Input(CATEGORY_VIEWED_INPUT_CHANNEL)
    MessageChannel categoryViewedInputChannel();

    @Input(PRODUCT_ADDED_TO_CART_INPUT_CHANNEL)
    MessageChannel productAddedToCartInputChannel();

    @Input(PRODUCT_VIEWED_INPUT_CHANNEL)
    MessageChannel productViewedInputChannel();
}
