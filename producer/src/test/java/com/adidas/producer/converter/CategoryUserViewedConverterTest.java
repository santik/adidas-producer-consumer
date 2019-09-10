package com.adidas.producer.converter;

import com.adidas.generated.ActivityEvent;
import com.adidas.generated.CategoryUserViewed;
import com.adidas.generated.Payload;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

class CategoryUserViewedConverterTest {

    @Test
    public void convert_withActivityEvent_shouldReturnCorrectObject() {
        //arrange
        CategoryUserViewedConverter converter = new CategoryUserViewedConverter();
        ActivityEvent activityEvent = new ActivityEvent();
        activityEvent.setCreated(new Date());
        activityEvent.setUserId("userId");
        Payload payload = new Payload();
        payload.setCategoryId("categoryId");
        activityEvent.setPayload(payload);

        //act
        CategoryUserViewed categoryUserViewed = converter.convert(activityEvent);

        //assert
        assertEquals(activityEvent.getCreated(), categoryUserViewed.getCreated());
        assertEquals(activityEvent.getUserId(), categoryUserViewed.getUserId());
        assertEquals(activityEvent.getPayload().getCategoryId(), categoryUserViewed.getCategoryId());
    }
}
