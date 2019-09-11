package com.adidas.subscriber.kafka.productaddedtocart;

import com.adidas.generated.ProductUserAddedToCart;
import com.adidas.subscriber.redis.model.ProductUserAddedToCartModel;
import com.adidas.subscriber.redis.repository.ProductUserAddedToCartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductAddedToCartProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductAddedToCartProcessor.class);

    private ProductUserAddedToCartRepository repository;

    public ProductAddedToCartProcessor(ProductUserAddedToCartRepository repository) {
        this.repository = repository;
    }

    public void process(ProductUserAddedToCart productUserAddedToCart) {
        LOGGER.info("Received {}", productUserAddedToCart);
        repository.save(ProductUserAddedToCartModel.createFromKafkaMessage(productUserAddedToCart));
    }
}
