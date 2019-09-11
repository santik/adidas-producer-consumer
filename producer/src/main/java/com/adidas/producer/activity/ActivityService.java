package com.adidas.producer.activity;

import com.adidas.generated.ActivityEvent;
import com.adidas.producer.converter.ActivityEventConverter;
import com.adidas.producer.converter.ActivityEventConverterProvider;
import com.adidas.producer.converter.ConverterException;
import com.adidas.producer.kafka.KafkaPublisher;
import com.adidas.producer.kafka.PublisherException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityService.class);
    private final ActivityEventConverterProvider activityEventConverterProvider;
    private final KafkaPublisher kafkaPublisher;

    public ActivityService(ActivityEventConverterProvider activityEventConverterProvider, KafkaPublisher kafkaPublisher) {
        this.activityEventConverterProvider = activityEventConverterProvider;
        this.kafkaPublisher = kafkaPublisher;
    }

    public void publishActivityEvent(ActivityEvent activityEvent) {
        try {
            Object message = convertToKafkaMessage(activityEvent);
            kafkaPublisher.publish(message);
        } catch (PublisherException | ConverterException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private Object convertToKafkaMessage(ActivityEvent activityEvent) throws ConverterException {
        ActivityEventConverter converter = activityEventConverterProvider.provide(activityEvent.getType());
        return converter.convert(activityEvent);
    }
}
