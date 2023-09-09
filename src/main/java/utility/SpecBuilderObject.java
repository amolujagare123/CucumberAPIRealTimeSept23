package utility;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

import static utility.ConfigReader.getUrl;

public class SpecBuilderObject {


    public static RequestSpecBuilder getSampleUserSpec() throws IOException {
        RequestSpecBuilder requestSpec = new RequestSpecBuilder()
                .setBaseUri(getUrl())
                .setAccept("application/json")
                .setContentType("application/json");


        return requestSpec;
    }
}
