package se.chalmers.student.aviato.flights;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Calendar;

import static se.chalmers.student.aviato.Utilities.APPID;
import static se.chalmers.student.aviato.Utilities.APPKEY;

public class FlightRequests {

    private static String TAG = "FlightRequests";
    private RequestQueueSingleton queue;
    private Context context;

    public FlightRequests(Context context) {
        this.context = context;
    }

    public void getArrivals(String airportCode, Calendar calendar, int timeWindow, Context context,
                            Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {

        queue = RequestQueueSingleton.getInstance(context);

        String url = "https://api.flightstats.com/flex/flightstatus/rest/v2/json/airport/status/GOT/arr/"
                + Integer.toString(calendar.get(Calendar.YEAR)) + "/" + Integer.toString(calendar.get(Calendar.MONTH) + 1) + "/" + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH))
                + "/" + Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)) + "?appId=" + APPID +
                "&appKey=" + APPKEY + "&utc=false&numHours=" + Integer.toString(timeWindow) + "&extendedOptions=useInlinedReferences";

        Log.d(TAG, url);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, listener, errorListener);

        queue.addToRequestQueue(jsObjRequest);
    }

    public void getDepartures(String airportCode, Calendar calendar, int timeWindow, Context context,
                              Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {

        queue = RequestQueueSingleton.getInstance(context);
        String url = "https://api.flightstats.com/flex/flightstatus/rest/v2/json/airport/status/GOT/dep/"
                + Integer.toString(calendar.get(Calendar.YEAR)) + "/" + Integer.toString(calendar.get(Calendar.MONTH) + 1) + "/" + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH))
                + "/" + Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)) + "?appId=" + APPID +
                "&appKey=" + APPKEY + "&utc=false&numHours=" + Integer.toString(timeWindow) + "&extendedOptions=useInlinedReferences";

        Log.d(TAG, url);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, listener, errorListener);

        queue.addToRequestQueue(jsObjRequest);
    }

    public void getFlightStatus(String flightId, Context context,
                                Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {

        queue = RequestQueueSingleton.getInstance(context);
        String url = "https://api.flightstats.com/flex/flightstatus/rest/v2/json/flight/status/"
                + flightId + "?appId=" + APPID + "&appKey=" + APPKEY + "&extendedOptions=useInlinedReferences";;

        Log.d(TAG, url);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, listener, errorListener);

        queue.addToRequestQueue(jsObjRequest);
    }
}
