package api.utils;

import api.configEntities.ConfigEntity;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.util.Map;

/**
 * Created by maxim on 1/21/2017.
 */

@Listeners(CustomTestListener.class)
public class TestBase {
    protected static String baseURL;
    protected static Map<String, String> methods;
    protected static String apiKey;
    protected static String currentWeatherURL;
    protected static String forecastURL;
    protected static String forecastDailyURL;
    protected static String historyURL;

    @BeforeSuite(groups = {"Config"})
    public void setUp(){
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        setMethodsUrl();
        getAccessToken();
    }

    private void setMethodsUrl() {
        ConfigEntity param = YamlFileReader.fromYaml(ConfigEntity.class, System.getProperty("user.dir") + "/src/test/config/apiConfig.yml");
        baseURL = param.getBaseURL();
        methods = param.getMethods();
        currentWeatherURL = methods.get("currentWeatherURL");
        forecastURL = methods.get("forecastURL");
        forecastDailyURL = methods.get("forecastDailyURL");
        historyURL = methods.get("historyURL");
    }

    private void getAccessToken(){
        ConfigEntity param = YamlFileReader.fromYaml(ConfigEntity.class, System.getProperty("user.dir") + "/src/test/config/apiConfig.yml");
        apiKey = param.getApiKey();
    }
}
