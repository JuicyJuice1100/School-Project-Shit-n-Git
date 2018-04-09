import java.sql.*;
import java.io.FileInputStream;
import java.util.Properties;

public class Query2 {

	private Properties config;
        
    private void loadProperties() {
        try {
            FileInputStream file = new FileInputStream("config.properties");
            config = new Properties();
            config.load(file);
            file.close();
        } catch (Exception e) {
            System.out.println("An error occurred while trying to load config.properties.");
        }
    }

	public void query(double x) {
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
            String query ="SELECT cname, SUM(amount) FROM Loan GROUP BY cname HAVING COUNT(amount) > " + x;
            PreparedStatement pstmt=conn.prepareStatement(query);
            pstmt.clearParameters(); 
            
            ResultSet results = pstmt.executeQuery ();
            System.out.println ("Results:  \n");
            while ( results.next ()){
                String name = results.getString ("cname");
                String amount = results.getString ("SUM(amount)");
                System.out. println ("name = " + name + ", amount = " + amount);
            }
            conn.close();
          }
          catch (SQLException e) 
          {System.out.println("SQL Error " + e.getMessage());}
          catch (Exception e) 
          {System.out.println("General Error " + e.getMessage());}
	}
}
