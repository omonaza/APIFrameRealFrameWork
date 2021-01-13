package com.devxschool.summer.cucumber.testrunners.fooddelivery;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/food-delivery/UserRegistration.feature"
        },
        glue = {
                "com.devxschool.summer.cucumber.steps.userregistration"
        },
        tags = {
                "@userSuccessfullyRegistered"
        },
        dryRun = false
)
public class UserRegistrationTest { }
