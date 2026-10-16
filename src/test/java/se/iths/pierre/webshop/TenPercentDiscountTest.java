package se.iths.pierre.webshop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import se.iths.pierre.webshop.model.TenPercentDiscount;

public class TenPercentDiscountTest {


    @Test
    void testCalculatorDiscount() {
        TenPercentDiscount tenPercentDiscount = new TenPercentDiscount("10 procent rabatt");


        double result = tenPercentDiscount.calculateDiscount(100);
        assertEquals(10.0, result);


    }

    @Test
    void testCalculatePrice() {
        TenPercentDiscount discount = new TenPercentDiscount("10 procent rabatt inräknat");

        double result = discount.calculatePrice(100);
        assertEquals(90.0, result);
    }
}
