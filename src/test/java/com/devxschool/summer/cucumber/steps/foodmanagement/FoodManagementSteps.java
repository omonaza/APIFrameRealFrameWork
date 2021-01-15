package com.devxschool.summer.cucumber.steps.foodmanagement;

import com.devxschool.summer.pojos.fooddelivery.FoodRequest;
import com.devxschool.summer.pojos.fooddelivery.FoodResponse;
import com.google.gson.Gson;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class FoodManagementSteps {
    private Gson gson;
    private Response response;

    @Before
    public void setUp() {
        gson = new Gson();
        RestAssured.baseURI = "http://localhost:8082";
    }

    @Given("^add new food to FoodDelivery with the following fields$")
    public void addNewFoodToFoodDeliveryWithTheFollowingFields(List<FoodRequest> foodRequest) {
        String foodRequestJson = gson.toJson(foodRequest.get(0));

        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(foodRequestJson)
                .when()
                .request("POST", "/food/cache/add");
    }

    @Then("^verify that status code is (\\d+)$")
    public void verify_that_status_code_is(int statusCode) throws Throwable {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @Then("^the following food has been added:$")
    public void theFollowingFoodHasBeenAdded(List<FoodRequest> expectedFood) {
        FoodResponse actualFood = gson.fromJson(response.body().asString(), FoodResponse.class);

        Assert.assertEquals(expectedFood.get(0).getDescription(), actualFood.getFoodCached().get(0).getDescription());
        Assert.assertEquals(expectedFood.get(0).getFoodType(), actualFood.getFoodCached().get(0).getFoodType());
        Assert.assertEquals(expectedFood.get(0).getImageUrl(), actualFood.getFoodCached().get(0).getImageUrl());
        Assert.assertEquals(expectedFood.get(0).getPrice(), actualFood.getFoodCached().get(0).getPrice());
        Assert.assertEquals(expectedFood.get(0).getName(), actualFood.getFoodCached().get(0).getName());
    }

    @Then("^verify response error message \"([^\"]*)\"$")
    public void verify_response_error_message(String expectedErrorMessage) throws Throwable {
        Assert.assertEquals(expectedErrorMessage, response.body().jsonPath().getString("errorMessage"));
    }

    @When("^food entry \"([^\"]*)\" is updated with the following fields$")
    public void updateFood(String fieldName, List<FoodRequest> foodRequests) {
        String foodRequestJson = gson.toJson(foodRequests.get(0));

        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("name", foodRequests.get(0).getName())
                .queryParam("field", fieldName)
                .body(foodRequestJson)
                .when()
                .request("PUT", "/food/cache/update");
    }

    private void clearFoodCache() {
        when().request("POST", "/food/commit");
    }

    @After
    public void cleanUp() {
        clearFoodCache();
    }
}
