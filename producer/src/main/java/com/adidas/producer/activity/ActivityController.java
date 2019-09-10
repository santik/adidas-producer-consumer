package com.adidas.producer.activity;

import com.adidas.generated.ActivityEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/activity")
@Validated
public class ActivityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping(path = "/", consumes = "application/json")
    public void activity(@RequestBody @Valid ActivityEvent activity) {
        activityService.publishActivityEvent(activity);
    }

    @ExceptionHandler(Exception.class)
    public void handleAllExceptions(Exception ex, WebRequest request) {
        LOGGER.error(ex.getMessage());
    }
}
