package com.adidas.producer.functional;

import com.adidas.producer.functional.stories.PublishesMessagesWithDataFromApi;
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
        PublishesMessagesWithDataFromApi.class
})
@SpringFunctionalTestEnvironment
public class FunctionalTestSuite extends SerenityStories {

    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionalTestSuite.class);

    @Autowired
    private ApplicationContext context;

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
}
