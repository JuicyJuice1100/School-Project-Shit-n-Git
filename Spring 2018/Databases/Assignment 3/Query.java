import java.util.*;
import java.sql.*;

public class Query{
    private String query, parameters;
    private int num;
    private Scanner scan;

    public Query(){
        scan = new Scanner(System.in);
        query = scan.nextInt();
        num = scan.next();
        parameters = scan.next();
    }

    public static void main(String [] args){
        QueryCall queryCall;
        Query query = new Query();
        Connect connect = new Connnect();

        if(query.query.equalsIgnoreCase("Query")){
            switch(query.num){
                case 1:
                    //insert query here
                    break;
                case 2:
                    //insert query here
                    break;
                case 3:
                    //insert query here
                    break;
                default:
                    System.out.println("invalid query call");
            }
            connect.conn.close();
        } else {
            System.out.println("invalid query call");
        }
        
    }
}