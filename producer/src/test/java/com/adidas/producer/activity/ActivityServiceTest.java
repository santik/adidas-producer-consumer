package com.adidas.producer.activity;

import com.adidas.generated.ActivityEvent;
import com.adidas.producer.converter.ActivityEventConverter;
import com.adidas.producer.converter.ActivityEventConverterProvider;
import com.adidas.producer.converter.ConverterException;
import com.adidas.producer.publisher.KafkaPublisher;
import com.adidas.producer.publisher.PublisherException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    @Mock
    private KafkaPublisher publisher;

    @Mock
    private ActivityEventConverterProvider converterProvider;

    @Test
    public void publishActivityEvent_withActivityEvent_shouldCallPublisherWithConvertedPojo() throws PublisherException, ConverterException {
        //arrange
        ActivityService activityService = new ActivityService(converterProvider, publisher);
        ActivityEvent activityEvent = new ActivityEvent();
        ActivityEventConverter converter = mock(ActivityEventConverter.class);
        activityEvent.setType(ActivityEvent.Type.PRODUCT_VIEWED);
        when(converterProvider.provide(activityEvent.getType()))
                .thenReturn(converter);
        Object convertedEvent = new Object();
        when(converter.convert(activityEvent)).thenReturn(convertedEvent);

        //act
        activityService.publishActivityEvent(activityEvent);

        //assert
        verify(publisher).publish(convertedEvent);
    }

    @Test
    public void publishActivityEvent_withConverterException_shouldNotCallPublisher() throws PublisherException, ConverterException {
        //arrange
        ActivityService activityService = new ActivityService(converterProvider, publisher);
        ActivityEvent activityEvent = new ActivityEvent();
        activityEvent.setType(ActivityEvent.Type.PRODUCT_VIEWED);
        when(converterProvider.provide(activityEvent.getType())).thenThrow(ConverterException.class);

        //act
        activityService.publishActivityEvent(activityEvent);

        //assert
        verify(publisher, never()).publish(any());
    }
}
