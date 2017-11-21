/*
 * Use LPHashTable to create a program that updates the live files and syncs the
 * backup files
 */
package assignment3;

import java.io.*;
import java.util.TreeMap;

/**
 *
 * @author Justin Espiritu
 */
public class Assignment3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LPHashTable<String, File> liveHash = new LPHashTable();
        LPHashTable<String, File> backupHash = new LPHashTable();
        LPHashTable<Long, File> lastModified = new LPHashTable();
        TreeMap replace = new TreeMap();
        TreeMap add = new TreeMap();
        TreeMap remove = new TreeMap();
        
        final String REMOVE = "REMOVE";
        final String ADD = "ADD";
        final String REPLACE = "REPLACE";
        
        try{
            FileInputStream fstream = new FileInputStream("../backup.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            
            String liveLocation, backupLocation;
        
            liveLocation = br.readLine().trim();
            backupLocation = br.readLine().trim();
            
            File live = new File(liveLocation);
            File backup = new File(backupLocation);
            
            //initialize all files in live and save their lastModified time
            for(File file: live.listFiles()){
                lastModified.insert(file, file.lastModified());
            }
            
            for(File backupFile: backup.listFiles()){
                //initialize all backup to remove and adjust from there
                backupHash.insert(backupFile, REMOVE);
                remove.put(backupFile, backupFile);
                if(liveHash.search(backupFile) != null){
                    //if backupFile does exist then it either doesn't need to get updated or needs to get replaced
                    backupHash.delete(backupFile);
                    if(backupFile.lastModified() < lastModified.search(backupFile)){
                        backupHash.insert(backupFile, REPLACE);
                        remove.remove(backupFile);
                        replace.put(backupFile, backupFile);
                    }  
                }
            }
            
            for(File liveFile: live.listFiles()){
                if(backupHash.search(liveFile) == null){
                    backupHash.insert(liveFile, ADD);
                    add.put(liveFile, liveFile);
                }
            }
            
            //instead of printing nothing, if anything is empty it prints none
            if(replace.isEmpty()){
                System.out.println("Replace: none");
            }
            else{
                System.out.println("Replace: " + replace.toString());
            }
            
            if(add.isEmpty()){
                System.out.println("Add: none");
            }
            else{
                System.out.println("Add: " + add.toString());
            }
            
            if(remove.isEmpty()){
                System.out.println("Remove: none");
            }
            else{
                System.out.println("Remove: " + remove.toString());
            }
            
            br.close();
        }
        catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
