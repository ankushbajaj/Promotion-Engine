package com.promotion.engine.config;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PromotionConfigLoaderTest {

    @Autowired
    private PromotionConfigLoader configLoader;

    @Test
    public void testLoadConfig() {
        JsonObject config = configLoader.loadConfig();
        assertNotNull(config, "Configuration should not be null");

        // Check SKU prices
        JsonObject skus = config.getAsJsonObject("skus");
        assertEquals(50, skus.get("A").getAsInt());
        assertEquals(30, skus.get("B").getAsInt());

        // Check Promotions
        assertTrue(config.getAsJsonArray("promotions").size() > 0, "Promotions should not be empty");
    }
}
