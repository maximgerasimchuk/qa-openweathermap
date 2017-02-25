package api.entities;

import api.entities.basic.Location;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maxim on 1/21/2017.
 */
public class WeatherParamRQ {
    private Map<String, String> parametersMap;
    private String appid;
    private String apiKey;
    private Location location;
    private String q; // (allow cityName || (cityName && countryCode))
    private String id;
    private String lat;
    private String lon;
    private String zip; //allow (zipCode && countryCode)
    private String units;
    private String unitValue;

    public WeatherParamRQ(Location location, String apiKey, String unit) {
        this.location = location;
        this.q = "q";
        this.id = "id";
        this.lat = "lat";
        this.lon = "lon";
        this.zip = "zip";
        this.appid = "appid";
        this.units = "units";
        this.unitValue = unit;
        this.apiKey = apiKey;
    }

    public Map<String, String> setParameters() {
        parametersMap = new HashMap<>();
        if (apiKey != null) {
            parametersMap.put(appid, apiKey);
        }
        if (unitValue != null){
            parametersMap.put(units, unitValue);
        }
        if (location.getCityName() != null) {
            parametersMap.put(q, location.getCityName());
        }
        if (location.getCityName() != null && location.getCountryCode() != null) {
            parametersMap.put(q, location.getCityName() + "," + location.getCountryCode());
        }
        if (location.getCityId() != null) {
            parametersMap.put(id, location.getCityId());
        }
        if (location.getLatitude() != null && location.getLongitude() != null) {
            parametersMap.put(lat, String.valueOf(location.getLatitude()));
        }
        if (location.getLongitude() != null) {
            parametersMap.put(lon, String.valueOf(location.getLongitude()));
        }
        if (location.getZipCode() != null) {
            parametersMap.put(zip, location.getZipCode());
        }

        return parametersMap;
    }
}
