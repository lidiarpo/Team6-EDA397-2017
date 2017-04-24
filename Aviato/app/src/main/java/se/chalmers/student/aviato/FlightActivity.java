package se.chalmers.student.aviato;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;


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


        //TODO: Make Flight attributes look differently in the listview now the Flight object is
        //TODO: just shown as a list, need to change activity_flight.xml

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

    //TODO: make OnItemClick method for the flightlistView

    @Override
    protected void onStart() {
        super.onStart();
        flightRequests  = new FlightRequests(this);
        //TO-DO Modify in order to select Departures

        Calendar rightNow = Calendar.getInstance();
        int timeWindow = 6;
        String airportCode = "GOT";

        flightRequests.getDepartures(airportCode, rightNow, timeWindow, this, listener, errorListener);
    }


    public void setFlights(ArrayList<Flight> result) {

        FlightAdapter adapter = new FlightAdapter(this, result);
        flightlistView.setAdapter(adapter);
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
    }


}
