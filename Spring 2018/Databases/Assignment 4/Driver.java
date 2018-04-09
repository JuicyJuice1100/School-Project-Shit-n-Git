import java.io.*;
import java.util.*;

public class Driver {
    public static void main(String[] args){
        String csvFile = "family.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] family = line.split(cvsSplitBy);
                System.out.println(Arrays.toString(family));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}