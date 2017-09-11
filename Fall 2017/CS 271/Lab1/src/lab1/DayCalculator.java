/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 *
 * @author espirj96
 */
public class DayCalculator extends UserCalendarInput{
    GregorianCalendar test;
    
    DayCalculator(){
        test = new GregorianCalendar(year, month - 1, day);
        PrintString();
    }
    
    public boolean IsLeapYear(int year){
        boolean isLeapYear = false;
        
        if(year % 4 == 0 && year % 100 != 0){
            isLeapYear = true;
        }
        
        return isLeapYear;
    }
    
    public int MonthAdjustment(int month){
        int leapYearAdjustment = 0;
        int adjustment;
        if(IsLeapYear(year) && month == 0 || month == 1){
            leapYearAdjustment = -1;
        }
        
        switch(month){
            case 1: case 10:
               adjustment = 1;
               break;
            case 2: case 3: case 11: 
               adjustment = 4;
               break;
            case 4: case 7:
                adjustment = 0;
                break;
            case 5:
                adjustment = 2;
                break;
            case 6:
                adjustment = 5;
                break;
            case 8:
                adjustment = 5;
                break;
            case 9: case 12:
                adjustment = 6;
                break;
            default:
                adjustment = -1;   
        }
        
        adjustment += leapYearAdjustment;
        
        return adjustment;
    }
    
    public int YearAdjustment(int year){
        int adjustedYear;
        year = year - 1900;
        adjustedYear = year/4;
        return adjustedYear;
    }
    
    public int DayCalculation(){
        int sum, calculation;
        sum = MonthAdjustment(month) + (year - 1900) + day + YearAdjustment(year);
        calculation = sum % 7;
        return calculation;
    }
    
    public String DayCalculation(int calculation){
        switch(calculation){
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 0:
                return "Saturday";
            default:
                return "";
        }
    }
    
    public boolean IsEqual(int calculation){
        int gregorianCalendarDayCalculation = test.get(Calendar.DAY_OF_WEEK);
        boolean isEqual = false;
        
        if(gregorianCalendarDayCalculation == calculation){
            isEqual = true;
        }
        return isEqual;
    }
    
    public void PrintString(){
        System.out.printf("User Input Month: %d \nUser Input Day: %d \nUser Input Year: %d \nDay Calculation: %s \nGregorian Test: %b", month, day, year, DayCalculation(DayCalculation()), IsEqual(DayCalculation()));
    }
}
