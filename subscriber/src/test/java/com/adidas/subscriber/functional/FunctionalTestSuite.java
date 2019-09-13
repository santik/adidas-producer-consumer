package com.adidas.subscriber.functional;

import com.adidas.subscriber.functional.stories.ListensToTopicsAndSavesDataFromMessages;
import com.adidas.subscriber.redis.repository.CategoryUserViewedRepository;
import com.adidas.subscriber.redis.repository.ProductUserAddedToCartRepository;
import com.adidas.subscriber.redis.repository.ProductUserViewedRepository;
import net.serenitybdd.jbehave.SerenityStories;
import org.jbehave.core.annotations.BeforeStories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ListensToTopicsAndSavesDataFromMessages.class
})
@SpringFunctionalTestEnvironment
public class FunctionalTestSuite extends SerenityStories {

    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionalTestSuite.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ProductUserViewedRepository productUserViewedRepository;

    @Autowired
    private CategoryUserViewedRepository categoryUserViewedRepository;

    @Autowired
    private ProductUserAddedToCartRepository productUserAddedToCartRepository;

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @PostConstruct
    public void init() {
        EnvironmentContainer.setEnvironment(this);
    }

    @BeforeStories
    public void setUp() {
        final String titleMargins = "\n=========================================================";
        final String title = "\n>>>>>>>>>>> STARTING THE FUNCTIONAL TEST SUITE";
        LOGGER.info("{}{}{}", titleMargins, title, titleMargins);
    }

    public ProductUserViewedRepository getProductUserViewedRepository() {
        return productUserViewedRepository;
    }

    public CategoryUserViewedRepository getCategoryUserViewedRepository() {
        return categoryUserViewedRepository;
    }

    public ProductUserAddedToCartRepository getProductUserAddedToCartRepository() {
        return productUserAddedToCartRepository;
    }
}
