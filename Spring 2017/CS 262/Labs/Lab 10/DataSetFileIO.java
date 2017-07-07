import java.util.Scanner;
import java.io.IOException;

public class DataSetFileIO <E extends Number> extends DataSet<E>{
    private Scanner scan;
    //private PrintWriter writer;
   
    public DataSetFileIO(){
    super();
    scan = new Scanner(System.in);
    //writer = new PrintWriter(); 
    }

    public boolean writeToFile(String fileName){
        try{
            PrintWriter writer = new PrintWriter("~\\" + fileName + ".txt");

            writer.println(toString());

            writer.close();   
        }
        catch(IOException e){
            throw new IOException();
        }
    }
    
    public boolean readFromFile(String fileName){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("~\\" + fileName + ".txt"));
        }
        catch(IOException e){
            throw new IOException();
        }
    }
    public static void main(String[] args){
        DataSetFileIO data = new DataSetFileIO();
    }
}

// public static void main(String[] args){
//     DataSetFileIO data = new DataSetFileIO();
// }