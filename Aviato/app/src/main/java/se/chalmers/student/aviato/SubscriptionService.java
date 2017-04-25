package se.chalmers.student.aviato;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import se.chalmers.student.aviato.DB.FlightsDbHelper;
import se.chalmers.student.aviato.DB.SubscriptionsCRUD;


public class SubscriptionService extends IntentService {

    private static String TAG = "SubscriptionService";
    FlightsDbHelper mDbHelper;
    SubscriptionsCRUD subscriptionsCRUD;

    public SubscriptionService() {
        super("SubscriptionService");
        mDbHelper = new FlightsDbHelper(this);
        subscriptionsCRUD = new SubscriptionsCRUD(mDbHelper);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG,"Subscription Service running");
        if (intent != null) {
            //TO-DO Update information of the subscribed flights on the DB.
            //TO-DO Check Departure times for and create notification if needed.
            //subscriptionsCRUD.deleteSubscription("N/A");
            subscriptionsCRUD.readSubscriptions();
        }
    }

}
