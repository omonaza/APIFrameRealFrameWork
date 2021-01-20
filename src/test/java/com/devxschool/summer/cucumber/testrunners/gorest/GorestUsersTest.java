package com.devxschool.summer.cucumber.testrunners.gorest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
            "src/test/resources/features/gorest/Users.feature"
        },
        glue = {
            "com.devxschool.summer.cucumber.steps.gorest.users"
        },
        tags = {
            "@successfullyCreateUser"
        },
        dryRun = false
)
public class GorestUsersTest {
}
