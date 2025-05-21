package PageObject;

public class ProductObject {
    private String name;
    private double price;

    public ProductObject(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}
