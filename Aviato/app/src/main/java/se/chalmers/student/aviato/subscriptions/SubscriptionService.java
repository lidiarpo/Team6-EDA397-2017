package se.chalmers.student.aviato.subscriptions;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import se.chalmers.student.aviato.DB.FlightsDbHelper;
import se.chalmers.student.aviato.DB.NotificationsCRUD;
import se.chalmers.student.aviato.DB.NotificationsDbHelper;
import se.chalmers.student.aviato.DB.SubscriptionsCRUD;
import se.chalmers.student.aviato.flights.RequestQueueSingleton;
import se.chalmers.student.aviato.flights.Flight;
import se.chalmers.student.aviato.flights.FlightParser;

import static se.chalmers.student.aviato.Utilities.APPID;
import static se.chalmers.student.aviato.Utilities.APPKEY;


public class SubscriptionService extends IntentService {

    private static String TAG = "SubscriptionService";
    FlightsDbHelper mDbHelper;
    SubscriptionsCRUD subscriptionsCRUD;
    NotificationsDbHelper notificationsDbHelper;
    NotificationsCRUD notificationsCRUD;
    private RequestQueueSingleton queue;

    public SubscriptionService() {
        super("SubscriptionService");
        mDbHelper = new FlightsDbHelper(this);
        subscriptionsCRUD = new SubscriptionsCRUD(mDbHelper);
        notificationsDbHelper = new NotificationsDbHelper(this);
        notificationsCRUD = new NotificationsCRUD(notificationsDbHelper);
        queue = RequestQueueSingleton.getInstance(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG,"Subscription Service running");
        if (intent != null) {

            List<Flight> subscriptionFlights = subscriptionsCRUD.readSubscriptions();
            //TO-DO Check Departure times for and create notification if needed.

            updateFlightSubscriptions(subscriptionFlights);
        }
    }

    private void updateFlightSubscriptions(List<Flight> subscriptionFlights){
        Calendar cal = Calendar.getInstance();

        for (Flight flight:subscriptionFlights) {
            if (false){
                //TO-DO Compare date of the flight, if it has passed remove it from subscription database
            }else {
                String url = "https://api.flightstats.com/flex/flightstatus/rest/v2/json/flight/status/"
                        + flight.get("flightId") + "?appId=" + APPID + "&appKey=" + APPKEY;
                RequestFuture<JSONObject> future = RequestFuture.newFuture();
                JsonObjectRequest request = new JsonObjectRequest(url, null, future, future);
                queue.addToRequestQueue(request);

                try {
                    JSONObject response = future.get();
                    Flight f = new FlightParser().parseSingleFlight(response);
                    subscriptionsCRUD.updateSubscription(f);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
