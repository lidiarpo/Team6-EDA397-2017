package se.chalmers.student.aviato.flights;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AirlineFilter implements FlightFilter{

    @Override
    public List<Flight> filter(List<Flight> flights, String... parameters) {

        String airlineCode = parameters[0];
        List<Flight> flightResult = new ArrayList<Flight>();

        for (Flight flight: flights) {
            if (flight.get("carrierFsCode").equals(airlineCode)){
                flightResult.add(flight);
            }

        }
        Log.d("Filtered Flight list: ",flightResult +"");
        return flightResult;
    }
}
