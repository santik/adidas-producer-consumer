package com.adidas.subscriber.kafka.categoryviewed;

import com.adidas.generated.CategoryUserViewed;
import com.adidas.subscriber.redis.model.CategoryUserViewedModel;
import com.adidas.subscriber.redis.repository.CategoryUserViewedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CategoryUserViewedProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryUserViewedProcessor.class);

    private CategoryUserViewedRepository repository;

    public CategoryUserViewedProcessor(CategoryUserViewedRepository repository) {
        this.repository = repository;
    }

    public void process(CategoryUserViewed categoryUserViewed) {
        LOGGER.info("Received {}", categoryUserViewed);
        repository.save(CategoryUserViewedModel.createFromKafkaMessage(categoryUserViewed));
    }
}
