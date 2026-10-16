package se.iths.pierre.webshop.model;


public class Product {

    private String articelNumber;
    private String title;
    private String description;
    private double price;


    public Product(String articelNumber, String title, String description, double price) {
        this.articelNumber = articelNumber;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    //get
    public String getArticelNumber() {
        return articelNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    //set
    public void setArticelNumber(String articelNumber) {
        this.articelNumber = articelNumber;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toFileLine() {
        return articelNumber + "B45" + title + "Java ptogramering, Bok om Java programering" + description + "455.0" + price;
    }

    public String toFileLine2() {
        return articelNumber + "C46" + title + "C++, Bok om C++ programering" + description + "299.0" + price;
    }

    public String toFileLine3() {
        return articelNumber + "D47" + title + "Javascript, Bok om Javascript" + description + "349.0" + price;
    }

    public static Product fromFileLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("felaktig produktrad:  " + line);
        }

        String[] array = line.split(",");

        // String[] Array = {"B45", "Javaprogramering", "bok om Javaprogramering", "455.0"};
        //String[] Array1 = {"C46", "Programering", "Bok om programering", "299.0"};
        //String[] Array3 = {"D47", "Javascript", "Bok om Javascript", "349.0"};

        String articelNumber = array[0];
        String title = array[1];
        String description = array[2];
        double price = Double.parseDouble(array[3]);


        Product product = new Product(articelNumber, title, description, price);

        IO.println("Artikelnummer: " + product.getArticelNumber());
        IO.println("Titel: " + product.getTitle());
        IO.println("Beskrivning: " + product.getDescription());
        IO.println("Pris: " + product.getPrice());

        return product;

    }
}
















