package se.chalmers.student.aviato;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import se.chalmers.student.aviato.flights.Flight;

public class Utilities {

    public final static String APPID = "0a1973b0";
    public final static String APPKEY = "18c1fc7152a12bcf3f8be5ebb3fd34f2";

    public final static String API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public final static String VIEW_DATE_FORMAT = "HH:mm";

    private static long timeToNotify = 3600000; // How much time (in ms) to notify prior to a flight

    /**
     * Get the amount of time prior to a flight that a user should be notified about it
     * @return time in milliseconds
     */
    public static long getTimeToNotify() {
        return timeToNotify;
    }

    /**
     * Set the amount of time prior to a flight that a user should be notified about it
     * @param newTime in milliseconds for the user to be notified for a flight
     * @return the previous time in milliseconds that the user used to be notified about a flight
     */
    public static long setTimeToNotify(long newTime) {
        long prevTime = timeToNotify;
        timeToNotify = newTime;
        return prevTime;
    }

    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * Generate a list of dummy flights that can be used for testing purposes
     * @return a list of dummy flights
     */
    public static List<Flight> getDummyFlights() {
        final long ONE_MINUTE = 1000 * 60;
        final long ONE_HOUR = ONE_MINUTE * 60;

        List<Flight> dummyFlights = new ArrayList<Flight>();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat apiDateFormat = new SimpleDateFormat(Utilities.API_DATE_FORMAT);
        Random rnd = new Random();
        rnd.setSeed(now.getTimeInMillis());


        Flight f1 = new Flight();
        f1.set("flightId", String.valueOf(rnd.nextInt(1000)));
        f1.set("flightNumber", "AO105");
        f1.set("arrivalAirportFsCode", "GOT");
        f1.set("departureAirportFsCode", "ARN");
        Calendar arrival = Calendar.getInstance();
        arrival.setTimeInMillis(now.getTimeInMillis() + 50 * ONE_MINUTE); // Arriving in 50 minutes from now
        String arrivalDate = apiDateFormat.format(arrival.getTime());
        Calendar departure = Calendar.getInstance();
        departure.setTimeInMillis(now.getTimeInMillis() - ONE_HOUR);
        String departureDate = apiDateFormat.format(departure.getTime());
        f1.set("departureDate", departureDate);
        f1.set("arrivalDate", arrivalDate);

        Flight f2 = new Flight();
        f2.set("flightId", String.valueOf(rnd.nextInt(1000)));
        f2.set("flightNumber", "SAS123");
        f2.set("arrivalAirportFsCode", "ARN");
        f2.set("departureAirportFsCode", "GOT");
        arrival.setTimeInMillis(now.getTimeInMillis() + 2 * ONE_HOUR);
        arrivalDate = apiDateFormat.format(arrival.getTime());
        departure.setTimeInMillis(now.getTimeInMillis() + 55 * ONE_MINUTE); // Departing in 55 minutes from now
        departureDate = apiDateFormat.format(departure.getTime());
        f2.set("departureDate", departureDate);
        f2.set("arrivalDate", arrivalDate);

        Flight f3 = new Flight();
        f3.set("flightId", String.valueOf(rnd.nextInt(1000)));
        f3.set("flightNumber", "BA129299");
        f3.set("arrivalAirportFsCode", "BEJ");
        f3.set("departureAirportFsCode", "GOT");
        arrival.setTimeInMillis(now.getTimeInMillis() + 11 * ONE_HOUR);
        arrivalDate = apiDateFormat.format(arrival.getTime());
        departure.setTimeInMillis(now.getTimeInMillis() + 65 * ONE_MINUTE); // Departing in 65 minutes from now
        departureDate = apiDateFormat.format(departure.getTime());
        f3.set("departureDate", departureDate);
        f3.set("arrivalDate", arrivalDate);

        Flight f4 = new Flight();
        f4.set("flightId", String.valueOf(rnd.nextInt(1000)));
        f4.set("flightNumber", "AEGEAN29299");
        f4.set("arrivalAirportFsCode", "GOT");
        f4.set("departureAirportFsCode", "ATH");
        arrival.setTimeInMillis(now.getTimeInMillis() + 70 * ONE_MINUTE); //Arriving in 1 hour and 10 minutes from now
        arrivalDate = apiDateFormat.format(arrival.getTime());
        departure.setTimeInMillis(now.getTimeInMillis() - 4 * ONE_HOUR);
        departureDate = apiDateFormat.format(departure.getTime());
        f4.set("departureDate", departureDate);
        f4.set("arrivalDate", arrivalDate);

        dummyFlights.add(f1);
        dummyFlights.add(f2);
        dummyFlights.add(f3);
        dummyFlights.add(f4);

        return dummyFlights;
    }
}
