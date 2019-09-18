package com.adidas.subscriber.kafka;

import com.adidas.generated.ProductUserViewed;
import com.adidas.subscriber.processor.ProductUserViewedProcessor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import static com.adidas.subscriber.kafka.KafkaChannels.PRODUCT_VIEWED_INPUT_CHANNEL;

@EnableBinding(KafkaChannels.class)
public class ProductViewedConsumer implements Consumer<ProductUserViewed> {

    private ProductUserViewedProcessor processor;

    public ProductViewedConsumer(ProductUserViewedProcessor processor) {
        this.processor = processor;
    }

    @Override
    @StreamListener(PRODUCT_VIEWED_INPUT_CHANNEL)
    public void consume(Message<ProductUserViewed> message) {
        processor.process(message.getPayload());
    }
}
