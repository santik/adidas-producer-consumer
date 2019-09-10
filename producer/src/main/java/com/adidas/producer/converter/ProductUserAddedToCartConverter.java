package com.adidas.producer.converter;

import com.adidas.generated.ActivityEvent;
import com.adidas.generated.ProductUserAddedToCart;
import org.springframework.stereotype.Service;

@Service
public class ProductUserAddedToCartConverter implements ActivityEventConverter<ProductUserAddedToCart> {

    @Override
    public ProductUserAddedToCart convert(ActivityEvent activityEvent) {

        ProductUserAddedToCart productUserAddedToCart = new ProductUserAddedToCart();
        productUserAddedToCart.setUserId(activityEvent.getUserId());
        productUserAddedToCart.setProductId(activityEvent.getPayload().getProductId());
        productUserAddedToCart.setCreated(activityEvent.getCreated());

        return productUserAddedToCart;
    }
}
