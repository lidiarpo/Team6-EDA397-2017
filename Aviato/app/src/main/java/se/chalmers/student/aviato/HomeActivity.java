package se.chalmers.student.aviato;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;


public class HomeActivity extends Activity {

    private static final String TAG = "HomeActivity";
    private RequestQueueSingleton queue;

    public JSONObject flightArrivals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    @Override
    protected void onStart() {
        super.onStart();
        getArrivals();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    private void getArrivals(){
        queue = RequestQueueSingleton.getInstance(this);

        String url = "https://api.flightstats.com/flex/flightstatus/rest/v2/json/airport/status/GOT/arr/2017/3/31/16?appId="+ Utilities.APPID +
                "&appKey=" + Utilities.APPKEY + "&utc=false&numHours=6";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        flightArrivals = response;
                        Log.d(TAG,"Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        queue.addToRequestQueue(jsObjRequest);
    }
}
