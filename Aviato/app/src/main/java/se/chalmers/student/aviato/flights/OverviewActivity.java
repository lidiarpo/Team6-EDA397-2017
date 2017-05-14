package se.chalmers.student.aviato.flights;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TimeZone;

import se.chalmers.student.aviato.DB.FlightsDbHelper;
import se.chalmers.student.aviato.DB.SubscriptionsCRUD;
import se.chalmers.student.aviato.R;
import se.chalmers.student.aviato.Utilities;

import static se.chalmers.student.aviato.Utilities.API_DATE_FORMAT;
import static se.chalmers.student.aviato.Utilities.VIEW_DATE_FORMAT;


public class OverviewActivity extends Activity {

    private String flight;
    private Flight flightObject;
    HashMap<String, String> data;
    FlightsDbHelper mDbHelper;
    SubscriptionsCRUD subscriptionsCRUD;
    private static String TAG = "OverviewActivity";

    Button btnSubscription;

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
        mDbHelper = new FlightsDbHelper(this);
        subscriptionsCRUD = new SubscriptionsCRUD(mDbHelper);

        initListeners();

        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            flight = extras.getString("flight");
            flight = flight.substring(7);

            data = new HashMap<String, String>();
            StringTokenizer tokenizer = new StringTokenizer(flight, ",");
            while(tokenizer.hasMoreTokens()) {
                StringTokenizer s = new StringTokenizer(tokenizer.nextToken(), "='");
                String attribute = s.nextToken().trim();
                String value = s.nextToken().trim();
                data.put(attribute, value);
            }

            flightObject = getFlightObject();
            populateOverview(flightObject);

            if (subscriptionsCRUD.existsFlight(flightObject)){
                btnSubscription.setVisibility(View.GONE);
            }

        }
    }

    private void initListeners(){

        btnSubscription.setClickable(true);
        btnSubscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast toast;

                // Perform action on click
                if(v.getId() == R.id.btnSubscribe)
                {
                    if(subscriptionsCRUD.existsFlight(flightObject)){
                        toast = Toast.makeText(context, "Already subscribed to this flight!", Toast.LENGTH_SHORT);
                        toast.show();
                    }else {
                        //Add that flight to subscription
                        subscriptionsCRUD.addSubscription(getFlightObject());
                        toast = Toast.makeText(context, "You have been subscribed to this flight", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    btnSubscription.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    private void createView() {

        tvAirlineName = (TextView) findViewById(R.id.tvAirlineName);
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        tvSource = (TextView) findViewById(R.id.tvSource);
        tvDepTime = (TextView) findViewById(R.id.tvDepTime);
        tvDepGate = (TextView) findViewById(R.id.tvDepGate);
        tvDepTerminal = (TextView) findViewById(R.id.tvDepTerminal);
        tvDestination = (TextView) findViewById(R.id.tvDestination);
        tvArrTime = (TextView) findViewById(R.id.tvArrTime);
        tvArrGate = (TextView) findViewById(R.id.tvArrGate);
        tvArrTerminal = (TextView) findViewById(R.id.tvArrTerminal);

        btnSubscription = (Button) findViewById(R.id.btnSubscribe);
    }


    private void populateOverview(Flight flight) {

        SimpleDateFormat format = new SimpleDateFormat(API_DATE_FORMAT);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat viewFormat = new SimpleDateFormat(VIEW_DATE_FORMAT);
        Calendar cal = Calendar.getInstance();
        viewFormat.setTimeZone(cal.getTimeZone());

        tvAirlineName.setText("(" + flight.get("carrierFsCode") + ")"+ " " +flight.get("carrierName")
                + " " + flight.get("flightNumber"));
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
        flightOverview.set("carrierName",data.get("carrierName"));
        flightOverview.set("scheduledGateArrival",data.get("scheduledGateArrival"));
        flightOverview.set("scheduledGateDeparture",data.get("scheduledGateDeparture"));
        flightOverview.set("arrivalAirportName",data.get("arrivalAirportName"));
        flightOverview.set("departureAirportName",data.get("departureAirportName"));
        flightOverview.set("flightNumber",data.get("flightNumber"));
        flightOverview.set("departureAirportFsCode",data.get("departureAirportFsCode"));
        flightOverview.set("arrivalAirportFsCode",data.get("arrivalAirportFsCode"));
        flightOverview.set("departureDate",data.get("departureDate"));
        flightOverview.set("arrivalDate",data.get("arrivalDate"));
        flightOverview.set("status",data.get("status"));
        flightOverview.set("flightType",data.get("flightType"));
        flightOverview.set("flightDurations",data.get("flightDurations"));
        flightOverview.set("departureTerminal",data.get("departureTerminal"));
        flightOverview.set("departureGate",data.get("departureGate"));
        flightOverview.set("arrivalTerminal",data.get("arrivalTerminal"));
        flightOverview.set("arrivalGate",data.get("arrivalGate"));

        return flightOverview;

    }

}