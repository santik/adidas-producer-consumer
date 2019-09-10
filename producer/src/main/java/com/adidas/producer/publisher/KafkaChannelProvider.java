package com.adidas.producer.publisher;

import com.adidas.generated.CategoryUserViewed;
import com.adidas.generated.ProductUserAddedToCart;
import com.adidas.generated.ProductUserViewed;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
@EnableBinding({KafkaChannels.class})
public class KafkaChannelProvider {

    private final MessageChannel categoryViewedOutputChannel;
    private final MessageChannel productViewedOutputChannel;
    private final MessageChannel productAddedToCartOutputChannel;

    public KafkaChannelProvider(
            MessageChannel categoryViewedOutputChannel,
            MessageChannel productViewedOutputChannel,
            MessageChannel productAddedToCartOutputChannel) {
        this.categoryViewedOutputChannel = categoryViewedOutputChannel;
        this.productViewedOutputChannel = productViewedOutputChannel;
        this.productAddedToCartOutputChannel = productAddedToCartOutputChannel;
    }

    public MessageChannel provideForMessage(Object message) throws PublisherException {
        if (message instanceof ProductUserViewed) {
            return productViewedOutputChannel;
        }

        if (message instanceof CategoryUserViewed) {
            return categoryViewedOutputChannel;
        }

        if (message instanceof ProductUserAddedToCart) {
            return productAddedToCartOutputChannel;
        }

        throw new PublisherException("No suitable channel for " + message);
    }
}
