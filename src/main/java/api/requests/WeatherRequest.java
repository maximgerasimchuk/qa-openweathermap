package api.requests;

import api.entities.WeatherParamRQ;
import api.utils.JsonUtil;
import api.utils.ReportWriter;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.ValidatableResponse;

import java.util.Map;

/**
 * Created by maxim on 1/21/2017.
 */
public class WeatherRequest {
    private String URL;
    private Header header;
    private Map<String, String> parametersMap;

    public WeatherRequest(String URL, Header header, WeatherParamRQ weatherParamRQ) {
        this.URL = URL;
        this.header = header;
        this.parametersMap = weatherParamRQ.setParameters();
    }

    public Map<String, String> getParametersMap() {
        return parametersMap;
    }

    public void setParametersMap(Map<String, String> parametersMap) {
        this.parametersMap = parametersMap;
    }

    public ValidatableResponse send() {
        ValidatableResponse response = RestAssured.
                given().
                    baseUri(URL).
                    header(header).
                    params(parametersMap).
                when().
                get().
                then();
        ReportWriter.logInfo("Weather Request: " + parametersMap.entrySet().toString());
        JsonUtil.jsonToFile("Response", response.extract().asString());
        return response;
    }

}
