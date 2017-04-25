package se.chalmers.student.aviato;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

import se.chalmers.student.aviato.DB.FlightsDbHelper;
import se.chalmers.student.aviato.DB.NotificationsCRUD;
import se.chalmers.student.aviato.DB.NotificationsDbHelper;
import se.chalmers.student.aviato.DB.SubscriptionsCRUD;


public class SubscriptionService extends IntentService {

    private static String TAG = "SubscriptionService";
    FlightsDbHelper mDbHelper;
    SubscriptionsCRUD subscriptionsCRUD;
    NotificationsDbHelper notificationsDbHelper;
    NotificationsCRUD notificationsCRUD;

    public SubscriptionService() {
        super("SubscriptionService");
        mDbHelper = new FlightsDbHelper(this);
        subscriptionsCRUD = new SubscriptionsCRUD(mDbHelper);
        notificationsDbHelper = new NotificationsDbHelper(this);
        notificationsCRUD = new NotificationsCRUD(notificationsDbHelper);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG,"Subscription Service running");
        if (intent != null) {
            List<Flight> subscriptionFlights = subscriptionsCRUD.readSubscriptions();
            //TO-DO Check Departure times for and create notification if needed.

            //subscriptionsCRUD.deleteSubscription("N/A");
            updateFlightSubscriptions(subscriptionFlights);
        }
    }

    private void updateFlightSubscriptions(List<Flight> subscriptionFlights){
        Calendar cal = Calendar.getInstance();

        for (Flight flight:subscriptionFlights) {
            if (false){
                //TO-DO Compare date of the flight, if it has passed remove it from subscription database
            }else {
                //TO-DO Retrieve new info from API for the flight synchronously. Otherwise the Service could be dead after
                subscriptionsCRUD.updateSubscription(flight);
            }
        }

    }

}
