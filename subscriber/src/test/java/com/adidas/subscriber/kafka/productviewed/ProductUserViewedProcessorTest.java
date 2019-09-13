package com.adidas.subscriber.kafka.productviewed;

import com.adidas.generated.ProductUserViewed;
import com.adidas.subscriber.redis.model.ProductUserViewedModel;
import com.adidas.subscriber.redis.repository.ProductUserViewedRepository;
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
class ProductUserViewedProcessorTest {
    @Mock
    private ProductUserViewedRepository productUserViewedRepository;

    @Captor
    private ArgumentCaptor<ProductUserViewedModel> productUserViewedModelArgumentCaptor;

    @Test
    void process_withMessage_shouldSaveCorrecgtModel() {
        //arrange
        ProductUserViewedProcessor processor = new ProductUserViewedProcessor(productUserViewedRepository);
        ProductUserViewed productUserViewed = new ProductUserViewed();
        productUserViewed.setProductId("someId");
        productUserViewed.setUserId("someId");
        productUserViewed.setCreated(new Date());
        ProductUserViewedModel fromKafkaMessageExpected = ProductUserViewedModel.createFromKafkaMessage(productUserViewed);


        //act
        processor.process(productUserViewed);

        //assert
        verify(productUserViewedRepository).save(productUserViewedModelArgumentCaptor.capture());
        ProductUserViewedModel actualModel = productUserViewedModelArgumentCaptor.getValue();
        assertEquals(actualModel.getProductId(), fromKafkaMessageExpected.getProductId());
        assertEquals(actualModel.getId(), fromKafkaMessageExpected.getId());
    }
}
