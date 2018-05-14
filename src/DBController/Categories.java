package DBController;

import javafx.beans.property.*;



/*
 * POJO (plain old java object)
 * структура в которой будут храниться данные из БД
 * а также загружаться в TableView для таблицы Categories
 */
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
