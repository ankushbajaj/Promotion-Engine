package com.promotion.engine;

import com.promotion.engine.config.PromotionConfigLoader;
import com.promotion.engine.engine.PromotionEngine;
import com.promotion.engine.model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PromotionEngineTest {

    @Autowired
    private PromotionConfigLoader configLoader;

    private PromotionEngine promotionEngine;

    @BeforeEach
    public void setUp() {
        promotionEngine = new PromotionEngine(configLoader);
    }

    @Test
    public void testCalculateTotalScenarioA() {
        Cart cart = new Cart();
        cart.addItem("A", 1);
        cart.addItem("B", 1);
        cart.addItem("C", 1);

        int total = promotionEngine.calculateTotal(cart);
        assertEquals(100, total, "Total should be 100 for Scenario A");
    }

    @Test
    public void testCalculateTotalScenarioB() {
        Cart cart = new Cart();
        cart.addItem("A", 5);
        cart.addItem("B", 5);
        cart.addItem("C", 1);

        int total = promotionEngine.calculateTotal(cart);
        assertEquals(370, total, "Total should be 370 for Scenario B");
    }

    @Test
    public void testCalculateTotalScenarioC() {
        Cart cart = new Cart();
        cart.addItem("A", 3);
        cart.addItem("B", 5);
        cart.addItem("C", 1);
        cart.addItem("D", 1);

        int total = promotionEngine.calculateTotal(cart);
        assertEquals(280, total, "Total should be 280 for Scenario C");
    }
}
