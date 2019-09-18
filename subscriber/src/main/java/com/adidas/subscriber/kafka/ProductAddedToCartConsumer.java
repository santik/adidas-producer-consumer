package com.adidas.subscriber.kafka;

import com.adidas.generated.ProductUserAddedToCart;
import com.adidas.subscriber.processor.ProductAddedToCartProcessor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import static com.adidas.subscriber.kafka.KafkaChannels.PRODUCT_ADDED_TO_CART_INPUT_CHANNEL;

@EnableBinding(KafkaChannels.class)
public class ProductAddedToCartConsumer implements Consumer<ProductUserAddedToCart> {

    private ProductAddedToCartProcessor processor;

    public ProductAddedToCartConsumer(ProductAddedToCartProcessor processor) {
        this.processor = processor;
    }

    @Override
    @StreamListener(PRODUCT_ADDED_TO_CART_INPUT_CHANNEL)
    public void consume(Message<ProductUserAddedToCart> message) {
        processor.process(message.getPayload());
    }
}
