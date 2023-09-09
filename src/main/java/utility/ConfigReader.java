package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static Properties getLoadedPropertiesObject() throws IOException {
        FileInputStream fis = new FileInputStream("Config/Config.properties");
        Properties prop = new Properties();
        prop.load(fis);
        return  prop;
    }

    public static String getUrl() throws IOException {
        return getLoadedPropertiesObject().getProperty("url");
    }
}
