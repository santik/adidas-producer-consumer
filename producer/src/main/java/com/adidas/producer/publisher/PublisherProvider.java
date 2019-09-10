package com.adidas.producer.publisher;

import com.adidas.generated.ActivityEvent;
import com.adidas.producer.publisher.productviewed.ProductViewedPublisher;
import org.springframework.stereotype.Service;

@Service
public class PublisherProvider {

    private ProductViewedPublisher productViewedPublisher;

    public PublisherProvider(ProductViewedPublisher productViewedPublisher) {
        this.productViewedPublisher = productViewedPublisher;
    }

    public ActivityEventPublisher provide(ActivityEvent.Type type) throws PublisherException {
        if (type.equals(ActivityEvent.Type.PRODUCT_VIEWED)) {
            return productViewedPublisher;
        }

        throw new PublisherException(type.toString());
    }
}
