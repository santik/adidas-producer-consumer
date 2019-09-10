package com.adidas.subscriber.categoryviewed;

import com.adidas.generated.CategoryUserViewed;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import static com.adidas.subscriber.categoryviewed.CategoryViewedInput.CATEGORY_VIEWED_INPUT_CHANNEL;

@EnableBinding(CategoryViewedInput.class)
public class CategoryViewedConsumer {

    private CategoryUserViewedProcessor processor;

    public CategoryViewedConsumer(CategoryUserViewedProcessor processor) {
        this.processor = processor;
    }

    @StreamListener(CATEGORY_VIEWED_INPUT_CHANNEL)
    public void consume(Message<CategoryUserViewed> message) {
        processor.process(message.getPayload());
    }
}
