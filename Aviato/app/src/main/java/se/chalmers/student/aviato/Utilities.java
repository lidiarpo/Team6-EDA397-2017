package se.chalmers.student.aviato;


import java.util.Calendar;
import java.util.Date;

public class Utilities {

    public final static String APPID = "0a1973b0";
    public final static String APPKEY = "18c1fc7152a12bcf3f8be5ebb3fd34f2";

    public final static String API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public final static String VIEW_DATE_FORMAT = "HH:mm";

    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}
