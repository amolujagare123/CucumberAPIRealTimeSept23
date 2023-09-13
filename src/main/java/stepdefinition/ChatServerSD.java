package stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static utility.JsonPathUtil.getJsonObject;
import static utility.POJO.chatServer.GetObject.CreateUserObject.getCreateUserPojoObject;
import static utility.SpecBuilderChatServerObject.getChatUserSpec;

public class ChatServerSD {
    RequestSpecification requestSpec;
    static RequestSpecification listUserRequest;
    Response response;
    String resultStr;
    static RequestSpecification createUserRequest;
    @Given("List User given request for chat server is created with query parameter")
    public void createRequestWithQueryParameter() throws IOException {

         requestSpec = getChatUserSpec()
                .build();

         listUserRequest = given().log().all().spec(requestSpec);

    }

    @When("user calls List user Request for chat server with Get http request")
    public void callGetRequest() {
         response = listUserRequest.when().get("/restapi/getusers");
    }

    @Then("the API call for chat server got success with status code {int}")
    public void verifyStatusCode(int statusCode) {

        ResponseSpecification chatUserResponse = new ResponseSpecBuilder()
                .expectStatusCode(statusCode).build();

         resultStr = response.then().log().all().spec(chatUserResponse).extract().asString();

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

    @Given("create User given request for chat server is created")
    public void create_user_given_request_for_chat_server_is_created() throws IOException {
        requestSpec = getChatUserSpec()
                .build();

         createUserRequest = given().log().all().spec(requestSpec)
                .body(getCreateUserPojoObject("parmeshwari1122", "p1234",
                        "p@gmail.com", "Parameshwari", "xyz", "p111"));
    }
    @When("user calls create user Request for chat server with post http request")
    public void user_calls_create_user_request_for_chat_server_with_post_http_request() {
        response = createUserRequest.when().post("/restapi/user");
    }
    @Then("{string} should be {string} in the json output")
    public void should_be_in_the_json_output(String jsonPath, String expectedValue) {

       String actualValue = (String) getJsonObject(resultStr,jsonPath);

        System.out.println("expected="+expectedValue);
        System.out.println("actual="+actualValue);

        Assert.assertEquals("Value does not match",expectedValue,actualValue);
    }

    @When("user calls {string} for chat server with {string} http request")
    public void userCallsForChatServerWithHttpRequest(String requestType, String method) {

        // response = createUserRequest.when().post("/restapi/user");

        switch (requestType) {
            case "listUser":
                response = createUserRequest.when().post("/restapi/user");
                break;
            case "createUser":
                response = listUserRequest.when().get("/restapi/getusers");
                break;
        }
    }
}
