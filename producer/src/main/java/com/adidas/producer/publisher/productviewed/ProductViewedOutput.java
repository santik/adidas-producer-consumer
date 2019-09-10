package com.adidas.producer.publisher.productviewed;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProductViewedOutput {

    String PRODUCT_VIEWED_OUTPUT_CHANNEL = "productViewedOutputChannel";

    @Output(PRODUCT_VIEWED_OUTPUT_CHANNEL)
    MessageChannel productViewedOutputChannel();
}

