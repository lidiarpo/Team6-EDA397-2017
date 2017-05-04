package se.chalmers.student.aviato.flights;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import se.chalmers.student.aviato.DB.FlightsDbHelper;
import se.chalmers.student.aviato.DB.SubscriptionsCRUD;
import se.chalmers.student.aviato.R;
import se.chalmers.student.aviato.Utilities;

import static se.chalmers.student.aviato.Utilities.API_DATE_FORMAT;
import static se.chalmers.student.aviato.Utilities.VIEW_DATE_FORMAT;


public class OverviewActivity extends Activity {

    private String flight;
    HashMap<String, String> data;
    FlightsDbHelper mDbHelper;
    SubscriptionsCRUD subscriptionsCRUD;



    TextView tvAirlineName;
    TextView tvStatus;
    TextView tvFlightNumber;
    TextView tvSource;
    TextView tvDepFrom;
    TextView tvDepTime;
    TextView tvDepGate;
    TextView tvDepTerminal;
    TextView tvDestination;
    TextView tvArrFrom;
    TextView tvArrTime;
    TextView tvArrGate;
    TextView tvArrTerminal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_overview);

        createView();



        final Button btnSubscription = (Button) findViewById(R.id.btnSubscribe);
        btnSubscription.setClickable(true);
        btnSubscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Context context = getApplicationContext();
        CharSequence text = "You have Subscribed to this flight";
        int duration = Toast.LENGTH_SHORT;
        String flightIDtoCheck;

            // Perform action on click
            if(v.getId() == R.id.btnSubscribe)
            {
                Toast toast = Toast.makeText(context, text, duration);
                mDbHelper = new FlightsDbHelper(context);
                subscriptionsCRUD = new SubscriptionsCRUD(mDbHelper);

                //Find the flightID of clicked flight
                String flightString = getFlightObject().toString();
                Pattern pattern = Pattern.compile("flightId='(.*?)'");
                Matcher matcher = pattern.matcher(flightString);
                if (matcher.find()) {
                    flightIDtoCheck = matcher.group(1);
                    Boolean check = comparetoDB(flightIDtoCheck);
                    // if clicked flight ID is found in the DB
                    if(check.equals(true)){
                        Toast toast2 = Toast.makeText(context, "Already subscribed to this flight!", duration);
                        toast2.show();
                    }else {
                        //Add that flight to subscription
                        subscriptionsCRUD.addSubscription(getFlightObject());
                        toast.show();
                    }
                }
            }
            }
        });


        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            flight = extras.getString("flight");
            flight = flight.substring(7);
            Log.d("Flight", flight);

            //tvAirlineName.setText("TEST!!!!");

            //HashMap<String, String> data = new HashMap<String, String>();
            data = new HashMap<String, String>();
            StringTokenizer tokenizer = new StringTokenizer(flight, ",");
            while(tokenizer.hasMoreTokens()) {
                StringTokenizer s = new StringTokenizer(tokenizer.nextToken(), "='");
                String attribute = s.nextToken();
                String value = s.nextToken();
                //Log.d("Attribute", attribute);
                //Log.d("Value", value);

                data.put(attribute, value);

            }

            populateOverview(data);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    private void createView() {

        tvAirlineName = (TextView) findViewById(R.id.tvAirlineName);
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        //tvFlightNumber = (TextView) findViewById(R.id.tvFlightNumber);
        tvSource = (TextView) findViewById(R.id.tvSource);
        tvDepTime = (TextView) findViewById(R.id.tvDepTime);
        tvDepGate = (TextView) findViewById(R.id.tvDepGate);
        tvDepTerminal = (TextView) findViewById(R.id.tvDepTerminal);
        tvDestination = (TextView) findViewById(R.id.tvDestination);
        tvArrTime = (TextView) findViewById(R.id.tvArrTime);
        tvArrGate = (TextView) findViewById(R.id.tvArrGate);
        tvArrTerminal = (TextView) findViewById(R.id.tvArrTerminal);
    }

    //Populate activity_overview xml

    private void populateOverview(HashMap<String, String> flight) {

        SimpleDateFormat format = new SimpleDateFormat(API_DATE_FORMAT);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat viewFormat = new SimpleDateFormat(VIEW_DATE_FORMAT);
        Calendar cal = Calendar.getInstance();
        viewFormat.setTimeZone(cal.getTimeZone());

        tvAirlineName.setText("(" + flight.get("carrierFsCode") + ")"+ " " +flight.get("carrierName")
                + " " + flight.get("flightNumber"));
        //tvFlightNumber.setText(flight.get("flightNumber"));
        String status = flight.get("status");
        tvStatus.setBackgroundColor(Utilities.getStatusColor(status));
        tvStatus.setText(Utilities.getStatusName(status));
        tvSource.setText(flight.get("departureAirportFsCode") + "  -  " + flight.get("departureAirportName"));
        tvDepGate.setText(flight.get("departureGate"));
        tvDepTerminal.setText(flight.get("departureTerminal"));
        tvDestination.setText(flight.get("arrivalAirportFsCode") + "  -  " + flight.get("arrivalAirportName"));


        try {
            tvDepTime.setText(viewFormat.format(format.parse(flight.get("departureDate"))));
            tvArrTime.setText(viewFormat.format(format.parse(flight.get("arrivalDate"))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvArrGate.setText(flight.get("arrivalGate"));
        tvArrTerminal.setText(flight.get("arrivalTerminal"));

    }
    public Flight getFlightObject(){
        Flight flightOverview = new Flight();
        flightOverview.set("flightId",data.get("flightId"));
        flightOverview.set("carrierFsCode",data.get("carrierFsCode"));
        flightOverview.set("flightNumber",data.get("flightNumber"));
        flightOverview.set("departureAirportFsCode",data.get("departureAirportFsCode"));
        flightOverview.set("arrivalAirportFsCode",data.get("arrivalAirportFsCode"));
        flightOverview.set("departureDate",data.get("departureDate"));
        flightOverview.set("arrivalDate",data.get("arrivalDate"));
        flightOverview.set("status",data.get("status"));
        flightOverview.set("flightType",data.get("flightType"));
        flightOverview.set("flightDurations",data.get("flightDurations"));
        flightOverview.set("departureTerminal",data.get("departureTerminal"));
        flightOverview.set("departureGate",data.get("Flight{departureGate"));
        flightOverview.set("arrivalTerminal",data.get("arrivalTerminal"));
        flightOverview.set("arrivalGate",data.get("arrivalGate"));


        return flightOverview;

    }


    boolean contains;
    public Boolean comparetoDB(String id){
        List<Flight> subscriptionFlights = subscriptionsCRUD.readSubscriptions();
        for (Flight f : subscriptionFlights) {
            if (f.get("flightId").equals(id)) {
                contains = true;
                break;
            }
        }
        if(contains) {
            // contains the id in the database
        }else{
            // does not contain the id in the database
            contains = false;
        }
        return contains;
    }

}