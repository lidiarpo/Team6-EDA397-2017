package se.chalmers.student.aviato.subscriptions;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
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
import se.chalmers.student.aviato.R;
import se.chalmers.student.aviato.Utilities;
import se.chalmers.student.aviato.flights.Flight;
import se.chalmers.student.aviato.flights.FlightParser;
import se.chalmers.student.aviato.flights.RequestQueueSingleton;
import se.chalmers.student.aviato.notifications.Notification;
import se.chalmers.student.aviato.notifications.NotificationActivity;

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
            updateFlightSubscriptions(subscriptionFlights);

            // For every flight we are subscribed to, check if any of them is leaving soon
            boolean notificationGenerated = false;

            //List<Flight> dummies = Utilities.getDummyFlights();
            for (Flight f : subscriptionFlights) {
                Calendar calendar = f.getTime();
                if (calendar != null) {
                    long rawTime = calendar.getTimeInMillis(); // the time of the departure or arrival
                    Calendar now = calendar.getInstance();
                    long timeDelta = rawTime - now.getTimeInMillis();
                    // Notifications should be created for flights in the future only (timeDelta positive)
                    if (timeDelta >= 0 && timeDelta <= Utilities.getTimeToNotify()) {
                        if (generateNotification(f)){
                            // If there is at least one notification generated, play a sound
                            notificationGenerated = true;
                        }
                    }
                }
            }
            if (notificationGenerated) {
                Log.d(TAG, "Playing notification sound");
                playNotificationSound();
            }
        }
    }

    private void playNotificationSound(){
        // Play the standard alarm
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringtone = RingtoneManager.getRingtone(this, uri);
        ringtone.play();

        // Vibrate for half a second
        Vibrator v = (Vibrator)this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);
    }

    private boolean generateNotification(Flight flight) {
        String arrivalOrDeparture = flight.isArrival() ? "arrives" : "leaves";
        int hour = flight.getTime().get(Calendar.HOUR);
        int min = flight.getTime().get(Calendar.MINUTE);
        String notificationText = "Flight to " +
                flight.get("arrivalAirportFsCode") + " " + arrivalOrDeparture + " at " + hour + ":" + min;
        // Add and create a notification only if one does not already exist
        if (!notificationsCRUD.existsNotification(flight.get("flightId"))){
            Log.d(TAG, "Notification text: " + notificationText);
            Notification n = new Notification(flight.get("flightNumber"), notificationText, NotificationActivity.NOTIFICATION_NOT_READ);
            notificationsCRUD.addNotification(n);
        } else {
            Log.i(TAG, "Notification for flight" + flight.get("flightId") + " already exists");
            return false;
        }

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.android_airplane_takeoff)
                        .setContentTitle("Flight alert")
                        .setContentText(notificationText);
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, NotificationActivity.class);

        // The stack builder object will contain an artificial back stack for the started Activity.
        // This ensures that navigating backward from the Activity leads out of your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(NotificationActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Configure the notification to be removed after the user tapping it
        mBuilder.setAutoCancel(true);
        mNotificationManager.notify(0, mBuilder.build());
        return true;
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
