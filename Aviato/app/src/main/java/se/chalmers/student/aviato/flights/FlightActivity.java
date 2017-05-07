package se.chalmers.student.aviato.flights;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import se.chalmers.student.aviato.R;
import se.chalmers.student.aviato.subscriptions.SubscriptionReceiver;


public class FlightActivity extends Activity{

    private Response.Listener<JSONObject> listener;
    private Response.ErrorListener errorListener;
    private FlightRequests flightRequests;
    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    Button btnFilters;
    ListView flightlistView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    PopupWindow popupWindow;
    TextView header, airlineHeader;
    RadioButton arrivalBtn, departureBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        flightlistView = (ListView) findViewById(R.id.lvFlightContainer);
        flightlistView.setClickable(true);
        btnFilters = (Button) findViewById(R.id.btnFilters);
        btnFilters.setClickable(true);
        initListeners();

        //scheduleAlarm();
    }

    private void initListeners() {
        btnFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //Inflate the view from a predefined XML layout
                View popupView = inflater.inflate(R.layout.filters_dialog, null);
                // create a 300px width and 470px height PopupWindow
                popupWindow = new PopupWindow(popupView, 900, 1070, true);
                // display the popup in the center
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

                popupWindow.setTouchable(true);
                popupWindow.setFocusable(true);

                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
                header = (TextView) popupView.findViewById(R.id.filtersText);
                arrivalBtn = (RadioButton) popupView.findViewById(R.id.arrivalRadioBtn);
                departureBtn = (RadioButton) popupView.findViewById(R.id.departureRadioBtn);
                airlineHeader = (TextView) popupView.findViewById(R.id.airlineFilter);
            }
        });
        flightlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Flight flight = (Flight)flightlistView.getItemAtPosition(position);

                Intent loadOverviewActivity = new Intent(FlightActivity.this, OverviewActivity.class);

                loadOverviewActivity.putExtra("flight", flight.toString());
                startActivity(loadOverviewActivity);

            }
        });



        refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(){
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
     * @param flights the arrayList with Flights objects
     */
    public void setFlights(List<Flight> flights) {
        Collections.sort(flights, new Comparator<Flight>() {
            @Override
            public int compare(Flight f1, Flight f2) {
                return f1.get("departureDate").compareTo(f2.get("departureDate"));
            }
        });
        FlightAdapter adapter = new FlightAdapter(this, flights);
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