package com.adidas.subscriber.kafka.categoryviewed;

import com.adidas.generated.CategoryUserViewed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CategoryUserViewedProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryUserViewedProcessor.class);

    public void process(CategoryUserViewed categoryUserViewed) {
        LOGGER.info("Received {}", categoryUserViewed);
    }
}
