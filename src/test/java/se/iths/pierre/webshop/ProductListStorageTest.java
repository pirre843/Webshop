package se.iths.pierre.webshop;

import org.junit.jupiter.api.Test;
import se.iths.pierre.webshop.model.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductListStorageTest implements ProductStorage {


    private List<Product> products = new ArrayList<>();

    /*  @Override
      public void saveProduct(Product product) {
          products.add(product);
      }
  */
    @Override
    public List<Product> getProducts() {
        return products;
    }


    @Override
    public Product getProduct(String articelNumber) {


        for (Product product : products) {
            if (product.getArticelNumber().equals(articelNumber)) {
                return product;
            }
        }
        return null;

    }

    @Test
    void testSave() {
        Product product = new Product("B45: ", "Java Programmering ", "Bok om Java programmering", 455.0);
        Product product1 = new Product("AC46: ", "Bok om c++ ", "Bok om C++ programmering", 299.0);

        saveProduct(product);
        saveProduct(product1);

        assertEquals(2, products.size());
    }


    @Override
    public void saveProduct(Product product) {
        products.add(product);

    }


}








