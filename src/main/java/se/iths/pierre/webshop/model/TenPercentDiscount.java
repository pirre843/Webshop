package se.iths.pierre.webshop.model;

public class TenPercentDiscount extends Discount {

    public TenPercentDiscount(String description) {
        super(description);

    }

    @Override
    public double calculatePrice(double originalPrice) {
        return 0.90 * originalPrice;
    }

    public double calculateDiscount(double price) {
        return price * 0.10;



    }
}



