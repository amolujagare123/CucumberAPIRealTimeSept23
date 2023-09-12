package stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static utility.JsonPathUtil.getJsonObject;
import static utility.SpecBuilderChatServerObject.getChatUserSpec;
import static utility.SpecBuilderObject.getSampleUserSpec;

public class ChatServerSD {
    RequestSpecification requestSpec;
    RequestSpecification listUserRequest;
    Response resource;
    String resultStr;
    @Given("List User given request for chat server is created with query parameter")
    public void createRequestWithQueryParameter() throws IOException {

         requestSpec = getChatUserSpec()
                .build();

         listUserRequest = given().log().all().spec(requestSpec);

    }

    @When("user calls List user Request for chat server with Get http request")
    public void callGetRequest() {
         resource = listUserRequest.when().get("/restapi/getusers");
    }

    @Then("the API call for chat server got success with status code {int}")
    public void verifyStatusCode(int statusCode) {

        ResponseSpecification listChatUserResponse = new ResponseSpecBuilder()
                .expectStatusCode(statusCode).build();

         resultStr = resource.then().log().all().spec(listChatUserResponse).extract().asString();

    }

    @And("total users for chat server should be {int}")
    public void verifyTotalUsers(int expectedUsers) {

    int actualUsers =  (Integer) getJsonObject(resultStr,"result.size()");
        System.out.println("expected="+expectedUsers);
        System.out.println("actualUsers="+actualUsers);

        Assert.assertEquals("actual users are different",expectedUsers,actualUsers);
    }

    /*@Then("the API call for chat server  got success with status code {int}")
    public void theAPICallForChatServerGotSuccessWithStatusCode(int arg0) {
    }*/
}
