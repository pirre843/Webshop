package se.iths.pierre.webshop;

import se.iths.pierre.webshop.ProductStorage;
import se.iths.pierre.webshop.model.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    static void main() {

        //Path och ProductStorage skapas här, fungerar

        
        ProductStorage storage = new ProductFileStorage(Path.of("products.txt"));


        WebshopManager webshopManager = new WebshopManager("shop", storage);
        webshopManager.startWebShop();
        //objektet storage (lagringen av varorna) i webshopen in till (konstruktorn i WebshopManager)
    }    // (dependency injection)
}

