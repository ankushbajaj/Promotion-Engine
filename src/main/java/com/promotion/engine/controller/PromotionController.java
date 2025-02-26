
package com.promotion.engine.controller;

import com.promotion.engine.model.Cart;
import com.promotion.engine.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping("/cart")
    public String addToCart(@RequestBody Cart cart) {
        promotionService.addToCart(cart);
        return "Items added to cart";
    }

    @GetMapping("/checkout")
    public int checkout() {
        return promotionService.calculateTotal();
    }
}
