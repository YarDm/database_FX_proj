package DBController;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
Класс реализующий соединение к базе данных
 */
public class DBHandler {
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        DriverManager.registerDriver(new JDBC());
        return DriverManager.getConnection("jdbc:sqlite:C:/IdeaProjects/database_FX_proj/src/SQLite/myfin.db");
    }
}
