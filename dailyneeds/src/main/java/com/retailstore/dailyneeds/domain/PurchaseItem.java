package com.retailstore.dailyneeds.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PurchaseItem {
    private String itemName;
    private ItemCategory itemCategory;
    private BigDecimal price;
    private int quantity;
    @JsonIgnore
    private BigDecimal discountAmount;
}

