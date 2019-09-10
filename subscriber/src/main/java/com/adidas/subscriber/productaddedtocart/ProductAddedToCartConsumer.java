package com.adidas.subscriber.productaddedtocart;

import com.adidas.generated.ProductUserAddedToCart;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import static com.adidas.subscriber.productaddedtocart.ProductAddedToCartInput.PRODUCT_ADDED_TO_CART_INPUT_CHANNEL;

@EnableBinding(ProductAddedToCartInput.class)
public class ProductAddedToCartConsumer {

    private ProductAddedToCartProcessor processor;

    public ProductAddedToCartConsumer(ProductAddedToCartProcessor processor) {
        this.processor = processor;
    }

    @StreamListener(PRODUCT_ADDED_TO_CART_INPUT_CHANNEL)
    public void consume(Message<ProductUserAddedToCart> message) {
        processor.process(message.getPayload());
    }
}
