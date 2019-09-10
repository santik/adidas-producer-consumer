package com.adidas.producer.publisher;

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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KafkaPublisherTest {

    @Mock
    private MessageChannel messageChannel;

    @Mock
    private KafkaChannelProvider provider;

    @Captor
    private ArgumentCaptor<Message> messageArgumentCaptor;

    @Test
    public void publish_shouldPublishToCorrectChannel() throws PublisherException {
        //arrange
        KafkaPublisher publisher = new KafkaPublisher(provider);
        Object message = new Object();
        when(provider.provideForMessage(message)).thenReturn(messageChannel);

        //act
        publisher.publish(message);

        //assert
        verify(messageChannel).send(messageArgumentCaptor.capture());
        assertEquals(message, messageArgumentCaptor.getValue().getPayload());
    }
}
