package com.adidas.subscriber.redis.model;

import com.adidas.generated.CategoryUserViewed;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;

@RedisHash("CategoryUserViewedModel")
public class CategoryUserViewedModel implements Serializable {

    @Id
    private String id;
    private String userId;
    private String categoryId;
    private Date created;

    private CategoryUserViewedModel(String userId, String categoryId, Date created) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.created = created;
        this.id  = userId + created.toString();
    }

    public static CategoryUserViewedModel createFromKafkaMessage(CategoryUserViewed categoryUserViewed) {
        return new CategoryUserViewedModel(categoryUserViewed.getUserId(), categoryUserViewed.getCategoryId(), categoryUserViewed.getCreated());
    }
}
