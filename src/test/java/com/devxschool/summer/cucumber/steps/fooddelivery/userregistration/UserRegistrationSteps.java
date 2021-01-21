package com.devxschool.summer.cucumber.steps.fooddelivery.userregistration;

import com.devxschool.summer.cucumber.steps.common.CommonData;
import com.devxschool.summer.cucumber.steps.fooddelivery.FoodDeliveryEndpoints;
import com.devxschool.summer.pojos.fooddelivery.UserRegistrationRequest;
import com.devxschool.summer.pojos.fooddelivery.UserRegistrationResponse;
import com.devxschool.summer.utility.ObjectConverter;
import com.devxschool.summer.utility.PropertiesReader;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserRegistrationSteps {
    private CommonData commonData;

    public UserRegistrationSteps(CommonData commonData) {
        this.commonData = commonData;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = PropertiesReader.getPropertiesValue("foodPropertiesBaseUrl");
    }

    @Given("^user registers to food delivery app with the following fields:$")
    public void user_registers_to_food_delivery_app_with_the_following_fields(List<UserRegistrationRequest> usersToRegister) throws Throwable {
        // Serializing usersToRegister element to the JSON String
        String userJson = ObjectConverter.convertObjectToJson(usersToRegister.get(0));

        commonData.response = FoodDeliveryEndpoints.registerUser(userJson);
    }

    /*
     * Cucumber can't automatically convert complex jsons/data tables into POJO. Use List of maps.
     *
     * */
    @Then("^the following user has been registered:$")
    public void verify_that_response_message_is(List<Map<String, String>> expectedUser) throws Throwable {
        // Deserializing response body JSON String to UserRegistrationResponse object
        UserRegistrationResponse userRegistrationResponse = ObjectConverter.convertJsonToObject(commonData.response.body().asString(), UserRegistrationResponse.class);

        Assert.assertEquals(expectedUser.get(0).get("status"), userRegistrationResponse.getStatus());
        Assert.assertEquals(expectedUser.get(0).get("username"), userRegistrationResponse.getUserInfo().getUsername());
        Assert.assertEquals(expectedUser.get(0).get("fullName"), userRegistrationResponse.getUserInfo().getUserProfile().getFullName());
    }

    @Then("^the following error message has been returned:$")
    public void verifyErrorMessage(List<Map<String, String>> expectedErrorMessage) {
        UserRegistrationResponse userRegistrationResponse = ObjectConverter.convertJsonToObject(commonData.response.body().asString(), UserRegistrationResponse.class);

        Assert.assertEquals(expectedErrorMessage.get(0).get("status"), userRegistrationResponse.getStatus());
        Assert.assertEquals(expectedErrorMessage.get(0).get("errorMessage"), userRegistrationResponse.getErrorMessage());
    }

}
