package com.adidas.subscriber.kafka.productviewed;

import com.adidas.generated.ProductUserViewed;
import com.adidas.subscriber.kafka.KafkaChannels;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import static com.adidas.subscriber.kafka.KafkaChannels.PRODUCT_VIEWED_INPUT_CHANNEL;

@EnableBinding(KafkaChannels.class)
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
