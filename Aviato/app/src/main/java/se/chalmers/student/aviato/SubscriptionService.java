package se.chalmers.student.aviato;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.student.aviato.DB.FlightsContract.FlightEntry;
import se.chalmers.student.aviato.DB.FlightsDbHelper;
import se.chalmers.student.aviato.DB.NotificationsCRUD;
import se.chalmers.student.aviato.DB.NotificationsDbHelper;


public class SubscriptionService extends IntentService {

    private static String TAG = "SubscriptionService";
    FlightsDbHelper mDbHelper;
    NotificationsDbHelper notificationsDbHelper;
    SQLiteDatabase db;
    NotificationsCRUD notificationsCRUD;

    public SubscriptionService() {
        super("SubscriptionService");
        mDbHelper = new FlightsDbHelper(this);
        notificationsDbHelper = new NotificationsDbHelper(this);
        notificationsCRUD = new NotificationsCRUD(notificationsDbHelper);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            //TO-DO Update information of the subscribed flights on the DB.
            //TO-DO Check Departure times for and create notification if needed.
            //addEntry();
            //readEntry();
            //deleteSubscription("8821");
            //Notification test = new Notification();
            //test.setFlightId("5522145");
            //test.setText("Hello World!");
            //notificationsCRUD.addNotification(test);
            //notificationsCRUD.readNotifications();
            //notificationsCRUD.existsNotification("5522145");
            //notificationsCRUD.deleteNotification("5522145");
            //notificationsCRUD.existsNotification("5522145");
            //notificationsCRUD.readNotifications();
            readSubscriptions();
        }
        Log.i(TAG,"Subscription Service running");
    }

    private void addSubscription(Flight flight){

        db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FlightEntry.COLUMN_NAME_FLIGHT_ID, flight.get("flightId"));
        values.put(FlightEntry.COLUMN_NAME_CARRIER_FS_CODE,flight.get("carrierFsCode"));
        values.put(FlightEntry.COLUMN_NAME_FLIGHT_NUMBER,flight.get("flightNumber"));
        values.put(FlightEntry.COLUMN_NAME_DEPARTURE_AIRPORT_FS_CODE,flight.get("departureAirportFsCode"));
        values.put(FlightEntry.COLUMN_NAME_ARRIVAL_AIRPORT_FS_CODE,flight.get("arrivalAirportFsCode"));
        values.put(FlightEntry.COLUMN_NAME_DEPARTURE_DATE,flight.get("departureDate"));
        values.put(FlightEntry.COLUMN_NAME_ARRIVAL_DATE,flight.get("arrivalDate"));
        values.put(FlightEntry.COLUMN_NAME_STATUS,flight.get("status"));
        values.put(FlightEntry.COLUMN_NAME_FLIGHT_TYPE,flight.get("flightType"));
        values.put(FlightEntry.COLUMN_NAME_FLIGHT_DURATIONS,flight.get("flightDurations"));
        values.put(FlightEntry.COLUMN_NAME_DEPARTURE_TERMINAL,flight.get("departureTerminal"));
        values.put(FlightEntry.COLUMN_NAME_DEPARTURE_GATE,flight.get("departureGate"));
        values.put(FlightEntry.COLUMN_NAME_ARRIVAL_TERMINAL,flight.get("arrivalTerminal"));
        values.put(FlightEntry.COLUMN_NAME_ARRIVAL_GATE,flight.get("arrivalGate"));

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(FlightEntry.TABLE_NAME, null, values);
        Log.d(TAG,"Added new row to the subscriptions DB with ID:" + newRowId);
    }

    private void deleteSubscription(String flightId){

        db = mDbHelper.getWritableDatabase();

        String selection = FlightEntry.COLUMN_NAME_FLIGHT_ID + " LIKE ?";
        String[] selectionArgs = { flightId};
        db.delete(FlightEntry.TABLE_NAME, selection, selectionArgs);

    }

    private void updateSubscription(Flight flight){
        deleteSubscription(flight.get("flightId"));
        addSubscription(flight);
    }

    private boolean existsFlight(Flight flight){
        List<Flight> flights = readSubscriptions();
        return flights.contains(flight);
    }

    private List<Flight> readSubscriptions(){

        db = mDbHelper.getReadableDatabase();
        List<Flight> flights = new ArrayList<Flight>();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FlightEntry.COLUMN_NAME_FLIGHT_ID,
                FlightEntry.COLUMN_NAME_CARRIER_FS_CODE,
                FlightEntry.COLUMN_NAME_FLIGHT_NUMBER,
                FlightEntry.COLUMN_NAME_DEPARTURE_AIRPORT_FS_CODE,
                FlightEntry.COLUMN_NAME_ARRIVAL_AIRPORT_FS_CODE,
                FlightEntry.COLUMN_NAME_DEPARTURE_DATE,
                FlightEntry.COLUMN_NAME_ARRIVAL_DATE,
                FlightEntry.COLUMN_NAME_STATUS,
                FlightEntry.COLUMN_NAME_FLIGHT_TYPE,
                FlightEntry.COLUMN_NAME_FLIGHT_DURATIONS,
                FlightEntry.COLUMN_NAME_DEPARTURE_TERMINAL,
                FlightEntry.COLUMN_NAME_DEPARTURE_GATE,
                FlightEntry.COLUMN_NAME_ARRIVAL_TERMINAL,
                FlightEntry.COLUMN_NAME_ARRIVAL_GATE};

        Cursor cursor = db.query(
                FlightEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while(cursor.moveToNext()) {
            Flight flight = new Flight();
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                flight.set(cursor.getColumnName(i),cursor.getString(i));
            }
            Log.d(TAG,flight.toString());
            flights.add(flight);
        }
        cursor.close();

        return flights;
    }

}
