package se.chalmers.student.aviato.flights;


import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class TimeWindowFilter implements FlightFilter {

    private static String TAG = "TimeWindowFilter";

    @Override
    public List<Flight> filter(List<Flight> flights, String... parameters) {

        Integer timeWindow = Integer.parseInt(parameters[0]);

        List<Flight> flightResult = new ArrayList<Flight>();

        for (Flight flight: flights) {
            Calendar cal = flight.getTime();
            cal.setTimeZone(TimeZone.getDefault());
            Calendar now = Calendar.getInstance();
            long rawTime = cal.getTimeInMillis(); // the time of the departure or arrival
            long timeDelta = rawTime - now.getTimeInMillis();
            Log.d(TAG,"timeDelta is:" + timeDelta);
            // Notifications should be created for flights in the future only (timeDelta positive)
            if (timeDelta >= 0 && timeDelta <= timeWindow*3600000) {
                flightResult.add(flight);
            }

        }

        return flightResult;
    }
}
