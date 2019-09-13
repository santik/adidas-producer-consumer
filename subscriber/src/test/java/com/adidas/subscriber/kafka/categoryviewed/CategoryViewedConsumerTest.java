package com.adidas.subscriber.kafka.categoryviewed;

import com.adidas.generated.CategoryUserViewed;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.Message;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryViewedConsumerTest {

    @Mock
    private CategoryUserViewedProcessor processor;

    @Test
    void consume_withMessage_shouldCallProcessor() {
        //arrange
        CategoryViewedConsumer categoryViewedConsumer = new CategoryViewedConsumer(processor);
        Message<CategoryUserViewed> message = mock(Message.class);
        CategoryUserViewed categoryViewed = new CategoryUserViewed();
        when(message.getPayload()).thenReturn(categoryViewed);

        //act
        categoryViewedConsumer.consume(message);

        //assert
        verify(processor).process(categoryViewed);
    }
}
