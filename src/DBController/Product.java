package DBController;

public class Product {
    public int id;
    public String good;
    public double price;
    public String category_name;

    public Product(int id, String good, double price, String category_name){
        this.id = id;
        this.good = good;
        this.price = price;
        this.category_name = category_name;
    }

    @Override
    public String toString(){
        return String.format("ID: %s | Good: %s | Price: %s | Category: %s",
                this.id, this.good, this.price, this.category_name);
    }
}
