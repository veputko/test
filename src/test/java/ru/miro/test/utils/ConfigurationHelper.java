package ru.miro.test.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationHelper {

    public static Properties getConfigurationParameters() {

        Properties prop = new Properties();
        try (InputStream input = ConfigurationHelper.class.getClassLoader()
                .getResourceAsStream("config.properties")){

           if (input == null) {
                throw new FileNotFoundException("Unable to find config.properties");
            }
            prop.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}
