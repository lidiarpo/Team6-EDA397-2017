package se.chalmers.student.aviato;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceGroup;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import se.chalmers.student.aviato.flights.FlightActivity;
import se.chalmers.student.aviato.notifications.NotificationActivity;
import se.chalmers.student.aviato.settings.SettingsActivity;
import se.chalmers.student.aviato.subscriptions.SubscriptionActivity;
import se.chalmers.student.aviato.subscriptions.SubscriptionReceiver;


public class HomeActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);


        //add flight image button to homescreen
        Button flightBtn = (Button) findViewById(R.id.flight_button);
        Button notificationBtn = (Button) findViewById(R.id.notification_button);
        Button subscriptionBtn = (Button) findViewById(R.id.subscription_button);
        Button settingsBtn = (Button) findViewById(R.id.settings_button);

        flightBtn.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v){
               Intent loadFlightActivity = new Intent(HomeActivity.this, FlightActivity.class);
               startActivity(loadFlightActivity);
           }
        });

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent loadNotificationActivity = new Intent(HomeActivity.this, NotificationActivity.class);
                startActivity(loadNotificationActivity);
            }
        });

        subscriptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent loadSupscriptionActivity = new Intent(HomeActivity.this, SubscriptionActivity.class);
                startActivity(loadSupscriptionActivity);
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent loadSettingsActivity = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(loadSettingsActivity);
            }
        });

        scheduleAlarm();
    }

    /**
     * Schedules an alarm to launch a service in the background to update
     * flight information from subscriptions and create notifications
     */
    public void scheduleAlarm() {
        Intent intent = new Intent(getApplicationContext(), SubscriptionReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, SubscriptionReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long firstMillis = System.currentTimeMillis();
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        int updateFreq = Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(this).getString("list_subscription_update","15"));
        switch (updateFreq){
            case 15: alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                    AlarmManager.INTERVAL_FIFTEEN_MINUTES, pIntent);
                break;
            case 30: alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                    AlarmManager.INTERVAL_HALF_HOUR, pIntent);
                break;
            case 60: alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                    AlarmManager.INTERVAL_HOUR, pIntent);
                break;
            default: alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                    AlarmManager.INTERVAL_FIFTEEN_MINUTES, pIntent);
        }
    }

}
