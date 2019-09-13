package com.adidas.subscriber.functional;

public final class EnvironmentContainer {
    private static FunctionalTestSuite environment;

    private EnvironmentContainer() { }

    public static FunctionalTestSuite getEnvironment() {
        return environment;
    }

    static void setEnvironment(FunctionalTestSuite environment) {
        EnvironmentContainer.environment = environment;
    }
}
