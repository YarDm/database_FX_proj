package DBController;

public class Categories {
    public int id;
    public String name;

    public Categories(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Category: %s", this.id, this.name);
    }
}
