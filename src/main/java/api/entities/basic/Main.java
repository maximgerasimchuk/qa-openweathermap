package api.entities.basic;

/**
 * Created by maxim on 1/21/2017.
 */
public class Main {
    private Double temp;
    private Integer pressure;
    private Integer humidity;
    private Integer temp_min;
    private Integer temp_max;
    private Integer sea_level;
    private Integer grnd_level;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(Integer temp_min) {
        this.temp_min = temp_min;
    }

    public Integer getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(Integer temp_max) {
        this.temp_max = temp_max;
    }

    public Integer getSea_level() {
        return sea_level;
    }

    public void setSea_level(Integer sea_level) {
        this.sea_level = sea_level;
    }

    public Integer getGrnd_level() {
        return grnd_level;
    }

    public void setGrnd_level(Integer grnd_level) {
        this.grnd_level = grnd_level;
    }

    @Override
    public String toString() {
        return "ClassPojo {temp = " + temp +
                ", pressure = " + pressure +
                ", humidity = " + humidity +
                ", temp_min = " + temp_min +
                ", temp_max = " + temp_max +
                ", sea_level = " + sea_level +
                ", grnd_level = " + grnd_level + "}";
    }
}
