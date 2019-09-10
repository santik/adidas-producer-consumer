package com.adidas.producer.publisher.categoryviewed;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CategoryViewedOutput {

    String CATEGORY_VIEWED_OUTPUT_CHANNEL = "categoryViewedOutputChannel";

    @Output(CATEGORY_VIEWED_OUTPUT_CHANNEL)
    MessageChannel categoryViewedOutputChannel();
}

