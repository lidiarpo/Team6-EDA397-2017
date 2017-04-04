package se.chalmers.student.aviato;


import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.List;


public class FlightParser {

    private FlightRequests flightRequests;
    private Context context;
    private Response.Listener<JSONObject> listener;
    private Response.ErrorListener errorListener;

    public FlightParser(Context context) {

        this.flightRequests = new FlightRequests();
        this.context = context;

        listener  = new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                retrieveFlights(response);
            }
        };

        errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }
        };


    }


   public List<Flight> retrieveFlights(JSONObject response){


       flightRequests.getArrivals("GOT", 2017, 4, 4, 16, 6, context, listener, errorListener);


       flightRequests.getDepartures("GOT", 2017, 4, 4, 16, 6, context, listener, errorListener);

       //TO-DO Create a Flight List parsing the JSONObject received and pass

       return null;

   }


}
