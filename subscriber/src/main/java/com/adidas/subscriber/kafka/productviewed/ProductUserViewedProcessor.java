package com.adidas.subscriber.kafka.productviewed;

import com.adidas.generated.ProductUserViewed;
import com.adidas.subscriber.redis.model.ProductUserViewedModel;
import com.adidas.subscriber.redis.repository.ProductUserViewedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductUserViewedProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductUserViewedProcessor.class);

    private ProductUserViewedRepository repository;

    public ProductUserViewedProcessor(ProductUserViewedRepository repository) {
        this.repository = repository;
    }

    public void process(ProductUserViewed productUserViewed) {
        LOGGER.info("Saving {}", productUserViewed);
        repository.save(ProductUserViewedModel.createFromKafkaMessage(productUserViewed));
    }
}
