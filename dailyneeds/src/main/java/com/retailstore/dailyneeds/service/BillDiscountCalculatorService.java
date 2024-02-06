package com.retailstore.dailyneeds.service;

import com.retailstore.dailyneeds.domain.BillDetails;
import com.retailstore.dailyneeds.domain.ItemCategory;
import com.retailstore.dailyneeds.domain.PurchaseItem;
import com.retailstore.dailyneeds.domain.UserType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BillDiscountCalculatorService {
    public BillDetails getBillAmountAfterDiscount(BillDetails billDetails) {
        UserType userType = billDetails.getUserType();
        BigDecimal discountPercentage = BigDecimal.ZERO;

        //30% discount if the user is Store employee
        if (userType.equals(UserType.EMPLOYEE)) {
            discountPercentage = BigDecimal.valueOf(30);
        } else if (userType.equals(UserType.AFFILIATE)) {     // 10% discount if the user is affiliate
            discountPercentage = BigDecimal.valueOf(10);
        } else if (userType.equals(UserType.CUSTOMERFORTWOYEARS)) { //5% discount if the user is customer for over 2 years
            discountPercentage = BigDecimal.valueOf(5);
        }
        if(discountPercentage.compareTo(BigDecimal.ZERO) > 0) {
            calculateDiscount(billDetails, discountPercentage); //calculate discount based on percentage
        }
        calculateDiscountOnTotalAmount(billDetails); //calculate discount on totalAmountAfterDiscount or totalAmount


        return billDetails;
    }

    //calculate discount based on percentage
    private void calculateDiscount(BillDetails billDetails, BigDecimal discountPercentage) {

        List<PurchaseItem> purchaseItemList = billDetails.getPurchasedItemList();
        List<PurchaseItem> calculateDiscountAmountOnItems = purchaseItemList.stream().filter(item ->
                        !item.getItemCategory().equals(ItemCategory.GROCERIES))
                .map(item -> {
                    BigDecimal itemTotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                    item.setDiscountAmount(itemTotal.multiply(discountPercentage.divide(BigDecimal.valueOf(100))));
                    return item;

                }).toList();

        Double totalDiscountAmount = calculateDiscountAmountOnItems.stream()
                .map(PurchaseItem::getDiscountAmount).mapToDouble(BigDecimal::doubleValue).sum();


        BigDecimal finalAmount = billDetails.getTotalBillAmount().subtract(BigDecimal.valueOf(totalDiscountAmount));
        billDetails.setTotalAmountAfterDiscount(finalAmount);
    }

    private void calculateDiscountOnTotalAmount(BillDetails billDetails) {
        BigDecimal totalAmountAfterDiscount = billDetails.getTotalAmountAfterDiscount();
        BigDecimal totalBillAmount = billDetails.getTotalBillAmount();
        BigDecimal discountPer100 = BigDecimal.valueOf(5);
        int numberOfHundreds = 0;

        // Calculate the number of times 100 fits into the total bill amount or totalAmountAfterDiscount
        if (totalAmountAfterDiscount != null) {
            numberOfHundreds = totalAmountAfterDiscount.divide(BigDecimal.valueOf(100)).intValue();
        } else
            numberOfHundreds = totalBillAmount.divide(BigDecimal.valueOf(100)).intValue();
        // Calculate the total discount amount
        BigDecimal discountAmount = discountPer100.multiply(BigDecimal.valueOf(numberOfHundreds));

        // Deduct the discount amount from the total bill amount or totalAmountAfterDiscount

        BigDecimal discountedTotal = totalAmountAfterDiscount != null ? totalAmountAfterDiscount.subtract(discountAmount) : totalBillAmount.subtract(discountAmount);
        billDetails.setTotalAmountAfterDiscount(discountedTotal);

    }


}
