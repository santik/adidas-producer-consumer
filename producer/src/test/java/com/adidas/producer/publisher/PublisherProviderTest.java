package com.adidas.producer.publisher;

import com.adidas.generated.ActivityEvent;
import com.adidas.producer.publisher.productviewed.ProductViewedPublisher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertTrue;

@ExtendWith(MockitoExtension.class)
class PublisherProviderTest {

    @Mock
    private ProductViewedPublisher productViewedPublisher;

    @Test
    public void provide_withProductViewedType_shouldReturnProductViewedProvider() throws PublisherException {
        //arrange
        PublisherProvider provider = new PublisherProvider(productViewedPublisher);

        //act
        ActivityEventPublisher provide = provider.provide(ActivityEvent.Type.PRODUCT_VIEWED);

        //assert
        assertTrue(provide instanceof ProductViewedPublisher);
    }

    @Test
    public void provide_withUnknownType_shouldThrowException() {
        //arrange
        PublisherProvider provider = new PublisherProvider(productViewedPublisher);

        //act && assert
        Assertions.assertThrows(PublisherException.class, () -> {
            provider.provide(ActivityEvent.Type.CATEGORY_VIEWED);
        });
    }
}