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

    public Flight parseSingleFlight(JSONObject flightObject){
        try {
            return parseFlightStatus(flightObject.getJSONObject("flightStatus"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Flight parseFlightStatus(JSONObject flightStatus){

        SimpleDateFormat format = new SimpleDateFormat(API_DATE_FORMAT);
        SimpleDateFormat viewFormat = new SimpleDateFormat(VIEW_DATE_FORMAT);
        Date date = new Date();

        Flight flight= new Flight();

        try {
            //flightObject = flightJson.getJSONObject("flightStatus");
            Set<String> flightAttributes = flight.getAttributes();
            // Fetch each attribute from the JSON object
            for (String attribute : flightAttributes) {
                if (attribute.equals("departureDate") || attribute.equals("arrivalDate")){
                    try {
                        date = format.parse(flightStatus.getJSONObject(attribute).getString("dateUtc"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    flight.set(attribute, viewFormat.format(date));
                }else {
                    flight.set(attribute, flightStatus.getString(attribute));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return flight;
    }

}