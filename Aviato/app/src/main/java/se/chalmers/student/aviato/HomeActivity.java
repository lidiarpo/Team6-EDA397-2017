package se.chalmers.student.aviato;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import se.chalmers.student.aviato.flights.FlightActivity;
import se.chalmers.student.aviato.notifications.NotificationActivity;
import se.chalmers.student.aviato.settings.SettingsActivity;
import se.chalmers.student.aviato.subscriptions.SubscriptionActivity;


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
    }

}
