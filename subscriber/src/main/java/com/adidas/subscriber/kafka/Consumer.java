package com.adidas.subscriber.kafka;

import org.springframework.messaging.Message;

public interface Consumer<T> {
    void consume(Message<T> message);
}
