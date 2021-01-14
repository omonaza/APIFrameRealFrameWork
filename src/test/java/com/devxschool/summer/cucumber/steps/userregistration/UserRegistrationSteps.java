package com.devxschool.summer.cucumber.steps.userregistration;

import com.devxschool.summer.pojos.fooddelivery.UserRegistrationRequest;
import com.devxschool.summer.pojos.fooddelivery.UserRegistrationResponse;
import com.google.gson.Gson;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserRegistrationSteps {

    private Response response;
    private Gson gson;

    @Before
    public void setUp() {
        gson = new Gson();
        RestAssured.baseURI = "http://localhost:8082";
    }

    @Given("^user registers to food delivery app with the following fields:$")
    public void user_registers_to_food_delivery_app_with_the_following_fields(List<UserRegistrationRequest> usersToRegister) throws Throwable {
        String userJson = gson.toJson(usersToRegister.get(0));

        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userJson)
                .when()
                    .request("POST", "/user/registration");
    }

    @Then("^verify that status code is (\\d+)$")
    public void verify_that_status_code_is(int statusCode) throws Throwable {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @Then("^verify that response message is \"([^\"]*)\"$")
    public void verify_that_response_message_is(String responseMessage) throws Throwable {
        UserRegistrationResponse userRegistrationResponse = gson.fromJson(response.body().asString(), UserRegistrationResponse.class);

        Assert.assertEquals(responseMessage, userRegistrationResponse.getStatus());
    }

}
