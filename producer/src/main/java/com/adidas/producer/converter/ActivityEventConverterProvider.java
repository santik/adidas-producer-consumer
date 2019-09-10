package com.adidas.producer.converter;

import com.adidas.generated.ActivityEvent;
import org.springframework.stereotype.Service;

@Service
public class ActivityEventConverterProvider {

    private ProductUserViewedConverter  productUserViewedConverter;

    public ActivityEventConverterProvider(ProductUserViewedConverter productUserViewedConverter) {
        this.productUserViewedConverter = productUserViewedConverter;
    }

    public ActivityEventConverter provide(ActivityEvent.Type type) throws ConverterException {
        if (type.equals(ActivityEvent.Type.PRODUCT_VIEWED)) {
            return productUserViewedConverter;
        }

        throw new ConverterException("No converter for type " + type.toString());
    }
}
