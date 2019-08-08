package root.checkers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormat {


    /*Returns the year as a String From a Date input*/
    public String year(Date date){

        SimpleDateFormat yearFormat=new SimpleDateFormat("yyyy");
       return yearFormat.format(date);
    }


    /*Returns the month as a String From a Date input*/
    public String month(Date date){

        SimpleDateFormat monthFormat=new SimpleDateFormat("MMM");
        return monthFormat.format(date);
    }


    /*Returns the day as a String From a Date input*/
    public int day(Date date){

        SimpleDateFormat dayFormat=new SimpleDateFormat("dd");
        return Integer.parseInt(dayFormat.format(date));
    }


    /*Returns the time as a String From a Date input*/
    public String time(Date date){

        SimpleDateFormat timeFormat=new SimpleDateFormat("kk:mm:ss");
        return timeFormat.format(date);
    }
}
