package se.chalmers.student.aviato;


import java.util.Calendar;
import java.util.Date;

public class Utilities {

    public final static String APPID = "0eae5c29";
    public final static String APPKEY = "27891fb90c9eaa671bac171fdad57ac1";

    public final static String API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public final static String VIEW_DATE_FORMAT = "HH:mm";

    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}
