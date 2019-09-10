package com.adidas.producer.activity;

import com.adidas.generated.ActivityEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ActivityControllerTest {

    @Mock
    private ActivityService activityService;

    @Test
    public void activity_withValidActivity_shouldCallService() {
        //arrange
        ActivityController activityController = new ActivityController(activityService);
        ActivityEvent activity = new ActivityEvent();
        activity.setType(ActivityEvent.Type.PRODUCT_VIEWED);
        String userId = "userId";
        activity.setUserId(userId);
        activity.setCreated(new Date());

        //act
        activityController.activity(activity);

        //assert
        verify(activityService).publishActivityEvent(activity);
    }
}
