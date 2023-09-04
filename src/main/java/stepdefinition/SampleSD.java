package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class SampleSD {
    RequestSpecification requestSpec;
    RequestSpecification listUserRequest;
    Response response;
    @Given("List User given request is created with query parameter")
    public void listUserGivenRequestIsCreatedWithQueryParameter() {

          requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/")
                .addQueryParam("page", "2")
                .setAccept("applicaation/json")
                .build();

         listUserRequest = given().log().all().spec(requestSpec);
    }

    @When("user calls List user Request with Get http request")
    public void userCallsListUserRequestWithGetHttpRequest() {
         response = listUserRequest.when().get("/api/users");

    }

    @Then("the API call got success with status code {int}")
    public void theAPICallGotSuccessWithStatusCode(int statusCode) {

        ResponseSpecification responseSpecification
                = new ResponseSpecBuilder().expectStatusCode(statusCode).build();

        String result = response.then().log().all().spec(responseSpecification).extract().asString();

        System.out.println(result);
    }
}
