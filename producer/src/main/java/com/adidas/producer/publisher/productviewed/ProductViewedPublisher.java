package com.adidas.producer.publisher.productviewed;

import com.adidas.generated.ProductUserViewed;
import com.adidas.producer.publisher.ActivityEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import static com.adidas.producer.publisher.productviewed.ProductViewedOutput.PRODUCT_VIEWED_OUTPUT_CHANNEL;

@EnableBinding({ProductViewedOutput.class})
public class ProductViewedPublisher implements ActivityEventPublisher<ProductUserViewed> {
    private final MessageChannel productViewedOutputChannel;

    @Autowired
    public ProductViewedPublisher(@Qualifier(PRODUCT_VIEWED_OUTPUT_CHANNEL) MessageChannel productViewedOutputChannel) {
        this.productViewedOutputChannel = productViewedOutputChannel;

    }

    @Override
    public void publish(ProductUserViewed productUserViewed) {
        productViewedOutputChannel.send(MessageBuilder.withPayload(productUserViewed).build());
    }
}
