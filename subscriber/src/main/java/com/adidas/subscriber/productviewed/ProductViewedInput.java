package com.adidas.subscriber.productviewed;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface ProductViewedInput {

    String PRODUCT_VIEWED_INPUT_CHANNEL = "productViewedInputChannel";

    @Input(PRODUCT_VIEWED_INPUT_CHANNEL)
    MessageChannel productViewedInputChannel();
}

