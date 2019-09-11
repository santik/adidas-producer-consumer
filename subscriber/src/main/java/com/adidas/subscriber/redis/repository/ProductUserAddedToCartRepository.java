package com.adidas.subscriber.redis.repository;

import com.adidas.subscriber.redis.model.ProductUserAddedToCartModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductUserAddedToCartRepository extends CrudRepository<ProductUserAddedToCartModel, String> {}
