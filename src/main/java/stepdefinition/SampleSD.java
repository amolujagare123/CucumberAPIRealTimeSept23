package stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import utility.POJO.CreateUserRequestBody;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static utility.JsonPathUtil.getJsonObject;
import static utility.SpecBuilderObject.getSampleUserSpec;


public class SampleSD {
    RequestSpecification requestSpec;
    RequestSpecification listUserRequest;
    static Response response;
    static String result;
    CreateUserRequestBody createUserRequestBody;
    static ResponseSpecification responseSpecification;
    RequestSpecification createUserRequest;

    @Given("List User given request is created with query parameter")
    public void listUserGivenRequestIsCreatedWithQueryParameter() throws IOException {

        requestSpec = getSampleUserSpec()
                .addQueryParam("page", "2")
                .build();

        listUserRequest = given().log().all().spec(requestSpec);
    }

    @When("user calls List user Request with Get http request")
    public void userCallsListUserRequestWithGetHttpRequest() {
        response = listUserRequest.when().get("/api/users");

    }

    @Then("the API call got success with status code {int}")
    public void theAPICallGotSuccessWithStatusCode(int statusCode) {

        responseSpecification
                = new ResponseSpecBuilder()
                .expectStatusCode(statusCode).build();

        result = response.then().log().all().spec(responseSpecification).extract().asString();

        System.out.println(result);
    }

    @And("total users should be {int}")
    public void totalUsersShouldBe(int expectedUsers) {


        int actual = (Integer) getJsonObject(result,"data.size()");

       /* Integer i = 10;
        i.intValue(); //  unboxing /  unwrapping
        int x = i; // auto unboxing / auto unwrapping
        */

        System.out.println(actual);
        Assert.assertEquals("", expectedUsers, actual);
    }

    @Given("Create User given request is created")
    public void createUserGivenRequestIsCreated() throws IOException {

        requestSpec = getSampleUserSpec().build();

      /*  createUserRequest = given().log().all().spec(requestSpec)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}");*/
        CreateUserRequestBody ob = new CreateUserRequestBody();
        ob.setName("Dev");
        ob.setJob("Trainer");

        createUserRequest = given().log().all().spec(requestSpec)
                .body(ob);
    }

    @When("user calls List user Request with POST http request")
    public void userCallsListUserRequestWithPOSTHttpRequest() {
        response = createUserRequest.when().post("api/users");
    }

    @And("total should be {int} users")
    public void totalShouldBeUsers(int expected) {

        JsonPath jsonPath = new JsonPath(result);
        int actual = jsonPath.getInt("total");

        Assert.assertEquals("wrong expected or actual", expected, actual);
    }

    @And("Job should be leader")
    public void jobShouldBeLeader() {
        String actual = (String) getJsonObject(result,"job");
        System.out.println(actual);
        Assert.assertEquals("", "leader", actual);
    }

    @And("{string} should be {string}")
    public void shouldBe(String jsonPath, String expected) {

        String actual = (String) getJsonObject(result,jsonPath);
        System.out.println(actual);
        Assert.assertEquals("", expected, actual);
    }

    @Given("^Create User given request is created with (.+) and (.+)$")
    public void createUserGivenRequestIsCreatedWithAnd(String name, String job) throws IOException {

        requestSpec = getSampleUserSpec().build();

        createUserRequestBody = new CreateUserRequestBody();
        createUserRequestBody.setName(name);
        createUserRequestBody.setJob(job);

        createUserRequest = given().log().all().spec(requestSpec)
                .body(createUserRequestBody);
    }

   /* @And("{string} is as expected")
    public void isAsExpected(String jsonPath) {

        String expected = createUserRequestBody.ge

    }*/
}
