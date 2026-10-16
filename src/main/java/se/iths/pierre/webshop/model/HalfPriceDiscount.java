package se.iths.pierre.webshop.model;

public class HalfPriceDiscount extends Discount {

    public HalfPriceDiscount(String description) {
        super(description);
    }


    public double calculatediscount(double price) {
        return price * 0.5;


    }

    @Override
    public double calculatePrice(double originalPrice) {
        return 0.50 * originalPrice;
    }

}