package se.iths.pierre.webshop;

import se.iths.pierre.webshop.ProductStorage;
import se.iths.pierre.webshop.model.Product;

import java.nio.file.Path;

public class Main {
    static void main() {

        WebshopManager webshopManager = new WebshopManager("Shop");
        webshopManager.startWebShop();


    }
}
