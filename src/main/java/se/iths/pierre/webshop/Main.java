package se.iths.pierre.webshop;

import se.iths.pierre.webshop.ProductStorage;
import se.iths.pierre.webshop.model.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    static void main() {

        //Path och ProductStorage ska skapas här, fungerar
        Path path = Path.of("products.txt");
        ProductStorage storage = new ProductFileStorage(path);

        //Skicka sedan in i webshopManager, fungerar
        try {
            Files.writeString(Path.of("cart.txt"), "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        WebshopManager webshopManager = new WebshopManager("shop", storage);
        webshopManager.startWebShop();
        //objektet storage (lagringen av varorna) i webshopen in till (konstruktorn i WebshopManager)
    }    // (dependency injection)
}

