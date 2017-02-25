package api.response;

import api.entities.basic.*;

import java.util.Arrays;

/**
 * Created by maxim on 1/21/2017.
 */
public class WeatherResponse {
    private Integer cod;
    private Integer id;
    private String name;
    private Coord coord;
    private Sys sys;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private Weather[] weather;
    private Integer dt;
    private String base;
    private String visibility;

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString(){
        return "WeatherResponse{" +
                "cod=" + cod +
                ", id=" + id +
                ", name=" + name +
                ", coord=" + coord +
                ", sys=" + sys +
                ", main=" + main +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", weather=" + Arrays.toString(weather) +
                ", dt=" + dt +
                ", base='" + base +
                ", visibility=" + visibility + "}";
    }


}
