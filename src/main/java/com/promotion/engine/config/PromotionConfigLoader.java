package com.promotion.engine.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;
import java.io.FileReader;

@Component
public class PromotionConfigLoader {

    public JsonObject loadConfig() {
        try {
            JsonElement jsonElement = JsonParser.parseReader(new FileReader("src/main/resources/promotionConfig.json"));
            return jsonElement.getAsJsonObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
