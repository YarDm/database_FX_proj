package DBController;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Categories {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;



    public Categories(int id, String name){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public Integer getID(){
        return id.get();
    }

    public String getName(){
        return name.get();
    }

    public IntegerProperty idProperty(){
        return id;
    }

    public StringProperty nameProperty(){
        return name;
    }
}
