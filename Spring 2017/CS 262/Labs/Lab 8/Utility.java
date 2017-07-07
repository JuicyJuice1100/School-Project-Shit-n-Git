import java.util.Scanner;

public class Utility{
    private Scanner scan;
    private DataSet dataSet;
    private int menuOption;
    
    public Utility(){
        scan = new Scanner(system.in);
        dataSet = new DataSet();
        menuOption = 1;

        utilityInt();
    }

    public void utilityInt(){
        system.out.println("Please enter an input\n
        0 to exit\n
        1 to return size of dataSet\n
        2 to add\n
        3 to insert at index\n
        4 to get element at index\n
        5 to set element at index\n
        6 to remove element at index\n
        7 to remove given element\n
        8 to find index of given element\n
        9 to clear dataSet\n
        10 to print dataSet\n");

        menuOption = scan.nextint();
        while(menuOption != 0){
            switch(menuOption){
                case 1:
                    system.out.println(data.size());
                    break;
                case 2:
                    system.out.println(add(scan.nextInt()));
                    break;
                case 3:
                    system.out.println(add(scan.nextInt(), scan.nextInt()));
                    break;
                case 4:
                    system.out.println(get(scan.nextInt()));
                    break;
                case 5:
                    system.out.println(set(scan.nextInt(), scan.nextInt()));
                    break;
                case 6:
                    system.out.println(remove(scan.nextInt()));
                    break;
                case 7:
                    system.out.println(remove(scan.nextInt()));
                    break;
                case 8:
                    system.out.println(indexOf(scan.nextInt()));
                    break;
                case 9: 
                    data.clear();
                    break;
                case 10:
                    system.out.println(data.toString());
                    break;
            }
        }
    }
}