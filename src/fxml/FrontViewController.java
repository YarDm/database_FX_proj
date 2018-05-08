package fxml;

import DBController.DBHandler;
import javafx.collections.ObservableList;
import main.DbFx_main;
import DBController.Categories;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

    @FXML
    private void initialize(){
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    }

    public void setMainApp(DbFx_main dbfx) throws SQLException {
        this.dbfx = dbfx;
        DBHandler dbHandler = DBHandler.getInstance();
        ObservableList<Categories> cats = (ObservableList<Categories>) dbHandler.getAllCategories();
        catTable.setItems(cats);
    }
}
