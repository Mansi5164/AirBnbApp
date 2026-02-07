package com.coding.shuttle.airBnb.Strategy;

import com.coding.shuttle.airBnb.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Inventory inventory);

}
