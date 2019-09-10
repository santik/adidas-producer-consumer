package com.adidas.producer.activity;

import com.adidas.generated.ActivityEvent;
import com.adidas.producer.converter.ActivityEventConverter;
import com.adidas.producer.converter.ActivityEventConverterProvider;
import com.adidas.producer.converter.ConverterException;
import com.adidas.producer.publisher.ActivityEventPublisher;
import com.adidas.producer.publisher.PublisherException;
import com.adidas.producer.publisher.PublisherProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityService.class);
    private final PublisherProvider publisherProvider;
    private final ActivityEventConverterProvider activityEventConverterProvider;

    public ActivityService(PublisherProvider publisherProvider, ActivityEventConverterProvider activityEventConverterProvider) {
        this.publisherProvider = publisherProvider;
        this.activityEventConverterProvider = activityEventConverterProvider;
    }

    public void publishActivityEvent(ActivityEvent activityEvent) {
        try {
            ActivityEventPublisher publisher = publisherProvider.provide(activityEvent.getType());
            ActivityEventConverter converter = activityEventConverterProvider.provide(activityEvent.getType());
            publisher.publish(converter.convert(activityEvent));
        } catch (PublisherException | ConverterException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
