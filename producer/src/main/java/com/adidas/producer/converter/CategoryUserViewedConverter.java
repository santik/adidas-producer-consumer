package com.adidas.producer.converter;

import com.adidas.generated.ActivityEvent;
import com.adidas.generated.CategoryUserViewed;
import org.springframework.stereotype.Service;

@Service
public class CategoryUserViewedConverter implements ActivityEventConverter<CategoryUserViewed> {

    @Override
    public CategoryUserViewed convert(ActivityEvent activityEvent) {

        CategoryUserViewed categoryUserViewed = new CategoryUserViewed();
        categoryUserViewed.setUserId(activityEvent.getUserId());
        categoryUserViewed.setCategoryId(activityEvent.getPayload().getCategoryId());
        categoryUserViewed.setCreated(activityEvent.getCreated());

        return categoryUserViewed;
    }
}
