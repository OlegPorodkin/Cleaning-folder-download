package properties_util;

import folder.FileCleaner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {

    private Properties properties ;
    private InputStream is;

    public LoadProperties() {
        load();
    }

    private void load(){
        properties = new Properties();
        is = FileCleaner.class.getResourceAsStream("/resources.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperties(String key) {
        return properties.getProperty(key);
    }
}
