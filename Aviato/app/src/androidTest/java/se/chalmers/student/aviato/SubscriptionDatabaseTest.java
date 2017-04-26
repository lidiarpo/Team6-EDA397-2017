package se.chalmers.student.aviato;

import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import se.chalmers.student.aviato.DB.FlightsContract;
import se.chalmers.student.aviato.DB.FlightsDbHelper;
import se.chalmers.student.aviato.DB.SubscriptionsCRUD;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SubscriptionDatabaseTest {

    private FlightsDbHelper database;
    private SubscriptionsCRUD databaseCRUD;
    private Flight flight1,flight2;

    private final String FLIGHT_ID1 =  "882280723";
    private final String FLIGHT_ID2 =  "882280724";


    private void deleteAll(){
        database.getWritableDatabase().delete(FlightsContract.FlightEntry.TABLE_NAME,null,null);
    }


    @Before
    public void setup() throws Exception{
        getTargetContext().deleteDatabase(FlightsDbHelper.DATABASE_NAME);
        database = new FlightsDbHelper(getTargetContext());
        databaseCRUD = new SubscriptionsCRUD(database);

        flight1 = new Flight();
        flight1.set("flightId",FLIGHT_ID1);
        flight1.set("carrierFsCode","FR");
        flight1.set("flightNumber","4713");
        flight1.set("departureAirportFsCode","GOT");
        flight1.set("arrivalAirportFsCode","BCN");
        flight1.set("departureDate","2017-04-26T11:15:00.000Z");
        flight1.set("arrivalDate","2017-04-26T16:15:00.000Z");
        flight1.set("status","S");
        flight1.set("flightType","J");
        flight1.set("flightDurations","57");
        flight1.set("departureTerminal","3");
        flight1.set("departureGate","G");
        flight1.set("arrivalTerminal","2");
        flight1.set("arrivalGate","D");

        flight2 = new Flight();
        flight2.set("flightId",FLIGHT_ID2);
        flight2.set("carrierFsCode","IB");
        flight2.set("flightNumber","5647");
        flight2.set("departureAirportFsCode","GOT");
        flight2.set("arrivalAirportFsCode","PRS");
        flight2.set("departureDate","2017-04-26T11:15:00.000Z");
        flight2.set("arrivalDate","2017-04-26T16:15:00.000Z");
        flight2.set("status","S");
        flight2.set("flightType","J");
        flight2.set("flightDurations","57");
        flight2.set("departureTerminal","3");
        flight2.set("departureGate","G");
        flight2.set("arrivalTerminal","2");
        flight2.set("arrivalGate","D");

    }

    @After
    public void tearDown() throws Exception{
        deleteAll();
        database.close();
    }

    @Test
    public void testDeleteAll() throws Exception {
        databaseCRUD.addSubscription(flight1);
        List<Flight> flightsList = databaseCRUD.readSubscriptions();
        assertThat(flightsList.size(),is(1));
        deleteAll();
        flightsList = databaseCRUD.readSubscriptions();
        assertThat(flightsList.size(),is(0));
    }

    @Test
    public void shouldAddFlightSubscription() throws Exception {
        databaseCRUD.addSubscription(flight1);
        databaseCRUD.addSubscription(flight2);
        List<Flight> flightsList = databaseCRUD.readSubscriptions();
        assertThat(flightsList.size(),is(2));
        deleteAll();
    }

    @Test
    public void testUpdateFlightSubscription() throws Exception {
        flight1.set("status","C");
        databaseCRUD.updateSubscription(flight1);
        List<Flight> flightsList = databaseCRUD.readSubscriptions();
        assertEquals(flightsList.get(0).get("status"),"C");
        flight1.set("status","S");
        deleteAll();
    }

    @Test
    public void testDeleteSubscription() throws Exception {
        databaseCRUD.addSubscription(flight1);
        databaseCRUD.addSubscription(flight2);
        List<Flight> flightsList = databaseCRUD.readSubscriptions();
        assertThat(flightsList.size(),is(2));
        databaseCRUD.deleteSubscription(FLIGHT_ID1);
        flightsList = databaseCRUD.readSubscriptions();
        assertThat(flightsList.size(),is(1));
        assertEquals(flightsList.get(0).get("flightId"),FLIGHT_ID2);
        deleteAll();
    }

    @Test
    public void testExistFlight() throws Exception {
        databaseCRUD.addSubscription(flight1);
        databaseCRUD.addSubscription(flight2);
        assertTrue(databaseCRUD.existsFlight(flight1));
        deleteAll();
    }


}
