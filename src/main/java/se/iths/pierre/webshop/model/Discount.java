package se.iths.pierre.webshop.model;

public abstract class Discount {

    protected String description;

    //konstruktor
    public Discount(String description) {
        this.description = description;
    }


    //get
    public String getDescription() {
        return description;

    }

    public abstract double calculatePrice(double originalPrice);


}