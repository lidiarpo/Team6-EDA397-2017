package se.chalmers.student.aviato;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class FlightActivity extends Activity{

    //This is our main array it will hold our flight data:
    ArrayList<Flight> arrayoftheFlightData = new ArrayList<Flight>();

    private Response.Listener<JSONObject> listener;
    private Response.ErrorListener errorListener;
    private FlightRequests flightRequests;

    ListView flightlistView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);

        // The listview to populate
        flightlistView = (ListView) findViewById(R.id.lvFlightContainer);

       /* try {
            //Using the ArrayList "flight_array" to temporarily hold our json rows, for each row we return
            //until we use the array to create our FlightObject
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            FlightParser fp = new FlightParser();
            arrayoftheFlightData = fp.parseFlights(obj);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //log out the final object after the JSON parse for debugging purposes
        Log.d("arrayoftheFlightData", arrayoftheFlightData.toString());


        //TODO: Make Flight attributes look differently in the listview now the Flight object is
        //TODO: just shown as a list, need to change activity_flight.xml
        //Populate listview with the Flight object
        FlightAdapter adapter = new FlightAdapter(this, arrayoftheFlightData);
        flightlistView.setAdapter(adapter);*/

        listener  = new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                FlightParser fp = new FlightParser();
                Log.d("RESPONSE",response.toString());
                setFlights(new FlightParser().parseFlights(response));
            }
        };

        errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR","Volley Error");
            }
        };
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

    @Override
    protected void onStart() {
        super.onStart();
        flightRequests  = new FlightRequests(this);
        //TO-DO Modify in order to select Departures
        flightRequests.getDepartures("GOT", 2017, 4, 5, 21, 1, this, listener, errorListener);
    }


    public void setFlights(ArrayList<Flight> result) {

        FlightAdapter adapter = new FlightAdapter(this, result);
        flightlistView.setAdapter(adapter);

    }


}
