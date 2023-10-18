package com.amazon.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;

public class JsonFileManager {
    private static final Logger logger = LoggerFactory.getLogger(JsonFileManager.class);

    public static JsonObject readJSONFile(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            JsonElement jsonElement = JsonParser.parseReader(fileReader);
            if (jsonElement.isJsonObject()) {
                return jsonElement.getAsJsonObject();
            } else {
                throw new IllegalStateException("The JSON file does not contain a valid JSON object.");
            }
        } catch (Exception e) {
            logger.error("Error reading JSON file: {}", e.getMessage(), e);
            return null;
        }
    }
}
