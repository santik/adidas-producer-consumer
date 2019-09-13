package com.adidas.subscriber.functional.steps;

import com.adidas.generated.CategoryUserViewed;
import com.adidas.generated.ProductUserAddedToCart;
import com.adidas.generated.ProductUserViewed;
import com.adidas.subscriber.functional.AsyncEventHelper;
import com.adidas.subscriber.functional.EnvironmentContainer;
import com.adidas.subscriber.functional.TestingConfiguration;
import com.adidas.subscriber.functional.kafka.CategoryViewedTestPublisher;
import com.adidas.subscriber.functional.kafka.ProductAddedToCartTestPublisher;
import com.adidas.subscriber.functional.kafka.ProductViewedTestPublisher;
import com.adidas.subscriber.redis.model.CategoryUserViewedModel;
import com.adidas.subscriber.redis.model.ProductUserAddedToCartModel;
import com.adidas.subscriber.redis.model.ProductUserViewedModel;
import com.adidas.subscriber.redis.repository.CategoryUserViewedRepository;
import com.adidas.subscriber.redis.repository.ProductUserAddedToCartRepository;
import com.adidas.subscriber.redis.repository.ProductUserViewedRepository;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.config.BinderFactoryConfiguration;
import org.springframework.cloud.stream.config.BindingServiceConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;
import java.util.Optional;

@ContextConfiguration(classes = {
        BinderFactoryConfiguration.class,
        BindingServiceConfiguration.class,
        TestingConfiguration.class,
})
@TestPropertySource(locations = {"classpath:application-functional.properties"})
public class ListensToTopicsAndSavesSteps {

    @Autowired
    private ProductViewedTestPublisher productViewedTestPublisher;

    @Autowired
    private ProductAddedToCartTestPublisher productAddedToCartTestPublisher;

    @Autowired
    private CategoryViewedTestPublisher categoryViewedTestPublisher;

    private ProductUserViewed productUserViewed;
    private ProductUserAddedToCart productUserAddedToCart;
    private CategoryUserViewed categoryUserViewed;

    @Given("ProductUserViewed message")
    public void createProductUserViewedMessage() {
        productUserViewed = new ProductUserViewed();
        productUserViewed.setUserId("someUserId" + Math.random());
        productUserViewed.setProductId("someProductId" + Math.random());
        productUserViewed.setCreated(new Date());
    }

    @When("ProductUserViewed message is published")
    public void publishProductUserViewedMessage() {
        productViewedTestPublisher.publish(productUserViewed);
    }

    @Then("data from ProductUserViewed message is saved")
    public void verifyProductUserViewedMessageSaved() {
        AsyncEventHelper.await(this::dataFromProductUserViewedMessageIsSaved, "Data saved");
    }

    private boolean dataFromProductUserViewedMessageIsSaved() {
        ProductUserViewedRepository productUserViewedRepository = EnvironmentContainer.getEnvironment().getProductUserViewedRepository();

        ProductUserViewedModel productUserViewedModelExpected = ProductUserViewedModel.createFromKafkaMessage(productUserViewed);
        Optional<ProductUserViewedModel> order = productUserViewedRepository.findById(productUserViewedModelExpected.getId());
        return order.isPresent() && order.get().getProductId().equals(productUserViewed.getProductId());
    }

    @Given("ProductUserAddedToCart message")
    public void createProductUserAddedToCartMessage() {
        productUserAddedToCart = new ProductUserAddedToCart();
        productUserAddedToCart.setUserId("someUserId" + Math.random());
        productUserAddedToCart.setProductId("someProductId" + Math.random());
        productUserAddedToCart.setCreated(new Date());
    }

    @When("ProductUserAddedToCart message is published")
    public void publishProductUserAddedToCartMessage() {
        productAddedToCartTestPublisher.publish(productUserAddedToCart);
    }

    @Then("data from ProductUserAddedToCart message is saved")
    public void verifyProductUserAddedToCartMessageSaved() {
        AsyncEventHelper.await(this::dataFromProductUserAddedToCartMessageIsSaved, "Data saved");
    }

    private boolean dataFromProductUserAddedToCartMessageIsSaved() {
        ProductUserAddedToCartRepository productUserAddedToCartRepository = EnvironmentContainer.getEnvironment().getProductUserAddedToCartRepository();

        ProductUserAddedToCartModel productUserAddedToCartModelExpected = ProductUserAddedToCartModel.createFromKafkaMessage(productUserAddedToCart);
        Optional<ProductUserAddedToCartModel> order = productUserAddedToCartRepository.findById(productUserAddedToCartModelExpected.getId());
        return order.isPresent() && order.get().getProductId().equals(productUserAddedToCart.getProductId());
    }

    @Given("CategoryUserViewed message")
    public void createCategoryUserViewedMessage() {
        categoryUserViewed = new CategoryUserViewed();
        categoryUserViewed.setUserId("someUserId" + Math.random());
        categoryUserViewed.setCategoryId("someCategoryId" + Math.random());
        categoryUserViewed.setCreated(new Date());
    }

    @When("CategoryUserViewed message is published")
    public void publishCategoryUserViewedMessage() {
        categoryViewedTestPublisher.publish(categoryUserViewed);
    }

    @Then("data from CategoryUserViewed message is saved")
    public void verifyCategoryUserViewedMessageSaved() {
        AsyncEventHelper.await(this::dataFromCategoryUserViewedMessageIsSaved, "Data saved");
    }

    private boolean dataFromCategoryUserViewedMessageIsSaved() {
        CategoryUserViewedRepository categoryUserViewedRepository = EnvironmentContainer.getEnvironment().getCategoryUserViewedRepository();

        CategoryUserViewedModel categoryUserViewedModelExpected = CategoryUserViewedModel.createFromKafkaMessage(categoryUserViewed);
        Optional<CategoryUserViewedModel> order = categoryUserViewedRepository.findById(categoryUserViewedModelExpected.getId());
        return order.isPresent() && order.get().getCategoryId().equals(categoryUserViewed.getCategoryId());
    }
}
