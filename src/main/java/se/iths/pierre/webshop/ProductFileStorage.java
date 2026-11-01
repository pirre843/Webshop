package se.iths.pierre.webshop;

import se.iths.pierre.webshop.model.Product;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.StandardOpenOption;

public class ProductFileStorage implements ProductStorage {

    private Path path;

    public ProductFileStorage(Path path) {
        this.path = path;

    }

    //ProductStorage cartStorage = new ProductFileStorage(Path.of("cart.txt"));


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
            throw new RuntimeException(e);
        }
        return products;
    }


    @Override
    public Product getProduct(String articelNumber) {
        return null;
    }

    public void saveProduct(Product product) {

        String line = product.getArticelNumber() + ","
                + product.getTitle() + ","                  //hämtar
                + product.getDescription() + "," +     //spara och skriva till fil
                product.getPrice();

        try {
            Files.writeString(path, line + System.lineSeparator(), //till fil
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);//Append lägger till fil när den skapas
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //TODO: Kod för att spara produkten till fil
//Nu är sökvägarna ändrade till för att spara till fil när produkterna läggs i varukorg.


    // IO.println("ProductFileStorage.saveProduct " + product);

    // List<Product> products = new ArrayList<>();


}
