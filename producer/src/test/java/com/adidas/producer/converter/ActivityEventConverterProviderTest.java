package com.adidas.producer.converter;

import com.adidas.generated.ActivityEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertTrue;

@ExtendWith(MockitoExtension.class)
class ActivityEventConverterProviderTest {

    @Mock
    private ProductUserViewedConverter  productUserViewedConverter;

    @Mock
    private ProductUserAddedToCartConverter  productUserAddedToCartConverter;

    @Mock
    private CategoryUserViewedConverter  categoryUserViewedConverter;

    @Test
    public void provide_withProductViewedType_shouldReturnProductViewedConverter() throws ConverterException {
        //arrange
        ActivityEventConverterProvider provider = new ActivityEventConverterProvider(productUserViewedConverter, categoryUserViewedConverter, productUserAddedToCartConverter);

        //act
        ActivityEventConverter provide = provider.provide(ActivityEvent.Type.PRODUCT_VIEWED);

        //assert
        assertTrue(provide instanceof ProductUserViewedConverter);
    }

    @Test
    public void provide_withCategoryViewedType_shouldReturnCategoryViewedConverter() throws ConverterException {
        //arrange
        ActivityEventConverterProvider provider = new ActivityEventConverterProvider(productUserViewedConverter, categoryUserViewedConverter, productUserAddedToCartConverter);

        //act
        ActivityEventConverter provide = provider.provide(ActivityEvent.Type.CATEGORY_VIEWED);

        //assert
        assertTrue(provide instanceof CategoryUserViewedConverter);
    }

    @Test
    public void provide_withAddedToCart_shouldReturnProductAddedToCartConverter() throws ConverterException {
        //arrange
        ActivityEventConverterProvider provider = new ActivityEventConverterProvider(productUserViewedConverter, categoryUserViewedConverter, productUserAddedToCartConverter);

        //act
        ActivityEventConverter provide = provider.provide(ActivityEvent.Type.PRODUCT_ADDED_TO_CART);

        //assert
        assertTrue(provide instanceof ProductUserAddedToCartConverter);
    }

    @Test
    public void provide_withUnknownType_shouldThrowException() {
        //arrange
        ActivityEventConverterProvider provider = new ActivityEventConverterProvider(productUserViewedConverter, categoryUserViewedConverter, productUserAddedToCartConverter);

        //act && assert
        Assertions.assertThrows(ConverterException.class, () -> {
            provider.provide(ActivityEvent.Type.UNKNOWN);
        });
    }
}