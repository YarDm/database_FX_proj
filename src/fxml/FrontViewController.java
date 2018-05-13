package fxml;

import DBController.Categories;
import DBController.DBHandler;
import DBController.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


/*
* Класс реализует модель данных для таблицы а так же запросы к БД
*
* Все запросы должны быть прописаны заранее для исклюючения SQL-инъекций
* Запрос в дочернюю базу должен производиться на основе выбранной строки родительской, то есть:
* String parentQuery = categories.name;
* Запрос в дочернюю базу будет выглядеть так:
* "SELECT p.* FROM categories as c, products as p
* WHERE c.name = " + parentQuery + " AND c.name = p.category_name"
 */
public class FrontViewController {

    private ObservableList<Categories> catData;
    //Список элементов из таблицы products
    private ObservableList<Product> prodData;
    private DBHandler db;
    private Connection con;

    @FXML
    Button button;
    @FXML
    private TableView<Categories> catTable;
    @FXML
    private TableColumn<Categories, Integer> idColumn;
    @FXML
    private TableColumn<Categories, String> nameColumn;

    @FXML
    private TableView<Product> prodTable;
    @FXML
    private TableColumn<Product, Integer> idPColumn;
    @FXML
    private TableColumn<Product, String> goodColumn;
    @FXML
    private TableColumn<Product,Double> priceColumn;
    @FXML
    private TableColumn<Product, String> catNameColumn;

    @FXML
    private void doSomethingButton(){

        db = new DBHandler();
        try{
            con = db.getConnection();
            buildCatData();
            buildProdData();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catTable.getSelectionModel().select(0);
        button.setDisable(true);
        button.setText("Подключени к базе уже выполнено");
    }

    @FXML
    private void initialize(){
        //инициализация источника данных в таблице для каждого столбца
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        idPColumn.setCellValueFactory(cellData -> cellData.getValue().idProdProperty().asObject());
        goodColumn.setCellValueFactory(cellData -> cellData.getValue().goodProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        catNameColumn.setCellValueFactory(cellData -> cellData.getValue().cnProperty());

        catTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> rebuildProdData(newValue));
    }

/*
Для JavaFX данные в TableView поставляются только из ObservableArrayList,
поэтому результат запроса в базу упаковываем в эту структуру данных
 */


    /*
    Метод для превращения SQL запроса в понятный JavaFX список табличных данных
     */
    public void buildCatData(){
        catData = FXCollections.observableArrayList();
        try {
            String querySQL = "SELECT * FROM categories";
            ResultSet rs =  con.createStatement().executeQuery(querySQL);
            while (rs.next()){
                Categories cat = new Categories(rs.getInt("id"), rs.getString("name"));
                catData.add(cat);
            }
            catTable.setItems(catData);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //метод для запроса из таблицы products и добавления полученных данных в TableView prodTable
    public void buildProdData(){
        prodData = FXCollections.observableArrayList();
        try{
            String querySQL = "SELECT * FROM products";
            ResultSet rs = con.createStatement().executeQuery(querySQL);
            while (rs.next()){
                Product product = new Product(rs.getInt("id"), rs.getString("good"),
                        rs.getDouble("price"), rs.getString("category_name"));
                prodData.add(product);
            }
            prodTable.setItems(prodData);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //Метод для запроса из таблицы products и добавления полученных данных в TableView prodTable при выборе строки в
    //родительской таблице
    public void rebuildProdData(Categories cat){
        prodData.clear();
        prodData = FXCollections.observableArrayList();
        String name = cat.getName();
        try{
            String querySQL = "SELECT p.* FROM categories as c, products as p WHERE c.name = '" + name +
                    "' AND c.name = p.category_name";
            ResultSet rs = con.createStatement().executeQuery(querySQL);
            while(rs.next()){
                Product product = new Product(rs.getInt("id"), rs.getString("good"),
                        rs.getDouble("price"), rs.getString("category_name"));
                prodData.add(product);
            }
            prodTable.setItems(prodData);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
