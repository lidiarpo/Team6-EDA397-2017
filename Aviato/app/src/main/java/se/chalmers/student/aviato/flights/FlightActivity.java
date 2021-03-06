package se.chalmers.student.aviato.flights;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import se.chalmers.student.aviato.R;
import se.chalmers.student.aviato.subscriptions.SubscriptionReceiver;


public class FlightActivity extends Activity{

    private Response.Listener<JSONObject> listener, filterListener;
    private Response.ErrorListener errorListener;
    private FlightRequests flightRequests;
    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    Button btnFilters, applyBtn, cancelBtn;
    ListView flightlistView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    PopupWindow popupWindow;
    EditText timeWindowFilter;
    TextView header, airlineHeader;
    CheckBox arrivalBtn, departureBtn;
    Spinner airlineSpinner;
    String airline = "";
    int timeWindow = 6;
    List<Flight> unfilteredFlights;
    List<Flight> filteredFlights;

    private static String TAG = "FlightActivity";

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


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.flightsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.refresh:
                setFlights(new ArrayList<Flight>());
                findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
                flightRequests  = new FlightRequests(getApplicationContext());
                Calendar rightNow = Calendar.getInstance();
                int timeWindow = 6;
                String airportCode = "GOT";
                unfilteredFlights=null;
                flightRequests.getDepartures(airportCode, rightNow, timeWindow, getApplicationContext(), listener, errorListener);
                flightRequests.getArrivals(airportCode, rightNow, timeWindow, getApplicationContext(), listener, errorListener);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initListeners() {
        btnFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //Inflate the view from a predefined XML layout
                final View popupView = inflater.inflate(R.layout.filters_dialog, null);
                // create a 300px width and 470px height PopupWindow
                popupWindow = new PopupWindow(popupView, 1000, 1200, true);
                // display the popup in the center
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

                popupWindow.setTouchable(true);
                popupWindow.setFocusable(true);

                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
                header = (TextView) popupView.findViewById(R.id.filtersText);
                arrivalBtn = (CheckBox) popupView.findViewById(R.id.arrivalRadioBtn);
                departureBtn = (CheckBox) popupView.findViewById(R.id.departureRadioBtn);
                airlineHeader = (TextView) popupView.findViewById(R.id.airlineFilter);
                applyBtn = (Button) popupView.findViewById(R.id.applyBtn);
                airlineSpinner = (Spinner) popupView.findViewById(R.id.spinnerAirline);
                timeWindowFilter = (EditText)popupView.findViewById(R.id.timeWindowFilter);
                timeWindowFilter.setFilters(new InputFilter[]{new TimeWindowInputFilter(1, 6)});

                applyBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        filteredFlights = new ArrayList<Flight>();
                        filteredFlights.addAll(unfilteredFlights);
                        timeWindow = 6;
                        airline = "";
                        airline = airlineSpinner.getSelectedItem().toString();
                        if (airline.equals("Select an airline")){
                            airline = "";
                        }
                        else{
                            int begin = airline.indexOf("(");
                            int end = airline.indexOf(")");
                            airline = airline.substring(begin + 1, end);
                            AirlineFilter af = new AirlineFilter();
                            filteredFlights = af.filter(filteredFlights,airline);
                        }

                        String inputWindow = timeWindowFilter.getText().toString();
                        if(inputWindow == null || inputWindow.trim().equals("")){
                            timeWindow = 6;
                        }
                        else {
                            timeWindow = Integer.parseInt(inputWindow);
                            TimeWindowFilter tf = new TimeWindowFilter();
                            filteredFlights = tf.filter(filteredFlights,inputWindow);
                        }

                        flightRequests  = new FlightRequests(getApplicationContext());

                        if(!arrivalBtn.isChecked() && !departureBtn.isChecked()){
                            filteredFlights = new ArrayList<Flight>();
                        }
                        else if(!arrivalBtn.isChecked() && departureBtn.isChecked()){
                            List<Flight> flightResult = new ArrayList<Flight>();

                            for (Flight flight: filteredFlights) {
                                if (flight.get("departureAirportFsCode").equals("GOT")){
                                    flightResult.add(flight);
                                }

                            }
                            filteredFlights = flightResult;
                        }
                        else if(arrivalBtn.isChecked() && !departureBtn.isChecked()){
                            List<Flight> flightResult = new ArrayList<Flight>();

                            for (Flight flight: filteredFlights) {
                                if (flight.get("arrivalAirportFsCode").equals("GOT")){
                                    flightResult.add(flight);
                                }

                            }
                            filteredFlights = flightResult;
                        }
                        setFlights(filteredFlights);
                        popupWindow.dismiss();

                    }
                });
                cancelBtn = (Button) popupView.findViewById(R.id.cancelbutton);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
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
                unfilteredFlights=null;
                flightRequests.getDepartures(airportCode, rightNow, timeWindow, getApplicationContext(), listener, errorListener);
                flightRequests.getArrivals(airportCode, rightNow, timeWindow, getApplicationContext(), listener, errorListener);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        };
        mSwipeRefreshLayout.setOnRefreshListener(refreshListener);

        listener  = new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                FlightParser fp = new FlightParser();
                if (unfilteredFlights!=null) {
                    unfilteredFlights.addAll(new FlightParser().parseFlights(response));
                }else{
                    unfilteredFlights = new FlightParser().parseFlights(response);
                }
                setFlights(unfilteredFlights);
                findViewById(R.id.loadingPanel).setVisibility(View.INVISIBLE);
            }
        };

        filterListener  = new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                FlightParser fp = new FlightParser();
                if(!airline.isEmpty()) {
                    AirlineFilter airlineFilter = new AirlineFilter();
                    filteredFlights.addAll(airlineFilter.filter(new FlightParser().parseFlights(response), airline));
                }
                else{
                    filteredFlights.addAll(new FlightParser().parseFlights(response));
                }
                setFlights(filteredFlights);
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
        unfilteredFlights = null;
        flightRequests.getDepartures(airportCode, rightNow, timeWindow, this, listener, errorListener);
        flightRequests.getArrivals(airportCode, rightNow, timeWindow, this, listener, errorListener);
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
        findViewById(R.id.loadingPanel).setVisibility(View.INVISIBLE);
        flightlistView.setEmptyView(findViewById(R.id.empty));
    }

}