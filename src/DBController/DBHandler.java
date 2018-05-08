package DBController;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
Класс реализующий соединение и запросы к базе данных
Все запросы должны быть прописаны заранее для исклюючения SQL-инъекций
Запрос в дочернюю базу должен производиться на основе выбранной строки родительской, то есть:
String parentQuery = categories.name;
Запрос в дочернюю базу будет выглядеть так:
"SELECT p.* FROM categories as c, products as p
WHERE c.name = " + parentQuery + " AND c.name = p.category_name"
 */
public class DBHandler {
    //todo: в SQL запрос должно подставляться значение из таблицы categories.name
    private static final String DB_PATH = "jdbc:sqlite:./SQLite/myfin.db";

    private static DBHandler instance = null;

    public static DBHandler getInstance() throws SQLException {
        if (instance == null){
            instance = new DBHandler();
        }
        return instance;
    }

    private Connection connection;

    private DBHandler() throws SQLException{
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(DB_PATH);
    }

    public List<Categories> getAllCategories(){
        try (Statement statement = this.connection.createStatement()){
            List<Categories> categories = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categories");
            while (resultSet.next()){
                categories.add(new Categories(resultSet.getInt("id"), resultSet.getString("name")));
            }
            return categories;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
