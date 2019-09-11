package com.adidas.subscriber.redis.model;

import com.adidas.generated.ProductUserViewed;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;

@RedisHash("ProductUserViewedModel")
public class ProductUserViewedModel implements Serializable {

    @Id
    private String id;
    private String userId;
    private String productId;
    private Date created;

    private ProductUserViewedModel(String userId, String productId, Date created) {
        this.userId = userId;
        this.productId = productId;
        this.created = created;
        this.id  = userId + created.toString();
    }

    public static ProductUserViewedModel createFromKafkaMessage(ProductUserViewed productUserViewed) {
        return new ProductUserViewedModel(productUserViewed.getUserId(), productUserViewed.getProductId(), productUserViewed.getCreated());
    }
}
