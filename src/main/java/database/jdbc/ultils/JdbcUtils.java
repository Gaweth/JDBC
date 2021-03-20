package database.jdbc.ultils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
   private static  JdbcUtils instance;

   private Connection connection;

   public  static JdbcUtils getInstance(){
       if(instance==null) {
           instance = new JdbcUtils();
       }
       return instance;
   }

   private JdbcUtils(){
       try {
           String connectionString = "jdbc:mysql://localhost:3306/runcenter";
           Properties prop = new Properties();
           prop.put("password", "medal123");
           prop.put("user", "root");
           prop.put("serverTimezone", "Europe/Warsaw");

           connection = DriverManager.getConnection(connectionString, prop);

       } catch (SQLException e){
           e.printStackTrace();
       }
   }

    public Connection getConnection() {
        return connection;
    }
}
