package se.chalmers.student.aviato.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.student.aviato.flights.Flight;
import se.chalmers.student.aviato.DB.FlightsContract.FlightEntry;


public class SubscriptionsCRUD {

    private static String TAG = "SubscriptionsCRUD";
    FlightsDbHelper mDbHelper;
    SQLiteDatabase db;

    public SubscriptionsCRUD(FlightsDbHelper dbHelper){
        mDbHelper = dbHelper;
    }

    public void addSubscription(Flight flight){

        db = mDbHelper.getWritableDatabase();

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
        values.put(FlightEntry.COLUMN_NAME_CARRIER_NAME,flight.get("carrierName"));
        values.put(FlightEntry.COLUMN_NAME_DEPARTURE_AIRPORT_NAME,flight.get("departureAirportName"));
        values.put(FlightEntry.COLUMN_NAME_ARRIVAL_AIRPORT_NAME,flight.get("arrivalAirportName"));
        values.put(FlightEntry.COLUMN_NAME_SCHEDULED_GATE_DEPARTURE,flight.get("scheduledGateDeparture"));
        values.put(FlightEntry.COLUMN_NAME_SCHEDULED_GATE_ARRIVAL,flight.get("scheduledGateArrival"));


        long newRowId = db.insert(FlightEntry.TABLE_NAME, null, values);
        Log.d(TAG,"Added new row to the subscriptions DB with ID:" + newRowId);
    }

    public void deleteSubscription(String flightId){

        db = mDbHelper.getWritableDatabase();

        String selection = FlightEntry.COLUMN_NAME_FLIGHT_ID + " LIKE ?";
        String[] selectionArgs = { flightId};
        db.delete(FlightEntry.TABLE_NAME, selection, selectionArgs);
        Log.d(TAG,"Deleted row from subscriptions DB with flightId:" + flightId);

    }

    public void updateSubscription(Flight flight){
        deleteSubscription(flight.get("flightId"));
        addSubscription(flight);
    }

    public boolean existsFlight(Flight flight){
        List<Flight> flights = readSubscriptions();
        for (Flight f:flights) {
            if (f.get("flightId").equals(flight.get("flightId"))){
                return true;
            }
        }
        return false;
    }

    public List<Flight> readSubscriptions(){

        db = mDbHelper.getReadableDatabase();
        List<Flight> flights = new ArrayList<Flight>();

        String[] projection = {
                FlightEntry.COLUMN_NAME_FLIGHT_ID,
                FlightEntry.COLUMN_NAME_CARRIER_FS_CODE,
                FlightEntry.COLUMN_NAME_CARRIER_NAME,
                FlightEntry.COLUMN_NAME_FLIGHT_NUMBER,
                FlightEntry.COLUMN_NAME_DEPARTURE_AIRPORT_FS_CODE,
                FlightEntry.COLUMN_NAME_DEPARTURE_AIRPORT_NAME,
                FlightEntry.COLUMN_NAME_ARRIVAL_AIRPORT_FS_CODE,
                FlightEntry.COLUMN_NAME_ARRIVAL_AIRPORT_NAME,
                FlightEntry.COLUMN_NAME_DEPARTURE_DATE,
                FlightEntry.COLUMN_NAME_SCHEDULED_GATE_DEPARTURE,
                FlightEntry.COLUMN_NAME_ARRIVAL_DATE,
                FlightEntry.COLUMN_NAME_SCHEDULED_GATE_ARRIVAL,
                FlightEntry.COLUMN_NAME_STATUS,
                FlightEntry.COLUMN_NAME_FLIGHT_TYPE,
                FlightEntry.COLUMN_NAME_FLIGHT_DURATIONS,
                FlightEntry.COLUMN_NAME_DEPARTURE_TERMINAL,
                FlightEntry.COLUMN_NAME_DEPARTURE_GATE,
                FlightEntry.COLUMN_NAME_ARRIVAL_TERMINAL,
                FlightEntry.COLUMN_NAME_ARRIVAL_GATE};

        Cursor cursor = db.query(
                FlightsContract.FlightEntry.TABLE_NAME,                     // The table to query
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
