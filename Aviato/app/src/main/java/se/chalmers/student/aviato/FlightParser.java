package se.chalmers.student.aviato;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Set;


public class FlightParser{

    /**
     * Given a JSON object containing flight data, parse it and return a list of flights that can
     * be easily manipulated in code.
     * @param flights a JSON object containing the flight data
     * @return a list of Flight class instances
     */
   public ArrayList<Flight> parseFlights(JSONObject flights){
       ArrayList<Flight> flightList = new ArrayList<>(); // The list of flights to be returned
       try {
           JSONArray allFlights = flights.getJSONArray("Flights");
           // Iterate through all the collected flights
           for (int i = 0; i < allFlights.length(); i++) {
               JSONObject flightObject = allFlights.getJSONObject(i);
               Flight flight = new Flight();
               Set<String> flightAttributes = flight.getAttributes();
               // Fetch each attribute from the JSON object
               for (String attribute : flightAttributes) {
                   flight.set(attribute, flightObject.getString(attribute));
               }
               // By now we should have fetched all flight attributes so add the flight to the list
               flightList.add(flight);
           }
       } catch (JSONException e) {
           e.printStackTrace();
       }
       return flightList;
   }

}
