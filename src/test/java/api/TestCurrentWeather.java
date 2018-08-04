package api;

import api.entities.WeatherParamRQ;
import api.entities.basic.Location;
import api.requests.WeatherRequest;
import api.response.WeatherResponse;
import api.utils.ReportWriter;
import api.utils.TestBase;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.FLOAT_AND_DOUBLE;

/**
 * Created by maxim on 1/22/2017.
 */
public class TestCurrentWeather extends TestBase {
    private Location location;
    private Header header;
    private ValidatableResponse weatherValidatableRS;
    private WeatherResponse weatherResponse;

    @BeforeClass
    public void before() {
        RestAssured.config = RestAssured.config().jsonConfig(jsonConfig().numberReturnType(FLOAT_AND_DOUBLE));
        header = new Header("Content-Type", "application/json");
    }

    @DataProvider
    public Object[][] city_country() {
        return new Object[][]{
                {"Kiev", "UA", "metric"},
                {"Los Angeles", "US", "imperial"},
                {"Paris", "FR", null},
        };
    }

    @Test (dataProvider = "city_country")
    public void testCurrentByCityCountry(String city, String country, String unit) {
        ReportWriter.logInfo("Get current weather in " + city + ", " + country);
        location = new Location(city, country);
        WeatherParamRQ weatherParamRQ = new WeatherParamRQ(location, apiKey, unit);
        WeatherRequest weatherRequest = new WeatherRequest(currentWeatherURL, header, weatherParamRQ);
        weatherValidatableRS = weatherRequest.send();
        weatherResponse = weatherValidatableRS.extract().as(WeatherResponse.class);
        weatherValidatableRS.
                assertThat().statusCode(200).
                assertThat().contentType(ContentType.JSON).
                assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonValidationSchema/currentWeather.json"));

        Assert.assertEquals(weatherResponse.getName(), city);
        Assert.assertEquals(weatherResponse.getSys().getCountry(), country);
    }
}
