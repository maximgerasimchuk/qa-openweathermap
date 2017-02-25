package api.entities.basic;

/**
 * Created by maxim on 1/21/2017.
 */
public class Wind {
    private Double speed;
    private Integer deg;
    private Integer gust;

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Integer getGust() {
        return gust;
    }

    public void setGust(Integer gust) {
        this.gust = gust;
    }

    @Override
    public String toString() {
        return "ClassPojo {speed = " + speed +
                ", deg = " + deg +
                ", gust = " + gust + "}";
    }
}
