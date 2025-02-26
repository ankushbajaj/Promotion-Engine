
package com.promotion.engine.model;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Map<String, Integer> items = new HashMap<>();

    public void addItem(String skuId, int quantity) {
        items.put(skuId, items.getOrDefault(skuId, 0) + quantity);
    }

    public Map<String, Integer> getItems() {
        return items;
    }
}
