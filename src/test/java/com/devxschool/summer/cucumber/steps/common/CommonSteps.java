package com.devxschool.summer.cucumber.steps.common;

import cucumber.api.java.en.Then;
import org.junit.Assert;

public class CommonSteps {
    private CommonData commonData;

    public CommonSteps(CommonData commonData) {
        this.commonData = commonData;
    }

    @Then("^verify that status code is (\\d+)$")
    public void verify_that_status_code_is(int statusCode) throws Throwable {
        Assert.assertEquals(statusCode, commonData.response.getStatusCode());
    }
}
