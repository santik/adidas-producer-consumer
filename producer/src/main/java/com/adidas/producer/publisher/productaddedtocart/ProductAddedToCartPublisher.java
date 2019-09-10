package com.adidas.producer.publisher.productaddedtocart;

import com.adidas.generated.ProductUserAddedToCart;
import com.adidas.producer.publisher.ActivityEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import static com.adidas.producer.publisher.productaddedtocart.ProductAddedToCartOutput.PRODUCT_ADDED_TO_CART_OUTPUT_CHANNEL;


@EnableBinding({ProductAddedToCartOutput.class})
public class ProductAddedToCartPublisher implements ActivityEventPublisher<ProductUserAddedToCart> {
    private final MessageChannel productAddedToCartOutputChannel;

    @Autowired
    public ProductAddedToCartPublisher(@Qualifier(PRODUCT_ADDED_TO_CART_OUTPUT_CHANNEL) MessageChannel productAddedToCartOutputChannel) {
        this.productAddedToCartOutputChannel = productAddedToCartOutputChannel;

    }

    @Override
    public void publish(ProductUserAddedToCart productUserAddedToCart) {
        productAddedToCartOutputChannel.send(MessageBuilder.withPayload(productUserAddedToCart).build());
    }
}
