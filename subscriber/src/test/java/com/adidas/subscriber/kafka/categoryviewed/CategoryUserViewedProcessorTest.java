package com.adidas.subscriber.kafka.categoryviewed;

import com.adidas.generated.CategoryUserViewed;
import com.adidas.subscriber.redis.model.CategoryUserViewedModel;
import com.adidas.subscriber.redis.repository.CategoryUserViewedRepository;
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
class CategoryUserViewedProcessorTest {

    @Mock
    private CategoryUserViewedRepository categoryUserViewedRepository;

    @Captor
    private ArgumentCaptor<CategoryUserViewedModel> categoryUserViewedModelArgumentCaptor;

    @Test
    void process_withMessage_shouldSaveCorrecgtModel() {
        //arrange
        CategoryUserViewedProcessor processor = new CategoryUserViewedProcessor(categoryUserViewedRepository);
        CategoryUserViewed categoryUserViewed = new CategoryUserViewed();
        categoryUserViewed.setCategoryId("someId");
        categoryUserViewed.setUserId("someId");
        categoryUserViewed.setCreated(new Date());
        CategoryUserViewedModel fromKafkaMessageExpected = CategoryUserViewedModel.createFromKafkaMessage(categoryUserViewed);


        //act
        processor.process(categoryUserViewed);

        //assert
        verify(categoryUserViewedRepository).save(categoryUserViewedModelArgumentCaptor.capture());
        CategoryUserViewedModel actualModel = categoryUserViewedModelArgumentCaptor.getValue();
        assertEquals(actualModel.getCategoryId(), fromKafkaMessageExpected.getCategoryId());
        assertEquals(actualModel.getId(), fromKafkaMessageExpected.getId());
    }
}