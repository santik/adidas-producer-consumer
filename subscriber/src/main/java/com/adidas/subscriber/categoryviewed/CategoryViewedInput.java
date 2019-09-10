package com.adidas.subscriber.categoryviewed;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface CategoryViewedInput {

    String CATEGORY_VIEWED_INPUT_CHANNEL = "categoryViewedInputChannel";

    @Input(CATEGORY_VIEWED_INPUT_CHANNEL)
    MessageChannel categoryViewedInputChannel();
}

