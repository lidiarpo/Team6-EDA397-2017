package se.chalmers.student.aviato;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import static se.chalmers.student.aviato.Utilities.*;


public class FlightParser{

    private static String TAG = "FlightParser";

    /**
     * Given a JSON object containing flight data, parse it and return a list of flights that can
     * be easily manipulated in code.
     * @param flights a JSON object containing the flight data
     * @return a list of Flight class instances
     */
    public ArrayList<Flight> parseFlights(JSONObject flights){

        SimpleDateFormat format = new SimpleDateFormat(API_DATE_FORMAT);
        SimpleDateFormat viewFormat = new SimpleDateFormat(VIEW_DATE_FORMAT);
        Date date = new Date();

        ArrayList<Flight> flightList = new ArrayList<>(); // The list of flights to be returned
        try {
            JSONArray allFlights = flights.getJSONArray("flightStatuses");
            // Iterate through all the collected flights
            for (int i = 0; i < allFlights.length(); i++) {
                flightList.add(parseFlightStatus(allFlights.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return flightList;
    }

    /**
     * Given a JSON object containing flight data, parse it and return a flight that can
     * be easily manipulated in code.
     * @param flightObject a JSON object containing the flight data
     * @return a Flight class instance
     */
    public Flight parseSingleFlight(JSONObject flightObject){
        try {
            return parseFlightStatus(flightObject.getJSONObject("flightStatus"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Given a JSON object containing flight status data, parse it and return a flight that can
     * be easily manipulated in code.
     * @param flightStatus a JSON object containing the flight data
     * @return a Flight class instance
     */
    private Flight parseFlightStatus(JSONObject flightStatus){

        SimpleDateFormat format = new SimpleDateFormat(API_DATE_FORMAT);
        SimpleDateFormat viewFormat = new SimpleDateFormat(VIEW_DATE_FORMAT);
        Date date = new Date();

        Flight flight= new Flight();

        //TO-DO: Refactor code to make it API agnostic and cleaner handling of optional fields
        try {
            Set<String> flightAttributes = flight.getAttributes();
            String value;
            // Fetch each attribute from the JSON object
            for (String attribute : flightAttributes) {
                value = null;
                if (attribute.equals("departureDate") || attribute.equals("arrivalDate")) {
                    try {
                        if (flightStatus.optJSONObject(attribute)!=null) {
                            date = format.parse(flightStatus.getJSONObject(attribute).optString("dateUtc",""));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    value = viewFormat.format(date);
                } else if (attribute.equals("flightType")) {
                    if (flightStatus.optJSONObject(attribute)!=null) {
                        value = flightStatus.getJSONObject("schedule").optString(attribute);
                    }
                }else if (attribute.equals("flightDurations")){
                    if (flightStatus.optJSONObject(attribute)!=null) {
                        value = flightStatus.getJSONObject(attribute).optString("scheduledBlockMinutes");
                    }
                }else if (attribute.equals("departureTerminal")){
                    if (flightStatus.optJSONObject("airportResources")!=null) {
                        value = flightStatus.getJSONObject("airportResources").optString(attribute);
                    }
                }else if (attribute.equals("departureGate")){
                    if (flightStatus.optJSONObject("airportResources")!=null) {
                        value = flightStatus.getJSONObject("airportResources").optString(attribute);
                    }
                }else if (attribute.equals("arrivalTerminal")){
                    if (flightStatus.optJSONObject("airportResources")!=null) {
                        value = flightStatus.getJSONObject("airportResources").optString(attribute);
                    }
                }else if (attribute.equals("arrivalGate")){
                    if (flightStatus.optJSONObject("airportResources")!=null) {
                        value = flightStatus.getJSONObject("airportResources").optString(attribute);
                    }
                }else{
                    value = flightStatus.optString(attribute);
                }

                if (value!=null && !value.isEmpty()) {
                    flight.set(attribute, value);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG,flight.toString());

        return flight;
    }


}