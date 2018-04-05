import java.util.*;
import java.sql.*;

public class Query{
    private String query, parameters;
    private int num;
    private Scanner scan;
    private static Properties config;

    public Query(){
        scan = new Scanner(System.in);
        query = scan.nextInt();
        num = scan.next();
        parameters = scan.next();
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
    
    public static void main(String [] args){
        QueryCall queryCall;
        Query query = new Query();
        String queryString;
        PreparedStatement pstmt;
        ResultSet results;
        
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
          }
          catch (SQLException e) 
          {System.out.println("SQL Error " + e.getMessage());}
          catch (Exception e) 
          {System.out.println("General Error " + e.getMessage());}

        if(query.query.equalsIgnoreCase("Query")){
            switch(query.num){
                case 1:
                    queryString = "SELECT Customer.cname FROM Customer JOIN Loan ON Loan.cname = Customer.cname JOIN Branch ON Branch.bname = Loan.bname WHERE assets > ? ORDER BY Customer.cname ASC";
                    pstmt=conn.prepareStatement(query);
                    pstmt.clearParameters(); 
            
                    pstmt.setString(1, query.parameters);
                    results = pstmt.executeQuery ();
                    System.out.println (" Results \n");
                    while ( results.next ()){
                        String customer = results.getString ("cname");
                        System.out.println(customer);
                    }
                    break;
                case 2:
                    queryString = "SELECT Customer.cname FROM Customer JOIN Loan On Loan.cname = Customer.cname GROUP BY cname HAVING SUM(amount) > ?";
                    pstmt=conn.prepareStatement(query);
                    pstmt.clearParameters(); 
            
                    pstmt.setString(1, query.parameters);
                    results = pstmt.executeQuery ();
                    System.out.println (" Results \n");
                    while ( results.next ()){
                        String customer = results.getString ("cname");
                        System.out.println(customer);
                    }
                    break;
                case 3:
                    queryString = "SELECT COALESCE(daccnum, \"n/a\"), COALESCE(balance, 0), COALESCE(Deposit.bname, \"n/a\"), COALESCE(laccnum, \"n/a\"), COALESCE(amount, 0), COALESCE(Loan.bname, \"n/a\")" + 
                    " FROM Deposit RIGHT JOIN Customer ON Customer.cname = Deposit.cname LEFT JOIN Loan On Loan.cname = Customer.cname WHERE Customer.cname = ?";
                    pstmt=conn.prepareStatement(query);
                    pstmt.clearParameters(); 
            
                    pstmt.setString(1, query.parameters);
                    results = pstmt.executeQuery ();
                    System.out.println (" Results \n");
                    while ( results.next ()){
                        String daccnum = results.getString ("daccnum");
                        String balance = results.getString("balance");
                        String dbname = results.getString("Deposit.bname");
                        String laccnum = results.getString("laccnum");
                        String amount = results.getString("amount");
                        String lbname = results.getString("loan.bname");
                        System.out.println(daccnum + "," + balance + "," + dbname  + "," + laccnum + "," + amount + "," + lbname);
                    }
                    break;
                default:
                    System.out.println("invalid query call");
            }
            conn.close();
        } else {
            System.out.println("invalid query call");
        }
        
    }
}