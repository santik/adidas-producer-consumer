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

    @Test
    public void provide_withProductViewedType_shouldReturnProductViewedConverter() throws ConverterException {
        //arrange
        ActivityEventConverterProvider provider = new ActivityEventConverterProvider(productUserViewedConverter);

        //act
        ActivityEventConverter provide = provider.provide(ActivityEvent.Type.PRODUCT_VIEWED);

        //assert
        assertTrue(provide instanceof ProductUserViewedConverter);
    }

    @Test
    public void provide_withUnknownType_shouldThrowException() {
        //arrange
        ActivityEventConverterProvider provider = new ActivityEventConverterProvider(productUserViewedConverter);

        //act && assert
        Assertions.assertThrows(ConverterException.class, () -> {
            provider.provide(ActivityEvent.Type.CATEGORY_VIEWED);
        });
    }
}