package com.adidas.producer.functional.steps;

import com.adidas.generated.ActivityEvent;
import com.adidas.generated.CategoryUserViewed;
import com.adidas.generated.Payload;
import com.adidas.generated.ProductUserAddedToCart;
import com.adidas.generated.ProductUserViewed;
import com.adidas.producer.functional.AsyncEventHelper;
import com.adidas.producer.functional.TestingConfiguration;
import com.adidas.producer.functional.TestingSession;
import com.adidas.producer.functional.ActivityEventClient;
import com.adidas.producer.functional.kafka.KafkaChannels;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.config.BinderFactoryConfiguration;
import org.springframework.cloud.stream.config.BindingServiceConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@ContextConfiguration(classes = {
        BinderFactoryConfiguration.class,
        BindingServiceConfiguration.class,
        TestingConfiguration.class,
})
@TestPropertySource(locations = {"classpath:application-functional.properties"})
public class PublishesMessagesWithDataFromApiSteps {

    @Autowired
    private ActivityEventClient client;

    private ActivityEvent categoryUserViewedEvent;
    private ActivityEvent productUserAddedToCartEvent;
    private ActivityEvent productUserViewedEvent;

    @Given("CategoryUserViewed event")
    public void createCategoryUserViewedMessage() {
        categoryUserViewedEvent = new ActivityEvent();
        categoryUserViewedEvent.setType(ActivityEvent.Type.CATEGORY_VIEWED);
        categoryUserViewedEvent.setUserId("someId" + Math.random());
        categoryUserViewedEvent.setCreated(new Date());
        Payload payload = new Payload();
        payload.setCategoryId("someId" + Math.random());
        categoryUserViewedEvent.setPayload(payload);
    }

    @When("CategoryUserViewed event was sent via API")
    public void sendCategoryUserViewedEvent() throws IOException, InterruptedException {
        client.sendActivityEvent(categoryUserViewedEvent);
    }

    @Then("data from CategoryUserViewed event is published")
    public void verifyCategoryUserViewedEventIsPublished() {
        AsyncEventHelper.await(this::dataFromCategoryUserViewedMessageIsSaved, "Data saved");
    }

    private boolean dataFromCategoryUserViewedMessageIsSaved() {
        var messages = new ArrayList<>(TestingSession.getInstance().getMessages(KafkaChannels.CATEGORY_VIEWED_INPUT_CHANNEL));
        long count = messages
                .stream().filter(message -> dataForCategoryViewedMatch((CategoryUserViewed) message, categoryUserViewedEvent)).count();

        return count > 0;
    }

    private boolean dataForCategoryViewedMatch(CategoryUserViewed message, ActivityEvent activityEvent) {
        return message.getUserId().equals(activityEvent.getUserId())
                && message.getCategoryId().equals(activityEvent.getPayload().getCategoryId())
                && message.getCreated().equals(activityEvent.getCreated());
    }

    @Given("ProductUserViewed event")
    public void createProductUserViewedMessage() {
        productUserViewedEvent = new ActivityEvent();
        productUserViewedEvent.setType(ActivityEvent.Type.PRODUCT_VIEWED);
        productUserViewedEvent.setUserId("someId" + Math.random());
        productUserViewedEvent.setCreated(new Date());
        Payload payload = new Payload();
        payload.setProductId("someId" + Math.random());
        productUserViewedEvent.setPayload(payload);
    }

    @When("ProductUserViewed event was sent via API")
    public void sendProductUserViewedEvent() throws IOException, InterruptedException {
        client.sendActivityEvent(productUserViewedEvent);
    }

    @Then("data from ProductUserViewed event is published")
    public void verifyProductUserViewedEventIsPublished() {
        AsyncEventHelper.await(this::dataFromProductUserViewedMessageIsSaved, "Data saved");
    }

    private boolean dataFromProductUserViewedMessageIsSaved() {
        var messages = new ArrayList<>(TestingSession.getInstance().getMessages(KafkaChannels.PRODUCT_VIEWED_INPUT_CHANNEL));
        long count = messages
                .stream().filter(message -> dataForProductViewedMatch((ProductUserViewed) message, productUserViewedEvent)).count();

        return count > 0;
    }

    private boolean dataForProductViewedMatch(ProductUserViewed message, ActivityEvent activityEvent) {
        return message.getUserId().equals(activityEvent.getUserId())
                && message.getProductId().equals(activityEvent.getPayload().getProductId())
                && message.getCreated().equals(activityEvent.getCreated());
    }

    @Given("ProductUserAddedToCart event")
    public void createProductUserAddedToCartMessage() {
        productUserAddedToCartEvent = new ActivityEvent();
        productUserAddedToCartEvent.setType(ActivityEvent.Type.PRODUCT_ADDED_TO_CART);
        productUserAddedToCartEvent.setUserId("someId" + Math.random());
        productUserAddedToCartEvent.setCreated(new Date());
        Payload payload = new Payload();
        payload.setProductId("someId" + Math.random());
        productUserAddedToCartEvent.setPayload(payload);
    }

    @When("ProductUserAddedToCart event was sent via API")
    public void sendProductUserAddedToCartEvent() throws IOException, InterruptedException {
        client.sendActivityEvent(productUserAddedToCartEvent);
    }

    @Then("data from ProductUserAddedToCart event is published")
    public void verifyProductUserAddedToCartEventIsPublished() {
        AsyncEventHelper.await(this::dataFromProductUserAddedToCartMessageIsSaved, "Data saved");
    }

    private boolean dataFromProductUserAddedToCartMessageIsSaved() {
        var messages = new ArrayList<>(TestingSession.getInstance().getMessages(KafkaChannels.PRODUCT_ADDED_TO_CART_INPUT_CHANNEL));
        long count = messages
                .stream().filter(message -> dataForProductViewedMatch((ProductUserAddedToCart) message, productUserAddedToCartEvent)).count();

        return count > 0;
    }

    private boolean dataForProductViewedMatch(ProductUserAddedToCart message, ActivityEvent activityEvent) {
        return message.getUserId().equals(activityEvent.getUserId())
                && message.getProductId().equals(activityEvent.getPayload().getProductId())
                && message.getCreated().equals(activityEvent.getCreated());
    }
}
