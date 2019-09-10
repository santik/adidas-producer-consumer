package com.adidas.producer.converter;

import com.adidas.generated.ActivityEvent;
import org.springframework.stereotype.Service;

@Service
public class ActivityEventConverterProvider {

    private ProductUserViewedConverter  productUserViewedConverter;
    private CategoryUserViewedConverter  categoryUserViewedConverter;
    private ProductUserAddedToCartConverter  productUserAddedToCartConverter;

    public ActivityEventConverterProvider(
            ProductUserViewedConverter productUserViewedConverter,
            CategoryUserViewedConverter categoryUserViewedConverter,
            ProductUserAddedToCartConverter productUserAddedToCartConverter
    ) {
        this.productUserViewedConverter = productUserViewedConverter;
        this.categoryUserViewedConverter = categoryUserViewedConverter;
        this.productUserAddedToCartConverter = productUserAddedToCartConverter;
    }

    public ActivityEventConverter provide(ActivityEvent.Type type) throws ConverterException {
        if (type.equals(ActivityEvent.Type.PRODUCT_VIEWED)) {
            return productUserViewedConverter;
        }

        if (type.equals(ActivityEvent.Type.PRODUCT_ADDED_TO_CART)) {
            return productUserAddedToCartConverter;
        }

        if (type.equals(ActivityEvent.Type.CATEGORY_VIEWED)) {
            return categoryUserViewedConverter;
        }

        throw new ConverterException("No converter for type " + type.toString());
    }
}
