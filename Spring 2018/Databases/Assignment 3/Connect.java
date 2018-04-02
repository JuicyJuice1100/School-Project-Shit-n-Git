import java.sql.*;
import java.io.FileInputStream;
import java.util.Properties;

public class Connnect{
    private String db;
    private String user;
    private String pw;
    private String driver;
    private String url;
    private Connection conn;

    private static Properties config;

    public Connect(){
        loadProperties();
        db = config.getProperty("database");
        user = config.getProperty("username");
        pw = config.getProperty("password");
        driver = config.getProperty("driver");
        url = config.getProperty("url") + db;
        try{
            Class.forName(driver);  // register the driver
            System.out.println("Attempting connection");
            conn = DriverManager.getConnection(url,user,pw);
            System.out.println("Connection successfully made");
        }
        catch (SQLException e) 
        {System.out.println("SQL Error " + e.getMessage());}
        catch (Exception e) 
        {System.out.println("General Error " + e.getMessage());}
    }
        
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
}