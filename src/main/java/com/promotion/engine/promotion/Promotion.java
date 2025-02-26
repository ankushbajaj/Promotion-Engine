
package com.promotion.engine.promotion;

import com.promotion.engine.model.SKU;
import java.util.Map;

public interface Promotion {
    int applyPromotion(Map<String, Integer> items, Map<String, SKU> skuData);
}
