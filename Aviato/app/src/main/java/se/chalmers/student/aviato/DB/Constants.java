package se.chalmers.student.aviato.DB;

import se.chalmers.student.aviato.DB.FlightsContract.FlightEntry;

public final class Constants {

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FlightEntry.TABLE_NAME + " (" +
                    FlightEntry._ID + " INTEGER PRIMARY KEY," +
                    FlightEntry.COLUMN_NAME_FLIGHT_ID + " TEXT," +
                    FlightEntry.COLUMN_NAME_CARRIER_FS_CODE + " TEXT," +
                    FlightEntry.COLUMN_NAME_FLIGHT_NUMBER + " TEXT," +
                    FlightEntry.COLUMN_NAME_DEPARTURE_AIRPORT_FS_CODE + " TEXT," +
                    FlightEntry.COLUMN_NAME_ARRIVAL_AIRPORT_FS_CODE + " TEXT," +
                    FlightEntry.COLUMN_NAME_DEPARTURE_DATE + " TEXT," +
                    FlightEntry.COLUMN_NAME_ARRIVAL_DATE + " TEXT," +
                    FlightEntry.COLUMN_NAME_STATUS + " TEXT," +
                    FlightEntry.COLUMN_NAME_FLIGHT_TYPE + " TEXT," +
                    FlightEntry.COLUMN_NAME_FLIGHT_DURATIONS + " TEXT," +
                    FlightEntry.COLUMN_NAME_DEPARTURE_TERMINAL + " TEXT," +
                    FlightEntry.COLUMN_NAME_DEPARTURE_GATE + " TEXT," +
                    FlightEntry.COLUMN_NAME_ARRIVAL_TERMINAL + " TEXT," +
                    FlightEntry.COLUMN_NAME_ARRIVAL_GATE + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FlightEntry.TABLE_NAME;
}
