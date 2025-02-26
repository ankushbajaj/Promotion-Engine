package com.promotion.engine.engine;

import com.promotion.engine.model.SKU;
import com.promotion.engine.model.Cart;
import com.promotion.engine.promotion.Promotion;
import com.promotion.engine.promotion.FixedPricePromotion;
import com.promotion.engine.promotion.ComboPromotion;
import com.promotion.engine.config.PromotionConfigLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Component
public class PromotionEngine {

	private final Map<String, SKU> skuData = new HashMap<>();
	private final List<Promotion> promotions = new ArrayList<>();

	@Autowired
	public PromotionEngine(PromotionConfigLoader configLoader) {
		loadSKUData(configLoader);
		loadPromotions(configLoader);
	}

	private void loadSKUData(PromotionConfigLoader configLoader) {
		configLoader.loadConfig().getAsJsonObject("skus").entrySet().forEach(entry -> {
			skuData.put(entry.getKey(), new SKU(entry.getKey(), entry.getValue().getAsInt()));
		});
	}

	private void loadPromotions(PromotionConfigLoader configLoader) {
		configLoader.loadConfig().getAsJsonArray("promotions").forEach(promotionElement -> {
			String type = promotionElement.getAsJsonObject().get("type").getAsString();
			if ("FixedPricePromotion".equals(type)) {
				String skuId = promotionElement.getAsJsonObject().get("skuId").getAsString();
				int quantityRequired = promotionElement.getAsJsonObject().get("quantityRequired").getAsInt();
				int fixedPrice = promotionElement.getAsJsonObject().get("fixedPrice").getAsInt();
				promotions.add(new FixedPricePromotion(skuId, quantityRequired, fixedPrice));
			} else if ("ComboPromotion".equals(type)) {
				String skuId1 = promotionElement.getAsJsonObject().get("skuId1").getAsString();
				String skuId2 = promotionElement.getAsJsonObject().get("skuId2").getAsString();
				int comboPrice = promotionElement.getAsJsonObject().get("comboPrice").getAsInt();
				promotions.add(new ComboPromotion(skuId1, skuId2, comboPrice));
			}
		});
	}

	public int calculateTotal(Cart cart) {
		Map<String, Integer> items = new HashMap<>(cart.getItems());
		int total = 0;

		for (Promotion promotion : promotions) {
			total += promotion.applyPromotion(items, skuData);
		}

		for (Map.Entry<String, Integer> entry : items.entrySet()) {
			total += entry.getValue() * skuData.get(entry.getKey()).getPrice();
		}

		return total;
	}
}
