package com.adidas.producer.publisher;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface ActivityEventPublisher<T> {
    void publish(@Valid  T t);
}
