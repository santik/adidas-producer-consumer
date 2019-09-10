package com.adidas.producer.converter;

import com.adidas.generated.ActivityEvent;
import com.adidas.generated.Payload;
import com.adidas.generated.ProductUserAddedToCart;
import com.adidas.generated.ProductUserViewed;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

class ProductUserAddedToCartConverterTest {

    @Test
    public void convert_withActivityEvent_shouldReturnCorrectObject() {
        //arrange
        ProductUserAddedToCartConverter converter = new ProductUserAddedToCartConverter();
        ActivityEvent activityEvent = new ActivityEvent();
        activityEvent.setCreated(new Date());
        activityEvent.setUserId("userId");
        Payload payload = new Payload();
        payload.setProductId("productId");
        activityEvent.setPayload(payload);

        //act
        ProductUserAddedToCart productUserAddedToCart = converter.convert(activityEvent);

        //assert
        assertEquals(activityEvent.getCreated(), productUserAddedToCart.getCreated());
        assertEquals(activityEvent.getUserId(), productUserAddedToCart.getUserId());
        assertEquals(activityEvent.getPayload().getProductId(), productUserAddedToCart.getProductId());
    }
}
