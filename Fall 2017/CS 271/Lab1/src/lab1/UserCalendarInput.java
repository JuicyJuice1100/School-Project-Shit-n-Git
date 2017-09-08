/*
 * assumptions: user will input in the following format (mm-dd-yyy)
 */
package lab1;

/**
 * @date 9/7/2017
 * @author espirj96
 */

import java.util.Scanner;
        
public class UserCalendarInput{
    private String userInput;
    private String[] userInputSplit;
    private Scanner scan;
    static int month, day, year;
    
    
    public UserCalendarInput(){
        this.scan = new Scanner(System.in);
        
        System.out.println("Enter new date (mm-dd-yyyy)");
        this.userInput = scan.next();
        this.userInputSplit = userInput.split("-");
        
        this.month = UserInputMonth();
        this.day = UserInputDay();
        this.year = UserInputYear();
    }
    
    //splits the user input and grabs the month
    public int UserInputMonth(){
        int month;
        month = Integer.parseInt(this.userInputSplit[0]);
        if(month > 12 && month < 1){
            month = -1;
        }
        return month;
    }
    
    //splits the user input and grabs the day
    public int UserInputDay() {
        int day;
        day = Integer.parseInt(this.userInputSplit[1]);
        if(day > 31 && day < 1){
            day = -1;
        }
        return day;
    }
    
    //splits the user input an grabs the year
    public int UserInputYear() {
        int year;
        year = Integer.parseInt(this.userInputSplit[2]);
        if(year < 1900 || year > 2099){
            year = -1;
        }
        return year;
    }
}
