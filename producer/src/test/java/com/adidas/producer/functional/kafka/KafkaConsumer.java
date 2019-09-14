package com.adidas.producer.functional.kafka;

import com.adidas.generated.CategoryUserViewed;
import com.adidas.generated.ProductUserAddedToCart;
import com.adidas.generated.ProductUserViewed;
import com.adidas.producer.functional.TestingSession;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import static com.adidas.producer.functional.kafka.KafkaChannels.CATEGORY_VIEWED_INPUT_CHANNEL;
import static com.adidas.producer.functional.kafka.KafkaChannels.PRODUCT_ADDED_TO_CART_INPUT_CHANNEL;
import static com.adidas.producer.functional.kafka.KafkaChannels.PRODUCT_VIEWED_INPUT_CHANNEL;

@EnableBinding(KafkaChannels.class)
public class KafkaConsumer {

    @StreamListener(CATEGORY_VIEWED_INPUT_CHANNEL)
    public void consumeCategoryViewed(Message<CategoryUserViewed> message) {
        TestingSession.getInstance().putMessage(CATEGORY_VIEWED_INPUT_CHANNEL, message.getPayload());
    }

    @StreamListener(PRODUCT_ADDED_TO_CART_INPUT_CHANNEL)
    public void consumeProductAddedToCart(Message<ProductUserAddedToCart> message) {
        TestingSession.getInstance().putMessage(PRODUCT_ADDED_TO_CART_INPUT_CHANNEL, message.getPayload());
    }

    @StreamListener(PRODUCT_VIEWED_INPUT_CHANNEL)
    public void consumeProductViewed(Message<ProductUserViewed> message) {
        TestingSession.getInstance().putMessage(PRODUCT_VIEWED_INPUT_CHANNEL, message.getPayload());
    }
}
