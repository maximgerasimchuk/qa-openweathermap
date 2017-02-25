package api.configEntities;

import java.util.Map;

/**
 * Created by maxim on 1/21/2017.
 */
public class ConfigEntity {
    private String apiKey;
    private String baseURL;
    private Map<String, String> methods;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public Map<String, String> getMethods() {
        return methods;
    }

    public void setMethods(Map<String, String> methods) {
        this.methods = methods;
    }
}
