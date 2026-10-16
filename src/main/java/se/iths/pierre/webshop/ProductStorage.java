package se.iths.pierre.webshop;

import se.iths.pierre.webshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public interface ProductStorage {

    void saveProduct(Product product);

    List<Product> getProducts();
    //List<Product> products = new ArrayList<>();

    Product getProduct(String articelNumber);


}


