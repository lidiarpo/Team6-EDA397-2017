package se.chalmers.student.aviato;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

import se.chalmers.student.aviato.flights.Flight;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the Flight class
 */
public class FlightTest {
    Flight f;
    String [] someValidAttributes = {"carrierFsCode", "flightNumber", "departureAirportFsCode",
            "arrivalAirportFsCode", "departureDate", "arrivalDate"};
    String [] someInvalidAttributes = {"Attr1", "Attr2", "Attr3"};

    @Before
    public void initialize() {
        f = new Flight();
    }
    @Test
    public void initializedCorrectly() throws Exception {
        assertEquals(f.get("flightNumber"), Flight.NOT_AVAILABLE);
    }

    @Test
    public void getValidAttribute_returnsCorrectValue() {
        String expectedValue = "Stockholm";
        String validAttribute = someValidAttributes[1];
        f.set(validAttribute, expectedValue);
        assertEquals(f.get(validAttribute), expectedValue);
    }

    @Test
    public void getInvalidAttribute_returnsInvalidMessage() {
        String expectedValue = Flight.INVALID_ATTRIBUTE;
        String invalidAttribute = someInvalidAttributes[1];
        assertEquals(f.get(invalidAttribute), expectedValue);
    }

    @Test
    public void setValidAttribute_returnsOldValue() {
        String oldValue = "Goteborg";
        String newValue = "Stockholm";
        String validAttribute = someValidAttributes[1];
        f.set(validAttribute, oldValue);
        assertEquals(f.set(validAttribute, newValue), oldValue);
    }

    @Test
    public void setInvalidAttribte_returnsInvalidMessage() {
        String expectedValue = Flight.INVALID_ATTRIBUTE;
        String invalidAttribute = someInvalidAttributes[1];
        assertEquals(f.set(invalidAttribute, "Something"), expectedValue);
    }

    @Test
    public void setOfAttributes_returnedValid() {
        Set<String> attributes = f.getAttributes();
        for (String validAttribute : someValidAttributes) {
            assertTrue(attributes.contains(validAttribute));
        }
    }

    @Test
    public void setDate_arrivalDateIsReturnedCorrectly() {
        Flight f1 = new Flight();
        f1.set("flightId", "123");
        f1.set("flightNumber", "AO105");
        f1.set("arrivalAirportFsCode", "GOT");
        f1.set("departureAirportFsCode", "ARN");
        final int expectedHour = 14;
        final int expectedMin = 30;

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat apiDateFormat = new SimpleDateFormat(Utilities.API_DATE_FORMAT);

        cal.set(Calendar.HOUR_OF_DAY, expectedHour);
        cal.set(Calendar.MINUTE, expectedMin);
        String arrivalDate = apiDateFormat.format(cal.getTime());

        cal.set(Calendar.HOUR_OF_DAY, 13);
        cal.set(Calendar.MINUTE, 30);
        String departureDate = apiDateFormat.format(cal.getTime());

        f1.set("departureDate", departureDate);
        f1.set("arrivalDate", arrivalDate);

        Calendar result = f1.getTime();
        int resHour = result.get(Calendar.HOUR_OF_DAY);
        int resMin = result.get(Calendar.MINUTE);
        assertEquals(expectedHour, resHour);
        assertEquals(expectedMin, resMin);
    }

    @Test
    public void setDate_departureDateIsReturnedCorrectly() {
        Flight f1 = new Flight();
        f1.set("flightId", "123");
        f1.set("flightNumber", "AO105");
        f1.set("arrivalAirportFsCode", "ARN");
        f1.set("departureAirportFsCode", "GOT");
        final int expectedHour = 8;
        final int expectedMin = 30;

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat apiDateFormat = new SimpleDateFormat(Utilities.API_DATE_FORMAT);

        cal.set(Calendar.HOUR_OF_DAY, 6);
        cal.set(Calendar.MINUTE, 45);
        String arrivalDate = apiDateFormat.format(cal.getTime());

        cal.set(Calendar.HOUR_OF_DAY, expectedHour);
        cal.set(Calendar.MINUTE, expectedMin);
        String departureDate = apiDateFormat.format(cal.getTime());

        f1.set("departureDate", departureDate);
        f1.set("arrivalDate", arrivalDate);

        Calendar result = f1.getTime();
        int resHour = result.get(Calendar.HOUR_OF_DAY);
        int resMin = result.get(Calendar.MINUTE);
        assertEquals(expectedHour, resHour);
        assertEquals(expectedMin, resMin);
    }
}