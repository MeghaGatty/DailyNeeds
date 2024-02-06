package com.retailstore.dailyneeds.service;

import com.retailstore.dailyneeds.domain.BillDetails;
import com.retailstore.dailyneeds.domain.ItemCategory;
import com.retailstore.dailyneeds.domain.PurchaseItem;
import com.retailstore.dailyneeds.domain.UserType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class BillDiscountCalculatorServiceTest {

    @InjectMocks
    BillDiscountCalculatorService billDiscountCalculatorService;



    @Test
    void testBillAmountAfterDiscountForEmployee(){
        BillDetails billDetailsAfterDiscount = billDiscountCalculatorService.getBillAmountAfterDiscount(sampleBillDetails());
        BigDecimal expectedAmountAfterDiscount = BigDecimal.valueOf(210);
        assertEquals(0, expectedAmountAfterDiscount.compareTo(billDetailsAfterDiscount.getTotalAmountAfterDiscount()));
    }

    @Test
    void testBillAmountAfterDiscountForAffiliate(){
        BillDetails billDetails = sampleBillDetails();
        billDetails.setUserType(UserType.AFFILIATE);
        BillDetails billDetailsAfterDiscount = billDiscountCalculatorService.getBillAmountAfterDiscount(billDetails);
        BigDecimal expectedAmountAfterDiscount = BigDecimal.valueOf(270);
        assertEquals(0, expectedAmountAfterDiscount.compareTo(billDetailsAfterDiscount.getTotalAmountAfterDiscount()));

    }

    @Test
    void testBillAmountAfterDiscountForCustomerForTwoYears(){
        BillDetails billDetails = sampleBillDetails();
        billDetails.setUserType(UserType.CUSTOMERFORTWOYEARS);
        BillDetails billDetailsAfterDiscount = billDiscountCalculatorService.getBillAmountAfterDiscount(billDetails);
        BigDecimal expectedAmountAfterDiscount = BigDecimal.valueOf(285);
        assertEquals(0, expectedAmountAfterDiscount.compareTo(billDetailsAfterDiscount.getTotalAmountAfterDiscount()));
    }

    @Test
    void testBillAmountAfterDiscountForOthers(){
        BillDetails billDetails = sampleBillDetails();
        billDetails.setUserType(UserType.OTHERS);
        BillDetails billDetailsAfterDiscount = billDiscountCalculatorService.getBillAmountAfterDiscount(billDetails);
        BigDecimal expectedAmountAfterDiscount = BigDecimal.valueOf(295);
        assertEquals(0, expectedAmountAfterDiscount.compareTo(billDetailsAfterDiscount.getTotalAmountAfterDiscount()));
    }

    public BillDetails sampleBillDetails() {
        BillDetails billDetails = new BillDetails();
        billDetails.setCustomerName("John");
        billDetails.setUserType(UserType.EMPLOYEE);

        PurchaseItem item1 = new PurchaseItem("Pen", ItemCategory.STATIONERY, BigDecimal.valueOf(12), 10, BigDecimal.valueOf(0));
        PurchaseItem item2 = new PurchaseItem("Pen Drive", ItemCategory.ELECTRONICS, BigDecimal.valueOf(25), 4, BigDecimal.valueOf(0));
        PurchaseItem item3 = new PurchaseItem("Handbag", ItemCategory.BAGS, BigDecimal.valueOf(20), 4, BigDecimal.valueOf(0));
        PurchaseItem item4 = new PurchaseItem("Milk", ItemCategory.GROCERIES, BigDecimal.valueOf(10), 1, BigDecimal.valueOf(0));

        List<PurchaseItem> purchaseItemList = Arrays.asList(item1, item2, item3, item4);

        billDetails.setPurchasedItemList(purchaseItemList);
        billDetails.setTotalBillAmount(BigDecimal.valueOf(310));
        return billDetails;
    }
}