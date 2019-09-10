package com.adidas.producer.publisher;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface KafkaChannels {

    String CATEGORY_VIEWED_OUTPUT_CHANNEL = "categoryViewedOutputChannel";
    String PRODUCT_ADDED_TO_CART_OUTPUT_CHANNEL = "productAddedToCartOutputChannel";
    String PRODUCT_VIEWED_OUTPUT_CHANNEL = "productViewedOutputChannel";

    @Output(CATEGORY_VIEWED_OUTPUT_CHANNEL)
    MessageChannel categoryViewedOutputChannel();

    @Output(PRODUCT_ADDED_TO_CART_OUTPUT_CHANNEL)
    MessageChannel productAddedToCartOutputChannel();

    @Output(PRODUCT_VIEWED_OUTPUT_CHANNEL)
    MessageChannel productViewedOutputChannel();
}
