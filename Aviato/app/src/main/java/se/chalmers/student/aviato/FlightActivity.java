package se.chalmers.student.aviato;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class FlightActivity extends Activity {

    //This is our main array it will hold our flight data:
    ArrayList<Flight> arrayoftheFlightData = new ArrayList<Flight>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);

        // The listview to populate
        ListView flightlistView = (ListView) findViewById(R.id.list);

        try {
            //Using the ArrayList "flight_array" to temporarily hold our json rows, for each row we return
            //until we use the array to create our FlightObject
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray flight_array = obj.getJSONArray("Flights");

            for(int i=0; i < flight_array.length() ; i++) {
                //This is our flight data
                JSONObject flight_obj = flight_array.getJSONObject(i);
                //Create a new flight
                Flight resultRow = new Flight();
                //set the  flights attributes
                resultRow.AirlineAlias = flight_obj.getString("AirlineAlias");
                resultRow.AirlineName = flight_obj.getString("AirlineName");
                resultRow.FlightNumber = flight_obj.getString("FlightNumber");
                resultRow.SourceAirport = flight_obj.getString("SourceAirport");
                resultRow.DestinationAirport = flight_obj.getString("DestinationAirport");
                resultRow.DestinationName = flight_obj.getString("DestinationName");
                resultRow.DepTime = flight_obj.getString("DepTime");
                resultRow.ArrTime = flight_obj.getString("ArrTime");

                arrayoftheFlightData.add(resultRow);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //log out the final object after the JSON parse for debugging purposes
        //System.out.println(arrayoftheFlightData);


        //TODO: Make Flight attributes look differently in the listview now the Flight object is
        //TODO: just shown as a list, need to change activity_flight.xml
        //Populate listview with the Flight object
        FlightAdapter adapter = new FlightAdapter(this, arrayoftheFlightData);
        flightlistView.setAdapter(adapter);
    }

    //Read the json file
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("flights_mockup.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    //TODO: make OnItemClick method for the flightlistView

}
