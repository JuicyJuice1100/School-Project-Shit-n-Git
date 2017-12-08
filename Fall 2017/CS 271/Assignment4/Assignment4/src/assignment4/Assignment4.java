
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4;

import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;

/**
 *
 * @author Juice
 */
public class Assignment4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        
        JFileChooser chosen = new JFileChooser();
        int returnVal = chosen.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            Graph fGraph = new Graph(false);
            Scanner file = new Scanner(chosen.getSelectedFile());
            ArrayList<Vertex> list = new ArrayList<>();
            HashMap<String, Integer> mostPopular = new HashMap<>();
            int popularCount = Integer.MIN_VALUE;
            int counter = 0;
            /*******************************************************************
            * This is a little dirty but basically grabbing each friend in order
            * and creating an edge.  We assume that the text contains information
            * in the correct order
            ********************************************************************/
            while(file.hasNext()){
                if(counter%3 == 2){
                    Edge fEdge = new Edge(list.get(0), list.get(1), file.nextInt());
                    fGraph.insertEdge(fEdge);
                    list.clear();
                } else{
                    Friends friend = new Friends(file.next(), null);
                    fGraph.insertVertex(friend);
                    list.add(friend);
                }
                counter++;
            }
            Iterator<Map.Entry<String, ArrayList<Edge>>> entries = fGraph.getMap().entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, ArrayList<Edge>> entry = entries.next();
                if(entry.getValue().size() >= popularCount){
                    if(entry.getValue().size() != popularCount){
                        mostPopular.clear();
                    }
                    mostPopular.put(entry.getKey(), entry.getValue().size());
                    popularCount = entry.getValue().size();
                }
            }
            System.out.println("Most Popular" + mostPopular.toString());
            fGraph.getMap();
        }
    }

}
