package com.promotion.engine.service;

import com.promotion.engine.model.Cart;
import com.promotion.engine.engine.PromotionEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PromotionServiceTest {

    @Mock
    private PromotionEngine promotionEngine;

    @InjectMocks
    private PromotionService promotionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddToCart() {
        Cart cart = new Cart();
        cart.addItem("A", 2);

        promotionService.addToCart(cart);
        assertNotNull(promotionService.calculateTotal());
    }

    @Test
    public void testCalculateTotal() {
        Cart cart = new Cart();
        cart.addItem("A", 3);

        when(promotionEngine.calculateTotal(cart)).thenReturn(130);

        promotionService.addToCart(cart);
        int total = promotionService.calculateTotal();

        assertEquals(130, total, "Total should be 130 for 3 A's");
        verify(promotionEngine, times(1)).calculateTotal(cart);
    }
}
