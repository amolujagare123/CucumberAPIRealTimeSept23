package utility;

import io.restassured.path.json.JsonPath;

public class JsonPathUtil {

    public static  Object getJsonObject(String jsonResult, String jsonPath)
    {
        JsonPath myJson = new JsonPath(jsonResult);
        return myJson.get(jsonPath);
    }
}
