package com.adidas.producer.publisher.productaddedtocart;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProductAddedToCartOutput {

    String PRODUCT_ADDED_TO_CART_OUTPUT_CHANNEL = "productAddedToCartOutputChannel";

    @Output(PRODUCT_ADDED_TO_CART_OUTPUT_CHANNEL)
    MessageChannel productAddedToCartOutputChannel();
}

