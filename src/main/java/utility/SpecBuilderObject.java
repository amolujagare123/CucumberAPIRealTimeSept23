package utility;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class SpecBuilderObject {

    static String baseUrl = "https://reqres.in/";
    public static RequestSpecBuilder getSampleUserSpec()
    {
        RequestSpecBuilder requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setAccept("application/json")
                .setContentType("application/json");


        return requestSpec;
    }
}
