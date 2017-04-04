package se.chalmers.student.aviato;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import static com.android.volley.VolleyLog.TAG;

public class FlightRequests {

    private RequestQueueSingleton queue;
    private Context context;

    public FlightRequests(Context cOntext) {
        this.context = context;
    }

    public void getArrivals(String airportCode, int year, int month, int day, int hour, int timeWindow, Context context,
                            Response.Listener<JSONObject>listener, Response.ErrorListener errorListener){
            queue = RequestQueueSingleton.getInstance(context);

        String url = "https://api.flightstats.com/flex/flightstatus/rest/v2/json/airport/status/GOT/dep/"
                + Integer.toString(year)+  "/" + Integer.toString(month)+ "/" + Integer.toString(day)
                + "/" + Integer.toString(hour) + "?appId="+ Utilities.APPID +
                "&appKey=" + Utilities.APPKEY + "&utc=false&numHours=" + Integer.toString(timeWindow);

        Log.d("GET", url);

            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, listener, errorListener);

            queue.addToRequestQueue(jsObjRequest);
        }

    public void getDepartures(String airportCode, int year, int month, int day, int hour, int timeWindow, Context context,
                                     Response.Listener<JSONObject>listener, Response.ErrorListener errorListener){

        queue = RequestQueueSingleton.getInstance(context);
        String url = "https://api.flightstats.com/flex/flightstatus/rest/v2/json/airport/status/GOT/arr/"
                + Integer.toString(year)+  "/" + Integer.toString(month)+ "/" + Integer.toString(day)
                + "/" + Integer.toString(hour) + "?appId="+ Utilities.APPID +
                "&appKey=" + Utilities.APPKEY + "&utc=false&numHours=" + Integer.toString(timeWindow);

        Log.d("GET", url);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, listener, errorListener);

        queue.addToRequestQueue(jsObjRequest);
    }
    }
