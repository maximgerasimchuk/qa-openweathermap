package api.utils;

import com.esotericsoftware.yamlbeans.YamlException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by maxim on 1/21/2017.
 */
public class YamlFileReader<T> {
    public static <T> T fromYaml(Class<T> className, String filePath){
        File f = new File(filePath);
        T obj = null;
        try {
            com.esotericsoftware.yamlbeans.YamlReader reader = new com.esotericsoftware.yamlbeans.YamlReader(new FileReader(f.getAbsolutePath()));
            obj = reader.read(className);

        }
        catch (YamlException e){
            ReportWriter.logException("YamlException" + e.getMessage());
//            Logger.getLogger(YamlUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (FileNotFoundException e){
            ReportWriter.logException("FileNotFoundException" + e.getMessage());
        }
        return  obj;
    }
}
