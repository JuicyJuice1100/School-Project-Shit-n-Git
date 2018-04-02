import java.sql.*;
import java.io.FileInputStream;
import java.util.Properties;

public class JDBCTest2{
    private static Properties config;
        
    private static void loadProperties() {
        try {
            FileInputStream file = new FileInputStream("config.properties");
            config = new Properties();
            config.load(file);
            file.close();
        } catch (Exception e) {
            System.out.println("An error occurred while trying to load config.properties.");
        }
    }   

    public static void main(String [] args){
        //Load properties file
        loadProperties();
        String db = config.getProperty("database");
        String user = config.getProperty("username");
        String pw = config.getProperty("password");
        String driver = config.getProperty("driver");
        String url = config.getProperty("url") + db;
    
          try{
              Class.forName(driver);  // register the driver
              System.out.println("Attempting connection");
              Connection conn = DriverManager.getConnection(url,user,pw);
              System.out.println("Connection successfully made");
              conn.close();
          }
          catch (SQLException e) 
          {System.out.println("SQL Error " + e.getMessage());}
          catch (Exception e) 
          {System.out.println("General Error " + e.getMessage());}
    }
}