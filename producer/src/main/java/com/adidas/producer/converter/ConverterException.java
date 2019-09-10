package com.adidas.producer.converter;

public class ConverterException extends Throwable {
    private final String reason;

    public ConverterException(String reason) {
        this.reason = reason;
    }
}
