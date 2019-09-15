package com.adidas.subscriber.processor;

import com.adidas.generated.CategoryUserViewed;
import com.adidas.subscriber.redis.model.CategoryUserViewedModel;
import com.adidas.subscriber.redis.repository.CategoryUserViewedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CategoryUserViewedProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryUserViewedProcessor.class);

    private CategoryUserViewedRepository categoryUserViewedRepository;

    public CategoryUserViewedProcessor(CategoryUserViewedRepository categoryUserViewedRepository) {
        this.categoryUserViewedRepository = categoryUserViewedRepository;
    }

    public void process(CategoryUserViewed categoryUserViewed) {
        LOGGER.info("Received {}", categoryUserViewed);
        categoryUserViewedRepository.save(CategoryUserViewedModel.createFromKafkaMessage(categoryUserViewed));
    }
}
