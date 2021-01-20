package com.devxschool.summer.cucumber.steps.gorest.users;

import com.devxschool.summer.cucumber.steps.common.CommonData;
import com.devxschool.summer.pojos.gorest.UserData;
import com.devxschool.summer.pojos.gorest.UserResponse;
import com.devxschool.summer.utility.ObjectConverter;
import com.devxschool.summer.utility.PropertiesReader;
import com.devxschool.summer.utility.RestHttpRequest;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;

public class UsersSteps {
    private CommonData commonData;

    public  UsersSteps(CommonData commonData) {
        this.commonData = commonData;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = PropertiesReader.getPropertiesValue("gorestBaseUrl");
        RestHttpRequest.requestSpecification.header("Authorization", PropertiesReader.getPropertiesValue("gorestBearerToken"));
    }

    @Given("^the following user has been created:$")
    public void the_following_user_has_been_created(List<UserData> userRequest) throws Throwable {
        String json = ObjectConverter.convertObjectToJson(userRequest.get(0));

        RestHttpRequest.addHeaders();

        commonData.response = RestHttpRequest.requestSpecification
                .body(json)
                .when()
                .request(String.valueOf(RestHttpRequest.HttpMethods.POST), "/users");
    }

    @Then("^the following user has been returned:$")
    public void the_following_user_has_been_returned(List<UserData> expectedUser) throws Throwable {
        UserResponse userResponse = ObjectConverter.convertJsonToObject(commonData.response.body().asString(), UserResponse.class);

        Assert.assertEquals(expectedUser.get(0).getEmail(), userResponse.getData().getEmail());
        Assert.assertEquals(expectedUser.get(0).getGender(), userResponse.getData().getGender());
        Assert.assertEquals(expectedUser.get(0).getName(), userResponse.getData().getName());
        Assert.assertEquals(expectedUser.get(0).getStatus(), userResponse.getData().getStatus());
    }

    private void deleteUser() {
        UserResponse userResponse = ObjectConverter.convertJsonToObject(commonData.response.body().asString(), UserResponse.class);

        RestHttpRequest.requestSpecification
                .pathParam("userId", userResponse.getData().getId())
                .when()
                .request(String.valueOf(RestHttpRequest.HttpMethods.DELETE), "/users/{userId}");
    }

    @After
    public void cleanUp() {
        deleteUser();
    }

}
