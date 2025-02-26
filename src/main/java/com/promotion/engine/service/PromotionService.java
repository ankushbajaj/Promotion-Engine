
package com.promotion.engine.service;

import com.promotion.engine.model.Cart;
import com.promotion.engine.engine.PromotionEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {

    private final PromotionEngine promotionEngine;
    private Cart cart = new Cart();
    
    @Autowired
    public PromotionService(PromotionEngine promotionEngine) {
        this.promotionEngine = promotionEngine;
    }

    public void addToCart(Cart newCart) {
        cart = newCart;
    }

    public int calculateTotal() {
        return promotionEngine.calculateTotal(cart);
    }
}
