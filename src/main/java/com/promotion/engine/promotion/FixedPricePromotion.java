
package com.promotion.engine.promotion;

import com.promotion.engine.model.SKU;
import java.util.Map;

public class FixedPricePromotion implements Promotion {
    private String skuId;
    private int quantityRequired;
    private int fixedPrice;

    public FixedPricePromotion(String skuId, int quantityRequired, int fixedPrice) {
        this.skuId = skuId;
        this.quantityRequired = quantityRequired;
        this.fixedPrice = fixedPrice;
    }

    @Override
    public int applyPromotion(Map<String, Integer> items, Map<String, SKU> skuData) {
        int total = 0;
        if (items.containsKey(skuId)) {
            int quantity = items.get(skuId);
            total += (quantity / quantityRequired) * fixedPrice;
            items.put(skuId, quantity % quantityRequired);
        }
        return total;
    }
}
