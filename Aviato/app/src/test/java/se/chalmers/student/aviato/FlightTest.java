package se.chalmers.student.aviato;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

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
}