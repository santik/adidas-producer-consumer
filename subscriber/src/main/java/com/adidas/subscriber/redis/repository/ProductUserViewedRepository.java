package com.adidas.subscriber.redis.repository;

import com.adidas.subscriber.redis.model.ProductUserViewedModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductUserViewedRepository extends CrudRepository<ProductUserViewedModel, String> {}
