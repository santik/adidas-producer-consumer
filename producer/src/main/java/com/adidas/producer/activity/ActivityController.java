package com.adidas.producer.activity;

import com.adidas.generated.ActivityEvent;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity activity(@RequestBody @Valid ActivityEvent activity) {
        activityService.publishActivityEvent(activity);
        return ResponseEntity.ok("ok");
    }

    @ExceptionHandler(InvalidDefinitionException.class)
    public ResponseEntity handleAllExceptions(InvalidDefinitionException ex, WebRequest request) {
        LOGGER.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
