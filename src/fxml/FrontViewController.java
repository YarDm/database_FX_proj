package fxml;

import DBController.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.DbFx_main;
import DBController.Categories;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FrontViewController {
    @FXML
    private TableView<Categories> catTable;
    @FXML
    private TableColumn<Categories, Integer> idColumn;
    @FXML
    private TableColumn<Categories, String> nameColumn;

    private DbFx_main dbfx;

    public FrontViewController(){

    }

    private ObservableList<Categories> catData;
    DBHandler db;
    Connection con;

    @FXML
    private void initialize(){
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        db = new DBHandler();
        try{
            con = db.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void buildData(){
        catData = FXCollections.observableArrayList();
        try {
            String quarySQL = "SELECT * FROM categories ORDER BY name";
            ResultSet rs =  con.createStatement().executeQuery(quarySQL);
            while (rs.next()){
                Categories cat = new Categories(rs.getInt("id"), rs.getString("name"));
                catData.add(cat);
            }
            catTable.setItems(catData);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
