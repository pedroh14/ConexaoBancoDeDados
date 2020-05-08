package jdbc1.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getconection(){
        if(conn == null){
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }
            catch (SQLException e){
                throw new dbExeption(e.getMessage());
            }
        }
        return conn;
    }
    public static void closeCon() {
        if (conn != null) {
            try {
                conn.close();
            }
            catch (SQLException e){
                throw new dbExeption(e.getMessage());
            }
        }
    }


    private static Properties loadProperties() {
        try{
            FileInputStream file = new FileInputStream("db.propriedades");
            Properties p = new Properties();
            p.load(file);
            return p;

        } catch (IOException e) {
            throw new dbExeption(e.getMessage());
        }
    }
}
