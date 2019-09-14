package com.adidas.subscriber.redis.model;

import com.adidas.generated.ProductUserAddedToCart;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;

@RedisHash("ProductUserAddedToCartModel")
public class ProductUserAddedToCartModel implements Serializable {

    @Id
    private String id;
    private String userId;
    private String productId;
    private Date created;

    private ProductUserAddedToCartModel(String userId, String productId, Date created) {
        this.userId = userId;
        this.productId = productId;
        this.created = created;
        this.id  = userId + created.toString();
    }

    public static ProductUserAddedToCartModel createFromKafkaMessage(ProductUserAddedToCart productUserAddedToCart) {
        return new ProductUserAddedToCartModel(productUserAddedToCart.getUserId(), productUserAddedToCart.getProductId(), productUserAddedToCart.getCreated());
    }

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }
}
