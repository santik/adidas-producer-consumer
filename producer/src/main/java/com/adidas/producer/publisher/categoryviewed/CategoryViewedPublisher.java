package com.adidas.producer.publisher.categoryviewed;

import com.adidas.generated.CategoryUserViewed;
import com.adidas.producer.publisher.ActivityEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import static com.adidas.producer.publisher.categoryviewed.CategoryViewedOutput.CATEGORY_VIEWED_OUTPUT_CHANNEL;

@EnableBinding({CategoryViewedOutput.class})
public class CategoryViewedPublisher implements ActivityEventPublisher<CategoryUserViewed> {
    private final MessageChannel categoryViewedOutputChannel;

    @Autowired
    public CategoryViewedPublisher(@Qualifier(CATEGORY_VIEWED_OUTPUT_CHANNEL) MessageChannel categoryViewedOutputChannel) {
        this.categoryViewedOutputChannel = categoryViewedOutputChannel;
    }

    @Override
    public void publish(CategoryUserViewed productUserViewed) {
        categoryViewedOutputChannel.send(MessageBuilder.withPayload(productUserViewed).build());
    }
}
