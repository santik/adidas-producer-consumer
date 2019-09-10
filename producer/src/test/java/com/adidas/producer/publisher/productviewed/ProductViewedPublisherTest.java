package com.adidas.producer.publisher.productviewed;

import com.adidas.generated.ProductUserViewed;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductViewedPublisherTest {

    @Mock
    private MessageChannel productViewedOutputChannel;

    @Captor
    private ArgumentCaptor<Message> messageArgumentCaptor;

    @Test
    public void publish_shouldPublishToCorrectChannel() {
        //arrange
        ProductViewedPublisher publisher = new ProductViewedPublisher(productViewedOutputChannel);
        ProductUserViewed productUserViewed = new ProductUserViewed();

        //act
        publisher.publish(productUserViewed);

        //assert
        verify(productViewedOutputChannel).send(messageArgumentCaptor.capture());
        assertEquals(productUserViewed, messageArgumentCaptor.getValue().getPayload());
    }
}
