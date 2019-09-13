package com.adidas.subscriber.kafka.productviewed;

import com.adidas.generated.ProductUserViewed;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.Message;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductViewedConsumerTest {
    @Mock
    private ProductUserViewedProcessor processor;

    @Test
    void consume_withMessage_shouldCallProcessor() {
        //arrange
        ProductViewedConsumer productViewedConsumer = new ProductViewedConsumer(processor);
        Message<ProductUserViewed> message = mock(Message.class);
        ProductUserViewed productViewed = new ProductUserViewed();
        when(message.getPayload()).thenReturn(productViewed);

        //act
        productViewedConsumer.consume(message);

        //assert
        verify(processor).process(productViewed);
    }
}
