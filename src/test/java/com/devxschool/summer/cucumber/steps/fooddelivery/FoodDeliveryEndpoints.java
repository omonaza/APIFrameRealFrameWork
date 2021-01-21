package com.devxschool.summer.cucumber.steps.fooddelivery;

import com.devxschool.summer.utility.PropertiesReader;
import com.devxschool.summer.utility.RestHttpRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class FoodDeliveryEndpoints {

    {
        RestAssured.baseURI = PropertiesReader.getPropertiesValue("foodDeliveryBaseUrl");
    }

    public static Response registerUser(String userJson) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userJson)
                .when()
                .request(String.valueOf(RestHttpRequest.HttpMethods.POST), "/user/registration");

        return response;
    }

    public static Response addFood(String foodRequestJson) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(foodRequestJson)
                .when()
                .request(String.valueOf(RestHttpRequest.HttpMethods.POST), "/food/cache/add");

        return response;
    }

    public static Response updateFood(String foodRequestJson, String foodName, String fieldName) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("name", foodName)
                .queryParam("field", fieldName)
                .body(foodRequestJson)
                .when()
                .request(String.valueOf(RestHttpRequest.HttpMethods.PUT), "/food/cache/update");

        return response;
    }

    public static Response commitFood() {
        Response response = when().request(String.valueOf(RestHttpRequest.HttpMethods.POST), "/food/commit");

        return response;
    }
}
