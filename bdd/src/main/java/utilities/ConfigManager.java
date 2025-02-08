package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private final Properties properties = new Properties();

    // Load properties from the default or environment-specific file
    public void loadProperties(String environment) {
        try {
            String filePath = "src/test/resources/configs/" + environment + ".properties";
            try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
                properties.load(fileInputStream);
            }
        } catch (IOException e) {
            System.out.println("Error loading properties file: " + e.getMessage());
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
