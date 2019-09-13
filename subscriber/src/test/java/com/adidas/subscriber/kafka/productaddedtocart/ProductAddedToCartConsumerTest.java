package com.adidas.subscriber.kafka.productaddedtocart;

import com.adidas.generated.ProductUserAddedToCart;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.Message;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductAddedToCartConsumerTest {
    @Mock
    private ProductAddedToCartProcessor processor;

    @Test
    void consume_withMessage_shouldCallProcessor() {
        //arrange
        ProductAddedToCartConsumer categoryViewedConsumer = new ProductAddedToCartConsumer(processor);
        Message<ProductUserAddedToCart> message = mock(Message.class);
        ProductUserAddedToCart categoryViewed = new ProductUserAddedToCart();
        when(message.getPayload()).thenReturn(categoryViewed);

        //act
        categoryViewedConsumer.consume(message);

        //assert
        verify(processor).process(categoryViewed);
    }
}
