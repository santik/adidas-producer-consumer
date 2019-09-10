package com.adidas.producer.converter;

import com.adidas.generated.ActivityEvent;
import com.adidas.generated.Payload;
import com.adidas.generated.ProductUserViewed;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

class ProductUserViewedConverterTest {

    @Test
    public void convert_withActivityEvent_shouldReturnCorrectObject() {
        //arrange
        ProductUserViewedConverter converter = new ProductUserViewedConverter();
        ActivityEvent activityEvent = new ActivityEvent();
        activityEvent.setCreated(new Date());
        activityEvent.setUserId("userId");
        Payload payload = new Payload();
        payload.setProductId("productId");
        activityEvent.setPayload(payload);

        //act
        ProductUserViewed productUserViewed = converter.convert(activityEvent);

        //assert
        assertEquals(activityEvent.getCreated(), productUserViewed.getCreated());
        assertEquals(activityEvent.getUserId(), productUserViewed.getUserId());
        assertEquals(activityEvent.getPayload().getProductId(), productUserViewed.getProductId());
    }
}
