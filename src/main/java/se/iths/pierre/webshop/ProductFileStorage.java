package se.iths.pierre.webshop;

import se.iths.pierre.webshop.model.Product;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.io.IOException;

public class ProductFileStorage implements ProductStorage {
    Path path;

    public ProductFileStorage(Path path) {
        this.path = path;

    }

    @Override
    public List<Product> getProducts() {

        List<Product> products = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                Product product = Product.fromFileLine(line);

                products.add(product);

            }


        } catch (IOException e) {
            IO.println("Det blev fel: " + e.getMessage());
        }

        return products;
    }


    @Override
    public Product getProduct(String articelNumber) {
        return null;
    }

    public void saveProduct(Product product) {

        IO.println("ProductFileStorage.saveProduct " + product);

        List<Product> products = new ArrayList<>();


    }
}