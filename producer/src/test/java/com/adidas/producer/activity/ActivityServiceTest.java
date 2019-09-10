package com.adidas.producer.activity;

import com.adidas.generated.ActivityEvent;
import com.adidas.producer.converter.ActivityEventConverter;
import com.adidas.producer.converter.ActivityEventConverterProvider;
import com.adidas.producer.converter.ConverterException;
import com.adidas.producer.publisher.ActivityEventPublisher;
import com.adidas.producer.publisher.PublisherException;
import com.adidas.producer.publisher.PublisherProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    @Mock
    private PublisherProvider publisherProvider;

    @Mock
    private ActivityEventConverterProvider converterProvider;

    @Test
    public void publishActivityEvent_withActivityEvent_shouldCallPublisherWithConvertedPojo() throws PublisherException, ConverterException {
        //arrange
        ActivityService activityService = new ActivityService(publisherProvider, converterProvider);
        ActivityEvent activityEvent = new ActivityEvent();
        ActivityEventPublisher publisher = mock(ActivityEventPublisher.class);
        ActivityEventConverter converter = mock(ActivityEventConverter.class);
        activityEvent.setType(ActivityEvent.Type.PRODUCT_VIEWED);
        when(publisherProvider.provide(activityEvent.getType()))
                .thenReturn(publisher);
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
    public void publishActivityEvent_withPublisherException_shouldNotCallPublisher() throws PublisherException {
        //arrange
        ActivityService activityService = new ActivityService(publisherProvider, converterProvider);
        ActivityEvent activityEvent = new ActivityEvent();
        ActivityEventPublisher publisher = mock(ActivityEventPublisher.class);
        activityEvent.setType(ActivityEvent.Type.PRODUCT_VIEWED);
        when(publisherProvider.provide(activityEvent.getType())).thenThrow(PublisherException.class);

        //act
        activityService.publishActivityEvent(activityEvent);

        //assert
        verify(publisher, never()).publish(any());
    }

    @Test
    public void publishActivityEvent_withConverterException_shouldNotCallPublisher() throws PublisherException, ConverterException {
        //arrange
        ActivityService activityService = new ActivityService(publisherProvider, converterProvider);
        ActivityEvent activityEvent = new ActivityEvent();
        ActivityEventPublisher publisher = mock(ActivityEventPublisher.class);
        activityEvent.setType(ActivityEvent.Type.PRODUCT_VIEWED);
        when(publisherProvider.provide(activityEvent.getType())).thenReturn(publisher);
        when(converterProvider.provide(activityEvent.getType())).thenThrow(ConverterException.class);

        //act
        activityService.publishActivityEvent(activityEvent);

        //assert
        verify(publisher, never()).publish(any());
    }
}
