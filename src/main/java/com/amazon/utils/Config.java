package com.amazon.utils;

import java.io.File;
import java.util.Properties;

public class Config {
    private static Properties properties;

    private Config() {
        String currentDirectory = System.getProperty("user.dir");
        String relativePath = "src/main/resources/environment.properties";
        String filePath = currentDirectory + File.separator + relativePath;
        properties = PropertiesReader.loadProperties(filePath);
    }

    public static void initialize() {
        if (properties == null) {
            new Config();
        }
    }

    public static String getBaseApiPath() {
        if (properties == null) {
            initialize();
        }
        String prop = properties.getProperty("baseAPIPath");
        if (prop != null) return prop;
        throw new RuntimeException("Could not find the base API Path in the property file");
    }
}
