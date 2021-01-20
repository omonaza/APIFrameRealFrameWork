package com.devxschool.summer.cucumber.testrunners.fooddelivery;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/food-delivery/UserRegistration.feature",
                "src/test/resources/features/food-delivery/UserRegistrationNegative.feature"
        },
        glue = {
                "com.devxschool.summer.cucumber.steps.fooddelivery.userregistration",
                "com.devxschool.summer.cucumber.steps.common"
        },
        tags = {
                "@userRegisterWithEmptyOrNullPassword"
        },
        dryRun = false
)
public class UserRegistrationTest { }
