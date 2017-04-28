package se.chalmers.student.aviato.DB;

import android.provider.BaseColumns;

public class FlightsContract {
        private FlightsContract() {}

        public static class FlightEntry implements BaseColumns {
            public static final String TABLE_NAME = "flights";
            public static final String COLUMN_NAME_FLIGHT_ID = "flightId";
            public static final String COLUMN_NAME_CARRIER_FS_CODE = "carrierFsCode";
            public static final String COLUMN_NAME_FLIGHT_NUMBER = "flightNumber";
            public static final String COLUMN_NAME_DEPARTURE_AIRPORT_FS_CODE = "departureAirportFsCode";
            public static final String COLUMN_NAME_ARRIVAL_AIRPORT_FS_CODE = "arrivalAirportFsCode";
            public static final String COLUMN_NAME_DEPARTURE_DATE = "departureDate";
            public static final String COLUMN_NAME_ARRIVAL_DATE = "arrivalDate";
            public static final String COLUMN_NAME_STATUS = "status";
            public static final String COLUMN_NAME_FLIGHT_TYPE = "flightType";
            public static final String COLUMN_NAME_FLIGHT_DURATIONS = "flightDurations";
            public static final String COLUMN_NAME_DEPARTURE_TERMINAL = "departureTerminal";
            public static final String COLUMN_NAME_DEPARTURE_GATE = "departureGate";
            public static final String COLUMN_NAME_ARRIVAL_TERMINAL = "arrivalTerminal";
            public static final String COLUMN_NAME_ARRIVAL_GATE = "arrivalGate";
            public static final String COLUMN_NAME_CARRIER_NAME = "carrierName";
            public static final String COLUMN_NAME_DEPARTURE_AIRPORT_NAME = "departureAirportName";
            public static final String COLUMN_NAME_ARRIVAL_AIRPORT_NAME = "arrivalAirportName";
            public static final String COLUMN_NAME_SCHEDULED_GATE_DEPARTURE = "scheduledGateDeparture";
            public static final String COLUMN_NAME_SCHEDULED_GATE_ARRIVAL = "scheduledGateArrival";
        }
    }
