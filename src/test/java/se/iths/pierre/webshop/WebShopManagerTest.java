package se.iths.pierre.webshop;

import org.junit.jupiter.api.Test;
import se.iths.pierre.webshop.model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class WebShopManagerTest {


    @Test
    void testSaveProductListStorage() {
        WebshopManager manager = new WebshopManager("Webshop");
        ProductStorage storage = new ProductListStorageTest();


        Product product = new Product("B45", "Java Programmering", "Bok om Javaprogrammering", 455.0);
        Product product1 = new Product("AC46", "C++", "Bok om C++ programmering", 299);

        // Product foundProduct2 = storage.getProduct("B47");


        storage.saveProduct(product);
        storage.saveProduct(product1);

        assertEquals(2, storage.getProducts().size());

        Product foundProduct = storage.getProduct("B45");
        Product foundProduct1 = storage.getProduct("AC46");
        assertEquals("B45", foundProduct.getArticelNumber());
        assertEquals("AC46", foundProduct1.getArticelNumber());

      /*  if (foundProduct == null) {
            System.out.println("finns ingen produkt ");
        } else {
            System.out.println("Produkten finns ");

            assertNull(foundProduct2);*/
    }

    @Test
    void testGetProductNull() {

    }

    {


        ProductStorage storage = new ProductListStorageTest();
        Product foundProduct = storage.getProduct("g55");
        assertNull(foundProduct);
        System.out.print("TestGetProducktNull: " + foundProduct);

    }

}

//private List<Product> products = new ArrayList<>();












