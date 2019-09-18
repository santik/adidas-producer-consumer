package com.adidas.subscriber.processor;

public interface Processor<T> {
    void process(T message);
}
