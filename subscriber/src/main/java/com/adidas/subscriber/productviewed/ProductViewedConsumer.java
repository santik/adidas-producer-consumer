package com.adidas.subscriber.productviewed;

import com.adidas.generated.ProductUserViewed;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import static com.adidas.subscriber.productviewed.ProductViewedInput.PRODUCT_VIEWED_INPUT_CHANNEL;

@EnableBinding(ProductViewedInput.class)
public class ProductViewedConsumer {

    private ProductUserViewedProcessor processor;

    public ProductViewedConsumer(ProductUserViewedProcessor processor) {
        this.processor = processor;
    }

    @StreamListener(PRODUCT_VIEWED_INPUT_CHANNEL)
    public void consume(Message<ProductUserViewed> message) {
        processor.process(message.getPayload());
    }
}
