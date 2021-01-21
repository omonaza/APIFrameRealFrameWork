package com.devxschool.summer.cucumber.steps.fooddelivery.foodmanagement;

import com.devxschool.summer.cucumber.steps.common.CommonData;
import com.devxschool.summer.cucumber.steps.fooddelivery.FoodDeliveryEndpoints;
import com.devxschool.summer.pojos.fooddelivery.FoodRequest;
import com.devxschool.summer.pojos.fooddelivery.FoodResponse;
import com.devxschool.summer.utility.ObjectConverter;
import com.devxschool.summer.utility.PropertiesReader;
import com.devxschool.summer.utility.RestHttpRequest;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;

import static io.restassured.RestAssured.when;

public class FoodManagementSteps {
    private CommonData commonData;

    public FoodManagementSteps(CommonData commonData) {
        this.commonData = commonData;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = PropertiesReader.getPropertiesValue("foodDeliveryBaseUrl");
    }

    @Given("^add new food to FoodDelivery with the following fields$")
    public void addNewFoodToFoodDeliveryWithTheFollowingFields(List<FoodRequest> foodRequest) {
        String foodRequestJson = ObjectConverter.convertObjectToJson(foodRequest.get(0));

        commonData.response = FoodDeliveryEndpoints.addFood(foodRequestJson);
    }

    @Then("^the following food has been added:$")
    public void theFollowingFoodHasBeenAdded(List<FoodRequest> expectedFood) {
        FoodResponse actualFood = ObjectConverter.convertJsonToObject(commonData.response.body().asString(), FoodResponse.class);

        Assert.assertEquals(expectedFood.get(0).getDescription(), actualFood.getFoodCached().get(0).getDescription());
        Assert.assertEquals(expectedFood.get(0).getFoodType(), actualFood.getFoodCached().get(0).getFoodType());
        Assert.assertEquals(expectedFood.get(0).getImageUrl(), actualFood.getFoodCached().get(0).getImageUrl());
        Assert.assertEquals(expectedFood.get(0).getPrice(), actualFood.getFoodCached().get(0).getPrice());
        Assert.assertEquals(expectedFood.get(0).getName(), actualFood.getFoodCached().get(0).getName());
    }

    @Then("^verify response error message \"([^\"]*)\"$")
    public void verify_response_error_message(String expectedErrorMessage) throws Throwable {
        Assert.assertEquals(expectedErrorMessage, commonData.response.body().jsonPath().getString("errorMessage"));
    }

    @When("^food entry \"([^\"]*)\" is updated with the following fields$")
    public void updateFood(String fieldName, List<FoodRequest> foodRequests) {
        String foodRequestJson = ObjectConverter.convertObjectToJson(foodRequests.get(0));

        commonData.response = FoodDeliveryEndpoints.updateFood(foodRequestJson, foodRequests.get(0).getName(), fieldName);
    }

    private void clearFoodCache() {
        FoodDeliveryEndpoints.commitFood();
    }

    @After
    public void cleanUp() {
        clearFoodCache();
    }
}
