
package com.promotion.engine.promotion;

import com.promotion.engine.model.SKU;
import java.util.Map;

public class ComboPromotion implements Promotion {
    private String skuId1;
    private String skuId2;
    private int comboPrice;

    public ComboPromotion(String skuId1, String skuId2, int comboPrice) {
        this.skuId1 = skuId1;
        this.skuId2 = skuId2;
        this.comboPrice = comboPrice;
    }

    @Override
    public int applyPromotion(Map<String, Integer> items, Map<String, SKU> skuData) {
        int total = 0;
        if (items.containsKey(skuId1) && items.containsKey(skuId2)) {
            int quantity1 = items.get(skuId1);
            int quantity2 = items.get(skuId2);
            int comboCount = Math.min(quantity1, quantity2);
            total += comboCount * comboPrice;
            items.put(skuId1, quantity1 - comboCount);
            items.put(skuId2, quantity2 - comboCount);
        }
        return total;
    }
}
