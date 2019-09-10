package com.adidas.producer.converter;

import com.adidas.generated.ActivityEvent;
import com.adidas.generated.ProductUserViewed;
import org.springframework.stereotype.Service;

@Service
public class ProductUserViewedConverter implements ActivityEventConverter<ProductUserViewed> {

    @Override
    public ProductUserViewed convert(ActivityEvent activityEvent) {

        ProductUserViewed productUserViewed = new ProductUserViewed();
        productUserViewed.setUserId(activityEvent.getUserId());
        productUserViewed.setProductId(activityEvent.getPayload().getProductId());
        productUserViewed.setCreated(activityEvent.getCreated());

        return productUserViewed;
    }
}
