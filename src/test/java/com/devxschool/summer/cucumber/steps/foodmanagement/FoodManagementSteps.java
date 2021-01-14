package com.devxschool.summer.cucumber.steps.foodmanagement;

import com.google.gson.Gson;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FoodManagementSteps {
    private Gson gson;
    private Response response;

    @Before
    public void setUp() {
        gson = new Gson();
        RestAssured.baseURI = "http://localhost:8082";
    }
}
