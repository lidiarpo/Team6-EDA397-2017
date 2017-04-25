package se.chalmers.student.aviato;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class to generate oneshot alarms
 */
public class FlightNotifier {

    private AlarmManager alarmMgr;
    private PendingIntent pendingIntent;

    /**
     * Setup a flight notifier, which will generate a notification at the requested time in the future
     * Example: {@code new FlightNotifier(this, "13:32")}
     *
     * @param context
     * @param date the date you want the alarm to be triggered (currently in HH:MM)
     */
    public FlightNotifier(Context context, String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            // Try to parse the supplied string that represents a date
            Date d = sdf.parse(date);
            // If successful then get the hours and minutes
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            int alarmHour = calendar.get(Calendar.HOUR);
            int alarmMinute = calendar.get(Calendar.MINUTE);

            // Create a pending intent that will receive our alarm
            Intent alarmIntent = new Intent(context, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
            alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

            // Set the proper time for our alarm to ring
            calendar.setTimeInMillis(System.currentTimeMillis());
            // calendar.set(2017, Calendar.APRIL, 26, 2, 55, 0); // Beware, months are indexed from 0
            calendar.set(Calendar.HOUR, alarmHour);
            calendar.set(Calendar.MINUTE, alarmMinute);
            calendar.set(Calendar.SECOND, 0);

            // Set the alarm
            alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            Log.v("FlightNotifier", "Alarm is set");

        } catch (ParseException ex) {
            // Should we throw an exception here and crash instead?
            Log.e("FlightNotifer", "Error parsing the supplied date");
        }
    }

    /**
     * Cancel the particular alarm
     */
    public void cancel() {
        if (alarmMgr!= null) {
            alarmMgr.cancel(pendingIntent);
        }
    }
}
