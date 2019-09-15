package com.adidas.subscriber.processor;

import com.adidas.generated.ProductUserViewed;
import com.adidas.subscriber.redis.model.ProductUserViewedModel;
import com.adidas.subscriber.redis.repository.ProductUserViewedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductUserViewedProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductUserViewedProcessor.class);

    private ProductUserViewedRepository productUserViewedRepository;

    public ProductUserViewedProcessor(ProductUserViewedRepository productUserViewedRepository) {
        this.productUserViewedRepository = productUserViewedRepository;
    }

    public void process(ProductUserViewed productUserViewed) {
        LOGGER.info("Saving {}", productUserViewed);
        productUserViewedRepository.save(ProductUserViewedModel.createFromKafkaMessage(productUserViewed));
    }
}
