import java.sql.*;
import java.io.FileInputStream;
import java.util.Properties;

public class Query3 {

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

    public void query(String x) {
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
            String query1 ="SELECT * FROM Deposit Deposit WHERE cname = \"" + x + "\"";
            String query2 ="SELECT * FROM Loan Deposit WHERE cname = \"" + x + "\"";
            PreparedStatement pstmt1=conn.prepareStatement(query1);
            PreparedStatement pstmt2=conn.prepareStatement(query2);
            pstmt1.clearParameters();
            pstmt2.clearParameters(); 
            
            ResultSet results1 = pstmt1.executeQuery ();
            ResultSet results2 = pstmt2.executeQuery ();
            System.out.println ("Results:  \n");
            System.out.println("Deposit Accounts\n---------------------------------------\n");
            while ( results1.next ()){
                String cname = results1.getString ("cname");
                String daccnum = results1.getString ("daccnum");
                String balance = results1.getString ("balance");
                String bname = results1.getString ("bname");
                System.out.println ("cname = " + cname + ", daccnum = " + daccnum + ", bname = " + bname + ", balance = " + balance);
            }
            if(!results1.previous()) {
                System.out.println(x + " does not have any Deposit Accounts");
            }
            System.out.println("\nLoan Accounts\n---------------------------------------\n");
            while ( results2.next ()){
                String cname = results2.getString ("cname");
                String bname = results2.getString ("bname");
                String laccnum = results2.getString ("laccnum");
                String amount = results2.getString ("amount");
                System.out.println ("cname = " + cname + ", laccnum = " + laccnum + ", bname = " + bname + ", amount = " + amount);
            }
            if(!results2.previous()) {
                System.out.println(x + " does not have any Loan Accounts");
            }
            conn.close();
          }
          catch (SQLException e) 
          {System.out.println("SQL Error " + e.getMessage());}
          catch (Exception e) 
          {System.out.println("General Error " + e.getMessage());}
    }
}
