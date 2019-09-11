package com.adidas.subscriber.kafka;

public class ConsumerException extends Throwable {

    private final String type;

    public ConsumerException(String type) {
        this.type = type;
    }
}
