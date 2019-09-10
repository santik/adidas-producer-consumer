package com.adidas.producer.publisher;

import com.adidas.generated.ActivityEvent;
import com.adidas.producer.publisher.categoryviewed.CategoryViewedPublisher;
import com.adidas.producer.publisher.productaddedtocart.ProductAddedToCartPublisher;
import com.adidas.producer.publisher.productviewed.ProductViewedPublisher;
import org.springframework.stereotype.Service;

@Service
public class PublisherProvider {

    private ProductViewedPublisher productViewedPublisher;
    private CategoryViewedPublisher categoryViewedPublisher;
    private ProductAddedToCartPublisher productAddedToCartPublisher;

    public PublisherProvider(ProductViewedPublisher productViewedPublisher, CategoryViewedPublisher categoryViewedPublisher, ProductAddedToCartPublisher productAddedToCartPublisher) {
        this.productViewedPublisher = productViewedPublisher;
        this.categoryViewedPublisher = categoryViewedPublisher;
        this.productAddedToCartPublisher = productAddedToCartPublisher;
    }

    public ActivityEventPublisher provide(ActivityEvent.Type type) throws PublisherException {
        if (type.equals(ActivityEvent.Type.PRODUCT_VIEWED)) {
            return productViewedPublisher;
        }

        if (type.equals(ActivityEvent.Type.PRODUCT_ADDED_TO_CART)) {
            return productAddedToCartPublisher;
        }

        if (type.equals(ActivityEvent.Type.CATEGORY_VIEWED)) {
            return categoryViewedPublisher;
        }

        throw new PublisherException(type.toString());
    }
}
