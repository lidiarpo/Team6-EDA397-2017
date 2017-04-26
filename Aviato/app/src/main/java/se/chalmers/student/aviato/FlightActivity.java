package se.chalmers.student.aviato;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;
import android.support.v4.widget.SwipeRefreshLayout;

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
    private SwipeRefreshLayout.OnRefreshListener refreshListener;

    ListView flightlistView;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);

        // The listview to populate
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        flightlistView = (ListView) findViewById(R.id.lvFlightContainer);


        flightlistView.setClickable(true);

        flightlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Flight flight = (Flight)flightlistView.getItemAtPosition(position);

                //setContentView(R.layout.flight_overview);
                //OverviewAdapter adapter = new OverviewAdapter( , arrayoftheFlightData);

                Intent loadOverviewActivity = new Intent(FlightActivity.this, OverviewActivity.class);

                loadOverviewActivity.putExtra("flight", flight.toString());
                startActivity(loadOverviewActivity);

            }
        });



        refreshListener = new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                flightRequests  = new FlightRequests(getApplicationContext());
                //TO-DO Modify in order to select Departures
                Calendar rightNow = Calendar.getInstance();
                int timeWindow = 6;
                String airportCode = "GOT";
                flightRequests.getDepartures(airportCode, rightNow, timeWindow, getApplicationContext(), listener, errorListener);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        };
        mSwipeRefreshLayout.setOnRefreshListener(refreshListener);

        listener  = new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                FlightParser fp = new FlightParser();
                //Log.d("RESPONSE",response.toString());
                setFlights(new FlightParser().parseFlights(response));
            }
        };

        errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Log.e("ERROR","Volley Error");
            }
        };

        scheduleAlarm();
    }

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

    /**
     * Set the flights in the adapter of the listview
     * @param result the arrayList with Flights objects
     */
    public void setFlights(ArrayList<Flight> result) {
        FlightAdapter adapter = new FlightAdapter(this, result);
        flightlistView.setAdapter(adapter);
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
    }

    /**
     * Schedules an alarm to launch a service in the background to update
     * flight information from subscriptions and create notifications
     */
    public void scheduleAlarm() {
        Intent intent = new Intent(getApplicationContext(), SubscriptionReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, SubscriptionReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long firstMillis = System.currentTimeMillis();
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                AlarmManager.INTERVAL_FIFTEEN_MINUTES, pIntent);
    }
}