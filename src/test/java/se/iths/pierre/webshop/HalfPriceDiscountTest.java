package se.iths.pierre.webshop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import se.iths.pierre.webshop.model.HalfPriceDiscount;

public class HalfPriceDiscountTest {
    @Test
    void HalfPriceDiscountTest() {
        HalfPriceDiscount halfPriceDiscount = new HalfPriceDiscount("50 procetn rabatt");

        double result = halfPriceDiscount.calculatediscount(100);
        assertEquals(50.0, result);

    }

    @Test
    void testCalculatePrice() {
        HalfPriceDiscount discount = new HalfPriceDiscount("50 procent  rabatt inräknat");
        double result = discount.calculatediscount(100);
        assertEquals(50.0, result);
    }
}
