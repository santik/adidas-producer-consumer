package com.adidas.producer.converter;

import com.adidas.generated.ActivityEvent;

public interface ActivityEventConverter<T> {
    T convert(ActivityEvent activityEvent) throws ConverterException;
}
