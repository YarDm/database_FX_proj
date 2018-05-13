package DBController;

import javafx.beans.property.*;

public class Product {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty good;
    private final SimpleDoubleProperty price;
    private final SimpleStringProperty category_name;

    public Product(int id, String good, double price, String category_name){
        this.id = new SimpleIntegerProperty(id);
        this.good = new SimpleStringProperty(good);
        this.price = new SimpleDoubleProperty(price);
        this.category_name = new SimpleStringProperty(category_name);
    }

    public IntegerProperty idProdProperty(){
        return id;
    }

    public StringProperty goodProperty(){
        return good;
    }

    public DoubleProperty priceProperty(){
        return price;
    }

    public StringProperty cnProperty(){
        return category_name;
    }


}
