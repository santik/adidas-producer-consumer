package com.adidas.subscriber.kafka.productaddedtocart;

import com.adidas.generated.ProductUserAddedToCart;
import com.adidas.subscriber.redis.model.ProductUserAddedToCartModel;
import com.adidas.subscriber.redis.repository.ProductUserAddedToCartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductAddedToCartProcessorTest {
    @Mock
    private ProductUserAddedToCartRepository productAddedToCartRepository;

    @Captor
    private ArgumentCaptor<ProductUserAddedToCartModel> productAddedToCartModelArgumentCaptor;

    @Test
    void process_withMessage_shouldSaveCorrecgtModel() {
        //arrange
        ProductAddedToCartProcessor processor = new ProductAddedToCartProcessor(productAddedToCartRepository);
        ProductUserAddedToCart productAddedToCart = new ProductUserAddedToCart();
        productAddedToCart.setProductId("someId");
        productAddedToCart.setUserId("someId");
        productAddedToCart.setCreated(new Date());
        ProductUserAddedToCartModel fromKafkaMessageExpected = ProductUserAddedToCartModel.createFromKafkaMessage(productAddedToCart);


        //act
        processor.process(productAddedToCart);

        //assert
        verify(productAddedToCartRepository).save(productAddedToCartModelArgumentCaptor.capture());
        ProductUserAddedToCartModel actualModel = productAddedToCartModelArgumentCaptor.getValue();
        assertEquals(actualModel.getProductId(), fromKafkaMessageExpected.getProductId());
        assertEquals(actualModel.getId(), fromKafkaMessageExpected.getId());
    }
}
