package se.chalmers.student.aviato;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class SubscriptionService extends IntentService {

    private static String TAG = "SubscriptionService";

    public SubscriptionService() {
        super("SubscriptionService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            //TO-DO Update information of the subscribed flights on the DB.
            //TO-DO Check Departure times for and create notification if needed.
        }
        Log.i(TAG,"Subscription Service running");
    }

}
