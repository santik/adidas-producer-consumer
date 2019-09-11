package com.adidas.subscriber.redis.repository;

import com.adidas.subscriber.redis.model.CategoryUserViewedModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryUserViewedRepository extends CrudRepository<CategoryUserViewedModel, String> {}
