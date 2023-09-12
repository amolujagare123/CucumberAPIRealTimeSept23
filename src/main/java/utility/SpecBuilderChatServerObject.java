package utility;

import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;

import java.io.IOException;

import static utility.ConfigReader.getUrl;

public class SpecBuilderChatServerObject {





    public static RequestSpecBuilder getChatUserSpec() throws IOException {

        PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
        auth.setUserName("admin");
        auth.setPassword("admin123");

        RequestSpecBuilder requestSpec = new RequestSpecBuilder()
                .setBaseUri(getUrl())
                .setAuth(auth)
                .setAccept("application/json")
                .setContentType("application/json");


        return requestSpec;
    }
}
