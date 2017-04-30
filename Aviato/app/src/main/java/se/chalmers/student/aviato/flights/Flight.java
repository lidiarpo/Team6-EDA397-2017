package se.chalmers.student.aviato.flights;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import se.chalmers.student.aviato.Utilities;

/**
 *  This will be our result object
 *
 * Created by gryphex on 2017-04-04.
 */

public class Flight{
    //Attributes for our FlightObject
    private Map<String, String> flightAttributes = new HashMap<>();
    public final static String INVALID_ATTRIBUTE = "INVALID ATTRIBUTE";
    public final static String NOT_AVAILABLE = "N/A";
    private final String [] flightAttributeArray = {"flightId","carrierFsCode","carrierName", "flightNumber",
            "departureAirportFsCode", "arrivalAirportFsCode", "departureDate", "arrivalDate","status",
            "flightType","flightDurations", "departureTerminal","departureGate","arrivalTerminal",
            "arrivalGate","scheduledGateDeparture", "scheduledGateArrival","arrivalAirportName",
            "departureAirportName"};

    /**
     * The Flight class constructor that initializes the flight attributes
     */
    public Flight() {
        // Initialize the Flight attributes map with some default values
        for (String attribute : flightAttributeArray) {
            flightAttributes.put(attribute, NOT_AVAILABLE);
        }
    }

    /**
     *
     * @return a set of strings with the available attributes for the selected flight
     */
    public Set<String> getAttributes() {
        return flightAttributes.keySet();
    }

    /**
     * Get the specific attribute value from a flight
     * @param attributeKey the attribute that needs to be returned
     * @return the value of the requested attribute or "Invalid attribute" if attribute is not available
     */
    public String get(String attributeKey) {
        return flightAttributes.containsKey(attributeKey) ? flightAttributes.get(attributeKey) : INVALID_ATTRIBUTE;
    }

    /**
     * Set the specific flight attribute to a (new) value
     * @param attributeKey the flight attribute to be updated
     * @param attributeValue the new value of the attribute
     * @return the attribute's old value
     */
    public String set(String attributeKey, String attributeValue) {
        return flightAttributes.containsKey(attributeKey) ? updateValue(attributeKey, attributeValue) : INVALID_ATTRIBUTE;
    }

    /**
     * Get the time (date) of the flight as a Calendar instance
     * @return a calendar with the time of the arrival if this is an arrival or the departure otherwise
     */
    public Calendar getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(Utilities.API_DATE_FORMAT);
        try {
            // The date we are interested in depends on whether this is an arrival or a departure
            String date = this.isArrival() ? this.get("arrivalDate") : this.get("departureDate");
            // Try to parse the supplied string that represents a date
            Date d = sdf.parse(date);
            // If successful then set the appropriate date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            return calendar;
        } catch (ParseException ex) {
            // Should we throw an exception here and crash instead?
            Log.e("Flight", "Error parsing the supplied date");
        }
        // Return null to indicate a failure to parse the date
        return null;
    }

    /**
     * Check to see if this is an arrival or a departure, depending on the arrival airport's code
     * @return true if the flight is towards Gothenburg
     */
    public boolean isArrival(){
        return this.get("arrivalAirportFsCode").equals("GOT");
    }

    /**
     * Updates a key that is presumed to already exist and returns its old value
     * @param key the key to be updated
     * @param value the new value of the key
     * @return the old value of the key
     */
    private String updateValue(String key, String value) {
        String oldVal = flightAttributes.get(key);
        flightAttributes.put(key, value);
        return oldVal;
    }

    /**
     * Stringify all the flight attributes for debugging purposes
     * @return a textual representation of all the flight attributes
     */
    @Override
    public String toString() {
        Set<String> attributes = getAttributes();
        String stringifiedAttributes = "Flight{";
        for (String attribute : attributes){
            stringifiedAttributes += attribute + "='" + flightAttributes.get(attribute) + "',";
        }
        // Let's replace the last comma with a curly bracket and return it
        return stringifiedAttributes.substring(0, stringifiedAttributes.length()-1) + "}";
    }
}
