package web.utils;

import api.utils.ReportWriter;

import java.io.UnsupportedEncodingException;

/**
 * Created by maxim on 1/28/2017.
 */
public class TestUtils {
    public static String convertToUTF8(String string) {
        String stringUtf8 = null;
        try {
            byte text[] = string.getBytes("UTF-8");
            stringUtf8 = new String(text, "UTF-8");
        }
        catch(UnsupportedEncodingException e){
            ReportWriter.logException(e.getMessage());
        }
        return stringUtf8;
    }
}
