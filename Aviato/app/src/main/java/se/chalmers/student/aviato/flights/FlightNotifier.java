package se.chalmers.student.aviato.flights;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import se.chalmers.student.aviato.Utilities;
import se.chalmers.student.aviato.notifications.AlarmReceiver;

/**
 * Class to generate oneshot alarms
 */
public class FlightNotifier {

    private AlarmManager alarmMgr;
    private PendingIntent pendingIntent;

    /**
     * Setup a flight notifier, which will generate a notification at the requested time in the future
     * @param context
     * @param date the date you want the alarm to be triggered (currently in a format such as: 2017-04-26T11:15:00.000Z)
     */
    public FlightNotifier(Context context, String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(Utilities.API_DATE_FORMAT);
        try {
            // Try to parse the supplied string that represents a date
            Date d = sdf.parse(date);
            // If successful then set the appropriate date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);

            init(context, calendar.getTimeInMillis());
        } catch (ParseException ex) {
            // Should we throw an exception here and crash instead?
            Log.e("FlightNotifer", "Error parsing the supplied date");
        }
    }

    /**
     * Setup a flight notifier, which will generate a notification at the requested time in the future
     * Example for setting an alarm 10 seconds later: {@code new FlightNotifier(this, Calendar.getInstance().getTimeInMillis() + 10 * 1000)}
     * @param context
     * @param millisToTriggerAlarm unix time in milliseconds that the alarm is triggered
     */
    public FlightNotifier(Context context, long millisToTriggerAlarm) {
        init(context, millisToTriggerAlarm);
    }

    /**
     * Initializer to be used by the different constructors
     * @param context
     * @param millisToTriggerAlarm
     */
    private void init(Context context, long millisToTriggerAlarm) {
        // Create a pending intent that will receive our alarm
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        // Set the alarm
        alarmMgr.set(AlarmManager.RTC_WAKEUP, millisToTriggerAlarm, pendingIntent);
        Log.v("FlightNotifier", "Alarm is set");
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
