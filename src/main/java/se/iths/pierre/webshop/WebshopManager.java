package se.iths.pierre.webshop;

import javax.swing.JOptionPane;

import se.iths.pierre.webshop.model.Discount;
import se.iths.pierre.webshop.model.HalfPriceDiscount;
import se.iths.pierre.webshop.model.Product;
import se.iths.pierre.webshop.model.TenPercentDiscount;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.getFileAttributeView;
import static java.nio.file.Files.readAllLines;


public class WebshopManager {


    private String name;
    private ProductStorage productStorage;

//dependency injection (tar emot och sparar från main)

    public WebshopManager(String name, ProductStorage productStorage) {
        this.name = name;
        this.productStorage = productStorage; //dependency injection

    }

    //Sparar product och sparar en ny product
    public void saveProductToStorage(Product product) {

        productStorage.saveProduct(product);
    }

    public Product getProduct(String articelNumber) {    //throws IOException {

        List<Product> products = productStorage.getProducts();
        Path path = Path.of("products.txt");


        for (Product product : products) {

            if (product.getArticelNumber()
                    .equalsIgnoreCase(articelNumber)) {

                return product;
            }

        }
        return null;

    }

    public String getName() {
        return name;


    }

    /*

     */

    static void main() {

        try {
            Files.writeString(Path.of("cart.txt"), "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Path cartPath = Path.of("cart.txt");

        ProductStorage cartStorage1 = new ProductFileStorage(cartPath);

        try {
            Files.createFile(cartPath);
            IO.println("Filen har skapats");

        } catch (IOException e) {
            IO.println("Filen har redan skapats: " + e.getMessage());
        }

        Discount discount = new TenPercentDiscount("10 procent rabatt");
        IO.println(discount.getDescription());


        Discount discount1 = new HalfPriceDiscount("50 procent rabatt");
        IO.println(discount1.getDescription());

    }

    public void startWebShop() {
        IO.println("Starta WebShop");

        // Path path = Path.of("products.txt"); //ligger main nu
        //ProductStorage storage = new ProductFileStorage(path); I Main


        List<Product> products = productStorage.getProducts();// ändrat till productStorage lagt till i Main. Dependency injection.
        IO.println("Antal produkter från filen: " + products.size());
        showMenu(products);
    }

    Discount discount1 = new TenPercentDiscount("10 procent rabatt");

    Discount discount2 = new HalfPriceDiscount("50 procent rabatt");

    Discount discount = null;


    public void showMenu(List<Product> products) {


        List<Product> cart = new ArrayList<>();
        boolean running = true;
        boolean rabatkupong = true;
        ProductStorage cartStorage = new ProductFileStorage(Path.of("cart.txt"));


        IO.println("-----------Välkommen till Webshopen!---------------------");

        while (running) {

//            IO.println("1-------------Lägg till Produkter varukorgen-------------");
//            IO.println("2-------------Visa produkterna i varukorgen--------------");
//            IO.println("3-------------Visa information om produkterna------------");
//            IO.println("4-------------Avsluta Webshoppen.........................");

            // int choice = Integer.parseInt(IO.readln("choice: "));

            // ProductStorage cartStorage = new ProductFileStorage(Path.of("cart.txt"));
            OutInPutHandler handler = new ConsoleHandler();
            String menuChoice = handler.menu(); //IO.readln("Välj ett av alternativen: ");

            if (menuChoice == null) {
                return;
            }
            switch (menuChoice) {
                case "1":
                    //    IO.println("Du valde att Lägga till produkter");
                    while (true) {
                        //OutInPutHandler handler = new ConsoleHandler();

                        //alla värden som användaren får mata in finns nu.


                        String articelNumber = handler.prompt("Ange artikelnummer: ");
                        if (articelNumber == null) {
                            IO.println("Återgår till huvudmenyn");
                            break;

                        }
                        String titel = handler.prompt("Ange Titel: ");
                        if (titel == null) {
                            IO.println("Återgår till huvudmenyn");
                            break;
                        }

                        String beskrivning = handler.prompt("Ange Beskrivning: ");
                        if (beskrivning == null) {
                            IO.println("Återgår till huvudmenyn");
                            break;
                        }

                        String priceInput = handler.prompt("Ange Pris: ");
                        if (priceInput == null) {
                            IO.println("Återgår till huvudmenyn");
                            break;

                        }
                        double price = Double.parseDouble(priceInput);

                        Product product = new Product(articelNumber, titel, beskrivning, price);
                        //Spara produkterna till fil funkar nu


                        // String articelNumber = IO.readln("Ange artikel nummer för att lägga varan i kundvagnen. ");
                        boolean found = false;

                        for (Product product1 : products) {
                            IO.println("kontrollerar artikelnummer: " + product1.getArticelNumber());
                            if (product1.getArticelNumber().trim().equalsIgnoreCase(articelNumber.trim())) {
                                cart.add(product1);
                                cartStorage.saveProduct(product1); //spara till fil!!!!!!

                                IO.println(product1.getTitle() + " har lagts i varukorgen.");

                                found = true;
                                break;
                            }
                        } //avslutar for loopen
                           /* if (is1) {
                                IO.println("Du har 50 procent i rabatt");
                            } else {
                                IO.println(" Du får 10 procent rabatt");
                            }
                            for (Product product : cart) {
                                double price = product.getPrice();
                                double newPrice;
                                if (is1) {
                                    newPrice = price * 0.50;
                                } else {
                                    newPrice = price * 0.90;
                                }
                                IO.println("artikelnummer: " + product.getArticelNumber());
                                IO.println("Titel: " + product.getTitle());
                                IO.println("Beskrivning:" + product.getDescription());
                                IO.print("Pris: " + product.getPrice());
                                IO.println(product.getTitle() + ": " + "Boken koster efter rabatten: " + newPrice + "kr");

                            }

                            */
                        if (!found) {
                            IO.println("Varan hittades inte");
                        }
                        String svar = IO.readln("vill du lägga till en vara till i kundvagnen? Ja/Nej: ");
                        if (svar.equalsIgnoreCase("n")) {
                            break;

                        }


                    }
                    break;

                case "2":
                    //IO.println("Du valde att lista produkter i varukorgen");
                    String choice = JOptionPane.showInputDialog(null, "Har du en rabattkupong på 50 procent? Tryck 1 för att använda din rabatt. Annars får du 10% i Webshoppen");
                    if (choice == null) {
                        break;
                    }
                    //ProductStorage cartStorage = new ProductFileStorage(Path.of("cart.txt"));

                    List<Product> products1 = cartStorage.getProducts();
                    IO.println("Antal böcker i kundvagnen: " + products1.size());
                    double summa = 0;
                    String productlist = "";

                    for (Product product : products1) {
                        productlist += product.getTitle()
                                + " _ " + product.getPrice()
                                + "kr\n";
                        summa += product.getPrice();

                    }
                    boolean is1 = choice.equals("1");
                    double summa1 = 0;
                    String productlist1 = "";


                    //fungerar
                    Path CarPath = Path.of("cart.txt");

                    //ProductFileStorage cartStorage = new ProductFileStorage(CartPath);
                    //Main

                    double slutpris;
                    if (is1) {
                        slutpris = summa * 0.50;
                    } else {
                        slutpris = summa * 0.90;
                    }
                    productlist += "\nTotalpris efter rabatt: " + slutpris + "kr";
                    handler.info(productlist);

                    try {

                        Files.writeString(Path.of("cart.txt"), "");  //tömmer kundvagnens filen
                        // cart.clear(); //tömmer kundvagnen minnet

                        IO.println("varukorgen är tom");

                    } catch (IOException e) {        //kastar en IOException om det inte funger att tömma varukorgen
                        IO.println("kunde inte tömma varukorgen");

                    }
                    break;


                // for (Product product : products) {
                // IO.println(product.getTitle());
                //IO.println(product.getPrice() + "kr");
                //IO.println(product.getArticelNumber());
                //Skriv om den här så den visa information om produkterna case 2 eller 3 Kolla
                //upp dessa!!!!!!!!!

                case "3":
                    String productinfo = "";  //kolla upp dessa tre

                    String articelnumber = handler.prompt("Ange artikelnummer: ");//kund matar

                    if (articelnumber == null) {

                        IO.println("Återgår till huvudmenyn");

                        break;
                    }
                    boolean found = false;                                         //in artikelnummer
                    // IO.println("Antal produkter i att söka i case 3: " + products.size());
                    for (Product product : products) {
                        // IO.println("kontrollerar: [" + product.getArticelNumber() + "]");
                        //IO.println("kunden söker efter: [" + articelnumber + "]");
                        // IO.println("Produkten i listan: [" + product.getArticelNumber() + "]");

                        //IO.println("Antal produkter att söka bland: [" + product.getArticelNumber() + "]");
                        if (product.getArticelNumber().trim().equalsIgnoreCase(articelnumber.trim())) {
                            found = true;

                            productinfo = "Ange artikelnummer: " + product.getArticelNumber()
                                    + "\nTitel: " + product.getTitle() + "\nBeskrivning: " + product.getDescription()
                                    + "\nPris: " + product.getPrice() + "kr";


                            handler.info(productinfo);
                        }
                    }
                    if (!found) {
                        handler.info("produkten finns inte");
                    }

                    break;


                //Användaren matar in artikelnummer
                //kollar om produkten finns fungerar nu
                //och visar info om produkten fungerar

                // for (Product product : products) {

                //   productinfo += "Titel: " + product.getTitle() + "\nArtikelnummer: " + product.getArticelNumber() + "\nBeskrivning: " + product.getDescription() + "\nPris: " + product.getPrice() + "kr\n\n";


                // handler.info(productinfo);


                case "4":
                    IO.println("Avsluta Webshoppen");

                    running = false;
                    break;
            }


          /* String Choice = IO.readln("Har du en rabattkupong på 50 procent? Tryck 1 för att använda din rabatt. Annars får du 10% i Webshoppen");
            boolean is1 = Choice.equals("1");

            if (is1) {
                IO.println("Du har 50 procent i rabatt");
            } else {
                IO.println(" Du får 10 procent rabatt");
            }

            for (Product product : cart) {
                double price = product.getPrice();
                double newPrice;
                if (is1) {
                    newPrice = price * 0.50;
                } else {
                    newPrice = price * 0.90;
                }
                IO.println("artikelnummer: " + product.getArticelNumber());
                IO.println("Titel: " + product.getTitle());
                IO.println("Beskrivning:" + product.getDescription());
                IO.print("Pris: " + product.getPrice());
                IO.println(product.getTitle() + ": " + "Boken koster efter rabatten: " + newPrice + "kr");
            }
*/

              /*  try {
                    List<String> lines = Files.readAllLines(Path.of("products.txt"));


                    for (String line : lines) {
                        Product product = Product.fromFileLine(line);
                        products.add(product);

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    IO.println("Fel: " + e.getMessage());

              */

     /*   // articelNumber = IO.readln("Ange artikelnumret");
        boolean found = false;
        for (Product product : products) {

            if (product.getArticelNumber().equals(articelNumber)) {
                cart.add(product);
                IO.println(product.getTitle() + " har lagts i varukorgen");
                found = true;
                break;
            }
        }

        if (!found) {
            IO.println("Produkten hittades inte");
        }
           /* for (Product product : cart) {
                double price = product.getPrice();
                double newPrice;
                if (is1) {
                    newPrice = price * 0.50;
                } else {
                    newPrice = price * 0.90;
                }

                IO.println("artikelnummer: " + product.getArticelNumber());
                IO.println("Titel: " + product.getTitle());
                IO.println("Beskrivning:" + product.getDescription());
                IO.print("Pris: " + product.getPrice());
                IO.println(product.getTitle() + ": " + newPrice + "kr");
*/

        }
    }
}







         /* if (!found) {
                IO.println("Produkten hittades inte");
            }*/
       /* if (!products.isEmpty()) {
            Product product = products.get(0);
            cart.add(product);
        }
        for (Product product : cart) {
            IO.println(product.getTitle());
            IO.println(product.getPrice() + " kr");

            String articelNumber = IO.readln("Ange artikelnummer: ");

*/




























