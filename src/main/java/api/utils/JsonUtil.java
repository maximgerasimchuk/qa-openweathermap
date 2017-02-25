package api.utils;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Created by maxim on 1/21/2017.
 */
public class JsonUtil {
    public static String objectToJson(Object object){
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return json;
    }

    public static void jsonToFile(String type, String json){
        String fileName = "/"+ type + "_" + DateTimeHelper.getDateTimeForFileName();

        File directory = new File(System.getProperty("user.dir") + "/target/surefire-reports/html/request_responses");
        if (!directory.exists()) directory.mkdirs();

        try {
            ObjectMapper mapper = new ObjectMapper();
            Object jsonRS = mapper.readValue(json, Object.class);
            String jsonPretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonRS);

            File newTextFile = new File(directory + fileName + ".json");
            ReportWriter.logJsonFile(type, "request_responses" + fileName + ".json");
            FileUtils.writeStringToFile(newTextFile, jsonPretty);
        } catch (IOException e) {
            try {
                File newTextFile = new File(directory + fileName + ".html");
                ReportWriter.logHtmlFile(type, "request_responses" + fileName + ".html");
                FileUtils.writeStringToFile(newTextFile, json);
            } catch (IOException e1) {

            }
        }
    }
}
