package DBController;

import org.sqlite.JDBC;

import java.sql.*;

//Класс реализующий соединение с базой данных
public class DBHandler {
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        DriverManager.registerDriver(new JDBC());
        return DriverManager.getConnection("jdbc:sqlite:C:/IdeaProjects/database_FX_proj/src/SQLite/myfin.db");
    }
}
