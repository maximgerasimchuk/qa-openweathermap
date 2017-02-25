package web;

/**
 * Created by maxim on 1/28/2017.
 */
public class ConfigEntity {
    private String chromeDriverPath;
    private String ieDriverPath;

    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public void setChromeDriverPath(String chromeDriverPath) {
        this.chromeDriverPath = chromeDriverPath;
    }

    public String getIeDriverPath() {
        return ieDriverPath;
    }

    public void setIeDriverPath(String ieDriverPath) {
        this.ieDriverPath = ieDriverPath;
    }
}
