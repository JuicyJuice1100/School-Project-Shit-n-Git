/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

/**
 *
 * @author espirj96
 */
public class DayCalculator extends UserCalendarInput{
    
    DayCalculator(){
        System.out.println(MonthAdjustment(month));
        System.out.println(YearAdjustment(year));
        System.out.println(DayCalculation());
        System.out.println(month);
        System.out.println(day);
        System.out.println(year);
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
        if(IsLeapYear(year) && month == 1 || month == 2){
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
        sum = MonthAdjustment(month) + year + day + YearAdjustment(year);
        calculation = sum % 4;
        return calculation;
    }
}
