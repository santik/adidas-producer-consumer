package com.adidas.subscriber.productaddedtocart;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface ProductAddedToCartInput {

    String PRODUCT_ADDED_TO_CART_INPUT_CHANNEL = "productAddedToCartInputChannel";

    @Input(PRODUCT_ADDED_TO_CART_INPUT_CHANNEL)
    MessageChannel productAddedToCartInputChannel();
}

