package com.retailstore.dailyneeds.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class BillDetails {
    private String customerName;
    private UserType userType;
    private List<PurchaseItem> purchasedItemList;
    private BigDecimal totalBillAmount;
    private BigDecimal totalAmountAfterDiscount;
}
