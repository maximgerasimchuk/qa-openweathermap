package api.entities.basic;

/**
 * Created by maxim on 1/21/2017.
 */
public class Location {
    private String cityId;
    private String cityName;
    private String zipCode;
    private String countryCode;
    private Double latitude;
    private Double longitude;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Location(){

    }

    public Location(String cityName){
        this.cityName = cityName;
    }

    public Location(String cityName, String countryCode){
        this.cityName = cityName;
        this.countryCode = countryCode;
    }


}
